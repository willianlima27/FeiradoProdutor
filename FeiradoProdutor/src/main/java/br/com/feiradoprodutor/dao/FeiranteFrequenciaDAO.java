package br.com.feiradoprodutor.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.feiradoprodutor.domain.FeiranteFrequencia;
import br.com.feiradoprodutor.util.HibernateUtil;

public class FeiranteFrequenciaDAO extends GenericDAO<FeiranteFrequencia>{
	
	@SuppressWarnings("unchecked")
	public List<FeiranteFrequencia> buscaPorData(Long frequenciaCodigo){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(FeiranteFrequencia.class);
			consulta.add(Restrictions.eq("frequencia.codigo", frequenciaCodigo));
			List<FeiranteFrequencia> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){
			throw erro;
		}finally{
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FeiranteFrequencia> buscaPorFeirante(Long feiranteCodigo){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(FeiranteFrequencia.class);
			consulta.add(Restrictions.eq("feirante.codigo", feiranteCodigo));
			List<FeiranteFrequencia> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){
			throw erro;
		}finally{
			sessao.close();
		}
	}

}
