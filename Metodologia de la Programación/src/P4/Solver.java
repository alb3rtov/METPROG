/*********************************************************
 * 
 * Class Name: Solver.java
 * Clase que se encarga de generar todas las soluciones todas posibles
 * del problema e imprimirlas por pantalla.
 * @author Alberto V�zquez Mart�nez y �ngel Villafranca Iniesta
 * 
 *********************************************************/

package P4;

import java.util.ArrayList;

public class Solver {
	
	public static void solve(ArrayList<Integer> list) {
		ArrayList<Operation> solutionsList = new ArrayList<Operation>();		
		int targetNumber = list.remove(list.size()-1); //El �ltimo elemento de la lista es el n�mero objetivo.
		
		generateAllSolutions(list, solutionsList, targetNumber);
		printResults();
		
	}
	
	public static boolean isSolution() {
		boolean solution = false;
		
		return solution;
	}
	
	public static void addSolution(ArrayList<Operation>solutionsList) {
		
	}
	
	public static boolean isFeasible() {
		boolean feasible = false;
		
		return feasible;
	}
	
	public static void generateAllSolutions(ArrayList<Integer> list, ArrayList<Operation> solutionsList, int targetNumber) {
		
		if (isSolution()) {
			addSolution(solutionsList);
			
		} else {

			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < list.size(); j++) {
					
					if (isFeasible()) {
						
						generateAllSolutions(list, solutionsList, targetNumber);
					}
					//Probar las cuatro operaciones

					

				}
			}
		}
	}
	
	public static void printResults() {
		
	}
}
