package org.grupo21.ViewModel;

import java.util.Collection;

import org.apache.commons.collections15.list.FastArrayList;
import org.grupo21.Model.Metodologia;
import org.grupo21.Model.Interfaces.CondicionInterface;
import org.grupo21.Repositories.DatabaseRepo;
import org.grupo21.Repositories.MetodologiasRepo;
import org.uqbar.commons.utils.Observable;

@Observable
public class CrearMetodologiaViewModel {

	private Collection<CondicionInterface> condiciones = new FastArrayList<CondicionInterface>();

	private String nombreMetodologia = "";
	private String condicionSinParsear = "";
	
	private boolean autoCommit = true;

	public CrearMetodologiaViewModel() {
	}

	public void crearMetodologia() {
		if(autoCommit)
			DatabaseRepo.getInstance().beginTransaction();
		
		MetodologiasRepo.getInstance().agregarMetodologia(new Metodologia(nombreMetodologia, condiciones));
		
		if(autoCommit)
			DatabaseRepo.getInstance().commit();
		
		nombreMetodologia = "";
		condicionSinParsear = "";
		condiciones.clear();
	}

	public void cargarCondicion() {
		condiciones.add(MetodologiasRepo.getInstance().leerCondicion(condicionSinParsear));
	}

	public Collection<CondicionInterface> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(Collection<CondicionInterface> condiciones) {
		this.condiciones = condiciones;
	}

	public String getNombreMetodologia() {
		return nombreMetodologia;
	}

	public void setNombreMetodologia(String nombreMetodologia) {
		this.nombreMetodologia = nombreMetodologia;
	}

	public String getCondicionSinParsear() {
		return condicionSinParsear;
	}

	public void setCondicionSinParsear(String condicionSinParsear) {
		this.condicionSinParsear = condicionSinParsear;
	}

	public MetodologiasRepo getRepo() {
		return MetodologiasRepo.getInstance();
	}

	public void setRepo(MetodologiasRepo repo) {
	}

	public boolean isAutoCommit() {
		return autoCommit;
	}

	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;
	}
}
