package negocio;
import java.util.*;


import views.BolaView;


public class Bola {

    private float velocidad;
    private int anguloEntrada;
    private int sentidoX, sentidoY;
    private float x,y;   
    private float factorRango;	
    private boolean ejecucion;
    private List<Ladrillo> ladrillos;
    private Barra barra;

    public Bola(List<Ladrillo> ladrillos, Barra barra) {
    	x = 105;
    	y = 248;
    	sentidoX = 1;
    	sentidoY = -1; 			
    	this.factorRango = 1;//variable utilizada para establecer el area de impacto posible con los ladrillos   	
    	this.velocidad = 2f;
		this.anguloEntrada = 85;
		this.ejecucion = true;
		this.ladrillos = ladrillos;
		this.barra = barra;
	}
    
    /*Direccion de la bola en los ejes x e y de acuerdo al valor de las variables de sentido:
     * SentidoX = derecha ---> +1
     * SentidoX = izquierda ---> -1
     * SentidoY = arriba -----> -1
     * sentidoY = abajo ------> +1
     */
    
    /**Metodo que mueve la bola. Realiza un cambio de direccion al tocar los bordes izquierdo, superior y derecho.*/
    public void mover() {
    	if(ejecucion) {   		
	    	if(x<=-5) {
				sentidoX *= -1;
	    	}if(x>=220) {
				sentidoX *= -1;
	    	}if(y<=-5) {
				sentidoY = 1;
	    	}
	    	float vx = (float) (velocidad * Math.cos(Math.toRadians(anguloEntrada)));
	    	float vy = (float)( velocidad * Math.sin(Math.toRadians(anguloEntrada)));
	    	x += (sentidoX * vx) ;
	    	y += (sentidoY * vy) ;
    	}
    }
    
    /**Informa si la bola entro en contacto con la parte inferior de la pantalla*/
    public boolean toqueFondo() {
    	return (int)y>=300;
    }
    
    /**
     * Manejo del impacto con la barra. Verifica si hay contacto entre la bola y la barra,
     *  luego en que porcion de la barra impacto y finalmente cambia el angulo de acuerdo 
     *  a que parte de la barra impacto. */
	public void impactar() {//Manejar rebote en la barra
		if((int)y==barra.getejeY()||(int)y==barra.getejeY()+1||(int)y==barra.getejeY()+2) {	
			int areaBarra = barra.getejeX() + 70;//Se suma la posición más el largo en eje X
			if (x>= barra.getejeX() && x<= areaBarra) {
				int mitadBarra = areaBarra-35;				
				if(x<=mitadBarra) {
					anguloEntrada += 70;								
				}
				else if(x>mitadBarra) {
					anguloEntrada += 60;					
				}
				if(anguloEntrada>180) {
					anguloEntrada %= 180;
					sentidoX *= -1;
				}else if(anguloEntrada==180) {
					anguloEntrada = 10;
				}
				sentidoY = -1;
			}				
		}
	}
	
	/**Manejo del impacto en los ladrillos. El metodo verifica en que porcion del eje x 
	 * se encuentra la bola y de acuerdo a esa posicion llama al metodo impactarLadrilloColumna
	 *  pasandole la columna, el area en x de comienzo de los ladrillos y el area en x del fin de los
	 *  ladrillos. Los 0 ingresados refieren a la caras del ladrillo que estan contra los bordes.
	 *  El metodo devuelve un entero correspondiente al valor del ladrillo destruido.*/
	public int golpeLadrillo() {
		
		if(x<220 && x >=176) {//destruir ladrillo primera columa
			return impactarLadrilloColumna(1,176,0);
		}
							
		else if(x<=175 && x >=131) {//destruir ladrillo segunda columa
			return impactarLadrilloColumna(2,131,175);
		}
		
		else if(x<=130 && x>=86) {//destruir ladrillo tercera columa
			return impactarLadrilloColumna(3,86,130);
		}
			
		else if(x<=85 && x>=41) {//destruir ladrillo cuarta columa
			return impactarLadrilloColumna(4,41,85);
		}				
			
		else if(x<=40 && x>-5) {//destruir ladrillo quinta columa
			return impactarLadrilloColumna(5,0,40);
		}
		
		else {
			return 0;
		}
	}
	
