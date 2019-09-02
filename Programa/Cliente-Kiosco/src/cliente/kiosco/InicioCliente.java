package cliente.kiosco;

import java.io.*; 
import java.net.*;
import java.rmi.server.Skeleton;

class InicioCliente { 
	static Socket sckt=null;
	static PrintWriter salidaC=null;
	static ObjectOutputStream salidaO = null;
	
  	public static void arrancarCliente(String dir, int puerto){
  		try{   
  			sckt = new Socket(dir,puerto);
  		}
  		catch(Exception e){ 
   			//Cliente.salida(1,e.getMessage());
   		}   		
   	}
   	
   	public static void procesarMensajes(){
   		BufferedReader entradaC;
   		ObjectInputStream entradaO;
   		try{
   			entradaC=new BufferedReader(new InputStreamReader(sckt.getInputStream()));
   			salidaC=new PrintWriter(sckt.getOutputStream(),true);

   			new HiloCliente(entradaC);
		}		
   		catch(Exception e){
   			System.out.println("Error al establecer canal de comunicacion");
   		}
   	}
	
	public static void main(String args[]){ 
		Cliente cliente = new Cliente();
		cliente.setVisible(true);	
  	}
} 
