package pe.com.sigbah.common.bean;



/**
 * @className: PedidoCompraBean.java
 * @description: 
 * @date: 30 jul. 2017
 * @author: whr.
 */
public class PedidoCompraBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private Integer idPedidoCom;
	private String codAnio;
	private String codMes;
	private String codPedido;
	private String codPedidoConcate;
	private Integer fkIdeDdi;
	private String nombreDdi;
	private String fecPedido;
	private String dee;
	private String tipFenomeno;
	private String NomEstado;
	private String codEstado;
	private Integer iEstado;
	private String NomMes;
	private String numPedidoCompra;
	private String descripcion;
	private String codDdi;
	private String codPedidoPor;
	private String descPedidoPor;
	private String tipPedido;
	private String declaratoriaDee;
	private String control;
	/**
	 * 
	 */
	public PedidoCompraBean() {
		super();
	}

	/**
	 * @return the idPedidoCom
	 */
	public Integer getIdPedidoCom() {
		return idPedidoCom;
	}

	/**
	 * @param idPedidoCom the idPedidoCom to set
	 */
	public void setIdPedidoCom(Integer idPedidoCom) {
		this.idPedidoCom = idPedidoCom;
	}

	/**
	 * @return the codAnio
	 */
	public String getCodAnio() {
		return codAnio;
	}

	/**
	 * @param codAnio the codAnio to set
	 */
	public void setCodAnio(String codAnio) {
		this.codAnio = codAnio;
	}

	/**
	 * @return the codPedido
	 */
	public String getCodPedido() {
		return codPedido;
	}

	/**
	 * @param codPedido the codPedido to set
	 */
	public void setCodPedido(String codPedido) {
		this.codPedido = codPedido;
	}



	/**
	 * @return the fkIdeDdi
	 */
	public Integer getFkIdeDdi() {
		return fkIdeDdi;
	}

	/**
	 * @param fkIdeDdi the fkIdeDdi to set
	 */
	public void setFkIdeDdi(Integer fkIdeDdi) {
		this.fkIdeDdi = fkIdeDdi;
	}

	/**
	 * @return the nombreDdi
	 */
	public String getNombreDdi() {
		return nombreDdi;
	}

	/**
	 * @param nombreDdi the nombreDdi to set
	 */
	public void setNombreDdi(String nombreDdi) {
		this.nombreDdi = nombreDdi;
	}

	/**
	 * @return the fecPedido
	 */
	public String getFecPedido() {
		return fecPedido;
	}

	/**
	 * @param fecPedido the fecPedido to set
	 */
	public void setFecPedido(String fecPedido) {
		this.fecPedido = fecPedido;
	}

	

	/**
	 * @return the dee
	 */
	public String getDee() {
		return dee;
	}

	/**
	 * @param dee the dee to set
	 */
	public void setDee(String dee) {
		this.dee = dee;
	}

	/**
	 * @return the tipFenomeno
	 */
	public String getTipFenomeno() {
		return tipFenomeno;
	}

	/**
	 * @param tipFenomeno the tipFenomeno to set
	 */
	public void setTipFenomeno(String tipFenomeno) {
		this.tipFenomeno = tipFenomeno;
	}

	/**
	 * @return the nomEstado
	 */
	public String getNomEstado() {
		return NomEstado;
	}

	/**
	 * @param nomEstado the nomEstado to set
	 */
	public void setNomEstado(String nomEstado) {
		NomEstado = nomEstado;
	}

	/**
	 * @return the codEstado
	 */
	public String getCodEstado() {
		return codEstado;
	}

	/**
	 * @param codEstado the codEstado to set
	 */
	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
	}

	/**
	 * @return the codMes
	 */
	public String getCodMes() {
		return codMes;
	}

	/**
	 * @param codMes the codMes to set
	 */
	public void setCodMes(String codMes) {
		this.codMes = codMes;
	}

	/**
	 * @return the iEstado
	 */
	public Integer getiEstado() {
		return iEstado;
	}

	/**
	 * @param iEstado the iEstado to set
	 */
	public void setiEstado(Integer iEstado) {
		this.iEstado = iEstado;
	}

	/**
	 * @return the nomMes
	 */
	public String getNomMes() {
		return NomMes;
	}

	/**
	 * @param nomMes the nomMes to set
	 */
	public void setNomMes(String nomMes) {
		NomMes = nomMes;
	}

	/**
	 * @return the numPedidoCompra
	 */
	public String getNumPedidoCompra() {
		return numPedidoCompra;
	}

	/**
	 * @param numPedidoCompra the numPedidoCompra to set
	 */
	public void setNumPedidoCompra(String numPedidoCompra) {
		this.numPedidoCompra = numPedidoCompra;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the codDdi
	 */
	public String getCodDdi() {
		return codDdi;
	}

	/**
	 * @param codDdi the codDdi to set
	 */
	public void setCodDdi(String codDdi) {
		this.codDdi = codDdi;
	}

	/**
	 * @return the codPedidoConcate
	 */
	public String getCodPedidoConcate() {
		return codPedidoConcate;
	}

	/**
	 * @param codPedidoConcate the codPedidoConcate to set
	 */
	public void setCodPedidoConcate(String codPedidoConcate) {
		this.codPedidoConcate = codPedidoConcate;
	}

	/**
	 * @return the codPedidoPor
	 */
	public String getCodPedidoPor() {
		return codPedidoPor;
	}

	/**
	 * @param codPedidoPor the codPedidoPor to set
	 */
	public void setCodPedidoPor(String codPedidoPor) {
		this.codPedidoPor = codPedidoPor;
	}

	/**
	 * @return the descPedidoPor
	 */
	public String getDescPedidoPor() {
		return descPedidoPor;
	}

	/**
	 * @param descPedidoPor the descPedidoPor to set
	 */
	public void setDescPedidoPor(String descPedidoPor) {
		this.descPedidoPor = descPedidoPor;
	}

	/**
	 * @return the tipPedido
	 */
	public String getTipPedido() {
		return tipPedido;
	}

	/**
	 * @param tipPedido the tipPedido to set
	 */
	public void setTipPedido(String tipPedido) {
		this.tipPedido = tipPedido;
	}

	/**
	 * @return the declaratoriaDee
	 */
	public String getDeclaratoriaDee() {
		return declaratoriaDee;
	}

	/**
	 * @param declaratoriaDee the declaratoriaDee to set
	 */
	public void setDeclaratoriaDee(String declaratoriaDee) {
		this.declaratoriaDee = declaratoriaDee;
	}

	/**
	 * @return the control
	 */
	public String getControl() {
		return control;
	}

	/**
	 * @param control the control to set
	 */
	public void setControl(String control) {
		this.control = control;
	}

	
	
	
}
