package br.com.feiradoprodutor.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Frequencia extends GenericDomain{
	
	@Column(nullable = false)
	private Date data;
	
	@Column(nullable = false)
	private Boolean situacao;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Feirante feirante;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	public Feirante getFeirante() {
		return feirante;
	}

	public void setFeirante(Feirante feirante) {
		this.feirante = feirante;
	}
	
	

}
