package classes;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MagoTest {

	static Mago mago;

	@BeforeEach
	void setUp() {
		mago = new Mago("TestMago", 100);
	}

	@Test
	void testCurar() {
		mago.setVida(0);
		mago.curar();
		assertTrue(mago.getVida() == 20);
	}

	@Test
	void testAtacar() {
		Personaje otro = new Guerrero("TestEnemigo", 100);
		mago.atacar(otro);
		assertTrue(otro.getVida() == 90);
	}
}
