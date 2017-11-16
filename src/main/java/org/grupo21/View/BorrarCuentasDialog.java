package org.grupo21.View;

import org.grupo21.CustomExceptions.ExcepcionUsuario;
import org.grupo21.CustomExceptions.ExcepcionUsuario.TipoExcepcion;
import org.apache.commons.collections15.list.FastArrayList;
import org.grupo21.Model.Cuenta;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.grupo21.ViewModel.BorrarCuentasViewModel;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.WindowOwner;

import java.awt.*;
import java.util.List;

@SuppressWarnings("serial")
public class BorrarCuentasDialog extends TransactionalDialog<BorrarCuentasViewModel> {

	private List<Cuenta> cuentas = new FastArrayList<Cuenta>();

	public BorrarCuentasDialog(WindowOwner owner, BorrarCuentasViewModel borrarCuentasViewModel) {
		super(owner, borrarCuentasViewModel);
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Borrar Cuentas");
		super.createMainTemplate(mainPanel);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel panelCuentas = new Panel(mainPanel).setLayout(new VerticalLayout());

		Table<Cuenta> tablaCuentas = new Table<>(panelCuentas, Cuenta.class);
		tablaCuentas.setHeight(800);
		tablaCuentas.setWidth(900);
		tablaCuentas.setNumberVisibleRows(10);
		tablaCuentas.bindItemsToProperty("repo.cuentas");
		tablaCuentas.bindSelectionToProperty("cuentaParaBorrar");

		describirResultados(tablaCuentas);

		Panel panelInferior = new Panel(mainPanel).setLayout(new HorizontalLayout());

		new Label(panelInferior).setText("Cuenta");
		new Label(panelInferior).setBackground(Color.white).setWidth(100)
				.bindValueToProperty("cuentaParaBorrar.compania");
		new Label(panelInferior).setText("Periodo:").setWidth(80);
		new Label(panelInferior).setBackground(Color.white).setWidth(100)
				.bindValueToProperty("cuentaParaBorrar.periodo");
		new Label(panelInferior).setText("Semestre:").setWidth(80);
		new Label(panelInferior).setBackground(Color.white).setWidth(100)
				.bindValueToProperty("cuentaParaBorrar.semestre");
		new Button(mainPanel).setCaption("Borrar").onClick(() -> this.borrarCuentaSeleccionada());
	}

	protected void describirResultados(Table<Cuenta> tabla) {
		new Column<>(tabla).setTitle("Compania").setFixedSize(100).bindContentsToProperty("compania");
		new Column<>(tabla).setTitle("Periodo").setFixedSize(70).bindContentsToProperty("periodo");
		new Column<>(tabla).setTitle("Semestre").setFixedSize(70).bindContentsToProperty("semestre");
		new Column<>(tabla).setTitle("Año Creacion").setFixedSize(100).bindContentsToProperty("anioCreacion");
		new Column<>(tabla).setTitle("Inversion Inicial").setFixedSize(100).bindContentsToProperty("inversionInicial");
		new Column<>(tabla).setTitle("Ingresos Continuos").setFixedSize(120)
				.bindContentsToProperty("ingresosContinuos");
		new Column<>(tabla).setTitle("Ingresos Discontinuos").setFixedSize(130)
				.bindContentsToProperty("ingresosDiscontinuos");
		new Column<>(tabla).setTitle("Capital Total").setFixedSize(100).bindContentsToProperty("capitalTotal");
		new Column<>(tabla).setTitle("Deuda").setFixedSize(70).bindContentsToProperty("deuda");
		new Column<>(tabla).setTitle("Margen de Ventas (%)").setFixedSize(140).bindContentsToProperty("margenVenta");
	}

	@Override
	protected void addActions(Panel actions) {
		new Button(actions).setCaption("Volver").onClick(this::accept).setAsDefault();

	}

	private void borrarCuentaSeleccionada() {
		Cuenta cuentaParaBorrar = this.getModelObject().getCuentaParaBorrar();

		if (cuentaParaBorrar == null || cuentaParaBorrar == null) {
			mostrarMensaje("Seleccione una cuenta para borrar", TipoExcepcion.ERROR);
			return;
		}
		this.getModelObject().borrarCuenta();
		mostrarMensaje("Se borro la cuenta: periodo: "
				+ cuentaParaBorrar.getPeriodo() + ", semestre: " + cuentaParaBorrar.getSemestre(),
				TipoExcepcion.INFORMATIVO);

	}

	protected void mostrarMensaje(String mensaje, ExcepcionUsuario.TipoExcepcion tipo) {
		MessageBox messageBox = null;
		switch (tipo) {
		case ERROR:
			messageBox = new MessageBox(this, MessageBox.Type.Error);
			break;
		case INFORMATIVO:
			messageBox = new MessageBox(this, MessageBox.Type.Information);
			break;
		case ALERTA:
			messageBox = new MessageBox(this, MessageBox.Type.Warning);
			break;
		}
		messageBox.setMessage(mensaje);
		messageBox.open();
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

}
