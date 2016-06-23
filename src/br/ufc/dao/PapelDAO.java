package br.ufc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufc.dao.interfaces.PapelI;
import br.ufc.model.Papel;
import br.ufc.model.Papel;

@Repository
public class PapelDAO implements PapelI{

	@PersistenceContext
	EntityManager manage;

	@Override
	public void inserir(Papel papel) {
		manage.persist(papel);
		
	}

	@Override
	public void remover(Papel papel) {
		manage.remove(papel);
		
	}

	@Override
	public void alterar(Papel papel) {
		manage.merge(papel);
		
	}

	@Override
	public List<Papel> listar() {
		return  manage.createQuery("select s from papel as s", Papel.class).getResultList();
	}

	@Override
	public Papel recuperar(Long id) {
		return manage.find(Papel.class, id);
	}

	@Override
	public Papel recuperar(String nomePapel) {
		List<Papel> papList;
		String hql = "select u from papel as u where u.nome_papel = :nome";
		Query qr = manage.createQuery(hql);
		papList = qr.setParameter("nome", nomePapel).getResultList();
		if(papList.size() != 0)
		{
			return papList.get(0);
		}
		return null;
	}

}
