import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.Date;
import java.net.*;

public class ServidorVentana extends JFrame {
	static SimpleAttributeSet at = new SimpleAttributeSet();
	static DefaultListModel us =new DefaultListModel();
	static Date hora;
	static String horaActual;
	static String datoEscrito;
	public JButton botonEnviar;
	static JButton desconectarUsuario;
    public JLabel jLabel1;
    public JLabel jLabel2;
    public JLabel jLabel3;
    public JLabel jLabel4;
    public JList usuarios;
    public JScrollPane scConversacion;
    public JScrollPane scUsuarios;
    public JScrollPane scEnvioDatos;
    static JLabel muestraIp;
    static JTextPane conversacion;
    static JTextPane envioDatos;
    
    public ServidorVentana() {
        initComponents();
        setSize(600,450);       
    }

    private void initComponents() {
        scConversacion = new JScrollPane();
        scUsuarios = new JScrollPane();
        scEnvioDatos = new JScrollPane();
        conversacion = new JTextPane();
        envioDatos = new JTextPane();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        muestraIp = new JLabel();
        jLabel3 = new JLabel();        
        usuarios = new JList(us);
        jLabel4 = new JLabel();        
        botonEnviar = new JButton();
        desconectarUsuario = new JButton();

        getContentPane().setLayout(null);

        setTitle("CHAT (servidor)");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                exitForm(evt);
            }
        });

		envioDatos.setEditable(true);
        scEnvioDatos.setViewportView(envioDatos);		
        getContentPane().add(scEnvioDatos);
        scEnvioDatos.setBounds(30, 352, 330, 60);

        conversacion.setEditable(false);
        scConversacion.setViewportView(conversacion);
        getContentPane().add(scConversacion);
        scConversacion.setBounds(30, 130, 330, 210);

        jLabel1.setFont(new Font("MS Sans Serif", 1, 24));
        jLabel1.setForeground(new Color(204, 102, 0));
        jLabel1.setText("SERVIDOR");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(210, 10, 130, 30);

        jLabel2.setFont(new Font("MS Sans Serif", 1, 14));
        jLabel2.setText("IP Local:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 70, 60, 20);

		muestraIp.setFont(new Font("MS Sans Serif", 1, 15));
		muestraIp.setForeground(new Color(255,0,0));
        getContentPane().add(muestraIp);
        muestraIp.setBounds(95, 70, 100, 20);

        jLabel3.setFont(new Font("MS Sans Serif", 1, 14));
        jLabel3.setText("Conversación:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 100, 110, 20);

        scUsuarios.setViewportView(usuarios);
        usuarios.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        getContentPane().add(scUsuarios);
        scUsuarios.setBounds(380, 132, 180, 180);

        jLabel4.setFont(new Font("MS Sans Serif", 1, 12));
        jLabel4.setText("Clientes en el chat:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(380, 110, 250, 16);

        botonEnviar.setFont(new Font("MS Sans Serif", 1, 16));
        botonEnviar.setForeground(new Color(204, 102, 0));
        botonEnviar.setText("ENVIAR !");
        getContentPane().add(botonEnviar);
        botonEnviar.setBounds(380, 352, 180, 60);
		botonEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonEnviarActionPerformed(evt);
            }
        });
                
        desconectarUsuario.setFont(new Font("MS Sans Serif", 1, 10));
        desconectarUsuario.setForeground(new Color(255,20,20));
        desconectarUsuario.setText("Desconectar seleccionados!");
        getContentPane().add(desconectarUsuario);
        desconectarUsuario.setBounds(380, 315, 180, 25);
		desconectarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                desconectarUsuarioActionPerformed(evt);
            }
        });
        setResizable(false);
        pack();
    }
        
    private void exitForm(WindowEvent evt) {    	
        System.exit(0);
    }
    
    private void desconectarUsuarioActionPerformed(ActionEvent evt) {
    	if(usuarios.isSelectionEmpty()){
    		JOptionPane.showMessageDialog(null,"Debe seleccionar al menos un cliente para desconectar","Información",JOptionPane.INFORMATION_MESSAGE);    		
    	}
    	else{
    		int resp=2,i,j;
    		resp=JOptionPane.showConfirmDialog(null,"Esta seguro de eliminar los clientes seleccionados?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);    		
    		if(resp==0){
    			Iterator it,it2;
    			for(i=us.getSize()-1; i>=0; i--){ //empezamos a desconectar de abajo hacia arriba			
    				if(usuarios.isSelectedIndex(i)){
    					try{
    						it = HiloServidor.hilos.iterator(); //para recorrer el array de hilos
    						HiloServidor tmp=null,tmp2;
    						for(j=0; j<=i; j++){
    							tmp = (HiloServidor)it.next();
    						}
    						us.removeElementAt(i); //eliminar de la lista
    						//se informa en la conversacion
    						salida(2,"Ha desconectado al cliente: "+tmp.rec.getInetAddress().getHostName().toString());
    						HiloServidor.hilos.remove(tmp); //eliminar del array
    						//se le informa a todos los clientes q ha desconectado uno
    						it = HiloServidor.hilos.iterator();
   							while(it.hasNext()){
   								tmp2 = (HiloServidor)it.next();
   								tmp2.salida.println("8"+tmp.rec.getInetAddress().getHostName());
   								tmp2.salida.println("9: Ha sido desconectado.");
   							}
    						//se le informa al cliente q ha sido desconectado
    						tmp.salida.println("6Usted a sido desconectado por el servidor.");
    						tmp.rec.close(); //cerrar el socket    						
    						tmp.stop(); //parar el hilo correspondiente    						
    					}
    					catch(Exception e){System.err.println(e.getMessage());}
    				}
    			}
    		}
    	}
    }
    
    private void botonEnviarActionPerformed(ActionEvent evt) {
    	if(usuarios.isSelectionEmpty()){
    		JOptionPane.showMessageDialog(null,"Debe seleccionar al menos un cliente para enviar el mensaje","Información",JOptionPane.INFORMATION_MESSAGE);    		
    	}
    	else{
    		datoEscrito=envioDatos.getText().toString(); //capturamos lo q se escribio
    	
    		if( datoEscrito.equals("") == false){ //se envia si escribe algo
    			
    			Iterator it = HiloServidor.hilos.iterator(); //para recorrer el array de hilos
    			salida(4,InicioServidor.nombreLocal+" (servidor) dice:\n"+datoEscrito);
    			HiloServidor tmp;
    			for(int i=0; i<us.getSize(); i++){
    				tmp = (HiloServidor)it.next();
    				if(usuarios.isSelectedIndex(i)){
    					try{//se envia el mensaje
    						tmp.salida.println("1"+InicioServidor.nombreLocal+" (servidor) dice:"); 
    						tmp.salida.println("2"+datoEscrito); 
    					}
    					catch(Exception e){}  
    				}    			  			
    			}
    		}
    		envioDatos.setText("");
    		envioDatos.requestFocus();
    	}
    }
    
    public static void salida(int objeto, String dato){
    	hora = new Date();
    	horaActual = hora.getHours()+":"+hora.getMinutes()+":"+hora.getSeconds()+" ";
    	switch(objeto){
    		case 1: //algun error
    			StyleConstants.setForeground(at,Color.red);
				StyleConstants.setBold(at,true);
				try{
					conversacion.getDocument().insertString(conversacion.getDocument().getLength(),horaActual+dato+"\n",at);
					conversacion.setCaretPosition(conversacion.getDocument().getLength());
				}
				catch(BadLocationException e) {System.err.println(e);}
    			break;
    			
    		case 2: //"conversacion" mensajes del sistema
    			try{
					StyleConstants.setForeground(at,Color.green.darker().darker());
					StyleConstants.setBold(at,true);
					conversacion.getDocument().insertString(conversacion.getDocument().getLength(),horaActual+dato+"\n",at);
					conversacion.setCaretPosition(conversacion.getDocument().getLength());
    			}
    			catch (BadLocationException e) {System.err.println(e);}
    			break;
    		
    		case 3: //"conversacion" mensaje de cliente
    			try{    				
					StyleConstants.setForeground(at,Color.blue.darker().darker());
					StyleConstants.setBold(at,false);
					conversacion.getDocument().insertString(conversacion.getDocument().getLength(),horaActual+dato+"\n",at);
					conversacion.setCaretPosition(conversacion.getDocument().getLength());
    			}
    			catch (BadLocationException e) {System.err.println(e);}
    			break;
    			
    		case 4: //"conversacion" mensaje de servidor
    			try{    				
					StyleConstants.setForeground(at,Color.blue.darker());					
					StyleConstants.setBold(at,true);
					conversacion.getDocument().insertString(conversacion.getDocument().getLength(),horaActual+dato+"\n",at);
					conversacion.setCaretPosition(conversacion.getDocument().getLength());
    			}
    			catch (BadLocationException e) {System.err.println(e);}
    			break;
    	}
    }    
}