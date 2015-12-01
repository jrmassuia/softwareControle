package br.com.irrigacao.automacao.schedule;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import br.com.irrigacao.automacao.service.SensorService;

@Singleton
public class AgendaShedule {

	@Inject
	private SensorService sensorService;

	// @Schedule(second = "*/1", minute = "*", hour = "8-17", persistent =
	// false)
	@Schedule(minute = "*", hour = "7", persistent = false)
	public void executarTarefa() {

		sensorService.executarTarefa();

	}

}
