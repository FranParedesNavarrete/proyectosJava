package Herencia_Netflix;

import java.util.*;

/**
 * Clase principal del proyecto.
 * Contiene los métodos:
 * {@link Principal#menu(int, boolean)}
 * {@link Principal#checkAnyo(boolean)}
 * {@link Principal#altaPelicula(boolean)}
 * {@link Principal#altaSerie(boolean)}
 * {@link Principal#verContenido()}
 * {@link Principal#listarcontenido()}
 * {@link Principal#listarPendiente()}
 * @author Fran Paredes
 */
public class Principal {

	public static Scanner sc = new Scanner(System.in); 
	
	/**HashMap que contiene las series y películas que damos de alta y tiene como clave el título del contenido.*/
	public static HashMap <String, Contenido> Catalogo = new HashMap <String, Contenido>(); 
	
	/**ArrayList que guarda los títulos de las series y películas.*/
	public static ArrayList <String> titulos = new ArrayList<String>();
	
	/**
	 * Método que muestra menú del proyecto.
	 * Recoge la opción del usuario y la pide en bucle hasta que sea una opción válida.
	 * @param option Este parámetro es el número que el usuario introducirá para escoger una opción. 
	 * @param check Este parámetro es un booleano que será falso hasta que la opción introducida sea válida.
	 * @return Una opción válida.
	 */
	public static int menu(int option, boolean check) {
		String errorAlert = "ERROR: Introduzca una opción entre el 1 al 6";
		
		System.out.println("1 - Dar de alta una película \n"
				+ "2 - Dar de alta una serie \n"
				+ "3 - Ver un contenido \n"
				+ "4 - Listar contenido \n"
				+ "5 - Listar contenido pendiente por ver \n"
				+ "6 - Salir");	
		
		do{	
			try {
				option = sc.nextInt();
				sc.nextLine();
					
				if (option > 0 && option < 7) check = true;
				else throw new IllegalArgumentException();
			}catch(InputMismatchException e) {
				System.out.println(errorAlert);
				sc.nextLine();
			}catch(IllegalArgumentException e) {
				System.out.println(errorAlert);
			}
			
			return option;
		} while (!check);
	}
	
	/**
	 * Método que recoge el año introducido por el usuario y lo pide en bucle hasta que sea un año válido.
	 * @param check Este parámetro es un booleano que será falso hasta que la opción introducida sea válida.
	 * @return Un año válido.
	 */
	public static int checkAnyo(boolean check) {
		int anyo = 0;
		
		do {
			try {
				System.out.println("Introduzca el año de publicación:");
				anyo = sc.nextInt();
				
				if(anyo > 0) check = true;
				else throw new IllegalArgumentException();
			}catch (InputMismatchException e) {
				System.out.println("ERROR: Introduzca un año válido");
				sc.nextLine();
			}catch (IllegalArgumentException e) {
				System.out.println("ERROR: No se puede introducir un año negativo");
			}
		}while(!check);
		
		return anyo;
	}
	
	/**
	 * Método que pide los datos necesarios para crear una nueva película.
	 * Se encarga de válidar el número de nominaciones en los Oscars y de que el número de Oscars ganados no sea mayor al número de nominaciones.
	 * Crea una película a partir del título, productora, num de nominaciones a los Oscars y Oscars ganados introducidos por el usuario.
	 * Hace uso de {@link Principal#checkAnyo(boolean)}
	 * Añade la película a {@link Principal#Catalogo} con el título como key.
	 * Añade el título a {@link Principal#titulos}.
	 * @param check Este parámetro es un booleano que será falso hasta que la opción introducida sea válida.
	 */
	public static void altaPelicula(boolean check) {
		int oscarsGanados = 0, nomOscars = 0;
		
		System.out.println("Introduzca el título de la película:");
		String titulo = sc.nextLine();
		
		System.out.println("Introduzca la productora de la película:");
		String productora = sc.nextLine();
		
		int anyo = checkAnyo(check);
		
		do {
			try {
				System.out.println("Número de nominaciones a los Oscars:");
				nomOscars = sc.nextInt();
				
				if(nomOscars > 0) check = true;
				else throw new IllegalArgumentException();
			}catch (InputMismatchException e) {
				System.out.println("ERROR: Introduzca un número de nominaciones válido");
				sc.nextLine();
			}catch (IllegalArgumentException e) {
				System.out.println("ERROR: No pueden haber nominaciones negativas");
			}
		}while(!check);
		
		check = false; // Vuelve a ser false para que la validación se haga bien
		
		do {
			try {
				System.out.println("Número de Oscars ganados:");
				oscarsGanados = sc.nextInt();
				
				if (oscarsGanados < nomOscars && oscarsGanados >= 0) check = true;
				else if (oscarsGanados > nomOscars) throw new IllegalArgumentException();
			}catch(InputMismatchException e) {
				System.out.println("ERROR: Introuzca un valor válido");
				sc.nextLine();
			}catch(IllegalArgumentException e) {
				System.out.println("ERROR: No se pueden tener más Oscars ganados que nominaciones hechas");
			}
		}while (!check);

		Pelicula newPelicula = new Pelicula(titulo, productora, anyo, nomOscars, oscarsGanados);  
		
		Catalogo.put(titulo, newPelicula); 
		titulos.add(titulo); 
		
		System.out.println("La película " + titulo + " ha sido dada de alta correctamente!");
	}
	
