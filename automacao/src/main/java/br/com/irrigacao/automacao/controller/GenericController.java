package br.com.irrigacao.automacao.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Conversation;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.irrigacao.automacao.enumeration.EnumEstadoFormulario;
import br.com.irrigacao.automacao.service.GenericService;
import br.com.irrigacao.automacao.tools.Tools;

public abstract class GenericController<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	protected GenericMessages messages;

	@Inject
	protected Conversation conversation;

	private GenericService<T> service;

	private LazyDataModel<T> lazyDataModel;

	protected EnumEstadoFormulario estadoFormulario = EnumEstadoFormulario.VISUALIZAR;

	protected T objeto;

	/**
	 * @author romualdo.massuia
	 * 
	 *         Método para iniciar um novo cadastro
	 * 
	 */
	public String novo() {
		this.estadoFormulario = EnumEstadoFormulario.INSERIR;
		try {
			antesNovo();
			this.objeto = newInstanceEntity();
			depoisNovo();
		} catch (Exception e) {
			messages.addErrorMessage("Falha na criação: " + e.getMessage());
		}
		return getNomeFormularioForm();
	}

	/**
	 * Método para criar uma nova instancia da entidade
	 * 
	 * @return entidade criada
	 */
	@SuppressWarnings("unchecked")
	public T newInstanceEntity() {

		try {
			return (T) Tools.getTNewInstance(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author romualdo.massuia
	 * @return retorna formulário de cadastro
	 * 
	 *         Método para persistir ou alterar objeto
	 */
	public String salvar() {
		try {
			if (this.objeto != null) {
				antesSalvar();
				this.objeto = getService().salvar(this.objeto);
				messages.addInfoMessage("Registro salvo com sucesso!");
				depoisSalvar();
				this.estadoFormulario = EnumEstadoFormulario.VISUALIZAR;
			}
		} catch (Exception e) {
			messages.addErrorMessage("Falha na inserção: " + e.getMessage());
		}

		return getNomeFormularioList();
	}

	/**
	 * @author romualdo.massuia
	 * @return retorna formulário de cadastro
	 * 
	 *         Método para deletar objeto
	 * 
	 */
	public void deletar() {

		if (this.objeto != null) {
			try {
				antesDeletar();
				getService().deletar(objeto);
				depoisDeletar();
				messages.addInfoMessage("Registro excluído com sucesso!");
			} catch (Exception e) {
				messages.addErrorMessage("Falha na exclusão: " + e.getMessage());
			}
		} else {
			messages.addWarnMessage("Selecione um registro para deletar");
		}

	}

	/**
	 * @author romualdo.massuia
	 * @return retorna formulário de cadastro
	 * 
	 *         Método para mudar estado do formulário
	 * 
	 */
	public String editar() {

		if (this.objeto != null) {
			antesEditar();
			this.estadoFormulario = EnumEstadoFormulario.EDITAR;
			depoisEditar();
			return getNomeFormularioForm();
		} else {
			messages.addWarnMessage("Selecione um registro para editar");
			return getNomeFormularioList();
		}

	}

	/**
	 * @author romualdo.massuia
	 * @return retorna fomulário de cadastro
	 * 
	 *         Método para mudar estado do formulário
	 * 
	 */
	public String visualizar() {

		if (this.objeto != null) {
			antesVisualizar();
			this.estadoFormulario = EnumEstadoFormulario.VISUALIZAR;
			depoisVisualizar();
			return getNomeFormularioForm();
		} else {
			messages.addWarnMessage("Selecione um registro para visualizar");
			return getNomeFormularioList();
		}
	}

	/**
	 * Método que cancela a inserção ou alteração de dados
	 * 
	 * @return String para retornar a página de listagem
	 */
	public String cancelar() {

		this.estadoFormulario = EnumEstadoFormulario.VISUALIZAR;

		return getNomeFormularioList();
	}

	/**
	 * @author romualdo.massuia
	 *
	 *         Método para implentar depois de visualizar um objeto
	 *
	 */
	protected void depoisVisualizar() {

	}

	/**
	 * @author romualdo.massuia
	 *
	 *         Método para implentar antes de visualizar um objeto
	 *
	 */
	protected void antesVisualizar() {

	}

	/**
	 * @author romualdo.massuia
	 *
	 *         Método para implentar depois de editar um objeto
	 *
	 */
	protected void depoisEditar() {

	}

	/**
	 * @author romualdo.massuia
	 *
	 *         Método para implentar antes de editar um objeto
	 *
	 */
	protected void antesEditar() {

	}

	/**
	 * @author romualdo.massuia
	 *
	 *         Método para implentar antes de deletar um objeto
	 *
	 */
	protected void antesDeletar() {

	}

	/**
	 * @author romualdo.massuia
	 *
	 *         Método para implentar depois de deletar um objeto
	 *
	 */
	protected void depoisDeletar() {

	}

	/**
	 * @author romualdo.massuia
	 *
	 *         Método para implentar depois de um novo objeto
	 *
	 */
	protected void depoisNovo() {

	}

	/**
	 * @author romualdo.massuia
	 *
	 *         Método para implentar antes de um novo objeto
	 *
	 */
	protected void antesNovo() {

	}

	/**
	 * @author romualdo.massuia
	 *
	 *         Método para implentar depois de salvar um objeto
	 *
	 */
	protected void depoisSalvar() {

	}

	/**
	 * @author romualdo.massuia
	 *
	 *         Método para implentar antes de salvar um objeto
	 *
	 */
	protected void antesSalvar() {

	}

	/**
	 * @author romualdo.massuia
	 * 
	 *         Método para iniciar uma nova conversacão
	 */
	public void iniciarConversacao() {

		if (conversation == null)
			return;
		if (!FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()) {
			conversation.setTimeout(1800000L); // 30 minutos
			conversation.begin();
		}

	}

	/**
	 * @author romualdo.massuia
	 * 
	 *         Método para finalizar uma conversacão
	 */
	public void finalizarConversacao() {

		if (conversation == null)
			return;
		try {
			conversation.end();
		} catch (Exception e) {
			e.printStackTrace();
			messages.addErrorMessage("Falha ao finalizar conversação: " + e.getMessage());
		}

	}

	/**
	 * @author romualdo.massuia
	 * 
	 *         Método para reiniciar uma conversacão
	 */
	public void reiniciarConversasao() {
		finalizarConversacao();
		iniciarConversacao();
	}

	/**
	 * @author romualdo.massuia
	 * @return retorna o formulário em que está
	 * 
	 *         Método para verificar o nome do fomulário em que está
	 */
	public String getNomeFormularioForm() {

		if (this.objeto != null) {
			String classe = this.objeto.getClass().getSimpleName();
			classe = classe.substring(0, 1).toLowerCase().concat(classe.substring(1));
			return classe + "Form";
		} else {
			return null;
		}

	}

	/**
	 * @author romualdo.massuia
	 * @return retorna o formulário em que está
	 * 
	 *         Método para verificar o nome do fomulário em que está
	 */
	public String getNomeFormularioList() {

		if (this.objeto != null) {
			String classe = this.objeto.getClass().getSimpleName();
			classe = classe.substring(0, 1).toLowerCase().concat(classe.substring(1));
			return classe + "List";
		} else {
			return null;
		}

	}

	public Boolean getHabilitaComponentes() {

		if (getEstadoFormulario().equals(EnumEstadoFormulario.VISUALIZAR)) {
			return true;
		} else if (getEstadoFormulario().equals(EnumEstadoFormulario.EDITAR)) {
			return false;
		} else if (getEstadoFormulario().equals(EnumEstadoFormulario.INSERIR)) {
			return false;
		}

		return true;
	}

	public Boolean getHabilitaEditarInserir() {
		if (getEstadoFormulario().equals(EnumEstadoFormulario.EDITAR) || getEstadoFormulario().equals(EnumEstadoFormulario.INSERIR)) {
			return true;
		}
		return false;
	}

	public Boolean getHabilitaVisualizarCancelar() {
		if (getEstadoFormulario().equals(EnumEstadoFormulario.VISUALIZAR)) {
			return true;
		}
		return false;
	}

	public void consultarListaObjetosPaginada() {

		this.lazyDataModel = new LazyDataModel<T>() {

			private static final long serialVersionUID = 1L;

			List<T> listaObjetos = new ArrayList<>();

			@Override
			public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

				listaObjetos = getService().consultarTodosLazy(first, pageSize);

				lazyDataModel.setRowCount(Integer.valueOf(getService().criteriaSelectCount().intValue()));

				return listaObjetos;
			}

		};
	}

	public LazyDataModel<T> getLazyDataModel() {

		if (this.lazyDataModel == null) {
			consultarListaObjetosPaginada();
		}

		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<T> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public T getObjeto() {
		return objeto;
	}

	public void setObjeto(T objeto) {
		this.objeto = objeto;
	}

	public GenericService<T> getService() {
		return service;
	}

	public void setService(GenericService<T> service) {
		this.service = service;
	}

	public EnumEstadoFormulario getEstadoFormulario() {
		return estadoFormulario;
	}

	public void setEstadoFormulario(EnumEstadoFormulario estadoFormulario) {
		this.estadoFormulario = estadoFormulario;
	}

}
