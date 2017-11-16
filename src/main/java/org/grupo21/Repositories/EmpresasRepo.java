package org.grupo21.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.grupo21.Model.Cuenta;
import org.grupo21.Model.CuentaViewItem;
import org.grupo21.Model.Empresa;

public class EmpresasRepo {

    private static EmpresasRepo instance = null;

    public static EmpresasRepo getInstance() {
        if (instance == null)
        	EmpresasRepo.newInstance();
        return instance;
    }

    public static void newInstance() {
        instance = new EmpresasRepo();
    }
    
    public void guardarEmpresa(Empresa empresa){
    	DatabaseRepo.getInstance().persistAndCommit(empresa);
    }
    
    public void removerEmpresa(Empresa unaEmpresa) {
    	DatabaseRepo.getInstance().removeAndCommit(unaEmpresa);
	}

    public void agregarEmpresas(List<Empresa> empresas) {
        empresas.addAll(empresas);
    }
    
	public Empresa obtenerEmpresa(String nombreEmpresa) {
		Empresa empresaBuscada = null;
		try{
			empresaBuscada = DatabaseRepo.getInstance().entityManager()
					.createQuery("from Empresa", Empresa.class)
					.getResultList().stream()
					.filter(emp -> ((Empresa) emp).getNombre().compareTo(nombreEmpresa) == 0)
					.collect(Collectors.toList()).get(0);
		} catch (Exception e) {
		}
		return empresaBuscada;
    }
	
	public List<Cuenta> todasLasCuentas(){
		List<Cuenta> result = new ArrayList<Cuenta>();
		
		getEmpresas().stream().forEach(emp -> result.addAll(emp.getCuentas()));
		
		return result;
	}
	
	public List<CuentaViewItem> todasLasCuentasParaVista(){
		List<CuentaViewItem> result = new ArrayList<CuentaViewItem>();
		
		for (Empresa emp : getEmpresas()) {
			for (Cuenta c : emp.getCuentas()) {
				result.add(new CuentaViewItem(emp.getNombre(), c));
			}
		}
		return result;
	}
	
	public Empresa empresaPorId(int id){
		return DatabaseRepo.getInstance().entityManager().find(Empresa.class, id);
	}
	
	/****************************************************/
	/***************** GETTERS Y SETTERS ****************/
	/****************************************************/
	
	public List<Empresa> getEmpresas() {
        return DatabaseRepo.getInstance().entityManager()
				.createQuery("from Empresa", Empresa.class)
				.getResultList();
    }
}
