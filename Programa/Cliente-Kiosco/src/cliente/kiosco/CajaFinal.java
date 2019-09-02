package cliente.kiosco;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.google.gson.Gson;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class CajaFinal extends JPanel {
	private JTextField textField;
	SimpleDateFormat formato;
	private Workbook excel;
	Gson gson;
	private JButton descargar;
	JButton btnCerrar;
	private JButton btnAbrir;
	String data;
	/**
	 * Create the panel.
	 */
	public CajaFinal() {
		
		int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        setSize(ancho,alto);
		setLayout(null);
		JLabel lbl1 = new JLabel("CAJA ABIERTA EL DIA: ");
		lbl1.setBounds(10, 30, 182, 20);
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lbl1);
		
		textField = new JTextField();
		textField.setBounds(198, 32, 197, 20);
		textField.setEditable(false);
		add(textField);
		textField.setColumns(10);
		
		btnCerrar = new JButton("CERRAR CAJA");
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCerrar.setForeground(new Color(255, 255, 255));
		btnCerrar.setBackground(new Color(0, 153, 0));
		btnCerrar.setBounds(10, 77, 182, 36);
		add(btnCerrar);
		
		descargar = new JButton("DESCARGAR");
		descargar.setFont(new Font("Tahoma", Font.BOLD, 16));
		descargar.setForeground(new Color(255, 255, 255));
		descargar.setBackground(new Color(0, 153, 0));
		descargar.setBounds(10, 175, 385, 36);
		add(descargar);
		
		btnAbrir = new JButton("ABRIR CAJA");
		btnAbrir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAbrir.setForeground(new Color(255, 255, 255));
		btnAbrir.setBackground(new Color(0, 153, 0));
		btnAbrir.setBounds(198, 77, 197, 36);
		add(btnAbrir);
		
		montoinicial = new JTextField();
		montoinicial.setFont(new Font("Tahoma", Font.PLAIN, 16));
		montoinicial.setBounds(198, 124, 197, 36);
		add(montoinicial);
		montoinicial.setColumns(10);
		
		JLabel lblMontoInicial = new JLabel("MONTO INICIAL");
		lblMontoInicial.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMontoInicial.setBounds(10, 124, 182, 36);
		add(lblMontoInicial);
		
		JLabel lblventa = new JLabel(" TOTAL VENTAS");
		lblventa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblventa.setBounds(10, 273, 151, 25);
		add(lblventa);
		
		JLabel lblTotalGastos = new JLabel(" TOTAL GASTOS");
		lblTotalGastos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotalGastos.setBounds(198, 273, 151, 25);
		add(lblTotalGastos);
		
		venta = new JTextField();
		venta.setEditable(false);
		venta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		venta.setBounds(10, 309, 151, 25);
		add(venta);
		venta.setColumns(10);
		
		gasto = new JTextField();
		gasto.setEditable(false);
		gasto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gasto.setColumns(10);
		gasto.setBounds(198, 309, 151, 25);
		add(gasto);
		
		JLabel lblGananciaDelDia = new JLabel("GANANCIA DEL DIA");
		lblGananciaDelDia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGananciaDelDia.setBounds(398, 273, 151, 25);
		add(lblGananciaDelDia);
		
		ganancia = new JTextField();
		ganancia.setEditable(false);
		ganancia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ganancia.setColumns(10);
		ganancia.setBounds(398, 309, 151, 25);
		add(ganancia);
		descargar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DescargarExcel();
				
			}
		});
		
		btnAbrir.addActionListener(abrirCaja);
		btnCerrar.addActionListener(cerrarCaja);
		try {
			Thread.sleep(1000);
			consultaCaja();
			resumenCaja();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JButton getBtnIniciar() {
		return btnCerrar;
	}

	public void setBtnIniciar(JButton btn) {
		this.btnCerrar = btn;
	}

	public void consultaCaja() {
		Runnable hilo = new Runnable() {
			
			@Override
			public void run() {
				try {
					gson = new Gson();
					data = HTTPRequest.ConsultasServidor(HTTPRequest.CONSULTA_CAJA, null);
					System.out.println(data);
					if(!data.isEmpty()) {
						Fecha[] fecha =  gson.fromJson(data, Fecha[].class);
						if(fecha[0].getFECHA_INICIO().contains("CERRADO")) {
							textField.setText("");
							btnCerrar.setEnabled(false);
							btnAbrir.setEnabled(true);
							descargar.setEnabled(true);
							montoinicial.setEnabled(true);
						}else {
							textField.setText(fecha[0].getFECHA_INICIO());
							btnCerrar.setEnabled(true);
							btnAbrir.setEnabled(false);
							montoinicial.setEnabled(false);
							descargar.setEnabled(false);
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
	
	public void abrirCaja() {
		Runnable hilo = new Runnable() {
			
			@Override
			public void run() {
				try {
					gson = new Gson();
					String data = HTTPRequest.ConsultasServidor(HTTPRequest.INICIO_CAJA_FINAL, null);
					System.out.println(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		
		Thread thread =  new Thread(hilo);
		thread.start();					
	}
	public void cerrarCaja() {
		Runnable hilo = new Runnable() {
			
			@Override
			public void run() {
				try {
					String data = HTTPRequest.ConsultasServidor(HTTPRequest.CIERRE_CAJA_FINAL, null);
					System.out.println(data);
					resumenCaja();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		
		Thread thread =  new Thread(hilo);
		thread.start();					
	}
	
	ActionListener cerrarCaja = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("CAJA CERRADA");
			cerrarCaja();
			textField.setText("");
			btnCerrar.setEnabled(false);
			btnAbrir.setEnabled(true);
			descargar.setEnabled(true);
			montoinicial.setEnabled(true);
		}
	};
	
	ActionListener abrirCaja = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("CAJA ABIERTA");	
			abrirCaja();
			btnCerrar.setEnabled(true);
			btnAbrir.setEnabled(false);
			descargar.setEnabled(false);
			montoinicial.setEnabled(false);
			cajaInicial();
			resumenCaja();
		}
	};
	private JTextField montoinicial;
	private JTextField venta;
	private JTextField gasto;
	private JTextField ganancia;

	
	public void DescargarExcel() {

        /**Capturo los mensajes que el ThreadCliente deja en el buffer **/
        Runnable hilo = new Runnable() {
			
			@Override
			public void run() {
				try {
					excel = new HSSFWorkbook();
					Sheet hoja = excel.createSheet("Stock");
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date fechaf = new java.util.Date();
					String fechaactual = formato.format(fechaf);
					String file = "C:\\Descargas\\CIERRE_CAJA_FINAL_" + fechaactual + ".xls";
					int i = 0;
					//"FECHA VENTA","HORA VENTA", "CODIGO PRODUCTO", "NOMBRE PRODUCTO", "CANTIDAD DISPONIBLE", "CANTIDAD VENDIDA", "VENTA PARCIAL"
					Row fila = hoja.createRow(i);
					Cell celda = fila.createCell(0);
					celda.setCellValue("FECHA VENTA");
					celda = fila.createCell(1);
					celda.setCellValue("HORA VENTA");
					celda = fila.createCell(2);
					celda.setCellValue("CODIGO PRODUCTO");
					celda = fila.createCell(3);
					celda.setCellValue("NOMBRE PRODUCTO");
					celda = fila.createCell(4);
					celda.setCellValue("CANTIDAD DISPONIBLE");
					celda = fila.createCell(5);
					celda.setCellValue("CANTIDAD VENDIDA");
					celda = fila.createCell(6);
					celda.setCellValue("VENTA PARCIAL");
					celda = fila.createCell(7);
					celda.setCellValue("TIPO GASTO");
					celda = fila.createCell(8);
					celda.setCellValue("PROVEEDOR");
					celda = fila.createCell(9);
					celda.setCellValue("FECHA");
					i++;

					String data = HTTPRequest.ConsultasServidor(HTTPRequest.DESCARGAR_CAJA_DIA, null);
					if(!data.isEmpty()) {
						Gson gson = new Gson();
						Caja [] caja =  gson.fromJson(data, Caja[].class);
						
						for(Caja s: caja) {
							fila = hoja.createRow(i);
							celda = fila.createCell(0);
							celda.setCellValue(s.getFECHA_LINEA_VENTA());
							celda = fila.createCell(1);
							celda.setCellValue(s.getHORA());
							celda = fila.createCell(2);
							celda.setCellValue(s.getPRODUCT_ID());
							celda = fila.createCell(3);
							celda.setCellValue(s.getNOMBRE());
							celda = fila.createCell(4);
							celda.setCellValue(s.getCANTIDAD_DISPONIBLE());
							celda = fila.createCell(5);
							celda.setCellValue(s.getCANTIDAD_VENDIDA());
							celda = fila.createCell(6);
							celda.setCellValue(s.getGANANCIA());
							celda = fila.createCell(7);
							celda.setCellValue(s.getTIPO_GASTO());
							celda = fila.createCell(8);
							celda.setCellValue(s.getPROVEEDOR());
							celda = fila.createCell(9);
							celda.setCellValue(s.getFecha());
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
	
	public void cajaInicial() {
		ArrayList<LineaDeGasto> array = new ArrayList<>();

		String codigo_producto = "CAJA INICIAL";
		double subtotal = Double.parseDouble(montoinicial.getText());
		int cantidad = 0;
		int facturable = 1;			
		LineaDeGasto linea = new LineaDeGasto(codigo_producto,subtotal,cantidad);
		array.add(linea);			
				
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
							JOptionPane.showMessageDialog(null,"Error de Servidor","Información",JOptionPane.ERROR_MESSAGE);						
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
	
	public void resumenCaja() {
		Runnable hilo = new Runnable() {			
			@Override
			public void run() {
				try {
					gson = new Gson();
					data = HTTPRequest.ConsultasServidor(HTTPRequest.RESUMEN_CAJA_FINAL, "");	
					System.out.println("mensaje: " + data);
					if(!data.isEmpty()) {
						Resumen[] total = gson.fromJson(data, Resumen[].class);
						venta.setText(Double.toString(total[0].getTOTAL()));
						gasto.setText(Double.toString(total[1].getTOTAL()));
						ganancia.setText(Double.toString(total[0].getTOTAL() - total[1].getTOTAL()));
						
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
	
	class Resumen{
		double TOTAL;

		public double getTOTAL() {
			return TOTAL;
		}

		public void setTOTAL(double tOTAL) {
			TOTAL = tOTAL;
		}
				
	}
}
