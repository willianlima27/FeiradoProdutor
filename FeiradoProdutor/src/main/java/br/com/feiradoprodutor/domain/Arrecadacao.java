package br.com.feiradoprodutor.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Arrecadacao extends GenericDomain{
	
	@Column(length = 30, nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private Date data;;
	
	@Column(nullable = false)
	private double valor;
	
	@Column(nullable = false)
	private Boolean situacao;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Feirante feirante;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
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
