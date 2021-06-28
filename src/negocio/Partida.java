package negocio;
import java.util.*;

import views.BarraView;
import views.BolaView;
import views.LadrilloView;
import views.PartidaView;

public class Partida {

    private int vidas;
    private boolean ejecucion;
    private int nivel;
    private int puntaje,ultimoPuntaje;
    
    private Barra barra;
    private Bola bola;
    private List<Ladrillo> ladrillos;

    public Partida() {
		this.vidas = 3;
		this.ejecucion = true;
		this.nivel = 1;
		this.puntaje = 0;
		this.ultimoPuntaje = 1000;//Variable utilizada para controlar la subida de vidas cada 1000 puntos
		this.barra = new Barra();
		this.ladrillos = new ArrayList<Ladrillo>();
		
		for(int i = 5;i>=1;i--) {//Generacion de ladrillos "i" es la fila. "j" es la columna
			for(int j = 5; j >=1; j--) {
				ladrillos.add(new Ladrillo(i,j));
			}
		}	
		this.bola = new Bola(ladrillos, barra);
	}

    /**verifica si las vidas llegaron a 0*/
	public boolean finalizoPartida() {
        return (vidas <=0);
    }

	/**cambia su estado de ejecucion y el de la bola y la barra*/
    public void cambiarEstadoEjecucion() {
    	this.ejecucion = !ejecucion;
    	barra.cambiarEstadoEjecucion();
    	bola.cambiarEstadoEjecucion();
    }

    private void restarVida() {
        vidas--;
    }
    
    /**controla sus ladrillos preguntando si todavía hay alguno que no fue destruido*/
    public boolean tengoLadrillosSanos() {
    	for(Ladrillo l:ladrillos) {
    		if(!l.getDestruido()) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**le pide a todos sus ladrillos que ejecuten el metodo resetear*/
    public void resetearLadrillos() {
    	for(Ladrillo l:ladrillos) {
    		l.resetear();
    	}
    }
    
    /**
     * @param puntaje: representa al puntaje del ladrillo destruido
     * Se suma dicho puntaje al puntaje total del jugador
     */    
    private void subirPuntaje(int puntaje) {
        this.puntaje += puntaje;
    }
    
    /**sube una vida cada 1000 puntos*/
    public void subirVida() {
    	if(puntaje >= ultimoPuntaje && puntaje !=0) {
    		ultimoPuntaje += 1000;
    		vidas++;
    	}
    }

    private void subirNivel() {
        nivel++;
    }

    /**le pide a la bola que gestione el choque con ladrillos*/
    public void golpeLadrillo() {
    	int valor = bola.golpeLadrillo();
    	subirPuntaje(valor);
    }
    
    /**le pide a la bola que gestione el choque con la barra*/
    public void impacto() {
    	bola.impactar();
    }
    
    public int getPuntaje() {
    	return puntaje;
    }
    
    /**le pide a la bola que ejecute su metodo mover*/
    public void moverBarra(int direccion) {
        barra.desplazarse(direccion);
    }
    
    /**le pide a la bola que ejecute su metodo mover*/
    public void moverBola() {
    	bola.mover();
    }
    
    /**resetea las posiciones de la bola y la barra de acuerdo al evento. Si la bola toca el fondo 
     * del tablero se debe restar una vida y devuelve un int "0" como control. Si todos los ladrillos 
     * estan destruidos se resetea las posiciones de la bola y barra, se aumenta la velocidad de la 
     * bola, se sube el nivel y devuelve un int "1" como control*/
    public int resetear() {
    	if(bola.toqueFondo()) {
    		restarVida();
    		bola.resetearPos();
    		barra.resetearEjeX();
    		return 0;
    	}else if(!tengoLadrillosSanos()) {
    		subirNivel();
    		bola.aumentarVelocidad();
    		bola.resetearPos();
    		barra.resetearEjeX();
    		return 1;
    	}
    	else {
    		return 2;
    	}
    }
    
    //Views
    public BarraView getBarra() {
    	return barra.toView();
    }
    
    public List<LadrilloView> getLadrillos() {
    	List<LadrilloView> ladrillosView = new ArrayList<LadrilloView>();
    	for(Ladrillo ladrillo:ladrillos) 
    		ladrillosView.add(ladrillo.toView());
    	return ladrillosView;
    }
    
    public BolaView getBola() {
    	return bola.toView();
    }

    public PartidaView toView() {
    	return new PartidaView(vidas, nivel, puntaje,ejecucion);
    }
}