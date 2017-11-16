package org.grupo21.Model.Interfaces;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import org.grupo21.Model.Cuenta;
import org.uqbar.commons.utils.Dependencies;
import org.uqbar.commons.utils.Observable;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Observable
public abstract class IndicadorInterface {

	private String nombre = "";
	
	private String ecuacion;
	
	private String usuario = "";
	
	public abstract double calcular(Cuenta p_cuenta);

	@Dependencies({ "formula" })
	public abstract String formula();

	public String getNombre() {
		return nombre;
	}
	
	public String getEcuacion() {
		return ecuacion;
	}

	public void setEcuacion(String ecuacion) {
		this.ecuacion = ecuacion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
