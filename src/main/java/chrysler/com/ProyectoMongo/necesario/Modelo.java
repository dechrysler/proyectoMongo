package chrysler.com.ProyectoMongo.necesario;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import chrysler.com.ProyectoMongo.base.Personaje;
import chrysler.com.ProyectoMongo.base.Pokemon;

public class Modelo {
	private  MongoClient mongoClient;
	private  MongoDatabase db;
	private Personaje po;
	public Modelo() {
		conectar();
	}
	
	@Override
	public void finalize() {
		desconectar();
	}
	
	public  void conectar() {
		
		CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		mongoClient = new MongoClient("localhost",
				MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
		db = mongoClient.getDatabase("pokemones");
	}
	
	public  void desconectar() {
		mongoClient.close();
	}
	public  void guardar(Pokemon pokemon) {
		MongoCollection<Pokemon> coleccionPokemon = db.getCollection("pokemon", Pokemon.class);
		coleccionPokemon.insertOne(pokemon);
	}
	
//	public void eliminar(Pokemon pokemon) {
//		MongoCollection<Pokemon> coleccionPokemon = db.getCollection("pokemon", Pokemon.class);
//		coleccionPokemon.deleteOne(eq("_id", pokemon.getId()));
//	}
	public void eliminarPokemon(Pokemon pokemon) {
		MongoCollection<Personaje>coleccionPersonaje = db.getCollection("personaje", Personaje.class);
		List<Personaje> person=coleccionPersonaje.find(eq("pokemones",pokemon)).into(new ArrayList<Personaje>());
		if(person.size()==0) {
			db.getCollection("pokemon").deleteOne(eq("_id", pokemon.getId()));
			return;
		}
		po = person.get(0);
		List<Pokemon>pokemones= new ArrayList<>();
		for (Pokemon pke: po.getPokemones())
			if(!pke.getNombre().equals(pokemon.getNombre()))
				pokemones.add(pke);
		po.getPokemones().clear();
		po.getPokemones().addAll(pokemones);
		modificar(po);
		db.getCollection("pokemon").deleteOne(eq("_id", pokemon.getId()));
	}
	public  ArrayList<Pokemon> getPokemones(){
		MongoCollection<Pokemon> coleccionPokemon = db.getCollection("pokemon", Pokemon.class);
		return coleccionPokemon.find().into(new ArrayList<Pokemon>());
	}
	
	public   void modificar(Pokemon pokemon) {
		MongoCollection<Pokemon> coleccionPokemon = db.getCollection("pokemon", Pokemon.class);
		coleccionPokemon.replaceOne(eq("id", pokemon.getId()), pokemon);
	}
	public void guardar(Personaje personaje) {
		MongoCollection<Personaje> coleccionPersonaje = db.getCollection("personaje", Personaje.class);
		coleccionPersonaje.insertOne(personaje);
	}
	public ArrayList<Pokemon> busquedaSimple(String tipo) {
		MongoCollection<Pokemon> coleccionPokemon = db.getCollection("pokemon", Pokemon.class);
		return coleccionPokemon.find(eq("tipo",tipo)).into(new ArrayList<Pokemon>());
	}
	public ArrayList<Pokemon> busquedaCompleja(String tipo,String numero) {
		MongoCollection<Pokemon> coleccionPokemon = db.getCollection("pokemon", Pokemon.class);
		return coleccionPokemon.find(and(eq("tipo",tipo), eq("vida",new BasicDBObject("$gt",Integer.parseInt(numero))) )).into(new ArrayList<Pokemon>());
	}
	public  ArrayList<Personaje> getPersonajes(){
		MongoCollection<Personaje> ColectionPersonaje = db.getCollection("personaje", Personaje.class);
		return ColectionPersonaje.find().into(new ArrayList<Personaje>());
	}
	public void modificar(Personaje personaje) {
		MongoCollection<Personaje> coleccionPersonaje = db.getCollection("personaje", Personaje.class);
		coleccionPersonaje.replaceOne(eq("_id", personaje.getId()), personaje);
	}
	public void eliminar(Personaje personaje) {
		MongoCollection<Personaje> CollectionPersonaje = db.getCollection("personaje",Personaje.class);
		CollectionPersonaje.deleteOne(eq("_id",personaje.getId()));
	}
	
	public List<Pokemon> pokemonesLibres() {
		List<Pokemon> pokemones = getPokemones(); 
		List<Pokemon>pokemonesLibres = new ArrayList<>();
		MongoCollection<Personaje>coleccionPersonaje = db.getCollection("personaje", Personaje.class);
		for(Pokemon pokimon : pokemones) {
		if(coleccionPersonaje.find(eq("pokemones",pokimon)).into(new ArrayList<Personaje>()).size()==0)
			pokemonesLibres.add(pokimon);
		}
		return pokemonesLibres;
	}
	public List<String> tiposPokemon(){
		List<Pokemon> pokimons= getPokemones();
		List<String> tipoPokimon = new ArrayList<>();
		for(Pokemon poke : pokimons)
			tipoPokimon.add(poke.getTipo());
		return tipoPokimon;
	}
}
