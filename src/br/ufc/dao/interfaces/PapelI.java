package br.ufc.dao.interfaces;

import java.util.List;

import br.ufc.model.Papel;

public interface PapelI {
	public void inserir(Papel papel);
	public void remover(Papel papel);
	public void alterar(Papel papel);
	public List<Papel> listar();
	//Recuperar Papel pelo ID
	public Papel recuperar(Long id);
	//Recuperar Papel pelo Nome
	public Papel recuperar(String nomePapel);
}
