package org.grupo21.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections15.list.FastArrayList;
import org.grupo21.CustomExceptions.ExcepcionCsv;
import org.grupo21.CustomExceptions.ExcepcionInterna;
import org.grupo21.CustomExceptions.ExcepcionCsv.CodigoExcepcionCsv;
import org.grupo21.Model.Cuenta;
import org.grupo21.Model.CuentaViewItem;
import org.grupo21.Model.Empresa;
import org.grupo21.Utils.CsvUtils;
import org.grupo21.Utils.CuentaCsvObject;
import org.grupo21.Utils.CuentaCsvObjectConverter;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

@Observable
public class CuentasRepo {

	private static CuentasRepo instance = null;

	public static CuentasRepo getInstance() {
		if (instance == null)
			CuentasRepo.newInstance();
		return instance;
	}

	public static void newInstance() {
		instance = new CuentasRepo();
	}

	public List<CuentaCsvObject> cargarCuentas(String direccionArchivo) throws ExcepcionCsv {

		List<CuentaCsvObject> _cuentas = new FastArrayList<CuentaCsvObject>();

		for (CuentaCsvObject cuenta : CsvUtils.getInstancia().deserializar(direccionArchivo, CuentaCsvObject.class)) {

			if (cuenta.getCompania().compareTo("") == 0) {
				_cuentas.clear();
				throw new ExcepcionCsv("El nombre de la empresa es obligatorio", CodigoExcepcionCsv.DATO_OBLIGATORIO);
			}

			_cuentas.add(cuenta);
		}
		return _cuentas;
	}

	public boolean removerCuenta(Cuenta unaCuenta) {
		for (Empresa emp : EmpresasRepo.getInstance().getEmpresas()) {
			List<Cuenta> encontradas = emp.getCuentas().stream().filter(c -> c.getId() == unaCuenta.getId())
					.collect(Collectors.toList());
			if (encontradas.size() == 1) {
				boolean result = emp.removerCuenta(encontradas.get(0));
				DatabaseRepo.getInstance().removeAndCommit(unaCuenta);
				ObservableUtils.firePropertyChanged(this, "cuentas");
				return result;
			}
		}
		return false;
	}

	public void agregarCuenta(CuentaCsvObject p_cuentas) {
		List<CuentaCsvObject> toSave = new ArrayList<CuentaCsvObject>();
		toSave.add(p_cuentas);
		this.guardarCuentasCsv(toSave);
	}

	public void agregarCuentas(List<CuentaCsvObject> p_cuentas) {
		this.guardarCuentasCsv(p_cuentas);
	}

	public boolean hayCuentas() {
		return getEmpresas().size() > 0;
	}

	public void guardarCuentasCsv(List<CuentaCsvObject> p_cuentas) {
		for (CuentaCsvObject csvObj : p_cuentas) {
			try {
				Empresa emp = EmpresasRepo.getInstance().obtenerEmpresa(csvObj.getCompania());
				Cuenta cuentaToSave = CuentaCsvObjectConverter.convertirACuenta(csvObj);

				for (Cuenta unaCuenta : emp.getCuentas()) {
					if (unaCuenta.getSemestre() == cuentaToSave.getSemestre() && unaCuenta.getPeriodo() == cuentaToSave.getPeriodo())
					{
						unaCuenta = CuentaCsvObjectConverter.updateCuentaFromCsvObject(unaCuenta, csvObj);
						DatabaseRepo.getInstance().persistAndCommit(unaCuenta);
						throw new ExcepcionInterna(emp.getNombre() + " para periodo " + unaCuenta.getPeriodo()
								+ " semestre " + unaCuenta.getSemestre() + " actualizada");
					}
				}
				
				emp.addCuenta(cuentaToSave);
				EmpresasRepo.getInstance().guardarEmpresa(emp);
				
				throw new ExcepcionInterna(emp.getNombre() + " para periodo " + cuentaToSave.getPeriodo()
					+ " semestre " + cuentaToSave.getSemestre() + " guardada");

			} catch (NullPointerException e) {
				// La empresa no existe, la creo y con ella a la cuenta (que
				// tampoco deberia existir)
				Empresa empToSave = new Empresa(csvObj.getCompania());
				Cuenta cuentaToSave = CuentaCsvObjectConverter.convertirACuenta(csvObj);
				empToSave.addCuenta(cuentaToSave);
				EmpresasRepo.getInstance().guardarEmpresa(empToSave);
			} catch (ExcepcionInterna e) {
				e.printStackTrace();
			}
		}
	}
	
	public Empresa empresaSegunCuenta(Cuenta p_cuenta){
		return EmpresasRepo.getInstance().getEmpresas().stream().filter(emp -> emp.getCuentas().contains(p_cuenta))
				.findFirst().get();
	}

	/****************************************************/
	/***************** GETTERS Y SETTERS ****************/
	/****************************************************/

	public List<Empresa> getEmpresas() {
		return EmpresasRepo.getInstance().getEmpresas();
	}

	public List<Cuenta> getCuentas() {
		return EmpresasRepo.getInstance().todasLasCuentas();
	}

	public void setCuentas(List<Cuenta> cuentas) {
	}
	
	public List<CuentaViewItem> getCuentasVista() {
		return EmpresasRepo.getInstance().todasLasCuentasParaVista();
	}

	public void setCuentasVista(List<CuentaViewItem> cuentas) {
	}
}
