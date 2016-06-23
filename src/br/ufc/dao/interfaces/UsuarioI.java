package br.ufc.dao.interfaces;

import java.util.List;

import br.ufc.model.Usuario;

public interface UsuarioI {
	public void inserir(Usuario usuario);
	public void remover(Usuario usuario);
	public void alterar(Usuario usuario);
	public List<Usuario> listar();
	//Recuperar Usuario pelo Id
	public Usuario recuperar(Long id);
	//Recuperar Usuario pelo Login
	public Usuario recuperar(String login);
}
