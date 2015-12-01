package br.com.irrigacao.automacao.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;

import br.com.irrigacao.automacao.entity.Sensor;
import br.com.irrigacao.automacao.entity.SensorUmidadeSolo;
import br.com.irrigacao.automacao.enumeration.EnumSituacaoSolo;

public class SensorRepository extends GenericRepository<Sensor> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	protected void addOrdem(Criteria criteria) {
		criteria.addOrder(Order.asc("id"));
	}

	public Integer consultarQuantidadeDeDias(Integer intervaloDeDias) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getHoje());
		calendar.add(Calendar.DATE, -intervaloDeDias);

		StringBuilder hql = new StringBuilder();

		hql.append(" SELECT distinct sensor.dataLeitura FROM SensorUmidadeSolo sensorUmidade ");
		hql.append(" LEFT JOIN sensorUmidade.sensor sensor ");
		hql.append(" where sensorUmidade.situacaoSolo = :situacaoSolo ");
		hql.append(" AND  sensor.dataLeitura BETWEEN :dataIncial AND :dataFinal  GROUP BY sensor.dataLeitura ");

		Query query = getSession().createQuery(hql.toString());
		query.setParameter("situacaoSolo", EnumSituacaoSolo.SECO.getDescricao());
		query.setParameter("dataIncial", calendar.getTime());
		query.setParameter("dataFinal", new Date());

		return query.list().size();
	}

	public Sensor consultaSensor() {

		StringBuilder hql = new StringBuilder();

		hql.append(" SELECT sensor FROM Sensor sensor ");
		hql.append(" LEFT JOIN FETCH sensor.sensorUmidadeSolo sensorUmidadeSolo ");
		hql.append(" WHERE sensor.dataLeitura= :dataLeitura ");

		Query query = getSession().createQuery(hql.toString());
		query.setParameter("dataLeitura", getHoje());

		Sensor sensor = (Sensor) query.uniqueResult();

		if (sensor == null) {
			sensor = new Sensor();
			sensor.setSensorUmidadeSolo(new SensorUmidadeSolo());
			return sensor;
		}

		return sensor;
	}

	public Date getHoje() {
		return new Date();
	}

	public Sensor consultarUltimaIrrigacao() {

		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT sensor FROM Sensor sensor ");
		hql.append(" WHERE sensor.dataLeitura = ");
		hql.append(" (SELECT MAX(sensorData.dataLeitura) FROM Sensor sensorData) ");

		Query query = getSession().createQuery(hql.toString());

		return (Sensor) query.uniqueResult();
	}

}
