package es.pruebas.hackerrank.algoritms.strings;

import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/richie-rich">https://www.hackerrank.com/challenges/richie-rich</a>
 * @author EMILIO
 *
 */
public class RichieRich {
	
	public String run(String number, int n, int k) {
		return new Palindromo(number, n, k).composeBigPalindrom();
	}
	
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        String number = in.next();
    	in.close();
    	
	 	System.out.println(new Palindromo(number, n, k).composeBigPalindrom());	 	   	 	   	 	    	 		 	        
    }
}


class Palindromo {
	private final char[] secuence;
	private final int k;
		
	public Palindromo(String number,  int n, int k) {
		StringBuilder padding = new StringBuilder();
		for(int i=0; i< (n - number.length()); i++) {
			padding.append("0");
		}
		
		this.secuence = padding.append(number).toString().toCharArray();
		this.k = k;
	}
	
	
	public String composeBigPalindrom() {		
		int distance = getDistance(secuence);
		if (!isPalindromable(distance)) {
			return "-1";
		}
		
		char[] bigSecuence = new char[secuence.length];
		
		int remaining = k;		
		int midSize = secuence.length / 2;
		for (int i=0; i<midSize; i++) {
			char palimChar = 'x';
			int numberOfNonNines = numberOfNonNines(secuence[i], secuence[secuence.length-i-1]);
			
			if (secuence[i] != secuence[secuence.length-i-1]) {				
				if ((remaining - numberOfNonNines) >= 0 &&  (remaining - numberOfNonNines) >= (distance - 1)) {
					palimChar = '9';
					remaining -= numberOfNonNines;
					distance--;
				}
				else {
					palimChar = (char) Math.max((int)secuence[i], (int)secuence[secuence.length-i-1]);
					remaining -= 1;
					distance--;
				}
			}
			else {
				if ((remaining - numberOfNonNines) >= 0 && (remaining - numberOfNonNines) >= distance) {
					palimChar = '9';
					remaining -= numberOfNonNines;					
				}
				else {
					palimChar = secuence[i];
				}
			}
			
			bigSecuence[i] = palimChar;
			bigSecuence[secuence.length-i-1] = palimChar;
		}
		
		
		if (secuence.length % 2 != 0) {
			if (remaining > 0) {
				bigSecuence[midSize] = '9';
			}
			else {
				bigSecuence[midSize] = secuence[midSize];
			}
		}
		return new String(bigSecuence);
	}		
	
	private int numberOfNonNines(char c1, char c2) {
		int number = 0;
		if (c1 != '9') {
			number ++;
		}
		
		if (c2 != '9') {
			number ++;
		}
		return number;
	}
	

	private int getDistance(final char[] secuence) {
		int midSize = secuence.length / 2;
		int affinity = 0;
		for(int i=0; i< midSize; i++) {
			if (secuence[i] == secuence[secuence.length-i-1]) {
				affinity++;
			}
		}
		return midSize - affinity;
	}
	
	
	private boolean isPalindromable(int distance) {
		return k >= distance; 
	}
}