package controlador;
import java.util.*;

import negocio.Partida;
import negocio.Registro;
import views.BarraView;
import views.BolaView;
import views.JugadorView;
import views.LadrilloView;
import views.PartidaView;

public class Controlador {

	private Partida partida;
	private Registro registro;
	private static Controlador instancia;

    private Controlador() {
    	iniciarJuego();
		this.registro = new Registro();	
	}
    
    /**
     * Devuelve la instancia del Controlador. La inicializa si no fue creada*/
    public static Controlador getInstancia() {
    	if (instancia == null) {
    		instancia = new Controlador();
    	}
    	return instancia;
    }

    /**
     * Crea un objeto partida/Sobreescribe
     * la partida del controlador*/
	public void iniciarJuego() {
        this.partida = new Partida();
    }
	
	/**
	 * Le pide a la partida que mueva la barra*/
	public void moverBarra(int direccion) {
    	partida.moverBarra(direccion);    	    
    }
	
	/**
	 * Le pide a la partida que mueva la bola*/
	public void moverBola() {
		partida.moverBola();
	}
	
	/**
	 * Le pide a la partida que maneje el paso de nivel*/
	public boolean resetearPasoNivel() {
		return partida.resetearPasoNivel();
	}
	
	/**
	 * Le pide a la partida que maneje la perdida de una vida*/
	public boolean resetearPerdidaVida() {
		return partida.resetearPerdidaVida();
	}
	
	/**
	 * Le pide a la partida que sume vidas*/
	public void sumarVidas() {
		partida.subirVida();
	}
	
	/**
	 * Le pide a la partida que realice un impacto entre la barra y la bola.*/
	public void impacto() {
		partida.impacto();
	}
	
	/**
	 * Le pide a la partida que realice un impacto entre la bola y los ladrillos.*/
	public void golpeLadrillo() {
		partida.golpeLadrillo();
	}

	/**
	 * Le pide a la partida que cambie el estado de ejecucion. Es decir: le pide que haga "Pausa" o "Reanude" el juego*/
    public void cambiarEstadoEjecucion() {
        partida.cambiarEstadoEjecucion();
    }
    
    /**
     * Le pide al registro que compruebe si corresponde agregar el puntaje obtenido al finalizar la partida o no. 
     * Devuelve "true" si corresponde, devuelve "false" si no corresponde*/
    public boolean comprobarPuntaje() {
        return registro.comprobarPuntaje(partida);
    }

    /**
     * Crea una nueva entrada en el registro. Este metodo recibe como parametro el nombre ingresado por el jugador. 
     * Este método debe ser llamado si el metodo "Comprobar puntaje" devuelve "true"*/
    public void agregarARegistro(String nombre) {
    	registro.agregarJugador(nombre, partida);
    }
    
    /**
     * Le pregunta a la partida si la misma finalizo*/
    public boolean comprobarFinPartida() {
        return partida.finalizoPartida();
    }
    
    //Llamada a metodos "View"
    public List<JugadorView> getJugadores(){
    	return registro.obtenerJugadores();
    }
    
    public BarraView getBarra() {
    	return partida.getBarra();
    }
    
    public List<LadrilloView> getLadrillos() {
    	return partida.getLadrillos();
    }
    
    public BolaView getBola() {
    	return partida.getBola();
    }
    
    public PartidaView getPartida() {
    	return partida.toView();
    }
}