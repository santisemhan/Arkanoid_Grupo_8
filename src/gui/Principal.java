package gui;

import java.awt.Color;
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
	private JLabel lblBola,lblBarra,lblVidas,lblPuntaje,lblNivel,lblPausa;	
	private List<JLabel> lblLadrillos;	
	private Timer timerBola;
	
	public Principal() {
		configurar();
		eventos();
		this.setSize(500, 700);
		this.setTitle("ARKANOID");
		this.setVisible(true);
		this.setResizable(false);
	}
	
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
			if(i<5) {
				lblLadrillos.add(new JLabel(new ImageIcon(getClass().getResource("ladrillo.png"))));
			}
			else if(i<10){
				lblLadrillos.add(new JLabel(new ImageIcon(getClass().getResource("ladrilloVerde.png"))));
			}
			else if (i<15){
				lblLadrillos.add(new JLabel(new ImageIcon(getClass().getResource("ladrilloMarron.png"))));
			}
			else if (i<20){
				lblLadrillos.add(new JLabel(new ImageIcon(getClass().getResource("ladrilloRojo.png"))));
			}
			else {
				lblLadrillos.add(new JLabel(new ImageIcon(getClass().getResource("ladrilloCeleste.png"))));
			}
		}
		//Se obtienen los objetos "View" y se los dibuja en la interfaz
		BarraView barraView = Controlador.getInstancia().getBarra();	
		BolaView bolaView = Controlador.getInstancia().getBola();	
		List<LadrilloView> ladrillosView = Controlador.getInstancia().getLadrillos();		
		
		lblBarra.setBounds(barraView.getejeX(), barraView.getejeY(), 140, 60);	
		lblBola.setBounds((int)bolaView.getX(), (int)bolaView.getY(), 40, 40);		
		configurarLadrillos(ladrillosView);
		
		PartidaView partidaView = Controlador.getInstancia().getPartida();
		
		lblNivel = new JLabel("Nivel: " + partidaView.getNivel());
		lblPuntaje = new JLabel("Puntaje: "+ partidaView.getPuntaje());
		lblVidas = new JLabel("Vidas: " + partidaView.getVidas());
		
		lblNivel.setBounds(20, 540, 120, 100);
		lblNivel.setFont(lblNivel.getFont().deriveFont(25f));
		lblVidas.setBounds(150, 540, 120, 100);
		lblVidas.setFont(lblVidas.getFont().deriveFont(25f));
		lblPuntaje.setBounds(290, 540, 200, 100);	
		lblPuntaje.setFont(lblPuntaje.getFont().deriveFont(25f));
		
		lblPausa = new JLabel("PAUSA");
		lblPausa.setBounds(200, 160, 180, 80);
		lblPausa.setFont(lblPausa.getFont().deriveFont(25f));
		lblPausa.setVisible(false);
		
		c.add(lblPausa);
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
				Controlador.getInstancia().impacto();//Manejo del impacto de la bola con la barra
				
				if(Controlador.getInstancia().resetearPasoNivel()) {//Manejo del paso de nivel					
					timerBola.stop();
					recrearLadrillos();
				}
				else if(Controlador.getInstancia().resetearPerdidaVida()){//Manejo de la perdida de vida	
					timerBola.stop();
				}
				
				Controlador.getInstancia().golpeLadrillo();//Manejo de impacto entre la bola y los ladrillos
				Controlador.getInstancia().sumarVidas();//Manejo de incremento de vidas de la partida
				//Inicio seccion de re-dibujo
				BarraView auxBarra = Controlador.getInstancia().getBarra();
				lblBarra.setBounds(auxBarra.getejeX(), auxBarra.getejeY(), 140, 60);
				BolaView auxBola = Controlador.getInstancia().getBola();
				lblBola.setBounds((int)auxBola.getX(),(int) auxBola.getY(), 40, 40);
				
				PartidaView auxPartida = Controlador.getInstancia().getPartida();
				lblPuntaje.setText("Puntaje: "+ auxPartida.getPuntaje());
				lblVidas.setText("Vidas: " + auxPartida.getVidas());
				lblNivel.setText("Nivel: "+ auxPartida.getNivel());
				
				List<LadrilloView> auxLadrillos = Controlador.getInstancia().getLadrillos();
				configurarLadrillos(auxLadrillos);
				//Fin seccion re-dibujo
				
				if(Controlador.getInstancia().comprobarFinPartida()) {//Manejo de fin de partida
					JOptionPane.showMessageDialog(rootPane, "GAME OVER");					
					if(Controlador.getInstancia().comprobarPuntaje()) {//Comprueba si corresponde agregar la partida al registro
						new AgregarRegistro();//Inicializa la ventana AgregarRegistro
						terminar();
					}
					else {
						new TablaPuntajes();//Inicializa la ventana TablaPuntajes
						terminar();
					}
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
					Controlador.getInstancia().cambiarEstadoEjecucion();//Reanudar el juego
					lblPausa.setVisible(false);
				}
				
				if(key.getKeyCode() == 32) {// Tecla: barra espacioadora
					timerBola.start();//Ordena el inicio del "Timer"
				}
				
				if((key.getKeyCode() == 37 || key.getKeyCode() == 39) && timerBola.isRunning()) { //Teclas: flecha izquierda y derecha
					Controlador.getInstancia().moverBarra(key.getKeyCode());//Le pide al controlador que mueva la barra
					BarraView auxBarra = Controlador.getInstancia().getBarra();
					lblBarra.setBounds(auxBarra.getejeX(), auxBarra.getejeY(), 140, 60);//Re-dibuja la nueva posición de la barra
				}
				if(key.getKeyCode() == 10) {//Tecla: enter
					Controlador.getInstancia().cambiarEstadoEjecucion();//Pausar/Reanudar el juego 
					
					if(!Controlador.getInstancia().getPartida().isEjecucion()) {//Hace visible el lblPausa si corresponde
						lblPausa.setVisible(true);
					}
					else {
						lblPausa.setVisible(false);
					}
					
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
		int y = 0;
		int x = 0;
		for(int i = 0; i<25;i++) {
			if(i%5==0 && i!=0) {
				y++;
				x = 0;
			}
			if(!ladrillosView.get(i).isDestruido()) {			
				JLabel ladrillo = lblLadrillos.get(i);
				ladrillo.setBounds(x*ladrillosView.get(i).getAncho(), y*ladrillosView.get(i).getAlto(), 
						ladrillosView.get(i).getAncho(), ladrillosView.get(i).getAlto());
			}else {
				JLabel ladrillo = lblLadrillos.get(i);				
				ladrillo.setVisible(false);
			}
			x++;
		}
		
	}
	
	private void terminar() {//Cierra la ventana actual
		this.dispose();
	}
	
	public void reiniciarPartida() {
		Controlador.getInstancia().iniciarJuego();//Se inicia una nueva partida
		List<LadrilloView> auxLadrillos = Controlador.getInstancia().getLadrillos();
		recrearLadrillos();//Se re-dibujan todos los ladrillos en la interfaz
		configurarLadrillos(auxLadrillos);//Se re-configuran los ladrillos en la interfaz
		
		PartidaView auxPartida = Controlador.getInstancia().getPartida();
		lblPuntaje.setText("Puntaje: "+ auxPartida.getPuntaje());
		lblVidas.setText("Vidas: " + auxPartida.getVidas());
		lblNivel.setText("Nivel: " + auxPartida.getNivel());
	}
	
}
