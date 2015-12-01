package br.com.irrigacao.automacao.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.irrigacao.automacao.entity.SensorUmidadeSolo;
import br.com.irrigacao.automacao.service.GenericService;
import br.com.irrigacao.automacao.service.SensorUmidadeSoloService;

@Named
@ConversationScoped
public class SensorUmidadeSoloController extends GenericController<SensorUmidadeSolo> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SensorUmidadeSoloService service;

	private LineChartModel animatedModel1;

	@Override
	public void setService(GenericService<SensorUmidadeSolo> service) {
		super.setService(this.service);
	}

	@Override
	public GenericService<SensorUmidadeSolo> getService() {
		return this.service;
	}

	private void createAnimatedModels() {

		animatedModel1 = initLinearModel();
		animatedModel1.setTitle("Umidade do Solo");
		animatedModel1.setAnimate(true);
		animatedModel1.setLegendPosition("se");
		Axis yAxis = animatedModel1.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(100);

	}

	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		LineChartSeries lineChartSeries = new LineChartSeries();
		lineChartSeries.setLabel("Data Leitura");

		List<SensorUmidadeSolo> intervalorDeDias = service.consultarIntervaloLeitura();

		int cont = 0;

		for (SensorUmidadeSolo sensorUmidadeSolo : intervalorDeDias) {
			lineChartSeries.set(cont++, sensorUmidadeSolo.getUmidade());
			model.addSeries(lineChartSeries);
		}

		return model;
	}

	public LineChartModel getAnimatedModel1() {
		createAnimatedModels();
		return animatedModel1;
	}

	public void setAnimatedModel1(LineChartModel animatedModel1) {
		this.animatedModel1 = animatedModel1;
	}

}
