package br.com.feiradoprodutor.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;

import br.com.feiradoprodutor.dao.CidadeDAO;
import br.com.feiradoprodutor.dao.EstadoDAO;
import br.com.feiradoprodutor.dao.FeiranteDAO;
import br.com.feiradoprodutor.domain.Cidade;
import br.com.feiradoprodutor.domain.Estado;
import br.com.feiradoprodutor.domain.Feirante;
import br.com.feiradoprodutor.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class FeiranteBean implements Serializable{
	
	private Feirante feirante;
	private Estado estado;
	
	private List<Feirante> feirantes;
	private List<Estado> estados;
	private List<Cidade> cidades;
	
	public Feirante getFeirante() {
		return feirante;
	}
	
	public void setFeirante(Feirante feirante) {
		this.feirante = feirante;
	}
	
	public List<Feirante> getFeirantes() {
		return feirantes;
	}
	
	public void setFeirantes(List<Feirante> feirantes) {
		this.feirantes = feirantes;
	}
	
	public List<Cidade> getCidades() {
		return cidades;
	}
	
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public List<Estado> getEstados() {
		return estados;
	}
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
	@PostConstruct
	public void listar() {
		try {
			FeiranteDAO feiranteDAO = new FeiranteDAO();
			feirantes = feiranteDAO.listarOrdenado("nomeFantasia");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os Feirantes");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			feirante = new Feirante();
			estado = new Estado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listarOrdenado("nomeEstado");
			
			cidades = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar o novo Feirante");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			
			FeiranteDAO feiranteDAO = new FeiranteDAO();
			feiranteDAO.salvar(feirante);
			feirantes = feiranteDAO.listarOrdenado("nomeFantasia");
			
			feirante = new Feirante();
			estado = new Estado();
			
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listarOrdenado("nomeEstado");
			
			cidades = new ArrayList<>();
			
			Messages.addGlobalInfo("Feirante salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um novo Feirante");
			erro.printStackTrace();
		}
	}
	
	public void popular(){
		try{
			if(estado != null){
				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscaPorEstado(estado.getCodigo());
			}else{
				cidades = new ArrayList<>();
			}
		}catch (RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar filtrar as Cidades");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try{
			feirante = (Feirante) evento.getComponent().getAttributes().get("feiranteSelecionado");
			
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
			
			
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listarOrdenado("nomeCidade");
			
		}catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao editar o Feirante");
			erro.printStackTrace();
		}	
		
	}
	
	public void excluir(ActionEvent evento){
		try{
			feirante = (Feirante) evento.getComponent().getAttributes().get("feiranteSelecionado");
			
			FeiranteDAO feiranteDAO = new FeiranteDAO();
			feiranteDAO.excluir(feirante);
			
			feirantes = feiranteDAO.listarOrdenado("nomeFantasia");
			
			Messages.addGlobalInfo("Feirante removido com sucesso");
		}catch (RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir o Feirante");
			erro.printStackTrace();
		}
	}
	
	public void imprimir(){
		try{
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabelaFeirantes");
			Map<String, Object> parametros = tabela.getFilters();		
		
			String caminho = Faces.getRealPath("/reports/feirantes.jasper");
					
			Connection conexao = HibernateUtil.getConexao();
					
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			
			JasperPrintManager.printReport(relatorio, true);
		}catch (JRException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
		}
	}

}
