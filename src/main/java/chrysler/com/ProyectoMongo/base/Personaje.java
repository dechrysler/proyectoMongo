package chrysler.com.ProyectoMongo.base;

import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;

import chrysler.com.ProyectoMongo.necesario.Modelo;

public class Personaje {
	private ObjectId id;
	private String nombre;
	private String region_procedencia;
	private float edad;
	private Set<Pokemon> pokemones;
	public Personaje() {
		pokemones = new HashSet<Pokemon>();
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRegion_procedencia() {
		return region_procedencia;
	}
	public void setRegion_procedencia(String region_procedencia) {
		this.region_procedencia = region_procedencia;
	}
	public float getEdad() {
		return edad;
	}
	public void setEdad(float edad) {
		this.edad = edad;
	}
	
	public Set<Pokemon> getPokemones() {
		return pokemones;
	}
	public void setPokemones(Set<Pokemon> pokemones) {
		this.pokemones = pokemones;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
}
