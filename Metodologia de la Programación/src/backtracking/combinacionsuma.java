package backtracking;

import java.util.ArrayList;

public class combinacionsuma {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 5;
		ArrayList<Integer> numeros = new ArrayList<>();
		ArrayList<Integer> solution = new ArrayList<>();		
		numeros.add(1);
		numeros.add(2);
		numeros.add(3);
		numeros.add(4);
		numeros.add(5);
		combinacionesSuma(n, numeros, solution, 0);
	}
	
	public static void combinacionesSuma(int numero, ArrayList<Integer> numeros,  ArrayList<Integer> solution, int suma) {
		
		if (suma == numero) {
			System.out.println(solution);
		} else {
			for (int i = 0; i <= numeros.size()-1; i++) {
				suma+=numeros.get(i);
				if (suma <= numero) {
					solution.add(numeros.get(i));
					//int num = numeros.remove(i);
					combinacionesSuma(numero, numeros, solution, suma);
					solution.remove(solution.size()-1);
					//numeros.add(num);
				}
				suma-=numeros.get(i);
			}
		}
		
	}

}
