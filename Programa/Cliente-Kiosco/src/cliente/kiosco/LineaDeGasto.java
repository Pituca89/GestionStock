package cliente.kiosco;

public class LineaDeGasto {
	private int LINEA_ID;
	private int GASTO_ID;
	private String CODIGO_BARRA;
	private double SUBTOTAL;
	private String FECHA_LINEA_GASTO;
	private int CANTIDAD;
	private int RUBRO;
	private double VALOR_COSTO;
	
	
	public LineaDeGasto(String cODIGO_BARRA, double sUBTOTAL, int cANTIDAD) {
		super();
		CODIGO_BARRA = cODIGO_BARRA;
		SUBTOTAL = sUBTOTAL;
		CANTIDAD = cANTIDAD;
	}
	
	public double getCOSTO() {
		return VALOR_COSTO;
	}

	public void setCOSTO(double vALOR_COSTO) {
		VALOR_COSTO = vALOR_COSTO;
	}

	public String getCODIGO_BARRA() {
		return CODIGO_BARRA;
	}
	public void setCODIGO_BARRA(String cODIGO_BARRA) {
		CODIGO_BARRA = cODIGO_BARRA;
	}
	public int getRUBRO() {
		return RUBRO;
	}
	public void setRUBRO(int rUBRO) {
		RUBRO = rUBRO;
	}
	public LineaDeGasto(String cODIGO_BARRA, double sUBTOTAL, int cANTIDAD,int rUBRO) {
		super();
		CODIGO_BARRA = cODIGO_BARRA;
		SUBTOTAL = sUBTOTAL;
		CANTIDAD = cANTIDAD;
		RUBRO = rUBRO;
	}
	
	public int getLINEA_ID() {
		return LINEA_ID;
	}
	public void setLINEA_ID(int lINEA_ID) {
		LINEA_ID = lINEA_ID;
	}
	public int getGASTO_ID() {
		return GASTO_ID;
	}
	public void setGASTO_ID(int gASTO_ID) {
		GASTO_ID = gASTO_ID;
	}
	public String getPRODUCT_ID() {
		return CODIGO_BARRA;
	}
	public void setPRODUCT_ID(String pRODUCT_ID) {
		CODIGO_BARRA = pRODUCT_ID;
	}
	public double getSUBTOTAL() {
		return SUBTOTAL;
	}
	public void setSUBTOTAL(double sUBTOTAL) {
		SUBTOTAL = sUBTOTAL;
	}
	public String getFECHA_LINEA_GASTO() {
		return FECHA_LINEA_GASTO;
	}
	public void setFECHA_LINEA_GASTO(String fECHA_LINEA_GASTO) {
		FECHA_LINEA_GASTO = fECHA_LINEA_GASTO;
	}
	public int getCANTIDAD() {
		return CANTIDAD;
	}
	public void setCANTIDAD(int cANTIDAD) {
		CANTIDAD = cANTIDAD;
	}
	
	
}
