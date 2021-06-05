package negocio;
import java.util.*;

public class Borde {

    private String tipo;
    private float largo;
    private float ancho;
  
    public Borde(String tipo) {
		this.tipo = tipo;
		this.largo = 0;
		this.ancho = 0;
	}

	public boolean impacto() {		
        return false;
    }
}