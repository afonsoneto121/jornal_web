package br.ufc.dao.interfaces;

import java.util.List;

import br.ufc.model.Comentario;

public interface ComentarioI {
	public void inserir(Comentario comentario);
	public void remover(Comentario comentario);
	public void alterar(Comentario comentario);
	public List<Comentario> listar();
	//Recuperar Comentario Pelo Id
	public Comentario recuperar(Long id);
}
