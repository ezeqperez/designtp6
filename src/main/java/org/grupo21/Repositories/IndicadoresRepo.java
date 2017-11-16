package org.grupo21.Repositories;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.grupo21.CustomExceptions.ExcepcionUsuario;
import org.grupo21.CustomExceptions.ExcepcionUsuario.TipoExcepcion;
import org.grupo21.Escaner.Escaner;
import org.grupo21.Escaner.ParseException;
import org.grupo21.Escaner.TokenMgrError;
import org.grupo21.Model.Cuenta;
import org.grupo21.Model.Indicador;
import org.grupo21.Model.Interfaces.IndicadorInterface;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

@Observable
public class IndicadoresRepo {

	private static IndicadoresRepo instance = null;

	public static IndicadoresRepo getInstance() {
		if (instance == null)
			newInstance();
		return instance;
	}
	
	public IndicadoresRepo() {
	}
	
	public static void newInstance() {
		instance = new IndicadoresRepo();
	}

	public void remover(IndicadorInterface indicador) {
		DatabaseRepo.getInstance().removeAndCommit(indicador);
	}
	
	public IndicadorInterface leerFormula(String nombreFormula, String formula) throws ExcepcionUsuario {

		if (nombreFormula.compareTo("") == 0)
			throw new ExcepcionUsuario("El nombre del indicador no puede estar vacio.", TipoExcepcion.ERROR);

		if (formula.compareTo("") == 0)
			throw new ExcepcionUsuario("La formula no puede estar vacia", TipoExcepcion.ERROR);

		return parsearFormula(nombreFormula + "=" + formula);
	}

	public double aplicarIndicadorACuenta(Cuenta p_cuenta, IndicadorInterface indicadorParaAplicar) {
		try{
			return indicadorParaAplicar.calcular(p_cuenta);
		} catch (NullPointerException e) {
			throw new ExcepcionUsuario("Seleccione cuenta e indicador para poder aplicar", TipoExcepcion.ERROR);
		}
	}

	public IndicadorInterface obtenerIndicadorSegunNombre(String nombre) {
		
		try {
			Indicador indicadorObtenido = DatabaseRepo.getInstance().getEntityManager().createQuery("from Indicador where nombre = '" + nombre + "'", Indicador.class).setMaxResults(1).getSingleResult();
					
			indicadorObtenido.setFormula(((Indicador)instance.leerFormula(indicadorObtenido.getNombre(), indicadorObtenido.getEcuacion())).getFormula());
			
			return indicadorObtenido;
			
		} catch (Exception e) {
			throw new ExcepcionUsuario("No existe el indicador " + nombre, TipoExcepcion.ERROR);
		}
		
	}

	public boolean existeIndicador(String nombre) {
		try {
			DatabaseRepo.getInstance().getEntityManager().createQuery("from Indicador where nombre = '" + nombre + "'", Indicador.class).setMaxResults(1).getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private IndicadorInterface parsearFormula(String formula) throws ExcepcionUsuario {
		try {
			Escaner _escaner = new Escaner(new StringReader(formula));
			_escaner.setContext(this);
			return _escaner.parsear();
		} catch (TokenMgrError e2) {
			throw new ExcepcionUsuario("Error en la formula: " + e2.getMessage(), TipoExcepcion.ERROR);
		} catch (ParseException e) {
			throw new ExcepcionUsuario("La formula no es correcta", TipoExcepcion.ERROR);
		}
	}

	public void agregarIndicador(IndicadorInterface unIndicador) {
		DatabaseRepo.getInstance().persist(unIndicador);
		ObservableUtils.firePropertyChanged(this, "indicadores");
	}
	
	public Collection<IndicadorInterface> getIndicadoresPorUsuario(String usuario) {
		return getIndicadores().stream().filter(indi -> sonElMismoUsuario(usuario, indi.getUsuario()) || usuarioGenerico(indi.getUsuario())).collect(Collectors.toList());
	}

	private boolean usuarioGenerico(String usuario) {
		return usuario.compareTo("") == 0;
	}

	private boolean sonElMismoUsuario(String usuario, String usuario2) {
		if(usuario2 != null)
			return usuario2.compareTo(usuario) == 0;
		
		return true;
	}


	/*********************************** GETTERS Y SETTERS *******************************************/
	
	public Collection<IndicadorInterface> getIndicadores() {
		Collection<IndicadorInterface> indicadores = new ArrayList<IndicadorInterface>();
		
		List<Indicador> result = DatabaseRepo.getInstance().getEntityManager().createQuery("from Indicador", Indicador.class).getResultList();
		
		for (Indicador indi : result) {
			indi.setFormula(((Indicador)instance.leerFormula(indi.getNombre(), indi.getEcuacion())).getFormula());
			indicadores.add(indi);
		}
		
		return indicadores;
	}	
}
