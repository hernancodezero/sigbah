package pe.com.sigbah.common.bean;

/**
 * @className: ControlCalidadBean.java
 * @description: 
 * @date: 23 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ControlCalidadBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private Integer idControlCalidad;
	private String codigoAnio;
	private String codigoMes;
	private String nombreAlmacen;
	private Integer idDdi;
	private String codigoDdi;
	private String nombreDdi;
	private String fechaEmision;
	private String tipoControlCalidad;
	private String nombreEstado;	
	private String codigoAlmacen;
	private String nroControlCalidad;
	private Integer idAlmacen;
	private String nombreMes;
	private String tipo;
	private Integer idTipoControl;
	private Integer idEncargado;
	private Integer idInspector;
	private Integer idProveedor;
	private Integer idEmpresaTransporte;
	private Integer idChofer;
	private String conclusiones;
	private String recomendaciones;
	private Integer idAlmacenOrigen;
	private String nroPlaca;
	private String nroOrdenCompra;
	private Integer idEstado;
	private String flagTipoBien;
	private String provRep;
	private String concepto;
	private Integer idDonacion;
	private String nombreDonante;
	private String razonSocial;
	private String representante;
	
	
	/**
	 * 
	 */
	public ControlCalidadBean() {
		super();
	}
	
	/**
	 * @param idControlCalidad
	 */
	public ControlCalidadBean(Integer idControlCalidad) {
		super();
		this.idControlCalidad = idControlCalidad;
	}
	
	/**
	 * @param codigoDdi
	 */
	public ControlCalidadBean(String codigoDdi) {
		super();
		this.codigoDdi = codigoDdi;
	}

	/**
	 * @return the idControlCalidad
	 */
	public Integer getIdControlCalidad() {
		return idControlCalidad;
	}
	/**
	 * @param idControlCalidad the idControlCalidad to set
	 */
	public void setIdControlCalidad(Integer idControlCalidad) {
		this.idControlCalidad = idControlCalidad;
	}
	/**
	 * @return the codigoAnio
	 */
	public String getCodigoAnio() {
		return codigoAnio;
	}
	/**
	 * @param codigoAnio the codigoAnio to set
	 */
	public void setCodigoAnio(String codigoAnio) {
		this.codigoAnio = codigoAnio;
	}
	/**
	 * @return the codigoMes
	 */
	public String getCodigoMes() {
		return codigoMes;
	}
	/**
	 * @param codigoMes the codigoMes to set
	 */
	public void setCodigoMes(String codigoMes) {
		this.codigoMes = codigoMes;
	}
	/**
	 * @return the nombreAlmacen
	 */
	public String getNombreAlmacen() {
		return nombreAlmacen;
	}
	/**
	 * @param nombreAlmacen the nombreAlmacen to set
	 */
	public void setNombreAlmacen(String nombreAlmacen) {
		this.nombreAlmacen = nombreAlmacen;
	}
	/**
	 * @return the fechaEmision
	 */
	public String getFechaEmision() {
		return fechaEmision;
	}
	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	/**
	 * @return the tipoControlCalidad
	 */
	public String getTipoControlCalidad() {
		return tipoControlCalidad;
	}
	/**
	 * @param tipoControlCalidad the tipoControlCalidad to set
	 */
	public void setTipoControlCalidad(String tipoControlCalidad) {
		this.tipoControlCalidad = tipoControlCalidad;
	}
	/**
	 * @return the nombreEstado
	 */
	public String getNombreEstado() {
		return nombreEstado;
	}
	/**
	 * @param nombreEstado the nombreEstado to set
	 */
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	/**
	 * @return the codigoDdi
	 */
	public String getCodigoDdi() {
		return codigoDdi;
	}
	/**
	 * @param codigoDdi the codigoDdi to set
	 */
	public void setCodigoDdi(String codigoDdi) {
		this.codigoDdi = codigoDdi;
	}
	/**
	 * @return the codigoAlmacen
	 */
	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}
	/**
	 * @param codigoAlmacen the codigoAlmacen to set
	 */
	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}
	/**
	 * @return the idDdi
	 */
	public Integer getIdDdi() {
		return idDdi;
	}
	/**
	 * @param idDdi the idDdi to set
	 */
	public void setIdDdi(Integer idDdi) {
		this.idDdi = idDdi;
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
	 * @return the nroControlCalidad
	 */
	public String getNroControlCalidad() {
		return nroControlCalidad;
	}
	/**
	 * @param nroControlCalidad the nroControlCalidad to set
	 */
	public void setNroControlCalidad(String nroControlCalidad) {
		this.nroControlCalidad = nroControlCalidad;
	}
	/**
	 * @return the idAlmacen
	 */
	public Integer getIdAlmacen() {
		return idAlmacen;
	}
	/**
	 * @param idAlmacen the idAlmacen to set
	 */
	public void setIdAlmacen(Integer idAlmacen) {
		this.idAlmacen = idAlmacen;
	}
	/**
	 * @return the nombreMes
	 */
	public String getNombreMes() {
		return nombreMes;
	}
	/**
	 * @param nombreMes the nombreMes to set
	 */
	public void setNombreMes(String nombreMes) {
		this.nombreMes = nombreMes;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the idTipoControl
	 */
	public Integer getIdTipoControl() {
		return idTipoControl;
	}
	/**
	 * @param idTipoControl the idTipoControl to set
	 */
	public void setIdTipoControl(Integer idTipoControl) {
		this.idTipoControl = idTipoControl;
	}
	/**
	 * @return the idEncargado
	 */
	public Integer getIdEncargado() {
		return idEncargado;
	}
	/**
	 * @param idEncargado the idEncargado to set
	 */
	public void setIdEncargado(Integer idEncargado) {
		this.idEncargado = idEncargado;
	}
	/**
	 * @return the idInspector
	 */
	public Integer getIdInspector() {
		return idInspector;
	}
	/**
	 * @param idInspector the idInspector to set
	 */
	public void setIdInspector(Integer idInspector) {
		this.idInspector = idInspector;
	}
	/**
	 * @return the idProveedor
	 */
	public Integer getIdProveedor() {
		return idProveedor;
	}
	/**
	 * @param idProveedor the idProveedor to set
	 */
	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}
	/**
	 * @return the idEmpresaTransporte
	 */
	public Integer getIdEmpresaTransporte() {
		return idEmpresaTransporte;
	}
	/**
	 * @param idEmpresaTransporte the idEmpresaTransporte to set
	 */
	public void setIdEmpresaTransporte(Integer idEmpresaTransporte) {
		this.idEmpresaTransporte = idEmpresaTransporte;
	}
	/**
	 * @return the idChofer
	 */
	public Integer getIdChofer() {
		return idChofer;
	}
	/**
	 * @param idChofer the idChofer to set
	 */
	public void setIdChofer(Integer idChofer) {
		this.idChofer = idChofer;
	}
	/**
	 * @return the conclusiones
	 */
	public String getConclusiones() {
		return conclusiones;
	}
	/**
	 * @param conclusiones the conclusiones to set
	 */
	public void setConclusiones(String conclusiones) {
		this.conclusiones = conclusiones;
	}
	/**
	 * @return the recomendaciones
	 */
	public String getRecomendaciones() {
		return recomendaciones;
	}
	/**
	 * @param recomendaciones the recomendaciones to set
	 */
	public void setRecomendaciones(String recomendaciones) {
		this.recomendaciones = recomendaciones;
	}
	/**
	 * @return the nroPlaca
	 */
	public String getNroPlaca() {
		return nroPlaca;
	}
	/**
	 * @param nroPlaca the nroPlaca to set
	 */
	public void setNroPlaca(String nroPlaca) {
		this.nroPlaca = nroPlaca;
	}
	/**
	 * @return the nroOrdenCompra
	 */
	public String getNroOrdenCompra() {
		return nroOrdenCompra;
	}
	/**
	 * @param nroOrdenCompra the nroOrdenCompra to set
	 */
	public void setNroOrdenCompra(String nroOrdenCompra) {
		this.nroOrdenCompra = nroOrdenCompra;
	}
	/**
	 * @return the idEstado
	 */
	public Integer getIdEstado() {
		return idEstado;
	}
	/**
	 * @param idEstado the idEstado to set
	 */
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	/**
	 * @return the flagTipoBien
	 */
	public String getFlagTipoBien() {
		return flagTipoBien;
	}
	/**
	 * @param flagTipoBien the flagTipoBien to set
	 */
	public void setFlagTipoBien(String flagTipoBien) {
		this.flagTipoBien = flagTipoBien;
	}
	/**
	 * @return the idAlmacenOrigen
	 */
	public Integer getIdAlmacenOrigen() {
		return idAlmacenOrigen;
	}
	/**
	 * @param idAlmacenOrigen the idAlmacenOrigen to set
	 */
	public void setIdAlmacenOrigen(Integer idAlmacenOrigen) {
		this.idAlmacenOrigen = idAlmacenOrigen;
	}
	/**
	 * @return the provRep
	 */
	public String getProvRep() {
		return provRep;
	}
	/**
	 * @param provRep the provRep to set
	 */
	public void setProvRep(String provRep) {
		this.provRep = provRep;
	}

	/**
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * @return the idDonacion
	 */
	public Integer getIdDonacion() {
		return idDonacion;
	}

	/**
	 * @param idDonacion the idDonacion to set
	 */
	public void setIdDonacion(Integer idDonacion) {
		this.idDonacion = idDonacion;
	}

	/**
	 * @return the nombreDonante
	 */
	public String getNombreDonante() {
		return nombreDonante;
	}

	/**
	 * @param nombreDonante the nombreDonante to set
	 */
	public void setNombreDonante(String nombreDonante) {
		this.nombreDonante = nombreDonante;
	}

	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * @return the representante
	 */
	public String getRepresentante() {
		return representante;
	}

	/**
	 * @param representante the representante to set
	 */
	public void setRepresentante(String representante) {
		this.representante = representante;
	}

}
