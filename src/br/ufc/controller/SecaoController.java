package br.ufc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.interfaces.SecaoI;
import br.ufc.model.Secao;

@Transactional
@Controller
public class SecaoController {
	
	@Autowired
	@Qualifier(value="secaoDAO")
	private SecaoI secaoDAO;
	
	@RequestMapping("/cadastrarSecaoFormulario")
	public String cadastrarSecaoFormulario() {
		return "secao/cadastrar_secao_form";
	}
	
	@RequestMapping("/cadastrarSecao")
	public String cadastrarSecao(Secao secao) {
		secaoDAO.inserir(secao);
		return "usuario/area_editor";
	}
}
