package cliente.kiosco;

public class Stock {

	private String CODIGO_BARRAS;
	private String NOMBRE;
	private String RUBRO;
	private int IDRUBRO;
	private int STOCK_MINIMO;
	private int STOCK_ACTUAL;
	private double VALOR_COSTO;
	private double VALOR_VENTA;
	private String FECHA_REPO;
	private String HORA_REPO;
	private int USUARIO_ID;
	private int ESTADO;
	private int STOCK_FALTANTE;
	
	
	public Stock(String nOMBRE, String rUBRO) {
		super();
		NOMBRE = nOMBRE;
		RUBRO = rUBRO;
	}

	public Stock(String cODIGO_BARRAS, String nOMBRE, String rUBRO, int sTOCK_MINIMO, int sTOCK_ACTUAL,
			int sTOCK_FALTANTE) {
		super();
		CODIGO_BARRAS = cODIGO_BARRAS;
		NOMBRE = nOMBRE;
		RUBRO = rUBRO;
		STOCK_MINIMO = sTOCK_MINIMO;
		STOCK_ACTUAL = sTOCK_ACTUAL;
		STOCK_FALTANTE = sTOCK_FALTANTE;
	}

	public int getIDRUBRO() {
		return IDRUBRO;
	}

	public void setIDRUBRO(int iDRUBRO) {
		IDRUBRO = iDRUBRO;
	}

	public String getRUBRO() {
		return RUBRO;
	}

	public void setRUBRO(String rUBRO) {
		RUBRO = rUBRO;
	}

	public int getESTADO() {
		return ESTADO;
	}

	public void setESTADO(int eSTADO) {
		ESTADO = eSTADO;
	}

	public String getProducto_id() {
		return CODIGO_BARRAS;
	}

	public void setProducto_id(String producto_id) {
		this.CODIGO_BARRAS = producto_id;
	}

	public String getNombre() {
		return NOMBRE;
	}

	public void setNombre(String nombre) {
		this.NOMBRE = nombre;
	}

	public int getStock_minimo() {
		return STOCK_MINIMO;
	}

	public void setStock_minimo(int stock_minimo) {
		this.STOCK_MINIMO = stock_minimo;
	}

	public int getStock_actual() {
		return STOCK_ACTUAL;
	}

	public void setStock_actual(int stock_actual) {
		this.STOCK_ACTUAL = stock_actual;
	}

	public double getPrecio_lista() {
		return VALOR_COSTO;
	}

	public void setPrecio_lista(double precio_lista) {
		this.VALOR_COSTO = precio_lista;
	}

	public double getPrecio_venta() {
		return VALOR_VENTA;
	}

	public void setPrecio_venta(double precio_venta) {
		this.VALOR_VENTA = precio_venta;
	}

	public String getFecha() {
		return FECHA_REPO;
	}

	public void setFecha(String fecha) {
		this.FECHA_REPO = fecha;
	}

	public int getUsuario() {
		return USUARIO_ID;
	}

	public void setUsuario(int usuario) {
		this.USUARIO_ID = usuario;
	}

	
	public Stock(String producto_id, String nombre, int stock_minimo, int stock_actual,int sTOCK_FALTANTE, double precio_lista,
			double precio_venta, String fecha, int usuario) {
		super();
		this.CODIGO_BARRAS = producto_id;
		this.NOMBRE = nombre;
		this.STOCK_MINIMO = stock_minimo;
		this.STOCK_ACTUAL = stock_actual;
		this.VALOR_COSTO = precio_lista;
		this.VALOR_VENTA = precio_venta;
		this.FECHA_REPO = fecha;
		this.USUARIO_ID = usuario;
		this.STOCK_FALTANTE = sTOCK_FALTANTE;
	}
	
	public int getSTOCK_FALTANTE() {
		return STOCK_FALTANTE;
	}

	public void setSTOCK_FALTANTE(int sTOCK_FALTANTE) {
		STOCK_FALTANTE = sTOCK_FALTANTE;
	}

	@Override
    public String toString() {
        return "Codigo Producto: " + this.CODIGO_BARRAS + " Descripcion: " + this.NOMBRE;
    }

	public Stock(String cODIGO_BARRAS, int stock_minimo, int sTOCK_ACTUAL, int usr) {
		super();
		CODIGO_BARRAS = cODIGO_BARRAS;
		STOCK_ACTUAL = sTOCK_ACTUAL;
		USUARIO_ID = usr;
		STOCK_MINIMO =  stock_minimo;
	}

	public String getHORA_REPO() {
		return HORA_REPO;
	}

	public void setHORA_REPO(String hORA_REPO) {
		HORA_REPO = hORA_REPO;
	}

	public Stock(String cODIGO_BARRAS, String nOMBRE, String rUBRO, int sTOCK_ACTUAL, double vALOR_COSTO,
			double vALOR_VENTA, String fECHA_REPO, String hORA_REPO) {
		super();
		CODIGO_BARRAS = cODIGO_BARRAS;
		NOMBRE = nOMBRE;
		RUBRO = rUBRO;
		STOCK_ACTUAL = sTOCK_ACTUAL;
		VALOR_COSTO = vALOR_COSTO;
		VALOR_VENTA = vALOR_VENTA;
		FECHA_REPO = fECHA_REPO;
		HORA_REPO = hORA_REPO;
	}
	
}
