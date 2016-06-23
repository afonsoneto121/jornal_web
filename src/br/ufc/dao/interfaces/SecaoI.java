package br.ufc.dao.interfaces;

import java.util.List;

import br.ufc.model.Secao;

public interface SecaoI {
	public void inserir(Secao secao);
	public void remover(Secao secao);
	public void alterar(Secao secao);
	public List<Secao> listar();
	//Recuperar Seção por ID
	public Secao recuperar(Long id);
	//Recuperar Seção por titulo
	public Secao recuperar(String titulo);
}
