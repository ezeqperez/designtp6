package org.grupo21.WebServer.Controllers;

import org.grupo21.CustomExceptions.ExcepcionInterna;
import org.grupo21.WebServer.Managers.SessionManager;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController extends BaseController{
	
	public ModelAndView mostrar(Request pedido, Response respuesta){
		return new ModelAndView(null, "login.hbs");
	}
	
	public ModelAndView loguear(Request pedido, Response respuesta){
		String emailUsuario = pedido.queryParams("email");
		String passUsuario = pedido.queryParams("contrasenia");
		
		try {
			SessionManager.getInstance().iniciarSesion(emailUsuario, passUsuario);
			pedido.session().attribute("username", emailUsuario);
		} catch (ExcepcionInterna e) {
			respuesta.redirect("/");
			return null;
		}

		System.out.println(SessionManager.getInstance().getCurrentStringTime() + " | " + emailUsuario + " ha iniciado sesión");
		
		respuesta.redirect("/home");
		
		return null;
	}
	
	public ModelAndView desloguear(Request pedido, Response respuesta){
		pedido.session().attribute("username", null);
		
		respuesta.redirect("/");
		
		return null;
	}
}
