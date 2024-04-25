package Herencia_Netflix;

/**
 * Esta clase es la clase objeto Pelicula.
 * @author Fran Paredes
 */
public class Pelicula extends Contenido{
	private int oscars_nominados;
	private int oscars_ganados;
	
	/**Constructor que requiere de un título, productora, año de publicación, número de nominaciones a los Oscars y número de Oscars ganados para crear un objeto Pelicula*/
	public Pelicula(String titulo, String productora, int anyoPublicacion, int oscars_nominados, int oscars_ganados) {
		super(titulo, productora, anyoPublicacion);
		this.oscars_nominados = oscars_nominados;
		this.oscars_ganados = oscars_ganados;
	}
	public int getOscars_nominados() {
		return oscars_nominados;
	}
	public void setOscars_nominados(int oscars_nominados) {
		this.oscars_nominados = oscars_nominados;
	}
	public int getOscars_ganados() {
		return oscars_ganados;
	}
	public void setOscars_ganados(int oscars_ganados) {
		this.oscars_ganados = oscars_ganados;
	}
	
	
}
