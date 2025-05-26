package utils;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import classes.Juego;

public class MetodosGuardadoTest {
	static Juego juego;

	@Test
	void quieresCargarTest() {
		Scanner sc = new Scanner("S		");
		char symbol = MetodosGuardado.quieresCargar(sc, (char) 0);
		assertTrue(symbol == 's');
	}

	@Test
	void archivoSiempreExisteTest() {
		File prueba = new File("saveTest.txt");
		MetodosGuardado.archivoSiempreExiste(prueba);
		assertTrue(prueba.exists());
	}

	@Test
	void juegoCargadoPrueba() {
		Scanner sc = new Scanner("s		");
		File guardado = new File("saveTest.txt");
		Juego prueba = null;
		prueba = MetodosGuardado.juegoCargado(sc, guardado);

		assertTrue(prueba != null);
	}

	@Before
	void setUp() {
		juego = new Juego();
		juego.setnRondas(5);
		juego.iniciarJuego();
	}

	@Test
	void guardarPartidaTest() {
		try {
			MetodosGuardado.guardarPartida(juego);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(new File("save.txt").exists());
	}

	@Before
	void setUpGuardado() {
		juego = new Juego();
		juego.setnRondas(5);
		juego.iniciarJuego();
		try {
			MetodosGuardado.guardarPartida(juego);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void cargarPartidaTest() {
		Juego otro = null;
		try {
			otro = MetodosGuardado.cargarPartida(new File("save.txt"));
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		assertTrue(juego == otro);
	}

}
