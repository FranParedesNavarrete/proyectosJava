package MiniTerminal;

import java.io.*;
import java.sql.Date;
import java.util.Arrays;
public class MiniFileManager {

	public static File fileActual = new File("");
	public static String rutaActual = fileActual.getAbsolutePath(); 

	// Constructor de la clase MiniFileManager, guarda la ruta del directorio actual en 'fileActual'
	public MiniFileManager() {
		fileActual = new File(rutaActual);		
	}

	// Devuelve la ruta del directorio actual
	public static String getPWD() {
		return rutaActual;
	}
	
	/* 
	 * Verifica que el directorio introducido 'dir' sea válido
	 * Devuelve false si la ruta introducida no existe o no es un directorio
	 * Si el usuario introduce '..' la ruta cambia a la carpeta superior y devuelve true
	 * Si se introduce una ruta válida 'fileActual' pasará a ser esa ruta y devuelve true
	*/
	public static boolean changeDIR(String dir) {
		File ubicacion = new File(rutaActual + File.separator + dir); 
		
		if(dir.equals("..")) {
			ubicacion = fileActual.getParentFile();
			if(ubicacion != null) {
				fileActual = ubicacion;
				rutaActual = fileActual.getAbsolutePath();
				
				return true;
			} else return false;
		}
		if(ubicacion.exists() && ubicacion.isDirectory()) {
			fileActual = ubicacion;
			rutaActual = fileActual.getAbsolutePath();
			
			return true;
		} else return false;
	}
	
	/*
	 * Muestra el nombre, fecha de modificación y el tamaño de los archivos de la ruta actual
	 * Si info es true solo muestra el nombre de las carpetas y archivos
	 * Si info es false:
	 * Para las carpetas solo mostrará el nombre y su fecha de última modificación
	 * Para los archivos mostrá el nombre, fecha de modificación y el tamaño del archivo en Kilobytes 'Kb'
	 */
	public static void printList(boolean info) {
		File[] listaFiles = fileActual.listFiles();
		
		Arrays.sort(listaFiles);
		
		if (!info) {
			System.out.println("Nombre \n"
					+ "-------------------------");
			
			for(File file : listaFiles) {
				if (file.isDirectory()) System.out.println(file.getName());
			}
			for(File file : listaFiles) {
				if (file.isFile()) System.out.println(file.getName());
			}
		}else {
			System.out.println("Nombre \t \t \t \t \t \t Fecha de modificación \t Tamaño \n"
					+ "----------------------------------------------------------------------------------");
			
			for(File file : listaFiles) {
				long milisegundos = file.lastModified();
				Date fecha = new Date(milisegundos);
				
				if (file.isDirectory()) System.out.println(file.getName() + " \t \t \t \t \t " + fecha);
			}
			for(File file : listaFiles) {
				long milisegundos = file.lastModified();
				Date fecha = new Date(milisegundos);
				
				if (file.isFile()) System.out.println(file.getName() + " \t \t \t \t \t " + fecha + " \t \t " + (double)file.length() / 1000 + "Kb");
			}
		}
	}
	
	// Crea una carpeta nueva con el nombre 'dir' en la ruta actual, si no se puede muestra un mensaje
	public static void mkDir(String dir) {
		File newFile = new File(rutaActual + File.separator + dir);
		
		if (!newFile.mkdir()) System.out.printf("ERROR: No se ha podido crear el directorio, introduzca un nombre válido \n", dir);
	}
	
	/*
	 *  Revisa 'rutaArchivo'
	 *  Si es una ruta absoluta crea file usandola como ruta
	 *  Si es una ruta relativa crea 'file' añadiendole 'rutaActual' y 'File.separator' (dependiendo del SO será '/' o '\\')
	*/
	public static File checkFile(String rutaArchivo) {
		File file = new File("");
		
		if(rutaArchivo.contains("/")) file = new File(rutaArchivo);
		else file = new File(rutaActual + File.separator + rutaArchivo);
		
		return file;
	}
	
	/*
	 * Elimina la carpeta 'dir'
	 * Si dir no es una ruta absoluta se le añade 'rutaActual' y '\\' + 'dir' para actualizar 'file'
	 * Si no se puede eliminar 'dir' porque no existe se mostrará un mensaje
	 * Si no se puede borrar porque contiene subcarpetas se eliminaran los archivos y se mostrará un mensaje informando de las subcarpetas
	 */
	public static void removeFile(String dir) {
		File file = checkFile(dir);
		
		if(!file.delete()) {
			if(!file.exists()) System.out.printf("ERROR: No se ha encontrado '%s' \n", dir);
			else {
				File[] listaFiles = file.listFiles();
				
				for(File element : listaFiles){
					if(element.isFile()) element.delete();
				}
				
				if(!file.delete()) {
					System.out.println("ALERT: El directorio contiene subcarpetas");
				}
			}
		}
	}
	
	// Renombra 'rutaOrigen' a 'rutaDestino'
	public static void renameFile(String dirOrigen, String dirDestino) {
		File rutaOrigen = checkFile(dirOrigen);
		File rutaDestino = checkFile(dirDestino);
		
		if (!rutaOrigen.renameTo(rutaDestino)) System.out.println("ERROR: No se ha podido hacer");;
	}
	
	// Muestra todos los comandos disponibles e información indicando su función 
	public static void help() {
		System.out.printf("Comando \t \t Función \n"
				+ "-------------------------------------------------------------------------------------------------- \n"
				+ "pwd \t \t \t: Muestra cual es la carpeta actual \n"
				+ "cd <DIR> \t \t: Cambia la carpeta actual a ‘DIR’. Con '..' cambia a la carpeta superior \n"
				+ "ls \t \t \t: Muestra la lista de directorios y archivos de la carpeta actual \n"
				+ "ll \t \t \t: Como ls pero muestra tambi'en el tamaño y la fecha de 'ultima modificación \n"
				+ "mkdir <DIR> \t \t: Crea el directorio 'DIR' en la carpeta actual \n"
				+ "rm <FILE> \t \t: Borra 'FILE'. Si es una carpeta, borrar'a primero sus archivos y luego la carpeta \n"
				+ "mv <FILE1><FILE2> \t: Mueve o renombra 'FILE1' a 'FILE2' \n"
				+ "help \t \t \t: Muestra una breve ayuda con los comandos disponibles \n"
				+ "exit \t \t \t: Termina el programa \n");
	}
}
