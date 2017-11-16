package org.grupo21.WebServer.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.grupo21.Model.Cuenta;
import org.grupo21.Model.Interfaces.IndicadorInterface;
import org.grupo21.Repositories.CuentasRepo;
import org.grupo21.Repositories.DatabaseRepo;
import org.grupo21.Repositories.IndicadoresRepo;
import org.grupo21.WebServer.Model.ResultadoIndicador;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadorController extends BaseController{
	
	public ModelAndView mostrar(Request pedido, Response respuesta)
	{
		Map<String, Object> modelo = getBaseModel(pedido, respuesta);
		
		List<IndicadorInterface> indicadores = new ArrayList<IndicadorInterface>();
		indicadores.addAll(IndicadoresRepo.getInstance().getIndicadoresPorUsuario(pedido.session().attribute("username")));
		
		modelo.put("indicadores", indicadores);
		
		return new ModelAndView(modelo, "indicadores_aplicar.hbs");
	}
	
	public ModelAndView aplicar(Request pedido, Response respuesta){
		Map<String, Object> modelo = getBaseModel(pedido, respuesta);
		
		int periodo = Integer.parseInt(pedido.queryParams("periodo"));
		int semestre = Integer.parseInt(pedido.queryParams("semestre"));
		
		IndicadorInterface indicadorSeleccionado = IndicadoresRepo.getInstance().obtenerIndicadorSegunNombre(pedido.queryParams("indicador_id"));
		
		List<ResultadoIndicador> cuentas = new ArrayList<ResultadoIndicador>();
		
		for (Cuenta c : CuentasRepo.getInstance().getCuentas()) {
			if(c.getPeriodo() == periodo && c.getSemestre() == semestre)
			{
				ResultadoIndicador resu = new ResultadoIndicador();
				resu.setPeriodo(periodo);
				resu.setSemestre(semestre);
				resu.setResultado(IndicadoresRepo.getInstance().aplicarIndicadorACuenta(c, indicadorSeleccionado));
				resu.setEmpresa(CuentasRepo.getInstance().empresaSegunCuenta(c).getNombre());
				
				cuentas.add(resu);
			}
		}
		
		modelo.put("cuentas", cuentas);
		
		return new ModelAndView(modelo, "resultado_indicadores.hbs");
	}
	
	public ModelAndView mostrarCrear(Request pedido, Response respuesta){
		Map<String, Object> modelo = getBaseModel(pedido, respuesta);
		
		return new ModelAndView(modelo, "indicador_crear.hbs");
	}
	
	public ModelAndView crear(Request pedido, Response respuesta){
		
		String ecuacion = pedido.queryParams("ecuacion");
		String nombre = pedido.queryParams("nombre");
		
		IndicadorInterface indi = IndicadoresRepo.getInstance().leerFormula(nombre, ecuacion);
		indi.setUsuario(pedido.session().attribute("username"));
		
		DatabaseRepo.getInstance().beginTransaction();
		IndicadoresRepo.getInstance().agregarIndicador(indi);
		DatabaseRepo.getInstance().commit();
		
		respuesta.redirect("/indicadores");
		
		return null;
	}
}
