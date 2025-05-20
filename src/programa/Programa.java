package programa;

import java.util.Scanner;
import classes.Juego;

public class Programa {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		char continuar = 0;

		do {
			Juego juego = new Juego();
			juego.jugar(sc);
			System.out.print("¿Volver a jugar?(s/n) ");
			continuar = sc.nextLine().toLowerCase().charAt(0);
		} while (continuar == 's');
	}

}
