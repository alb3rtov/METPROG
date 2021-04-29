/*********************************************************
 * 
 * Class Name: Solver.java
 * Clase que se encarga de generar todas las soluciones todas posibles
 * del problema e imprimirlas por pantalla.
 * @author Alberto Vázquez Martínez y Ángel Villafranca Iniesta
 * 
 *********************************************************/

package P4;

public class Solver {
    
    private int targetNumber = 0;
    private int[] numbers = new int[0];
    private int solutionCount = 0;
    private String solution = null;
   
    /* A cada operación se le asigna un valor númerico */
	public static final byte NOOP = 0;
    public static final byte ADD = 1;
    public static final byte SUBTRACT = 2;
    public static final byte MULTIPLY = 3;
    public static final byte DIVIDE = 4;
    
    /* Constructor de la clase */
    public Solver() {
    	
    }
    
    /**
     * Asigna los numeros base y el número objetivo a 
     * los atributos de la clase y los imprime por pantalla.
     * @param numbers
     * @param targetNumber
     */
    public void solve(int[] numbers, int targetNumber) {
        
    	this.targetNumber = targetNumber;
        this.numbers = numbers;
        this.solutionCount = 0;
        this.solution = null;
        
        System.out.print("Números base: ");
        for (int i = 0; i < this.numbers.length; i++) {
			System.out.print(this.numbers[i] + " ");
		}
        
        System.out.println("\nNúmero objetivo: " + this.targetNumber);
        
        findSubsets(this.numbers, new boolean[this.numbers.length]);
    }
    
    /**
     * When solving the puzzle, we do not have to use all of the numbers.
     * So find all subsets of the numbers we have been given, before then
     * using the permutate method to find all permutations of these.
     */
    private void findSubsets(int[] numbers, boolean[] members) {
        for (int setNum = 0; setNum < Math.pow(2, numbers.length); setNum++) {
            int size = 0;
            for (int i = 0; i < numbers.length; i++) {
                if (members[i]) {
                    size++;
                }
            }
            int[] playset = new int[size];
            
            // fill the array
            int pos = 0;
            for (int i = 0; i < numbers.length; i++) {
                if (members[i]) {
                    playset[pos] = numbers[i];
                    pos++;
                }
            }
            
            if (playset.length > 0) {
            	generateResults(playset, 0);
            }
            
            // jibble the bits to the next bit pattern
            int i = numbers.length - 1;
            while (i >= 0 && members[i]) {
                members[i] = false;
                i--;
            }
            if (i >= 0) {
                members[i] = true;
            }
        }
    }
    
    /**
     * Encuentra todas las combinaciones de los numeros y por cada
     * una de las combinaciones realiza 4 operaciones.
     * @param numbers
     * @param n
     */
    private void generateResults(int[] numbers, int n) {
        if (n == numbers.length) {
            operate(numbers, new byte[numbers.length - 1], 0);
        }
        else {
            for (int i = n; i < numbers.length; i++) {
                int temp = numbers[i];
                numbers[i] = numbers[n];
                numbers[n] = temp;
                generateResults((int[])numbers.clone(), n + 1);
                
            }
        }
    }
    
    /**
     * Take a permutation of numbers and try all mathematical combinations.
     * Division is not allowed if there is a non-integral result.
     * If the target is found, then the solution is printed to the standard
     * output, using as few brackets as possible.
     * (recursive)
     */
    private void operate(int[] numbers, byte[] operands, int pos) {
        if (pos == numbers.length) {
            // calculate the answer
            int total = numbers[0];
            for (int i = 1; i < numbers.length; i++) {
                switch (operands[i - 1]) {
                    case ADD:
                        total += numbers[i];
                        break;
                    case SUBTRACT:
                        total -= numbers[i];
                        break;
                    case MULTIPLY:
                        total *= numbers[i];
                        break;
                    case DIVIDE:
                        if (total % numbers[i] != 0) {
                            // Can't allow non-integer divisions in this game!
                            return;
                        }
                        total /= numbers[i]; 
                        break;
                }
            }

            if (total == this.targetNumber) {
                this.solutionCount++;
                if (this.solution == null) {
                    StringBuffer solution = new StringBuffer();
                    byte lastOp = NOOP;
                    for (int i = 0; i < numbers.length - 1; i++) {
                        solution.append(numbers[i]);
                        if (lastOp != NOOP && operands[i] >= 3 && lastOp <= 2) {
                            solution.append(")");
                            solution.insert(0, "(");
                        }
                        lastOp = operands[i];
                        switch (operands[i]) {
                            case ADD:
                                solution.append(" + ");
                                break;
                            case SUBTRACT:
                                solution.append(" - ");
                                break;
                            case MULTIPLY:
                                solution.append("*");
                                break;
                            case DIVIDE:
                                solution.append("/");
                                break;
                        }
                    }
                    solution.append(numbers[numbers.length - 1]);
                    this.solution = solution.toString();
                }
            }
        }
        else {
            if (pos == 0) {
                operate(numbers, operands, ++pos);
            }
            else {
                int newPos = pos + 1;
                int previousPos = pos - 1;
                operands[previousPos] = ADD;
                operate(numbers, operands, newPos);
                operands[previousPos] = SUBTRACT;
                operate(numbers, operands, newPos);
                operands[previousPos] = MULTIPLY;
                operate(numbers, operands, newPos);
                operands[previousPos] = DIVIDE;
                operate(numbers, operands, newPos);
            }
        }
    }
    
