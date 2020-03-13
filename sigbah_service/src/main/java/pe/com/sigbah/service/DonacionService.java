package pe.com.sigbah.service;

import java.util.List;

import pe.com.sigbah.common.bean.CartillaInventarioBean;
import pe.com.sigbah.common.bean.CierreStockBean;
import pe.com.sigbah.common.bean.ControlCalidadBean;
import pe.com.sigbah.common.bean.DetalleActaEntregaBean;
import pe.com.sigbah.common.bean.DetalleGuiaRemisionBean;
import pe.com.sigbah.common.bean.DetalleManifiestoCargaBean;
import pe.com.sigbah.common.bean.DocumentoDonacionBean;
import pe.com.sigbah.common.bean.DocumentoDonacionIngresoBean;
import pe.com.sigbah.common.bean.DocumentoIngresoBean;
import pe.com.sigbah.common.bean.DocumentoSalidaBean;
import pe.com.sigbah.common.bean.DonacionesBean;
import pe.com.sigbah.common.bean.DonacionesIngresoBean;
import pe.com.sigbah.common.bean.DonacionesSalidaBean;
import pe.com.sigbah.common.bean.EstadoCartillaInventarioBean;
import pe.com.sigbah.common.bean.GuiaRemisionBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.OrdenIngresoBean;
import pe.com.sigbah.common.bean.OrdenSalidaBean;
import pe.com.sigbah.common.bean.ProductoCartillaInventarioBean;
import pe.com.sigbah.common.bean.ProductoDonacionBean;
import pe.com.sigbah.common.bean.ProductoDonacionIngresoBean;
import pe.com.sigbah.common.bean.ProductoDonacionSalidaBean;
import pe.com.sigbah.common.bean.ProductoIngresoBean;
import pe.com.sigbah.common.bean.ProductoSalidaBean;
import pe.com.sigbah.common.bean.RegionDonacionBean;
import pe.com.sigbah.common.bean.StockAlmacenBean;
import pe.com.sigbah.common.bean.StockAlmacenProductoBean;
import pe.com.sigbah.common.bean.StockAlmacenProductoLoteBean;
import pe.com.sigbah.common.bean.StockConsultaBean;
/**
 * @className: DonacionService.java
 * @description: Clase que contiene el consumo de los procedimientos del package BAH_PKG_DONACION.
 * @date: 21 de jun. de 2017
 * @author: SUMERIO.
 */
public interface DonacionService {

