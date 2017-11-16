package org.grupo21.ViewModel;

import java.util.List;

import org.apache.commons.collections15.list.FastArrayList;
import org.grupo21.CustomExceptions.ExcepcionCsv;
import org.grupo21.CustomExceptions.ExcepcionUsuario;
import org.grupo21.Repositories.CuentasRepo;
import org.grupo21.Utils.CuentaCsvObject;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Dependencies;
import org.uqbar.commons.utils.Observable;

@Observable
public class CargarCuentasViewModel {

	private String direccionArchivo = "";
	private List<CuentaCsvObject> cuentasCargadas = new FastArrayList<CuentaCsvObject>();

	public CargarCuentasViewModel() {
	}

	/**
	 * throws ExcepcionUsuario
	 */
	public void cargarCuentas() {
		try {
			cuentasCargadas = CuentasRepo.getInstance().cargarCuentas(direccionArchivo);
			ObservableUtils.firePropertyChanged(this, "hayCuentasCargadas");

			throw new ExcepcionUsuario("Cargado.", ExcepcionUsuario.TipoExcepcion.INFORMATIVO);
			
		} catch (ExcepcionCsv e) {
			throw new ExcepcionUsuario(e.getMessage(), ExcepcionUsuario.TipoExcepcion.ERROR);
		}
	}

	@Dependencies({ "hayCuentasCargadas" })
	public boolean hayCuentasCargadas() {
		return cuentasCargadas.size() > 0;
	}

	public void guardarCuentasCargadas() {
		CuentasRepo.getInstance().guardarCuentasCsv(cuentasCargadas);
	}
	
	/****************************************************/
	/***************** GETTERS Y SETTERS ****************/
	/****************************************************/
	
	public void setDireccionArchivo(String archivo) {
		direccionArchivo = archivo;
	}

	public String getDireccionArchivo() {
		return direccionArchivo;
	}

	public List<CuentaCsvObject> getCuentasCargadas() {
		return cuentasCargadas;
	}

	public void setCuentasCargadas(List<CuentaCsvObject> cuentasCargadas) {
	}
}
