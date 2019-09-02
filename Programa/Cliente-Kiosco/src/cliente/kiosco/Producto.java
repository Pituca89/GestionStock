package cliente.kiosco;

public class Producto {
	
    private String CATEGORY_ID;
    private int IS_TEMPORAL;
    private String CODIGO_BARRAS;
    private String NOMBRE;
	private int STOCK_MINIMO;
	private int STOCK_ACTUAL;
	private double VALOR_COSTO;
	private double VALOR_VENTA;
	private int USER;
	private String CATEGORY_NAME;
	private String FECHA_ACTUALIZACION;
	private String FECHA_REPO;
	private int PRODUCT_ID;
	
	public Producto(String cODIGO_BARRAS, String nOMBRE, int sTOCK_ACTUAL, double pRECIO_COSTO, double pRECIO_VENTA,
			String cATEGORY_NAME, String fECHA_ACTUALIZACION, String fECHA_REPO/**, int pRODUCT_ID**/) {
		super();
		CODIGO_BARRAS = cODIGO_BARRAS;
		NOMBRE = nOMBRE;
		STOCK_ACTUAL = sTOCK_ACTUAL;
		VALOR_COSTO = pRECIO_COSTO;
		VALOR_VENTA = pRECIO_VENTA;
		CATEGORY_NAME = cATEGORY_NAME;
		FECHA_ACTUALIZACION = fECHA_ACTUALIZACION;
		FECHA_REPO = fECHA_REPO;
		//PRODUCT_ID = pRODUCT_ID;
	}

	public double getVALOR_COSTO() {
		return VALOR_COSTO;
	}

	public void setVALOR_COSTO(double vALOR_COSTO) {
		VALOR_COSTO = vALOR_COSTO;
	}

	public double getVALOR_VENTA() {
		return VALOR_VENTA;
	}

	public void setVALOR_VENTA(double vALOR_VENTA) {
		VALOR_VENTA = vALOR_VENTA;
	}

	public int getPRODUCT_ID() {
		return PRODUCT_ID;
	}

	public void setPRODUCT_ID(int pRODUCT_ID) {
		PRODUCT_ID = pRODUCT_ID;
	}

	public String getCATEGORY_NAME() {
		return CATEGORY_NAME;
	}

	public void setCATEGORY_NAME(String cATEGORY_NAME) {
		CATEGORY_NAME = cATEGORY_NAME;
	}

	public String getFECHA_ACTUALIZACION() {
		return FECHA_ACTUALIZACION;
	}

	public void setFECHA_ACTUALIZACION(String fECHA_ACTUALIZACION) {
		FECHA_ACTUALIZACION = fECHA_ACTUALIZACION;
	}

	public String getFECHA_REPO() {
		return FECHA_REPO;
	}

	public void setFECHA_REPO(String fECHA_REPO) {
		FECHA_REPO = fECHA_REPO;
	}

	public Producto(String cATEGORY_ID, int iS_TEMPORAL, String cODIGO_BARRAS, String nOMBRE, int sTOCK_MINIMO,
			int sTOCK_ACTUAL, double pRECIO_COSTO, double pRECIO_VENTA, int uSER, int pRODUCT_ID) {
		super();
		CATEGORY_ID = cATEGORY_ID;
		IS_TEMPORAL = iS_TEMPORAL;
		CODIGO_BARRAS = cODIGO_BARRAS;
		NOMBRE = nOMBRE;
		STOCK_MINIMO = sTOCK_MINIMO;
		STOCK_ACTUAL = sTOCK_ACTUAL;
		VALOR_COSTO = pRECIO_COSTO;
		VALOR_VENTA = pRECIO_VENTA;
		USER = uSER;
		PRODUCT_ID = pRODUCT_ID;
	}

	public String getCATEGORY_ID() {
		return CATEGORY_ID;
	}

	public void setCATEGORY_ID(String cATEGORY_ID) {
		CATEGORY_ID = cATEGORY_ID;
	}

	public int getIS_TEMPORAL() {
		return IS_TEMPORAL;
	}

	public void setIS_TEMPORAL(int iS_TEMPORAL) {
		IS_TEMPORAL = iS_TEMPORAL;
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

	public int getSTOCK_MINIMO() {
		return STOCK_MINIMO;
	}

	public void setSTOCK_MINIMO(int sTOCK_MINIMO) {
		STOCK_MINIMO = sTOCK_MINIMO;
	}

	public int getSTOCK_ACTUAL() {
		return STOCK_ACTUAL;
	}

	public void setSTOCK_ACTUAL(int sTOCK_ACTUAL) {
		STOCK_ACTUAL = sTOCK_ACTUAL;
	}

	public double getPRECIO_COSTO() {
		return VALOR_COSTO;
	}

	public void setPRECIO_COSTO(double pRECIO_COSTO) {
		VALOR_COSTO = pRECIO_COSTO;
	}

	public double getPRECIO_VENTA() {
		return VALOR_VENTA;
	}

	public void setPRECIO_VENTA(double pRECIO_VENTA) {
		VALOR_VENTA = pRECIO_VENTA;
	}

	public int getUSER() {
		return USER;
	}

	public void setUSER(int uSER) {
		USER = uSER;
	}
	
	
}
