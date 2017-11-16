package org.grupo21.View;

import org.grupo21.CustomExceptions.ExcepcionUsuario;
import org.grupo21.Model.Interfaces.IndicadorInterface;
import org.grupo21.ViewModel.CrearIndicadoresViewModel;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.GroupPanel;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.WindowOwner;

@SuppressWarnings("serial")
public class IngresarIndicadoresDialog extends TransactionalDialog<CrearIndicadoresViewModel> {

	public IngresarIndicadoresDialog(WindowOwner owner, CrearIndicadoresViewModel crearIndicadoresViewModel) {
		super(owner, crearIndicadoresViewModel);
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Ingresar Indicadores");
		super.createMainTemplate(mainPanel);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel panelIndicadores = new Panel(mainPanel).setLayout(new VerticalLayout());

		Table<IndicadorInterface> tablaIndicadores = new Table<>(panelIndicadores, IndicadorInterface.class);
		tablaIndicadores.setHeight(900);
		tablaIndicadores.setWidth(900);
		tablaIndicadores.setNumberVisibleRows(10);
		tablaIndicadores.bindItemsToProperty("repo.indicadores");

		describirResultados(tablaIndicadores);
		this.crearPanelCreador(panelIndicadores);
	}

	protected void describirResultados(Table<IndicadorInterface> tabla) {
		new Column<>(tabla).setTitle("Nombre").setFixedSize(250).bindContentsToProperty("nombre");
		new Column<>(tabla).setTitle("Formula").setFixedSize(650).bindContentsToProperty("ecuacion");
	}

	private void crearPanelCreador(Panel panelIndicadores) {
		GroupPanel panelCreador = new GroupPanel(panelIndicadores);
		panelCreador.setLayout(new HorizontalLayout());
		panelCreador.setTitle("Crear indicador");

		new TextBox(panelCreador).setMultiLine(false).setWidth(350).setHeight(20)
				.bindValueToProperty("nombreIndicador");

		new TextBox(panelCreador).setMultiLine(false).setWidth(650).setHeight(20)
				.bindValueToProperty("formulaIndicador");

		new Button(panelCreador).setCaption("Crear indicador").onClick(() -> this.crearIndicador());
	}

	@Override
	protected void addActions(Panel actions) {
		new Button(actions).setCaption("Aceptar").onClick(() -> persistirYRetornar(this));
	}

	private void persistirYRetornar(IngresarIndicadoresDialog ingresarIndicadoresDialog) {
		// NO POR AHORA
		// this.getModelObject().getIndicadoresController().persistirIndicadores();
		ingresarIndicadoresDialog.accept();
	}

	private void crearIndicador() {
		try {
			this.getModelObject().crearIndicador();
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