package dyv;

public class kesimomenor {

	
	public static void main(String[] args) {
		
		int arr[] = {4,2,3,1,5,1000};
		int k = 4;
		
		quicksort(arr, 0, arr.length-1);
		
		System.out.println(kEsimoMenor(0, arr.length-1, arr, k));
		
	}
	
	public static int kEsimoMenor(int inicio, int fin,int [] arr, int k) {
		int num = -1;
		
		if (inicio == fin) {
			if (inicio == k) {
				num = arr[inicio];
			}
		} else {
			int mitad = (inicio+fin)/2;			
			int izq = kEsimoMenor(inicio, mitad, arr, k);
		    int der = kEsimoMenor(mitad+1, fin, arr, k);
		
			if (izq != -1) {
				num = izq;
			}
			
			if (der != -1) {
				num = der;
			}
		}
 		
		return num;
	}
	
	public static void quicksort(int A[], int izq, int der) {

		int pivote=A[izq]; // tomamos primer elemento como pivote
		int i=izq;         // i realiza la búsqueda de izquierda a derecha
		int j=der;         // j realiza la búsqueda de derecha a izquierda
		int aux;

		while(i < j){                          // mientras no se crucen las búsquedas                                   
			while(A[i] <= pivote && i < j) i++; // busca elemento mayor que pivote
			while(A[j] > pivote) j--;           // busca elemento menor que pivote
			if (i < j) {                        // si no se han cruzado                      
				aux= A[i];                      // los intercambia
				A[i]=A[j];
				A[j]=aux;
			}
		}

		A[izq]=A[j];      // se coloca el pivote en su lugar de forma que tendremos                                    
		A[j]=pivote;      // los menores a su izquierda y los mayores a su derecha

		if(izq < j-1)
			quicksort(A,izq,j-1);          // ordenamos subarray izquierdo
		if(j+1 < der)
			quicksort(A,j+1,der);          // ordenamos subarray derecho

	}



}
