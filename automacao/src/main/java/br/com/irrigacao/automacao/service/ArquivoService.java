package br.com.irrigacao.automacao.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.irrigacao.automacao.dao.GenericRepository;
import br.com.irrigacao.automacao.dao.ArquivoRepository;
import br.com.irrigacao.automacao.entity.Arquivo;

public class ArquivoService extends GenericService<Arquivo> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ArquivoRepository dao;

	@Override
	public GenericRepository<Arquivo> getRepository() {
		return this.dao;
	}

}
