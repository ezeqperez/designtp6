package org.grupo21.Indicadores; 

import static org.junit.Assert.assertEquals;

import org.grupo21.CustomExceptions.ExcepcionUsuario;
import org.grupo21.Model.Cuenta;
import org.grupo21.Model.Interfaces.IndicadorInterface;
import org.grupo21.Repositories.IndicadoresRepo;
import org.junit.Test;

public class AplicacionDeIndicadoresTest {

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

	@Test
	public void aplicarIndicador() {
		IndicadorInterface indi = IndicadoresRepo.getInstance().leerFormula("IndicadorPrueba", "5");

		assertEquals(5.0, IndicadoresRepo.getInstance().aplicarIndicadorACuenta(crearUnaCuentaParaTest(), indi), 0);
	}

	@Test
	public void aplicarIndicadorSobreDeuda() {
		IndicadorInterface indi = IndicadoresRepo.getInstance().leerFormula("IndicadorPrueba", "deuda/2");

		assertEquals(4000.0, IndicadoresRepo.getInstance().aplicarIndicadorACuenta(crearUnaCuentaParaTest(), indi), 0);
	}

	@Test(expected = ExcepcionUsuario.class)
	public void aplicarIndicadorVacioExepcion() {
		IndicadoresRepo.getInstance().aplicarIndicadorACuenta(crearUnaCuentaParaTest(),
				IndicadoresRepo.getInstance().obtenerIndicadorSegunNombre(""));
	}
}
