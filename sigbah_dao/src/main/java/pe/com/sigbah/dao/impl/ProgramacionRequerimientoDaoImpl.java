package pe.com.sigbah.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import oracle.jdbc.OracleTypes;
import pe.com.sigbah.common.bean.CorreoBean;
import pe.com.sigbah.common.bean.DocumentoProgramacionBean;
import pe.com.sigbah.common.bean.EstadoProgramacionBean;
import pe.com.sigbah.common.bean.EstadoUsuarioBean;
import pe.com.sigbah.common.bean.ProductoAlimentoBean;
import pe.com.sigbah.common.bean.ProductoDonacionBean;
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
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.DateUtil;
import pe.com.sigbah.common.util.SpringUtil;
import pe.com.sigbah.common.util.Utils;
import pe.com.sigbah.dao.ProgramacionRequerimientoDao;
import pe.com.sigbah.mapper.CorreoMapper;
import pe.com.sigbah.mapper.CorreoProgMapper;
import pe.com.sigbah.mapper.DocumentoProgramacionMapper;
import pe.com.sigbah.mapper.EstadoProgramacionMapper;
import pe.com.sigbah.mapper.EstadoUsuarioMapper;
import pe.com.sigbah.mapper.ProductoNoAlimentarioProgramacion1Mapper;
import pe.com.sigbah.mapper.ProductoNoAlimentarioProgramacion2Mapper;
import pe.com.sigbah.mapper.ProductoNoAlimentarioProgramacionMapper;
import pe.com.sigbah.mapper.ProgramacionAlimentoMapper;
import pe.com.sigbah.mapper.ProgramacionAlmacenMapper;
import pe.com.sigbah.mapper.ProgramacionMapper;
import pe.com.sigbah.mapper.ProgramacionNoAlimentarioMapper;
import pe.com.sigbah.mapper.ProgramacionRacionOperativaMapper;
import pe.com.sigbah.mapper.ProgramacionRequerimientoMapper;
import pe.com.sigbah.mapper.RacionOperativaMapper;
import pe.com.sigbah.mapper.RegistroCabeceraProgramacion1Mapper;
import pe.com.sigbah.mapper.RegistroCabeceraProgramacionMapper;
import pe.com.sigbah.mapper.RegistroProgramacionMapper;
import pe.com.sigbah.mapper.ResumenStockAlimentoMapper;
import pe.com.sigbah.mapper.ResumenStockNoAlimentarioMapper;
import pe.com.sigbah.mapper.TotalProductoAlimentoMapper;

/**
 * @className: ProgramacionRequerimientoDaoImpl.java
 * @description: 
 * @date: 29/07/2017
 * @author: Junior Huaman Flores.
 */
@SuppressWarnings("unchecked")
@Repository
public class ProgramacionRequerimientoDaoImpl extends JdbcDaoSupport implements ProgramacionRequerimientoDao, Serializable {

	private static final long serialVersionUID = 1L;
	private transient final Log LOGGER = LogFactory.getLog(getClass());
	
	private SimpleJdbcCall objJdbcCall;
	
