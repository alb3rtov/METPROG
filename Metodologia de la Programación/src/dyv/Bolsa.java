package dyv;

import java.util.Random;

public class Bolsa {
	private int[] bolsa = null;

	/*
	 * se llena la bolsa inicializando el array y poniendo un elemento a 1 (moneda falsa)
	 */
	Bolsa(int n) {
		bolsa = new int[n];
		Random rnd=new Random();
		bolsa[n-1] = 1;
	}

	/*
	 * mostrar los resultados
	 */
	public void mostrar() {
		for (int i = 0; i < bolsa.length; i++) {
			System.out.printf("#%d: %d\t", i, bolsa[i]);
		}
		System.out.println();
	}

	/*
	 * pesa una parte de la bolsa 
	 */
	public int pesar (int li, int ls) {
		int peso = 0;
		for (int i = li; i <= ls; i++ ){
			peso += bolsa[i];
		}
		return peso;
	}


	/**
	 * resolución recursiva de la moneda falsa. 
	 */
	public int posicionFalsa(int li, int ls){
		int pos=-1;
		if (li==ls) {
			pos=li;
		} else {
			int mitad= (li + ls) /2;
			// como iniciamos con ls -> n - 1 entonces hay que sumar 1 
			// para comprobar si número de monedas par. 
			if (((ls + 1) - li) % 2 != 0){ //nº monedas impar
				int pesoizq = pesar(li,mitad-1);//dejamos la del centro sin pesar
				int pesoder = pesar(mitad+1, ls);
				if (pesoizq > pesoder)
					pos = posicionFalsa(li,mitad-1);
				else if (pesoizq < pesoder)
					pos = posicionFalsa(mitad+1,ls);
				else pos=mitad;
			} else {//nº de monedas par ver comentario anterior
				int pesoizq = pesar(li,mitad);
				int pesoder = pesar(mitad+1, ls);
				if (pesoizq > pesoder)
					pos = posicionFalsa(li,mitad);
				else if (pesoizq < pesoder)
					pos = posicionFalsa(mitad+1,ls);
			}
		}
		return pos;
	}

	public static void main(String [] args) {
		int n = 10;
		Bolsa bolsa = new Bolsa(n);
		bolsa.mostrar();
		// limite superior lo ponemos a n - 1 (importante para par/impar)
		System.out.println(bolsa.posicionFalsa(0, n - 1));
	}


}