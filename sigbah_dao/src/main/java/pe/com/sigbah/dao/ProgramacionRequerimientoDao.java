package pe.com.sigbah.dao;

import java.util.List;

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

/**
 * @className: ProgramacionRequerimientoDao.java
 * @description: 
 * @date: 29/07/2017
 * @author: Junior Huaman Flores.
 */
public interface ProgramacionRequerimientoDao {
	
	/**
	 * @param programacionBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ProgramacionBean> listarProgramacion(ProgramacionBean programacionBean) throws Exception;

	/**
	 * @param programacionBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProgramacionBean obtenerCorrelativoProgramacion(ProgramacionBean programacionBean) throws Exception;

	/**
	 * @param idProgramacion
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProgramacionBean obtenerRegistroProgramacion(Integer idProgramacion) throws Exception;
	
	/**
	 * @param requerimientoBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<RequerimientoBean> listarRequerimiento(RequerimientoBean requerimientoBean) throws Exception;
	
	/**
	 * @param racionOperativaBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<RacionOperativaBean> listarRacionOperativa(RacionOperativaBean racionOperativaBean) throws Exception;
	
	/**
	 * @param estadoProgramacionBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract EstadoProgramacionBean grabarEstadoProgramacion(EstadoProgramacionBean estadoProgramacionBean) throws Exception;

	/**
	 * @param estadoProgramacionBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<EstadoProgramacionBean> listarEstadoProgramacion(EstadoProgramacionBean estadoProgramacionBean) throws Exception;
	
	/**
	 * @param programacionBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProgramacionBean grabarProgramacion(ProgramacionBean programacionBean) throws Exception;
	
	/**
	 * @param programacionAlmacenBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ProgramacionAlmacenBean> listarProgramacionAlmacen(ProgramacionAlmacenBean programacionAlmacenBean) throws Exception;
	
	/**
	 * @param programacionAlmacenBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProgramacionAlmacenBean grabarProgramacionAlmacen(ProgramacionAlmacenBean programacionAlmacenBean) throws Exception;

	/**
	 * @param programacionAlmacenBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProgramacionAlmacenBean eliminarProgramacionAlmacen(ProgramacionAlmacenBean programacionAlmacenBean) throws Exception;
	
	/**
	 * @param idRacionOperativa
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<RacionOperativaBean> listarProgramacionRacionOperativa(Integer idRacionOperativa) throws Exception;
	
	/**
	 * @param racionOperativaBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract RacionOperativaBean actualizarProgramacionRacionOperativa(RacionOperativaBean racionOperativaBean) throws Exception;
	
	/**
	 * @param idProgramacion
	 * @param arrIdProducto
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ProgramacionAlimentoBean> listarProgramacionAlimento(Integer idProgramacion, List<Integer> arrIdProducto) throws Exception;

	/**
	 * @param idProgramacion
	 * @param idRacionOperativa
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ResumenStockAlimentoBean> listarResumenStockAlimento(Integer idProgramacion, Integer idRacionOperativa) throws Exception;
	
	/**
	 * @param productoAlimentoBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProductoAlimentoBean actualizarDetalleProgramacionAlimento(ProductoAlimentoBean productoAlimentoBean) throws Exception;
	
	/**
	 * @param programacionAlimentoBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProgramacionAlimentoBean eliminarDetalleProgramacionAlimento(ProgramacionAlimentoBean programacionAlimentoBean) throws Exception;
	
	/**
	 * @param programacionBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProgramacionBean eliminarProgramacionAlimento(ProgramacionBean programacionBean) throws Exception;
	
	/**
	 * @param idProgramacion
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ProductoNoAlimentarioProgramacionBean> listarProductoNoAlimentarioProgramacion(Integer idProgramacion) throws Exception;

	/**
	 * @param productoNoAlimentarioProgramacionBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProductoNoAlimentarioProgramacionBean grabarProductoNoAlimentarioProgramacion(ProductoNoAlimentarioProgramacionBean productoNoAlimentarioProgramacionBean) throws Exception;

	/**
	 * @param productoNoAlimentarioProgramacionBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProductoNoAlimentarioProgramacionBean eliminarProductoNoAlimentarioProgramacion(ProductoNoAlimentarioProgramacionBean productoNoAlimentarioProgramacionBean) throws Exception;

	/**
	 * @param productoNoAlimentarioProgramacionBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProductoNoAlimentarioProgramacionBean actualizarProgramacionNoAlimentario(ProductoNoAlimentarioProgramacionBean productoNoAlimentarioProgramacionBean) throws Exception;

	/**
	 * @param idProgramacion
	 * @param arrIdProducto
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ProgramacionNoAlimentarioBean> listarProgramacionNoAlimentario(Integer idProgramacion, List<Integer> arrIdProducto) throws Exception;

	/**
	 * @param idProgramacion
	 * @param idRacionOperativa
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ResumenStockNoAlimentarioBean> listarResumenStockNoAlimentario(Integer idProgramacion, Integer idRacionOperativa) throws Exception;

	/**
	 * @param productoNoAlimentarioBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProductoNoAlimentarioBean actualizarDetalleProgramacionNoAlimentario(ProductoNoAlimentarioBean productoNoAlimentarioBean) throws Exception;

	/**
	 * @param programacionNoAlimentarioBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProgramacionNoAlimentarioBean eliminarDetalleProgramacionNoAlimentario(ProgramacionNoAlimentarioBean programacionNoAlimentarioBean) throws Exception;

	/**
	 * @param documentoProgramacionBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<DocumentoProgramacionBean> listarDocumentoProgramacion(DocumentoProgramacionBean documentoProgramacionBean) throws Exception;

	/**
	 * @param documentoProgramacionBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract DocumentoProgramacionBean grabarDocumentoProgramacion(DocumentoProgramacionBean documentoProgramacionBean) throws Exception;

	/**
	 * @param documentoProgramacionBean
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract DocumentoProgramacionBean eliminarDocumentoProgramacion(DocumentoProgramacionBean documentoProgramacionBean) throws Exception;
	
	/**
	 * @param idProgramacion
	 * @return Objeto.
	 * @throws Exception
	 */
	public abstract ProgramacionBean obtenerRegistroCabeceraProgramacion(Integer idProgramacion) throws Exception;
	
	/**
	 * @param idProgramacion 
	 * @param idRacionOperativa
	 * @param arrIdProducto 
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ProgramacionAlimentoBean> listarTotalProductoAlimento(Integer idProgramacion, Integer idRacionOperativa, List<Integer> arrIdProducto) throws Exception;
	
	/**
	 * @param estadoUsuarioBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<EstadoUsuarioBean> listarEstadoUsuario(EstadoUsuarioBean estadoUsuarioBean) throws Exception;
	
	/**
	 * @param correoBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<CorreoBean> listarCorreoDestino(CorreoBean correoBean) throws Exception;

	/**
	 * @param requerimientoBean
	 * @return
	 * @throws Exception
	 */
	public abstract RequerimientoBean eliminarRequerimientoEdan(RequerimientoBean requerimientoBean) throws Exception;

	/**
	 * @param programacionBean
	 * @return
	 * @throws Exception
	 */
	public abstract ProgramacionBean insertarUbigeoRacion(ProgramacionBean programacionBean) throws Exception;

	/**
	 * @param correoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<CorreoBean> listarCorreoProg(CorreoBean correoBean) throws Exception;

}
