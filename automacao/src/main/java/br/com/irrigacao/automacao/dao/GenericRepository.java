package br.com.irrigacao.automacao.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

@SuppressWarnings("unchecked")
@Stateless
public abstract class GenericRepository<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	//@PersistenceContext(name = "automacao")
	@Inject
	protected EntityManager entityManager;

	public T salvar(T objeto) {

		try {
			antesSalvar();
			objeto = (T) getSession().merge(objeto);
			getSession().flush();
			depoisSalvar();
			return objeto;
		} catch (Exception e) {
			e.printStackTrace();
			if (!getSession().getTransaction().isActive()) {
				getSession().getTransaction().begin();
			}
			getSession().getTransaction().rollback();

			return null;
		}

	}

	public T deletar(T objeto) {
		try {
			antesDeletar();
			Object object = getSession().merge(objeto);
			getSession().delete(object);
			getSession().flush();
			depoisDeletar();
			return objeto;
		} catch (Exception e) {
			if (!getSession().getTransaction().isActive()) {
				getSession().getTransaction().begin();
			}
			getSession().getTransaction().rollback();
			e.printStackTrace();
			getSession().clear();
			return null;
		}
	}

	public List<T> salvarTodos(List<T> objetos) {
		List<T> list = new ArrayList<T>();
		try {
			for (T objeto : objetos) {
				if (objeto != null) {
					objeto = salvar(objeto);
					list.add(objeto);
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	protected Criteria createCriteria() {
		Criteria criteria = getSession().createCriteria(
				(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).setCacheable(false);
		return criteria;
	}

	public T consultarPorId(Integer id) {
		return (T) createCriteria().add(Restrictions.idEq(id)).uniqueResult();
	}

	public T consultarPorObjetoId(Object object) {
		return (T) createCriteria().add(Restrictions.idEq(object)).uniqueResult();
	}

	public List<T> consultarTodos() {
		Criteria criteria;
		try {
			criteria = createCriteria();
			addJoinFecth(criteria);
			addOrdem(criteria);
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	protected void addJoinFecth(Criteria consult) {

	}

	protected void addOrdem(Criteria criteria) {

	}

	public Session getSession() {
		return (Session) entityManager.getDelegate();
	}

	public void cleanEntityManager() {
		entityManager.clear();
	}

	public void detachObject(Object obj) {
		if (obj != null) {
			entityManager.detach(obj);
		}
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

	public List<T> consultarTodosLazy(int first, int pageSize) {

		Criteria criteria;

		try {
			criteria = createCriteria().setFirstResult(first).setMaxResults(pageSize);
			addJoinFecth(criteria);
			addOrdem(criteria);

			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Retorna a quantidade de resultados de uma consulta
	 *
	 */
	public Number criteriaSelectCount() {
		return (Number) createCriteria().setProjection(Projections.rowCount()).uniqueResult();
	}

}
