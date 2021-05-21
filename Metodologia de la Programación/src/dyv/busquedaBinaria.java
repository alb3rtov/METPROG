package dyv;

public class busquedaBinaria {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] arr = {1,2,3,4};
		System.out.println(busquedaBinaria(arr, 0, arr.length-1, 4));
	}
	
	public static int busquedaBinaria(int [] arr, int inicio, int fin, int numero) {
		int pos = -1;
		
		if(inicio==fin) {
			pos = inicio;
		} else {
			int mitad = (inicio+fin)/2;
			int izq = busquedaBinaria(arr, inicio, mitad, numero);
			int der = busquedaBinaria(arr, mitad+1, fin, numero);
			
			if (izq != -1) {
				if (arr[izq] == numero) {
					pos = izq;
				}
			}
			
			if (der != -1) {
				if(arr[der] == numero) {
					pos = der;
				}
			}


		}
		
		return pos;
	}

}
