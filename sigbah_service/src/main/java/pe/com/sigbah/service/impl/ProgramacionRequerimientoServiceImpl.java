package pe.com.sigbah.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sigbah.common.bean.CorreoBean;
import pe.com.sigbah.common.bean.DocumentoProgramacionBean;
import pe.com.sigbah.common.bean.EstadoProgramacionBean;
import pe.com.sigbah.common.bean.EstadoUsuarioBean;
import pe.com.sigbah.common.bean.ProductoAlimentoBean;
import pe.com.sigbah.common.bean.ProductoNoAlimentarioBean;
import pe.com.sigbah.common.bean.ProductoNoAlimentarioProgramacionBean;
import pe.com.sigbah.common.bean.ProgramacionAlimentoBean;
import pe.com.sigbah.common.bean.ProgramacionAlmacenBean;
import pe.com.sigbah.common.bean.ProgramacionBean;
import pe.com.sigbah.common.bean.ProgramacionNoAlimentarioBean;
import pe.com.sigbah.common.bean.RacionOperativaBean;
import pe.com.sigbah.common.bean.RequerimientoBean;
import pe.com.sigbah.common.bean.ResumenStockAlimentoBean;
import pe.com.sigbah.common.bean.ResumenStockNoAlimentarioBean;
import pe.com.sigbah.dao.ProgramacionRequerimientoDao;
import pe.com.sigbah.service.ProgramacionRequerimientoService;

/**
 * @className: ProgramacionRequerimientoServiceImpl.java
 * @description: 
 * @date: 29/07/2017
 * @author: Junior Huaman Flores.
 */
