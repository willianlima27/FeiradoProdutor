package br.com.feiradoprodutor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain{
	
	@Column(length = 30, nullable = false)
	private String nome;
	
	@Column(length = 50, nullable = false, unique = true)
	private String email;
	
	@Column(length = 32, nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private boolean situacao = true;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}
	
	

}
