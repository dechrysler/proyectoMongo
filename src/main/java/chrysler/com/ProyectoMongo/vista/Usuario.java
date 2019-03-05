package chrysler.com.ProyectoMongo.vista;

import org.bson.types.ObjectId;

public class Usuario {
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
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	private ObjectId id;
	private String nombre;
	private String contrasena;
	public  Usuario(String nombre,String contrasena) {
		this.nombre = nombre;
		this.contrasena = contrasena;
	}
	public  Usuario() {
	
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Usuario))
			return false;
		
		Usuario propietario = (Usuario) obj;
		if (nombre.equals(propietario.getNombre()) && contrasena.equals(propietario.getContrasena()))
			return true;
		
		return false;
}
	
	
}
