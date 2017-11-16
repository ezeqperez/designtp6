package org.grupo21.ViewModel;

import org.grupo21.Model.CuentaViewItem;
import org.grupo21.Model.Interfaces.IndicadorInterface;
import org.grupo21.Repositories.CuentasRepo;
import org.grupo21.Repositories.IndicadoresRepo;
import org.uqbar.commons.utils.Observable;

@Observable
public class AplicarIndicadorViewModel {

	private CuentaViewItem cuentaParaAplicar = null;
	private IndicadorInterface indicadorParaAplicar = null;

	public AplicarIndicadorViewModel() {
	}

	public double aplicarIndicador() {
		return IndicadoresRepo.getInstance().aplicarIndicadorACuenta(cuentaParaAplicar.getCuenta(),
				indicadorParaAplicar);
	}

	public CuentaViewItem getCuentaParaAplicar() {
		return cuentaParaAplicar;
	}

	public void setCuentaParaAplicar(CuentaViewItem cuentaParaAplicar) {
		this.cuentaParaAplicar = cuentaParaAplicar;
	}

	public IndicadorInterface getIndicadorParaAplicar() {
		return indicadorParaAplicar;
	}

	public void setIndicadorParaAplicar(IndicadorInterface indicadorParaAplicar) {
		this.indicadorParaAplicar = indicadorParaAplicar;
	}

	public CuentasRepo getRepo() {
		return CuentasRepo.getInstance();
	}

	public void setRepo(CuentasRepo repo) {
	}

	public IndicadoresRepo getRepoIndicadores() {
		return IndicadoresRepo.getInstance();
	}

	public void setRepoIndicadores(IndicadoresRepo repoIndicadores) {
	}

}
