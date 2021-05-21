package dyv;

import java.util.Random;

import java.util.*;

public class TornillosTuercas {
	static class Tornillo{
		private int calibre;
		public Tornillo(int c) {
			// TODO Auto-generated constructor stub
			calibre=c;
		}
		public int getCalibre() {
			return calibre;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return " "+calibre;
		}
	}
	static class Tuerca{
		private int grosor;
		public Tuerca(int g) {
			// TODO Auto-generated constructor stub
			grosor=g;
		}
		public int getGrosor() {
			return grosor;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return " "+grosor;
		}
	}
	static Tornillo[] tornillos;
	static Tuerca[] tuercas;
	static int enrosca(Tornillo t, Tuerca r){
		return t.getCalibre()-r.getGrosor();
	}
	static void emparejar(Tornillo[] tornillos, Tuerca[] tuercas){
		emparejar(tornillos, tuercas, 0, tornillos.length-1);
	}
	private static void emparejar(Tornillo[] tornillos, Tuerca[] tuercas, int li, int ls){
		if (li<ls){
			Tuerca r=tuercas[(li+ls)/2]; //Tuerca pivote, puede ser cualquiera
			int izq=li;
			int der=ls;
			do{
				while (enrosca (tornillos[izq],r)<0) izq++;
				while (enrosca (tornillos[der],r)>0) der--;
				if (izq<der){//intercambiamos izq y der
					Tornillo aux=tornillos[izq];
					tornillos[izq]=tornillos[der];
					tornillos[der]=aux;
				}
			}while (izq!=der);
			Tornillo t=tornillos[der]; //Tornillo pivote, el que acabamos de colocar
			System.out.println("Tornillo pivote "+t.toString());
			izq=li;
			der=ls;
			do{
				while (enrosca (t, tuercas[izq])>0) izq++;
				while (enrosca (t, tuercas[der])<0) der--;
				if (izq<der){//intercambiamos izq y der
					Tuerca aux=tuercas[izq];
					tuercas[izq]=tuercas[der];
					tuercas[der]=aux;
				}
			}while (izq!=der);
			emparejar(tornillos, tuercas, li,der-1);
			emparejar(tornillos, tuercas, der+1,ls);
		}
	}
	static void generarTornillosTuercas(){
		Random rd=new Random();
		for (int m=0; m<tornillos.length; m++){
			int posTor=rd.nextInt(tornillos.length);
			while (tornillos[posTor]!=null)
				posTor=rd.nextInt(tornillos.length);
			tornillos[posTor]=new Tornillo(m);
			int posTuer=rd.nextInt(tuercas.length);
			while (tuercas[posTuer]!=null)
				posTuer=rd.nextInt(tuercas.length);
			tuercas[posTuer]=new Tuerca(m);
		}
	}
	
	public static void mostrar(Object[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.err.print(arr[i].toString() + " ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		//int numt=leer.entero("Introduce nº de tuercas y tornillos");
		Scanner teclado = new Scanner(System.in);
		System.out.println("leer numero: ");
		int numt = teclado.nextInt();
		tornillos=new Tornillo[numt];
		tuercas=new Tuerca[numt];
		generarTornillosTuercas();
		mostrar(tornillos);
		mostrar(tuercas);
		//System.out.println("Tornillos: "+MatricesOperaciones.mostrar(tornillos));
		//System.out.println("Tuercas: "+MatricesOperaciones.mostrar(tuercas));
		emparejar(tornillos, tuercas);
		mostrar(tornillos);
		mostrar(tuercas);
		//System.out.println("Tornillos: "+MatricesOperaciones.mostrar(tornillos));
		//System.out.println("Tuercas: "+MatricesOperaciones.mostrar(tuercas));
	}
}