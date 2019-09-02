package cliente.kiosco;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Configuracion extends JPanel {
	private JTextField ip;
	private JTextField puerto;

	/**
	 * Create the panel.
	 */
	public Configuracion() {
		
		JLabel lblConfiguracion = new JLabel("Configuracion");
		
		JLabel lblDireccionIp = new JLabel("Direccion IP");
		
		ip = new JTextField();
		ip.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Puerto");
		
		puerto = new JTextField();
		puerto.setColumns(10);
		
		JButton btnConectar = new JButton("CONECTAR");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					conectarmeActionPerformed();
				}catch(Exception e) {
					System.out.println("error de conexion desde el cliente");
				}
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(178)
					.addComponent(lblConfiguracion))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(60)
					.addComponent(lblDireccionIp)
					.addGap(158)
					.addComponent(ip, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(60)
					.addComponent(lblNewLabel)
					.addGap(182)
					.addComponent(puerto, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(178)
					.addComponent(btnConectar))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(lblConfiguracion)
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblDireccionIp))
						.addComponent(ip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(62)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel))
						.addComponent(puerto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addComponent(btnConectar))
		);
		setLayout(groupLayout);

	}
	   private void conectarmeActionPerformed() {
	    	//InicioCliente.arrancarCliente(muestraIp.getText(),Integer.parseInt(puertoServ.getText())); 
		   Runnable hilo = new Runnable() {
			
			@Override
			public void run() {
				InicioCliente.arrancarCliente(ip.getText(),Integer.parseInt(puerto.getText()));
		    	InicioCliente.procesarMensajes();
				
				}
			};
			
			Thread thread = new Thread(hilo);
			thread.start();
	    	
	   }

}
