package br.ufc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.interfaces.ComentarioI;
import br.ufc.dao.interfaces.NoticiaI;
import br.ufc.dao.interfaces.SecaoI;
import br.ufc.dao.interfaces.UsuarioI;
import br.ufc.model.Comentario;
import br.ufc.model.Noticia;
import br.ufc.model.Usuario;

@Transactional
@Controller
public class ComentarioController {
	
	@Autowired
	@Qualifier(value="comentarioDAO")
	private ComentarioI cDAO;
	
	@Autowired
	@Qualifier(value="noticiaDAO")
	private NoticiaI nDAO;
	
	@Autowired
	@Qualifier(value="secaoDAO")
	private SecaoI sDAO;
	
	@Autowired
	@Qualifier(value="usuarioDAO")
	private UsuarioI uDAO;
	
	@RequestMapping("/inserirComentario")
	public String inserirComentario(Comentario comentario, String login, Long id)
	{
		Noticia noticia = nDAO.recuperar(id);
		Usuario usuario = uDAO.recuperar(login);
		//System.out.println(noticia.getTitulo() + " " + login);
		comentario.setUsuario(usuario);
		comentario.setNoticia(noticia);
		this.cDAO.inserir(comentario);
		return "redirect:direcionarNoticia?id=" + noticia.getIdNoticia();
	}
}
