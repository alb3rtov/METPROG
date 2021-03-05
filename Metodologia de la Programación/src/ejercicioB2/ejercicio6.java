package ejercicioB2;

public class ejercicio6 {

	public static void main(String[] args) {
		
		int numbers[] = {4,2,3,1,5,1000};
		
		kEsimoMenor(numbers, 0);
		kEsimoMenor(numbers, 3);
		kEsimoMenor(numbers, 5);
	}
	
	public static void kEsimoMenor(int numbers[], int n) {
		
		if (numbers.length == 0) {
			System.out.println("Array vacío");
		} 
		else if (numbers.length == 1) {
			System.out.println(numbers[0]);
		} 
		else {
			
		}
		
	}

}
