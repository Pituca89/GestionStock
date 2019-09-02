package cliente.kiosco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Descontar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JTextField cantDesc;
	private JTextField codProducto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Descontar dialog = new Descontar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Descontar() {
		setBounds(100, 100, 451, 357);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblNewLabel = new JLabel("Descontar");
			lblNewLabel.setBounds(164, 11, 100, 20);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		}
		JLabel lblCodigoDeProducto = new JLabel("Codigo de Producto");
		lblCodigoDeProducto.setForeground(new Color(255, 255, 255));
		lblCodigoDeProducto.setBackground(new Color(0, 153, 0));
		lblCodigoDeProducto.setBounds(15, 39, 189, 28);
		lblCodigoDeProducto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodigoDeProducto.setOpaque(true);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.setBounds(230, 127, 160, 23);
		
		JLabel lblCantidadADescontar = new JLabel("Cantidad a descontar");
		lblCantidadADescontar.setForeground(new Color(255, 255, 255));
		lblCantidadADescontar.setBackground(new Color(0, 153, 0));
		lblCantidadADescontar.setBounds(15, 100, 189, 28);
		lblCantidadADescontar.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCantidadADescontar.setOpaque(true);
		
		cantDesc = new JTextField();
		cantDesc.setBounds(15, 128, 189, 20);
		cantDesc.setColumns(10);
		
		codProducto = new JTextField();
		codProducto.setBounds(15, 67, 189, 20);
		codProducto.setColumns(10);
		contentPanel.setLayout(null);
		contentPanel.add(codProducto);
		contentPanel.add(lblCantidadADescontar);
		contentPanel.add(lblCodigoDeProducto);
		contentPanel.add(lblNewLabel);
		contentPanel.add(cantDesc);
		contentPanel.add(btnBuscar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("CONFIRMAR");
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
	}
}
