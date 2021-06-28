package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import controlador.Controlador;
import views.BarraView;
import views.BolaView;
import views.LadrilloView;
import views.PartidaView;

public class Principal extends JFrame {

	private static final long serialVersionUID = -7189647070719732198L;

	private JLabel lblBola,lblBarra,lblVidas,lblPuntaje,lblNivel;
	private List<JLabel> lblLadrillos;
	
	private Timer timerBola;
	
	public Principal() {
		configurar();
		eventos();
		this.setSize(250, 350);
		this.setTitle("ARKANOID");
		this.setVisible(true);
		this.setResizable(false);	
	}

	/**
	 * Se inicializan los objetos de la interfaz, junto con su ubicacion*/	
	private void configurar() {
		//Gestion de contenedor y layout
		Container c = this.getContentPane();
		c.setLayout(null);
		c.setBackground(Color.GRAY);
		//Se inicializan los JLABEL
		lblBarra = new JLabel(new ImageIcon(getClass().getResource("barra.jpg")));
		lblBola = new JLabel(new ImageIcon(getClass().getResource("bola.jpg")));	
		lblLadrillos = new ArrayList<JLabel>();
		for(int i = 0; i<25;i++) {
			lblLadrillos.add(new JLabel(new ImageIcon(getClass().getResource("ladrillo.png"))));
		}
		//Se obtienen los objetos "View" y se los dibuja en la interfaz
		BarraView barraView = Controlador.getInstancia().getBarra();	
		BolaView bolaView = Controlador.getInstancia().getBola();	
		List<LadrilloView> ladrillosView = Controlador.getInstancia().getLadrillos();		
		
		lblBarra.setBounds(barraView.getejeX(), barraView.getejeY(), 70, 30);	
		lblBola.setBounds((int)bolaView.getX(), (int)bolaView.getY(), 20, 20);		
		configurarLadrillos(ladrillosView);
		
		PartidaView partidaView = Controlador.getInstancia().getPartida();
		
		lblNivel = new JLabel("Nivel: " + partidaView.getNivel());
		lblPuntaje = new JLabel("Puntaje: "+ partidaView.getPuntaje());
		lblVidas = new JLabel("Vidas: " + partidaView.getVidas());
		
		lblNivel.setBounds(10, 270, 40, 50);
		lblVidas.setBounds(70, 270, 50, 50);
		lblPuntaje.setBounds(140, 270, 90, 50);	
		
		c.add(lblBarra);
		c.add(lblBola);	
		for(JLabel l: lblLadrillos) {
			c.add(l);
		}
		
		c.add(lblNivel);
		c.add(lblVidas);
		c.add(lblPuntaje);
	}

