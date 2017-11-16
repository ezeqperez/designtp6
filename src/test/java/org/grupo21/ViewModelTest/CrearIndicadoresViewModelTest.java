package org.grupo21.ViewModelTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.grupo21.CustomExceptions.ExcepcionUsuario;
import org.grupo21.Model.Cuenta;
import org.grupo21.Model.Interfaces.IndicadorInterface;
import org.grupo21.Repositories.IndicadoresRepo;
import org.grupo21.ViewModel.CrearIndicadoresViewModel;
import org.grupo21.TestAbstracto.TestPersistencia;
import org.junit.Test;

public class CrearIndicadoresViewModelTest extends TestPersistencia{

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

	@Test(expected = ExcepcionUsuario.class)
	public void crearUnIndicadorSinNombreArrojaExcepcion() {
		CrearIndicadoresViewModel vm = new CrearIndicadoresViewModel();
		vm.setAutoCommit(false);

		vm.setNombreIndicador("");
		vm.setFormulaIndicador("");

		vm.crearIndicador();
	}

	@Test(expected = ExcepcionUsuario.class)
	public void crearUnIndicadorSinFormulaArrojaExcepcion() {

		CrearIndicadoresViewModel vm = new CrearIndicadoresViewModel();
		vm.setAutoCommit(false);

		vm.setNombreIndicador("NombrePrueba");
		vm.setFormulaIndicador("");

		vm.crearIndicador();
	}

	@Test(expected = ExcepcionUsuario.class)
	public void crearUnIndicadorUsandoComoFormulaUnaCadenaDeNumeroYLetrasArrojaExcepcion() {

		CrearIndicadoresViewModel vm = new CrearIndicadoresViewModel();
		vm.setAutoCommit(false);

		vm.setNombreIndicador("NombrePrueba");
		vm.setFormulaIndicador("agag123asfasf");

		vm.crearIndicador();
	}

	@Test
	public void crearIndicador2Mas2() {

		CrearIndicadoresViewModel vm = new CrearIndicadoresViewModel();
		vm.setAutoCommit(false);

		try {
			vm.setNombreIndicador("IndicadorDeTesteoDosMasDos");
			vm.setFormulaIndicador("2+2");

			vm.crearIndicador();
			assertTrue(true);
		} catch (ExcepcionUsuario e) {
			fail();
		}
	}

	@Test
	public void resolverIndicador2Mas2Retorna4() {

		CrearIndicadoresViewModel vm = new CrearIndicadoresViewModel();
		vm.setAutoCommit(false);

		try {
			vm.setNombreIndicador("IndicadorDeTesteo");
			vm.setFormulaIndicador("2+2");

			vm.crearIndicador();

			IndicadorInterface unIndicador = IndicadoresRepo.getInstance().obtenerIndicadorSegunNombre("IndicadorDeTesteo");

			assertTrue(unIndicador.calcular(new Cuenta()) == 4);
		} catch (ExcepcionUsuario e) {
			fail();
		}
	}

	@Test
	public void crearIndicadorQueUsaOtroIndicador() {
		CrearIndicadoresViewModel vm = new CrearIndicadoresViewModel();
		vm.setAutoCommit(false);

		try {
			vm.setNombreIndicador("Test1");
			vm.setFormulaIndicador("2+2");
			vm.crearIndicador();

			vm.setNombreIndicador("Test2");
			vm.setFormulaIndicador("Test1+2");
			vm.crearIndicador();

			assertTrue(true);
		} catch (ExcepcionUsuario e) {
			fail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void crearIndicadorQueUsaUnParametroDeCuenta() {

		CrearIndicadoresViewModel vm = new CrearIndicadoresViewModel();
		vm.setAutoCommit(false);

		try {
			vm.setNombreIndicador("IndicadorDeTesteoParametroCuenta");
			vm.setFormulaIndicador("deuda*2");
			vm.crearIndicador();

			assertTrue(true);
		} catch (ExcepcionUsuario e) {
			fail();
		}
	}

	@Test
	public void resolverIndicadorQueUsaUnParametroDeCuenta() {

		CrearIndicadoresViewModel vm = new CrearIndicadoresViewModel();
		vm.setAutoCommit(false);

		try {
			vm.setNombreIndicador("ResolverIndicadorDeTesteoParametroCuenta");
			vm.setFormulaIndicador("deuda*2");
			vm.crearIndicador();

			IndicadorInterface indicadorCargado = vm.getRepo().obtenerIndicadorSegunNombre("ResolverIndicadorDeTesteoParametroCuenta");

			Cuenta c = crearUnaCuentaParaTest();
			c.setDeuda(200);

			assertTrue(indicadorCargado.calcular(c) == 400);

		} catch (ExcepcionUsuario e) {
			fail();
		}
	}
}
