package OrientacionObjetos_Pelea;

public class Escenario {

	private String nombre;
	
	public Escenario(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	// Reestablece la vida de los Luchadores
	public void reinicioVida(Luchador luch1, Luchador luch2) {
		luch1.setVida(100);
		luch2.setVida(100);
	}
	
	// Restablece los rounds ganados de los luchadores
	public void reinicioRoundsGanados(Luchador luch1, Luchador luch2) {
		luch1.setRoundsGanados(0);
		luch2.setRoundsGanados(0);
	}
	
	// Retrasa 1 segundo la salida por pantalla
	public void delay() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Verifica si el luchador está con vida y si está vivo golpea
	public void golpe(Luchador luch1, Luchador luch2) {
		int da = 0;
		
		delay();
		
		if (luch1.getVida() != 0) {
			da = luch1.quitarVida();
			luch2.reducirVida(da);
			System.out.printf("%s golpea a %s con una intensidad de %d ", luch1.getNombre(), luch2.getNombre(), da);
			System.out.printf("\t %s:%d - %s:%d \n", luch1.getNombre(), luch1.getVida(), luch2.getNombre(), luch2.getVida());
		} 
		
		delay();
		
		if (luch2.getVida() != 0) {
			da = luch2.quitarVida();
			luch1.reducirVida(da);
			System.out.printf("%s golpea a %s con una intensidad de %d ", luch2.getNombre(), luch1.getNombre(), da);
			System.out.printf("\t %s:%d - %s:%d \n", luch1.getNombre(), luch1.getVida(), luch2.getNombre(), luch2.getVida());
		}
	}
	
	// Cuenta los rounds ganados de cada luchador y muestra el ganador del round por pantalla
	public Luchador ganadorRound (Luchador ganador, Luchador luch1, Luchador luch2) {
		if (luch1.getVida() == 0) {
			ganador = luch2;
			luch2.setRoundsGanados(luch2.getRoundsGanados() + 1);
		} 
		if (luch2.getVida() == 0) {
			ganador = luch1;
			luch1.setRoundsGanados(luch1.getRoundsGanados() + 1);
		}
		
		System.out.println("El ganador de este round es: " + ganador.getNombre());
		
		return ganador;
	}
	
	// Verifica el ganador de la pelea y lo muestra por pantalla
	public Luchador ganadorPelea (Luchador ganador, Luchador luch1, Luchador luch2) {
		if (luch1.getRoundsGanados() == 2) ganador = luch1;
		if (luch2.getRoundsGanados() == 2) ganador = luch2;
		
		System.out.println("El ganador de la pelea ha sido: " + ganador.getNombre());		
		
		return ganador;
	}
	
	// Muestra por pantalla una frase del ganador de la pelea mediante un número aleatorio entre 6 y 1
	public void fraseGanador(Luchador ganador,Luchador luch1, Luchador luch2) {
		int num = (int)((Math.random()* 6) + 1);
		switch(num){
		case 1 :
			System.out.printf("%s: %s pensaba que eras más duro, que decepción\n \n",luch1.getNombre(), luch2.getNombre());
			break;
		case 2 :
			System.out.printf("%s: ¿Eso fue todo? Creí que ibamos a pelear, no a hacer yoga \n \n", ganador.getNombre() );
			break;
		case 3 :
			System.out.printf("%s: ¿Estabas peleando o solo calentando? \n \n", ganador.getNombre() );
			break;
		case 4:
			System.out.printf("%s: Creí que pelearías, no que te rendirías \n \n", ganador.getNombre() );
			break;
		case 5 :
			System.out.printf("%s: Sencillito y pal lobby GG \n \n", ganador.getNombre() );
			break;
		case 6 :
			System.out.printf("%s: Sabía que iba a ser fácil, pero esto es casi aburrido %s \n \n", ganador.getNombre(), luch2.getNombre());
			break;
		}
	}
	
	// Programa principal de la pelea
	public Luchador pelea (Escenario escenario, Luchador luch1, Luchador luch2) {
		Luchador ganador = new Luchador("");
		int round = 1;
		
		reinicioRoundsGanados(luch1, luch2);
		
		System.out.printf("Comienza la pelea entre %s contra %s en %s \n", luch1.getNombre(), luch2.getNombre(), escenario.getNombre());
		
		do {	
			reinicioVida(luch1, luch2);
			delay();
			
			System.out.printf("ROUND %d....FIGHT!!!! \n", round);
			
			while(luch1.getVida() > 0 && luch2.getVida() > 0) {
				golpe(luch1, luch2);
			}
			
			ganador = ganadorRound(ganador, luch1, luch2);
			
			System.out.println();
			
			round++;
		} while (round < 3 || luch1.getRoundsGanados() < 2 && luch2.getRoundsGanados() < 2);
		
		return ganadorPelea(ganador, luch1, luch2);
	}
}
