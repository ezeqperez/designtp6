package org.grupo21.Model;

import org.uqbar.commons.utils.Observable;

@Observable
public class CuentaViewItem {
	
	private String compania = "";
	private Cuenta cuenta = null;
	
	public CuentaViewItem(String p_compania, Cuenta p_cuenta) {
		compania = p_compania;
		cuenta = p_cuenta;
	}

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
}
