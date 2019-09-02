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
import java.awt.GridLayout;
import javax.swing.SpringLayout;

public class ListadoProductos extends JPanel {
	static JTextField codProducto;
	static JTable table;
	static Gson gson;
	static String data = "";
	static Stock[] stock;
	private Rubro[] rubro_1;
	static MouseAdapter mouseAdapter;
	private JComboBox rubro;

	/**
	 * Create the panel.
	 */
	public ListadoProductos() {
		
		gson = new Gson();

		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"FECHA","HORA","CODIGO","DESCRIPCIÓN","RUBRO","CANTIDAD","UNITARIO","IMPORTE"
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
		scrollPane.setViewportView(table);
		
		this.ActualizarStock("","");
		
        
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
        SpringLayout springLayout = new SpringLayout();
        springLayout.putConstraint(SpringLayout.WEST, codProducto, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, codProducto, -6, SpringLayout.WEST, rubro);
        springLayout.putConstraint(SpringLayout.WEST, btnBuscar, 425, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, btnBuscar, -799, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.WEST, rubro, 237, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, btnBuscar, -6, SpringLayout.NORTH, scrollPane);
        springLayout.putConstraint(SpringLayout.SOUTH, codProducto, -6, SpringLayout.NORTH, scrollPane);
        springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 60, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.NORTH, rubro, 31, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.SOUTH, rubro, -9, SpringLayout.NORTH, scrollPane);
        springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -253, SpringLayout.SOUTH, this);
        setLayout(springLayout);
        JLabel lblCodigoDeProducto = new JLabel(" Descripción del producto");
        springLayout.putConstraint(SpringLayout.NORTH, btnBuscar, 0, SpringLayout.NORTH, lblCodigoDeProducto);
        springLayout.putConstraint(SpringLayout.NORTH, codProducto, 4, SpringLayout.SOUTH, lblCodigoDeProducto);
        springLayout.putConstraint(SpringLayout.WEST, lblCodigoDeProducto, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, lblCodigoDeProducto, 0, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.SOUTH, lblCodigoDeProducto, 25, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.EAST, lblCodigoDeProducto, 231, SpringLayout.WEST, this);
        lblCodigoDeProducto.setForeground(new Color(255, 255, 255));
        lblCodigoDeProducto.setBackground(new Color(0, 153, 0));
        lblCodigoDeProducto.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCodigoDeProducto.setOpaque(true);
        add(lblCodigoDeProducto);
        
        JLabel lblRubro = new JLabel("Rubro");
        springLayout.putConstraint(SpringLayout.NORTH, lblRubro, 0, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.SOUTH, lblRubro, -6, SpringLayout.NORTH, rubro);
        springLayout.putConstraint(SpringLayout.EAST, rubro, 0, SpringLayout.EAST, lblRubro);
        springLayout.putConstraint(SpringLayout.WEST, lblRubro, 6, SpringLayout.EAST, lblCodigoDeProducto);
        springLayout.putConstraint(SpringLayout.EAST, lblRubro, -6, SpringLayout.WEST, btnBuscar);
        lblRubro.setOpaque(true);
        lblRubro.setForeground(Color.WHITE);
        lblRubro.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblRubro.setBackground(new Color(0, 153, 0));
        add(lblRubro);
        add(btnBuscar);
        add(codProducto);
        add(rubro);
        add(scrollPane);
		int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        setSize(ancho,alto);
        this.CargarRubro();
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
					data = HTTPRequest.ConsultasServidor(HTTPRequest.LISTA_PRODUCTOS, json);
					if(!data.isEmpty()) {
						stock =  gson.fromJson(data, Stock[].class);
						for(Stock s: stock) {
							Object[] row = new Object[8];
							row[0] = s.getFecha();
							row[1] = s.getHORA_REPO();
							row[2] = s.getProducto_id();
							row[3] = s.getNombre();
							row[4] = s.getRUBRO();
							row[5] = s.getStock_actual();
							row[6] = s.getPrecio_lista();
							row[7] = s.getPrecio_venta();
							
							modal = (DefaultTableModel) table.getModel();
							modal.addRow(row);
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
}
