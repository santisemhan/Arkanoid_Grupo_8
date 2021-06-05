package negocio;
import java.util.*;

public class Partida {

    private int vidas;
    private boolean ejecucion;
    private int nivel;
    private int puntaje;
    //private int id;
    
    private Barra barra;
    private List<Borde> bordes;
    private Bola bola;
    private List<Ladrillo> ladrillos;

    public Partida() {
		this.vidas = 3;
		this.ejecucion = true;
		this.nivel = 1;
		this.puntaje = 0;
		//this.id = id;
		this.barra = new Barra();
		this.bordes = new ArrayList<Borde>();
		this.ladrillos = new ArrayList<Ladrillo>();
		
		for(int i = 5;i>=1;i--) {
			if(i>1) {
				if(i == 5)
					bordes.add(new Borde("inferior"));
				else if(i == 4)
					bordes.add(new Borde("superior"));
				else if(i == 3)
					bordes.add(new Borde("izquierdo"));
				else
					bordes.add(new Borde("derecho"));
			}
			
			for(int j = 5; j >=1; j--) {
				ladrillos.add(new Ladrillo(i,j));
			}
		}	
		
		this.bola = new Bola(ladrillos, barra, bordes);
	}

	public boolean finalizoPartida() {
        return (vidas <=0 );
    }

    public void cambiarEstadoEjecucion() {
    	this.ejecucion = !ejecucion;
    	barra.cambiarEstadoEjecucion();
    }

    public void restarVida() {
        vidas--;
    }
    
    public void sumarVida() {
        vidas++;
    }

    public int getVidas() {
        return vidas;
    }

    public int getNivel() {
        return nivel;
    }
    
    /**
     * @param puntaje: representa al puntaje del ladrillo destruido
     * Se suma dicho puntaje al puntaje total del jugador
     */
    
    public void setPuntaje(int puntaje) {
        this.puntaje += puntaje;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void subirNivel() {
        nivel++;
        bola.aumentarVelocidad();
    }
/*
    public boolean soyEsaPartida(int id) {
        // TODO implement here
        return false;
    }
*/
    public void moverBarra(int direccion) {
        // TODO implement here
        return ;
    }

}