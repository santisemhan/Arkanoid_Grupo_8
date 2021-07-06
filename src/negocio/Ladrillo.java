package negocio;

import views.LadrilloView;

public class Ladrillo {

    private int valor;
    private boolean destruido;
    private int columna;
    private int fila;
    private int ancho,alto;
    private Partida partida;

    public Ladrillo(int fila,int columna,Partida partida) {
		this.valor = fila*10;
		this.destruido = false;
		this.columna = columna;
		this.fila = fila;
		this.ancho = 97;
		this.alto = 20;
		this.partida = partida;
	}
//    Disposicion de los ladrillos de acuerdo a sus filas, columnas y valor
//          5 4 3 2 1
//    5        50
//    4        40
//    3        30
//    2        20
//    1        10

    /**cambia a "true" el atributo "destruido" y le indica a la partida que debe sumar su valor al puntaje global*/
	public void destruir() {
        destruido = true;
        partida.subirPuntaje(valor);
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

    public int getFila() {
        return fila;
    }
    
    public LadrilloView toView() {
    	return new LadrilloView(ancho, alto,destruido);
    }

}