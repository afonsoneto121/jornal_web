package br.ufc.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

@Entity(name = "noticia")
public class Noticia {
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idNoticia;
	private String titulo;
	private String subTitulo;
	private String texto;
	private Calendar dataNoticia;
	
	@ManyToOne
	@JoinColumn(name = "usuario", referencedColumnName = "idUsuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="secao", referencedColumnName = "idSecao")
	private Secao secao;
	
	@OneToMany(mappedBy = "noticia", cascade = CascadeType.ALL,
			targetEntity=Comentario.class, fetch=FetchType.LAZY)
	List<Comentario> comentarios;

	public Long getIdNoticia() {
		return idNoticia;
	}

	public void setIdNoticia(Long idNoticia) {
		this.idNoticia = idNoticia;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubTitulo() {
		return subTitulo;
	}

	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Calendar getDataNoticia() {
		return dataNoticia;
	}

	public void setDataNoticia(Calendar dataNoticia) {
		this.dataNoticia = dataNoticia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
		
}
