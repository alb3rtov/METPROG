package dyv;

public class PrintMatrices {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int [][] matrix = {{1,2,3,4},
						   {5,6,7,8},
						   {9,10,11,12},
						   {13,14,15,16}};
		
		//imprimirMatriz(matrix, 0, 0, matrix.length);
	}
	
	public static int [][] traspuesta(int [][] matrix, int fila, int columna, int dimension) {
		int[][] matrixTras = new int[dimension][dimension];
		
		if (dimension==2) {
			matrixTras[0][0] = matrix[fila][columna];
			matrixTras[0][1] = matrix[fila][columna];
			matrixTras[1][0] = matrix[fila][columna];
			matrixTras[1][1] = matrix[fila][columna];
		} else {
			int [][] aux1 = traspuesta(matrix, fila, columna, dimension/2);
			//int [][] aux2 = traspuesta(matrix, fila, columna, dimension);
			//int [][] aux3 = traspuesta(matrix, fila, columna, dimension);
			//int [][] aux4 = traspuesta(matrix, fila, columna, dimension);
			
			//combinar(aux1,aux2,aux3,aux4);
		}
		
		
		return matrixTras;
	}
	
	public static void combinar(int [][] aux1 , int [][] aux2, int [][] aux3, int [][] aux4, int dimension) {
		
	}
	
	public static void imprimirMatriz(int [][] matrix, int fila, int columna, int dimension) {
		
		if (dimension == 2) {
			printMatriz(matrix, fila, columna, dimension);
		} else {
			
			//imprimirMatriz(matrix, fila, columna, dimension/2);
			//imprimirMatriz(matrix, fila, columna+(dimension/2), dimension/2);
			//imprimirMatriz(matrix, fila+(dimension/2), columna, dimension/2);
			//imprimirMatriz(matrix, fila+(dimension/2), columna+(dimension/2), dimension/2);
		}
		
	}
	
	
	public static void printMatriz(int [][] matrix, int fila, int columna, int dimension) {
		for (int i = fila; i < dimension+fila; i++) {
			for (int j = columna; j < dimension+columna; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
