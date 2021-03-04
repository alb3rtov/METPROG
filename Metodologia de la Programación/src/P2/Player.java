package P2;

public class Player {
	
	int experience;
	int tasks;
	int rage;
	
	public Player(int experience, int tasks, int rage) {
		this.experience = experience;
		this.tasks = tasks;
		this.rage = rage;
	}
	
	public int getExperience() {
		return experience;
	}
	
	public int getTasks() {
		return tasks;
	}
	
	public int getRage() {
		return rage;
	}	
	
	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	public void setTasks(int tasks) {
		this.tasks = tasks;
	}
	
	public void setRage(int rage) {
		this.rage = rage;
	}
	
	public String toString() {
		String info = "";
		
		info += "Experiencia: " + experience + "\n";
		info += "Tareas: " + tasks + "\n";
		info += "Ira: " + rage + "\n";
		
		return info;
	}
}
