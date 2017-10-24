package br.com.feiradoprodutor.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.feiradoprodutor.domain.FeiranteProduto;
import br.com.feiradoprodutor.util.HibernateUtil;



public class FeiranteProdutoDAO extends GenericDAO<FeiranteProduto>{
	
	@SuppressWarnings("unchecked")
	public List<FeiranteProduto> buscaPorFeirante(Long feiranteCodigo){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(FeiranteProduto.class);
			consulta.add(Restrictions.eq("feirante.codigo", feiranteCodigo));
			consulta.addOrder(Order.asc("produto"));
			List<FeiranteProduto> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){
			throw erro;
		}finally{
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FeiranteProduto> listarNaoVinculados(Long feiranteCodigo, Long produtoCodigo){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(FeiranteProduto.class);
			consulta.add(Restrictions.eq("feirante.codigo", feiranteCodigo));
			consulta.add(Restrictions.eq("produto.codigo", produtoCodigo));
			consulta.addOrder(Order.asc("produto"));
			List<FeiranteProduto> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){
			throw erro;
		}finally{
			sessao.close();
		}
	}

}
