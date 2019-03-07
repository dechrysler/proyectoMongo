package chrysler.com.ProyectoMongo.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Panel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class Vista extends JFrame {

	public JPanel contenedor;
	public JMenu mnNewMenu,mnNewMenu_1;
	public JMenuItem menuItemSalir,mntmPersonaje,mntmPokemon;
	public JTabbedPane tabbedPane;
	public JMenu mnConsultas;
	public JMenuItem mntmComplejasimple;
	public Vista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 424);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);
		
	    menuItemSalir = new JMenuItem("Salir");
		mnNewMenu.add(menuItemSalir);
		menuItemSalir.setActionCommand("salir");
		
		mnNewMenu_1 = new JMenu("Editar");
		menuBar.add(mnNewMenu_1);
		
		mntmPersonaje = new JMenuItem("Personajes");
		mntmPersonaje.setHorizontalAlignment(SwingConstants.CENTER);
		mntmPersonaje.setActionCommand("panel_personajes");
		mnNewMenu_1.add(mntmPersonaje);
		
		
		mntmPokemon = new JMenuItem("Pokemon");
		mntmPokemon.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_1.add(mntmPokemon);
		mntmPokemon.setActionCommand("panel_pokemon");
		
		mnConsultas = new JMenu("Consultas");
		menuBar.add(mnConsultas);
		
		mntmComplejasimple = new JMenuItem("Compleja/Simple");
		mnConsultas.add(mntmComplejasimple);
		contenedor = new JPanel();
		contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contenedor);
		contenedor.setLayout(null);
		mntmComplejasimple.setActionCommand("CONSULTAS");
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 497, 353);
		contenedor.add(tabbedPane);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
