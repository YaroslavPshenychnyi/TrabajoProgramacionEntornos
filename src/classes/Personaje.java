package classes;

public class Personaje {
	private String nombre;
	private int vida;
	private int vidaInicial;
	private int ataque;
	private int defensa;

	public Personaje() {

	}

	public Personaje(String nombre, int vida, int ataque, int defensa) {
		this.nombre = nombre;
		this.vidaInicial = this.vida = vida;
		this.ataque = ataque;
		this.defensa = defensa;
	}

	public void atacar(Personaje otro) {
		int damage = this.ataque - otro.defensa;
		if (damage > 0) {
			int nuevaVida = otro.getVida() - damage;
			if (nuevaVida >= 0)
				otro.setVida(nuevaVida);
			else
				otro.setVida(0);
		}
	}

	public void curar() {
	}

	public void resetear() {
		vida = vidaInicial;
	}

	public boolean muerto() {
		return vida == 0;
	}

	@Override
	public String toString() {
		return "" + nombre + " => Vida: " + vida + "/" + vidaInicial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getVidaInicial() {
		return vidaInicial;
	}

	public void setVidaInicial(int vidaInicial) {
		this.vidaInicial = vidaInicial;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

}
