package org.grupo21.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.collections15.list.FastArrayList;
import org.grupo21.CustomExceptions.ExcepcionUsuario;
import org.grupo21.CustomExceptions.ExcepcionUsuario.TipoExcepcion;
import org.uqbar.commons.utils.Observable;

@Observable
@Entity
@Table(name = "empresa")
public class Empresa {

	@Id
	@GeneratedValue
	@Column(name = "empresa_id")
	private Long id;

	private String nombre;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "empresa_id")
	private List<Cuenta> cuentas = new FastArrayList<Cuenta>();

	public Empresa() {
	}

	public Empresa(String nombreEmpresa) {
		nombre = nombreEmpresa;
	}

	public void addCuenta(Cuenta unaCuenta) {
		cuentas.add(unaCuenta);
	}

	public boolean cumpleMetodologia(Metodologia unaMetodologia, int periodo, int semestre) {
		try {
			for (Cuenta c : cuentas) {
				if (c.getPeriodo() == periodo && c.getSemestre() == semestre) {
					if (unaMetodologia.cumpleMetodologia(c))
						return true;
				}
			}
			return false;
		} catch (NullPointerException e) {
			throw new ExcepcionUsuario("Seleccione una metodologia", TipoExcepcion.ERROR);
		}
	}

	/***************************
	 * GETTERS Y SETTERS
	 ****************************************************/

	public boolean removerCuenta(Cuenta unaCuenta) {
		return cuentas.remove(unaCuenta);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
