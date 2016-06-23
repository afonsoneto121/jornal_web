package br.ufc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufc.dao.interfaces.SecaoI;
import br.ufc.model.Secao;
import br.ufc.model.Usuario;

@Repository
public class SecaoDAO implements SecaoI{
	@PersistenceContext
	EntityManager manage;
	
	@Override
	public void inserir(Secao secao) {
		manage.persist(secao);
		
	}

	@Override
	public void remover(Secao secao) {
		manage.remove(secao); 
		
	}

	@Override
	public void alterar(Secao secao) {
		manage.merge(secao);
		
	}

	@Override
	public List<Secao> listar() {
		return manage.createQuery("select s from secao as s", Secao.class).getResultList();
	}

	@Override
	public Secao recuperar(Long id) {
		return manage.find(Secao.class, id);
	}

	@Override
	public Secao recuperar(String titulo) {
		List<Secao> secList;
		String hql = "select u from secao as u where u.titulo = :titulo";
		Query qr = manage.createQuery(hql);
		secList = qr.setParameter("titulo", titulo).getResultList();
		if(secList.size() != 0)
		{
			return secList.get(0);
		}
		return null;
	}

}
