package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controlador.Controlador;
import views.JugadorView;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TablaPuntajes extends JFrame {

	private static final long serialVersionUID = 3358517820595678374L;
	private List<JLabel> nombresPuntajes;
	private JLabel lblPuntajeActual;
	private JButton btnVolver;

	public TablaPuntajes() {		
		configurar();
		eventos();
		this.setSize(500, 700);
		this.setTitle("HIGH SCORES");
		this.setVisible(true);
		this.setResizable(false);
	}

	private void configurar() {		
		Container c = this.getContentPane();
		c.setLayout(null);
		c.setBackground(Color.WHITE);
		
		nombresPuntajes = new ArrayList<JLabel>();
		for(int i= 0; i<20;i++) {
			nombresPuntajes.add(new JLabel("",SwingConstants.RIGHT));
		}
		
		List<JugadorView> jugadoresView = Controlador.getInstancia().getJugadores();		
		configurarTableroPuntajes(jugadoresView);
		
		btnVolver = new JButton("Volver a Jugar");
		btnVolver.setBounds(100, 550, 260, 60);
		btnVolver.setFont(btnVolver.getFont().deriveFont(25f));
		
		lblPuntajeActual = new JLabel("Usted obtuvo " + Controlador.getInstancia().getPartida().getPuntaje() + " puntos",SwingConstants.CENTER);
		lblPuntajeActual.setBounds(80, 500, 300, 30);
		lblPuntajeActual.setFont(lblPuntajeActual.getFont().deriveFont(16.5f));		
		
		for(JLabel p: nombresPuntajes) {
			c.add(p);
		}		
		c.add(btnVolver);
		c.add(lblPuntajeActual);
	}

	private void eventos() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Principal().reiniciarPartida();
				terminar();
			}
		});
	}
	
	private void configurarTableroPuntajes(List<JugadorView> jugadores) {
		int x = 10;
		int y = 10;
		int i = 0;
		for (JugadorView jugador: jugadores) {			
			if(i==10) {
				x = 240;
				y = 10;
			}			
			JLabel j = nombresPuntajes.get(i);
			j.setText(jugador.toString());
			j.setBounds(x, y, 200, 80);
			j.setFont(j.getFont().deriveFont(18f));
			y+=40;
			i++;
		}	
	}
	
	private void terminar() {
		this.dispose();
	}
}