	/**
	 * @param donacionesBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<DonacionesBean> listarDonaciones(DonacionesBean donacionesBean) throws Exception;
	
	
	/**
	 * @param controlCalidadBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract DonacionesBean actualizarDonaciones(DonacionesBean controlCalidadBean) throws Exception;
	/**
	 * @param donacionesBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract DonacionesBean insertarDonaciones(DonacionesBean donacionesBean) throws Exception;
	
	
	///////////////////////////////
	
	/**
	 * @param donacionesBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<DonacionesBean> listarSalidaDonaciones(DonacionesBean donacionesBean) throws Exception;
	
	/**
	 * @param donacionesBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract DonacionesBean obtenerCodigoDonativo(DonacionesBean donacionesBean) throws Exception;
	
	/**
	 * @param donacioesnBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract DonacionesBean insertarRegistroDonacion(DonacionesBean donacionesBean) throws Exception;

	/**
	 * @param donacionesBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract DonacionesBean actualizarRegistroDonacion(DonacionesBean donacionesBean) throws Exception;
	
	public abstract List<ItemBean> listarDonadores(DonacionesBean donacionesBean) throws Exception;
	
	public abstract DonacionesBean obtenerDonacionXIdDonacion(Integer idDonacion) throws Exception;
	
	public abstract List<ItemBean> listarEstadoDonacionUsuario(ItemBean itemBean) throws Exception;
	
	public abstract List<ItemBean> mostrarEstadoDonacionUsuario(ItemBean itemBean) throws Exception;
	
	public abstract ProductoDonacionBean insertarProductoDonacion(ProductoDonacionBean productoDonacionBean) throws Exception;
	
	public abstract ProductoDonacionBean actualizarProductoDonacion(ProductoDonacionBean productoDonacionBean) throws Exception;
	
	public abstract List<ProductoDonacionBean> listarProductoDonacion(ProductoDonacionBean productoDonacionBean) throws Exception;
	
	public abstract ProductoDonacionBean eliminarProductoDonacion(ProductoDonacionBean productoDonacionBean) throws Exception;
	
	public abstract DocumentoDonacionBean insertarDocumentoDonacion(DocumentoDonacionBean documentoDonacionBean) throws Exception;
	
	public abstract DocumentoDonacionBean actualizarDocumentoDonacion(DocumentoDonacionBean documentoDonacionBean) throws Exception;
	
	public abstract List<DocumentoDonacionBean> listarDocumentoDonacion(DocumentoDonacionBean documentoDonacionBean) throws Exception;
	
	public abstract DocumentoDonacionBean eliminarDocumentoDonacion(DocumentoDonacionBean documentoDonacionBean) throws Exception;
	
	public abstract List<RegionDonacionBean> listarRegionesXDonacion(RegionDonacionBean regionDonacionBean) throws Exception;
	
	public abstract RegionDonacionBean insertarRegionDonacion(RegionDonacionBean regionDonacionBean) throws Exception;
	
	public abstract RegionDonacionBean eliminarRegionDonacion(RegionDonacionBean regionDonacionBean) throws Exception;
	
	public abstract List<DonacionesBean> listarReporteDonacion(Integer idDonacion) throws Exception;
	
	public abstract DonacionesBean actualizarEstadoDonacion(DonacionesBean donacionesBean) throws Exception;
	
	////////////////////////////////////////////////
	
	public abstract List<DonacionesIngresoBean> listarIngresoDonaciones(DonacionesIngresoBean donacionesIngresoBean) throws Exception;


	/**
	 * @param donacionesIngresoBean
	 * @return
	 * @throws Exception
	 */
	public abstract DonacionesIngresoBean obtenerCorrelativoOrdenIngreso(DonacionesIngresoBean donacionesIngresoBean) throws Exception;


	/**
	 * @param itemBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<DonacionesBean> listarCodigoDonacion(ItemBean itemBean) throws Exception;


	/**
	 * @param itemBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ControlCalidadBean> listarControCalidad(ItemBean itemBean) throws Exception;


	/**
	 * @param donacionesIngresoBean
	 * @return
	 * @throws Exception
	 */
	public abstract DonacionesIngresoBean insertarRegistroDonacionIngreso(DonacionesIngresoBean donacionesIngresoBean) throws Exception;


	/**
	 * @param donacionIngreso
	 * @return
	 * @throws Exception
	 */
	public abstract DonacionesIngresoBean obtenerDonacionIngresoXIdIngreso(Integer donacionIngreso) throws Exception;


	/**
	 * @param productoDonacionIngresoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoDonacionIngresoBean> listarProductoDonacionIngreso(ProductoDonacionIngresoBean productoDonacionIngresoBean) throws Exception;


	/**
	 * @param productoDonacionIngresoBean
	 * @return
	 * @throws Exception
	 */
	public abstract ProductoDonacionIngresoBean insertarProductoDonacionIngreso(ProductoDonacionIngresoBean productoDonacionIngresoBean) throws Exception;


	/**
	 * @param productoDonacionIngresoBean
	 * @return
	 * @throws Exception
	 */
	public abstract ProductoDonacionIngresoBean eliminarProductoDonacionIngreso(ProductoDonacionIngresoBean productoDonacionIngresoBean)throws Exception;

	
	/**
	 * @param documentoIngresoBean
	 * @return
	 * @throws Exception
	 */
	public abstract DocumentoIngresoBean insertarDocumentoDonacionIngreso(DocumentoIngresoBean documentoIngresoBean) throws Exception;
	

	/**
	 * @param documentoDonacionIngresoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<DocumentoIngresoBean> listarDocumentoDonacionIngreso(DocumentoIngresoBean documentoDonacionIngresoBean) throws Exception;


	/**
	 * @param documentoIngresoBean
	 * @return
	 * @throws Exception
	 */
	public abstract DocumentoIngresoBean eliminarDocumentoIngresoDonacion(DocumentoIngresoBean documentoIngresoBean) throws Exception;


	/**
	 * @param itemBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoDonacionBean> listarProductosDonacion(DonacionesIngresoBean itemBean) throws Exception;


	/**
	 * @param donacionesIngresoBean
	 * @return
	 * @throws Exception
	 */
	public abstract DonacionesIngresoBean actualizarRegistroDonacionIngreso(DonacionesIngresoBean donacionesIngresoBean) throws Exception;


