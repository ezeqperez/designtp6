package org.grupo21.WebServer.Controllers;

import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController extends BaseController {

	public ModelAndView mostrar(Request pedido, Response respuesta){
		Map<String, Object> modelo = getBaseModel(pedido, respuesta);
		
		return new ModelAndView(modelo, "home.hbs");
	}
}
