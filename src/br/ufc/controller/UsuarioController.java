package br.ufc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.Util.Criptografia;
import br.ufc.Util.JornalFileUtil;
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
	@Qualifier(value="mailSender")
    private JavaMailSender mailSender;
	
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
	
	@RequestMapping("/cadastrarEditorFormulario")
	public String cadastrarEditorFormulario(){
		return "usuario/cadastrar_editor_form";
	}
	
	//Cadastrar Usuario Editor
	@RequestMapping("/cadastrarEditor")
	public String cadastrarEditor(Usuario usuario,
				@RequestParam(value="imagem", required=false) MultipartFile imagem){
		List<Papel> papels = papelDAO.listar();
		Papel papel = new Papel();
		Papel papelLeitor = new Papel();
		List<Papel> papeisJornalista = new ArrayList<Papel>();
		
		for (Papel p : papels) {
			if(p.getNomePalpel().equals("Editor"))
			{
				papel = p;
			}
		}
		papeisJornalista.add(papel);
		usuario.setPapel(papeisJornalista);
		usuario.setSenha(Criptografia.criptografar(usuario.getSenha()));
		usuario.setNomePapel("Editor");
		String path = servletContext.getRealPath("/")+"WEB-INF/resources/images/" + usuario.getLogin() + ".png";
		
		JornalFileUtil.salvarImagem(path, imagem);
		usuDAO.inserir(usuario);
		
		return "usuario/area_editor";
	}
	
	//Cadastrar Usuario Jornalista
	@RequestMapping("/cadastrarJornalista")
	public String cadastrarJornalista(Usuario usuario, String teste,
			@RequestParam(value="imagem", required=false) MultipartFile imagem){
		List<Papel> papels = papelDAO.listar();
		Papel papel = new Papel();
		Papel papelLeitor = new Papel();
		List<Papel> papeisJornalista = new ArrayList<Papel>();
		
		if(teste != null)
		{
			for (Papel p : papels) {
				if(p.getNomePalpel().equals("Leitor"))
				{
					papelLeitor = p;
				}
			}
			papeisJornalista.add(papelLeitor);
		}
		for (Papel p : papels) {
			if(p.getNomePalpel().equals("Jornalista"))
			{
				papel = p;
			}
		}
		papeisJornalista.add(papel);
		usuario.setPapel(papeisJornalista);
		usuario.setSenha(Criptografia.criptografar(usuario.getSenha()));
		usuario.setNomePapel("Jornalista");
		usuDAO.inserir(usuario);
		String path = servletContext.getRealPath("/")+"WEB-INF/resources/images/" + usuario.getLogin() + ".png";
		
		JornalFileUtil.salvarImagem(path, imagem);
		return "usuario/area_editor";
	}

	//Cadastrar Usuario Leitor
	@RequestMapping("/cadastrarUsuario")
	public String cadastrarUsuario(Usuario usuario,Model model,
			@RequestParam(value="imagem", required=false) MultipartFile imagem)
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
		String path = servletContext.getRealPath("/")+"WEB-INF/resources/images/" + usuario.getLogin() + ".png";
		
		JornalFileUtil.salvarImagem(path, imagem);
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
	
	@RequestMapping("/pefilUsuario")
	public String pefilUsuario(Long id, Model model){
		Usuario usurio = usuDAO.recuperar(id);
		List<Secao> secaos = secaoDAO.listar();
		model.addAttribute("secoes", secaos);
		model.addAttribute("usuario", usurio);
		return "usuario/perfil_usuario";
	}
	
	@RequestMapping("/apagarUsuario")
	public String apagarUsuario(Long id, Model model){
		Usuario usuario = usuDAO.recuperar(id);
		usuDAO.remover(usuario);
		String path = servletContext.getRealPath("/")+"WEB-INF/resources/images/" + usuario.getLogin() + ".png";
		
		JornalFileUtil.removerArquivos(path);
		
		return "redirect:logout";
	}
	
	@RequestMapping("/editarUsuarioForm")
	public String editarUsuarioForm(Long id,Model model) {
		Usuario usuario = usuDAO.recuperar(id);
		model.addAttribute("usuario", usuario);
				
		return "usuario/alterar_usuario_form";
	}
	
	@RequestMapping("/editarUsuario")
	public String editarUsuario(Long idUsuario,String nome,String email,String login) {
		Usuario usuario = usuDAO.recuperar(idUsuario);
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setLogin(login);
		
		usuDAO.alterar(usuario);
		return "redirect:pefilUsuario?id=" + usuario.getIdUsuario();
	}

	@RequestMapping("/enviarEmail")
    public String enviarEmail(String login) {
		Usuario usuario = usuDAO.recuperar(login);
		System.out.println(usuario.getEmail()+" "+ usuario.getLogin());
    	if(usuario != null)
    	{
    		String emailString = usuario.getEmail();
    		System.out.println(emailString);
    		
    		SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(emailString);
            email.setSubject("Nova Senha para jornal Eletronico");
            Random gerador = new Random();
            String novaSenha = "";

    	    for (int i = 0; i < 4; i++) {
    	    	novaSenha = novaSenha + gerador.nextInt(10);
    		 }
            usuario.setSenha(Criptografia.criptografar(novaSenha));
            usuDAO.alterar(usuario);
            email.setText("Sua nova Senha é " + novaSenha);
             
            mailSender.send(email);
            return "redirect:loginFormulario";
    	}
    	return "redirect:recuperarSenha";    	
    }
	
	@RequestMapping("/alterarSenhaForm")
	public String alterarSenhaForm(Long id,Model model){
		Usuario usuario = usuDAO.recuperar(id);
		model.addAttribute("usuario", usuario);
		return "usuario/muda_senha";
	}
	
	@RequestMapping("/alterarSenha")
	public String alterarSenha(Long id, String primeiro, String segundo, String antiga, Model model,
							HttpSession session){
		if(primeiro.equals(segundo)){
			Usuario usuario = usuDAO.recuperar(id);
			if(usuario.getSenha().equals(Criptografia.criptografar(antiga))){
				usuario.setSenha(Criptografia.criptografar(primeiro));
				usuDAO.alterar(usuario);
				session.setAttribute("usuario_logado", usuario);
				return "redirect:pefilUsuario?id="+id;
			}
			return "redirect:alterarSenhaForm?id="+id;
			
		}
		
		return "redirect:alterarSenhaForm?id="+id;
	}
	
	@RequestMapping("/informacaoAutor")
	public String informacaoAutor(Long id,Model model){
		Usuario usuario = usuDAO.recuperar(id);
		model.addAttribute("usuario", usuario);
		return "usuario/informacao";
	}
}


