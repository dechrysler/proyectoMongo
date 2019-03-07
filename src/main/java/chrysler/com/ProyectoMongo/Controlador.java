package chrysler.com.ProyectoMongo;

import static com.mongodb.client.model.Filters.eq;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import chrysler.com.ProyectoMongo.necesario.Modelo;
import chrysler.com.ProyectoMongo.paneles.Consultas;
import chrysler.com.ProyectoMongo.paneles.PanelPersonaje;
import chrysler.com.ProyectoMongo.paneles.PanelPokemon;
import chrysler.com.ProyectoMongo.vista.Login;
import chrysler.com.ProyectoMongo.vista.Usuario;
import chrysler.com.ProyectoMongo.vista.Vista;

public class Controlador implements ActionListener,ChangeListener {
	private  MongoClient mongoClient;
	private  MongoDatabase db;
	private Vista vista;
	private Modelo modelo;
	private Login login;
	private PanelPersonaje personaje;
	PanelPokemon pokemon ;
	Consultas consulta;
	public Controlador(Vista vista, Modelo modelo) {
		this.modelo = modelo;
		this.vista = vista;
		anadirListener();
		 login = new Login();
		userConect();
	}
	public void userConect() {
		boolean conectar=false;
			do {
				
				if(login.hacerVisible().equals(Login.Accion.CONECTAR)){
				Conectar();
				Usuario usu1= new Usuario(login.getUsuario(),login.getContrasena());
				
				if (iniciarSesion(login.getUsuario()).size()==0)
					;
				else
					conectar =usu1.equals(iniciarSesion(login.getUsuario()).get(0));
				if(conectar==false)
					JOptionPane.showMessageDialog(null, "El Usuario o contraseña son erroneas", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					System.exit(0);
					 
				}
			}while(conectar==false);
			desconectar();
	}
	public void anadirListener() {
		vista.mntmPersonaje.addActionListener(this);
		vista.mntmPokemon.addActionListener(this);
		vista.menuItemSalir.addActionListener(this);
		vista.tabbedPane.addChangeListener(this);
		vista.mntmComplejasimple.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("panel_personajes")) {
			 personaje = new PanelPersonaje(modelo);
			vista.tabbedPane.addTab("personajes", personaje);
		}
		
		else if(e.getActionCommand().equals("salir"))
			System.exit(0);
		else if(e.getActionCommand().equals("CONSULTAS"))
		{
			consulta = new Consultas(modelo);
			vista.tabbedPane.addTab("Consultas", consulta);
		}
		else
		{
			pokemon = new PanelPokemon(modelo);
			vista.tabbedPane.addTab("Pokemon", pokemon);
			pokemon.refrescarLista();
		}
	}
	public void Conectar() {
		CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		mongoClient = new MongoClient("localhost",
				MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
		db = mongoClient.getDatabase("usuarios");
		}
	public ArrayList<Usuario> iniciarSesion(String nombre) {
		MongoCollection<Usuario> coleccionPokemon = db.getCollection("users", Usuario.class);
		return coleccionPokemon.find(eq("nombre",nombre)).into(new ArrayList<Usuario>());
	}
	public void desconectar() {
		mongoClient.close();
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if(personaje!=null){
		personaje.refrescarLista();
		}
		if(consulta!=null)
			consulta.inicializar();
	}
}
