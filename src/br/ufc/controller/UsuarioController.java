package br.ufc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.Util.Criptografia;
import br.ufc.dao.interfaces.NoticiaI;
import br.ufc.dao.interfaces.PapelI;
import br.ufc.dao.interfaces.SecaoI;
import br.ufc.dao.interfaces.UsuarioI;
import br.ufc.model.Noticia;
import br.ufc.model.Papel;
import br.ufc.model.Secao;
import br.ufc.model.Usuario;

@Transactional
@Controller
public class UsuarioController {

	@Autowired
	@Qualifier(value="usuarioDAO")
	private UsuarioI usuDAO;
	
	@Autowired
	@Qualifier(value="papelDAO")
	private PapelI papelDAO;
	
	@Autowired
	@Qualifier(value="secaoDAO")
	private SecaoI secaoDAO;
	
	@Autowired
	@Qualifier(value="noticiaDAO")
	private NoticiaI noticDAO;
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping("/cadastrarJornalistaFormulario")
	public String cadastrarJornalistaFormulario(){
		return "usuario/cadastrar_jornalista_form";
	}
	
	@RequestMapping("/cadastrarClassificadoFormulario")
	public String cadastrarClassificadoFormulario(){
		return "usuario/cadastrar_classificado_form";
	}
	
	@RequestMapping("/cadastrarSecaoFormulario")
	public String cadastrarSecaoFormulario() {
		return "usuario/cadastrar_secao_form";
	}
	@RequestMapping("/cadastrarJornalista")
	public String cadastrarJornalista(){
		return "";
	}
	@RequestMapping("/cadastrarClassificado")
	public String cadastrarClassificado(){
		return "usuario/cadastrar_classificado_form";
	}
	
	@RequestMapping("/cadastrarSecao")
	public String cadastrarSecao() {
		return "usuario/cadastrar_secao_form";
	}
	//Cadastrar Usuario Leitor
	@RequestMapping("/cadastrarUsuario")
	public String cadastrarUsuario(Usuario usuario,Model model)
	{
		List<Papel> papels = papelDAO.listar();
		Papel papel = new Papel();
		for (Papel p : papels) {
			if(p.getNomePalpel().equals("Leitor"))
			{
				papel = p;
			}
		}
		List<Papel> papeisLeitor = new ArrayList<Papel>();
		papeisLeitor.add(papel);
		usuario.setPapel(papeisLeitor);
		usuario.setSenha(Criptografia.criptografar(usuario.getSenha()));
		usuDAO.inserir(usuario);
		model.addAttribute("usuario_login", usuario);
		
		return "login";
	}
	
	@RequestMapping("/cadastrarUsuarioFormulario")
	public String cadastrarUsuarioFormulario(Model model)
	{
		Usuario aux = new Usuario();
		aux.setNome("");
		model.addAttribute("usuario_login", aux);
		return "usuario/cadastrar_usuario_formulario";
	}
	
	@RequestMapping("/paginaPrincipal")
	public String paginaPrincipal(Model model)
	{
		List<Noticia> noticias = noticDAO.listar();
		List<Secao> secoes = secaoDAO.listar();
		model.addAttribute("noticias_principal", noticias);
		model.addAttribute("secao_principal", secoes);
		for (Secao secao : secoes) {
			System.out.println(secao.getTitulo());
		}
		return "principal";
	}
	
	@RequestMapping("/areaDoEditor")
	public String areaDoEditor(String id){
		System.out.println("Tipo de Id"+id);
		if(id.equals("Editor")){
			return "usuario/area_editor";
		}
		
		return "redirect:paginaPrincipal";
	}
	
	@RequestMapping("/areaDoJornalista")
	public String areaDoJornalista(String id){
		System.out.println("Tipo de Id"+id);
		if(id.equals("Jornalista")){
			return "usuario/area_jornalista";
		}
		
		return "redirect:paginaPrincipal";
	}
	
}


