package org.grupo21.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.grupo21.Model.Interfaces.IndicadorInterface;
import org.uqbar.commons.utils.Dependencies;
import org.uqbar.commons.utils.Observable;

@Entity
@Table(name = "Indicador")
@Observable
public class Indicador extends IndicadorInterface {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Transient
	private IndicadorInterface formula;
	
	public Indicador(){
		this.setEcuacion("");
	}
	
	public Indicador(String p_nombre, IndicadorInterface p_formula) {
		this.setNombre(p_nombre);
		formula = p_formula;
		this.setEcuacion(p_formula.formula());
	}

	@Override
	public double calcular(Cuenta p_cuenta) {
		return formula.calcular(p_cuenta);
	}

	@Dependencies({ "formula" })
	@Override
	public String formula() {
		return formula.formula();
	}

	
	
	/*********************************** GETTERS Y SETTERS ******************************************/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IndicadorInterface getFormula() {
		return formula;
	}

	public void setFormula(IndicadorInterface formula) {
		this.formula = formula;
	}

}
