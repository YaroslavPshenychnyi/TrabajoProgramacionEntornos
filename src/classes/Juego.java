package classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

import utils.Metodos;

public class Juego implements Serializable {

	private static final long serialVersionUID = -8020844114105634678L;

	private static final String[] nombresEnemigos = { "Sergio Malon", "Don Malvado", "La Sombra", "Diego Viver",
			"El Escorpión", "Doña Cruel", "El Fantasma", "Señor Veneno", "La Viuda Negra", "El Diablo Rojo",
			"Capitán Trampa", "Marquesa Oscura", "El Verdugo", "Reina Letal", "Hijo del Miedo", "El Lobo Negro",
			"Brujo Sangriento" };

	private ArrayList<Enemigo> enemigos;
	Personaje jugador;
	int nRondas;
	int ronda;

	public Juego() {
		enemigos = new ArrayList<Enemigo>();
	}

	private static String nombreAleatorio() {
		Random rnd = new Random();
		return nombresEnemigos[rnd.nextInt(nombresEnemigos.length)];
	}

	public void iniciarJuego() {
		if (!enemigos.isEmpty()) {
			enemigos = new ArrayList<Enemigo>();
		}

		for (int i = 0; i < nRondas; i++) {
			Enemigo enemigo = new Enemigo();
			enemigo.iniciarEnemigo(nombreAleatorio());
			enemigos.add(enemigo);
		}

		ronda = 1;
	}

	public Enemigo getSiguiente() {
		return enemigos.get(0);
	}

	public boolean terminarRonda() {
		Enemigo e = enemigos.get(0);
		if (e.muerto()) {
			enemigos.remove(0);
			ronda++;
			return true;
		} else {
			return false;
		}
	}

	public void nuevoGuerrero(String nombre) {
		Random rnd = new Random();
		jugador = new Guerrero(nombre, rnd.nextInt(100) + 100);
	}

	public void nuevoMago(String nombre) {
		Random rnd = new Random();
		jugador = new Mago(nombre, rnd.nextInt(100) + 50);
	}

	public boolean finalJuego() {
		return enemigos.isEmpty();
	}

	public void jugar(Scanner sc) {
		System.out.println("Bienvenido al juego: ");

		int rondas = Metodos.cogerRondas(sc);
		String nombre = Metodos.cogerNombre(sc);
		int clase = Metodos.cogerClase(sc);

		if (clase == 1)
			this.nuevoMago(nombre);
		else if (clase == 2)
			this.nuevoGuerrero(nombre);
		else
			System.out.println("Esta clase no existe");

		this.setnRondas(rondas);
		this.iniciarJuego();

		while (this.getRonda() <= this.getnRondas() && !jugador.muerto()) {
			this.procesarRonda(sc);
		}

		if (!jugador.muerto() && this.finalJuego()) {
			System.out.println("Has ganado");
		} else {
			System.out.println("Has perdido");
		}
	}

	public void procesarRonda(Scanner sc) {
		Enemigo enemigo = this.getSiguiente();
		FileOutputStream escriba = null;
		ObjectOutputStream editor = null;
		System.out.printf("Ronda %d/%d%n", this.getRonda(), this.getnRondas());
		System.out.printf("Eres %s%n", jugador.toString());
		System.out.printf("Estás luchando contra: %s%n", enemigo.toString());

		int accion = Metodos.cogerAccion(sc);
		if (accion == 1) {
			System.out.printf("%s ataca a %s%n", jugador.getNombre(), enemigo.getNombre());
			System.out.printf("%s ataca a %s%n", enemigo.getNombre(), jugador.getNombre());
			jugador.atacar(enemigo);
			enemigo.atacar(jugador);

		} else if (accion == 2) {
			System.out.printf("%s se cura%n", jugador.getNombre());
			System.out.printf("%s ataca a %s%n", enemigo.getNombre(), jugador.getNombre());
			if (jugador instanceof Jugable) {
				((Jugable) jugador).curar();
			}
			enemigo.atacar(jugador);
		} else if (accion == 3) {
			System.out.println("Guardamos partida");
			File guardado = new File("save.txt");
			if (!guardado.exists()) {
				try {
					guardado.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				escriba = new FileOutputStream(guardado);
				editor = new ObjectOutputStream(escriba);
				editor.writeObject(this);

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					editor.close();
					escriba.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} else
			System.out.println("Esta accion no existe");

		if (this.terminarRonda()) {
			System.out.println("Enemigo vencido");
		} else if (jugador.muerto()) {
			System.out.println("Has muerto");

		}
	}

	public Personaje getJugador() {
		return jugador;
	}

	public void setJugador(Personaje jugador) {
		this.jugador = jugador;
	}

	public int getnRondas() {
		return nRondas;
	}

	public void setnRondas(int nRondas) {
		this.nRondas = nRondas;
	}

	public int getRonda() {
		return ronda;
	}

	public void setRonda(int ronda) {
		this.ronda = ronda;
	}

}
