package br.com.irrigacao.automacao.service;

import java.io.Serializable;
import java.util.List;

import br.com.irrigacao.automacao.dao.GenericRepository;

public abstract class GenericService<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract GenericRepository<T> getRepository();

	/**
	 * @author romualdo.massuia
	 * @param objeto
	 * @return retorna objeto persitido
	 * 
	 *         M�todo para salvar objeto
	 * 
	 */
	public T salvar(T objeto) {
		try {
			antesSalvar();
			objeto = getRepository().salvar(objeto);
			depoisSalvar();
			return objeto;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @author romualdo.massuia
	 * @param objeto
	 * @return retorna objeto deletado
	 * 
	 *         M�todo para deletar objeto
	 */
	public T deletar(T objeto) {
		try {
			antesDeletar();
			objeto = getRepository().deletar(objeto);
			depoisDeletar();
			return objeto;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @author romualdo.massuia
	 * @param objetos
	 * @return retorna lista de objetos persistido
	 * 
	 *         M�todo para salvar um lista de objetos
	 * 
	 */
	public List<T> salvarTodos(List<T> objetos) {

		try {
			return getRepository().salvarTodos(objetos);
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * @return retorna consulta de uma lista de objetos
	 * 
	 *         M�todo para consultar todos os objetos
	 */
	public List<T> consultarTodos() {
		return getRepository().consultarTodos();
	}

	/**
	 * @author romualdo.massuia
	 * @param id
	 * @return retorna objeto
	 * 
	 *         M�todo para consultar um objeto por id
	 */
	public T consultarPorId(Integer id) {
		return getRepository().consultarPorId(id);
	}

	/**
	 * @author romualdo.massuia
	 * @param id
	 * @return retorna objeto
	 * 
	 *         M�todo para consultar um objeto por objeto
	 */
	public T consultarPorObjetoId(Object id) {
		return getRepository().consultarPorObjetoId(id);
	}

	/**
	 * @author romualdo.massuia
	 * @param obj
	 * 
	 *            M�todo para remover gerencia do entitymaneger
	 */
	public void detachObject(Object obj) {
		getRepository().detachObject(obj);
	}

	/**
	 * @author romualdo.massuia
	 *
	 *         M�todo para implentar antes de deletar um objeto
	 *
	 */
	protected void antesDeletar() {

	}

	/**
	 * @author romualdo.massuia
	 *
	 *         M�todo para implentar depois de deletar um objeto
	 *
	 */
	protected void depoisDeletar() {

	}

	/**
	 * @author romualdo.massuia
	 *
	 *         M�todo para implentar depois de salvar um objeto
	 *
	 */
	protected void depoisSalvar() {

	}

	/**
	 * @author romualdo.massuia
	 *
	 *         M�todo para implentar antes de salvar um objeto
	 *
	 */
	protected void antesSalvar() {

	}

	public List<T> consultarTodosLazy(int first, int pageSize) {
		return getRepository().consultarTodosLazy(first, pageSize);
	}

	public Number criteriaSelectCount() {
		return getRepository().criteriaSelectCount();
	}

}
