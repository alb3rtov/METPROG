package backtracking;
/**
 * Countdown numbers game puzzle solver.
 * 
 * A fast yet bruteforce method of obtaining most solutions using only
 * primitive arrays. This numbers games can be played with any number of
 * numbers and any target. To keep within the rules of the game, operations
 * with non-integer results are not permitted.
 * Note that of all solutions explored, only the first solution found
 * is kept. The program keeps track of how long it took to find the first
 * solution, as well as how long it took to find all solutions (if any).
 * It can explore the entire problem space in 0.4 seconds on my Pentium II
 * 350MHz machine and about 3 seconds on my handheld Compaq iPAQ PocketPC,
 * which uses an Intel StrongARM 206MHz processor.
 * 
 * Note that this will only explore cases expressable in RPN.
 *
 * Copyright Paul Mutton, 2002.
 * http://www.jibble.org/
 *
 */
public class Countdown {
    
    public static final byte NOOP = 0;
    public static final byte ADD = 1;
    public static final byte SUBTRACT = 2;
    public static final byte MULTIPLY = 3;
    public static final byte DIVIDE = 4;
    
    /**
     * Constructor. Used to do something once, but not any more.
     */
    public Countdown() {
        
    }
    
    /**
     * Take an array of numbers and the target to seek.
     */
    public void play(int[] numbers, int target) {
        // Remember when we started...
        
        _target = target;
        _numbers = numbers;
        _solutionCount = 0;
        _solution = null;
        findSubsets(numbers, new boolean[numbers.length]);
        
        // Program ends here.
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
                permutate(playset, 0);
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
     * Find all permutations of the numbers in the array.
     * For each permutation, we call the operate method.
     * (recursive)
     */
    private void permutate(int[] numbers, int k) {
        if (k == numbers.length) {
            operate(numbers, new byte[numbers.length - 1], 0);
        }
        else {
            for (int i = k; i < numbers.length; i++) {
                int temp = numbers[i];
                numbers[i] = numbers[k];
                numbers[k] = temp;
                permutate((int[])numbers.clone(), k + 1);
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

            if (total == _target) {
                _solutionCount++;
                if (_solution == null) {
                    _successTime = System.currentTimeMillis();
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
                    _solution = solution.toString();
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
    
    public int getTarget() {
        return _target;
    }
    
    public int[] getNumbers() {
        return _numbers;
    }
    
    public long getSuccessTime() {
        return _successTime;
    }
    
    public int getSolutionCount() {
        return _solutionCount;
    }
    
    public String getSolution() {
        return _solution;
    }
    
    /**
     * Main method used to test the play method with a solvable numbers game.
     */
    public static void main(String[] args) {
        Countdown countdown = new Countdown();
        
        countdown.play(new int[]{25, 100, 9, 10, 10, 3}, 459);
        if (countdown.getSolutionCount() > 0) {
            //long t1 = countdown.getSuccessTime() - countdown.getStartTime();
            //long t2 = countdown.getEndTime() - countdown.getStartTime();
            //System.out.println("I found a solution in approximately " + t1 + " milliseconds. I found all " + countdown.getSolutionCount() + " solutions in about " + t2 + " milliseconds.");
            System.out.println("ibot " + countdown.getSolution());
        }
        else {
            System.out.println("Oh bugger, I don't think I can solve that one.");
        }
    }
    
    private int _target = 0;
    private int[] _numbers = new int[0];
    private long _successTime = -1;
    private int _solutionCount = 0;
    private String _solution = null;
        
}