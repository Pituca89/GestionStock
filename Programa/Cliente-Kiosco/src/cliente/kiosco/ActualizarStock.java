package cliente.kiosco;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import javax.swing.ImageIcon;

public class ActualizarStock extends JPanel {
	private JTextField codProducto;
	private JTextField stockMin;
	private JTextField stockActual;
	private JButton btnBuscar;
	private JLabel lblCodigo;
	private JLabel lblDesc;
	private boolean type;
	Gson gson;
	String data = "";
	LineaDeVenta[] lineaDeVentas;

	public JTextField getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(JTextField codProducto) {
		this.codProducto = codProducto;
	}

	public JTextField getStockMin() {
		return stockMin;
	}

	public void setStockMin(JTextField stockMin) {
		this.stockMin = stockMin;
	}

	public JTextField getStockActual() {
		return stockActual;
	}

	public void setStockActual(JTextField stockActual) {
		this.stockActual = stockActual;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	/**
	 * Create the panel.
	 */
	public ActualizarStock() {
		
		JLabel lblActualizarStokc = new JLabel("Actualizar Stock");
		lblActualizarStokc.setBounds(208, 11, 133, 20);
		lblActualizarStokc.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblCodigoDeProducto = new JLabel(" Codigo de Producto");
		lblCodigoDeProducto.setForeground(new Color(255, 255, 255));
		lblCodigoDeProducto.setBackground(new Color(0, 153, 0));
		lblCodigoDeProducto.setBounds(10, 54, 189, 28);
		lblCodigoDeProducto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblActualizarStokc.setOpaque(true);
		lblCodigoDeProducto.setOpaque(true);
		
		codProducto = new JTextField();
		codProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codProducto.setBounds(10, 83, 189, 20);
		codProducto.setColumns(10);
		
		codProducto.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!codProducto.getText().isEmpty())
					buscarProducto();	
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!codProducto.getText().isEmpty())
					buscarProducto();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {	
				if(!codProducto.getText().isEmpty())
					buscarProducto();
			}	
			
		});
		codProducto.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {

			}
			
			@Override
			public void keyReleased(KeyEvent e) {

			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			
			}
		});
		
		btnBuscar = new JButton("        BUSCAR");
		btnBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		btnBuscar.setIcon(new ImageIcon("./imagen/binoculares.png"));
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(0, 153, 0));
		btnBuscar.setBounds(282, 54, 225, 49);
		btnBuscar.setFocusable(false);
		JLabel lblStockMinimo = new JLabel(" Stock Minimo");
		lblStockMinimo.setBackground(new Color(0, 153, 0));
		lblStockMinimo.setForeground(new Color(255, 255, 255));
		lblStockMinimo.setBounds(10, 255, 225, 28);
		lblStockMinimo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStockMinimo.setOpaque(true);
		
		stockMin = new JTextField();
		stockMin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		stockMin.setBounds(10, 283, 225, 28);
		stockMin.setColumns(10);
		
		JLabel lblStockActual = new JLabel(" Stock Actual");
		lblStockActual.setBackground(new Color(0, 153, 0));
		lblStockActual.setForeground(new Color(255, 255, 255));
		lblStockActual.setBounds(282, 255, 225, 28);
		lblStockActual.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStockActual.setOpaque(true);
		
		stockActual = new JTextField();
		stockActual.setFont(new Font("Tahoma", Font.PLAIN, 14));
		stockActual.setBounds(282, 284, 225, 28);
		stockActual.setColumns(10);
		setLayout(null);
		add(lblActualizarStokc);
		add(lblCodigoDeProducto);
		add(codProducto);
		add(btnBuscar);
		add(lblStockActual);
		add(lblStockMinimo);
		add(stockActual);
		add(stockMin);
		
		JPanel panel = new JPanel();
		
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		panel.setBackground(new Color(0, 153, 0));
		panel.setBounds(10, 114, 497, 130);
		add(panel);
		panel.setLayout(null);
		
		lblCodigo = new JLabel("CODIGO");
		lblCodigo.setBounds(10, 11, 181, 30);
		panel.add(lblCodigo);
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCodigo.setForeground(new Color(255, 255, 255));
		
		lblDesc = new JLabel("DESCRIPCION");
		lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesc.setBounds(10, 52, 477, 45);
		panel.add(lblDesc);
		lblDesc.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDesc.setForeground(new Color(255, 255, 255));
		
	}
	public void buscarProducto() {
		Runnable hilo = new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!codProducto.getText().isEmpty()) {
					try {
						data = HTTPRequest.ConsultasServidor(HTTPRequest.NUEVO_ITEM, codProducto.getText());
						System.out.println(data);
						gson = new Gson();
						lineaDeVentas =  gson.fromJson(data, LineaDeVenta[].class);
							
						for(LineaDeVenta ldv: lineaDeVentas) {
							
							lblCodigo.setText(ldv.getCODIGO_BARRAS());
							lblDesc.setText(ldv.getNOMBRE());
							stockMin.setText(Integer.toString(ldv.getSTOCK_MINIMO()));
							stockActual.setText(Integer.toString(ldv.getCANT_DISPONIBLE()));
							
						}
						codProducto.setText("");
					} catch (IOException e) {
						JOptionPane.showInternalMessageDialog(null,"Ingrese un Codigo Valido!");
					}
				}
			}
			
		};
		Thread thread =  new Thread(hilo);
		thread.start();

		//cargarProducto();
	}
	public void cargarProducto() {
		if(!codProducto.getText().isEmpty()) {
			try {
				data = HTTPRequest.ConsultasServidor(HTTPRequest.NUEVO_ITEM, codProducto.getText());
				System.out.println(data);
				gson = new Gson();
				lineaDeVentas =  gson.fromJson(data, LineaDeVenta[].class);
					
				for(LineaDeVenta ldv: lineaDeVentas) {
					
					lblCodigo.setText(ldv.getCODIGO_BARRAS());
					lblDesc.setText(ldv.getNOMBRE());
					stockMin.setText(Integer.toString(ldv.getSTOCK_MINIMO()));
					stockActual.setText(Integer.toString(ldv.getCANT_DISPONIBLE()));
					
				}
				codProducto.setText("");
			} catch (IOException e) {
				JOptionPane.showInternalMessageDialog(null,"Ingrese un Codigo Valido!");
			}
		}
	}
	
	public JLabel getLblCodigo() {
		return lblCodigo;
	}

	public void setLblCodigo(JLabel lblCodigo) {
		this.lblCodigo = lblCodigo;
	}

	public JLabel getLblDesc() {
		return lblDesc;
	}

	public void setLblDesc(JLabel lblDesc) {
		this.lblDesc = lblDesc;
	}
}
