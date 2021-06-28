package views;

public class PartidaView {

	private int vidas, nivel,puntaje;
	private boolean ejecucion;


	public PartidaView() {	}

	public PartidaView(int vidas, int nivel, int puntaje,boolean ejecucion) {
		this.vidas = vidas;
		this.nivel = nivel;
		this.puntaje = puntaje;
		this.ejecucion = ejecucion;
	}
	

	public boolean isEjecucion() {
		return ejecucion;
	}

	public void setEjecucion(boolean ejecucion) {
		this.ejecucion = ejecucion;
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
}
