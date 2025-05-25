package programa;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import classes.Juego;
import utils.Metodos;
import utils.MetodosGuardado;

public class Programa {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Juego juego = new Juego();
		File guardado = new File("save.txt");
		MetodosGuardado.archivoSiempreExiste(guardado);
		char continuar = 0;
		do {
			juego = MetodosGuardado.juegoCargado(sc, guardado);
			try {
				if (juego.jugar(sc, juego.isCargado()) == true) {
					continuar = Metodos.seguirJugando(sc);
				} else {
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (continuar == 's');
	}

}
