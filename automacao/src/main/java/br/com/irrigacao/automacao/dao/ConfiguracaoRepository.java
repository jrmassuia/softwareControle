package br.com.irrigacao.automacao.dao;

import java.io.Serializable;

import org.hibernate.Query;

import br.com.irrigacao.automacao.entity.Configuracao;

public class ConfiguracaoRepository extends GenericRepository<Configuracao> implements Serializable {

	private static final long serialVersionUID = 1L;

	public Configuracao consultar() {
		Query query = getSession().createQuery("SELECT c FROM Configuracao c");
		return (Configuracao) query.uniqueResult();
	}

}
