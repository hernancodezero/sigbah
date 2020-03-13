package pe.com.sigbah.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sigbah.common.bean.BincardAlmacenBean;
import pe.com.sigbah.common.bean.CartillaInventarioBean;
import pe.com.sigbah.common.bean.CierreStockBean;
import pe.com.sigbah.common.bean.ControlCalidadBean;
import pe.com.sigbah.common.bean.DetalleActaEntregaBean;
import pe.com.sigbah.common.bean.DetalleGuiaRemisionBean;
import pe.com.sigbah.common.bean.DetalleManifiestoCargaBean;
import pe.com.sigbah.common.bean.DetalleProductoControlCalidadBean;
import pe.com.sigbah.common.bean.DocumentoControlCalidadBean;
import pe.com.sigbah.common.bean.DocumentoIngresoBean;
import pe.com.sigbah.common.bean.DocumentoProyectoManifiestoBean;
import pe.com.sigbah.common.bean.DocumentoSalidaBean;
import pe.com.sigbah.common.bean.EstadoCartillaInventarioBean;
import pe.com.sigbah.common.bean.GuiaRemisionBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.KardexAlmacenBean;
import pe.com.sigbah.common.bean.LoteProductoBean;
import pe.com.sigbah.common.bean.ManifiestoVehiculoBean;
import pe.com.sigbah.common.bean.OrdenCompraBean;
import pe.com.sigbah.common.bean.OrdenIngresoBean;
import pe.com.sigbah.common.bean.OrdenSalidaBean;
import pe.com.sigbah.common.bean.ProductoBean;
import pe.com.sigbah.common.bean.ProductoCartillaInventarioBean;
import pe.com.sigbah.common.bean.ProductoControlCalidadBean;
import pe.com.sigbah.common.bean.ProductoIngresoBean;
import pe.com.sigbah.common.bean.ProductoProyectoManifiestoBean;
import pe.com.sigbah.common.bean.ProductoSalidaBean;
import pe.com.sigbah.common.bean.ProductoStockAlmacenBean;
import pe.com.sigbah.common.bean.ProyectoManifiestoBean;
import pe.com.sigbah.common.bean.StockAlmacenBean;
import pe.com.sigbah.common.bean.StockAlmacenLoteBean;
import pe.com.sigbah.common.bean.StockAlmacenProductoBean;
import pe.com.sigbah.common.bean.StockAlmacenProductoLoteBean;
import pe.com.sigbah.common.bean.StockConsultaBean;
import pe.com.sigbah.common.bean.StockProductoKardexBean;
import pe.com.sigbah.common.bean.StockProductoLoteBean;
import pe.com.sigbah.dao.LogisticaDao;
import pe.com.sigbah.service.LogisticaService;

