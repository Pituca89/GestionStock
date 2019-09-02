package cliente.kiosco;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class RecuperarStock extends JPanel {

	/**
	 * Create the panel.
	 */
	public RecuperarStock() {
		
		JLabel lblRecuperarStock = new JLabel("Recuperar Stock");
		lblRecuperarStock.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblRecuperarStock);

	}

}
