package br.com.feiradoprodutor.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.hibernate.exception.ConstraintViolationException;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.feiradoprodutor.dao.FeiranteDAO;
import br.com.feiradoprodutor.dao.FeiranteFrequenciaDAO;
import br.com.feiradoprodutor.dao.FrequenciaDAO;
import br.com.feiradoprodutor.domain.Feirante;
import br.com.feiradoprodutor.domain.FeiranteFrequencia;
import br.com.feiradoprodutor.domain.Frequencia;
import br.com.feiradoprodutor.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class FeiranteFrequenciaBean implements Serializable{
	
	private FeiranteFrequencia feiranteFrequencia;
	private Frequencia frequencia;
	private Feirante feirante;
		
	private List<FeiranteFrequencia> feirantesFrequencias;
	private List<FeiranteFrequencia> frequenciaPorData;
	private List<Frequencia> frequencias;
	private List<Feirante> feirantes;
	private List<Frequencia> filtroFrequencias;
	
	private Date datainicio;
	private Date datafim;

	public FeiranteFrequencia getFeiranteFrequencia() {
		return feiranteFrequencia;
	}

	public void setFeiranteFrequencia(FeiranteFrequencia feiranteFrequencia) {
		this.feiranteFrequencia = feiranteFrequencia;
	}

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

	public List<FeiranteFrequencia> getFeirantesFrequencias() {
		return feirantesFrequencias;
	}

	public void setFeirantesFrequencias(List<FeiranteFrequencia> feirantesFrequencias) {
		this.feirantesFrequencias = feirantesFrequencias;
	}

	public List<FeiranteFrequencia> getFrequenciaPorData() {
		return frequenciaPorData;
	}

	public void setFrequenciaPorData(List<FeiranteFrequencia> frequenciaPorData) {
		this.frequenciaPorData = frequenciaPorData;
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
	
	public List<Frequencia> getFiltroFrequencias() {
		return filtroFrequencias;
	}

	public void setFiltroFrequencias(List<Frequencia> filtroFrequencias) {
		this.filtroFrequencias = filtroFrequencias;
	}
	public Date getDatainicio() {
		return datainicio;
	}

	public void setDatainicio(Date datainicio) {
		this.datainicio = datainicio;
	}

	public Date getDatafim() {
		return datafim;
	}

	public void setDatafim(Date datafim) {
		this.datafim = datafim;
	}

	@PostConstruct
	public void listar() {
		try {
			FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
			frequencias = frequenciaDAO.listar();
			FeiranteDAO feiranteDAO = new FeiranteDAO();
			feirantes = feiranteDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as Frequências do Feirante");
			erro.printStackTrace();
		}
	}
	
	public void popular(){
		try{
			FeiranteFrequenciaDAO feiranteFrequenciaDAO = new FeiranteFrequenciaDAO();
			frequenciaPorData = feiranteFrequenciaDAO.buscaPorData(frequencia.getCodigo());
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as Frequencia dessa data");
			erro.printStackTrace();
		}
		
	}
	
	public Frequencia editar(ActionEvent evento){
		frequencia = new Frequencia();
		frequencia = (Frequencia) evento.getComponent().getAttributes().get("frequenciaSelecionada");
		listar();
		popular();
		return frequencia;		
	}
	
	public void novo() {
		try {
			listar();
			feiranteFrequencia = new FeiranteFrequencia();
			frequencia = new Frequencia();
			
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova Frequência");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
			frequenciaDAO.salvar(frequencia);	
			Long codigoFrequencia = frequenciaDAO.listarUltimo();
			frequencia = frequenciaDAO.buscar(codigoFrequencia);
			for(Feirante f: feirantes){
				feiranteFrequencia.setFeirante(f);
				feiranteFrequencia.setFrequencia(frequencia);
				feiranteFrequencia.setSituacao(f.isSituacaoFrequencia());
				FeiranteFrequenciaDAO feiranteFrequenciaDAO = new FeiranteFrequenciaDAO();
				feiranteFrequenciaDAO.salvar(feiranteFrequencia);
				
			}
			listar();
			
			Messages.addGlobalInfo("Registro de Frequencia adicionado com sucesso");
		} catch (ConstraintViolationException erro) {
			Messages.addFlashGlobalError("ERRO: Já existe um registro de frequência para esta data");
			erro.printStackTrace();
		}
	}
	
	public void salvarEdicao() {
		try {
			FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
			frequenciaDAO.salvar(frequencia);
			for(FeiranteFrequencia feiranteFrequencia : frequenciaPorData){
				FeiranteFrequenciaDAO feiranteFrequenciaDAO = new FeiranteFrequenciaDAO();
				feiranteFrequenciaDAO.salvar(feiranteFrequencia);
			}
			listar();
			
			Messages.addGlobalInfo("Registro de Frequencia editado com sucesso");
		} catch (ConstraintViolationException erro) {
			Messages.addFlashGlobalError("ERRO: Já existe um registro de frequência para esta data");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try{
			frequencia = new Frequencia();
			frequencia = (Frequencia) evento.getComponent().getAttributes().get("frequenciaSelecionada");
			popular();
			for(FeiranteFrequencia feiranteFrequencia: frequenciaPorData){
				FeiranteFrequenciaDAO feiranteFrequenciaDAO = new FeiranteFrequenciaDAO();
				feiranteFrequenciaDAO.excluir(feiranteFrequencia);;
				
			}
			FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
			frequenciaDAO.excluir(frequencia);
			listar();

			Messages.addGlobalInfo("Registro de Frequência removido com sucesso");
		}catch (RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Registro de Frequência");
			erro.printStackTrace();
		}
	}
	
	public void imprimir(){
		
		try{
			//Caminho do arquivo Jasper
			String caminho = Faces.getRealPath("/reports/feiranteFrequencia.jasper");
			//recebe os parâmetros
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("data_inicio", this.datainicio);
			parametros.put("data_fim", this.datafim);
			//Realiza a conexão com o Banco
			Connection conexao = HibernateUtil.getConexao();
			//Recebe um relatório populado
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			//Habilita a impressão
			JasperPrintManager.printReport(relatorio, true);
			
		}catch (JRException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
		}
			
	}
	

}
