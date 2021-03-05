package ejercicios;

public class ejercicio10 {

	public static void main(String[] args) {
		
		int serie = 10;
		fibonacciIterativo(serie);

		System.out.printf("\nFibonnaci recursivo (%d): ", serie);
		for (int i = 0; i < serie; i++) { 
			System.out.print(fibonacciRecursivo(i) + " ");
		}
	}
	
	//Orden de O(n)
	public static void fibonacciIterativo(int serie) {

		int num1 = 0, num2 = 1, suma = 1;
		System.out.printf("Fibonnaci iterativo (%d): ", serie);
		System.out.print(num1 + " ");
		for (int i = 1; i < serie; i++) {

			System.out.print(suma + " ");
			suma = num1 + num2;
			num1 = num2;
			num2 = suma;
		}
	}
	
	//Orden de O(2^n)*O(n) -> O(2^n)
	public static int fibonacciRecursivo(int i) {
		
		if (i == 0) {
			return 0;  
		} else if (i == 1) {
			return 1;
		} else {
			return fibonacciRecursivo(i - 1) + fibonacciRecursivo(i - 2);
		}
	}
}
