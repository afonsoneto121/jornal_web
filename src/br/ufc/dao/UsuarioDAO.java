package br.ufc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufc.dao.interfaces.UsuarioI;
import br.ufc.model.Usuario;

@Repository
public class UsuarioDAO implements UsuarioI{

	@PersistenceContext
	EntityManager manage;
	
	@Override
	public void inserir(Usuario usuario) {
		manage.persist(usuario);		
	}

	@Override
	public void remover(Usuario usuario) {
		manage.remove(usuario);
		
	}

	@Override
	public void alterar(Usuario usuario) {
		manage.merge(usuario);
	}

	@Override
	public List<Usuario> listar() {
		List<Usuario> usuarios = manage.createQuery("select u from usuario as u", Usuario.class).getResultList();
		return usuarios;
	}

	@Override
	public Usuario recuperar(Long id) {
		return manage.find(Usuario.class, id);
	}

	@Override
	public Usuario recuperar(String login) {
		List<Usuario> usuList;
		String hql = "select u from usuario as u where u.login = :login";
		Query qr = manage.createQuery(hql);
		usuList = qr.setParameter("login", login).getResultList();
		if(usuList.size() != 0)
		{
			return usuList.get(0);
		}
		return null;
	}

}
