package CifradoCesar;

import java.util.*;
import java.io.*;
public class cifradoCesar {
	
	public static boolean checkPassword = true;

	/* 
	 * Método que recibe un String y un boolean para comprobar que cumpla los siguientes requisitos:
	 * Que no contenga ni 'ñ' ni vocales acentuadas
	 * En el caso de el nombre de usuario y la contraseña debe tener mínimo 5 caracteres y maximo 15, para esto sirve el boolean 'checkPassword'
	 * En el caso de la frase debe tener más de 15 caracteres
	 * Si el boolena 'checkPassword' es true se revisará que 'text' tebga mínimo 5 caracteres y máximo 15, si esta en false comprobará que 'text' tenga mínimo 15 caracteres
	 * Si alguno de estos requisitos no se cumple lanza una excepción y se para el programa
	*/
	public static void check(String text, boolean boo) {
		char[] acentos = {'ñ', 'á', 'é', 'í', 'ó', 'ú', 'Ñ', 'Á', 'É', 'Í', 'Ó', 'Ú'};
		
		for(char caracter : acentos) {
			if(text.indexOf(caracter) == -1) {
				throw new IllegalArgumentException();	
			}
		}
		
		if (boo) {
			if (text.length() < 5 || text.length() > 15) throw new IndexOutOfBoundsException(); 
		}
		else {
			if (text.length() < 15) {
				checkPassword = false;
				throw new IndexOutOfBoundsException(); 
			}
		}
	}

	/*
	 *  Método que recibre la entrada del usuario y el número de posiciones que queremos para el Cifrado César
	 * 	El bucle recorre 'entrada' y convierte sus caracteres a códgio ASCII
	 *  Guarda los caractares ya cifrados (se le suma 5 a su código ASCII) en el array 'palabraCifrada
	 *  Devuelve la palabra ya cifrada sustituyendo los '%' por " "
	 *  Se sustituyem los '%' porque el " " en código ASCII es 32 y despues del cifrado queda en 37 que al pasarlo a caracter es '%'
	 */
	public static String cifrarEntrada(String entrada, int num) {
		char[] palabraCifrada = new char[entrada.length()];

		String palabra = "";
		
		for (int i = 0; i < entrada.length(); i ++) {
			char caracter = entrada.charAt(i);
			int ASCII = (int) caracter; // Convierte el caracter en su código ASCII
			int max = 0;
			
			// Calcula el máximo al que puede llegar en código ASCII
			if (ASCII > 64 && ASCII < 91) {
				max = 90 - num;
			}else if (ASCII > 96 && ASCII < 123) {
				max = 122 - num;
			}
			
			/*
			 * Estas condiciones sirven para separar mayúsculas de minúsculas
			 * El primer if recoge las mayúsculas y suma 5 si su código ASCII es menor a 97, si es mayor tambien suma 5, pero cuando el ASCII supera el 90 sigue sumando desde 65
			 * El segundo if recoge las minúsculas y suma 5 si su código ASCII es menor a 118, si es mayor tambien lo suma, pero cuando el ASCII supera el 122 sigue sumando desde 97
			 */
			if (ASCII > 64 && !(ASCII < max) && ASCII < 97) {
				ASCII = ((ASCII - 65) + num) %26 + 65;
			}else if (ASCII > 96 && !(ASCII < max)) {
				ASCII = ((ASCII - 97) + num) %26 + 97;
			}else ASCII = ASCII + num;
			
			caracter = (char) ASCII; // Convierte el int 'ASCII' en un caracter
			palabraCifrada[i] = caracter; // Guarda 'caracter', que ya esta cifrado con el método César, en el array 'palabraCifrada'
		}

		palabra = new String (palabraCifrada); // Convierte la array de char 'palabraCifrada' en una cadena de texto
		
		return palabra.replace('%', ' '); 
	}
	
	/*
	 * Método main, se propaga una excepción para parar el programa en caso de que el usuario introduzca un campo inválido
	 */
	public static void main(String[] args) throws IllegalArgumentException{
		Scanner sc = new Scanner(System.in);
		
		File destino = new File("user_info.txt");
		String user = "", password = "", frase = "";

		try {	
			System.out.println("Introduce un nombre de usuario (entre 5 y 15 caracteres):");
			check(user = sc.nextLine(), true);
	
			System.out.println("Introduce una contraseña (entre 5 y 15 caracteres):");
			check(password = sc.nextLine(), true);
		
			System.out.println("Introduce una frase de recordatorio de la contraseña (más de 15 caracteres):");
			check(frase = sc.nextLine(), false);
			
			try {
				FileWriter escritura = new FileWriter(destino);
				
				escritura.write(cifrarEntrada(user, 5) + "\n" + cifrarEntrada(password, 5) + "\n" + cifrarEntrada(frase, 5) + "\n");
				
				escritura.close();

				System.out.println("La información se ha guardado en 'user_info.txt' de forma cifrada");
			}catch(IOException e) {
				System.out.println("ERROR: No se ha podido escribir");
			}
			
		} catch(IllegalArgumentException e) {
			System.out.println("ERROR: No se pueden introducir 'ñ' o vocales con acentos");
		} catch(IndexOutOfBoundsException e) {
			if (checkPassword) System.out.println("ERROR: Introduzca mínimo entre 5 y 15 caracteres");
			else System.out.println("ERROR: Introduzca más de 15 caracteres");
		}
	}
}
