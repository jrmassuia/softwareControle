package br.com.irrigacao.automacao.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.irrigacao.automacao.entity.Configuracao;
import br.com.irrigacao.automacao.enumeration.EnumLeitorXml;
import br.com.irrigacao.automacao.enumeration.EnumProxy;
import br.com.irrigacao.automacao.enumeration.EnumValidaAcoes;
import br.com.irrigacao.automacao.service.ConfiguracaoService;
import br.com.irrigacao.automacao.service.GenericService;

@Named
@ConversationScoped
public class ConfiguracaoController extends GenericController<Configuracao> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ConfiguracaoService service;

	@PostConstruct
	public void init() {
		this.objeto = SessionController.configuracao;
	}

	@Override
	public void setService(GenericService<Configuracao> service) {
		super.setService(this.service);
	}

	@Override
	public GenericService<Configuracao> getService() {
		return this.service;
	}

	public EnumLeitorXml[] getListaLeituraXml() {
		return EnumLeitorXml.values();
	}

	public EnumProxy[] getListaProxy() {
		return EnumProxy.values();
	}

	public EnumValidaAcoes[] getListaValidaAcoes() {
		return EnumValidaAcoes.values();
	}

	public String salvar() {
		try {
			if (this.objeto != null) {
				this.objeto = getService().salvar(this.objeto);
				messages.addInfoMessage("Registro salvo com sucesso!");
			}
		} catch (Exception e) {
			messages.addErrorMessage("Falha na inserção: " + e.getMessage());
		}

		return "";
	}

}
