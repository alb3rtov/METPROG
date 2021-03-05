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
	
	final static int SQUARE = 2;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//int ns [] = {1, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121, 144, 169, 196, 225};
		//int ns [] = {400,504,675,876,987,1023,2321,3542,4353,5646,6040,8004,10320,12340};
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
		sqrtBinarySearch(ns, sqrt3, time3);
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
		
		System.out.printf("\n%-10s |   %-25s   |   %-25s    |   %-35s       |   %-20s\n","Number","Math","Babilonico","Binary Search","Recursive");
		System.out.println("-----------|-------------------------------|--------------------------------|---------------------------------------------|-------------------------------------");
		for (int i = 0; i < numbers.size(); i++) {
			System.out.printf("%-10s |   %-12s / %-12s |   %-12s / %-13s |   %-17s / %-18s    |   %-12s / %-12s \n",numbers.get(i), time1[i]+" (ns)", sqrt1[i], time2[i]+" (ns)", sqrt2[i], time3[i]+" (ns)", sqrt3[i], time4[i]+" (ns)", sqrt4[i]);
		}
		System.out.println("\n(Time / Sqrt)");
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

		System.out.println("Método biblioteca Math:");
		
		for (int i = 0; i < numbers.size(); i++) {
			t0 = System.nanoTime();
			//System.out.println("Raíz cuadrada de " + numbers.get(i) + " = " + Math.sqrt(numbers.get(i)));
			sqrt[i] = Math.sqrt(numbers.get(i));
			t1 = System.nanoTime();
			time[i] = t1-t0;
			t += (t1 - t0)/1e9;
		}	
		
		System.out.println("Tiempo de ejecución: " + (t/numbers.size()) + " segundos");
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
					//System.out.println("Raiz cuadrada de " + numbers.get(i) + "= " + sqrt[i]);
					min = Double.MAX_VALUE;
					break;
				}
			}
			
			t1 = System.nanoTime();
			time[i] = t1-t0;
			t += (t1 - t0)/1e9;
		}

		System.out.println("Tiempo de ejecución: " + (t/numbers.size()) + " segundos");
	}

	/**
	 * Recorre el array de numeros llamando a la funcion
	 * que calcula la raiz cuadrada con el método binario
	 * @param numbers Array de números
	 * @param time Array con los tiempos de ejecución
	 */
	public static void sqrtBinarySearch(ArrayList<Float> numbers, double sqrt[], long time[]) {
		long t1, t0;
		double t = 0;
		System.out.println("\nMétodo busqueda binaria:");
		
		for (int i = 0; i < numbers.size(); i++) {
			t0 = System.nanoTime();
			//System.out.println("Raíz cuadrada de " + numbers.get(i) + "= " + sqrtBS(numbers.get(i),numbers.get(i)/2));
			sqrt[i] = sqrtBS(numbers.get(i),numbers.get(i)/2);
			t1 = System.nanoTime();
			time[i] = t1-t0;
			t += (t1 - t0)/1e9;
		}
		
		System.out.println("Tiempo de ejecución " + (t/numbers.size()) + " segundos");
	}
	
	/**
	 * Calcula la raiz cuadrado con una busqueda binaria
	 * @param numbers Array de números
	 * @param time Array con los tiempos de ejecución
	 * @return Raiz cuadrada del número dado
	 */
	public static float sqrtBS(float number, float precision) { 
        float start = 0, end = number; 
        float mid;  
        double ans = 0;
        double increment = 0.01;
        
        while (start <= end) { 
            mid = (start + end) / 2;  
            
            if (mid * mid == number) { 
                ans = mid; 
                break; 
            } 
            
            if (mid * mid < number) { 
                start = mid + 1; 
                ans = mid; 
            } else { 
                end = mid - 1; 
            } 
        }

        for (int i = 0; i < precision; i++) {
            while (ans * ans < number) {
                ans += increment;
            } 
            ans = ans - increment;
        } 
        
        return (float) ans; 
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
			//System.out.printf("Raíz cuadrada de %.1f = %.2f\n", numbers.get(i), root(numbers.get(i),SQUARE));
			sqrt[i] = root(numbers.get(i), SQUARE);
			t1 = System.nanoTime();
			time[i] = t1-t0;
			t += (t1 - t0)/1e9;
		}
		
		System.out.println("Tiempo de ejecución " + (t/numbers.size()) + " segundos");
	}
	
	/**
	 * 
	 * @param w
	 * @param g
	 * @param n
	 * @return
	 */
    public static double f(double w, double g, int n) {
        return (Math.pow(g,n) - w);
    }

    public static double fPrime(double g, int n) {
        return (n * Math.pow(g, n-1));
    }

    public static boolean closeEnough(double a, double b) {
        return (Math.abs(a-b) < Math.abs(b * 0.0001));
    }

    public static double findRoot(double w, double g, int n) {
        double newGuess = g - f(w,g,n) / fPrime(g,n);

        if (closeEnough(newGuess, g))
            return newGuess;
        else
            return findRoot(w, newGuess, n);
    }

    public static double root(double w, int n) {
        return findRoot(w,1,n);
    }
}
