package org.grupo21.ViewModel;

import org.grupo21.Repositories.CuentasRepo;
import org.uqbar.commons.utils.Observable;

@Observable
public class VerCuentasViewModel {

	public VerCuentasViewModel() {
	}

	public CuentasRepo getRepo() {
		return CuentasRepo.getInstance();
	}

	public void setRepo(CuentasRepo repo) {
	}
}
