package pe.com.sigbah.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;
import pe.com.sigbah.common.bean.AlmacenBean;
import pe.com.sigbah.common.bean.CatalogoProductoBean;
import pe.com.sigbah.common.bean.CierreStockBean;
import pe.com.sigbah.common.bean.CorreoBean;
import pe.com.sigbah.common.bean.DetalleUsuarioBean;
import pe.com.sigbah.common.bean.DonacionesBean;
import pe.com.sigbah.common.bean.EstadoUsuarioBean;
import pe.com.sigbah.common.bean.EstadosXUsuarioBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.MaeAlmacenBean;
import pe.com.sigbah.common.bean.MaeAlmacenExternoBean;
import pe.com.sigbah.common.bean.MaeAniosBean;
import pe.com.sigbah.common.bean.MaeCategoriaBean;
import pe.com.sigbah.common.bean.MaeChoferBean;
import pe.com.sigbah.common.bean.MaeCorrelativoBean;
import pe.com.sigbah.common.bean.MaeDdiBean;
import pe.com.sigbah.common.bean.MaeDistritoIneiBean;
import pe.com.sigbah.common.bean.MaeDonanteBean;
import pe.com.sigbah.common.bean.MaeEmpresaTransporteBean;
import pe.com.sigbah.common.bean.MaeEnvaseBean;
import pe.com.sigbah.common.bean.MaeEstadoBean;
import pe.com.sigbah.common.bean.MaeEstadoDonacionBean;
import pe.com.sigbah.common.bean.MaeEstadoProgramacionBean;
import pe.com.sigbah.common.bean.MaeMarcaBean;
import pe.com.sigbah.common.bean.MaeMedioTransporteBean;
import pe.com.sigbah.common.bean.MaeModAlmacenBean;
import pe.com.sigbah.common.bean.MaeMonedaBean;
import pe.com.sigbah.common.bean.MaeMotivoTrasladoBean;
import pe.com.sigbah.common.bean.MaeOficinaBean;
import pe.com.sigbah.common.bean.MaePaisBean;
import pe.com.sigbah.common.bean.MaeParametroBean;
import pe.com.sigbah.common.bean.MaePersonalBean;
import pe.com.sigbah.common.bean.MaePersonalExternoBean;
import pe.com.sigbah.common.bean.MaeRegionBean;
import pe.com.sigbah.common.bean.MaeTipCamionBean;
import pe.com.sigbah.common.bean.MaeTipControlCalidadBean;
import pe.com.sigbah.common.bean.MaeTipDocumentoBean;
import pe.com.sigbah.common.bean.MaeTipMovimientoBean;
import pe.com.sigbah.common.bean.MaeUnidadMedidaBean;
import pe.com.sigbah.common.bean.ModuloBean;
import pe.com.sigbah.common.bean.ProyectoManifiestoBean;
import pe.com.sigbah.common.bean.RolBean;
import pe.com.sigbah.common.bean.RolMenuBean;
import pe.com.sigbah.common.bean.UsuarioBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.DateUtil;
import pe.com.sigbah.common.util.SpringUtil;
import pe.com.sigbah.common.util.Utils;
import pe.com.sigbah.dao.AdministracionDao;
import pe.com.sigbah.mapper.AlmacenUsuarioDdiMapper;
import pe.com.sigbah.mapper.AlmacenUsuarioMapper;
import pe.com.sigbah.mapper.CatalogoProductoEditar1Mapper;
import pe.com.sigbah.mapper.CatalogoProductoEditarMapper;
import pe.com.sigbah.mapper.CatalogoProductoMapper;
import pe.com.sigbah.mapper.ClaseProductoMapper;
import pe.com.sigbah.mapper.CorreosDestinatariosMapper;
import pe.com.sigbah.mapper.EstadoUsuarioMapper;
import pe.com.sigbah.mapper.EstadoXUsuarioMapper;
import pe.com.sigbah.mapper.EstadosModuloMapper;
import pe.com.sigbah.mapper.FamiliaProductoMapper;
import pe.com.sigbah.mapper.GrupoProductoMapper;
import pe.com.sigbah.mapper.HijosXMenuMapper;
import pe.com.sigbah.mapper.MaeAlmacen1Mapper;
import pe.com.sigbah.mapper.MaeAlmacenExternoMapper;
import pe.com.sigbah.mapper.MaeAlmacenMapper;
import pe.com.sigbah.mapper.MaeAnioMapper;
import pe.com.sigbah.mapper.MaeCategoriaMapper;
import pe.com.sigbah.mapper.MaeChofer1Mapper;
import pe.com.sigbah.mapper.MaeChoferMapper;
import pe.com.sigbah.mapper.MaeCorrelativoMapper;
import pe.com.sigbah.mapper.MaeDdiMapper;
import pe.com.sigbah.mapper.MaeDistritoInei1Mapper;
import pe.com.sigbah.mapper.MaeDistritoIneiMapper;
import pe.com.sigbah.mapper.MaeDonanteMapper;
import pe.com.sigbah.mapper.MaeEmpresaTransporteMapper;
import pe.com.sigbah.mapper.MaeEnvaseMapper;
import pe.com.sigbah.mapper.MaeEstadoDonacionMapper;
import pe.com.sigbah.mapper.MaeEstadoMapper;
import pe.com.sigbah.mapper.MaeEstadoProgramacionMapper;
import pe.com.sigbah.mapper.MaeMarcaMapper;
import pe.com.sigbah.mapper.MaeMedioTransporteMapper;
import pe.com.sigbah.mapper.MaeModAlmacenMapper;
import pe.com.sigbah.mapper.MaeMonedaMapper;
import pe.com.sigbah.mapper.MaeMotivoTrasladoMapper;
import pe.com.sigbah.mapper.MaeOficinaMapper;
import pe.com.sigbah.mapper.MaePaisMapper;
import pe.com.sigbah.mapper.MaeParametroMapper;
import pe.com.sigbah.mapper.MaePersonalExternoMapper;
import pe.com.sigbah.mapper.MaePersonalMapper;
import pe.com.sigbah.mapper.MaeRegionMapper;
import pe.com.sigbah.mapper.MaeTipCamionMapper;
import pe.com.sigbah.mapper.MaeTipControlCalidadMapper;
import pe.com.sigbah.mapper.MaeTipDocumentoMapper;
import pe.com.sigbah.mapper.MaeTipMovimientoMapper;
import pe.com.sigbah.mapper.MaeUnidadMedida1Mapper;
import pe.com.sigbah.mapper.MaeUnidadMedidaMapper;
import pe.com.sigbah.mapper.MenuUsuario1Mapper;
import pe.com.sigbah.mapper.MenuUsuario2Mapper;
import pe.com.sigbah.mapper.MenuUsuarioMapper;
import pe.com.sigbah.mapper.MesTrabajoMapper;
import pe.com.sigbah.mapper.ProductoSigaMapper;
import pe.com.sigbah.mapper.RegionXDdiMapper;
import pe.com.sigbah.mapper.RegistroProyectoManifiestoMapper;
import pe.com.sigbah.mapper.RolMapper;
import pe.com.sigbah.mapper.RolMenuMapper;
import pe.com.sigbah.mapper.RolMostrarMapper;
import pe.com.sigbah.mapper.TablasGeneralesMapper;
import pe.com.sigbah.mapper.UsuarioEditar1Mapper;
import pe.com.sigbah.mapper.UsuarioEditarMapper;
import pe.com.sigbah.mapper.UsuarioMapper;
import pe.com.sigbah.mapper.Usuarios1Mapper;
import pe.com.sigbah.mapper.UsuariosCorreoMapper;
import pe.com.sigbah.mapper.UsuariosMapper;

/**
 * @className: AdministracionDaoImpl.java
 * @description: Clase que contiene el consumo de los procedimientos del package BAH_PKG_ADMINISTRACION.
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
@SuppressWarnings("unchecked")
@Repository
public class AdministracionDaoImpl extends JdbcDaoSupport implements AdministracionDao, Serializable {
	
	private static final long serialVersionUID = 1L;
	private transient final Log LOGGER = LogFactory.getLog(getClass());
	
	private SimpleJdbcCall objJdbcCall;
	
	/**
	 * @param dataSource
	 */
	@Autowired
	public AdministracionDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.AdministracionDao#obtenerDatosUsuario(pe.com.sigbah.common.bean.UsuarioBean)
	 */
	@Override
	public DetalleUsuarioBean obtenerDatosUsuario(UsuarioBean usuarioBean) throws Exception {
		LOGGER.info("[obtenerDatosUsuario] Inicio ");
		DetalleUsuarioBean detalleUsuarioBean = new DetalleUsuarioBean();
		try {			
			Collection<UsuarioBean> colDetalleUsuarioBean = null;
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_USERNAME", usuarioBean.getUsuario(), Types.VARCHAR);
			input_objParametros.addValue("PI_PASSWORD", usuarioBean.getPassword(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_USUARIO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PI_PASSWORD", new SqlParameter("PI_PASSWORD", Types.VARCHAR));
			output_objParametros.put("PO_USER_EXISTE", new SqlOutParameter("PO_USER_EXISTE", Types.VARCHAR));
			output_objParametros.put("PO_CURSOR", new SqlOutParameter("PO_CURSOR", OracleTypes.CURSOR, new UsuarioMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			colDetalleUsuarioBean = (List<UsuarioBean>) out.get("PO_CURSOR");			
			detalleUsuarioBean.setIndicadorUsuario((String) out.get("PO_USER_EXISTE"));
			detalleUsuarioBean.setCodigoRespuesta((String) out.get("PO_CODIGO_RESPUESTA"));
			detalleUsuarioBean.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));	
			
			if (!Utils.isEmpty(colDetalleUsuarioBean)) {
				detalleUsuarioBean.setDatosUsuario(new ArrayList<UsuarioBean>(colDetalleUsuarioBean));
			}
			
			if (detalleUsuarioBean.getCodigoRespuesta().equals(Constantes.COD_ERROR_GENERAL)) {
				LOGGER.info("[obtenerDatosUsuario] Ocurrio un error en la operacion del USP_SEL_USUARIO");
    			throw new Exception();
    		}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerDatosUsuario] Fin ");
		return detalleUsuarioBean;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.AdministracionDao#listarModuloUsuario(java.lang.Integer)
	 */
	@Override
	public List<ModuloBean> listarModuloUsuario(Integer idUsuario) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.AdministracionDao#listarEstadoUsuario(pe.com.sigbah.common.bean.EstadoUsuarioBean)
	 */
	@Override
	public List<EstadoUsuarioBean> listarEstadoUsuario(EstadoUsuarioBean estadoUsuarioBean) throws Exception {
		LOGGER.info("[listarEstadoUsuario] Inicio ");
		List<EstadoUsuarioBean> lista = new ArrayList<EstadoUsuarioBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_USER", estadoUsuarioBean.getIdUsuario(), Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_MODULO", estadoUsuarioBean.getNombreModulo(), Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_ESTADOS_POR_USUARIO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_USER", new SqlParameter("PI_ID_USER", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_MODULO", new SqlParameter("PI_NOMBRE_MODULO", Types.VARCHAR));
			output_objParametros.put("PO_CURSOR_ESTADOS", new SqlOutParameter("PO_CURSOR_ESTADOS", OracleTypes.CURSOR, new EstadoUsuarioMapper()));		
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			lista = (List<EstadoUsuarioBean>) out.get("PO_CURSOR_ESTADOS");
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarEstadoUsuario] Fin ");
		return lista;
	}
	
	@Override
	public CierreStockBean obtenerMesTrabajo(Integer idAlmacen) throws Exception {
		LOGGER.info("[obtenerMesTrabajo] Inicio ");
		CierreStockBean lista = new CierreStockBean();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("pi_IDE_ALMACEN", idAlmacen, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_MES_TRABAJO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("pi_IDE_ALMACEN", new SqlParameter("pi_IDE_ALMACEN", Types.NUMERIC));
			output_objParametros.put("po_Lr_Recordset", new SqlOutParameter("po_Lr_Recordset", OracleTypes.CURSOR, new MesTrabajoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				LOGGER.info("[obtenerMesTrabajo] Ocurrio un error en la operacion del USP_SEL_MES_TRABAJO");
				throw new Exception();
			} else {
				List<CierreStockBean> listaDatos = (List<CierreStockBean>) out.get("po_Lr_Recordset");
				if (!Utils.isEmpty(listaDatos)) {
					lista = listaDatos.get(0);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerMesTrabajo] Fin ");
		return lista;
	}
	
	/////////////////////////////TABLAS GENERALES///////////////////////
	
	@Override
	public List<ItemBean> listarTablas() throws Exception  {
		LOGGER.info("[listarTablas] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS");

			Map<String, Object> out = objJdbcCall.withoutProcedureColumnMetaDataAccess()
					.returningResultSet("V_CURSOR_TABLAS", new RowMapper<ItemBean>() {
						public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							ItemBean item = new ItemBean();
							item.setIcodigo(rs.getInt("IDE_TABLA"));
							item.setDescripcion(rs.getString("NOMBRE_PROPIO"));
							item.setDescripcionCorta(rs.getString("NOMBRE_TABLA"));
							item.setVcodigo(rs.getString("FLG_EDICION"));
							return item;
						}
					}).execute(objJdbcCall);

			lista = new ArrayList((Collection<ItemBean>) out.get("V_CURSOR_TABLAS"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablas] Fin ");
		return lista;
	}
	
	@Override
	public List<ItemBean> listarTablasGenerales() throws Exception  {
		LOGGER.info("[listarTablasGenerales] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTA_TABLAS_GENERALES");

			Map<String, Object> out = objJdbcCall.withoutProcedureColumnMetaDataAccess()
					.returningResultSet("V_CURSOR_TABLAS", new RowMapper<ItemBean>() {
						public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							ItemBean item = new ItemBean();
							item.setIcodigo(rs.getInt("IDE_TABLA"));
							item.setDescripcion(rs.getString("NOMBRE_PROPIO"));
							item.setDescripcionCorta(rs.getString("NOMBRE_TABLA"));
							item.setVcodigo(rs.getString("FLG_EDICION"));
							return item;
						}
					}).execute(objJdbcCall);

			lista = new ArrayList((Collection<ItemBean>) out.get("V_CURSOR_TABLAS"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablasGenerales] Fin ");
		return lista;
	}
	
	@Override
	public List<ItemBean> listarTablasDdi() throws Exception  {
		LOGGER.info("[listarTablasDdi] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DDI");

			Map<String, Object> out = objJdbcCall.withoutProcedureColumnMetaDataAccess()
					.returningResultSet("V_CURSOR_TABLAS", new RowMapper<ItemBean>() {
						public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							ItemBean item = new ItemBean();
							item.setIcodigo(rs.getInt("IDE_TABLA"));
							item.setDescripcion(rs.getString("NOMBRE_PROPIO"));
							item.setDescripcionCorta(rs.getString("NOMBRE_TABLA"));
							item.setVcodigo(rs.getString("FLG_EDICION"));
							return item;
						}
					}).execute(objJdbcCall);

			lista = new ArrayList((Collection<ItemBean>) out.get("V_CURSOR_TABLAS"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablasDdi] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeAlmacenBean> listarTablaAlmacen(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaAlmacen] Inicio ");
		List<MaeAlmacenBean> lista = new ArrayList<MaeAlmacenBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeAlmacen1Mapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaAlmacen] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeAlmacenBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaAlmacen] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeAlmacenExternoBean> listarTablaAlmacenExterno(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaAlmacenExterno] Inicio ");
		List<MaeAlmacenExternoBean> lista = new ArrayList<MaeAlmacenExternoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeAlmacenExternoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaAlmacenExterno] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeAlmacenExternoBean>) out.get("V_CURSOR_DATOS");
				System.out.println("LISTA: "+lista.size());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaAlmacenExterno] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeAlmacenExternoBean> listarTablaAlmacenExterno1(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarTablaAlmacenExterno1] Inicio ");
		List<MaeAlmacenExternoBean> lista = new ArrayList<MaeAlmacenExternoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_TIPO_ALMACEN", itemBean.getVcodigoParam4(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_DPTO", itemBean.getVcodigoParam2(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_PROV", itemBean.getVcodigoParam3(), Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_ALMACEN_EXT");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_TIPO_ALMACEN", new SqlParameter("PI_TIPO_ALMACEN", Types.VARCHAR));
			output_objParametros.put("PI_COD_DPTO", new SqlParameter("PI_COD_DPTO", Types.VARCHAR));
			output_objParametros.put("PI_COD_PROV", new SqlParameter("PI_COD_PROV", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeAlmacenExternoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaAlmacenExterno1] Ocurrio un error en la operacion del USP_SEL_LISTAR_ALMACEN_EXT : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeAlmacenExternoBean>) out.get("V_CURSOR_DATOS");
				System.out.println("LISTA: "+lista.size());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaAlmacenExterno1] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeDistritoIneiBean> listarTablaPoblacionInei(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarTablaPoblacionInei] Inicio ");
		List<MaeDistritoIneiBean> lista = new ArrayList<MaeDistritoIneiBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_COD_ANIO", itemBean.getVcodigoParam2(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_DPTO", itemBean.getVcodigoParam3(), Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_MOSTRAR_POBLACION_INEI");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_COD_DPTO", new SqlParameter("PI_COD_DPTO", Types.VARCHAR));
			output_objParametros.put("PI_CURSOR_POBLACION", new SqlOutParameter("PI_CURSOR_POBLACION", OracleTypes.CURSOR, new MaeDistritoInei1Mapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaPoblacionInei] Ocurrio un error en la operacion del USP_SEL_MOSTRAR_POBLACION_INEI : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeDistritoIneiBean>) out.get("PI_CURSOR_POBLACION");
				System.out.println("LISTA: "+lista.size());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaPoblacionInei] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeAniosBean> listarTablaAnios(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaAnios] Inicio ");
		List<MaeAniosBean> lista = new ArrayList<MaeAniosBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeAnioMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaAnios] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeAniosBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaAnios] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeCategoriaBean> listarTablaCategoria(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaCategoria] Inicio ");
		List<MaeCategoriaBean> lista = new ArrayList<MaeCategoriaBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeCategoriaMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaCategoria] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeCategoriaBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaCategoria] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeChoferBean> listarTablaChofer(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaChofer] Inicio ");
		List<MaeChoferBean> lista = new ArrayList<MaeChoferBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeChofer1Mapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaChofer] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeChoferBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaChofer] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeDdiBean> listarTablaDdi(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaDdi] Inicio ");
		List<MaeDdiBean> lista = new ArrayList<MaeDdiBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeDdiMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaDdi] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeDdiBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaDdi] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeDistritoIneiBean> listarTablaDistritoInei(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaDistritoInei] Inicio ");
		List<MaeDistritoIneiBean> lista = new ArrayList<MaeDistritoIneiBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeDistritoIneiMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaDistritoInei] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeDistritoIneiBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaDistritoInei] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeDonanteBean> listarTablaDonante(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaDonante] Inicio ");
		List<MaeDonanteBean> lista = new ArrayList<MaeDonanteBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeDonanteMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaDonante] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeDonanteBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaDonante] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeEmpresaTransporteBean> listarTablaEmpresaTransporte(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaEmpresaTransporte] Inicio ");
		List<MaeEmpresaTransporteBean> lista = new ArrayList<MaeEmpresaTransporteBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeEmpresaTransporteMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaEmpresaTransporte] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeEmpresaTransporteBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaEmpresaTransporte] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeEnvaseBean> listarTablaEnvase(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaEnvase] Inicio ");
		List<MaeEnvaseBean> lista = new ArrayList<MaeEnvaseBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeEnvaseMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaEnvase] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeEnvaseBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaEnvase] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeEstadoBean> listarTablaEstado(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaEstado] Inicio ");
		List<MaeEstadoBean> lista = new ArrayList<MaeEstadoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeEstadoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaEstado] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeEstadoBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaEstado] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeEstadoDonacionBean> listarTablaEstadoDonacion(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaEstadoDonacion] Inicio ");
		List<MaeEstadoDonacionBean> lista = new ArrayList<MaeEstadoDonacionBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeEstadoDonacionMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaEstadoDonacion] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeEstadoDonacionBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaEstadoDonacion] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeEstadoProgramacionBean> listarTablaEstadoProgramacion(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaEstadoProgramacion] Inicio ");
		List<MaeEstadoProgramacionBean> lista = new ArrayList<MaeEstadoProgramacionBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeEstadoProgramacionMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaEstadoProgramacion] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeEstadoProgramacionBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaEstadoProgramacion] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeMedioTransporteBean> listarTablaMedioTransporte(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaMedioTransporte] Inicio ");
		List<MaeMedioTransporteBean> lista = new ArrayList<MaeMedioTransporteBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeMedioTransporteMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaMedioTransporte] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeMedioTransporteBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaMedioTransporte] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeModAlmacenBean> listarTablaModAlmacen(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaModAlmacen] Inicio ");
		List<MaeModAlmacenBean> lista = new ArrayList<MaeModAlmacenBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeModAlmacenMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaModAlmacen] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeModAlmacenBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaModAlmacen] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeMonedaBean> listarTablaMoneda(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaMoneda] Inicio ");
		List<MaeMonedaBean> lista = new ArrayList<MaeMonedaBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeMonedaMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaMoneda] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeMonedaBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaMoneda] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeMotivoTrasladoBean> listarTablaMotivoTraslado(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaMotivoTraslado] Inicio ");
		List<MaeMotivoTrasladoBean> lista = new ArrayList<MaeMotivoTrasladoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeMotivoTrasladoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaMotivoTraslado] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeMotivoTrasladoBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaMotivoTraslado] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeOficinaBean> listarTablaOficina(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaOficina] Inicio ");
		List<MaeOficinaBean> lista = new ArrayList<MaeOficinaBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeOficinaMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaOficina] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeOficinaBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaOficina] Fin ");
		return lista;
	}
	
	@Override
	public List<MaePaisBean> listarTablaPais(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaPais] Inicio ");
		List<MaePaisBean> lista = new ArrayList<MaePaisBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaePaisMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaPais] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaePaisBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaPais] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeParametroBean> listarTablaParametro(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaParametro] Inicio ");
		List<MaeParametroBean> lista = new ArrayList<MaeParametroBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeParametroMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaParametro] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeParametroBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaParametro] Fin ");
		return lista;
	}
	
	@Override
	public List<MaePersonalBean> listarTablaPersonal(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaPersonal] Inicio ");
		List<MaePersonalBean> lista = new ArrayList<MaePersonalBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaePersonalMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaPersonal] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaePersonalBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaPersonal] Fin ");
		return lista;
	}
	
	@Override
	public List<MaePersonalExternoBean> listarTablaPersonalExterno(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaPersonalExterno] Inicio ");
		List<MaePersonalExternoBean> lista = new ArrayList<MaePersonalExternoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaePersonalExternoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaPersonalExterno] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaePersonalExternoBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaPersonalExterno] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeRegionBean> listarTablaRegion(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaRegion] Inicio ");
		List<MaeRegionBean> lista = new ArrayList<MaeRegionBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeRegionMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaRegion] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeRegionBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaRegion] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeTipCamionBean> listarTablaTipCamion(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaTipCamion] Inicio ");
		List<MaeTipCamionBean> lista = new ArrayList<MaeTipCamionBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeTipCamionMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaTipCamion] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeTipCamionBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaTipCamion] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeTipControlCalidadBean> listarTablaTipControlCalidad(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaTipControlCalidad] Inicio ");
		List<MaeTipControlCalidadBean> lista = new ArrayList<MaeTipControlCalidadBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeTipControlCalidadMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaTipControlCalidad] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeTipControlCalidadBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaTipControlCalidad] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeTipDocumentoBean> listarTablaTipDocumento(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaTipDocumento] Inicio ");
		List<MaeTipDocumentoBean> lista = new ArrayList<MaeTipDocumentoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeTipDocumentoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaTipDocumento] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeTipDocumentoBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaTipDocumento] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeTipMovimientoBean> listarTablaTipMovimiento(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaTipMovimiento] Inicio ");
		List<MaeTipMovimientoBean> lista = new ArrayList<MaeTipMovimientoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeTipMovimientoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaTipMovimiento] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeTipMovimientoBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaTipMovimiento] Fin ");
		return lista;
	}
	
	@Override
	public List<MaeUnidadMedidaBean> listarTablaUnidadMedida(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaUnidadMedida] Inicio ");
		List<MaeUnidadMedidaBean> lista = new ArrayList<MaeUnidadMedidaBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeUnidadMedida1Mapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaUnidadMedida] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeUnidadMedidaBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaUnidadMedida] Fin ");
		System.out.println("FLAG: "+lista.get(0).getIdFlagActivo());
		return lista;
	}
	
	@Override
	public List<MaeMarcaBean> listarTablaMarca(Integer idDdi, String nomTabla) throws Exception {
		LOGGER.info("[listarTablaMarca] Inicio ");
		List<MaeMarcaBean> lista = new ArrayList<MaeMarcaBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeMarcaMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarTablaMarca] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeMarcaBean>) out.get("V_CURSOR_DATOS");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTablaMarca] Fin ");
