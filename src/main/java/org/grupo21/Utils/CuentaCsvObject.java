package org.grupo21.Utils;

import java.io.Serializable;

import org.uqbar.commons.utils.Observable;

import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;

@SuppressWarnings("serial")
@Observable
@CsvDataType
public class CuentaCsvObject implements Serializable {

	@CsvField(pos = 1)
	private int periodo = 0;
	@CsvField(pos = 2)
	private int semestre = 0;
	@CsvField(pos = 3)
	private String compania = "";
	@CsvField(pos = 4)
	private int anioCreacion = 0;
	@CsvField(pos = 5)
	private String inversionInicial = "0";
	@CsvField(pos = 6)
	private String ingresosContinuos = "0";
	@CsvField(pos = 7)
	private String ingresosDiscontinuos = "0";
	@CsvField(pos = 8)
	private String capitalTotal = "0";
	@CsvField(pos = 9)
	private String dividendos = "0";
	@CsvField(pos = 10)
	private String deuda = "0";
	@CsvField(pos = 11)
	private String margenVenta = "0";
	@CsvField(pos = 12)
	private String roa = "0";
	@CsvField(pos = 13)
	private String tir = "0";
	@CsvField(pos = 14)
	private String recuperoInversion = "0";

	public CuentaCsvObject() {
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

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public int getAnioCreacion() {
		return anioCreacion;
	}

	public void setAnioCreacion(int anioCreacion) {
		this.anioCreacion = anioCreacion;
	}

	public String getInversionInicial() {
		return inversionInicial;
	}

	public void setInversionInicial(String inversionInicial) {
		this.inversionInicial = inversionInicial;
	}

	public String getIngresosContinuos() {
		return ingresosContinuos;
	}

	public void setIngresosContinuos(String ingresosContinuos) {
		this.ingresosContinuos = ingresosContinuos;
	}

	public String getIngresosDiscontinuos() {
		return ingresosDiscontinuos;
	}

	public void setIngresosDiscontinuos(String ingresosDiscontinuos) {
		this.ingresosDiscontinuos = ingresosDiscontinuos;
	}

	public String getCapitalTotal() {
		return capitalTotal;
	}

	public void setCapitalTotal(String capitalTotal) {
		this.capitalTotal = capitalTotal;
	}

	public String getDividendos() {
		return dividendos;
	}

	public void setDividendos(String dividendos) {
		this.dividendos = dividendos;
	}

	public String getDeuda() {
		return deuda;
	}

	public void setDeuda(String deuda) {
		this.deuda = deuda;
	}

	public String getMargenVenta() {
		return margenVenta;
	}

	public void setMargenVenta(String margenVenta) {
		this.margenVenta = margenVenta;
	}

	public String getRoa() {
		return roa;
	}

	public void setRoa(String roa) {
		this.roa = roa;
	}

	public String getTir() {
		return tir;
	}

	public void setTir(String tir) {
		this.tir = tir;
	}

	public String getRecuperoInversion() {
		return recuperoInversion;
	}

	public void setRecuperoInversion(String recuperoInversion) {
		this.recuperoInversion = recuperoInversion;
	}

	@Override
	public String toString() {
		return this.compania + " - " + this.semestre;
	}
}
