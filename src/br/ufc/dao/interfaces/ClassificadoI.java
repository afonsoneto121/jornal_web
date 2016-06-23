package br.ufc.dao.interfaces;

import java.util.List;

import br.ufc.model.Classificado;

public interface ClassificadoI {
	public void inserir(Classificado classificado);
	public void remover(Classificado classificado);
	public void alterar(Classificado classificado);
	public List<Classificado> listar();
	//Recuperar Classificado pelo ID
	public Classificado recuperar(Long id);
}
