package MiniTerminal;

import java.util.*;
import java.io.*;
public class MiniTerminal {

	public static MiniFileManager miniTerminal = new MiniFileManager();
	public static String dir = "";
	public static String fileOrigen = "";
	public static String fileDestino = "";
	
	// Recoge el comando del usuario para separar el comando del directorio, guarda el directorio en 'dir' y los archivos en 'fileOrigen' y 'fileDestino'
	public static String checkCommand(String command) {
		if (command.startsWith("cd") && command.length() >= 4) {
			dir = command.substring(3);
			command = "cd";
		}
		if (command.startsWith("mkdir") && command.length() >= 6) {
			dir = command.substring(6);
			command = "mkdir";
		} 
		if (command.startsWith("rm") && command.length() >= 4) {
			dir = command.substring(3);
			command = "rm";
		} 
		if (command.startsWith("mv") && command.length() >= 4) {
			int longRuta = command.lastIndexOf(" ");
			
			fileOrigen = command.substring(3, (longRuta));
			fileDestino = command.substring(longRuta + 1);
			command = "mv";
		}
		
		return command;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String command = "";
		
		File fileActual = new File(miniTerminal.getPWD());
		
		// muestra la ubicación actual y recoge el comando que introduce el usuario hasta que se introduzca 'exit'
		do {
			System.out.printf(miniTerminal.getPWD() + ": ");

			command = checkCommand(command = sc.nextLine());
			
			switch(command) {
			case "pwd":
				System.out.println(miniTerminal.getPWD());
				break;
			case "cd":
				/* 
				 * Si el método 'changeDIR' devuelve false significa que ocurrió un error y muestra un mensaje
				 * Si devuelve true significa que no han habido errores y no mostrará nada, pero si se hará el cambio de ruta
				 */
				if (!miniTerminal.changeDIR(dir)) System.out.println("ERROR: No se encuentra la ruta de acceso '" + dir + "' porque no existe");
				break;
			case "ls":
				miniTerminal.printList(false);
				break;
			case "ll":
				miniTerminal.printList(true);
				break;
			case "mkdir":
				miniTerminal.mkDir(dir);
				break;
			case "rm":
				miniTerminal.removeFile(dir);
				break;
			case "mv":
				miniTerminal.renameFile(fileOrigen, fileDestino);
				break;
			case "help":
				miniTerminal.help();
				break;
			case "exit":
				System.out.println("Saliendo ...");
				break;
			default:
				System.out.println("ERROR: Consulte la lista de comandos usando 'help'");
			}
		}while(!command.equals("exit"));
	}
}
