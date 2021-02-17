public class raizCuadrada {

	public static void main(String[] args) {
		
		int ns [] = {1, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121, 144, 169, 196, 225};
		
		long time1[] = new long[ns.length];
		long time2[] = new long[ns.length];
		long time3[] = new long[ns.length];
		long time4[] = new long[ns.length];
		
		raizCuadradaMath(ns, time1);
		metodoBabilonico(ns, time2);
		busquedaBinaria();
		raizCuadradaRecursiva();
		
		for (int i = 0; i < time1.length; i++) {
			System.out.println("Tiempo numero: " + ns[i] + " / math: " + time1[i] + "s / bab:  " + time2[i] + "s");
		}
	}
	
	public static void raizCuadradaMath(int numbers[], long time[]) {
		long t1, t0;
		double t = 0;
		
		System.out.println("Método math: ");
		for (int i = 0; i < numbers.length; i++) {
			t0 = System.nanoTime();
			System.out.println("Raíz cuadrada de " + numbers[i] + " = " + Math.sqrt(numbers[i]));
			t1 = System.nanoTime();
			time[i] = t1-t0;
			t += (t1 - t0)/1e9;
			
		}	
		
		System.out.println("\nTiempo de ejecución: " + (t/numbers.length) + " segundos");
	}
	
	
	public static void metodoBabilonico(int numbers[], long time[]) {
		System.out.println("\nMétodo babilonico: ");
		
		long t1, t0 = 0;
		double t = 0;
		double n = 0, nn = 0, r;
		double min = Double.MAX_VALUE;
		double aux, m_babilonico;
		
		
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
		

		
		System.out.println("\nTiempo de ejecución: " + t + " segundos");
	}
	
	public static void busquedaBinaria() {
		
	}

	public static void raizCuadradaRecursiva() {
		
	}
}
