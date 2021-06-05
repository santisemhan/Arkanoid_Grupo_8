package negocio;
import java.util.*;

public class Ladrillo {

    private int valor;
    private boolean destruido;
    private int columna;
    private int fila;

    public Ladrillo(int fila,int columna) {

		this.valor = fila*10;
		this.destruido = false;
		this.columna = columna;
		this.fila = fila;
	}
    
//       5  4  3  2  1
//    5        50
//    4        40
//    3        30
//    2        20
//    1        10

	public void destruir() {
        destruido = true;
    }

    public int getColumna() {
        return columna;
    }

    public int getFila() {
        return fila;
    }

}