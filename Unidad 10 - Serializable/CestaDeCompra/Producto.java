package CestaDeCompra;

import java.io.Serializable;
public class Producto implements Consultable, Serializable {

	protected String nombre;
	 protected double precio;
	 
	 public Producto(String nombre, double precio) {
		 this.nombre = nombre;
		 this.precio = precio;
	 }
	 
	 @Override
	 public String toString() {
		 return "Producto: " + this.nombre + " | Precio: " + this.precio;
	 }
	 
	 public void mostrarProducto() {
		 System.out.println(this.toString());
	 }
}
