package org.grupo21.ViewModelTest;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.collections15.list.FastArrayList;
import org.grupo21.CustomExceptions.ExcepcionUsuario;
import org.grupo21.Model.Metodologia;
import org.grupo21.Model.Interfaces.CondicionInterface;
import org.grupo21.Repositories.CuentasRepo;
import org.grupo21.Repositories.MetodologiasRepo;
import org.grupo21.TestAbstracto.TestPersistencia;
import org.grupo21.Utils.CuentaCsvObject;
import org.grupo21.ViewModel.AplicarMetodologiaViewModel;
import org.junit.Test;

public class AplicarMetodologiaViewModelTest extends TestPersistencia{

	private Metodologia crearUnaMetodologiaParaTest() {
		List<CondicionInterface> condiciones = new FastArrayList<CondicionInterface>();

		CondicionInterface condicion1 = MetodologiasRepo.getInstance().leerCondicion("deuda>1000");

		condiciones.add(condicion1);

		Metodologia m = new Metodologia("nombreMetodologiaParaTest", condiciones);

		return m;
	}

	@Test(expected = ExcepcionUsuario.class)
	public void siNoSeSeleccionaMetodologiaAplicarArrojaExcepcion() {
		CuentaCsvObject cuentaCsv = new CuentaCsvObject();
		cuentaCsv.setCompania("ASD");
		cuentaCsv.setPeriodo(2017);;
		cuentaCsv.setSemestre(1);
		cuentaCsv.setCapitalTotal("10000");
		CuentasRepo.getInstance().agregarCuenta(cuentaCsv);
		
		AplicarMetodologiaViewModel vm = new AplicarMetodologiaViewModel();

		vm.setMetodologiaSeleccionada(null);
		vm.setPeriodo(2017);
		vm.setSemestre(1);
		vm.aplicarMetodologia();
	}

	@Test
	public void siSeAplicaUnaMetodologiaSobreUnaListaDeCuentasVaciaElResultadoEstaVacio() {
		
		Metodologia m = crearUnaMetodologiaParaTest();
		MetodologiasRepo.getInstance().agregarMetodologia(m);
		AplicarMetodologiaViewModel vm = new AplicarMetodologiaViewModel();

		try {
			vm.setPeriodo(2017);
			vm.setMetodologiaSeleccionada(m);
			vm.aplicarMetodologia();
		} catch (ExcepcionUsuario e) {
		}

		assertTrue(vm.resultadoAplicarMetodologia.size() == 0);
	}
}
