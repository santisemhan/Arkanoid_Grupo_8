package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class IniciarJuego extends JFrame {

	private static final long serialVersionUID = -908690305388995658L;

	private JButton btnIniciar;
	private JLabel control1,control2,control3,control4,control5,control;

	public IniciarJuego() {
		this.setSize(500, 700);
		this.setTitle("ARKANOID");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container c = this.getContentPane();
		c.setLayout(null);
		c.setBackground(Color.WHITE);

		control = new JLabel("CONTROLES",SwingConstants.CENTER);
		control.setBounds(130, 20, 210, 40);
		control.setFont(control.getFont().deriveFont(25f));
		
		control1 = new JLabel("Flecha izq: La barra va a la izquierda",SwingConstants.CENTER);
		control1.setBounds(20, 90, 420, 40);
		control1.setFont(control1.getFont().deriveFont(15f));
		
		control2 = new JLabel("Flecha der: La barra va a la derecha",SwingConstants.CENTER);
		control2.setBounds(20, 170, 420, 40);
		control2.setFont(control2.getFont().deriveFont(15f));
		
		control3 = new JLabel("Enter: Pausar/Reanudar juego",SwingConstants.CENTER);
		control3.setBounds(20, 250, 420, 40);
		control3.setFont(control3.getFont().deriveFont(15f));
		
		control4 = new JLabel("Barra espaciadora: Despegar bola",SwingConstants.CENTER);
		control4.setBounds(20, 330, 420, 40);
		control4.setFont(control4.getFont().deriveFont(15f));
		
		control5 = new JLabel("Barra espaciadora: Reanudar juego",SwingConstants.CENTER);
		control5.setBounds(20, 410, 420, 40);
		control5.setFont(control5.getFont().deriveFont(15f));
		
		btnIniciar = new JButton("Iniciar Juego");		
		btnIniciar.setBounds(84, 500, 300, 100);
		btnIniciar.setFont(btnIniciar.getFont().deriveFont(30f));
		
		c.add(btnIniciar);
		c.add(control);
		c.add(control1);
		c.add(control2);
		c.add(control3);
		c.add(control4);
		c.add(control5);
		
		btnIniciar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Principal();
				terminar();
			}
		});	
		
	}
	
	private void terminar() {
		this.dispose();
	}
}
