/**
 * Class Name: Strassen
 * 
 * Clase que calcula la matriz cuadrada dada por la
 * multiplicación entre dos matrices cuadradas de orden N
 * Los elementos de las matrices son generados aleatoriamente.
 * 
 * @author Alberto Vázquez Martínez
 */

package ejercicioB2;

import java.util.*;

public class Strassen {
	
	final static Scanner KEYBOARD = new Scanner(System.in);
	
	/**
	 * Metodo main que llama al resto de funciones
	 * @param args
	 */
	public static void main(String[] args) {
		
		long t1, t0;
		double t = 0;
		/* Genera numero aleatorios del 0 al 100 */
		Random r = new Random(100);
		Random r1 = new Random(100);
		
		System.out.println("Introduzca el orden de la matriz: ");
		int n = KEYBOARD.nextInt();
		
		int[][] matrix1 = new int[n][n];
		int[][] matrix2 = new int[n][n];
		
		int[][] mMatrix = new int[n][n];
		
		for (int i = 0; i < n ; i++) {
			for (int j = 0; j < n ; j++) {
				matrix1[i][j] = r.nextInt(100);
				matrix2[i][j] = r1.nextInt(100);
			}
		}
		
		System.out.println("Matriz1 generada: ");
		printMatrix(matrix1);
		System.out.println("Matriz2 generada: ");
		printMatrix(matrix2);
		
		t0 = System.nanoTime();
		mMatrix = strassen(matrix1,matrix2);
		t1 = System.nanoTime();
				
		System.out.println("Resultado multiplicación matriz1 x matrix2: ");
		
		printMatrix(mMatrix);  
		System.out.println("Tiempo de cálculo (orden " + n + ") = " + (t1-t0)/1e9 + " (s)");
	}
	
	/**
	 * Algoritmo de Strassen para calcular multiplicaciones de matrices
	 * @param a
	 * @param b
	 * @return
	 */
	public static int [][] strassen(int [][] a, int [][] b) {
		int n = a.length;
		int [][] result = new int[n][n];

		if ((n % 2 != 0 ) && (n != 1)) {
			int[][] a1, b1, c1;
			int n1 = n+1;
			
			a1 = new int[n1][n1];
			b1 = new int[n1][n1];
			c1 = new int[n1][n1];

			for(int i = 0; i < n; i++)
				for(int j = 0; j < n; j++) {
					a1[i][j] =a[i][j];
					b1[i][j] =b[i][j];
				}
			
			c1 = strassen(a1, b1);
			
			for(int i = 0; i < n; i++)
				for(int j = 0; j < n; j++)
					result[i][j] =c1[i][j];
			return result;
		}

		if(n == 1) {
			result[0][0] = a[0][0] * b[0][0];
		} else {
			int [][] A11 = new int[n/2][n/2];
			int [][] A12 = new int[n/2][n/2];
			int [][] A21 = new int[n/2][n/2];
			int [][] A22 = new int[n/2][n/2];

			int [][] B11 = new int[n/2][n/2];
			int [][] B12 = new int[n/2][n/2];
			int [][] B21 = new int[n/2][n/2];
			int [][] B22 = new int[n/2][n/2];

			divide(a, A11, 0 , 0);
			divide(a, A12, 0 , n/2);
			divide(a, A21, n/2, 0);
			divide(a, A22, n/2, n/2);

			divide(b, B11, 0 , 0);
			divide(b, B12, 0 , n/2);
			divide(b, B21, n/2, 0);
			divide(b, B22, n/2, n/2);

			int [][] P1 = strassen(add(A11, A22), add(B11, B22));
			int [][] P2 = strassen(add(A21, A22), B11);
			int [][] P3 = strassen(A11, sub(B12, B22));
			int [][] P4 = strassen(A22, sub(B21, B11));
			int [][] P5 = strassen(add(A11, A12), B22);
			int [][] P6 = strassen(sub(A21, A11), add(B11, B12));
			int [][] P7 = strassen(sub(A12, A22), add(B21, B22));

			int [][] C11 = add(sub(add(P1, P4), P5), P7);
			int [][] C12 = add(P3, P5);
			int [][] C21 = add(P2, P4);
			int [][] C22 = add(sub(add(P1, P3), P2), P6);

			copy(C11, result, 0 , 0);
			copy(C12, result, 0 , n/2);
			copy(C21, result, n/2, 0);
			copy(C22, result, n/2, n/2);
		}
		
		return result;
	}

	/**
	 * Sumar dos numeros de la matriz
	 * @param A
	 * @param B
	 * @return
	 */
	public static int [][] add(int [][] A, int [][] B) {
		int n = A.length;

		int [][] result = new int[n][n];

		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				result[i][j] = A[i][j] + B[i][j];

		return result;
	}
	
	/**
	 * Restar dos numeros de la matriz
	 * @param A
	 * @param B
	 * @return
	 */
	public static int [][] sub(int [][] A, int [][] B) {
		int n = A.length;

		int [][] result = new int[n][n];

		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				result[i][j] = A[i][j] - B[i][j];

		return result;
	}

	/**
	 * Dividir dos numeros de la matriz
	 * @param p1
	 * @param c1
	 * @param iB
	 * @param jB
	 */
	public static void divide(int[][] p1, int[][] c1, int iB, int jB) {
		for(int i1 = 0, i2=iB; i1<c1.length; i1++, i2++)
			for(int j1 = 0, j2=jB; j1<c1.length; j1++, j2++) {
				c1[i1][j1] = p1[i2][j2];
			}
	}

	/**
	 * Generar una copia de la matriz
	 * @param c1
	 * @param p1
	 * @param iB
	 * @param jB
	 */
	public static void copy(int[][] c1, int[][] p1, int iB, int jB) {
		for (int i1 = 0, i2=iB; i1<c1.length; i1++, i2++)
			for (int j1 = 0, j2=jB; j1<c1.length; j1++, j2++) {
				p1[i2][j2] = c1[i1][j1];
			}
	}

	/**
	 * Imprimir matriz
	 * @param array
	 */
	public static void printMatrix(int [][] matrix) {

		System.out.println();
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}