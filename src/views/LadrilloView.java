package views;

public class LadrilloView {
	 private int ancho,alto,valor;
	 private boolean destruido;
 
	public LadrilloView() {
	}

	public LadrilloView(int ancho, int alto,boolean destruido,int valor) {
		this.ancho = ancho;
		this.alto = alto;
		this.destruido = destruido;
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public boolean isDestruido() {
		return destruido;
	}

	public void setDestruido(boolean destruido) {
		this.destruido = destruido;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}
	 
	 
}
