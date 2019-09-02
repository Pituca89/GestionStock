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
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.logging.Handler;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.google.gson.Gson;

import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class StockActual extends JPanel {
	static JTextField codProducto;
	static JTable table;
	static Gson gson;
	static String data = "";
	static Stock[] stock;
	static Actualizar actualizar;// = new Actualizar(2);
	static MouseAdapter mouseAdapter;
	private Workbook excel;
	/**
	 * Create the panel.
	 */
	public StockActual() {
		
		actualizar = new Actualizar(2);
		gson = new Gson();
		int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        setSize(ancho,alto);
        
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
        			MostrarStockCompleto();
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
        		botonEnviarActionPerformed(e);
        		
        	}
        });
        JScrollPane scrollPane = new JScrollPane();
        
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Codigo","Descripcion", "Stock Minimo", "Stock Actual", "Stock Faltante" ,"Precio de Lista", "Precio de Venta", "Fecha Actualizaci\u00F3n", "Usuario"
        	}
        ) {
        	boolean[] columnEditables = new boolean[] {
        		false, false, false, false, false, false, false, false
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        });
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(550);
		table.getColumnModel().getColumn(2).setPreferredWidth(10);
		table.getColumnModel().getColumn(3).setPreferredWidth(10);
		table.getColumnModel().getColumn(4).setPreferredWidth(10);
		table.getColumnModel().getColumn(5).setPreferredWidth(10);
		table.getColumnModel().getColumn(6).setPreferredWidth(10);
		table.getColumnModel().getColumn(7).setPreferredWidth(10);
		table.getColumnModel().getColumn(8).setPreferredWidth(10);
        table.setRowHeight(25);
        table.getTableHeader().setBackground(new Color(0, 153, 0));
        table.getTableHeader().setForeground(new Color(255, 255, 255));
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
        scrollPane.setViewportView(table);
        
        JButton btnDescargar = new JButton("DESCARGAR");
        btnDescargar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		DescargarExcel();
        	}
        });
        btnDescargar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnDescargar.setForeground(new Color(255, 255, 255));
        btnDescargar.setBackground(new Color(0, 153, 0));
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1342, Short.MAX_VALUE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(codProducto, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblCodigoDeProducto, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
        					.addGap(93)
        					.addComponent(btnDescargar)))
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(lblCodigoDeProducto, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(codProducto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
        					.addComponent(btnDescargar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(btnBuscar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
        			.addGap(157))
        );
        setLayout(groupLayout);
		mouseAdapter = new MouseAdapter() {
			 public void mousePressed(MouseEvent Mouse_evt) {
				 JTable tbl =(JTable) Mouse_evt.getSource();
				 Point point = Mouse_evt.getPoint();
				 int row = tbl.rowAtPoint(point);
				 if (Mouse_evt.getClickCount() == 2) {
					 
					 actualizar.getActualizarStock().getCodProducto().setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					 actualizar.getActualizarStock().getLblCodigo().setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					 actualizar.getActualizarStock().getLblDesc().setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					 actualizar.getActualizarStock().getStockMin().setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					 actualizar.getActualizarStock().getStockActual().setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					 actualizar.setVisible(true);
					 
				 }
			 }
		};
		
		this.ActualizarStock();

	}

	
	private void botonEnviarActionPerformed(ActionEvent evt) {
		MostrarStockCompleto();
    }

	public void MostrarStockCompleto() {
		Runnable hilo1 = new Runnable() {
			
			@Override
			public void run() {
				try {
					
					data = HTTPRequest.ConsultasServidor(HTTPRequest.UN_STOCK, codProducto.getText());
					if(!data.isEmpty()) {
						DefaultTableModel modal = new DefaultTableModel();
						modal = (DefaultTableModel) table.getModel();
						for (int i = table.getRowCount() -1; i >= 0; i--){
							modal.removeRow(i);
						}
						stock =  gson.fromJson(data, Stock[].class);
						
						for(Stock s: stock) {
							
							Object[] row = new Object[9];
							row[0] = s.getProducto_id();
							row[1] = s.getNombre();
							row[2] = s.getStock_minimo();
							row[3] = s.getStock_actual();
							if(s.getSTOCK_FALTANTE() > 0) {
								row[4] = "<html><div style=\"background:green;padding-right:150;padding-top:150;padding-bottom:150\"><font color=\"white\">"+s.getSTOCK_FALTANTE()+"</font></div></html>";
							}else if(s.getSTOCK_FALTANTE() <= 0){
								row[4] = "<html><div style=\"background:red;padding-right:150;padding-top:150;padding-bottom:150\"><font color=\"white\">"+s.getSTOCK_FALTANTE()+"</font></div></html>";
							}else {
								row[4] = s.getSTOCK_FALTANTE();
							}
							row[5] = s.getPrecio_lista();
							row[6] = s.getPrecio_venta();
							row[7] = s.getFecha();
							row[8] = s.getUsuario();
							
							modal.addRow(row);
							
						}
						table.addMouseListener(mouseAdapter);
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
	public void ActualizarComponentes() {
		String linea = "";
		String[] obj= null;
		DefaultTableModel model = new DefaultTableModel();
		Canal canal = Canal.getInstance();
		
		if((linea = canal.Pop()) != null) {
			obj = linea.split("|");
			System.out.println(obj[0] + " - " + obj[1] + "\n");
			//this.codProducto.setText(linea);
		}else {
			System.out.println("es null");
		}		
	}

	public static void ActualizarStock() {

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
					data = HTTPRequest.ConsultasServidor(HTTPRequest.ALL_STOCK, null);
					if(!data.isEmpty()) {
						stock =  gson.fromJson(data, Stock[].class);
						
						for(Stock s: stock) {
							Object[] row = new Object[9];
							row[0] = s.getProducto_id();
							row[1] = s.getNombre();
							row[2] = s.getStock_minimo();
							row[3] = s.getStock_actual();
							if(s.getSTOCK_FALTANTE() > 0) {
								row[4] = "<html><div style=\"background:green;padding-right:150;padding-top:150;padding-bottom:150\"><font color=\"white\">"+s.getSTOCK_FALTANTE()+"</font></div></html>";
							}else if(s.getSTOCK_FALTANTE() <= 0){
								row[4] = "<html><div style=\"background:red;padding-right:150;padding-top:150;padding-bottom:150\"><font color=\"white\">"+s.getSTOCK_FALTANTE()+"</font></div></html>";
							}else {
								row[4] = s.getSTOCK_FALTANTE();
							}
							
							row[5] = s.getPrecio_lista();
							row[6] = s.getPrecio_venta();
							row[7] = s.getFecha();
							row[8] = s.getUsuario();
							
							modal = (DefaultTableModel) table.getModel();
							modal.addRow(row);
							//modal.addRow(new Object()[s.getProducto_id(), s.getNombre(),s.getStock_minimo(),s.getStock_actual(),s.getPrecio_lista(),s.getPrecio_venta(),s.getFecha(),s.getFecha()]);
						}
						table.addMouseListener(mouseAdapter);
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
	
	public void DescargarExcel() {

        /**Capturo los mensajes que el ThreadCliente deja en el buffer **/
        Runnable hilo = new Runnable() {
			
			@Override
			public void run() {
				try {
					excel = new HSSFWorkbook();
					Sheet hoja = excel.createSheet("Stock");
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date fecha = new java.util.Date();
					String fechaactual = formato.format(fecha);
					String file = "C:\\Descargas\\STOCK_ACTUAL_" + fechaactual + ".xls";
					int i = 0;
					//"Codigo","Descripcion", "Stock Minimo", "Stock Actual", "Stock Faltante" ,"Precio de Lista", "Precio de Venta", "Fecha Actualizaci\u00F3n", "Usuario"
					Row fila = hoja.createRow(i);
					Cell celda = fila.createCell(0);
					celda.setCellValue("CODIGO");
					celda = fila.createCell(1);
					celda.setCellValue("DESCRIPCION");
					celda = fila.createCell(2);
					celda.setCellValue("STOCK MINIMO");
					celda = fila.createCell(3);
					celda.setCellValue("STOCK ACTUAL");
					celda = fila.createCell(4);
					celda.setCellValue("STOCK FALTANTE");
					celda = fila.createCell(5);
					celda.setCellValue("PRECIO LISTA");
					celda = fila.createCell(6);
					celda.setCellValue("PRECIO VENTA");
					celda = fila.createCell(7);
					celda.setCellValue("FECHA ACTUALIZACION");
					celda = fila.createCell(8);
					celda.setCellValue("USUARIO");
					i++;
					data = HTTPRequest.ConsultasServidor(HTTPRequest.ALL_STOCK, null);
					if(!data.isEmpty()) {
						stock =  gson.fromJson(data, Stock[].class);
						
						for(Stock s: stock) {
							fila = hoja.createRow(i);
							celda = fila.createCell(0);
							celda.setCellValue(s.getProducto_id());
							celda = fila.createCell(1);
							celda.setCellValue(s.getNombre());
							celda = fila.createCell(2);
							celda.setCellValue(s.getStock_minimo());
							celda = fila.createCell(3);
							celda.setCellValue(s.getStock_actual());
							celda = fila.createCell(4);
							celda.setCellValue(s.getSTOCK_FALTANTE());
							celda = fila.createCell(5);
							celda.setCellValue(s.getPrecio_lista());
							celda = fila.createCell(6);
							celda.setCellValue(s.getPrecio_venta());
							celda = fila.createCell(7);
							celda.setCellValue(s.getFecha());
							celda = fila.createCell(8);
							celda.setCellValue(s.getUsuario());
							i++;
						}
						FileOutputStream salida = new FileOutputStream(file);
						excel.write(salida);
						salida.close();
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
