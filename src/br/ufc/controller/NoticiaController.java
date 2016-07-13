package br.ufc.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.Util.JornalFileUtil;
import br.ufc.dao.interfaces.ComentarioI;
import br.ufc.dao.interfaces.NoticiaI;
import br.ufc.dao.interfaces.SecaoI;
import br.ufc.dao.interfaces.UsuarioI;
import br.ufc.model.Comentario;
import br.ufc.model.Noticia;
import br.ufc.model.Secao;
import br.ufc.model.Usuario;

@Transactional
@Controller
public class NoticiaController {

	@Autowired
	@Qualifier(value ="usuarioDAO")
	UsuarioI usuDAO;
	
	@Autowired
	@Qualifier(value ="noticiaDAO")
	NoticiaI notiDAO;
	
	@Autowired
	@Qualifier(value ="secaoDAO")
	SecaoI secDAO;
	
	@Autowired
	@Qualifier(value ="comentarioDAO")
	ComentarioI comDAO;
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping("/listarNoticiaFormuario")
	public String listarNoticiaFormuario(){
		return "noticia/noticias_editor";
	}
	@RequestMapping("/listaNoticiaTitulo")
	public String listaNoticiaTitulo(String titulo,Model model){
		List<Noticia> noticias = notiDAO.recuperar(titulo);
		
		model.addAttribute("noticiasDel", noticias);
		return "noticia/noticias_editor";
	}
	
	@RequestMapping("/apagarNoticia")
	public String apagarNoticia(Long id){
		Noticia noticia = notiDAO.recuperar(id);
		notiDAO.remover(noticia);
		String path = servletContext.getRealPath("/")+"WEB-INF/resources/images/" + noticia.getIdNoticia() + ".png";
		
		JornalFileUtil.removerArquivos(path);
		return "redirect:paginaPrincipal";
	}
	
	@RequestMapping("/verNoticia")
	public String verNoticia(Long id){
		
		return "noticia/noticia_individual";
	}
	
	@RequestMapping("/cadastrarNoticiaFormulario")
	public String cadastrarNoticiaFormulario(Model model){
		List<Secao> secoes = secDAO.listar();
		model.addAttribute("secoes", secoes);
		
		return "noticia/cadastrar_noticia_formulario";
	}

	@RequestMapping("/cadastrarNoticia")
	public String cadastrarNoticia(Noticia noticia, String secaoTitulo, String login,
			@RequestParam(value="imagem", required=false) MultipartFile imagem){
		
		Secao secao = secDAO.recuperar(secaoTitulo);
		Usuario usuario = usuDAO.recuperar(login);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		noticia.setDataNoticia(calendar);
		noticia.setSecao(secao);
		noticia.setUsuario(usuario);
		notiDAO.inserir(noticia);
		List<Noticia> list = notiDAO.listar();
		Long size = 0l;
		for (Noticia noticia2 : list) {
			size = noticia2.getIdNoticia();
		}
		
		String path = servletContext.getRealPath("/")+"WEB-INF/resources/images/" + size + ".png";
		
		JornalFileUtil.salvarImagem(path, imagem);
		return "usuario/area_jornalista";
	}
	@RequestMapping("/listarNoticiaJornalista")
	public String listarNoticiaJornalista(String id,Model model) {
		Usuario usu = usuDAO.recuperar(id);
		List<Noticia> todas = notiDAO.listar();
		List<Noticia> noticiaJornalista = new ArrayList<Noticia>();
		List<Secao> secaos = secDAO.listar();
		model.addAttribute("secoes", secaos);
		for (Noticia noticia : todas) {
			if(usu.getLogin().equals( (noticia.getUsuario()).getLogin()) ){
				noticiaJornalista.add(noticia);
			}
			
		}
		model.addAttribute("noticias", noticiaJornalista);
		return "usuario/noticia_jornalista";
	}
	
	@RequestMapping("/direcionarNoticia")
	public String direcionarNoticia(Model model,Long id){
		//Long idBanco = Long.parseLong(id);
		Noticia noticia = notiDAO.recuperar(id);
		List<Comentario> comentarios = comDAO.listar();
		List<Comentario> comentarioNoticia = new ArrayList<>();
		for (Comentario comentario : comentarios) {
			if(comentario.getNoticia().getIdNoticia() == noticia.getIdNoticia()) {
				comentarioNoticia.add(comentario);
			}
		}
		model.addAttribute("noticia", noticia);
		model.addAttribute("comentario", comentarioNoticia);
		List<Secao> secoes = secDAO.listar();
		model.addAttribute("secoes", secoes);
		return "noticia/ver_noticia";
	}
}
