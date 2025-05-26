package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import classes.Juego;

public class MetodosGuardado {

	
	
	public static void archivoSiempreExiste(File guardado) {
		if (!guardado.exists()) {
			try {
				guardado.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Juego juegoCargado(Scanner sc, File guardado) {
		Juego juego = null;
		char opcion = 0;
		opcion = quieresCargar(sc, opcion);
		if (opcion == 'n') {
			juego = new Juego();
			juego.setCargado(false);
			return juego;
		} else if (opcion == 's') {
			try {
				if (cargarPartida(guardado) != null) {
					System.out.println("Cargamos partida");
					juego = cargarPartida(guardado);
					juego.setCargado(true);
					return juego;
				} else {
					System.out.println("\nNo hay datos guardados\n");
					juego = new Juego();
					juego.setCargado(false);
					return juego;
				}
			} catch (IOException | ClassNotFoundException e) {

				System.out.println("Error de lectura del archivo de guardado");
				return null;
			}
		} else {
			System.out.println("Opcion no encotrada, creamos partida");
			juego = new Juego();
			juego.setCargado(false);
			return juego;
		}
	}

	public static char quieresCargar(Scanner sc, char opcion) {
		do {
			System.out.print("Quieres cargar partida?[s/n]: ");
			try {
				opcion = sc.nextLine().toLowerCase().charAt(0);
			} catch (StringIndexOutOfBoundsException e) {
				System.out.print("No has escrito nada, pulsa 'Enter'");
			}
		} while (!Character.isAlphabetic(opcion));
		return opcion;
	}

	public static Juego cargarPartida(File guardado) throws ClassNotFoundException, IOException {
		Juego juego = null;
		FileInputStream escriba = null;
		ObjectInputStream imprenta = null;

		if (guardado.length() > 0) {
			escriba = new FileInputStream(guardado);
			imprenta = new ObjectInputStream(escriba);
			juego = (Juego) imprenta.readObject();
			imprenta.close();
			escriba.close();
			return juego;
		}
		return null;

	}

	public static void guardarPartida(Juego juego) throws IOException {
		FileOutputStream escriba = null;
		ObjectOutputStream editor = null;

		escriba = new FileOutputStream("save.txt");
		editor = new ObjectOutputStream(escriba);
		editor.writeObject(juego);
		editor.close();
		escriba.close();
	}
}
