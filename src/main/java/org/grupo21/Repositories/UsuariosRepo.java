package org.grupo21.Repositories;

import java.util.List;

import org.grupo21.Model.Usuario;

public class UsuariosRepo {

	private static UsuariosRepo instance = null;

	public static UsuariosRepo getInstance() {
		if (instance == null)
			UsuariosRepo.newInstance();
		return instance;
	}

	public static void newInstance() {
		instance = new UsuariosRepo();
	}

	public List<Usuario> todosLosUsuarios() {
		return DatabaseRepo.getInstance().entityManager().createQuery("from Usuario", Usuario.class).getResultList();
	}
}
