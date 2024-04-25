package Herencia_Netflix;

/**
 * Esta clase es la clase objeto Serie.
 * @author Fran Paredes
 */
public class Serie extends Contenido{
	private int ntemporadas;
	private boolean finalizada;
	
	/**Constructor que requiere de un título, productora, año de publicación, número de temporadas y si está finalizada o no para crear un objeto Serie*/
	public Serie(String titulo, String productora, int anyoPublicacion, int ntemporadas, boolean finalizada) {
		super(titulo, productora, anyoPublicacion);
		this.ntemporadas = ntemporadas;
		this.finalizada = finalizada;
	}
	public int getNtemporadas() {
		return ntemporadas;
	}
	public void setNtemporadas(int ntemporadas) {
		this.ntemporadas = ntemporadas;
	}
	public boolean isFinalizada() {
		return finalizada;
	}
	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}
	
	
}
