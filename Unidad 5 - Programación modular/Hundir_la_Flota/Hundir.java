package Hundir_la_Flota;

import java.util.*;
public class Hundir {

	public static Scanner sc1 = new Scanner (System.in);
	public static int option = 0;
	public static int trets = 0;
	public static int contAigua = 0;
	public static int contTocat = 0;
	public static char[][] tauler = new char[11][11];
	public static char[][] taulerVacio = new char[11][11];
	public static int llanxa = 1;
	public static int vaixell = 3;
	public static int cuirassat = 4;
	public static int portaavions = 5;
	
	public static void menu() {
		do {
			System.out.println("===== BENVINGUTS A AFONAR LA FLOTA =====");
			System.out.printf("\nNivells de dificultat: \n");
			System.out.println("1. Fácil: 5 llanxes, 3 vaixells, 1 cuirassat i 1 portaavions (50 trets)");
			System.out.println("2. Mitja: 2 llanxes, 1 vaixell, 1 cuirassat i 1 portaavions (30 trets)");
			System.out.println("3. Difícil: 1 llanxa i 1 vaixell (10 trets)");
			System.out.println("Quin nivell tries?");
			option = sc1.nextInt();
			sc1.nextLine();
			option = demana_dades_entre_max_i_min(option);
		} while (option == 0);
	}
	
	public static int demana_dades_entre_max_i_min(int option) {
		if (option > 3 || option < 0) {
			System.out.println("Introduzca una opción válida");
			return 0;
		}
		return option;
	}
	
	public static void crear_tauler() {
		for (int i = 0; i < tauler.length; i++) {
			for (int j = 0; j < tauler.length; j++) {
				tauler[i][j] = '-'; 	
			}
		}
		
		tauler[0][0] = ' ';
		tauler[1][0] = 'A';
		tauler[2][0] = 'B';
		tauler[3][0] = 'C';
		tauler[4][0] = 'D';
		tauler[5][0] = 'E';
		tauler[6][0] = 'F';
		tauler[7][0] = 'G';
		tauler[8][0] = 'H';
		tauler[9][0] = 'I';
		tauler[10][0] = 'J';
		tauler[0][1] = '0';
		tauler[0][2] = '1';
		tauler[0][3] = '2';
		tauler[0][4] = '3';
		tauler[0][5] = '4';
		tauler[0][6] = '5';
		tauler[0][7] = '6';
		tauler[0][8] = '7';
		tauler[0][9] = '8';
		tauler[0][10] = '9';
	}
	
	public static void mostrar_tauler() {
		if (trets == 0) {
			System.out.println();
			for (int i = 0; i < tauler.length; i++) {
				for (int j = 0; j < tauler.length; j++) {
					System.out.printf("%s",tauler[i][j]);
				}
				System.out.println();
			}
		}
	}
	
	public static void crear_taulerVacio() {
		for (int i = 0; i < taulerVacio.length; i++) {
			for (int j = 0; j < taulerVacio.length; j++) {
				taulerVacio[i][j] = '-'; 	
			}
		}
		
		taulerVacio[0][0] = ' ';
		taulerVacio[1][0] = 'A';
		taulerVacio[2][0] = 'B';
		taulerVacio[3][0] = 'C';
		taulerVacio[4][0] = 'D';
		taulerVacio[5][0] = 'E';
		taulerVacio[6][0] = 'F';
		taulerVacio[7][0] = 'G';
		taulerVacio[8][0] = 'H';
		taulerVacio[9][0] = 'I';
		taulerVacio[10][0] = 'J';
		taulerVacio[0][1] = '0';
		taulerVacio[0][2] = '1';
		taulerVacio[0][3] = '2';
		taulerVacio[0][4] = '3';
		taulerVacio[0][5] = '4';
		taulerVacio[0][6] = '5';
		taulerVacio[0][7] = '6';
		taulerVacio[0][8] = '7';
		taulerVacio[0][9] = '8';
		taulerVacio[0][10] = '9';
		
	}
	
	public static void mostrar_taulerVacio() {
		
		System.out.println();
		for (int i = 0; i < taulerVacio.length; i++) {
			for (int j = 0; j < taulerVacio.length; j++) {
				System.out.printf("%s",taulerVacio[i][j]);
			}
			System.out.println();
		}
	}
	
