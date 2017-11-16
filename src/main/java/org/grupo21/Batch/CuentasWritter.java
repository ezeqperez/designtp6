package org.grupo21.Batch;

import java.util.ArrayList;
import java.util.List;

import org.grupo21.Repositories.CuentasRepo;
import org.grupo21.Utils.CuentaCsvObject;
import org.springframework.batch.item.ItemWriter;

public class CuentasWritter implements ItemWriter<CuentaCsvObject> {
 
    @Override
    public void write(List<? extends CuentaCsvObject> items) throws Exception {
    	
    	List<CuentaCsvObject> resultado = new ArrayList<CuentaCsvObject>();
    	
        for (CuentaCsvObject cuenta : items) {
            resultado.add(cuenta);
        }
        
        CuentasRepo.getInstance().agregarCuentas(resultado);
    }
}