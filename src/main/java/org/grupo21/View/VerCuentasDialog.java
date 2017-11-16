package org.grupo21.View;

import org.grupo21.Model.CuentaViewItem;
import org.grupo21.ViewModel.VerCuentasViewModel;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.WindowOwner;

@SuppressWarnings("serial")
public class VerCuentasDialog extends TransactionalDialog<VerCuentasViewModel> {

	public VerCuentasDialog(WindowOwner owner, VerCuentasViewModel verCuentasViewModel) {
		super(owner, verCuentasViewModel);
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Ver Cuentas");
		super.createMainTemplate(mainPanel);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel panelCuentas = new Panel(mainPanel).setLayout(new VerticalLayout());

		Table<CuentaViewItem> tablaCuentas = new Table<>(panelCuentas, CuentaViewItem.class);
		tablaCuentas.setHeight(800);
		tablaCuentas.setWidth(900);
		tablaCuentas.setNumberVisibleRows(10);
		tablaCuentas.bindItemsToProperty("repo.cuentasVista");

		describirResultados(tablaCuentas);
	}

	protected void describirResultados(Table<CuentaViewItem> tabla) {
		new Column<>(tabla).setTitle("Compania").setFixedSize(100).bindContentsToProperty("compania");
		new Column<>(tabla).setTitle("Periodo").setFixedSize(70).bindContentsToProperty("cuenta.periodo");
		new Column<>(tabla).setTitle("Semestre").setFixedSize(70).bindContentsToProperty("cuenta.semestre");
		new Column<>(tabla).setTitle("Inversion Inicial").setFixedSize(100).bindContentsToProperty("cuenta.inversionInicial");
		new Column<>(tabla).setTitle("Ingresos Continuos").setFixedSize(120)
				.bindContentsToProperty("cuenta.ingresosContinuos");
		new Column<>(tabla).setTitle("Ingresos Discontinuos").setFixedSize(130)
				.bindContentsToProperty("cuenta.ingresosDiscontinuos");
		new Column<>(tabla).setTitle("Capital Total").setFixedSize(100).bindContentsToProperty("cuenta.capitalTotal");
		new Column<>(tabla).setTitle("Deuda").setFixedSize(70).bindContentsToProperty("cuenta.deuda");
		new Column<>(tabla).setTitle("Margen de Ventas (%)").setFixedSize(140).bindContentsToProperty("cuenta.margenVenta");
	}

	@Override
	protected void addActions(Panel actions) {
		new Button(actions).setCaption("Volver").onClick(this::accept).setAsDefault();
	}
}