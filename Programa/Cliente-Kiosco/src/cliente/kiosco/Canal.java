package cliente.kiosco;

import java.security.GeneralSecurityException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Queue;

import javax.swing.JLabel;

public class Canal{
	private static Canal canal;
	private ArrayList<String> buffer;
	
	public Canal() {
		buffer = new ArrayList<>();
	}
	
	public static Canal getInstance() {
				
		if(canal == null) {
			canal = new Canal();
		}
		return canal;
	}
	
	public void Push(String rs) {
		buffer.add(rs);
	}
	
	public String Pop() {
		String rs = null;
		if(!buffer.isEmpty()) {
			rs = buffer.remove(0);
		}
		return rs;
	}
}
