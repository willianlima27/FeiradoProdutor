package br.com.feiradoprodutor.util;
import org.hibernate.Session;
import org.junit.Test;

import br.com.feiradoprodutor.util.HibernateUtil;

public class HibernateUtilTest {

	@Test
	public void conector(){
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	}
}
