package cliente.kiosco;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ListaProdVenta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JTextField codProducto;
	private JTable table;
	static Gson gson;
	static String data = "";
	static Stock[] stock;
	VentasDiarias ventas;

	public ListaProdVenta(VentasDiarias ventas_1) {
		
		this.ventas = ventas_1;
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaProdVenta.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		setBounds(100, 100, 683, 500);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 667, 428);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			lblNewLabel = new JLabel("Productos");
			lblNewLabel.setBounds(287, 16, 81, 20);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		}
		JLabel lblDescripcionDeProducto = new JLabel(" Descripcion de Producto");
		lblDescripcionDeProducto.setForeground(new Color(255, 255, 255));
		lblDescripcionDeProducto.setBackground(new Color(0, 153, 0));
		lblDescripcionDeProducto.setBounds(25, 71, 182, 24);
		lblDescripcionDeProducto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescripcionDeProducto.setOpaque(true);
		codProducto = new JTextField();
		codProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == e.VK_ENTER) {
					buscarProducto(); 
				}
			}
		});
		codProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codProducto.setBounds(25, 96, 182, 24);
		codProducto.setColumns(10);
		JButton btnBuscar = new JButton("     BUSCAR");
		btnBuscar.requestFocus(true);
		btnBuscar.setFocusable(true);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscarProducto(); 
		    	
			}
		});
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(0, 153, 0));
		btnBuscar.setIcon(new ImageIcon("./imagen/binoculares.png"));
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscar.setBounds(241, 71, 202, 49);
		btnBuscar.setFocusable(false);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 142, 618, 234);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Seleccionar", "Codigo", "Descripci\u00F3n", "Costo Unitario", "Cantidad"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getTableHeader().setBackground(new Color(0, 153, 0));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		contentPanel.setLayout(null);
		contentPanel.add(lblDescripcionDeProducto);
		contentPanel.add(codProducto);
		contentPanel.add(btnBuscar);
		contentPanel.add(scrollPane);
		
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//buscarProducto();
				DefaultTableModel modal = new DefaultTableModel();
				DefaultTableModel modalPadre = new DefaultTableModel();
				modal = (DefaultTableModel) table.getModel();
				modalPadre = (DefaultTableModel) ventas.getTable().getModel();

				for (int i = table.getRowCount() -1; i >= 0; i--){
					
					if(table.getValueAt(i, 0) != null) {
						
						Object[] row = new Object[6];
						row[0] = table.getValueAt(i, 1);
						row[1] = table.getValueAt(i, 2);
						row[2] = 1;
						row[3] = table.getValueAt(i, 3);
						row[4]= table.getValueAt(i, 3);
						
						modalPadre.addRow(row);
					}
					VentasDiarias.obtenerTotal();
					
				}
				dispose();
			}
		});
		btnConfirmar.setBounds(373, 393, 135, 35);
		contentPanel.add(btnConfirmar);
		
		JButton btnNewButton = new JButton("CANCELAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(150, 393, 135, 35);
		contentPanel.add(btnNewButton);
		contentPanel.requestFocus();
		
		/**
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 428, 667, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			buttonPane.setFocusable(false);
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("CONFIRMAR");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//buscarProducto();
						DefaultTableModel modal = new DefaultTableModel();
						DefaultTableModel modalPadre = new DefaultTableModel();
						modal = (DefaultTableModel) table.getModel();
						modalPadre = (DefaultTableModel) ventas.getTable().getModel();

						for (int i = table.getRowCount() -1; i >= 0; i--){
							
							if(table.getValueAt(i, 0) != null) {
								
								Object[] row = new Object[6];
								row[0] = table.getValueAt(i, 1);
								row[1] = table.getValueAt(i, 2);
								row[2] = table.getValueAt(i, 4);
								row[3] = 1;
								row[4] = table.getValueAt(i, 3);
								row[5] = table.getValueAt(i, 3);
								
								modalPadre.addRow(row);
							}
							VentasDiarias.obtenerTotal();
							
						}
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				okButton.setFocusable(false);
				okButton.requestFocus(false);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
			}
			{
				JButton cancelButton = new JButton("CANCELAR");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.setFocusable(false);
			}
		}
		**/
	}
	
	public void buscarProducto() {
		Runnable hilo1 = new Runnable() {
			
			@Override
			public void run() {
				try {
					JCheckBox check;
					gson = new Gson();
					data = HTTPRequest.ConsultasServidor(HTTPRequest.UN_STOCK, codProducto.getText());
					if(!data.isEmpty()) {
						DefaultTableModel modal = new DefaultTableModel();
						modal = (DefaultTableModel) table.getModel();
						for (int i = table.getRowCount() -1; i >= 0; i--){
							modal.removeRow(i);
						}
						stock =  gson.fromJson(data, Stock[].class);
						
						for(Stock s: stock) {
							
							Object[] row = new Object[5];
							
							row[1] = s.getProducto_id();
							row[2] = s.getNombre();
							row[3] = s.getPrecio_venta();
							if(s.getStock_actual() <= 0)
								row[4] = "<html><div style=\"background:#FF0000;padding-right:160;padding-top:5\"><font color=\"white\">"+s.getStock_actual()+"</font></div></html>";
							else
								row[4] = s.getStock_actual();
							
							modal.addRow(row);
							
						}
						//table.addMouseListener(mouseAdapter);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		
		Thread thread =  new Thread(hilo1);
		thread.start();
	}
}
