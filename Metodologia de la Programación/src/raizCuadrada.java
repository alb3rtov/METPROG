/**
 * Class Name: raizCuadrada
 * <p>
 * Clase que calcula de 4 formas diferentes la raíz cuadrada
 * de un array de numeros enteros definido.
 * </p>
 * @author Alberto Vázquez Martínez y Ángel Villafranca Iniesta
 */
public class raizCuadrada {

	public static void main(String[] args) {
		
		int ns [] = {1, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121, 144, 169, 196, 225};
		
		long time1[] = new long[ns.length];
		long time2[] = new long[ns.length];
		long time3[] = new long[ns.length];
		long time4[] = new long[ns.length];
		
		raizCuadradaMath(ns, time1);
		metodoBabilonico(ns, time2);
		busquedaBinaria(ns, time3);
		raizCuadradaRecursiva(ns, time4);
		
		/*
		for (int i = 0; i < time1.length; i++) {
			System.out.println("Tiempo numero: " + ns[i] + " / math: " + time1[i] + "s / bab:  " + time2[i] + "s");
		}*/
	}
	
	/**
	 * <p> Calcula la raiz cuadrada con la biblioteca Math de Java.
	 * </p>
	 * @param numbers Array de números
	 * @param time Array con los tiempos de ejecución
	 */
	public static void raizCuadradaMath(int numbers[], long time[]) {
		long t1, t0;
		double t = 0;
		
		System.out.println("Método biblioteca Math:\n");
		
		for (int i = 0; i < numbers.length; i++) {
			t0 = System.nanoTime();
			System.out.println("Raíz cuadrada de " + numbers[i] + " = " + Math.sqrt(numbers[i]));
			t1 = System.nanoTime();
			time[i] = t1-t0;
			t += (t1 - t0)/1e9;
		}	
		
		System.out.println("\nTiempo de ejecución: " + (t/numbers.length) + " segundos");
	}
	
	/**
	 * <p> Calcula de raíz cuadrada con el método babilónico
	 * </p>
	 * @param numbers Array de números
	 * @param time Array con los tiempos de ejecución
	 */
	public static void metodoBabilonico(int numbers[], long time[]) {		
		long t1, t0 = 0;
		double t = 0;
		double n = 0, nn = 0, r;
		double min = Double.MAX_VALUE;
		double aux, m_babilonico;
		
		System.out.println("\nMétodo babilonico:\n");
		
		for (int i = 0; i < numbers.length; i++) {
			t0 = System.nanoTime();
			
			for (int j = 0; j < Integer.MAX_VALUE; j++) {
				r = j * j;
				aux = Math.abs(r - numbers[i]);
				
				if (aux < min) {
					n = j;
					nn = r;
					min = aux;
				} else {
					m_babilonico = (numbers[i] + nn) / (2 * n);
					System.out.println("Raiz cuadrada de " + numbers[i] + "= " + m_babilonico);
					min = Double.MAX_VALUE;
					break;
				}
			}
			
			t1 = System.nanoTime();
			time[i] = t1-t0;
			t += (t1 - t0)/1e9;
		}

		System.out.println("\nTiempo de ejecución: " + (t/numbers.length) + " segundos");
	}

	/**
	 * <p>
	 * </p>
	 * @param numbers Array de números
	 * @param time Array con los tiempos de ejecución
	 */
	public static void busquedaBinaria(int numbers[], long time[]) {
		long t1, t0;
		double t = 0;
		
		System.out.println("\nMétodo busqueda binaria:\n");
		
		for (int i = 0; i < numbers.length; i++) {
			t0 = System.nanoTime();
			System.out.println("Raíz cuadrada de " + numbers[i] + "= " + sqrtBS(numbers[i],numbers[i]/2));
			t1 = System.nanoTime();
			time[i] = t1-t0;
			t += (t1 - t0)/1e9;
		}
		
		System.out.println("\nTiempo de ejecución " + (t/numbers.length) + " segundos");
	}
	
	public static float sqrtBS(int number, int precision) { 
        int start = 0, end = number; 
        int mid;  
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

	public static void raizCuadradaRecursiva(int numbers[], long time[]) {
		long t1, t0;
		double t = 0;
		
		System.out.println("\nMétodo recursivo:\n");
		
		for (int i = 0; i < numbers.length; i++) {
			t0 = System.nanoTime();
			System.out.printf("Raíz cuadrada de %d = %.2f\n", numbers[i], root(numbers[i],2));
			t1 = System.nanoTime();
			time[i] = t1-t0;
			t += (t1 - t0)/1e9;
		}
		
		System.out.println("\nTiempo de ejecución " + (t/numbers.length) + " segundos");
	}
	
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
