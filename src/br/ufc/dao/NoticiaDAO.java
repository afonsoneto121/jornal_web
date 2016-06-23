package br.ufc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufc.dao.interfaces.NoticiaI;
import br.ufc.model.Noticia;
import br.ufc.model.Noticia;

@Repository
public class NoticiaDAO implements NoticiaI{

	@PersistenceContext
	EntityManager manage;
	
	@Override
	public void inserir(Noticia noticia) {
		manage.persist(noticia);
		
	}

	@Override
	public void remover(Noticia noticia) {
		manage.remove(noticia);
		
	}

	@Override
	public void alterar(Noticia noticia) {
		manage.persist("noticia");
		
	}

	@Override
	public List<Noticia> listar() {
		return manage.createQuery("select s from noticia as s", Noticia.class).getResultList();
	}

	@Override
	public Noticia recuperar(Long id) {
		return manage.find(Noticia.class, id);
	}

	@Override
	public Noticia recuperar(String titulo) {
		List<Noticia> notcList;
		String hql = "select u from noticia as u where u.titulo = :titulo";
		Query qr = manage.createQuery(hql);
		notcList = qr.setParameter("titulo", titulo).getResultList();
		if(notcList.size() != 0)
		{
			return notcList.get(0);
		}
		return null;
	}

	@Override
	public List<Noticia> recuperarUltimas() {
		List<Noticia> noticias =  null;
		String hql = "from noticia order by noticia_id desc";
		Query query = manage.createQuery(hql);		
		noticias = query.getResultList();
		return noticias;
	}

}
