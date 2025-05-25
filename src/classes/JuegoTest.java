package classes;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JuegoTest {
	static Juego juego;

	@BeforeEach
	void setUp() {
		juego = new Juego();
		juego.setnRondas(5);
		juego.iniciarJuego();
	}

	@Test
	void testTerminarRonda() {
		Personaje enemigo = juego.getSiguiente();
		enemigo.setVida(0);
		assertTrue(juego.terminarRonda() == true);
	}
}
