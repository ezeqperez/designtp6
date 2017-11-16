package org.grupo21.WebServer.Controllers;

import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;

public class BaseController {
	public Map<String, Object> getBaseModel(Request pedido, Response respuesta) {
		Map<String, Object> modelo = new HashMap<String, Object>();
		modelo.put("username", pedido.session().attribute("username"));
		return modelo;
	}
}
