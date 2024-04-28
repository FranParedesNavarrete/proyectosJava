package CestaDeCompra;

public class ProductoPerecedero extends Producto{
	protected String fecha;
	
	public ProductoPerecedero(String nombre, double precio, String fecha) {
		super(nombre, precio);
		this.fecha = fecha;
	}
	
	@Override
	public String toString() {
		return "Producto: " + this.nombre + " | Precio: " + this.precio + " | Fecha caducidad: " + this.fecha;
	}
}