	/**Gestion de eventos de la interfaz grafica*/
	private void eventos() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Inicio sección "Timer" del programa
		timerBola = new Timer(1, new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				
				Controlador.getInstancia().moverBola();//Manejo del movimiento de la bola
				Controlador.getInstancia().impacto();//Manejo de impacto con la barra
				BolaView auxBola = Controlador.getInstancia().getBola();
				
				lblBola.setBounds((int)auxBola.getX(), (int)auxBola.getY(), 20, 20);
				
				int reseteo = Controlador.getInstancia().resetearBolaBarra();//Manejo de reseteos de barra y bola
				if(reseteo==0 || reseteo==1) {//Si reseteo = 0 la bola llego al fondo, si reseteo= 1 se rompieron todos los ladrillos
					BarraView auxBarra = Controlador.getInstancia().getBarra();
					lblBarra.setBounds(auxBarra.getejeX(), auxBarra.getejeY(), 70, 30);
					auxBola = Controlador.getInstancia().getBola();
					lblBola.setBounds((int)auxBola.getX(),(int) auxBola.getY(), 20, 20);					
					
					PartidaView auxPartida = Controlador.getInstancia().getPartida();
					lblVidas.setText("Vidas: " + auxPartida.getVidas());
					lblNivel.setText("Nivel: "+ auxPartida.getNivel());
					
					if(reseteo==1) {//Manejo de pasaje de nivel
						Controlador.getInstancia().resetearLadrillos();//Se cambia el atributo "destruido" de cada Ladrillo
						recrearLadrillos();//Se re-dibujan todos los ladrillos
					}
											
					timerBola.stop();
				}				
				
				Controlador.getInstancia().golpeLadrillo();//Manejo de impacto entre la bola y los ladrillos
				Controlador.getInstancia().sumarVidas();//Manejo de incremento de vidas de la partida
				
				PartidaView auxPartida = Controlador.getInstancia().getPartida();
				lblPuntaje.setText("Puntaje: "+ auxPartida.getPuntaje());
				lblVidas.setText("Vidas: " + auxPartida.getVidas());
				
				List<LadrilloView> auxLadrillos = Controlador.getInstancia().getLadrillos();
				configurarLadrillos(auxLadrillos);
				
				if(Controlador.getInstancia().comprobarFinPartida()) {
					JOptionPane.showMessageDialog(rootPane, "GAME OVER");
					
					if(Controlador.getInstancia().comprobarPuntaje()) {//Comprueba si corresponde agregar la partida al registro
						String nombre = JOptionPane.showInputDialog(rootPane, "Ingrese su nombre:");//Solicita el nombre
						Controlador.getInstancia().agregarARegistro(nombre);//Ingresa el nombre y puntaje al registro
					}
					
					Controlador.getInstancia().iniciarJuego();//Se inicia una nueva partida
					auxLadrillos = Controlador.getInstancia().getLadrillos();
					recrearLadrillos();//Se re-dibujan todos los ladrillos en la interfaz
					configurarLadrillos(auxLadrillos);//Se re-configuran los ladrillos en la interfaz
					
					auxPartida = Controlador.getInstancia().getPartida();
					lblPuntaje.setText("Puntaje: "+ auxPartida.getPuntaje());
					lblVidas.setText("Vidas: " + auxPartida.getVidas());
					lblNivel.setText("Nivel: " + auxPartida.getNivel());
				}
				
			}
		});
		//Fin sección "Timer" del programa
		
		//Inicio sección "KeyListener" del programa
		this.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {}

			public void keyReleased(KeyEvent e) {}

			public void keyPressed(KeyEvent key) {
				
				if(key.getKeyCode() == 32 && !Controlador.getInstancia().getPartida().isEjecucion() && timerBola.isRunning()) {// Tecla: barra espacioadora
					Controlador.getInstancia().cambiarEstadoEjecucion();//Pausar/Reanudar el juego 
				}
				
				if(key.getKeyCode() == 32) {// Tecla: barra espacioadora
					timerBola.start();//Ordena el inicio del "Timer"
				}	
				if((key.getKeyCode() == 37 || key.getKeyCode() == 39) && timerBola.isRunning()) { //Teclas: flecha izquierda y derecha
					Controlador.getInstancia().moverBarra(key.getKeyCode());//Le pide al controlador que mueva la barra
					BarraView auxBarra = Controlador.getInstancia().getBarra();
					lblBarra.setBounds(auxBarra.getejeX(), auxBarra.getejeY(), 70, 30);//Re-dibuja la nueva posición de la barra
				}
				if(key.getKeyCode() == 10) {//Tecla: enter
					Controlador.getInstancia().cambiarEstadoEjecucion();//Pausar/Reanudar el juego 
				}
			}
		});
		//Fin sección "KeyListener" del programa
	}
	
	/**
	 * Dibuja o Re-Dibuja la totalidad de los ladrillos en la interfaz grafica*/
	private void recrearLadrillos() {
		for(JLabel ladrillo: lblLadrillos) {
			ladrillo.setVisible(true);
		}
	}
	
	/**
	 * Gestiona los ladrillos que aparecen en la interfaz grafica. Si fueron destruidos no son dibujados*/
	private void configurarLadrillos(List<LadrilloView> ladrillosView){
		int vuelta = 0;
		int inicio = 0;
		for(int i = 0; i<25;i++) {
			if(i%5==0 && i!=0) {
				vuelta++;
				inicio = 0;
			}
			if(!ladrillosView.get(i).isDestruido()) {			
				JLabel ladrillo = lblLadrillos.get(i);
				ladrillo.setBounds(inicio*ladrillosView.get(i).getAncho(), vuelta*ladrillosView.get(i).getAlto(), 
						ladrillosView.get(i).getAncho(), ladrillosView.get(i).getAlto());
			}else {
				JLabel ladrillo = lblLadrillos.get(i);				
				ladrillo.setVisible(false);
			}
			inicio++;
		}
		
	}
	
}
