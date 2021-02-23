package ejercicios;

public class ejercicio4c {

	public static void main(String[] args) {
		
		int N = 1000;
		int sum = 0;
		for (int i = 0; i < N; i++)
			for (int j = i; j < N; j++)
				for (int k = i; k <= j; k++)
					sum++;
		
		System.out.println(sum);
		System.out.println((N*N*N)/6);
		System.out.println("Diff: " + (sum - (N*N*N)/6));
	}
}
