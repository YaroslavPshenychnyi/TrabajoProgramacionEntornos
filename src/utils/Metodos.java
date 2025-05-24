package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import classes.Juego;

public class Metodos {
	public static int cogerRondas(Scanner sc) {
		boolean valido = false;
		int rondas = 0;
		do {
			System.out.print("\n¿Cuántas rondas quieres jugar? ");

			try {
				rondas = sc.nextInt();
				valido = true;
			} catch (InputMismatchException e) {
				System.out.println("Por favor, introduce un numero válido");
			} finally {
				sc.nextLine();
			}
		} while (!valido);
		return rondas;

	}

	public static String cogerNombre(Scanner sc) {
		String nombre = "";
		while (nombre.isEmpty()) {
			System.out.print("Introduce tu nombre: ");
			nombre = sc.nextLine();
		}
		return nombre;
	}

	public static int cogerClase(Scanner sc) {

		int clase = 0;
		do {
			System.out.println("Elige tu clase:\n" + "1. Mago\n" + "2. Guerrero");
			System.out.print("Elige (1, 2): ");
			try {
				clase = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println();
			} finally {
				sc.nextLine();
			}
		} while (clase != 1 && clase != 2);
		return clase;
	}

	public static int cogerAccion(Scanner sc) {
		int accion = 0;

		try {
			System.out.println("Acciones:\n" + "1. Atacar\n" + "2. Curar\n" + "3. Guardar partida y salir\n");
			System.out.print("Elige: ");
			accion = sc.nextInt();
		} catch (InputMismatchException e) {
		} finally {
			sc.nextLine();
		}
		return accion;
	}


	public static char seguirJugando(Scanner sc) {
		char continuar;
		System.out.print("¿Volver a jugar?(s/n) ");
		continuar = sc.nextLine().toLowerCase().charAt(0);
		return continuar;
	}

}
