package ejercicioB2;

import java.util.ArrayList;

public class ejercicio9 {

	public static void main(String[] args) {
		
		ArrayList<Integer> ns = new ArrayList<Integer>();
		ns.add(1);
		ns.add(2);
		ns.add(1);

		System.out.println(method(ns, 0, ns.size()-1));

	}
	
	public static int method(ArrayList<Integer> ns, int li, int ls) {
		int n = -1;
		int sumIzq = 0;
		int sumDer = 0;
		int middle = 0;
		
		if (li==ls) {
			n = li;
		} 
		else {		
			if (ns.size()%2==0) {
				middle = (li + ls)/2;
				sumIzq += method(ns, li, middle);
				sumDer += method(ns, middle+1, ls);
				
			} else {
				middle = (li + ls)/2;
				sumIzq += method(ns, li , middle-1);
				sumDer += method(ns, middle+1 , ls);
			}
			
			if (sumIzq == 2) {
				n = sumIzq;
			} else if (sumDer == 2) {
				n = sumDer;
			}
		}
		
		return n;
	}
}
