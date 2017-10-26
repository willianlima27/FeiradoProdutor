package br.com.feiradoprodutor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Feirante extends GenericDomain{
	
	@Column(nullable = false)
	private boolean situacao = true;
	
	@Column(length = 30, nullable = false)
	private String nomeFantasia;

	@Column(length = 50, nullable = false)
	private String nomeCompleto;
	
	@Column(length = 18, nullable = false)
	private String cpf;
	
	@Column(length = 12, nullable = true)
	private String rg;
	
	@Column(length = 50, nullable = false)
	private String logradouro;
	
	@Column(length = 6, nullable = false)
	private String numero;
	
	@Column(length = 30, nullable = true)
	private String complemento;
	
	@Column(length = 30, nullable = false)
	private String bairro;
	
	@Column(length = 10, nullable = false)
	private String cep;
	
	@Column(length = 14, nullable = true)
	private String telefone;
	
	@Column(length = 15, nullable = false)
	private String celular;
	
	@Column(length = 50, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String sobre;
	
	@Column(nullable = true)
	private int faltas;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Cidade cidade;
	
	@Column(nullable = false)
	private boolean situacaoFrequencia = true;
	
	@Column(nullable = false)
	private boolean situacaoArrecadacao = true;

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSobre() {
		return sobre;
	}

	public void setSobre(String sobre) {
		this.sobre = sobre;
	}

	public int getFaltas() {
		return faltas;
	}

	public void setFaltas(int faltas) {
		this.faltas = faltas;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public boolean isSituacaoFrequencia() {
		return situacaoFrequencia;
	}

	public void setSituacaoFrequencia(boolean situacaoFrequencia) {
		this.situacaoFrequencia = situacaoFrequencia;
	}

	public boolean isSituacaoArrecadacao() {
		return situacaoArrecadacao;
	}

	public void setSituacaoArrecadacao(boolean situacaoArrecadacao) {
		this.situacaoArrecadacao = situacaoArrecadacao;
	}
	
}
