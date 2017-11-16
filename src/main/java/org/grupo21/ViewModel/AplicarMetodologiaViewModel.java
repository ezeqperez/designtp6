package org.grupo21.ViewModel;

import java.util.List;

import org.apache.commons.collections15.list.FastArrayList;
import org.grupo21.Model.Empresa;
import org.grupo21.Model.Metodologia;
import org.grupo21.Repositories.CuentasRepo;
import org.grupo21.Repositories.MetodologiasRepo;
import org.uqbar.commons.utils.Observable;

@Observable
public class AplicarMetodologiaViewModel {

	public List<Empresa> resultadoAplicarMetodologia = new FastArrayList<Empresa>();

	private int periodo = 2016;
	private int semestre = 1;
	private Metodologia metodologiaSeleccionada = null;

	public AplicarMetodologiaViewModel() {
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public void aplicarMetodologia() {
		resultadoAplicarMetodologia.clear();
		
		for (Empresa empresa : CuentasRepo.getInstance().getEmpresas()) {
			if (empresa.cumpleMetodologia(metodologiaSeleccionada, periodo, semestre))
				resultadoAplicarMetodologia.add(empresa);
		}
	}

	public List<Empresa> getResultadoAplicarMetodologia() {
		return resultadoAplicarMetodologia;
	}

	public void setResultadoAplicarMetodologia(List<Empresa> resultadoAplicarMetodologia) {
		this.resultadoAplicarMetodologia = resultadoAplicarMetodologia;
	}

	public List<Empresa> getEmpresas() {
		return CuentasRepo.getInstance().getEmpresas();
	}

	public void setEmpresas(List<Empresa> empresas) {
	}

	public Metodologia getMetodologiaSeleccionada() {
		return metodologiaSeleccionada;
	}

	public void setMetodologiaSeleccionada(Metodologia metodologiaSeleccionada) {
		this.metodologiaSeleccionada = metodologiaSeleccionada;
	}

	public CuentasRepo getRepo() {
		return CuentasRepo.getInstance();
	}

	public void setRepo(CuentasRepo repo) {
	}

	public MetodologiasRepo getRepoMetodologias() {
		return MetodologiasRepo.getInstance();
	}

	public void setRepoMetodologias(MetodologiasRepo repoMetodologias) {
	}
}
