package chrysler.com.ProyectoMongo.paneles;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import chrysler.com.ProyectoMongo.base.Pokemon;
import chrysler.com.ProyectoMongo.necesario.Modelo;

public class PanelAnadirPokemones extends JPanel implements ActionListener{

	public JList list;
	private JButton anadir,eliminar;
	public DefaultListModel <Pokemon>mlista;
	public JComboGenerico<Pokemon> comboTipo;
	public PanelAnadirPokemones() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 150, 66);
		add(scrollPane);
		
		 list = new JList();
		scrollPane.setViewportView(list);
		mlista = new DefaultListModel<>();
		list.setModel(mlista);
		
		anadir = new JButton("+");
		anadir.setBounds(153, 11, 41, 23);
		add(anadir);
		
		eliminar = new JButton("-");
		eliminar.setBounds(153, 43, 41, 23);
		add(eliminar);
		comboTipo = new JComboGenerico<>();
		comboTipo.setBounds(0, 68, 150, 20);
		comboTipo.setPreferredSize(new Dimension(100, 20));
		add(comboTipo);
		inicializar();
	}
	private void inicializar() {
		Modelo modelo = new Modelo();
		List<Pokemon>tipos = modelo.getPokemones();
		anadir.addActionListener(this);
		eliminar.addActionListener(this);
		refrescar();
	}
	
	public void anadirPokemones(List<Pokemon> pokemons) {
		limpiar();
		for(Pokemon tipin : pokemons)
			mlista.addElement(tipin);
	}
	public List<Pokemon> getListaPokemones(){
		List<Pokemon> lista = new ArrayList<>();
		lista =  Collections.list(mlista.elements());
		return lista;
	}
	
	public void refrescar() {
		Modelo modelo= new Modelo();
		mlista.removeAllElements();
		comboTipo.refrescar(modelo.getPokemones());
	}
	public void limpiar() {
		mlista.removeAllElements();
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(anadir)) {
			Pokemon tipoSelect = comboTipo.getTipoSeleccionada();
			 if(tipoSelect== null)
				 return;
			 mlista.addElement(tipoSelect);
			 comboTipo.removeItem(tipoSelect);
			 comboTipo.setEnabled(false);
			 }
			 else if(event.getSource().equals(eliminar)) {
				 if(list.getSelectedIndex()==-1)
					 return ;
				 comboTipo.addItem((Pokemon)mlista.remove(list.getSelectedIndex()));
				 mlista.clear();
				 comboTipo.setEnabled(true);
			 }
		}
	
	
}

