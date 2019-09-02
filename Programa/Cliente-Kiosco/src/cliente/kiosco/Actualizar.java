package cliente.kiosco;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Actualizar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ActualizarStock actualizarStock;
	private JTextField textField;
	String data = "";
	Thread thread;

	public ActualizarStock getActualizarStock() {
		return actualizarStock;
	}

	public void setActualizarStock(ActualizarStock actualizarStock) {
		this.actualizarStock = actualizarStock;
	}

	/**
	 * Create the dialog.
	 */
	public Actualizar(int flag) {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Actualizar.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		setBounds(100, 100, 535, 419);
		getContentPane().setLayout(new BorderLayout());
		
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("GUARDAR");
				okButton.setFocusable(false);
				okButton.requestFocus(false);
				okButton.addKeyListener(new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {
						if(e.getKeyCode() ==  e.VK_ENTER) {
							return;
						}
						
					}
					
					@Override
					public void keyReleased(KeyEvent e) {
						if(e.getKeyCode() ==  e.VK_ENTER) {
							return;
						}
						
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode() ==  e.VK_ENTER) {
							return;
						}
						
					}
				});
				okButton.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						int resp;
						resp=JOptionPane.showConfirmDialog(null,"Esta seguro que desea actualizar el stock?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
						if(resp==0) {
							Runnable hilo = new Runnable() {
								
								@Override
								public void run() {
									ArrayList<Stock> array = new ArrayList<>();
									Stock stock = new Stock(actualizarStock.getLblCodigo().getText(),
											Integer.parseInt(actualizarStock.getStockMin().getText()),
											Integer.parseInt(actualizarStock.getStockActual().getText()),
											1);
									array.add(stock);
									Gson gson = new Gson();
									try {
										String json = gson.toJson(array);
										System.out.println(json);
										data = HTTPRequest.ConsultasServidor(HTTPRequest.ACTUALIZAR_STOCK, json);	
										if(!data.isEmpty()) {
											if(data.equals("ok")) {
												JOptionPane.showMessageDialog(null,"Los datos se actualizaron correctamente","Información",JOptionPane.INFORMATION_MESSAGE);											
												if(flag == 2) StockActual.ActualizarStock();
												dispose();
													
											}else {
												JOptionPane.showMessageDialog(null,"Error de actualización","Información",JOptionPane.ERROR_MESSAGE);
											}
											System.out.println(data);
										}
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								}
							};
							
						thread =  new Thread(hilo);
						thread.start();
						}
						
					}
				} );
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCELAR");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(thread != null)
							thread.stop();
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			contentPanel.setFocusable(false);
			
			actualizarStock = new ActualizarStock();
			
			actualizarStock.getCodProducto().setFocusable(true);
			actualizarStock.getCodProducto().requestFocus();
			getContentPane().add(actualizarStock);
			
		}
		
	}

}
