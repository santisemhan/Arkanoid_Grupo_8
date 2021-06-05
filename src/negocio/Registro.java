package negocio;
import java.util.*;

public class Registro {
	
	private ArrayList<Jugador> jugadores;

    public Registro() {
		this.jugadores = new ArrayList<Jugador>();
	}

	public void agregarJugador(String nombreJugador, int puntaje) {
		if(getCantidad()==20)
			this.deleteJugador();
		Jugador nuevoJ = new Jugador(nombreJugador,puntaje);
		jugadores.add(nuevoJ);
		//Collections.sort(jugadores.get);
    }

    private void deleteJugador() {
        jugadores.remove(19);
    }

    public List<Jugador> getRegistro() {
        return jugadores;
    }

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