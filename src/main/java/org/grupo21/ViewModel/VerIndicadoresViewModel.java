package org.grupo21.ViewModel;

import org.grupo21.Repositories.IndicadoresRepo;
import org.uqbar.commons.utils.Observable;

@Observable
public class VerIndicadoresViewModel {

	public VerIndicadoresViewModel() {
	}

	public IndicadoresRepo getRepo() {
		return IndicadoresRepo.getInstance();
	}

	public void setRepo(IndicadoresRepo repo) {
	}
}
