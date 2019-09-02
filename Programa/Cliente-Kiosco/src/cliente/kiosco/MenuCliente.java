package cliente.kiosco;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class MenuCliente extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuCliente frame = new MenuCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 377);
		setExtendedState(MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnStock = new JMenu("Stock");
		menuBar.add(mnStock);
		
		JMenuItem mntmStockActual = new JMenuItem("Stock Actual");
		mnStock.add(mntmStockActual);
		
		JMenuItem mntmActualizarStock = new JMenuItem("Actualizar Stock");
		mnStock.add(mntmActualizarStock);
		
		JMenuItem mntmConsumoDeNegocio = new JMenuItem("Consumo de Negocio");
		mnStock.add(mntmConsumoDeNegocio);
		
		JMenuItem mntmDevolucion = new JMenuItem("Devoluci\u00F3n ");
		mnStock.add(mntmDevolucion);
		
		JMenu mnVentas = new JMenu("Ventas");
		menuBar.add(mnVentas);
		
		JMenuItem mntmVentaDelDia = new JMenuItem("Venta del Dia");
		mnVentas.add(mntmVentaDelDia);
		
		JMenu mnProductos = new JMenu("Productos");
		menuBar.add(mnProductos);
		
		JMenu mnInformes = new JMenu("Informes");
		menuBar.add(mnInformes);
		
		JMenu mnNegocio = new JMenu("Negocio");
		menuBar.add(mnNegocio);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		VentasDiarias panel = new VentasDiarias();
		JPanel panelPrincipal = new JPanel();
		GroupLayout gl_panelPrincipal = new GroupLayout(panelPrincipal);
		gl_panelPrincipal.setHorizontalGroup(
			gl_panelPrincipal.createParallelGroup(Alignment.LEADING)
				.addComponent(panel)
				.addGap(0, 424, Short.MAX_VALUE)
		);
		gl_panelPrincipal.setVerticalGroup(
			gl_panelPrincipal.createParallelGroup(Alignment.LEADING)
				.addComponent(panel)
				.addGap(0, 219, Short.MAX_VALUE)
		);
		panelPrincipal.setLayout(gl_panelPrincipal);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panelPrincipal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelPrincipal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(11))
		);
		contentPane.setLayout(gl_contentPane);
				
		mntmStockActual.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StockActual panel = new StockActual();
							
				panel.setSize(contentPane.getSize());
				
				panelPrincipal.removeAll();
				panelPrincipal.add(panel);
				panelPrincipal.revalidate();
				panelPrincipal.repaint();
				
			}
		});
		
		mntmActualizarStock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ActualizarStock panel = new ActualizarStock();
				panel.setSize(contentPane.getSize());
				panelPrincipal.removeAll();
				panelPrincipal.add(panel);
				panelPrincipal.revalidate();
				panelPrincipal.repaint();
			}
		});
		
		mntmConsumoDeNegocio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DescontarStock panel = new DescontarStock();
				panel.setSize(contentPane.getSize());
				panelPrincipal.removeAll();
				panelPrincipal.add(panel);
				panelPrincipal.revalidate();
				panelPrincipal.repaint();
			}
		});
		
		mntmDevolucion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RecuperarStock panel = new RecuperarStock();
				panel.setSize(contentPane.getSize());
				panelPrincipal.removeAll();
				panelPrincipal.add(panel);
				panelPrincipal.revalidate();
				panelPrincipal.repaint();
			}
		});
		
		mntmVentaDelDia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentasDiarias panel = new VentasDiarias();
				panel.setSize(contentPane.getSize());
				panelPrincipal.removeAll();
				panelPrincipal.add(panel);
				panelPrincipal.revalidate();
				panelPrincipal.repaint();
			}
		});
	}

}
