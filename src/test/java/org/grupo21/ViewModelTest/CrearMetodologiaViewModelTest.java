package org.grupo21.ViewModelTest;

import static org.junit.Assert.assertTrue;

import org.grupo21.CustomExceptions.ExcepcionUsuario;
import org.grupo21.ViewModel.CrearMetodologiaViewModel;
import org.grupo21.ViewModel.PrincipalViewModel;
import org.junit.Test;

public class CrearMetodologiaViewModelTest {

	PrincipalViewModel pVM = new PrincipalViewModel();

	@Test(expected = ExcepcionUsuario.class)
	public void crearUnaMetodologiaConNombreVacioArrojaExcepcion() {
		CrearMetodologiaViewModel vm = pVM.crearMetododologiaVM();
		vm.crearMetodologia();
	}

	@Test(expected = ExcepcionUsuario.class)
	public void crearUnaMetodologiaSinCondicionesArrojaExcepcion() {
		CrearMetodologiaViewModel vm = pVM.crearMetododologiaVM();
		vm.setNombreMetodologia("unNombre");
		vm.crearMetodologia();
	}

	@Test(expected = ExcepcionUsuario.class)
	public void leerUnaCondicionVaciaArrojaExcepcion() {
		CrearMetodologiaViewModel vm = pVM.crearMetododologiaVM();
		vm.setNombreMetodologia("unNombre");
		vm.setCondicionSinParsear("");
		vm.cargarCondicion();
	}

	@Test(expected = ExcepcionUsuario.class)
	public void leerUnaCadenaAleatoriaComoCondicionArrojaExcepcion() {
		CrearMetodologiaViewModel vm = pVM.crearMetododologiaVM();
		vm.setNombreMetodologia("unNombre");
		vm.setCondicionSinParsear("adgjhsgjdsg");
		vm.cargarCondicion();
	}

	@Test
	public void leerUnaCondicionConParametrosDeCuentaNoArrojaExcepcion() {
		CrearMetodologiaViewModel vm = pVM.crearMetododologiaVM();
		vm.setNombreMetodologia("unNombreConParametroCuenta");
		vm.setCondicionSinParsear("deuda > 2");
		vm.cargarCondicion();

		assertTrue(true);
	}
}
