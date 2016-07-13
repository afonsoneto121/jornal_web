package br.ufc.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;

@Entity(name = "usuario")
public class Usuario {
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idUsuario;
	@NotNull
	private String login;
	@NotNull
	private String senha;
	@NotNull
	private String nome;
	@NotNull
	private String email;

	private String nomePapel;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "usuario_papel",joinColumns=@JoinColumn(name="usuario_id",
			referencedColumnName="idUsuario"), inverseJoinColumns=@JoinColumn(name="papel_id", 
												referencedColumnName="idPapel"))
	private List<Papel> papel;
	
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true,
			targetEntity = Noticia.class,fetch=FetchType.LAZY)
	@org.hibernate.annotations.Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	List<Noticia> noticias;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, 
			targetEntity = Comentario.class,fetch=FetchType.LAZY)
	List<Comentario> comentarios;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, 
			targetEntity = Classificado.class,fetch=FetchType.LAZY)

	List<Classificado> classificados;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Papel> getPapel() {
		return papel;
	}

	public void setPapel(List<Papel> papel) {
		this.papel = papel;
	}

	public List<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Classificado> getClassificados() {
		return classificados;
	}

	public String getNomePapel() {
		return nomePapel;
	}

	public void setNomePapel(String nomePapel) {
		this.nomePapel = nomePapel;
	}

	public void setClassificados(List<Classificado> classificados) {
		this.classificados = classificados;
	}
	public boolean equals(Object obj){
		if(!(obj instanceof Usuario))
			return false;
		
		Usuario ref = (Usuario) obj;
		if(ref.getIdUsuario() == this.idUsuario)
			return true;
		return false;
	}
}
