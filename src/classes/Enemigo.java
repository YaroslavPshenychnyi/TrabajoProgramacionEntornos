package classes;

import java.util.Random;

public class Enemigo extends Personaje {

	public Enemigo() {

	}

	public void iniciarEnemigo(String nombre) {
		Random rnd = new Random();
		setNombre(nombre);
		setVida(rnd.nextInt(80) + 20);
		setVidaInicial(getVida());
		setAtaque(rnd.nextInt(8) + 2);
		setDefensa(rnd.nextInt(2) + 1);
	}
}
