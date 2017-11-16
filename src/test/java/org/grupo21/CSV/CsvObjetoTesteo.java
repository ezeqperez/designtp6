package org.grupo21.CSV;

import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;

/**
 * 
 * Es un objeto solo usado para testeo. Sirve para comprobar el funcionamiento
 * del deserializador de csv sin importar el tipo del objeto que retorna
 *
 */
@CsvDataType
public class CsvObjetoTesteo {

	@CsvField(pos = 1)
	private String primero = "";
	@CsvField(pos = 2)
	private String segundo = "";
	@CsvField(pos = 3)
	private String tercero = "";
	@CsvField(pos = 4)
	private int cuarto = 0;
	@CsvField(pos = 5)
	private int quinto = 0;

	public CsvObjetoTesteo() {
	}

	public String getPrimero() {
		return primero;
	}

	public void setPrimero(String primero) {
		this.primero = primero;
	}

	public String getSegundo() {
		return segundo;
	}

	public void setSegundo(String segundo) {
		this.segundo = segundo;
	}

	public String getTercero() {
		return tercero;
	}

	public void setTercero(String tercero) {
		this.tercero = tercero;
	}

	public int getFourth() {
		return cuarto;
	}

	public void setFourth(int fourth) {
		this.cuarto = fourth;
	}

	public int getFifth() {
		return quinto;
	}

	public void setFifth(int fifth) {
		this.quinto = fifth;
	}
}
