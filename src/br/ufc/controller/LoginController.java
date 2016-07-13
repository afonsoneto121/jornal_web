package br.ufc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufc.Util.Criptografia;
import br.ufc.dao.interfaces.NoticiaI;
import br.ufc.dao.interfaces.PapelI;
import br.ufc.dao.interfaces.SecaoI;
import br.ufc.dao.interfaces.UsuarioI;
import br.ufc.model.Noticia;
import br.ufc.model.Papel;
import br.ufc.model.Secao;
import br.ufc.model.Usuario;

@Controller
public class LoginController {

	@Autowired
	@Qualifier(value="usuarioDAO")
	private UsuarioI usuDAO;
	@Autowired
	@Qualifier(value="papelDAO")
	private PapelI papelDAO;
	
	@Autowired
	@Qualifier(value="noticiaDAO")
	private NoticiaI notcDAO;
	
	@Autowired
	@Qualifier(value="secaoDAO")
	private SecaoI secaoDAO;
	@RequestMapping("/login")
	public String login(Usuario usuario, HttpSession session, Model model){
		List<Papel> papeis = papelDAO.listar();
		Papel papel2 = new Papel();
		String s = usuario.getNomePapel();
		for(Papel p : papeis){
			if(p.getNomePalpel().equals(s))
				papel2 = p;
		}
		
		Usuario usu = usuDAO.recuperar(usuario.getLogin());
		//System.out.println(usu.getNome()+ ","+papel2.getNomePalpel());
		if(usu != null)
		{
			if(usu.getPapel().contains(papel2)){
				System.out.println(usu.getSenha()+", "+Criptografia.criptografar(usuario.getSenha()));
				if(usu.getSenha().equals(Criptografia.criptografar(usuario.getSenha()))){
					usu.setNomePapel(s);
					session.setAttribute("usuario_logado", usu);
					List<Noticia> noticias = notcDAO.listar();
					List<Secao> secoes = secaoDAO.listar();
					model.addAttribute("noticias_principal", noticias);
					model.addAttribute("secao_principal", secoes);
					//System.out.println("Entrou");
					return "principal";
				}
			}
		}
		return "redirect:loginFormulario";
	}
	@RequestMapping("/loginFormulario")
	public String loginFormulario(Model model){
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:paginaPrincipal";
	}
	
	@RequestMapping("/recuperarSenha")
	public String recuperarSenha(){
		
		return "usuario/recuperar_senha";
	}

	
}
