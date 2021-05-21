package dyv;

public class Traspuesta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][]m = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		imprimir(m);
		traspuestaDAC(m);
		System.out.println();
		imprimir(m);
	}
	
	public static void imprimir(int[][]m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void traspuestaDAC(int[][]m){
		if(m.length>1 && m.length==m[0].length && esPotenciaDe2(m.length)) //algunas comprobaciones previas
			traspuestaDAC(m,0,0,m.length);
	}
	
	public static void traspuestaDAC(int[][]m,int f,int c,int dim){
		if(dim>1){
			traspuestaDAC(m,f,c,dim/2);
			traspuestaDAC(m,f,c+dim/2,dim/2);
			traspuestaDAC(m,f+dim/2,c,dim/2);
			traspuestaDAC(m,f+dim/2,c+dim/2,dim/2);
			combinar(m,f,c,dim/2);
		}
	}
	
	public static void combinar(int[][]m,int f,int c,int dim){
		int[][]aux=new int[dim][dim];
		aux=copia(m,f,c+dim,dim);
		aux=pasar(m,f+dim,c,aux);//pasacuadrante2al3ydevuelveel3
		aux=pasar(m,f,c+dim,aux);//pasacuadrante3al2
	}
	
	private static int[][]pasar(int[][]m,int f,int c,int[][]orig){
		int dim=orig.length;
		int[][]aux=new int[dim][dim];
		for(int n=0;n<dim;n++)
			for(int p=0;p<dim;p++){
				aux[n][p]=m[f+n][c+p];
				m[f+n][c+p]=orig[n][p];
			}
		return aux;
	}

	private static int[][] copia(int[][]m,int f,int c,int dim){
		int[][]aux=new int[dim][dim];
		for(int n=0;n<dim;n++) {
			for(int p=0;p<dim;p++) {
				aux[n][p]=m[f+n][c+p];
			}
		}
		return aux;
	}
	public static boolean esPotenciaDe2(int n){
		boolean esPot=true;
		while(n>1&&esPot){
			esPot=n%2==0;
			n=n/2;
		}
		return esPot;
	}

}
