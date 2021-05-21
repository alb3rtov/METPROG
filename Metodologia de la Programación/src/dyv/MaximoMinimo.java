package dyv;

public class MaximoMinimo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]  arr = {1,2,3,4,5,6,7,8,4,3,1,0,7,6,-10};
		//System.out.println(minimo(0, arr.length-1, arr));
		int [] sol = minmax(0, arr.length-1, arr);
		
		for (int i = 0; i < sol.length; i++) {
			System.out.println(sol[i]);
		}
	}
	
	public static int minimo(int inicio, int fin, int [] arr) {
		int num = 0;
		
		if (inicio == fin) {
			num = arr[inicio];
		} else {
			int mitad = (inicio+fin)/2;
			int x = minimo(inicio, mitad, arr);
			int y = minimo(mitad+1, fin, arr);
			if (x > y) {
				num = y;
			} else {
				num = x;
			}
		}
		
		
		return num;
	}
	
	public static int[] minmax(int inicio, int fin, int [] arr) {
		int [] minmax = new int[2];
		
		if (fin - inicio <= 1) {
			
			minmax[0] = arr[inicio];
			minmax[1] = arr[fin];
			
			if (minmax[0] > minmax[1]) {
				int aux = minmax[0];
				minmax[0] = minmax[1];
				minmax[1] = aux;
			}

		} else {
			int mitad = (inicio+fin)/2;
			int[] x = minmax(inicio, mitad, arr);
			int[] y = minmax(mitad+1, fin, arr);
			
			if (x[0] < y[0]) {
				minmax[0] = x[0];
			} else {
				minmax[0] = y[0];
			}
			
			if (x[1] > y[1]) {
				minmax[1] = x[1];
			} else {
				minmax[1] = y[1];
			}
		}
		
		return minmax;
	}

}
