package br.com.irrigacao.automacao.controller;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.irrigacao.automacao.entity.Configuracao;
import br.com.irrigacao.automacao.entity.Sensor;
import br.com.irrigacao.automacao.service.GenericService;
import br.com.irrigacao.automacao.service.SensorService;

@Named
@ConversationScoped
public class SensorController extends GenericController<Sensor> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Sensor sensorUltimoIrrigacaoRealizada;

	@Inject
	private SensorService service;

	@Override
	public void setService(GenericService<Sensor> service) {
		super.setService(this.service);
	}

	@Override
	public GenericService<Sensor> getService() {
		return this.getService();
	}

	public void consultarSensores() {
		this.objeto = service.consultarSensores();

		if (this.objeto == null) {
			this.messages.addErrorMessage("Erro ao realizar leitura do sensor !!!");
		}
	}

	public void executarTarefa() {
		this.objeto = service.executarTarefa();
		if (this.objeto == null) {
			this.messages.addErrorMessage("Erro ao realizar leitura do sensor !!!");
		}
	}

	public Sensor getSensorUltimoIrrigacaoRealizada() {
		this.setSensorUltimoIrrigacaoRealizada(service.consultarUltimaIrrigacao());
		return sensorUltimoIrrigacaoRealizada;
	}

	public void setSensorUltimoIrrigacaoRealizada(Sensor ensorUltimoIrrigacaoRealizada) {
		this.sensorUltimoIrrigacaoRealizada = ensorUltimoIrrigacaoRealizada;
	}

	public String getVerificarQuandoVaiChover() {
		return service.verificarQuandoVaiChover();
	}

	public Configuracao configuracao() {
		return SessionController.configuracao;
	}
}
