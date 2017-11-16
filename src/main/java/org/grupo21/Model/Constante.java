package org.grupo21.Model;

import org.grupo21.Model.Interfaces.IndicadorInterface;

public class Constante extends IndicadorInterface {

	private int valor = 0;

	public Constante(int p_valor) {
		valor = p_valor;
	}

	@Override
	public double calcular(Cuenta p_cuenta) {
		return valor;
	}

	@Override
	public String formula() {
		return String.valueOf(valor);
	}

}
