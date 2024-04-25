package Serializacion;

import java.util.*;
import java.io.*;

public class AgendaSerializada {
	public static Scanner sc1 = new Scanner (System.in);
	public static ArrayList<Contacto> agenda = new ArrayList<>();
	
	public static int menu() {
	int option = 0;
	do {
		System.out.println("1. Ver contactos");
		System.out.println("2. Añadir contacto");
		System.out.println("3. Eliminar contacto");
		System.out.println("4. Buscar contacto");
		System.out.println("5. Guardar agenda en disco");
		System.out.println("6. Recuperar agenda de disco");
		System.out.println("7. Salir");
		option = sc1.nextInt();
		sc1.nextLine(); // Limpia el buffer
	} while (option <= 0 || option > 7);
	return option;
}

public static void mostrarContactos() {
	int i = 0;
	if (agenda.isEmpty()) System.out.println("No hay contactos");
	else for (Contacto elemento : agenda) {
		System.out.printf(i + ". ");
		elemento.mostrarDatos();
		i++;
	}
	System.out.println();
}

public static String checkFile(String file) {
	if (file.contains(".txt")) return file;
	else return file + ".txt";
}

public static void guardarContactos() {
	System.out.printf("Introduzca el nombre para la agenda:");
	String archivoNombre = sc1.nextLine();
	
	File archivo = new File (checkFile(archivoNombre)); 
	
	try {
		FileOutputStream archivoSalida = new FileOutputStream(archivo);
		ObjectOutputStream objetoSalida = new ObjectOutputStream(archivoSalida);
		
		for (Contacto contacto : agenda) {
			objetoSalida.writeObject(contacto);
		}
		
		objetoSalida.close();
		
		System.out.println("Agenda guardada");
	} catch(IOException e){
		e.printStackTrace();
	}
}

public static void recuperarContactos() {
	System.out.printf("Introduzca el nombre de la agenda a recuperar:");
	String archivo = sc1.nextLine();

	archivo = checkFile(archivo); 
	
	boolean boo = true;

	agenda.clear();
	
	try {
		FileInputStream archivoEntrada = new FileInputStream(archivo);
		ObjectInputStream objetoEntrada = new ObjectInputStream(archivoEntrada);
		
		while(boo) {
			Contacto contactoDeserializado = (Contacto) objetoEntrada.readObject();
		
			agenda.add(contactoDeserializado);
		}
		
		objetoEntrada.close();
	}catch (ClassNotFoundException e) {
		System.out.println("No se ha podido encontrar el archivo");
	}catch(EOFException e) {
		boo = false;
		System.out.printf("Agenda %s recuperada con exito \n", archivo);
	}catch(IOException e) {
		System.out.println("Error de entrada salida");
	}
}

// Ejecutara las opciones de nuestro menu
public static int opcion(int option) {
	switch (option) {
	case 1:
		mostrarContactos();
		break;
	case 2:
		System.out.println("Introduzca el nombre");
		String nombre1 = sc1.nextLine();
		System.out.println("Introduzca el telefono");
		String telefono1 = sc1.nextLine();
		System.out.println("Introduzca el email");
		String email1 = sc1.nextLine();
		System.out.println("Introduzca la edad");
		int edad1 = sc1.nextInt();
		sc1.nextLine();
		
		agenda.add(new Contacto (nombre1, telefono1 , email1, edad1));
		Collections.sort(agenda);
		break;
	case 3:
		mostrarContactos();
		if (!agenda.isEmpty()) {
			System.out.println("Introduzca el id del contacto a borrar");
			int id = sc1.nextInt();
			sc1.nextLine();
			if (id >= 0 && id < agenda.size()) {
				agenda.remove(id); 
			}
			else System.out.println("No existe el contacto");
		}
		break;
	case 4:
		if (agenda.isEmpty()) System.out.println("No hay contactos");
		else {
			System.out.println("Introduzca el dato del contacto a buscar");
			String nombre = sc1.nextLine();
			
			boolean o = false;
			
			for (Contacto elemento : agenda) {
				if (elemento.getNombre().equalsIgnoreCase(nombre) || elemento.getTelefono().equalsIgnoreCase(nombre) || elemento.getEmail().equalsIgnoreCase(nombre)) {
					elemento.mostrarDatos(); 
					o = true;
				}
			}
			if (o == false) System.out.println("No existe el contacto");
			
		}
		break;
	case 5:
		guardarContactos();
		break;
	case 6:
		recuperarContactos();
		break;
	case 7:
		System.out.println("Saliendo del programa...");
		return 7;
	}	
	return 0;
}
	
	public static void main(String[] args) {
		do {
		} while (opcion(menu()) != 7);
	}

}

class Contacto implements Comparable, Serializable{
	private static final long serialVersionUID = -7548739626093448246L;
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