package org.grupo21.Model.Interfaces;

import org.grupo21.Model.Cuenta;
import org.uqbar.commons.utils.Dependencies;
import org.uqbar.commons.utils.Observable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Observable
@Entity
@Table(name = "Condicion")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo", discriminatorType=DiscriminatorType.STRING)
public abstract class CondicionInterface {

	@Id
	@GeneratedValue
	private Long id;
	
	private String condicion;

	@Dependencies({ "condicionAString" })
	public abstract String condicionAString();

	public abstract boolean cumpleCondicion(Cuenta unaCuenta);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
}
