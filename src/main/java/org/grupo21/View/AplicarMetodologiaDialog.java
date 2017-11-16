package org.grupo21.View;

import org.grupo21.CustomExceptions.ExcepcionUsuario;
import org.grupo21.CustomExceptions.ExcepcionUsuario.TipoExcepcion;
import org.grupo21.Model.Empresa;
import org.grupo21.Model.Metodologia;
import org.grupo21.ViewModel.AplicarMetodologiaViewModel;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.bindings.ObservableProperty;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.Spinner;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

@SuppressWarnings("serial")
public class AplicarMetodologiaDialog extends TransactionalDialog<AplicarMetodologiaViewModel> {

	public AplicarMetodologiaDialog(WindowOwner owner, AplicarMetodologiaViewModel aplicarMetodologiaVM) {
		super(owner, aplicarMetodologiaVM);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Aplicar Metodologia");

		mainPanel.setLayout(new VerticalLayout());

		Panel panelTop = new Panel(mainPanel).setLayout(new HorizontalLayout());
		new Label(panelTop).setText("Periodo");
		new NumericField(panelTop).setWidth(100).bindValueToProperty("periodo");
		new Label(panelTop).setText("Semestre");
		Spinner spinSemestre = new Spinner(panelTop);
		spinSemestre.setWidth(100).bindValueToProperty("semestre");
		spinSemestre.setMaximumValue(2);
		spinSemestre.setMinimumValue(1);

		Selector<Metodologia> selectorMetodologia = new Selector<Metodologia>(panelTop).allowNull(false);
		selectorMetodologia.setWidth(300);
		selectorMetodologia.bindValueToProperty("metodologiaSeleccionada");
		Binding<Metodologia, Selector<Metodologia>, ListBuilder<Metodologia>> propiedadMetodologia = selectorMetodologia
				.bindItems(new ObservableProperty("repoMetodologias.metodologias"));
		propiedadMetodologia.setAdapter(new PropertyAdapter(Metodologia.class, "nombre"));

		Button btnAplicar = new Button(panelTop);
		btnAplicar.setCaption("Aplicar").setWidth(300);
		btnAplicar.onClick(() -> this.aplicarMetodologia());

		Table<Empresa> tablaResultados = new Table<>(mainPanel, Empresa.class);
		tablaResultados.setHeight(800);
		tablaResultados.setWidth(900);
		tablaResultados.setNumberVisibleRows(10);
		tablaResultados.bindItemsToProperty("resultadoAplicarMetodologia");

		describirResultados(tablaResultados);
	}

	protected void describirResultados(Table<Empresa> tabla) {
		new Column<>(tabla).setTitle("Compania").setFixedSize(250).bindContentsToProperty("nombre");
	}

	private void aplicarMetodologia() {
		this.getModelObject().aplicarMetodologia();
		mostrarMensaje("Aplicada correctamente. Resultado: " + this.getModelObject().resultadoAplicarMetodologia.size()
				+ " empresas", TipoExcepcion.INFORMATIVO);
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
