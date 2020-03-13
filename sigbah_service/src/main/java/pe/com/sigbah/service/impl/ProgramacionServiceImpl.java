package pe.com.sigbah.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import pe.com.sigbah.dao.ProgramacionDao;
import pe.com.sigbah.service.ProgramacionService;

/**
 * @className: ProgramacionServiceImpl.java
 * @description: Clase que contiene el consumo de los procedimientos del package BAH_PKG_PROGRAMACION.
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
@Service
public class ProgramacionServiceImpl implements ProgramacionService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProgramacionDao programacionDao;

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#listarEmergencia(pe.com.sigbah.common.bean.EmergenciaBean)
	 */
	@Override
	public List<EmergenciaBean> listarEmergencia(EmergenciaBean emergenciaBean) throws Exception {
		return programacionDao.listarEmergencia(emergenciaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#obtenerRegistroEmergencia(java.lang.Integer)
	 */
	@Override
	public ListaRespuestaEmergenciaBean obtenerRegistroEmergencia(Integer idEmergencia, String codAnio) throws Exception {
		return programacionDao.obtenerRegistroEmergencia(idEmergencia,  codAnio);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#listarRequerimiento(pe.com.sigbah.common.bean.RequerimientoBean)
	 */
	@Override
	public List<RequerimientoBean> listarRequerimiento(RequerimientoBean requerimientoBean) throws Exception {
		return programacionDao.listarRequerimiento(requerimientoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#obtenerRequerimiento(java.lang.Integer, java.lang.String)
	 */
	@Override
	public ListaRespuestaRequerimientoBean obtenerRequerimiento( String codAnio, String codDdi,Integer idRequerimiento) throws Exception {
		return programacionDao.obtenerRequerimiento( codAnio,  codDdi, idRequerimiento );
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#obtenerCorrelativoRequerimiento(pe.com.sigbah.common.bean.RequerimientoBean)
	 */
	@Override
	public RequerimientoBean obtenerCorrelativoRequerimiento(RequerimientoBean parametros) throws Exception {
		return programacionDao.obtenerCorrelativoRequerimiento(parametros );
	}
	
	@Override
	public RequerimientoBean insertarRegistroRequerimiento(RequerimientoBean requerimiento) throws Exception {
		return programacionDao.insertarRegistroRequerimiento(requerimiento);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.LogisticaService#actualizarRegistroControlCalidad(pe.com.sigbah.common.bean.ControlCalidadBean)
	 */
	@Override
	public RequerimientoBean actualizarRegistroRequerimiento(RequerimientoBean requerimiento) throws Exception {
		return programacionDao.actualizarRegistroRequerimiento(requerimiento);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#listarEmergenciasActivas(pe.com.sigbah.common.bean.RequerimientoBean)
	 */
	@Override
	public List<EmergenciaBean> listarEmergenciasActivas(RequerimientoBean requerimientoBean) throws Exception {
		return programacionDao.listarEmergenciasActivas(requerimientoBean);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#pasarDistritos(pe.com.sigbah.common.bean.EmergenciaBean)
	 */
	@Override
	public EmergenciaBean pasarDistritos(EmergenciaBean emergenciaBean) throws Exception {
		return programacionDao.pasarDistritos(emergenciaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#listarUbigeoInei(pe.com.sigbah.common.bean.UbigeoIneiBean)
	 */
	@Override
	public List<UbigeoIneiBean> listarUbigeoInei(UbigeoIneiBean ubigeoBean) throws Exception {
		return programacionDao.listarUbigeoInei(ubigeoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#pasarDistritosUbigeo(pe.com.sigbah.common.bean.UbigeoIneiBean)
	 */
	@Override
	public EmergenciaBean pasarDistritosUbigeo(EmergenciaBean emergenciaBean) throws Exception {
		return programacionDao.pasarDistritosUbigeo(emergenciaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#listarRaciones(pe.com.sigbah.common.bean.RacionBean)
	 */
	@Override
	public List<RacionBean> listarRaciones(RacionBean racionBean) throws Exception {
		return programacionDao.listarRaciones(racionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#copiarRacion(pe.com.sigbah.common.bean.RacionBean)
	 */
	@Override
	public RacionBean copiarRacion(RacionBean racionBean) throws Exception {
		return programacionDao.copiarRacion(racionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#obtenerCorrelativoRacion(pe.com.sigbah.common.bean.RacionBean)
	 */
	@Override
	public RacionBean obtenerCorrelativoRacion(RacionBean parametros) throws Exception {
		return programacionDao.obtenerCorrelativoRacion(parametros);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#insertarRegistroRacion(pe.com.sigbah.common.bean.RacionBean)
	 */
	@Override
	public RacionBean insertarRegistroRacion(RacionBean racionBean) throws Exception {
		return programacionDao.insertarRegistroRacion(racionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#actualizarRegistroRacion(pe.com.sigbah.common.bean.RacionBean)
	 */
	@Override
	public RacionBean actualizarRegistroRacion(RacionBean racionBean) throws Exception {
		return programacionDao.actualizarRegistroRacion(racionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#insertarRegistroProducto(pe.com.sigbah.common.bean.ProductoRacionBean)
	 */
	@Override
	public ProductoRacionBean insertarRegistroProducto(ProductoRacionBean productoBean) throws Exception {
		return programacionDao.insertarRegistroProducto(productoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#actualizarRegistroProducto(pe.com.sigbah.common.bean.ProductoRacionBean)
	 */
	@Override
	public ProductoRacionBean actualizarRegistroProducto(ProductoRacionBean productoBean) throws Exception {
		return programacionDao.actualizarRegistroProducto(productoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#listarPedidosCompra(pe.com.sigbah.common.bean.PedidoCompraBean)
	 */
	@Override
	public List<PedidoCompraBean> listarPedidosCompra(PedidoCompraBean pedidoBean) throws Exception {
		return programacionDao.listarPedidosCompra(pedidoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#listarDee(pe.com.sigbah.common.bean.DeeBean)
	 */
	@Override
	public List<DeeBean> listarDee(DeeBean deeBean) throws Exception {
		return programacionDao.listarDee(deeBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#listarProductos(pe.com.sigbah.common.bean.ProductoRacionBean)
	 */
	@Override
	public List<ProductoRacionBean> listarProductos(ProductoRacionBean racionBean) throws Exception {
		return programacionDao.listarProductos(racionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#eliminarProductoRacion(pe.com.sigbah.common.bean.ProductoRacionBean)
	 */
	@Override
	public ProductoRacionBean eliminarProductoRacion(ProductoRacionBean productoRacionBean) throws Exception {
		return programacionDao.eliminarProductoRacion(productoRacionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#obtenerRegistroRacion(java.lang.Integer)
	 */
	@Override
	public RacionBean obtenerRegistroRacion(Integer idRacion) throws Exception {
		return programacionDao.obtenerRegistroRacion(idRacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#obtenerCorrelativoPedidoCompra(pe.com.sigbah.common.bean.PedidoCompraBean)
	 */
	@Override
	public PedidoCompraBean obtenerCorrelativoPedidoCompra(PedidoCompraBean parametros) throws Exception {
		return programacionDao.obtenerCorrelativoPedidoCompra(parametros);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#insertarRegistroPedido(pe.com.sigbah.common.bean.PedidoCompraBean)
	 */
	@Override
	public PedidoCompraBean insertarRegistroPedido(PedidoCompraBean pedidoCompraBean) throws Exception {
		return programacionDao.insertarRegistroPedido(pedidoCompraBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#grabarDocumentoPedidoCompra(pe.com.sigbah.common.bean.DocumentoPedidoCompraBean)
	 */
	@Override
	public DocumentoPedidoCompraBean grabarDocumentoPedidoCompra(DocumentoPedidoCompraBean documentoPedidoCompraBean)
			throws Exception {
		return programacionDao.grabarDocumentoPedidoCompra(documentoPedidoCompraBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#listarDocumentoPedidoCompra(pe.com.sigbah.common.bean.DocumentoPedidoCompraBean)
	 */
	@Override
	public List<DocumentoPedidoCompraBean> listarDocumentoPedidoCompra(
			DocumentoPedidoCompraBean documentoPedidoCompraBean) throws Exception {
		return programacionDao.listarDocumentoPedidoCompra(documentoPedidoCompraBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#eliminarDocumentoPedidoCompra(pe.com.sigbah.common.bean.DocumentoPedidoCompraBean)
	 */
	@Override
	public DocumentoPedidoCompraBean eliminarDocumentoPedidoCompra(DocumentoPedidoCompraBean documentoPedidoCompraBean)
			throws Exception {
		return programacionDao.eliminarDocumentoPedidoCompra(documentoPedidoCompraBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#grabarProductoPedidoCompra(pe.com.sigbah.common.bean.ProductoPedidoCompraBean)
	 */
	@Override
	public ProductoPedidoCompraBean grabarProductoPedidoCompra(ProductoPedidoCompraBean productoPedidoCompraBean)
			throws Exception {
		// TODO Auto-generated method stub
		return programacionDao.grabarProductoPedidoCompra(productoPedidoCompraBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#listarProductoPedidoCompra(pe.com.sigbah.common.bean.ProductoPedidoCompraBean)
	 */
	@Override
	public List<ProductoPedidoCompraBean> listarProductoPedidoCompra(ProductoPedidoCompraBean producto)
			throws Exception {
		return programacionDao.listarProductoPedidoCompra(producto);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#eliminarProductoPedidoCompra(pe.com.sigbah.common.bean.ProductoPedidoCompraBean)
	 */
	@Override
	public ProductoPedidoCompraBean eliminarProductoPedidoCompra(ProductoPedidoCompraBean productoPedidoCompraBean)
			throws Exception {
		return programacionDao.eliminarProductoPedidoCompra(productoPedidoCompraBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#obtenerPedidoCompra(java.lang.Integer)
	 */
	@Override
	public PedidoCompraBean obtenerPedidoCompra(Integer idPedidoCompra) throws Exception {
		return programacionDao.obtenerPedidoCompra(idPedidoCompra);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#listarRequerimientoDetalle(pe.com.sigbah.common.bean.RequerimientoBean)
	 */
	@Override
	public List<EmergenciaBean> listarRequerimientoDetalle(RequerimientoBean requerimientoBean) throws Exception {
		return programacionDao.listarRequerimientoDetalle(requerimientoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#actualizarDamnificados(pe.com.sigbah.common.bean.EmergenciaBean)
	 */
	@Override
	public EmergenciaBean actualizarDamnificados(EmergenciaBean emergenciaBean) throws Exception {
		return programacionDao.actualizarDamnificados(emergenciaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#obtenerDee(java.lang.Integer)
	 */
	@Override
	public DeeBean obtenerDee(Integer idDee) throws Exception {
		return programacionDao.obtenerDee(idDee);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#insertarRegistroDee(pe.com.sigbah.common.bean.DeeBean)
	 */
	@Override
	public DeeBean insertarRegistroDee(DeeBean deeBean) throws Exception {
		return programacionDao.insertarRegistroDee(deeBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#listarUbigeoDee(pe.com.sigbah.common.bean.UbigeoIneiBean)
	 */
	@Override
	public List<UbigeoIneiBean> listarUbigeoDee(UbigeoIneiBean ubigeoBean) throws Exception {
		return programacionDao.listarUbigeoDee(ubigeoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#pasarDistritosUbigeoDee(pe.com.sigbah.common.bean.DeeBean)
	 */
	@Override
	public UbigeoDeeBean pasarDistritosUbigeoDee(DeeBean deeBean) throws Exception {
		return programacionDao.pasarDistritosUbigeoDee(deeBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#listarDistritosEmergencia(pe.com.sigbah.common.bean.UbigeoDeeBean)
	 */
	@Override
	public List<UbigeoDeeBean> listarDistritosEmergencia(UbigeoDeeBean ubigeoDeeBean) throws Exception {
		return programacionDao.listarDistritosEmergencia(ubigeoDeeBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#eliminarDistritoEmergencia(pe.com.sigbah.common.bean.UbigeoDeeBean)
	 */
	@Override
	public UbigeoDeeBean eliminarDistritoEmergencia(UbigeoDeeBean ubigeoDeeBean) throws Exception {
		return programacionDao.eliminarDistritoEmergencia(ubigeoDeeBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#obtenerReporteRequerimiento(java.lang.Integer)
	 */
	@Override
	public ListaRespuestaRequerimientoBean obtenerReporteRequerimiento(Integer idRequerimiento) throws Exception {
		return programacionDao.obtenerReporteRequerimiento(idRequerimiento);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#obtenerReportePedidoCompra(java.lang.Integer)
	 */
	@Override
	public ListaRespuestaPedidoCompraBean obtenerReportePedidoCompra(Integer idPedidoCompra) throws Exception {
		return programacionDao.obtenerReportePedidoCompra(idPedidoCompra);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#listarProg_x_mes(pe.com.sigbah.common.bean.ConsultaProgramacionBean)
	 */
	@Override
	public List<ConsultaProgramacionBean> listarProg_x_mes(ConsultaProgramacionBean consultaBean) throws Exception {
		return programacionDao.listarProg_x_mes(consultaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#listarProg_x_dee(pe.com.sigbah.common.bean.ConsultaProgramacionBean)
	 */
	@Override
	public List<ConsultaProgramacionBean> listarProg_x_dee(ConsultaProgramacionBean consultaBean) throws Exception {
		return programacionDao.listarProg_x_dee(consultaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#ConsultaProducto(java.lang.Integer)
	 */
	@Override
	public ListaRespuestaConsultaProductoBean obtenerConsultaProducto(Integer idProgramacion) throws Exception {
		return programacionDao.obtenerConsultaProducto(idProgramacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#listarConsultaPedidosCompra(pe.com.sigbah.common.bean.ConsultaPedidoCompraBean)
	 */
	@Override
	public List<ConsultaPedidoCompraBean> listarConsultaPedidosCompra(ConsultaPedidoCompraBean consultaBean)throws Exception {
		return programacionDao.listarConsultaPedidosCompra(consultaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#listarConsultaPedidosCompra2(pe.com.sigbah.common.bean.ConsultaPedidoCompraBean)
	 */
	@Override
	public List<ConsultaPedidoCompraBean> listarConsultaPedidosCompra2(ConsultaPedidoCompraBean consultaBean)throws Exception {
		return programacionDao.listarConsultaPedidosCompra2(consultaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#obtenerConsultaPedido(java.lang.Integer)
	 */
	@Override
	public ListaRespuestaConsultaPedidoBean obtenerConsultaPedido(ProductoConsultaPedidoBean consulta) throws Exception {
		return programacionDao.obtenerConsultaPedido(consulta);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#mostrarProductosxPedido(pe.com.sigbah.common.bean.ProductoConsultaPedidoBean)
	 */
	@Override
	public ListaRespuestaConsultaPedidoBean mostrarProductosxPedido(ProductoConsultaPedidoBean pedido) throws Exception {
		return programacionDao.mostrarProductosxPedido(pedido);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionService#obtenerReporteEmergencia(java.lang.Integer, java.lang.String)
	 */
	@Override
	public ListaRespuestaEmergenciaBean obtenerReporteEmergencia(Integer idPedidoCompra, String codAnio)
			throws Exception {
		return programacionDao.obtenerReporteEmergencia(idPedidoCompra, codAnio);
	}
			
	@Override
	public ReporteConsultaProgramacionBean obtenerReporteConsultaProg(ConsultaProgramacionBean consultaBean)
			throws Exception {
		return programacionDao.obtenerReporteConsultaProg(consultaBean);
	}
			
	@Override
	public ConsultaProgramacionBean grabarCorreosEnviados(ConsultaProgramacionBean consultaBean)
			throws Exception {
		return programacionDao.grabarCorreosEnviados(consultaBean);
	}		
}
