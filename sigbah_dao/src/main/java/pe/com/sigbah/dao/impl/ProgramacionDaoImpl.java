package pe.com.sigbah.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;
import pe.com.sigbah.common.bean.AlimentariaEmergenciaBean;
import pe.com.sigbah.common.bean.CabeceraEmergenciaBean;
import pe.com.sigbah.common.bean.ConsultaPedidoCompraBean;
import pe.com.sigbah.common.bean.ConsultaProgramacionBean;
import pe.com.sigbah.common.bean.DeeBean;
import pe.com.sigbah.common.bean.DeeConsultaBean;
import pe.com.sigbah.common.bean.DocumentoPedidoCompraBean;
import pe.com.sigbah.common.bean.EmergenciaBean;
import pe.com.sigbah.common.bean.ListaRespuestaConsultaPedidoBean;
import pe.com.sigbah.common.bean.ListaRespuestaConsultaProductoBean;
import pe.com.sigbah.common.bean.ListaRespuestaEmergenciaBean;
import pe.com.sigbah.common.bean.ListaRespuestaPedidoCompraBean;
import pe.com.sigbah.common.bean.ListaRespuestaRequerimientoBean;
import pe.com.sigbah.common.bean.LocalidadEmergenciaBean;
import pe.com.sigbah.common.bean.NoAlimentariaEmergenciaBean;
import pe.com.sigbah.common.bean.PedidoCompraBean;
import pe.com.sigbah.common.bean.PedidoCompraReporteBean;
import pe.com.sigbah.common.bean.ProductoConsultaPedidoBean;
import pe.com.sigbah.common.bean.ProductoConsultaProductoBean;
import pe.com.sigbah.common.bean.ProductoPedidoCompraBean;
import pe.com.sigbah.common.bean.ProductoRacionBean;
import pe.com.sigbah.common.bean.ProgramacionBean;
import pe.com.sigbah.common.bean.RacionBean;
import pe.com.sigbah.common.bean.ReporteConsultaProgramacionBean;
import pe.com.sigbah.common.bean.RequerimientoBean;
import pe.com.sigbah.common.bean.UbigeoDeeBean;
import pe.com.sigbah.common.bean.UbigeoIneiBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.DateUtil;
import pe.com.sigbah.common.util.SpringUtil;
import pe.com.sigbah.common.util.Utils;
import pe.com.sigbah.dao.ProgramacionDao;
import pe.com.sigbah.mapper.AlimentosConsultaProgramacionMapper;
import pe.com.sigbah.mapper.CabeceraConsultaProgramacionMapper;
import pe.com.sigbah.mapper.ConsultaProgramacionMapper;
import pe.com.sigbah.mapper.DecretoMapper;
import pe.com.sigbah.mapper.DeeDistritoEmergenciaMapper;
import pe.com.sigbah.mapper.DocumentoPedidoCompraMapper;
import pe.com.sigbah.mapper.EmergenciaMapper;
import pe.com.sigbah.mapper.PedidoCompraMapper;
import pe.com.sigbah.mapper.ProductoPedidoCompraMapper;
import pe.com.sigbah.mapper.ProductoRacionMapper;
import pe.com.sigbah.mapper.RacionMapper;
import pe.com.sigbah.mapper.RegistroAlimentariaEmergenciaMapper;
import pe.com.sigbah.mapper.RegistroAlimentariaEmergenciaReportMapper;
import pe.com.sigbah.mapper.RegistroCabeceraEmergenciaMapper;
import pe.com.sigbah.mapper.RegistroConsultaPedidoCompra2Mapper;
import pe.com.sigbah.mapper.RegistroConsultaPedidoCompraMapper;
import pe.com.sigbah.mapper.RegistroConsultaPedidoModalCab2Mapper;
import pe.com.sigbah.mapper.RegistroConsultaPedidoModalCabMapper;
import pe.com.sigbah.mapper.RegistroConsultaPedidoModalNoProdMapper;
import pe.com.sigbah.mapper.RegistroConsultaPedidoModalProdMapper;
import pe.com.sigbah.mapper.RegistroConsultaProgramacionMapper;
import pe.com.sigbah.mapper.RegistroDeeMapper;
import pe.com.sigbah.mapper.RegistroLocalidadEmergenciaMapper;
import pe.com.sigbah.mapper.RegistroLocalidadReportEmergenciaMapper;
import pe.com.sigbah.mapper.RegistroNoAlimentariaEmergenciaMapper;
import pe.com.sigbah.mapper.RegistroNoAlimentariaEmergenciaReportMapper;
import pe.com.sigbah.mapper.RegistroPedidoCompraMapper;
import pe.com.sigbah.mapper.RegistroRacionMapper;
import pe.com.sigbah.mapper.ReporteCabeceraPedidoCompraMapper;
import pe.com.sigbah.mapper.ReporteCabeceraRequerimiento1Mapper;
import pe.com.sigbah.mapper.ReporteCabeceraRequerimientoMapper;
import pe.com.sigbah.mapper.ReporteDetallePedidoCompraMapper;
import pe.com.sigbah.mapper.ReporteDetalleRequerimientoMapper;
import pe.com.sigbah.mapper.Requerimiento1Mapper;
import pe.com.sigbah.mapper.RequerimientoDetalleMapper;
import pe.com.sigbah.mapper.RequerimientoEditMapper;
import pe.com.sigbah.mapper.RequerimientoMapper;
import pe.com.sigbah.mapper.UbigeoDeeMapper;
import pe.com.sigbah.mapper.UbigeoIneiMapper;


/**
 * @className: ProgramacionDaoImpl.java
 * @description: 
 * @date: 27 jul. 2017
 * @author: whr.
 */
@Repository
public class ProgramacionDaoImpl extends JdbcDaoSupport implements ProgramacionDao, Serializable {

	private static final long serialVersionUID = 1L;
	private transient final Log LOGGER = LogFactory.getLog(getClass());
	
	private SimpleJdbcCall objJdbcCall;
	
