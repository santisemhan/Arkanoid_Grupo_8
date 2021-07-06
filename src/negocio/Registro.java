package negocio;
import java.util.*;

import views.JugadorView;

public class Registro {
	
	private ArrayList<Jugador> jugadores;

    public Registro() {
		this.jugadores = new ArrayList<Jugador>();
		llenarRegistro();
	}

    /**Recibe un nuevo nombre y puntaje para agregar al registro. 
     * Si el registro esta lleno se elimina al jugador de la ultima posicion*/
	public void agregarJugador(String nombreJugador, Partida partida) {
		if(getCantidad()==20)
			this.deleteJugador();
		Jugador nuevoJ = new Jugador(nombreJugador,partida.getPuntaje());
		jugadores.add(nuevoJ);
		jugadores.sort(new SortByScore());
	}

	/**Comparator utilizado para ordenar el arrayList "jugadores" a partir de los puntajes de manera decreciente*/
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
    
    /**Devuelve una lista de jugadores view correspondiente al atributo jugadores de la clase*/
    public List<JugadorView> obtenerJugadores(){
    	List<JugadorView> listaJugadores = new ArrayList<>();
    	for(Jugador jugador:jugadores) {
    		listaJugadores.add(jugador.toView());
    	}
		return listaJugadores;   	
    }    
    
    /**
     * Metedo utilitario para la presentación del proyecto. Llena el registro con 20 jugadores.
     * IMPORTANTE!!!!!!: Este método no figura en la documentación ya que su uso está enfocado en mostrar el display de jugadores
     * al momento de presentar el proyecto 
     * */
    private void llenarRegistro() {
    	Jugador j = new Jugador("XXXXXXXXXXX", 0);
    	for(int i = 0; i<20;i++) {
    		jugadores.add(j);
    	}
    }
}