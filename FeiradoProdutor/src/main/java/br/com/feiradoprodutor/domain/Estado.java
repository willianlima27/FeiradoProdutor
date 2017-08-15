package br.com.feiradoprodutor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Estado extends GenericDomain{

	@Column(length = 30, nullable = false)
	private String nomeEstado;
	
	@Column(length = 2, nullable = false)
	private String sigla;

	/*Métodos acessores*/
	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	
}
