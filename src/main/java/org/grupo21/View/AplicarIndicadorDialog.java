package org.grupo21.View;

import org.grupo21.CustomExceptions.ExcepcionUsuario;
import org.grupo21.CustomExceptions.ExcepcionUsuario.TipoExcepcion;
import org.grupo21.Model.CuentaViewItem;
import org.grupo21.Model.Interfaces.IndicadorInterface;
import org.grupo21.ViewModel.AplicarIndicadorViewModel;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.WindowOwner;

@SuppressWarnings("serial")
public class AplicarIndicadorDialog extends TransactionalDialog<AplicarIndicadorViewModel> {

	public AplicarIndicadorDialog(WindowOwner owner, AplicarIndicadorViewModel model) {
		super(owner, model);
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Aplicar indicador");
		super.createMainTemplate(mainPanel);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel panelCuentasIndicadores = new Panel(mainPanel).setLayout(new VerticalLayout());

		Table<CuentaViewItem> tablaCuentas = new Table<>(panelCuentasIndicadores, CuentaViewItem.class);
		tablaCuentas.setHeight(800);
		tablaCuentas.setWidth(450);
		tablaCuentas.setNumberVisibleRows(5);
		tablaCuentas.bindItemsToProperty("repo.cuentasVista");
		tablaCuentas.bindSelectionToProperty("cuentaParaAplicar");

		describirResultados(tablaCuentas);

		Table<IndicadorInterface> tablaIndicadores = new Table<>(panelCuentasIndicadores, IndicadorInterface.class);
		tablaIndicadores.setHeight(800);
		tablaIndicadores.setWidth(450);
		tablaIndicadores.setNumberVisibleRows(5);
		tablaIndicadores.bindItemsToProperty("repoIndicadores.indicadores");
		tablaIndicadores.bindSelectionToProperty("indicadorParaAplicar");

		describirResultadosIndicadores(tablaIndicadores);

		Panel panelInferior = new Panel(mainPanel).setLayout(new HorizontalLayout());

		new Label(panelInferior).setText("Cuenta");
		new TextBox(panelInferior).setWidth(100).bindValueToProperty("cuentaParaAplicar.compania");
		new Label(panelInferior).setText("Periodo:").setWidth(80);
		new TextBox(panelInferior).setWidth(100).bindValueToProperty("cuentaParaAplicar.cuenta.periodo");
		new Label(panelInferior).setText("Semestre:").setWidth(80);
		new TextBox(panelInferior).setWidth(100).bindValueToProperty("cuentaParaAplicar.cuenta.semestre");
		new Label(panelInferior).setText("Indicador:").setWidth(120);
		new TextBox(panelInferior).setWidth(350).bindValueToProperty("indicadorParaAplicar.ecuacion");

		new Button(mainPanel).setCaption("Calcular").onClick(() -> this.aplicarIndicador());
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

	protected void describirResultadosIndicadores(Table<IndicadorInterface> tabla) {
		new Column<>(tabla).setTitle("Nombre").setFixedSize(250).bindContentsToProperty("nombre");
		new Column<>(tabla).setTitle("Formula").setFixedSize(650).bindContentsToProperty("ecuacion");
	}

	@Override
	protected void addActions(Panel actions) {
		new Button(actions).setCaption("Volver").onClick(this::accept).setAsDefault();
	}

	private void aplicarIndicador() {
		try {
			mostrarMensaje("El resultado es: " + this.getModelObject().aplicarIndicador(), TipoExcepcion.INFORMATIVO);
		} catch (ExcepcionUsuario e) {
			mostrarMensaje(e.getMessage(), e.getTipo());
		}
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