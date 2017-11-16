package org.grupo21.Model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.grupo21.Model.Interfaces.CondicionInterface;
import org.grupo21.Model.Interfaces.IndicadorInterface;

@Entity
@DiscriminatorValue(value="Menor")
public class CondicionMenor extends CondicionInterface {

	@Transient
	IndicadorInterface indicadorIzquierda;
	@Transient
	IndicadorInterface indicadorDerecha;

	public CondicionMenor() {
	}
	
	public CondicionMenor(IndicadorInterface p_indicadorIzquierda, IndicadorInterface p_indicadorDerecha) {
		indicadorDerecha = p_indicadorDerecha;
		indicadorIzquierda = p_indicadorIzquierda;
		setCondicion(condicionAString());
	}

	@Override
	public String condicionAString() {
		return indicadorIzquierda.formula() + " < " + indicadorDerecha.formula();
	}

	@Override
	public boolean cumpleCondicion(Cuenta unaCuenta) {
		return indicadorIzquierda.calcular(unaCuenta) < indicadorDerecha.calcular(unaCuenta);
	}
}
