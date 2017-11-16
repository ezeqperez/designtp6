package org.grupo21.Utils;

import org.grupo21.Model.Cuenta;

public class CuentaCsvObjectConverter {
	
	public static Cuenta convertirACuenta(CuentaCsvObject unaCuentaCsv){
		Cuenta cuentaConvertida = new Cuenta();
		
		cuentaConvertida.setCapitalTotal(stringToDoubleValor(unaCuentaCsv.getCapitalTotal()));
		cuentaConvertida.setDeuda(stringToDoubleValor(unaCuentaCsv.getDeuda()));
		cuentaConvertida.setDividendos(stringToDoubleValor(unaCuentaCsv.getDividendos()));
		cuentaConvertida.setIngresosContinuos(stringToDoubleValor(unaCuentaCsv.getIngresosContinuos()));
		cuentaConvertida.setIngresosDiscontinuos(stringToDoubleValor(unaCuentaCsv.getIngresosDiscontinuos()));
		cuentaConvertida.setInversionInicial(stringToDoubleValor(unaCuentaCsv.getInversionInicial()));
		cuentaConvertida.setMargenVenta(stringToDoubleValor(unaCuentaCsv.getMargenVenta()));
		cuentaConvertida.setPeriodo(unaCuentaCsv.getPeriodo());
		cuentaConvertida.setRecuperoInversion(stringToDoubleValor(unaCuentaCsv.getRecuperoInversion()));
		cuentaConvertida.setRoa(stringToDoubleValor(unaCuentaCsv.getRoa()));
		cuentaConvertida.setSemestre(unaCuentaCsv.getSemestre());
		cuentaConvertida.setTir(stringToDoubleValor(unaCuentaCsv.getTir()));
		
		return cuentaConvertida;
	}
	
	private static double stringToDoubleValor(String unValorString){
		return Double.parseDouble(unValorString);
	}
	
	public static Cuenta updateCuentaFromCsvObject(Cuenta unaCuenta, CuentaCsvObject unaCuentaCsv){
		unaCuenta.setCapitalTotal(stringToDoubleValor(unaCuentaCsv.getCapitalTotal()));
		unaCuenta.setDeuda(stringToDoubleValor(unaCuentaCsv.getDeuda()));
		unaCuenta.setDividendos(stringToDoubleValor(unaCuentaCsv.getDividendos()));
		unaCuenta.setIngresosContinuos(stringToDoubleValor(unaCuentaCsv.getIngresosContinuos()));
		unaCuenta.setIngresosDiscontinuos(stringToDoubleValor(unaCuentaCsv.getIngresosDiscontinuos()));
		unaCuenta.setInversionInicial(stringToDoubleValor(unaCuentaCsv.getInversionInicial()));
		unaCuenta.setMargenVenta(stringToDoubleValor(unaCuentaCsv.getMargenVenta()));
		unaCuenta.setRecuperoInversion(stringToDoubleValor(unaCuentaCsv.getRecuperoInversion()));
		unaCuenta.setRoa(stringToDoubleValor(unaCuentaCsv.getRoa()));
		unaCuenta.setTir(stringToDoubleValor(unaCuentaCsv.getTir()));
		
		return unaCuenta;
	}
	
}
