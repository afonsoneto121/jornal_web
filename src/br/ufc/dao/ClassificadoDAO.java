package br.ufc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.ufc.dao.interfaces.ClassificadoI;
import br.ufc.model.Classificado;
import br.ufc.model.Comentario;

@Repository
public class ClassificadoDAO implements ClassificadoI{

	@PersistenceContext
	EntityManager manage;
	
	@Override
	public void inserir(Classificado classificado) {
		manage.persist(classificado);
		
	}

	@Override
	public void remover(Classificado classificado) {
		manage.remove(classificado);
		
	}

	@Override
	public void alterar(Classificado classificado) {
		manage.merge(classificado);
		
	}

	@Override
	public List<Classificado> listar() {
		return manage.createQuery("select s from classificado as s", Classificado.class).getResultList();
	}

	@Override
	public Classificado recuperar(Long id) {
		return manage.find(Classificado.class, id);
	}

}
