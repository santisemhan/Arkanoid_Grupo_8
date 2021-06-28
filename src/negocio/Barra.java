package negocio;

import views.BarraView;

public class Barra {

    private int largo, ancho ,ejeY,ejeX;
    private boolean ejecucion;
 
    public Barra() {
		this.ancho = 70;
		this.ejeY = 250;
		this.ejeX = 80;
		this.ejecucion = true;
	
	}
    
    /**
     * La dirección es según key code. Con 37 la barra se mueve a la izquierda.
     * Con 39 la barra se mueve a la derecha. La barra se movera unicamente si
     * estado de ejecucion es "true"
     * */
	public void desplazarse (int direccion) {
		if(ejecucion) {
	        if (direccion == 37 && ejeX >= -8)//Se mueve a la izquierda
	        		ejeX-=3;
	        if(direccion == 39 && ejeX <= 172)//Se mueve a la derecha
	        		ejeX+=3;
        }
    }

    public int getejeX() {
        return ejeX;
    }
    
    public int getejeY() {
        return ejeY;
    }
    
    /**Coloca a la barra en la posicion de inicio*/
    public void resetearEjeX() {
		this.ejeX = 80;
		this.ejecucion = true;
    }

    public void cambiarEstadoEjecucion() {
    	ejecucion = !ejecucion;
    }
    
    public BarraView toView() {
    	return new BarraView(largo, ancho, ejeX, ejeY);
    }
}