package chrysler.com.ProyectoMongo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chrysler.com.ProyectoMongo.necesario.Modelo;
import chrysler.com.ProyectoMongo.paneles.PanelPersonaje;
import chrysler.com.ProyectoMongo.paneles.PanelPokemon;
import chrysler.com.ProyectoMongo.vista.Vista;

public class Controlador implements ActionListener {

	private Vista vista;
	private Modelo modelo;
	
	public Controlador(Vista vista, Modelo modelo) {
		this.modelo = modelo;
		this.vista = vista;
		anadirListener();
	}
	
	public void anadirListener() {
		vista.mntmPersonaje.addActionListener(this);
		vista.mntmPokemon.addActionListener(this);
		vista.menuItemSalir.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("panel_personajes")) {
			PanelPersonaje personaje = new PanelPersonaje(modelo);
			vista.tabbedPane.addTab("personajes", personaje);
		}
		
		else if(e.getActionCommand().equals("salir"))
			System.exit(0);
		else
		{
			PanelPokemon pokemon = new PanelPokemon(modelo);
			vista.tabbedPane.addTab("Pokemon", pokemon);
		}
	}
}
