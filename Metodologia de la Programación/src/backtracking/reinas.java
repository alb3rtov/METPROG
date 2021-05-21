package backtracking;

public class reinas {

	final static int N = 4;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tablero[][] = new int[N][N];
		int posReinas[] = new int[N];
		
		combinacionesReina(tablero, posReinas, 0, 0);
		
	}
	
	public static boolean comprobarPosicion(int tablero[][], int fila, int columnaAnterior, int columnaActual) {
		
		boolean pos = false;
		
		if (fila == 0) {
			pos = true;
		} else {
			System.out.println(columnaActual + " " +columnaAnterior + "\n");
			if (columnaActual != columnaAnterior) {
				if (fila-1 >= 0 && columnaActual-1 >=0 && fila-1 <= tablero.length-1 && columnaActual+1 <= tablero.length-1) {
					if (tablero[fila-1][columnaActual-1] == 0 && tablero[fila-1][columnaActual+1] == 0) {
						pos = true;
					}
				}
			}

		}
		
		
		
		return pos;
	}
	
	
	
	
	public static void combinacionesReina(int tablero[][] , int posReinas[], int fila, int columna) {
		int columnaAnterior = columna;
		
		if (columna == N-1) {
			System.out.println("daskdjas");
			printmatrix(tablero);

		} else {
			for (int i = 0; i < posReinas.length && columna < N; i++) {
					tablero[fila][i] = fila+1;
					printmatrix(tablero);
					System.out.println(columna);
					if (comprobarPosicion(tablero, fila, columnaAnterior, i)) {
						System.out.println("hola");
						combinacionesReina(tablero, posReinas, ++fila, columna);
					} else {
						tablero[fila][i] = 0;
						columna++;
					}
					
					
					
					/*if (fila+i+1 <= tablero.length-1 && fila+i-1 >= 0) {
						System.out.println(fila);
						if (tablero[fila-1][fila+i-1] == 0 && tablero[fila-1][fila+i+1] == 0) {
							fila++;
							combinacionesReina(tablero, posReinas, fila);
						}
					}*/
			}	
		}	
	}
	
	public static void printmatrix(int tablero[][]) {
		for (int k = 0; k < tablero.length; k++) {
			for (int j = 0; j < tablero.length; j++) {
				System.out.print(tablero[k][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
}
