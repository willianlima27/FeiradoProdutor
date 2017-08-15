package br.com.feiradoprodutor.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.feiradoprodutor.util.HibernateUtil;

public class GenericDAO<Entidade> {
	
private Class<Entidade> classe;
	
	//Pega a classe em execusão e atribui a classe - Api Reflection
	@SuppressWarnings("unchecked")
	public GenericDAO(){
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void salvar(Entidade entidade){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		
		try{	
			transacao = sessao.beginTransaction();
			sessao.merge(entidade);
			transacao.commit();
		} catch (RuntimeException erro){
			if(transacao != null){
				transacao.rollback();	
			}
			throw erro;
		}finally {
			sessao.close();
		}
		
	} 
	
	@SuppressWarnings("unchecked")
	public List<Entidade> listar(){
		//acessa a fábrica
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			//atribui a classe em execução a consulta
			Criteria consulta = sessao.createCriteria(classe);
			//atribui a consulta ao resultado do tipo List
			List<Entidade> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){
			throw erro;
		}finally{
			sessao.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Entidade> listarOrdenado(String campoOrdenado){
		//acessa a fábrica de e...
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(classe);
			consulta.addOrder(Order.asc(campoOrdenado));
			List<Entidade> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){
			throw erro;
		}finally{
			//sessao.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public Entidade buscar(long codigo){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(classe);
			//Restriction são os criterios de consulta, que nesse caso está pesquisando o código.
			consulta.add(Restrictions.idEq(codigo));
			//Converte a consulta do uniqueResult para resultado do tipo Entidade
			Entidade resultado = (Entidade) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro){
			throw erro;
		} finally{
			sessao.close();
		}
	}
	
	public void excluir(Entidade entidade){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(entidade);
			transacao.commit();
		} catch (RuntimeException erro){
			if(transacao != null){
				transacao.rollback();	
			}
			throw erro;
		}finally {
			sessao.close();
		}
		
	}
	
	public void editar(Entidade entidade){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(entidade);
			transacao.commit();
		} catch (RuntimeException erro){
			if(transacao != null){
				transacao.rollback();	
			}
			throw erro;
		}finally {
			sessao.close();
		}
		
	} 

}
