package cliente.kiosco;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JTextField;
import javax.swing.RepaintManager;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.google.gson.Gson;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.BevelBorder;

import java.awt.AWTException;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodEvent;
import net.miginfocom.swing.MigLayout;
import java.awt.event.KeyAdapter;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class VentasDiarias extends JPanel implements interfaceMenu{

	private static JTable table;
	Gson gson;
	String data = "";
	LineaDeVenta[] lineaDeVentas;
	static JLabel numero;
	MouseAdapter mouseAdapter;
	static double total = 0;
	Cambio_cantidad cambio_cantidad = new Cambio_cantidad(VentasDiarias.this);
	static JLabel lbltotal;
	private JTextField textField;
	private String lectura;
	private boolean type;
	int itemMenu;
	public static int CONFIRM = 0;
	Login login;
	/**
	 * Create the panel.
	 */
	public VentasDiarias() {
		
		gson = new Gson();
		login = new Login(this);
		Calendar fecha = new GregorianCalendar();
		String dia = Integer.toString(fecha.get(Calendar.DATE));
		String mes = Integer.toString(fecha.get(Calendar.MONTH) + 1);
		String anio = Integer.toString(fecha.get(Calendar.YEAR));
		mouseAdapter = new MouseAdapter() {
			 public void mousePressed(MouseEvent Mouse_evt) {
				 JTable tbl =(JTable) Mouse_evt.getSource();
				 Point point = Mouse_evt.getPoint();
				 int row = tbl.rowAtPoint(point);
				 if (Mouse_evt.getClickCount() == 2) {
					 
					 cambio_cantidad.getLblCodigo().setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					 cambio_cantidad.getLblNewLabel().setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					 cambio_cantidad.getCantidad().setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					 cambio_cantidad.getPrecio().setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					 cambio_cantidad.getCantidad().selectAll();
					 cambio_cantidad.setVisible(true);					 
				 }
			 }
		};
		int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        setSize(ancho,alto);
		JButton btnNewButton = new JButton("CANCELAR VENTA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RefreshTable();
			}
		});
		
		JLabel lblCodigoProducto = new JLabel(" Codigo Producto");
		lblCodigoProducto.setForeground(new Color(255, 255, 255));
		lblCodigoProducto.setBackground(new Color(0, 153, 0));
		lblCodigoProducto.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCodigoProducto.setOpaque(true);
		
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.setIcon(new ImageIcon("./imagen/binoculares.png"));
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(0, 153, 0));
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaProdVenta lp = new ListaProdVenta(VentasDiarias.this);
				lp.setVisible(true);
			}
		});
		btnBuscar.setFocusable(false);
		JLabel lblVentaDiaria = new JLabel("Venta del dia: " + dia + "/" + mes + "/" + anio);
		lblVentaDiaria.setHorizontalAlignment(SwingConstants.CENTER);
		lblVentaDiaria.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
			
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {	
				
			}	
			
		});

		
		textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {

			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == e.VK_F2) 
					
					Cobrar();	
				if(e.getKeyCode() == e.VK_F3) {
					int input = JOptionPane.showConfirmDialog(null, 
			                "La cantidad ingresada se descontara del Stock", "Atención!", 
			                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(input == 0) {
						itemMenu = CONFIRM;
						login.setVisible(true);
					}
				}
				if(e.getKeyCode() == e.VK_ENTER) {
					ingresarItem();					
				}
			}
		});
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
		
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setLabel("Menu Secundario");
		addPopup(textField, popupMenu);
		popupMenu.setBounds(20, 80, 118, 16);
		
		JMenuItem mntmPegar = new JMenuItem("Pegar");
		popupMenu.add(mntmPegar);
		mntmPegar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.paste();			
			}
		});
		
		JMenuItem mntmCopiar = new JMenuItem("Copiar");
		popupMenu.add(mntmCopiar);
				
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 16));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Descripci\u00F3n", "Cant", "C/U", "Subtotal"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(450);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(35);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.setRowHeight(25);
		table.addMouseListener(mouseAdapter);
		table.getTableHeader().setBackground(new Color(0, 153, 0));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 153, 0));
		panel.setBackground(new Color(0, 0, 0));
		panel.setBorder(new LineBorder(new Color(0, 153, 0), 4));
		
		JLabel lblNewLabel = new JLabel(" $");
		lblNewLabel.setForeground(new Color(0, 153, 0));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		lbltotal = new JLabel("0.0");
		lbltotal.setForeground(new Color(0, 153, 0));
		lbltotal.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbltotal.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(lbltotal, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(296, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(87)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbltotal, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(new Color(0, 153, 0));
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DefaultTableModel modelo = (DefaultTableModel) table.getModel();
					total -= Double.parseDouble(table.getValueAt(table.getSelectedRow(), 4).toString());
					lbltotal.setText(Double.toString(total));
					modelo.removeRow(table.getSelectedRow());
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un registro");
				}
				
			}
		});
		
		JButton btn_nuevaVenta = new JButton("F2 - COBRAR");
		btn_nuevaVenta.setForeground(new Color(255, 255, 255));
		btn_nuevaVenta.setBackground(new Color(0, 153, 0));
		btn_nuevaVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				Cobrar();
			}
		});
		btn_nuevaVenta.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBackground(new Color(0, 153, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnGasto = new JButton("F3 - NEGOCIO");
		btnGasto.setForeground(new Color(255, 255, 255));
		btnGasto.setBackground(new Color(0, 153, 0));
		btnGasto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int input = JOptionPane.showConfirmDialog(null, 
		                "La cantidad ingresada se descontara del Stock", "Atención!", 
		                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(input == 0) {
					itemMenu = CONFIRM;
					login.setVisible(true);
				}
			}
		});
		btnGasto.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblNewLabel_2 = new JLabel("VENTA NUMERO:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		numero = new JLabel("New label");
		numero.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField)
								.addComponent(lblCodigoProducto, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblVentaDiaria))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(numero)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGasto, 0, 0, Short.MAX_VALUE)
						.addComponent(btn_nuevaVenta, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 207, Short.MAX_VALUE))
					.addGap(21))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCodigoProducto, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblVentaDiaria)))
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_nuevaVenta, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(17)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton)
								.addComponent(btnEliminar))
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(numero, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(btnGasto, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))
					.addGap(129))
		);
		setLayout(groupLayout);
				
		Runnable hilo = new Runnable() {
			
			@Override
			public void run() {
				try {
					InetAddress address = InetAddress.getLocalHost();
					NetworkInterface net = NetworkInterface.getByInetAddress(address);
					byte[] mac = net.getHardwareAddress();
				    StringBuilder sb = new StringBuilder(18);
				    for (byte b : mac) {
				        if (sb.length() > 0)
				            sb.append(':');
				        sb.append(String.format("%02x", b));
				    }
					data = HTTPRequest.ConsultasServidor(HTTPRequest.NUEVA_VENTA, sb.toString());
					
					lineaDeVentas =  gson.fromJson(data, LineaDeVenta[].class);
					for(LineaDeVenta ldv: lineaDeVentas) {
						
						numero.setText(Integer.toString(ldv.getULTIMO()));  				
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		/*
		Runnable hilo1 = new Runnable() {		
			@Override
			public void run() {
				while(true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!textField.getText().isEmpty()) {
					try {
						data = HTTPRequest.ConsultasServidor(HTTPRequest.NUEVO_ITEM, textField.getText());
						System.out.println(data);
						lineaDeVentas =  gson.fromJson(data, LineaDeVenta[].class);
						DefaultTableModel modal = new DefaultTableModel();
						modal = (DefaultTableModel) table.getModel();
						
						for(LineaDeVenta ldv: lineaDeVentas) {
							
							if(ldv.getCANT_DISPONIBLE() > 0) {
								
								Object[] row = new Object[5];
								row[0] = ldv.getCODIGO_BARRAS();
								row[1] = ldv.getNOMBRE();
								row[2] = ldv.getCANTIDAD();
								row[3] = ldv.getVALOR_VENTA();
								row[4] = ldv.getVALOR_VENTA() * ldv.getCANTIDAD();
								
								modal.addRow(row);
								
								obtenerTotal();

							}else {
								JOptionPane.showMessageDialog(null,"No hay stock disponible para el producto ingresado","Información",JOptionPane.ERROR_MESSAGE);
							}
							if(ldv.getIS_TEMPORAL() == 1 ) {
								
								table.getSelectionModel().setSelectionInterval(modal.getRowCount()-1,modal.getRowCount()-1);
								cambio_cantidad.getLblCodigo().setText(ldv.getCODIGO_BARRAS());
								cambio_cantidad.getLblNewLabel().setText(ldv.getNOMBRE());
								cambio_cantidad.getCantidad().setText("1");	
								cambio_cantidad.getPrecio().setText("0");
								cambio_cantidad.getPrecio().selectAll();
								cambio_cantidad.setVisible(true);
								cambio_cantidad.getPrecio().requestFocus();
								
							}															
						}
						textField.setText("");
						
					} catch (IOException e) {
						JOptionPane.showInternalMessageDialog(null,"Ingrese un Codigo Valido!");
					}
				}
				}
			}			
		};
		Thread thread =  new Thread(hilo1);
		thread.start();
		*/
		Thread thread1 =  new Thread(hilo);
		thread1.start();
		textField.requestFocus();
		textField.setFocusable(true);
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public JLabel getLbltotal() {
		return lbltotal;
	}
	public void setLbltotal(JLabel lbltotal) {
		this.lbltotal = lbltotal;
	}
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public JTextField getTextField() {
		return textField;
	}
	public JLabel getNumero() {
		return numero;
	}
	public void setNumero(JLabel numero) {
		this.numero = numero;
	}
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	
	public static void obtenerTotal() {
		
		DefaultTableModel model = new DefaultTableModel();
		
		model = (DefaultTableModel) table.getModel();
		total = 0;
		for (int i = table.getRowCount() -1; i >= 0; i--){
			total += Double.parseDouble(model.getValueAt(i, 4).toString());
		}
		System.out.println(total);
		lbltotal.setText(Double.toString(total));
	}
	
	public static void RefreshTable() {
		
		DefaultTableModel model = new DefaultTableModel();
		
		model = (DefaultTableModel) table.getModel();
		lbltotal.setText("0.0");
		total = 0;
		for (int i = table.getRowCount() -1; i >= 0; i--){
			model.removeRow(i);
		}
		try {
			InetAddress address = InetAddress.getLocalHost();
			NetworkInterface net = NetworkInterface.getByInetAddress(address);
			byte[] mac = net.getHardwareAddress();
			StringBuilder sb = new StringBuilder(18);
		    for (byte b : mac) {
		        if (sb.length() > 0)
		            sb.append(':');
		        sb.append(String.format("%02x", b));
		    }		
			String date = HTTPRequest.ConsultasServidor(HTTPRequest.NUEVA_VENTA, sb.toString());
			Gson gson = new Gson();
			
			LineaDeVenta[] lineaDeVentas =  gson.fromJson(date, LineaDeVenta[].class);
			for(LineaDeVenta ldv: lineaDeVentas) {
				
				numero.setText(Integer.toString(ldv.getULTIMO()));  				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void ingresarItem() {
		Runnable hilo = new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!textField.getText().isEmpty()) {
					try {
						data = HTTPRequest.ConsultasServidor(HTTPRequest.NUEVO_ITEM, textField.getText());
						System.out.println(data);
						lineaDeVentas =  gson.fromJson(data, LineaDeVenta[].class);
						DefaultTableModel modal = new DefaultTableModel();
						modal = (DefaultTableModel) table.getModel();
						
						for(LineaDeVenta ldv: lineaDeVentas) {
							
							if(ldv.getCANT_DISPONIBLE() > 0) {
								
								Object[] row = new Object[5];
								row[0] = ldv.getCODIGO_BARRAS();
								row[1] = ldv.getNOMBRE();
								row[2] = ldv.getCANTIDAD();
								row[3] = ldv.getVALOR_VENTA();
								row[4] = ldv.getVALOR_VENTA() * ldv.getCANTIDAD();
								
								modal.addRow(row);
								
								obtenerTotal();

							}else {
								JOptionPane.showMessageDialog(null,"No hay stock disponible para el producto ingresado","Información",JOptionPane.ERROR_MESSAGE);
							}
							if(ldv.getIS_TEMPORAL() == 1 ) {
								
								table.getSelectionModel().setSelectionInterval(modal.getRowCount()-1,modal.getRowCount()-1);
								cambio_cantidad.getLblCodigo().setText(ldv.getCODIGO_BARRAS());
								cambio_cantidad.getLblNewLabel().setText(ldv.getNOMBRE());
								cambio_cantidad.getCantidad().setText("1");	
								cambio_cantidad.getPrecio().setText("0");
								cambio_cantidad.getPrecio().selectAll();
								cambio_cantidad.setVisible(true);
								cambio_cantidad.getPrecio().requestFocus();
								
							}															
						}
						textField.setText("");
						
					} catch (IOException e) {
						JOptionPane.showInternalMessageDialog(null,"Ingrese un Codigo Valido!");
					}
				}
			}			
		};
		Thread thread =  new Thread(hilo);
		thread.start();
	}
	
	public void Cobrar() {
		Vuelto vuelto;
		ArrayList<LineaDeVenta> array = new ArrayList<>();
		DefaultTableModel model = new DefaultTableModel();
		model = (DefaultTableModel) table.getModel();
		for (int i = table.getRowCount() -1; i >= 0; i--){
			String codigo_producto = model.getValueAt(i, 0).toString();
			double subtotal = Double.parseDouble(model.getValueAt(i, 4).toString());
			int cantidad = Integer.parseInt(model.getValueAt(i, 2).toString());
			int facturable = 1;
			
			LineaDeVenta linea = new LineaDeVenta(Integer.parseInt(getNumero().getText()),codigo_producto,cantidad,subtotal,facturable);
			array.add(linea);
			
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(array);
		vuelto  = new Vuelto(json);
		vuelto.setVisible(true);
	}
	
	public void descuentoNegocio() {
		ArrayList<LineaDeGasto> array = new ArrayList<>();
		DefaultTableModel model = new DefaultTableModel();
		model = (DefaultTableModel) table.getModel();
		for (int i = table.getRowCount() -1; i >= 0; i--){
			String codigo_producto = model.getValueAt(i, 0).toString();
			double subtotal = Double.parseDouble(model.getValueAt(i, 4).toString());
			int cantidad = Integer.parseInt(model.getValueAt(i, 2).toString());
			int facturable = 1;			
			LineaDeGasto linea = new LineaDeGasto(codigo_producto,subtotal,cantidad);
			array.add(linea);			
		}		
		Gson gson = new Gson();
		String json = gson.toJson(array);	
		Runnable hilo = new Runnable() {			
			@Override
			public void run() {
				try {
					System.out.println(json);
					data = HTTPRequest.ConsultasServidor(HTTPRequest.CARGAR_GASTO, json);	
					System.out.println("mensaje: " + data);
					if(!data.isEmpty()) {
						if(data.contains("stock")) {
							JOptionPane.showMessageDialog(null,"Falta stock para el producto ingresado","Información",JOptionPane.ERROR_MESSAGE);
							
						}else {
							VentasDiarias.RefreshTable();
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
	public static String MontoTotal() {
		return lbltotal.getText();
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	@Override
	public void verificarLogin() {
		descuentoNegocio();
		RefreshTable();
		
	}
}
