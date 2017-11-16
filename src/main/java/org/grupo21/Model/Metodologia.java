package org.grupo21.Model;

import java.util.Collection;

import org.apache.commons.collections15.list.FastArrayList;
import org.grupo21.Model.Interfaces.CondicionInterface;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Metodologia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nombre = "";
	private String usuario = "";

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private Collection<CondicionInterface> condiciones = new FastArrayList<CondicionInterface>();

	public Metodologia() {
	}

	public Metodologia(String p_nombre, Collection<CondicionInterface> p_condiciones) {
		nombre = p_nombre;
		condiciones.addAll(p_condiciones);
	}

	public boolean cumpleMetodologia(Cuenta p_cuenta) {
		return condiciones.stream().allMatch(c -> c.cumpleCondicion(p_cuenta));
	}

	
	
	/*************************************** GETTERS Y SETTERS ******************************************/
	
	public String getNombre() {
		return nombre;
	}

	public Collection<CondicionInterface> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(Collection<CondicionInterface> condiciones) {
		this.condiciones = condiciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
