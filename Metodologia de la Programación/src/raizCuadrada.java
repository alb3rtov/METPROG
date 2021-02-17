public class raizCuadrada {

	public static void main(String[] args) {
		
		int ns [] = {1, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121, 144, 169, 196, 225};

		raizCuadradaMath(ns);
		metodoBabilonio();
		busquedaBinaria();
		raizCuadradaRecursiva();
		
	}
	
	public static void raizCuadradaMath(int numbers[]) {
		long t1, t0;
		double t = 0;

		System.out.println("M�todo math: ");
		for (int i = 0; i < numbers.length; i++) {
			t0 = System.nanoTime();
			System.out.println("Ra�z cuadrada de " + numbers[i] + " = " + Math.sqrt(numbers[i]));
			t1 = System.nanoTime();
			t += (t1 - t0)/1e9;
		}	
		
		System.out.println("\nTiempo de ejecuci�n: " + (t/numbers.length) + " segundos");
	}
	
	
	public static void metodoBabilonio() {
		
		
	}
	
	public static void busquedaBinaria() {
		
	}

	public static void raizCuadradaRecursiva() {
		
	}
}
