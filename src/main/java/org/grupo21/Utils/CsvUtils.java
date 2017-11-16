package org.grupo21.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

import org.apache.commons.collections15.list.FastArrayList;
import org.grupo21.CustomExceptions.ExcepcionCsv;
import org.grupo21.CustomExceptions.ExcepcionCsv.CodigoExcepcionCsv;

import net.sf.jsefa.DeserializationException;
import net.sf.jsefa.Deserializer;
import net.sf.jsefa.IOFactoryException;
import net.sf.jsefa.common.converter.ConversionException;
import net.sf.jsefa.csv.CsvIOFactory;
import net.sf.jsefa.csv.config.CsvConfiguration;

public class CsvUtils {
	private Deserializer _deserializador = null;
	private char _delimitador = ';';
	private static CsvUtils _instancia = null;

	public CsvUtils() {
	}

	public static CsvUtils getInstancia() {
		if (_instancia == null)
			_instancia = new CsvUtils();

		return _instancia;
	}

	public <T> Collection<T> deserializar(String p_direccionArchivo, Class<T> p_clase) throws ExcepcionCsv {

		Collection<T> resultado = new FastArrayList<T>();
		InputStream streaming = null;
		_deserializador = null;

		CsvConfiguration config = new CsvConfiguration();
		config.setFieldDelimiter(_delimitador);

		if (p_direccionArchivo == "")
			throw new ExcepcionCsv("El nombre del archivo esta vacío", CodigoExcepcionCsv.NOMBRE_ARCHIVO_VACIO);

		try {
			streaming = new FileInputStream(new File(p_direccionArchivo));

			_deserializador = CsvIOFactory.createFactory(config, p_clase).createDeserializer();

			_deserializador.open(new InputStreamReader(streaming));

			if (!_deserializador.hasNext())
				throw new ExcepcionCsv("El archivo esta vacío", CodigoExcepcionCsv.ARCHIVO_VACIO);

			while (_deserializador.hasNext()) {
				Object obj = _deserializador.next();
				if (obj != null)
					resultado.add(p_clase.cast(obj));
			}

			if (resultado.isEmpty())
				throw new ExcepcionCsv("Sin resultados", CodigoExcepcionCsv.SIN_RESULTADOS);

		} catch (ClassCastException e) {
			throw new ExcepcionCsv("Fallo al castear el resultado", CodigoExcepcionCsv.FALLO_CASTEO);
		} catch (IOFactoryException e) {
			throw new ExcepcionCsv("El objeto no tiene el type correcto",
					CodigoExcepcionCsv.TIPO_CLASE_NO_ES_CSV_DATA_TYPE);
		} catch (FileNotFoundException e) {
			throw new ExcepcionCsv("Archivo de cuentas no encontrado", CodigoExcepcionCsv.ARCHIVO_NO_ENCONTRADO);
		} catch (DeserializationException e) {
			if (e.getCause().getClass() == ConversionException.class)
				throw new ExcepcionCsv("Error. Se ingreso un tipo de dato que no era el esperado",
						CodigoExcepcionCsv.FALLO_CONVERSION);
			throw new ExcepcionCsv("Fallo al deserializar", CodigoExcepcionCsv.FALLO_DESERIALIZACION);
		} finally {
			if (_deserializador != null)
				_deserializador.close(true);

			if (streaming != null) {
				try {
					streaming.close();
				} catch (IOException e) {
					System.out.println("No se pudo cerrar el archivo.");
				}
			}
		}
		return resultado;
	}
}
