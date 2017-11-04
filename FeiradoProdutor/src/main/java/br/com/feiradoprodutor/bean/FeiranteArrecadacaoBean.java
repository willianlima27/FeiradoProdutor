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

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.feiradoprodutor.dao.ArrecadacaoDAO;
import br.com.feiradoprodutor.dao.FeiranteArrecadacaoDAO;
import br.com.feiradoprodutor.dao.FeiranteDAO;
import br.com.feiradoprodutor.domain.Arrecadacao;
import br.com.feiradoprodutor.domain.Feirante;
import br.com.feiradoprodutor.domain.FeiranteArrecadacao;
import br.com.feiradoprodutor.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class FeiranteArrecadacaoBean implements Serializable{
	
	private FeiranteArrecadacao feiranteArrecadacao;
	private Arrecadacao arrecadacao;
	private Feirante feirante;
		
	private List<FeiranteArrecadacao> feirantesArrecadacoes;
	private List<FeiranteArrecadacao> arrecadacaoPorData;
	private List<FeiranteArrecadacao> filtroArrecadacoes;
	private List<Arrecadacao> arrecadacoes;
	private List<Feirante> feirantes;
	
	private Date datainicio;
	private Date datafim;

	public FeiranteArrecadacao getFeiranteArrecadacao() {
		return feiranteArrecadacao;
	}

	public void setFeiranteArrecadacao(FeiranteArrecadacao feiranteArrecadacao) {
		this.feiranteArrecadacao = feiranteArrecadacao;
	}

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

	public List<FeiranteArrecadacao> getFeirantesArrecadacoes() {
		return feirantesArrecadacoes;
	}

	public void setFeirantesArrecadacoes(List<FeiranteArrecadacao> feirantesArrecadacoes) {
		this.feirantesArrecadacoes = feirantesArrecadacoes;
	}

	public List<FeiranteArrecadacao> getArrecadacaoPorData() {
		return arrecadacaoPorData;
	}

	public void setArrecadacaoPorData(List<FeiranteArrecadacao> arrecadacaoPorData) {
		this.arrecadacaoPorData = arrecadacaoPorData;
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

	public List<FeiranteArrecadacao> getFiltroArrecadacoes() {
		return filtroArrecadacoes;
	}

	public void setFiltroArrecadacoes(List<FeiranteArrecadacao> filtroArrecadacoes) {
		this.filtroArrecadacoes = filtroArrecadacoes;
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
			ArrecadacaoDAO arrecadacaoDAO = new ArrecadacaoDAO();
			arrecadacoes = arrecadacaoDAO.listar();
			FeiranteDAO feiranteDAO = new FeiranteDAO();
			feirantes = feiranteDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os registros de Arrecadações");
			erro.printStackTrace();
		}
	}
	
	public void popular(){
		try{
			FeiranteArrecadacaoDAO feiranteArrecadacaoDAO = new FeiranteArrecadacaoDAO();
			arrecadacaoPorData = feiranteArrecadacaoDAO.buscaPorData(arrecadacao.getCodigo());
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as Arrecadações dessa data");
			erro.printStackTrace();
		}
		
	}
	
	public Arrecadacao editar(ActionEvent evento){
		arrecadacao = new Arrecadacao();
		arrecadacao = (Arrecadacao) evento.getComponent().getAttributes().get("arrecadacaoSelecionada");
		listar();
		popular();
		return arrecadacao;		
	}
	
	public void novo() {
		try {
			listar();
			feiranteArrecadacao = new FeiranteArrecadacao();
			arrecadacao = new Arrecadacao();	
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova Arrecadação");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			ArrecadacaoDAO arrecadacaoDAO = new ArrecadacaoDAO();
			arrecadacaoDAO.salvar(arrecadacao);
			Long codigoArrecadacao = arrecadacaoDAO.listarUltimo();
			arrecadacao = arrecadacaoDAO.buscar(codigoArrecadacao);
			for(Feirante f: feirantes){;
				feiranteArrecadacao.setFeirante(f);
				feiranteArrecadacao.setArrecadacao(arrecadacao);
				feiranteArrecadacao.setSituacao(f.isSituacaoArrecadacao());
				FeiranteArrecadacaoDAO feiranteArrecadacaoDAO = new FeiranteArrecadacaoDAO();
				feiranteArrecadacaoDAO.salvar(feiranteArrecadacao);
				
			}
			listar();
			
			Messages.addGlobalInfo("Registro de Arrecadacao adicionado com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("ERRO ao salvar o registro de Arrecadacao");
			erro.printStackTrace();
		}
	}
	
	public void salvarEdicao() {
		try {
			ArrecadacaoDAO arrecadacaoDAO = new ArrecadacaoDAO();
			arrecadacaoDAO.salvar(arrecadacao);
			for(FeiranteArrecadacao feiranteArrecadacao : arrecadacaoPorData){
				FeiranteArrecadacaoDAO feiranteArrecadacaoDAO = new FeiranteArrecadacaoDAO();
				feiranteArrecadacaoDAO.salvar(feiranteArrecadacao);
			}
			listar();
			
			Messages.addGlobalInfo("Registro de Frequencia editado com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("ERRO ao salvar o registro de Arrecadacao");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try{
			arrecadacao = new Arrecadacao();
			arrecadacao = (Arrecadacao) evento.getComponent().getAttributes().get("arrecadacaoSelecionada");
			popular();
			for(FeiranteArrecadacao feiranteArrecadacao : arrecadacaoPorData){
				FeiranteArrecadacaoDAO feiranteArrecadacaoDAO = new FeiranteArrecadacaoDAO();
				feiranteArrecadacaoDAO.excluir(feiranteArrecadacao);
			}
			ArrecadacaoDAO arrecadacaoDAO = new ArrecadacaoDAO();
			arrecadacaoDAO.excluir(arrecadacao);
			listar();

			Messages.addGlobalInfo("Registro de Arrecadacao removido com sucesso");
		}catch (RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Registro de Arrecadação");
			erro.printStackTrace();
		}
	}
	
	public void imprimir(){
		
		try{
			//Caminho do arquivo Jasper
			String caminho = Faces.getRealPath("/reports/feiranteArrecadacao.jasper");
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
