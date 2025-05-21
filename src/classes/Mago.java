package classes;

public class Mago extends Personaje implements Jugable {
	private static final int ATAQUE_MAGIA = 20;
	private static final int ATAQUE_SIN_MAGIA = 5;
	private static final int DEFENSA = 5;
	private static final int MAGIA = 10;

	private int magia;

	public Mago(String nombre, int vida) {
		super(nombre, vida, ATAQUE_MAGIA, DEFENSA);
		this.magia = MAGIA;
	}

	@Override
	public void atacar(Personaje otro) {
		magia = magia <= 0 ? 0 : magia - 1;
		if (magia == 0) {
			setAtaque(ATAQUE_SIN_MAGIA);
		} else {
			setAtaque(ATAQUE_MAGIA);
		}
		super.atacar(otro);
	}

	@Override
	public void curar() {
		if (magia >= 1) {
			int viejaVida = getVida();

			int nuevaVida = (viejaVida + getAtaque()) > getVidaInicial() ? getVidaInicial() : viejaVida + getAtaque();
			setVida(nuevaVida);
			magia -= 1;
		}
	}

	@Override
	public void resetear() {
		super.resetear();
		magia = MAGIA;
	}

	@Override
	public String toString() {
		return super.toString() + "; Magia: " + magia;
	}

}
