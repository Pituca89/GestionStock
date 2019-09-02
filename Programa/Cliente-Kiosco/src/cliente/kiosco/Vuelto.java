package cliente.kiosco;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.RenderingHints.Key;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox.KeySelectionManager;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Vuelto extends JFrame {

	private JPanel contentPane;
	private JTextField efectivo;
	private String data;
	JLabel lblsaldo;
	JLabel lblpagado;
	String json;

	public Vuelto(String json_1) {
		
		this.json = json_1;
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                exitForm(evt);
                
            }
        });
		setIconImage(Toolkit.getDefaultToolkit().getImage(Vuelto.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl1 = new JLabel("TOTAL A PAGAR");
		lbl1.setBounds(10, 11, 261, 41);
		contentPane.add(lbl1);
		lbl1.setBackground(new Color(0, 153, 0));
		lbl1.setForeground(Color.WHITE);
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setOpaque(true);
		
		JLabel lblpagar = new JLabel("$ 0.0");
		lblpagar.setBounds(10, 52, 261, 51);
		contentPane.add(lblpagar);
		lblpagar.setForeground(new Color(0, 153, 0));
		lblpagar.setBackground(new Color(0, 0, 0));
		lblpagar.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblpagar.setHorizontalAlignment(SwingConstants.CENTER);
		lblpagar.setOpaque(true);
		
		JLabel lblTotalPagado = new JLabel("TOTAL PAGADO");
		lblTotalPagado.setOpaque(true);
		lblTotalPagado.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalPagado.setForeground(Color.WHITE);
		lblTotalPagado.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalPagado.setBackground(new Color(0, 153, 0));
		lblTotalPagado.setBounds(10, 114, 261, 41);
		contentPane.add(lblTotalPagado);
		
		lblpagado = new JLabel("$ 0.0");
		lblpagado.setOpaque(true);
		lblpagado.setHorizontalAlignment(SwingConstants.CENTER);
		lblpagado.setForeground(new Color(0, 153, 0));
		lblpagado.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblpagado.setBackground(Color.BLACK);
		lblpagado.setBounds(10, 155, 261, 51);
		contentPane.add(lblpagado);
		
		JLabel lblSaldo = new JLabel("VUELTO");
		lblSaldo.setOpaque(true);
		lblSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaldo.setForeground(Color.WHITE);
		lblSaldo.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblSaldo.setBackground(new Color(0, 153, 0));
		lblSaldo.setBounds(325, 11, 293, 92);
		contentPane.add(lblSaldo);
		
		lblsaldo = new JLabel("$ 0.0");
		lblsaldo.setOpaque(true);
		lblsaldo.setHorizontalAlignment(SwingConstants.CENTER);
		lblsaldo.setForeground(new Color(0, 153, 0));
		lblsaldo.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblsaldo.setBackground(Color.BLACK);
		lblsaldo.setBounds(325, 103, 293, 103);
		contentPane.add(lblsaldo);
		
		JButton btnEfectivo = new JButton("EFECTIVO");
		btnEfectivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obtenerVuelto();
			}
		});
		btnEfectivo.setBackground(new Color(0, 153, 0));
		btnEfectivo.setForeground(new Color(255, 255, 255));
		btnEfectivo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEfectivo.setBounds(162, 246, 109, 41);
		contentPane.add(btnEfectivo);
		
		efectivo = new JTextField();
		efectivo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				obtenerVuelto();
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == e.VK_ENTER) {
					System.out.println("presione enter");
					procesarVenta();
				}
				else if(e.getKeyCode() == e.VK_ESCAPE)
					dispose();
			}
		});
		

		efectivo.setHorizontalAlignment(SwingConstants.CENTER);
		efectivo.setFont(new Font("Tahoma", Font.PLAIN, 26));
		efectivo.setBounds(10, 246, 142, 41);
		contentPane.add(efectivo);
		efectivo.setColumns(10);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		 
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
            	
                dispose();
            }
        });
		
		JButton btnFinalizarVenta = new JButton("FINALIZAR VENTA");
		btnFinalizarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				procesarVenta();
						
			}
		});
		btnFinalizarVenta.setBackground(new Color(0, 153, 0));
		btnFinalizarVenta.setForeground(new Color(255, 255, 255));
		btnFinalizarVenta.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFinalizarVenta.setBounds(162, 317, 286, 51);
		contentPane.add(btnFinalizarVenta);
		
		lblpagar.setText(VentasDiarias.MontoTotal());
	}
	
	public void obtenerVuelto() {
		double total = Double.parseDouble(VentasDiarias.MontoTotal());
		if(efectivo.getText() != "") {
			double efec =  Double.parseDouble(efectivo.getText());
			double vuelto = efec - total;
			lblpagado.setText(Double.toString(efec));
			lblsaldo.setText(Double.toString(vuelto));
		}
		
	}
	
	public void procesarVenta() {
		Runnable hilo = new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println(json);
					data = HTTPRequest.ConsultasServidor(HTTPRequest.PROCESAR_VENTA, json);	
					System.out.println("mensaje: " + data);
					if(!data.isEmpty()) {
						if(data.contains("stock")) {
							//JOptionPane.showMessageDialog(null,"La venta se registro correctamente","Información",JOptionPane.INFORMATION_MESSAGE);								
							JOptionPane.showMessageDialog(null,"Falta stock para uno de los productos","Información",JOptionPane.ERROR_MESSAGE);
							dispose();
						}else {
							VentasDiarias.RefreshTable();
							dispose();
							
						}
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		
		Thread thread =  new Thread(hilo);
		thread.start();	
	}
	private void exitForm(WindowEvent evt) {
    	
    }
	
}
