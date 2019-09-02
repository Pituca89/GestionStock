package cliente.kiosco;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.sql.RowSetMetaData;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Handler;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;

import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ListaDeReposicion extends JPanel {
	static JTextField codProducto;
	static JTable table;
	static Gson gson;
	static String data = "";
	static Stock[] stock;
	private Rubro[] rubro_1;
	static MouseAdapter mouseAdapter;
	private JComboBox rubro;
	private GastoProveedor gastoProveedor = new GastoProveedor();
	static int total;
	/**
	 * Create the panel.
	 */
	public ListaDeReposicion() {
		
		gson = new Gson();
			
		JLabel lblCodigoDeProducto = new JLabel(" Descripción del producto");
		lblCodigoDeProducto.setForeground(new Color(255, 255, 255));
		lblCodigoDeProducto.setBackground(new Color(0, 153, 0));
		lblCodigoDeProducto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodigoDeProducto.setOpaque(true);
		codProducto = new JTextField();
		codProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codProducto.setColumns(10);
		codProducto.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == e.VK_ENTER) {
					ActualizarStock(codProducto.getText(),rubro.getSelectedItem().toString());
				}
				
			}
		});
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.setIcon(new ImageIcon("./imagen/binoculares.png"));
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(0, 153, 0));
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ActualizarStock(codProducto.getText(),rubro.getSelectedItem().toString());
				
			}
		});
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo de Producto", "Descripcion", "Rubro","ID RUBRO", "Stock Minimo", "Stock Actual" ,"Costo","Cantidad a Reponer"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setRowHeight(25);
		table.getTableHeader().setBackground(new Color(0, 153, 0));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		/*
		mouseAdapter = new MouseAdapter() {
			 public void mousePressed(MouseEvent Mouse_evt) {
				 JTable tbl =(JTable) Mouse_evt.getSource();
				 Point point = Mouse_evt.getPoint();
				 int row = tbl.rowAtPoint(point);
				 if (Mouse_evt.getClickCount() == 2) {
					 
					 gastoProveedor.getCodBar().setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					 gastoProveedor.getDesc().setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					 gastoProveedor.getRubro().setText(table.getValueAt(table.getSelectedRow(), 2).toString());	
					 gastoProveedor.setIdRubro(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 3).toString()));
					 gastoProveedor.getRubro().setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					 gastoProveedor.getCosto().setText(table.getValueAt(table.getSelectedRow(), 6).toString());
					 gastoProveedor.setVisible(true);					 
				 }
			 }
		};
		*/
		//table.addMouseListener(mouseAdapter);
		scrollPane.setViewportView(table);
		
		JLabel lblRubro = new JLabel("     Rubro");
		lblRubro.setOpaque(true);
		lblRubro.setForeground(Color.WHITE);
		lblRubro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRubro.setBackground(new Color(0, 153, 0));
		
		rubro = new JComboBox();
		rubro.setModel(new DefaultComboBoxModel(new String[] {"TODOS"}));
		rubro.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == e.VK_ENTER) {
					ActualizarStock(codProducto.getText(),rubro.getSelectedItem().toString());
				}
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1334, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCodigoDeProducto, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblRubro, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(codProducto, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rubro, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRubro, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCodigoDeProducto, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(rubro, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(codProducto, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
					.addGap(177))
		);
		setLayout(groupLayout);
		
		this.ActualizarStock("","");
		this.CargarRubro();

		table.getColumnModel().getColumn(3).setMaxWidth(0);
		table.getColumnModel().getColumn(3).setMinWidth(0);
		table.getColumnModel().getColumn(3).setPreferredWidth(0);

		int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        setSize(ancho,alto);
	}


	public void CargarRubro() {
        /**Capturo los mensajes que el ThreadCliente deja en el buffer **/
        Runnable hilo = new Runnable() {
			
			@Override
			public void run() {
				try {

					DefaultComboBoxModel modal = new DefaultComboBoxModel();
					modal =  (DefaultComboBoxModel) rubro.getModel();

					data = HTTPRequest.ConsultasServidor(HTTPRequest.RUBRO, null);
					System.out.println(data);
					if(!data.isEmpty()) {
						rubro_1 =  gson.fromJson(data, Rubro[].class);
						
						for(Rubro s: rubro_1) {
							Object row = new Object();
							row = s.getRUBRO();
							modal.addElement(row);
							
							//modal.addRow(new Object()[s.getProducto_id(), s.getNombre(),s.getStock_minimo(),s.getStock_actual(),s.getPrecio_lista(),s.getPrecio_venta(),s.getFecha(),s.getFecha()]);
						}
						codProducto.requestFocus();
						codProducto.selectAll();
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

	public static void ActualizarStock(String nombre,String rubro) {
        /**Capturo los mensajes que el ThreadCliente deja en el buffer **/
        Runnable hilo = new Runnable() {
			
			@Override
			public void run() {
				try {

					DefaultTableModel modal = new DefaultTableModel();
					modal = (DefaultTableModel) table.getModel();
					for (int i = table.getRowCount() -1; i >= 0; i--){
						modal.removeRow(i);
					}
					ArrayList<Stock> arrayList = new ArrayList<>();
					Stock sk =  new Stock(nombre,rubro);
					arrayList.add(sk);
					String json = gson.toJson(arrayList);
					data = HTTPRequest.ConsultasServidor(HTTPRequest.ALL_PRODUCTOS, json);
					System.out.println("respuesta: " + data);
					if(!data.isEmpty()) {
						stock =  gson.fromJson(data, Stock[].class);
						total = 0;
						for(Stock s: stock) {
							Object[] row = new Object[8];
							row[0] = s.getProducto_id();
							row[1] = s.getNombre();
							row[2] = s.getRUBRO();
							row[3] = s.getIDRUBRO();
							row[4] = s.getStock_minimo();
							row[5] = s.getStock_actual();
							row[6] = s.getPrecio_lista();
							if(s.getSTOCK_FALTANTE() > 0) {
								row[7] = "<html><div style=\"background:green;padding-right:200;padding-top:150;padding-bottom:150\"><font color=\"white\">"+s.getSTOCK_FALTANTE()+"</font></div></html>";
							}else if(s.getSTOCK_FALTANTE() <= 0){
								row[7] = "<html><div style=\"background:red;padding-right:200;padding-top:150;padding-bottom:150\"><font color=\"white\">"+s.getSTOCK_FALTANTE()+"</font></div></html>";
								total += s.getSTOCK_FALTANTE();
							}else {
								row[7] = s.getSTOCK_FALTANTE();
							}							
							modal = (DefaultTableModel) table.getModel();
							modal.addRow(row);
							//modal.addRow(new Object()[s.getProducto_id(), s.getNombre(),s.getStock_minimo(),s.getStock_actual(),s.getPrecio_lista(),s.getPrecio_venta(),s.getFecha(),s.getFecha()]);
						}
						//lblCantidad.setText(Integer.toString(Math.abs(total)));
						codProducto.requestFocus();
						codProducto.selectAll();
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
}
