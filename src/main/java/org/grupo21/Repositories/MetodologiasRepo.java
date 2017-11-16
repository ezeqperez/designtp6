package org.grupo21.Repositories;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.grupo21.CustomExceptions.ExcepcionUsuario;
import org.grupo21.CustomExceptions.ExcepcionUsuario.TipoExcepcion;
import org.grupo21.Escaner.Escaner;
import org.grupo21.Escaner.ParseException;
import org.grupo21.Escaner.TokenMgrError;
import org.grupo21.Model.Metodologia;
import org.grupo21.Model.Interfaces.CondicionInterface;
import org.uqbar.commons.utils.Observable;

@Observable
public class MetodologiasRepo {

	private static MetodologiasRepo instance = null;

	public static MetodologiasRepo getInstance() {
		if (instance == null)
			newInstance();
		return instance;
	}

	public static void newInstance() {
		instance = new MetodologiasRepo();
	}

	/**
	 * Toma como parametro una condicion en formato String y la retorna como
	 * CondicionInterface.
	 * 
	 * Throws ExcepcionUsuario
	 * 
	 * @param condicion
	 * @return
	 */
	public CondicionInterface leerCondicion(String condicion) {
		if (condicion.compareTo("") == 0)
			throw new ExcepcionUsuario("La condicion no puede estar vacia", TipoExcepcion.ERROR);

		return parsearCondicion(condicion);
	}

	private CondicionInterface parsearCondicion(String condicion) throws ExcepcionUsuario {
		try {
			Escaner _escaner = new Escaner(new StringReader(condicion));
			_escaner.setContext(IndicadoresRepo.getInstance());
			return _escaner.parsearCondicion();
		} catch (TokenMgrError e2) {
			throw new ExcepcionUsuario("Error en la condición: " + e2.getMessage(), TipoExcepcion.ERROR);
		} catch (ParseException e) {
			throw new ExcepcionUsuario("La condición no es correcta", TipoExcepcion.ERROR);
		}
	}

	public void agregarMetodologia(Metodologia unaMetodologia) {
		if (existeMetodologia(unaMetodologia.getNombre()))
			throw new ExcepcionUsuario("La metodologia ya existe!", TipoExcepcion.ERROR);

		if (unaMetodologia.getNombre().compareTo("") == 0)
			throw new ExcepcionUsuario("El nombre de la metodologia no puede estar vacio", TipoExcepcion.ERROR);

		if (unaMetodologia.getCondiciones().isEmpty())
			throw new ExcepcionUsuario("No hay condiciones cargadas", TipoExcepcion.ERROR);

		for (CondicionInterface condicion : unaMetodologia.getCondiciones()) {
			DatabaseRepo.getInstance().persist(condicion);
		}

		DatabaseRepo.getInstance().persist(unaMetodologia);
	}

	public void removerMetodologia(Metodologia unaMetodologia) {
		DatabaseRepo.getInstance().removeAndCommit(unaMetodologia);
	}

	public boolean existeMetodologia(String nombreMetodologia) {
		try {
			DatabaseRepo.getInstance().getEntityManager()
					.createQuery("from Metodologia where nombre = '" + nombreMetodologia + "'", Metodologia.class)
					.setMaxResults(1).getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Metodologia getMetodologiaPorId(int id) {
		return parsearMetodologia(DatabaseRepo.getInstance().getEntityManager().find(Metodologia.class, new Long(id)));
	}

	public List<Metodologia> getMetodologiasPorUsuario(String nombreUsuario) {
		return DatabaseRepo.getInstance().getEntityManager()
				.createQuery("from Metodologia where usuario = '" + nombreUsuario + "' OR usuario = ''",
						Metodologia.class)
				.getResultList();
	}

	public Collection<Metodologia> getMetodologias() {
		Collection<Metodologia> metodologias = new ArrayList<Metodologia>();
		List<Metodologia> result = DatabaseRepo.getInstance().getEntityManager()
				.createQuery("from Metodologia", Metodologia.class).getResultList();
		List<CondicionInterface> condiciones = new ArrayList<CondicionInterface>();

		for (Object obj : result) {
			Metodologia _metodologia = (Metodologia) obj;
			condiciones.clear();

			Metodologia _metodologiaParseada = new Metodologia();
			_metodologiaParseada.setId(_metodologia.getId());
			_metodologiaParseada.setNombre(_metodologia.getNombre());
			_metodologiaParseada.setUsuario(_metodologia.getUsuario());

			for (CondicionInterface ci : _metodologia.getCondiciones()) {
				CondicionInterface ciParseada = parsearCondicion(ci.getCondicion());
				ciParseada.setId(ci.getId());
				condiciones.add(ciParseada);
			}
			_metodologiaParseada.setCondiciones(condiciones);
			metodologias.add(_metodologiaParseada);
		}
		return metodologias;
	}

	public Metodologia parsearMetodologia(Metodologia _metodologia) {
		if(_metodologia == null)
			return _metodologia;
		
		List<CondicionInterface> condiciones = new ArrayList<CondicionInterface>();

		condiciones.clear();

		Metodologia _metodologiaParseada = new Metodologia();
		_metodologiaParseada.setId(_metodologia.getId());
		_metodologiaParseada.setNombre(_metodologia.getNombre());
		_metodologiaParseada.setUsuario(_metodologia.getUsuario());

		for (CondicionInterface ci : _metodologia.getCondiciones()) {
			CondicionInterface ciParseada = parsearCondicion(ci.getCondicion());
			ciParseada.setId(ci.getId());
			condiciones.add(ciParseada);
		}
		_metodologiaParseada.setCondiciones(condiciones);
		return _metodologiaParseada;
	}
}
