package br.com.feiradoprodutor.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.feiradoprodutor.domain.FeiranteFrequencia;
import br.com.feiradoprodutor.util.HibernateUtil;

public class FeiranteFrequenciaDAO extends GenericDAO<FeiranteFrequencia>{
	
	@SuppressWarnings("unchecked")
	public List<FeiranteFrequencia> buscaPorData(Long frequenciaCodigo){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			System.out.println("Código Frequencia:" +frequenciaCodigo);
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
	public List<FeiranteFrequencia> buscaGroup(){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(FeiranteFrequencia.class);
			ProjectionList pjList = Projections.projectionList();
			pjList.add(Projections.groupProperty("feirante.idfeirante"));
			consulta.setProjection(pjList);
			List<FeiranteFrequencia> resultado = consulta.list();
			for(FeiranteFrequencia f : resultado){
				System.out.println("Feirante"+f.getFeirante().getNomeFantasia());
			}
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
