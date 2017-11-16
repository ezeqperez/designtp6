package org.grupo21.View;

import org.grupo21.CustomExceptions.ExcepcionUsuario;
import org.grupo21.Model.Interfaces.CondicionInterface;
import org.grupo21.ViewModel.CrearMetodologiaViewModel;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.widgets.GroupPanel;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;

@SuppressWarnings("serial")
public class CrearMetodologiaView extends TransactionalDialog<CrearMetodologiaViewModel> {

	public CrearMetodologiaView(WindowOwner owner, CrearMetodologiaViewModel crearMetodologiaVM) {
		super(owner, crearMetodologiaVM);
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Crear Metodologia");
		super.createMainTemplate(mainPanel);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel panelCondiciones = new Panel(mainPanel).setLayout(new VerticalLayout());

		new Label(panelCondiciones).setText("Condiciones: ");

		Panel panelCrearCondicion = new Panel(panelCondiciones).setLayout(new HorizontalLayout());

		new Label(panelCrearCondicion).setText("Ingrese condicion aqui: ");
		new TextBox(panelCrearCondicion).setWidth(300).bindValueToProperty("condicionSinParsear");
		new Button(panelCrearCondicion).setCaption("Agregar Condicion").onClick(() -> this.cargarCondicion());

		Table<CondicionInterface> tablaCondiciones = new Table<>(panelCondiciones, CondicionInterface.class);
		tablaCondiciones.setHeight(900);
		tablaCondiciones.setWidth(900);
		tablaCondiciones.setNumberVisibleRows(5);
		tablaCondiciones.bindItemsToProperty("condiciones");

		describirResultados(tablaCondiciones);

		this.crearPanelCreador(panelCondiciones);

	}

	protected void describirResultados(Table<CondicionInterface> tabla) {
		new Column<>(tabla).setTitle("Condicion").setFixedSize(250).bindContentsToProperty("condicionAString");
	}

	private void crearPanelCreador(Panel panelMetodologias) {
		GroupPanel panelCreador = new GroupPanel(panelMetodologias);
		panelCreador.setLayout(new HorizontalLayout());
		panelCreador.setTitle("Crear metodologia");

		new Label(panelCreador).setText("Nombre: ");

		new TextBox(panelCreador).setMultiLine(false).setWidth(350).setHeight(20)
				.bindValueToProperty("nombreMetodologia");

		new Button(panelCreador).setCaption("Crear metodologia").onClick(() -> {
			try {
				this.getModelObject().crearMetodologia();
				mostrarMensaje("Ingresada correctamente", ExcepcionUsuario.TipoExcepcion.INFORMATIVO);
			} catch (ExcepcionUsuario e) {
				mostrarMensaje(e.getMessage(), e.getTipo());
			}
		});
	}

	void cargarCondicion() {
		try {
			this.getModelObject().cargarCondicion();
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