package ArrayList_Contactos;

import java.util.*;
public class Principal {

	public static Scanner sc1 = new Scanner (System.in);
	public static ArrayList<Contacto> agenda = new ArrayList();

	// Muestra el menu de nuestra agenda
	public static int menu() {
		int option = 0;
		do {
			System.out.println("1. Ver contactos");
			System.out.println("2. Añadir contacto");
			System.out.println("3. Eliminar contacto");
			System.out.println("4. Buscar contacto");
			System.out.println("5. Salir");
			option = sc1.nextInt();
			sc1.nextLine(); // Limpia el buffer
		} while (option <= 0 || option > 5);
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
			System.out.println("Saliendo del programa...");
			return 5;
		}	
		return 0;
	}
	
	public static void main(String[] args) {
		do {
		} while (opcion(menu()) != 5);
		
	}

}
