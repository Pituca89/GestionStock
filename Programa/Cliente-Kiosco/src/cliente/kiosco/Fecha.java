package cliente.kiosco;

public class Fecha {

	String inicio;
	String fin;
	String FECHA_INICIO;
	int idmaquina;
	
	
	public int getIdmaquina() {
		return idmaquina;
	}
	public void setIdmaquina(int idmaquina) {
		this.idmaquina = idmaquina;
	}
	public String getFECHA_INICIO() {
		return FECHA_INICIO;
	}
	public void setFECHA_INICIO(String fECHA_INICIO) {
		FECHA_INICIO = fECHA_INICIO;
	}
	public String getInicio() {
		return inicio;
	}
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
	public Fecha(String inicio, String fin, int idmaquina) {
		super();
		this.inicio = inicio;
		this.fin = fin;
		this.idmaquina = idmaquina;
	}
	
	public Fecha(String fECHA_INICIO) {
		super();
		this.FECHA_INICIO = fECHA_INICIO;
	}
	
	
}
