package br.com.irrigacao.automacao.service;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;

import com.thoughtworks.xstream.XStream;

import br.com.irrigacao.atutomacao.wrapper.Chanel;
import br.com.irrigacao.atutomacao.wrapper.Item;
import br.com.irrigacao.automacao.controller.SessionController;
import br.com.irrigacao.automacao.dao.GenericRepository;
import br.com.irrigacao.automacao.dao.TempoRepository;
import br.com.irrigacao.automacao.entity.Tempo;

public class TempoService extends GenericService<Tempo> implements Serializable {

	private static final double ZERO = 0.0;

	private static final long serialVersionUID = 1L;

	@Inject
	protected SessionController sessionController;

	@Inject
	private TempoRepository dao;

	@Override
	public GenericRepository<Tempo> getRepository() {
		return this.dao;
	}

	public List<Tempo> consultarPrevisaoTempo() {

		try {
			// String urlTempoAgora =
			// "http://www.tempoagora.com.br/rss/cidades/rss_prev_CampoGrande-MS.xml";
			URL url = new URL("http://www.climatempo.com.br/rss/capitais.xml");
			InputStream input = url.openStream();
			String strXml = IOUtils.toString(input, "utf-8");
			input.close();

			XStream xStream = new XStream();
			xStream.processAnnotations(Chanel.class);
			xStream.alias("channel", Chanel.class);

			Chanel chanel = (Chanel) xStream.fromXML(strXml);
			criarListaString(chanel);

			return chanel.getPrevisaoTempo();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void criarListaString(Chanel chanel) {
		for (Item item : chanel.getItem()) {
			for (String string : item.getDescription().split("\n")) {
				if (!string.isEmpty()) {
					item.getDescriptions().add(string);
					if (item.getTitle().contains("campogrande")) {
						chanel.getPrevisaoTempo().add(converterStrinParaObjeto(string));
					}
				}
			}
		}

	}

	private static Tempo converterStrinParaObjeto(String string) {

		Tempo tempo = new Tempo();

		try {

			tempo.setDiaSemana(string.substring(6, string.indexOf(" -")));

			String data = string.substring(string.indexOf("-") + 2, string.indexOf("</b>"));
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
			cal.setTime(sdf.parse(data));
			tempo.setDataDia(cal);

			tempo.setTemperaturaMinima(string.substring(string.indexOf(") ") + 9, string.indexOf(" /")));

			tempo.setTemperaturaMaxima(string.substring(string.indexOf("Máx:") + 5, string.indexOf(" / P")));

			String precChuva = string.substring(string.indexOf("Prec: ") + 6, string.indexOf("mm"));
			tempo.setPrecChuva(Double.parseDouble(precChuva));

			String probChuva = string.substring(string.indexOf("Prob: ") + 6, string.indexOf("%"));
			tempo.setProbChuva(Double.parseDouble(probChuva));

			tempo.setSituacao(string.substring(string.indexOf("Condição: ") + 10, string.indexOf("<br />")));

		} catch (Exception e) {
			e.getMessage();
		}
		return tempo;
	}

	public boolean verficarSeVaiChover(Integer intervaloDeDias) {

		boolean chuva = false;
		int cont = 0;

		for (Tempo tempo : consultarPrevisaoTempo()) {
			if (tempo.getProbChuva() > ZERO) {
				chuva = true;
				break;
			} else if (cont == intervaloDeDias) {
				break;
			}
			cont++;
		}

		return chuva;
	}

	public String verificarQuandoVaiChover() {
		for (Tempo tempo : consultarPrevisaoTempo()) {
			if (tempo.getProbChuva() > ZERO) {
				return tempo.getDiaSemana();
			}
		}
		return "";
	}

	public Integer verificarIntervaloDeDias() {

		int qtdDias = 0;

		for (Tempo tempo : consultarPrevisaoTempo()) {

			if (tempo.getProbChuva() > ZERO && qtdDias < SessionController.configuracao.getQuantidadeDia()) {

				qtdDias++;

			}

		}

		return qtdDias;
	}

}
