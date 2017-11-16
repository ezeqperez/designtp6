package org.grupo21.Model;

import org.grupo21.Model.Interfaces.IndicadorInterface;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ParametroCuenta extends IndicadorInterface {

	private String parametro = "";
	private Map<String, Method> commands = new HashMap<>();

	public ParametroCuenta(String p_parametro) {
		parametro = p_parametro;

		try {
			commands.put("inversion_inicial", Cuenta.class.getMethod("getInversionInicial"));
			commands.put("ingresos_continuos", Cuenta.class.getMethod("getIngresosContinuos"));
			commands.put("ingresos_discontinuos", Cuenta.class.getMethod("getIngresosDiscontinuos"));
			commands.put("dividendos", Cuenta.class.getMethod("getDividendos"));
			commands.put("deuda", Cuenta.class.getMethod("getDeuda"));
			commands.put("margen_venta", Cuenta.class.getMethod("getMargenVenta"));
			commands.put("roa", Cuenta.class.getMethod("getRoa"));
			commands.put("tir", Cuenta.class.getMethod("getTir"));
			commands.put("recupero_inversion", Cuenta.class.getMethod("getRecuperoInversion"));
			commands.put("capital_total", Cuenta.class.getMethod("getCapitalTotal"));

		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

	@Override
	public double calcular(Cuenta p_cuenta) {
		try {
			return (double) commands.get(parametro).invoke(p_cuenta);

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String formula() {
		return parametro;
	}

}
