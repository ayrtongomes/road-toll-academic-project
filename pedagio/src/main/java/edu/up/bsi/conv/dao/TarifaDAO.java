package edu.up.bsi.conv.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import edu.up.bsi.conv.entidade.Tarifa;

@Stateless
public class TarifaDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public TarifaDAO() {
		// Construção padrão
	}

	public Tarifa cadastrarTarifa(Tarifa tarifa) {
		entityManager.persist(tarifa);
		return tarifa;
	}

	public Tarifa buscarTarifaPorCodigo(Integer codigo) {
		return entityManager.find(Tarifa.class, codigo);
	}

	public Tarifa listarPorPracaCategoria(String praca, Integer categoria) {
		try {
			return entityManager.createNamedQuery("Tarifa.ListarPorPracaCategoria", Tarifa.class)
					.setParameter("t1", praca.trim())
					.setParameter("t2", categoria).getSingleResult();	
		} catch (NoResultException e) {
			return null;
		}
	}

	public void removerTarifaPorCodigo(Integer codigo) {
		Tarifa tarifa = buscarTarifaPorCodigo(codigo);
		if (tarifa != null) {
			entityManager.remove(tarifa);
		}
	}

	public Tarifa atualizarTarifa(Tarifa tarifa) {
		return entityManager.merge(tarifa);
	}

	public List<Tarifa> listarTodos() {
		return entityManager.createNamedQuery("Tarifa.ListarTodos", Tarifa.class).getResultList();
	}

}

