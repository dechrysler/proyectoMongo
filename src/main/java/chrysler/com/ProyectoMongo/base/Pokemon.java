package chrysler.com.ProyectoMongo.base;

import org.bson.types.ObjectId;

public class Pokemon {	
	private ObjectId id;
	private String nombre;
	private String tipo;
	private float danio;
	private float vida;
	private Personaje personaje;
	public Pokemon() {
		
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public float getDanio() {
		return danio;
	}
	public void setDanio(float danio) {
		this.danio = danio;
	}
	public float getVida() {
		return vida;
	}
	public void setVida(float vida) {
		this.vida = vida;
	}
	public Personaje getPersonaje() {
		return personaje;
	}
	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}
	@Override
	public String toString() {
		return nombre;
	}
	
}
