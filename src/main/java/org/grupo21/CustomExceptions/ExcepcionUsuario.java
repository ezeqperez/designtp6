package org.grupo21.CustomExceptions;

import org.uqbar.commons.model.UserException;

@SuppressWarnings("serial")
public class ExcepcionUsuario extends UserException {

	public enum TipoExcepcion {
		ERROR, INFORMATIVO, ALERTA
	}

	private TipoExcepcion _tipo = TipoExcepcion.INFORMATIVO;

	public ExcepcionUsuario(String mensaje, TipoExcepcion tipo) {
		super(mensaje);
		_tipo = tipo;
	}

	public TipoExcepcion getTipo() {
		return _tipo;
	}

}
