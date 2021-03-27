package P3;

public class Eopie {

	private double maxLiters;
	private boolean isCarryingContainer;
	
	public Eopie(double maxLiters) {
		this.maxLiters = maxLiters;
	}
	
	public double getMaxLiters() {
		return maxLiters;
	}
	
	public boolean getIsCarryingContainer() {
		return isCarryingContainer;
	}
	
	public void setMaxLiters(double maxLiters) {
		this.maxLiters = maxLiters;
	}
	
	public void setIsCarryingContainer(boolean isCarryingContainer) {
		this.isCarryingContainer = isCarryingContainer;
	}
}
