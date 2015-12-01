package br.com.irrigacao.automacao.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;

import br.com.irrigacao.automacao.entity.SensorUmidadeSolo;

public class SensorUmidadeSoloRepository extends GenericRepository<SensorUmidadeSolo> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	protected void addOrdem(Criteria criteria) {
		criteria.addOrder(Order.asc("id"));
	}

	@SuppressWarnings("unchecked")
	public List<SensorUmidadeSolo> consultarIntervaloLeitura() {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getHoje());
		calendar.add(Calendar.DATE, -10);

		StringBuilder hql = new StringBuilder();

		hql.append(" SELECT sensorUmidade FROM SensorUmidadeSolo sensorUmidade ");
		hql.append(" LEFT JOIN sensorUmidade.sensor sensor ");
		hql.append(" where sensor.dataLeitura BETWEEN :dataIncial AND :dataFinal");

		Query query = getSession().createQuery(hql.toString());
		query.setParameter("dataIncial", calendar.getTime());
		query.setParameter("dataFinal", getHoje());

		return query.list();

	}

	private Date getHoje() {
		return new Date();
	}

}
