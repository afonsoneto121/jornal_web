package br.ufc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.interfaces.NoticiaI;
import br.ufc.dao.interfaces.SecaoI;
import br.ufc.model.Noticia;
import br.ufc.model.Secao;

@Transactional
@Controller
public class SecaoController {
	
	@Autowired
	@Qualifier(value="secaoDAO")
	private SecaoI secaoDAO;
	
	@Autowired
	@Qualifier(value="noticiaDAO")
	private NoticiaI nDAO;
	
	@RequestMapping("/cadastrarSecaoFormulario")
	public String cadastrarSecaoFormulario() {
		return "secao/cadastrar_secao_form";
	}
	
	@RequestMapping("/cadastrarSecao")
	public String cadastrarSecao(Secao secao) {
		secaoDAO.inserir(secao);
		return "usuario/area_editor";
	}
	
	@RequestMapping("/listarNoticiasSecao")
	public String listarNoticiasSecao(Long id,Model model){
		Secao secao = secaoDAO.recuperar(id);
		List<Noticia> noticias = nDAO.listar();
		List<Noticia> noticiasSecao = new ArrayList<Noticia>();
		for(Noticia noticia : noticias){
			if(noticia.getSecao().getIdSecao() ==  id){
				noticiasSecao.add(noticia);
			}
		}
		model.addAttribute("secao", secao);
		model.addAttribute("noticias", noticiasSecao);
		List<Secao> secoes2 = secaoDAO.listar();
		model.addAttribute("secoes", secoes2);
		return "secao/noticia_secao";
	}
}
