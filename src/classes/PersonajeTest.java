package classes;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

	@Test
	void getAtaqueTest() {
		personaje.setAtaque(20);
		assertEquals(20, personaje.getAtaque());

	}
	@Test
	void getDefensaTest() {
		personaje.setDefensa(20);
		assertEquals(20, personaje.getDefensa());
	}
	@Test
	void getVidaTest() {
		personaje.setVida(20);
		assertEquals(20, personaje.getVida());
	}
	@Test
	void getNombreTest() {
		personaje.setNombre("nombre");
		assertEquals("nombre", personaje.getNombre());
	}
}
