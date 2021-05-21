package dyv;

public class ordi1718 {

	/*
	 * (2 puntos) Usando la técnica divide y vencerás, implementa en Java un método que
	 *	calcule la media de un vector de n enteros, siendo n potencia de 2.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int arr[] = {1,2,3,4,5,6,7,8};
		
		if (esPotencia2(arr.length)) {
			System.out.println(calcularMedia(0, arr.length-1, arr));
		} else {
			System.out.println("La longitud del array no es potencia de 2");
		}
	}
	
	public static double calcularMedia(int inicio, int fin, int []arr) {
		double media =0;
		
		if (inicio == fin) {
			media = (double) arr[inicio]/arr.length;
		} else {
			int mitad = (inicio+fin)/2;
			double mediaIzq = calcularMedia(inicio, mitad, arr);
			double mediaDer = calcularMedia(mitad+1, fin, arr);
			
			media = (mediaIzq+mediaDer);
		}
		
		return media;
	}
	
	
	public static boolean esPotencia2(int n) {
		double x = Math.log(n)/Math.log(2);
		int cast = (int) x;
		
		if (cast == x) {
			return true;
		}
		return false;
	}

}
