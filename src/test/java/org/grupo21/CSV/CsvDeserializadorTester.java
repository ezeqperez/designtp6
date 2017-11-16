package org.grupo21.CSV;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import org.grupo21.CustomExceptions.ExcepcionCsv;
import org.grupo21.CustomExceptions.ExcepcionCsv.CodigoExcepcionCsv;
import org.grupo21.Utils.CsvUtils;
import org.junit.Test;

public class CsvDeserializadorTester {

	private String testNombreArchivo = "accounts.csv";
	private String testDirArchivo = "src/main/resources/";
	private String textoPrueba = "Field1Row1;Field2Row1;Field3Row1;2016";

	/**
	 * METODOS AUXILIARES
	 */
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

	/**
	 * TESTs
	 */

	@Test
	public void deserializarUnArchivoNoExistenteArrojaArchivoNoEncontrado() {
		try {
			CsvUtils.getInstancia().deserializar("ua0efv.asx", CsvObjetoTesteo.class);
			fail("No error catched");
		} catch (ExcepcionCsv e) {
			assertEquals(e.getCodigoError(), CodigoExcepcionCsv.ARCHIVO_NO_ENCONTRADO);
		}
	}

	@Test
	// Si intento deserializar un csv a un objeto que no tiene el annotation
	// @CsvDataType deberia tirar una excepción
	public void deserializarUsandoUnaClaseQueNoEsCsvDataTypeArrojaExcepcion() {
		try {
			crearArchivoCsv(textoPrueba);
			CsvUtils.getInstancia().deserializar(testDirArchivo + testNombreArchivo, Object.class);
			fail("No error catched");
		} catch (ExcepcionCsv e) {
			assertEquals(e.getCodigoError(), CodigoExcepcionCsv.TIPO_CLASE_NO_ES_CSV_DATA_TYPE);
		} finally {
			borrarArchivoCsv();
		}
	}

	@Test
	public void deserializarCsvConUnaFilaRetornaUnObjeto() {
		try {
			crearArchivoCsv(textoPrueba);
			Collection<CsvObjetoTesteo> result = CsvUtils.getInstancia()
					.deserializar(testDirArchivo + testNombreArchivo, CsvObjetoTesteo.class);
			assertTrue(result.size() == 1);
		} catch (ExcepcionCsv e) {
			System.out.println(e.getMessage());
			fail();
		} finally {
			borrarArchivoCsv();
		}
	}

	@Test
	public void deserializarCsvVacioArrojaArchivoVacio() {
		try {
			crearArchivoCsv("");
			CsvUtils.getInstancia().deserializar(testDirArchivo + testNombreArchivo, CsvObjetoTesteo.class);
			fail("No error catched");
		} catch (ExcepcionCsv e) {
			assertEquals(e.getCodigoError(), CodigoExcepcionCsv.ARCHIVO_VACIO);
		} finally {
			borrarArchivoCsv();
		}
	}

	@Test
	// Si al deserializar los tipos de datos difieren de los tipos de datos que
	// espera el CsvTestObject
	public void deserializarCsvConTipoDeDatosDistintosAlObjetoArrojaFalloConversion() {
		try {
			crearArchivoCsv("0;B;8;D");
			CsvUtils.getInstancia().deserializar(testDirArchivo + testNombreArchivo, CsvObjetoTesteo.class);
			fail("No error catched");
		} catch (ExcepcionCsv e) {
			assertEquals(e.getCodigoError(), CodigoExcepcionCsv.FALLO_CONVERSION);
		} finally {
			borrarArchivoCsv();
		}
	}

	@Test
	public void deserializarCsvSoloConSeparadoresYSinInformacionArrojaSinResultados() {
		try {
			crearArchivoCsv(";;;");
			CsvUtils.getInstancia().deserializar(testDirArchivo + testNombreArchivo, CsvObjetoTesteo.class);
			fail("No error catched");
		} catch (ExcepcionCsv e) {
			assertEquals(e.getCodigoError(), CodigoExcepcionCsv.SIN_RESULTADOS);
		} finally {
			borrarArchivoCsv();
		}
	}
}