	/**
	 * @param dataSource
	 */
	@Autowired
	public ProgramacionDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	
	@Override
	public List<EmergenciaBean> listarEmergencia(EmergenciaBean emergenciaBean) throws Exception {
		LOGGER.info("[listarEmergencia] Inicio ");
		List<EmergenciaBean> lista = new ArrayList<EmergenciaBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_COD_ANIO", emergenciaBean.getCodAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_MES", Utils.getParam( emergenciaBean.getCodMes()), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_REGION", emergenciaBean.getIdRegion(), Types.INTEGER);
			input_objParametros.addValue("PI_IDE_FENOMENO", emergenciaBean.getIdFenomeno(), Types.INTEGER);
						
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_EMERGENCIAS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_COD_MES", new SqlParameter("PI_COD_MES", Types.VARCHAR));
			output_objParametros.put("PI_COD_REGION", new SqlParameter("PI_COD_REGION", Types.INTEGER));
			output_objParametros.put("PI_IDE_FENOMENO", new SqlParameter("PI_IDE_FENOMENO", Types.INTEGER));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new EmergenciaMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarEmergencia] Ocurrio un error en la operacion del USP_SEL_Listar_Emergencias : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<EmergenciaBean>) out.get("PO_LR_RECORDSET");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarEmergencia] Fin ");
		return lista;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#obtenerRegistroEmergencia(java.lang.Integer)
	 */
	@Override
	public ListaRespuestaEmergenciaBean obtenerRegistroEmergencia(Integer idEmergencia, String codAnio) throws Exception {
		LOGGER.info("[obtenerRegistroEmergencia] Inicio ");
		ListaRespuestaEmergenciaBean listaRetorno = new ListaRespuestaEmergenciaBean();
		
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();	
			input_objParametros.addValue("PI_COD_ANIO", codAnio, Types.VARCHAR);
			input_objParametros.addValue("PI_IDE_EMERGENCIA", idEmergencia, Types.NUMERIC);
			
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_MOSTRAR_EMERGENCIAS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_IDE_EMERGENCIA", new SqlParameter("PI_IDE_EMERGENCIA", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET_CABECERA", new SqlOutParameter("PO_LR_RECORDSET_CABECERA", OracleTypes.CURSOR, new RegistroCabeceraEmergenciaMapper()));
			output_objParametros.put("PO_LR_RECORDSET_LOCALIDAD", new SqlOutParameter("PO_LR_RECORDSET_LOCALIDAD", OracleTypes.CURSOR, new RegistroLocalidadEmergenciaMapper()));
			output_objParametros.put("PO_LR_RECORDSET_ALIMENTOS", new SqlOutParameter("PO_LR_RECORDSET_ALIMENTOS", OracleTypes.CURSOR, new RegistroAlimentariaEmergenciaMapper()));
			output_objParametros.put("PO_LR_RECORDSET_NO_ALIMENTOS", new SqlOutParameter("PO_LR_RECORDSET_NO_ALIMENTOS", OracleTypes.CURSOR, new RegistroNoAlimentariaEmergenciaMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerRegistroEmergencia] Ocurrio un error en la operacion del USP_SEL_MOSTRAR_EMERGENCIAS : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			List<CabeceraEmergenciaBean> listaCabecera = (List<CabeceraEmergenciaBean>) out.get("PO_LR_RECORDSET_CABECERA");
			List<LocalidadEmergenciaBean> listaLocalidad = (List<LocalidadEmergenciaBean>) out.get("PO_LR_RECORDSET_LOCALIDAD");
			List<AlimentariaEmergenciaBean> listaAlimentaria = (List<AlimentariaEmergenciaBean>) out.get("PO_LR_RECORDSET_ALIMENTOS");
			List<NoAlimentariaEmergenciaBean> listaNoAlimentaria = (List<NoAlimentariaEmergenciaBean>) out.get("PO_LR_RECORDSET_NO_ALIMENTOS");
			
			listaRetorno.setLstCabecera(listaCabecera);
			listaRetorno.setLstLocalidad(listaLocalidad);
			listaRetorno.setLstAlimentaria(listaAlimentaria);
			listaRetorno.setLstNoAlimentaria(listaNoAlimentaria);
			
			listaRetorno.setCodigoRespuesta(codigoRespuesta);
			listaRetorno.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerRegistroEmergencia] Fin ");
		return listaRetorno;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#listarRequerimiento(pe.com.sigbah.common.bean.RequerimientoBean)
	 */
	@Override
	public List<RequerimientoBean> listarRequerimiento(RequerimientoBean requerimientoBean) throws Exception {
		LOGGER.info("[listarRequerimiento] Inicio ");
		List<RequerimientoBean> lista = new ArrayList<RequerimientoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_COD_ANIO", requerimientoBean.getCodAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_MES", Utils.getParam(requerimientoBean.getCodMes()), Types.VARCHAR);
			input_objParametros.addValue("PI_ID_DDI", Utils.getParam(requerimientoBean.getFkIdeDdi()), Types.INTEGER);
			input_objParametros.addValue("PI_IDE_FENOMENO", requerimientoBean.getIdFenomeno(), Types.VARCHAR);
			
		
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_REQUERIMIENTOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_COD_MES", new SqlParameter("PI_COD_MES", Types.VARCHAR));
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.INTEGER));
			output_objParametros.put("PI_IDE_FENOMENO", new SqlParameter("PI_IDE_FENOMENO", Types.VARCHAR));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new Requerimiento1Mapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarRequerimiento] Ocurrio un error en la operacion del USP_SEL_LISTAR_REQUERIMIENTOS : "+mensajeRespuesta);
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
	 * @see pe.com.sigbah.dao.ProgramacionDao#obtenerRequerimiento(java.lang.Integer, java.lang.String)
	 */
	@Override
	public ListaRespuestaRequerimientoBean obtenerRequerimiento( String codAnio, String codDdi,Integer idRequerimiento) throws Exception {
		LOGGER.info("[obtenerRequerimiento] Inicio ");
		ListaRespuestaRequerimientoBean listaRetorno = new ListaRespuestaRequerimientoBean();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		

			input_objParametros.addValue("PI_COD_DDI", codDdi, Types.VARCHAR);
			input_objParametros.addValue("PI_IDE_REQUERIMIENTO", idRequerimiento, Types.NUMERIC);
			
		    
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_EDITAR_REQUERIMIENTO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();

			output_objParametros.put("PI_COD_DDI", new SqlParameter("PI_COD_DDI", Types.VARCHAR));
			output_objParametros.put("PI_IDE_REQUERIMIENTO", new SqlParameter("PI_IDE_REQUERIMIENTO", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET1", new SqlOutParameter("PO_LR_RECORDSET1", OracleTypes.CURSOR, new RequerimientoEditMapper()));
			output_objParametros.put("PO_LR_RECORDSET2", new SqlOutParameter("PO_LR_RECORDSET2", OracleTypes.CURSOR, new RequerimientoDetalleMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("po_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerRequerimiento] Ocurrio un error en la operacion del USP_SEL_EDITAR_REQUERIMIENTO : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			List<RequerimientoBean> listaCabecera = (List<RequerimientoBean>) out.get("PO_LR_RECORDSET1");
			List<EmergenciaBean> listaDetalle = (List<EmergenciaBean>) out.get("PO_LR_RECORDSET2");
			
			listaRetorno.setLstCabecera(listaCabecera);
			listaRetorno.setLstDetalle(listaDetalle);
			
			listaRetorno.setCodigoRespuesta(codigoRespuesta);
			listaRetorno.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));					
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerRequerimiento] Fin ");
		return listaRetorno; 
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#obtenerCorrelativoRequerimiento(pe.com.sigbah.common.bean.RequerimientoBean)
	 */
	@Override
	public RequerimientoBean obtenerCorrelativoRequerimiento(RequerimientoBean requerimientoBean) throws Exception {
		LOGGER.info("[obtenerCorrelativoRequerimiento] Inicio ");
		RequerimientoBean detalleUsuarioBean = new RequerimientoBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_COD_ANIO", requerimientoBean.getCodAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_DDI", requerimientoBean.getCodDdi(), Types.VARCHAR);
			input_objParametros.addValue("PI_FK_IDE_DDI", requerimientoBean.getIdDdi(), Types.NUMERIC);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_GENERA_REQUERIMIENTO");
			

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_COD_DDI", new SqlParameter("PI_COD_DDI", Types.VARCHAR));
			output_objParametros.put("PI_FK_IDE_DDI", new SqlParameter("PI_FK_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PO_COD_REQUERIMIENTO", new SqlOutParameter("PO_COD_REQUERIMIENTO", Types.VARCHAR));
			output_objParametros.put("PO_COD_REQ_CONCATENADO", new SqlOutParameter("PO_COD_REQ_CONCATENADO", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerCorrelativoRequerimiento] Ocurrio un error en la operacion del USP_SEL_GENERA_REQUERIMIENTO : "+mensajeRespuesta);
    			throw new Exception();
    		}

			detalleUsuarioBean.setCodRequerimiento((String) out.get("PO_COD_REQUERIMIENTO"));
			detalleUsuarioBean.setNumRequerimiento((String) out.get("PO_COD_REQ_CONCATENADO"));
			detalleUsuarioBean.setCodigoRespuesta(codigoRespuesta);
			detalleUsuarioBean.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerCorrelativoRequerimiento] Fin ");
		return detalleUsuarioBean;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#insertarRegistroRequerimiento(pe.com.sigbah.common.bean.RequerimientoBean)
	 */
	@Override
	public RequerimientoBean insertarRegistroRequerimiento(RequerimientoBean requerimientoBean) throws Exception {
		LOGGER.info("[insertarRequerimiento] Inicio ");
		RequerimientoBean registroRequerimiento = new RequerimientoBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_REQUERIMIENTO", requerimientoBean.getIdRequerimiento(), Types.NUMERIC);
			input_objParametros.addValue("PI_COD_ANIO", requerimientoBean.getCodAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_MES", requerimientoBean.getCodMes(), Types.VARCHAR);
			input_objParametros.addValue("PI_FK_IDE_DDI", requerimientoBean.getFkIdeDdi(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_REGION", requerimientoBean.getFkIdeRegion(), Types.NUMERIC);
			input_objParametros.addValue("PI_COD_REQUERIMIENTO", requerimientoBean.getCodRequerimiento(), Types.VARCHAR);
			input_objParametros.addValue("PI_NOM_REQUERIMIENTO", requerimientoBean.getNomRequerimiento(), Types.VARCHAR);
			input_objParametros.addValue("PI_FEC_REQUERIMIENTO", DateUtil.obtenerFechaHoraParseada(requerimientoBean.getFechaRequerimiento()), Types.DATE);
			input_objParametros.addValue("PI_FLG_SINPAD", requerimientoBean.getFlgSinpad(), Types.VARCHAR);
			input_objParametros.addValue("PI_FK_IDE_FENOMENO", requerimientoBean.getFkIdeFenomeno(), Types.NUMERIC);
			input_objParametros.addValue("PI_OBSERVACION", requerimientoBean.getObservacion(), Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", requerimientoBean.getUsuarioRegistro(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", requerimientoBean.getControl(), Types.VARCHAR);
			input_objParametros.addValue("PI_IDE_ESTADO", requerimientoBean.getIdEstado(), Types.NUMERIC);
			    
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_REQUERIMIENTO_CAB");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_REQUERIMIENTO", new SqlParameter("PI_IDE_REQUERIMIENTO", Types.NUMERIC));
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_COD_MES", new SqlParameter("PI_COD_MES", Types.VARCHAR));
			output_objParametros.put("PI_FK_IDE_DDI", new SqlParameter("PI_FK_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_REGION", new SqlParameter("PI_FK_IDE_REGION", Types.NUMERIC));
			output_objParametros.put("PI_COD_REQUERIMIENTO", new SqlParameter("PI_COD_REQUERIMIENTO", Types.VARCHAR));
			output_objParametros.put("PI_NOM_REQUERIMIENTO", new SqlParameter("PI_NOM_REQUERIMIENTO", Types.VARCHAR));
			output_objParametros.put("PI_FEC_REQUERIMIENTO", new SqlParameter("PI_FEC_REQUERIMIENTO", Types.DATE));
			output_objParametros.put("PI_FLG_SINPAD", new SqlParameter("PI_FLG_SINPAD", Types.VARCHAR));
			output_objParametros.put("PI_FK_IDE_FENOMENO", new SqlParameter("PI_FK_IDE_FENOMENO", Types.NUMERIC));
			output_objParametros.put("PI_OBSERVACION", new SqlParameter("PI_OBSERVACION", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PI_IDE_ESTADO", new SqlParameter("PI_IDE_ESTADO", Types.NUMERIC));
			output_objParametros.put("PO_IDE_REQUERIMIENTO", new SqlOutParameter("PO_IDE_REQUERIMIENTO", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_NRO_REQUERIMIENTO", new SqlOutParameter("PO_NRO_REQUERIMIENTO", Types.VARCHAR));
			output_objParametros.put("PO_NRO_REQ_CONCATENADO", new SqlOutParameter("PO_NRO_REQ_CONCATENADO", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRequerimiento] Ocurrio un error en la operacion del USP_INS_REGISTRA_CONTROL_CALID : "+mensajeRespuesta);
    			throw new Exception();
    		}
			if( requerimientoBean.getControl()=="I"){
				registroRequerimiento.setIdRequerimiento(((BigDecimal)  out.get("PO_IDE_REQUERIMIENTO")).intValue());
			}
			
			registroRequerimiento.setCodigoRespuesta(codigoRespuesta);
			registroRequerimiento.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
//			registroRequerimiento.setMensajeRespuesta((String) out.get("PO_NRO_REQUERIMIENTO"));
			registroRequerimiento.setNumRequerimiento((String) out.get("PO_NRO_REQ_CONCATENADO"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRequerimiento] Fin ");
		return registroRequerimiento;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#actualizarRegistroRequerimiento(pe.com.sigbah.common.bean.RequerimientoBean)
	 */
	@Override
	public RequerimientoBean actualizarRegistroRequerimiento(RequerimientoBean requerimientoBean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#listarEmergenciasActivas(pe.com.sigbah.common.bean.RequerimientoBean)
	 */
	@Override
	public List<EmergenciaBean> listarEmergenciasActivas(RequerimientoBean requerimientoBean) throws Exception {
		LOGGER.info("[listarEmergenciasActivas] Inicio ");
		List<EmergenciaBean> lista = new ArrayList<EmergenciaBean>();
		try {				
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_COD_ANIO", requerimientoBean.getCodAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_MES", Utils.getParam(requerimientoBean.getCodMes()), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_DEPARTAMENTO", requerimientoBean.getCodDpto(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_PROVINCIA", Utils.getParam(requerimientoBean.getCodProvincia()), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_FENOMENO", Utils.getParam(requerimientoBean.getIdFenomeno()), Types.INTEGER);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_EMERG_ACTIVAS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_COD_MES", new SqlParameter("PI_COD_MES", Types.VARCHAR));
			output_objParametros.put("PI_COD_DEPARTAMENTO", new SqlParameter("PI_COD_DEPARTAMENTO", Types.VARCHAR));
			output_objParametros.put("PI_COD_PROVINCIA", new SqlParameter("PI_COD_PROVINCIA", Types.VARCHAR));
			output_objParametros.put("PI_COD_FENOMENO", new SqlParameter("PI_COD_FENOMENO", Types.INTEGER));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new EmergenciaMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarEmergenciasActivas] Ocurrio un error en la operacion del USP_SEL_LISTAR_EMERG_ACTIVAS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<EmergenciaBean>) out.get("PO_LR_RECORDSET");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarEmergenciasActivas] Fin ");
		return lista;
	}

	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#pasarDistritos(pe.com.sigbah.common.bean.EmergenciaBean)
	 */
	@Override
	public EmergenciaBean pasarDistritos(EmergenciaBean emergenciaBean) throws Exception {
		LOGGER.info("[pasarDistritos] Inicio ");
		EmergenciaBean emergenciaRequerimiento = new EmergenciaBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_FK_IDE_REQUERIMIENTO", emergenciaBean.getFkIdRequerimiento(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_EMERGENCIA", emergenciaBean.getIdEmergencia(), Types.NUMERIC);
			input_objParametros.addValue("PI_COD_DISTRITO", emergenciaBean.getCodDistrito(), Types.VARCHAR);
			input_objParametros.addValue("PI_NUM_FAM_AFECTADAS", emergenciaBean.getFamAfectado(), Types.NUMERIC);
			input_objParametros.addValue("PI_NUM_FAM_DAMNIFICADAS", emergenciaBean.getFamDamnificado(), Types.NUMERIC);
			input_objParametros.addValue("PI_NUM_PER_AFECTADAS", emergenciaBean.getPersoAfectado(), Types.NUMERIC);
			input_objParametros.addValue("PI_NUM_PER_DAMNIFICADAS", emergenciaBean.getPersoDamnificado(), Types.NUMERIC);
			input_objParametros.addValue("PI_NUM_FAM_AFECTADAS_REAL", emergenciaBean.getFamAfectadoReal(), Types.NUMERIC);
			input_objParametros.addValue("PI_NUM_FAM_DAMNIFICADAS_REAL", emergenciaBean.getFamDamnificadoReal(), Types.NUMERIC);
			input_objParametros.addValue("PI_NUM_PER_AFECTADAS_REAL", emergenciaBean.getPersoAfectadoReal(), Types.NUMERIC);
			input_objParametros.addValue("PI_NUM_PER_DAMNIFICADAS_REAL", emergenciaBean.getPersoDamnificadoReal(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", emergenciaBean.getUsuarioRegistro(), Types.VARCHAR);
//			input_objParametros.addValue("PI_IDE_REQ_DAMNIFICADO", emergenciaBean.getFkIdRequerimientoDamni(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_DISTRITOEMERGENCIA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FK_IDE_REQUERIMIENTO", new SqlParameter("PI_FK_IDE_REQUERIMIENTO", Types.NUMERIC));
			output_objParametros.put("PI_IDE_EMERGENCIA", new SqlParameter("PI_IDE_EMERGENCIA", Types.NUMERIC));
			output_objParametros.put("PI_COD_DISTRITO", new SqlParameter("PI_COD_DISTRITO", Types.VARCHAR));
			output_objParametros.put("PI_NUM_FAM_AFECTADAS", new SqlParameter("PI_NUM_FAM_AFECTADAS", Types.NUMERIC));
			output_objParametros.put("PI_NUM_FAM_DAMNIFICADAS", new SqlParameter("PI_NUM_FAM_DAMNIFICADAS", Types.NUMERIC));
			output_objParametros.put("PI_NUM_PER_AFECTADAS", new SqlParameter("PI_NUM_PER_AFECTADAS", Types.NUMERIC));
			output_objParametros.put("PI_NUM_PER_DAMNIFICADAS", new SqlParameter("PI_NUM_PER_DAMNIFICADAS", Types.NUMERIC));
			output_objParametros.put("PI_NUM_FAM_AFECTADAS_REAL", new SqlParameter("PI_NUM_FAM_AFECTADAS_REAL", Types.NUMERIC));
			output_objParametros.put("PI_NUM_FAM_DAMNIFICADAS_REAL", new SqlParameter("PI_NUM_FAM_DAMNIFICADAS_REAL", Types.NUMERIC));
			output_objParametros.put("PI_NUM_PER_AFECTADAS_REAL", new SqlParameter("PI_NUM_PER_AFECTADAS_REAL", Types.NUMERIC));
			output_objParametros.put("PI_NUM_PER_DAMNIFICADAS_REAL", new SqlParameter("PI_NUM_PER_DAMNIFICADAS_REAL", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
//			output_objParametros.put("PI_IDE_REQ_DAMNIFICADO", new SqlParameter("PI_IDE_REQ_DAMNIFICADO", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[pasarDistritos] Ocurrio un error en la operacion del USP_INS_UPD_DISTRITOEMERGENCIA : "+mensajeRespuesta);
    			throw new Exception();
    		}
		
			
			emergenciaRequerimiento.setCodigoRespuesta(codigoRespuesta);
			emergenciaRequerimiento.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[pasarDistritos] Fin ");
		return emergenciaRequerimiento;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#listarUbigeoInei(pe.com.sigbah.common.bean.UbigeoIneiBean)
	 */
	@Override
	public List<UbigeoIneiBean> listarUbigeoInei(UbigeoIneiBean ubigeoBean) throws Exception {
		LOGGER.info("[listarUbigeoInei] Inicio ");
		List<UbigeoIneiBean> lista = new ArrayList<UbigeoIneiBean>();
		try {				
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_COD_ANIO", ubigeoBean.getCodAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_DEPARTAMENTO", ubigeoBean.getCoddpto(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_PROVINCIA", Utils.getParam(ubigeoBean.getCodprov()), Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_UBIGEO_INEI");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_COD_DEPARTAMENTO", new SqlParameter("PI_COD_DEPARTAMENTO", Types.VARCHAR));
			output_objParametros.put("PI_COD_PROVINCIA", new SqlParameter("PI_COD_PROVINCIA", Types.VARCHAR));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new UbigeoIneiMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarUbigeoInei] Ocurrio un error en la operacion del USP_SEL_LISTAR_UBIGEO_INEI : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<UbigeoIneiBean>) out.get("PO_LR_RECORDSET");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarUbigeoInei] Fin ");
		return lista;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#pasarDistritosUbigeo(pe.com.sigbah.common.bean.UbigeoIneiBean)
	 */
	@Override
	public EmergenciaBean pasarDistritosUbigeo(EmergenciaBean emergenciaBean) throws Exception {
		LOGGER.info("[pasarDistritosUbigeo] Inicio ");
		EmergenciaBean emergenciaRequerimiento = new EmergenciaBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_FK_IDE_REQUERIMIENTO", emergenciaBean.getFkIdRequerimiento(), Types.NUMERIC);
			input_objParametros.addValue("PI_COD_DISTRITO", emergenciaBean.getCodDistrito(), Types.VARCHAR);
			input_objParametros.addValue("PI_NUM_POBLACION_INEI", emergenciaBean.getPoblacionINEI(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", emergenciaBean.getUsuarioRegistro(), Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_DISTRITOEMERG_INEI");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FK_IDE_REQUERIMIENTO", new SqlParameter("PI_FK_IDE_REQUERIMIENTO", Types.NUMERIC));
			output_objParametros.put("PI_COD_DISTRITO", new SqlParameter("PI_COD_DISTRITO", Types.VARCHAR));
			output_objParametros.put("PI_NUM_POBLACION_INEI", new SqlParameter("PI_NUM_POBLACION_INEI", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[pasarDistritosUbigeo] Ocurrio un error en la operacion del USP_INS_DISTRITOEMERG_INEI : "+mensajeRespuesta);
    			throw new Exception();
    		}
		
			
			emergenciaRequerimiento.setCodigoRespuesta(codigoRespuesta);
			emergenciaRequerimiento.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[pasarDistritosUbigeo] Fin ");
		return emergenciaRequerimiento;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#listarRaciones(pe.com.sigbah.common.bean.RacionBean)
	 */
	@Override
	public List<RacionBean> listarRaciones(RacionBean racionBean) throws Exception {
		LOGGER.info("[listarRaciones] Inicio ");
		List<RacionBean> lista = new ArrayList<RacionBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();	

			input_objParametros.addValue("PI_COD_ANIO", racionBean.getCodAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_MES", Utils.getParam( racionBean.getCodMesRacion()), Types.VARCHAR);
			input_objParametros.addValue("PI_IDE_DDI", racionBean.getIdDdi(), Types.INTEGER);
			input_objParametros.addValue("PI_TIPO_RACION", Utils.getParam(racionBean.getTipoRacion()), Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_RACIONES");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_COD_MES", new SqlParameter("PI_COD_MES", Types.VARCHAR));
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.INTEGER));
			output_objParametros.put("PI_TIPO_RACION", new SqlParameter("PI_TIPO_RACION", Types.VARCHAR));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new RacionMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarRaciones] Ocurrio un error en la operacion del USP_SEL_LISTAR_RACIONES : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<RacionBean>) out.get("PO_LR_RECORDSET");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarRaciones] Fin ");
		return lista;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#copiarRacion(pe.com.sigbah.common.bean.RacionBean)
	 */
	@Override
	public RacionBean copiarRacion(RacionBean racionBean) throws Exception {
		LOGGER.info("[copiarRacion] Inicio ");
		RacionBean racion = new RacionBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_COD_ANIO", racionBean.getCodAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_ID_DDI", racionBean.getIdDdi(), Types.INTEGER);
			input_objParametros.addValue("PI_IDE_RAC_OPERATIVA", racionBean.getIdRacionOpe(), Types.INTEGER);
			input_objParametros.addValue("PI_USERNAME", Utils.getParam(racionBean.getUsuarioRegistro()), Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_COPIAR_RACION");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_IDE_RAC_OPERATIVA", new SqlParameter("PI_IDE_RAC_OPERATIVA", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[copiarRacion] Ocurrio un error en la operacion del USP_SEL_COPIAR_RACION : "+mensajeRespuesta);
    			throw new Exception();
    		}
		
			
			racion.setCodigoRespuesta(codigoRespuesta);
			racion.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[copiarRacion] Fin ");
		return racion;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#obtenerCorrelativoRacion(pe.com.sigbah.common.bean.RacionBean)
	 */
	@Override
	public RacionBean obtenerCorrelativoRacion(RacionBean parametros) throws Exception {
		LOGGER.info("[obtenerCorrelativoRacion] Inicio ");
		RacionBean racionBean = new RacionBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ANIO", parametros.getCodAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_DDI", parametros.getIdDdi(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_GENERA_CORR_RACION");
			

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ANIO", new SqlParameter("PI_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_DDI", new SqlParameter("PI_DDI", Types.NUMERIC));
			output_objParametros.put("PO_COD_RACION", new SqlOutParameter("PO_COD_RACION", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerCorrelativoRacion] Ocurrio un error en la operacion del USP_SEL_GENERA_CORR_RACION : "+mensajeRespuesta);
    			throw new Exception();
    		}

			racionBean.setCodRacion((String) out.get("PO_COD_RACION"));
			racionBean.setCodigoRespuesta(codigoRespuesta);
			racionBean.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerCorrelativoRacion] Fin ");
		return racionBean;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#insertarRegistroRacion(pe.com.sigbah.common.bean.RacionBean)
	 */
	@Override
	public RacionBean insertarRegistroRacion(RacionBean racionBean) throws Exception {
		LOGGER.info("[insertarRegistroRacion] Inicio ");
		RacionBean registroRacion = new RacionBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_RAC_OPERATIVA", racionBean.getIdRacionOpe(), Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_RACION", racionBean.getNombreRacion(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_RACION", racionBean.getCodRacion(), Types.VARCHAR);
			input_objParametros.addValue("PI_IDE_DDI", racionBean.getIdDdi(), Types.NUMERIC);
			input_objParametros.addValue("PI_DIAS_ATENCION", racionBean.getDiasAtencion(), Types.NUMERIC);
			input_objParametros.addValue("PI_FEC_RACION", DateUtil.obtenerFechaHoraParseada(racionBean.getFechaRacion()), Types.DATE);
			input_objParametros.addValue("PI_TIP_RACION", racionBean.getTipoRacion(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_ANIO", racionBean.getCodAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", racionBean.getControl(), Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", racionBean.getUsuarioRegistro(), Types.VARCHAR);
					
            objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_RACIONOPERATIVACAB");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_RAC_OPERATIVA", new SqlParameter("PI_IDE_RAC_OPERATIVA", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_RACION", new SqlParameter("PI_NOMBRE_RACION", Types.VARCHAR));
			output_objParametros.put("PI_COD_RACION", new SqlParameter("PI_COD_RACION", Types.VARCHAR));
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PI_DIAS_ATENCION", new SqlParameter("PI_DIAS_ATENCION", Types.NUMERIC));
			output_objParametros.put("PI_FEC_RACION", new SqlParameter("PI_FEC_RACION", Types.DATE));
			output_objParametros.put("PI_TIP_RACION", new SqlParameter("PI_TIP_RACION", Types.VARCHAR));
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_IDE_RAC_OPERATIVA", new SqlOutParameter("PO_IDE_RAC_OPERATIVA", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_COD_RACION", new SqlOutParameter("PO_COD_RACION", Types.VARCHAR));
				
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroRacion] Ocurrio un error en la operacion del USP_INS_UPD_RACIONOPERATIVACAB : "+mensajeRespuesta);
    			throw new Exception();
    		}
		
			if( racionBean.getControl()=="I"){
				registroRacion.setIdRacionOpe(((BigDecimal)  out.get("PO_IDE_RAC_OPERATIVA")).intValue());
			}
			
			registroRacion.setCodigoRespuesta(codigoRespuesta);
			registroRacion.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
			registroRacion.setCodRacion((String) out.get("PO_COD_RACION"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroRacion] Fin ");
		return registroRacion;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#actualizarRegistroRacion(pe.com.sigbah.common.bean.RacionBean)
	 */
	@Override
	public RacionBean actualizarRegistroRacion(RacionBean racionBean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#insertarRegistroProducto(pe.com.sigbah.common.bean.ProductoRacionBean)
	 */
	@Override
	public ProductoRacionBean insertarRegistroProducto(ProductoRacionBean productoBean) throws Exception {
		LOGGER.info("[insertarRegistroProducto] Inicio ");
		ProductoRacionBean registroProducto = new ProductoRacionBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_DET_RAC_OPERATIVA", productoBean.getIdDetaRacion(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_RAC_OPERATIVA", productoBean.getIdRacion(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_PRODUCTO", productoBean.getFkIdProducto(), Types.NUMERIC);
			input_objParametros.addValue("PI_PESO_UND_PRES", productoBean.getPesoUnitarioPres(), Types.NUMERIC);
			input_objParametros.addValue("PI_CANT_RACION_KGS", productoBean.getCantRacionKg(), Types.NUMERIC);
			input_objParametros.addValue("PI_COD_ANIO", productoBean.getCodAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", "I", Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", productoBean.getUsuarioRegistro(), Types.VARCHAR);
			
            
            objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_RACIONOPERATIVADET");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DET_RAC_OPERATIVA", new SqlParameter("PI_IDE_DET_RAC_OPERATIVA", Types.NUMERIC));
			output_objParametros.put("PI_IDE_RAC_OPERATIVA", new SqlParameter("PI_IDE_RAC_OPERATIVA", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_PRODUCTO", new SqlParameter("PI_FK_IDE_PRODUCTO", Types.NUMERIC));
			output_objParametros.put("PI_PESO_UND_PRES", new SqlParameter("PI_PESO_UND_PRES", Types.NUMERIC));
			output_objParametros.put("PI_CANT_RACION_KGS", new SqlParameter("PI_CANT_RACION_KGS", Types.NUMERIC));
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroProducto] Ocurrio un error en la operacion del USP_INS_UPD_RACIONOPERATIVADET : "+mensajeRespuesta);
    			throw new Exception();
    		}
		
			
			registroProducto.setCodigoRespuesta(codigoRespuesta);
			registroProducto.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroProducto] Fin ");
		return registroProducto;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#actualizarRegistroProducto(pe.com.sigbah.common.bean.ProductoRacionBean)
	 */
	@Override
	public ProductoRacionBean actualizarRegistroProducto(ProductoRacionBean productoBean) throws Exception {
		LOGGER.info("[insertarRegistroProducto] Inicio ");
		ProductoRacionBean registroProducto = new ProductoRacionBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_DET_RAC_OPERATIVA", productoBean.getIdDetaRacion(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_RAC_OPERATIVA", productoBean.getIdRacion(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_PRODUCTO", productoBean.getFkIdProducto(), Types.NUMERIC);
			input_objParametros.addValue("PI_PESO_UND_PRES", productoBean.getPesoUnitarioPres(), Types.NUMERIC);
			input_objParametros.addValue("PI_CANT_RACION_KGS", productoBean.getCantRacionKg(), Types.NUMERIC);
			input_objParametros.addValue("PI_COD_ANIO", productoBean.getCodAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", "U", Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", productoBean.getUsuarioRegistro(), Types.VARCHAR);
			
            
            objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_RACIONOPERATIVADET");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DET_RAC_OPERATIVA", new SqlParameter("PI_IDE_DET_RAC_OPERATIVA", Types.NUMERIC));
			output_objParametros.put("PI_IDE_RAC_OPERATIVA", new SqlParameter("PI_IDE_RAC_OPERATIVA", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_PRODUCTO", new SqlParameter("PI_FK_IDE_PRODUCTO", Types.NUMERIC));
			output_objParametros.put("PI_PESO_UND_PRES", new SqlParameter("PI_PESO_UND_PRES", Types.NUMERIC));
			output_objParametros.put("PI_CANT_RACION_KGS", new SqlParameter("PI_CANT_RACION_KGS", Types.NUMERIC));
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroProducto] Ocurrio un error en la operacion del USP_INS_UPD_RACIONOPERATIVADET : "+mensajeRespuesta);
    			throw new Exception();
    		}
		
			
			registroProducto.setCodigoRespuesta(codigoRespuesta);
			registroProducto.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroProducto] Fin ");
		return registroProducto;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#listarPedidosCompra(pe.com.sigbah.common.bean.PedidoCompraBean)
	 */
	@Override
	public List<PedidoCompraBean> listarPedidosCompra(PedidoCompraBean pedidoBean) throws Exception {
		LOGGER.info("[listarPedidosCompra] Inicio ");
		List<PedidoCompraBean> lista = new ArrayList<PedidoCompraBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_COD_ANIO", pedidoBean.getCodAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_MES", Utils.getParam( pedidoBean.getCodMes()), Types.VARCHAR);
			input_objParametros.addValue("PI_ID_ESTADO", pedidoBean.getCodEstado(), Types.INTEGER);
			input_objParametros.addValue("PI_ID_DDI", pedidoBean.getFkIdeDdi(), Types.INTEGER);
			          
            
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_PEDIDO_COMPRA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_COD_MES", new SqlParameter("PI_COD_MES", Types.VARCHAR));
			output_objParametros.put("PI_ID_ESTADO", new SqlParameter("PI_ID_ESTADO", Types.INTEGER));
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.INTEGER));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new PedidoCompraMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR)); 
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarPedidosCompra] Ocurrio un error en la operacion del USP_SEL_LISTAR_PEDIDO_COMPRA : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<PedidoCompraBean>) out.get("PO_LR_RECORDSET");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarPedidosCompra] Fin ");
		return lista;
	}




	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#listarProductos(pe.com.sigbah.common.bean.ProductoRacionBean)
	 */
	@Override
	public List<ProductoRacionBean> listarProductos(ProductoRacionBean racionBean) throws Exception {
		LOGGER.info("[listarProductos] Inicio ");
		List<ProductoRacionBean> lista = new ArrayList<ProductoRacionBean>();
		try { 
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_IDE_RAC_OPERATIVA", racionBean.getIdRacion(), Types.INTEGER);
			  
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_PRODUCTO_RACION");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_RAC_OPERATIVA", new SqlParameter("PI_IDE_RAC_OPERATIVA", Types.INTEGER));
			output_objParametros.put("PO_LR_RECORDSET2", new SqlOutParameter("PO_LR_RECORDSET2", OracleTypes.CURSOR, new ProductoRacionMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarProductos] Ocurrio un error en la operacion del USP_SEL_LISTAR_PRODUCTO_RACION : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<ProductoRacionBean>) out.get("PO_LR_RECORDSET2");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarProductos] Fin ");
		return lista;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#eliminarProductoRacion(pe.com.sigbah.common.bean.ProductoRacionBean)
	 */
	@Override
	public ProductoRacionBean eliminarProductoRacion(ProductoRacionBean productoRacionBean) throws Exception {
		LOGGER.info("[eliminarProductoRacion] Inicio ");
		ProductoRacionBean registroProductoRacion = new ProductoRacionBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ide_det_rac_operativa", productoRacionBean.getIdDetaRacion(), Types.NUMERIC);

            
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_DEL_PRODUCTO_RACION");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ide_det_rac_operativa", new SqlParameter("PI_ide_det_rac_operativa", Types.NUMERIC));
			output_objParametros.put("po_CODIGO_RESPUESTA", new SqlOutParameter("po_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("po_MENSAJE_RESPUESTA", new SqlOutParameter("po_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("po_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("po_MENSAJE_RESPUESTA");
				LOGGER.info("[eliminarProductoRacion] Ocurrio un error en la operacion del USP_DEL_PRODUCTO_RACION : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			registroProductoRacion.setCodigoRespuesta(codigoRespuesta);
			registroProductoRacion.setMensajeRespuesta((String) out.get("po_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[eliminarProductoRacion] Fin ");
		return registroProductoRacion;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#obtenerRegistroRacion(java.lang.Integer)
	 */
	@Override
	public RacionBean obtenerRegistroRacion(Integer idRacion) throws Exception {
		LOGGER.info("[obtenerRegistroRacion] Inicio ");
		RacionBean racion = new RacionBean();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_IDE_RAC_OPERATIVA", idRacion, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_EDITAR_RACION");
			

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_RAC_OPERATIVA", new SqlParameter("PI_IDE_RAC_OPERATIVA", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET1", new SqlOutParameter("PO_LR_RECORDSET1", OracleTypes.CURSOR, new RegistroRacionMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("po_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerRegistroRacion] Ocurrio un error en la operacion del USP_SEL_EDITAR_RACION : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			List<RacionBean> lista = (List<RacionBean>) out.get("PO_LR_RECORDSET1");
			if (!Utils.isEmpty(lista)) {
				racion = lista.get(0);
			}
			
			racion.setCodigoRespuesta(codigoRespuesta);
			racion.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerRegistroRacion] Fin ");
		return racion;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#obtenerCorrelativoPedidoCompra(pe.com.sigbah.common.bean.PedidoCompraBean)
	 */
	@Override
	public PedidoCompraBean obtenerCorrelativoPedidoCompra(PedidoCompraBean parametros) throws Exception {
		LOGGER.info("[obtenerCorrelativoPedidoCompra] Inicio ");
		PedidoCompraBean pedidoBean = new PedidoCompraBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_COD_ANIO", parametros.getCodAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_DDI", parametros.getCodDdi(), Types.VARCHAR);
			input_objParametros.addValue("PI_FK_IDE_DDI", parametros.getFkIdeDdi(), Types.NUMERIC);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_GENERA_PEDIDO_COMPRA");
			

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_COD_DDI", new SqlParameter("PI_COD_DDI", Types.VARCHAR));
			output_objParametros.put("PI_FK_IDE_DDI", new SqlParameter("PI_FK_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PO_COD_PEDIDO_COMPRA", new SqlOutParameter("PO_COD_PEDIDO_COMPRA", Types.VARCHAR));
			output_objParametros.put("PO_COD_PEDIDO_CONCATENADO", new SqlOutParameter("PO_COD_PEDIDO_CONCATENADO", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerCorrelativoPedidoCompra] Ocurrio un error en la operacion del USP_SEL_GENERA_PEDIDO_COMPRA : "+mensajeRespuesta);
    			throw new Exception();
    		}

			pedidoBean.setCodPedido((String) out.get("PO_COD_PEDIDO_COMPRA"));
			pedidoBean.setCodPedidoConcate((String) out.get("PO_COD_PEDIDO_CONCATENADO"));
			pedidoBean.setCodigoRespuesta(codigoRespuesta);
			pedidoBean.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerCorrelativoPedidoCompra] Fin ");
		return pedidoBean;
		
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#insertarRegistroPedido(pe.com.sigbah.common.bean.PedidoCompraBean)
	 */
	@Override
	public PedidoCompraBean insertarRegistroPedido(PedidoCompraBean pedidoCompraBean) throws Exception {
		LOGGER.info("[insertarRegistroPedido] Inicio ");
		PedidoCompraBean pedidoCompra = new PedidoCompraBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_PEDIDO_COMPRA", pedidoCompraBean.getIdPedidoCom(), Types.NUMERIC);
			input_objParametros.addValue("PI_NOM_PEDIDO_COMPRA", pedidoCompraBean.getDescripcion(), Types.VARCHAR);//whr verificar
			input_objParametros.addValue("PI_IDE_ESTADO", pedidoCompraBean.getCodEstado(), Types.NUMERIC);
			input_objParametros.addValue("PI_COD_ANIO", pedidoCompraBean.getCodAnio(), Types.VARCHAR);  
			input_objParametros.addValue("PI_COD_MES", pedidoCompraBean.getCodMes(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_PEDIDO", pedidoCompraBean.getCodPedido(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_DDI", pedidoCompraBean.getCodDdi(), Types.VARCHAR);
			input_objParametros.addValue("PI_FEC_PEDIDO", DateUtil.obtenerFechaHoraParseada(pedidoCompraBean.getFecPedido()), Types.DATE);
			input_objParametros.addValue("PI_TIP_PEDIDO", pedidoCompraBean.getCodPedidoPor(), Types.VARCHAR);
			input_objParametros.addValue("PI_FK_IDE_DEE", pedidoCompraBean.getDee(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_DDI", pedidoCompraBean.getFkIdeDdi(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", pedidoCompraBean.getUsuarioRegistro(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL",pedidoCompraBean.getControl(), Types.VARCHAR);
           
			System.out.println("PI_IDE_PEDIDO_COMPRA "+pedidoCompraBean.getIdPedidoCom());
			System.out.println("PI_NOM_PEDIDO_COMPRA "+pedidoCompraBean.getDescripcion());
			System.out.println("PI_IDE_ESTADO "+pedidoCompraBean.getCodEstado());
			System.out.println("PI_COD_ANIO "+pedidoCompraBean.getCodAnio());
			System.out.println("PI_COD_MES "+pedidoCompraBean.getCodMes());
			System.out.println("PI_COD_PEDIDO "+pedidoCompraBean.getCodPedido());
			System.out.println("PI_COD_DDI "+pedidoCompraBean.getCodDdi());
			System.out.println("PI_FEC_PEDIDO "+pedidoCompraBean.getFecPedido());
			System.out.println("PI_TIP_PEDIDO "+pedidoCompraBean.getCodPedidoPor());
			System.out.println("PI_FK_IDE_DEE "+pedidoCompraBean.getDee());
			System.out.println("PI_FK_IDE_DDI "+pedidoCompraBean.getFkIdeDdi());
			System.out.println("PI_USERNAME "+pedidoCompraBean.getUsuarioRegistro());
			System.out.println("PI_CONTROL "+pedidoCompraBean.getControl());
			
			
            objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_PEDIDO_COMPRA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PEDIDO_COMPRA", new SqlParameter("PI_IDE_PEDIDO_COMPRA", Types.NUMERIC));
			output_objParametros.put("PI_NOM_PEDIDO_COMPRA", new SqlParameter("PI_NOM_PEDIDO_COMPRA", Types.VARCHAR));
			output_objParametros.put("PI_IDE_ESTADO", new SqlParameter("PI_IDE_ESTADO", Types.NUMERIC));
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_COD_MES", new SqlParameter("PI_COD_MES", Types.VARCHAR));
			output_objParametros.put("PI_COD_PEDIDO", new SqlParameter("PI_COD_PEDIDO", Types.VARCHAR));
			output_objParametros.put("PI_COD_DDI", new SqlParameter("PI_COD_DDI", Types.VARCHAR));
			output_objParametros.put("PI_FEC_PEDIDO", new SqlParameter("PI_FEC_PEDIDO", Types.DATE));
			output_objParametros.put("PI_TIP_PEDIDO", new SqlParameter("PI_TIP_PEDIDO", Types.VARCHAR));
			output_objParametros.put("PI_FK_IDE_DEE", new SqlParameter("PI_FK_IDE_DEE", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_DDI", new SqlParameter("PI_FK_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_IDE_PEDIDO_COMPRA", new SqlOutParameter("PO_IDE_PEDIDO_COMPRA", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroPedido] Ocurrio un error en la operacion del USP_INS_UPD_PEDIDO_COMPRA : "+mensajeRespuesta);
    			throw new Exception();
    		}
			if( pedidoCompraBean.getControl()=="I"){
				pedidoCompra.setIdPedidoCom(((BigDecimal)  out.get("PO_IDE_PEDIDO_COMPRA")).intValue());
			}
		
			
			pedidoCompra.setCodigoRespuesta(codigoRespuesta);
			pedidoCompra.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroPedido] Fin ");
		return pedidoCompra;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#grabarDocumentoPedidoCompra(pe.com.sigbah.common.bean.DocumentoPedidoCompraBean)
	 */
	@Override
	public DocumentoPedidoCompraBean grabarDocumentoPedidoCompra(DocumentoPedidoCompraBean documentoPedidoCompraBean)throws Exception {
	LOGGER.info("[grabarDocumentoPedidoCompra] Inicio ");
	DocumentoPedidoCompraBean registroDocumentoPedidoCompra = new DocumentoPedidoCompraBean();
	try {			
		MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
		input_objParametros.addValue("PI_FK_IDE_PEDIDO_COMPRA", documentoPedidoCompraBean.getIdPedidoCompra(), Types.NUMERIC);
		input_objParametros.addValue("PI_ID_DOCUMENTO", documentoPedidoCompraBean.getIdDocumentoPedidoCompra(), Types.NUMERIC);
		input_objParametros.addValue("PI_nro_documento", documentoPedidoCompraBean.getNroDocumento(), Types.VARCHAR);
		input_objParametros.addValue("PI_fk_ide_tip_documento", documentoPedidoCompraBean.getIdTipoDocumento(), Types.NUMERIC);
		 input_objParametros.addValue("PI_COD_ALFRESCO", documentoPedidoCompraBean.getCodigoArchivoAlfresco(), Types.VARCHAR);
		 input_objParametros.addValue("PI_NOMB_ARCHIVO", documentoPedidoCompraBean.getNombreArchivo(), Types.VARCHAR);	
		 input_objParametros.addValue("PI_FEC_DOCUMENTO", DateUtil.obtenerFechaHoraParseada(documentoPedidoCompraBean.getFechaDocumento()), Types.DATE);
		 input_objParametros.addValue("PI_FLG_ACTIVO", "1", Types.VARCHAR);// 1 para nuevo
		 input_objParametros.addValue("PI_USERNAME", documentoPedidoCompraBean.getUsuarioRegistro(), Types.VARCHAR);	
		 if(documentoPedidoCompraBean.getIdDocumentoPedidoCompra()==0 || documentoPedidoCompraBean.getIdDocumentoPedidoCompra().equals(null)){
			 input_objParametros.addValue("PI_CONTROL", "I", Types.VARCHAR);
		 }else{
			 input_objParametros.addValue("PI_CONTROL", "U", Types.VARCHAR);
		 }
			

		objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
		objJdbcCall.withoutProcedureColumnMetaDataAccess();
		objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
		objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
		objJdbcCall.withProcedureName("USP_INS_UPD_PEDIDO_FILE");

		LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
		output_objParametros.put("PI_FK_IDE_PEDIDO_COMPRA", new SqlParameter("PI_FK_IDE_PEDIDO_COMPRA", Types.NUMERIC));
		output_objParametros.put("PI_ID_DOCUMENTO", new SqlParameter("PI_ID_DOCUMENTO", Types.NUMERIC));
		output_objParametros.put("PI_nro_documento", new SqlParameter("PI_nro_documento", Types.VARCHAR));
		output_objParametros.put("PI_fk_ide_tip_documento", new SqlParameter("PI_fk_ide_tip_documento", Types.NUMERIC));
		output_objParametros.put("PI_COD_ALFRESCO", new SqlParameter("PI_COD_ALFRESCO", Types.VARCHAR));
		output_objParametros.put("PI_NOMB_ARCHIVO", new SqlParameter("PI_NOMB_ARCHIVO", Types.VARCHAR));
		output_objParametros.put("PI_FEC_DOCUMENTO", new SqlParameter("PI_FEC_DOCUMENTO", Types.DATE));
		output_objParametros.put("PI_FLG_ACTIVO", new SqlParameter("PI_FLG_ACTIVO", Types.VARCHAR));
		output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
		output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
		output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
		output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

		objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
		
		Map<String, Object> out = objJdbcCall.execute(input_objParametros);
		
		String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
		
		if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
			String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
			LOGGER.info("[grabarDocumentoPedidoCompra] Ocurrio un error en la operacion del USP_INS_UPD_PEDIDO_FILE : "+mensajeRespuesta);
			throw new Exception();
		}
		
		registroDocumentoPedidoCompra.setCodigoRespuesta(codigoRespuesta);
		registroDocumentoPedidoCompra.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));

	} catch (Exception e) {
		LOGGER.error(e.getMessage(), e);
		throw new Exception();
	}		
	LOGGER.info("[grabarDocumentoPedidoCompra] Fin ");
	return registroDocumentoPedidoCompra;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#listarDocumentoPedidoCompra(pe.com.sigbah.common.bean.DocumentoPedidoCompraBean)
	 */
	@Override
	public List<DocumentoPedidoCompraBean> listarDocumentoPedidoCompra(	DocumentoPedidoCompraBean documentoPedidoCompraBean) throws Exception {
		LOGGER.info("[listarDocumentoPedidoCompra] Inicio ");
		List<DocumentoPedidoCompraBean> lista = new ArrayList<DocumentoPedidoCompraBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_FK_IDE_PEDIDO_COMPRA", documentoPedidoCompraBean.getIdPedidoCompra(), Types.NUMERIC);
			
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_PEDIDO_FILE");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FK_IDE_PEDIDO_COMPRA", new SqlParameter("PI_FK_IDE_PEDIDO_COMPRA", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_DOCUMENTOS", new SqlOutParameter("PO_CURSOR_DOCUMENTOS", OracleTypes.CURSOR, new DocumentoPedidoCompraMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarDocumentoPedidoCompra] Ocurrio un error en la operacion del USP_SEL_LISTAR_PEDIDO_FILE : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			lista = (List<DocumentoPedidoCompraBean>) out.get("PO_CURSOR_DOCUMENTOS");
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarDocumentoPedidoCompra] Fin ");
		return lista;
	}

	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#eliminarDocumentoPedidoCompra(pe.com.sigbah.common.bean.DocumentoPedidoCompraBean)
	 */
	@Override
	public DocumentoPedidoCompraBean eliminarDocumentoPedidoCompra(DocumentoPedidoCompraBean documentoPedidoCompraBean)throws Exception {
	LOGGER.info("[eliminarDocumentoPedidoCompra] Inicio ");
	DocumentoPedidoCompraBean registroDocumentoPedidoCompra = new DocumentoPedidoCompraBean();
	try {			
		MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
		input_objParametros.addValue("PI_ID_DOCUMENTO", documentoPedidoCompraBean.getIdDocumentoPedidoCompra(), Types.NUMERIC);

		objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
		objJdbcCall.withoutProcedureColumnMetaDataAccess();
		objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
		objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
		objJdbcCall.withProcedureName("USP_DEL_PEDIDO_FILE");

		LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
		output_objParametros.put("PI_ID_DOCUMENTO", new SqlParameter("PI_ID_DOCUMENTO", Types.NUMERIC));
		output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
		output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

		objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
		
		Map<String, Object> out = objJdbcCall.execute(input_objParametros);
		
		String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
		
		if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
			String mensajeRespuesta = (String) out.get("po_MENSAJE_RESPUESTA");
			LOGGER.info("[eliminarDocumentoPedidoCompra] Ocurrio un error en la operacion del USP_DEL_DOCUMENTO_CC : "+mensajeRespuesta);
			throw new Exception();
		}
		
		registroDocumentoPedidoCompra.setCodigoRespuesta(codigoRespuesta);
		registroDocumentoPedidoCompra.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));

	} catch (Exception e) {
		LOGGER.error(e.getMessage(), e);
		throw new Exception();
	}		
	LOGGER.info("[eliminarDocumentoPedidoCompra] Fin ");
	return registroDocumentoPedidoCompra;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#grabarProductoPedidoCompra(pe.com.sigbah.common.bean.ProductoPedidoCompraBean)
	 */
	@Override
	public ProductoPedidoCompraBean grabarProductoPedidoCompra(ProductoPedidoCompraBean productoPedidoCompraBean)throws Exception {
		LOGGER.info("[grabarProductoPedidoCompra] Inicio ");
		ProductoPedidoCompraBean registroProductoPedidoCompra = new ProductoPedidoCompraBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_DET_PEDIDO_COMPRA", productoPedidoCompraBean.getIdDetallePedidoCompra(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_PEDIDO_COMPRA", productoPedidoCompraBean.getIdPedidoCompra(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_PRODUCTO", productoPedidoCompraBean.getIdProducto(), Types.NUMERIC);
			input_objParametros.addValue("PI_CANTIDAD", productoPedidoCompraBean.getCantidad(), Types.NUMERIC);
			input_objParametros.addValue("PI_PRECIO_UNITARIO", productoPedidoCompraBean.getPrecioUnitario(), Types.NUMERIC);
			
			input_objParametros.addValue("PI_USERNAME", productoPedidoCompraBean.getUsuarioRegistro(), Types.VARCHAR);			
			 if(productoPedidoCompraBean.getIdDetallePedidoCompra()== 0 || productoPedidoCompraBean.getIdDetallePedidoCompra().equals(null)){
				 input_objParametros.addValue("PI_CONTROL", "I", Types.VARCHAR);
			 }else{
				 input_objParametros.addValue("PI_CONTROL", "U", Types.VARCHAR);
			 }
		
                     
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_PRODUCTO_PEDIDO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DET_PEDIDO_COMPRA", new SqlParameter("PI_IDE_DET_PEDIDO_COMPRA", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_PEDIDO_COMPRA", new SqlParameter("PI_FK_IDE_PEDIDO_COMPRA", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_PRODUCTO", new SqlParameter("PI_FK_IDE_PRODUCTO", Types.NUMERIC));
			output_objParametros.put("PI_CANTIDAD", new SqlParameter("PI_CANTIDAD", Types.NUMERIC));
			output_objParametros.put("PI_PRECIO_UNITARIO", new SqlParameter("PI_PRECIO_UNITARIO", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[grabarProductoPedidoCompra] Ocurrio un error en la operacion del USP_INS_UPD_PRODUCTO_PEDIDO : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			registroProductoPedidoCompra.setCodigoRespuesta(codigoRespuesta);
			registroProductoPedidoCompra.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[grabarProductoPedidoCompra] Fin ");
		return registroProductoPedidoCompra;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#listarProductoPedidoCompra(pe.com.sigbah.common.bean.ProductoPedidoCompraBean)
	 */
	@Override
	public List<ProductoPedidoCompraBean> listarProductoPedidoCompra(ProductoPedidoCompraBean producto)
			throws Exception {
		LOGGER.info("[listarProductoPedidoCompra] Inicio ");
		List<ProductoPedidoCompraBean> lista = new ArrayList<ProductoPedidoCompraBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_FK_IDE_PEDIDO_COMPRA", producto.getIdDetallePedidoCompra(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_PRODUCTO_PEDIDO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FK_IDE_PEDIDO_COMPRA", new SqlParameter("PI_FK_IDE_PEDIDO_COMPRA", Types.NUMERIC));
			output_objParametros.put("PO_CUR_PRODUCTOS_COMPRA", new SqlOutParameter("PO_CUR_PRODUCTOS_COMPRA", OracleTypes.CURSOR, new ProductoPedidoCompraMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarProductoPedidoCompra] Ocurrio un error en la operacion del USP_SEL_LISTAR_PRODUCTO_PEDIDO : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			lista = (List<ProductoPedidoCompraBean>) out.get("PO_CUR_PRODUCTOS_COMPRA");
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarProductoPedidoCompra] Fin ");
		return lista;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#eliminarProductoPedidoCompra(pe.com.sigbah.common.bean.ProductoPedidoCompraBean)
	 */
	@Override
	public ProductoPedidoCompraBean eliminarProductoPedidoCompra(ProductoPedidoCompraBean productoPedidoCompraBean)throws Exception {
		LOGGER.info("[eliminarProductoPedidoCompra] Inicio ");
		ProductoPedidoCompraBean registroProductooPedidoCompra = new ProductoPedidoCompraBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_DET_PEDIDO_COMPRA", productoPedidoCompraBean.getIdDetallePedidoCompra(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", productoPedidoCompraBean.getUsuarioRegistro(), Types.VARCHAR);

            objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_DEL_PRODUCTO_PEDIDO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DET_PEDIDO_COMPRA", new SqlParameter("PI_IDE_DET_PEDIDO_COMPRA", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("po_MENSAJE_RESPUESTA");
				LOGGER.info("[eliminarProductoPedidoCompra] Ocurrio un error en la operacion del USP_DEL_PRODUCTO_PEDIDO : "+mensajeRespuesta);
				throw new Exception();
			}
			
			registroProductooPedidoCompra.setCodigoRespuesta(codigoRespuesta);
			registroProductooPedidoCompra.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[eliminarProductoPedidoCompra] Fin ");
		return registroProductooPedidoCompra;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#obtenerPedidoCompra(java.lang.Integer)
	 */
	@Override
	public PedidoCompraBean obtenerPedidoCompra(Integer idPedidoCompra) throws Exception {
		LOGGER.info("[obtenerPedidoCompra] Inicio ");
		PedidoCompraBean pedidoCompra = new PedidoCompraBean();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_IDE_PEDIDO_COMPRA", idPedidoCompra, Types.NUMERIC);
			
                    
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_EDITAR_PEDIDO_COMPRA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PEDIDO_COMPRA", new SqlParameter("PI_IDE_PEDIDO_COMPRA", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new RegistroPedidoCompraMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerPedidoCompra] Ocurrio un error en la operacion del USP_SEL_EDITAR_PEDIDO_COMPRA : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			List<PedidoCompraBean> lista = (List<PedidoCompraBean>) out.get("PO_LR_RECORDSET");
			if (!Utils.isEmpty(lista)) {
				pedidoCompra = lista.get(0);
			}

			pedidoCompra.setCodigoRespuesta(codigoRespuesta);
			pedidoCompra.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerPedidoCompra] Fin ");
		return pedidoCompra;
	}

	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#listarRequerimientoDetalle(pe.com.sigbah.common.bean.RequerimientoBean)
	 */
	@Override
	public List<EmergenciaBean> listarRequerimientoDetalle(RequerimientoBean requerimientoBean) throws Exception {
		LOGGER.info("[listarRequerimientoDetalle] Inicio ");
		List<EmergenciaBean> lista = new ArrayList<EmergenciaBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_COD_ANIO", requerimientoBean.getCodAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_IDE_REQUERIMIENTO", requerimientoBean.getIdRequerimiento(), Types.NUMERIC);
			
			
		            
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_listar_REQUERIMIENTO2");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_IDE_REQUERIMIENTO", new SqlParameter("PI_IDE_REQUERIMIENTO", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET2", new SqlOutParameter("PO_LR_RECORDSET2", OracleTypes.CURSOR, new RequerimientoDetalleMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarRequerimientoDetalle] Ocurrio un error en la operacion del USP_SEL_LISTAR_PRODUCTO_PEDIDO : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			lista = (List<EmergenciaBean>) out.get("PO_LR_RECORDSET2");
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarRequerimientoDetalle] Fin ");
		return lista;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#actualizarDamnificados(pe.com.sigbah.common.bean.EmergenciaBean)
	 */
	@Override
	public EmergenciaBean actualizarDamnificados(EmergenciaBean emergenciaBean) throws Exception {
		LOGGER.info("[actualizarDamnificados] Inicio ");
		EmergenciaBean emergenciaRequerimiento = new EmergenciaBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_REQ_DAMNIFICADO", emergenciaBean.getFkIdRequerimiento(), Types.NUMERIC);
			input_objParametros.addValue("PI_NUM_FAM_AFECTADAS_REAL", emergenciaBean.getFamAfectadoReal(), Types.NUMERIC);
			input_objParametros.addValue("PI_NUM_FAM_DAMNIFICADAS_REAL", emergenciaBean.getFamDamnificadoReal(), Types.NUMERIC);
			input_objParametros.addValue("PI_NUM_PER_AFECTADAS_REAL", emergenciaBean.getPersoAfectadoReal(), Types.NUMERIC);
			input_objParametros.addValue("PI_NUM_PER_DAMNIFICADAS_REAL", emergenciaBean.getPersoDamnificadoReal(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", emergenciaBean.getUsuarioRegistro(), Types.VARCHAR);
			 
                    
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_UPD_REQUERIMIENTO_DET");
			
			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_REQ_DAMNIFICADO", new SqlParameter("PI_IDE_REQ_DAMNIFICADO", Types.NUMERIC));
			output_objParametros.put("PI_NUM_FAM_AFECTADAS_REAL", new SqlParameter("PI_NUM_FAM_AFECTADAS_REAL", Types.NUMERIC));
			output_objParametros.put("PI_NUM_FAM_DAMNIFICADAS_REAL", new SqlParameter("PI_NUM_FAM_DAMNIFICADAS_REAL", Types.NUMERIC));
			output_objParametros.put("PI_NUM_PER_AFECTADAS_REAL", new SqlParameter("PI_NUM_PER_AFECTADAS_REAL", Types.NUMERIC));
			output_objParametros.put("PI_NUM_PER_DAMNIFICADAS_REAL", new SqlParameter("PI_NUM_PER_DAMNIFICADAS_REAL", Types.NUMERIC));
			
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[actualizarDamnificados] Ocurrio un error en la operacion del USP_UPD_REQUERIMIENTO_DET : "+mensajeRespuesta);
    			throw new Exception();
    		}
		
			
			emergenciaRequerimiento.setCodigoRespuesta(codigoRespuesta);
			emergenciaRequerimiento.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[actualizarDamnificados] Fin ");
		return emergenciaRequerimiento;
	}
	

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#listarDee(pe.com.sigbah.common.bean.DeeBean)
	 */
	@Override
	public List<DeeBean> listarDee(DeeBean deeBean) throws Exception {
		LOGGER.info("[listarDee] Inicio ");
		List<DeeBean> lista = new ArrayList<DeeBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_COD_ANIO", deeBean.getCodAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_MES", Utils.getParam( deeBean.getCodMes()), Types.VARCHAR);
			input_objParametros.addValue("PI_ESTADO", deeBean.getIdEstado(), Types.INTEGER);
			
            objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_DEE");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_COD_MES", new SqlParameter("PI_COD_MES", Types.VARCHAR));
			output_objParametros.put("PI_ESTADO", new SqlParameter("PI_ESTADO", Types.INTEGER));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new DecretoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR)); 
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarDee] Ocurrio un error en la operacion del USP_SEL_LISTAR_DEE : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<DeeBean>) out.get("PO_LR_RECORDSET");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarDee] Fin ");
		return lista;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#obtenerDee(java.lang.Integer)
	 */
	@Override
	public DeeBean obtenerDee(Integer idDee) throws Exception {
		LOGGER.info("[obtenerDee] Inicio ");
		DeeBean dee = new DeeBean();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_IDE_DEE", idDee, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_EDITAR_DEE");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DEE", new SqlParameter("PI_IDE_DEE", Types.NUMERIC));
			output_objParametros.put("V_CURSOR_DEE", new SqlOutParameter("V_CURSOR_DEE", OracleTypes.CURSOR, new RegistroDeeMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerDee] Ocurrio un error en la operacion del USP_SEL_EDITAR_PEDIDO_COMPRA : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			List<DeeBean> lista = (List<DeeBean>) out.get("V_CURSOR_DEE");
			if (!Utils.isEmpty(lista)) {
				dee = lista.get(0);
			}

			dee.setCodigoRespuesta(codigoRespuesta);
			dee.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerDee] Fin ");
		return dee;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#insertarRegistroDee(pe.com.sigbah.common.bean.DeeBean)
	 */
	@Override
	public DeeBean insertarRegistroDee(DeeBean deeBean) throws Exception {
		LOGGER.info("[insertarRegistroDee] Inicio ");
		DeeBean dee = new DeeBean();
		try {		
			
			System.out.println("ID: "+deeBean.getIdDee()+ " - "+deeBean.getControl());
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_DEE", deeBean.getIdDee(), Types.NUMERIC);
			input_objParametros.addValue("PI_NRO_DEE", deeBean.getNumDee(), Types.VARCHAR);
			input_objParametros.addValue("PI_NOM_DECLARATORIA", deeBean.getNomDee(), Types.VARCHAR);
			input_objParametros.addValue("PI_FEC_INICIO", DateUtil.obtenerFechaHoraParseada(deeBean.getFechaIni()), Types.DATE);
			input_objParametros.addValue("PI_FEC_FIN", DateUtil.obtenerFechaHoraParseada(deeBean.getFechaFin()), Types.DATE);
			input_objParametros.addValue("PI_PLAZO", deeBean.getPlazo(), Types.NUMERIC);
			input_objParametros.addValue("PI_ID_ESTADO_DEE", deeBean.getIdEstado(), Types.NUMERIC);  
			input_objParametros.addValue("PI_TIP_FENOMENO", deeBean.getMotivo(), Types.VARCHAR);
			input_objParametros.addValue("PI_FLG_PRORROGA", deeBean.getFlgProrroga(), Types.VARCHAR);
			input_objParametros.addValue("PI_OBSERVACION", deeBean.getObservacion(), Types.VARCHAR);
			input_objParametros.addValue("PI_NOM_ARCHIVO", deeBean.getNombreArchivo(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_ALFRESCO", deeBean.getCodigoArchivoAlfresco(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", deeBean.getControl(), Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", deeBean.getUsuarioRegistro(), Types.VARCHAR);
			
            objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_DEE");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DEE", new SqlParameter("PI_IDE_DEE", Types.NUMERIC));
			output_objParametros.put("PI_NRO_DEE", new SqlParameter("PI_NRO_DEE", Types.VARCHAR));
			output_objParametros.put("PI_NOM_DECLARATORIA", new SqlParameter("PI_NOM_DECLARATORIA", Types.VARCHAR));
			output_objParametros.put("PI_FEC_INICIO", new SqlParameter("PI_FEC_INICIO", Types.DATE));
			output_objParametros.put("PI_FEC_FIN", new SqlParameter("PI_FEC_FIN", Types.DATE));
			output_objParametros.put("PI_PLAZO", new SqlParameter("PI_PLAZO", Types.NUMERIC));
			output_objParametros.put("PI_ID_ESTADO_DEE", new SqlParameter("PI_ID_ESTADO_DEE", Types.NUMERIC));
			output_objParametros.put("PI_TIP_FENOMENO", new SqlParameter("PI_TIP_FENOMENO", Types.VARCHAR));
			output_objParametros.put("PI_FLG_PRORROGA", new SqlParameter("PI_FLG_PRORROGA", Types.VARCHAR));
			output_objParametros.put("PI_OBSERVACION", new SqlParameter("PI_OBSERVACION", Types.VARCHAR));
			output_objParametros.put("PI_NOM_ARCHIVO", new SqlParameter("PI_NOM_ARCHIVO", Types.VARCHAR));
			output_objParametros.put("PI_COD_ALFRESCO", new SqlParameter("PI_COD_ALFRESCO", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_IDE_DEE", new SqlOutParameter("PO_IDE_DEE", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroDee] Ocurrio un error en la operacion del USP_INS_UPD_DEE : "+mensajeRespuesta);
    			throw new Exception();
    		}
			if( deeBean.getControl()=="I"){
				dee.setIdDee(((BigDecimal)  out.get("PO_IDE_DEE")).intValue());
			}
		
			dee.setCodigoRespuesta(codigoRespuesta);
			dee.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroDee] Fin ");
		return dee;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#listarUbigeoDee(pe.com.sigbah.common.bean.UbigeoIneiBean)
	 */
	@Override
	public List<UbigeoIneiBean> listarUbigeoDee(UbigeoIneiBean ubigeoBean) throws Exception {
		LOGGER.info("[listarUbigeoDee] Inicio ");
		List<UbigeoIneiBean> lista = new ArrayList<UbigeoIneiBean>();
		try {				
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_COD_PROVINCIA", Utils.getParam(ubigeoBean.getCodprov()), Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_UBIGEO");
			
			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_PROVINCIA", new SqlParameter("PI_COD_PROVINCIA", Types.VARCHAR));
			output_objParametros.put("CURSOR_UBIGEO", new SqlOutParameter("CURSOR_UBIGEO", OracleTypes.CURSOR, new UbigeoDeeMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarUbigeoDee] Ocurrio un error en la operacion del USP_SEL_UBIGEO : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<UbigeoIneiBean>) out.get("CURSOR_UBIGEO");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarUbigeoDee] Fin ");
		return lista;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#pasarDistritosUbigeoDee(pe.com.sigbah.common.bean.DeeBean)
	 */
	@Override
	public UbigeoDeeBean pasarDistritosUbigeoDee(DeeBean deeBean) throws Exception {
		LOGGER.info("[pasarDistritosUbigeoDee] Inicio ");
		UbigeoDeeBean ubigeoDee = new UbigeoDeeBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_PK_IDE_DEE", deeBean.getIdDee(), Types.NUMERIC);
			input_objParametros.addValue("PI_COD_DISTRITO", deeBean.getCodDistrito(), Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", deeBean.getUsuarioRegistro(), Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_DEE_UBIGEO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_PK_IDE_DEE", new SqlParameter("PI_PK_IDE_DEE", Types.NUMERIC));
			output_objParametros.put("PI_COD_DISTRITO", new SqlParameter("PI_COD_DISTRITO", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[pasarDistritosUbigeoDee] Ocurrio un error en la operacion del USP_INS_DEE_UBIGEO : "+mensajeRespuesta);
    			throw new Exception();
    		}
		
			
			ubigeoDee.setCodigoRespuesta(codigoRespuesta);
			ubigeoDee.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[pasarDistritosUbigeoDee] Fin ");
		return ubigeoDee;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#listarDistritosEmergencia(pe.com.sigbah.common.bean.UbigeoDeeBean)
	 */
	@Override
	public List<UbigeoDeeBean> listarDistritosEmergencia(UbigeoDeeBean ubigeoDeeBean) throws Exception {
		LOGGER.info("[listarDistritosEmergencia] Inicio ");
		List<UbigeoDeeBean> lista = new ArrayList<UbigeoDeeBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_PK_IDE_DEE", ubigeoDeeBean.getIdDeeDetalle(), Types.NUMERIC);
		
		            
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_LISTAR_UBIGEO_DEE");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_PK_IDE_DEE", new SqlParameter("PI_PK_IDE_DEE", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_UBIGEO", new SqlOutParameter("PO_CURSOR_UBIGEO", OracleTypes.CURSOR, new DeeDistritoEmergenciaMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarDistritosEmergencia] Ocurrio un error en la operacion del USP_LISTAR_UBIGEO_DEE : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			lista = (List<UbigeoDeeBean>) out.get("PO_CURSOR_UBIGEO");
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarDistritosEmergencia] Fin ");
		return lista;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#eliminarDistritoEmergencia(pe.com.sigbah.common.bean.UbigeoDeeBean)
	 */
	@Override
	public UbigeoDeeBean eliminarDistritoEmergencia(UbigeoDeeBean ubigeoDeeBean) throws Exception {
		LOGGER.info("[eliminarDistritoEmergencia] Inicio ");
		UbigeoDeeBean ubigeoDee = new UbigeoDeeBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_DEE_DET", ubigeoDeeBean.getIdDeeDetalle(), Types.NUMERIC);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_DEL_UBIGEO_DEE");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DEE_DET", new SqlParameter("PI_IDE_DEE_DET", Types.NUMERIC));
			output_objParametros.put("po_CODIGO_RESPUESTA", new SqlOutParameter("po_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("po_MENSAJE_RESPUESTA", new SqlOutParameter("po_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("po_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("po_MENSAJE_RESPUESTA");
				LOGGER.info("[eliminarDistritoEmergencia] Ocurrio un error en la operacion del USP_DEL_UBIGEO_DEE : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			ubigeoDee.setCodigoRespuesta(codigoRespuesta);
			ubigeoDee.setMensajeRespuesta((String) out.get("po_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[eliminarDistritoEmergencia] Fin ");
		return ubigeoDee;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#obtenerReporteRequerimiento(java.lang.Integer)
	 */
	@Override
	public ListaRespuestaRequerimientoBean obtenerReporteRequerimiento(Integer idRequerimiento) throws Exception {
		LOGGER.info("[obtenerReporteRequerimiento] Inicio ");
		ListaRespuestaRequerimientoBean listaRetorno = new ListaRespuestaRequerimientoBean();
		
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();	
			input_objParametros.addValue("PI_REQUERIMIENTO", idRequerimiento, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_REPORT_REQUERIMIENTO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_REQUERIMIENTO", new SqlParameter("PI_REQUERIMIENTO", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_UBIGEO", new SqlOutParameter("PO_CURSOR_UBIGEO", OracleTypes.CURSOR, new ReporteDetalleRequerimientoMapper()));
			output_objParametros.put("PO_CURSOR_DATOS_GENERALES", new SqlOutParameter("PO_CURSOR_DATOS_GENERALES", OracleTypes.CURSOR, new ReporteCabeceraRequerimiento1Mapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerReporteRequerimiento] Ocurrio un error en la operacion del USP_REPORT_REQUERIMIENTO : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			List<RequerimientoBean> listaCabecera = (List<RequerimientoBean>) out.get("PO_CURSOR_DATOS_GENERALES");
			List<EmergenciaBean> listaDetalle = (List<EmergenciaBean>) out.get("PO_CURSOR_UBIGEO");
			
			listaRetorno.setLstCabecera(listaCabecera);
			listaRetorno.setLstDetalle(listaDetalle);
			
			listaRetorno.setCodigoRespuesta(codigoRespuesta);
			listaRetorno.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerReporteRequerimiento] Fin ");
		return listaRetorno;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#obtenerReportePedidoCompra(java.lang.Integer)
	 */
	@Override
	public ListaRespuestaPedidoCompraBean obtenerReportePedidoCompra(Integer idPedidoCompra) throws Exception {
		LOGGER.info("[obtenerReportePedidoCompra] Inicio ");
		ListaRespuestaPedidoCompraBean listaRetorno = new ListaRespuestaPedidoCompraBean();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_FK_IDE_PEDIDO_COMPRA",idPedidoCompra, Types.INTEGER);
			
                    
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_REPORT_PEDIDO_COMPRA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FK_IDE_PEDIDO_COMPRA", new SqlParameter("PI_FK_IDE_PEDIDO_COMPRA", Types.INTEGER));
			output_objParametros.put("PO_CURSOR_PEDIDO_COMPRA", new SqlOutParameter("PO_CURSOR_PEDIDO_COMPRA", OracleTypes.CURSOR, new ReporteCabeceraPedidoCompraMapper()));
			output_objParametros.put("PO_CURSOR_PEDIDO_COMPRA_PROD", new SqlOutParameter("PO_CURSOR_PEDIDO_COMPRA_PROD", OracleTypes.CURSOR, new ReporteDetallePedidoCompraMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
	objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerReporteRequerimiento] Ocurrio un error en la operacion del USP_REPORT_REQUERIMIENTO : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			List<PedidoCompraReporteBean> listaCabecera = (List<PedidoCompraReporteBean>) out.get("PO_CURSOR_PEDIDO_COMPRA");
			List<PedidoCompraReporteBean> listaDetalle = (List<PedidoCompraReporteBean>) out.get("PO_CURSOR_PEDIDO_COMPRA_PROD");
			
			listaRetorno.setLstCabecera(listaCabecera);
			listaRetorno.setLstDetalle(listaDetalle);
			
			listaRetorno.setCodigoRespuesta(codigoRespuesta);
			listaRetorno.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerReporteRequerimiento] Fin ");
		return listaRetorno;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#listarProg_x_mes(pe.com.sigbah.common.bean.ConsultaProgramacionBean)
	 */
	@Override
	public List<ConsultaProgramacionBean> listarProg_x_mes(ConsultaProgramacionBean consultaBean) throws Exception {
		LOGGER.info("[listarProg_x_mes] Inicio ");
		List<ConsultaProgramacionBean> lista = new ArrayList<ConsultaProgramacionBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			String cadena=consultaBean.getCodigoMes();
			
			input_objParametros.addValue("PI_COD_ANIO", consultaBean.getCodigoAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_M1", "", Types.VARCHAR);
     		input_objParametros.addValue("PI_M2", "", Types.VARCHAR);
     		input_objParametros.addValue("PI_M3", "", Types.VARCHAR);
     		input_objParametros.addValue("PI_M4","", Types.VARCHAR);
     		input_objParametros.addValue("PI_M5", "", Types.VARCHAR);
     		input_objParametros.addValue("PI_M6", "", Types.VARCHAR);
     		input_objParametros.addValue("PI_M7", "", Types.VARCHAR);
     		input_objParametros.addValue("PI_M8", "", Types.VARCHAR);
     		input_objParametros.addValue("PI_M9", "", Types.VARCHAR);
     		input_objParametros.addValue("PI_M10", "", Types.VARCHAR);
     		input_objParametros.addValue("PI_M11", "", Types.VARCHAR);
     		input_objParametros.addValue("PI_M12", "", Types.VARCHAR);
     		
			for (String retval: cadena.split(",")) {
		         System.out.println(retval);
		         int valorNUm=Integer.parseInt(retval);
		         switch (valorNUm) {
		         	 case 1:// case "01":
		         		input_objParametros.addValue("PI_M1", "01", Types.VARCHAR);
		         		break;
		         	case 2://case "02":
		         		input_objParametros.addValue("PI_M2", "02", Types.VARCHAR);
		         		break;
		         	case 3://case "03":
		         		input_objParametros.addValue("PI_M3", "03", Types.VARCHAR);
		         		break;
		         	case 4://case "04":
		         		input_objParametros.addValue("PI_M4", "04", Types.VARCHAR);
		         		break;
		         	case 5://case "05":
		         		input_objParametros.addValue("PI_M5", "05", Types.VARCHAR);
		         		break;
		         	case 6://case "06":
		         		input_objParametros.addValue("PI_M6", "06", Types.VARCHAR);
		         		break;
		         	case 7://case "07":
		         		input_objParametros.addValue("PI_M7", "07", Types.VARCHAR);
		         		break;
		         	case 8://case "08":
		         		input_objParametros.addValue("PI_M8", "08", Types.VARCHAR);
		         		break;
		         	case 9://case "09":
		         		input_objParametros.addValue("PI_M9", "09", Types.VARCHAR);
		         		break;
		         	case 10://case "10":
		         		input_objParametros.addValue("PI_M10", "10", Types.VARCHAR);
		         		break;
		         	case 11://case "11":
		         		input_objParametros.addValue("PI_M11", "11", Types.VARCHAR);
		         		break;
		         	case 12://case "12":
		         		input_objParametros.addValue("PI_M12", "12", Types.VARCHAR);
		         		break;
  
				  }
		      }

			input_objParametros.addValue("PI_IDE_DDI", Utils.getParam(consultaBean.getIdDdi()), Types.INTEGER);
			input_objParametros.addValue("PI_ESTADO", consultaBean.getIdEstado(), Types.INTEGER);
            
		
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_PROG_X_MES");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_M1", new SqlParameter("PI_M1", Types.VARCHAR));
			output_objParametros.put("PI_M2", new SqlParameter("PI_M2", Types.VARCHAR));
			output_objParametros.put("PI_M3", new SqlParameter("PI_M3", Types.VARCHAR));
			output_objParametros.put("PI_M4", new SqlParameter("PI_M4", Types.VARCHAR));
			output_objParametros.put("PI_M5", new SqlParameter("PI_M5", Types.VARCHAR));
			output_objParametros.put("PI_M6", new SqlParameter("PI_M6", Types.VARCHAR));
			output_objParametros.put("PI_M7", new SqlParameter("PI_M7", Types.VARCHAR));
			output_objParametros.put("PI_M8", new SqlParameter("PI_M8", Types.VARCHAR));
			output_objParametros.put("PI_M9", new SqlParameter("PI_M9", Types.VARCHAR));
			output_objParametros.put("PI_M10", new SqlParameter("PI_M10", Types.VARCHAR));
			output_objParametros.put("PI_M11", new SqlParameter("PI_M11", Types.VARCHAR));
			output_objParametros.put("PI_M12", new SqlParameter("PI_M12", Types.VARCHAR));
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.INTEGER));
			output_objParametros.put("PI_ESTADO", new SqlParameter("PI_ESTADO", Types.INTEGER));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new ConsultaProgramacionMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarProg_x_mes] Ocurrio un error en la operacion del USP_SEL_LISTAR_PROG_X_MES : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<ConsultaProgramacionBean>) out.get("PO_LR_RECORDSET");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarProg_x_mes] Fin ");
		return lista;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#listarProg_x_dee(pe.com.sigbah.common.bean.ConsultaProgramacionBean)
	 */
	@Override 
	public List<ConsultaProgramacionBean> listarProg_x_dee(ConsultaProgramacionBean consultaBean) throws Exception {
		LOGGER.info("[listarProg_x_dee] Inicio ");
		List<ConsultaProgramacionBean> lista = new ArrayList<ConsultaProgramacionBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			String cadena=consultaBean.getNroDee();
			
			input_objParametros.addValue("PI_COD_ANIO", consultaBean.getCodigoAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_DEE1", null, Types.INTEGER);
     		input_objParametros.addValue("PI_DEE2", null, Types.INTEGER);
     		input_objParametros.addValue("PI_DEE3", null, Types.INTEGER);
     		input_objParametros.addValue("PI_DEE4",null, Types.INTEGER);
     		input_objParametros.addValue("PI_DEE5", null, Types.INTEGER);
     		
     		List<DeeConsultaBean> lstcondee=new ArrayList<DeeConsultaBean>();
     		int num=1;
			for (String retval: cadena.split(",")) {
		         System.out.println(retval);
		         int numDee =Integer.parseInt(retval);
		         DeeConsultaBean condee=new DeeConsultaBean();
		         condee.setNum(num);
		         condee.setIdDee(numDee);
		         lstcondee.add(condee);
		         num++;
			 } 
			for (DeeConsultaBean deeConsultaBean : lstcondee) {
				 switch (deeConsultaBean.getNum()) {
	         	 case 1:// case "01":
	         		input_objParametros.addValue("PI_DEE1", deeConsultaBean.getIdDee(), Types.INTEGER);
	         		break;
	         	case 2://case "02":
	         		input_objParametros.addValue("PI_DEE2", deeConsultaBean.getIdDee(), Types.INTEGER);
	         		break;
	         	case 3://case "03":
	         		input_objParametros.addValue("PI_DEE3", deeConsultaBean.getIdDee(), Types.INTEGER);
	         		break;
	         	case 4://case "04":
	         		input_objParametros.addValue("PI_DEE4", deeConsultaBean.getIdDee(), Types.INTEGER);
	         		break;
	         	case 5://case "05":
	         		input_objParametros.addValue("PI_DEE5", deeConsultaBean.getIdDee(), Types.INTEGER);
	         		break;
	         }
			}
		        
		     

			input_objParametros.addValue("PI_IDE_DDI", Utils.getParam(consultaBean.getIdDdi()), Types.INTEGER);
			input_objParametros.addValue("PI_ESTADO", consultaBean.getIdEstado(), Types.INTEGER);
            
		
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_PROG_X_DEE");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_DEE1", new SqlParameter("PI_DEE1", Types.INTEGER));
			output_objParametros.put("PI_DEE2", new SqlParameter("PI_DEE2", Types.INTEGER));
			output_objParametros.put("PI_DEE3", new SqlParameter("PI_DEE3", Types.INTEGER));
			output_objParametros.put("PI_DEE4", new SqlParameter("PI_DEE4", Types.INTEGER));
			output_objParametros.put("PI_DEE5", new SqlParameter("PI_DEE5", Types.INTEGER));
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.INTEGER));
			output_objParametros.put("PI_ESTADO", new SqlParameter("PI_ESTADO", Types.INTEGER));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new ConsultaProgramacionMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarProg_x_dee] Ocurrio un error en la operacion del USP_SEL_LISTAR_PROG_X_DEE : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<ConsultaProgramacionBean>) out.get("PO_LR_RECORDSET");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarProg_x_dee] Fin ");
		return lista;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#obtenerConsultaProducto(java.lang.Integer)
	 */
	@Override
	public ListaRespuestaConsultaProductoBean obtenerConsultaProducto(Integer idProgramacion) throws Exception {
		LOGGER.info("[obtenerConsultaProducto] Inicio ");
		ListaRespuestaConsultaProductoBean listaRetorno = new ListaRespuestaConsultaProductoBean();
		
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();	
			input_objParametros.addValue("PI_ID_PROGRAMACION", idProgramacion, Types.NUMERIC);
			
		
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_MOSTRAR_PRODUCTOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_PROGRAMACION", new SqlParameter("PI_ID_PROGRAMACION", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_ALIMENTOS", new SqlOutParameter("PO_CURSOR_ALIMENTOS", OracleTypes.CURSOR, new RegistroConsultaProgramacionMapper()));
			output_objParametros.put("PO_CURSOR_BNA", new SqlOutParameter("PO_CURSOR_BNA", OracleTypes.CURSOR, new RegistroConsultaProgramacionMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerConsultaProducto] Ocurrio un error en la operacion del USP_SEL_MOSTRAR_PRODUCTOS : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			List<ProductoConsultaProductoBean> listaAlimentaria = (List<ProductoConsultaProductoBean>) out.get("PO_CURSOR_ALIMENTOS");
			List<ProductoConsultaProductoBean> listaNoAlimentaria = (List<ProductoConsultaProductoBean>) out.get("PO_CURSOR_BNA");
			
			listaRetorno.setLstAlimentaria(listaAlimentaria);
			listaRetorno.setLstNoAlimentaria(listaNoAlimentaria);
			
			listaRetorno.setCodigoRespuesta(codigoRespuesta);
			listaRetorno.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerConsultaProducto] Fin ");
		return listaRetorno;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#listarConsultaPedidosCompra(pe.com.sigbah.common.bean.ConsultaPedidoCompraBean)
	 */
	@Override
	public List<ConsultaPedidoCompraBean> listarConsultaPedidosCompra(ConsultaPedidoCompraBean consultaBean) throws Exception {
		LOGGER.info("[listarConsultaPedidosCompra] Inicio ");
		List<ConsultaPedidoCompraBean> lista = new ArrayList<ConsultaPedidoCompraBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			
        	input_objParametros.addValue("PI_COD_ANIO", consultaBean.getCodigoAnio(), Types.VARCHAR);
  			input_objParametros.addValue("PI_ID_ESTADO", Utils.getParam(consultaBean.getIdEstado()), Types.INTEGER);
  			input_objParametros.addValue("PI_TIPO_PEDIDO", Utils.getParam(consultaBean.getMotivo()), Types.INTEGER);
  			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_PEDIDO_COMPRA_NACIONAL");
			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_ID_ESTADO", new SqlParameter("PI_ID_ESTADO", Types.INTEGER));
			output_objParametros.put("PI_TIPO_PEDIDO", new SqlParameter("PI_TIPO_PEDIDO", Types.INTEGER));
			output_objParametros.put("PO_CURSOR_PEDIDOS", new SqlOutParameter("PO_CURSOR_PEDIDOS", OracleTypes.CURSOR, new RegistroConsultaPedidoCompraMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
        
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarConsultaPedidosCompra] Ocurrio un error en la operacion del USP_SEL_PEDIDO_COMPRA_NACIONAL : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<ConsultaPedidoCompraBean>) out.get("PO_CURSOR_PEDIDOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarConsultaPedidosCompra] Fin ");
		return lista;
	}
	
	@Override
	public List<ConsultaPedidoCompraBean> listarConsultaPedidosCompra2(ConsultaPedidoCompraBean consultaBean) throws Exception {
		LOGGER.info("[listarConsultaPedidosCompra2] Inicio ");
		List<ConsultaPedidoCompraBean> lista = new ArrayList<ConsultaPedidoCompraBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			
       
        	input_objParametros.addValue("PI_COD_ANIO", consultaBean.getCodigoAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_ID_ESTADO", Utils.getParam(consultaBean.getIdEstado()), Types.INTEGER);
			input_objParametros.addValue("PI_TIPO_PEDIDO", Utils.getParam(consultaBean.getMotivo()), Types.INTEGER);
			input_objParametros.addValue("PI_ID_DDI", Utils.getParam(consultaBean.getIdDdi()), Types.INTEGER);
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
  			objJdbcCall.withoutProcedureColumnMetaDataAccess();
  			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
  			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
  			objJdbcCall.withProcedureName("USP_SEL_PEDIDO_COMPRA_DDI");
  			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_ID_ESTADO", new SqlParameter("PI_ID_ESTADO", Types.INTEGER));
			output_objParametros.put("PI_TIPO_PEDIDO", new SqlParameter("PI_TIPO_PEDIDO", Types.INTEGER));
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.INTEGER));
			output_objParametros.put("PO_CURSOR_PEDIDOS", new SqlOutParameter("PO_CURSOR_PEDIDOS", OracleTypes.CURSOR, new RegistroConsultaPedidoCompra2Mapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
          
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarConsultaPedidosCompra] Ocurrio un error en la operacion del USP_SEL_PEDIDO_COMPRA_DDI : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<ConsultaPedidoCompraBean>) out.get("PO_CURSOR_PEDIDOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarConsultaPedidosCompra] Fin ");
		return lista;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#obtenerConsultaPedido(java.lang.Integer)
	 */
	@Override
	public ListaRespuestaConsultaPedidoBean obtenerConsultaPedido(ProductoConsultaPedidoBean consulta) throws Exception {
		LOGGER.info("[obtenerConsultaPedido] Inicio ");
		ListaRespuestaConsultaPedidoBean listaRetorno = new ListaRespuestaConsultaPedidoBean();
		
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();	
			input_objParametros.addValue("PI_COD_ANIO", consulta.getCodAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_IDE_DDI", consulta.getIdDdi(), Types.NUMERIC);
			input_objParametros.addValue("PI_ESTADO", consulta.getIdEstado(), Types.NUMERIC);
			input_objParametros.addValue("PI_TIPO_PEDIDO", consulta.getIdMotivo(), Types.NUMERIC);
		
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_PRODUCTO_PEDIDO_X_DDI");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PI_ESTADO", new SqlParameter("PI_ESTADO", Types.NUMERIC));
			output_objParametros.put("PI_TIPO_PEDIDO", new SqlParameter("PI_TIPO_PEDIDO", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_CABECERA", new SqlOutParameter("PO_CURSOR_CABECERA", OracleTypes.CURSOR, new RegistroConsultaPedidoModalCabMapper()));
			output_objParametros.put("PO_CURSOR_ALIMENTOS", new SqlOutParameter("PO_CURSOR_ALIMENTOS", OracleTypes.CURSOR, new RegistroConsultaPedidoModalProdMapper()));
			output_objParametros.put("PO_CURSOR_BNA", new SqlOutParameter("PO_CURSOR_BNA", OracleTypes.CURSOR, new RegistroConsultaPedidoModalNoProdMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerConsultaPedido] Ocurrio un error en la operacion del USP_SEL_MOSTRAR_PRODUCTOS : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			List<ProductoConsultaPedidoBean> listaCabecera = (List<ProductoConsultaPedidoBean>) out.get("PO_CURSOR_CABECERA");
			List<ProductoConsultaPedidoBean> listaAlimentaria = (List<ProductoConsultaPedidoBean>) out.get("PO_CURSOR_ALIMENTOS");
			List<ProductoConsultaPedidoBean> listaNoAlimentaria = (List<ProductoConsultaPedidoBean>) out.get("PO_CURSOR_BNA");
			
			listaRetorno.setLstCabecera(listaCabecera);
			listaRetorno.setLstAlimentaria(listaAlimentaria);
			listaRetorno.setLstNoAlimentaria(listaNoAlimentaria);
			
			listaRetorno.setCodigoRespuesta(codigoRespuesta);
			listaRetorno.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerConsultaPedido] Fin ");
		return listaRetorno;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#mostrarProductosxPedido(pe.com.sigbah.common.bean.ProductoConsultaPedidoBean)
	 */
	@Override
	public ListaRespuestaConsultaPedidoBean mostrarProductosxPedido(ProductoConsultaPedidoBean pedido) throws Exception {
		LOGGER.info("[mostrarProductosxPedido] Inicio ");
		ListaRespuestaConsultaPedidoBean listaRetorno = new ListaRespuestaConsultaPedidoBean();
		
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();	
			input_objParametros.addValue("PI_ID_PEDIDO_COMPRA", pedido.getIdPedido(), Types.NUMERIC);
		
                   
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_PRODUCTO_X_NRO_PEDIDO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_PEDIDO_COMPRA", new SqlParameter("PI_ID_PEDIDO_COMPRA", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_CABECERA", new SqlOutParameter("PO_CURSOR_CABECERA", OracleTypes.CURSOR, new RegistroConsultaPedidoModalCab2Mapper()));
			output_objParametros.put("PO_CURSOR_ALIMENTOS", new SqlOutParameter("PO_CURSOR_ALIMENTOS", OracleTypes.CURSOR, new RegistroConsultaPedidoModalProdMapper()));
			output_objParametros.put("PO_CURSOR_BNA", new SqlOutParameter("PO_CURSOR_BNA", OracleTypes.CURSOR, new RegistroConsultaPedidoModalNoProdMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerConsultaPedido] Ocurrio un error en la operacion del USP_SEL_PRODUCTO_X_NRO_PEDIDO : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			List<ProductoConsultaPedidoBean> listaCabecera = (List<ProductoConsultaPedidoBean>) out.get("PO_CURSOR_CABECERA");
			List<ProductoConsultaPedidoBean> listaAlimentaria = (List<ProductoConsultaPedidoBean>) out.get("PO_CURSOR_ALIMENTOS");
			List<ProductoConsultaPedidoBean> listaNoAlimentaria = (List<ProductoConsultaPedidoBean>) out.get("PO_CURSOR_BNA");
			
			listaRetorno.setLstCabecera(listaCabecera);
			listaRetorno.setLstAlimentaria(listaAlimentaria);
			listaRetorno.setLstNoAlimentaria(listaNoAlimentaria);
			
			listaRetorno.setCodigoRespuesta(codigoRespuesta);
			listaRetorno.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[mostrarProductosxPedido] Fin ");
		return listaRetorno;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#obtenerReporteEmergencia(java.lang.Integer, java.lang.String)
	 */
	@Override
	public ListaRespuestaEmergenciaBean obtenerReporteEmergencia(Integer idPedidoCompra, String codAnio)throws Exception {
		LOGGER.info("[obtenerReporteEmergencia] Inicio ");
		ListaRespuestaEmergenciaBean listaRetorno = new ListaRespuestaEmergenciaBean();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_COD_ANIO",idPedidoCompra, Types.VARCHAR);
			input_objParametros.addValue("PI_IDE_EMERGENCIA",idPedidoCompra, Types.NUMERIC);
                    
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_REPORT_SINPAD");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_IDE_EMERGENCIA", new SqlParameter("PI_IDE_EMERGENCIA", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_DATOS_GENERALES", new SqlOutParameter("PO_CURSOR_DATOS_GENERALES", OracleTypes.CURSOR, new RegistroCabeceraEmergenciaMapper()));
			output_objParametros.put("PO_CURSOR_LODALIDADES", new SqlOutParameter("PO_CURSOR_LODALIDADES", OracleTypes.CURSOR, new RegistroLocalidadReportEmergenciaMapper()));
			output_objParametros.put("PO_CURSOR_ALIMENTOS", new SqlOutParameter("PO_CURSOR_ALIMENTOS", OracleTypes.CURSOR, new RegistroAlimentariaEmergenciaReportMapper()));
			output_objParametros.put("PO_CURSOR_BIENES_NO_ALIM", new SqlOutParameter("PO_CURSOR_BIENES_NO_ALIM", OracleTypes.CURSOR, new RegistroNoAlimentariaEmergenciaReportMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerReporteEmergencia] Ocurrio un error en la operacion del USP_REPORT_SINPAD : "+mensajeRespuesta);
    			throw new Exception();
    		}
		
				List<CabeceraEmergenciaBean> listaCabecera = (List<CabeceraEmergenciaBean>) out.get("PO_CURSOR_DATOS_GENERALES");
				List<LocalidadEmergenciaBean> listaLocalidad = (List<LocalidadEmergenciaBean>) out.get("PO_CURSOR_LODALIDADES");
				List<AlimentariaEmergenciaBean> listaAlimentaria = (List<AlimentariaEmergenciaBean>) out.get("PO_CURSOR_ALIMENTOS");
				List<NoAlimentariaEmergenciaBean> listaNoAlimentaria = (List<NoAlimentariaEmergenciaBean>) out.get("PO_CURSOR_BIENES_NO_ALIM");
				
				listaRetorno.setLstCabecera(listaCabecera);
				listaRetorno.setLstLocalidad(listaLocalidad);
				listaRetorno.setLstAlimentaria(listaAlimentaria);
				listaRetorno.setLstNoAlimentaria(listaNoAlimentaria);
				
			listaRetorno.setCodigoRespuesta(codigoRespuesta);
			listaRetorno.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerReporteEmergencia] Fin ");
		return listaRetorno;
	}
	
	@Override
	public ReporteConsultaProgramacionBean obtenerReporteConsultaProg(ConsultaProgramacionBean consultaBean)throws Exception {
		LOGGER.info("[obtenerReporteConsultaProg] Inicio ");
		ReporteConsultaProgramacionBean listaRetorno = new ReporteConsultaProgramacionBean();
		try {
			String cadena=consultaBean.getNroDee();
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_COD_ANIO",consultaBean.getCodigoAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_DEE1",null, Types.NUMERIC);
			input_objParametros.addValue("PI_DEE2",null, Types.NUMERIC);
			input_objParametros.addValue("PI_DEE3",null, Types.NUMERIC);
			input_objParametros.addValue("PI_DEE4",null, Types.NUMERIC);
			input_objParametros.addValue("PI_DEE5",null, Types.NUMERIC);
			List<DeeConsultaBean> lstcondee=new ArrayList<DeeConsultaBean>();
     		int num=1;
			for (String retval: cadena.split(",")) {
		         System.out.println(retval);
		         int numDee =Integer.parseInt(retval);
		         DeeConsultaBean condee=new DeeConsultaBean();
		         condee.setNum(num);
		         condee.setIdDee(numDee);
		         lstcondee.add(condee);
		         num++;
			 } 
			for (DeeConsultaBean deeConsultaBean : lstcondee) {
				 switch (deeConsultaBean.getNum()) {
	         	 case 1:// case "01":
	         		input_objParametros.addValue("PI_DEE1", deeConsultaBean.getIdDee(), Types.NUMERIC);
	         		break;
	         	case 2://case "02":
	         		input_objParametros.addValue("PI_DEE2", deeConsultaBean.getIdDee(), Types.NUMERIC);
	         		break;
	         	case 3://case "03":
	         		input_objParametros.addValue("PI_DEE3", deeConsultaBean.getIdDee(), Types.NUMERIC);
	         		break;
	         	case 4://case "04":
	         		input_objParametros.addValue("PI_DEE4", deeConsultaBean.getIdDee(), Types.NUMERIC);
	         		break;
	         	case 5://case "05":
	         		input_objParametros.addValue("PI_DEE5", deeConsultaBean.getIdDee(), Types.NUMERIC);
	         		break;
	         }
			}
			input_objParametros.addValue("PI_IDE_DDI",consultaBean.getIdDdi(), Types.NUMERIC);
			input_objParametros.addValue("PI_ESTADO",consultaBean.getIdEstado(), Types.NUMERIC);
                    
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_REPORT_PROG_X_DEE");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_DEE1", new SqlParameter("PI_DEE1", Types.NUMERIC));
			output_objParametros.put("PI_DEE2", new SqlParameter("PI_DEE2", Types.NUMERIC));
			output_objParametros.put("PI_DEE3", new SqlParameter("PI_DEE3", Types.NUMERIC));
			output_objParametros.put("PI_DEE4", new SqlParameter("PI_DEE4", Types.NUMERIC));
			output_objParametros.put("PI_DEE5", new SqlParameter("PI_DEE5", Types.NUMERIC));
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PI_ESTADO", new SqlParameter("PI_ESTADO", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_CABECERA", new SqlOutParameter("PO_CURSOR_CABECERA", OracleTypes.CURSOR, new CabeceraConsultaProgramacionMapper()));
			output_objParametros.put("PO_CURSOR_ALIMENTOS", new SqlOutParameter("PO_CURSOR_ALIMENTOS", OracleTypes.CURSOR, new AlimentosConsultaProgramacionMapper()));
			output_objParametros.put("PO_CURSOR_BNA", new SqlOutParameter("PO_CURSOR_BNA", OracleTypes.CURSOR, new AlimentosConsultaProgramacionMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerReporteConsultaProg] Ocurrio un error en la operacion del USP_SEL_REPORT_PROG_X_DEE : "+mensajeRespuesta);
    			throw new Exception();
    		}
		
				List<ProgramacionBean> listaCabecera = (List<ProgramacionBean>) out.get("PO_CURSOR_CABECERA");
				List<ProductoConsultaProductoBean> listaAlimentaria = (List<ProductoConsultaProductoBean>) out.get("PO_CURSOR_ALIMENTOS");
				List<ProductoConsultaProductoBean> listaNoAlimentaria = (List<ProductoConsultaProductoBean>) out.get("PO_CURSOR_BNA");
				
				listaRetorno.setLstCabecera(listaCabecera);
				listaRetorno.setLstAlimentaria(listaAlimentaria);
				listaRetorno.setLstNoAlimentaria(listaNoAlimentaria);
				
			listaRetorno.setCodigoRespuesta(codigoRespuesta);
			listaRetorno.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerReporteConsultaProg] Fin ");
		return listaRetorno;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.ProgramacionDao#listarProg_x_dee(pe.com.sigbah.common.bean.ConsultaProgramacionBean)
	 */
	@Override 
	public ConsultaProgramacionBean grabarCorreosEnviados(ConsultaProgramacionBean consultaBean) throws Exception {
		LOGGER.info("[grabarCorreosEnviados] Inicio ");
		ConsultaProgramacionBean lista = new ConsultaProgramacionBean();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			
			input_objParametros.addValue("PI_FECHA_CORREO", consultaBean.getFechaProgramacion(), Types.VARCHAR);
			input_objParametros.addValue("PI_ID_PROGRAMACION", (consultaBean.getIdProgramacion()), Types.INTEGER);
			input_objParametros.addValue("PI_COD_PROGRAMACION", consultaBean.getNroProgramacion(), Types.VARCHAR);
			input_objParametros.addValue("PI_NOM_PROGRAMACION", consultaBean.getNombreProgramacion(), Types.VARCHAR);
			input_objParametros.addValue("PI_IDE_ESTADO_PROGRAMACION", consultaBean.getIdEstado(), Types.INTEGER);
			input_objParametros.addValue("PI_NOM_ESTADO_PROGRAMACION", consultaBean.getNombreEstado(), Types.VARCHAR);
			input_objParametros.addValue("PI_ID_USER", consultaBean.getIdUsuarioUsu(), Types.INTEGER);
			input_objParametros.addValue("PI_USERNAME", consultaBean.getUsuarioUsu(), Types.VARCHAR);
			input_objParametros.addValue("PI_NOMBRE_USUARIO", consultaBean.getNombreUsu(), Types.VARCHAR);
			input_objParametros.addValue("PI_EMAIL", consultaBean.getEmailUsu(), Types.VARCHAR);
			input_objParametros.addValue("PI_USU_REGISTRO", consultaBean.getUsuarioRegistro(), Types.VARCHAR);
		
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_PROGRAMACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_GRABAR_CORREOS_PROG");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FECHA_CORREO", new SqlParameter("PI_FECHA_CORREO", Types.VARCHAR));
			output_objParametros.put("PI_ID_PROGRAMACION", new SqlParameter("PI_ID_PROGRAMACION", Types.INTEGER));
			output_objParametros.put("PI_COD_PROGRAMACION", new SqlParameter("PI_COD_PROGRAMACION", Types.VARCHAR));
			output_objParametros.put("PI_NOM_PROGRAMACION", new SqlParameter("PI_NOM_PROGRAMACION", Types.VARCHAR));
			output_objParametros.put("PI_IDE_ESTADO_PROGRAMACION", new SqlParameter("PI_IDE_ESTADO_PROGRAMACION", Types.INTEGER));
			output_objParametros.put("PI_NOM_ESTADO_PROGRAMACION", new SqlParameter("PI_NOM_ESTADO_PROGRAMACION", Types.VARCHAR));
			output_objParametros.put("PI_ID_USER", new SqlParameter("PI_ID_USER", Types.INTEGER));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PI_NOMBRE_USUARIO", new SqlParameter("PI_NOMBRE_USUARIO", Types.VARCHAR));
			output_objParametros.put("PI_EMAIL", new SqlParameter("PI_EMAIL", Types.VARCHAR));
			output_objParametros.put("PI_USU_REGISTRO", new SqlParameter("PI_USU_REGISTRO", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			lista.setCodigoRespuesta((String) out.get("PO_CODIGO_RESPUESTA"));
			if (lista.getCodigoRespuesta().equals(Constantes.COD_ERROR_GENERAL)) {
				lista.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
				LOGGER.info("[grabarCorreosEnviados] Ocurrio un error en la operacion del USP_SEL_GRABAR_CORREOS_PROG : "+lista.getMensajeRespuesta());
				throw new Exception();
			} 
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[grabarCorreosEnviados] Fin ");
		return lista;
	}
	
}
