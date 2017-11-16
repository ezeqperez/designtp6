package org.grupo21.WebServer;

import static spark.Spark.*;

import org.grupo21.WebServer.Controllers.CuentasController;
import org.grupo21.WebServer.Controllers.HomeController;
import org.grupo21.WebServer.Controllers.IndicadorController;
import org.grupo21.WebServer.Controllers.LoginController;
import org.grupo21.WebServer.Controllers.MetodologiaController;

import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {

	public static void main(String[] args) {
		System.out.println("Iniciando servidor");
		
		port(8080);
		staticFileLocation("/public");
		
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		
		LoginController pantallaLogin = new LoginController();
		HomeController pantallaPrincipalController = new HomeController();
		MetodologiaController pantallaMetodologiaController = new MetodologiaController();
		IndicadorController pantallaIndicadorController = new IndicadorController();
		CuentasController pantallaCuentasController = new CuentasController();
		
		get("/", pantallaLogin::mostrar, engine);
		post("/login", pantallaLogin::loguear, engine);
		get("/logout", pantallaLogin::desloguear, engine);
		get("/home", pantallaPrincipalController::mostrar, engine);
		get("/metodologias", pantallaMetodologiaController::mostrar, engine);
		post("/aplicarmetodologias", pantallaMetodologiaController::aplicar, engine);
		get("/metodologia", pantallaMetodologiaController::mostrarCrear, engine);
		post("/condicion", pantallaMetodologiaController::agregarCondicion, engine);
		post("/condiciones", pantallaMetodologiaController::limpiarCondiciones, engine);
		post("/metodologia", pantallaMetodologiaController::crearMetodologia, engine);
		get("/indicadores", pantallaIndicadorController::mostrar, engine);
		post("/aplicarindicadores", pantallaIndicadorController::aplicar, engine);
		get("/cuentas", pantallaCuentasController::mostrar, engine);
		get("/indicador", pantallaIndicadorController::mostrarCrear, engine);
		post("/indicador", pantallaIndicadorController::crear, engine);
	}
}
