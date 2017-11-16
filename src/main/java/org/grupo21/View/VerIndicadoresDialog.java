package org.grupo21.View;

import org.grupo21.Model.Interfaces.IndicadorInterface;
import org.grupo21.ViewModel.VerIndicadoresViewModel;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.WindowOwner;

@SuppressWarnings("serial")
public class VerIndicadoresDialog extends TransactionalDialog<VerIndicadoresViewModel> {

	public VerIndicadoresDialog(WindowOwner owner, VerIndicadoresViewModel verIndicadoresViewModel) {
		super(owner, verIndicadoresViewModel);
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Ver Indicadores");
		super.createMainTemplate(mainPanel);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel panelIndicadores = new Panel(mainPanel).setLayout(new VerticalLayout());

		Table<IndicadorInterface> tablaIndicadores = new Table<>(panelIndicadores, IndicadorInterface.class);
		tablaIndicadores.setHeight(800);
		tablaIndicadores.setWidth(900);
		tablaIndicadores.setNumberVisibleRows(10);
		tablaIndicadores.bindItemsToProperty("repo.indicadores");

		describirResultados(tablaIndicadores);

	}

	protected void describirResultados(Table<IndicadorInterface> tabla) {
		new Column<>(tabla).setTitle("Nombre").setFixedSize(250).bindContentsToProperty("nombre");
		new Column<>(tabla).setTitle("Formula").setFixedSize(650).bindContentsToProperty("ecuacion");
	}

	@Override
	protected void addActions(Panel actions) {
		new Button(actions).setCaption("Volver").onClick(this::accept).setAsDefault();
	}
}