	/**
	 * Segun su parametro "columna" recorre todos los ladrillos de esa columna y verifica si la bola 
	 * esta en el area correspondiente a un ladrillo. Los parametro inicioX y finX refieren a donde empieza y
	 * donde termina el ladrillo. Si la bola intercede las cordenadas de un ladrillo de la columna entonces se calcula
	 * si lo impacto en los lados superior-inferior (se llama al metodo impactoLEnX) o en los lados derecho-izquierdo
	 * (se llama al metodo impactoLEnX). Luego llama al metodo romperLadrillo pasandole la fila y columna del ladrillo
	 * Luego se obtiene el valor del ladrillo destruido y el mismo es devuelto*/
	private int impactarLadrilloColumna(int columna,int inicioX,int finX) {			
		int finY = 0;
		int inicioY = -5;
		int valor = 0;
		for(int i = 5; i>=1; i--) {
			finY = inicioY+ 10;
			if (y> inicioY && y<= finY && !buscarLadrillo(i,columna).getDestruido()) {				
				
				if(Math.abs(y - finY)/y <0.2*factorRango) {
					romperLadrillo(i,columna);
					impactoLEnX();
					valor =  buscarLadrillo(i, columna).getValor();
					
					break;				
				}
				
				else if(inicioX != 0 && finX != 0  && (Math.abs(x-inicioX)/x<0.04*factorRango||Math.abs(x-finX)/x<0.04*factorRango)){			
					romperLadrillo(i,columna);
					impactoLEnY();
					valor=buscarLadrillo(i, columna).getValor();
					
					break;
				}		
			}
			inicioY += 10;
		}
		return valor;
	}
	
	/**busca un ladrillo identificado por su fila y columna*/
	private Ladrillo buscarLadrillo(int fila,int columna) {
	    	for(Ladrillo ladrillo:ladrillos) {
	    		if(ladrillo.getFila()== fila && ladrillo.getColumna() == columna)
	    			return ladrillo;
	    	}
	    	return null;
	}
	
	/**maneja los impactos en los bordes superior-inferior. Cambia el angulo de la bola por un angulo entre
	 * 85 y 95 grados*/
	private void impactoLEnX() {
		int min=85;
		int max=95;		
		anguloEntrada = (int)Math.floor(Math.random()*(max-min+1)+min);
		if(sentidoY ==1) {
			sentidoY = -1;
		}	
		else if(sentidoY == -1) {
			sentidoY =1;
		}
	}
	
	/**frente a un impacto en los bordes derecho-izquierdo se cambia la direccion de la bola en el eje x.*/
	private void impactoLEnY() {
		sentidoX *= -1;
		
	}
	
	/**cambia el atributo booleano "destruir" de un ladrillo identificado por su fila y columna*/
	private void romperLadrillo(int fila,int columna) {
		Ladrillo l = buscarLadrillo(fila, columna);
		if (l!= null) {
			l.destruir();
		}
	}

	/**aumenta la velocidad de la bola y la variable "factorRango" utilizada para controlar el impacto con los ladrillos*/
    public void aumentarVelocidad() {
        velocidad += 0.5f;
        factorRango *= (velocidad/2);
    }
    
    /**re-setea los atributos de la bola a sus estados iniciales*/
    public void resetearPos() {
	    x = 105;
	    y = 248;
	    sentidoX = 1;
	    sentidoY = -1;
	    anguloEntrada = 85;
    }

    public void cambiarEstadoEjecucion() {
        ejecucion = !ejecucion;
    }
    
    public BolaView toView() {
    	return new BolaView(x, y,velocidad);
    }

}