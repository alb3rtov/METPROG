package greedy;

public class Pelicula {
	
	int duracion;
	int horaComienzo;
	int horaFin;
	int sala;
	
	public Pelicula(int duracion, int horaComienzo, int sala) {
		this.duracion = duracion;
		this.horaComienzo = horaComienzo;
		this.horaFin = horaComienzo + duracion;
		this.sala = sala;
	}
	
	public int getDuracion() {
		return duracion;
	}
	
	public int getHoraComienzo() {
		return horaComienzo;
	}	
	
	public int getHoraFin() {
		return horaFin;
	}
	
	public int getSala() {
		return sala;
	}
}
