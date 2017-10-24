package br.com.feiradoprodutor.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.feiradoprodutor.dao.CidadeDAO;
import br.com.feiradoprodutor.dao.FeiranteDAO;
import br.com.feiradoprodutor.domain.Cidade;
import br.com.feiradoprodutor.domain.Feirante;

public class FeiranteDaoTest {
	
	@Test
	@Ignore
	public void salvar(){
		
		Long codigoCidade = 3007L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();	
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		
		Feirante feirante = new Feirante();
		
		feirante.setSituacao(true);
		feirante.setNomeFantasia("Nome Teste");
		feirante.setNomeCompleto("Nome Completo Teste");
		feirante.setCpf("999.999.999-99");
		feirante.setRg("99999999-9");
		feirante.setLogradouro("Rua Bisbo Dom Carlos");
		feirante.setNumero("2081");
		feirante.setComplemento("Em frente ao mercado total");
		feirante.setBairro("Centro");
		feirante.setCep("85555-000");
		feirante.setTelefone("(46) 9999-9999");
		feirante.setCelular("(46) 99999-9999");
		feirante.setEmail("teste@teste.com.br");
		feirante.setSobre("Situada no centro da cidade de Palmas, a Teste"
				+ " atua no mercado a mais de 20 anos trazendo os melhores produtos"
				+ "para a sua casa.");
		
		feirante.setCidade(cidade);
		
		FeiranteDAO feiranteDAO = new FeiranteDAO();
		feiranteDAO.salvar(feirante);
	}
	
	@Test
	@Ignore
	public void listar(){
		FeiranteDAO feiranteDAO	 = new FeiranteDAO();
		List<Feirante> resultado = feiranteDAO.listar();
		
		for(Feirante feirante : resultado){
			System.out.println("Registro Encotrado:");
			System.out.println("Código do Feirante: " + feirante.getCodigo());
			System.out.println("Situação: " + feirante.isSituacao());
			System.out.println("Fantasia: " + feirante.getNomeFantasia());
			System.out.println("Nome Completo: " + feirante.getNomeCompleto());
			System.out.println("CPF: " + feirante.getCpf());
			System.out.println("RG: " + feirante.getRg());
			System.out.println("Logradouro: " + feirante.getLogradouro());
			System.out.println("Numero: " + feirante.getNumero());
			System.out.println("Complemento: " + feirante.getComplemento());
			System.out.println("Bairro: " + feirante.getBairro());
			System.out.println("CEP: " + feirante.getCep());
			System.out.println("Telefone: " + feirante.getTelefone());
			System.out.println("Celular: " + feirante.getCelular());
			System.out.println("Email: " + feirante.getEmail());
			System.out.println("Sobre: " + feirante.getSobre());
			System.out.println("Cod Cidade: " + feirante.getCidade().getCodigo());
			System.out.println("Cidade: " + feirante.getCidade().getNomeCidade());
			System.out.println("Estado: " + feirante.getCidade().getEstado().getSigla());
		}
	}
	
