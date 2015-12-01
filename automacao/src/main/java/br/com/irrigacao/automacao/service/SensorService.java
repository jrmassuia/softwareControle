package br.com.irrigacao.automacao.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import org.primefaces.model.chart.MeterGaugeChartModel;

import br.com.irrigacao.automacao.connection.ConexaoWifi;
import br.com.irrigacao.automacao.controller.SessionController;
import br.com.irrigacao.automacao.dao.GenericRepository;
import br.com.irrigacao.automacao.dao.SensorRepository;
import br.com.irrigacao.automacao.entity.Sensor;
import br.com.irrigacao.automacao.entity.SensorUmidadeSolo;
import br.com.irrigacao.automacao.enumeration.EnumChaveArduino;
import br.com.irrigacao.automacao.enumeration.EnumSituacaoSolo;
import br.com.irrigacao.automacao.enumeration.EnumUmidadeSolo;
import br.com.irrigacao.automacao.enumeration.EnumValidaAcoes;

public class SensorService extends GenericService<Sensor> implements Serializable {

	private static final double ZERO = 0.0;

	private static final long serialVersionUID = 1L;

	@Inject
	private SensorRepository dao;

	@Inject
	private ConexaoWifi conexaoWifi;

	@Inject
	private SensorUmidadeSoloService sensorUmidadeSoloService;

	@Inject
	private TempoService tempoService;

	@Override
	public GenericRepository<Sensor> getRepository() {
		return this.dao;
	}

	public String enviaDados(String parametro) {
		return conexaoWifi.enviaDados(parametro);
	}

	public MeterGaugeChartModel consultarUmidadeSoloUm(Double umidade) {
		return sensorUmidadeSoloService.consultarUmidadeSoloUm(umidade);
	}

	public MeterGaugeChartModel consultarUmidadeSoloDois(Double umidade) {
		return sensorUmidadeSoloService.consultarUmidadeSoloDois(umidade);
	}

	public Sensor converterStringParaObjeto(String leituraSensor) {

		Sensor sensor = dao.consultaSensor();

		if (leituraSensor == "") {
			leituraSensor = "0";
		}

		Double umidade = Double.parseDouble(leituraSensor);

		if (umidade < ZERO) {
			umidade = ZERO;
		}

		if (sensor.getId() != null) {
			sensor.getSensorUmidadeSolo().setUmidade(Double.parseDouble(leituraSensor));
		} else {

			sensor.getSensorUmidadeSolo().setUmidade(umidade);

		}

		return sensor;

	}

	public Sensor consultarSensores() {

		// String consulta =
		// enviaDados(EnumChaveArduino.CONSULTAR_SENSOR.getDescricao());

		String consulta = "500";

		if (consulta != null && consulta != "") {

			Sensor sensor = converterStringParaObjeto(consulta);
			verificarSituacaoDoSolo(sensor.getSensorUmidadeSolo());
			verificarIntervaloDeDias(sensor);
			verificarProximaChuva(sensor);
			sensor.setDataLeitura(getHoje());
			sensor.getSensorUmidadeSolo().setSensor(sensor);

			return sensor;

		} else {

			return null;
		}
	}

	private void verificarProximaChuva(Sensor sensor) {

		sensor.setProximaChuva(tempoService.verificarQuandoVaiChover());
	}

	private void verificarIntervaloDeDias(Sensor sensor) {

		sensor.setIntervaloDia(tempoService.verificarIntervaloDeDias());

	}

	private void verificarSituacaoDoSolo(SensorUmidadeSolo sensorUmidadeSolo) {

		if (sensorUmidadeSolo.getUmidade() <= EnumUmidadeSolo.SECO.getUmidadeSolo()) {

			sensorUmidadeSolo.setSituacaoSolo(EnumSituacaoSolo.SECO.getDescricao());

		} else {

			sensorUmidadeSolo.setSituacaoSolo(EnumSituacaoSolo.UMIDO.getDescricao());

		}

	}

	public Integer consultarQuantidadeDeDias(Integer intervaloDeDias) {
		return dao.consultarQuantidadeDeDias(intervaloDeDias);
	}

	public String ligarBomba() {
		return enviaDados(EnumChaveArduino.LIGAR_BOMBA.getDescricao());
	}

	public Sensor executarTarefa() {

		Sensor sensor = consultarSensores();

		if (sensor != null) {
			verificarUmidadeDoSolo(sensor);
		}

		return sensor;

	}

	private void verificarUmidadeDoSolo(Sensor sensor) {

		if (sensor.getSensorUmidadeSolo().getSituacaoSolo() == EnumSituacaoSolo.SECO.getDescricao()) {

			// Solo Seco
			// Iniciar Irrigação

			salvar(sensor);
			verificarSoloSeco(SessionController.configuracao.getQuantidadeDia(), sensor);

		} else {

			// Solo Umido
			// Não fazer nada - retornar mensagem com solo umido

			salvar(sensor);

		}

	}

	private void verificarSoloSeco(Integer intervaloDeDias, Sensor sensor) {

		if (SessionController.configuracao.getValidaAcoes().equals(EnumValidaAcoes.SIM)) {

			int qtdDias = consultarQuantidadeDeDias(intervaloDeDias);

			if (qtdDias > intervaloDeDias) {
				// Inicia irrigação
				iniciarIrrigacao(sensor);

			} else {
				// Consulta Previsão do tempo
				boolean verficarSeVaiChover = tempoService.verficarSeVaiChover(intervaloDeDias);

				if (verficarSeVaiChover) {

					iniciarIrrigacao(sensor);

				}
			}
		} else {

			iniciarIrrigacao(sensor);

		}

	}

	private void iniciarIrrigacao(Sensor sensor) {

		sensor.setHoraInicio(getHoje());

		ligarBomba();

		sensor.setHoraFim(getHoje());

		salvar(sensor);
	}

	public Date getHoje() {
		return new Date();
	}

	public Sensor consultarUltimaIrrigacao() {
		return dao.consultarUltimaIrrigacao();
	}

	public String verificarQuandoVaiChover() {
		return tempoService.verificarQuandoVaiChover();
	}
}
