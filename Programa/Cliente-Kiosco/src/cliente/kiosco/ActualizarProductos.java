package cliente.kiosco;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ActualizarProductos extends JFrame {

	private JPanel contentPane;
	private static JTextField textField;
	private static JTable table;
	private static Thread thread;
	static MouseAdapter mouseAdapter;
	private static boolean type;
	NuevoProducto nuevoProducto;// =  new NuevoProducto();
	
	public ActualizarProductos() {
		nuevoProducto =  new NuevoProducto();
		setIconImage(Toolkit.getDefaultToolkit().getImage(ActualizarProductos.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnAgregarProducto = new JButton("AGREGAR");
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				nuevoProducto.setVisible(true);
			}
		});
		btnAgregarProducto.setBackground(new Color(255, 255, 255));
		btnAgregarProducto.setIcon(null);
		btnAgregarProducto.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEliminar.setBackground(new Color(255, 255, 255));
		btnEliminar.setIcon(null);
		btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String data;
				try {
					int input = JOptionPane.showConfirmDialog(null, 
			                "Desea eliminar los datos de forma permanente?", "Atención!", 
			                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(input == 0) {
						String id = table.getValueAt(table.getSelectedRow(), 10).toString();
						data = HTTPRequest.ConsultasServidor(HTTPRequest.ELIMINAR_PRODUCTO, id);
						System.out.println(data);
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.removeRow(table.getSelectedRow());
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnActualizar.setBackground(new Color(255, 255, 255));
		btnActualizar.setIcon(null);
		btnActualizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				 nuevoProducto.getCodBar().setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				 nuevoProducto.getDesc().setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				 nuevoProducto.getComboBox().getModel().setSelectedItem(table.getValueAt(table.getSelectedRow(), 2).toString());
				 nuevoProducto.getTxtlista().setText(table.getValueAt(table.getSelectedRow(), 4).toString());
				 nuevoProducto.getTxtventa().setText(table.getValueAt(table.getSelectedRow(), 5).toString());
				 nuevoProducto.getStockmin().setText(table.getValueAt(table.getSelectedRow(), 6).toString());
				 nuevoProducto.getStockact().setText(table.getValueAt(table.getSelectedRow(), 7).toString());
				 if(table.getValueAt(table.getSelectedRow(), 9).toString().equals("1")) {
				 System.out.println("entre");
					 nuevoProducto.getChckbxTemporal().setSelected(true);
				 }
				 nuevoProducto.setId(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 10).toString()));
				 nuevoProducto.setVisible(true);
				
			}
		});
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
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
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == e.VK_ENTER)
					buscarProducto();
				
			}
		});
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
		JButton btnbuscar = new JButton("BUSCAR");
		btnbuscar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnbuscar.setBackground(new Color(255, 255, 255));
		btnbuscar.setIcon(new ImageIcon("./imagen/binoculares.png"));
		
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo Barras", "Descripci\u00F3n", "Rubro", "Fecha Alta", "Precio Lista", "Precio Venta", "Stock Minimo", "Stock Actual", "Ultima Actualizacion","Temporal","ID"
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
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(350);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		table.getColumnModel().getColumn(5).setPreferredWidth(20);
		table.getColumnModel().getColumn(6).setPreferredWidth(20);
		table.getColumnModel().getColumn(7).setPreferredWidth(20);
		
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(3).setMaxWidth(0);
		table.getColumnModel().getColumn(3).setMinWidth(0);
		table.getColumnModel().getColumn(3).setPreferredWidth(0);
		table.getColumnModel().getColumn(10).setMaxWidth(0);
		table.getColumnModel().getColumn(10).setMinWidth(0);
		table.getColumnModel().getColumn(10).setPreferredWidth(0);
		scrollPane.setViewportView(table);
		setExtendedState(MAXIMIZED_BOTH);
		
		mouseAdapter = new MouseAdapter() {
			 public void mousePressed(MouseEvent Mouse_evt) {
				 JTable tbl =(JTable) Mouse_evt.getSource();
				 Point point = Mouse_evt.getPoint();
				 int row = tbl.rowAtPoint(point);
				 if (Mouse_evt.getClickCount() == 2) {
					 nuevoProducto.getCodBar().setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					 nuevoProducto.getDesc().setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					 nuevoProducto.getComboBox().getModel().setSelectedItem(table.getValueAt(table.getSelectedRow(), 2).toString());
					 nuevoProducto.getTxtlista().setText(table.getValueAt(table.getSelectedRow(), 4).toString());
					 nuevoProducto.getTxtventa().setText(table.getValueAt(table.getSelectedRow(), 5).toString());
					 nuevoProducto.getStockmin().setText(table.getValueAt(table.getSelectedRow(), 6).toString());
					 nuevoProducto.getStockact().setText(table.getValueAt(table.getSelectedRow(), 7).toString());
					 if(table.getValueAt(table.getSelectedRow(), 9).toString().equals("1")) {
						 System.out.println("entre");
						 nuevoProducto.getChckbxTemporal().setSelected(true);
					 }
					 nuevoProducto.setId(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 10).toString()));
					 nuevoProducto.setVisible(true);
					 
				 }
			 }
		};
		
		
		
		btnActualizar.setFocusable(false);
		btnAgregarProducto.setFocusable(false);
		btnEliminar.setFocusable(false);
		btnbuscar.setFocusable(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1337, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAgregarProducto, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnbuscar, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAgregarProducto, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnbuscar, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
					.addGap(296))
		);
		contentPane.setLayout(gl_contentPane);
		textField.requestFocus();
		int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        setSize(ancho,alto);
        //this.setBounds((ancho / 2) - (this.getWidth() / 2), (alto / 2) - (this.getHeight() / 2), alto, ancho);
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		//setExtendedState(Frame.MAXIMIZED_BOTH);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
            	if(thread != null)
            		thread.stop();
            	dispose();
            }
        });
        buscarProducto();
	}
	
	public static void buscarProducto() {
		Runnable hilo1 = new Runnable() {
			
			@Override
			public void run() {	
				DefaultTableModel model = new DefaultTableModel();
				
				model = (DefaultTableModel) table.getModel();
				for (int i = table.getRowCount() -1; i >= 0; i--){
					model.removeRow(i);
				}
				if(!textField.getText().isEmpty()) {
					type = false;
					try {
						String data = HTTPRequest.ConsultasServidor(HTTPRequest.BUSCAR_PRODUCTO, textField.getText());
						System.out.println(data);
						Gson gson = new Gson();
						Producto[] producto =  gson.fromJson(data, Producto[].class);
						DefaultTableModel modal = new DefaultTableModel();
						modal = (DefaultTableModel) table.getModel();
						
						for(Producto prod: producto) {
								
								Object[] row = new Object[11];
								row[0] = prod.getCODIGO_BARRAS();
								row[1] = prod.getNOMBRE();
								row[2] = prod.getCATEGORY_NAME();
								row[3] = prod.getFECHA_ACTUALIZACION();
								row[4] = prod.getPRECIO_COSTO();
								row[5] = prod.getPRECIO_VENTA();
								row[6] = prod.getSTOCK_MINIMO();
								row[7] = prod.getSTOCK_ACTUAL();
								row[8] = prod.getFECHA_REPO();
								row[9] = prod.getIS_TEMPORAL();
								row[10] = prod.getPRODUCT_ID();
								
								modal.addRow(row);
								
						}
						textField.selectAll();
						table.addMouseListener(mouseAdapter);
					} catch (IOException e) {
						JOptionPane.showInternalMessageDialog(null,"Ingrese un Codigo Valido!");
					}
				}				
			}
		};
		thread =  new Thread(hilo1);
		thread.start();
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
}
