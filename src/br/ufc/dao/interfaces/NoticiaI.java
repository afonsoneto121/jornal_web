package br.ufc.dao.interfaces;

import java.util.List;

import br.ufc.model.Noticia;

public interface NoticiaI {
	public void inserir(Noticia noticia);
	public void remover(Noticia noticia);
	public void alterar(Noticia noticia);
	public List<Noticia> listar();
	//Recuperar Noticia pelo ID
	public Noticia recuperar(Long id);
	//Recuperar Noticia pelo Titulo
	public List<Noticia> recuperar(String titulo);
	//Recuperar as ultimas noticias cadastradas
	public List<Noticia> recuperarUltimas();
}
