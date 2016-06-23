package br.ufc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity(name = "papel")
public class Papel {
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idPapel;
	
	@NotNull
	@Column(name = "nome_papel")
	private String nomePalpel;
	
	@ManyToMany(mappedBy = "papel")
	List<Usuario> usuarios;

	public Long getIdPapel() {
		return idPapel;
	}

	public void setIdPapel(Long idPapel) {
		this.idPapel = idPapel;
	}

	public String getNomePalpel() {
		return nomePalpel;
	}

	public void setNomePalpel(String nomePalpel) {
		this.nomePalpel = nomePalpel;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
		
	public boolean equals(Object obj){
		if(!(obj instanceof Papel))
			return false;
		
		Papel ref = (Papel) obj;
		if(ref.getIdPapel() == this.idPapel)
			return true;
		return false;
	}
}