    /* Getters */
    public int getTarget() {
        return this.targetNumber;
    }
    
    public int[] getNumbers() {
        return this.numbers;
    }
    
    public int getSolutionCount() {
        return this.solutionCount;
    }
    
    public String getSolution() {
        return this.solution;
    }    
}

/*
public class Solver {

	public static void solve(ArrayList<Integer> list) {
		ArrayList<Operation> solutionsList = new ArrayList<Operation>();		
		
		System.out.print("Lista de números: ");
		int targetNumber = list.remove(list.size()-1); //El último elemento de la lista es el número objetivo.
		
		//for (int i = 0; i < list.size(); i++) {
		//	System.out.print(list.get(i) + " ");
		//}
		
		//System.out.println("\nNúmero objetivo: " + targetNumber);
		
		
		List<Integer> listNumbers = Arrays.asList(25,100,9,10,10,3);
		ArrayList<Integer> lista =  new ArrayList<Integer>();		
		lista.addAll(listNumbers);
		targetNumber = 459;
		System.out.println(lista);
		System.out.println("\nNúmero objetivo: " + targetNumber);
		
		generateAllSolutions(lista, solutionsList, 0, targetNumber);
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
	
	public static void generateAllSolutions(ArrayList<Integer> list, ArrayList<Operation> solutionsList, int currentResult, int targetNumber) {
		
		int recursiveCalls = 0;
		ArrayList<Integer> partialSolutions = new ArrayList<Integer>();
		
		if (currentResult == targetNumber) {

			for (int i = 0; i < solutionsList.size(); i++) {
				System.out.print(solutionsList.get(i).toString());
			}

		} else {
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < list.size(); j++) {
					if (i != j) {
						currentResult += list.get(i) + list.get(j);
						if (currentResult <= targetNumber) {
							Operation operation = new Operation(1,2,"+");
							solutionsList.add(operation);
							generateAllSolutions(list, solutionsList, currentResult, targetNumber);
							solutionsList.remove(solutionsList.size()-1);
						}
						currentResult -= list.get(i) + list.get(j);
						//if (list.get(i) >= list.get(j)) {
							//recursiveCalls++;
							//partialSolutions.add(list.get(i)-list.get(j));
						//	System.out.println("Resta de " + list.get(i) + " y " + list.get(j) + " = " + (list.get(i) - list.get(j)));
						//} 
						
						//if (list.get(i) % list.get(j) == 0) {
							//recursiveCalls++;
							//partialSolutions.add(list.get(i)/list.get(j));
						//	System.out.println("División de " + list.get(i) + " y " + list.get(j) + " = " + (list.get(i) / list.get(j)));
						//}
							
						//if (list.get(i) <= list.get(j)) {
							//recursiveCalls+=2;
							//partialSolutions.add(list.get(i)+list.get(j));
							//partialSolutions.add(list.get(i)*list.get(j));
						//	System.out.println("Suma de " + list.get(i) + " y " + list.get(j) + " = " + (list.get(i) + list.get(j)));
						//	System.out.println("Multiplicación de " + list.get(i) + " y " + list.get(j) + " = " + (list.get(i) * list.get(j)));
						//}
						
						//for (int k = 0; k < recursiveCalls; k++) {
						//	generateAllSolutions(list, solutionsList, currentResult, targetNumber);
						//}
						
						
					}
				}
				//System.out.println("\nSiguiente");
			}
		}
		
	}
	
	public static void printResults() {
		
	}
}*/