	/**
	 * @param documentoIngresoBean
	 * @return
	 * @throws Exception
	 */
	public abstract DocumentoIngresoBean actualizarDocumentoDonacionIngreso(DocumentoIngresoBean documentoIngresoBean) throws Exception;


	/**
	 * @param productoDonacionIngresoBean
	 * @return
	 * @throws Exception
	 */
	public abstract ProductoDonacionIngresoBean actualizarProductoDonacionIngreso(ProductoDonacionIngresoBean productoDonacionIngresoBean) throws Exception;


	/**
	 * @param itemBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarSalida(ItemBean itemBean) throws Exception;


	/**
	 * @param donacionesSalidaBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<DonacionesSalidaBean> listarSalidaDonaciones(DonacionesSalidaBean donacionesSalidaBean) throws Exception;


	/**
	 * @param donacionesSalidaBean
	 * @return
	 * @throws Exception
	 */
	public abstract DonacionesSalidaBean obtenerCorrelativoOrdenSalida(DonacionesSalidaBean donacionesSalidaBean) throws Exception;


	/**
	 * @param donacionesSalidaBean
	 * @return
	 * @throws Exception
	 */
	public abstract DonacionesSalidaBean insertarRegistroDonacionSalida(DonacionesSalidaBean donacionesSalidaBean) throws Exception;


	/**
	 * @param idSalida
	 * @param codAnio
	 * @return
	 * @throws Exception
	 */
	public abstract DonacionesSalidaBean obtenerDonacionSalidaXIdSalida(Integer idSalida) throws Exception;


	/**
	 * @param itemBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoDonacionSalidaBean> listarProductoDonacionSalida(ProductoDonacionSalidaBean itemBean) throws Exception;


	/**
	 * @param productoDonacionSalidaBean
	 * @return
	 * @throws Exception
	 */
	public abstract ProductoDonacionSalidaBean insertarProductoDonacionSalida(ProductoDonacionSalidaBean productoDonacionSalidaBean) throws Exception;


	/**
	 * @param productoDonacionSalidaBean
	 * @return
	 * @throws Exception
	 */
	public abstract ProductoDonacionSalidaBean actualizarProductoDonacionSalida(ProductoDonacionSalidaBean productoDonacionSalidaBean) throws Exception;


	/**
	 * @param productoDonacionSalidaBean
	 * @return
	 * @throws Exception
	 */
	public abstract ProductoDonacionSalidaBean eliminarProductoDonacionSalida(ProductoDonacionSalidaBean productoDonacionSalidaBean) throws Exception;


	/**
	 * @param itemBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoDonacionSalidaBean> listarProductosDonacionSalida(ProductoDonacionSalidaBean itemBean) throws Exception;


	/**
	 * @param documentoSalidaBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<DocumentoSalidaBean> listarDocumentoDonacionSalida(DocumentoSalidaBean documentoSalidaBean) throws Exception;


	/**
	 * @param documentoSalidaBean
	 * @return
	 * @throws Exception
	 */
	public abstract DocumentoSalidaBean insertarDocumentoDonacionSalida(DocumentoSalidaBean documentoSalidaBean) throws Exception;


	/**
	 * @param documentoSalidaBean
	 * @return
	 * @throws Exception
	 */
	public abstract DocumentoSalidaBean eliminarDocumentoSalidaDonacion(DocumentoSalidaBean documentoSalidaBean) throws Exception;


	/**
	 * @param documentoSalidaBean
	 * @return
	 * @throws Exception
	 */
	public abstract DocumentoSalidaBean actualizarDocumentoDonacionSalida(DocumentoSalidaBean documentoSalidaBean) throws Exception;


	/**
	 * @param donacionesSalidaBean
	 * @return
	 * @throws Exception
	 */
	public abstract DonacionesSalidaBean actualizarRegistroDonacionSalida(DonacionesSalidaBean donacionesSalidaBean) throws Exception;

	
	

	/**
	 * @param guiaRemisionBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<GuiaRemisionBean> listarGuiaRemision(GuiaRemisionBean guiaRemisionBean) throws Exception;


	/**
	 * @param idGuiaRemision
	 * @return
	 * @throws Exception
	 */
	public abstract GuiaRemisionBean obtenerRegistroGuiaRemision(Integer idGuiaRemision) throws Exception;


