package utils;

import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class MetodosTest {

	@Test
	void cogerRondasTest() {
		Scanner sc = new Scanner("5   ");
		int rondasNum = Metodos.cogerRondas(sc);
		assertTrue(rondasNum == 5);
	}

	@Test
	void cogerNombreTest() {
		Scanner sc = new Scanner("Test Nombre");
		String nombre = Metodos.cogerNombre(sc);
		assertTrue(nombre.equals("Test Nombre"));
	}

	@Test
	void cogerClaseTest()
	{
		Scanner sc = new Scanner("2   ");
		int clase = Metodos.cogerClase(sc);
		assertTrue(clase == 2);
	}
	
	@Test
	void cogerAccionTest()
	{
		Scanner sc = new Scanner("1   ");
		int accion = Metodos.cogerAccion(sc);
		assertTrue(accion == 1);
	}
	
	@Test
	void seguirJugandoTest()
	{
		Scanner sc = new Scanner("s");
		char symbol = Metodos.seguirJugando(sc);
		assertTrue(symbol == 's');
	}
}
