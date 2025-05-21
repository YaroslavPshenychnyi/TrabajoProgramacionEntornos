package utils;

import java.util.Scanner;

public class Metodos {
	public static int cogerRondas(Scanner sc) {
		System.out.print("¿Cuántas rondas quieres jugar? ");
		int rondas = sc.nextInt();
		sc.nextLine();
		return rondas;

	}

	public static String cogerNombre(Scanner sc) {
		System.out.print("Introduce tu nombre: ");
		return sc.nextLine();
	}

	public static int cogerClase(Scanner sc) {
		System.out.println("Elige tu clase:\n" + "1. Mago\n" + "2. Guerrero");
		System.out.print("Elige (1, 2): ");

		int clase = sc.nextInt();
		sc.nextLine();
		return clase;
	}

	public static int cogerAccion(Scanner sc) {
		System.out.println("Acciones:\n" + "1. Atacar\n" + "2. Curar\n" + "3. Guardar partida\n");
		System.out.print("Elige: ");
		int accion = sc.nextInt();
		sc.nextLine();
		return accion;
	}
}
