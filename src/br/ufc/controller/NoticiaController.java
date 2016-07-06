package br.ufc.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.interfaces.NoticiaI;
import br.ufc.dao.interfaces.SecaoI;
import br.ufc.dao.interfaces.UsuarioI;
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
		return "usuario/area_editor";
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
	public String cadastrarNoticia(Noticia noticia, String secaoTitulo, String login){
		Secao secao = secDAO.recuperar(secaoTitulo);
		Usuario usuario = usuDAO.recuperar(login);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		noticia.setDataNoticia(calendar);
		noticia.setSecao(secao);
		noticia.setUsuario(usuario);
		notiDAO.inserir(noticia);
		return "usuario/area_jornalista";
	}
	@RequestMapping("/listarNoticiaJornalista")
	public String listarNoticiaJornalista(String id,Model model) {
		Usuario usu = usuDAO.recuperar(id);
		List<Noticia> todas = notiDAO.listar();
		List<Noticia> noticiaJornalista = new ArrayList<Noticia>();
		for (Noticia noticia : todas) {
			if(usu.getLogin().equals( (noticia.getUsuario()).getLogin()) ){
				noticiaJornalista.add(noticia);
			}
			
		}
		model.addAttribute("noticias", noticiaJornalista);
		return "usuario/noticia_jornalista";
	}
}
