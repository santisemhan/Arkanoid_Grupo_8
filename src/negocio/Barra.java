package negocio;
import java.util.*;

public class Barra {

    private float largo, ancho ,posicionX;
    private boolean ejecucion;
    

    
    public Barra() {
		this.largo = 0;
		this.ancho = 0;
		this.posicionX = 0;
		this.ejecucion = true;
	}

    /**
     * La dirección es según key code
     * */
	public void desplazarse (int direccion) {
        switch(direccion) {
        	case 37://Se mueve a la izquierda
        		this.posicionX--;
        		break;
        	case 39://Se mueve a la derecha
        		this.posicionX++;     	
        		break;
        }
    }

    public float getPosicionX() {
        return posicionX;
    }

    public void cambiarEstadoEjecucion() {
    	ejecucion = !ejecucion;
    }
}