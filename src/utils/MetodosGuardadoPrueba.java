package utils;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import classes.Juego;

public class MetodosGuardadoPrueba {
	
	Juego juego = new Juego();
	
	@Test
	public void cargarPartidaPrueba() {
		File guardado = new File("save.txt");
		Juego juego1 = null;
		try {
			juego1 = MetodosGuardado.cargarPartida(guardado);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(juego1 !=null);
	}
	
	@Test
	public void archivoSiempreExiste() {
		File archivo = new File("prueba.txt");
		MetodosGuardado.archivoSiempreExiste(archivo);
		assertTrue(archivo.exists());
	}
	
	
	@Test
	public void quieresCargarPrueba() {
		Scanner entrada = new Scanner(System.in);
		char opcion = MetodosGuardado.quieresCargar(entrada);
		assertTrue(opcion == 's');
	}
	
	@Test
	public void juegoCargadoPrueb() {
		File guardado = new File("save.txt");
		Juego juego = null;
		try {
			juego = MetodosGuardado.cargarPartida(guardado);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		assertTrue(juego!= null);
		
	}
}