@Service
public class ProgramacionRequerimientoServiceImpl implements ProgramacionRequerimientoService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProgramacionRequerimientoDao programacionRequerimientoDao;

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#listarProgramacion(pe.com.sigbah.common.bean.ProgramacionBean)
	 */
	@Override
	public List<ProgramacionBean> listarProgramacion(ProgramacionBean programacionBean) throws Exception {
		return programacionRequerimientoDao.listarProgramacion(programacionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#obtenerCorrelativoProgramacion(pe.com.sigbah.common.bean.ProgramacionBean)
	 */
	@Override
	public ProgramacionBean obtenerCorrelativoProgramacion(ProgramacionBean programacionBean) throws Exception {
		return programacionRequerimientoDao.obtenerCorrelativoProgramacion(programacionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#obtenerRegistroProgramacion(java.lang.Integer)
	 */
	@Override
	public ProgramacionBean obtenerRegistroProgramacion(Integer idProgramacion) throws Exception {
		return programacionRequerimientoDao.obtenerRegistroProgramacion(idProgramacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#listarRequerimiento(pe.com.sigbah.common.bean.RequerimientoBean)
	 */
	@Override
	public List<RequerimientoBean> listarRequerimiento(RequerimientoBean requerimientoBean) throws Exception {
		return programacionRequerimientoDao.listarRequerimiento(requerimientoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#listarRacionOperativa(pe.com.sigbah.common.bean.RacionOperativaBean)
	 */
	@Override
	public List<RacionOperativaBean> listarRacionOperativa(RacionOperativaBean racionOperativaBean) throws Exception {
		return programacionRequerimientoDao.listarRacionOperativa(racionOperativaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#grabarEstadoProgramacion(pe.com.sigbah.common.bean.EstadoProgramacionBean)
	 */
	@Override
	public EstadoProgramacionBean grabarEstadoProgramacion(EstadoProgramacionBean estadoProgramacionBean) throws Exception {
		return programacionRequerimientoDao.grabarEstadoProgramacion(estadoProgramacionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#listarEstadoProgramacion(pe.com.sigbah.common.bean.EstadoProgramacionBean)
	 */
	@Override
	public List<EstadoProgramacionBean> listarEstadoProgramacion(EstadoProgramacionBean estadoProgramacionBean) throws Exception {
		return programacionRequerimientoDao.listarEstadoProgramacion(estadoProgramacionBean);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#grabarProgramacion(pe.com.sigbah.common.bean.ProgramacionBean)
	 */
	@Override
	public ProgramacionBean grabarProgramacion(ProgramacionBean programacionBean) throws Exception {
		return programacionRequerimientoDao.grabarProgramacion(programacionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#listarProgramacionAlmacen(pe.com.sigbah.common.bean.ProgramacionAlmacenBean)
	 */
	@Override
	public List<ProgramacionAlmacenBean> listarProgramacionAlmacen(ProgramacionAlmacenBean programacionAlmacenBean) throws Exception {
		return programacionRequerimientoDao.listarProgramacionAlmacen(programacionAlmacenBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#grabarProgramacionAlmacen(pe.com.sigbah.common.bean.ProgramacionAlmacenBean)
	 */
	@Override
	public ProgramacionAlmacenBean grabarProgramacionAlmacen(ProgramacionAlmacenBean programacionAlmacenBean) throws Exception {
		return programacionRequerimientoDao.grabarProgramacionAlmacen(programacionAlmacenBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#eliminarProgramacionAlmacen(pe.com.sigbah.common.bean.ProgramacionAlmacenBean)
	 */
	@Override
	public ProgramacionAlmacenBean eliminarProgramacionAlmacen(ProgramacionAlmacenBean programacionAlmacenBean) throws Exception {
		return programacionRequerimientoDao.eliminarProgramacionAlmacen(programacionAlmacenBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#listarProgramacionRacionOperativa(java.lang.Integer)
	 */
	@Override
	public List<RacionOperativaBean> listarProgramacionRacionOperativa(Integer idRacionOperativa) throws Exception {
		return programacionRequerimientoDao.listarProgramacionRacionOperativa(idRacionOperativa);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#actualizarProgramacionRacionOperativa(pe.com.sigbah.common.bean.RacionOperativaBean)
	 */
	@Override
	public RacionOperativaBean actualizarProgramacionRacionOperativa(RacionOperativaBean racionOperativaBean) throws Exception {
		return programacionRequerimientoDao.actualizarProgramacionRacionOperativa(racionOperativaBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#listarProgramacionAlimento(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<ProgramacionAlimentoBean> listarProgramacionAlimento(Integer idProgramacion, List<Integer> arrIdProducto) throws Exception {
		return programacionRequerimientoDao.listarProgramacionAlimento(idProgramacion, arrIdProducto);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#listarResumenStockAlimento(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<ResumenStockAlimentoBean> listarResumenStockAlimento(Integer idProgramacion, Integer idRacionOperativa) throws Exception {
		return programacionRequerimientoDao.listarResumenStockAlimento(idProgramacion, idRacionOperativa);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#actualizarDetalleProgramacionAlimento(pe.com.sigbah.common.bean.ProductoAlimentoBean)
	 */
	@Override
	public ProductoAlimentoBean actualizarDetalleProgramacionAlimento(ProductoAlimentoBean productoAlimentoBean) throws Exception {
		return programacionRequerimientoDao.actualizarDetalleProgramacionAlimento(productoAlimentoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#eliminarDetalleProgramacionAlimento(pe.com.sigbah.common.bean.ProgramacionAlimentoBean)
	 */
	@Override
	public ProgramacionAlimentoBean eliminarDetalleProgramacionAlimento(ProgramacionAlimentoBean programacionAlimentoBean) throws Exception {
		return programacionRequerimientoDao.eliminarDetalleProgramacionAlimento(programacionAlimentoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#eliminarProgramacionAlimento(pe.com.sigbah.common.bean.ProgramacionBean)
	 */
	@Override
	public ProgramacionBean eliminarProgramacionAlimento(ProgramacionBean programacionBean) throws Exception {
		return programacionRequerimientoDao.eliminarProgramacionAlimento(programacionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#listarProductoNoAlimentarioProgramacion(java.lang.Integer)
	 */
	@Override
	public List<ProductoNoAlimentarioProgramacionBean> listarProductoNoAlimentarioProgramacion(Integer idProgramacion) throws Exception {
		return programacionRequerimientoDao.listarProductoNoAlimentarioProgramacion(idProgramacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#grabarProductoNoAlimentarioProgramacion(pe.com.sigbah.common.bean.ProductoNoAlimentarioProgramacionBean)
	 */
	@Override
	public ProductoNoAlimentarioProgramacionBean grabarProductoNoAlimentarioProgramacion(ProductoNoAlimentarioProgramacionBean productoNoAlimentarioProgramacionBean) throws Exception {
		return programacionRequerimientoDao.grabarProductoNoAlimentarioProgramacion(productoNoAlimentarioProgramacionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#eliminarProductoNoAlimentarioProgramacion(pe.com.sigbah.common.bean.ProductoNoAlimentarioProgramacionBean)
	 */
	@Override
	public ProductoNoAlimentarioProgramacionBean eliminarProductoNoAlimentarioProgramacion(ProductoNoAlimentarioProgramacionBean productoNoAlimentarioProgramacionBean) throws Exception {
		return programacionRequerimientoDao.eliminarProductoNoAlimentarioProgramacion(productoNoAlimentarioProgramacionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#actualizarProgramacionNoAlimentario(pe.com.sigbah.common.bean.ProductoNoAlimentarioProgramacionBean)
	 */
	@Override
	public ProductoNoAlimentarioProgramacionBean actualizarProgramacionNoAlimentario(ProductoNoAlimentarioProgramacionBean productoNoAlimentarioProgramacionBean) throws Exception {
		return programacionRequerimientoDao.actualizarProgramacionNoAlimentario(productoNoAlimentarioProgramacionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#listarProgramacionNoAlimentario(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<ProgramacionNoAlimentarioBean> listarProgramacionNoAlimentario(Integer idProgramacion, List<Integer> arrIdProducto) throws Exception {
		return programacionRequerimientoDao.listarProgramacionNoAlimentario(idProgramacion, arrIdProducto);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#listarResumenStockNoAlimentario(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<ResumenStockNoAlimentarioBean> listarResumenStockNoAlimentario(Integer idProgramacion, Integer idRacionOperativa) throws Exception {
		return programacionRequerimientoDao.listarResumenStockNoAlimentario(idProgramacion, idRacionOperativa);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#actualizarDetalleProgramacionNoAlimentario(pe.com.sigbah.common.bean.ProductoNoAlimentarioBean)
	 */
	@Override
	public ProductoNoAlimentarioBean actualizarDetalleProgramacionNoAlimentario(ProductoNoAlimentarioBean productoNoAlimentarioBean) throws Exception {
		return programacionRequerimientoDao.actualizarDetalleProgramacionNoAlimentario(productoNoAlimentarioBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#eliminarDetalleProgramacionNoAlimentario(pe.com.sigbah.common.bean.ProgramacionNoAlimentarioBean)
	 */
	@Override
	public ProgramacionNoAlimentarioBean eliminarDetalleProgramacionNoAlimentario(ProgramacionNoAlimentarioBean programacionNoAlimentarioBean) throws Exception {
		return programacionRequerimientoDao.eliminarDetalleProgramacionNoAlimentario(programacionNoAlimentarioBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#listarDocumentoProgramacion(pe.com.sigbah.common.bean.DocumentoProgramacionBean)
	 */
	@Override
	public List<DocumentoProgramacionBean> listarDocumentoProgramacion(DocumentoProgramacionBean documentoProgramacionBean) throws Exception {
		return programacionRequerimientoDao.listarDocumentoProgramacion(documentoProgramacionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#grabarDocumentoProgramacion(pe.com.sigbah.common.bean.DocumentoProgramacionBean)
	 */
	@Override
	public DocumentoProgramacionBean grabarDocumentoProgramacion(DocumentoProgramacionBean documentoProgramacionBean) throws Exception {
		return programacionRequerimientoDao.grabarDocumentoProgramacion(documentoProgramacionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#eliminarDocumentoProgramacion(pe.com.sigbah.common.bean.DocumentoProgramacionBean)
	 */
	@Override
	public DocumentoProgramacionBean eliminarDocumentoProgramacion(DocumentoProgramacionBean documentoProgramacionBean) throws Exception {
		return programacionRequerimientoDao.eliminarDocumentoProgramacion(documentoProgramacionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#obtenerRegistroCabeceraProgramacion(java.lang.Integer)
	 */
	@Override
	public ProgramacionBean obtenerRegistroCabeceraProgramacion(Integer idProgramacion) throws Exception {
		return programacionRequerimientoDao.obtenerRegistroCabeceraProgramacion(idProgramacion);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#listarTotalProductoAlimento(java.lang.Integer, java.lang.Integer, java.util.List)
	 */
	@Override
	public List<ProgramacionAlimentoBean> listarTotalProductoAlimento(Integer idProgramacion, Integer idRacionOperativa, List<Integer> arrIdProducto) throws Exception {
		return programacionRequerimientoDao.listarTotalProductoAlimento(idProgramacion, idRacionOperativa, arrIdProducto);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#listarEstadoUsuario(pe.com.sigbah.common.bean.EstadoUsuarioBean)
	 */
	@Override
	public List<EstadoUsuarioBean> listarEstadoUsuario(EstadoUsuarioBean estadoUsuarioBean) throws Exception {
		return programacionRequerimientoDao.listarEstadoUsuario(estadoUsuarioBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.ProgramacionRequerimientoService#listarCorreoDestino(pe.com.sigbah.common.bean.CorreoBean)
	 */
	@Override
	public List<CorreoBean> listarCorreoDestino(CorreoBean correoBean) throws Exception {
		return programacionRequerimientoDao.listarCorreoDestino(correoBean);
	}
	
	@Override
	public RequerimientoBean eliminarRequerimientoEdan(RequerimientoBean requerimientoBean) throws Exception {
		return programacionRequerimientoDao.eliminarRequerimientoEdan(requerimientoBean);
	}
	
	@Override
	public ProgramacionBean insertarUbigeoRacion(ProgramacionBean programacionBean) throws Exception {
		return programacionRequerimientoDao.insertarUbigeoRacion(programacionBean);
	}
	
	@Override
	public List<CorreoBean> listarCorreoProg(CorreoBean correoBean) throws Exception {
		return programacionRequerimientoDao.listarCorreoProg(correoBean);
	}

}
