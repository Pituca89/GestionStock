package cliente.kiosco;
import java.awt.Component;
import java.io.*; 
import java.net.*;
import java.util.ArrayList;

import javax.swing.JLabel;

class HiloCliente extends Thread{ 
	static BufferedReader entradaDatos=null;
	static ObjectInputStream entradaDatosO=null;
	
	public HiloCliente(BufferedReader entrada2){
		entradaDatos = entrada2;
		start();
	}
	
	public HiloCliente(ObjectInputStream entrada2){
		entradaDatosO = entrada2;
		start();
	}
	
	public void run(){
		
   		String linea="";
   		/**Interpreto la respuesta del servidor para saber en que ventana mostrar los datos**/
		try{
			
			while( (linea = entradaDatos.readLine()) != null ){ //escucha mensajes del servidor
				
				//StockActual.ActualizarComponentes(linea);
				//System.out.println(linea);
				
				Canal canal = Canal.getInstance();
				canal.Push(linea);
				
								
				if(linea.equals("6Usted a sido desconectado por el servidor."))	break;
				if(linea.equals("6Usted se ha desconectado correctamente.")) break;
			}
			
		}
		catch(Exception e){ 
   			System.out.println("Error al crear hilo cliente");
   			if(entradaDatos!=null){
   				try{ entradaDatos.close();}
   				catch(Exception er){}
   			}
   		}
	}
}
