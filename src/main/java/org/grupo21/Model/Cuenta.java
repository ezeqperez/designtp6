package org.grupo21.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.uqbar.commons.utils.Observable;

@Observable
@Entity
@Table(name="cuenta")
public class Cuenta {
	
	@Id
	@GeneratedValue
	@Column(name="cuenta_id")
	private Long id;
	
	private int periodo = 0;
	private int semestre = 0;
	private double inversionInicial = 0;
	private double ingresosContinuos = 0;
	private double ingresosDiscontinuos = 0;
	private double capitalTotal = 0;
	private double dividendos = 0;
	private double deuda = 0;
	private double margenVenta = 0;
	private double roa = 0;
	private double tir = 0;
	private double recuperoInversion = 0;

	public Cuenta() {
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

	public double getInversionInicial() {
		return inversionInicial;
	}

	public void setInversionInicial(double inversionInicial) {
		this.inversionInicial = inversionInicial;
	}

	public double getIngresosContinuos() {
		return ingresosContinuos;
	}

	public void setIngresosContinuos(double ingresosContinuos) {
		this.ingresosContinuos = ingresosContinuos;
	}

	public double getIngresosDiscontinuos() {
		return ingresosDiscontinuos;
	}

	public void setIngresosDiscontinuos(double ingresosDiscontinuos) {
		this.ingresosDiscontinuos = ingresosDiscontinuos;
	}

	public double getCapitalTotal() {
		return capitalTotal;
	}

	public void setCapitalTotal(double capitalTotal) {
		this.capitalTotal = capitalTotal;
	}

	public double getDividendos() {
		return dividendos;
	}

	public void setDividendos(double dividendos) {
		this.dividendos = dividendos;
	}

	public double getDeuda() {
		return deuda;
	}

	public void setDeuda(double deuda) {
		this.deuda = deuda;
	}

	public double getMargenVenta() {
		return margenVenta;
	}

	public void setMargenVenta(double margenVenta) {
		this.margenVenta = margenVenta;
	}

	public double getRoa() {
		return roa;
	}

	public void setRoa(double roa) {
		this.roa = roa;
	}

	public double getTir() {
		return tir;
	}

	public void setTir(double tir) {
		this.tir = tir;
	}

	public double getRecuperoInversion() {
		return recuperoInversion;
	}

	public void setRecuperoInversion(double recuperoInversion) {
		this.recuperoInversion = recuperoInversion;
	}

	@Override
	public String toString() {
		return "Semestre: " + this.semestre + " - Periodo: " + this.periodo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
