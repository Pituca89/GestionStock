import java.io.*; 
import java.net.*;
import java.util.*;

class HiloServidor extends Thread{ 
	Socket rec;
	BufferedReader entrada;
   	PrintWriter salida;
	
	public static List hilos = new ArrayList();
		
	public HiloServidor(Socket c){ 
		rec=c; 
			
		try{// Extraemos los Streams de entrada y de salida 
   			salida = new PrintWriter(rec.getOutputStream(),true);
   			entrada=new BufferedReader(new InputStreamReader(rec.getInputStream()));
   			start();
 		}
 		catch(Exception e){
 			ServidorVentana.salida(1,e.getMessage());
 		}
	}
		
	public void run(){ 
   			
   		String entra="";
   		Iterator it;
   		//se informa al cliente q se ha conectado correctamente
   		salida.println("5Ha entrado al chat.");
   		
   		//se informa al cliente quienes estan conectados actualmente
   		it = hilos.iterator();
   		HiloServidor tmp;
   		while(it.hasNext()){
   			tmp = (HiloServidor)it.next();
   			salida.println("7"+tmp.rec.getInetAddress().getHostName());
   		}
   		
   		//se informa a todos los clientes q se ha conectado otro
   		it = hilos.iterator();
   		while(it.hasNext()){
   			tmp = (HiloServidor)it.next();
   			tmp.salida.println("3"+rec.getInetAddress().getHostName());
   			tmp.salida.println("4: Ha entrado al chat.");
   		}
   		
   		hilos.add(this); //añadir a lista de clientes
   		
 		try{
 			while( (entra=entrada.readLine()) != null){
 				if(entra.equals("2")){
 					try{
    					it = hilos.iterator(); //para recorrer el array de hilos    					
    					for(int j=0; j<ServidorVentana.us.getSize(); j++){
    						if(this == (HiloServidor)it.next()){
    							ServidorVentana.us.removeElementAt(j); //eliminar de la lista
    							break;
    						}
    					}    						
    					//se informa en la conversacion
    					ServidorVentana.salida(2,rec.getInetAddress().getHostName().toString()+": Ha abandonado el chat.");
    					hilos.remove(this); //eliminar del array
    					//se le informa a todos los clientes q alguien se ha desconectado
    					it = hilos.iterator();
   						while(it.hasNext()){
   							tmp = (HiloServidor)it.next();
   							tmp.salida.println("8"+rec.getInetAddress().getHostName());
   							tmp.salida.println("9: Ha abandonado el chat.");
   						}
    					//se le informa al cliente q se ha desconectado correctamente
    					salida.println("6Usted se ha desconectado correctamente.");
    					rec.close(); //cerrar el socket
    					break; //parar este hilo
    				}
    				catch(Exception e){System.err.println(e.getMessage());}
 				}
 				
   				ServidorVentana.salida(3, rec.getInetAddress().getHostName()+" dice: \n"+entra.substring(1));
   				//se reenvia el mesaje a todos los clientes "ECHO"
   				it = hilos.iterator();
   				HiloServidor tmp2;
   				while(it.hasNext()){
   					tmp2 = (HiloServidor)it.next();
   					if( !(tmp2.equals(this)) ){
   						tmp2.salida.println("1"+tmp2.rec.getInetAddress().getHostName()+" dice:");
   						tmp2.salida.println("2"+entra.substring(1));
   					}
   				}
   			}
   		}
   		catch(Exception e){
   			ServidorVentana.salida(1,e.getMessage());
   		}
	} 
} 