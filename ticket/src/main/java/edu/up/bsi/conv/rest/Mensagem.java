package edu.up.bsi.conv.rest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidade para trafegar mensagens REST para fluxos alternativos
 * 
 * 
 *
 */
@XmlRootElement
public class Mensagem {

	private String status;

	private String descricao;

	public Mensagem() {

	}

	public Mensagem(String status, String descricao) {
		this.status = status;
		this.setDescricao(descricao);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
