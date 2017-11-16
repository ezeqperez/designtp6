package org.grupo21.WebServer.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.grupo21.Model.CuentaViewItem;
import org.grupo21.Repositories.CuentasRepo;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController extends BaseController{
	
	public ModelAndView mostrar(Request pedido, Response respuesta){
		List<CuentaViewItem> cuentas = new ArrayList<>();
		cuentas.addAll(CuentasRepo.getInstance().getCuentasVista());

		Map<String, Object> modelo = getBaseModel(pedido, respuesta);
		modelo.put("cuentas", cuentas);

		return new ModelAndView(modelo, "cuentas.hbs");
	}
}
