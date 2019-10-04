package edu.up.bsi.conv.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import edu.up.conv.reflection.EntidadeUtils;


//@NamedQuery(name = "Tarifa.ListarPorPracaCategoria", query = "SELECT t FROM Tarifa t WHERE UPPER(t.nomePraca) =UPPER(:t1) AND t.categoria = :t2")
//@NamedQuery(name = "Tarifa.ListarTodos", query = "SELECT t FROM Tarifa t")
@EntityListeners(EntidadeUtils.class)
@Entity
@Table(name="ticket")
public class Ticketao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String praca;
	
	private String cancela;
	
	private Date dataHora;
	
	private double tarifa;
	
	private String categoria;
			
	public Ticketao() {
		
	}
	
	public Ticketao(String praca, String cancela, Date dataHora, double tarifa, String categoria) {
		this.praca = praca;
		this.cancela = cancela;
		this.dataHora = dataHora;
		this.tarifa = tarifa;
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getPraca() {
		return praca;
	}

	public void setPraca(String praca) {
		this.praca = praca;
	}

	public String getCancela() {
		return cancela;
	}

	public void setCancela(String cancela) {
		this.cancela = cancela;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public double getTarifa() {
		return tarifa;
	}

	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String toPath() {
		return "/".concat(String.valueOf(this.id));
	}
}


