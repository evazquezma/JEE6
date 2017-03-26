package es.pruebas.hackerrank.algoritms.strings;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import es.pruebas.hackerrank.algoritms.srings.RichieRich;

public class TestRichieRich {
	private RichieRich richieRich = new RichieRich();
	
	
	@Test
	public void basicTests() {		
		assertEquals("-1", richieRich.run("0011", 4, 1));
		assertEquals("1111", richieRich.run("0011", 4, 2));
		assertEquals("9119", richieRich.run("0011", 4, 3));
		assertEquals("9999", richieRich.run("0011", 4, 4));
		
		assertEquals("3993", richieRich.run("3943", 4, 1));		
		assertEquals("992299", richieRich.run("092282", 6, 3));
		assertEquals("292292", richieRich.run("092282", 6, 2));
		assertEquals("-1", richieRich.run("092282", 6, 1));
		assertEquals("9920299", richieRich.run("0920282", 7, 3));
		assertEquals("-1", richieRich.run("0011", 4, 1));		
	}
	
	@Test
	public void tests1() {		
		assertEquals("-1", richieRich.run("11199", 5, 1));
		assertEquals("99199", richieRich.run("11199", 5, 2));
		assertEquals("99999", richieRich.run("11199", 5, 3));
	}
	
	@Test
	public void tests2() {		
		assertEquals("-1", richieRich.run("99111", 5, 1));
		assertEquals("99199", richieRich.run("99111", 5, 2));
		assertEquals("99999", richieRich.run("99111", 5, 3));
	}
	
	@Test
	public void tests3() {
		assertEquals("2821282", richieRich.run("0821282", 7, 1));
		
		assertEquals("999999999", richieRich.run("111111111", 9, 9));
		assertEquals("999919999", richieRich.run("111111111", 9, 8));
		assertEquals("999191999", richieRich.run("111111111", 9, 7));
		assertEquals("999111999", richieRich.run("111111111", 9, 6));
		assertEquals("991191199", richieRich.run("111111111", 9, 5));
		
		assertEquals("911111119", richieRich.run("111111111", 9, 2));
		assertEquals("111191111", richieRich.run("111111111", 9, 1));
	}
	
	
	@Test
	public void tests4() {		
		assertEquals("999919999", richieRich.run("888811111", 8, 8));
		assertEquals("999818999", richieRich.run("888811111", 8, 7));
		assertEquals("998818899", richieRich.run("888811111", 8, 6));
		assertEquals("988818889", richieRich.run("888811111", 8, 5));
		assertEquals("888818888", richieRich.run("888811111", 8, 4));
		
		
		assertEquals("99999999", richieRich.run("12345678", 8, 9));
		assertEquals("99999999", richieRich.run("12345678", 8, 8));
		assertEquals("99955999", richieRich.run("12345678", 8, 7));
		assertEquals("99655699", richieRich.run("12345678", 8, 6));
		assertEquals("97655679", richieRich.run("12345678", 8, 5));
		assertEquals("87655678", richieRich.run("12345678", 8, 4));
		assertEquals("-1", richieRich.run("12345678", 8, 3));
		assertEquals("-1", richieRich.run("12345678", 8, 2));
		assertEquals("-1", richieRich.run("12345678", 8, 1));
		
		assertEquals("9999", richieRich.run("0111", 4, 4));
		assertEquals("9999", richieRich.run("111", 4, 4));
	}
	
	
	@Test
	public void test4() throws IOException {
		final InputStream  inInput = this.getClass().getClassLoader().getResourceAsStream("es/pruebas/hackerrank/algoritms/strings/RichieRich/test10.txt");		
		final BufferedReader reader = new BufferedReader(new InputStreamReader(inInput));
		final int n= Integer.valueOf(reader.readLine().trim());
		final int k = Integer.valueOf(reader.readLine().trim());
		final String number = reader.readLine();
		
		System.out.println("n: " + n + ", k: "+ k);
		
		final String expected = reader.readLine();
				
		String result = richieRich.run(number, n, k);
		
		assertEquals(expected.length(), result.length());
		
		int cambios = 0;
		for (int i=0; i<result.length(); i++) {
			if (result.charAt(i) != number.charAt(i)) {
				cambios ++;
			}
		}
		System.out.println(cambios);
		
		assertEquals(expected, result);
	}
}

