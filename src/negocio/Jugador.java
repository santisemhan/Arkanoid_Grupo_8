package negocio;

import views.JugadorView;

public class Jugador {

    private String nombreJugador;
    private int puntajeFinal;

    public Jugador(String nombreJugador, int puntajeFinal) {
		this.nombreJugador = nombreJugador;
		this.puntajeFinal = puntajeFinal;
	}

    public int getPuntaje() {
    	return puntajeFinal;
    }
    
    public JugadorView toView() {
    	return new JugadorView(nombreJugador, puntajeFinal);
    }
    
}