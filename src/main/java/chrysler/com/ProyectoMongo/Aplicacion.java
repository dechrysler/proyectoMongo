package chrysler.com.ProyectoMongo;

import chrysler.com.ProyectoMongo.necesario.Modelo;
import chrysler.com.ProyectoMongo.vista.Vista;

public class Aplicacion {
	public static void main (String []args) {
		Vista vista = new Vista();
		Modelo modelo = new Modelo();
		Controlador controlador;
		controlador = new Controlador(vista,modelo);
	}
}
