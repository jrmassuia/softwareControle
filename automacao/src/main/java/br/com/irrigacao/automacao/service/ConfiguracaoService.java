package br.com.irrigacao.automacao.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.irrigacao.automacao.dao.ConfiguracaoRepository;
import br.com.irrigacao.automacao.dao.GenericRepository;
import br.com.irrigacao.automacao.entity.Configuracao;

public class ConfiguracaoService extends GenericService<Configuracao> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ConfiguracaoRepository dao;

	@Override
	public GenericRepository<Configuracao> getRepository() {
		return this.dao;
	}

	public Configuracao consultar() {		
		return this.dao.consultar();
	}

}