	/**
	 * @param guiaRemisionBean
	 * @return
	 * @throws Exception
	 */
	public abstract GuiaRemisionBean actualizarGuiaRemision(GuiaRemisionBean guiaRemisionBean) throws Exception;


	/**
	 * @param stockAlmacenBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<StockAlmacenBean> listarStockAlmacen(StockAlmacenBean stockAlmacenBean) throws Exception;


	/**
	 * @param stockAlmacenBean
	 * @return
	 * @throws Exception
	 */
	public abstract StockAlmacenBean obtenerRegistroStockAlmacen(StockAlmacenBean stockAlmacenBean) throws Exception;


	/**
	 * @param stockAlmacenBean
	 * @return
	 * @throws Exception
	 */
	public abstract StockAlmacenBean actualizarStockAlmacen(StockAlmacenBean stockAlmacenBean) throws Exception;


	/**
	 * @param cartillaInventarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<CartillaInventarioBean> listarCartillaInventario(CartillaInventarioBean cartillaInventarioBean) throws Exception;


	/**
	 * @param idCartilla
	 * @return
	 * @throws Exception
	 */
	public abstract CartillaInventarioBean obtenerRegistroCartillaInventario(Integer idCartilla) throws Exception;


	/**
	 * @param productoCartillaInventarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoCartillaInventarioBean> listarProductoCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception;


	/**
	 * @param estadoCartillaInventarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<EstadoCartillaInventarioBean> listarEstadoCartillaInventario(EstadoCartillaInventarioBean estadoCartillaInventarioBean) throws Exception;


	/**
	 * @param cartillaInventarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract CartillaInventarioBean grabarCartillaInventario(CartillaInventarioBean cartillaInventarioBean) throws Exception;


	/**
	 * @param estadoCartillaInventarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract EstadoCartillaInventarioBean grabarEstadoCartillaInventario(EstadoCartillaInventarioBean estadoCartillaInventarioBean) throws Exception;


	/**
	 * @param productoCartillaInventarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract ProductoCartillaInventarioBean grabarProductoCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception;


	/**
	 * @param productoCartillaInventarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract ProductoCartillaInventarioBean procesarProductosCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception;


	/**
	 * @param stockAlmacenProductoLoteBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<StockAlmacenProductoLoteBean> listarStockAlmacenProductoLote(StockAlmacenProductoLoteBean stockAlmacenProductoLoteBean) throws Exception;


	/**
	 * @param productoCartillaInventarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract ProductoCartillaInventarioBean eliminarProductoCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception;


	/**
	 * @param productoCartillaInventarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract ProductoCartillaInventarioBean actualizarAjusteProductoCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception;


	/**
	 * @param cierreStockBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<CierreStockBean> listarCierreStock(CierreStockBean cierreStockBean) throws Exception;


	/**
	 * @param cierreStockBean
	 * @return
	 * @throws Exception
	 */
	public abstract CierreStockBean obtenerRegistroCierreStock(CierreStockBean cierreStockBean) throws Exception;


	/**
	 * @param cierreStockBean
	 * @return
	 * @throws Exception
	 */
	public abstract CierreStockBean grabarCierreStock(CierreStockBean cierreStockBean) throws Exception;


	/**
	 * @param idGuiaRemision
	 * @param tipoOrigen
	 * @return
	 * @throws Exception
	 */
	public abstract List<DetalleGuiaRemisionBean> listarDetalleGuiaRemision(Integer idGuiaRemision, String tipoOrigen) throws Exception;


	/**
	 * @param idDonacion
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoDonacionBean> listarReporteDonacionProductos(Integer idDonacion) throws Exception;


	/**
	 * @param idIngreso
	 * @return
	 * @throws Exception
	 */
	public abstract List<DonacionesIngresoBean> listarReporteDonacionIngreso(Integer idIngreso)	throws Exception;


	/**
	 * @param idIngreso
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoDonacionIngresoBean> listarProductosReporteDonacionIngreso(Integer idIngreso) throws Exception;


	/**
	 * @param idSalida
	 * @return
	 * @throws Exception
	 */
	public abstract List<DonacionesSalidaBean> listarReporteDonacionSalida(Integer idSalida) throws Exception;


