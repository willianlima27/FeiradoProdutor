package br.com.feiradoprodutor.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.feiradoprodutor.domain.Cidade;
import br.com.feiradoprodutor.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade>{
	@SuppressWarnings("unchecked")
	public List<Cidade> buscaPorEstado(Long estadoCodigo){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Cidade.class);
			consulta.add(Restrictions.eq("estado.id", estadoCodigo));
			consulta.addOrder(Order.asc("nomeCidade"));
			List<Cidade> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){
			throw erro;
		}finally{
			sessao.close();
		}
	}

}
