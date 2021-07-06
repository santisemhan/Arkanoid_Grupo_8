package views;

public class JugadorView {

	private String nombreJugador;
    private int puntajeFinal;
	public JugadorView() {}

	public JugadorView(String nombreJugador, int puntajeFinal) {
		this.nombreJugador = nombreJugador;
		this.puntajeFinal = puntajeFinal;
	}

	public String getNombreJugador() {
		return nombreJugador;
	}

	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}

	public int getPuntajeFinal() {
		return puntajeFinal;
	}

	public void setPuntajeFinal(int puntajeFinal) {
		this.puntajeFinal = puntajeFinal;
	}

	public String toString() {
		return nombreJugador+": "+puntajeFinal;
	}
    
}
