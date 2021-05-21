package dyv;

public class ArrayIguales {
	public static void main(String[] args) {
		
		int [] arr1 = {1,2,3,4,5,6,7,8};
		int [] arr2 = {1,2,3,4,5,6,7,8};
	
		System.out.println(iguales(0, arr1.length-1, arr1, arr2));
	}
	
	
	public static int iguales(int inicio, int fin, int [] arr1, int [] arr2) {
		int num = 0;
		
		if (inicio == fin) {
			if (arr1[inicio] == arr2[inicio]) {
				num = -1;
			} else {
				num = inicio;
			}
		} else {
			int mitad = (inicio+fin)/2;
			int x = iguales(inicio, mitad, arr1, arr2);
			int y = iguales(mitad+1, fin, arr1, arr2);
			
			if (x != -1) {
				num = x;
			} else if (y != -1) {
				num = y;
			} else {
				num = -1;
			}
		}	
		
		return num;
	}
}
