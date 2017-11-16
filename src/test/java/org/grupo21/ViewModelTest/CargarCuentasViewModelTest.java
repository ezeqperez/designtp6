package org.grupo21.ViewModelTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.grupo21.CustomExceptions.ExcepcionUsuario;
import org.grupo21.ViewModel.CargarCuentasViewModel;
import org.junit.Test;

public class CargarCuentasViewModelTest {

	private String testNombreArchivo = "accounts.csv";
	private String testDirArchivo = "src/main/resources/";
	private String textoPrueba = "2016;1;Arcor S.A.;2003;6000;50000;5000;20000;15000;8000;3;4;17;5";

	/**
	 * METODOS AUXILIARES
	 */
	/***/
	private void crearArchivoCsv(String texto) {
		try {
			PrintWriter writer = new PrintWriter(testDirArchivo + testNombreArchivo, "UTF-8");
			writer.print(texto);
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException("Error al crear el archivo");
		}
	}

	private void borrarArchivoCsv() {
		File csvFile = new File(testDirArchivo + testNombreArchivo);
		csvFile.delete();
	}

	@Test
	public void hayCuentasCargadasRetornaFalseSiNoHayCuentas() {
		CargarCuentasViewModel vm = new CargarCuentasViewModel();
		assertFalse(vm.hayCuentasCargadas());
	}

	@Test()
	public void hayCuentasCargadasRetornaTrueSiSeCarganCuentas() {
		crearArchivoCsv(textoPrueba);

		CargarCuentasViewModel vm = new CargarCuentasViewModel();
		vm.setDireccionArchivo(testDirArchivo + testNombreArchivo);

		try {
			vm.cargarCuentas();
		} catch (ExcepcionUsuario e) {
			borrarArchivoCsv();
			assertTrue(vm.hayCuentasCargadas());
		}
	}

	@Test(expected = ExcepcionUsuario.class)
	public void cargarCuentasDeUnArchivoInexistenteArrojaExcepcion() {

		CargarCuentasViewModel vm = new CargarCuentasViewModel();
		vm.setDireccionArchivo("afgasfghba.txt");
		vm.cargarCuentas();
	}

	@Test
	public void cargarCuentasDeUnArchivoVacioArrojaExcepcion() {
		crearArchivoCsv("");

		CargarCuentasViewModel vm = new CargarCuentasViewModel();
		vm.setDireccionArchivo(testDirArchivo + testNombreArchivo);

		try {
			vm.cargarCuentas();

			// si no entro al catch fail
			fail();
		} catch (ExcepcionUsuario e) {
			// El tema es que cargarCuentas() arroja una excepcion usuario para
			// notificar el exito de la carga
			assertTrue(true);
		} finally {
			borrarArchivoCsv();
		}
	}
}
