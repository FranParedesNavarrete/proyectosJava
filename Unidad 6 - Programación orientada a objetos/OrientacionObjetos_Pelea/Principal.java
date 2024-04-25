package OrientacionObjetos_Pelea;

public class Principal {

	public static void main(String[] args) {

		Luchador luch1 = new Luchador("Ryu");
		Luchador luch2 = new Luchador("Ken");
		Luchador luch3 = new Luchador("Mr Bison");
		Escenario esc1 = new Escenario("El templo de pekin");
		Escenario esc2 = new Escenario("Bison Mansion");
		Luchador ganador1 = esc1.pelea(esc1, luch1, luch2);
		esc1.delay();
		esc1.fraseGanador(ganador1, luch1, luch2);
		Luchador ganador2 = esc2.pelea(esc2, ganador1, luch3);
		esc2.delay();
		esc2.fraseGanador(ganador2, ganador1, luch3);
	}

}