	/**
	 * @param idIngreso
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoDonacionSalidaBean> listarProductosReporteDonacionSalida(Integer idIngreso) throws Exception;


	/**
	 * @param idIngreso
	 * @param idDdi
	 * @return
	 * @throws Exception
	 */
	public abstract List<DonacionesIngresoBean> listarReporteDonacionIngresoNacional(Integer idIngreso, Integer idDdi) throws Exception;


	/**
	 * @param idIngreso
	 * @param idDdi
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoDonacionIngresoBean> listarProductoReporteDonacionIngresoNacional(Integer idIngreso, Integer idDdi) throws Exception;


	/**
	 * @param idIngreso
	 * @param idDdi
	 * @return
	 * @throws Exception
	 */
	public abstract List<DonacionesIngresoBean> listarReporteDonacionIngresoInternacional(Integer idIngreso, Integer idDdi) throws Exception;


	/**
	 * @param idIngreso
	 * @param idDdi
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoDonacionIngresoBean> listarProductoReporteDonacionIngresoInternacional(Integer idIngreso,Integer idDdi) throws Exception;


	/**
	 * @param idDonacion
	 * @return
	 * @throws Exception
	 */
	public abstract List<RegionDonacionBean> listarReporteDonacionRegiones(Integer idDonacion) throws Exception;


	/**
	 * @param idIngreso
	 * @return
	 * @throws Exception
	 */
	public abstract List<DocumentoDonacionIngresoBean> listarDocumentosReporteDonacionIngreso(Integer idIngreso) throws Exception;


	/**
	 * @param idSalida
	 * @return
	 * @throws Exception
	 */
	public abstract List<DocumentoSalidaBean> listarDocumentosReporteDonacionSalida(Integer idSalida) throws Exception;


	/**
	 * @param cartillaInventarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract CartillaInventarioBean obtenerCorrelativoCartillaInventario(CartillaInventarioBean cartillaInventarioBean) throws Exception;


	/**
	 * @param itemBean
	 * @return
	 * @throws Exception
	 */
	public abstract ItemBean validaStockProducto(ItemBean itemBean) throws Exception;


	/**
	 * @param stockConsultaBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<StockConsultaBean> listarConsultaStock(StockConsultaBean stockConsultaBean) throws Exception;


	/**
	 * @param stockConsultaBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<StockConsultaBean> listarReporteStockAlimentos(StockConsultaBean stockConsultaBean) throws Exception;


	/**
	 * @param stockConsultaBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<StockConsultaBean> listarReporteStockBNA(StockConsultaBean stockConsultaBean) throws Exception;


	/**
	 * @param stockConsultaBean
	 * @return
	 * @throws Exception
	 */
	public abstract StockConsultaBean listarReporteStockTitulo(StockConsultaBean stockConsultaBean) throws Exception;


	/**
	 * @param idGuiaRemision
	 * @param tipoOrigen
	 * @return
	 * @throws Exception
	 */
	public abstract List<DetalleGuiaRemisionBean> listarDetalleGuiaRemisionCabecera(Integer idGuiaRemision, String tipoOrigen)
			throws Exception;


	/**
	 * @param idGuiaRemision
	 * @param tipoOrigen
	 * @return
	 * @throws Exception
	 */
	public abstract List<DetalleGuiaRemisionBean> listarDetalleGuiaRemisionDetalle(Integer idGuiaRemision, String tipoOrigen)
			throws Exception;


	/**
	 * @param donacionesBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<DonacionesBean> listarHistorialDonaciones(DonacionesBean donacionesBean) throws Exception;


	/**
	 * @param idDonacion
	 * @return
	 * @throws Exception
	 */
	public abstract List<DonacionesBean> listarReporteHistorialGeneral(Integer idDonacion) throws Exception;


	/**
	 * @param productoCartillaInventarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract ProductoCartillaInventarioBean procesarAjusteCartilla(ProductoCartillaInventarioBean productoCartillaInventarioBean)
			throws Exception;


	/**
	 * @param idSalida
	 * @return
	 * @throws Exception
	 */
	public abstract ItemBean obtenerTransporte(ItemBean itemBean) throws Exception;


