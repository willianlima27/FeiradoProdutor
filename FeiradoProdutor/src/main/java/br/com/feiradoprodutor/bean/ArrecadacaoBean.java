package br.com.feiradoprodutor.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.feiradoprodutor.dao.ArrecadacaoDAO;
import br.com.feiradoprodutor.dao.FeiranteDAO;
import br.com.feiradoprodutor.domain.Arrecadacao;
import br.com.feiradoprodutor.domain.Feirante;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class ArrecadacaoBean implements Serializable{
	
	private Arrecadacao arrecadacao;
	private Feirante feirante;
	
	private List<Arrecadacao> arrecadacoes;
	private List<Feirante> feirantes;
	
	public Arrecadacao getArrecadacao() {
		return arrecadacao;
	}
	public void setArrecadacao(Arrecadacao arrecadacao) {
		this.arrecadacao = arrecadacao;
	}
	public Feirante getFeirante() {
		return feirante;
	}
	public void setFeirante(Feirante feirante) {
		this.feirante = feirante;
	}
	public List<Arrecadacao> getArrecadacoes() {
		return arrecadacoes;
	}
	public void setArrecadacoes(List<Arrecadacao> arrecadacoes) {
		this.arrecadacoes = arrecadacoes;
	}
	public List<Feirante> getFeirantes() {
		return feirantes;
	}
	public void setFeirantes(List<Feirante> feirantes) {
		this.feirantes = feirantes;
	}
	
	@PostConstruct
	public void listar() {
		try {
			ArrecadacaoDAO arrecadacaoDAO = new ArrecadacaoDAO();
			arrecadacoes = arrecadacaoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as Arrecadações");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			arrecadacao = new Arrecadacao();
			feirantes = new ArrayList<>();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova Arrecadação");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			
			ArrecadacaoDAO arrecadacaoDAO = new ArrecadacaoDAO();
			arrecadacaoDAO.salvar(arrecadacao);
			arrecadacoes = arrecadacaoDAO.listar();
			
			arrecadacao = new Arrecadacao();
			feirantes = new ArrayList<>();
			
			Messages.addGlobalInfo("Registro de Arrecadação Salva com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o registro de Arrecadação");
			erro.printStackTrace();
		}
	}
	
	public void popular(){
		try{
			FeiranteDAO feiranteDAO = new FeiranteDAO();
			feirantes = feiranteDAO.listarOrdenado("nomeFantasia");
		
		}catch (RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao listar os Feirantes");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try{
			arrecadacao = (Arrecadacao) evento.getComponent().getAttributes().get("arrecadacaoSelecionado");
			
			FeiranteDAO feiranteDAO = new FeiranteDAO();
			feirantes = feiranteDAO.listarOrdenado("nomeFantasia");
			
		}catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao editar o registro de Arrecadação");
			erro.printStackTrace();
		}	
		
	}
	
	public void excluir(ActionEvent evento){
		try{
			arrecadacao = (Arrecadacao) evento.getComponent().getAttributes().get("arrecadacaoSelecionado");
			
			ArrecadacaoDAO arrecadacaoDAO = new ArrecadacaoDAO();
			arrecadacaoDAO.excluir(arrecadacao);
			
			arrecadacoes = arrecadacaoDAO.listar();
			
			Messages.addGlobalInfo("Registro de Arrecadação removido com sucesso");
		}catch (RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir o registro de Arrecadação");
			erro.printStackTrace();
		}
	}

}
