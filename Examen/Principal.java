package src.Examen;

import java.util.*;
public class Principal {

	public static Scanner sc= new Scanner(System.in);
	public static Animal nuevoAnimal = new Animal("", "", 0);
	
	// Menú que válida la opcion del usuario y devuelve la opción ya validada
	public static int menu(int opcion, boolean valida) {
		do {
			try {
				System.out.println("Bienvenido al Zoo Tigres & Leones \n"
						+ "---------------------------------- \n"
						+ "1 - Dar de alta animal \n"
						+ "2 - Dar de baja animal \n"
						+ "3 - Modificar peso \n"
						+ "4 - Listado animales \n"
						+ "5 - Salir");
				opcion = sc.nextInt();
				sc.nextLine();
				
				if (opcion > 0 && opcion < 6) valida = true;
				else throw new IllegalArgumentException();
			}catch(InputMismatchException e) {
				System.out.println("ERROR: Opción incorrecta");
				sc.nextLine();
			}catch (IllegalArgumentException e) {
				System.out.println("ERROR: Opción incorrecta");
			}
		}while(!valida);
		
		return opcion;
	}
	
	// Pide al usuario el tipo, nombre peso del animal a dar de alta, comprueba sus campos y lo añade a la lista de animales del Zoo
	public static boolean darAltaAnimal(boolean valida) {
		int pesoAnimal;
		
		System.out.println("Introduzca el tipo de animal (l - leon, t- tigre):");
		String tipoAnimal = sc.nextLine();
		
		nuevoAnimal.setTipoAnimal(tipoAnimal);
		
		if (valida = nuevoAnimal.comprobarTipo(tipoAnimal) == false) return valida = false; // Si es false devuelve al usuario al menú
		
		System.out.println("Introduzca el nombre del animal (mínimo 4 letras!):");
		String nombreAnimal = sc.nextLine();
		
		nuevoAnimal.setNombre(nombreAnimal);
		
		if (valida = nuevoAnimal.comprobarLongitudNombre(nombreAnimal) == false) return valida = false; // Si es false devuelve al usuario al menú
		
		try {
			System.out.println("Introduzca el peso del animal");
			pesoAnimal = sc.nextInt();
			
			if (pesoAnimal < 0) throw new IllegalArgumentException();
		}catch(InputMismatchException e) {
			System.out.println("ERROR: Debes ingresar valores enteros");
			sc.nextLine();
			return valida = false; // Si se tira la excepción devuelve al usuario al menú
		}catch (IllegalArgumentException e) {
			System.out.println("ERROR: Debes ingresar valores enteros");
			return valida = false; // Si se tira la excepción devuelve al usuario al menú
		}
		
		Animal nuevoAnimal = new Animal(tipoAnimal, nombreAnimal, pesoAnimal);
		
		nuevoAnimal.anadirAnimal(nuevoAnimal);
		
		return valida;
	}
	
	// Pide el nombre del animal al usuario y despues de validar si está o no lo quita de la lista o devuelve false
	public static boolean darBajaAnimal(boolean valida) {
		System.out.println("Introduzca el nombre del animal para dar de baja");
		String nombre = sc.nextLine();
		
		if (nuevoAnimal.comprobarAnimales(nombre) == -1) {
			System.out.println("ERROR: No existe un animal que se llame: " + nombre);
			return valida = false;
		}
		
		nuevoAnimal.quitarAnimal(nombre);
		
		return valida;
	}
	
	// Pide el nombre del animal a modificar el peso y comprueba que esté en el listado del Zoo, si no está tira una excepción y si si está pide el nuevo peso y lo válida para luego cambiar el peso
	public static boolean modificarPeso(boolean valida) {
		int pesoAnimal = 0;
		
		System.out.println("Introduzca el nombre del animal a cambiar el peso");
		String nombre = sc.nextLine();
		
		if (nuevoAnimal.comprobarAnimales(nombre) == -1) {
			System.out.println("ERROR: No existe un animal que se llame: " + nombre);
			return valida = false;
		}
		
		try {
			System.out.println("Introduzca el nuevo peso del animal " + nombre);
			pesoAnimal = sc.nextInt();
			sc.nextLine();
			
			if (pesoAnimal < 0) throw new IllegalArgumentException();
		}catch(InputMismatchException e) {
			System.out.println("ERROR: Debes ingresar valores enteros");
			sc.nextLine();
			return valida = false;
		}catch (IllegalArgumentException e) {
			System.out.println("ERROR: Debes ingresar valores enteros");
			return valida = false;
		}
		
		nuevoAnimal.cambiarPeso(pesoAnimal, nombre);
		
		return valida;
	}
	
	// Mustra el listado de animales que hay en el Zoo
	public static void mostrarListaAnimales() {
		nuevoAnimal.mostrarAnimales();
	}
	
	public static void main(String[] args) {
		int opcion = 0;
		boolean valida = false;
		
		do {
			do {
				opcion = menu(opcion, valida);
				
				switch(opcion) {
					case 1:
						darAltaAnimal(valida);
						break;
					case 2:
						darBajaAnimal(valida);
						break;
					case 3: 
						modificarPeso(valida);
						break;
					case 4:
						mostrarListaAnimales();
						break;
					case 5:
						System.out.println("Gracias por usar el sistema.");
						valida = true;
						break;
				}
			}while (!valida);
		}while (opcion != 5);
	}

}
