package cliente.kiosco;

public class Caja {
	String FECHA_LINEA_VENTA;
	String HORA;
	int PRODUCT_ID;
	String NOMBRE;
	int CANTIDAD_DISPONIBLE;
	int CANTIDAD_VENDIDA;
	double VENTA_PARCIAL;
	int TIPO_GASTO;
	String PROVEEDOR;
	String fecha;
	
	public Caja(String fECHA_LINEA_VENTA,String hORA, int pRODUCT_ID, String nOMBRE, int cANTIDAD_DISPONIBLE, int cANTIDAD_VENDIDA,
			double vENTA_PARCIAL) {
		super();
		FECHA_LINEA_VENTA = fECHA_LINEA_VENTA;
		HORA = hORA;
		PRODUCT_ID = pRODUCT_ID;
		NOMBRE = nOMBRE;
		CANTIDAD_DISPONIBLE = cANTIDAD_DISPONIBLE;
		CANTIDAD_VENDIDA = cANTIDAD_VENDIDA;
		VENTA_PARCIAL = vENTA_PARCIAL;
	}

	
	public double getVENTA_PARCIAL() {
		return VENTA_PARCIAL;
	}


	public void setVENTA_PARCIAL(double vENTA_PARCIAL) {
		VENTA_PARCIAL = vENTA_PARCIAL;
	}


	public int getTIPO_GASTO() {
		return TIPO_GASTO;
	}


	public void setTIPO_GASTO(int tIPO_GASTO) {
		TIPO_GASTO = tIPO_GASTO;
	}


	public String getPROVEEDOR() {
		return PROVEEDOR;
	}


	public void setPROVEEDOR(String pROVEEDOR) {
		PROVEEDOR = pROVEEDOR;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getHORA() {
		return HORA;
	}

	public void setHORA(String hORA) {
		HORA = hORA;
	}

	public String getFECHA_LINEA_VENTA() {
		return FECHA_LINEA_VENTA;
	}

	public void setFECHA_LINEA_VENTA(String fECHA_LINEA_VENTA) {
		FECHA_LINEA_VENTA = fECHA_LINEA_VENTA;
	}

	public int getPRODUCT_ID() {
		return PRODUCT_ID;
	}

	public void setPRODUCT_ID(int pRODUCT_ID) {
		PRODUCT_ID = pRODUCT_ID;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

	public int getCANTIDAD_DISPONIBLE() {
		return CANTIDAD_DISPONIBLE;
	}

	public void setCANTIDAD_DISPONIBLE(int cANTIDAD_DISPONIBLE) {
		CANTIDAD_DISPONIBLE = cANTIDAD_DISPONIBLE;
	}

	public int getCANTIDAD_VENDIDA() {
		return CANTIDAD_VENDIDA;
	}

	public void setCANTIDAD_VENDIDA(int cANTIDAD_VENDIDA) {
		CANTIDAD_VENDIDA = cANTIDAD_VENDIDA;
	}

	public double getGANANCIA() {
		return VENTA_PARCIAL;
	}

	public void setGANANCIA(double gANANCIA) {
		VENTA_PARCIAL = gANANCIA;
	}
	
	
	
}
