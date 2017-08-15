package br.com.feiradoprodutor.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessao = criaSessao();
	
	public static SessionFactory getFabricaDeSessoes(){
		
		return sessao;
	}
	
	private static SessionFactory criaSessao(){
		
		try{
			
			Configuration configuracao = new Configuration().configure();
			
			ServiceRegistry registro = new StandardServiceRegistryBuilder()
					.applySettings(configuracao.getProperties()).build();
			
			SessionFactory s = configuracao.buildSessionFactory(registro);
			
			return s;
			
		}catch(Throwable ex){
			
			System.err.println("A sessão não pôde ser criada." + ex);
			
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static Connection getConexao(){
		
		Session s = sessao.openSession();
		
		Connection conexao = s.doReturningWork(new ReturningWork<Connection>() {
			@Override
			public Connection execute(Connection conn) throws SQLException{
				return conn;
			}			
		});
		
		return conexao;		
	}
}
