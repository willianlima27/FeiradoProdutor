package br.com.feiradoprodutor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class FeiranteFrequencia extends GenericDomain{

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idfeirante", nullable = false)
	private Feirante feirante;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idfrequencia", nullable = false)
	private Frequencia frequencia;
	
	@Column(nullable = false)
	private Boolean situacao;

	public Feirante getFeirante() {
		return feirante;
	}

	public void setFeirante(Feirante feirante) {
		this.feirante = feirante;
	}

	public Frequencia getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(Frequencia frequencia) {
		this.frequencia = frequencia;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

}
