package org.grupo21.EscanerMetodologia;

import static org.junit.Assert.assertTrue;

import org.grupo21.CustomExceptions.ExcepcionUsuario;
import org.grupo21.Model.Cuenta;
import org.grupo21.Model.Interfaces.CondicionInterface;
import org.grupo21.Model.Interfaces.IndicadorInterface;
import org.grupo21.Repositories.IndicadoresRepo;
import org.grupo21.Repositories.MetodologiasRepo;
import org.grupo21.TestAbstracto.TestPersistencia;
import org.junit.Test;

public class TestEscanerMetodologia extends TestPersistencia{

	private Cuenta crearUnaCuentaParaTest() {
		Cuenta c = new Cuenta();

		c.setCapitalTotal(50000);
		c.setDeuda(8000);
		c.setDividendos(10000);
		c.setIngresosContinuos(15000);
		c.setIngresosDiscontinuos(6000);
		c.setMargenVenta(4.5);
		c.setPeriodo(2015);
		c.setRecuperoInversion(3);
		c.setRoa(13);
		c.setSemestre(1);
		c.setTir(20);

		return c;
	}

	/**************************
	 * Test lectura de condicion
	 ************************/
	/***/

	@Test
	public void leerLaCondicion2EsMayorAUnoNoArrojaExcepcion() {
		MetodologiasRepo.getInstance().leerCondicion("2 > 1");
		assertTrue(true);
	}

	@Test(expected = ExcepcionUsuario.class)
	public void leerCondicionVaciaArrojaExcepcion() {
		MetodologiasRepo.getInstance().leerCondicion("");
	}

	@Test(expected = ExcepcionUsuario.class)
	public void leerCondicionConCaracteresAlAzarArrojaExcepcion() {
		MetodologiasRepo.getInstance().leerCondicion("asfgiyagsdyuaif > dsagadisufgv");
	}

	@Test(expected = ExcepcionUsuario.class)
	public void leerUnaCadenaDeTextoArrojaExcepcion() {
		MetodologiasRepo.getInstance().leerCondicion("asfgiyagsdyuaif");
	}

	@Test(expected = ExcepcionUsuario.class)
	public void leerUnaCondicionQueUsaUnIndicadorNoExistenteArrojaExcepcion() {
		MetodologiasRepo.getInstance().leerCondicion("indicadorNoExistente > 2");
	}

	/***************************
	 * TEST FUNCIONAMIENTO
	 ******************************/
	/***/
	@Test
	public void leerLaCondicion2EsMayorAUnoCumple() {
		CondicionInterface condicion = MetodologiasRepo.getInstance().leerCondicion("2 > 1");
		assertTrue(condicion.cumpleCondicion(new Cuenta()));
	}

	@Test
	public void aplicarUnaCondicionAUnaCuentaQueCumpleConLaCondicionPropuestaNoFalla() {
		CondicionInterface condicion = MetodologiasRepo.getInstance().leerCondicion("margen_venta > 4");
		assertTrue(condicion.cumpleCondicion(crearUnaCuentaParaTest()));
	}

	@Test
	public void aplicarUnaCondicionConIndicadorAUnaCuentaQueCumpleConLaCondicionPropuestaNoFalla() {
		
		IndicadoresRepo.newInstance();
		IndicadorInterface indicadorLeido = IndicadoresRepo.getInstance().leerFormula("indicadorPruebaCondicion", "deuda * 2");

		IndicadoresRepo.getInstance().agregarIndicador(indicadorLeido);

		CondicionInterface condicion = MetodologiasRepo.getInstance().leerCondicion("indicadorPruebaCondicion < 30000");
		assertTrue(condicion.cumpleCondicion(crearUnaCuentaParaTest()));
	}
}
