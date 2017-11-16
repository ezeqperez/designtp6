package org.grupo21.ViewModel;

import org.grupo21.Model.Interfaces.IndicadorInterface;
import org.grupo21.Repositories.DatabaseRepo;
import org.grupo21.Repositories.IndicadoresRepo;
import org.uqbar.commons.utils.Observable;

@Observable
public class CrearIndicadoresViewModel {

	private String nombreIndicador = "";
	private String formulaIndicador = "";
	
	private boolean autoCommit = true;

	public CrearIndicadoresViewModel() {
	}

	/**
	 * throws ExcepcionUsuario
	 */
	public void crearIndicador() {
		IndicadorInterface indi = IndicadoresRepo.getInstance().leerFormula(nombreIndicador, formulaIndicador);
		
		if(autoCommit)
			DatabaseRepo.getInstance().beginTransaction();
		
		IndicadoresRepo.getInstance().agregarIndicador(indi);
		
		if(autoCommit)
			DatabaseRepo.getInstance().commit();

		nombreIndicador = "";
		formulaIndicador = "";
	}

	/*
	 ************************ GETTERS Y SETTERS ************************
	 */

	public String getNombreIndicador() {
		return nombreIndicador;
	}

	public void setNombreIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador;
	}

	public String getFormulaIndicador() {
		return formulaIndicador;
	}

	public void setFormulaIndicador(String formulaIndicador) {
		this.formulaIndicador = formulaIndicador;
	}

	public IndicadoresRepo getRepo() {
		return IndicadoresRepo.getInstance();
	}

	public void setRepo(IndicadoresRepo repo) {
	}

	/**
	 * @return the autoCommit
	 */
	public boolean isAutoCommit() {
		return autoCommit;
	}

	/**
	 * @param autoCommit the autoCommit to set
	 */
	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;
	}
}
