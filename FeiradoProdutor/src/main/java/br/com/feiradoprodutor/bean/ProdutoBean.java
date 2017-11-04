package br.com.feiradoprodutor.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.feiradoprodutor.dao.ProdutoDAO;
import br.com.feiradoprodutor.domain.Produto;
import br.com.feiradoprodutor.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class ProdutoBean implements Serializable{
	
	private Produto produto;
	private List<Produto> produtos;
	private List<Produto> filtroProdutos;
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public List<Produto> getFiltroProdutos() {
		return filtroProdutos;
	}
	public void setFiltroProdutos(List<Produto> filtroProdutos) {
		this.filtroProdutos = filtroProdutos;
	}
	@PostConstruct
	public void listar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listarOrdenado("nomeProduto");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os Produtos");
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		try{
			produto = new Produto();
			
		
		}catch (RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar o novo Produto");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		
		try{
		
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.salvar(produto);
			produtos = produtoDAO.listarOrdenado("nomeProduto");
		
			produto = new Produto();
			Messages.addGlobalInfo("Feirante salvo com sucesso");
		}catch (RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o produto");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try{
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			
		}catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao editar o Produto");
			erro.printStackTrace();
		}	
		
	}
	
	public void excluir(ActionEvent evento){
		try{
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto);
			
			produtos = produtoDAO.listarOrdenado("nomeProduto");
			
			
			Messages.addGlobalInfo("Produto removido com sucesso");
		}catch (RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir o Produto");
			erro.printStackTrace();
		}
	}
	
	public void imprimir(){
		
		try{
			//Caminho do arquivo Jasper
			String caminho = Faces.getRealPath("/reports/produtos.jasper");
			//recebe os parâmetros
			Map<String, Object> parametros = new HashMap<>();
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
