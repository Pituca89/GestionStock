package cliente.kiosco;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;

import com.github.cliftonlabs.json_simple.JsonArray;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.Date;
import java.net.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import net.miginfocom.swing.MigLayout;

public class Cliente extends JFrame implements interfaceMenu{

	private static final int ACTUALIZAR_STOCK = 0;
	private static final int ACTUALIZAR_PROVEEDOR = 1;
	private static final int CAJA_FINAL = 2;
	private static final int CAJA_PARCIAL = 3;
	private static final int CONSUMO_NEGOCIO = 4;
	private static final int GASTO_PROVEEDOR = 5;
	private static final int REPOSICION_RAPIDA = 6;
	private static final int ACTUALIZAR_PRODUCTO = 7;
	
    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    int itemMenu;
    private JPanel contentPane;
    Login login;
    public Cliente() {
    	setExtendedState(Frame.MAXIMIZED_BOTH);
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Cliente.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
        initComponents();   
    }

    private void initComponents() {
    	
        setTitle("GESTION DE STOCK");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                exitForm(evt);
                
            }
        });
        login = new Login(this);
        setSize(ancho,alto);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.setBackground(new Color(255, 255, 255));
		setJMenuBar(menuBar);
		
		JMenu mnStock = new JMenu("Stock");
		mnStock.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnStock.setBackground(new Color(255, 255, 255));
		menuBar.add(mnStock);
		
		JMenuItem mntmStockActual = new JMenuItem("Stock Actual");
		mntmStockActual.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmStockActual.setBackground(new Color(255, 255, 255));
		mnStock.add(mntmStockActual);
		
		JMenuItem mntmActualizarStock = new JMenuItem("Reposición rapida");
		mntmActualizarStock.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmActualizarStock.setBackground(new Color(255, 255, 255));
		mnStock.add(mntmActualizarStock);
		
		JMenuItem mntmListaDeReposicin = new JMenuItem("Lista de Reposici\u00F3n");
		mntmListaDeReposicin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmListaDeReposicin.setBackground(new Color(255, 255, 255));
		mnStock.add(mntmListaDeReposicin);
		
		//JMenuItem mntmConsumoDeNegocio = new JMenuItem("Consumo de Negocio");
		//mntmConsumoDeNegocio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		//mntmConsumoDeNegocio.setBackground(new Color(255, 255, 255));
		//mnStock.add(mntmConsumoDeNegocio);
		
		JMenuItem mntmDevolucion = new JMenuItem("Devoluci\u00F3n ");
		mntmDevolucion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmDevolucion.setBackground(new Color(255, 255, 255));
		mnStock.add(mntmDevolucion);
		
		JMenu mnVentas = new JMenu("Ventas");
		mnVentas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnVentas.setBackground(new Color(255, 255, 255));
		menuBar.add(mnVentas);
		
		JMenuItem mntmVentaDelDia = new JMenuItem("Venta del Dia");
		mntmVentaDelDia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmVentaDelDia.setBackground(new Color(255, 255, 255));
		mnVentas.add(mntmVentaDelDia);
		
		JMenu mnProductos = new JMenu("Productos");
		mnProductos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnProductos.setBackground(new Color(255, 255, 255));
		menuBar.add(mnProductos);
		
		JMenuItem mntmListadoDeProductos = new JMenuItem("Listado de Productos");
		mntmListadoDeProductos.setBackground(new Color(255, 255, 255));
		mntmListadoDeProductos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnProductos.add(mntmListadoDeProductos);
		
		JMenuItem mntmActualizarProductos = new JMenuItem("Actualizar Productos");
		mntmActualizarProductos.setBackground(new Color(255, 255, 255));
		mntmActualizarProductos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnProductos.add(mntmActualizarProductos);
		
		JMenu mnProveedores = new JMenu("Proveedores");
		mnProveedores.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnProveedores.setBackground(new Color(255, 255, 255));
		menuBar.add(mnProveedores);
		
		JMenuItem mntmGastoProveedores = new JMenuItem("Gasto Proveedores");
		mnProveedores.add(mntmGastoProveedores);
		
		JMenu mnNegocio = new JMenu("Negocio");
		mnNegocio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNegocio.setBackground(new Color(255, 255, 255));
		menuBar.add(mnNegocio);
		
		JMenuItem mntmCierreParcial = new JMenuItem("Consulta Cierre Parcial");
		mntmCierreParcial.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmCierreParcial.setBackground(new Color(255, 255, 255));
		mnNegocio.add(mntmCierreParcial);
		
		JMenuItem mntmCierreFinal = new JMenuItem("Abrir/Cerrar Caja");
		mntmCierreFinal.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmCierreFinal.setBackground(new Color(255, 255, 255));
		mnNegocio.add(mntmCierreFinal);
		
		JMenuItem mntmConfiguracion = new JMenuItem("Configuracion");
		mntmConfiguracion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmConfiguracion.setBackground(new Color(255, 255, 255));
		menuBar.add(mntmConfiguracion);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1344, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 671, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
				
		mntmStockActual.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				login.setVisible(true);
				itemMenu = ACTUALIZAR_STOCK;				
			}
		});
		
		mntmActualizarStock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				login.setVisible(true);
				itemMenu = REPOSICION_RAPIDA;
			}
		});
		mntmGastoProveedores.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				login.setVisible(true);
				itemMenu = GASTO_PROVEEDOR;
			}
		});
		mntmListaDeReposicin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ListaDeReposicion panel = new ListaDeReposicion();
				//panel.setSize(ancho, alto);	
				contentPane.removeAll();
				contentPane.add(panel);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		mntmCierreFinal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				login.setVisible(true);
				itemMenu = CAJA_FINAL;
			}
		});
		
		mntmCierreParcial.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				login.setVisible(true);
				itemMenu = CAJA_PARCIAL;
			}
		});
		/*
		mntmConsumoDeNegocio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Descontar panel = new Descontar();
				panel.setVisible(true);
			}
		});
		*/
		mntmDevolucion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Recuperar panel = new Recuperar();
				panel.setVisible(true);
				
			}
		});
		
		mntmVentaDelDia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentasDiarias panel = new VentasDiarias();
				//panel.setSize(ancho, alto);	
				contentPane.removeAll();
				contentPane.add(panel);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		mntmListadoDeProductos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ListadoProductos panel = new ListadoProductos();
				//panel.setSize(ancho, alto);	
				contentPane.removeAll();
				contentPane.add(panel);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		mntmConfiguracion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Configuracion panel = new Configuracion();
				//panel.setSize(ancho, alto);	
				contentPane.removeAll();
				contentPane.add(panel);
				contentPane.revalidate();
				contentPane.repaint();
				
			}
		});
		
		mntmActualizarProductos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				login.setVisible(true);
				itemMenu = ACTUALIZAR_PRODUCTO;
			}
		});
        pack();
        
        try {
			if(HTTPRequest.VerificarServidor()) {
				System.out.println("conexion exitosa");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error de verificacion");
		}

    }
        
    private void exitForm(WindowEvent evt) {
        System.exit(0);
    }
    
    
	@Override
	public void verificarLogin() {
		switch (itemMenu) {
		case ACTUALIZAR_PROVEEDOR:
			System.out.println("");
		break;
		case ACTUALIZAR_STOCK:
			StockActual stockActual = new StockActual();			
			contentPane.removeAll();
			contentPane.add(stockActual);
			contentPane.revalidate();
			contentPane.repaint();
		break;
		case CAJA_FINAL:
			CajaFinal panel = new CajaFinal();
			contentPane.removeAll();
			contentPane.add(panel);
			contentPane.revalidate();
			contentPane.repaint();
		break;
		case CAJA_PARCIAL:
			CierreParcial cierreParcial = new CierreParcial();
			cierreParcial.setVisible(true);
		break;
		case CONSUMO_NEGOCIO:
			System.out.println("");
		break;
		case GASTO_PROVEEDOR:
			GastoProveedor gastoProveedor = new GastoProveedor();
			gastoProveedor.setVisible(true);	
		break;
		case REPOSICION_RAPIDA:
			Actualizar reposicioRapida = new Actualizar(1);
			reposicioRapida.setVisible(true);	
		break;
		case ACTUALIZAR_PRODUCTO:
			ActualizarProductos actualizarProductos = new ActualizarProductos();
			actualizarProductos.setVisible(true);	
		break;
		default:
			break;
		}
	}
}