	/**
	 * Método que pide los datos necesarios para crear una nueva serie.
	 * Se encarga de válidar el número de temporadas.
	 * Pregunta si la serie está finalizada o no.
	 * Crea una serie a partir del título, productora, num de temporadas y si está finalizada o no.
	 * Hace uso de {@link Principal#checkAnyo(boolean)}
	 * Añade la serie a {@link Principal#Catalogo} con el título como key.
	 * Añade el título a {@link Principal#titulos}.
	 * @param check Este parámetro es un booleano que será falso hasta que la opción introducida sea válida.
	 */
	public static void altaSerie(boolean check) {
		int ntemporadas = 0; 
		boolean finalizada = false;
		
		System.out.println("Introduzca el título de la serie:");
		String titulo = sc.nextLine();
		
		System.out.println("Introduzca la productora de la serie:");
		String productora = sc.nextLine();
		
		int anyo = checkAnyo(check);
		
		do {
			try {
				System.out.println("Introduzca el número de temporadas:");
				ntemporadas = sc.nextInt();
				sc.nextLine();
				
				if (ntemporadas > 0) check = true;
				else throw new IllegalArgumentException();
			}catch(InputMismatchException e) {
				System.out.println("ERROR: Introuzca un valor válido");
				sc.nextLine();
			}catch(IllegalArgumentException e) {
				System.out.printf("ERROR: %s debe tener mínimo 1 temporada \n", titulo);
			}
		}while(!check);
		
		check = false; // Vuelve a ser false para que la validación se haga bien
		
		do {
			try {
				System.out.printf("¿La serie %s está finalizada o no? (S/N) \n", titulo);
				String estadoEmision = sc.nextLine();
			
				if (!(estadoEmision.equalsIgnoreCase("s") || estadoEmision.equalsIgnoreCase("n"))) throw new IllegalArgumentException();
				else if(estadoEmision.equalsIgnoreCase("s")) {
					finalizada = true;
					check = true;
				}
				else if (estadoEmision.equalsIgnoreCase("n")) {
					finalizada = false;
					check = true;
				}
			}catch(IllegalArgumentException e) {
				System.out.println("ERROR: Introduzca 'S' (si está finalizada) o 'N' (si no está finalizada)");
			}
		}while(!check);
		
		Serie newSerie = new Serie(titulo, productora, anyo, ntemporadas, finalizada);
		
		Catalogo.put(titulo, newSerie); 
		titulos.add(titulo); 
		
		System.out.println("La serie " + titulo + " ha sido dada de alta correctamente!");
	}
	
	/**
	 * Método que muestra el contenido y lo marca como visto.
	 * Se encarga de válidar que el título introducido esté dentro de {@link Principal#Catalogo}.
	 * Hace uso de {@link Contenido#setVisto()}.
	 */
	public static void verContenido() {
		System.out.println("Introduzca el titulo del contenido que quiere visualizar:");
		String titulo = sc.nextLine();
		
		if (Catalogo.isEmpty()) System.out.println("El catálogo de Netflix está vacio");
		else if (Catalogo.get(titulo) == null) System.out.println("El contenido " + titulo + " no existe en Netflix");
		else {
			Contenido contenido = Catalogo.get(titulo);
			contenido.setVisto();
			
			System.out.printf("El contenido %s marcado como visto! \n", titulo);
		}
	}
	
	/**
	 * Método que muestra todo el contenido dentro de {@link Principal#Catalogo}.
	 * Hace uso de {@link Contenido#toString()}.
	 */
	public static void listarcontenido() {
		for (int i = 0; i < titulos.size(); i++) {
			String titulo = titulos.get(i); // Guarda el título del contenido en la variable 'titulo' para usarla como key en Catalogo.get()
			System.out.println(Catalogo.get(titulo));
		}
	}
	
	/**
	 * Método que muestra todo el contenido pendiente de ver de {@link Principal#Catalogo}.
	 * Válida que {@link Principal#Catalogo} no esté vacio y si lo está muestra una mensaje indicandolo.
	 * Si todo el contenido está visto muestra un mensaje indicandolo.
	 * Hace uso de {@link Contenido#toString()}.
	 */
	public static void listarPendiente() {
		int cont = 0; // Variable que sirve para saber si el catálogo está visto(0). 
		
		if (Catalogo.isEmpty()) System.out.println("El catálogo de Netflix está vacio actualmente");
		else {
			for (int i = 0; i < titulos.size(); i++) {
				String titulo = titulos.get(i); // Guarda el título del contenido en la variable 'titulo' para usarla como key en Catalogo.get()
				
				Contenido contenido = Catalogo.get(titulo); // Guarda el contenido para luego válidar que no esté visto
				
				if (!contenido.getVisto()) { // Válida que no esté visto
					System.out.println(Catalogo.get(titulo));
					cont++;
				}
			}
			if (cont == 0) System.out.println("Ya has visto todo el contenido del catálogo de Netflix");
		}
	}
	
	/**
	 * Método principal.
	 * Muestra {@link Principal#menu(int, boolean)} mientras no se introduzca el número 6.
	 */
	public static void main(String[] args) {
		int option = 0;
		boolean check = false;
		
		// Muestra el menú mientras el usuario no introduzca '6'
		do {
			option = menu(option, check);
			
			switch(option) {
			case 1:
				altaPelicula(check);
				break;
			case 2:
				altaSerie(check);
				break;
			case 3:
				verContenido();
				break;
			case 4:
				listarcontenido();
				break;
			case 5:
				listarPendiente();
				break;
			case 6:
				System.out.println("Gracias por utilizar Netflix");
				break;	
			}
		}while (option != 6);
	}
}
