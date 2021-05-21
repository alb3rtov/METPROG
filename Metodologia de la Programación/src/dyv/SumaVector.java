package dyv;

public class SumaVector {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] array = {1,2,3,4,5,6,7,8};
	
		System.out.println(sumar(0, array.length-1, array));
		System.out.println(media(0, array.length-1, array));
	}
	
	public static int sumar(int inicio, int fin, int [] array) {
		int num = 0;
		
		if (inicio == fin) {
			num = array[inicio];
		} else {
			int mitad = (inicio+fin)/2;
			int izquierda = sumar(inicio, mitad, array);
			int derecha = sumar(mitad+1, fin, array);
			num = izquierda + derecha;
		}
		
		return num;
	}
	
	public static float media(int inicio, int fin, int[] array) {
		float num = 0;
		
		if (inicio == fin) {
			num = array[inicio];
		} else {
			int mitad = (inicio+fin)/2;
			float izq = media(inicio, mitad, array);
			float der = media(mitad+1, fin, array);
			num = (izq + der)/2;
		}
		
		
		return num;
	}
	

}
