package br.com.feiradoprodutor.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import br.com.feiradoprodutor.domain.Frequencia;
import br.com.feiradoprodutor.util.HibernateUtil;

public class FrequenciaDAO extends GenericDAO<Frequencia>{
	
	public Long listarUltimo(){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Frequencia.class);
			consulta.setProjection(Projections.max("codigo"));
			return (Long) consulta.uniqueResult();
		}catch(RuntimeException erro){
			throw erro;
		}finally{
			sessao.close();
		}
	}
	
}
