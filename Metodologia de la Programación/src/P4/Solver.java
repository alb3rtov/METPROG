/*********************************************************
 * 
 * Class Name: Solver.java
 * Clase que se encarga de generar todas las soluciones 
 * todas posibles del problema y solo quedarse con las mejores
 * @author Alberto Vázquez Martínez y Ángel Villafranca Iniesta
 * 
 *********************************************************/

package P4;

import java.util.ArrayList;

public class Solver {
    
	/* Atributos de la clase */
    private int targetNumber = 0;
    private int solutionsCount = 0;
    private int bestSolutionsCount = 0;
    private int[] numbers = new int[0];
    private int amountOfNumbersBestSolution;
    private ArrayList<String> solutions = new ArrayList<String>();

    
    /* A cada operación se le asigna un valor númerico */
    public static final int SUM = 1;
    public static final int SUBTRACTION = 2;
    public static final int MULTIPLICATION = 3;
    public static final int DIVISION = 4;
	public static final int NO_OPERATION = 0;
	
	/* Signo de los diferentes operadores */
    public static final String CR_SUM =  " + ";
    public static final String CR_SUBTRACTION =  " - ";
    public static final String CR_MULTIPLICATION =  " * ";
    public static final String CR_DIVISION =  " / ";
    
    /* Constructor de la clase */
    public Solver(int[] numbers, int targetNumber) {
    	this.targetNumber = targetNumber;
        this.numbers = numbers;
        this.amountOfNumbersBestSolution = 999;
    }
    
    /**
     * Asigna los numeros base y el número objetivo a 
     * los atributos de la clase y los imprime por pantalla.
     * @param numbers
     * @param targetNumber
     */
    public void solve() {
        System.out.print("Números base: ");
        for (int i = 0; i < this.numbers.length; i++) {
			System.out.print(this.numbers[i] + " ");
		}
        
        System.out.println("\nNúmero objetivo: " + this.targetNumber);
        boolean combinations[] = new boolean[this.numbers.length];
        generateAllCombinations(this.numbers, combinations);
    }
    
