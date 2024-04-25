package Herencia_Netflix;
/**
 * Esta clase es la clase objeto Contenido.
 * Hace de clase padre para {@link Serie} y {@link Pelicula}
 * Contiene el método sobreescrito {@link Contenido#toString()} para formatear la sálida.
 * @author juaparnav
 */
 
public class Contenido {
	protected String titulo;
	protected String productora;
	protected int anyoPublicacion;
	private boolean visto;
	
	/**Constructor que requiere de un título, productora y año de publicación para crear una clase Contenido*/
	public Contenido(String titulo, String productora, int anyoPublicacion) {
		this.titulo = titulo;
		this.productora = productora;
		this.anyoPublicacion = anyoPublicacion;
		this.visto = false;
	}
	
	public void setVisto() {
		visto = true;
	}
	
	public boolean getVisto() {
		return visto;
	}

	@Override
	public String toString() {
		return titulo + "|" + productora + "|" + anyoPublicacion + "|" + visto; 
	}
}
