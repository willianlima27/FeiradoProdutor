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
		produto.setNomeProduto("Produto Teste2");
		produto.setGenero("Alimenticio");
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
	}
	
	@Test
	@Ignore
	public void listar(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> resultado = produtoDAO.listar();
		
		for(Produto produto : resultado){
			System.out.println("Registro Encotrado:");
			System.out.println("Código do Produto: " + produto.getCodigo());
			System.out.println("Nome do Produto: " + produto.getNomeProduto());
			System.out.println("Genero do Produto: " + produto.getGenero());
			
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		Long codigo = 6L;
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
			
		if(produto == null){
			System.out.println("Nenhum Registro Encontrado");
		}else{
			System.out.println("Registro Encotrado:");
			System.out.println("Código do Produto: " + produto.getCodigo());
			System.out.println("Nome do Produto: " + produto.getNomeProduto());
			System.out.println("Genero do Produto: " + produto.getGenero());
			
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codigo = 8L;
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
		
		produtoDAO.excluir(produto);
		
		System.out.println("Registro Removido:");
		System.out.println("Código do Produto: " + produto.getCodigo());
		System.out.println("Nome do Produto: " + produto.getNomeProduto());
		System.out.println("Genero do Produto: " + produto.getGenero());
		
		
	}
	
	@Test
	@Ignore
	public void editar(){
		Long codigo = 7L;
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
		
		System.out.println("Produto a ser Editado:");
		System.out.println("Código do Produto: " + produto.getCodigo());
		System.out.println("Nome do Produto: " + produto.getNomeProduto());
		System.out.println("Genero do Produto: " + produto.getGenero());
		
		produto.setNomeProduto("Produto Alterado");
		produto.setGenero("Artesanal");
		
		produtoDAO.editar(produto);
		
		System.out.println("Produto Editado:");
		System.out.println("Código do Produto: " + produto.getCodigo());
		System.out.println("Nome do Produto: " + produto.getNomeProduto());
		System.out.println("Genero do Produto: " + produto.getGenero());
		
	}

}
