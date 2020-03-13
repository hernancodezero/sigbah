package pe.com.sigbah.service;

import java.util.List;

import pe.com.sigbah.common.bean.ConsultaPedidoCompraBean;
import pe.com.sigbah.common.bean.ConsultaProgramacionBean;
import pe.com.sigbah.common.bean.DeeBean;
import pe.com.sigbah.common.bean.DocumentoPedidoCompraBean;
import pe.com.sigbah.common.bean.EmergenciaBean;
import pe.com.sigbah.common.bean.ListaRespuestaConsultaPedidoBean;
import pe.com.sigbah.common.bean.ListaRespuestaConsultaProductoBean;
import pe.com.sigbah.common.bean.ListaRespuestaEmergenciaBean;
import pe.com.sigbah.common.bean.ListaRespuestaPedidoCompraBean;
import pe.com.sigbah.common.bean.ListaRespuestaRequerimientoBean;
import pe.com.sigbah.common.bean.PedidoCompraBean;
import pe.com.sigbah.common.bean.ProductoConsultaPedidoBean;
import pe.com.sigbah.common.bean.ProductoPedidoCompraBean;
import pe.com.sigbah.common.bean.ProductoRacionBean;
import pe.com.sigbah.common.bean.RacionBean;
import pe.com.sigbah.common.bean.ReporteConsultaProgramacionBean;
import pe.com.sigbah.common.bean.RequerimientoBean;
import pe.com.sigbah.common.bean.UbigeoDeeBean;
import pe.com.sigbah.common.bean.UbigeoIneiBean;

