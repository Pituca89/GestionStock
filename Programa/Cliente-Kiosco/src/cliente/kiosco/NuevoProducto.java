package cliente.kiosco;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class NuevoProducto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField codBar;
	private JTextField desc;
	private JTextField txtlista;
	private JTextField txtventa;
	private JTextField stockmin;
	private JTextField stockact;
	private JLabel lblCodbar;
	private JLabel lblDesc;
	private JComboBox comboBox;
	private JLabel lblRubro;
	private JPanel precio;
	private JLabel lblPrecioLista;
	private JLabel lblPrecioVenta;
	private JLabel lblStockMinimo;
	private JLabel lblStockActual;
	private JCheckBox chckbxTemporal;
	private int id;
	private Rubro[] rubro_1;
	static String data = "";
	static Gson gson;
	
	public NuevoProducto() {
		gson = new Gson();
		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevoProducto.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		setBounds(100, 100, 595, 355);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 559, 261);
		contentPanel.add(tabbedPane);
		
		JPanel general = new JPanel();
		tabbedPane.addTab("General", null, general, null);
		general.setLayout(null);
		
		codBar = new JTextField();
		codBar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codBar.setBounds(10, 46, 173, 20);
		general.add(codBar);
		codBar.setColumns(10);
		
		lblCodbar = new JLabel(" Codigo Barras");
		lblCodbar.setForeground(new Color(255, 255, 255));
		lblCodbar.setBackground(new Color(0, 153, 0));
		lblCodbar.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodbar.setBounds(10, 24, 173, 20);
		lblCodbar.setOpaque(true);
		general.add(lblCodbar);
		
		lblDesc = new JLabel(" Descripci\u00F3n");
		lblDesc.setOpaque(true);
		lblDesc.setForeground(Color.WHITE);
		lblDesc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDesc.setBackground(new Color(0, 153, 0));
		lblDesc.setBounds(232, 24, 312, 20);
		general.add(lblDesc);
		
		desc = new JTextField();
		desc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		desc.setColumns(10);
		desc.setBounds(232, 46, 312, 20);
		general.add(desc);
		
		comboBox = new JComboBox();
		comboBox.setBounds(232, 77, 173, 28);
		general.add(comboBox);

		lblRubro = new JLabel(" Rubro");
		lblRubro.setOpaque(true);
		lblRubro.setForeground(Color.WHITE);
		lblRubro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRubro.setBackground(new Color(0, 153, 0));
		lblRubro.setBounds(10, 77, 173, 28);
		general.add(lblRubro);
		
		chckbxTemporal = new JCheckBox("Temporal");
		//chckbxTemporal.setSelected(false);
		chckbxTemporal.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxTemporal.setBounds(10, 127, 97, 23);
		general.add(chckbxTemporal);
		
		JLabel lblLosProductosTemporales = new JLabel("Los productos temporales requieren definir el precio en cada venta");
		lblLosProductosTemporales.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLosProductosTemporales.setBounds(113, 131, 431, 19);
		general.add(lblLosProductosTemporales);
		
		precio = new JPanel();
		tabbedPane.addTab("Precio", null, precio, null);
		precio.setLayout(null);
		
		lblPrecioLista = new JLabel(" Precio Lista");
		lblPrecioLista.setOpaque(true);
		lblPrecioLista.setForeground(Color.WHITE);
		lblPrecioLista.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrecioLista.setBackground(new Color(0, 153, 0));
		lblPrecioLista.setBounds(10, 11, 173, 20);
		precio.add(lblPrecioLista);
		
		txtlista = new JTextField();
		txtlista.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtlista.setColumns(10);
		txtlista.setBounds(10, 33, 173, 20);
		precio.add(txtlista);
		
		lblPrecioVenta = new JLabel(" Precio Venta");
		lblPrecioVenta.setOpaque(true);
		lblPrecioVenta.setForeground(Color.WHITE);
		lblPrecioVenta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrecioVenta.setBackground(new Color(0, 153, 0));
		lblPrecioVenta.setBounds(10, 67, 173, 20);
		precio.add(lblPrecioVenta);
		
		txtventa = new JTextField();
		txtventa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtventa.setColumns(10);
		txtventa.setBounds(10, 89, 173, 20);
		precio.add(txtventa);
		
		JCheckBox chckbxIva = new JCheckBox("IVA");
		chckbxIva.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxIva.setBounds(10, 128, 63, 23);
		precio.add(chckbxIva);
		
		JLabel label = new JLabel("21%");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setForeground(new Color(0, 0, 0));
		label.setBounds(79, 125, 75, 28);
		precio.add(label);
		
		JPanel stock = new JPanel();
		tabbedPane.addTab("Stock", null, stock, null);
		stock.setLayout(null);
		
		lblStockMinimo = new JLabel(" Stock Minimo");
		lblStockMinimo.setOpaque(true);
		lblStockMinimo.setForeground(Color.WHITE);
		lblStockMinimo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStockMinimo.setBackground(new Color(0, 153, 0));
		lblStockMinimo.setBounds(10, 11, 173, 20);
		stock.add(lblStockMinimo);
		
		stockmin = new JTextField();
		stockmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		stockmin.setColumns(10);
		stockmin.setBounds(10, 33, 173, 20);
		stock.add(stockmin);
		
		lblStockActual = new JLabel(" Stock Actual");
		lblStockActual.setOpaque(true);
		lblStockActual.setForeground(Color.WHITE);
		lblStockActual.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStockActual.setBackground(new Color(0, 153, 0));
		lblStockActual.setBounds(10, 64, 173, 20);
		stock.add(lblStockActual);
		
		stockact = new JTextField();
		stockact.setFont(new Font("Tahoma", Font.PLAIN, 14));
		stockact.setColumns(10);
		stockact.setBounds(10, 86, 173, 20);
		stock.add(stockact);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("GUARDAR");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int temp = 0;
						
						if(codBar.getText() != "" && desc.getText() != "" && stockmin.getText() != "" && stockact.getText() != "" && txtlista.getText() != "" && txtventa.getText() != "") {
							if(chckbxTemporal.isSelected()) {
								temp = 1;
							}
							ArrayList<Producto> array = new ArrayList<>();
							Producto producto = new Producto(
									comboBox.getSelectedItem().toString(),
									temp,
									codBar.getText(),
									desc.getText(),
									Integer.parseInt(stockmin.getText()),
									Integer.parseInt(stockact.getText()),
									Double.parseDouble(txtlista.getText()),
									Double.parseDouble(txtventa.getText()),
									1,
									getId()
									);
							
							array.add(producto);
							Gson gson = new Gson();						
							try {
								String json = gson.toJson(array);
								String data = HTTPRequest.ConsultasServidor(HTTPRequest.NUEVO_PRODUCTO, json);	
								System.out.println("Respuesta Server: " + data);
								if(!data.isEmpty()) {
									if(data.equals("ok")) {
										System.out.println("Nuevo Producto OK: " + json);
										JOptionPane.showMessageDialog(null,"Los datos se actualizaron correctamente","Información",JOptionPane.INFORMATION_MESSAGE);											
										dispose();
									}else if(data.equals("duplicado")){
										System.out.println("Nuevo Producto Duplicado: " + json);
										int input = JOptionPane.showConfirmDialog(null, 
								                "Desea actualizar los datos?", "Codigo de Barras Duplicado", 
								                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
										if(input == 0) {
											data = HTTPRequest.ConsultasServidor(HTTPRequest.ACTUALIZAR_PRODUCTO, json);
											if(data.equals("ok")) {
												System.out.println("Actualizar Producto ok: " + json);
												JOptionPane.showMessageDialog(null,"Los datos se actualizaron correctamente","Información",JOptionPane.INFORMATION_MESSAGE);											
												ActualizarProductos.buscarProducto();
												dispose();
											}else {
												System.out.println("Actualizar Producto Error: " + json);
												JOptionPane.showMessageDialog(null,"Error de Actualización","Información",JOptionPane.ERROR_MESSAGE);
											}	
										}else {
											dispose();
										}
																		
									}else {
										JOptionPane.showMessageDialog(null,"Error de Registro","Información",JOptionPane.ERROR_MESSAGE);
									}
									
								}else {
									JOptionPane.showMessageDialog(null,"Error de Actualización","Información",JOptionPane.ERROR_MESSAGE);
								}
							} catch (IOException e) {
								JOptionPane.showMessageDialog(null,"Error","Información",JOptionPane.ERROR_MESSAGE);
							}
						
						}else {
							JOptionPane.showMessageDialog(null, "Debe completar los campos obligatorios!");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCELAR");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		CargarRubro();
	}
	
	public JTextField getCodBar() {
		return codBar;
	}
	public void setCodBar(JTextField codBar) {
		this.codBar = codBar;
	}
	public JTextField getDesc() {
		return desc;
	}
	public void setDesc(JTextField desc) {
		this.desc = desc;
	}
	public JTextField getTxtlista() {
		return txtlista;
	}
	public void setTxtlista(JTextField txtlista) {
		this.txtlista = txtlista;
	}
	public JTextField getTxtventa() {
		return txtventa;
	}
	public void setTxtventa(JTextField txtventa) {
		this.txtventa = txtventa;
	}
	public JTextField getStockmin() {
		return stockmin;
	}
	public void setStockmin(JTextField stockmin) {
		this.stockmin = stockmin;
	}
	public JTextField getStockact() {
		return stockact;
	}
	public void setStockact(JTextField stockact) {
		this.stockact = stockact;
	}
	public JLabel getLblCodbar() {
		return lblCodbar;
	}
	public void setLblCodbar(JLabel lblCodbar) {
		this.lblCodbar = lblCodbar;
	}
	public JLabel getLblDesc() {
		return lblDesc;
	}
	public void setLblDesc(JLabel lblDesc) {
		this.lblDesc = lblDesc;
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}
	public JLabel getLblRubro() {
		return lblRubro;
	}
	public void setLblRubro(JLabel lblRubro) {
		this.lblRubro = lblRubro;
	}
	public JPanel getPrecio() {
		return precio;
	}
	public void setPrecio(JPanel precio) {
		this.precio = precio;
	}
	public JLabel getLblPrecioLista() {
		return lblPrecioLista;
	}
	public void setLblPrecioLista(JLabel lblPrecioLista) {
		this.lblPrecioLista = lblPrecioLista;
	}
	public JLabel getLblPrecioVenta() {
		return lblPrecioVenta;
	}
	public void setLblPrecioVenta(JLabel lblPrecioVenta) {
		this.lblPrecioVenta = lblPrecioVenta;
	}
	public JLabel getLblStockMinimo() {
		return lblStockMinimo;
	}
	public void setLblStockMinimo(JLabel lblStockMinimo) {
		this.lblStockMinimo = lblStockMinimo;
	}
	public JLabel getLblStockActual() {
		return lblStockActual;
	}
	public void setLblStockActual(JLabel lblStockActual) {
		this.lblStockActual = lblStockActual;
	}
	public JPanel getContentPanel() {
		return contentPanel;
	}
	public JCheckBox getChckbxTemporal() {
		return chckbxTemporal;
	}
	public void setChckbxTemporal(JCheckBox chckbxTemporal) {
		this.chckbxTemporal = chckbxTemporal;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void CargarRubro() {
        /**Capturo los mensajes que el ThreadCliente deja en el buffer **/
        Runnable hilo = new Runnable() {
			
			@Override
			public void run() {
				try {

					DefaultComboBoxModel modal = new DefaultComboBoxModel();
					modal =  (DefaultComboBoxModel) comboBox.getModel();

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