	@Test
	//@Ignore
	public void buscar(){
		Long codigo = 2L;
		
		FeiranteDAO feiranteDAO = new FeiranteDAO();
		Feirante feirante = feiranteDAO.buscar(codigo);
		
		if(feirante == null){
			System.out.println("Nenhum Registro Encontrado");
		}else{
			System.out.println("Registro Encotrado:");
			System.out.println("Código do Feirante: " + feirante.getCodigo());
			System.out.println("Situação: " + feirante.isSituacao());
			System.out.println("Fantasia: " + feirante.getNomeFantasia());
			System.out.println("Nome Completo: " + feirante.getNomeCompleto());
			System.out.println("CPF: " + feirante.getCpf());
			System.out.println("RG: " + feirante.getRg());
			System.out.println("Logradouro: " + feirante.getLogradouro());
			System.out.println("Numero: " + feirante.getNumero());
			System.out.println("Complemento: " + feirante.getComplemento());
			System.out.println("Bairro: " + feirante.getBairro());
			System.out.println("CEP: " + feirante.getCep());
			System.out.println("Telefone: " + feirante.getTelefone());
			System.out.println("Celular: " + feirante.getCelular());
			System.out.println("Email: " + feirante.getEmail());
			System.out.println("Sobre: " + feirante.getSobre());
			System.out.println("Cod Cidade: " + feirante.getCidade().getCodigo());
			System.out.println("Cidade: " + feirante.getCidade().getNomeCidade());
			System.out.println("Estado: " + feirante.getCidade().getEstado().getSigla());
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codigo = 2L;
		
		FeiranteDAO feiranteDAO = new FeiranteDAO();
		Feirante feirante = feiranteDAO.buscar(codigo);
		
		feiranteDAO.excluir(feirante);
		
		System.out.println("Registro Removido:");
		System.out.println("Código do Feirante: " + feirante.getCodigo());
		System.out.println("Situação: " + feirante.isSituacao());
		System.out.println("Fantasia: " + feirante.getNomeFantasia());
		System.out.println("Nome Completo: " + feirante.getNomeCompleto());
		System.out.println("CPF: " + feirante.getCpf());
		System.out.println("RG: " + feirante.getRg());
		System.out.println("Logradouro: " + feirante.getLogradouro());
		System.out.println("Numero: " + feirante.getNumero());
		System.out.println("Complemento: " + feirante.getComplemento());
		System.out.println("Bairro: " + feirante.getBairro());
		System.out.println("CEP: " + feirante.getCep());
		System.out.println("Telefone: " + feirante.getTelefone());
		System.out.println("Celular: " + feirante.getCelular());
		System.out.println("Email: " + feirante.getEmail());
		System.out.println("Sobre: " + feirante.getSobre());
		System.out.println("Cod Cidade: " + feirante.getCidade().getCodigo());
		System.out.println("Cidade: " + feirante.getCidade().getNomeCidade());
		System.out.println("Estado: " + feirante.getCidade().getEstado().getSigla());
		
	}
	
	@Test
	@Ignore
	public void editar(){
		Long codigoFeirante = 2L;
		Long codigoCidade = 5457L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		
		FeiranteDAO feiranteDAO = new FeiranteDAO();
		Feirante feirante = feiranteDAO.buscar(codigoFeirante);
		
		System.out.println("Feirante a ser editado:");
		System.out.println("Código do Feirante: " + feirante.getCodigo());
		System.out.println("Situação: " + feirante.isSituacao());
		System.out.println("Fantasia: " + feirante.getNomeFantasia());
		System.out.println("Nome Completo: " + feirante.getNomeCompleto());
		System.out.println("CPF: " + feirante.getCpf());
		System.out.println("RG: " + feirante.getRg());
		System.out.println("Logradouro: " + feirante.getLogradouro());
		System.out.println("Numero: " + feirante.getNumero());
		System.out.println("Complemento: " + feirante.getComplemento());
		System.out.println("Bairro: " + feirante.getBairro());
		System.out.println("CEP: " + feirante.getCep());
		System.out.println("Telefone: " + feirante.getTelefone());
		System.out.println("Celular: " + feirante.getCelular());
		System.out.println("Email: " + feirante.getEmail());
		System.out.println("Sobre: " + feirante.getSobre());
		System.out.println("Cod Cidade: " + feirante.getCidade().getCodigo());
		System.out.println("Cidade: " + feirante.getCidade().getNomeCidade());
		System.out.println("Estado: " + feirante.getCidade().getEstado().getSigla());
		
		feirante.setSituacao(true);
		feirante.setNomeFantasia("Nome Teste Editado");
		feirante.setNomeCompleto("Nome Completo Teste Editado");
		feirante.setCpf("888.888.888-88");
		feirante.setRg("88888888-8");
		feirante.setLogradouro("Rua Bisbo Dom Carlos Editado");
		feirante.setNumero("9999");
		feirante.setComplemento("Em frente ao mercado total Editado");
		feirante.setBairro("Centro Editado");
		feirante.setCep("99999-999");
		feirante.setTelefone("(46) 8888-8888");
		feirante.setCelular("(46) 88888-8888");
		feirante.setEmail("testeeditado@teste.com.br");
		feirante.setSobre("Sobre Editado");
		
		feirante.setCidade(cidade);
		
		feiranteDAO.editar(feirante);
		
		System.out.println("Feirante Edidado");
		System.out.println("Código do Feirante: " + feirante.getCodigo());
		System.out.println("Situação: " + feirante.isSituacao());
		System.out.println("Fantasia: " + feirante.getNomeFantasia());
		System.out.println("Nome Completo: " + feirante.getNomeCompleto());
		System.out.println("CPF: " + feirante.getCpf());
		System.out.println("RG: " + feirante.getRg());
		System.out.println("Logradouro: " + feirante.getLogradouro());
		System.out.println("Numero: " + feirante.getNumero());
		System.out.println("Complemento: " + feirante.getComplemento());
		System.out.println("Bairro: " + feirante.getBairro());
		System.out.println("CEP: " + feirante.getCep());
		System.out.println("Telefone: " + feirante.getTelefone());
		System.out.println("Celular: " + feirante.getCelular());
		System.out.println("Email: " + feirante.getEmail());
		System.out.println("Sobre: " + feirante.getSobre());
		System.out.println("Cod Cidade: " + feirante.getCidade().getCodigo());
		System.out.println("Cidade: " + feirante.getCidade().getNomeCidade());
		System.out.println("Estado: " + feirante.getCidade().getEstado().getSigla());
		
	}

}