	/**
	 * @param dataSource
	 */
	@Autowired
	public ProgramacionRequerimientoDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#listarProgramacion(pe.com.sigbah.common.bean.ProgramacionBean)
	 */
	@Override
	public List<ProgramacionBean> listarProgramacion(ProgramacionBean programacionBean) throws Exception {
		LOGGER.info("[listarProgramacion] Inicio ");
		List<ProgramacionBean> lista = new ArrayList<ProgramacionBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_COD_ANIO", programacionBean.getCodigoAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_MES", Utils.getParam(programacionBean.getCodigoMes()), Types.VARCHAR);
			input_objParametros.addValue("PI_IDE_DDI", programacionBean.getIdDdi(), Types.NUMERIC);
			input_objParametros.addValue("PI_FENOMENO", Utils.getParamInt(programacionBean.getIdFenomeno()), Types.NUMERIC);
			input_objParametros.addValue("PI_ESTADO", Utils.getParamInt(programacionBean.getIdEstado()), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_PROGRAMACION");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_COD_MES", new SqlParameter("PI_COD_MES", Types.VARCHAR));
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PI_FENOMENO", new SqlParameter("PI_FENOMENO", Types.NUMERIC));
			output_objParametros.put("PI_ESTADO", new SqlParameter("PI_ESTADO", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new ProgramacionMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarProgramacion] Ocurrio un error en la operacion del USP_SEL_LISTAR_PROGRAMACION : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<ProgramacionBean>) out.get("PO_LR_RECORDSET");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarProgramacion] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#obtenerCorrelativoProgramacion(pe.com.sigbah.common.bean.ProgramacionBean)
	 */
	@Override
	public ProgramacionBean obtenerCorrelativoProgramacion(ProgramacionBean programacionBean) throws Exception {
		LOGGER.info("[obtenerCorrelativoProgramacion] Inicio ");
		ProgramacionBean registroProgramacion = new ProgramacionBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ANIO", programacionBean.getCodigoAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_DDI", programacionBean.getIdDdi(), Types.NUMERIC);
			input_objParametros.addValue("PI_COD_DDI", programacionBean.getCodigoDdi(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_GENERA_CORR_PROG");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ANIO", new SqlParameter("PI_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_DDI", new SqlParameter("PI_DDI", Types.NUMERIC));
			output_objParametros.put("PI_COD_DDI", new SqlParameter("PI_COD_DDI", Types.VARCHAR));
			output_objParametros.put("PO_COD_PROG", new SqlOutParameter("PO_COD_PROG", Types.VARCHAR));
			output_objParametros.put("PO_COD_PROG_CONCATENADO", new SqlOutParameter("PO_COD_PROG_CONCATENADO", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerCorrelativoProgramacion] Ocurrio un error en la operacion del USP_SEL_GENERA_CORR_PROG : "+mensajeRespuesta);
    			throw new Exception();
    		}

			registroProgramacion.setCodigoProgramacion((String) out.get("PO_COD_PROG"));
			registroProgramacion.setNroProgramacion((String) out.get("PO_COD_PROG_CONCATENADO"));
			registroProgramacion.setCodigoRespuesta(codigoRespuesta);
			registroProgramacion.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerCorrelativoProgramacion] Fin ");
		return registroProgramacion;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#obtenerRegistroProgramacion(java.lang.Integer)
	 */
	@Override
	public ProgramacionBean obtenerRegistroProgramacion(Integer idProgramacion) throws Exception {
		LOGGER.info("[obtenerRegistroProgramacion] Inicio ");
		ProgramacionBean programacion = new ProgramacionBean();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_IDE_PROGRAMACION", idProgramacion, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_EDITAR_PROGRAMACION");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PROGRAMACION", new SqlParameter("PI_IDE_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_DATOSGENERALES", new SqlOutParameter("PO_CURSOR_DATOSGENERALES", OracleTypes.CURSOR, new RegistroProgramacionMapper()));
			output_objParametros.put("PO_CURSOR_ALMACEN", new SqlOutParameter("PO_CURSOR_ALMACEN", OracleTypes.CURSOR, new ProgramacionAlmacenMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerRegistroProgramacion] Ocurrio un error en la operacion del USP_SEL_EDITAR_PROGRAMACION : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			List<ProgramacionBean> lista = (List<ProgramacionBean>) out.get("PO_CURSOR_DATOSGENERALES");
			if (!Utils.isEmpty(lista)) {
				programacion = lista.get(0);				
				List<ProgramacionAlmacenBean> listaAlmacen = (List<ProgramacionAlmacenBean>) out.get("PO_CURSOR_ALMACEN");
				if (!Utils.isEmpty(listaAlmacen)) {
					programacion.setAlmacenes(listaAlmacen);
				}
			}
			
			programacion.setCodigoRespuesta(codigoRespuesta);
			programacion.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerRegistroProgramacion] Fin ");
		return programacion;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#listarRequerimiento(pe.com.sigbah.common.bean.RequerimientoBean)
	 */
	@Override
	public List<RequerimientoBean> listarRequerimiento(RequerimientoBean requerimientoBean) throws Exception {
		LOGGER.info("[listarRequerimiento] Inicio ");
		List<RequerimientoBean> lista = new ArrayList<RequerimientoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
//			input_objParametros.addValue("PI_COD_ANIO", requerimientoBean.getCodAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_IDE_DDI", requerimientoBean.getIdDdi(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_REQUERIMIENTO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
//			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new ProgramacionRequerimientoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarRequerimiento] Ocurrio un error en la operacion del USP_SEL_REQUERIMIENTO : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<RequerimientoBean>) out.get("PO_LR_RECORDSET");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarRequerimiento] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#listarRacionOperativa(pe.com.sigbah.common.bean.RacionOperativaBean)
	 */
	@Override
	public List<RacionOperativaBean> listarRacionOperativa(RacionOperativaBean racionOperativaBean) throws Exception {
		LOGGER.info("[listarRacionOperativa] Inicio ");
		List<RacionOperativaBean> lista = new ArrayList<RacionOperativaBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_IDE_RAC_OPERATIVA", racionOperativaBean.getIdRacionOperativa(), Types.NUMERIC);
			input_objParametros.addValue("PI_ID_DDI", racionOperativaBean.getIdDdi(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_RACION_OPERATIVA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_RAC_OPERATIVA", new SqlParameter("PI_IDE_RAC_OPERATIVA", Types.NUMERIC));
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new RacionOperativaMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarRacionOperativa] Ocurrio un error en la operacion del USP_SEL_RACION_OPERATIVA : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<RacionOperativaBean>) out.get("PO_LR_RECORDSET");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarRacionOperativa] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#grabarEstadoProgramacion(pe.com.sigbah.common.bean.EstadoProgramacionBean)
	 */
	@Override
	public EstadoProgramacionBean grabarEstadoProgramacion(EstadoProgramacionBean estadoProgramacionBean) throws Exception {
		LOGGER.info("[grabarEstadoProgramacion] Inicio ");
		EstadoProgramacionBean registroEstadoProgramacion = new EstadoProgramacionBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_PROGRAMACION", estadoProgramacionBean.getIdProgramacion(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_ESTADO", estadoProgramacionBean.getIdEstado(), Types.NUMERIC);
			input_objParametros.addValue("PI_OBSERVACION", estadoProgramacionBean.getObservacion(), Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", estadoProgramacionBean.getUsuarioRegistro(), Types.VARCHAR);				
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_ESTADO_PROGRAMACION");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PROGRAMACION", new SqlParameter("PI_IDE_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PI_IDE_ESTADO", new SqlParameter("PI_IDE_ESTADO", Types.NUMERIC));
			output_objParametros.put("PI_OBSERVACION", new SqlParameter("PI_OBSERVACION", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));		
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[grabarEstadoProgramacion] Ocurrio un error en la operacion del USP_INS_ESTADO_PROGRAMACION : "+mensajeRespuesta);
    			throw new Exception();
    		}

			registroEstadoProgramacion.setCodigoRespuesta(codigoRespuesta);
			registroEstadoProgramacion.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[grabarEstadoProgramacion] Fin ");
		return registroEstadoProgramacion;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#listarEstadoProgramacion(pe.com.sigbah.common.bean.EstadoProgramacionBean)
	 */
	@Override
	public List<EstadoProgramacionBean> listarEstadoProgramacion(EstadoProgramacionBean estadoProgramacionBean) throws Exception {
		LOGGER.info("[listarEstadoProgramacion] Inicio ");
		List<EstadoProgramacionBean> lista = new ArrayList<EstadoProgramacionBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_IDE_PROGRAMACION", estadoProgramacionBean.getIdProgramacion(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_ESTADOS_PROG");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PROGRAMACION", new SqlParameter("PI_IDE_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_ESTADOS", new SqlOutParameter("PO_CURSOR_ESTADOS", OracleTypes.CURSOR, new EstadoProgramacionMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarEstadoProgramacion] Ocurrio un error en la operacion del USP_SEL_LISTAR_ESTADOS_PROG : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<EstadoProgramacionBean>) out.get("PO_CURSOR_ESTADOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarEstadoProgramacion] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#grabarProgramacion(pe.com.sigbah.common.bean.ProgramacionBean)
	 */
	@Override
	public ProgramacionBean grabarProgramacion(ProgramacionBean programacionBean) throws Exception {
		LOGGER.info("[grabarProgramacion] Inicio ");
		ProgramacionBean registroProgramacion = new ProgramacionBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_PROGRAMACION", programacionBean.getIdProgramacion(), Types.NUMERIC);
			input_objParametros.addValue("PI_COD_ANIO", programacionBean.getCodigoAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_IDE_REQUERIMIENTO", programacionBean.getIdRequerimiento(), Types.NUMERIC);			
			input_objParametros.addValue("PI_FEC_PROGRAMACION", DateUtil.obtenerFechaHoraParseada(programacionBean.getFechaProgramacion()), Types.DATE);			
			input_objParametros.addValue("PI_IDE_DDI", programacionBean.getIdDdi(), Types.NUMERIC);
			input_objParametros.addValue("PI_COD_DDI", programacionBean.getCodigoDdi(), Types.VARCHAR);			
			input_objParametros.addValue("PI_IDE_REGION", programacionBean.getIdRegion(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_RAC_OPERATIVA", programacionBean.getIdRacion(), Types.NUMERIC);
			input_objParametros.addValue("PI_NOM_PROGRAMACION", programacionBean.getNombreProgramacion(), Types.VARCHAR);
			input_objParametros.addValue("PI_FK_IDE_DEE", programacionBean.getIdNroDee(), Types.NUMERIC);			
			input_objParametros.addValue("PI_TIP_ATENCION", programacionBean.getTipoAtencion(), Types.VARCHAR);
			input_objParametros.addValue("PI_OBSERVACION", programacionBean.getObservacion(), Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", programacionBean.getUsuarioRegistro(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", programacionBean.getTipoControl(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_PROGRAMACIONDATGEN");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PROGRAMACION", new SqlParameter("PI_IDE_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_IDE_REQUERIMIENTO", new SqlParameter("PI_IDE_REQUERIMIENTO", Types.NUMERIC));			
			output_objParametros.put("PI_FEC_PROGRAMACION", new SqlParameter("PI_FEC_PROGRAMACION", Types.DATE));
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PI_COD_DDI", new SqlParameter("PI_COD_DDI", Types.VARCHAR));			
			output_objParametros.put("PI_IDE_REGION", new SqlParameter("PI_IDE_REGION", Types.NUMERIC));
			output_objParametros.put("PI_IDE_RAC_OPERATIVA", new SqlParameter("PI_IDE_RAC_OPERATIVA", Types.NUMERIC));
			output_objParametros.put("PI_NOM_PROGRAMACION", new SqlParameter("PI_NOM_PROGRAMACION", Types.VARCHAR));			
			output_objParametros.put("PI_FK_IDE_DEE", new SqlParameter("PI_FK_IDE_DEE", Types.NUMERIC));
			output_objParametros.put("PI_TIP_ATENCION", new SqlParameter("PI_TIP_ATENCION", Types.NUMERIC));
			output_objParametros.put("PI_OBSERVACION", new SqlParameter("PI_OBSERVACION", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_ID_PROGRAMACION", new SqlOutParameter("PO_ID_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PO_COD_PROGRAMACION", new SqlOutParameter("PO_COD_PROGRAMACION", Types.VARCHAR));
			output_objParametros.put("PO_COD_PROG_CONCATENADO", new SqlOutParameter("PO_COD_PROG_CONCATENADO", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[grabarProgramacion] Ocurrio un error en la operacion del USP_INS_UPD_PROGRAMACIONDATGEN : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			if (Utils.isNullInteger(programacionBean.getIdProgramacion())) {
				registroProgramacion.setIdProgramacion(((BigDecimal) out.get("PO_ID_PROGRAMACION")).intValue());
				registroProgramacion.setNroProgramacion((String) out.get("PO_COD_PROG_CONCATENADO"));
				registroProgramacion.setCodigoProgramacion((String) out.get("PO_COD_PROGRAMACION"));
				registroProgramacion.setIdEstado(Constantes.COD_GENERADO);
				registroProgramacion.setNombreEstado(Constantes.DES_GENERADO);
			}
			registroProgramacion.setCodigoRespuesta(codigoRespuesta);
			registroProgramacion.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[grabarProgramacion] Fin ");
		return registroProgramacion;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#listarProgramacionAlmacen(pe.com.sigbah.common.bean.ProgramacionAlmacenBean)
	 */
	@Override
	public List<ProgramacionAlmacenBean> listarProgramacionAlmacen(ProgramacionAlmacenBean programacionAlmacenBean) throws Exception {
		LOGGER.info("[listarProgramacionAlmacen] Inicio ");
		List<ProgramacionAlmacenBean> lista = new ArrayList<ProgramacionAlmacenBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_IDE_PROGRAMACION", programacionAlmacenBean.getIdProgramacion(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_ALMACEN_PROG");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PROGRAMACION", new SqlParameter("PI_IDE_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new ProgramacionAlmacenMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarProgramacionAlmacen] Ocurrio un error en la operacion del USP_SEL_LISTAR_ALMACEN_PROG : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<ProgramacionAlmacenBean>) out.get("PO_LR_RECORDSET");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarProgramacionAlmacen] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#grabarProgramacionAlmacen(pe.com.sigbah.common.bean.ProgramacionAlmacenBean)
	 */
	@Override
	public ProgramacionAlmacenBean grabarProgramacionAlmacen(ProgramacionAlmacenBean programacionAlmacenBean) throws Exception {
		LOGGER.info("[grabarProgramacionAlmacen] Inicio ");
		ProgramacionAlmacenBean registroProgramacionAlmacen = new ProgramacionAlmacenBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_FK_IDE_PROGRAMACION", programacionAlmacenBean.getIdProgramacion(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_ALMACEN", programacionAlmacenBean.getIdAlmacen(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", programacionAlmacenBean.getUsuarioRegistro(), Types.VARCHAR);			

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_PROGRAMACION_ALMACEN");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FK_IDE_PROGRAMACION", new SqlParameter("PI_FK_IDE_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PI_IDE_ALMACEN", new SqlParameter("PI_IDE_ALMACEN", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[grabarProgramacionAlmacen] Ocurrio un error en la operacion del USP_INS_PROGRAMACION_ALMACEN : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			registroProgramacionAlmacen.setCodigoRespuesta(codigoRespuesta);
			registroProgramacionAlmacen.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[grabarProductoControlCalidad] Fin ");
		return registroProgramacionAlmacen;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#eliminarProgramacionAlmacen(pe.com.sigbah.common.bean.ProgramacionAlmacenBean)
	 */
	@Override
	public ProgramacionAlmacenBean eliminarProgramacionAlmacen(ProgramacionAlmacenBean programacionAlmacenBean) throws Exception {
		LOGGER.info("[eliminarProgramacionAlmacen] Inicio ");
		ProgramacionAlmacenBean registroProgramacionAlmacen = new ProgramacionAlmacenBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_PROG_ALMACEN", programacionAlmacenBean.getIdProgramacionAlmacen(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", programacionAlmacenBean.getUsuarioRegistro(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_DEL_PROGRAMACION_ALMACEN");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PROG_ALMACEN", new SqlParameter("PI_IDE_PROG_ALMACEN", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[eliminarProgramacionAlmacen] Ocurrio un error en la operacion del USP_DEL_PROGRAMACION_ALMACEN : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			registroProgramacionAlmacen.setCodigoRespuesta(codigoRespuesta);
			registroProgramacionAlmacen.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[eliminarProgramacionAlmacen] Fin ");
		return registroProgramacionAlmacen;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#listarProgramacionRacionOperativa(java.lang.Integer)
	 */
	@Override
	public List<RacionOperativaBean> listarProgramacionRacionOperativa(Integer idRacionOperativa) throws Exception {
		LOGGER.info("[listarProgramacionRacionOperativa] Inicio ");
		List<RacionOperativaBean> lista = new ArrayList<RacionOperativaBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_IDE_RAC_OPERATIVA", idRacionOperativa, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_RACION");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_RAC_OPERATIVA", new SqlParameter("PI_IDE_RAC_OPERATIVA", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new ProgramacionRacionOperativaMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarProgramacionRacionOperativa] Ocurrio un error en la operacion del USP_SEL_LISTAR_RACION : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<RacionOperativaBean>) out.get("PO_LR_RECORDSET");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarProgramacionRacionOperativa] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#actualizarProgramacionRacionOperativa(pe.com.sigbah.common.bean.RacionOperativaBean)
	 */
	@Override
	public RacionOperativaBean actualizarProgramacionRacionOperativa(RacionOperativaBean racionOperativaBean) throws Exception {
		LOGGER.info("[actualizarProgramacionRacionOperativa] Inicio ");
		RacionOperativaBean registroRacionOperativa = new RacionOperativaBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_FK_IDE_PROGRAMACION", racionOperativaBean.getIdProgramacion(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_RACION", racionOperativaBean.getIdRacion(), Types.NUMERIC);
			input_objParametros.addValue("PI_NUM_DIAS", racionOperativaBean.getDiasAtencion(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", racionOperativaBean.getUsuarioRegistro(), Types.VARCHAR);			

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_UPD_PROGRAMACION_ALIMENTO1");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FK_IDE_PROGRAMACION", new SqlParameter("PI_FK_IDE_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_RACION", new SqlParameter("PI_FK_IDE_RACION", Types.NUMERIC));
			output_objParametros.put("PI_NUM_DIAS", new SqlParameter("PI_NUM_DIAS", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[actualizarProgramacionRacionOperativa] Ocurrio un error en la operacion del USP_UPD_PROGRAMACION_ALIMENTO1 : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			registroRacionOperativa.setCodigoRespuesta(codigoRespuesta);
			registroRacionOperativa.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[actualizarProgramacionRacionOperativa] Fin ");
		return registroRacionOperativa;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#listarProgramacionAlimento(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<ProgramacionAlimentoBean> listarProgramacionAlimento(Integer idProgramacion, List<Integer> arrIdProducto) throws Exception {
		LOGGER.info("[listarProgramacionAlimento] Inicio ");
		List<ProgramacionAlimentoBean> lista = new ArrayList<ProgramacionAlimentoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_PROG", idProgramacion, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_PIVOT_ALIMENTOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_PROG", new SqlParameter("PI_ID_PROG", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_ALIMENTOS", new SqlOutParameter("PO_CURSOR_ALIMENTOS", OracleTypes.CURSOR, new ProgramacionAlimentoMapper(arrIdProducto)));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarProgramacionAlimento] Ocurrio un error en la operacion del USP_SEL_LISTAR_PIVOT_ALIMENTOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<ProgramacionAlimentoBean>) out.get("PO_CURSOR_ALIMENTOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarProgramacionAlimento] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#listarResumenStockAlimento(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<ResumenStockAlimentoBean> listarResumenStockAlimento(Integer idProgramacion, Integer idRacionOperativa) throws Exception {
		LOGGER.info("[listarResumenStockAlimento] Inicio ");
		List<ResumenStockAlimentoBean> lista = new ArrayList<ResumenStockAlimentoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_PROG", idProgramacion, Types.NUMERIC);
			input_objParametros.addValue("PI_ID_RACION", idRacionOperativa, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_STOCK_ALIMENTO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_PROG", new SqlParameter("PI_ID_PROG", Types.NUMERIC));
			output_objParametros.put("PI_ID_RACION", new SqlParameter("PI_ID_RACION", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_RESUMEN_STOCK", new SqlOutParameter("PO_CURSOR_RESUMEN_STOCK", OracleTypes.CURSOR, new ResumenStockAlimentoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarResumenStockAlimento] Ocurrio un error en la operacion del USP_SEL_LISTAR_STOCK_ALIMENTO : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<ResumenStockAlimentoBean>) out.get("PO_CURSOR_RESUMEN_STOCK");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarResumenStockAlimento] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#actualizarDetalleProgramacionAlimento(pe.com.sigbah.common.bean.ProductoAlimentoBean)
	 */
	@Override
	public ProductoAlimentoBean actualizarDetalleProgramacionAlimento(ProductoAlimentoBean productoAlimentoBean) throws Exception {
		LOGGER.info("[actualizarDetalleProgramacionAlimento] Inicio ");
		ProductoAlimentoBean registroProductoAlimento = new ProductoAlimentoBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_FK_IDE_PROGRAMACION_UBIGEO", productoAlimentoBean.getIdProgramacionUbigeo(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_PRODUCTO", productoAlimentoBean.getIdProducto(), Types.NUMERIC);
			input_objParametros.addValue("PI_NRO_UNIDADES", productoAlimentoBean.getUnidad(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", productoAlimentoBean.getUsuarioRegistro(), Types.VARCHAR);			

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_UPD_PROGRAMACION_ALIMENTO2");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FK_IDE_PROGRAMACION_UBIGEO", new SqlParameter("PI_FK_IDE_PROGRAMACION_UBIGEO", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_PRODUCTO", new SqlParameter("PI_FK_IDE_PRODUCTO", Types.NUMERIC));
			output_objParametros.put("PI_NRO_UNIDADES", new SqlParameter("PI_NRO_UNIDADES", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[actualizarDetalleProgramacionAlimento] Ocurrio un error en la operacion del USP_UPD_PROGRAMACION_ALIMENTO2 : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			registroProductoAlimento.setCodigoRespuesta(codigoRespuesta);
			registroProductoAlimento.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[actualizarDetalleProgramacionAlimento] Fin ");
		return registroProductoAlimento;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#eliminarDetalleProgramacionAlimento(pe.com.sigbah.common.bean.ProgramacionAlimentoBean)
	 */
	@Override
	public ProgramacionAlimentoBean eliminarDetalleProgramacionAlimento(ProgramacionAlimentoBean programacionAlimentoBean) throws Exception {
		LOGGER.info("[eliminarDetalleProgramacionAlimento] Inicio ");
		ProgramacionAlimentoBean registroDetalleProgramacionAlimento = new ProgramacionAlimentoBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_FK_IDE_PROGRAMACION_UBIGEO", programacionAlimentoBean.getIdProgramacionUbigeo(), Types.NUMERIC);
//			input_objParametros.addValue("PI_USERNAME", programacionAlimentoBean.getUsuarioRegistro(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_DEL_PROG_ALIMENTO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FK_IDE_PROGRAMACION_UBIGEO", new SqlParameter("PI_FK_IDE_PROGRAMACION_UBIGEO", Types.NUMERIC));
//			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[eliminarDetalleProgramacionAlimento] Ocurrio un error en la operacion del USP_DEL_PROG_ALIMENTO : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			registroDetalleProgramacionAlimento.setCodigoRespuesta(codigoRespuesta);
			registroDetalleProgramacionAlimento.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[eliminarDetalleProgramacionAlimento] Fin ");
		return registroDetalleProgramacionAlimento;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#eliminarProgramacionAlimento(pe.com.sigbah.common.bean.ProgramacionBean)
	 */
	@Override
	public ProgramacionBean eliminarProgramacionAlimento(ProgramacionBean programacionBean) throws Exception {
		LOGGER.info("[eliminarProgramacionAlimento] Inicio ");
		ProgramacionBean registroProgramacion = new ProgramacionBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_FK_IDE_PROGRAMACION", programacionBean.getIdProgramacion(), Types.NUMERIC);
//			input_objParametros.addValue("PI_USERNAME", programacionBean.getUsuarioRegistro(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_DEL_PROGRAMACION_PRODUCTO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FK_IDE_PROGRAMACION", new SqlParameter("PI_FK_IDE_PROGRAMACION", Types.NUMERIC));
//			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[eliminarProgramacionAlimento] Ocurrio un error en la operacion del USP_DEL_PROGRAMACION_PRODUCTO : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			registroProgramacion.setCodigoRespuesta(codigoRespuesta);
			registroProgramacion.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[eliminarProgramacionAlimento] Fin ");
		return registroProgramacion;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#insertarUbigeoRacion(pe.com.sigbah.common.bean.ProgramacionBean)
	 */
	@Override
	public ProgramacionBean insertarUbigeoRacion(ProgramacionBean programacionBean) throws Exception {
		LOGGER.info("[insertarUbigeoRacion] Inicio ");
		ProgramacionBean registroProgramacion = new ProgramacionBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_FK_IDE_PROGRAMACION", programacionBean.getIdProgramacion(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_RACION", programacionBean.getIdRacion(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", programacionBean.getUsuarioRegistro(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UBIGEO_RACION_A_PROG");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FK_IDE_PROGRAMACION", new SqlParameter("PI_FK_IDE_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PI_IDE_RACION", new SqlParameter("PI_IDE_RACION", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
//			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
//			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
//			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
//			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
//				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
//				LOGGER.info("[insertarUbigeoRacion] Ocurrio un error en la operacion del USP_INS_UBIGEO_RACION_A_PROG : "+mensajeRespuesta);
//    			throw new Exception();
//    		}
			
//			registroProgramacion.setCodigoRespuesta(codigoRespuesta);
//			registroProgramacion.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
			LOGGER.info("[insertarUbigeoRacion] Se ejecutó USP_INS_UBIGEO_RACION_A_PROG");	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarUbigeoRacion] Fin ");
		return registroProgramacion;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#listarProductoNoAlimentarioProgramacion(java.lang.Integer)
	 */
	@Override
	public List<ProductoNoAlimentarioProgramacionBean> listarProductoNoAlimentarioProgramacion(Integer idProgramacion) throws Exception {
		LOGGER.info("[listarProductoNoAlimentarioProgramacion] Inicio ");
		List<ProductoNoAlimentarioProgramacionBean> lista = new ArrayList<ProductoNoAlimentarioProgramacionBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_IDE_PROGRAMACION", idProgramacion, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_PRODUCTOS_BNA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PROGRAMACION", new SqlParameter("PI_IDE_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PO_PRODUCTOS_BNA", new SqlOutParameter("PO_PRODUCTOS_BNA", OracleTypes.CURSOR, new ProductoNoAlimentarioProgramacion2Mapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarProductoNoAlimentarioProgramacion] Ocurrio un error en la operacion del USP_SEL_LISTAR_PRODUCTOS_BNA : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<ProductoNoAlimentarioProgramacionBean>) out.get("PO_PRODUCTOS_BNA");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarProductoNoAlimentarioProgramacion] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#grabarProductoNoAlimentarioProgramacion(pe.com.sigbah.common.bean.ProductoNoAlimentarioProgramacionBean)
	 */
	@Override
	public ProductoNoAlimentarioProgramacionBean grabarProductoNoAlimentarioProgramacion(ProductoNoAlimentarioProgramacionBean productoNoAlimentarioProgramacionBean) throws Exception {
		LOGGER.info("[grabarProductoNoAlimentarioProgramacion] Inicio ");
		ProductoNoAlimentarioProgramacionBean registroProductoNoAlimentarioProgramacion = new ProductoNoAlimentarioProgramacionBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_PRODUCTO_BNA", productoNoAlimentarioProgramacionBean.getIdDetalleProductoNoAlimentario(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_PROGRAMACION", productoNoAlimentarioProgramacionBean.getIdProgramacion(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_PRODUCTO", productoNoAlimentarioProgramacionBean.getIdProducto(), Types.NUMERIC);
//			input_objParametros.addValue("PI_CANTIDAD", productoNoAlimentarioProgramacionBean.getCantidad(), Types.NUMERIC);
			input_objParametros.addValue("PI_TIPO_ENTREGA", productoNoAlimentarioProgramacionBean.getTipoEntrega(), Types.VARCHAR);
//			input_objParametros.addValue("PI_TIPO_BENEFICIARIO", productoNoAlimentarioProgramacionBean.getTipoBeneficiario(), Types.VARCHAR);
			input_objParametros.addValue("PI_CANTIDAD_POR_DAMNIFICADO", productoNoAlimentarioProgramacionBean.getCantidadDamnificado(), Types.NUMERIC);
			input_objParametros.addValue("PI_CANTIDAD_POR_AFECTADO", productoNoAlimentarioProgramacionBean.getCantidadAfectado(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", productoNoAlimentarioProgramacionBean.getUsuarioRegistro(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_PRODUCTOS_BNA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PRODUCTO_BNA", new SqlParameter("PI_IDE_PRODUCTO_BNA", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_PROGRAMACION", new SqlParameter("PI_FK_IDE_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_PRODUCTO", new SqlParameter("PI_FK_IDE_PRODUCTO", Types.NUMERIC));
//			output_objParametros.put("PI_CANTIDAD", new SqlParameter("PI_CANTIDAD", Types.NUMERIC));
			output_objParametros.put("PI_TIPO_ENTREGA", new SqlParameter("PI_TIPO_ENTREGA", Types.VARCHAR));
//			output_objParametros.put("PI_TIPO_BENEFICIARIO", new SqlParameter("PI_TIPO_BENEFICIARIO", Types.VARCHAR));
			output_objParametros.put("PI_CANTIDAD_POR_DAMNIFICADO", new SqlParameter("PI_CANTIDAD_POR_DAMNIFICADO", Types.NUMERIC));
			output_objParametros.put("PI_CANTIDAD_POR_AFECTADO", new SqlParameter("PI_CANTIDAD_POR_AFECTADO", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[grabarProductoNoAlimentarioProgramacion] Ocurrio un error en la operacion del USP_INS_UPD_PRODUCTOS_BNA : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			registroProductoNoAlimentarioProgramacion.setCodigoRespuesta(codigoRespuesta);
			registroProductoNoAlimentarioProgramacion.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[grabarProductoNoAlimentarioProgramacion] Fin ");
		return registroProductoNoAlimentarioProgramacion;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#eliminarProductoNoAlimentarioProgramacion(pe.com.sigbah.common.bean.ProductoNoAlimentarioProgramacionBean)
	 */
	@Override
	public ProductoNoAlimentarioProgramacionBean eliminarProductoNoAlimentarioProgramacion(ProductoNoAlimentarioProgramacionBean productoNoAlimentarioProgramacionBean) throws Exception {
		LOGGER.info("[eliminarProductoNoAlimentarioProgramacion] Inicio ");
		ProductoNoAlimentarioProgramacionBean registroProductoNoAlimentarioProgramacion = new ProductoNoAlimentarioProgramacionBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_PRODUCTO_BNA", productoNoAlimentarioProgramacionBean.getIdDetalleProductoNoAlimentario(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", productoNoAlimentarioProgramacionBean.getUsuarioRegistro(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_DEL_PRODUCTO_BNA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PRODUCTO_BNA", new SqlParameter("PI_IDE_PRODUCTO_BNA", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[eliminarProductoNoAlimentarioProgramacion] Ocurrio un error en la operacion del USP_DEL_PRODUCTO_BNA : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			registroProductoNoAlimentarioProgramacion.setCodigoRespuesta(codigoRespuesta);
			registroProductoNoAlimentarioProgramacion.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[eliminarProductoNoAlimentarioProgramacion] Fin ");
		return registroProductoNoAlimentarioProgramacion;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#actualizarProgramacionNoAlimentario(pe.com.sigbah.common.bean.ProductoNoAlimentarioProgramacionBean)
	 */
	@Override
	public ProductoNoAlimentarioProgramacionBean actualizarProgramacionNoAlimentario(ProductoNoAlimentarioProgramacionBean productoNoAlimentarioProgramacionBean) throws Exception {
		LOGGER.info("[actualizarProgramacionNoAlimentario] Inicio ");
		ProductoNoAlimentarioProgramacionBean registroProductoNoAlimentarioProgramacion = new ProductoNoAlimentarioProgramacionBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_FK_IDE_PROGRAMACION", productoNoAlimentarioProgramacionBean.getIdProgramacion(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", productoNoAlimentarioProgramacionBean.getUsuarioRegistro(), Types.VARCHAR);			

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_PROGRAMACION_BNA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FK_IDE_PROGRAMACION", new SqlParameter("PI_FK_IDE_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[actualizarProgramacionNoAlimentario] Ocurrio un error en la operacion del USP_INS_PROGRAMACION_BNA : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			registroProductoNoAlimentarioProgramacion.setCodigoRespuesta(codigoRespuesta);
			registroProductoNoAlimentarioProgramacion.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[actualizarProgramacionNoAlimentario] Fin ");
		return registroProductoNoAlimentarioProgramacion;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#listarProgramacionNoAlimentario(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<ProgramacionNoAlimentarioBean> listarProgramacionNoAlimentario(Integer idProgramacion, List<Integer> arrIdProducto) throws Exception {
		LOGGER.info("[listarProgramacionNoAlimentario] Inicio ");
		List<ProgramacionNoAlimentarioBean> lista = new ArrayList<ProgramacionNoAlimentarioBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_PROG", idProgramacion, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_PIVOT_BNA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_PROG", new SqlParameter("PI_ID_PROG", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_CANTIDADES_BNA", new SqlOutParameter("PO_CURSOR_CANTIDADES_BNA", OracleTypes.CURSOR, new ProgramacionNoAlimentarioMapper(arrIdProducto)));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarProgramacionNoAlimentario] Ocurrio un error en la operacion del USP_SEL_LISTAR_PIVOT_BNA : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<ProgramacionNoAlimentarioBean>) out.get("PO_CURSOR_CANTIDADES_BNA");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarProgramacionNoAlimentario] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#listarResumenStockNoAlimentario(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<ResumenStockNoAlimentarioBean> listarResumenStockNoAlimentario(Integer idProgramacion, Integer idRacionOperativa) throws Exception {
		LOGGER.info("[listarResumenStockNoAlimentario] Inicio ");
		List<ResumenStockNoAlimentarioBean> lista = new ArrayList<ResumenStockNoAlimentarioBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_PROG", idProgramacion, Types.NUMERIC);
//			input_objParametros.addValue("PI_ID_RACION", idRacionOperativa, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_STOCK_BNA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_PROG", new SqlParameter("PI_ID_PROG", Types.NUMERIC));
//			output_objParametros.put("PI_ID_RACION", new SqlParameter("PI_ID_RACION", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_RESUMEN_BNA", new SqlOutParameter("PO_CURSOR_RESUMEN_BNA", OracleTypes.CURSOR, new ResumenStockNoAlimentarioMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarResumenStockNoAlimentario] Ocurrio un error en la operacion del USP_SEL_LISTAR_STOCK_BNA : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<ResumenStockNoAlimentarioBean>) out.get("PO_CURSOR_RESUMEN_BNA");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarResumenStockNoAlimentario] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#actualizarDetalleProgramacionNoAlimentario(pe.com.sigbah.common.bean.ProductoNoAlimentarioBean)
	 */
	@Override
	public ProductoNoAlimentarioBean actualizarDetalleProgramacionNoAlimentario(ProductoNoAlimentarioBean productoNoAlimentarioBean) throws Exception {
		LOGGER.info("[actualizarDetalleProgramacionNoAlimentario] Inicio ");
		ProductoNoAlimentarioBean registroProductoNoAlimentario = new ProductoNoAlimentarioBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_FK_IDE_PROGRAMACION_UBIGEO", productoNoAlimentarioBean.getIdProgramacionUbigeo(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_PRODUCTO", productoNoAlimentarioBean.getIdProducto(), Types.NUMERIC);
			input_objParametros.addValue("PI_NRO_UNIDADES", productoNoAlimentarioBean.getUnidad(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", productoNoAlimentarioBean.getUsuarioRegistro(), Types.VARCHAR);			

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_UPD_PROGRAMACION_BNA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FK_IDE_PROGRAMACION_UBIGEO", new SqlParameter("PI_FK_IDE_PROGRAMACION_UBIGEO", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_PRODUCTO", new SqlParameter("PI_FK_IDE_PRODUCTO", Types.NUMERIC));
			output_objParametros.put("PI_NRO_UNIDADES", new SqlParameter("PI_NRO_UNIDADES", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[actualizarDetalleProgramacionNoAlimentario] Ocurrio un error en la operacion del USP_UPD_PROGRAMACION_BNA : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			registroProductoNoAlimentario.setCodigoRespuesta(codigoRespuesta);
			registroProductoNoAlimentario.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[actualizarDetalleProgramacionNoAlimentario] Fin ");
		return registroProductoNoAlimentario;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#eliminarDetalleProgramacionNoAlimentario(pe.com.sigbah.common.bean.ProgramacionNoAlimentarioBean)
	 */
	@Override
	public ProgramacionNoAlimentarioBean eliminarDetalleProgramacionNoAlimentario(ProgramacionNoAlimentarioBean programacionNoAlimentarioBean) throws Exception {
		LOGGER.info("[eliminarDetalleProgramacionNoAlimentario] Inicio ");
		ProgramacionNoAlimentarioBean registroProductoNoAlimentario = new ProgramacionNoAlimentarioBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_FK_IDE_PROGRAMACION_UBIGEO", programacionNoAlimentarioBean.getIdProgramacionUbigeo(), Types.NUMERIC);
//			input_objParametros.addValue("PI_USERNAME", programacionNoAlimentarioBean.getUsuarioRegistro(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_DEL_PROG_BNA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FK_IDE_PROGRAMACION_UBIGEO", new SqlParameter("PI_FK_IDE_PROGRAMACION_UBIGEO", Types.NUMERIC));
//			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[eliminarDetalleProgramacionNoAlimentario] Ocurrio un error en la operacion del USP_DEL_PROG_BNA : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			registroProductoNoAlimentario.setCodigoRespuesta(codigoRespuesta);
			registroProductoNoAlimentario.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[eliminarDetalleProgramacionNoAlimentario] Fin ");
		return registroProductoNoAlimentario;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#listarDocumentoProgramacion(pe.com.sigbah.common.bean.DocumentoProgramacionBean)
	 */
	@Override
	public List<DocumentoProgramacionBean> listarDocumentoProgramacion(DocumentoProgramacionBean documentoProgramacionBean) throws Exception {
		LOGGER.info("[listarDocumentoProgramacion] Inicio ");
		List<DocumentoProgramacionBean> lista = new ArrayList<DocumentoProgramacionBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_IDE_PROGRAMACION", documentoProgramacionBean.getIdProgramacion(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_PROG_FILE");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PROGRAMACION", new SqlParameter("PI_IDE_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_DOCUMENTOS", new SqlOutParameter("PO_CURSOR_DOCUMENTOS", OracleTypes.CURSOR, new DocumentoProgramacionMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarDocumentoProgramacion] Ocurrio un error en la operacion del USP_SEL_LISTAR_PROG_FILE : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			lista = (List<DocumentoProgramacionBean>) out.get("PO_CURSOR_DOCUMENTOS");
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarDocumentoProgramacion] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#grabarDocumentoProgramacion(pe.com.sigbah.common.bean.DocumentoProgramacionBean)
	 */
	@Override
	public DocumentoProgramacionBean grabarDocumentoProgramacion(DocumentoProgramacionBean documentoProgramacionBean) throws Exception {
		LOGGER.info("[grabarDocumentoProgramacion] Inicio ");
		DocumentoProgramacionBean registroDocumentoProgramacion = new DocumentoProgramacionBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_FK_IDE_PROGRAMACION", documentoProgramacionBean.getIdProgramacion(), Types.NUMERIC);
			input_objParametros.addValue("PI_ID_DOCUMENTO", documentoProgramacionBean.getIdDocumentoProgramacion(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_TIP_DOCUMENTO", documentoProgramacionBean.getIdTipoDocumento(), Types.NUMERIC);
			input_objParametros.addValue("PI_NRO_DOCUMENTO", StringUtils.upperCase(documentoProgramacionBean.getNroDocumento()), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_ALFRESCO", documentoProgramacionBean.getCodigoArchivoAlfresco(), Types.VARCHAR);
			input_objParametros.addValue("PI_NOMB_ARCHIVO", documentoProgramacionBean.getNombreArchivo(), Types.VARCHAR);
	        input_objParametros.addValue("PI_FEC_DOCUMENTO", DateUtil.obtenerFechaHoraParseada(documentoProgramacionBean.getFechaDocumento()), Types.DATE);		
			input_objParametros.addValue("PI_USERNAME", documentoProgramacionBean.getUsuarioRegistro(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", documentoProgramacionBean.getIndControl(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_PROGRAMACION_FILE");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FK_IDE_PROGRAMACION", new SqlParameter("PI_FK_IDE_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PI_ID_DOCUMENTO", new SqlParameter("PI_ID_DOCUMENTO", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_TIP_DOCUMENTO", new SqlParameter("PI_FK_IDE_TIP_DOCUMENTO", Types.NUMERIC));
			output_objParametros.put("PI_NRO_DOCUMENTO", new SqlParameter("PI_NRO_DOCUMENTO", Types.VARCHAR));
			output_objParametros.put("PI_COD_ALFRESCO", new SqlParameter("PI_COD_ALFRESCO", Types.VARCHAR));
			output_objParametros.put("PI_NOMB_ARCHIVO", new SqlParameter("PI_NOMB_ARCHIVO", Types.VARCHAR));
			output_objParametros.put("PI_FEC_DOCUMENTO", new SqlParameter("PI_FEC_DOCUMENTO", Types.DATE));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[grabarDocumentoProgramacion] Ocurrio un error en la operacion del USP_INS_UPD_PROGRAMACION_FILE : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			registroDocumentoProgramacion.setCodigoRespuesta(codigoRespuesta);
			registroDocumentoProgramacion.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[grabarDocumentoProgramacion] Fin ");
		return registroDocumentoProgramacion;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#eliminarDocumentoProgramacion(pe.com.sigbah.common.bean.DocumentoProgramacionBean)
	 */
	@Override
	public DocumentoProgramacionBean eliminarDocumentoProgramacion(DocumentoProgramacionBean documentoProgramacionBean) throws Exception {
		LOGGER.info("[eliminarDocumentoProgramacion] Inicio ");
		DocumentoProgramacionBean registroDocumentoProgramacion = new DocumentoProgramacionBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ID_DOCUMENTO", documentoProgramacionBean.getIdDocumentoProgramacion(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", documentoProgramacionBean.getUsuarioRegistro(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_DEL_PROGRAMACION_FILE");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DOCUMENTO", new SqlParameter("PI_ID_DOCUMENTO", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[eliminarDocumentoProgramacion] Ocurrio un error en la operacion del USP_DEL_PROGRAMACION_FILE : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			registroDocumentoProgramacion.setCodigoRespuesta(codigoRespuesta);
			registroDocumentoProgramacion.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[eliminarDocumentoProgramacion] Fin ");
		return registroDocumentoProgramacion;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#obtenerRegistroCabeceraProgramacion(java.lang.Integer)
	 */
	@Override
	public ProgramacionBean obtenerRegistroCabeceraProgramacion(Integer idProgramacion) throws Exception {
		LOGGER.info("[obtenerRegistroCabeceraProgramacion] Inicio ");
		ProgramacionBean programacion = new ProgramacionBean();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_IDE_PROGRAMACION", idProgramacion, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_REPORT_PROGRAMACION");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PROGRAMACION", new SqlParameter("PI_IDE_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_CABECERA", new SqlOutParameter("PO_CURSOR_CABECERA", OracleTypes.CURSOR, new RegistroCabeceraProgramacion1Mapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerRegistroCabeceraProgramacion] Ocurrio un error en la operacion del USP_REPORT_PROGRAMACION : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			List<ProgramacionBean> lista = (List<ProgramacionBean>) out.get("PO_CURSOR_CABECERA");
			if (!Utils.isEmpty(lista)) {
				programacion = lista.get(0);
			}
			
			programacion.setCodigoRespuesta(codigoRespuesta);
			programacion.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerRegistroCabeceraProgramacion] Fin ");
		return programacion;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#listarTotalProductoAlimento(java.lang.Integer, java.lang.Integer, java.util.List)
	 */
	@Override
	public List<ProgramacionAlimentoBean> listarTotalProductoAlimento(Integer idProgramacion, Integer idRacionOperativa, List<Integer> arrIdProducto) throws Exception {
		LOGGER.info("[listarTotalProductoAlimento] Inicio ");
		List<ProgramacionAlimentoBean> lista = new ArrayList<ProgramacionAlimentoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_IDE_RACION", idRacionOperativa, Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_PROGRAMACION", idProgramacion, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTA_TOTAL_X_PRODUCTO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_RACION", new SqlParameter("PI_IDE_RACION", Types.NUMERIC));
			output_objParametros.put("PI_IDE_PROGRAMACION", new SqlParameter("PI_IDE_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PI_CURSOR_PRESENTACION", new SqlOutParameter("PI_CURSOR_PRESENTACION", OracleTypes.CURSOR, new TotalProductoAlimentoMapper(arrIdProducto)));
			output_objParametros.put("PI_CURSOR_TOTALES", new SqlOutParameter("PI_CURSOR_TOTALES", OracleTypes.CURSOR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTotalProductoAlimento] Ocurrio un error en la operacion del USP_SEL_LISTA_TOTAL_X_PRODUCTO : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<ProgramacionAlimentoBean>) out.get("PI_CURSOR_PRESENTACION");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTotalProductoAlimento] Fin ");
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#listarEstadoUsuario(pe.com.sigbah.common.bean.EstadoUsuarioBean)
	 */
	@Transactional
	@Override
	public List<EstadoUsuarioBean> listarEstadoUsuario(EstadoUsuarioBean estadoUsuarioBean) throws Exception {
		LOGGER.info("[listarEstadoUsuario] Inicio ");
		List<EstadoUsuarioBean> lista = new ArrayList<EstadoUsuarioBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_USER", estadoUsuarioBean.getIdUsuario(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_PROGRAMACION", estadoUsuarioBean.getIdProgramacion(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_ESTADOS_POR_USUARIO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_USER", new SqlParameter("PI_ID_USER", Types.NUMERIC));
			output_objParametros.put("PI_IDE_PROGRAMACION", new SqlParameter("PI_IDE_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_ESTADOS", new SqlOutParameter("PO_CURSOR_ESTADOS", OracleTypes.CURSOR, new EstadoUsuarioMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarEstadoUsuario] Ocurrio un error en la operacion del USP_SEL_ESTADOS_POR_USUARIO : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			lista = (List<EstadoUsuarioBean>) out.get("PO_CURSOR_ESTADOS");
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarEstadoUsuario] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#listarCorreoDestino(pe.com.sigbah.common.bean.CorreoBean)
	 */
	@Override
	public List<CorreoBean> listarCorreoDestino(CorreoBean correoBean) throws Exception {
		LOGGER.info("[listarCorreoDestino] Inicio ");
		List<CorreoBean> lista = new ArrayList<CorreoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_PROGRAMACION", correoBean.getIdProgramacion(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_ESTADO", correoBean.getIdEstado(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_ENVIA_CORREO_DESTINO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_PROGRAMACION", new SqlParameter("PI_ID_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PI_IDE_ESTADO", new SqlParameter("PI_IDE_ESTADO", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_CORREO", new SqlOutParameter("PO_CURSOR_CORREO", OracleTypes.CURSOR, new CorreoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarCorreoDestino] Ocurrio un error en la operacion del USP_SEL_ENVIA_CORREO_DESTINO : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			lista = (List<CorreoBean>) out.get("PO_CURSOR_CORREO");
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarCorreoDestino] Fin ");
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionRequerimientoDao#listarCorreoDestino(pe.com.sigbah.common.bean.CorreoBean)
	 */
	@Override
	public List<CorreoBean> listarCorreoProg(CorreoBean correoBean) throws Exception {
		LOGGER.info("[listarCorreoProg] Inicio ");
		List<CorreoBean> lista = new ArrayList<CorreoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_PROGRAMACION", correoBean.getIdProgramacion(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_ESTADO", correoBean.getIdEstado(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_ENVIAR_CORREOS_PROG");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_PROGRAMACION", new SqlParameter("PI_ID_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PI_IDE_ESTADO", new SqlParameter("PI_IDE_ESTADO", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_CORREO", new SqlOutParameter("PO_CURSOR_CORREO", OracleTypes.CURSOR, new CorreoProgMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarCorreoProg] Ocurrio un error en la operacion del USP_SEL_ENVIAR_CORREOS_PROG : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			lista = (List<CorreoBean>) out.get("PO_CURSOR_CORREO");
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarCorreoProg] Fin ");
		return lista;
	}
	
	@Override
	public RequerimientoBean eliminarRequerimientoEdan(RequerimientoBean requerimientoBean) throws Exception {
		LOGGER.info("[eliminarRequerimientoEdan] Inicio ");
		RequerimientoBean registroRequerimiento = new RequerimientoBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_REQ_DAMNIFICADO", requerimientoBean.getIdRequerimiento(), Types.NUMERIC);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_DEL_REQUERIMIENTO_DET");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_REQ_DAMNIFICADO", new SqlParameter("PI_IDE_REQ_DAMNIFICADO", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[eliminarRequerimientoEdan] Ocurrio un error en la operacion del USP_DEL_REQUERIMIENTO_DET : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			registroRequerimiento.setCodigoRespuesta(codigoRespuesta);
			registroRequerimiento.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[eliminarRequerimientoEdan] Fin ");
		return registroRequerimiento;
	}
	
}
