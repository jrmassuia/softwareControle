package br.com.irrigacao.automacao.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.irrigacao.automacao.entity.Configuracao;
import br.com.irrigacao.automacao.enumeration.EnumProxy;
import br.com.irrigacao.automacao.service.ConfiguracaoService;

@Named
@SessionScoped
public class SessionController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ConfiguracaoService configuracaoService;

	public static Configuracao configuracao;

	@PostConstruct
	public void init() {
		configuracao = configuracaoService.consultar();
		if (configuracao == null) {
			configuracao = new Configuracao();
		}
	}

	public void configurarConexaoRede() {

		if (configuracao.getPossuiProxy() == EnumProxy.COM_PROXY.getProxy()) {

			System.getProperties().put("http.proxyHost", configuracao.getHost());
			System.getProperties().put("http.proxyPort", configuracao.getPorta());
			System.getProperties().put("http.proxyUser", configuracao.getUsuario());
			System.getProperties().put("http.proxyPassword", configuracao.getSenha());

		} else {
			System.getProperties().put("http.proxyHost", "");
			System.getProperties().put("http.proxyPort", "");
			System.getProperties().put("http.proxyUser", "");
			System.getProperties().put("http.proxyPassword", "");
		}
	}

}
