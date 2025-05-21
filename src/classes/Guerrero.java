package classes;

public class Guerrero extends Personaje implements Jugable {
	private static final int ATAQUE = 15;
	private static final int DEFENSA = 10;
	private static final int POCIONES = 15;

	private int pociones;

	public Guerrero(String nombre, int vida) {
		super(nombre, vida, ATAQUE, DEFENSA);
		pociones = POCIONES;
	}

	@Override
	public void curar() {
		if (pociones > 0) {
			setVida(getVidaInicial());
			pociones -= 1;
		}
	}

	@Override
	public void resetear() {
		super.resetear();
		pociones = POCIONES;
	}

	@Override
	public String toString() {
		return super.toString() + "; Posiones: " + pociones;
	}
}
