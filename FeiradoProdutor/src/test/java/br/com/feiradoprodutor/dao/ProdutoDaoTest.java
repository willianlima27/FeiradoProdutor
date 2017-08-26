package br.com.feiradoprodutor.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.feiradoprodutor.domain.Produto;

public class ProdutoDaoTest {
	
	@Test
	@Ignore
	public void salvar(){
		
		Produto produto = new Produto();
		produto.setDescricao("PRoduto Teste");
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
	}
	
	@Test
	//@Ignore
	public void listar(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> resultado = produtoDAO.listar();
		
		for(Produto produto : resultado){
			System.out.println("Registro Encotrado:");
			System.out.println("Código do Produto: " + produto.getCodigo());
			System.out.println("Descrição do Produto: " + produto.getDescricao());
			
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		Long codigo = 1L;
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
			
		if(produto == null){
			System.out.println("Nenhum Registro Encontrado");
		}else{
			System.out.println("Registro Encotrado:");
			System.out.println("Código do Produto: " + produto.getCodigo());
			System.out.println("Descriação: " + produto.getDescricao());
			
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codigo = 3L;
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
		
		produtoDAO.excluir(produto);
		
		System.out.println("Registro Removido:");
		System.out.println("Código do Produto: " + produto.getCodigo());
		System.out.println("Descriação: " + produto.getDescricao());
		
		
	}
	
	@Test
	@Ignore
	public void editar(){
		Long codigo = 3L;
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
		
		System.out.println("Produto a ser Editado:");
		System.out.println("Código do Produto: " + produto.getCodigo());
		System.out.println("Descriação: " + produto.getDescricao());
		
		produto.setDescricao("Produto Alterado");
		
		produtoDAO.editar(produto);
		
		System.out.println("Produto Editado:");
		System.out.println("Código do Produto: " + produto.getCodigo());
		System.out.println("Descriação: " + produto.getDescricao());
		
	}

}
