package org.grupo21.Batch;

import java.util.List;

import org.grupo21.Repositories.CuentasRepo;
import org.grupo21.Utils.CuentaCsvObject;

public class CargaBatchRunner {
	public static void main(String[] args) {
		List<CuentaCsvObject> resultScan = CuentasRepo.getInstance().cargarCuentas(args[0]);
		CuentasRepo.getInstance().agregarCuentas(resultScan);
	}
}
