package src.simulacro2;

import java.util.*;
public class Principal {

	public static Scanner sc = new Scanner(System.in);
	public static int salas; // Guarda el n'umero de salas disponibles
	
	// Muestra el men'u por pantalla y pide un int para elegir la opcion del men'u
	public static int menu() {		
		System.out.println("***********KINEPOLIS***********");
		System.out.println("1. Encolar cliente en una sala \n"
				+ "2. Desencolar cliente de una sala \n"
				+ "3. Estado de la cola \n"
				+ "4. Salir");
		int opcion = sc.nextInt();
		sc.nextLine(); // Limpiar el buffer
		
		return opcion;
	}
	
	// Comprueba que la sala introducida por el usuario este dentro del n'umero de salas disponibles(true) o sea una sala invalida(false)
	public static int checkSalas() {
		int sala = 0;
		boolean valida = false; 
		
		do {
			try {
				System.out.println("Sala sobre la que realizar la operación");
				sala = sc.nextInt();
				sc.nextLine();

				if (sala > 0 && sala <= salas) {
					valida = true;
				}else throw new IllegalArgumentException(); // Salta la excepci'on si la sala introducida es negativa o esta no existe
			}catch (IllegalArgumentException e) {
				System.out.println("Introduzca una sala del 1 al " + salas);
			}catch (InputMismatchException e) { // Si se introduce un valor distinto a un int salta la excepci'on
				System.out.println("Solo se pueden introducir n'umeros enteros");
				sc.nextLine();
			}
		}while(!valida);	
		return sala;
	}
	
	// Invoca el metodo checkSalas para comprobar la sala y despues pide los datos del cliente para introducirlos a la cola de la sala elegida
	public static void encolar(ColaCine[] colaSala) {
		int sala = checkSalas(), edad = 0;
		boolean valida = false;
		
		System.out.println("Nombre del cliente en la sala " + sala);
		String nombre = sc.nextLine();
		
		do {
			try {
				System.out.println("Edad del cliente a encolar en la sala " + sala);
				edad = sc.nextInt();
				sc.nextLine();
				if (edad > 0) {
					valida = true;
				}else throw new IllegalArgumentException();
			} catch(IllegalArgumentException e) {
				System.out.println("No se puede introducir una edad negativa"); // Salta la excepci'on si la edad introducida es negativa
			} catch(InputMismatchException e) {
				System.out.println("Solo se puede introducir numeros enteros"); // Si se introduce un valor distinto a un int salta la excepci'on
				sc.nextLine();
			}
		}while (!valida);
		
		sala = sala - 1; // Se resta uno para no superar el tamaño de la array
		
		Cliente nuevoCliente = new Cliente(nombre, edad); // Creamos el cliente con el nombre y la edad ya verificada
		colaSala[sala].encolarCliente(nuevoCliente); // Añadimos el cliente que acabamos de crear a la cola de la sala 'sala'

		}
	
	// Quita de la cola al cliente que este en primera posici'on de la colaSala
	public static void desencolar(ColaCine[] colaSala) {
		int sala = checkSalas();
		
		sala = sala - 1; // Se resta uno para ajustar la entrada del usuario al index del array
		
		colaSala[sala].desencolarCliente();
	}
		
	// Muestra el nombre y la edad de lso clientes que esten en la cola de la sala seleccionada
	public static void mostrarCola(ColaCine[] colaSala) {
		int sala = checkSalas();
		
		sala = sala - 1; // Se resta uno para ajustar la entrada del usuario al index del array
		
		colaSala[sala].mostrarCola();
	}
	
	public static void main(String[] args) {
		int opcion = 4;
		boolean valida = false;
		
		do {
			try {
				System.out.println("¿Cuantas salas tiene el cine?"); // Se pide el n'umero de salas para establecer el n'umero de salas disponibles en el cine
				salas = sc.nextInt();
				sc.nextLine();
				
				if (salas > 0) {
					valida = true;
				}else throw new IllegalArgumentException();
			}catch(IllegalArgumentException e) {
				System.out.println("El n'umero no puede ser negativo"); // Salta la excepci'on si el valor es negativo
			}catch(InputMismatchException e) {
				System.out.println("Solo se pueden introducir n'umeros enteros"); // Si se introduce un valor distinto a un int salta la excepci'on
				sc.nextLine();
			}
		}while(!valida);
		
		ColaCine[] colaSala = new ColaCine[salas]; // Se crea una nueva array de colas del tamaño de las salas disponibles 
		for(int i = 0; i < salas; i++) {
			colaSala[i] = new ColaCine(); 
		}
		
		do {
		    try {
		        opcion = menu();
		        if (opcion < 0 || opcion > 4) throw new IllegalArgumentException();

		        switch (opcion) {
		            case 1:
		                encolar(colaSala);
		                break;
		            case 2:
		            	desencolar(colaSala);
		            	break;
		            case 3:
		            	mostrarCola(colaSala);
		            	break;
		            case 4:
		            	System.out.println("Saliendo del programa...");
		        }
		    } catch (IllegalArgumentException e) {
		        System.out.println("Introduzca una opción válida");
		    }
		}while (opcion != 4);
		
		
	}

}
