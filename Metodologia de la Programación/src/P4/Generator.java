/*********************************************************
 * 
 * Class Name: Generator.java
 * Clase que se encarga de generar la lista de n�meros
 * semi-aleatorios que ser� utilizada para resolver el problema.
 * 
 * @author Alberto V�zquez Mart�nez y �ngel Villafranca Iniesta
 * 
 *********************************************************/

package P4;

import java.util.ArrayList;

public class Generator {
	/**
	 * Genera la lista con los n�meros a utilizar y el n�mero objetivo a calcular.
	 * @param numBigNumbers
	 * @param numSmallNumbers
	 * @param bigNumbers
	 * @param smallNumbers
	 * @return
	 */
	public static int[] generateNumbersList(int numBigNumbers, int numSmallNumbers, ArrayList<Integer> bigNumbers, ArrayList<Integer> smallNumbers) {
		int[] list = new int[numBigNumbers+numSmallNumbers];
		int i = 0;
		
		for (i = 0; i < numBigNumbers; i++) {
			int numberPos = generateRandomNumber(0, bigNumbers.size());
			list[i] = bigNumbers.remove(numberPos);
		}
		
		for (int j = i; j < numBigNumbers+numSmallNumbers; j++) {
			int numberPos = generateRandomNumber(0, smallNumbers.size());
			list[j] = smallNumbers.remove(numberPos);
		}

		return list;
	}
	
	/**
	 * Genera un n�mero random dado un m�nimo y un m�ximo
	 * @param max
	 * @param min
	 * @return n�mero random
	 */
	public static int generateRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
