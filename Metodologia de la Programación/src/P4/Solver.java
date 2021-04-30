/*********************************************************
 * 
 * Class Name: Solver.java
 * Clase que se encarga de generar todas las soluciones todas posibles
 * del problema e imprimirlas por pantalla.
 * @author Alberto Vázquez Martínez y Ángel Villafranca Iniesta
 * 
 *********************************************************/

package P4;

import java.util.ArrayList;

public class Solver {
    
    private int targetNumber = 0;
    private int[] numbers = new int[0];
    private int solutionCount = 0;
    private int bestSolutionsCount = 0;
    private ArrayList<String> solutions = new ArrayList<String>();
    private int amountOfNumbersBestSolution = 999;
    
    /* A cada operación se le asigna un valor númerico */
	public static final byte NOOP = 0;
    public static final byte ADD = 1;
    public static final byte SUBTRACT = 2;
    public static final byte MULTIPLY = 3;
    public static final byte DIVIDE = 4;
    
    /* Constructor de la clase */
    public Solver() {}
    
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
        this.bestSolutionsCount = 0;
        
        System.out.print("Números base: ");
        for (int i = 0; i < this.numbers.length; i++) {
			System.out.print(this.numbers[i] + " ");
		}
        
        System.out.println("\nNúmero objetivo: " + this.targetNumber);
        
        combineAllNumbers(this.numbers, new boolean[this.numbers.length]);
    }
    
    /**
     * When solving the puzzle, we do not have to use all of the numbers.
     * So find all subsets of the numbers we have been given, before then
     * using the permutate method to find all permutations of these.
     */
    private void combineAllNumbers(int[] numbers, boolean[] members) {
        for (int i = 0; i < Math.pow(2, numbers.length); i++) {
            int size = 0;
            for (int j = 0; j < numbers.length; j++) {
                if (members[j]) {
                    size++;
                }
            }
            int[] playset = new int[size];
            
            // fill the array
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
            
            // jibble the bits to the next bit pattern
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
     * Encuentra todas las combinaciones de los numeros y por cada
     * una de las combinaciones realiza 4 operaciones.
     * @param numbers
     * @param n
     */
    private void generateResults(int[] numbers, int n) {
    	/* Caso base */
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
                            return;
                        }
                        total /= numbers[i]; 
                        break;
                }
            }

            if (total == this.targetNumber) {
        		this.solutionCount++;
            	if (amountOfNumbersBestSolution >= numbers.length) {
            		this.bestSolutionsCount++;
            		this.amountOfNumbersBestSolution = numbers.length;
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
            		this.solutions.add(solution.toString());
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
    
    public int getBestSolutionCount() {
        return this.bestSolutionsCount;
    }
       
    public ArrayList<String> getSolutions() {
    	return this.solutions;
    }
}