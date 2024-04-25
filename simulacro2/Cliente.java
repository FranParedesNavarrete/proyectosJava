package src.simulacro2;

// Se crea la clase cliente que necesitar'a de un nombre(String) y una edad(int) para poder construirse
public class Cliente {
	private String nombre;
	private int edad;
	
	public Cliente(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	
}
