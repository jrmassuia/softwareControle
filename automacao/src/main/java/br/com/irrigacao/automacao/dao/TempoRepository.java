package br.com.irrigacao.automacao.dao;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import br.com.irrigacao.automacao.entity.Tempo;

public class TempoRepository extends GenericRepository<Tempo> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	protected void addOrdem(Criteria criteria) {
		criteria.addOrder(Order.asc("id"));
	}

}
