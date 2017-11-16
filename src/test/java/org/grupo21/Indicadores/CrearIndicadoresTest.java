package org.grupo21.Indicadores;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.grupo21.CustomExceptions.ExcepcionUsuario;
import org.grupo21.Model.Interfaces.IndicadorInterface;
import org.grupo21.Repositories.IndicadoresRepo;
import org.grupo21.TestAbstracto.TestPersistencia;
import org.junit.Test;

public class CrearIndicadoresTest extends TestPersistencia{

	@Test
	public void cargarNuevoIndicadorConValor5() {
		assertEquals("5", IndicadoresRepo.getInstance().leerFormula("IndicadorPrueba", "5").formula());
	}

	@Test
	public void cargarIndicadorAPartirDeIndicador() {
		IndicadorInterface indi1 = IndicadoresRepo.getInstance().leerFormula("IndicadorPruebaBase", "5");
		IndicadoresRepo.getInstance().agregarIndicador(indi1);
		
		IndicadorInterface indi2 = IndicadoresRepo.getInstance().leerFormula("IndicadorPruebaCompuesto", "IndicadorPruebaBase + 77");
		IndicadoresRepo.getInstance().agregarIndicador(indi2);
		
		assertEquals("5+77", IndicadoresRepo.getInstance().obtenerIndicadorSegunNombre("IndicadorPruebaCompuesto").formula());
	}

	@Test(expected = ExcepcionUsuario.class)
	public void cargarIndicadorAPartirDeIndicadorInexistenteExcepcion() {
		IndicadoresRepo.getInstance().leerFormula("IndicadorPrueba1", "UnIndicadorQueNoExiste + 77");
	}

	@Test(expected = ExcepcionUsuario.class)
	public void cargarIndicadorSinFormulaExcepcion() {
		IndicadoresRepo.getInstance().leerFormula("IndicadorSinFormula", "");
	}

	@Test(expected = ExcepcionUsuario.class)
	public void cargarIndicadorSinNombreExcepcion() {
		IndicadoresRepo.getInstance().leerFormula("", "77");
	}

	@Test(expected = ExcepcionUsuario.class)
	public void cargarIndicadorConOperadoresConsecutivosExcepcion() {
		IndicadoresRepo.getInstance().leerFormula("IndicadorOperadoresConsecutivos", "33 + * 2");
	}

	@Test()
	public void existeIndicador() {
		IndicadorInterface unIndicador = IndicadoresRepo.getInstance().leerFormula("IndicadorRepetido", "3");
		IndicadoresRepo.getInstance().agregarIndicador(unIndicador);

		assertTrue(IndicadoresRepo.getInstance().existeIndicador(unIndicador.getNombre()));
	}
}
