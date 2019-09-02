package cliente.kiosco;

public class LineaDeVenta {
	private String CODIGO_BARRAS;
	private String NOMBRE;
	private int STOCK_DISPONIBLE;
	private int CANTIDAD;
	private double VALOR_VENTA;
	private double SUBTOTAL;
	private int ULTIMO;
	private int IS_TEMPORAL;
	private int IS_FACTURABLE;
	private int VENTA_ID;
	private int STOCK_MINIMO;
	public LineaDeVenta(String cODIGO_BARRAS, String nOMBRE,int sTOCK_MINIMO, int cANTIDAD, double vALOR_VENTA, double sUBTOTAL, int uLTIMO, int cANT_DISPONIBLE, int iS_TEMPORAL, int iS_FACTURABLE) {
		super();
		CODIGO_BARRAS = cODIGO_BARRAS;
		NOMBRE = nOMBRE;
		STOCK_MINIMO = sTOCK_MINIMO;
		CANTIDAD = cANTIDAD;
		VALOR_VENTA = vALOR_VENTA;
		SUBTOTAL = sUBTOTAL;
		ULTIMO = uLTIMO;
		STOCK_DISPONIBLE = cANT_DISPONIBLE;
		IS_TEMPORAL = iS_TEMPORAL;
		IS_FACTURABLE = iS_FACTURABLE;
	}
	
	
	public int getSTOCK_MINIMO() {
		return STOCK_MINIMO;
	}


	public void setSTOCK_MINIMO(int sTOCK_MINIMO) {
		STOCK_MINIMO = sTOCK_MINIMO;
	}


	public LineaDeVenta(int vENTA_ID,String cODIGO_BARRAS, int cANTIDAD, double sUBTOTAL, int iS_FACTURABLE) {
		super();
		CODIGO_BARRAS = cODIGO_BARRAS;
		CANTIDAD = cANTIDAD;
		SUBTOTAL = sUBTOTAL;
		IS_FACTURABLE = iS_FACTURABLE;
		VENTA_ID = vENTA_ID;
	}


	public int getIS_TEMPORAL() {
		return IS_TEMPORAL;
	}

	public void setIS_TEMPORAL(int iS_TEMPORAL) {
		IS_TEMPORAL = iS_TEMPORAL;
	}

	
	public int getCANT_DISPONIBLE() {
		return STOCK_DISPONIBLE;
	}

	public void setCANT_DISPONIBLE(int cANT_DISPONIBLE) {
		STOCK_DISPONIBLE = cANT_DISPONIBLE;
	}

	public int getULTIMO() {
		return ULTIMO;
	}

	public void setULTIMO(int uLTIMO) {
		ULTIMO = uLTIMO;
	}

	public String getCODIGO_BARRAS() {
		return CODIGO_BARRAS;
	}
	public void setCODIGO_BARRAS(String cODIGO_BARRAS) {
		CODIGO_BARRAS = cODIGO_BARRAS;
	}
	public String getNOMBRE() {
		return NOMBRE;
	}
	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}
	public int getCANTIDAD() {
		return CANTIDAD;
	}
	public void setCANTIDAD(int cANTIDAD) {
		CANTIDAD = cANTIDAD;
	}
	public double getVALOR_VENTA() {
		return VALOR_VENTA;
	}
	public void setVALOR_VENTA(double vALOR_VENTA) {
		VALOR_VENTA = vALOR_VENTA;
	}
	public double getSUBTOTAL() {
		return SUBTOTAL;
	}
	public void setSUBTOTAL(double sUBTOTAL) {
		SUBTOTAL = sUBTOTAL;
	}
}
