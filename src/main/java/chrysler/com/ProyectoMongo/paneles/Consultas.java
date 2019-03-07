package chrysler.com.ProyectoMongo.paneles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import chrysler.com.ProyectoMongo.base.Pokemon;
import chrysler.com.ProyectoMongo.necesario.Modelo;
import javax.swing.JTextField;

public class Consultas extends JPanel implements ActionListener {

	private JList lista,list;
	private DefaultListModel pokimon;
	private DefaultListModel pokimon2;
	private JButton btnBuscar;
	private JComboGenerico<String> comboGenerico;
	private Modelo modelo;
	private  JComboGenerico<String> comboGenerico_1;
	private JButton btnBComplex;
	private JComboGenerico<String> comboGenerico_2;
	public Consultas(Modelo modelo) {
		setLayout(null);
		this.modelo= modelo;
		JLabel lblConsultaSimple = new JLabel("Consulta Simple");
		lblConsultaSimple.setForeground(Color.BLUE);
		lblConsultaSimple.setBounds(10, 11, 92, 14);
		add(lblConsultaSimple);
		
		JLabel lblNewLabel = new JLabel("Dame todos los pokemon de tipo");
		lblNewLabel.setBounds(10, 46, 197, 14);
		add(lblNewLabel);
		
		 comboGenerico = new JComboGenerico<String>();
		comboGenerico.setPreferredSize(new Dimension(100, 20));
		comboGenerico.setBounds(205, 43, 128, 20);
		add(comboGenerico);
		
		 btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(351, 42, 89, 23);
		add(btnBuscar);
		btnBuscar.addActionListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 71, 378, 76);
		add(scrollPane);
		
		 lista = new JList();
		scrollPane.setViewportView(lista);
		pokimon = new DefaultListModel<Pokemon>();
		lista.setModel(pokimon);
		
		JLabel lblNewLabel_1 = new JLabel("Dame pokemon de tipo");
		lblNewLabel_1.setBounds(5, 178, 213, 20);
		add(lblNewLabel_1);
		
		 comboGenerico_1 = new JComboGenerico<String>();
		comboGenerico_1.setPreferredSize(new Dimension(100, 20));
		comboGenerico_1.setBounds(135, 178, 100, 20);
		add(comboGenerico_1);
		
		JLabel lblNewLabel_2 = new JLabel("y vida >");
		lblNewLabel_2.setBounds(244, 181, 46, 14);
		add(lblNewLabel_2);
		
		 comboGenerico_2 = new JComboGenerico<String>();
		comboGenerico_2.setPreferredSize(new Dimension(100, 20));
		comboGenerico_2.setBounds(286, 178, 92, 20);
		add(comboGenerico_2);
		
		btnBComplex = new JButton("Buscar");
		btnBComplex.setBounds(385, 177, 76, 23);
		add(btnBComplex);
		btnBComplex.addActionListener(this);
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 209, 419, 80);
		add(scrollPane_1);
		
		 list = new JList();
		scrollPane_1.setViewportView(list);
		this.setVisible(true);
		pokimon2 = new DefaultListModel<Pokemon>();
		list.setModel(pokimon2);
		for(int i=10;i<20;i++)
			comboGenerico_2.addItem(""+i);
		inicializar();
	}
	public void inicializar() {
		comboGenerico.refrescar(modelo.tiposPokemon());
		comboGenerico_1.refrescar(modelo.tiposPokemon());
		pokimon.removeAllElements();
		pokimon2.removeAllElements();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnBuscar))
		anadirMovidas(modelo.busquedaSimple((String)comboGenerico.getSelectedItem()));
		if(e.getSource().equals(btnBComplex))
			anadirMovidas2(modelo.busquedaCompleja((String)comboGenerico_1.getSelectedItem(), (String)comboGenerico_2.getSelectedItem()));
	}
	public void anadirMovidas(List<Pokemon> pokemones) {
		pokimon.removeAllElements();
		for(Pokemon pokemon : pokemones)
			pokimon.addElement(pokemon);
	}
	public void anadirMovidas2(List<Pokemon> pokemones) {
		pokimon2.removeAllElements();
		for(Pokemon pokemon : pokemones)
			pokimon2.addElement(pokemon);
	}
	}

