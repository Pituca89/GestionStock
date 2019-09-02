package servidor.kiosco;

import java.io.*; 
import java.net.*;
import java.util.*;

import com.mysql.jdbc.Connection;
  
public class InicioServidor {
	static String ipLocal = "";
	static String nombreLocal="";
	static ServerSocket socketServ = null; 
	private static BaseMySQL mysql;
    
    public static void main(String args[]) {
    	
        new Servidor().setVisible(true);
        
        try{ 
        	ipLocal = InetAddress.getLocalHost().getHostAddress().toString();
        	nombreLocal = InetAddress.getLocalHost().getHostName().toString();
        	Servidor.muestraIp.setText(ipLocal);
   		
   			socketServ = new ServerSocket(7777);
   			Servidor.salida(2, "Socket creado, escuchando en puerto 7777.");
   			
   			mysql = mysql.getInstance();
   			   			
   			while(true){
   				Socket s=null;   				
   				s = socketServ.accept(); 
   				Servidor.us.addElement(s.getInetAddress().getHostName()+"/"+
   					s.getInetAddress().getHostAddress()+":"+s.getPort());
   				Servidor.salida(2, "Se ha conectado "+s.getInetAddress().getHostName());
   				
   				new HiloServidor(s); //nuevo hilo q atendera los mensajes de este cleinte
   			}
   		}
   		catch(java.net.BindException e){
   			Servidor.salida(1,"No se puede arrancar el servidor: puerto 7777 ocupado.");
   		}
   		catch(java.lang.SecurityException e){
   			Servidor.salida(1,"No se puede arrancar el servidor: Hay restricciones de seguridad.");
   		}
   		catch(Exception e){
   			Servidor.salida(1,e.getMessage());
   		}
    }
}
