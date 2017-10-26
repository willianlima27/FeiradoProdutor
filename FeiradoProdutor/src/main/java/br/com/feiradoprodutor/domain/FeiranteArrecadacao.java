package br.com.feiradoprodutor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class FeiranteArrecadacao extends GenericDomain{
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idfeirante", nullable = false)
	private Feirante feirante;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idarrecadacao", nullable = false)
	private Arrecadacao arrecadacao;
	
	@Column(nullable = false)
	private Boolean situacao;

	public Feirante getFeirante() {
		return feirante;
	}

	public void setFeirante(Feirante feirante) {
		this.feirante = feirante;
	}

	public Arrecadacao getArrecadacao() {
		return arrecadacao;
	}

	public void setArrecadacao(Arrecadacao arrecadacao) {
		this.arrecadacao = arrecadacao;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}
	
	

}
