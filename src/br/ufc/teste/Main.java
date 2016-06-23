package br.ufc.teste;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.constraints.NotNull;

import br.ufc.model.Papel;
import br.ufc.model.Usuario;

public class Main {

	public static void main(String[] args) {
	EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jornal_web");
	fabrica.close();
	}
	
}
	
