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
    	x = 210;
    	y = 496;
    	sentidoX = 1;
    	sentidoY = -1; 			 	
    	this.velocidad = 2.5f;
    	this.factorRango = (velocidad/2);//variable utilizada para establecer el area de impacto posible con los ladrillos  
		this.anguloEntrada = 85;
		this.ejecucion = true;
		this.ladrillos = ladrillos;
		this.barra = barra;
	}
    
    /*Direccion de la bola en los ejes x e y de acuerdo al valor de las variables de sentido:
     * sentidoX = derecha ---> +1
     * sentidoX = izquierda ---> -1
     * sentidoY = arriba -----> -1
     * sentidoY = abajo ------> +1
     */
    
    /**Metodo que mueve la bola. Realiza un cambio de direccion al tocar los bordes izquierdo, superior y derecho.*/
    public void mover() {
    	if(ejecucion) {   		
	    	if(x<=-10) {
				sentidoX *= -1;
	    	}if(x>=455) {
				sentidoX *= -1;
	    	}if(y<=-10) {
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
    	return (int)y>=650;
    }
    
    /**
     * Manejo del impacto con la barra. Verifica si hay contacto entre la bola y la barra,
     *  luego en que porcion de la barra impacto y finalmente cambia el angulo de acuerdo 
     *  a que parte de la barra impacto. */      
    public void impactar() {//Manejar rebote en la barra
    	int barraEjeY = barra.getEjeY();
    	int barraEjeX = barra.getEjeX();
		if((int)y>=barraEjeY && (int)y<=barraEjeY+4) {	
			int areaBarra =barraEjeX + 140;//Se suma la posición más el largo en eje X
			if (x+15>= barraEjeX && x<= areaBarra) {
				int mitadBarra = areaBarra-70;
				if(x-15<mitadBarra-40) {
					anguloEntrada += 90-90;	
					sentidoY = -1;
					if(anguloEntrada==90) {
						anguloEntrada = 85;
					}
				}
				else if(x>=mitadBarra-40 && x< areaBarra-30) {
					anguloEntrada += 60;
					sentidoY = -1;					
					if(anguloEntrada>90) {
						anguloEntrada %= 90;
						sentidoX *= -1;
					}
				}			
			}				
		}
	}
        
	/**Manejo del impacto en los ladrillos. El metodo verifica en que porcion del eje x 
	 * se encuentra la bola y de acuerdo a esa posicion llama al metodo impactarLadrilloColumna
	 *  pasandole la columna, el area en x de comienzo de los ladrillos y el area en x del fin de los
	 *  ladrillos.*/	
    public void golpeLadrillo() {		
		if(x<475 && x >=376) {//destruir ladrillo primera columa
			impactarLadrilloColumna(1,376,475);
		}
							
		else if(x<=375 && x >=276) {//destruir ladrillo segunda columa
			impactarLadrilloColumna(2,276,375);
		}
		
		else if(x<=275 && x>=176) {//destruir ladrillo tercera columa
			impactarLadrilloColumna(3,176,275);
		}
			
		else if(x<=175 && x>=76) {//destruir ladrillo cuarta columa
			impactarLadrilloColumna(4,76,175);
		}				
			
		else if(x<=75 && x>-10) {//destruir ladrillo quinta columa
			impactarLadrilloColumna(5,1,75);
		}
    }
	
	
	/**
	 * Segun su parametro "columna" recorre todos los ladrillos de esa columna y verifica si la bola 
	 * esta en el area correspondiente a un ladrillo. Los parametro inicioX y finX refieren a donde empieza y
	 * donde termina el ladrillo. Si la bola intercede las cordenadas de un ladrillo de la columna entonces se calcula
	 * si lo impacto en los lados superior-inferior (se llama al metodo impactoLEnX) o en los lados derecho-izquierdo
	 * (se llama al metodo impactoLEnX). Luego llama al metodo romperLadrillo pasandole el ladrillo a romper.*/
	private void impactarLadrilloColumna(int columna,int inicioX,int finX) {			
		int finY = 0;
		int inicioY = -5;
		for(int i = 5; i>=1; i--) {
			finY = inicioY+ 20;
			Ladrillo ladrillo = buscarLadrillo(i,columna);
			if (y> inicioY && y<= finY && !ladrillo.getDestruido()) {				
				
				if(Math.abs(y - finY)/y <0.15*factorRango) {
					impactoLEnX();
					romperLadrillo(ladrillo);					
					break;				
				}
				
				else if(Math.abs(x-inicioX)/x<0.3*factorRango || Math.abs(x-finX)/x<0.3*factorRango){
					impactoLEnY();
					romperLadrillo(ladrillo);					
					break;
				}		
			}
			inicioY += 20;
		}
	}
	
	
	/**busca un ladrillo identificado por su fila y columna*/
	private Ladrillo buscarLadrillo(int fila,int columna) {
	    	for(Ladrillo ladrillo:ladrillos) {
	    		if(ladrillo.getColumna() == columna && ladrillo.getFila()== fila)
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
		sentidoY *= -1; 
	} 

	/**frente a un impacto en los bordes derecho-izquierdo se cambia la direccion de la bola en el eje x.*/
	private void impactoLEnY() {
		sentidoX *= -1;		
	}
	
	/**Le pide a un ladrillo que ejecute el metodo destruir*/
	private void romperLadrillo(Ladrillo ladrillo) {
		ladrillo.destruir();
	}
	

	/**aumenta la velocidad de la bola y la variable "factorRango"*/
    public void aumentarVelocidad() {
        velocidad += 0.5f;
        factorRango *= (velocidad/2);
    }
    
    /**re-setea los atributos de la bola a sus estados iniciales*/
    public void resetearPos() {
	    x = 210;
	    y = 496;
	    sentidoX = 1;
	    sentidoY = -1;
	    anguloEntrada = 85;
    }

    public void cambiarEstadoEjecucion() {
        ejecucion = !ejecucion;
    }
    
    public BolaView toView() {
    	return new BolaView(x, y);
    }

}