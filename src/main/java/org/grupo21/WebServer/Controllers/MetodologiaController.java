package org.grupo21.WebServer.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.grupo21.Model.Empresa;
import org.grupo21.Model.Metodologia;
import org.grupo21.Model.Interfaces.CondicionInterface;
import org.grupo21.Repositories.CuentasRepo;
import org.grupo21.Repositories.DatabaseRepo;
import org.grupo21.Repositories.MetodologiasRepo;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiaController extends BaseController{
	
	public ModelAndView mostrar(Request pedido, Response respuesta){
		List<Metodologia> metodologias = new ArrayList<Metodologia>();
		metodologias.addAll(MetodologiasRepo.getInstance().getMetodologiasPorUsuario(pedido.session().attribute("username")));
		
		Map<String, Object> modelo = getBaseModel(pedido, respuesta);
		modelo.put("metodologias", metodologias);
		
		return new ModelAndView(modelo, "metodologias.hbs");
	}
	
	public ModelAndView aplicar(Request pedido, Response respuesta){
		List<Empresa> resultadoAplicarMetodologia = new ArrayList<Empresa>();
		
		int periodo = Integer.parseInt(pedido.queryParams("periodo"));
		int semestre = Integer.parseInt(pedido.queryParams("semestre"));
		
		Metodologia metodologiaSeleccionada = MetodologiasRepo.getInstance().getMetodologiaPorId(Integer.parseInt(pedido.queryParams("id")));
		
		for (Empresa empresa : CuentasRepo.getInstance().getEmpresas()) {
			if (empresa.cumpleMetodologia(metodologiaSeleccionada, periodo, semestre))
				resultadoAplicarMetodologia.add(empresa);
		}
		
		Map<String, Object> modelo = getBaseModel(pedido, respuesta);
		modelo.put("cuentas", resultadoAplicarMetodologia);
		
		return new ModelAndView(modelo, "resultado_metodologia.hbs");
	}
	
	public ModelAndView mostrarCrear(Request pedido, Response respuesta){
		return new ModelAndView(getBaseModel(pedido, respuesta), "metodologia_crear.hbs");
	}
	
	public ModelAndView agregarCondicion(Request pedido, Response respuesta){
		Map<String, Object> modelo = getBaseModel(pedido, respuesta);
		
		List<CondicionInterface> condiciones = new ArrayList<CondicionInterface>();
		
		if(pedido.session().attribute("condiciones") != null)
			condiciones.addAll(pedido.session().attribute("condiciones"));
		
		try{
			condiciones.add(MetodologiasRepo.getInstance().leerCondicion(pedido.queryParams("condicion")));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		modelo.put("condiciones", condiciones);
		
		pedido.session().attribute("condiciones", condiciones);
		
		return new ModelAndView(modelo, "metodologia_crear.hbs");
	}
	
	public ModelAndView limpiarCondiciones(Request pedido, Response respuesta){
		Map<String, Object> modelo = getBaseModel(pedido, respuesta);
		
		modelo.put("condiciones", new ArrayList<CondicionInterface>());
		
		pedido.session().attribute("condiciones", null);
		
		return new ModelAndView(modelo, "metodologia_crear.hbs");
	}
	
	public ModelAndView crearMetodologia(Request pedido, Response respuesta){
		
		try{
			Metodologia metodologiaParaGuardar = new Metodologia(pedido.queryParams("nombre"), pedido.session().attribute("condiciones"));
			metodologiaParaGuardar.setUsuario(pedido.session().attribute("username"));
			
			DatabaseRepo.getInstance().beginTransaction();
			MetodologiasRepo.getInstance().agregarMetodologia(metodologiaParaGuardar);
			DatabaseRepo.getInstance().commit();
			
			pedido.session().attribute("condiciones", null);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		respuesta.redirect("/metodologias");
		
		return null;
	}
}
