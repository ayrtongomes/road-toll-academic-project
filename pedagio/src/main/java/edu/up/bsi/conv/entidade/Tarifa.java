package edu.up.bsi.conv.entidade;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import edu.up.conv.reflection.EntidadeUtils;


@NamedQuery(name = "Tarifa.ListarPorPracaCategoria", query = "SELECT t FROM Tarifa t WHERE UPPER(t.nomePraca) =UPPER(:t1) AND t.categoria = :t2")
@NamedQuery(name = "Tarifa.ListarTodos", query = "SELECT t FROM Tarifa t")
@EntityListeners(EntidadeUtils.class)
@Entity
public class Tarifa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nomePraca;
	
	private Integer categoria;
	
	private double valorPraticado;
			
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Tarifa() {

	}

	public String getNomePraca() {
		return nomePraca;
	}

	public void setNomePraca(String nomePraca) {
		this.nomePraca = nomePraca;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public double getValorPraticado() {
		return valorPraticado;
	}

	public void setValorPraticado(double valorPraticado) {
		this.valorPraticado = valorPraticado;
	}
	
	public String toPath() {
		return "/".concat(String.valueOf(this.id));
	}
}


