package br.com.feiradoprodutor.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class FeiranteProduto extends GenericDomain{
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idproduto", nullable = false)
	private Produto produto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idfeirante", nullable = false)
	private Feirante feirante;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Feirante getFeirante() {
		return feirante;
	}

	public void setFeirante(Feirante feirante) {
		this.feirante = feirante;
	}
	
	

}
