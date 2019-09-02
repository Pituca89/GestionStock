package cliente.kiosco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;


public class HTTPRequest {
	public static final String ALL_STOCK = "ALL_STOCK";
	public static final String UN_STOCK = "UN_STOCK";
	public static final String BUSCAR_PRODUCTO = "BUSCAR_PRODUCTO";
	public static final String ACTUALIZAR_STOCK = "ACTUALIZAR_STOCK";
	public static final String DEVOLVER_PRODUCTO = "DEVOLVER_PRODUCTO";
	public static final String DESCONTAR_PRODUCTO = "DESCONTAR_PRODUCTO";
	public static final String PROCESAR_VENTA = "PROCESAR_VENTA";
	public static final String ALERTA_STOCK = "ALERTA_STOCK";
	public static final String REPOSICION = "REPOSICION";
	public static final String NUEVA_VENTA = "NUEVA_VENTA";
	public static final String NUEVO_ITEM = "NUEVO_ITEM";
	public static final String NUEVO_PRODUCTO = "NUEVO_PRODUCTO";
	public static final String CIERRE_CAJA = "CIERRE_CAJA";
	public static final String ACTUALIZAR_PRODUCTO = "ACTUALIZAR_PRODUCTO";
	public static final String ELIMINAR_PRODUCTO = "ELIMINAR_PRODUCTO";
	public static final String ALL_PRODUCTOS = "ALL_PRODUCTOS";
	public static final String RUBRO = "RUBRO";
	public static final String LISTA_PRODUCTOS = "LISTA_PRODUCTOS";
	public static final String CARGAR_GASTO = "CARGAR_GASTO";
	public static final String INICIO_CAJA_FINAL = "INICIO_CAJA_FINAL";
	public static final String CIERRE_CAJA_FINAL = "CIERRE_CAJA_FINAL";
	public static final String GASTO_PROVEEDORES = "GASTO_PROVEEDORES";
	public static final String CONSULTA_CAJA = "CONSULTA_CAJA";
	public static final String DESCARGAR_CAJA_DIA = "DESCARGAR_CAJA_DIA";
	public static final String RESUMEN_CAJA_FINAL = "RESUMEN_CAJA_FINAL";
	public static final String LOGIN = "LOGIN";
	public static final String HOST = "HOST";
	
    static BufferedReader in = null;
    static String inputLine = "";   
	public static String host = "192.168.1.57";
	
    public static boolean VerificarServidor() throws IOException {
    	URL oracle;
        URLConnection yc = null;
          
        String inputLine;
		try {
			BufferedReader in;
			oracle = new URL("http://"+host+"?data=1");
			yc = oracle.openConnection();
			in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			while ((inputLine = in.readLine()) != null) { 
				System.out.println(inputLine);
	        	if(inputLine.equals("1")) return true;
	        }
			in.close();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			System.out.println("error de conexion");
		} // URL to Parse
                  
    	return false;
    }
    
    public static String ConsultasServidor(String op,String json) throws IOException {

        switch (op) {
		case ALL_STOCK:	
			return peticionPOST("all", json);
		case CIERRE_CAJA: 
			return peticionPOST("cierrep", json);
		case UN_STOCK:
			return peticionPOST("unstock", json);
		case BUSCAR_PRODUCTO:			
			return peticionPOST("buscarprod", json);
		case ACTUALIZAR_STOCK:		
			return peticionPOST("actualizar", json);
		case DEVOLVER_PRODUCTO:
			return peticionPOST("all", json);
		case DESCONTAR_PRODUCTO:			
			return peticionPOST("all", json);
		case NUEVA_VENTA:	
			return peticionPOST("nueva_venta", json);
		case NUEVO_ITEM:
			return peticionPOST("nuevo_item", json);
		case PROCESAR_VENTA:
			return peticionPOST("venta", json);
		case ALERTA_STOCK:
			return peticionPOST("all", json);
		case REPOSICION:
			return peticionPOST("repo", json);
	    case NUEVO_PRODUCTO:
	    	return peticionPOST("nuevo_producto", json);
	    case LISTA_PRODUCTOS:		 
	    	return peticionPOST("listaprod", json);
	    case ACTUALIZAR_PRODUCTO:
	    	return peticionPOST("actprod", json);		 
	    case ELIMINAR_PRODUCTO:
	    	return peticionPOST("eliminarprod", json);
	    case ALL_PRODUCTOS:
	    	return peticionPOST("allprod", json);
	    case RUBRO:
	    	return peticionPOST("rubro", json);
	    case CARGAR_GASTO:		 
	    	return peticionPOST("gasto", json);
	    case GASTO_PROVEEDORES:	 
	    	return peticionPOST("proveedor", json);
	    case INICIO_CAJA_FINAL:		 
	    	return peticionPOST("iniciocaja", json);
	    case CIERRE_CAJA_FINAL:		 
	    	return peticionPOST("cierrecaja", json);
	    case CONSULTA_CAJA:	 
	    	return peticionPOST("consulta_caja", json);
	    case DESCARGAR_CAJA_DIA:	
	    	return peticionPOST("descargar_caja_dia", json);
	    case RESUMEN_CAJA_FINAL:	
	    	return peticionPOST("resumen", json);
	    case LOGIN:	
	    	return peticionPOST("login", json);
	    case HOST:	
	    	return peticionPOST("host", json);
		default:
			break;
		}
        
    	return inputLine;
    }
    
    public static String peticionPOST(String data, String cod) {
    	try{
	    	URL url = new URL("http://"+host);
	        HashMap<String, Object> params = new LinkedHashMap<>();
	 
	        params.put("data", data);
	        params.put("cod", cod);
	        StringBuilder postData = new StringBuilder();
	        for (Map.Entry<String, Object> param : params.entrySet()) {
	            if (postData.length() != 0)
	                postData.append('&');
	            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
	                    "UTF-8"));
	        }
	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	 
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type",
	                "application/x-www-form-urlencoded");
	        conn.setRequestProperty("Content-Length",
	                String.valueOf(postDataBytes.length));
	        conn.setDoOutput(true);
	        conn.getOutputStream().write(postDataBytes);
	 
	        in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			if ((inputLine = in.readLine()) != null) { 
				return inputLine;
	        }else
	        	return "error";
	    	}catch(Exception e) {
	    		System.out.println(e);
	    	}
    	return "";
    }
}
