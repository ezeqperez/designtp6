package org.grupo21.WebServer.Managers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.grupo21.CustomExceptions.ExcepcionInterna;
import org.grupo21.Model.Usuario;
import org.grupo21.Repositories.UsuariosRepo;

public class SessionManager {

	private static SessionManager _instance = null;

	public static SessionManager getInstance() {
		if (_instance == null)
			_instance = new SessionManager();

		return _instance;
	}

	public Usuario iniciarSesion(String p_mail, String p_password) throws ExcepcionInterna {
		Usuario usuarioLogueado = new Usuario();
		usuarioLogueado.setEmail(p_mail);
		usuarioLogueado.setPass(p_password);

		Usuario usuarioBase = existeUsuario(usuarioLogueado);

		return usuarioBase;
	}

	public Usuario existeUsuario(Usuario p_usuario) throws ExcepcionInterna {
		for (Usuario _usuario : UsuariosRepo.getInstance().todosLosUsuarios()) {
			if (_usuario.getEmail().equals(p_usuario.getEmail()) && _usuario.getPass().equals(p_usuario.getPass()))
				return _usuario;
		}

		throw new ExcepcionInterna("No existe el usuario");
	}

	public Date getCurrentTime() {
		return new Date(System.currentTimeMillis());
	}

	public String getCurrentStringTime() {
		Date fechaActual = new Date(System.currentTimeMillis());
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return formatoFecha.format(fechaActual);
	}
}