//		System.out.println("FLAG: "+lista.get(0).getIdFlagActivo());
		return lista;
	}
	
//	@Override
//	public List<MaeCatalogoProductoBean> listarTablaCatalogoProducto(Integer idDdi, String nomTabla) throws Exception {
//		LOGGER.info("[listarTablaCatalogoProducto] Inicio ");
//		List<MaeCatalogoProductoBean> lista = new ArrayList<MaeCatalogoProductoBean>();
//		try {
//			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
//			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);
//			input_objParametros.addValue("PI_NOMBRE_TABLA", nomTabla, Types.VARCHAR);
//			
//			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
//			objJdbcCall.withoutProcedureColumnMetaDataAccess();
//			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
//			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
//			objJdbcCall.withProcedureName("USP_SEL_LISTAR_TABLAS_DATOS");
//
//			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
//			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
//			output_objParametros.put("PI_NOMBRE_TABLA", new SqlParameter("PI_NOMBRE_TABLA", Types.VARCHAR));
//			output_objParametros.put("V_CURSOR_DATOS", new SqlOutParameter("V_CURSOR_DATOS", OracleTypes.CURSOR, new MaeCatalogoProductoMapper()));
//			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
//			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
//			
//			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
//
//			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
//			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
//			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
//				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
//				LOGGER.info("[listarTablaCatalogoProducto] Ocurrio un error en la operacion del USP_SEL_LISTAR_TABLAS_DATOS : "+mensajeRespuesta);
//				throw new Exception();
//			} else {
//				lista = (List<MaeCatalogoProductoBean>) out.get("V_CURSOR_DATOS");
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage(), e);
//			throw new Exception();
//		}		
//		LOGGER.info("[listarTablaCatalogoProducto] Fin ");
//		return lista;
//	}
	
	@Override
	public MaeAlmacenBean obtenerCodigoAlmacen(MaeAlmacenBean maeAlmacenBean) throws Exception {
		LOGGER.info("[obtenerCodigoAlmacen] Inicio ");
		MaeAlmacenBean almacen = new MaeAlmacenBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_COD_DDI", maeAlmacenBean.getCodDdi(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_GENERA_COD_ALMACEN");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_DDI", new SqlParameter("PI_COD_DDI", Types.VARCHAR));
			output_objParametros.put("PO_COD_ALMACEN", new SqlOutParameter("PO_COD_ALMACEN", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerCodigoAlmacen] Ocurrio un error en la operacion del USP_SEL_GENERA_COD_ALMACEN : "+mensajeRespuesta);
    			throw new Exception();
    		}

			almacen.setCodAlmacen(((String) out.get("PO_COD_ALMACEN")).trim());
			almacen.setCodigoRespuesta(codigoRespuesta);
			almacen.setMensajeRespuesta((String) out.get("po_MENSAJE_RESPUESTA"));

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerCodigoAlmacen] Fin ");
		return almacen;
	}
	
	@Override
	public MaeAlmacenExternoBean obtenerCodigoAlmacenExterno(MaeAlmacenExternoBean maeAlmacenExternoBean) throws Exception {
		LOGGER.info("[obtenerCodigoAlmacenExterno] Inicio ");
		MaeAlmacenExternoBean almacenExterno = new MaeAlmacenExternoBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_COD_UBIGEO", maeAlmacenExternoBean.getCodUbigeo(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_GENERA_COD_ALMACEN_EXT");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_UBIGEO", new SqlParameter("PI_COD_UBIGEO", Types.VARCHAR));
			output_objParametros.put("PO_COD_ALMACEN", new SqlOutParameter("PO_COD_ALMACEN", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerCodigoAlmacenExterno] Ocurrio un error en la operacion del c : "+mensajeRespuesta);
    			throw new Exception();
    		}

			almacenExterno.setCodAlmacen(((String) out.get("PO_COD_ALMACEN")).trim());
			almacenExterno.setCodigoRespuesta(codigoRespuesta);
			almacenExterno.setMensajeRespuesta((String) out.get("po_MENSAJE_RESPUESTA"));

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerCodigoAlmacenExterno] Fin ");
		return almacenExterno;
	}
	
	@Override
	public MaeAlmacenBean insertarRegistroAlmacen(MaeAlmacenBean maeAlmacenBean) throws Exception {
		LOGGER.info("[insertarRegistroAlmacen] Inicio ");
		MaeAlmacenBean registroAlmacen= new MaeAlmacenBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_fk_ide_ddi", maeAlmacenBean.getIdDdi(), Types.NUMERIC);
			input_objParametros.addValue("PI_cod_ddi", maeAlmacenBean.getCodDdi(), Types.VARCHAR);
			input_objParametros.addValue("PI_ide_almacen", maeAlmacenBean.getIdAlmacen(), Types.NUMERIC);
			input_objParametros.addValue("PI_nombre_almacen", maeAlmacenBean.getNombreAlmacen(), Types.VARCHAR);
			input_objParametros.addValue("PI_cod_almacen", maeAlmacenBean.getCodAlmacen(), Types.VARCHAR);
			input_objParametros.addValue("PI_direccion", maeAlmacenBean.getDireccion(), Types.VARCHAR);
			input_objParametros.addValue("PI_fk_ide_mod_almacen", maeAlmacenBean.getIdModAlmacen(), Types.NUMERIC);
			input_objParametros.addValue("PI_caracteristicas", maeAlmacenBean.getCaracteristicas(), Types.VARCHAR);
			input_objParametros.addValue("PI_flg_activo", maeAlmacenBean.getFlgActivo(), Types.VARCHAR);
//			input_objParametros.addValue("PI_nombre_secundario", maeAlmacenBean.getNombreSecundario(), Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", maeAlmacenBean.getUsuario(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", "I", Types.VARCHAR);	

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_BAH_MAE_ALMACEN");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_fk_ide_ddi", new SqlParameter("PI_fk_ide_ddi", Types.NUMERIC));
			output_objParametros.put("PI_cod_ddi", new SqlParameter("PI_cod_ddi", Types.VARCHAR));
			output_objParametros.put("PI_ide_almacen", new SqlParameter("PI_ide_almacen", Types.NUMERIC));
			output_objParametros.put("PI_nombre_almacen", new SqlParameter("PI_nombre_almacen", Types.VARCHAR));
			output_objParametros.put("PI_cod_almacen", new SqlParameter("PI_cod_almacen", Types.VARCHAR));
			output_objParametros.put("PI_direccion", new SqlParameter("PI_direccion", Types.VARCHAR));
			output_objParametros.put("PI_fk_ide_mod_almacen", new SqlParameter("PI_fk_ide_mod_almacen", Types.NUMERIC));
			output_objParametros.put("PI_caracteristicas", new SqlParameter("PI_caracteristicas", Types.VARCHAR));
			output_objParametros.put("PI_flg_activo", new SqlParameter("PI_flg_activo", Types.VARCHAR));
