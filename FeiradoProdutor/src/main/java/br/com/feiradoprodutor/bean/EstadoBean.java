package br.com.feiradoprodutor.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Messages;

import br.com.feiradoprodutor.dao.EstadoDAO;
import br.com.feiradoprodutor.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class EstadoBean implements Serializable{
	
	private Estado estado;
	
	private List<Estado> estados;

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public void novo(){
		estado = new Estado();
	}
	
	@PostConstruct
	public void listar(){
		try{
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listarOrdenado("nomeEstado");
		}catch (RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
	}

}
