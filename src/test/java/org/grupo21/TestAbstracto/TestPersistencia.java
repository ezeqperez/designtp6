package org.grupo21.TestAbstracto;

import org.grupo21.Repositories.DatabaseRepo;
import org.junit.After;
import org.junit.Before;

public abstract class TestPersistencia {
	
	@Before
	public void startUp(){
		DatabaseRepo.getInstance().beginTransaction();
	}
	
	@After
	public void finalize(){
		DatabaseRepo.getInstance().rollback();
	}
	
}
