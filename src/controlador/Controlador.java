package controlador;
import java.util.*;

import negocio.Partida;
import negocio.Registro;

public class Controlador {

	private List<Partida> partidas;
	private List<Registro> registros;
	private static Controlador instancia;

    private Controlador() {
		this.partidas = new ArrayList<Partida>();
		this.registros = new ArrayList<Registro>();
	}
    
    public static Controlador getInstancia() {
    	if (instancia == null) {
    		instancia = new Controlador();
    	}
    	return instancia;
    }

	public void iniciarJuego() {
        Partida nuevaP = new Partida();
        partidas.add(nuevaP);
    }

    public void cambiarEstadoEjecucion() {
        Partida partidaActual = partidas.get(partidas.size()-1);
        partidaActual.cambiarEstadoEjecucion();
    }

    public void agregarARegistro(String nombre) {
    	Registro registroActual = registros.get(registros.size()-1);
    	registroActual.agregarJugador(nombre, partidas.get(partidas.size()-1).getPuntaje());
    }

    public void moverBarra(int direccion) {
    	Partida partidaActual = partidas.get(partidas.size()-1);
    	partidaActual.moverBarra(direccion);    	    
    }
    
    public void comprobarJuego() {
        return ;
    }

    public boolean comprobarPuntaje() {
    	Registro registroActual = registros.get(registros.size()-1);
    	Partida partidaActual = partidas.get(partidas.size()-1);
        return registroActual.comprobarPuntaje(partidaActual);
    }

    public boolean comprobarFinPartida() {
    	Partida partidaActual = partidas.get(partidas.size()-1);
        return partidaActual.finalizoPartida();
    }
}