package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

import utils.Metodos;
import utils.MetodosGuardado;

public class Juego implements Serializable {

	private static final long serialVersionUID = -8020844114105634678L;

	private static final String[] nombresEnemigos = { "Sergio Malon", "Don Malvado", "La Sombra", "Diego Viver",
			"El Escorpión", "Doña Cruel", "El Fantasma", "Señor Veneno", "La Viuda Negra", "El Diablo Rojo",
			"Capitán Trampa", "Marquesa Oscura", "El Verdugo", "Reina Letal", "Hijo del Miedo", "El Lobo Negro",
			"Brujo Sangriento" };
	private static String nombreRecord;
	private static int rondaRecord;
	private ArrayList<Enemigo> enemigos;
	Personaje jugador;
	int nRondas;
	int ronda;
	boolean cargado;

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

	public boolean jugar(Scanner sc, boolean cargado) throws IOException {
		File guardad = new File("save.txt");
		if (cargado == false) {
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

		}
		if (this.algoQueLeer()) {
			System.out.println("\nRecord actual de rondas: " + rondaRecord);
			System.out.println("Personaje que lo tiene : " + nombreRecord + "\n");
		}
		while (this.getRonda() <= this.getnRondas() && !jugador.muerto()) {
			if (this.procesarRonda(sc) == false) {
				break;
			}
		}
		if (!jugador.muerto() && this.finalJuego()) {
			System.out.println("Has ganado");
			guardad.delete();
			if (this.ronda > rondaRecord) {
				System.out.println("Nuevo récord");
				rondaRecord = this.ronda - 1;
				nombreRecord = this.jugador.getNombre();
				this.guardarRecord();
			}
		} else if (!jugador.muerto() && !this.finalJuego()) {
			System.out.println("Nos vemos");
			return false;
		} else {
			System.out.println("Perdiste");
			guardad.delete();

		}
		return true;
	}

	public boolean procesarRonda(Scanner sc) throws IOException {
		Enemigo enemigo = this.getSiguiente();
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

			MetodosGuardado.guardarPartida(this);
			System.out.println("Guardamos y salimos del juego");
			return false;

		} else {
			System.out.println("Acción no encotrada");
		}
		if (this.terminarRonda()) {
			System.out.println("Enemigo vencido");
		} else if (jugador.muerto()) {
			System.out.println("Has muerto");
			if (this.ronda - 1 > rondaRecord) {
				System.out.println("Nuevo record");
				rondaRecord = this.ronda - 1;
				nombreRecord = this.jugador.getNombre();
				try {
					this.guardarRecord();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return true;
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

	public boolean algoQueLeer() throws IOException {

		FileReader record = new FileReader("record.txt");
		BufferedReader lector = new BufferedReader(record);
		ArrayList<String> datos = new ArrayList<String>();
		while (lector.ready()) {
			datos.add(lector.readLine());
		}
		if (datos.isEmpty()) {
			lector.close();
			return false;
		} else {
			rondaRecord = Integer.parseInt(datos.get(0));
			nombreRecord = datos.get(1);
			lector.close();
			return true;
		}

	}

	public void guardarRecord() throws IOException {
		FileWriter escriba = new FileWriter("record.txt");
		BufferedWriter imprenta = new BufferedWriter(escriba);

		imprenta.append(rondaRecord + "\n");
		imprenta.append(nombreRecord);

		imprenta.close();
		escriba.close();
	}

	public boolean isCargado() {
		return cargado;
	}

	public void setCargado(boolean cargado) {
		this.cargado = cargado;
	}

}
