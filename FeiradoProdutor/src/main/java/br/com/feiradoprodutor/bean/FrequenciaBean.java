package br.com.feiradoprodutor.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.feiradoprodutor.dao.FeiranteDAO;
import br.com.feiradoprodutor.dao.FrequenciaDAO;
import br.com.feiradoprodutor.domain.Feirante;
import br.com.feiradoprodutor.domain.Frequencia;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class FrequenciaBean implements Serializable{
	
	private Frequencia frequencia;
	private Feirante feirante;
	
	private List<Frequencia> frequencias;
	private List<Feirante> feirantes;
	public Frequencia getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(Frequencia frequencia) {
		this.frequencia = frequencia;
	}
	public Feirante getFeirante() {
		return feirante;
	}
	public void setFeirante(Feirante feirante) {
		this.feirante = feirante;
	}
	public List<Frequencia> getFrequencias() {
		return frequencias;
	}
	public void setFrequencias(List<Frequencia> frequencias) {
		this.frequencias = frequencias;
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
			FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
			frequencias = frequenciaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os registros de frequência");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			frequencia = new Frequencia();
			frequencias = new ArrayList<>();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova Frequência");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			
			FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
			frequenciaDAO.salvar(frequencia);
			frequencias = frequenciaDAO.listar();
			
			frequencia = new Frequencia();
			feirantes = new ArrayList<>();
			
			Messages.addGlobalInfo("Registro de Frequência Salvo com sucesso");
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
			frequencia = (Frequencia) evento.getComponent().getAttributes().get("frequenciaSelecionado");
			
			FeiranteDAO feiranteDAO = new FeiranteDAO();
			feirantes = feiranteDAO.listarOrdenado("nomeFantasia");
			
		}catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao editar o registro de Frequencia");
			erro.printStackTrace();
		}	
		
	}
	
	public void excluir(ActionEvent evento){
		try{
			frequencia = (Frequencia) evento.getComponent().getAttributes().get("frequenciaSelecionado");
			
			FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
			frequenciaDAO.excluir(frequencia);
			
			frequencias = frequenciaDAO.listar();
			
			Messages.addGlobalInfo("Registro de Frequência removido com sucesso");
		}catch (RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir o registro de Frequência");
			erro.printStackTrace();
		}
	}
	
	
	

}
