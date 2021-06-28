package negocio;

import views.LadrilloView;

public class Ladrillo {

    private int valor;
    private boolean destruido;
    private int columna;
    private int fila;
    private int ancho,alto;

    public Ladrillo(int fila,int columna) {
		this.valor = fila*10;
		this.destruido = false;
		this.columna = columna;
		this.fila = fila;
		this.ancho = 47;
		this.alto = 10;
	}
//    Disposicion de los ladrillos de acuerdo a sus filas, columnas y valor
//          5 4 3 2 1
//    5        50
//    4        40
//    3        30
//    2        20
//    1        10

    /**cambia a "true" el atributo "destruido"*/
	public void destruir() {
        destruido = true;
    }
	
	public boolean getDestruido() {
		return destruido;
	}
	
	/**cambia a "false" el atributo "destruido"*/
	public void resetear() {
		destruido = false;
	}

    public int getColumna() {
        return columna;
    }
    
    public int getValor() {
    	return valor;
    }

    public int getFila() {
        return fila;
    }
    
    public LadrilloView toView() {
    	return new LadrilloView(ancho, alto,destruido,valor);
    }

}