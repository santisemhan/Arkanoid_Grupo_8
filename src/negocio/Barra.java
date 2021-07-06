package negocio;

import views.BarraView;

public class Barra {

    private int ejeY,ejeX;
    private boolean ejecucion;
 
    public Barra() {
		this.ejeY = 500;
		this.ejeX = 160;
		this.ejecucion = true;	
	}
    
    /**
     * La dirección es según key code. Con 37 la barra se mueve a la izquierda.
     * Con 39 la barra se mueve a la derecha. La barra se movera unicamente si
     * estado de ejecucion es "true"
     * */
	public void desplazarse (int direccion) {
		if(ejecucion) {
	        if (direccion == 37 && ejeX >= -16)//Se mueve a la izquierda
	        		ejeX-=3;
	        if(direccion == 39 && ejeX <= 360)//Se mueve a la derecha
	        		ejeX+=3;
        }
    }

    public int getEjeX() {
        return ejeX;
    }
    
    public int getEjeY() {
        return ejeY;
    }
    
    /**Coloca a la barra en la posicion de inicio*/
    public void resetearEjeX() {
		this.ejeX = 160;
		this.ejecucion = true;
    }

    public void cambiarEstadoEjecucion() {
    	ejecucion = !ejecucion;
    }
    
    public BarraView toView() {
    	return new BarraView(ejeX, ejeY);
    }
}