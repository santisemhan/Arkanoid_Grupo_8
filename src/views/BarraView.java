package views;

public class BarraView {
	private int largo, ancho ,ejeX,ejeY;

	public BarraView() {}

	public BarraView(int largo, int ancho, int ejeX,int ejeY) {
		this.largo = largo;
		this.ancho = ancho;
		this.ejeX = ejeX;
		this.ejeY = ejeY;
	}

	public int getejeY() {
		return ejeY;
	}

	public void setejeY(int ejeY) {
		this.ejeY = ejeY;
	}

	public int getLargo() {
		return largo;
	}

	public void setLargo(int largo) {
		this.largo = largo;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getejeX() {
		return ejeX;
	}

	public void setejeX(int ejeX) {
		this.ejeX = ejeX;
	}
	
}
