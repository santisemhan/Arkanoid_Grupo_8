package negocio;
import java.util.*;

public class Bola {

    private float velocidad;
    private float anguloEntrada;
    private boolean ejecucion;
    private List<Ladrillo> ladrillos;
    private Barra barra;
    private List<Borde> bordes;

    public Bola(List<Ladrillo> ladrillos, Barra barra,
			List<Borde> bordes) {
		this.velocidad = 0;
		this.anguloEntrada = 0;
		this.ejecucion = true;
		this.ladrillos = ladrillos;
		this.barra = barra;
		this.bordes = bordes;
	}

	public void impactar() {
        
    }

   public void setAnguloEntrada() {

    }

    public void calcularAnguloSalida(String lugarImpacto) {
        // TODO implement here
        return ;
    } 
    
    public void aumentarVelocidad() {
        velocidad *= 1.1;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public float getAnguloEntrada() {
        return anguloEntrada;
    }


    public void cambiarEstadoEjecucion() {
        ejecucion = !ejecucion;
    }

}