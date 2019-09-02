package servidor.kiosco;
import java.io.*; 
import java.net.*;
import java.sql.ResultSet;
import java.util.*;

import javax.swing.JLabel;

import com.mysql.jdbc.Connection;

class HiloServidor extends Thread{ 
	Socket rec;
	BufferedReader entrada;
   	PrintWriter salida;
   	
   	ObjectInputStream entradaO;
   	ObjectOutputStream salidaO;
	private BaseMySQL mysql;
	public static List hilos = new ArrayList();
		
	public HiloServidor(Socket c){ 
		rec=c; 
			
		try{// Extraemos los Streams de entrada y de salida 
   			salida = new PrintWriter(rec.getOutputStream(),true);
   			entrada = new BufferedReader(new InputStreamReader(rec.getInputStream()));
   			
   			//entradaO = new ObjectInputStream(rec.getInputStream());
   			//salidaO = new ObjectOutputStream(rec.getOutputStream());
   			start();
 		}
 		catch(Exception e){
 			System.out.println("error al crear hilo servidor");
 			Servidor.salida(1,e.getMessage());
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
   		
   		/**Defino los mensajes que me envia el cliente para poder realizar las consultas correspondientes en la bdd**/
 		try{
 			while( (entra=entrada.readLine()) != null){//while(entradaO.readObject()!=null) {//
 				
 				if(entra.equals("2")){
 					try{
    					it = hilos.iterator(); //para recorrer el array de hilos    					
    					for(int j=0; j<Servidor.us.getSize(); j++){
    						if(this == (HiloServidor)it.next()){
    							Servidor.us.removeElementAt(j); //eliminar de la lista
    							break;
    						}
    					}    						
    					//se informa en la conversacions
    					Servidor.salida(2,rec.getInetAddress().getHostName().toString()+": Ha abandonado el chat.");
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
 				
 				Servidor.salida(3, rec.getInetAddress().getHostName()+" dice: \n"+entra.substring(1));
 				//JLabel lbl = (JLabel) entradaO.readObject();
 				//Servidor.salida(3, rec.getInetAddress().getHostName()+" dice: \n" + lbl.getText());
 				if(entra.equals(Servidor.STOCK_ACTUAL)) {
	   				
	   				//se reenvia el mesaje a todos los clientes "ECHO"
	   				it = hilos.iterator();
	   				HiloServidor tmp2;
	   				while(it.hasNext()){
	   					tmp2 = (HiloServidor)it.next();
	   					if((tmp2.equals(this)) ){
	   						this.mysql = BaseMySQL.getInstance();
	   						String query = "CALL SCH_KIOSCO.SP_STOCK_ACTUAL('');";
	   						ResultSet rs = this.mysql.Sentencia(query);
	   						while(rs.next()) {
	   							//System.out.println(rs.getInt(1) + "->" + rs.getString(2));
	   							//String salida = Conversor.convertToJSON(rs);
	   							tmp2.salida.println(rs.getInt(1) + "|" + rs.getString(2) + "|" + rs.getInt(3) 
	   							+ "|" + rs.getInt(4) + "|" + rs.getDouble(5) + "|" + rs.getDouble(6) 
	   							+ "|" + rs.getDate(7) + "|" + rs.getInt(8));
	   						}
	   						//tmp2.salida.println("1"+tmp2.rec.getInetAddress().getHostName()+" dice:");
	   						//tmp2.salida.println("2"+entra.substring(1));
	   					}
	   				}
 				}
   			}
   		}
   		catch(Exception e){
   			Servidor.salida(1,e.getMessage());
   			System.out.println("error al recibir mensajes en el servidor");
   		}
	} 
} 