	/**
	 * @param productoCartillaInventarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoCartillaInventarioBean> listarReporteCartillaInventario(
			ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception;


	/**
	 * @param stockConsultaBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<StockConsultaBean> listarConsultaProductos(StockConsultaBean stockConsultaBean) throws Exception;


	/**
	 * @param donacionesBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<DonacionesBean> listarConsultaResoluciones(DonacionesBean donacionesBean) throws Exception;


	/**
	 * @param idDonacion
	 * @return
	 * @throws Exception
	 */
	public abstract List<DonacionesBean> listarReporteHistorialDonante(Integer idDonacion) throws Exception;


	/**
	 * @param idDonacion
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoDonacionBean> listarReporteHistorialProducto(Integer idDonacion) throws Exception;


	/**
	 * @param idDonacion
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoDonacionBean> listarReporteHistorialProductoIngSal(Integer idDonacion) throws Exception;


	/**
	 * @param idDonacion
	 * @return
	 * @throws Exception
	 */
	public abstract List<DocumentoDonacionBean> listarReporteHistorialDocumento(Integer idDonacion) throws Exception;


	/**
	 * @param idDonacion
	 * @return
	 * @throws Exception
	 */
	public abstract ItemBean listarReporteHistorialCabecera(Integer idDonacion) throws Exception;


	/**
	 * @param idDonacion
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoDonacionBean> listarReporteHistorialProductoIndeci(Integer idDonacion) throws Exception;


	/**
	 * @param idDonacion
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoDonacionBean> listarReporteHistorialProductoEntidades(Integer idDonacion) throws Exception;


	/**
	 * @param idGuiaRemision
	 * @param tipoOrigen
	 * @return
	 * @throws Exception
	 */
	public abstract DetalleManifiestoCargaBean listarDetalleManifiestoCargaDon(Integer idGuiaRemision, String tipoOrigen)
			throws Exception;


	/**
	 * @param productoDonacionBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoDonacionBean> listarConsultaKardex(ProductoDonacionBean productoDonacionBean) throws Exception;


	/**
	 * @param itemBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoDonacionSalidaBean> listarProductosDonacionSalidaEdi(ProductoDonacionSalidaBean itemBean)
			throws Exception;


	/**
	 * @param stockAlmacenProductoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<StockAlmacenProductoBean> listarStockAlmacenProductoDon(StockAlmacenProductoBean stockAlmacenProductoBean)
			throws Exception;


	/**
	 * @param idGuiaRemision
	 * @param tipoOrigen
	 * @return
	 * @throws Exception
	 */
	public abstract DetalleActaEntregaBean reporteActaEntrega(Integer idGuiaRemision, String tipoOrigen) throws Exception;


	/**
	 * @param itemBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoDonacionBean> buscarCantidadProducto(DonacionesIngresoBean itemBean) throws Exception;


	/**
	 * @param productoSalidaBean
	 * @return
	 * @throws Exception
	 */
	public abstract  List<ProductoSalidaBean> listarReporteDetalleOrdenSalida(ProductoSalidaBean productoSalidaBean) throws Exception;


	/**
	 * @param ordenSalidaBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<OrdenSalidaBean> listarReporteOrdenSalida(OrdenSalidaBean ordenSalidaBean) throws Exception;


	/**
	 * @param ordenIngresoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<OrdenIngresoBean> listarReporteSigaE(OrdenIngresoBean ordenIngresoBean) throws Exception;


	/**
	 * @param ordenIngresoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<OrdenIngresoBean> listarReporteSigaS(OrdenIngresoBean ordenIngresoBean) throws Exception;


	/**
	 * @param ordenIngresoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<OrdenIngresoBean> listarReporteOrdenIngreso(OrdenIngresoBean ordenIngresoBean) throws Exception;


	/**
	 * @param productoIngresoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoIngresoBean> listarReporteDetalleOrdenIngreso(ProductoIngresoBean productoIngresoBean)
			throws Exception;


	/**
	 * @param guiaRemisionBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<GuiaRemisionBean> listarReporteGuiaRemision(GuiaRemisionBean guiaRemisionBean) throws Exception;


	/**
	 * @param detalleGuiaRemisionBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<DetalleGuiaRemisionBean> listarReporteDetalleGuiaRemision(DetalleGuiaRemisionBean detalleGuiaRemisionBean)
			throws Exception;



	
}
