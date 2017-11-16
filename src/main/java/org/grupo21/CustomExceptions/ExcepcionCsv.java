package org.grupo21.CustomExceptions;

@SuppressWarnings("serial")
public class ExcepcionCsv extends RuntimeException {

	private CodigoExcepcionCsv _codigo = null;

	public enum CodigoExcepcionCsv {
		NOMBRE_ARCHIVO_VACIO, ARCHIVO_VACIO, FALLO_DESERIALIZACION, FALLO_CASTEO, ARCHIVO_NO_ENCONTRADO, TIPO_CLASE_NO_ES_CSV_DATA_TYPE, ERROR_GENERAL, FALLO_CONVERSION, SIN_RESULTADOS, DATO_OBLIGATORIO
	};

	/**
	 * @param mensaje
	 * @param codigo
	 */
	public ExcepcionCsv(String mensaje, CodigoExcepcionCsv codigo) {
		super(mensaje);
		this._codigo = codigo;
	}

	/**
	 * @return
	 */
	public CodigoExcepcionCsv getCodigoError() {
		return _codigo;
	}
}
