package ArrayList_Contactos;

public class Contacto implements Comparable{

	private String nombre;
	private String telefono;
	private String email;
	private int edad;
	
	// Constructor para el objeto Contacto
	public Contacto(String nombre, String telefono, String email, int edad) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.edad = edad;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public void mostrarDatos() {
		System.out.printf("Nombre %s, Telefono %s, Email %s, Edad %d \n", nombre, telefono, email, edad);
	}

	public int compareTo(Object o) {

		Contacto c = (Contacto) o; // Casteamos el Objeto o para que ahora sea el Contacto c
				
		return this.edad-c.edad;
	}
	
	
	

}
