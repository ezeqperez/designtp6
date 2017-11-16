package org.grupo21.WebServer.Model;

public class ResultadoIndicador {
	private String empresa;
	private int periodo;
	private int semestre;
	private double resultado;
	
	public ResultadoIndicador() {
		empresa = "";
		periodo = 0;
		semestre = 0;
		resultado = 0;
	}
	
	public String getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
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
	
	public double getResultado() {
		return resultado;
	}
	
	public void setResultado(double resultado) {
		this.resultado = resultado;
	}
}
