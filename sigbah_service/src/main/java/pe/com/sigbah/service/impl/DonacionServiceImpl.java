package pe.com.sigbah.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import pe.com.sigbah.dao.DonacionDao;
import pe.com.sigbah.service.DonacionService;

/**
 * @className: DonacionServiceImpl.java
 * @description: Clase que contiene el consumo de los procedimientos del package BAH_PKG_DONACION.
 * @date: 21 de jun. de 2017
 * @author: SUMERIO.
 */
@Service
public class DonacionServiceImpl implements DonacionService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private DonacionDao donacionDao;
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarControlCalidad(pe.com.sigbah.common.bean.ControlCalidadBean)
	 */
	@Override
	public List<DonacionesBean> listarDonaciones(DonacionesBean donacionesBean) throws Exception {
		return donacionDao.listarDonaciones(donacionesBean);
	}
	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#actualizarRegistroControlCalidad(pe.com.sigbah.common.bean.ControlCalidadBean)
	 */
	@Override
	public DonacionesBean actualizarDonaciones(DonacionesBean donacionesBean) throws Exception {
		return donacionDao.actualizarDonaciones(donacionesBean);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#actualizarRegistroControlCalidad(pe.com.sigbah.common.bean.DonacionesBean)
	 */
	@Override
	public DonacionesBean insertarDonaciones(DonacionesBean donacionesBean) throws Exception {
		return donacionDao.insertarDonaciones(donacionesBean);
	}

	//////////////////////////////////////
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarControlCalidad(pe.com.sigbah.common.bean.ControlCalidadBean)
	 */
	@Override
	public List<DonacionesBean> listarSalidaDonaciones(DonacionesBean donacionesBean) throws Exception {
		return donacionDao.listarSalidaDonaciones(donacionesBean);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#obtenerCorrelativoControlCalidad(pe.com.sigbah.common.bean.ControlCalidadBean)
	 */
	@Override
	public DonacionesBean obtenerCodigoDonativo(DonacionesBean donacionesBean) throws Exception {
		return donacionDao.obtenerCodigoDonativo(donacionesBean);
	}
	
	@Override
	public DonacionesBean insertarRegistroDonacion(DonacionesBean donacionesBean) throws Exception {
		return donacionDao.insertarRegistroDonacion(donacionesBean);
	}

	@Override
	public DonacionesBean actualizarRegistroDonacion(DonacionesBean donacionesBean) throws Exception {
		return donacionDao.actualizarRegistroDonacion(donacionesBean);
	}
	
	@Override
	public List<ItemBean> listarDonadores(DonacionesBean donacionesBean) throws Exception {
		return donacionDao.listarDonadores(donacionesBean);
	}
	
	@Override
	public DonacionesBean obtenerDonacionXIdDonacion(Integer idDonacion) throws Exception {
		return donacionDao.obtenerDonacionXIdDonacion(idDonacion);
	}
	
	@Override
	public List<ItemBean> listarEstadoDonacionUsuario(ItemBean itemBean) throws Exception {
		return donacionDao.listarEstadoDonacionUsuario(itemBean);
	}
	
	@Override
	public List<ItemBean> mostrarEstadoDonacionUsuario(ItemBean itemBean) throws Exception {
		return donacionDao.mostrarEstadoDonacionUsuario(itemBean);
	}
	
	@Override
	public ProductoDonacionBean insertarProductoDonacion(ProductoDonacionBean productoDonacionBean) throws Exception {
		return donacionDao.insertarProductoDonacion(productoDonacionBean);
	}
	
	@Override
	public ProductoDonacionBean actualizarProductoDonacion(ProductoDonacionBean productoDonacionBean) throws Exception {
		return donacionDao.actualizarProductoDonacion(productoDonacionBean);
	}
	
	@Override
	public List<ProductoDonacionBean> listarProductoDonacion(ProductoDonacionBean productoDonacionBean) throws Exception {
		return donacionDao.listarProductoDonacion(productoDonacionBean);
	}
	
	@Override
	public ProductoDonacionBean eliminarProductoDonacion(ProductoDonacionBean productoDonacionBean) throws Exception {
		return donacionDao.eliminarProductoDonacion(productoDonacionBean);
	}
	
	@Override
	public DocumentoDonacionBean insertarDocumentoDonacion(DocumentoDonacionBean documentoDonacionBean) throws Exception {
		return donacionDao.insertarDocumentoDonacion(documentoDonacionBean);
	}
	
	@Override
	public DocumentoDonacionBean actualizarDocumentoDonacion(DocumentoDonacionBean documentoDonacionBean) throws Exception {
		return donacionDao.actualizarDocumentoDonacion(documentoDonacionBean);
	}
	
	@Override
	public List<DocumentoDonacionBean> listarDocumentoDonacion(DocumentoDonacionBean documentoDonacionBean) throws Exception {
		return donacionDao.listarDocumentoDonacion(documentoDonacionBean);
	}
	
	@Override
	public DocumentoDonacionBean eliminarDocumentoDonacion(DocumentoDonacionBean documentoDonacionBean) throws Exception {
		return donacionDao.eliminarDocumentoDonacion(documentoDonacionBean);
	}
	
	@Override
	public List<RegionDonacionBean> listarRegionesXDonacion(RegionDonacionBean regionDonacionBean) throws Exception {
		return donacionDao.listarRegionesXDonacion(regionDonacionBean);
	}

	@Override
	public RegionDonacionBean insertarRegionDonacion(RegionDonacionBean regionDonacionBean) throws Exception {
		return donacionDao.insertarRegionDonacion(regionDonacionBean);
	}
	
	@Override
	public RegionDonacionBean eliminarRegionDonacion(RegionDonacionBean regionDonacionBean) throws Exception {
		return donacionDao.eliminarRegionDonacion(regionDonacionBean);
	}
	
	@Override
	public List<DonacionesBean> listarReporteDonacion(Integer idDonacion) throws Exception {
		return donacionDao.listarReporteDonacion(idDonacion);
	}
	
	@Override
	public DonacionesBean actualizarEstadoDonacion(DonacionesBean donacionesBean) throws Exception {
		return donacionDao.actualizarEstadoDonacion(donacionesBean);
	}
	///////INGRESO
	@Override
	public List<DonacionesIngresoBean> listarIngresoDonaciones(DonacionesIngresoBean donacionesIngresoBean) throws Exception {
		return donacionDao.listarIngresoDonaciones(donacionesIngresoBean);
	}
	
	@Override
	public DonacionesIngresoBean obtenerCorrelativoOrdenIngreso(DonacionesIngresoBean donacionesIngresoBean) throws Exception {
		return donacionDao.obtenerCorrelativoOrdenIngreso(donacionesIngresoBean);
	}
	
	@Override
	public List<DonacionesBean> listarCodigoDonacion(ItemBean itemBean) throws Exception {
		return donacionDao.listarCodigoDonacion(itemBean);
	}
	
	@Override
	public List<ControlCalidadBean> listarControCalidad(ItemBean itemBean) throws Exception {
		return donacionDao.listarControCalidad(itemBean);
	}
	
	@Override
	public DonacionesIngresoBean insertarRegistroDonacionIngreso(DonacionesIngresoBean donacionesIngresoBean) throws Exception {
		return donacionDao.insertarRegistroDonacionIngreso(donacionesIngresoBean);
	}
	
	@Override
	public DonacionesIngresoBean obtenerDonacionIngresoXIdIngreso(Integer donacionIngreso) throws Exception {
		return donacionDao.obtenerDonacionIngresoXIdIngreso(donacionIngreso);
	}
	
	@Override
	public List<ProductoDonacionIngresoBean> listarProductoDonacionIngreso(ProductoDonacionIngresoBean productoDonacionIngresoBean) throws Exception {
		return donacionDao.listarProductoDonacionIngreso(productoDonacionIngresoBean);
	}
	
	@Override
	public ProductoDonacionIngresoBean insertarProductoDonacionIngreso(ProductoDonacionIngresoBean productoDonacionIngresoBean) throws Exception {
		return donacionDao.insertarProductoDonacionIngreso(productoDonacionIngresoBean);
	}
	
	@Override
	public ProductoDonacionIngresoBean actualizarProductoDonacionIngreso(ProductoDonacionIngresoBean productoDonacionIngresoBean) throws Exception {
		return donacionDao.actualizarProductoDonacionIngreso(productoDonacionIngresoBean);
	}
	
	@Override
	public ProductoDonacionIngresoBean eliminarProductoDonacionIngreso(ProductoDonacionIngresoBean productoDonacionIngresoBean) throws Exception {
		return donacionDao.eliminarProductoDonacionIngreso(productoDonacionIngresoBean);
	}
	
	@Override
	public DocumentoIngresoBean insertarDocumentoDonacionIngreso(DocumentoIngresoBean documentoIngresoBean) throws Exception {
		return donacionDao.insertarDocumentoDonacionIngreso(documentoIngresoBean);
	}
	
	@Override
	public DocumentoIngresoBean actualizarDocumentoDonacionIngreso(DocumentoIngresoBean documentoIngresoBean) throws Exception {
		return donacionDao.actualizarDocumentoDonacionIngreso(documentoIngresoBean);
	}
	
	@Override
	public List<DocumentoIngresoBean> listarDocumentoDonacionIngreso(DocumentoIngresoBean documentoDonacionIngresoBean) throws Exception {
		return donacionDao.listarDocumentoDonacionIngreso(documentoDonacionIngresoBean);
	}
	
	@Override
	public DocumentoIngresoBean eliminarDocumentoIngresoDonacion(DocumentoIngresoBean documentoIngresoBean) throws Exception {
		return donacionDao.eliminarDocumentoIngresoDonacion(documentoIngresoBean);
	}
	
	@Override
	public List<ProductoDonacionBean> listarProductosDonacion(DonacionesIngresoBean itemBean) throws Exception {
		return donacionDao.listarProductosDonacion(itemBean);
	}
	
	@Override
	public DonacionesIngresoBean actualizarRegistroDonacionIngreso(DonacionesIngresoBean donacionesIngresoBean) throws Exception {
		return donacionDao.actualizarRegistroDonacionIngreso(donacionesIngresoBean);
	}
	
	@Override
	public List<ItemBean> listarSalida(ItemBean itemBean) throws Exception {
		return donacionDao.listarSalida(itemBean);
	}
	
	///////////SALIDA////////////
	
	
	@Override
	public List<DonacionesSalidaBean> listarSalidaDonaciones(DonacionesSalidaBean donacionesSalidaBean) throws Exception {
		return donacionDao.listarSalidaDonaciones(donacionesSalidaBean);
	}
	
	@Override
	public DonacionesSalidaBean obtenerCorrelativoOrdenSalida(DonacionesSalidaBean donacionesSalidaBean) throws Exception {
		return donacionDao.obtenerCorrelativoOrdenSalida(donacionesSalidaBean);
	}
	
	@Override
	public DonacionesSalidaBean insertarRegistroDonacionSalida(DonacionesSalidaBean donacionesSalidaBean) throws Exception {
		return donacionDao.insertarRegistroDonacionSalida(donacionesSalidaBean);
	}
	
	@Override
	public DonacionesSalidaBean obtenerDonacionSalidaXIdSalida(Integer idSalida) throws Exception {
		return donacionDao.obtenerDonacionSalidaXIdSalida(idSalida);
	}
	
	@Override
	public List<ProductoDonacionSalidaBean> listarProductoDonacionSalida(ProductoDonacionSalidaBean itemBean) throws Exception {
		return donacionDao.listarProductoDonacionSalida(itemBean);
	}
	
	@Override
	public ProductoDonacionSalidaBean insertarProductoDonacionSalida(ProductoDonacionSalidaBean productoDonacionSalidaBean) throws Exception {
		return donacionDao.insertarProductoDonacionSalida(productoDonacionSalidaBean);
	}
	
	@Override
	public ProductoDonacionSalidaBean actualizarProductoDonacionSalida(ProductoDonacionSalidaBean productoDonacionSalidaBean) throws Exception {
		return donacionDao.actualizarProductoDonacionSalida(productoDonacionSalidaBean);
	}
	
	@Override
	public ProductoDonacionSalidaBean eliminarProductoDonacionSalida(ProductoDonacionSalidaBean productoDonacionSalidaBean) throws Exception {
		return donacionDao.eliminarProductoDonacionSalida(productoDonacionSalidaBean);
	}
	
	@Override
	public List<ProductoDonacionSalidaBean> listarProductosDonacionSalida(ProductoDonacionSalidaBean itemBean) throws Exception {
		return donacionDao.listarProductosDonacionSalida(itemBean);
	}
	
	@Override
	public List<DocumentoSalidaBean> listarDocumentoDonacionSalida(DocumentoSalidaBean documentoSalidaBean) throws Exception {
		return donacionDao.listarDocumentoDonacionSalida(documentoSalidaBean);
	}
	
	@Override
	public DocumentoSalidaBean insertarDocumentoDonacionSalida(DocumentoSalidaBean documentoSalidaBean) throws Exception {
		return donacionDao.insertarDocumentoDonacionSalida(documentoSalidaBean);
	}
	
	@Override
	public DocumentoSalidaBean eliminarDocumentoSalidaDonacion(DocumentoSalidaBean documentoSalidaBean) throws Exception {
		return donacionDao.eliminarDocumentoSalidaDonacion(documentoSalidaBean);
	}
	
	@Override
	public DocumentoSalidaBean actualizarDocumentoDonacionSalida(DocumentoSalidaBean documentoSalidaBean) throws Exception {
		return donacionDao.actualizarDocumentoDonacionSalida(documentoSalidaBean);
	}
	
	@Override
	public DonacionesSalidaBean actualizarRegistroDonacionSalida(DonacionesSalidaBean donacionesSalidaBean) throws Exception {
		return donacionDao.actualizarRegistroDonacionSalida(donacionesSalidaBean);
	}
	
	
	@Override
	public List<GuiaRemisionBean> listarGuiaRemision(GuiaRemisionBean guiaRemisionBean) throws Exception {
		return donacionDao.listarGuiaRemision(guiaRemisionBean);
	}
	
	@Override
	public GuiaRemisionBean obtenerRegistroGuiaRemision(Integer idGuiaRemision) throws Exception {
		return donacionDao.obtenerRegistroGuiaRemision(idGuiaRemision);
	}
	
	@Override
	public GuiaRemisionBean actualizarGuiaRemision(GuiaRemisionBean guiaRemisionBean) throws Exception {
		return donacionDao.actualizarGuiaRemision(guiaRemisionBean);
	}
	
	@Override
	public List<DetalleGuiaRemisionBean> listarDetalleGuiaRemision(Integer idGuiaRemision, String tipoOrigen) throws Exception {
		return donacionDao.listarDetalleGuiaRemision(idGuiaRemision, tipoOrigen);
	}
	
	
	
	@Override
	public List<StockAlmacenBean> listarStockAlmacen(StockAlmacenBean stockAlmacenBean) throws Exception {
		return donacionDao.listarStockAlmacen(stockAlmacenBean);
	}
	
	@Override
	public StockAlmacenBean obtenerRegistroStockAlmacen(StockAlmacenBean stockAlmacenBean) throws Exception {
		return donacionDao.obtenerRegistroStockAlmacen(stockAlmacenBean);
	}
	
	@Override
	public StockAlmacenBean actualizarStockAlmacen(StockAlmacenBean stockAlmacenBean) throws Exception {
		return donacionDao.actualizarStockAlmacen(stockAlmacenBean);
	}
	
	@Override
	public List<CartillaInventarioBean> listarCartillaInventario(CartillaInventarioBean cartillaInventarioBean) throws Exception {
		return donacionDao.listarCartillaInventario(cartillaInventarioBean);
	}
	
	@Override
	public CartillaInventarioBean obtenerRegistroCartillaInventario(Integer idCartilla) throws Exception {
		return donacionDao.obtenerRegistroCartillaInventario(idCartilla);
	}
	
	@Override
	public List<ProductoCartillaInventarioBean> listarProductoCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception {
		return donacionDao.listarProductoCartillaInventario(productoCartillaInventarioBean);
	}
	
	@Override
	public List<EstadoCartillaInventarioBean> listarEstadoCartillaInventario(EstadoCartillaInventarioBean estadoCartillaInventarioBean) throws Exception {
		return donacionDao.listarEstadoCartillaInventario(estadoCartillaInventarioBean);
	}
	
	@Override
	public CartillaInventarioBean grabarCartillaInventario(CartillaInventarioBean cartillaInventarioBean) throws Exception {
		return donacionDao.grabarCartillaInventario(cartillaInventarioBean);
	}
	
	@Override
	public EstadoCartillaInventarioBean grabarEstadoCartillaInventario(EstadoCartillaInventarioBean estadoCartillaInventarioBean) throws Exception {
		return donacionDao.grabarEstadoCartillaInventario(estadoCartillaInventarioBean);
	}
	
	@Override
	public ProductoCartillaInventarioBean grabarProductoCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception {
		return donacionDao.grabarProductoCartillaInventario(productoCartillaInventarioBean);
	}
	
	@Override
	public ProductoCartillaInventarioBean procesarProductosCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception {
		return donacionDao.procesarProductosCartillaInventario(productoCartillaInventarioBean);
	}
	
	@Override
	public List<StockAlmacenProductoLoteBean> listarStockAlmacenProductoLote(StockAlmacenProductoLoteBean stockAlmacenProductoLoteBean) throws Exception {
		return donacionDao.listarStockAlmacenProductoLote(stockAlmacenProductoLoteBean);
	}
	
	@Override
	public ProductoCartillaInventarioBean eliminarProductoCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception {
		return donacionDao.eliminarProductoCartillaInventario(productoCartillaInventarioBean);
	}
	
	@Override
	public ProductoCartillaInventarioBean actualizarAjusteProductoCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception {
		return donacionDao.actualizarAjusteProductoCartillaInventario(productoCartillaInventarioBean);
	}
	
	@Override
	public List<CierreStockBean> listarCierreStock(CierreStockBean cierreStockBean) throws Exception {
		return donacionDao.listarCierreStock(cierreStockBean);
	}
	
	@Override
	public CierreStockBean obtenerRegistroCierreStock(CierreStockBean cierreStockBean) throws Exception {
		return donacionDao.obtenerRegistroCierreStock(cierreStockBean);
	}
	
	@Override
	public CierreStockBean grabarCierreStock(CierreStockBean cierreStockBean) throws Exception {
		return donacionDao.grabarCierreStock(cierreStockBean);
	}
	
	@Override
	public List<ProductoDonacionBean> listarReporteDonacionProductos(Integer idDonacion) throws Exception {
		return donacionDao.listarReporteDonacionProductos(idDonacion);
	}
	
	@Override
	public List<DonacionesIngresoBean> listarReporteDonacionIngreso(Integer idIngreso) throws Exception {
		return donacionDao.listarReporteDonacionIngreso(idIngreso);
	}
	
	@Override
	public List<ProductoDonacionIngresoBean> listarProductosReporteDonacionIngreso(Integer idIngreso) throws Exception {
		return donacionDao.listarProductosReporteDonacionIngreso(idIngreso);
	}
	
	@Override
	public List<DonacionesSalidaBean> listarReporteDonacionSalida(Integer idSalida) throws Exception {
		return donacionDao.listarReporteDonacionSalida(idSalida);
	}
	
	@Override
	public List<ProductoDonacionSalidaBean> listarProductosReporteDonacionSalida(Integer idIngreso) throws Exception {
		return donacionDao.listarProductosReporteDonacionSalida(idIngreso);
	}
	
	@Override
	public List<DonacionesIngresoBean> listarReporteDonacionIngresoNacional(Integer idIngreso, Integer idDdi) throws Exception {
		return donacionDao.listarReporteDonacionIngresoNacional(idIngreso, idDdi);
	}
	
	@Override
	public List<ProductoDonacionIngresoBean> listarProductoReporteDonacionIngresoNacional(Integer idIngreso, Integer idDdi) throws Exception {
		return donacionDao.listarProductoReporteDonacionIngresoNacional(idIngreso, idDdi);
	}
	
	@Override
	public List<DonacionesIngresoBean> listarReporteDonacionIngresoInternacional(Integer idIngreso, Integer idDdi) throws Exception {
		return donacionDao.listarReporteDonacionIngresoInternacional(idIngreso, idDdi);
	}
	
	@Override
	public List<ProductoDonacionIngresoBean> listarProductoReporteDonacionIngresoInternacional(Integer idIngreso, Integer idDdi) throws Exception {
		return donacionDao.listarProductoReporteDonacionIngresoInternacional(idIngreso, idDdi);
	}
	
	@Override
	public List<RegionDonacionBean> listarReporteDonacionRegiones(Integer idDonacion) throws Exception {
		return donacionDao.listarReporteDonacionRegiones(idDonacion);
	} 
	
	@Override
	public List<DocumentoDonacionIngresoBean> listarDocumentosReporteDonacionIngreso(Integer idIngreso) throws Exception {
		return donacionDao.listarDocumentosReporteDonacionIngreso(idIngreso);
	}
	
	@Override
	public List<DocumentoSalidaBean> listarDocumentosReporteDonacionSalida(Integer idSalida) throws Exception {
		return donacionDao.listarDocumentosReporteDonacionSalida(idSalida);
	}
	
	@Override
	public CartillaInventarioBean obtenerCorrelativoCartillaInventario(CartillaInventarioBean cartillaInventarioBean) throws Exception {
		return donacionDao.obtenerCorrelativoCartillaInventario(cartillaInventarioBean);
	}
	
	@Override
	public ItemBean validaStockProducto(ItemBean itemBean) throws Exception {
		return donacionDao.validaStockProducto(itemBean);
	}
	
	@Override
	public List<StockConsultaBean> listarConsultaStock(StockConsultaBean stockConsultaBean) throws Exception {
		return donacionDao.listarConsultaStock(stockConsultaBean);
	}
	
	@Override
	public List<StockConsultaBean> listarReporteStockAlimentos(StockConsultaBean stockConsultaBean) throws Exception {
		return donacionDao.listarReporteStockAlimentos(stockConsultaBean);
	}
	
	@Override
	public List<StockConsultaBean> listarReporteStockBNA(StockConsultaBean stockConsultaBean) throws Exception {
		return donacionDao.listarReporteStockBNA(stockConsultaBean);
	}
	
	@Override
	public StockConsultaBean listarReporteStockTitulo(StockConsultaBean stockConsultaBean) throws Exception {
		return donacionDao.listarReporteStockTitulo(stockConsultaBean);
	}
	
	@Override
	public List<DetalleGuiaRemisionBean> listarDetalleGuiaRemisionCabecera(Integer idGuiaRemision, String tipoOrigen) throws Exception {
		return donacionDao.listarDetalleGuiaRemisionCabecera(idGuiaRemision, tipoOrigen);
	}
	
	@Override
	public List<DetalleGuiaRemisionBean> listarDetalleGuiaRemisionDetalle(Integer idGuiaRemision, String tipoOrigen) throws Exception {
		return donacionDao.listarDetalleGuiaRemisionDetalle(idGuiaRemision, tipoOrigen);
	}
	
	@Override
	public List<DonacionesBean> listarHistorialDonaciones(DonacionesBean donacionesBean) throws Exception {
		return donacionDao.listarHistorialDonaciones(donacionesBean);
	}
	
	@Override
	public List<DonacionesBean> listarReporteHistorialGeneral(Integer idDonacion) throws Exception {
		return donacionDao.listarReporteHistorialGeneral(idDonacion);
	}
	
	@Override
	public ProductoCartillaInventarioBean procesarAjusteCartilla(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception {
		return donacionDao.procesarAjusteCartilla(productoCartillaInventarioBean);
	}
	
	@Override
	public ItemBean obtenerTransporte(ItemBean itemBean) throws Exception {
		return donacionDao.obtenerTransporte(itemBean);
	}
	
	@Override
	public List<ProductoCartillaInventarioBean> listarReporteCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception {
		return donacionDao.listarReporteCartillaInventario(productoCartillaInventarioBean);
	}
	
	@Override
	public List<StockConsultaBean> listarConsultaProductos(StockConsultaBean stockConsultaBean) throws Exception {
		return donacionDao.listarConsultaProductos(stockConsultaBean);
	}
	
	@Override
	public List<DonacionesBean> listarConsultaResoluciones(DonacionesBean donacionesBean) throws Exception {
		return donacionDao.listarConsultaResoluciones(donacionesBean);
	}
	
	@Override
	public List<DonacionesBean> listarReporteHistorialDonante(Integer idDonacion) throws Exception {
		return donacionDao.listarReporteHistorialDonante(idDonacion);
	}
	
	@Override
	public List<ProductoDonacionBean> listarReporteHistorialProducto(Integer idDonacion) throws Exception {
		return donacionDao.listarReporteHistorialProducto(idDonacion);
	}
	
	@Override
	public List<ProductoDonacionBean> listarReporteHistorialProductoIngSal(Integer idDonacion) throws Exception {
		return donacionDao.listarReporteHistorialProductoIngSal(idDonacion);
	}
	
	@Override
	public List<DocumentoDonacionBean> listarReporteHistorialDocumento(Integer idDonacion) throws Exception {
		return donacionDao.listarReporteHistorialDocumento(idDonacion);
	}
	
	@Override
	public ItemBean listarReporteHistorialCabecera(Integer idDonacion) throws Exception {
		return donacionDao.listarReporteHistorialCabecera(idDonacion);
	}
	
	@Override
	public List<ProductoDonacionBean> listarReporteHistorialProductoIndeci(Integer idDonacion) throws Exception {
		return donacionDao.listarReporteHistorialProductoIndeci(idDonacion);
	}
	
	@Override
	public List<ProductoDonacionBean> listarReporteHistorialProductoEntidades(Integer idDonacion) throws Exception {
		return donacionDao.listarReporteHistorialProductoEntidades(idDonacion);
	}
	
	@Override
	public DetalleManifiestoCargaBean listarDetalleManifiestoCargaDon(Integer idGuiaRemision, String tipoOrigen) throws Exception {
		return donacionDao.listarDetalleManifiestoCargaDon(idGuiaRemision, tipoOrigen);
	}
	
	@Override
	public List<ProductoDonacionBean> listarConsultaKardex(ProductoDonacionBean productoDonacionBean) throws Exception {
		return donacionDao.listarConsultaKardex(productoDonacionBean);
	}
	
	@Override
	public List<ProductoDonacionSalidaBean> listarProductosDonacionSalidaEdi(ProductoDonacionSalidaBean itemBean) throws Exception {
		return donacionDao.listarProductosDonacionSalidaEdi(itemBean);
	}
	
	@Override
	public List<StockAlmacenProductoBean> listarStockAlmacenProductoDon(StockAlmacenProductoBean stockAlmacenProductoBean) throws Exception {
		return donacionDao.listarStockAlmacenProductoDon(stockAlmacenProductoBean);
	}
	
	@Override
	public DetalleActaEntregaBean reporteActaEntrega(Integer idGuiaRemision, String tipoOrigen) throws Exception {
		return donacionDao.reporteActaEntrega(idGuiaRemision, tipoOrigen);
	}
	
	@Override
	public List<ProductoDonacionBean> buscarCantidadProducto(DonacionesIngresoBean itemBean) throws Exception {
		return donacionDao.buscarCantidadProducto(itemBean);
	}
	
	@Override
	public List<ProductoSalidaBean> listarReporteDetalleOrdenSalida(ProductoSalidaBean productoSalidaBean) throws Exception {
		return donacionDao.listarReporteDetalleOrdenSalida(productoSalidaBean);
	}
	
	@Override
	public List<OrdenSalidaBean> listarReporteOrdenSalida(OrdenSalidaBean ordenSalidaBean) throws Exception {
		return donacionDao.listarReporteOrdenSalida(ordenSalidaBean);
	}
	
	@Override
	public List<OrdenIngresoBean> listarReporteSigaE(OrdenIngresoBean ordenIngresoBean) throws Exception {
		return donacionDao.listarReporteSigaE(ordenIngresoBean);
	}
	
	@Override
	public List<OrdenIngresoBean> listarReporteSigaS(OrdenIngresoBean ordenIngresoBean) throws Exception {
		return donacionDao.listarReporteSigaS(ordenIngresoBean);
	}
	
	@Override
	public List<OrdenIngresoBean> listarReporteOrdenIngreso(OrdenIngresoBean ordenIngresoBean) throws Exception {
		return donacionDao.listarReporteOrdenIngreso(ordenIngresoBean);
	}
	
	@Override
	public List<ProductoIngresoBean> listarReporteDetalleOrdenIngreso(ProductoIngresoBean productoIngresoBean) throws Exception {
		return donacionDao.listarReporteDetalleOrdenIngreso(productoIngresoBean);
	}
	
	@Override
	public List<GuiaRemisionBean> listarReporteGuiaRemision(GuiaRemisionBean guiaRemisionBean) throws Exception {
		return donacionDao.listarReporteGuiaRemision(guiaRemisionBean);
	}
	
	@Override
	public List<DetalleGuiaRemisionBean> listarReporteDetalleGuiaRemision(DetalleGuiaRemisionBean detalleGuiaRemisionBean) throws Exception {
		return donacionDao.listarReporteDetalleGuiaRemision(detalleGuiaRemisionBean);
	}
}
