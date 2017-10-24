package br.com.feiradoprodutor.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;

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
	private List<FeiranteFrequencia> feiranteGroup;
	private List<Frequencia> frequencias;
	private List<Feirante> feirantes;

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
	
	public List<FeiranteFrequencia> getFeiranteGroup() {
		return feiranteGroup;
	}

	public void setFeiranteGroup(List<FeiranteFrequencia> feiranteGroup) {
		this.feiranteGroup = feiranteGroup;
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
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova Adição de Produto");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
			System.out.println("data:"+frequencia.getCodigo());
			frequenciaDAO.salvar(frequencia);	
			System.out.println("codigo antes:"+frequencia.getCodigo());
			Long codigoFrequencia = frequenciaDAO.listarUltimo();
			frequencia = frequenciaDAO.buscar(codigoFrequencia);
			System.out.println("codigo depois:"+frequencia.getCodigo());
			for(Feirante f: feirantes){
				System.out.println("codigo feirante:"+f.getCodigo());
				feiranteFrequencia.setFeirante(f);
				feiranteFrequencia.setFrequencia(frequencia);
				feiranteFrequencia.setSituacao(f.isSituacaoFrequencia());
				FeiranteFrequenciaDAO feiranteFrequenciaDAO = new FeiranteFrequenciaDAO();
				feiranteFrequenciaDAO.salvar(feiranteFrequencia);
				
			}
			
			Messages.addGlobalInfo("Registro de Frequencia adicionado com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar adicionar a lista de Frequencia");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try{
			feiranteFrequencia = (FeiranteFrequencia) evento.getComponent().getAttributes().get("feiranteFrequenciaSelecionada");
			
			FeiranteFrequenciaDAO feiranteFrequenciaDAO = new FeiranteFrequenciaDAO();
			feiranteFrequenciaDAO.excluir(feiranteFrequencia);
			
			popular();
			
			Messages.addGlobalInfo("Produto removido com sucesso");
		}catch (RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Produto");
			erro.printStackTrace();
		}
	}
	
	public void imprimir(){
		try{
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagemProduto:tabelaProduto");
			Map<String, Object> parametros = tabela.getFilters();		
		
			String caminho = Faces.getRealPath("/reports/feirantesprodutos.jasper");
					
			Connection conexao = HibernateUtil.getConexao();
					
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			
			JasperPrintManager.printReport(relatorio, true);
		}catch (JRException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
		}
	}
	

}