//			output_objParametros.put("PI_nombre_secundario", new SqlParameter("PI_nombre_secundario", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_COD_ALMACEN", new SqlOutParameter("PO_COD_ALMACEN", Types.VARCHAR));
			output_objParametros.put("PO_IDE_ALMACEN", new SqlOutParameter("PO_IDE_ALMACEN", Types.NUMERIC));
			output_objParametros.put("PO_COD_ALMACEN_CONCATENADO", new SqlOutParameter("PO_COD_ALMACEN_CONCATENADO", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroAlmacen] Ocurrio un error en la operacion del USP_INS_UPD_BAH_MAE_ALMACEN : "+mensajeRespuesta);
    			throw new Exception();
    		}
		
			registroAlmacen.setCodAlmacen(((String) out.get("PO_COD_ALMACEN")));
			registroAlmacen.setIdAlmacen(((BigDecimal) out.get("PO_IDE_ALMACEN")).intValue());
			registroAlmacen.setCodAlmacenConcatenado((String) out.get("PO_COD_ALMACEN_CONCATENADO"));
			registroAlmacen.setCodigoRespuesta(codigoRespuesta);
			registroAlmacen.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroAlmacen] Fin ");
		return registroAlmacen;
	}
	
	@Override
	public MaeAlmacenBean actualizarRegistroAlmacen(MaeAlmacenBean maeAlmacenBean) throws Exception {
		LOGGER.info("[actualizarRegistroAlmacen] Inicio ");
		MaeAlmacenBean registroAlmacen= new MaeAlmacenBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_fk_ide_ddi", maeAlmacenBean.getIdDdi(), Types.NUMERIC);
			input_objParametros.addValue("PI_cod_ddi", maeAlmacenBean.getCodDdi(), Types.VARCHAR);
			input_objParametros.addValue("PI_ide_almacen", maeAlmacenBean.getIdAlmacen(), Types.NUMERIC);
			input_objParametros.addValue("PI_nombre_almacen", maeAlmacenBean.getNombreAlmacen(), Types.VARCHAR);
			input_objParametros.addValue("PI_cod_almacen", maeAlmacenBean.getCodAlmacen(), Types.VARCHAR);
			input_objParametros.addValue("PI_direccion", maeAlmacenBean.getDireccion(), Types.VARCHAR);
			input_objParametros.addValue("PI_fk_ide_mod_almacen", maeAlmacenBean.getIdModAlmacen(), Types.NUMERIC);
			input_objParametros.addValue("PI_caracteristicas", maeAlmacenBean.getCaracteristicas(), Types.VARCHAR);
			input_objParametros.addValue("PI_flg_activo", maeAlmacenBean.getFlgActivo(), Types.VARCHAR);
//			input_objParametros.addValue("PI_nombre_secundario", "", Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", maeAlmacenBean.getUsuario(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", "U", Types.VARCHAR);	

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_BAH_MAE_ALMACEN");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_fk_ide_ddi", new SqlParameter("PI_fk_ide_ddi", Types.NUMERIC));
			output_objParametros.put("PI_cod_ddi", new SqlParameter("PI_cod_ddi", Types.VARCHAR));
			output_objParametros.put("PI_ide_almacen", new SqlParameter("PI_ide_almacen", Types.NUMERIC));
			output_objParametros.put("PI_nombre_almacen", new SqlParameter("PI_nombre_almacen", Types.VARCHAR));
			output_objParametros.put("PI_cod_almacen", new SqlParameter("PI_cod_almacen", Types.VARCHAR));
			output_objParametros.put("PI_direccion", new SqlParameter("PI_direccion", Types.VARCHAR));
			output_objParametros.put("PI_fk_ide_mod_almacen", new SqlParameter("PI_fk_ide_mod_almacen", Types.NUMERIC));
			output_objParametros.put("PI_caracteristicas", new SqlParameter("PI_caracteristicas", Types.VARCHAR));
			output_objParametros.put("PI_flg_activo", new SqlParameter("PI_flg_activo", Types.VARCHAR));
