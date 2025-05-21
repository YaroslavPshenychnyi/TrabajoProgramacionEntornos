package programa;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import classes.Juego;

public class Programa {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		File guardado = new File("save.txt");
		if (!guardado.exists()) {
			try {
				guardado.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileInputStream escriba = null;
		ObjectInputStream imprenta = null;
		char continuar = 0;

		do {
			Juego juego = null;
			System.out.print("Quieres cargar partida?[s/n]: ");
			char opcion = sc.nextLine().toLowerCase().charAt(0);
			if (opcion == 'n') {
				juego = new Juego();
			} else if (opcion == 's') {
				try {
					escriba = new FileInputStream(guardado);
					imprenta = new ObjectInputStream(escriba);
					juego = (Juego) imprenta.readObject();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						imprenta.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			juego.jugar(sc);
			System.out.print("¿Volver a jugar?(s/n) ");
			continuar = sc.nextLine().toLowerCase().charAt(0);
		} while (continuar == 's');
	}

}
