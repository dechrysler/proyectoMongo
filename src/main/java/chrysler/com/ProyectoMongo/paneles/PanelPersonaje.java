package chrysler.com.ProyectoMongo.paneles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import chrysler.com.ProyectoMongo.base.Personaje;
import chrysler.com.ProyectoMongo.base.Pokemon;
import chrysler.com.ProyectoMongo.necesario.Modelo;

public class PanelPersonaje extends JPanel implements ActionListener,ListSelectionListener{

	private BotonesCrud botones;
	private JTextField tfNombre;
	private JTextField tfRegion_procedencia;
	private JTextField tfEdad;
	private PanelAnadirPokemones anadirPanel;
	private Personaje personajeSeleccionado;
	private Modelo modelo;
	private DefaultListModel<Personaje> mlista;
	private boolean editar = false;
	private JList list;
	public PanelPersonaje(Modelo modelo) {
		this.modelo = modelo;
		setLayout(null);
		botones = new BotonesCrud();
		botones.btnEditar.setLocation(114, 0);
		botones.setBounds(10, 231, 203, 69);
		add(botones);
		
		JLabel lblNewLabel = new JLabel("Panel Personaje");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(130, 11, 179, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(10, 54, 69, 14);
		add(lblNewLabel_1);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(127, 51, 86, 20);
		add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblRegionprocedencia = new JLabel("Region_procedencia");
		lblRegionprocedencia.setBounds(0, 79, 129, 14);
		add(lblRegionprocedencia);
		
		tfRegion_procedencia = new JTextField();
		tfRegion_procedencia.setBounds(127, 76, 86, 20);
		add(tfRegion_procedencia);
		tfRegion_procedencia.setColumns(10);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(10, 104, 46, 14);
		add(lblEdad);
		
		tfEdad = new JTextField();
		tfEdad.setBounds(127, 101, 86, 20);
		add(tfEdad);
		tfEdad.setColumns(10);
		anadirPanel = new PanelAnadirPokemones();
		
		anadirPanel.list.setBounds(10, 46, 148, 59);
		anadirPanel.setBounds(10,129, 203, 91);
		add(anadirPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(271, 54, 169, 112);
		add(scrollPane);
		botones.addListeners(this);
		
		list= new JList();
		scrollPane.setViewportView(list);
		mlista = new DefaultListModel<>();
		list.setModel(mlista);
		list.addListSelectionListener(this);
		inicializar();
	}
	
	public void inicializar() {
		anadirPanel.refrescar();
		refrescarLista();
		botones.modoEdicion(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "ELIMINAR":
			modelo.eliminar(personajeSeleccionado);
			limpiar();
			refrescarLista();
			
			break;
		case "GUARDAR":
			Personaje personaje = recogerDatos();
			if(editar==true) {
				recogerDatos(personajeSeleccionado);
				modelo.modificar(personajeSeleccionado);
			}
			else
			modelo.guardar(personaje);
			limpiar();
			refrescarLista();
			botones.modoEdicion(false);
			editar=false;
			break;
		case "EDITAR":
			editar=true;
			botones.modoEdicion(true);
			break;
		case "NUEVO":
			botones.modoEdicion(true);
			editar=false;
			break;
		case "CANCELAR":
			limpiar();
			botones.modoEdicion(false);
			editar=false;
			break;
		default:
			System.out.println("error");
	}
	}
	
	public void limpiar() {
		tfNombre.setText("");
		tfRegion_procedencia.setText("");
		tfEdad.setText("");
		anadirPanel.refrescar();
	}
	public Personaje recogerDatos() {
		 Personaje personaje = new Personaje();
		 personaje.setNombre(tfNombre.getText());
		personaje.setRegion_procedencia(tfRegion_procedencia.getText());
		personaje.setEdad(Float.parseFloat(tfEdad.getText()));
		if(anadirPanel.mlista.getSize()>0) {
			personaje.getPokemones().add(anadirPanel.getListaPokemones().get(0));
		}
		else if(personaje.getPokemones().size()==0)
			personaje.getPokemones().clear();
		return personaje;
	}
	public void refrescarLista(){
		mlista.removeAllElements();
		for(Personaje personajillo: modelo.getPersonajes())
			mlista.addElement(personajillo);
		anadirPanel.comboTipo.refrescar(modelo.pokemonesLibres());
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		if(list.getSelectedIndex()==-1)
			return;
		personajeSeleccionado =(Personaje) list.getSelectedValue();
		limpiar();
		rellenarDatos();
	}
	public void rellenarDatos() {
		tfNombre.setText(personajeSeleccionado.getNombre());
		tfRegion_procedencia.setText(personajeSeleccionado.getRegion_procedencia());
		tfEdad.setText(""+personajeSeleccionado.getEdad());
		for(Pokemon pokimon : personajeSeleccionado.getPokemones()) 
			anadirPanel.mlista.addElement(pokimon);
		
	}
	public void recogerDatos(Personaje personaje) {
		 personaje.setNombre(tfNombre.getText());
		personaje.setRegion_procedencia(tfRegion_procedencia.getText());
		personaje.setEdad(Float.parseFloat(tfEdad.getText()));
		personajeSeleccionado.getPokemones().clear();
		for(Pokemon pokimon : anadirPanel.getListaPokemones())
				personajeSeleccionado.getPokemones().add(pokimon);
	}
}
