package br.com.irrigacao.automacao.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.irrigacao.automacao.entity.Tempo;
import br.com.irrigacao.automacao.service.GenericService;
import br.com.irrigacao.automacao.service.TempoService;

@Named
@ConversationScoped
public class TempoController extends GenericController<Tempo> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TempoService service;

	private List<Tempo> listaTempo;

	@Override
	public void setService(GenericService<Tempo> service) {
		super.setService(this.service);
	}

	@Override
	public GenericService<Tempo> getService() {
		return this.service;
	}
//
//	public void salvarListaTempo() {
//		if (service.salvarTodos(listaTempo) != null) {
//			messages.addInfoMessage("Registro salvo com sucesso!");
//		} else {
//			messages.addErrorMessage("Falha na inserção!");
//		}
//	}

	public List<Tempo> getListaTempo() {
		if (listaTempo == null) {
			listaTempo = service.consultarPrevisaoTempo();
		}
		return listaTempo;
	}

	public void setListaTempo(List<Tempo> listaTempo) {
		this.listaTempo = listaTempo;
	}

}
