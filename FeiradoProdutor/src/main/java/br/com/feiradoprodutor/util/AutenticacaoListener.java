package br.com.feiradoprodutor.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.feiradoprodutor.bean.AutenticacaoBean;
import br.com.feiradoprodutor.domain.Usuario;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent event) {
		//Pega a página atual
		String paginaAtual = Faces.getViewId();
		
		//Verifica se a página é autenticada
		boolean paginaautenticacao = paginaAtual.contains("autenticacao.xhtml");
		
		//Se a página não for autenticada
		if(!paginaautenticacao){
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			//Verifica se o bean foi criado
			if(autenticacaoBean == null){
				//Se for nulo, direciona
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
			
			Usuario usuario = autenticacaoBean.getUsuarioLogado();
			//Verifica se o usuário é nulo
			if(usuario == null){
				//Se for nulo, direciona
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
		}
				
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
