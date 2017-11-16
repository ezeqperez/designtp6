package org.grupo21.ViewModel;

import org.grupo21.Repositories.CuentasRepo;
import org.grupo21.Repositories.IndicadoresRepo;
import org.grupo21.Repositories.MetodologiasRepo;

public class PrincipalViewModel {

	public PrincipalViewModel() {
	}

	public CargarCuentasViewModel crearCargarCuentasVM() {
		return new CargarCuentasViewModel();
	}
	
	public VerCuentasViewModel crearVerCuentasVM() {
		return new VerCuentasViewModel();
	}

	public BorrarCuentasViewModel crearBorrarCuentasVM() {
		return new BorrarCuentasViewModel();
	}

	public VerIndicadoresViewModel crearVerIndicadoresVM() {
		return new VerIndicadoresViewModel();
	}

	public CrearIndicadoresViewModel crearCargarIndicadoresVM() {
		return new CrearIndicadoresViewModel();
	}

	public AplicarIndicadorViewModel crearAplicarIndicadorVM() {
		return new AplicarIndicadorViewModel();
	}

	public CrearMetodologiaViewModel crearMetododologiaVM() {
		return new CrearMetodologiaViewModel();
	}

	public AplicarMetodologiaViewModel crearAplicarMetododologiaVM() {
		return new AplicarMetodologiaViewModel();
	}

	/**********************************************************/

	public boolean hayCuentasCargadas() {
		return CuentasRepo.getInstance().hayCuentas();
	}

	public boolean hayIndicadoresCargados() {
		return IndicadoresRepo.getInstance().getIndicadores().size() > 0;
	}

	public boolean hayMetodologiasCargadas() {
		return MetodologiasRepo.getInstance().getMetodologias().size() > 0;
	}
}
