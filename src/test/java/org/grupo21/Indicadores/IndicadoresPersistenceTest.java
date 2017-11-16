package org.grupo21.Indicadores;

import org.grupo21.Model.Indicador;
import org.grupo21.Repositories.DatabaseRepo;
import org.grupo21.Repositories.IndicadoresRepo;
import org.grupo21.TestAbstracto.TestPersistencia;
import org.junit.*;
import static org.junit.Assert.*;

public class IndicadoresPersistenceTest extends TestPersistencia{

	@Test
	public void persistirUnIndicadorTest() {
		Indicador indi = (Indicador)IndicadoresRepo.getInstance().leerFormula("unIndicadorPersistir", "2+2");
		
		DatabaseRepo.getInstance().persist(indi);
		
		assertNotNull(DatabaseRepo.getInstance().getEntityManager().find(Indicador.class, indi.getId()));
	}
}
