package CestaDeCompra;

import java.util.*;
import java.io.*;
public class main {

	public static Scanner sc = new Scanner(System.in);
	public static ArrayList <Producto> cesta = new ArrayList <Producto>();
	
	// Muestra las opciones disponibles y verifica que la opción sea válida
	public static int menu() {
		int option;
		do {
			System.out.printf("1. Añadir producto \n"
					+ "2. Eliminar producto \n"
					+ "3. Mostrar cesta de la compra \n"
					+ "4. Guardar cesta \n"
					+ "5. Recuperar cesta \n"
					+ "6. Salir \n");
			System.out.println("Seleccione una opción");
			option = sc.nextInt();
			sc.nextLine();
		}while(option < 0 && option > 7);
		
		return option;
	}
	
	/*
	 * Pide los datos necesarios al usuario para crear un producto
	 * Pregunta si el producto tiene fecha de caducidad y si la tiene pide la fecha
	 * Al final crea el producto con los datos introducidos y guarda el producto en la ArrayList 'cesta'
	 */
	public static void addProducto() {
		boolean boo = false;
		
		System.out.println("Introduzca el nombre del producto:");
		String nombreProducto = sc.nextLine();
		
		System.out.println("Introduzca el precio de " + nombreProducto);
		double precio = sc.nextDouble();
		sc.nextLine();
		
		do {
			System.out.println("¿El producto es imperecedero? Introduzca 'S'(si) o 'N'(no)");
			String imperecedero = sc.nextLine();
			
			if (imperecedero.equals("S") || imperecedero.equals("N") || imperecedero.equals("s") || imperecedero.equals("n")) {
				if (imperecedero.equals("S") || imperecedero.equals("s")) {
					System.out.println("Introduzca la fecha de caducidad");
					String fecha = sc.nextLine();
					
					Producto p = new ProductoPerecedero(nombreProducto, precio, fecha);
					cesta.add(p);
					
					boo = true;	
				}
				if (imperecedero.equals("N") || imperecedero.equals("n")) {
					Producto p = new Producto(nombreProducto, precio);
					cesta.add(p);
					
					boo = true;
				}
			} else System.out.println("ERROR: Introduzca 'S' si el producto tiene fecha de caducidad o 'N' si no la tiene");
			
			System.out.println("Producto añadido a la cesta correctamente!");
			
		}while(!boo);
	}
	
	// Revisa el  estado de la ArrayList 'cesta' y si esta vacia devuelve true
	public static boolean cestaVacia() {
		if (cesta.isEmpty()) {
			System.out.println("La cesta está vacia");
			return true;
		} else return false;
	}

	/*
	 * Llama al método 'cestaBacia' para comprobar su estado
	 * Si no está vacia muestra 'cesta' y pide al usuario el índice del producto a borrar
	 */
	public static void eliminarProducto() {
		boolean boo = false;
		
		if (!cestaVacia()) {
			do {
				System.out.println("Introduzca el índice del producto a eliminar:");
				mostrarCesta();
				int indice = sc.nextInt();
				
				if (indice > cesta.size()) System.out.println("ERROR: Introduzca un índice válido");
				else {
					cesta.remove(indice - 1);
					System.out.println("Producto eliminado con exito!");
					boo = true;
				}
			}while (!boo);
			
		}
	}
	
	// Muestra 'cesta' indicando el índice de cada producto
	public static void mostrarCesta() {
		int indice = 0;
		
		if (!cestaVacia()) {
			for(Producto producto : cesta) {
				indice++;
				System.out.println("Indice: " + indice + " | " + producto.toString());
			}
		}
	}
	
	// Guarda la cesta actual con el nombre introducido por el usuario
	public static void guardarCesta() {
		System.out.println("Introduzca el nombre que desea ponerle a la cesta");
		String nombreCesta = sc.nextLine();
		
		if (!nombreCesta.contains(".txt")) nombreCesta = nombreCesta + ".txt";
		
		try {
			File archivo = new File(nombreCesta);
			FileOutputStream archivoSalida = new FileOutputStream(archivo);
			ObjectOutputStream objetoSalida = new ObjectOutputStream(archivoSalida);
			
			for(Producto producto : cesta) {
				objetoSalida.writeObject(producto);
			}
			
			objetoSalida.close();
			
		}catch(FileNotFoundException e) {
			System.out.println("No se ha podido encontrar el archivo");
		}catch(IOException e) {
			System.out.println("Error de entrada salida");
		}
	}
	
	// Pide al usuario el nombre de la cesta que quiere recuperar y si existe la recupera
	public static void recuperarCesta() {
		cesta.clear();
		
		System.out.println("Introduzca el nombre de la cesta:");
		String nombreCesta = sc.nextLine();
		
		if (!nombreCesta.contains(".txt")) nombreCesta = nombreCesta + ".txt";
		
		try {
			File archivo = new File(nombreCesta);
			FileInputStream archivoEntrada = new FileInputStream(archivo);
			ObjectInputStream objetoEntrada = new ObjectInputStream(archivoEntrada);
			
			boolean boo = false;
			
			while(!boo){
				try {
					Producto productoDeserializado = (Producto) objetoEntrada.readObject();
					cesta.add(productoDeserializado);
				}catch(EOFException e) {
					boo = true;
				}
			}
			
			objetoEntrada.close();
			
		}catch(ClassNotFoundException e) {
			System.out.println("ERROR: No se ha podido encontrar el objeto");
		}catch(FileNotFoundException e) {
			System.out.println("ERROR: No se ha podido encontrar el archivo");
		}catch(IOException e) {
			System.out.println("Error de entrada salida");
		}
		
		System.out.println("Cesta recupera con exito!");
	}
	
	public static void main(String[] args) {
		int option = 0;
		
		do {
			option = menu();
			
			switch (option) {
			case 1:
				addProducto();
				break;
			case 2: 
				eliminarProducto();
				break;
			case 3:
				mostrarCesta();
				break;
			case 4:
				guardarCesta();
				break;
			case 5:
				recuperarCesta();
				break;
			case 6:
				System.out.println("Gracias por usar la cesta de la compra.");
				break;
			default:
				System.out.println("ERROR: Introduzca una opción válida");
				break;
			}
		}while(option != 6);
	}

}
