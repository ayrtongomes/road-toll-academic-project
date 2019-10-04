package edu.up.bsi.conv.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.up.bsi.conv.entidade.Ticketao;

@Stateless
public class TicketDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public TicketDAO() {
		// Construção padrão
	}

	public Ticketao cadastrarTicket(Ticketao ticket) {
		entityManager.persist(ticket);
		return ticket;
	}

//	public Tarifa buscarTarifaPorCodigo(Integer codigo) {
//		return entityManager.find(Tarifa.class, codigo);
//	}
//
//	public Tarifa listarPorPracaCategoria(String praca, Integer categoria) {
//		try {
//			return entityManager.createNamedQuery("Tarifa.ListarPorPracaCategoria", Tarifa.class)
//					.setParameter("t1", praca.trim())
//					.setParameter("t2", categoria).getSingleResult();	
//		} catch (NoResultException e) {
//			return null;
//		}
//	}
//
//	public void removerTarifaPorCodigo(Integer codigo) {
//		Tarifa tarifa = buscarTarifaPorCodigo(codigo);
//		if (tarifa != null) {
//			entityManager.remove(tarifa);
//		}
//	}
//
//	public Tarifa atualizarTarifa(Tarifa tarifa) {
//		return entityManager.merge(tarifa);
//	}
//
//	public List<Tarifa> listarTodos() {
//		return entityManager.createNamedQuery("Tarifa.ListarTodos", Tarifa.class).getResultList();
//	}

}

