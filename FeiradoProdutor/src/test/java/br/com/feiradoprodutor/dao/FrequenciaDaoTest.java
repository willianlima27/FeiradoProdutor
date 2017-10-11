package br.com.feiradoprodutor.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.feiradoprodutor.domain.Feirante;
import br.com.feiradoprodutor.domain.Frequencia;

public class FrequenciaDaoTest {
	
	@Test
	@Ignore
	public void salvar(){
		
		Long codigoFeirante = 1L;
		
		FeiranteDAO feiranteDAO = new FeiranteDAO();
		Feirante feirante = feiranteDAO.buscar(codigoFeirante);
		
		Frequencia frequencia = new Frequencia();
		
		//Formatando a Data
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = null;
		try {
			dt = f.parse("05/10/2017");
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
		
		frequencia.setData(dataSql);
		frequencia.setSituacao(false);
		frequencia.setFeirante(feirante);
		
		FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
		frequenciaDAO.salvar(frequencia);
		
	}
	
	@Test
	@Ignore
	public void listar(){
		
		FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
		List<Frequencia> resultado = frequenciaDAO.listar();
		
		for(Frequencia frequencia : resultado){
			System.out.println("Registros Encotrado:");
			System.out.println("Código: " + frequencia.getCodigo());
			System.out.println("Data: " + frequencia.getData());
			System.out.println("Feirante: " + frequencia.getFeirante().getNomeFantasia());
			System.out.println("Situação: " + frequencia.getSituacao());
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		Long codigo = 2L;
		
		FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
		Frequencia frequencia = frequenciaDAO.buscar(codigo);
		
		if(frequencia == null){
			System.out.println("Nenhum Registro Encontrado");
		}else{
			System.out.println("Registro Encotrado:");
			System.out.println("Código: " + frequencia.getCodigo());
			System.out.println("Data: " + frequencia.getData());
			System.out.println("Feirante: " + frequencia.getFeirante().getNomeFantasia());
			System.out.println("Situação: " + frequencia.getSituacao());
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codigo = 4L;
		
		FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
		Frequencia frequencia = frequenciaDAO.buscar(codigo);
		
		frequenciaDAO.excluir(frequencia);
		
		System.out.println("Registro Removido:");
		System.out.println("Código: " + frequencia.getCodigo());
		System.out.println("Data: " + frequencia.getData());
		System.out.println("Feirante: " + frequencia.getFeirante().getNomeFantasia());
		System.out.println("Situação: " + frequencia.getSituacao());
			
	}
	
	@Test
	//@Ignore
	public void editar(){
		Long codigoFeirante = 1L;
		Long codigoFrequencia = 3L;
		
		FeiranteDAO feiranteDAO = new FeiranteDAO();
		Feirante feirante = feiranteDAO.buscar(codigoFeirante);
		
		FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
		Frequencia frequencia = frequenciaDAO.buscar(codigoFrequencia);
		
		System.out.println("Frequencia a ser editado:");
		System.out.println("Código: " + frequencia.getCodigo());
		System.out.println("Data: " + frequencia.getData());
		System.out.println("Feirante: " + frequencia.getFeirante().getNomeFantasia());
		System.out.println("Situação: " + frequencia.getSituacao());
		
		//Formatando a Data
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = null;
		try {
			dt = f.parse("07/10/2017");
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
		
		frequencia.setData(dataSql);
		frequencia.setSituacao(true);
		frequencia.setFeirante(feirante);
		
		System.out.println("Frequencia Edidado");
		System.out.println("Código: " + frequencia.getCodigo());
		System.out.println("Data: " + frequencia.getData());
		System.out.println("Feirante: " + frequencia.getFeirante().getNomeFantasia());
		System.out.println("Situação: " + frequencia.getSituacao());
	}

}
