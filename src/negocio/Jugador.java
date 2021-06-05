package negocio;
import java.util.*;

public class Jugador {

    private String nombreJugador;
    private int puntajeFinal;

    public Jugador(String nombreJugador, int puntajeFinal) {
		this.nombreJugador = nombreJugador;
		this.puntajeFinal = puntajeFinal;
	}

    /*
	public void setNombreJugador() {
        return ;
    }

    public String getNombreJugador() {
        return "";
    }
	*/
    
    public int getPuntaje() {
    	return puntajeFinal;
    }
}