/**
 * @className: LogisticaServiceImpl.java
 * @description: Clase que contiene el consumo de los procedimientos del package BAH_PKG_LOGISTICA.
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
@Service
public class LogisticaServiceImpl implements LogisticaService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LogisticaDao logisticaDao;
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarControlCalidad(pe.com.sigbah.common.bean.ControlCalidadBean)
	 */
	@Override
	public List<ControlCalidadBean> listarControlCalidad(ControlCalidadBean controlCalidadBean) throws Exception {
		return logisticaDao.listarControlCalidad(controlCalidadBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#obtenerCorrelativoControlCalidad(pe.com.sigbah.common.bean.ControlCalidadBean)
	 */
	@Override
	public ControlCalidadBean obtenerCorrelativoControlCalidad(ControlCalidadBean controlCalidadBean) throws Exception {
		return logisticaDao.obtenerCorrelativoControlCalidad(controlCalidadBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarAlmacenActivo(pe.com.sigbah.common.bean.ControlCalidadBean)
	 */
	@Override
	public List<ControlCalidadBean> listarAlmacenActivo(ControlCalidadBean controlCalidadBean) throws Exception {
		return logisticaDao.listarAlmacenActivo(controlCalidadBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarOrdenCompra()
	 */
	@Override
	public List<OrdenCompraBean> listarOrdenCompra() throws Exception {
		return logisticaDao.listarOrdenCompra();
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#insertarRegistroControlCalidad(pe.com.sigbah.common.bean.ControlCalidadBean)
	 */
	@Override
	public ControlCalidadBean insertarRegistroControlCalidad(ControlCalidadBean controlCalidadBean) throws Exception {
		return logisticaDao.insertarRegistroControlCalidad(controlCalidadBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#actualizarRegistroControlCalidad(pe.com.sigbah.common.bean.ControlCalidadBean)
	 */
	@Override
	public ControlCalidadBean actualizarRegistroControlCalidad(ControlCalidadBean controlCalidadBean) throws Exception {
		return logisticaDao.actualizarRegistroControlCalidad(controlCalidadBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#obtenerRegistroControlCalidad(java.lang.Integer)
	 */
	@Override
	public ControlCalidadBean obtenerRegistroControlCalidad(Integer idControlCalidad) throws Exception {
		return logisticaDao.obtenerRegistroControlCalidad(idControlCalidad);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarProductoControlCalidad(pe.com.sigbah.common.bean.ProductoControlCalidadBean)
	 */
	@Override
	public List<ProductoControlCalidadBean> listarProductoControlCalidad(ProductoControlCalidadBean producto) throws Exception {
		return logisticaDao.listarProductoControlCalidad(producto);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#grabarProductoControlCalidad(pe.com.sigbah.common.bean.ProductoControlCalidadBean)
	 */
	@Override
	public ProductoControlCalidadBean grabarProductoControlCalidad(ProductoControlCalidadBean productoControlCalidadBean) throws Exception {
		return logisticaDao.grabarProductoControlCalidad(productoControlCalidadBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#eliminarProductoControlCalidad(pe.com.sigbah.common.bean.ProductoControlCalidadBean)
	 */
	@Override
	public ProductoControlCalidadBean eliminarProductoControlCalidad(ProductoControlCalidadBean productoControlCalidadBean) throws Exception {
		return logisticaDao.eliminarProductoControlCalidad(productoControlCalidadBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarDocumentoControlCalidad(pe.com.sigbah.common.bean.DocumentoControlCalidadBean)
	 */
	@Override
	public List<DocumentoControlCalidadBean> listarDocumentoControlCalidad(DocumentoControlCalidadBean documentoControlCalidadBean) throws Exception {
		return logisticaDao.listarDocumentoControlCalidad(documentoControlCalidadBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#grabarDocumentoControlCalidad(pe.com.sigbah.common.bean.DocumentoControlCalidadBean)
	 */
	@Override
	public DocumentoControlCalidadBean grabarDocumentoControlCalidad(DocumentoControlCalidadBean documentoControlCalidadBean) throws Exception {
		return logisticaDao.grabarDocumentoControlCalidad(documentoControlCalidadBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#eliminarDocumentoControlCalidad(pe.com.sigbah.common.bean.DocumentoControlCalidadBean)
	 */
	@Override
	public DocumentoControlCalidadBean eliminarDocumentoControlCalidad(DocumentoControlCalidadBean documentoControlCalidadBean) throws Exception {
		return logisticaDao.eliminarDocumentoControlCalidad(documentoControlCalidadBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarDetalleProductoControlCalidad(java.lang.Integer)
	 */
	@Override
	public List<DetalleProductoControlCalidadBean> listarDetalleProductoControlCalidad(Integer idControlCalidad) throws Exception {
		return logisticaDao.listarDetalleProductoControlCalidad(idControlCalidad);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarOrdenIngreso(pe.com.sigbah.common.bean.OrdenIngresoBean)
	 */
	@Override
	public List<OrdenIngresoBean> listarOrdenIngreso(OrdenIngresoBean ordenIngresoBean) throws Exception {
		return logisticaDao.listarOrdenIngreso(ordenIngresoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#obtenerCorrelativoOrdenIngreso(pe.com.sigbah.common.bean.OrdenIngresoBean)
	 */
	@Override
	public OrdenIngresoBean obtenerCorrelativoOrdenIngreso(OrdenIngresoBean ordenIngresoBean) throws Exception {
		return logisticaDao.obtenerCorrelativoOrdenIngreso(ordenIngresoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarNroControlCalidad(pe.com.sigbah.common.bean.ControlCalidadBean)
	 */
	@Override
	public List<ControlCalidadBean> listarNroControlCalidad(ControlCalidadBean controlCalidadBean) throws Exception {
		return logisticaDao.listarNroControlCalidad(controlCalidadBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#grabarOrdenIngreso(pe.com.sigbah.common.bean.OrdenIngresoBean)
	 */
	@Override
	public OrdenIngresoBean grabarOrdenIngreso(OrdenIngresoBean ordenIngresoBean) throws Exception {
		return logisticaDao.grabarOrdenIngreso(ordenIngresoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#obtenerRegistroOrdenIngreso(java.lang.Integer)
	 */
	@Override
	public OrdenIngresoBean obtenerRegistroOrdenIngreso(Integer idIngreso) throws Exception {
		return logisticaDao.obtenerRegistroOrdenIngreso(idIngreso);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarProductoIngreso(pe.com.sigbah.common.bean.ProductoIngresoBean)
	 */
	@Override
	public List<ProductoIngresoBean> listarProductoIngreso(ProductoIngresoBean productoIngresoBean) throws Exception {
		return logisticaDao.listarProductoIngreso(productoIngresoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#grabarProductoIngreso(pe.com.sigbah.common.bean.ProductoIngresoBean)
	 */
	@Override
	public ProductoIngresoBean grabarProductoIngreso(ProductoIngresoBean productoIngresoBean) throws Exception {
		return logisticaDao.grabarProductoIngreso(productoIngresoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#eliminarProductoIngreso(pe.com.sigbah.common.bean.ProductoIngresoBean)
	 */
	@Override
	public ProductoIngresoBean eliminarProductoIngreso(ProductoIngresoBean productoIngresoBean) throws Exception {
		return logisticaDao.eliminarProductoIngreso(productoIngresoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarLoteProductos(pe.com.sigbah.common.bean.LoteProductoBean)
	 */
	@Override
	public List<LoteProductoBean> listarLoteProductos(LoteProductoBean loteProductoBean) throws Exception {
		return logisticaDao.listarLoteProductos(loteProductoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarDocumentoIngreso(pe.com.sigbah.common.bean.DocumentoIngresoBean)
	 */
	@Override
	public List<DocumentoIngresoBean> listarDocumentoIngreso(DocumentoIngresoBean documentoIngresoBean) throws Exception {
		return logisticaDao.listarDocumentoIngreso(documentoIngresoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#grabarDocumentoIngreso(pe.com.sigbah.common.bean.DocumentoIngresoBean)
	 */
	@Override
	public DocumentoIngresoBean grabarDocumentoIngreso(DocumentoIngresoBean documentoIngresoBean) throws Exception {
		return logisticaDao.grabarDocumentoIngreso(documentoIngresoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#eliminarDocumentoIngreso(pe.com.sigbah.common.bean.DocumentoIngresoBean)
	 */
	@Override
	public DocumentoIngresoBean eliminarDocumentoIngreso(DocumentoIngresoBean documentoIngresoBean) throws Exception {
		return logisticaDao.eliminarDocumentoIngreso(documentoIngresoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarOrdenSalida(pe.com.sigbah.common.bean.OrdenSalidaBean)
	 */
	@Override
	public List<OrdenSalidaBean> listarOrdenSalida(OrdenSalidaBean ordenSalidaBean) throws Exception {
		return logisticaDao.listarOrdenSalida(ordenSalidaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#obtenerCorrelativoOrdenSalida(pe.com.sigbah.common.bean.OrdenSalidaBean)
	 */
	@Override
	public OrdenSalidaBean obtenerCorrelativoOrdenSalida(OrdenSalidaBean ordenSalidaBean) throws Exception {
		return logisticaDao.obtenerCorrelativoOrdenSalida(ordenSalidaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#grabarOrdenSalida(pe.com.sigbah.common.bean.OrdenSalidaBean)
	 */
	@Override
	public OrdenSalidaBean grabarOrdenSalida(OrdenSalidaBean ordenSalidaBean) throws Exception {
		return logisticaDao.grabarOrdenSalida(ordenSalidaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#obtenerRegistroOrdenSalida(java.lang.Integer, java.lang.String)
	 */
	@Override
	public OrdenSalidaBean obtenerRegistroOrdenSalida(Integer idSalida, String anio) throws Exception {
		return logisticaDao.obtenerRegistroOrdenSalida(idSalida, anio);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarProductoSalida(pe.com.sigbah.common.bean.ProductoSalidaBean)
	 */
	@Override
	public List<ProductoSalidaBean> listarProductoSalida(ProductoSalidaBean productoSalidaBean) throws Exception {
		return logisticaDao.listarProductoSalida(productoSalidaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#grabarProductoSalida(pe.com.sigbah.common.bean.ProductoSalidaBean)
	 */
	@Override
	public ProductoSalidaBean grabarProductoSalida(ProductoSalidaBean productoSalidaBean) throws Exception {
		return logisticaDao.grabarProductoSalida(productoSalidaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#eliminarProductoSalida(pe.com.sigbah.common.bean.ProductoSalidaBean)
	 */
	@Override
	public ProductoSalidaBean eliminarProductoSalida(ProductoSalidaBean productoSalidaBean) throws Exception {
		return logisticaDao.eliminarProductoSalida(productoSalidaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarDocumentoSalida(pe.com.sigbah.common.bean.DocumentoSalidaBean)
	 */
	@Override
	public List<DocumentoSalidaBean> listarDocumentoSalida(DocumentoSalidaBean documentoSalidaBean) throws Exception {
		return logisticaDao.listarDocumentoSalida(documentoSalidaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#grabarDocumentoSalida(pe.com.sigbah.common.bean.DocumentoSalidaBean)
	 */
	@Override
	public DocumentoSalidaBean grabarDocumentoSalida(DocumentoSalidaBean documentoSalidaBean) throws Exception {
		return logisticaDao.grabarDocumentoSalida(documentoSalidaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#eliminarDocumentoSalida(pe.com.sigbah.common.bean.DocumentoSalidaBean)
	 */
	@Override
	public DocumentoSalidaBean eliminarDocumentoSalida(DocumentoSalidaBean documentoSalidaBean) throws Exception {
		return logisticaDao.eliminarDocumentoSalida(documentoSalidaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarManifiesto(pe.com.sigbah.common.bean.ProyectoManifiestoBean)
	 */
	@Override
	public List<ProyectoManifiestoBean> listarManifiesto(ProyectoManifiestoBean proyectoManifiestoBean) throws Exception {
		return logisticaDao.listarManifiesto(proyectoManifiestoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarProyectoManifiesto(pe.com.sigbah.common.bean.ProyectoManifiestoBean)
	 */
	@Override
	public List<ProyectoManifiestoBean> listarProyectoManifiesto(ProyectoManifiestoBean proyectoManifiestoBean) throws Exception {
		return logisticaDao.listarProyectoManifiesto(proyectoManifiestoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#obtenerCorrelativoProyectoManifiesto(pe.com.sigbah.common.bean.ProyectoManifiestoBean)
	 */
	@Override
	public ProyectoManifiestoBean obtenerCorrelativoProyectoManifiesto(ProyectoManifiestoBean proyectoManifiestoBean) throws Exception {
		return logisticaDao.obtenerCorrelativoProyectoManifiesto(proyectoManifiestoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#grabarProyectoManifiesto(pe.com.sigbah.common.bean.ProyectoManifiestoBean)
	 */
	@Override
	public ProyectoManifiestoBean grabarProyectoManifiesto(ProyectoManifiestoBean proyectoManifiestoBean) throws Exception {
		return logisticaDao.grabarProyectoManifiesto(proyectoManifiestoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#obtenerRegistroProyectoManifiesto(java.lang.Integer)
	 */
	@Override
	public ProyectoManifiestoBean obtenerRegistroProyectoManifiesto(Integer idProyecto) throws Exception {
		return logisticaDao.obtenerRegistroProyectoManifiesto(idProyecto);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarProductoProyectoManifiesto(pe.com.sigbah.common.bean.ProductoProyectoManifiestoBean)
	 */
	@Override
	public List<ProductoProyectoManifiestoBean> listarProductoProyectoManifiesto(ProductoProyectoManifiestoBean productoProyectoManifiestoBean) throws Exception {
		return logisticaDao.listarProductoProyectoManifiesto(productoProyectoManifiestoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#grabarProductoProyectoManifiesto(pe.com.sigbah.common.bean.ProductoProyectoManifiestoBean)
	 */
	@Override
	public ProductoProyectoManifiestoBean grabarProductoProyectoManifiesto(ProductoProyectoManifiestoBean productoProyectoManifiestoBean) throws Exception {
		return logisticaDao.grabarProductoProyectoManifiesto(productoProyectoManifiestoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#eliminarProductoProyectoManifiesto(pe.com.sigbah.common.bean.ProductoProyectoManifiestoBean)
	 */
	@Override
	public ProductoProyectoManifiestoBean eliminarProductoProyectoManifiesto(ProductoProyectoManifiestoBean productoProyectoManifiestoBean) throws Exception {
		return logisticaDao.eliminarProductoProyectoManifiesto(productoProyectoManifiestoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarDocumentoProyectoManifiesto(pe.com.sigbah.common.bean.DocumentoProyectoManifiestoBean)
	 */
	@Override
	public List<DocumentoProyectoManifiestoBean> listarDocumentoProyectoManifiesto(DocumentoProyectoManifiestoBean documentoProyectoManifiestoBean) throws Exception {
		return logisticaDao.listarDocumentoProyectoManifiesto(documentoProyectoManifiestoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#grabarDocumentoProyectoManifiesto(pe.com.sigbah.common.bean.DocumentoProyectoManifiestoBean)
	 */
	@Override
	public DocumentoProyectoManifiestoBean grabarDocumentoProyectoManifiesto(DocumentoProyectoManifiestoBean documentoProyectoManifiestoBean) throws Exception {
		return logisticaDao.grabarDocumentoProyectoManifiesto(documentoProyectoManifiestoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#eliminarDocumentoProyectoManifiesto(pe.com.sigbah.common.bean.DocumentoProyectoManifiestoBean)
	 */
	@Override
	public DocumentoProyectoManifiestoBean eliminarDocumentoProyectoManifiesto(DocumentoProyectoManifiestoBean documentoProyectoManifiestoBean) throws Exception {
		return logisticaDao.eliminarDocumentoProyectoManifiesto(documentoProyectoManifiestoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#verificarProductosProgramacion(java.lang.Integer)
	 */
	@Override
	public int verificarProductosProgramacion(Integer idProyecto) throws Exception {
		return logisticaDao.verificarProductosProgramacion(idProyecto);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#procesarManifiestoProducto(pe.com.sigbah.common.bean.ProyectoManifiestoBean)
	 */
	@Override
	public String procesarManifiestoProducto(ProyectoManifiestoBean proyectoManifiestoBean) throws Exception {
		return logisticaDao.procesarManifiestoProducto(proyectoManifiestoBean);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarManifiestoVehiculo(pe.com.sigbah.common.bean.ManifiestoVehiculoBean)
	 */
	@Override
	public List<ManifiestoVehiculoBean> listarManifiestoVehiculo(ManifiestoVehiculoBean manifiestoVehiculoBean) throws Exception {
		return logisticaDao.listarManifiestoVehiculo(manifiestoVehiculoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#procesarManifiestoVehiculo(pe.com.sigbah.common.bean.ProyectoManifiestoVehiculoBean)
	 */
	@Override
	public ManifiestoVehiculoBean procesarManifiestoVehiculo(ManifiestoVehiculoBean manifiestoVehiculoBean) throws Exception {
		return logisticaDao.procesarManifiestoVehiculo(manifiestoVehiculoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarGuiaRemision(pe.com.sigbah.common.bean.GuiaRemisionBean)
	 */
	@Override
	public List<GuiaRemisionBean> listarGuiaRemision(GuiaRemisionBean guiaRemisionBean) throws Exception {
		return logisticaDao.listarGuiaRemision(guiaRemisionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#obtenerRegistroGuiaRemision(java.lang.Integer)
	 */
	@Override
	public GuiaRemisionBean obtenerRegistroGuiaRemision(Integer idGuiaRemision) throws Exception {
		return logisticaDao.obtenerRegistroGuiaRemision(idGuiaRemision);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#insertarGuiaRemision(pe.com.sigbah.common.bean.GuiaRemisionBean)
	 */
	@Override
	public GuiaRemisionBean insertarGuiaRemision(GuiaRemisionBean guiaRemisionBean) throws Exception {
		return logisticaDao.insertarGuiaRemision(guiaRemisionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#anularGuiaRemision(pe.com.sigbah.common.bean.GuiaRemisionBean)
	 */
	@Override
	public GuiaRemisionBean anularGuiaRemision(GuiaRemisionBean guiaRemisionBean) throws Exception {
		return logisticaDao.anularGuiaRemision(guiaRemisionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#actualizarGuiaRemision(pe.com.sigbah.common.bean.GuiaRemisionBean)
	 */
	@Override
	public GuiaRemisionBean actualizarGuiaRemision(GuiaRemisionBean guiaRemisionBean) throws Exception {
		return logisticaDao.actualizarGuiaRemision(guiaRemisionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarDetalleGuiaRemision(java.lang.Integer, java.lang.String)
	 */
	@Override
	public List<DetalleGuiaRemisionBean> listarDetalleGuiaRemision(Integer idGuiaRemision, String tipoOrigen) throws Exception {
		return logisticaDao.listarDetalleGuiaRemision(idGuiaRemision, tipoOrigen);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarDetalleManifiestoCarga(java.lang.Integer, java.lang.String)
	 */
	@Override
	public List<DetalleManifiestoCargaBean> listarDetalleManifiestoCarga(Integer idGuiaRemision, String tipoOrigen) throws Exception {
		return logisticaDao.listarDetalleManifiestoCarga(idGuiaRemision, tipoOrigen);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarDetalleActaEntrega(java.lang.Integer, java.lang.String)
	 */
	@Override
	public List<DetalleActaEntregaBean> listarDetalleActaEntrega(Integer idGuiaRemision, String tipoOrigen) throws Exception {
		return logisticaDao.listarDetalleActaEntrega(idGuiaRemision, tipoOrigen);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarStockAlmacen(pe.com.sigbah.common.bean.StockAlmacenBean)
	 */
	@Override
	public List<StockAlmacenBean> listarStockAlmacen(StockAlmacenBean stockAlmacenBean) throws Exception {
		return logisticaDao.listarStockAlmacen(stockAlmacenBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#obtenerRegistroStockAlmacen(pe.com.sigbah.common.bean.StockAlmacenBean)
	 */
	@Override
	public StockAlmacenBean obtenerRegistroStockAlmacen(StockAlmacenBean stockAlmacenBean) throws Exception {
		return logisticaDao.obtenerRegistroStockAlmacen(stockAlmacenBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#actualizarStockAlmacen(pe.com.sigbah.common.bean.StockAlmacenBean)
	 */
	@Override
	public StockAlmacenBean actualizarStockAlmacen(StockAlmacenBean stockAlmacenBean) throws Exception {
		return logisticaDao.actualizarStockAlmacen(stockAlmacenBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#obtenerProductoStockAlmacen(pe.com.sigbah.common.bean.StockAlmacenBean)
	 */
	@Override
	public ProductoStockAlmacenBean obtenerProductoStockAlmacen(StockAlmacenBean stockAlmacenBean) throws Exception {
		return logisticaDao.obtenerProductoStockAlmacen(stockAlmacenBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarStockAlmacenLote(pe.com.sigbah.common.bean.StockAlmacenLoteBean)
	 */
	@Override
	public List<StockAlmacenLoteBean> listarStockAlmacenLote(StockAlmacenLoteBean stockAlmacenLoteBean) throws Exception {
		return logisticaDao.listarStockAlmacenLote(stockAlmacenLoteBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#actualizarStockAlmacenLote(pe.com.sigbah.common.bean.StockAlmacenLoteBean)
	 */
	@Override
	public StockAlmacenLoteBean actualizarStockAlmacenLote(StockAlmacenLoteBean stockAlmacenLoteBean) throws Exception {
		return logisticaDao.actualizarStockAlmacenLote(stockAlmacenLoteBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarCartillaInventario(pe.com.sigbah.common.bean.CartillaInventarioBean)
	 */
	@Override
	public List<CartillaInventarioBean> listarCartillaInventario(CartillaInventarioBean cartillaInventarioBean) throws Exception {
		return logisticaDao.listarCartillaInventario(cartillaInventarioBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#obtenerRegistroCartillaInventario(java.lang.Integer)
	 */
	@Override
	public CartillaInventarioBean obtenerRegistroCartillaInventario(Integer idCartilla) throws Exception {
		return logisticaDao.obtenerRegistroCartillaInventario(idCartilla);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#obtenerCorrelativoCartillaInventario(pe.com.sigbah.common.bean.CartillaInventarioBean)
	 */
	@Override
	public CartillaInventarioBean obtenerCorrelativoCartillaInventario(CartillaInventarioBean cartillaInventarioBean) throws Exception {
		return logisticaDao.obtenerCorrelativoCartillaInventario(cartillaInventarioBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#grabarCartillaInventario(pe.com.sigbah.common.bean.CartillaInventarioBean)
	 */
	@Override
	public CartillaInventarioBean grabarCartillaInventario(CartillaInventarioBean cartillaInventarioBean) throws Exception {
		return logisticaDao.grabarCartillaInventario(cartillaInventarioBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarProductoCartillaInventario(pe.com.sigbah.common.bean.ProductoCartillaInventarioBean)
	 */
	@Override
	public List<ProductoCartillaInventarioBean> listarProductoCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception {
		return logisticaDao.listarProductoCartillaInventario(productoCartillaInventarioBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#grabarProductoCartillaInventario(pe.com.sigbah.common.bean.ProductoCartillaInventarioBean)
	 */
	@Override
	public ProductoCartillaInventarioBean grabarProductoCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception {
		return logisticaDao.grabarProductoCartillaInventario(productoCartillaInventarioBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#eliminarProductoCartillaInventario(pe.com.sigbah.common.bean.ProductoCartillaInventarioBean)
	 */
	@Override
	public ProductoCartillaInventarioBean eliminarProductoCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception {
		return logisticaDao.eliminarProductoCartillaInventario(productoCartillaInventarioBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#procesarProductosCartillaInventario(pe.com.sigbah.common.bean.ProductoCartillaInventarioBean)
	 */
	@Override
	public ProductoCartillaInventarioBean procesarProductosCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception {
		return logisticaDao.procesarProductosCartillaInventario(productoCartillaInventarioBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarStockAlmacenProducto(pe.com.sigbah.common.bean.StockAlmacenProductoBean)
	 */
	@Override
	public List<StockAlmacenProductoBean> listarStockAlmacenProducto(StockAlmacenProductoBean stockAlmacenProductoBean) throws Exception {
		return logisticaDao.listarStockAlmacenProducto(stockAlmacenProductoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarStockAlmacenProductoLote(pe.com.sigbah.common.bean.StockAlmacenProductoLoteBean)
	 */
	@Override
	public List<StockAlmacenProductoLoteBean> listarStockAlmacenProductoLote(StockAlmacenProductoLoteBean stockAlmacenProductoLoteBean) throws Exception {
		return logisticaDao.listarStockAlmacenProductoLote(stockAlmacenProductoLoteBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#actualizarAjusteProductoCartillaInventario(pe.com.sigbah.common.bean.ProductoCartillaInventarioBean)
	 */
	@Override
	public ProductoCartillaInventarioBean actualizarAjusteProductoCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception {
		return logisticaDao.actualizarAjusteProductoCartillaInventario(productoCartillaInventarioBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#procesarAjusteProductoCartillaInventario(pe.com.sigbah.common.bean.ProductoCartillaInventarioBean)
	 */
	@Override
	public ProductoCartillaInventarioBean procesarAjusteProductoCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception {
		return logisticaDao.procesarAjusteProductoCartillaInventario(productoCartillaInventarioBean);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarEstadoCartillaInventario(pe.com.sigbah.common.bean.EstadoCartillaInventarioBean)
	 */
	@Override
	public List<EstadoCartillaInventarioBean> listarEstadoCartillaInventario(EstadoCartillaInventarioBean estadoCartillaInventarioBean) throws Exception {
		return logisticaDao.listarEstadoCartillaInventario(estadoCartillaInventarioBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#obtenerEstadosCartillaInventario(pe.com.sigbah.common.bean.EstadoCartillaInventarioBean)
	 */
	@Override
	public List<EstadoCartillaInventarioBean> obtenerEstadosCartillaInventario(EstadoCartillaInventarioBean estadoCartillaInventarioBean) throws Exception {
		return logisticaDao.obtenerEstadosCartillaInventario(estadoCartillaInventarioBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#grabarEstadoCartillaInventario(pe.com.sigbah.common.bean.EstadoCartillaInventarioBean)
	 */
	@Override
	public EstadoCartillaInventarioBean grabarEstadoCartillaInventario(EstadoCartillaInventarioBean estadoCartillaInventarioBean) throws Exception {
		return logisticaDao.grabarEstadoCartillaInventario(estadoCartillaInventarioBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarCierreStock(pe.com.sigbah.common.bean.CierreStockBean)
	 */
	@Override
	public List<CierreStockBean> listarCierreStock(CierreStockBean cierreStockBean) throws Exception {
		return logisticaDao.listarCierreStock(cierreStockBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#obtenerRegistroCierreStock(pe.com.sigbah.common.bean.CierreStockBean)
	 */
	@Override
	public CierreStockBean obtenerRegistroCierreStock(CierreStockBean cierreStockBean) throws Exception {
		return logisticaDao.obtenerRegistroCierreStock(cierreStockBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#grabarCierreStock(pe.com.sigbah.common.bean.CierreStockBean)
	 */
	@Override
	public CierreStockBean grabarCierreStock(CierreStockBean cierreStockBean) throws Exception {
		return logisticaDao.grabarCierreStock(cierreStockBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarStockProductoKardex(pe.com.sigbah.common.bean.StockProductoKardexBean)
	 */
	@Override
	public List<StockProductoKardexBean> listarStockProductoKardex(StockProductoKardexBean stockProductoKardexBean) throws Exception {
		return logisticaDao.listarStockProductoKardex(stockProductoKardexBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarStockProductoLote(pe.com.sigbah.common.bean.StockProductoLoteBean)
	 */
	@Override
	public List<StockProductoLoteBean> listarStockProductoLote(StockProductoLoteBean stockProductoLoteBean) throws Exception {
		return logisticaDao.listarStockProductoLote(stockProductoLoteBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarReporteOrdenSalida(pe.com.sigbah.common.bean.OrdenSalidaBean)
	 */
	@Override
	public List<OrdenSalidaBean> listarReporteOrdenSalida(OrdenSalidaBean ordenSalidaBean) throws Exception {
		return logisticaDao.listarReporteOrdenSalida(ordenSalidaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarReporteDetalleOrdenSalida(pe.com.sigbah.common.bean.ProductoSalidaBean)
	 */
	@Override
	public List<ProductoSalidaBean> listarReporteDetalleOrdenSalida(ProductoSalidaBean productoSalidaBean) throws Exception {
		return logisticaDao.listarReporteDetalleOrdenSalida(productoSalidaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarReporteProyectoManifiesto(pe.com.sigbah.common.bean.ProyectoManifiestoBean)
	 */
	@Override
	public List<ProyectoManifiestoBean> listarReporteProyectoManifiesto(ProyectoManifiestoBean proyectoManifiestoBean) throws Exception {
		return logisticaDao.listarReporteProyectoManifiesto(proyectoManifiestoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarReporteDetalleProyectoManifiesto(pe.com.sigbah.common.bean.ProductoProyectoManifiestoBean)
	 */
	@Override
	public List<ProductoProyectoManifiestoBean> listarReporteDetalleProyectoManifiesto(ProductoProyectoManifiestoBean productoProyectoManifiestoBean) throws Exception {
		return logisticaDao.listarReporteDetalleProyectoManifiesto(productoProyectoManifiestoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarReporteOrdenIngreso(pe.com.sigbah.common.bean.OrdenIngresoBean)
	 */
	@Override
	public List<OrdenIngresoBean> listarReporteOrdenIngreso(OrdenIngresoBean ordenIngresoBean) throws Exception {
		return logisticaDao.listarReporteOrdenIngreso(ordenIngresoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarReporteDetalleOrdenIngreso(pe.com.sigbah.common.bean.ProductoIngresoBean)
	 */
	@Override
	public List<ProductoIngresoBean> listarReporteDetalleOrdenIngreso(ProductoIngresoBean productoIngresoBean) throws Exception {
		return logisticaDao.listarReporteDetalleOrdenIngreso(productoIngresoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarReporteGuiaRemision(pe.com.sigbah.common.bean.GuiaRemisionBean)
	 */
	@Override
	public List<GuiaRemisionBean> listarReporteGuiaRemision(GuiaRemisionBean guiaRemisionBean) throws Exception {
		return logisticaDao.listarReporteGuiaRemision(guiaRemisionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarReporteDetalleGuiaRemision(pe.com.sigbah.common.bean.DetalleGuiaRemisionBean)
	 */
	@Override
	public List<DetalleGuiaRemisionBean> listarReporteDetalleGuiaRemision(DetalleGuiaRemisionBean detalleGuiaRemisionBean) throws Exception {
		return logisticaDao.listarReporteDetalleGuiaRemision(detalleGuiaRemisionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarReporteKardex(pe.com.sigbah.common.bean.KardexAlmacenBean)
	 */
	@Override
	public List<KardexAlmacenBean> listarReporteKardex(KardexAlmacenBean kardexAlmacenBean) throws Exception {
		return logisticaDao.listarReporteKardex(kardexAlmacenBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarReporteBincard(pe.com.sigbah.common.bean.BincardAlmacenBean)
	 */
	@Override
	public List<BincardAlmacenBean> listarReporteBincard(BincardAlmacenBean bincardAlmacenBean) throws Exception {
		return logisticaDao.listarReporteBincard(bincardAlmacenBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarProductosStock(pe.com.sigbah.common.bean.ProductoBean)
	 */
	@Override
	public List<ProductoBean> listarProductosStock(ProductoBean productoBean) throws Exception {
		return logisticaDao.listarProductosStock(productoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarLoteProductoSalida(pe.com.sigbah.common.bean.LoteProductoBean)
	 */
	@Override
	public List<LoteProductoBean> listarLoteProductoSalida(LoteProductoBean loteProductoBean) throws Exception {
		return logisticaDao.listarLoteProductoSalida(loteProductoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#listarProductoManifiestoSalida(pe.com.sigbah.common.bean.ProductoProyectoManifiestoBean)
	 */
	@Override
	public List<ProductoProyectoManifiestoBean> listarProductoManifiestoSalida(ProductoProyectoManifiestoBean productoProyectoManifiestoBean) throws Exception {
		return logisticaDao.listarProductoManifiestoSalida(productoProyectoManifiestoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#grabarProductoManifiestoSalida(pe.com.sigbah.common.bean.ProductoProyectoManifiestoBean)
	 */
	@Override
	public ProductoProyectoManifiestoBean grabarProductoManifiestoSalida(ProductoProyectoManifiestoBean productoProyectoManifiestoBean) throws Exception {
		return logisticaDao.grabarProductoManifiestoSalida(productoProyectoManifiestoBean);
	}

	@Override
	public List<StockAlmacenBean> listarConsultaStockAlmacen(StockAlmacenBean stockAlmacenBean) throws Exception {
		return logisticaDao.listarConsultaStockAlmacen(stockAlmacenBean);
	}
	
	@Override
	public List<ProductoIngresoBean> listarHistorialIngreso(ProductoIngresoBean productoIngresoBean) throws Exception {
		return logisticaDao.listarHistorialIngreso(productoIngresoBean);
	}
	
	@Override
	public List<ProductoIngresoBean> listarHistorialSalida(ProductoIngresoBean productoIngresoBean) throws Exception {
		return logisticaDao.listarHistorialSalida(productoIngresoBean);
	}
	
	@Override
	public List<ProductoBean> listarProductosFechaVencimiento(ProductoBean productoBean) throws Exception {
		return logisticaDao.listarProductosFechaVencimiento(productoBean);
	}
	
	@Override
	public ProductoCartillaInventarioBean procesarAjusteCartilla(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception {
		return logisticaDao.procesarAjusteCartilla(productoCartillaInventarioBean);
	}
	
	@Override
	public List<ProductoCartillaInventarioBean> listarReporteCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception {
		return logisticaDao.listarReporteCartillaInventario(productoCartillaInventarioBean);
	}
	
	@Override
	public List<ItemBean> listarSalida(ItemBean itemBean) throws Exception {
		return logisticaDao.listarSalida(itemBean);
	}
	
	@Override
	public ItemBean obtenerNumProductos(Integer idSalida) throws Exception {
		return logisticaDao.obtenerNumProductos(idSalida);
	}
	
	@Override
	public List<ProductoBean> listarCatologoProductosNuevo(ProductoBean productoBean) throws Exception {
		return logisticaDao.listarCatologoProductosNuevo(productoBean);
	}
	
	@Override
	public List<ProductoBean> listarCatologoProductosControl(ProductoBean productoBean) throws Exception {
		return logisticaDao.listarCatologoProductosControl(productoBean);
	}
	
	@Override
	public ItemBean obtenerTransporteAlmacen(ItemBean itemBean) throws Exception {
		return logisticaDao.obtenerTransporteAlmacen(itemBean);
	}
	
	@Override
	public OrdenSalidaBean reportOrdenSalida(Integer idSalida, String anio) throws Exception {
		return logisticaDao.reportOrdenSalida(idSalida,anio);
	}
	
	@Override
	public List<StockConsultaBean> listarConsultaProductos(StockConsultaBean stockConsultaBean) throws Exception {
		return logisticaDao.listarConsultaProductos(stockConsultaBean);
	}
	
	@Override
	public List<ProductoBean> nroProductosXCompra(ProductoBean productoBean) throws Exception {
		return logisticaDao.nroProductosXCompra(productoBean);
	}
	
	@Override
	public List<OrdenIngresoBean> listarReporteSigaE(OrdenIngresoBean ordenIngresoBean) throws Exception {
		return logisticaDao.listarReporteSigaE(ordenIngresoBean);
	}
	
	@Override
	public List<OrdenIngresoBean> listarReporteSigaS(OrdenIngresoBean ordenIngresoBean) throws Exception {
		return logisticaDao.listarReporteSigaS(ordenIngresoBean);
	}
	
	@Override
	public List<StockConsultaBean> listarReporteStockDatos(StockConsultaBean stockConsultaBean) throws Exception {
		return logisticaDao.listarReporteStockDatos(stockConsultaBean);
	}
	
	@Override
	public StockConsultaBean listarReporteStockTitulo(StockConsultaBean stockConsultaBean) throws Exception {
		return logisticaDao.listarReporteStockTitulo(stockConsultaBean);
	}
}
