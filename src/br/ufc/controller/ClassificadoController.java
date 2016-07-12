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

import br.ufc.dao.interfaces.ClassificadoI;
import br.ufc.dao.interfaces.SecaoI;
import br.ufc.dao.interfaces.UsuarioI;
import br.ufc.model.Classificado;
import br.ufc.model.Secao;
import br.ufc.model.Usuario;

@Transactional
@Controller
public class ClassificadoController {
	@Autowired
	@Qualifier(value="usuarioDAO")
	private UsuarioI usuDAO;
	
	@Autowired
	@Qualifier(value="secaoDAO")
	private SecaoI sDAO;
	
	@Autowired
	@Qualifier(value="classificadoDAO")
	private ClassificadoI clasDAO;
	
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
	
	@RequestMapping("/listarClassificados")
	public String listarClassificados(Model model){
		List<Classificado> classificados = clasDAO.listar();
		model.addAttribute("classificados", classificados);
		List<Secao> secoes = this.sDAO.listar();
		model.addAttribute("secoes", secoes);
		return "classificado/listar_classificados";
	}
	
	@RequestMapping("/inserirOfertaForm")
	public String inserirOfertaForm(Long id,Model model){
		Classificado classificado = clasDAO.recuperar(id);
		Usuario usuario = null;
		
		if(classificado.getIdAutoMO() != null){
			usuario = usuDAO.recuperar(classificado.getIdAutoMO());
		}
		model.addAttribute("autor", usuario);
		model.addAttribute("classificado", classificado);
		List<Secao> secoes = this.sDAO.listar();
		model.addAttribute("secoes", secoes);
		return "classificado/inserir_oferta_form";
	}
	
	@RequestMapping("/inserirOferta")
	public String inserirOferta(Long id_classificado, Long id_usuario, String oferta){
		Classificado classificado = clasDAO.recuperar(id_classificado);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		if(classificado.getVendido()==1)
		{
			return "redirect:listarClassificados";
		}
		if(classificado.getMelhorOferta() == null && classificado.getPreco() < Float.parseFloat(oferta)){
			//classificado.setDataOferta(calendar);
			classificado.setMelhorOferta(Float.parseFloat(oferta));
			classificado.setIdAutoMO(id_usuario);
			clasDAO.alterar(classificado);
			return "redirect:listarClassificados";
		}
		if(classificado.getMelhorOferta() != null){
			if(classificado.getMelhorOferta() > Float.parseFloat(oferta) || classificado.getPreco() > Float.parseFloat(oferta)){
				return "redirect:inserirOfertaForm?id=" + classificado.getId();
			}
			//classificado.setDataOferta(calendar);
			classificado.setMelhorOferta(Float.parseFloat(oferta));
			classificado.setIdAutoMO(id_usuario);
			clasDAO.alterar(classificado);
			return "redirect:listarClassificados";
		}
		return "redirect:inserirOfertaForm?id=" + classificado.getId();
	}
	
	@RequestMapping("/apagarClassificado")
	public String apagarClassificado(Long id) {
		clasDAO.remover(clasDAO.recuperar(id));
		
		return "redirect:listarClassificados";
	}

	@RequestMapping("/venderClassificado")
	public String venderClassificado(Long id){
		Classificado classificado = clasDAO.recuperar(id);
		classificado.setVendido(1);
		clasDAO.alterar(classificado);
		return "redirect:listarClassificados";
	}
	
	@RequestMapping("/alterarClassificadoForm")
	public String alterarClassificadoForm(Long id, Model model, Model secao){
		Classificado classificado = clasDAO.recuperar(id);
		model.addAttribute("classificado", classificado);
		List<Secao> secoes = this.sDAO.listar();
		
		secao.addAttribute("secoes", secoes);
		
		return "classificado/alterar_classificado_form";
	}
	
	@RequestMapping("/alterarClassificado")
	public String alterarClassificado(Long id_classificado, String tituloClassificado, String textoClassificado,
			String precoClassificado, String telefoneClassificado){
		
		Classificado classificado = clasDAO.recuperar(id_classificado);
		Usuario usuario = usuDAO.recuperar(classificado.getIdUsuario());

		classificado.setPreco(Float.parseFloat(precoClassificado));
		classificado.setTexto(textoClassificado);
		classificado.setTelefone(telefoneClassificado);
		classificado.setTitulo(tituloClassificado);
		classificado.setUsuario(usuario);
		classificado.setVendido(0);
		
		clasDAO.alterar(classificado);
		
		return "redirect:listarClassificados";
	}
	
	@RequestMapping("/lanceIndividual")
	public String lanceIndividual(Long id,Model model){
		List<Classificado> classificados = clasDAO.listar();
		List<Classificado> list = new ArrayList<Classificado>();
		for (Classificado classificado : classificados) {
			if(classificado.getIdAutoMO() == id && classificado.getVendido() == 1) {
				list.add(classificado);
			}
		}
		model.addAttribute("classiicados", list);
		return "classificado/meu_lance";
	}
	
	@RequestMapping("/confirmarComprar")
	public String confirmarComprar(Long id) {
		
		return "redirect:apagarClassificado?id=" + id;
	}
}

