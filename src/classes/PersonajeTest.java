package classes;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonajeTest {

	static Personaje personaje;

	@BeforeEach
	void setUp() {
		personaje = new Guerrero("TestNombre", 100);
	}

	@Test
	void atacarTest() {
		Personaje otro = new Guerrero("TestEnemigo", 30);
		personaje.atacar(otro);
		assertTrue(otro.getVida() == 25);
	}

	@Test
	void resetearTest() {
		personaje.setVida(0);
		personaje.resetear();
		assertTrue(personaje.getVida() == personaje.getVidaInicial());
	}

	@Test
	void muertoTest() {
		personaje.setVida(0);
		assertTrue(personaje.muerto() == true);
	}
}
