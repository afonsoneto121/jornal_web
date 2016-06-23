package br.ufc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.ufc.dao.interfaces.ComentarioI;
import br.ufc.model.Comentario;
import br.ufc.model.Noticia;

@Repository
public class ComentarioDAO implements ComentarioI{

	@PersistenceContext
	EntityManager manage;
	
	@Override
	public void inserir(Comentario comentario) {
		manage.persist(comentario);
		
	}

	@Override
	public void remover(Comentario comentario) {
		manage.remove(comentario);
		
	}

	@Override
	public void alterar(Comentario comentario) {
		manage.merge(comentario);
		
	}

	@Override
	public List<Comentario> listar() {
		return manage.createQuery("select s from comentario as s", Comentario.class).getResultList();

	}

	@Override
	public Comentario recuperar(Long id) {
		return manage.find(Comentario.class, id);
	}

}
