package classes;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GuerreroTest {
	
	static Guerrero guerrero;

	@BeforeEach
	void setUp() {
		guerrero = new Guerrero("TestGuerrero", 100);
	}

	@Test
	void testCurar() {
		guerrero.setVida(0);
		guerrero.curar();
		assertTrue(guerrero.getVida() == guerrero.getVidaInicial());
	}
}
