package greedy;

import java.util.*;

public class maratoncine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
		generarPeliculas(peliculas);
		ArrayList<Pelicula> peliculasSeleccionadas = maratonCine(peliculas);
		
		
		for (int i = 0; i < args.length; i++) {
			System.out.println(peliculasSeleccionadas.get(i).getSala());
		}
		

	}
	
	public static void generarPeliculas(ArrayList<Pelicula> peliculas) {
		
		Pelicula p1 = new Pelicula(100, 0, 2);
		Pelicula p2 = new Pelicula(310, 130, 3);
		Pelicula p3 = new Pelicula(240, 353, 4);
		Pelicula p4 = new Pelicula(160, 300, 5);
		Pelicula p5 = new Pelicula(120, 160, 1);
		Pelicula p6 = new Pelicula(140, 300, 6);
		
		peliculas.add(p1);
		peliculas.add(p2);
		peliculas.add(p3);
		peliculas.add(p4);
		peliculas.add(p5);
		peliculas.add(p6);
	}

	public static boolean esFactible(Pelicula pelicula, ArrayList<Pelicula> peliculasSeleccionadas) {
		
		boolean factible = false;
		
		if (peliculasSeleccionadas.isEmpty()) {
			factible = true;
		} else {
			if (pelicula.getHoraComienzo() > peliculasSeleccionadas.get(peliculasSeleccionadas.size()-1).getHoraFin()) {
				factible = true;
			}
		}
		
		return factible;
	}	
	
	
	public static ArrayList<Pelicula> maratonCine(ArrayList<Pelicula> peliculas) {
		Collections.sort(peliculas, Comparator.comparing(Pelicula::getHoraFin));
		/*
		for (int i = 0; i < peliculas.size(); i++) {
			System.out.println(peliculas.get(i).getHoraFin());
		}		*/
		
		ArrayList<Pelicula> peliculasSeleccionadas = new ArrayList<Pelicula>();
		
		while (!peliculas.isEmpty()) {
			Pelicula pelicula = peliculas.get(0);
			peliculas.remove(0);
			
			if (esFactible(pelicula, peliculasSeleccionadas)) {
				peliculasSeleccionadas.add(pelicula);
			}
			
		}
		
		return peliculasSeleccionadas;

	}
}
