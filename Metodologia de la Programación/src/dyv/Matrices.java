package dyv;

public class Matrices {

	/**
	 * comprobamos la longitud y hacemos la llamada recursiva
	 */
	public static int[][] multiplicaDAC(int[][]A,int[][]B){
		int[][]res = new int[A.length][B[0].length];
		// habria que comprobar dim > 0 dimA = dimB y dim 2^n
		//if(A.length > 0 && A.length==A[0].length && B.length==B[0].length 
		// && A.length==B.length && esPotencia2(A.length)) 
		res= multiplicaDAC(A,B,A.length);


		return res;
	}

	/**
	 * algoritmo
	 */
	public static int[][] multiplicaDAC(int[][]A,int[][]B,int dim){

		int[][]res= new int[dim][dim];

		if(dim==1) { // caso base.
			res[0][0]=A[0][0]*B[0][0];
		} else{
			int[][]A11=divide(A,0,0,dim/2);
			int[][]A12=divide(A,0,dim/2,dim/2);
			int[][]A21=divide(A,dim/2,0,dim/2);
			int[][]A22=divide(A,dim/2,dim/2,dim/2);

			int[][]B11=divide(B,0,0,dim/2);
			int[][]B12=divide(B,0,dim/2,dim/2);
			int[][]B21=divide(B,dim/2,0,dim/2);
			int[][]B22=divide(B,dim/2,dim/2,dim/2);

			poner(res,suma(multiplicaDAC(A11,B11,dim/2),multiplicaDAC(A12,B21,dim/2)),0,0); 
			poner(res,suma(multiplicaDAC(A11,B12,dim/2),multiplicaDAC(A12,B22,dim/2)),0,dim/2); 
			poner(res,suma(multiplicaDAC(A21,B11,dim/2),multiplicaDAC(A22,B21,dim/2)),dim/2,0); 
			poner(res,suma(multiplicaDAC(A21,B12,dim/2),multiplicaDAC(A22,B22,dim/2)),dim/2,dim/2);
		}
		return res;
	}

	/*
	 * coloca una submatriz a partir de una posición
	 */
	private static void poner(int[][] res, int[][]A,int f, int c){
		for(int fil=0;fil<A.length;fil++)
			for(int col=0;col<A[0].length;col++) 
				res[f+fil][c+col]=A[fil][col];
	}

	/*
	 * extrae una submatrix de una matrix dada
	 */
	public static int[][] divide(int[][]M,int f,int c,int d){
		int[][]res=new int[d][d];
		for(int fil=0;fil<d;fil++)
			for(int col=0;col<d;col++) 
				res[fil][col]=M[f+fil][c+col];
		return res;
	}

	/* 
	 * suma dos matrices de la misma dimensión
	 */
	public static int[][] suma(int[][]A,int[][]B){
		int[][]res=new int[A.length][B[0].length];
		// comprobamos que la dimensión no sea cero y que sean iguales.
		if (A.length > 0 && A[0].length > 0 
				&& A.length == B.length && A[0].length == B[0].length)
			for(int f=0;f<A.length;f++) 
				for(int c=0;c < A[0].length;c++) 
					res[f][c] = A[f][c] + B[f][c];
		return res;
	}

	/**
	 * mostrar la matriz
	 */
	public static void printMatrix(int[][] A)
	{
		for(int i=0;i<A.length;i++)
		{
			for(int j=0;j<A.length;j++)
				System.out.print(A[i][j]+"\t");
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
		int n= 4;
		int arr1[][]={{1,2,3,4},{3,2,7,9},{5,8,3,3},{0,2,2,1}};
		int arr2[][]={{2,0,3,0},{6,6,1,1},{7,3,1,3},{9,0,0,4}};
		int arr3[][] = Matrices.multiplicaDAC(arr1, arr2);
		Matrices.printMatrix(arr3);
	}
}




