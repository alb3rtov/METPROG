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
	public static ArrayList<Integer> generateNumbersList(int numBigNumbers, int numSmallNumbers, ArrayList<Integer> bigNumbers, ArrayList<Integer> smallNumbers) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for (int i = 0; i < numBigNumbers; i++) {
			int numberPos = generateRandomNumber(bigNumbers.size()-1, 0);
			list.add(bigNumbers.remove(numberPos));
		}
		
		for (int i = 0; i < numSmallNumbers; i++) {
			int numberPos = generateRandomNumber(smallNumbers.size()-1, 0);
			list.add(smallNumbers.remove(numberPos));
		}
		
		list.add(generateRandomNumber(999, 101)); //El �ltimo elemento de la lista es el n�mero objetivo
		
		return list;
	}
	
	/**
	 * Genera un n�mero random dado un m�nimo y un m�ximo
	 * @param max
	 * @param min
	 * @return n�mero random
	 */
	public static int generateRandomNumber(int max, int min) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	
}
