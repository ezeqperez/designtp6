package org.grupo21.Model;

import org.grupo21.Model.Interfaces.IndicadorInterface;

public class Division extends IndicadorInterface {

	private IndicadorInterface valorIzq;
	private IndicadorInterface valorDer;

	public Division(IndicadorInterface p_valorIzq, IndicadorInterface p_valorDer) {
		valorIzq = p_valorIzq;
		valorDer = p_valorDer;
	}

	@Override
	public double calcular(Cuenta p_cuenta) {
		return valorIzq.calcular(p_cuenta) / valorDer.calcular(p_cuenta);
	}

	@Override
	public String formula() {
		return valorIzq.formula() + "/" + valorDer.formula();
	}

}
