package br.ufc.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity(name = "classificado")
public class Classificado {
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	private String titulo;
	@NotNull
	private String texto;
	@NotNull
	private Float preco;
	@NotNull
	private String telefone;
	@NotNull
	private Float melhorOferta;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataOferta;
	
	@Column(name="usuario", insertable=false, updatable=false, nullable=false)
	private Long idUsuario;
	
	@ManyToOne (optional=false)
	@JoinColumn(name="usuario", referencedColumnName = "idUsuario")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Float getMelhorOferta() {
		return melhorOferta;
	}

	public void setMelhorOferta(Float melhorOferta) {
		this.melhorOferta = melhorOferta;
	}

	public Calendar getDataOferta() {
		return dataOferta;
	}

	public void setDataOferta(Calendar dataOferta) {
		this.dataOferta = dataOferta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
}
