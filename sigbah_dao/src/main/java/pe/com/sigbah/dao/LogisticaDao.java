package pe.com.sigbah.dao;

import java.util.List;

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

/**
 * @className: LogisticaDao.java
 * @description: Clase que contiene el consumo de los procedimientos del package BAH_PKG_LOGISTICA.
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public interface LogisticaDao {
	
	/**
	 * @param controlCalidadBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ControlCalidadBean> listarControlCalidad(ControlCalidadBean controlCalidadBean) throws Exception;
	
	/**
	 * @param controlCalidadBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ControlCalidadBean obtenerCorrelativoControlCalidad(ControlCalidadBean controlCalidadBean) throws Exception;
	
	/**
	 * @param controlCalidadBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ControlCalidadBean> listarAlmacenActivo(ControlCalidadBean controlCalidadBean) throws Exception;

	/**
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<OrdenCompraBean> listarOrdenCompra() throws Exception;

	/**
	 * @param controlCalidadBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract ControlCalidadBean insertarRegistroControlCalidad(ControlCalidadBean controlCalidadBean) throws Exception;

	/**
	 * @param controlCalidadBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract ControlCalidadBean actualizarRegistroControlCalidad(ControlCalidadBean controlCalidadBean) throws Exception;
	
	/**
	 * @param idControlCalidad
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ControlCalidadBean obtenerRegistroControlCalidad(Integer idControlCalidad) throws Exception;

	/**
	 * @param producto
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<ProductoControlCalidadBean> listarProductoControlCalidad(ProductoControlCalidadBean producto) throws Exception;

	/**
	 * @param productoControlCalidadBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract ProductoControlCalidadBean grabarProductoControlCalidad(ProductoControlCalidadBean productoControlCalidadBean) throws Exception;
	
	/**
	 * @param productoControlCalidadBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProductoControlCalidadBean eliminarProductoControlCalidad(ProductoControlCalidadBean productoControlCalidadBean) throws Exception;
	
	/**
	 * @param documentoControlCalidadBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<DocumentoControlCalidadBean> listarDocumentoControlCalidad(DocumentoControlCalidadBean documentoControlCalidadBean) throws Exception;

	/**
	 * @param documentoControlCalidadBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract DocumentoControlCalidadBean grabarDocumentoControlCalidad(DocumentoControlCalidadBean documentoControlCalidadBean) throws Exception;
	
	/**
	 * @param documentoControlCalidadBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract DocumentoControlCalidadBean eliminarDocumentoControlCalidad(DocumentoControlCalidadBean documentoControlCalidadBean) throws Exception;
	
	/**
	 * @param idControlCalidad
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<DetalleProductoControlCalidadBean> listarDetalleProductoControlCalidad(Integer idControlCalidad) throws Exception;
	
	/**
	 * @param ordenIngresoBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<OrdenIngresoBean> listarOrdenIngreso(OrdenIngresoBean ordenIngresoBean) throws Exception;
	
	/**
	 * @param ordenIngresoBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract OrdenIngresoBean obtenerCorrelativoOrdenIngreso(OrdenIngresoBean ordenIngresoBean) throws Exception;
	
	/**
	 * @param controlCalidadBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ControlCalidadBean> listarNroControlCalidad(ControlCalidadBean controlCalidadBean) throws Exception;

	/**
	 * @param ordenIngresoBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract OrdenIngresoBean grabarOrdenIngreso(OrdenIngresoBean ordenIngresoBean) throws Exception;
	
	/**
	 * @param idIngreso
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract OrdenIngresoBean obtenerRegistroOrdenIngreso(Integer idIngreso) throws Exception;
	
	/**
	 * @param productoIngresoBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<ProductoIngresoBean> listarProductoIngreso(ProductoIngresoBean productoIngresoBean) throws Exception;

	/**
	 * @param productoIngresoBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract ProductoIngresoBean grabarProductoIngreso(ProductoIngresoBean productoIngresoBean) throws Exception;
	
	/**
	 * @param productoIngresoBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProductoIngresoBean eliminarProductoIngreso(ProductoIngresoBean productoIngresoBean) throws Exception;
	
	/**
	 * @param loteProductoBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<LoteProductoBean> listarLoteProductos(LoteProductoBean loteProductoBean) throws Exception;

	/**
	 * @param documentoIngresoBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<DocumentoIngresoBean> listarDocumentoIngreso(DocumentoIngresoBean documentoIngresoBean) throws Exception;

	/**
	 * @param documentoIngresoBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract DocumentoIngresoBean grabarDocumentoIngreso(DocumentoIngresoBean documentoIngresoBean) throws Exception;

	/**
	 * @param documentoIngresoBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract DocumentoIngresoBean eliminarDocumentoIngreso(DocumentoIngresoBean documentoIngresoBean) throws Exception;
	
	/**
	 * @param ordenSalidaBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<OrdenSalidaBean> listarOrdenSalida(OrdenSalidaBean ordenSalidaBean) throws Exception;
	
	/**
	 * @param ordenSalidaBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract OrdenSalidaBean obtenerCorrelativoOrdenSalida(OrdenSalidaBean ordenSalidaBean) throws Exception;
	
	/**
	 * @param ordenSalidaBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract OrdenSalidaBean grabarOrdenSalida(OrdenSalidaBean ordenSalidaBean) throws Exception;
	
	/**
	 * @param idSalida
	 * @param anio 
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract OrdenSalidaBean obtenerRegistroOrdenSalida(Integer idSalida, String anio) throws Exception;
	
	/**
	 * @param productoSalidaBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<ProductoSalidaBean> listarProductoSalida(ProductoSalidaBean productoSalidaBean) throws Exception;

	/**
	 * @param productoSalidaBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract ProductoSalidaBean grabarProductoSalida(ProductoSalidaBean productoSalidaBean) throws Exception;
	
	/**
	 * @param productoSalidaBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProductoSalidaBean eliminarProductoSalida(ProductoSalidaBean productoSalidaBean) throws Exception;

	/**
	 * @param documentoSalidaBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<DocumentoSalidaBean> listarDocumentoSalida(DocumentoSalidaBean documentoSalidaBean) throws Exception;

	/**
	 * @param documentoSalidaBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract DocumentoSalidaBean grabarDocumentoSalida(DocumentoSalidaBean documentoSalidaBean) throws Exception;

	/**
	 * @param documentoSalidaBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract DocumentoSalidaBean eliminarDocumentoSalida(DocumentoSalidaBean documentoSalidaBean) throws Exception;
	
	/**
	 * @param proyectoManifiestoBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<ProyectoManifiestoBean> listarManifiesto(ProyectoManifiestoBean proyectoManifiestoBean) throws Exception;
	
	/**
	 * @param proyectoManifiestoBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ProyectoManifiestoBean> listarProyectoManifiesto(ProyectoManifiestoBean proyectoManifiestoBean) throws Exception;
	
	/**
	 * @param proyectoManifiestoBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProyectoManifiestoBean obtenerCorrelativoProyectoManifiesto(ProyectoManifiestoBean proyectoManifiestoBean) throws Exception;
	
	/**
	 * @param proyectoManifiestoBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract ProyectoManifiestoBean grabarProyectoManifiesto(ProyectoManifiestoBean proyectoManifiestoBean) throws Exception;
	
	/**
	 * @param idProyecto
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProyectoManifiestoBean obtenerRegistroProyectoManifiesto(Integer idProyecto) throws Exception;
	
	/**
	 * @param productoProyectoManifiestoBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<ProductoProyectoManifiestoBean> listarProductoProyectoManifiesto(ProductoProyectoManifiestoBean productoProyectoManifiestoBean) throws Exception;

	/**
	 * @param productoProyectoManifiestoBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract ProductoProyectoManifiestoBean grabarProductoProyectoManifiesto(ProductoProyectoManifiestoBean productoProyectoManifiestoBean) throws Exception;
	
	/**
	 * @param productoProyectoManifiestoBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProductoProyectoManifiestoBean eliminarProductoProyectoManifiesto(ProductoProyectoManifiestoBean productoProyectoManifiestoBean) throws Exception;

	/**
	 * @param documentoProyectoManifiestoBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<DocumentoProyectoManifiestoBean> listarDocumentoProyectoManifiesto(DocumentoProyectoManifiestoBean documentoProyectoManifiestoBean) throws Exception;

	/**
	 * @param documentoProyectoManifiestoBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract DocumentoProyectoManifiestoBean grabarDocumentoProyectoManifiesto(DocumentoProyectoManifiestoBean documentoProyectoManifiestoBean) throws Exception;

	/**
	 * @param documentoProyectoManifiestoBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract DocumentoProyectoManifiestoBean eliminarDocumentoProyectoManifiesto(DocumentoProyectoManifiestoBean documentoProyectoManifiestoBean) throws Exception;
	
	/**
	 * @param idProyecto
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract int verificarProductosProgramacion(Integer idProyecto) throws Exception;

	/**
	 * @param proyectoManifiestoBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract String procesarManifiestoProducto(ProyectoManifiestoBean proyectoManifiestoBean) throws Exception;
	
	/**
	 * @param manifiestoVehiculoBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<ManifiestoVehiculoBean> listarManifiestoVehiculo(ManifiestoVehiculoBean manifiestoVehiculoBean) throws Exception;

	/**
	 * @param manifiestoVehiculoBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract ManifiestoVehiculoBean procesarManifiestoVehiculo(ManifiestoVehiculoBean manifiestoVehiculoBean) throws Exception;

	/**
	 * @param guiaRemisionBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<GuiaRemisionBean> listarGuiaRemision(GuiaRemisionBean guiaRemisionBean) throws Exception;

	/**
	 * @param idGuiaRemision
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract GuiaRemisionBean obtenerRegistroGuiaRemision(Integer idGuiaRemision) throws Exception;

	/**
	 * @param guiaRemisionBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract GuiaRemisionBean insertarGuiaRemision(GuiaRemisionBean guiaRemisionBean) throws Exception;
	
	/**
	 * @param guiaRemisionBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract GuiaRemisionBean anularGuiaRemision(GuiaRemisionBean guiaRemisionBean) throws Exception;
	
	/**
	 * @param guiaRemisionBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract GuiaRemisionBean actualizarGuiaRemision(GuiaRemisionBean guiaRemisionBean) throws Exception;

	/**
	 * @param idGuiaRemision
	 * @param tipoOrigen
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<DetalleGuiaRemisionBean> listarDetalleGuiaRemision(Integer idGuiaRemision, String tipoOrigen) throws Exception;

	/**
	 * @param idGuiaRemision
	 * @param tipoOrigen
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<DetalleManifiestoCargaBean> listarDetalleManifiestoCarga(Integer idGuiaRemision, String tipoOrigen) throws Exception;
	
	/**
	 * @param idGuiaRemision
	 * @param tipoOrigen
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<DetalleActaEntregaBean> listarDetalleActaEntrega(Integer idGuiaRemision, String tipoOrigen) throws Exception;

	/**
	 * @param stockAlmacenBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<StockAlmacenBean> listarStockAlmacen(StockAlmacenBean stockAlmacenBean) throws Exception;

	/**
	 * @param stockAlmacenBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract StockAlmacenBean obtenerRegistroStockAlmacen(StockAlmacenBean stockAlmacenBean) throws Exception;

	/**
	 * @param stockAlmacenBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract StockAlmacenBean actualizarStockAlmacen(StockAlmacenBean stockAlmacenBean) throws Exception;
	
	/**
	 * @param stockAlmacenBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract ProductoStockAlmacenBean obtenerProductoStockAlmacen(StockAlmacenBean stockAlmacenBean) throws Exception;
	
	/**
	 * @param stockAlmacenLoteBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<StockAlmacenLoteBean> listarStockAlmacenLote(StockAlmacenLoteBean stockAlmacenLoteBean) throws Exception;
	
	/**
	 * @param stockAlmacenLoteBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract StockAlmacenLoteBean actualizarStockAlmacenLote(StockAlmacenLoteBean stockAlmacenLoteBean) throws Exception;

	/**
	 * @param cartillaInventarioBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<CartillaInventarioBean> listarCartillaInventario(CartillaInventarioBean cartillaInventarioBean) throws Exception;

	/**
	 * @param idCartilla
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract CartillaInventarioBean obtenerRegistroCartillaInventario(Integer idCartilla) throws Exception;

	/**
	 * @param cartillaInventarioBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract CartillaInventarioBean obtenerCorrelativoCartillaInventario(CartillaInventarioBean cartillaInventarioBean) throws Exception;

	/**
	 * @param cartillaInventarioBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract CartillaInventarioBean grabarCartillaInventario(CartillaInventarioBean cartillaInventarioBean) throws Exception;

	/**
	 * @param productoCartillaInventarioBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<ProductoCartillaInventarioBean> listarProductoCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception;

	/**
	 * @param productoCartillaInventarioBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProductoCartillaInventarioBean grabarProductoCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception;

	/**
	 * @param productoCartillaInventarioBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProductoCartillaInventarioBean eliminarProductoCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception;
	
	/**
	 * @param productoCartillaInventarioBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProductoCartillaInventarioBean procesarProductosCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception;
	
	/**
	 * @param stockAlmacenProductoBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<StockAlmacenProductoBean> listarStockAlmacenProducto(StockAlmacenProductoBean stockAlmacenProductoBean) throws Exception;
	
	/**
	 * @param stockAlmacenProductoLoteBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<StockAlmacenProductoLoteBean> listarStockAlmacenProductoLote(StockAlmacenProductoLoteBean stockAlmacenProductoLoteBean) throws Exception;
	
	/**
	 * @param productoCartillaInventarioBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProductoCartillaInventarioBean actualizarAjusteProductoCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception;
	
	/**
	 * @param productoCartillaInventarioBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProductoCartillaInventarioBean procesarAjusteProductoCartillaInventario(ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception;
	
	/**
	 * @param estadoCartillaInventarioBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<EstadoCartillaInventarioBean> listarEstadoCartillaInventario(EstadoCartillaInventarioBean estadoCartillaInventarioBean) throws Exception;
	
	/**
	 * @param estadoCartillaInventarioBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<EstadoCartillaInventarioBean> obtenerEstadosCartillaInventario(EstadoCartillaInventarioBean estadoCartillaInventarioBean) throws Exception;
	
	/**
	 * @param estadoCartillaInventarioBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract EstadoCartillaInventarioBean grabarEstadoCartillaInventario(EstadoCartillaInventarioBean estadoCartillaInventarioBean) throws Exception;

	/**
	 * @param cierreStockBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<CierreStockBean> listarCierreStock(CierreStockBean cierreStockBean) throws Exception;

	/**
	 * @param cierreStockBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract CierreStockBean obtenerRegistroCierreStock(CierreStockBean cierreStockBean) throws Exception;

	/**
	 * @param cierreStockBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract CierreStockBean grabarCierreStock(CierreStockBean cierreStockBean) throws Exception;
	
	/**
	 * @param stockProductoKardexBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<StockProductoKardexBean> listarStockProductoKardex(StockProductoKardexBean stockProductoKardexBean) throws Exception;
	
	/**
	 * @param stockProductoLoteBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<StockProductoLoteBean> listarStockProductoLote(StockProductoLoteBean stockProductoLoteBean) throws Exception;
	
	/**
	 * @param ordenSalidaBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<OrdenSalidaBean> listarReporteOrdenSalida(OrdenSalidaBean ordenSalidaBean) throws Exception;
	
	/**
	 * @param productoSalidaBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<ProductoSalidaBean> listarReporteDetalleOrdenSalida(ProductoSalidaBean productoSalidaBean) throws Exception;
	
	/**
	 * @param proyectoManifiestoBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ProyectoManifiestoBean> listarReporteProyectoManifiesto(ProyectoManifiestoBean proyectoManifiestoBean) throws Exception;
	
	/**
	 * @param productoProyectoManifiestoBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<ProductoProyectoManifiestoBean> listarReporteDetalleProyectoManifiesto(ProductoProyectoManifiestoBean productoProyectoManifiestoBean) throws Exception;
	
	/**
	 * @param ordenIngresoBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<OrdenIngresoBean> listarReporteOrdenIngreso(OrdenIngresoBean ordenIngresoBean) throws Exception;
	
	/**
	 * @param productoIngresoBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<ProductoIngresoBean> listarReporteDetalleOrdenIngreso(ProductoIngresoBean productoIngresoBean) throws Exception;
	
	/**
	 * @param guiaRemisionBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<GuiaRemisionBean> listarReporteGuiaRemision(GuiaRemisionBean guiaRemisionBean) throws Exception;
	
	/**
	 * @param detalleGuiaRemisionBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<DetalleGuiaRemisionBean> listarReporteDetalleGuiaRemision(DetalleGuiaRemisionBean detalleGuiaRemisionBean) throws Exception;
	
	/**
	 * @param kardexAlmacenBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<KardexAlmacenBean> listarReporteKardex(KardexAlmacenBean kardexAlmacenBean) throws Exception;
	
	/**
	 * @param bincardAlmacenBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<BincardAlmacenBean> listarReporteBincard(BincardAlmacenBean bincardAlmacenBean) throws Exception;
	
	/**
	 * @param productoBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<ProductoBean> listarProductosStock(ProductoBean productoBean) throws Exception;
	
	/**
	 * @param loteProductoBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<LoteProductoBean> listarLoteProductoSalida(LoteProductoBean loteProductoBean) throws Exception;
	
	/**
	 * @param productoProyectoManifiestoBean
	 * @return Lista de registros.
	 * @throws Exception 
	 */
	public abstract List<ProductoProyectoManifiestoBean> listarProductoManifiestoSalida(ProductoProyectoManifiestoBean productoProyectoManifiestoBean) throws Exception;

	/**
	 * @param productoProyectoManifiestoBean
	 * @return Objeto.
	 * @throws Exception 
	 */
	public abstract ProductoProyectoManifiestoBean grabarProductoManifiestoSalida(ProductoProyectoManifiestoBean productoProyectoManifiestoBean) throws Exception;

	/**
	 * @param stockAlmacenBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<StockAlmacenBean> listarConsultaStockAlmacen(StockAlmacenBean stockAlmacenBean) throws Exception;

	/**
	 * @param productoIngresoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoIngresoBean> listarHistorialIngreso(ProductoIngresoBean productoIngresoBean) throws Exception;

	/**
	 * @param productoIngresoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoIngresoBean> listarHistorialSalida(ProductoIngresoBean productoIngresoBean) throws Exception;

	/**
	 * @param productoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoBean> listarProductosFechaVencimiento(ProductoBean productoBean) throws Exception;

	/**
	 * @param productoCartillaInventarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract ProductoCartillaInventarioBean procesarAjusteCartilla(ProductoCartillaInventarioBean productoCartillaInventarioBean)
			throws Exception;

	/**
	 * @param productoCartillaInventarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoCartillaInventarioBean> listarReporteCartillaInventario(
			ProductoCartillaInventarioBean productoCartillaInventarioBean) throws Exception;

	/**
	 * @param itemBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarSalida(ItemBean itemBean) throws Exception;

	/**
	 * @param idSalida
	 * @return
	 * @throws Exception
	 */
	public abstract ItemBean obtenerNumProductos(Integer idSalida) throws Exception;

	/**
	 * @param productoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoBean> listarCatologoProductosNuevo(ProductoBean productoBean) throws Exception;

	/**
	 * @param productoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoBean> listarCatologoProductosControl(ProductoBean productoBean) throws Exception;

	/**
	 * @param itemBean
	 * @return
	 * @throws Exception
	 */
	public abstract ItemBean obtenerTransporteAlmacen(ItemBean itemBean) throws Exception;

	/**
	 * @param idSalida
	 * @param anio
	 * @return
	 * @throws Exception
	 */
	public abstract OrdenSalidaBean reportOrdenSalida(Integer idSalida, String anio) throws Exception;

	/**
	 * @param stockConsultaBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<StockConsultaBean> listarConsultaProductos(StockConsultaBean stockConsultaBean) throws Exception;

	/**
	 * @param productoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoBean> nroProductosXCompra(ProductoBean productoBean) throws Exception;

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
	 * @param stockConsultaBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<StockConsultaBean> listarReporteStockDatos(StockConsultaBean stockConsultaBean) throws Exception;

	/**
	 * @param stockConsultaBean
	 * @return
	 * @throws Exception
	 */
	public abstract StockConsultaBean listarReporteStockTitulo(StockConsultaBean stockConsultaBean) throws Exception;
	
}
