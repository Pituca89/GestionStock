package cliente.kiosco;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;

public class Cambio_cantidad extends JDialog {

	public JLabel getLblCodigo() {
		return lblCodigo_1;
	}

	public void setLblCodigo(JLabel lblCodigo) {
		this.lblCodigo_1 = lblCodigo;
	}

	private final JPanel contentPanel = new JPanel();
	private JTextField cantidad;
	private JTextField precio;
	private JLabel lblNewLabel;
	private JLabel lblCodigo;
	private JLabel lblCodigo_1;
	int valor_previo;
	
	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}


	public Cambio_cantidad(VentasDiarias padre) {
		
		setBounds(100, 100, 399, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblProducto = new JLabel("PRODUCTO:");
		lblProducto.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		lblNewLabel = new JLabel("DESCRIPCI\u00D3N");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblCantidad = new JLabel("CANTIDAD");
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		cantidad = new JTextField();
		cantidad.setFont(new Font("Tahoma", Font.BOLD, 16));
		cantidad.setColumns(10);
		
		JLabel lblPrecio = new JLabel("PRECIO");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		precio = new JTextField();
		precio.setFont(new Font("Tahoma", Font.BOLD, 16));
		precio.setColumns(10);
		
		lblCodigo = new JLabel("CODIGO");
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		lblCodigo_1 = new JLabel("CODIGO");
		lblCodigo_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		contentPanel.setLayout(new MigLayout("", "[154px][184px]", "[63.00px][59.00px][54.00px][26px]"));
		contentPanel.add(lblCodigo, "cell 0 0,alignx left,aligny top");
		contentPanel.add(lblProducto, "cell 0 1,growx,aligny top");
		contentPanel.add(lblCantidad, "cell 0 2,alignx left,aligny center");
		
		contentPanel.add(lblPrecio, "cell 0 3,growx,aligny top");
		contentPanel.add(lblNewLabel, "cell 1 1,growx,aligny top");
		contentPanel.add(precio, "cell 1 3,growx,aligny top");
		contentPanel.add(cantidad, "cell 1 2,growx,aligny center");
		contentPanel.add(lblCodigo_1, "cell 1 0,alignx center,aligny top");
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
		
						/**Seteo la cantidad tomada del text dialog, en la fila de la tabla**/
						padre.getTable().setValueAt(getCantidad().getText(), padre.getTable().getSelectedRow(), 2);
						padre.getTable().setValueAt(getPrecio().getText(), padre.getTable().getSelectedRow(), 3);
						/**Seteo el subtotal obtenido en base a la nueva cantidad ingresada en el dialog**/
						double newsub = Double.parseDouble(getCantidad().getText()) * Double.parseDouble(getPrecio().getText()) ;
						padre.getTable().setValueAt(newsub, padre.getTable().getSelectedRow(), 4);
						
						VentasDiarias.obtenerTotal();
						padre.getTextField().requestFocus();
						dispose();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		if(!getCantidad().getText().isEmpty())
			valor_previo = Integer.parseInt(getCantidad().getText());
	}

	public JTextField getCantidad() {
		return cantidad;
	}

	public void setCantidad(JTextField cantidad) {
		this.cantidad = cantidad;
	}

	public JTextField getPrecio() {
		return precio;
	}

	public void setPrecio(JTextField precio) {
		this.precio = precio;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}
}
