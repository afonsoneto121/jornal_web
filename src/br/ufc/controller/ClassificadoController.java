package br.ufc.controller;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.interfaces.ClassificadoI;
import br.ufc.dao.interfaces.UsuarioI;
import br.ufc.model.Classificado;
import br.ufc.model.Usuario;

@Transactional
@Controller
public class ClassificadoController {
	@Autowired
	@Qualifier(value="usuarioDAO")
	private UsuarioI usuDAO;
	
	@Autowired
	@Qualifier(value="classificadoDAO")
	ClassificadoI clasDAO;
	
	@RequestMapping("/cadastrarClassificado")
	public String cadastrarClassificado(Classificado classificado, String login){
		Usuario usuario = usuDAO.recuperar(login);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		classificado.setDataOferta(calendar);
		
		classificado.setUsuario(usuario);
		classificado.setMelhorOferta(0F);
		clasDAO.inserir(classificado);
		return "usuario/area_editor";
	}
	
	@RequestMapping("/cadastrarClassificadoFormulario")
	public String cadastrarClassificadoFormulario(){
		return "classificado/cadastrar_classificado_form";
	}
	
}

