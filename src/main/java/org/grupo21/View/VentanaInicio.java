package org.grupo21.View;

import org.grupo21.ViewModel.PrincipalViewModel;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.GroupPanel;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MainWindow;
import org.uqbar.arena.windows.MessageBox;

@SuppressWarnings("serial")
public class VentanaInicio extends MainWindow<PrincipalViewModel> {

	public VentanaInicio() {
		super(new PrincipalViewModel());
	}

	@Override
	public void createContents(Panel mainPanel) {
		this.setTitle("Cuentas");

		mainPanel.setLayout(new VerticalLayout());

		crearGrupoCuentas(mainPanel);
		crearGrupoIndicadores(mainPanel);
		crearGrupoMetodologias(mainPanel);
	}

	private void crearGrupoCuentas(Panel mainPanel) {
		GroupPanel cuentasPanel = new GroupPanel(mainPanel);
		cuentasPanel.setLayout(new HorizontalLayout());
		cuentasPanel.setTitle("CUENTAS");

		new Button(cuentasPanel).setCaption("Cargar Cuentas").onClick(() -> this.cargarCuentas()).setWidth(300);
		new Button(cuentasPanel).setCaption("Ver Cuentas").onClick(() -> this.verCuentas()).setWidth(300);
		new Button(cuentasPanel).setCaption("Borrar Cuentas").onClick(() -> this.borrarCuentas()).setWidth(300);
	}

	private void crearGrupoIndicadores(Panel mainPanel) {
		GroupPanel indicadoresPanel = new GroupPanel(mainPanel);
		indicadoresPanel.setLayout(new HorizontalLayout());
		indicadoresPanel.setTitle("INDICADORES");

		new Button(indicadoresPanel).setCaption("Ingresar Indicadores").onClick(() -> this.ingresarIndicadores())
				.setWidth(300);
		new Button(indicadoresPanel).setCaption("Ver Indicadores").onClick(() -> this.verIndicadores()).setWidth(300);
		new Button(indicadoresPanel).setCaption("Aplicar Indicador").onClick(() -> this.aplicarIndicador())
				.setWidth(300);
	}

	private void crearGrupoMetodologias(Panel mainPanel) {
		GroupPanel metodologiasPanel = new GroupPanel(mainPanel);
		metodologiasPanel.setLayout(new HorizontalLayout());
		metodologiasPanel.setTitle("METODOLOGIAS");

		new Button(metodologiasPanel).setCaption("Crear Metodologia").onClick(() -> this.crearMetodologia())
				.setWidth(300);
		new Button(metodologiasPanel).setCaption("Aplicar Metodologia").onClick(() -> this.aplicarMetodologia())
				.setWidth(300);
	}

	public void cargarCuentas() {
		Dialog<?> cargarCuentas = new CargarCuentasDialog(this, this.getModelObject().crearCargarCuentasVM());
		cargarCuentas.setTaskDescription("Seleccione un archivo para cargar la información de las cuentas");
		cargarCuentas.open();
	}

	public void verCuentas() {
		Dialog<?> verCuentas = new VerCuentasDialog(this, this.getModelObject().crearVerCuentasVM());
		if (!this.getModelObject().hayCuentasCargadas()) {
			mostrarMensaje("No hay cuentas cargadas", MessageBox.Type.Error);
			return;
		}
		verCuentas.setTaskDescription("Listado de las cuentas cargadas:");
		verCuentas.open();
	}

	public void borrarCuentas() {
		Dialog<?> borrarCuentas = new BorrarCuentasDialog(this, this.getModelObject().crearBorrarCuentasVM());
		if (!this.getModelObject().hayCuentasCargadas()) {
			mostrarMensaje("No hay cuentas cargadas", MessageBox.Type.Error);
			return;
		}
		borrarCuentas.setTaskDescription("Listado de las cuentas cargadas:");
		borrarCuentas.open();
	}

	public void ingresarIndicadores() {
		Dialog<?> ingresarIndicadores = new IngresarIndicadoresDialog(this,
				this.getModelObject().crearCargarIndicadoresVM());
		ingresarIndicadores.setTaskDescription("Ingrese nombre y formula para crear un indicador");
		ingresarIndicadores.open();
	}

	public void verIndicadores() {
		Dialog<?> verIndicadores = new VerIndicadoresDialog(this, this.getModelObject().crearVerIndicadoresVM());
		if (!this.getModelObject().hayIndicadoresCargados()) {
			mostrarMensaje("No hay indicadores cargados", MessageBox.Type.Error);
			return;
		}
		verIndicadores.setTaskDescription("Listado de indicadores cargados:");
		verIndicadores.open();
	}

	public void aplicarIndicador() {
		Dialog<?> aplicarIndicadores = new AplicarIndicadorDialog(this,
				this.getModelObject().crearAplicarIndicadorVM());
		if (!this.getModelObject().hayIndicadoresCargados()) {
			mostrarMensaje("No hay indicadores cargados", MessageBox.Type.Error);
			return;
		} else if (!this.getModelObject().hayCuentasCargadas()) {
			mostrarMensaje("No hay cuentas cargadas", MessageBox.Type.Error);
			return;
		}
		aplicarIndicadores.setTaskDescription("Seleccione una cuenta y aplique un indicador:");
		aplicarIndicadores.open();
	}

	public void crearMetodologia() {
		Dialog<?> dialogCrearMetodologia = new CrearMetodologiaView(this, this.getModelObject().crearMetododologiaVM());
		dialogCrearMetodologia
				.setTaskDescription("Ingrese condiciones, y finalmente guarde la metodologia con un nombre");
		dialogCrearMetodologia.open();
	}

	public void aplicarMetodologia() {
		if (!this.getModelObject().hayCuentasCargadas()) {
			mostrarMensaje("No hay cuentas cargadas", MessageBox.Type.Error);
			return;
		}
		if (!this.getModelObject().hayMetodologiasCargadas()) {
			mostrarMensaje("No hay metodologias cargadas", MessageBox.Type.Error);
			return;
		}
		Dialog<?> dialogAplicarMetodologia = new AplicarMetodologiaDialog(this,
				this.getModelObject().crearAplicarMetododologiaVM());
		dialogAplicarMetodologia
				.setTaskDescription("Seleccione una Metodologia para aplicarla sobre todas las cuentas");
		dialogAplicarMetodologia.open();
	}

	protected void mostrarMensaje(String mensaje, MessageBox.Type tipo) {
		MessageBox messageBox = new MessageBox(this, tipo);
		messageBox.setMessage(mensaje);
		messageBox.open();
	}
}
