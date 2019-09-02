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
import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GastoProveedor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField codBar;
	private JLabel lblCodbar;
	private JLabel lblRubro;
	private int id;
	private Rubro[] rubro_1;
	static String data = "";
	static Gson gson;
	private JTextField costo;
	private int idRubro;
	JComboBox rubro;

	public GastoProveedor() {
		gson = new Gson();
		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevoProducto.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		setBounds(100, 100, 490, 282);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		codBar = new JTextField();
		codBar.setText("GASTO PROVEEDOR");
		codBar.setEditable(false);
		codBar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codBar.setColumns(10);
		
		lblCodbar = new JLabel(" Codigo");
		lblCodbar.setForeground(new Color(255, 255, 255));
		lblCodbar.setBackground(new Color(0, 153, 0));
		lblCodbar.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodbar.setOpaque(true);
		
		lblRubro = new JLabel(" Proveedor");
		lblRubro.setOpaque(true);
		lblRubro.setForeground(Color.WHITE);
		lblRubro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRubro.setBackground(new Color(0, 153, 0));
		
		JLabel lblCostoUnitario = new JLabel(" Monto Facturado");
		lblCostoUnitario.setOpaque(true);
		lblCostoUnitario.setForeground(Color.WHITE);
		lblCostoUnitario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCostoUnitario.setBackground(new Color(0, 153, 0));
		
		costo = new JTextField();
		costo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		costo.setColumns(10);
		
		rubro = new JComboBox();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblCodbar, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
								.addComponent(codBar, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(rubro, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRubro, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)))
						.addComponent(costo, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCostoUnitario, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodbar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRubro, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(rubro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(codBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addComponent(lblCostoUnitario, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(costo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("GUARDAR");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					
						if(costo.getText() != "") {
							ArrayList<LineaDeGasto> array = new ArrayList<>();							
							LineaDeGasto lineaDeGasto = new LineaDeGasto(								
									codBar.getText(),
									Double.parseDouble(costo.getText()),
									1,
									rubro_1[rubro.getSelectedIndex()].getID()					
									);
							
							array.add(lineaDeGasto);
							Gson gson = new Gson();						
							try {
								String json = gson.toJson(array);
								System.out.println(json);
								String data = HTTPRequest.ConsultasServidor(HTTPRequest.GASTO_PROVEEDORES, json);	
								System.out.println("Respuesta Server: " + data);
								if(!data.isEmpty()) {
									if(data.equals("ok")) {
										System.out.println("Nuevo Producto OK: " + json);
										JOptionPane.showMessageDialog(null,"Los datos se actualizaron correctamente","Información",JOptionPane.INFORMATION_MESSAGE);											
										//dispose();
										costo.setText("");
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

	public JLabel getLblCodbar() {
		return lblCodbar;
	}
	public void setLblCodbar(JLabel lblCodbar) {
		this.lblCodbar = lblCodbar;
	}

	public JLabel getLblRubro() {
		return lblRubro;
	}
	public void setLblRubro(JLabel lblRubro) {
		this.lblRubro = lblRubro;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	public JTextField getCosto() {
		return costo;
	}

	public void setCosto(JTextField costo) {
		this.costo = costo;
	}
	

	public int getIdRubro() {
		return idRubro;
	}

	public void setIdRubro(int idRubro) {
		this.idRubro = idRubro;
	}

	public JComboBox getRubro() {
		return rubro;
	}

	public void setRubro(JComboBox rubro) {
		this.rubro = rubro;
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