//			output_objParametros.put("PI_nombre_secundario", new SqlParameter("PI_nombre_secundario", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_COD_ALMACEN", new SqlOutParameter("PO_COD_ALMACEN", Types.VARCHAR));
			output_objParametros.put("PO_IDE_ALMACEN", new SqlOutParameter("PO_IDE_ALMACEN", Types.NUMERIC));
			output_objParametros.put("PO_COD_ALMACEN_CONCATENADO", new SqlOutParameter("PO_COD_ALMACEN_CONCATENADO", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[actualizarRegistroAlmacen] Ocurrio un error en la operacion del USP_INS_UPD_BAH_MAE_ALMACEN : "+mensajeRespuesta);
    			throw new Exception();
    		}
		
			registroAlmacen.setCodAlmacen(maeAlmacenBean.getCodAlmacen());
			registroAlmacen.setIdAlmacen(maeAlmacenBean.getIdAlmacen());
			//registroAlmacen.setCodAlmacenConcatenado((String) out.get("PO_COD_ALMACEN_CONCATENADO"));
			registroAlmacen.setCodigoRespuesta(codigoRespuesta);
			registroAlmacen.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[actualizarRegistroAlmacen] Fin ");
		return registroAlmacen;
	}
	
	@Override
	public MaeAlmacenExternoBean insertarRegistroAlmacenExterno(MaeAlmacenExternoBean maeAlmacenExternoBean, String control) throws Exception {
		LOGGER.info("[insertarRegistroAlmacenExterno] Inicio ");
		MaeAlmacenExternoBean registroAlmacenExterno= new MaeAlmacenExternoBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_ALMACEN_EXTERNO", maeAlmacenExternoBean.getIdAlmacenExterno(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_REGION", maeAlmacenExternoBean.getIdRegion(), Types.NUMERIC);
			input_objParametros.addValue("PI_COD_UBIGEO", maeAlmacenExternoBean.getCodUbigeo(), Types.VARCHAR);
			input_objParametros.addValue("PI_NOMBRE_ALMACEN", maeAlmacenExternoBean.getNombreAlmacen(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_ALMACEN", maeAlmacenExternoBean.getCodAlmacen(), Types.VARCHAR);
			input_objParametros.addValue("PI_TIPO_ALMACEN", maeAlmacenExternoBean.getTipoAlmacen(), Types.VARCHAR);
			input_objParametros.addValue("PI_DIRECCION", maeAlmacenExternoBean.getDireccion(), Types.VARCHAR);
			input_objParametros.addValue("PI_RUC_UBIGEO", maeAlmacenExternoBean.getRucUbigeo(), Types.VARCHAR);
			input_objParametros.addValue("PI_NOMBRE_UBIGEO", maeAlmacenExternoBean.getNombreUbigeo(), Types.VARCHAR);
			input_objParametros.addValue("PI_FLG_ACTIVO", maeAlmacenExternoBean.getFlgActivo(), Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", maeAlmacenExternoBean.getUsuario(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", control, Types.VARCHAR);	

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_ALMACEN_EXTERNO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_ALMACEN_EXTERNO", new SqlParameter("PI_IDE_ALMACEN_EXTERNO", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_REGION", new SqlParameter("PI_FK_IDE_REGION", Types.NUMERIC));
			output_objParametros.put("PI_COD_UBIGEO", new SqlParameter("PI_COD_UBIGEO", Types.VARCHAR));
			output_objParametros.put("PI_NOMBRE_ALMACEN", new SqlParameter("PI_NOMBRE_ALMACEN", Types.VARCHAR));
			output_objParametros.put("PI_COD_ALMACEN", new SqlParameter("PI_COD_ALMACEN", Types.VARCHAR));
			output_objParametros.put("PI_TIPO_ALMACEN", new SqlParameter("PI_TIPO_ALMACEN", Types.VARCHAR));
			output_objParametros.put("PI_DIRECCION", new SqlParameter("PI_DIRECCION", Types.VARCHAR));
			output_objParametros.put("PI_RUC_UBIGEO", new SqlParameter("PI_RUC_UBIGEO", Types.VARCHAR));
			output_objParametros.put("PI_NOMBRE_UBIGEO", new SqlParameter("PI_NOMBRE_UBIGEO", Types.VARCHAR));
			output_objParametros.put("PI_FLG_ACTIVO", new SqlParameter("PI_FLG_ACTIVO", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_IDE_ALMACEN_EXTERNO", new SqlOutParameter("PO_IDE_ALMACEN_EXTERNO", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroAlmacenExterno] Ocurrio un error en la operacion del USP_INS_UPD_ALMACEN_EXTERNO : "+mensajeRespuesta);
    			throw new Exception();
    		}

			//registroAlmacenExterno.setIdAlmacenExterno(((BigDecimal) out.get("PO_IDE_ALMACEN_EXTERNO")).intValue());
			registroAlmacenExterno.setCodigoRespuesta(codigoRespuesta);
			registroAlmacenExterno.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroAlmacenExterno] Fin ");
		return registroAlmacenExterno;
	}
	
	@Override
	public MaeAniosBean insertarRegistroAnio(MaeAniosBean maeAniosBean) throws Exception {
		LOGGER.info("[insertarRegistroAnio] Inicio ");
		MaeAniosBean registroAnio= new MaeAniosBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_COD_ANIO", maeAniosBean.getCodAnio(), Types.VARCHAR);
			input_objParametros.addValue("PI_NOMBRE_ANIO", maeAniosBean.getNombre(), Types.VARCHAR);
			input_objParametros.addValue("PI_DESCRIPCION", maeAniosBean.getDescripcion(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_ANIOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_ANIO", new SqlParameter("PI_COD_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_NOMBRE_ANIO", new SqlParameter("PI_NOMBRE_ANIO", Types.VARCHAR));
			output_objParametros.put("PI_DESCRIPCION", new SqlParameter("PI_DESCRIPCION", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroAnio] Ocurrio un error en la operacion del USP_INS_UPD_ANIOS : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroAnio.setCodigoRespuesta(codigoRespuesta);
			registroAnio.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroAnio] Fin ");
		return registroAnio;
	}
	
	@Override
	public List<ItemBean> obtenerCodigoDdi() throws Exception  {
		LOGGER.info("[obtenerCodigoDdi] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_GENERA_CODIGO_DDI");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PO_COD_DDI", new SqlOutParameter("PO_COD_DDI", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute();
			String codigoRespuesta = (String) out.get("PO_COD_DDI");
			System.out.println("OBTENER CODIGO DDI: "+codigoRespuesta);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerCodigoDdi] Fin ");
		return lista;
	}
	
	@Override
	public MaeDdiBean insertarRegistroDdi(MaeDdiBean maeDdiBean, String control) throws Exception {
		LOGGER.info("[insertarRegistroDdi] Inicio ");
		MaeDdiBean registroDdi= new MaeDdiBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_DDI", maeDdiBean.getIdDdi(), Types.NUMERIC);
			input_objParametros.addValue("PI_COD_DDI", maeDdiBean.getCodigoDdi(), Types.VARCHAR);
			input_objParametros.addValue("PI_NOM_DDI", maeDdiBean.getNombreDdi(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", control, Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_DDI");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.VARCHAR));
			output_objParametros.put("PI_COD_DDI", new SqlParameter("PI_COD_DDI", Types.VARCHAR));
			output_objParametros.put("PI_NOM_DDI", new SqlParameter("PI_NOM_DDI", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroDdi] Ocurrio un error en la operacion del USP_INS_UPD_DDI : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroDdi.setCodigoRespuesta(codigoRespuesta);
			registroDdi.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroDdi] Fin ");
		return registroDdi;
	}
	
	@Override
	public MaeDonanteBean insertarRegistroDonante(MaeDonanteBean maeDonanteBean, String control) throws Exception {
		LOGGER.info("[insertarRegistroDonante] Inicio ");
		MaeDonanteBean registroDonante= new MaeDonanteBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_DONANTE", maeDonanteBean.getIdDonante(), Types.NUMERIC);
			input_objParametros.addValue("PI_NOM_DONANTE", maeDonanteBean.getNombreDonante(), Types.VARCHAR);
			input_objParametros.addValue("PI_NUM_DOCUMENTO", maeDonanteBean.getNroDocumento(), Types.VARCHAR);
			input_objParametros.addValue("PI_TIP_DONANTE", maeDonanteBean.getTipo(), Types.VARCHAR);
			input_objParametros.addValue("PI_REPRESENTANTE", maeDonanteBean.getRepresentante(), Types.VARCHAR);
			input_objParametros.addValue("PI_FK_IDE_DDI", maeDonanteBean.getIdDdi(), Types.NUMERIC);
			input_objParametros.addValue("PI_CONTROL", control, Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_DONANTES");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DONANTE", new SqlParameter("PI_IDE_DONANTE", Types.NUMERIC));
			output_objParametros.put("PI_NOM_DONANTE", new SqlParameter("PI_NOM_DONANTE", Types.VARCHAR));
			output_objParametros.put("PI_NUM_DOCUMENTO", new SqlParameter("PI_NUM_DOCUMENTO", Types.VARCHAR));
			output_objParametros.put("PI_TIP_DONANTE", new SqlParameter("PI_TIP_DONANTE", Types.VARCHAR));
			output_objParametros.put("PI_REPRESENTANTE", new SqlParameter("PI_REPRESENTANTE", Types.VARCHAR));
			output_objParametros.put("PI_FK_IDE_DDI", new SqlParameter("PI_FK_IDE_DDI", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_IDE_DONANTE", new SqlOutParameter("PO_IDE_DONANTE", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroDonante] Ocurrio un error en la operacion del USP_INS_UPD_DONANTES : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroDonante.setCodigoRespuesta(codigoRespuesta);
			registroDonante.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroDonante] Fin ");
		return registroDonante;
	}
	
	@Override
	public MaeEmpresaTransporteBean insertarRegistroEmpresaTransportes(MaeEmpresaTransporteBean maeEmpresaTransporteBean, String control) throws Exception {
		LOGGER.info("[insertarRegistroEmpresaTransportes] Inicio ");
		MaeEmpresaTransporteBean registroEmpresaTransporte= new MaeEmpresaTransporteBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_EMP_TRANS", maeEmpresaTransporteBean.getIdEmpresaTransporte(), Types.NUMERIC);
			input_objParametros.addValue("PI_NOM_EMPRESA", maeEmpresaTransporteBean.getNombreEmpresa(), Types.VARCHAR);
			input_objParametros.addValue("PI_DIRECCION", maeEmpresaTransporteBean.getDireccion(), Types.VARCHAR);
			input_objParametros.addValue("PI_REPRESENTANTE", maeEmpresaTransporteBean.getRepresentante(), Types.VARCHAR);
			input_objParametros.addValue("PI_RUC", maeEmpresaTransporteBean.getRuc(), Types.VARCHAR);
			input_objParametros.addValue("PI_FK_IDE_DDI", maeEmpresaTransporteBean.getIdDdi(), Types.NUMERIC);
			input_objParametros.addValue("PI_TELEFONOS", maeEmpresaTransporteBean.getTelefono(), Types.VARCHAR);
			input_objParametros.addValue("PI_FK_IDE_MED_TRANSPORTE", maeEmpresaTransporteBean.getIdMedioTransporte(), Types.NUMERIC);
			input_objParametros.addValue("PI_CONTROL", control, Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_EMPRESA_TRANSPORTE");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_EMP_TRANS", new SqlParameter("PI_IDE_EMP_TRANS", Types.NUMERIC));
			output_objParametros.put("PI_NOM_EMPRESA", new SqlParameter("PI_NOM_EMPRESA", Types.VARCHAR));
			output_objParametros.put("PI_DIRECCION", new SqlParameter("PI_DIRECCION", Types.VARCHAR));
			output_objParametros.put("PI_REPRESENTANTE", new SqlParameter("PI_REPRESENTANTE", Types.VARCHAR));
			output_objParametros.put("PI_RUC", new SqlParameter("PI_RUC", Types.VARCHAR));
			output_objParametros.put("PI_FK_IDE_DDI", new SqlParameter("PI_FK_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PI_TELEFONOS", new SqlParameter("PI_TELEFONOS", Types.VARCHAR));
			output_objParametros.put("PI_FK_IDE_MED_TRANSPORTE", new SqlParameter("PI_FK_IDE_MED_TRANSPORTE", Types.NUMERIC));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_IDE_EMP_TRANS", new SqlOutParameter("PO_IDE_EMP_TRANS", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroEmpresaTransportes] Ocurrio un error en la operacion del USP_INS_UPD_EMPRESA_TRANSPORTE : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroEmpresaTransporte.setCodigoRespuesta(codigoRespuesta);
			registroEmpresaTransporte.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroEmpresaTransportes] Fin ");
		return registroEmpresaTransporte;
	}
	
	@Override
	public MaeEnvaseBean insertarRegistroEnvase(MaeEnvaseBean maeEnvaseBean, String control) throws Exception {
		LOGGER.info("[insertarRegistroEnvase] Inicio ");
		MaeEnvaseBean registroEnvase= new MaeEnvaseBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("pi_ide_envase", maeEnvaseBean.getIdEnvase(), Types.NUMERIC);
			input_objParametros.addValue("Pi_nom_envase", maeEnvaseBean.getNombreEnvase(), Types.VARCHAR);
			input_objParametros.addValue("Pi_desc_corta", maeEnvaseBean.getDescripcion(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", control, Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_ENVASES");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("pi_ide_envase", new SqlParameter("pi_ide_envase", Types.NUMERIC));
			output_objParametros.put("Pi_nom_envase", new SqlParameter("Pi_nom_envase", Types.VARCHAR));
			output_objParametros.put("Pi_desc_corta", new SqlParameter("Pi_desc_corta", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_ide_envase", new SqlOutParameter("PO_IDE_EMP_TRANS", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroEnvase] Ocurrio un error en la operacion del USP_INS_UPD_ENVASES : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroEnvase.setCodigoRespuesta(codigoRespuesta);
			registroEnvase.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroEnvase] Fin ");
		return registroEnvase;
	}
	
	@Override
	public MaeModAlmacenBean insertarRegistroModAlmacen(MaeModAlmacenBean maeModAlmacenBean, String control) throws Exception {
		LOGGER.info("[insertarRegistroModAlmacen] Inicio ");
		MaeModAlmacenBean registroModAlmacen= new MaeModAlmacenBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("pi_ide_mod_almacen", maeModAlmacenBean.getIdModAlmacen(), Types.NUMERIC);
			input_objParametros.addValue("pi_nom_modalidad", maeModAlmacenBean.getNombreModalidad(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", control, Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_MODALIDAD_ALM");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("pi_ide_mod_almacen", new SqlParameter("pi_ide_mod_almacen", Types.NUMERIC));
			output_objParametros.put("pi_nom_modalidad", new SqlParameter("pi_nom_modalidad", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_ide_envase", new SqlOutParameter("PO_IDE_EMP_TRANS", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroModAlmacen] Ocurrio un error en la operacion del USP_INS_UPD_MODALIDAD_ALM : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroModAlmacen.setCodigoRespuesta(codigoRespuesta);
			registroModAlmacen.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroModAlmacen] Fin ");
		return registroModAlmacen;
	}
	
	@Override
	public MaeOficinaBean insertarRegistroOficina(MaeOficinaBean maeOficinaBean, String control) throws Exception {
		LOGGER.info("[insertarRegistroOficina] Inicio ");
		MaeOficinaBean registroOficina= new MaeOficinaBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_OFICINA", maeOficinaBean.getIdOficina(), Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_OFICINA", maeOficinaBean.getNombreOficina(), Types.VARCHAR);
			input_objParametros.addValue("PI_IDE_DDI", maeOficinaBean.getIdDdi(), Types.NUMERIC);
			input_objParametros.addValue("PI_CONTROL", control, Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_OFICINA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_OFICINA", new SqlParameter("PI_IDE_OFICINA", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_OFICINA", new SqlParameter("PI_NOMBRE_OFICINA", Types.VARCHAR));
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_IDE_MOD_ALMACEN", new SqlOutParameter("PO_IDE_MOD_ALMACEN", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroOficina] Ocurrio un error en la operacion del USP_INS_UPD_OFICINA : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroOficina.setCodigoRespuesta(codigoRespuesta);
			registroOficina.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroOficina] Fin ");
		return registroOficina;
	}
	
	@Override
	public MaePersonalBean insertarRegistroPersonal(MaePersonalBean maePersonalBean, String control) throws Exception {
		LOGGER.info("[insertarRegistroPersonal] Inicio ");
		MaePersonalBean registroPersonal= new MaePersonalBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("pi_ide_personal", maePersonalBean.getIdPersonal(), Types.NUMERIC);
			input_objParametros.addValue("pi_fk_ide_ddi", maePersonalBean.getIdDdi(), Types.NUMERIC);
			input_objParametros.addValue("pi_apellidos", maePersonalBean.getApellidos(), Types.VARCHAR);
			input_objParametros.addValue("pi_nombres", maePersonalBean.getNombres(), Types.VARCHAR);
			input_objParametros.addValue("pi_cargo", maePersonalBean.getCargo(), Types.VARCHAR);
			input_objParametros.addValue("pi_fk_ide_oficina", maePersonalBean.getIdOficina(), Types.NUMERIC);
			input_objParametros.addValue("pi_telefonos", maePersonalBean.getTelefono(), Types.VARCHAR);
			input_objParametros.addValue("pi_flg_activo", maePersonalBean.getFlagActivo(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", control, Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", maePersonalBean.getUsuario(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_PERSONAL");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("pi_ide_personal", new SqlParameter("pi_ide_personal", Types.NUMERIC));
			output_objParametros.put("pi_fk_ide_ddi", new SqlParameter("pi_fk_ide_ddi", Types.NUMERIC));
			output_objParametros.put("pi_apellidos", new SqlParameter("pi_apellidos", Types.VARCHAR));
			output_objParametros.put("pi_nombres", new SqlParameter("pi_nombres", Types.VARCHAR));
			output_objParametros.put("pi_cargo", new SqlParameter("pi_cargo", Types.VARCHAR));
			output_objParametros.put("pi_fk_ide_oficina", new SqlParameter("pi_fk_ide_oficina", Types.NUMERIC));
			output_objParametros.put("pi_telefonos", new SqlParameter("pi_telefonos", Types.VARCHAR));
			output_objParametros.put("pi_flg_activo", new SqlParameter("pi_flg_activo", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("pO_ide_personal", new SqlOutParameter("pO_ide_personal", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroPersonal] Ocurrio un error en la operacion del USP_INS_UPD_PERSONAL : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroPersonal.setCodigoRespuesta(codigoRespuesta);
			registroPersonal.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroPersonal] Fin ");
		return registroPersonal;
	}
	
	@Override
	public MaePersonalExternoBean insertarRegistroPersonalExterno(MaePersonalExternoBean maePersonalExternoBean, String control) throws Exception {
		LOGGER.info("[insertarRegistroPersonalExterno] Inicio ");
		MaePersonalExternoBean registroPersonalexterno= new MaePersonalExternoBean();
		try {			
			LOGGER.info("[insertarRegistroPersonalExterno] Inicioooo "+maePersonalExternoBean.getIdRegion());
			System.out.println("IDREGION "+maePersonalExternoBean.getIdRegion());
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_PERSONAL_EXT", maePersonalExternoBean.getIdPersonalExterno(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_REGION", maePersonalExternoBean.getIdRegion(), Types.NUMERIC);
			input_objParametros.addValue("PI_COD_UBIGEO", maePersonalExternoBean.getCodUbigeo(), Types.VARCHAR);
			input_objParametros.addValue("PI_NOMBRE_PERSONAL_EXTERNO", maePersonalExternoBean.getNombres(), Types.VARCHAR);
			input_objParametros.addValue("PI_CARGO", maePersonalExternoBean.getCargo(), Types.VARCHAR);
			input_objParametros.addValue("PI_TIPO_PERSONAL", maePersonalExternoBean.getTipoPersonal(), Types.VARCHAR);
			input_objParametros.addValue("PI_TELEFONOS", maePersonalExternoBean.getTelefono(), Types.VARCHAR);
			input_objParametros.addValue("PI_FLG_ACTIVO", maePersonalExternoBean.getFlagActivo(), Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", maePersonalExternoBean.getUsuario(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", control, Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_PERSONAL_EXT");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PERSONAL_EXT", new SqlParameter("PI_IDE_PERSONAL_EXT", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_REGION", new SqlParameter("PI_FK_IDE_REGION", Types.NUMERIC));
			output_objParametros.put("PI_COD_UBIGEO", new SqlParameter("PI_COD_UBIGEO", Types.VARCHAR));
			output_objParametros.put("PI_NOMBRE_PERSONAL_EXTERNO", new SqlParameter("PI_NOMBRE_PERSONAL_EXTERNO", Types.VARCHAR));
			output_objParametros.put("PI_CARGO", new SqlParameter("PI_CARGO", Types.VARCHAR));
			output_objParametros.put("PI_TIPO_PERSONAL", new SqlParameter("PI_TIPO_PERSONAL", Types.VARCHAR));
			output_objParametros.put("PI_TELEFONOS", new SqlParameter("PI_TELEFONOS", Types.VARCHAR));
			output_objParametros.put("PI_FLG_ACTIVO", new SqlParameter("PI_FLG_ACTIVO", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_IDE_PERSONAL_EXT", new SqlOutParameter("PO_IDE_PERSONAL_EXT", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroPersonalExterno] Ocurrio un error en la operacion del USP_INS_UPD_PERSONAL_EXT : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroPersonalexterno.setCodigoRespuesta(codigoRespuesta);
			registroPersonalexterno.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroPersonalExterno] Fin ");
		return registroPersonalexterno;
	}
	
	@Override
	public List<ItemBean> obtenerRegionXDdi(Integer idDdi) throws Exception {
		LOGGER.info("[obtenerRegionXDdi] Inicio ");
		List<ItemBean> region = new ArrayList<ItemBean>();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ID_DDI", idDdi, Types.NUMERIC);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_BUSCAR_REGION");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_DDI", new SqlParameter("PI_ID_DDI", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new RegionXDdiMapper()));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			region = (List<ItemBean>) out.get("PO_LR_RECORDSET");

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerRegionXDdi] Fin ");
		return region;
	}
	
	@Override
	public MaeTipCamionBean insertarRegistroTipoCamion(MaeTipCamionBean maeTipCamionBean, String control) throws Exception {
		LOGGER.info("[insertarRegistroTipoCamion] Inicio ");
		MaeTipCamionBean registroTipCamion= new MaeTipCamionBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ide_tip_camion", maeTipCamionBean.getIdTipCamion(), Types.NUMERIC);
			input_objParametros.addValue("PI_tonelaje", maeTipCamionBean.getTonelaje(), Types.NUMERIC);
			input_objParametros.addValue("PI_volumen", maeTipCamionBean.getVolumen(), Types.NUMERIC);
			input_objParametros.addValue("PI_descripcion", maeTipCamionBean.getDescripcion(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", control, Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_TIPO_CAMION");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ide_tip_camion", new SqlParameter("PI_ide_tip_camion", Types.NUMERIC));
			output_objParametros.put("PI_tonelaje", new SqlParameter("PI_tonelaje", Types.NUMERIC));
			output_objParametros.put("PI_volumen", new SqlParameter("PI_volumen", Types.NUMERIC));
			output_objParametros.put("PI_descripcion", new SqlParameter("PI_descripcion", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_ide_tip_camion", new SqlOutParameter("PO_ide_tip_camion", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroTipoCamion] Ocurrio un error en la operacion del USP_INS_UPD_TIPO_CAMION : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroTipCamion.setCodigoRespuesta(codigoRespuesta);
			registroTipCamion.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroTipoCamion] Fin ");
		return registroTipCamion;
	}
	
	@Override
	public MaeTipControlCalidadBean insertarRegistroControlCalidad(MaeTipControlCalidadBean maeTipControlCalidadBean, String control) throws Exception {
		LOGGER.info("[insertarRegistroControlCalidad] Inicio ");
		MaeTipControlCalidadBean registroTipControlCalidad= new MaeTipControlCalidadBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_TIP_CONTROL", maeTipControlCalidadBean.getIdTipControl(), Types.NUMERIC);
			input_objParametros.addValue("PI_NOM_TIP_CONTROL", maeTipControlCalidadBean.getNombreTipControl(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", control, Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_TIP_CONTROL_CALIDA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_TIP_CONTROL", new SqlParameter("PI_IDE_TIP_CONTROL", Types.NUMERIC));
			output_objParametros.put("PI_NOM_TIP_CONTROL", new SqlParameter("PI_NOM_TIP_CONTROL", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_IDE_TIP_CONTROL", new SqlOutParameter("PO_ide_tip_camion", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroControlCalidad] Ocurrio un error en la operacion del USP_INS_UPD_TIP_CONTROL_CALIDA : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroTipControlCalidad.setCodigoRespuesta(codigoRespuesta);
			registroTipControlCalidad.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroControlCalidad] Fin ");
		return registroTipControlCalidad;
	}
	
	@Override
	public MaeTipDocumentoBean insertarRegistroDocumentos(MaeTipDocumentoBean maeTipDocumentoBean, String control) throws Exception {
		LOGGER.info("[insertarRegistroDocumentos] Inicio ");
		MaeTipDocumentoBean registroTipDocumento= new MaeTipDocumentoBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_TIP_DOCUMENTO", maeTipDocumentoBean.getIdTipDocumento(), Types.NUMERIC);
			input_objParametros.addValue("PI_NOM_DOCUMENTO", maeTipDocumentoBean.getNombreDocumento(), Types.VARCHAR);
			input_objParametros.addValue("PI_NOM_CORTO", maeTipDocumentoBean.getNombreCorto(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", control, Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_TIPO_DOCUMENTO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_TIP_DOCUMENTO", new SqlParameter("PI_IDE_TIP_DOCUMENTO", Types.NUMERIC));
			output_objParametros.put("PI_NOM_DOCUMENTO", new SqlParameter("PI_NOM_DOCUMENTO", Types.VARCHAR));
			output_objParametros.put("PI_NOM_CORTO", new SqlParameter("PI_NOM_CORTO", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_IDE_TIP_DOCUMENTO", new SqlOutParameter("PO_ide_tip_camion", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroDocumentos] Ocurrio un error en la operacion del USP_INS_UPD_TIPO_DOCUMENTO : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroTipDocumento.setCodigoRespuesta(codigoRespuesta);
			registroTipDocumento.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroDocumentos] Fin ");
		return registroTipDocumento;
	}
	
	@Override
	public MaeUnidadMedidaBean insertarRegistroUnidadMedida(MaeUnidadMedidaBean maeUnidadMedidaBean, String control) throws Exception {
		LOGGER.info("[insertarRegistroUnidadMedida] Inicio ");
		MaeUnidadMedidaBean registroUnidadMedida= new MaeUnidadMedidaBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_UNID_MEDIDA", maeUnidadMedidaBean.getIdUnidad(), Types.NUMERIC);
			input_objParametros.addValue("PI_NOM_UNIDAD", maeUnidadMedidaBean.getNombreUnidad(), Types.VARCHAR);
			input_objParametros.addValue("PI_NOM_CORTO", maeUnidadMedidaBean.getNombreCorto(), Types.VARCHAR);
			input_objParametros.addValue("PI_FLG_ACTIVO", maeUnidadMedidaBean.getFlagActivo(), Types.VARCHAR);
//			input_objParametros.addValue("PI_IDE_UNID_MEDIDA_SIGA", maeUnidadMedidaBean.getIdUnidadSiga(), Types.NUMERIC);
			input_objParametros.addValue("PI_CONTROL", control, Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_UNIDAD_MEDIDA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_UNID_MEDIDA", new SqlParameter("PI_IDE_UNID_MEDIDA", Types.NUMERIC));
			output_objParametros.put("PI_NOM_UNIDAD", new SqlParameter("PI_NOM_UNIDAD", Types.VARCHAR));
			output_objParametros.put("PI_NOM_CORTO", new SqlParameter("PI_NOM_CORTO", Types.VARCHAR));
			output_objParametros.put("PI_FLG_ACTIVO", new SqlParameter("PI_FLG_ACTIVO", Types.VARCHAR));
//			output_objParametros.put("PI_IDE_UNID_MEDIDA_SIGA", new SqlParameter("PI_IDE_UNID_MEDIDA_SIGA", Types.NUMERIC));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_IDE_UNID_MEDIDA", new SqlOutParameter("PO_IDE_UNID_MEDIDA", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroUnidadMedida] Ocurrio un error en la operacion del USP_INS_UPD_UNIDAD_MEDIDA : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroUnidadMedida.setCodigoRespuesta(codigoRespuesta);
			registroUnidadMedida.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroUnidadMedida] Fin ");
		return registroUnidadMedida;
	}
	
	@Override
	public MaeMarcaBean insertarRegistroMarca(MaeMarcaBean maeMarcaBean, String control) throws Exception {
		LOGGER.info("[insertarRegistroMarca] Inicio ");
		MaeMarcaBean registroUnidadMedida= new MaeMarcaBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_MARCA", maeMarcaBean.getIdMarca(), Types.NUMERIC);
			input_objParametros.addValue("PI_NOM_MARCA", maeMarcaBean.getNombreMarca(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", control, Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", maeMarcaBean.getUsuario(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_MARCA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_MARCA", new SqlParameter("PI_IDE_MARCA", Types.NUMERIC));
			output_objParametros.put("PI_NOM_MARCA", new SqlParameter("PI_NOM_MARCA", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_IDE_MARCA", new SqlOutParameter("PO_IDE_MARCA", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroMarca] Ocurrio un error en la operacion del USP_INS_UPD_MARCA : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroUnidadMedida.setCodigoRespuesta(codigoRespuesta);
			registroUnidadMedida.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroMarca] Fin ");
		return registroUnidadMedida;
	}
	
	@Override
	public MaeChoferBean insertarRegistroChofer(MaeChoferBean maeChoferBean, String control) throws Exception {
		LOGGER.info("[insertarRegistroChofer] Inicio ");
		MaeChoferBean registroChofer= new MaeChoferBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_CHOFER", maeChoferBean.getIdChofer(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_EMP_TRANS", maeChoferBean.getIdEmpresaTransporte(), Types.NUMERIC);
			input_objParametros.addValue("PI_NOM_CHOFER", maeChoferBean.getNombreChofer(), Types.VARCHAR);
			input_objParametros.addValue("PI_NRO_DNI", maeChoferBean.getNroDni(), Types.VARCHAR);
			input_objParametros.addValue("PI_NRO_BREVETE", maeChoferBean.getNroBrevete(), Types.VARCHAR);
			input_objParametros.addValue("PI_FLG_ACTIVO", maeChoferBean.getFlagActivo(), Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", maeChoferBean.getUsuario(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", control, Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_CHOFER");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_CHOFER", new SqlParameter("PI_IDE_CHOFER", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_EMP_TRANS", new SqlParameter("PI_FK_IDE_EMP_TRANS", Types.NUMERIC));
			output_objParametros.put("PI_NOM_CHOFER", new SqlParameter("PI_NOM_CHOFER", Types.VARCHAR));
			output_objParametros.put("PI_NRO_DNI", new SqlParameter("PI_NRO_DNI", Types.VARCHAR));
			output_objParametros.put("PI_NRO_BREVETE", new SqlParameter("PI_NRO_BREVETE", Types.VARCHAR));
			output_objParametros.put("PI_FLG_ACTIVO", new SqlParameter("PI_FLG_ACTIVO", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_IDE_CHOFER", new SqlOutParameter("PO_IDE_CHOFER", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRegistroChofer] Ocurrio un error en la operacion del USP_INS_UPD_CHOFER : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroChofer.setCodigoRespuesta(codigoRespuesta);
			registroChofer.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRegistroChofer] Fin ");
		return registroChofer;
	}
	
	/////////////////////////////////////////////////////////////////////////
	/////////////////USUARIOS////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////

	@Override
	public List<UsuarioBean> listarUsuarios(UsuarioBean user) throws Exception {
		LOGGER.info("[listarUsuarios] Inicio ");
		List<UsuarioBean> lista = new ArrayList<UsuarioBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_IDE_DDI", user.getIdDdi(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_USUARIO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_USUARIO", new SqlOutParameter("PO_CURSOR_USUARIO", OracleTypes.CURSOR, new Usuarios1Mapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarUsuarios] Ocurrio un error en la operacion del USP_SEL_LISTAR_USUARIO : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<UsuarioBean>) out.get("PO_CURSOR_USUARIO");
				
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarUsuarios] Fin ");
		return lista;
	}
	
	
	@Override
	public UsuarioBean obtenerUsuarioXId(Integer idusuario) throws Exception {
		LOGGER.info("[obtenerUsuarioXId] Inicio ");
		UsuarioBean proyectoManifiesto = new UsuarioBean();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();	
			input_objParametros.addValue("PI_ID_USER", idusuario, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_EDITAR_USUARIO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_USER", new SqlParameter("PI_ID_USER", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_USUARIO", new SqlOutParameter("PO_CURSOR_USUARIO", OracleTypes.CURSOR, new UsuarioEditar1Mapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerUsuarioXId] Ocurrio un error en la operacion del USP_SEL_EDITAR_USUARIO : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			List<UsuarioBean> lista = (List<UsuarioBean>) out.get("PO_CURSOR_USUARIO");
			if (!Utils.isEmpty(lista)) {
				proyectoManifiesto = lista.get(0);
			}
			
			proyectoManifiesto.setCodigoRespuesta(codigoRespuesta);
			proyectoManifiesto.setMensajeRespuesta((String) out.get("po_MENSAJE_RESPUESTA"));			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerUsuarioXId] Fin ");
		return proyectoManifiesto;
	}
	
	@Override
	public List<AlmacenBean> ListaAlmacenesXUsuario(Integer idUsuario) throws Exception {
		LOGGER.info("[ListaAlmacenesXUsuario] Inicio ");
		List<AlmacenBean> lista = new ArrayList<AlmacenBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_IDE_USER", idUsuario, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTA_ALMACEN_POR_USER");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_USER", new SqlParameter("PI_IDE_USER", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new AlmacenUsuarioMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[ListaAlmacenesXUsuario] Ocurrio un error en la operacion del USP_SEL_LISTA_ALMACEN_POR_USER : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<AlmacenBean>) out.get("PO_LR_RECORDSET");
				
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[ListaAlmacenesXUsuario] Fin ");
		return lista;
	}
	
	@Override
	public UsuarioBean insertarUsuario(UsuarioBean user) throws Exception {
		LOGGER.info("[insertarUsuario] Inicio ");
		UsuarioBean registroUsuario= new UsuarioBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ID_USER", user.getIdUsuario(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", user.getUsuario(), Types.VARCHAR);
			input_objParametros.addValue("PI_PASSWORD", user.getPassword(), Types.VARCHAR);
			input_objParametros.addValue("PI_CARGO", user.getCargo(), Types.VARCHAR);
			input_objParametros.addValue("PI_EMAIL", user.getEmail(), Types.VARCHAR);
			input_objParametros.addValue("PI_NOMBRE_USUARIO", user.getNombreUsuario(), Types.VARCHAR);
			input_objParametros.addValue("PI_FLG_ACTIVO", user.getFlagActivo(), Types.VARCHAR);
			input_objParametros.addValue("PI_IDE_DDI", user.getIdDdi(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", "I", Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_USUARIO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_USER", new SqlParameter("PI_ID_USER", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PI_PASSWORD", new SqlParameter("PI_PASSWORD", Types.VARCHAR));
			output_objParametros.put("PI_CARGO", new SqlParameter("PI_CARGO", Types.VARCHAR));
			output_objParametros.put("PI_EMAIL", new SqlParameter("PI_EMAIL", Types.VARCHAR));
			output_objParametros.put("PI_NOMBRE_USUARIO", new SqlParameter("PI_NOMBRE_USUARIO", Types.VARCHAR));
			output_objParametros.put("PI_FLG_ACTIVO", new SqlParameter("PI_FLG_ACTIVO", Types.VARCHAR));
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_ID_USER", new SqlOutParameter("PO_ID_USER", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarUsuario] Ocurrio un error en la operacion del USP_INS_UPD_USUARIO : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			if (codigoRespuesta.equals(Constantes.COD_VALIDACION_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarUsuario] Usuario ya existe un error en la operacion del USP_INS_UPD_USUARIO : "+mensajeRespuesta);
    			
    		}else{
    			registroUsuario.setIdUsuario(((BigDecimal) out.get("PO_ID_USER")).intValue());
    		}
	
			
			registroUsuario.setCodigoRespuesta(codigoRespuesta);
			registroUsuario.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarUsuario] Fin ");
		return registroUsuario;
	}
	
	@Override
	public UsuarioBean actualizarUsuario(UsuarioBean user) throws Exception {
		LOGGER.info("[actualizarUsuario] Inicio ");
		UsuarioBean registroUsuario= new UsuarioBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ID_USER", user.getIdUsuario(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", user.getUsuario(), Types.VARCHAR);
			input_objParametros.addValue("PI_PASSWORD", user.getPassword(), Types.VARCHAR);
			input_objParametros.addValue("PI_CARGO", user.getCargo(), Types.VARCHAR);
			input_objParametros.addValue("PI_EMAIL", user.getEmail(), Types.VARCHAR);
			input_objParametros.addValue("PI_NOMBRE_USUARIO", user.getNombreUsuario(), Types.VARCHAR);
			input_objParametros.addValue("PI_FLG_ACTIVO", user.getFlagActivo(), Types.VARCHAR);
			input_objParametros.addValue("PI_IDE_DDI", user.getIdDdi(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", "U", Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_USUARIO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_USER", new SqlParameter("PI_ID_USER", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PI_PASSWORD", new SqlParameter("PI_PASSWORD", Types.VARCHAR));
			output_objParametros.put("PI_CARGO", new SqlParameter("PI_CARGO", Types.VARCHAR));
			output_objParametros.put("PI_EMAIL", new SqlParameter("PI_EMAIL", Types.VARCHAR));
			output_objParametros.put("PI_NOMBRE_USUARIO", new SqlParameter("PI_NOMBRE_USUARIO", Types.VARCHAR));
			output_objParametros.put("PI_FLG_ACTIVO", new SqlParameter("PI_FLG_ACTIVO", Types.VARCHAR));
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_ID_USER", new SqlOutParameter("PO_ID_USER", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[actualizarUsuario] Ocurrio un error en la operacion del USP_INS_UPD_USUARIO : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
	
			registroUsuario.setIdUsuario(user.getIdUsuario());
			registroUsuario.setCodigoRespuesta(codigoRespuesta);
			registroUsuario.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[actualizarUsuario] Fin ");
		return registroUsuario;
	}
	
	
	@Override
	public List<RolBean> listarRoles(Integer idUsuario) throws Exception {
		LOGGER.info("[listarRoles] Inicio ");
		List<RolBean> lista = new ArrayList<RolBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			
			input_objParametros.addValue("PI_ID_USER", idUsuario, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_ROLES");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_USER", new SqlParameter("PI_ID_USER", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new RolMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarRoles] Ocurrio un error en la operacion del USP_SEL_LISTAR_ROLES : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<RolBean>) out.get("PO_LR_RECORDSET");
				
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarRoles] Fin ");
		return lista;
	}
	
	@Override
	public List<AlmacenBean> ListaAlmacenesXUsuarioDdi(AlmacenBean almacenBean) throws Exception {
		LOGGER.info("[ListaAlmacenesXUsuarioDdi] Inicio ");
		List<AlmacenBean> lista = new ArrayList<AlmacenBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();	
			input_objParametros.addValue("PI_IDE_DDI", almacenBean.getIdDdi(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_USER", almacenBean.getIdUsuario(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_EDITA_ALMACEN_POR_USER");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PI_IDE_USER", new SqlParameter("PI_IDE_USER", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new AlmacenUsuarioDdiMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[ListaAlmacenesXUsuarioDdi] Ocurrio un error en la operacion del USP_SEL_EDITA_ALMACEN_POR_USER : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<AlmacenBean>) out.get("PO_LR_RECORDSET");
				
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[ListaAlmacenesXUsuarioDdi] Fin ");
		return lista;
	}
	
	
	@Override
	public AlmacenBean insertarAlmacenXUsuario(AlmacenBean almacenBean) throws Exception {
		LOGGER.info("[insertarAlmacenXUsuario] Inicio ");
		AlmacenBean registroAlmacen= new AlmacenBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ID_USER", almacenBean.getIdUsuario(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_ALMACEN", almacenBean.getIdAlmacen(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", almacenBean.getUsuario(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_ALMACEN_POR_USER");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_USER", new SqlParameter("PI_ID_USER", Types.NUMERIC));
			output_objParametros.put("PI_IDE_ALMACEN", new SqlParameter("PI_IDE_ALMACEN", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarAlmacenXUsuario] Ocurrio un error en la operacion del USP_INS_ALMACEN_POR_USER : "+mensajeRespuesta);
    			throw new Exception();
    		}
	
			registroAlmacen.setCodigoRespuesta(codigoRespuesta);
			registroAlmacen.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarAlmacenXUsuario] Fin ");
		return registroAlmacen;
	}
	
	@Override
	public AlmacenBean eliminarAlmacenXUsuario(AlmacenBean almacenBean) throws Exception {
		LOGGER.info("[eliminarAlmacenXUsuario] Inicio ");
		AlmacenBean registroAlmacen= new AlmacenBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ID_USER", almacenBean.getIdUsuario(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_ALMACEN", almacenBean.getIdAlmacen(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", almacenBean.getUsuario(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_DEL_ALMACEN_POR_USER");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_USER", new SqlParameter("PI_ID_USER", Types.NUMERIC));
			output_objParametros.put("PI_IDE_ALMACEN", new SqlParameter("PI_IDE_ALMACEN", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[eliminarAlmacenXUsuario] Ocurrio un error en la operacion del USP_DEL_ALMACEN_POR_USER : "+mensajeRespuesta);
    			throw new Exception();
    		}
	
			registroAlmacen.setCodigoRespuesta(codigoRespuesta);
			registroAlmacen.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[eliminarAlmacenXUsuario] Fin ");
		return registroAlmacen;
	}
	
	@Override
	public RolBean insertarRolXUsuario(RolBean rolBean) throws Exception {
		LOGGER.info("[insertarRolXUsuario] Inicio ");
		RolBean registroRol= new RolBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ID_USER", rolBean.getIdUsuario(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_ROL", rolBean.getIdRol(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", rolBean.getUsuario(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_ROL_POR_USER");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_USER", new SqlParameter("PI_ID_USER", Types.NUMERIC));
			output_objParametros.put("PI_IDE_ROL", new SqlParameter("PI_IDE_ROL", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRolXUsuario] Ocurrio un error en la operacion del USP_INS_ROL_POR_USER : "+mensajeRespuesta);
    			throw new Exception();
    		}
	
			registroRol.setCodigoRespuesta(codigoRespuesta);
			registroRol.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRolXUsuario] Fin ");
		return registroRol;
	}
	
	@Override
	public RolBean eliminarRolXUsuario(RolBean rolBean) throws Exception {
		LOGGER.info("[eliminarRolXUsuario] Inicio ");
		RolBean registroRol= new RolBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ID_USER", rolBean.getIdUsuario(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_ROL", rolBean.getIdRol(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", rolBean.getUsuario(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_DEL_ROL_POR_USER");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_USER", new SqlParameter("PI_ID_USER", Types.NUMERIC));
			output_objParametros.put("PI_IDE_ROL", new SqlParameter("PI_IDE_ROL", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[eliminarRolXUsuario] Ocurrio un error en la operacion del USP_DEL_ROL_POR_USER : "+mensajeRespuesta);
    			throw new Exception();
    		}
	
			registroRol.setCodigoRespuesta(codigoRespuesta);
			registroRol.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[eliminarRolXUsuario] Fin ");
		return registroRol;
	}
	
	@Override
	public List<RolMenuBean> mostrarRoles(Integer idRol) throws Exception {
		LOGGER.info("[mostrarRoles] Inicio ");
		List<RolMenuBean> lista = new ArrayList<RolMenuBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			
			input_objParametros.addValue("pi_ide_rol", idRol, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_EDITAR_MENU_POR_ROL");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("pi_ide_rol", new SqlParameter("pi_ide_rol", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new RolMostrarMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[mostrarRoles] Ocurrio un error en la operacion del USP_SEL_EDITAR_MENU_POR_ROL : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<RolMenuBean>) out.get("PO_LR_RECORDSET");
				
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[mostrarRoles] Fin ");
		return lista;
	}
	
	@Override
	public List<RolMenuBean> ListaMenuRoles(Integer idRol) throws Exception {
		LOGGER.info("[ListaMenuRoles] Inicio ");
		List<RolMenuBean> lista = new ArrayList<RolMenuBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();	
			System.out.println("IDROL: "+idRol);
			input_objParametros.addValue("PI_ID_ROL", idRol, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_MENU_POR_ROL");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_ROL", new SqlParameter("PI_ID_ROL", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new RolMenuMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[ListaMenuRoles] Ocurrio un error en la operacion del USP_SEL_LISTAR_MENU_POR_ROL : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<RolMenuBean>) out.get("PO_LR_RECORDSET");
				
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[ListaMenuRoles] Fin ");
		return lista;
	}
	
	@Override
	public List<RolMenuBean> ListaMenuNuevo() throws Exception {
		LOGGER.info("[ListaMenuNuevo] Inicio ");
		List<RolMenuBean> lista = new ArrayList<RolMenuBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();	
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_MENU");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new RolMenuMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[ListaMenuNuevo] Ocurrio un error en la operacion del USP_SEL_LISTAR_MENU : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<RolMenuBean>) out.get("PO_LR_RECORDSET");
				
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[ListaMenuNuevo] Fin ");
		return lista;
	}
	
	@Override
	public RolBean insertarRol(RolBean rolBean) throws Exception {
		LOGGER.info("[insertarRol] Inicio ");
		RolBean registroRol= new RolBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ID_ROL", rolBean.getIdRol(), Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_ROL", rolBean.getNombreRol(), Types.VARCHAR);
			input_objParametros.addValue("PI_FLG_ACTIVO", rolBean.getFlagActivo(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", "I", Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", rolBean.getUsuario(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_ROL");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_ROL", new SqlParameter("PI_ID_ROL", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_ROL", new SqlParameter("PI_NOMBRE_ROL", Types.VARCHAR));
			output_objParametros.put("PI_FLG_ACTIVO", new SqlParameter("PI_FLG_ACTIVO", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_ID_ROL", new SqlOutParameter("PO_ID_ROL", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarRol] Ocurrio un error en la operacion del USP_INS_UPD_ROL : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroRol.setIdRol(((BigDecimal) out.get("PO_ID_ROL")).intValue());
			registroRol.setCodigoRespuesta(codigoRespuesta);
			registroRol.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarRol] Fin ");
		return registroRol;
	}
	
	@Override
	public RolBean actualizarRol(RolBean rolBean) throws Exception {
		LOGGER.info("[actualizarRol] Inicio ");
		RolBean registroRol= new RolBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ID_ROL", rolBean.getIdRol(), Types.NUMERIC);
			input_objParametros.addValue("PI_NOMBRE_ROL", rolBean.getNombreRol(), Types.VARCHAR);
			input_objParametros.addValue("PI_FLG_ACTIVO", rolBean.getFlagActivo(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", "U", Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", rolBean.getUsuario(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_ROL");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_ROL", new SqlParameter("PI_ID_ROL", Types.NUMERIC));
			output_objParametros.put("PI_NOMBRE_ROL", new SqlParameter("PI_NOMBRE_ROL", Types.VARCHAR));
			output_objParametros.put("PI_FLG_ACTIVO", new SqlParameter("PI_FLG_ACTIVO", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_ID_ROL", new SqlOutParameter("PO_ID_ROL", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[actualizarRol] Ocurrio un error en la operacion del USP_INS_UPD_ROL : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroRol.setIdRol(rolBean.getIdRol());
			registroRol.setCodigoRespuesta(codigoRespuesta);
			registroRol.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[actualizarRol] Fin ");
		return registroRol;
	}
	
	@Override
	public RolMenuBean InsertarActualizarMenuRol(RolMenuBean rolBean) throws Exception {
		LOGGER.info("[InsertarActualizarMenuRol] Inicio ");
		RolMenuBean registroRol= new RolMenuBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ID_ROL", rolBean.getIdRol(), Types.NUMERIC);
			input_objParametros.addValue("PI_ID_MENU", rolBean.getIdMenu(), Types.NUMERIC);
			input_objParametros.addValue("PI_FLG_ACTIVO", rolBean.getFlagActivo(), Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", rolBean.getUsuario(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_MENU_POR_ROL");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_ROL", new SqlParameter("PI_ID_ROL", Types.NUMERIC));
			output_objParametros.put("PI_ID_MENU", new SqlParameter("PI_ID_MENU", Types.NUMERIC));
			output_objParametros.put("PI_FLG_ACTIVO", new SqlParameter("PI_FLG_ACTIVO", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[InsertarActualizarMenuRol] Ocurrio un error en la operacion USP_INS_UPD_MENU_POR_ROL : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroRol.setCodigoRespuesta(codigoRespuesta);
			registroRol.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[InsertarActualizarMenuRol] Fin ");
		return registroRol;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////
	/////////////////CATALOGO DE PRODUCTOS///////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public List<CatalogoProductoBean> listarCatalogoProductosXCat(CatalogoProductoBean catalogoProductoBean) throws Exception {
		LOGGER.info("[listarCatalogoProductosXCat] Inicio ");
		List<CatalogoProductoBean> lista = new ArrayList<CatalogoProductoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_IDE_CATEGORIA_BAH", catalogoProductoBean.getIdCategoria(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_PRODUCTOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_CATEGORIA_BAH", new SqlParameter("PI_IDE_CATEGORIA_BAH", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new CatalogoProductoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				LOGGER.info("[listarCatalogoProductosXCat] Ocurrio un error en la operacion del USP_SEL_LISTAR_PRODUCTOS");
				throw new Exception();
			} else {
				lista = (List<CatalogoProductoBean>) out.get("PO_LR_RECORDSET");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarCatalogoProductosXCat] Fin ");
		return lista;
	}
	
	@Override
	public List<CatalogoProductoBean> listarCatalogoProductosXNom(CatalogoProductoBean catalogoProductoBean) throws Exception {
		LOGGER.info("[listarCatalogoProductosXNom] Inicio ");
		List<CatalogoProductoBean> lista = new ArrayList<CatalogoProductoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_NOMBRE_PRODUCTO", catalogoProductoBean.getNombreProducto(), Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_BUSCAR_PRODUCTO_X_NOMB");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_NOMBRE_PRODUCTO", new SqlParameter("PI_NOMBRE_PRODUCTO", Types.VARCHAR));
			output_objParametros.put("PO_CURSOR_PRODUCTO", new SqlOutParameter("PO_CURSOR_PRODUCTO", OracleTypes.CURSOR, new CatalogoProductoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				LOGGER.info("[listarCatalogoProductosXNom] Ocurrio un error en la operacion del USP_SEL_BUSCAR_PRODUCTO_X_NOMB");
				throw new Exception();
			} else {
				lista = (List<CatalogoProductoBean>) out.get("PO_CURSOR_PRODUCTO");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarCatalogoProductosXNom] Fin ");
		return lista;
	}
	
	@Override
	public CatalogoProductoBean obtenerCatalogoProductoXId(Integer idProducto) throws Exception {
		LOGGER.info("[obtenerCatalogoProductoXId] Inicio ");
		CatalogoProductoBean catalogoProducto = new CatalogoProductoBean();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();	
			input_objParametros.addValue("PI_IDE_PRODUCTO", idProducto, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_EDITAR_PRODUCTO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PRODUCTO", new SqlParameter("PI_IDE_PRODUCTO", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_PRODUCTO", new SqlOutParameter("PO_CURSOR_PRODUCTO", OracleTypes.CURSOR, new CatalogoProductoEditar1Mapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[obtenerCatalogoProductoXId] Ocurrio un error en la operacion del USP_SEL_EDITAR_PRODUCTO : "+mensajeRespuesta);
    			throw new Exception();
    		}
			
			List<CatalogoProductoBean> lista = (List<CatalogoProductoBean>) out.get("PO_CURSOR_PRODUCTO");
			if (!Utils.isEmpty(lista)) {
				catalogoProducto = lista.get(0);
			}

			catalogoProducto.setCodigoRespuesta(codigoRespuesta);
			catalogoProducto.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerCatalogoProductoXId] Fin ");
		return catalogoProducto;
	}
	
	@Override
	public CatalogoProductoBean insertarCatalogoProducto(CatalogoProductoBean catalogoProductoBean) throws Exception {
		LOGGER.info("[insertarCatalogoProducto] Inicio ");
		CatalogoProductoBean registroProducto= new CatalogoProductoBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_PRODUCTO", catalogoProductoBean.getIdProducto(), Types.NUMERIC);
			input_objParametros.addValue("PI_COD_PRODUCTO", catalogoProductoBean.getCodigoProducto(), Types.VARCHAR);
			input_objParametros.addValue("PI_NOM_PRODUCTO", catalogoProductoBean.getNombreProducto(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_SIGA", catalogoProductoBean.getCodigoSiga(), Types.VARCHAR);
			input_objParametros.addValue("PI_FK_IDE_CATEGORIA_BAH", catalogoProductoBean.getIdCategoria(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_ENVASE", catalogoProductoBean.getIdEnvase(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_UNID_MEDIDA", catalogoProductoBean.getIdUnidadMedida(), Types.NUMERIC);
			input_objParametros.addValue("PI_OBSERVACION", catalogoProductoBean.getObservacion(), Types.VARCHAR);
			input_objParametros.addValue("PI_DIM_LARGO", catalogoProductoBean.getDimLargo(), Types.NUMERIC);
			input_objParametros.addValue("PI_DIM_ANCHO", catalogoProductoBean.getDimAncho(), Types.NUMERIC);
			input_objParametros.addValue("PI_DIM_ALTO", catalogoProductoBean.getDimAlto(), Types.NUMERIC);
			input_objParametros.addValue("PI_PESO_UNIT_NETO", catalogoProductoBean.getPesoNeto(), Types.NUMERIC);	
			input_objParametros.addValue("PI_PESO_UNIT_BRUTO", catalogoProductoBean.getPesoBruto(), Types.NUMERIC);
			input_objParametros.addValue("PI_FLAG_ACTIVO", catalogoProductoBean.getEstado(), Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", catalogoProductoBean.getUsuario(), Types.VARCHAR);
			input_objParametros.addValue("PI_FK_TIP_ENVASE_SEC", catalogoProductoBean.getTipoEnvaseSec(), Types.NUMERIC);
			input_objParametros.addValue("PI_UNIDADES_ENVASE_SEC", catalogoProductoBean.getUnidadEnvaseSec(), Types.NUMERIC);
			input_objParametros.addValue("PI_DESCR_ENVASE_SEC", catalogoProductoBean.getDescripcionEnvaseSec(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", "I", Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_PRODUCTO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PRODUCTO", new SqlParameter("PI_IDE_PRODUCTO", Types.NUMERIC));
			output_objParametros.put("PI_COD_PRODUCTO", new SqlParameter("PI_COD_PRODUCTO", Types.VARCHAR));
			output_objParametros.put("PI_NOM_PRODUCTO", new SqlParameter("PI_NOM_PRODUCTO", Types.VARCHAR));
			output_objParametros.put("PI_COD_SIGA", new SqlParameter("PI_COD_SIGA", Types.VARCHAR));
			output_objParametros.put("PI_FK_IDE_CATEGORIA_BAH", new SqlParameter("PI_FK_IDE_CATEGORIA_BAH", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_ENVASE", new SqlParameter("PI_FK_IDE_ENVASE", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_UNID_MEDIDA", new SqlParameter("PI_FK_IDE_UNID_MEDIDA", Types.NUMERIC));
			output_objParametros.put("PI_OBSERVACION", new SqlParameter("PI_OBSERVACION", Types.VARCHAR));
			output_objParametros.put("PI_DIM_LARGO", new SqlParameter("PI_DIM_LARGO", Types.NUMERIC));
			output_objParametros.put("PI_DIM_ANCHO", new SqlParameter("PI_DIM_ANCHO", Types.NUMERIC));
			output_objParametros.put("PI_DIM_ALTO", new SqlParameter("PI_DIM_ALTO", Types.NUMERIC));
			output_objParametros.put("PI_PESO_UNIT_NETO", new SqlParameter("PI_PESO_UNIT_NETO", Types.NUMERIC));
			output_objParametros.put("PI_PESO_UNIT_BRUTO", new SqlParameter("PI_PESO_UNIT_BRUTO", Types.NUMERIC));
			output_objParametros.put("PI_FLAG_ACTIVO", new SqlParameter("PI_FLAG_ACTIVO", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PI_FK_TIP_ENVASE_SEC", new SqlParameter("PI_FK_TIP_ENVASE_SEC", Types.NUMERIC));
			output_objParametros.put("PI_UNIDADES_ENVASE_SEC", new SqlParameter("PI_UNIDADES_ENVASE_SEC", Types.NUMERIC));
			output_objParametros.put("PI_DESCR_ENVASE_SEC", new SqlParameter("PI_DESCR_ENVASE_SEC", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_IDE_PRODUCTO", new SqlOutParameter("PO_IDE_PRODUCTO", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarCatalogoProducto] Ocurrio un error en la operacion del USP_INS_UPD_PRODUCTO : "+mensajeRespuesta);
    			throw new Exception();
    		}
		
			registroProducto.setIdProducto(((BigDecimal) out.get("PO_IDE_PRODUCTO")).intValue());
			registroProducto.setCodigoRespuesta(codigoRespuesta);
			registroProducto.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarCatalogoProducto] Fin ");
		return registroProducto;
	}
	
	@Override
	public CatalogoProductoBean actualizarCatalogoProducto(CatalogoProductoBean catalogoProductoBean) throws Exception {
		LOGGER.info("[actualizarCatalogoProducto] Inicio ");
		CatalogoProductoBean registroProducto= new CatalogoProductoBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_PRODUCTO", catalogoProductoBean.getIdProducto(), Types.NUMERIC);
			input_objParametros.addValue("PI_COD_PRODUCTO", catalogoProductoBean.getCodigoProducto(), Types.VARCHAR);
			input_objParametros.addValue("PI_NOM_PRODUCTO", catalogoProductoBean.getNombreProducto(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_SIGA", catalogoProductoBean.getCodigoSiga(), Types.VARCHAR);
			input_objParametros.addValue("PI_FK_IDE_CATEGORIA_BAH", catalogoProductoBean.getIdCategoria(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_ENVASE", catalogoProductoBean.getIdEnvase(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_UNID_MEDIDA", catalogoProductoBean.getIdUnidadMedida(), Types.NUMERIC);
			input_objParametros.addValue("PI_OBSERVACION", catalogoProductoBean.getObservacion(), Types.VARCHAR);
			input_objParametros.addValue("PI_DIM_LARGO", catalogoProductoBean.getDimLargo(), Types.NUMERIC);
			input_objParametros.addValue("PI_DIM_ANCHO", catalogoProductoBean.getDimAncho(), Types.NUMERIC);
			input_objParametros.addValue("PI_DIM_ALTO", catalogoProductoBean.getDimAlto(), Types.NUMERIC);
			input_objParametros.addValue("PI_PESO_UNIT_NETO", catalogoProductoBean.getPesoNeto(), Types.NUMERIC);	
			input_objParametros.addValue("PI_PESO_UNIT_BRUTO", catalogoProductoBean.getPesoBruto(), Types.NUMERIC);
			input_objParametros.addValue("PI_FLAG_ACTIVO", catalogoProductoBean.getEstado(), Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", catalogoProductoBean.getUsuario(), Types.VARCHAR);
			input_objParametros.addValue("PI_FK_TIP_ENVASE_SEC", catalogoProductoBean.getTipoEnvaseSec(), Types.NUMERIC);
			input_objParametros.addValue("PI_UNIDADES_ENVASE_SEC", catalogoProductoBean.getUnidadEnvaseSec(), Types.NUMERIC);
			input_objParametros.addValue("PI_DESCR_ENVASE_SEC", catalogoProductoBean.getDescripcionEnvaseSec(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", "U", Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_PRODUCTO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PRODUCTO", new SqlParameter("PI_IDE_PRODUCTO", Types.NUMERIC));
			output_objParametros.put("PI_COD_PRODUCTO", new SqlParameter("PI_COD_PRODUCTO", Types.VARCHAR));
			output_objParametros.put("PI_NOM_PRODUCTO", new SqlParameter("PI_NOM_PRODUCTO", Types.VARCHAR));
			output_objParametros.put("PI_COD_SIGA", new SqlParameter("PI_COD_SIGA", Types.VARCHAR));
			output_objParametros.put("PI_FK_IDE_CATEGORIA_BAH", new SqlParameter("PI_FK_IDE_CATEGORIA_BAH", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_ENVASE", new SqlParameter("PI_FK_IDE_ENVASE", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_UNID_MEDIDA", new SqlParameter("PI_FK_IDE_UNID_MEDIDA", Types.NUMERIC));
			output_objParametros.put("PI_OBSERVACION", new SqlParameter("PI_OBSERVACION", Types.VARCHAR));
			output_objParametros.put("PI_DIM_LARGO", new SqlParameter("PI_DIM_LARGO", Types.NUMERIC));
			output_objParametros.put("PI_DIM_ANCHO", new SqlParameter("PI_DIM_ANCHO", Types.NUMERIC));
			output_objParametros.put("PI_DIM_ALTO", new SqlParameter("PI_DIM_ALTO", Types.NUMERIC));
			output_objParametros.put("PI_PESO_UNIT_NETO", new SqlParameter("PI_PESO_UNIT_NETO", Types.NUMERIC));
			output_objParametros.put("PI_PESO_UNIT_BRUTO", new SqlParameter("PI_PESO_UNIT_BRUTO", Types.NUMERIC));
			output_objParametros.put("PI_FLAG_ACTIVO", new SqlParameter("PI_FLAG_ACTIVO", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PI_FK_TIP_ENVASE_SEC", new SqlParameter("PI_FK_TIP_ENVASE_SEC", Types.NUMERIC));
			output_objParametros.put("PI_UNIDADES_ENVASE_SEC", new SqlParameter("PI_UNIDADES_ENVASE_SEC", Types.NUMERIC));
			output_objParametros.put("PI_DESCR_ENVASE_SEC", new SqlParameter("PI_DESCR_ENVASE_SEC", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_IDE_PRODUCTO", new SqlOutParameter("PO_IDE_PRODUCTO", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[actualizarCatalogoProducto] Ocurrio un error en la operacion del USP_INS_UPD_PRODUCTO : "+mensajeRespuesta);
    			throw new Exception();
    		}
		
			registroProducto.setIdProducto(catalogoProductoBean.getIdProducto());
			registroProducto.setCodigoRespuesta(codigoRespuesta);
			registroProducto.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[actualizarCatalogoProducto] Fin ");
		return registroProducto;
	}
	
	@Override
	public CatalogoProductoBean generaCodigoProducto() throws Exception {
		LOGGER.info("[generaCodigoProducto] Inicio ");
		CatalogoProductoBean registroProducto= new CatalogoProductoBean();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();	
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_GENERA_COD_PRODUCTO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PO_COD_PRODUCTO", new SqlOutParameter("PO_COD_PRODUCTO", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[generaCodigoProducto] Ocurrio un error en la operacion del USP_SEL_GENERA_COD_PRODUCTO : "+mensajeRespuesta);
				throw new Exception();
			} else {
				registroProducto.setCodigoProducto(((String) out.get("PO_COD_PRODUCTO")).trim());
				
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[generaCodigoProducto] Fin ");
		return registroProducto;
	}
	
	@Override
	public List<ItemBean> listarGrupoProducto() throws Exception  {
		LOGGER.info("[listarGrupoProducto] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_GRUPO_PRODUCTO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PO_CURSOR_GRUPO", new SqlOutParameter("PO_CURSOR_GRUPO", OracleTypes.CURSOR, new GrupoProductoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				LOGGER.info("[listarGrupoProducto] Ocurrio un error en la operacion del USP_SEL_GRUPO_PRODUCTO");
				throw new Exception();
			} else {
				lista = (List<ItemBean>) out.get("PO_CURSOR_GRUPO");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarGrupoProducto] Fin ");
		return lista;
	}
	
	@Override
	public List<ItemBean> listarClaseProducto(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarClaseProducto] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_COD_GRUPO", itemBean.getVcodigo(), Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_CLASE_PRODUCTO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_GRUPO", new SqlParameter("PI_COD_GRUPO", Types.VARCHAR));
			output_objParametros.put("PO_CURSOR_CLASE", new SqlOutParameter("PO_CURSOR_CLASE", OracleTypes.CURSOR, new ClaseProductoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				LOGGER.info("[listarClaseProducto] Ocurrio un error en la operacion del USP_SEL_CLASE_PRODUCTO");
				throw new Exception();
			} else {
				lista = (List<ItemBean>) out.get("PO_CURSOR_CLASE");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarClaseProducto] Fin ");
		return lista;
	}
	 
	@Override
	public List<ItemBean> listarFamiliaProducto(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarFamiliaProducto] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			
			System.out.println("GRUPO: "+itemBean.getVcodigo()+" - "+itemBean.getVcodigoParam2());
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_COD_GRUPO", itemBean.getVcodigo(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_CLASE", itemBean.getVcodigoParam2(), Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_FAMILIA_PRODUCTO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_GRUPO", new SqlParameter("PI_COD_GRUPO", Types.VARCHAR));
			output_objParametros.put("PI_COD_CLASE", new SqlParameter("PI_COD_CLASE", Types.VARCHAR));
			output_objParametros.put("PO_CURSOR_FAMILIA", new SqlOutParameter("PO_CURSOR_FAMILIA", OracleTypes.CURSOR, new FamiliaProductoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				LOGGER.info("[listarFamiliaProducto] Ocurrio un error en la operacion del USP_SEL_FAMILIA_PRODUCTO");
				throw new Exception();
			} else {
				lista = (List<ItemBean>) out.get("PO_CURSOR_FAMILIA");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarFamiliaProducto] Fin ");
		return lista;
	}
	
	@Override
	public List<CatalogoProductoBean> listarSigaXNombre(CatalogoProductoBean catalogoProductoBean) throws Exception {
		LOGGER.info("[listarSigaXNombre] Inicio ");
		List<CatalogoProductoBean> lista = new ArrayList<CatalogoProductoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_NOMBRE_PRODUCTO", catalogoProductoBean.getNombreProducto(), Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_BUSCAR_PRODUCTO_X_NAME");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_NOMBRE_PRODUCTO", new SqlParameter("PI_NOMBRE_PRODUCTO", Types.VARCHAR));
			output_objParametros.put("PO_CURSOR_PRODUCTO", new SqlOutParameter("PO_CURSOR_PRODUCTO", OracleTypes.CURSOR, new ProductoSigaMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarSigaXNombre] Ocurrio un error en la operacion del USP_SEL_BUSCAR_PRODUCTO_X_NAME "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<CatalogoProductoBean>) out.get("PO_CURSOR_PRODUCTO");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarSigaXNombre] Fin ");
		return lista;
	}
	
	@Override
	public List<CatalogoProductoBean> listarSigaXGrupo(CatalogoProductoBean catalogoProductoBean) throws Exception {
		LOGGER.info("[listarSigaXGrupo] Inicio ");
		List<CatalogoProductoBean> lista = new ArrayList<CatalogoProductoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_GRUPO_PRODUCTO", catalogoProductoBean.getGrupoBien(), Types.VARCHAR);
			input_objParametros.addValue("PI_CLASE_PRODUCTO", catalogoProductoBean.getClaseBien(), Types.VARCHAR);
			input_objParametros.addValue("PI_FAMILIA_PRODUCTO", catalogoProductoBean.getFamiliaBien(), Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_BUSCAR_PRODUCTO_X_FAM");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_GRUPO_PRODUCTO", new SqlParameter("PI_GRUPO_PRODUCTO", Types.VARCHAR));
			output_objParametros.put("PI_CLASE_PRODUCTO", new SqlParameter("PI_CLASE_PRODUCTO", Types.VARCHAR));
			output_objParametros.put("PI_FAMILIA_PRODUCTO", new SqlParameter("PI_FAMILIA_PRODUCTO", Types.VARCHAR));
			output_objParametros.put("PO_CURSOR_PRODUCTO", new SqlOutParameter("PO_CURSOR_PRODUCTO", OracleTypes.CURSOR, new ProductoSigaMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarSigaXGrupo] Ocurrio un error en la operacion del USP_SEL_BUSCAR_PRODUCTO_X_FAM "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<CatalogoProductoBean>) out.get("PO_CURSOR_PRODUCTO");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarSigaXGrupo] Fin ");
		return lista;
	}
	
	@Override
	public CatalogoProductoBean insertarProductoSiga(CatalogoProductoBean catalogoProductoBean) throws Exception {
		LOGGER.info("[insertarProductoSiga] Inicio ");
		CatalogoProductoBean registroProducto= new CatalogoProductoBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_CODIGO_SIGA", catalogoProductoBean.getCodigoSiga(), Types.VARCHAR);
			input_objParametros.addValue("PI_NOMBRE_PRODUCTO", catalogoProductoBean.getNombreProducto(), Types.VARCHAR);
			input_objParametros.addValue("PI_GRUPO_BIEN", catalogoProductoBean.getGrupoBien(), Types.VARCHAR);
			input_objParametros.addValue("PI_CLASE_BIEN", catalogoProductoBean.getClaseBien(), Types.VARCHAR);
			input_objParametros.addValue("PI_FAMILIA_BIEN", catalogoProductoBean.getFamiliaBien(), Types.VARCHAR);
			input_objParametros.addValue("PI_ITEM_BIEN", catalogoProductoBean.getItemBien(), Types.VARCHAR);
			input_objParametros.addValue("PI_ID_CATEGORIA", catalogoProductoBean.getIdCategoria(), Types.NUMERIC);
			input_objParametros.addValue("PI_ESTADO", catalogoProductoBean.getEstado(), Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", catalogoProductoBean.getUsuario(), Types.VARCHAR);

			System.out.println("PI_CODIGO_SIGA "+catalogoProductoBean.getCodigoSiga());
			System.out.println("PI_NOMBRE_PRODUCTO "+catalogoProductoBean.getNombreProducto());
			System.out.println("PI_GRUPO_BIEN "+catalogoProductoBean.getGrupoBien());
			System.out.println("PI_CLASE_BIEN "+catalogoProductoBean.getClaseBien());
			System.out.println("PI_FAMILIA_BIEN "+catalogoProductoBean.getFamiliaBien());
			System.out.println("PI_ITEM_BIEN "+catalogoProductoBean.getItemBien());
			System.out.println("PI_ID_CATEGORIA "+catalogoProductoBean.getIdCategoria());
			System.out.println("PI_ESTADO "+catalogoProductoBean.getEstado());
			System.out.println("PI_USERNAME "+catalogoProductoBean.getUsuario());
			
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_PRODUCTO_DEL_SIGA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_CODIGO_SIGA", new SqlParameter("PI_CODIGO_SIGA", Types.VARCHAR));
			output_objParametros.put("PI_NOMBRE_PRODUCTO", new SqlParameter("PI_NOMBRE_PRODUCTO", Types.VARCHAR));
			output_objParametros.put("PI_GRUPO_BIEN", new SqlParameter("PI_GRUPO_BIEN", Types.VARCHAR));
			output_objParametros.put("PI_CLASE_BIEN", new SqlParameter("PI_CLASE_BIEN", Types.VARCHAR));
			output_objParametros.put("PI_FAMILIA_BIEN", new SqlParameter("PI_FAMILIA_BIEN", Types.VARCHAR));
			output_objParametros.put("PI_ITEM_BIEN", new SqlParameter("PI_ITEM_BIEN", Types.VARCHAR));
			output_objParametros.put("PI_ID_CATEGORIA", new SqlParameter("PI_ID_CATEGORIA", Types.NUMERIC));
			output_objParametros.put("PI_ESTADO", new SqlParameter("PI_ESTADO", Types.VARCHAR));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_IDE_PRODUCTO", new SqlOutParameter("PO_IDE_PRODUCTO", Types.VARCHAR));
			output_objParametros.put("PO_COD_PRODUCTO", new SqlOutParameter("PO_COD_PRODUCTO", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarProductoSiga] Ocurrio un error en la operacion del USP_INS_PRODUCTO_DEL_SIGA : "+mensajeRespuesta);
    			throw new Exception();
    		}
		
//			registroProducto.setIdProducto(((BigDecimal) out.get("PO_IDE_PRODUCTO")).intValue());
			registroProducto.setCodigoRespuesta(codigoRespuesta);
			registroProducto.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarProductoSiga] Fin ");
		return registroProducto;
	}
	
	@Override
	public List<EstadosXUsuarioBean> listarEstadosDonacion(Integer idUsuario) throws Exception {
		LOGGER.info("[listarEstadosDonacion] Inicio ");
		List<EstadosXUsuarioBean> lista = new ArrayList<EstadosXUsuarioBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			
			input_objParametros.addValue("PI_USERNAME", idUsuario, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_ESTADO_DONACION_X_USER");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new EstadoXUsuarioMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarEstadosDonacion] Ocurrio un error en la operacion del USP_SEL_ESTADO_DONACION_X_USER : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<EstadosXUsuarioBean>) out.get("PO_LR_RECORDSET");
				
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarEstadosDonacion] Fin ");
		return lista;
	}
	
	@Override
	public List<EstadosXUsuarioBean> listarEstadosProgramacion(Integer idUsuario) throws Exception {
		LOGGER.info("[listarEstadosProgramacion] Inicio ");
		List<EstadosXUsuarioBean> lista = new ArrayList<EstadosXUsuarioBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			
			input_objParametros.addValue("PI_USERNAME", idUsuario, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_ESTADO_PROG_X_USER");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new EstadoXUsuarioMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarEstadosProgramacion] Ocurrio un error en la operacion del USP_SEL_ESTADO_PROG_X_USER : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<EstadosXUsuarioBean>) out.get("PO_LR_RECORDSET");
				
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarEstadosProgramacion] Fin ");
		return lista;
	}
	
	@Override
	public List<EstadosXUsuarioBean> listarEstadosInventario(Integer idUsuario) throws Exception {
		LOGGER.info("[listarEstadosInventario] Inicio ");
		List<EstadosXUsuarioBean> lista = new ArrayList<EstadosXUsuarioBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			
			input_objParametros.addValue("PI_USERNAME", idUsuario, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_ESTADO_INVE_X_USER");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new EstadoXUsuarioMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarEstadosInventario] Ocurrio un error en la operacion del USP_SEL_ESTADO_INVE_X_USER : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<EstadosXUsuarioBean>) out.get("PO_LR_RECORDSET");
				
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarEstadosInventario] Fin ");
		return lista;
	}
	
	@Override
	public EstadosXUsuarioBean insertarDonacionXUsuario(EstadosXUsuarioBean estadosXUsuarioBean) throws Exception {
		LOGGER.info("[insertarDonacionXUsuario] Inicio ");
		EstadosXUsuarioBean registro= new EstadosXUsuarioBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ID_ESTADO", estadosXUsuarioBean.getIdEstado(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_USUARIO", estadosXUsuarioBean.getIdUsuario(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", estadosXUsuarioBean.getUsuario(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_DON_POR_USER");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_ESTADO", new SqlParameter("PI_ID_ESTADO", Types.NUMERIC));
			output_objParametros.put("PI_IDE_USUARIO", new SqlParameter("PI_IDE_USUARIO", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarDonacionXUsuario] Ocurrio un error en la operacion del USP_INS_DON_POR_USER : "+mensajeRespuesta);
    			throw new Exception();
    		}
	
			registro.setCodigoRespuesta(codigoRespuesta);
			registro.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarDonacionXUsuario] Fin ");
		return registro;
	}
	
	@Override
	public EstadosXUsuarioBean eliminarDonacionXUsuario(EstadosXUsuarioBean estadosXUsuarioBean) throws Exception {
		LOGGER.info("[eliminarDonacionXUsuario] Inicio ");
		EstadosXUsuarioBean registro= new EstadosXUsuarioBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ID_ESTADO", estadosXUsuarioBean.getIdEstado(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_USUARIO", estadosXUsuarioBean.getIdUsuario(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", estadosXUsuarioBean.getUsuario(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_DEL_DON_POR_USER");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_ESTADO", new SqlParameter("PI_ID_ESTADO", Types.NUMERIC));
			output_objParametros.put("PI_IDE_USUARIO", new SqlParameter("PI_IDE_USUARIO", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[eliminarDonacionXUsuario] Ocurrio un error en la operacion del USP_DEL_DON_POR_USER : "+mensajeRespuesta);
    			throw new Exception();
    		}
	
			registro.setCodigoRespuesta(codigoRespuesta);
			registro.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[eliminarDonacionXUsuario] Fin ");
		return registro;
	}
	
	@Override
	public EstadosXUsuarioBean insertarProgramacionXUsuario(EstadosXUsuarioBean estadosXUsuarioBean) throws Exception {
		LOGGER.info("[insertarProgramacionXUsuario] Inicio ");
		EstadosXUsuarioBean registro= new EstadosXUsuarioBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ID_ESTADO", estadosXUsuarioBean.getIdEstado(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_USUARIO", estadosXUsuarioBean.getIdUsuario(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", estadosXUsuarioBean.getUsuario(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_PRO_POR_USER");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_ESTADO", new SqlParameter("PI_ID_ESTADO", Types.NUMERIC));
			output_objParametros.put("PI_IDE_USUARIO", new SqlParameter("PI_IDE_USUARIO", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarProgramacionXUsuario] Ocurrio un error en la operacion del USP_INS_PRO_POR_USER : "+mensajeRespuesta);
    			throw new Exception();
    		}
	
			registro.setCodigoRespuesta(codigoRespuesta);
			registro.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarProgramacionXUsuario] Fin ");
		return registro;
	}
	
	@Override
	public EstadosXUsuarioBean eliminarProgramacionXUsuario(EstadosXUsuarioBean estadosXUsuarioBean) throws Exception {
		LOGGER.info("[eliminarProgramacionXUsuario] Inicio ");
		EstadosXUsuarioBean registro= new EstadosXUsuarioBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ID_ESTADO", estadosXUsuarioBean.getIdEstado(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_USUARIO", estadosXUsuarioBean.getIdUsuario(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", estadosXUsuarioBean.getUsuario(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_DEL_PRO_POR_USER");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_ESTADO", new SqlParameter("PI_ID_ESTADO", Types.NUMERIC));
			output_objParametros.put("PI_IDE_USUARIO", new SqlParameter("PI_IDE_USUARIO", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[eliminarProgramacionXUsuario] Ocurrio un error en la operacion del USP_DEL_PRO_POR_USER : "+mensajeRespuesta);
    			throw new Exception();
    		}
	
			registro.setCodigoRespuesta(codigoRespuesta);
			registro.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[eliminarProgramacionXUsuario] Fin ");
		return registro;
	}
	
	@Override
	public EstadosXUsuarioBean insertarInventarioXUsuario(EstadosXUsuarioBean estadosXUsuarioBean) throws Exception {
		LOGGER.info("[insertarInventarioXUsuario] Inicio ");
		EstadosXUsuarioBean registro= new EstadosXUsuarioBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ID_ESTADO", estadosXUsuarioBean.getIdEstado(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_USUARIO", estadosXUsuarioBean.getIdUsuario(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", estadosXUsuarioBean.getUsuario(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_INV_POR_USER");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_ESTADO", new SqlParameter("PI_ID_ESTADO", Types.NUMERIC));
			output_objParametros.put("PI_IDE_USUARIO", new SqlParameter("PI_IDE_USUARIO", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarInventarioXUsuario] Ocurrio un error en la operacion del USP_INS_INV_POR_USER : "+mensajeRespuesta);
    			throw new Exception();
    		}
	
			registro.setCodigoRespuesta(codigoRespuesta);
			registro.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarInventarioXUsuario] Fin ");
		return registro;
	}
	
	@Override
	public EstadosXUsuarioBean eliminarInventarioXUsuario(EstadosXUsuarioBean estadosXUsuarioBean) throws Exception {
		LOGGER.info("[eliminarInventarioXUsuario] Inicio ");
		EstadosXUsuarioBean registro= new EstadosXUsuarioBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_ID_ESTADO", estadosXUsuarioBean.getIdEstado(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_USUARIO", estadosXUsuarioBean.getIdUsuario(), Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", estadosXUsuarioBean.getUsuario(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_DEL_INV_POR_USER");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_ESTADO", new SqlParameter("PI_ID_ESTADO", Types.NUMERIC));
			output_objParametros.put("PI_IDE_USUARIO", new SqlParameter("PI_IDE_USUARIO", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[eliminarInventarioXUsuario] Ocurrio un error en la operacion del USP_DEL_INV_POR_USER : "+mensajeRespuesta);
    			throw new Exception();
    		}
	
			registro.setCodigoRespuesta(codigoRespuesta);
			registro.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[eliminarInventarioXUsuario] Fin ");
		return registro;
	}
	

	@Override
	public CierreStockBean mesTrabajoActivo(Integer idAlmacen, String tipo) throws Exception {
		LOGGER.info("[mesTrabajoActivo] Inicio ");
		CierreStockBean lista = new CierreStockBean();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("pi_IDE_ALMACEN", idAlmacen, Types.NUMERIC);
			input_objParametros.addValue("pi_TIPO_ORIGEN", tipo, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_MES_TRABAJO_ACTIVO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("pi_IDE_ALMACEN", new SqlParameter("pi_IDE_ALMACEN", Types.NUMERIC));
			output_objParametros.put("pi_TIPO_ORIGEN", new SqlParameter("pi_TIPO_ORIGEN", Types.VARCHAR));
			output_objParametros.put("po_Lr_Recordset", new SqlOutParameter("po_Lr_Recordset", OracleTypes.CURSOR, new MesTrabajoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				LOGGER.info("[mesTrabajoActivo] Ocurrio un error en la operacion del USP_SEL_MES_TRABAJO_ACTIVO");
				throw new Exception();
			} else {
				List<CierreStockBean> listaDatos = (List<CierreStockBean>) out.get("po_Lr_Recordset");
				if (!Utils.isEmpty(listaDatos)) {
					lista = listaDatos.get(0);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[mesTrabajoActivo] Fin ");
		return lista;
	}
	
	@Override
	public List<CorreoBean> listarCorreos(CorreoBean correoBean) throws Exception {
		LOGGER.info("[listarCorreos] Inicio ");
		List<CorreoBean> lista = new ArrayList<CorreoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_NOMBRE_MODULO", correoBean.getNombre(), Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_DESTINATARIOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_NOMBRE_MODULO", new SqlParameter("PI_NOMBRE_MODULO", Types.VARCHAR));
			output_objParametros.put("PO_CURSOR_DESTINATARIOS", new SqlOutParameter("PO_CURSOR_DESTINATARIOS", OracleTypes.CURSOR, new CorreosDestinatariosMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				LOGGER.info("[listarCorreos] Ocurrio un error en la operacion del USP_SEL_LISTAR_DESTINATARIOS");
				throw new Exception();
			} else {
				 lista = (List<CorreoBean>) out.get("PO_CURSOR_DESTINATARIOS");

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarCorreos] Fin ");
		return lista;
	}
	
	@Override
	public List<ItemBean> listarEstadosModulo(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarEstadosModulo] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_NOMBRE_MODULO", itemBean.getDescripcion(), Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_ESTADO_X_MODULO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_NOMBRE_MODULO", new SqlParameter("PI_NOMBRE_MODULO", Types.VARCHAR));
			output_objParametros.put("PO_CURSOR_ESTADOS", new SqlOutParameter("PO_CURSOR_ESTADOS", OracleTypes.CURSOR, new EstadosModuloMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				LOGGER.info("[listarEstadosModulo] Ocurrio un error en la operacion del USP_SEL_LISTAR_ESTADO_X_MODULO");
				throw new Exception();
			} else {
				 lista = (List<ItemBean>) out.get("PO_CURSOR_ESTADOS");

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarEstadosModulo] Fin ");
		return lista;
	}
	
	@Override
	public CorreoBean insertarDestinatario(CorreoBean correoBean) throws Exception {
		LOGGER.info("[insertarDestinatario] Inicio ");
		CorreoBean registroCorreo= new CorreoBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_NOMBRE_MODULO", correoBean.getNombre(), Types.VARCHAR);
			input_objParametros.addValue("PI_FK_IDE_ESTADO", correoBean.getIdEstado(), Types.NUMERIC);
			input_objParametros.addValue("PI_FK_IDE_USER_DESTINATARIO", correoBean.getIdUsuario(), Types.NUMERIC);
			input_objParametros.addValue("PI_FLG_ACTIVO", correoBean.getFlag(), Types.VARCHAR);
			input_objParametros.addValue("PI_USERNAME", correoBean.getUsuario(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", correoBean.getControl(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_DESTINATARIO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_NOMBRE_MODULO", new SqlParameter("PI_NOMBRE_MODULO", Types.VARCHAR));
			output_objParametros.put("PI_FK_IDE_ESTADO", new SqlParameter("PI_FK_IDE_ESTADO", Types.NUMERIC));
			output_objParametros.put("PI_FK_IDE_USER_DESTINATARIO", new SqlParameter("PI_FK_IDE_USER_DESTINATARIO", Types.NUMERIC));
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
				LOGGER.info("[insertarDestinatario] Ocurrio un error en la operacion del USP_INS_UPD_DESTINATARIO : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroCorreo.setCodigoRespuesta(codigoRespuesta);
			registroCorreo.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarDestinatario] Fin ");
		return registroCorreo;
	}
	
	@Override
	public List<ItemBean> listarHijosMenu(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarHijosMenu] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_MENU", itemBean.getIcodigo(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_MENU_HIJOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_MENU", new SqlParameter("PI_ID_MENU", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new HijosXMenuMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				LOGGER.info("[listarHijosMenu] Ocurrio un error en la operacion del USP_SEL_LISTAR_MENU_HIJOS");
				throw new Exception();
			} else {
				 lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarHijosMenu] Fin ");
		return lista;
	}
	
	@Override
	public List<ItemBean> listarPadresMenu(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarPadresMenu] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_MENU", itemBean.getIcodigo(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_MENU_PADRES");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_MENU", new SqlParameter("PI_ID_MENU", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new HijosXMenuMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				LOGGER.info("[listarPadresMenu] Ocurrio un error en la operacion del USP_SEL_LISTAR_MENU_PADRES");
				throw new Exception();
			} else {
				 lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarPadresMenu] Fin ");
		return lista;
	}
	
	@Override
	public List<RolMenuBean> mostrarMenuUsuario(Integer idUsuario) throws Exception {
		LOGGER.info("[mostrarMenuUsuario] Inicio ");
		List<RolMenuBean> lista = new ArrayList<RolMenuBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			
			input_objParametros.addValue("PI_ID_USER", idUsuario, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_DESPLEGAR_MENU_X_USER");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_USER", new SqlParameter("PI_ID_USER", Types.NUMERIC));
			output_objParametros.put("PO_CURSOR_MENU", new SqlOutParameter("PO_CURSOR_MENU", OracleTypes.CURSOR, new MenuUsuario2Mapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[mostrarMenuUsuario] Ocurrio un error en la operacion del USP_SEL_DESPLEGAR_MENU_X_USER : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<RolMenuBean>) out.get("PO_CURSOR_MENU");
				
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[mostrarMenuUsuario] Fin ");
		return lista;
	}
	
	@Override
	public List<UsuarioBean> listarUsuariosCorreo() throws Exception  {
		LOGGER.info("[listarUsuariosCorreo] Inicio ");
		List<UsuarioBean> lista = new ArrayList<UsuarioBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_LISTAR_DESTINATARIOS_NEW");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PO_CURSOR_USUARIO", new SqlOutParameter("PO_CURSOR_USUARIO", OracleTypes.CURSOR, new UsuariosCorreoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));			
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				LOGGER.info("[listarUsuariosCorreo] Ocurrio un error en la operacion del USP_LISTAR_DESTINATARIOS_NEW");
				throw new Exception();
			} else {
				lista = (List<UsuarioBean>) out.get("PO_CURSOR_USUARIO");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarUsuariosCorreo] Fin ");
		return lista;
	}
	
	@Override
	public MaeCorrelativoBean insertarActualizarCorrelativo(MaeCorrelativoBean maeCorrelativoBean, String control) throws Exception {
		LOGGER.info("[insertarActualizarCorrelativo] Inicio ");
		MaeCorrelativoBean registroCorrelativo= new MaeCorrelativoBean();
		try {			
			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_CORRELATIVO", maeCorrelativoBean.getIdCorrelativo(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_ALMACEN", maeCorrelativoBean.getIdAlmacen(), Types.NUMERIC);
			input_objParametros.addValue("PI_NRO_SERIE", maeCorrelativoBean.getNroSerie(), Types.VARCHAR);
			input_objParametros.addValue("PI_NRO_CORR_INI", maeCorrelativoBean.getNroInicio(), Types.VARCHAR);
			input_objParametros.addValue("PI_NRO_CORR_FIN", maeCorrelativoBean.getNroFin(), Types.VARCHAR);
			input_objParametros.addValue("PI_NRO_CORR_ACTUAL", maeCorrelativoBean.getNroActual(), Types.VARCHAR);
			input_objParametros.addValue("PI_FLG_ACTIVO", maeCorrelativoBean.getFlagActivo(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_TIPO", maeCorrelativoBean.getCodTipo(), Types.VARCHAR);
			//input_objParametros.addValue("PI_FLG_INI_GUIA_REM", maeCorrelativoBean.getActivarFormato(), Types.VARCHAR);
			input_objParametros.addValue("PI_NOM_IMPRENTA", maeCorrelativoBean.getNombreImprenta(), Types.VARCHAR);
			input_objParametros.addValue("PI_RUC_IMPRENTA", maeCorrelativoBean.getRucImprenta(), Types.VARCHAR);
			input_objParametros.addValue("PI_NRO_AUTORIZACION", maeCorrelativoBean.getNroAutorizacion(), Types.VARCHAR);
			input_objParametros.addValue("PI_FEC_INICIO", DateUtil.obtenerFechaHoraParseada(maeCorrelativoBean.getFechaInicio()), Types.DATE);
			input_objParametros.addValue("PI_USERNAME", maeCorrelativoBean.getUsuario(), Types.VARCHAR);
			input_objParametros.addValue("PI_CONTROL", maeCorrelativoBean.getControl(), Types.VARCHAR);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_UPD_CORRELATIVO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_CORRELATIVO", new SqlParameter("PI_IDE_CORRELATIVO", Types.NUMERIC));
			output_objParametros.put("PI_IDE_ALMACEN", new SqlParameter("PI_IDE_ALMACEN", Types.VARCHAR));
			output_objParametros.put("PI_NRO_SERIE", new SqlParameter("PI_NRO_SERIE", Types.VARCHAR));
			output_objParametros.put("PI_NRO_CORR_INI", new SqlParameter("PI_NRO_CORR_INI", Types.VARCHAR));
			output_objParametros.put("PI_NRO_CORR_FIN", new SqlParameter("PI_NRO_CORR_FIN", Types.VARCHAR));
			output_objParametros.put("PI_NRO_CORR_ACTUAL", new SqlParameter("PI_NRO_CORR_ACTUAL", Types.VARCHAR));
			output_objParametros.put("PI_FLG_ACTIVO", new SqlParameter("PI_FLG_ACTIVO", Types.VARCHAR));
			output_objParametros.put("PI_COD_TIPO", new SqlParameter("PI_COD_TIPO", Types.VARCHAR));
//			output_objParametros.put("PI_FLG_INI_GUIA_REM", new SqlParameter("PI_FLG_INI_GUIA_REM", Types.VARCHAR));
			output_objParametros.put("PI_NOM_IMPRENTA", new SqlParameter("PI_NOM_IMPRENTA", Types.VARCHAR));
			output_objParametros.put("PI_RUC_IMPRENTA", new SqlParameter("PI_RUC_IMPRENTA", Types.VARCHAR));
			output_objParametros.put("PI_NRO_AUTORIZACION", new SqlParameter("PI_NRO_AUTORIZACION", Types.VARCHAR));
			output_objParametros.put("PI_FEC_INICIO", new SqlParameter("PI_FEC_INICIO", Types.DATE));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PI_CONTROL", new SqlParameter("PI_CONTROL", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[insertarActualizarCorrelativo] Ocurrio un error en la operacion del USP_INS_UPD_CORRELATIVO : "+mensajeRespuesta);
    			throw new Exception();
    		}
			registroCorrelativo.setCodigoRespuesta(codigoRespuesta);
			registroCorrelativo.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[insertarActualizarCorrelativo] Fin ");
		return registroCorrelativo;
	}
	
	@Override
	public List<MaeCorrelativoBean> listarCorrelativo(Integer idAlmacen) throws Exception {
		LOGGER.info("[listarCorrelativo] Inicio ");
		List<MaeCorrelativoBean> lista = new ArrayList<MaeCorrelativoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			System.out.println("ALMACEN "+idAlmacen);
			input_objParametros.addValue("PI_IDE_ALMACEN", idAlmacen, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_LISTAR_CORR_ALMACEN");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_ALMACEN", new SqlParameter("PI_IDE_ALMACEN", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new MaeCorrelativoMapper()));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarCorrelativo] Ocurrio un error en la operacion del USP_SEL_LISTAR_CORR_ALMACEN : "+mensajeRespuesta);
				throw new Exception();
			} else {
				lista = (List<MaeCorrelativoBean>) out.get("PO_LR_RECORDSET");
				
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarCorrelativo] Fin ");
		return lista;
	}
	
	@Override
	public ItemBean valoresIniciales(Integer idAlmacen, String usua) throws Exception {
		LOGGER.info("[valoresIniciales] Inicio ");
		ItemBean lista = new ItemBean();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
		
			input_objParametros.addValue("PI_IDE_ALMACEN", idAlmacen, Types.NUMERIC);
			input_objParametros.addValue("PI_USERNAME", idAlmacen, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_INS_VALORES_INICIALES");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_ALMACEN", new SqlParameter("PI_IDE_ALMACEN", Types.NUMERIC));
			output_objParametros.put("PI_USERNAME", new SqlParameter("PI_USERNAME", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));

			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[valoresIniciales] Ocurrio un error en la operacion del USP_INS_VALORES_INICIALES : "+mensajeRespuesta);
				throw new Exception();
			} else {
				
				LOGGER.info("[valoresIniciales] SUCESS - USP_INS_VALORES_INICIALES : ");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[valoresIniciales] Fin ");
		return lista;
	}
	
//	@Override
//	public MaeCorrelativoBean validaVigencia(Integer idAlmacen) throws Exception {
//		System.out.println("xxxxxxxxxxxxxxxxxx");
//		LOGGER.info("[validaVigencia] Inicio ");
//		MaeCorrelativoBean lista = null;
//		try {
//			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
//
//			input_objParametros.addValue("PI_IDE_ALMACEN", idAlmacen, Types.NUMERIC);
//			System.out.println("ALMACEN "+idAlmacen);
//			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
//			objJdbcCall.withoutProcedureColumnMetaDataAccess();
//			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
//			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
//			objJdbcCall.withProcedureName("USP_VALIDA_VIGENCIA_GUIA_REMI");
//
//			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
//			output_objParametros.put("PI_IDE_ALMACEN", new SqlParameter("PI_IDE_ALMACEN", Types.NUMERIC));
//			output_objParametros.put("PO_VIGENTE", new SqlOutParameter("PO_VIGENTE", Types.VARCHAR));
//			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
//			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
//			
//			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
//
//			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
//			
//			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
//			String codigoVIGENCIA = (String) out.get("PO_VIGENTE");
//			LOGGER.error("CODIGO: "+codigoRespuesta+ "VIG: "+codigoVIGENCIA);
//			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
//				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
//				LOGGER.info("[validaVigencia] Ocurrio un error en la operacion del USP_VALIDA_VIGENCIA_GUIA_REMI : "+mensajeRespuesta);
//				throw new Exception();
//			} else {	
//				LOGGER.error("VIGENTE: "+(String) out.get("PO_VIGENTE"));
//				lista.setVigencia((String) out.get("PO_VIGENTE"));
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage(), e);
//			throw new Exception();
//		}		
//		LOGGER.info("[validaVigencia] Fin ");
//		LOGGER.info("[validaVigencia] VIGENCIA "+lista.getVigencia());
//		return lista;
//	}
	
	@Override
	public MaeCorrelativoBean validaVigencia(Integer idAlmacen) throws Exception {
		LOGGER.info("[validaVigencia] Inicio ");
		MaeCorrelativoBean almacen = new MaeCorrelativoBean();
		try {			
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_IDE_ALMACEN", idAlmacen, Types.NUMERIC);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_ADMINISTRACION);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_VALIDA_VIGENCIA_GUIA_REMI");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_ALMACEN", new SqlParameter("PI_IDE_ALMACEN", Types.NUMERIC));
			output_objParametros.put("PO_VIGENTE", new SqlOutParameter("PO_VIGENTE", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[validaVigencia] Ocurrio un error en la operacion del USP_VALIDA_VIGENCIA_GUIA_REMI : "+mensajeRespuesta);
    			throw new Exception();
    		}

			almacen.setVigencia(((String) out.get("PO_VIGENTE")).trim());
			almacen.setCodigoRespuesta(codigoRespuesta);
			almacen.setMensajeRespuesta((String) out.get("PO_MENSAJE_RESPUESTA"));

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[validaVigencia] Fin ");
		return almacen;
	}

}