    /**
     * Generar el array de combinaciones en cada iteración
     * @param playSet
     * @param numbers
     * @param combinations
     */
    void generateCurrentPlaySet(int[] playSet, int[] numbers, boolean[] combinations) {
        int pos = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (combinations[i]) {
            	playSet[pos] = numbers[i];
                pos++;
            }
        }
    }
    
    /**
     * Array de booleanos para cada combinación
     * @param numbers
     * @param combinations
     */
    void setCombinations(int[] numbers, boolean[] combinations) {
        int k = numbers.length - 1;
        while (k >= 0 && combinations[k]) {
        	combinations[k--] = false;
        }
        if (k >= 0) {
        	combinations[k] = true;
        }
    }
    
    /**
     * Encuentra todas las combinaciones de números posibles
     * del conjunto de número generado aleatoriamente
     * @param numbers
     * @param members
     */
    void generateAllCombinations(int[] numbers, boolean[] combinations) {
        for (int i = 0; i < Math.pow(2, numbers.length); i++) {
            int size = 0;
            for (int j = 0; j < numbers.length; j++) {
                if (combinations[j]) {
                    size++;
                }
            }
            
            int[] playSet = new int[size];
            
            generateCurrentPlaySet(playSet, numbers, combinations);
            
            /* Si contiene numeros el conjunto actual, generar los resultados */
            if (playSet.length > 0) {
            	generateResults(playSet, 0);
            }
            
            setCombinations(numbers, combinations);
        }
    }
    
    /**
     * Genera los resultados factibles, operando los numeros con las 4 operaciones aritmeticas.
     * @param numbers
     * @param n
     */
    void generateResults(int[] numbers, int n) {
        if (n == numbers.length) {  /* Caso base */
        	calculate(numbers, new int[numbers.length - 1], 0);
        } else {
            for (int i = n; i < numbers.length; i++) {
                int auxNumber= numbers[i];
                numbers[i] = numbers[n];
                numbers[n] = auxNumber;
                generateResults((int[]) numbers.clone(), n + 1);       
            }
        }
    }
    
    /**
     * Comprueba si en el array de soluciones existen soluciones con
     * mas operadores/operandos que la nueva solución encontrada. Si existen, las elimina.
     * @param numberOfCurrentOperators
     */
    void checkPreviousSolutions(int numberOfCurrentOperators) {
    	int counter = 0;
    	for (int i = 0; i < solutions.size()-1; i++) {
    		for (int j = 0; j < solutions.get(i).length(); j++ ) {
    			if (solutions.get(i).charAt(j) == CR_SUM.charAt(1) || 
    				solutions.get(i).charAt(j) == CR_SUBTRACTION.charAt(1) ||
    				solutions.get(i).charAt(j) == CR_MULTIPLICATION.charAt(1) ||
    				solutions.get(i).charAt(j) == CR_DIVISION.charAt(1)) {
    				counter++;
    			}
    		}
    		
    		if (counter > numberOfCurrentOperators) {
    			solutions.remove(i);
    			bestSolutionsCount--;
    		}
    		counter = 0;
    	}
    }
    
    /**
     * Añadir numeros y operandos al arraylist de soluciones
     * @param numbers
     * @param operands
     */
    void addToBestSolutions(int[] numbers, int[] operands) {
    	this.bestSolutionsCount++;
		this.amountOfNumbersBestSolution = numbers.length;
		
		StringBuffer foundSolution = new StringBuffer();
		int lastOp = NO_OPERATION;
		for (int i = 0; i < numbers.length - 1; i++) {
			foundSolution.append(numbers[i]);
			if (lastOp != NO_OPERATION && operands[i] >= 3 && lastOp <= 2) {
				foundSolution.append(")");
				foundSolution.insert(0, "(");
			}
			lastOp = operands[i];
			switch (operands[i]) {
			case SUM:
				foundSolution.append(CR_SUM);
				break;
			case SUBTRACTION:
				foundSolution.append(CR_SUBTRACTION);
				break;
			case MULTIPLICATION:
				foundSolution.append(CR_MULTIPLICATION);
				break;
			case DIVISION:
				foundSolution.append(CR_DIVISION);
				break;
			}
		}
		foundSolution.append(numbers[numbers.length - 1]);
		this.solutions.add(foundSolution.toString());
    }

    /**
     * Realiza las operaciones entre los números y 
     * genera los strings de soluciones válidas.
     * @param numbers
     * @param operands
     * @param position
     */
    void calculate(int[] numbers, int[] operands, int position) {
    	if (position == numbers.length) {
    		int totalResult = numbers[0];
    		for (int i = 1; i < numbers.length; i++) {
    			switch (operands[i-1]) {
    			case SUM:
    				totalResult += numbers[i];
    				break;
    			case SUBTRACTION:
    				totalResult -= numbers[i];
    				break;
    			case MULTIPLICATION:
    				totalResult *= numbers[i];
    				break;
    			case DIVISION:
    				if (totalResult % numbers[i] != 0) { /* Comprueba si la división da de resto 0 */
    					return;
    				}
    				totalResult /= numbers[i]; 
    				break;
    			}
    		}

    		if (totalResult == this.targetNumber) { /* Comprobar si es igual al número objetivo */
    			this.solutionsCount++;
    			if (this.amountOfNumbersBestSolution >= numbers.length) { /* Comprobar si hay más operandos en la operación anterior */	
    				checkPreviousSolutions(numbers.length-1);
    				addToBestSolutions(numbers, operands);
    			}
    		}
    		
    	} else {
    		if (position == 0) {
    			calculate(numbers, operands, ++position);
        	} else {
        		int newPosition = position + 1;
        		int previousPosition = position - 1;
        		operands[previousPosition] = SUM;
        		calculate(numbers, operands, newPosition);
        		operands[previousPosition] = SUBTRACTION;
        		calculate(numbers, operands, newPosition);
        		operands[previousPosition] = MULTIPLICATION;
        		calculate(numbers, operands, newPosition);
        		operands[previousPosition] = DIVISION;
        		calculate(numbers, operands, newPosition);
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
        return this.solutionsCount;
    }
    
    public int getBestSolutionCount() {
        return this.bestSolutionsCount;
    }
       
    public ArrayList<String> getSolutions() {
    	return this.solutions;
    }
}