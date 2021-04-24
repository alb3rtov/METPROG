package P4;

public class Operation {
	
	private int operator1;
	private int operator2;
	private String operationType;

	public Operation(int operator1, int operator2, String operationType) {
		this.operator1 = operator1;
		this.operator2 = operator2;
		this.operationType = operationType;
	}
	
	public int getOperator1() {
		return this.operator1;
	}
	
	public int getOperator2() {
		return this.operator2;
	}
	
	public String getOperationType() {
		return this.operationType;
	}
	
}
