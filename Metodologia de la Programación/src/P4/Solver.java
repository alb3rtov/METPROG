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
    private int[] numbers = new int[0];
    private int solutionsCount = 0;
    private int bestSolutionsCount = 0;
    private ArrayList<String> solutions = new ArrayList<String>();
    private int amountOfNumbersBestSolution = 999;
    
    /* A cada operación se le asigna un valor númerico */
    public static final byte SUM = 1;
    public static final byte SUBTRACTION = 2;
    public static final byte MULTIPLICATION = 3;
    public static final byte DIVISION = 4;
	public static final byte NO_OPERATION = 0;
	
	/* Signo de los diferentes operadores */
    public static final String CR_SUM =  " + ";
    public static final String CR_SUBTRACTION =  " - ";
    public static final String CR_MULTIPLICATION =  " * ";
    public static final String CR_DIVISION =  " / ";
    
    /* Constructor de la clase */
    public Solver(int[] numbers, int targetNumber) {
    	this.targetNumber = targetNumber;
        this.numbers = numbers;
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
        combineAllCombinations(this.numbers, new boolean[this.numbers.length]);
    }
    
    /**
     * Encuentra todas las combinaciones de números posibles
     * del conjunto de número generado aleatoriamente
     * @param numbers
     * @param members
     */
    private void combineAllCombinations(int[] numbers, boolean[] members) {
        for (int i = 0; i < Math.pow(2, numbers.length); i++) {
            int size = 0;
            for (int j = 0; j < numbers.length; j++) {
                if (members[j]) {
                    size++;
                }
            }
            int[] playset = new int[size];
            
            // Generar el array de combinaciones en cada iteración
            int pos = 0;
            for (int j = 0; j < numbers.length; j++) {
                if (members[j]) {
                    playset[pos] = numbers[j];
                    pos++;
                }
            }
            
            if (playset.length > 0) {
            	generateResults(playset, 0);
            }
            
            int k = numbers.length - 1;
            while (k >= 0 && members[k]) {
                members[k] = false;
                k--;
            }
            if (k >= 0) {
                members[k] = true;
            }
        }
    }
    
    /**
     * Genera los resultados factibles, operando los numeros con las 4 operaciones aritmeticas.
     * @param numbers
     * @param n
     */
    private void generateResults(int[] numbers, int n) {
        if (n == numbers.length) {  /* Caso base */
            operate(numbers, new byte[numbers.length - 1], 0);
        } else {
            for (int i = n; i < numbers.length; i++) {
                int temp = numbers[i];
                numbers[i] = numbers[n];
                numbers[n] = temp;
                generateResults((int[])numbers.clone(), n + 1);       
            }
        }
    }
    
    /**
     * Comprueba si en el array de soluciones existen soluciones con
     * mas operadores/operandos que la nueva solución encontrada. Si existen, las elimina.
     * @param numberOfCurrentOperators
     */
    private void checkPreviousSolutions(int numberOfCurrentOperators) {
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
     * Realiza las operaciones entre los números y 
     * genera los strings de soluciones válidas.
     * @param numbers
     * @param operands
     * @param pos
     */
    private void operate(int[] numbers, byte[] operands, int pos) {
        if (pos == numbers.length) {
            int total = numbers[0];
            for (int i = 1; i < numbers.length; i++) {
                switch (operands[i - 1]) {
                    case SUM:
                        total += numbers[i];
                        break;
                    case SUBTRACTION:
                        total -= numbers[i];
                        break;
                    case MULTIPLICATION:
                        total *= numbers[i];
                        break;
                    case DIVISION:
                        if (total % numbers[i] != 0) {
                            return;
                        }
                        total /= numbers[i]; 
                        break;
                }
            }

            if (total == this.targetNumber) { /* Comprobar si es igual al número objetivo */
        		this.solutionsCount++;
            	if (this.amountOfNumbersBestSolution >= numbers.length) { /* Comprobar si hay más operandos en la operación anterior */
            		
            		checkPreviousSolutions(numbers.length-1);
            		
            		this.bestSolutionsCount++;
            		this.amountOfNumbersBestSolution = numbers.length;
            		
            		StringBuffer foundSolution = new StringBuffer();
            		byte lastOp = NO_OPERATION;
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
            }
        } else {
        	if (pos == 0) {
        		operate(numbers, operands, ++pos);
        	} else {
        		int newPos = pos + 1;
        		int previousPos = pos - 1;
        		operands[previousPos] = SUM;
        		operate(numbers, operands, newPos);
        		operands[previousPos] = SUBTRACTION;
        		operate(numbers, operands, newPos);
        		operands[previousPos] = MULTIPLICATION;
        		operate(numbers, operands, newPos);
        		operands[previousPos] = DIVISION;
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
        return this.solutionsCount;
    }
    
    public int getBestSolutionCount() {
        return this.bestSolutionsCount;
    }
       
    public ArrayList<String> getSolutions() {
    	return this.solutions;
    }
}