package br.com.irrigacao.automacao.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.primefaces.model.chart.MeterGaugeChartModel;

import br.com.irrigacao.automacao.dao.GenericRepository;
import br.com.irrigacao.automacao.dao.SensorUmidadeSoloRepository;
import br.com.irrigacao.automacao.entity.SensorUmidadeSolo;

public class SensorUmidadeSoloService extends GenericService<SensorUmidadeSolo> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SensorUmidadeSoloRepository dao;

	@Override
	public GenericRepository<SensorUmidadeSolo> getRepository() {
		return this.dao;
	}

	public MeterGaugeChartModel consultarUmidadeSoloUm(Double umidade) {

		MeterGaugeChartModel graficoUmidadeAr;

		graficoUmidadeAr = initMeterGaugeModel(umidade);
		graficoUmidadeAr.setTitle("Umidade do Solo");
		graficoUmidadeAr.setSeriesColors("3b6df7,3b6df7,3b6df7,3b6df7");
		graficoUmidadeAr.setGaugeLabel(umidade + " %");
		graficoUmidadeAr.setLabelHeightAdjust(80);
		graficoUmidadeAr.setIntervalOuterRadius(70);

		return graficoUmidadeAr;

	}

	public MeterGaugeChartModel consultarUmidadeSoloDois(Double umidade) {

		MeterGaugeChartModel graficoUmidadeAr;

		graficoUmidadeAr = initMeterGaugeModel(umidade);
		graficoUmidadeAr.setTitle("Umidade do Solo 2");
		graficoUmidadeAr.setSeriesColors("3b6df7,3b6df7,3b6df7,3b6df7");
		graficoUmidadeAr.setGaugeLabel(umidade + " %");
		graficoUmidadeAr.setLabelHeightAdjust(65);
		graficoUmidadeAr.setIntervalOuterRadius(55);

		return graficoUmidadeAr;

	}

	private MeterGaugeChartModel initMeterGaugeModel(Double umidade) {
		List<Number> intervals = new ArrayList<Number>() {
			private static final long serialVersionUID = 1L;

			{
				add(25);
				add(50);
				add(75);
				add(100);
			}
		};

		return new MeterGaugeChartModel(umidade, intervals);
	}

	public List<SensorUmidadeSolo> consultarIntervaloLeitura() {		
		return dao.consultarIntervaloLeitura();
	}

}
