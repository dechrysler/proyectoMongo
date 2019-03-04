package chrysler.com.ProyectoMongo.paneles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import chrysler.com.ProyectoMongo.base.Pokemon;
import chrysler.com.ProyectoMongo.necesario.Modelo;

public class PanelPokemon extends JPanel implements ActionListener,ListSelectionListener{

	/**
	 * Create the panel.
	 */
	public BotonesCrud botones;
	private JTextField tfNombre;
	private JTextField tfTipo;
	private JTextField tfVida;
	private JTextField tfDanio;
	private Modelo modelo;
	private JList list;
	private Pokemon pokemonSeleccionado;
	public boolean editar = false;
	private DefaultListModel<Pokemon>mlista;
	public PanelPokemon(Modelo modelo) {
		setLayout(null);
		this.modelo = modelo;
		botones = new BotonesCrud();
		botones.btnEditar.setLocation(114, 0);
		botones.setBounds(10, 231, 203, 69);
		add(botones);
		
		JLabel lblNewLabel = new JLabel("Panel Pokemon");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(169, 11, 124, 14);
		add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 57, 54, 14);
		add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(98, 54, 86, 20);
		add(tfNombre);
		tfNombre.setColumns(10);
		
		tfTipo = new JTextField();
		tfTipo.setBounds(98, 85, 86, 20);
		add(tfTipo);
		tfTipo.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 88, 46, 14);
		add(lblTipo);
		
		JLabel lblAtaque = new JLabel("Vida");
		lblAtaque.setBounds(10, 124, 46, 14);
		add(lblAtaque);
		
		tfVida = new JTextField();
		tfVida.setBounds(98, 121, 86, 20);
		add(tfVida);
		tfVida.setColumns(10);
		
		JLabel lblDao = new JLabel("Daño");
		lblDao.setBounds(10, 162, 46, 14);
		add(lblDao);
		
		tfDanio = new JTextField();
		tfDanio.setBounds(98, 159, 86, 20);
		add(tfDanio);
		tfDanio.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(262, 57, 161, 132);
		add(scrollPane);
		
		 list = new JList();
		scrollPane.setViewportView(list);
		botones.addListeners(this);
		mlista = new DefaultListModel<>();
		list.setModel(mlista);
		list.addListSelectionListener(this);
		refrescarLista();
		botones.modoEdicion(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "ELIMINAR":
			modelo.eliminar(pokemonSeleccionado);
			limpiar();
			refrescarLista();
			
			break;
		case "GUARDAR":
			Pokemon pokemon = recogerDatos();
			if(editar)
				modelo.modificar(pokemon);
			else
				modelo.guardar(pokemon);
			limpiar();
			refrescarLista();
			botones.modoEdicion(false);
			break;
		case "EDITAR":
			editar=true;
		case "NUEVO":
			botones.modoEdicion(true);
			break;
		case "CANCELAR":
			limpiar();
			botones.modoEdicion(false);
			editar=false;
		case "ELIMINAR_TODO":
			break;
		default:
			System.out.println("error");
	}
	}

	public void limpiar() {
	tfNombre.setText("");
	tfTipo.setText("");
	tfDanio.setText("");
	tfVida.setText("");
	}
	public Pokemon recogerDatos() {
		Pokemon pokemon = new Pokemon();
		pokemon.setNombre(tfNombre.getText());
		pokemon.setTipo(tfTipo.getText());
		pokemon.setVida(Float.parseFloat(tfVida.getText()));
		pokemon.setDanio(Float.parseFloat(tfDanio.getText()));
		return pokemon;
	}
	public void refrescarLista() {
		mlista.removeAllElements();
		for(Pokemon pokimon : modelo.getPokemones())
			mlista.addElement(pokimon);
	}

	public void rellenarDatos() {
		tfNombre.setText(pokemonSeleccionado.getNombre());
		tfTipo.setText(pokemonSeleccionado.getTipo());
		tfDanio.setText(""+pokemonSeleccionado.getDanio());
		tfVida.setText(""+pokemonSeleccionado.getVida());
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if(list.getSelectedIndex()==-1)
			return;
		pokemonSeleccionado = (Pokemon)list.getSelectedValue();
		rellenarDatos();
		
	}
}
