package org.grupo21.ViewModel;

import org.grupo21.CustomExceptions.ExcepcionUsuario;
import org.grupo21.Model.Cuenta;
import org.grupo21.Repositories.CuentasRepo;
import org.uqbar.commons.utils.Observable;

@Observable
public class BorrarCuentasViewModel {

	private Cuenta cuentaParaBorrar = new Cuenta();

	public BorrarCuentasViewModel() {
	}

	/*
	 ************************ GETTERS Y SETTERS ************************
	 */
	public Cuenta getCuentaParaBorrar() {
		return cuentaParaBorrar;
	}

	public void setCuentaParaBorrar(Cuenta cuentaParaBorrar) {
		this.cuentaParaBorrar = cuentaParaBorrar;
	}

	public void borrarCuenta() {
		if (!CuentasRepo.getInstance().removerCuenta(cuentaParaBorrar)) {
			throw new ExcepcionUsuario("Cuenta Incorrecta", ExcepcionUsuario.TipoExcepcion.INFORMATIVO);
		}
	}

	public CuentasRepo getRepo() {
		return CuentasRepo.getInstance();
	}

	public void setRepo(CuentasRepo repo) {
	}
}
