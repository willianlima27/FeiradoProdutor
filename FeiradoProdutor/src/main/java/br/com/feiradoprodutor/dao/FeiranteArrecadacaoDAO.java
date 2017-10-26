package br.com.feiradoprodutor.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.feiradoprodutor.domain.FeiranteArrecadacao;
import br.com.feiradoprodutor.util.HibernateUtil;

public class FeiranteArrecadacaoDAO extends GenericDAO<FeiranteArrecadacao>{
	
	@SuppressWarnings("unchecked")
	public List<FeiranteArrecadacao> buscaPorData(Long arrecadacaoCodigo){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(FeiranteArrecadacao.class);
			consulta.add(Restrictions.eq("arrecadacao.codigo", arrecadacaoCodigo));
			List<FeiranteArrecadacao> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){
			throw erro;
		}finally{
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FeiranteArrecadacao> buscaPorFeirante(Long feiranteCodigo){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(FeiranteArrecadacao.class);
			consulta.add(Restrictions.eq("feirante.codigo", feiranteCodigo));
			List<FeiranteArrecadacao> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){
			throw erro;
		}finally{
			sessao.close();
		}
	}

}
