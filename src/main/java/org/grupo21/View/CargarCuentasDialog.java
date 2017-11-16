package org.grupo21.View;

import org.grupo21.CustomExceptions.ExcepcionUsuario;
import org.grupo21.Utils.CuentaCsvObject;
import org.grupo21.ViewModel.CargarCuentasViewModel;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.FileSelector;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.WindowOwner;

@SuppressWarnings("serial")
public class CargarCuentasDialog extends TransactionalDialog<CargarCuentasViewModel> {

	public CargarCuentasDialog(WindowOwner owner, CargarCuentasViewModel cargarCuentasViewModel) {
		super(owner, cargarCuentasViewModel);
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Cargar Cuentas");
		super.createMainTemplate(mainPanel);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel panelCuentas = new Panel(mainPanel).setLayout(new VerticalLayout());

		this.crearPanelSelector(panelCuentas);

		Table<CuentaCsvObject> tablaCuentas = new Table<>(panelCuentas, CuentaCsvObject.class);
		tablaCuentas.setHeight(800);
		tablaCuentas.setWidth(900);
		tablaCuentas.setNumberVisibleRows(10);
		tablaCuentas.bindItemsToProperty("cuentasCargadas");

		describirResultados(tablaCuentas);
	}

	private void crearPanelSelector(Panel panelCuentas) {
		Panel panelSelector = new Panel(panelCuentas).setLayout(new HorizontalLayout());

		new TextBox(panelSelector).setMultiLine(false).setWidth(800).setHeight(20)
				.bindValueToProperty("direccionArchivo");

		new FileSelector(panelSelector).extensions("*.csv").setCaption("Examinar").setWidth(100)
				.bindValueToProperty("direccionArchivo");

		new Button(panelCuentas).setCaption("Realizar carga").onClick(() -> this.cargarCuentas());
	}

	protected void describirResultados(Table<CuentaCsvObject> tabla) {
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
		new Button(actions).setCaption("Guardar").onClick(() -> this.aceptarOnClicked()).disableOnError()
				.bindEnabledToProperty("hayCuentasCargadas");
		new Button(actions).setCaption("Volver").onClick(this::accept);
	}

	private void cargarCuentas() {
		try {
			this.getModelObject().cargarCuentas();
		} catch (ExcepcionUsuario e) {
			mostrarMensaje(e.getMessage(), e.getTipo());
		}
	}

	private void aceptarOnClicked() {
		this.getModelObject().guardarCuentasCargadas();
		this.accept();
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
}