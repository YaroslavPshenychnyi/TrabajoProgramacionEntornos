package utils;

import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class MetodosGuardadoTest {

	@Test
	void quieresCargarTest()
	{
		Scanner sc = new Scanner("S   ");
		char symbol = MetodosGuardado.quieresCargar(sc, (char)0);
		assertTrue(symbol == 's');
	}
	
}
