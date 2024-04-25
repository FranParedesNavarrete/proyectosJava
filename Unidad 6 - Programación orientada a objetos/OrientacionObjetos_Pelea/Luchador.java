package OrientacionObjetos_Pelea;

public class Luchador {
	
	private String nombre;
	private int vida;
	private int roundsGanados;

	public Luchador(String nombre) {
		this.nombre = nombre;
		this.vida = 100;
		this.roundsGanados = 0;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}
	
	public int getRoundsGanados() {
		return roundsGanados;
	}
	
	public void setRoundsGanados(int roundsGanados) {
		this.roundsGanados = roundsGanados;
	}

	public int quitarVida() {
		return (int)((Math.random()* 50) + 1);
	}
	
	// Reduce la vida del luchador restandole x a la vida actual e impide que la vida sea negativa
	public void reducirVida(int x) {
		setVida(getVida() - x);
		if (getVida() < 0) setVida(0);
	}
}
