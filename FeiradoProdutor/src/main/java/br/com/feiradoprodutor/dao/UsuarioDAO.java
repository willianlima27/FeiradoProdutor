package br.com.feiradoprodutor.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.feiradoprodutor.domain.Usuario;
import br.com.feiradoprodutor.util.HibernateUtil;


public class UsuarioDAO extends GenericDAO<Usuario>{
	
	public Usuario autenticar(String email, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			//comparação de igualdade entre o email recebido pelo método 
			// e o email presente na tabela usuario
			consulta.add(Restrictions.eq("email", email));

			//algortimo de criptografia
			//utilização do algortimo md5
			//gera um sequência hexadecimal de 32 caracteres
			SimpleHash hash = new SimpleHash("md5", senha);
			//toHex() é o método utilizado para aplicar o algortimo md5 na senha
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			//retorna apenas um resultado e faz um "cast" para usuário
			Usuario resultado = (Usuario) consulta.uniqueResult();

			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
