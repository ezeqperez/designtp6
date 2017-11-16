package org.grupo21.Repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class DatabaseRepo implements WithGlobalEntityManager {
	
	private static DatabaseRepo _instance;
	
    public DatabaseRepo() {
    }
    
    public static DatabaseRepo getInstance() {
		if (_instance == null)
			_instance = new DatabaseRepo();
		return _instance;
	}
    
    public EntityManager getEntityManager() {
		return entityManager();
    }

    public void beginTransaction() {
		EntityTransaction tx = entityManager().getTransaction();
		
		if(!tx.isActive()){
			tx.begin();
		}
    }

    public void commit() {
		EntityTransaction tx = entityManager().getTransaction();
		if(tx.isActive()){
			tx.commit();
		}
    }
    
    public void persistAndCommit(Object obj){
    	beginTransaction();
    	entityManager().persist(obj);
    	commit();
    }

    public void removeAndCommit(Object obj){
    	beginTransaction();
    	entityManager().remove(obj);
    	commit();
    }
    
    public void persist(Object obj){
    	entityManager().persist(obj);
    }

    public void rollback(){
		EntityTransaction tx = entityManager().getTransaction();
		if(tx.isActive()){
			tx.rollback();
		}
    }
}
