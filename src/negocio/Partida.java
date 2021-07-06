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
				ladrillos.add(new Ladrillo(i,j,this));
			}
		}	
		this.bola = new Bola(ladrillos, barra);
	}

    /**verifica si las vidas llegaron a 0*/
	public boolean finalizoPartida() {
        return (vidas <=0);
    }

	/**cambia su estado de ejecucion y le pide a la bola y la barra que hagan lo mismo*/
    public void cambiarEstadoEjecucion() {
    	this.ejecucion = !ejecucion;
    	bola.cambiarEstadoEjecucion();
    	barra.cambiarEstadoEjecucion();    	
    }

    private void restarVida() {
        vidas--;
    }
    
    /**controla sus ladrillos preguntando si todavía hay alguno que no fue destruido*/
    private boolean tengoLadrillosSanos() {
    	for(Ladrillo l:ladrillos) {
    		if(!l.getDestruido()) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**le pide a todos sus ladrillos que ejecuten el metodo resetear*/
    private void resetearLadrillos() {
    	for(Ladrillo l:ladrillos) {
    		l.resetear();
    	}
    }
    
    /**
     * @param puntaje: representa al puntaje del ladrillo destruido
     * Se suma dicho puntaje al puntaje total del jugador
     */    
    public void subirPuntaje(int puntaje) {
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
    	bola.golpeLadrillo();
    }
    
    /**le pide a la bola que gestione el choque con la barra*/
    public void impacto() {
    	bola.impactar();
    }
    
    public int getPuntaje() {
    	return puntaje;
    }
    
    /**le pide a la barra que ejecute su metodo desplazarse*/
    public void moverBarra(int direccion) {
        barra.desplazarse(direccion);
    }
    
    /**le pide a la bola que ejecute su metodo mover*/
    public void moverBola() {
    	bola.mover();
    }
        
    /**Manejo de la acción Pasar de nivel. La partida llama al método tengoLadrillosSanos que notifica si hay ladrillos no destruidos.
     * Si todos los ladrillos están destruidos, la partida llama a los metedos subirNivel y resetearLadrillos. La partida llama a los
     *  metedos de la bola que resetean su posición y aumentan su velocidad. La partida llama al metedo de la barra que resetea su posición.*/
    public boolean resetearPasoNivel() {
    	if(!tengoLadrillosSanos()) {
    		subirNivel();
    		resetearLadrillos();
    		bola.aumentarVelocidad();
    		bola.resetearPos();
    		barra.resetearEjeX();
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    /**Manejo de la acción Perder una vida. La partida le pregunta a la bola si llegó al fondo del tablero.
     * Si llegó, la partida llama a los metedos de la bola y la barra que resetean las respectivas posiciones.
     * Además, la partida llama al método restarVida*/
    public boolean resetearPerdidaVida() {
    	if(bola.toqueFondo()) {   		
    		bola.resetearPos();
    		barra.resetearEjeX();
    		restarVida();
    		return true;
    	}
    	else {
    		return false;
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