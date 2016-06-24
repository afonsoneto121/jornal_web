package br.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.interfaces.NoticiaI;
import br.ufc.model.Noticia;

@Transactional
@Controller
public class NoticiaController {

	@Autowired
	@Qualifier(value ="noticiaDAO")
	NoticiaI notiDAO;
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
}
