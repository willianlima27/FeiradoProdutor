package br.com.feiradoprodutor.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;

import br.com.feiradoprodutor.dao.UsuarioDAO;
import br.com.feiradoprodutor.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable{
	
	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Usuario> filtroUsuarios;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public List<Usuario> getFiltroUsuarios() {
		return filtroUsuarios;
	}
	public void setFiltroUsuarios(List<Usuario> filtroUsuarios) {
		this.filtroUsuarios = filtroUsuarios;
	}
	
	@PostConstruct
	public void listar(){
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listarOrdenado("nome");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			usuario = new Usuario();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo usuário");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
			//define o método e o algoritmo de criptografia, bem como o atributo que será criptografado
			SimpleHash hash = new SimpleHash("md5", usuario.getSenha());
			//excecuta as regras definidas no hash e transforma a senha em uma composição hexadecimal de 32 caracteres
			usuario.setSenha(hash.toHex());
			usuarioDAO.salvar(usuario);
			
			usuario = new Usuario();
			usuarios = usuarioDAO.listarOrdenado("nome");
			
			Messages.addGlobalInfo("Usuário salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o usuário");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);
			
			usuarios = usuarioDAO.listar();

			Messages.addGlobalInfo("Usuário removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o usuário");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar o usuário");
			erro.printStackTrace();
		}	
	}
	

}
