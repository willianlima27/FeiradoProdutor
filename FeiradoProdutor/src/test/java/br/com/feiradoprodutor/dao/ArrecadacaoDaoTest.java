package br.com.feiradoprodutor.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.feiradoprodutor.domain.Arrecadacao;

public class ArrecadacaoDaoTest {
	
	@Test
	@Ignore
	public void salvar(){
		
		Arrecadacao arrecadacao = new Arrecadacao();
		
		//Formatando a Data
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = null;
		try {
			dt = f.parse("04/10/2017");
		} catch (ParseException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String dt2 = f.format(dt);
		java.sql.Date dataSql = null;
		try {
			dataSql = new java.sql.Date(f.parse(dt2).getTime());
		} catch (ParseException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		arrecadacao.setDescricao("Descrição Teste3");
		arrecadacao.setData(dataSql);
		arrecadacao.setValor(10.00);
		
		ArrecadacaoDAO arrecadacaoDAO = new ArrecadacaoDAO();
		arrecadacaoDAO.salvar(arrecadacao);
		
	}
	
	@Test
	@Ignore
	public void listar(){
		
		ArrecadacaoDAO arrecadacaoDAO = new ArrecadacaoDAO();
		List<Arrecadacao> resultado = arrecadacaoDAO.listar();
		
		for(Arrecadacao arrecadacao : resultado){
			System.out.println("Registros Encotrado:");
			System.out.println("Código da Arrecadação: " + arrecadacao.getCodigo());
			System.out.println("Descrição: " + arrecadacao.getDescricao());
			System.out.println("Data: " + arrecadacao.getData());
			System.out.println("Valor: " + arrecadacao.getValor());
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		Long codigo = 2L;
		
		ArrecadacaoDAO arrecadacaoDAO = new ArrecadacaoDAO();
		Arrecadacao arrecadacao = arrecadacaoDAO.buscar(codigo);
		
		
		if(arrecadacao == null){
			System.out.println("Nenhum Registro Encontrado");
		}else{
			System.out.println("Registro Encotrado:");
			System.out.println("Código da Arrecadação: " + arrecadacao.getCodigo());
			System.out.println("Descrição: " + arrecadacao.getDescricao());
			System.out.println("Data: " + arrecadacao.getData());
			System.out.println("Valor: " + arrecadacao.getValor());
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codigo = 3L;
		
		ArrecadacaoDAO arrecadacaoDAO = new ArrecadacaoDAO();
		Arrecadacao arrecadacao = arrecadacaoDAO.buscar(codigo);
		
		arrecadacaoDAO.excluir(arrecadacao);
		
		System.out.println("Registro Removido:");
		System.out.println("Código da Arrecadação: " + arrecadacao.getCodigo());
		System.out.println("Descrição: " + arrecadacao.getDescricao());
		System.out.println("Data: " + arrecadacao.getData());
		System.out.println("Valor: " + arrecadacao.getValor());
			
	}
	
	@Test
	@Ignore
	public void editar(){
		Long codigoArrecadacao = 2L;
		
		ArrecadacaoDAO arrecadacaoDAO = new ArrecadacaoDAO();
		Arrecadacao arrecadacao = arrecadacaoDAO.buscar(codigoArrecadacao);
		
		System.out.println("Arrecadação a ser editado:");
		System.out.println("Código da Arrecadação: " + arrecadacao.getCodigo());
		System.out.println("Descrição: " + arrecadacao.getDescricao());
		System.out.println("Data: " + arrecadacao.getData());
		System.out.println("Valor: " + arrecadacao.getValor());
		
		//Formatando a Data
				SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
				Date dt = null;
				try {
					dt = f.parse("29/10/2017");
				} catch (ParseException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String dt2 = f.format(dt);
				java.sql.Date dataSql = null;
				try {
					dataSql = new java.sql.Date(f.parse(dt2).getTime());
				} catch (ParseException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		arrecadacao.setDescricao("Descrição Teste Editado");
		arrecadacao.setData(dataSql);
		arrecadacao.setValor(7.00);
		
		arrecadacaoDAO.editar(arrecadacao);
		
		System.out.println("Arrecadação Edidado");
		System.out.println("Código da Arrecadação: " + arrecadacao.getCodigo());
		System.out.println("Descrição: " + arrecadacao.getDescricao());
		System.out.println("Data: " + arrecadacao.getData());
		System.out.println("Valor: " + arrecadacao.getValor());
	}

}