	public static int es_pot_inserir_barcos(int tipo, int cont, int max, char A) {
		int a = (int) (Math.random() * (max - 1) + 1);
		int b = (int) (Math.random() * (max - 1) + 1);

		switch(tipo) {
		case 1:
			if (tauler[a][b] == '-') {
				tauler[a][b] = A;
				cont++;
			}
			
			break;
		case 3:
			if (tauler[a][b - 1] == '-' && tauler[a][b] == '-' && tauler[a][b + 1] == '-' && tauler[a][b + 2] == '-') {
				tauler[a][b] = A;
				tauler[a][b + 1] = A;
				tauler[a][b + 2] = A;
				cont++;
			}
			
			break;
		case 4:
			if (tauler[a][b - 1] == '-' && tauler[a][b] == '-' && tauler[a][b + 1] == '-' && tauler[a][b + 2] == '-' && tauler[a][b + 3] == '-') {
				tauler[a][b] = A;
				tauler[a][b + 1] = A;
				tauler[a][b + 2] = A;
				tauler[a][b + 3] = A;
				cont++;
			}
			
			break;
		case 5:
			if (tauler[a][b - 1] == '-' && tauler[a][b] == '-' && tauler[a][b + 1] == '-' && tauler[a][b + 2] == '-' && tauler[a][b + 3] == '-' && tauler[a][b + 4] == '-') {
				tauler[a][b] = A;
				tauler[a][b + 1] = A;
				tauler[a][b + 2] = A;
				tauler[a][b + 3] = A;
				tauler[a][b + 4] = A;
				cont++;
			}
			
			break;
		}

		return cont;
	}
	
	public static void inserir_barcos(int option) {
		int cont = 0;
		
		switch(option) {
		case 1: // 5 llanxes, 3 vaixells, 1 cuirassat i 1 portaavions
			trets = 50;
			do {
				cont = es_pot_inserir_barcos(portaavions, cont, 6, 'P');
			} while(cont < 1);
			cont = 0;
			do {
				cont = es_pot_inserir_barcos(cuirassat, cont, 7, 'Z');
			}while(cont < 1);
			cont = 0;
			do {
				cont = es_pot_inserir_barcos(vaixell, cont, 8, 'B');
			} while(cont < 3);
			cont = 0;
			do {
				cont = es_pot_inserir_barcos(llanxa, cont, 11, 'L');
			} while(cont < 5);
		
			break;
		case 2: // 2 llanxes, 1 vaixells, 1 cuirassat i 1 portaavions
			trets = 30;
			do {
				cont = es_pot_inserir_barcos(portaavions, cont, 6, 'P');
			} while(cont < 1);
			cont = 0;
			do {
				cont = es_pot_inserir_barcos(cuirassat, cont, 7, 'Z');
			}while(cont < 1);
			cont = 0;
			do {
				cont = es_pot_inserir_barcos(vaixell, cont, 8, 'B');
			} while(cont < 1);
			cont = 0;
			do {
				cont = es_pot_inserir_barcos(llanxa, cont, 11, 'L');
			} while(cont < 2);
			
			break;
		case 3: // 1 llanxa i 1 vaixell
			trets = 10;
			do {
				cont = es_pot_inserir_barcos(vaixell, cont, 8, 'B');
			} while(cont < 1);
			cont = 0;
			do {
				cont = es_pot_inserir_barcos(llanxa, cont, 11, 'L');
			} while(cont < 1);
			
			break;
		}
	}

	public static int[] demana_coordenades() {
		int[] coordenada =  new int[2];
		boolean verificacion = false;
		
		do {
			System.out.println("Itroduzca la coordenada (ej: 1 , 4): ");
			int coorX = sc1.nextInt();
			String coma = sc1.next();
			int coorY = sc1.nextInt();
			if (coorX >= 0 && coorX < 10 && coorY >= 0 && coorY < 10) {
				coordenada[0] = coorX + 1;
				coordenada[1] = coorY + 1;
				verificacion = true;
			} else System.out.println("Intrododuzca una coordenada válida (ej: 2 , 6)");
		} while(!verificacion);
		
		return coordenada;
	}
	
	public static void processa_tret(int[] coordenada) {
		int X = coordenada[0], Y = coordenada[1];
		
		if (tauler[X][Y] == '*') {
			System.out.println("Error! Ja hi havies fet un tret aci");
			trets++;
		}
		else {
			if (tauler[X][Y] == '-') {
				System.out.println("Aigua!");
				tauler[X][Y] = '*';
				taulerVacio[X][Y] = '*';
				contAigua++;
			}
			else  {
				System.out.println("Tocat!");
				tauler[X][Y] = '*';
				taulerVacio[X][Y] = '*';
				contTocat++;
			}
		}
	}

	public static void mostrar_resultado() {
		System.out.println("Has tocado " + contTocat + " barcos");
		System.out.println("Has tocado agua " + contAigua + " veces");
		if (contTocat == 0) System.out.println("Has acertado 0 veces. Cojo");
	}
	
	public static void jugar_partida(int option) {
		int[] coordenada = new int[2];
		
		crear_tauler();
		crear_taulerVacio();
		mostrar_tauler();
		inserir_barcos(option);
		do {
			coordenada = demana_coordenades();
			processa_tret(coordenada);
			mostrar_taulerVacio();
			trets--;
		} while(trets > 0);
		mostrar_tauler();
		mostrar_resultado();
	}
	
	public static void main(String[] args) {
		menu();
		jugar_partida(option);
	}

}
