package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.Controlador;

public class AgregarRegistro extends JFrame {

	private static final long serialVersionUID = 6379718712796709828L;

	private JLabel lblNombre,lblPuntaje,lblFelicitaciones,lblFelicitaciones2;
	private JTextField txtNombre;
	private JButton btnGuardar,btnLimpiar;
	
	
	public AgregarRegistro() {
		configurar();
		eventos();
		this.setSize(500, 700);
		this.setResizable(false);
		this.setTitle("Registro");
		this.setVisible(true);
	}
	
	private void configurar() {
		
		Container c = this.getContentPane();
		c.setLayout(null);
		c.setBackground(Color.WHITE);
		
		lblFelicitaciones = new JLabel("¡Felicitaciones!",SwingConstants.CENTER);
		lblFelicitaciones.setBounds(60, 50, 350, 60);
		lblFelicitaciones.setFont(lblFelicitaciones.getFont().deriveFont(25f));
		
		lblFelicitaciones2 = new JLabel("Obtuvo uno de los máximos puntajes",SwingConstants.CENTER);
		lblFelicitaciones2.setBounds(15, 100, 450, 60);
		lblFelicitaciones2.setFont(lblFelicitaciones2.getFont().deriveFont(25f));
		
		lblPuntaje = new JLabel(Controlador.getInstancia().getPartida().toString(),SwingConstants.CENTER);
		lblPuntaje.setBounds(50, 200, 350, 60);
		lblPuntaje.setFont(lblPuntaje.getFont().deriveFont(25f));
		
		lblNombre = new JLabel("Agregue su nombre para agregarlo a los mejores puntajes");
		lblNombre.setBounds(20, 330, 460, 60);
		lblNombre.setFont(lblNombre.getFont().deriveFont(16f));
		
		txtNombre = new JTextField();
		txtNombre.setBounds(30, 400, 415, 50);
		txtNombre.setFont(txtNombre.getFont().deriveFont(20f));
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(30, 525, 180, 60);
		btnLimpiar.setFont(btnLimpiar.getFont().deriveFont(20f));
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(270, 525, 180, 60);
		btnGuardar.setFont(btnGuardar.getFont().deriveFont(20f));
		
		c.add(lblFelicitaciones);
		c.add(lblFelicitaciones2);
		c.add(lblPuntaje);
		c.add(lblNombre);
		c.add(txtNombre);
		c.add(btnGuardar);
		c.add(btnLimpiar);
	}

	private void eventos() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstancia().agregarARegistro(txtNombre.getText());	
				new TablaPuntajes();
				terminar();
			}
		});
			
		btnLimpiar.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				txtNombre.setText("");				
			}
		});
	}
	
	private void terminar() {
		this.dispose();
	}
}
