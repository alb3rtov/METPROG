/**
 * Class Name: raizCuadrada
 * 
 * Clase que calcula de 4 formas diferentes la raíz cuadrada
 * de un array de numeros enteros.
 * 
 * @author Alberto Vázquez Martínez y Ángel Villafranca Iniesta
 */

package P1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class raizCuadrada {
	
	final static double APPROACH = 0.00001;
	final static int SQUARE = 2;
	
	public static void main(String[] args) throws FileNotFoundException {

		ArrayList<Float> ns = new ArrayList<>();

		File f=new File("numbers.txt");
		Scanner filename = new Scanner (f);
		
		while (filename.hasNext()){
			ns.add(filename.nextFloat());
		}
		
		filename.close();
		
		double sqrt1[] = new double[ns.size()];
		double sqrt2[] = new double[ns.size()];
		double sqrt3[] = new double[ns.size()];
		double sqrt4[] = new double[ns.size()];
		
		long time1[] = new long[ns.size()];
		long time2[] = new long[ns.size()];
		long time3[] = new long[ns.size()];
		long time4[] = new long[ns.size()];
		
		sqrtMath(ns, sqrt1, time1);
		sqrtBabilonico(ns, sqrt2, time2);
		sqrtBSLoop(ns, sqrt3, time3);
		sqrtRecursive(ns, sqrt4, time4);
		
		printTimeResults(ns, sqrt1, sqrt2, sqrt3, sqrt4, time1, time2, time3, time4);
	}
	
	/**
	 * Imprime los resultados de tiempo respecto a los numeros de la lista.
	 * 
	 * @param numbers Array con los números a calcular su raiz cuadrada
	 * @param sqrt1 
	 * @param sqrt2
	 * @param sqrt3
	 * @param sqrt4
	 * @param time1
	 * @param time2
	 * @param time3
	 * @param time4
	 */
	public static void printTimeResults(ArrayList<Float> numbers, double sqrt1[], double sqrt2[], 
				double sqrt3[], double sqrt4[], long time1[], long time2[], long time3[], long time4[]) {
		
		System.out.println("\n+------------------------------------+");
		System.out.println("| TABLA CON RESULTADOS (Time / Sqrt) |");
		System.out.println("+--------------+---------------------+---------+--------------------------------+----------------------------------------+------------------------------+");
		System.out.printf("|   %-10s |   %-25s   |   %-25s    |   %-35s  |   %-20s       |\n","Number","Math.sqrt","Babilonico","Binary Search","Recursive");
		System.out.println("|--------------|-------------------------------|--------------------------------|----------------------------------------|------------------------------|");
		for (int i = 0; i < numbers.size(); i++) {
			System.out.printf("|   %-10s |   %-12s / %-12.4f |   %-12s / %-13.4f |   %-17s / %-13.4f    |   %-12s / %-12.4f|\n",numbers.get(i), time1[i]+" (ns)", sqrt1[i], time2[i]+" (ns)", sqrt2[i], time3[i]+" (ns)", sqrt3[i], time4[i]+" (ns)", sqrt4[i]);
		}
		System.out.println("+--------------+-------------------------------+--------------------------------+----------------------------------------+------------------------------+");
	}
	
	/**
	 * Calcula la raiz cuadrada con la biblioteca Math de Java.
	 * 
	 * @param numbers Array de números
	 * @param time Array con los tiempos de ejecución
	 */
	public static void sqrtMath(ArrayList<Float> numbers, double sqrt[], long time[]) {
		long t1, t0;
		double t = 0;

		System.out.println("Método biblioteca Math.sqrt:");
		
		for (int i = 0; i < numbers.size(); i++) {
			t0 = System.nanoTime();
			sqrt[i] = Math.sqrt(numbers.get(i));
			t1 = System.nanoTime();
			time[i] = t1-t0;
			t += (t1 - t0)/1e9;
		}	
		
		System.out.println("Tiempo de ejecución total: " + (t/numbers.size()) + " segundos");
	}
	
	/**
	 * Calcula de raíz cuadrada con el método babilónico
	 * 
	 * @param numbers Array de números
	 * @param time Array con los tiempos de ejecución
	 */
	public static void sqrtBabilonico(ArrayList<Float> numbers, double sqrt[], long time[]) {		
		long t1, t0 = 0;
		double t = 0;
		double n = 0, nn = 0, r;
		double min = Double.MAX_VALUE;
		double aux;
		
		System.out.println("\nMétodo babilonico:");
		
		for (int i = 0; i < numbers.size(); i++) {
			t0 = System.nanoTime();
			
			for (int j = 0; j < Integer.MAX_VALUE; j++) {
				r = j * j;
				aux = Math.abs(r - numbers.get(i));
				
				if (aux < min) {
					n = j;
					nn = r;
					min = aux;
				} else {
					sqrt[i] = (numbers.get(i) + nn) / (2 * n);
					min = Double.MAX_VALUE;
					break;
				}
			}
			
			t1 = System.nanoTime();
			time[i] = t1-t0;
			t += (t1 - t0)/1e9;
		}

		System.out.println("Tiempo de ejecución total: " + (t/numbers.size()) + " segundos");
	}

	/**
	 * Recorre el array de numeros llamando a la funcion
	 * que calcula la raiz cuadrada con el método binario
	 * @param numbers Array de números
	 * @param time Array con los tiempos de ejecución
	 */
	public static void sqrtBSLoop(ArrayList<Float> numbers, double sqrt[], long time[]) {
		long t1, t0;
		double t = 0;
		System.out.println("\nMétodo busqueda binaria:");
		
		for (int i = 0; i < numbers.size(); i++) {
			t0 = System.nanoTime();
			sqrt[i] = sqrtBinarySearch(numbers.get(i),0,numbers.get(i));
			t1 = System.nanoTime();
			time[i] = t1-t0;
			t += (t1 - t0)/1e9;
		}
		
		System.out.println("Tiempo de ejecución total: " + (t/numbers.size()) + " segundos");
	}

	/**
	 * Calcula la raiz cuadrado con una busqueda binaria
	 * de manera recursiva
	 * @param numbers Array de números
	 * @param time Array con los tiempos de ejecución
	 * @return Raiz cuadrada del número dado
	 */
	public static double sqrtBinarySearch(double number, double low, double high) {
		double currentRoot;
		double currentApprox;
		
		currentRoot = (low+high)/2;
		currentApprox = Math.pow(currentRoot, 2);
		
		if (Math.abs(currentApprox-number) < APPROACH) {
			return currentRoot;
		} else if (currentApprox > number) {
			return sqrtBinarySearch(number, low, currentRoot);
		} else {
			return sqrtBinarySearch(number, currentRoot, high);
		}
	}
	
	/**
	 * Bucle que recorre el array de numeros llama a root para calcula la raiz cuadrada
	 * @param numbers Array de números
	 * @param time Array con los tiempos de ejecución
	 */
	public static void sqrtRecursive(ArrayList<Float> numbers, double sqrt[], long time[]) {
		long t1, t0;
		double t = 0;
		
		System.out.println("\nMétodo recursivo:");
		
		for (int i = 0; i < numbers.size(); i++) {
			t0 = System.nanoTime();
			sqrt[i] = findRoot(numbers.get(i), numbers.get(i)/2, SQUARE);
			t1 = System.nanoTime();
			time[i] = t1-t0;
			t += (t1 - t0)/1e9;
		}
		
		System.out.println("Tiempo de ejecución total: " + (t/numbers.size()) + " segundos");
	}
	
	/**
	 *Calculas la parte del numerador del metodo Newton-Raphson
	 * @param w Numero a calcular su raiz
	 * @param g aproximacion
	 * @param n grado de la raiz
	 * @return Numerador
	 */
    public static double f(double w, double g, int n) {
        return (Math.pow(g,n) - w);
    }

    /**
     * Calculas la parte del denominador del metodo Newton-Raphson
     * @param g aproximacion
     * @param n grado de la raiz
     * @return Denominador
     */
    public static double fPrime(double g, int n) {
        return (n * Math.pow(g, n-1));
    }

    /**
     * Devuelve un true si la aproximación es
     * suficientemente cercana a la raíz.
     * @param a
     * @param b
     * @return true o false dependiendo de la aproximación de la raiz
     */
    public static boolean closeEnough(double a, double b) {
        return (Math.abs(a-b) < Math.abs(b * APPROACH));
    }
    
    /**
     * Encuentra la raíz de n de un numero dado 
     * @param w Numero a calcular su raíz
     * @param g Aproximación
     * @param n grado de la raiz
     * @return Devuelve la aproximación encontrada
     */
    public static double findRoot(double w, double g, int n) {
        double newGuess = g - f(w,g,n) / fPrime(g,n);

        if (closeEnough(newGuess, g))
            return newGuess;
        else
            return findRoot(w, newGuess, n);
    }
}
