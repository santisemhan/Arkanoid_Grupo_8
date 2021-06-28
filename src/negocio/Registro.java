package negocio;
import java.util.*;

public class Registro {
	
	private ArrayList<Jugador> jugadores;

    public Registro() {
		this.jugadores = new ArrayList<Jugador>();
	}

    /**Recibe un nuevo nombre y puntaje para agregar al registro. 
     * Si el registro esta lleno se elimina al jugador de la ultima posicion*/
	public void agregarJugador(String nombreJugador, int puntaje) {
		if(getCantidad()==20)
			this.deleteJugador();
		Jugador nuevoJ = new Jugador(nombreJugador,puntaje);
		jugadores.add(nuevoJ);
		jugadores.sort(new SortByScore());
	}

	/**se ordena el arrayList de manera decreciente*/
    class SortByScore implements Comparator<Jugador> {
    	public int compare(Jugador a, Jugador b){
    		return b.getPuntaje() - a.getPuntaje();
    	}
    }
    
    /**Se elimina el ultimo jugador de jugadores*/
    private void deleteJugador() {
        jugadores.remove(19);
    }

    /**Comprueba si el puntaje de la partida pasada como parametro es mayor que el ultimo jugador del arraylist*/
    public boolean comprobarPuntaje(Partida partida) {
        if (getCantidad()==20) {
        	if(partida.getPuntaje() > jugadores.get(19).getPuntaje()) {
        		return true;
        	}
        	else {
        		return false;
        	}
        }
        return true;
    }

    private int getCantidad() {
        return jugadores.size();
    }

}