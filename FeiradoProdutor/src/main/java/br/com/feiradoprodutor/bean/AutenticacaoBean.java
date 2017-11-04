package br.com.feiradoprodutor.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.feiradoprodutor.dao.UsuarioDAO;
import br.com.feiradoprodutor.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	
	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
	}

	public void autenticar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			//usuarioLogado recebe o resultado do método autenticar que está no UsuarioDAO
			usuarioLogado = usuarioDAO.autenticar(usuario.getEmail(), usuario.getSenha());
			
			if(usuarioLogado == null){
				Messages.addGlobalError("Informações de acesso incorretos");
				return;
			}
				
			Faces.redirect("./pages/feirante.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}
	
	public void sair(){
		usuarioLogado = null;
	}
	
}