/**
 * @className: ProgramacionService.java
 * @description: Clase que contiene el consumo de los procedimientos del package BAH_PKG_PROGRAMACION.
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public interface ProgramacionService {
	
	
	/**
	 * @param controlCalidadBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<EmergenciaBean> listarEmergencia(EmergenciaBean emergenciaBean) throws Exception;
	
	/**
	 * @param idEmergencia
	 * @param codAnio
	 * @return
	 * @throws Exception
	 */
	public abstract ListaRespuestaEmergenciaBean obtenerRegistroEmergencia(Integer idEmergencia, String codAnio) throws Exception;

	
	/**
	 * @param requerimientoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<RequerimientoBean> listarRequerimiento(RequerimientoBean requerimientoBean) throws Exception;
	
	/**
	 * @param codAnio
	 * @return
	 * @throws Exception
	 */
	public abstract ListaRespuestaRequerimientoBean obtenerRequerimiento(String codAnio, String codDdi,Integer idRequerimiento) throws Exception;
	
	/**
	 * @param parametros
	 * @return
	 * @throws Exception
	 */
	public abstract RequerimientoBean obtenerCorrelativoRequerimiento(RequerimientoBean parametros ) throws Exception;
	
	
	/**
	 * @param requerimientoBean
	 * @return
	 * @throws Exception
	 */
	public abstract RequerimientoBean insertarRegistroRequerimiento(RequerimientoBean requerimientoBean) throws Exception;
	
	/**
	 * @param requerimientoBean
	 * @return
	 * @throws Exception
	 */
	public abstract RequerimientoBean actualizarRegistroRequerimiento(RequerimientoBean requerimientoBean) throws Exception;
	
	/**
	 * @param requerimientoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<EmergenciaBean> listarEmergenciasActivas(RequerimientoBean requerimientoBean) throws Exception;
	
	
	/**
	 * @param requerimientoBean
	 * @return
	 * @throws Exception
	 */
	public abstract EmergenciaBean pasarDistritos(EmergenciaBean emergenciaBean) throws Exception;
	
	/**
	 * @param ubigeoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<UbigeoIneiBean> listarUbigeoInei(UbigeoIneiBean ubigeoBean) throws Exception;
	
	
	/**
	 * @param ubigeoBean
	 * @return
	 * @throws Exception
	 */
	public abstract EmergenciaBean pasarDistritosUbigeo(EmergenciaBean emergenciaBean) throws Exception;
	
	/**
	 * @param racionBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<RacionBean> listarRaciones(RacionBean racionBean) throws Exception;
	
	
	/**
	 * @param racionBean
	 * @return
	 * @throws Exception
	 */
	public abstract RacionBean copiarRacion(RacionBean racionBean) throws Exception;
	
	/**
	 * @param parametros
	 * @return
	 * @throws Exception
	 */
	public abstract RacionBean obtenerCorrelativoRacion(RacionBean parametros ) throws Exception;
	
	/**
	 * @param racionBean
	 * @return
	 * @throws Exception
	 */
	public abstract RacionBean insertarRegistroRacion(RacionBean racionBean) throws Exception;
	
	/**
	 * @param racionBean
	 * @return
	 * @throws Exception
	 */
	public abstract RacionBean actualizarRegistroRacion(RacionBean racionBean) throws Exception;
	
	
	/**
	 * @param productoBean
	 * @return
	 * @throws Exception
	 */
	public abstract ProductoRacionBean insertarRegistroProducto(ProductoRacionBean productoBean) throws Exception;
	
	
	/**
	 * @param productoBean
	 * @return
	 * @throws Exception
	 */
	public abstract ProductoRacionBean actualizarRegistroProducto(ProductoRacionBean productoBean) throws Exception;
	
	
	
	/**
	 * @param pedidoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<PedidoCompraBean> listarPedidosCompra(PedidoCompraBean pedidoBean) throws Exception;
	
	/**
	 * @param racionBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoRacionBean> listarProductos(ProductoRacionBean racionBean) throws Exception;
	
	/**
	 * @param productoRacionBean
	 * @return
	 * @throws Exception
	 */
	public abstract ProductoRacionBean eliminarProductoRacion(ProductoRacionBean productoRacionBean) throws Exception;
	
	
	/**
	 * @param idControlCalidad
	 * @return
	 * @throws Exception
	 */
	public abstract RacionBean obtenerRegistroRacion(Integer idRacion) throws Exception;
	
	/**
	 * @param parametros
	 * @return
	 * @throws Exception
	 */
	public abstract PedidoCompraBean obtenerCorrelativoPedidoCompra(PedidoCompraBean parametros ) throws Exception;
	
	/**
	 * @param pedidoCompraBean
	 * @return
	 * @throws Exception
	 */
	public abstract PedidoCompraBean insertarRegistroPedido(PedidoCompraBean pedidoCompraBean) throws Exception;
	
	/**
	 * @param documentoPedidoCompraBean
	 * @return
	 * @throws Exception
	 */
	public abstract DocumentoPedidoCompraBean grabarDocumentoPedidoCompra(DocumentoPedidoCompraBean documentoPedidoCompraBean) throws Exception;
	
	/**
	 * @param documentoPedidoCompraBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<DocumentoPedidoCompraBean> listarDocumentoPedidoCompra(DocumentoPedidoCompraBean documentoPedidoCompraBean) throws Exception;
	
	/**
	 * @param documentoPedidoCompraBean
	 * @return
	 * @throws Exception
	 */
	public abstract DocumentoPedidoCompraBean eliminarDocumentoPedidoCompra(DocumentoPedidoCompraBean documentoPedidoCompraBean) throws Exception;
	
	/**
	 * @param productoPedidoCompraBean
	 * @return
	 * @throws Exception
	 */
	public abstract ProductoPedidoCompraBean grabarProductoPedidoCompra(ProductoPedidoCompraBean productoPedidoCompraBean) throws Exception;
	
	/**
	 * @param producto
	 * @return
	 * @throws Exception
	 */
	public abstract List<ProductoPedidoCompraBean> listarProductoPedidoCompra(ProductoPedidoCompraBean producto) throws Exception;
	
	/**
	 * @param productoPedidoCompraBean
	 * @return
	 * @throws Exception
	 */
	public abstract ProductoPedidoCompraBean eliminarProductoPedidoCompra(ProductoPedidoCompraBean productoPedidoCompraBean) throws Exception;
	
	/**
	 * @param idPedidoCompra
	 * @return
	 * @throws Exception
	 */
	public abstract PedidoCompraBean obtenerPedidoCompra(Integer idPedidoCompra) throws Exception;
	
	/**
	 * @param requerimientoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<EmergenciaBean> listarRequerimientoDetalle(RequerimientoBean requerimientoBean) throws Exception;
	
	/**
	 * @param emergenciaBean
	 * @return
	 * @throws Exception
	 */
	public abstract EmergenciaBean actualizarDamnificados(EmergenciaBean emergenciaBean) throws Exception;
	

	/**
	 * @param deeBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<DeeBean> listarDee(DeeBean deeBean) throws Exception;
	
	/**
	 * @param idDee
	 * @return
	 * @throws Exception
	 */
	public abstract DeeBean obtenerDee(Integer idDee) throws Exception;
	
	/**
	 * @param deeBean
	 * @return
	 * @throws Exception
	 */
	public abstract DeeBean insertarRegistroDee(DeeBean deeBean) throws Exception;
	
	/**
	 * @param ubigeoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<UbigeoIneiBean> listarUbigeoDee(UbigeoIneiBean ubigeoBean) throws Exception;
	
	/**
	 * @param DeeBean
	 * @return
	 * @throws Exception
	 */
	public abstract UbigeoDeeBean pasarDistritosUbigeoDee(DeeBean deeBean) throws Exception;
	
	
	/**
	 * @param requerimientoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<UbigeoDeeBean> listarDistritosEmergencia(UbigeoDeeBean ubigeoDeeBean) throws Exception;
	
	
	/**
	 * @param ubigeoDeeBean
	 * @return
	 * @throws Exception
	 */
	public abstract UbigeoDeeBean eliminarDistritoEmergencia(UbigeoDeeBean ubigeoDeeBean) throws Exception;
	
	
	/**
	 * @param idRequerimiento
	 * @return
	 * @throws Exception
	 */
	public abstract ListaRespuestaRequerimientoBean obtenerReporteRequerimiento(Integer  idRequerimiento) throws Exception;
	
	/**
	 * @param idPedidoCompra
	 * @return
	 * @throws Exception
	 */
	public abstract ListaRespuestaPedidoCompraBean obtenerReportePedidoCompra(Integer  idPedidoCompra) throws Exception;
	
	/**
	 * @param consultaBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ConsultaProgramacionBean> listarProg_x_mes(ConsultaProgramacionBean consultaBean) throws Exception;
	
	/**
	 * @param consultaBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ConsultaProgramacionBean> listarProg_x_dee(ConsultaProgramacionBean consultaBean) throws Exception;
	
	
	/**
	 * @param idProgramacion
	 * @return
	 * @throws Exception
	 */
	public abstract ListaRespuestaConsultaProductoBean obtenerConsultaProducto(Integer idProgramacion) throws Exception;
	
	/**
	 * @param consultaBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ConsultaPedidoCompraBean> listarConsultaPedidosCompra(ConsultaPedidoCompraBean consultaBean) throws Exception;
	
	public abstract List<ConsultaPedidoCompraBean> listarConsultaPedidosCompra2(ConsultaPedidoCompraBean consultaBean) throws Exception;
	
	public abstract ListaRespuestaConsultaPedidoBean obtenerConsultaPedido(ProductoConsultaPedidoBean pedido) throws Exception;
	
	public abstract ListaRespuestaConsultaPedidoBean mostrarProductosxPedido(ProductoConsultaPedidoBean pedido) throws Exception;
	
	public abstract ListaRespuestaEmergenciaBean obtenerReporteEmergencia(Integer  idPedidoCompra, String codAnio) throws Exception;

	/**
	 * @param consultaBean
	 * @return
	 * @throws Exception
	 */
	public abstract ReporteConsultaProgramacionBean obtenerReporteConsultaProg(ConsultaProgramacionBean consultaBean) throws Exception;

	/**
	 * @param consultaBean
	 * @return
	 * @throws Exception
	 */
	public abstract  ConsultaProgramacionBean grabarCorreosEnviados(ConsultaProgramacionBean consultaBean) throws Exception;
	
	
}
