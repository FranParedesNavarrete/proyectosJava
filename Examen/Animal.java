package src.Examen;

import java.util.*;
public class Animal {
	public static ArrayList<Animal> listaAnimales = new ArrayList<Animal>();
	public static ArrayList<String> listaNombreAnimales = new ArrayList<String>();

	private String tipoAnimal;
	private String nombre;
	private int peso;
	
	public Animal(String tipoAnimal, String nombre, int peso) {
		
		this.tipoAnimal = tipoAnimal;
		this.nombre = nombre;
		this.peso = peso;

	}
	
	public String getTipoAnimal() {
		return tipoAnimal;
	}

	public void setTipoAnimal(String tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	// Segun el tipo del Animal "l" o "t" devolverá "León" para "l" y "Tigre" para "t"
	public String saberTipoAnimal(String nombre) {
		String tipo = "";
		
		int pos = comprobarAnimales(nombre);
		Animal animal = listaAnimales.get(pos);
		
		if(animal.getTipoAnimal().equals("l")) tipo = "León";
		else if (animal.getTipoAnimal().equals("t")) tipo = "Tigre";
		
		return tipo;
	}
	
	// Comprueba si el tipo del animal es un tigre "t" o un león "l" mediante un boolean
	public boolean comprobarTipo(String tipo) {
		boolean valida = true;
		
		try {
			if (!tipoAnimal.equals("l") && !tipoAnimal.equals("t")) {
				valida = false;
				throw new IllegalArgumentException();
			}
		}catch(IllegalArgumentException e) {
			System.out.println("ERROR: Esto no ni un tigre ni un leon!");
		}
		
		return valida;
	}
	
	// Comprueba la longitud del nombre, si es menor a 4 (false) y si es mayor a 4(true)
	public boolean comprobarLongitudNombre(String nombre) {
		boolean valida = true;
		
		try {
			if (nombre.length() < 4) {
				valida = false;
				throw new IllegalArgumentException();
			}
		}catch(IllegalArgumentException e) {
			System.out.println("ERROR: El nombre es muy corto");
		}
		
		return valida;
	}
	
	// Añade el nuevo Animal a la lista de animales y tambien añade su nombre a la lista de nombres
	public void anadirAnimal(Animal animal) {
		listaAnimales.add(animal);
		listaNombreAnimales.add(animal.getNombre());
	}
	
	// Recive el nombre del animal a dar de baja, comprueba que esté en la lista y si esta lo quita de la lista de nombres y de animales
	public void quitarAnimal(String nombre) {
		int pos = comprobarAnimales(nombre);
		String tipo = saberTipoAnimal(nombre);
		
		Animal animal = listaAnimales.get(pos);
		
		listaNombreAnimales.remove(pos);
		listaAnimales.remove(pos);
		
		System.out.println(tipo + " de nombre " + nombre + " dado de baja");
	}
	
	// Comprueba que el animal esté en la lista(true) o que no esté(false)
	public int comprobarAnimales(String nombre) {
		int valida = -1;
		
		if (listaNombreAnimales.contains(nombre)) {
			valida = listaNombreAnimales.indexOf(nombre);
		}
		
		return valida;
	}
	
	// Recive el nombre del animal y le asigna su nuevo peso
	public void cambiarPeso(int peso, String nombre) {
		int pos = comprobarAnimales(nombre);
		
		Animal animal = listaAnimales.get(pos);
		animal.setPeso(peso);
		
		System.out.println("Ahora los datos de " + nombre + " son: " + animal.toString());
	}
	
	// Muestra los datos del animal
	public void mostrarAnimales() {
		if (listaAnimales.isEmpty()) System.out.println("No hay ni tigres ni leones en el Zoo actualmente");
		else {
			for (Animal element : listaAnimales) {
				System.out.println(element.toString());
			}
		}
	}
	
	// Formateamos la salida de datos de la clase Animal para que sea como pide el enunciado
	@Override
	public String toString() {
		String tipo = saberTipoAnimal(nombre);
		
		return tipo + " de nombre " + getNombre() + " y peso " + getPeso() + " Kg";
	}
}
