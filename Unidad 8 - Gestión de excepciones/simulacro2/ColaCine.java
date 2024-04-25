package src.simulacro2;

import java.util.*;
public class ColaCine {

	private Queue<Cliente> cola;
	
	ColaCine(){
		cola = new LinkedList<>();
	}

	// Añade un nuevo cliente a la cola
	public void encolarCliente(Cliente cliente) {
		cola.offer(cliente);
	}
	
	// Quita de la cola el cliente que este en primera posici'on, si no hay clientes en la cola muestra un mensaje
	public void desencolarCliente() {
		if (cola.isEmpty()) System.out.println("La cola esta vacia");
		else cola.poll();
	}
	
	// Muestra los clientes(nombre y edad) que estan dentro de la cola y muestra un mensaje si la cola esta vacia
	public void mostrarCola() {
		if (cola.isEmpty()) System.out.println("La cola esta vacia");
		else {
			for(Cliente element : cola) {
				System.out.println(element.getNombre() + ", " + element.getEdad());
			}
		}
	}
	
}
