package pe.com.sigbah.dao.impl;

import java.io.Serializable;
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
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.ProductoBean;
import pe.com.sigbah.common.bean.UbigeoBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.SpringUtil;
import pe.com.sigbah.common.util.Utils;
import pe.com.sigbah.dao.GeneralDao;
import pe.com.sigbah.mapper.AlmacenExternoLocalMapper;
import pe.com.sigbah.mapper.AlmacenExternoRegionMapper;
import pe.com.sigbah.mapper.AlmacenMapper;
import pe.com.sigbah.mapper.CategoriaMapper;
import pe.com.sigbah.mapper.CatologoProductosMapper;
import pe.com.sigbah.mapper.ChoferMapper;
import pe.com.sigbah.mapper.DdiMapper;
import pe.com.sigbah.mapper.DepartamentoMapper;
import pe.com.sigbah.mapper.DistritoMapper;
import pe.com.sigbah.mapper.EmpresaTransporteMapper;
import pe.com.sigbah.mapper.EnvaseMapper;
import pe.com.sigbah.mapper.EstadoDonacionMapper;
import pe.com.sigbah.mapper.EstadoMapper;
import pe.com.sigbah.mapper.FenomenoMapper;
import pe.com.sigbah.mapper.FuenteFinanciamientoMapper;
import pe.com.sigbah.mapper.ListaProgramacionMapper;
import pe.com.sigbah.mapper.MaestroDonanteMapper;
import pe.com.sigbah.mapper.MarcaMapper;
import pe.com.sigbah.mapper.MedioTransporteMapper;
import pe.com.sigbah.mapper.MesMapper;
import pe.com.sigbah.mapper.ModAlmacenMapper;
import pe.com.sigbah.mapper.PersonalExternoLocalMapper;
import pe.com.sigbah.mapper.PersonalExternoRegionMapper;
import pe.com.sigbah.mapper.PersonalMapper;
import pe.com.sigbah.mapper.ProveedorMapper;
import pe.com.sigbah.mapper.ProvinciaMapper;
import pe.com.sigbah.mapper.RegionMapper;
import pe.com.sigbah.mapper.TipoCamionMapper;
import pe.com.sigbah.mapper.TipoControlCalidadMapper;
import pe.com.sigbah.mapper.TipoDocumentoMapper;
import pe.com.sigbah.mapper.TipoMovimientoMapper;
import pe.com.sigbah.mapper.UnidadMedidaMapper;
import pe.com.sigbah.mapper.DeeMapper;
import pe.com.sigbah.mapper.PaisMapper;
import pe.com.sigbah.mapper.ParametroMapper;
import pe.com.sigbah.mapper.OficinaMapper;
import pe.com.sigbah.mapper.PersonalOficinaMapper;
import pe.com.sigbah.mapper.MonedaMapper;

/**
 * @className: GeneralDaoImpl.java
 * @description: Clase que contiene el consumo de los procedimientos del package BAH_PKG_GENERAL.
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class GeneralDaoImpl extends JdbcDaoSupport implements GeneralDao, Serializable {

	private static final long serialVersionUID = 1L;
	private transient final Log LOGGER = LogFactory.getLog(getClass());
	
	private SimpleJdbcCall objJdbcCall;
	
	/**
	 * @param dataSource
	 */
	@Autowired
	public GeneralDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarDepartamentos(pe.com.sigbah.common.bean.UbigeoBean)
	 */
	@Override
	public List<UbigeoBean> listarDepartamentos(UbigeoBean ubigeoBean) throws Exception {
		LOGGER.info("[listarDepartamentos] Inicio ");
		List<UbigeoBean> lista = new ArrayList<UbigeoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			String parametro = Utils.getParam(ubigeoBean.getCoddpto());
			input_objParametros.addValue("PI_COD_DEPARTAMENTO", parametro, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_DEPARTAMENTO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_DEPARTAMENTO", new SqlParameter("PI_COD_DEPARTAMENTO", Types.VARCHAR));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new DepartamentoMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<UbigeoBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarDepartamentos] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarProvincia(pe.com.sigbah.common.bean.UbigeoBean)
	 */
	@Override
	public List<UbigeoBean> listarProvincia(UbigeoBean ubigeoBean) throws Exception {
		LOGGER.info("[listarProvincia] Inicio ");
		List<UbigeoBean> lista = new ArrayList<UbigeoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			String parametro = Utils.getParam(ubigeoBean.getCodprov());
			input_objParametros.addValue("PI_COD_DEPARTAMENTO", ubigeoBean.getCoddpto(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_PROVINCIA", parametro, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_PROVINCIA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_DEPARTAMENTO", new SqlParameter("PI_COD_DEPARTAMENTO", Types.VARCHAR));
			output_objParametros.put("PI_COD_PROVINCIA", new SqlParameter("PI_COD_PROVINCIA", Types.VARCHAR));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new ProvinciaMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<UbigeoBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarProvincia] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarDistrito(pe.com.sigbah.common.bean.UbigeoBean)
	 */
	@Override
	public List<UbigeoBean> listarDistrito(UbigeoBean ubigeoBean) throws Exception {
		LOGGER.info("[listarDistrito] Inicio ");
		List<UbigeoBean> lista = new ArrayList<UbigeoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			String parametro = Utils.getParam(ubigeoBean.getCoddist());
			input_objParametros.addValue("PI_COD_DEPARTAMENTO", ubigeoBean.getCoddpto(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_PROVINCIA", ubigeoBean.getCodprov(), Types.VARCHAR);
			input_objParametros.addValue("PI_COD_DISTRITO", parametro, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_DISTRITO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_DEPARTAMENTO", new SqlParameter("PI_COD_DEPARTAMENTO", Types.VARCHAR));
			output_objParametros.put("PI_COD_PROVINCIA", new SqlParameter("PI_COD_PROVINCIA", Types.VARCHAR));
			output_objParametros.put("PI_COD_DISTRITO", new SqlParameter("PI_COD_DISTRITO", Types.VARCHAR));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new DistritoMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<UbigeoBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarDistrito] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarAnios()
	 */
	@Override
	public List<ItemBean> listarAnios() throws Exception {
		LOGGER.info("[listarAnios] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_ANIOS");

			Map<String, Object> out = objJdbcCall.withoutProcedureColumnMetaDataAccess()
					.returningResultSet("PO_LR_RECORDSET", new RowMapper<ItemBean>() {
						public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							ItemBean item = new ItemBean();
							item.setVcodigo(rs.getString("COD_ANIO"));
							item.setDescripcion(rs.getString("NOMBRE_ANIO"));
							return item;
						}
					}).execute(objJdbcCall);

			lista = new ArrayList((Collection<ItemBean>) out.get("PO_LR_RECORDSET"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarAnios] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarMeses(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarMeses(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarMeses] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			String parametro = Utils.getParam(itemBean.getVcodigo());
			input_objParametros.addValue("PI_COD_MES", parametro, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_MESES");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_MES", new SqlParameter("PI_COD_MES", Types.VARCHAR));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new MesMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarMeses] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarDdi(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarDdi(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarDdi] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_DDI", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_DDI");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new DdiMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarDdi] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarRegion(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarRegion(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarRegion] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_REGION", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_REGION");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_REGION", new SqlParameter("PI_IDE_REGION", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new RegionMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarRegion] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarAlmacen(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarAlmacen(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarAlmacen] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_DDI", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_ALMACEN");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new AlmacenMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarAlmacen] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarUnidadMedida(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarUnidadMedida(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarUnidadMedida] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_UND_MEDIDA", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_UNIDAD_MEDIDA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_UND_MEDIDA", new SqlParameter("PI_IDE_UND_MEDIDA", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new UnidadMedidaMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarUnidadMedida] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarEnvase(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarEnvase(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarEnvase] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_ENVASE", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_ENVASE");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_ENVASE", new SqlParameter("PI_IDE_ENVASE", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new EnvaseMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarEnvase] Fin ");
		LOGGER.info("LISTA "+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarEstado(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarEstado(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarEstado] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_ESTADO", parametro, Types.NUMERIC);
			input_objParametros.addValue("PI_TIPO", itemBean.getIcodigoParam2(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_ESTADO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_ESTADO", new SqlParameter("PI_IDE_ESTADO", Types.NUMERIC));
			output_objParametros.put("PI_TIPO", new SqlParameter("PI_TIPO", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new EstadoMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarEstado] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarAlmacenExternoRegion(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarAlmacenExternoRegion(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarAlmacenExternoRegion] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_FK_IDE_REGION", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_ALMACEN_EXT_REGION");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FK_IDE_REGION", new SqlParameter("PI_FK_IDE_REGION", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new AlmacenExternoRegionMapper()));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarAlmacenExternoRegion] Fin ");
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarAlmacenExternoLocal(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarAlmacenExternoLocal(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarAlmacenExternoLocal] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			String parametro = itemBean.getVcodigo();
			input_objParametros.addValue("PI_COD_UBIGEO", parametro, Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_ALMACEN_EXT_LOCAL");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_UBIGEO", new SqlParameter("PI_COD_UBIGEO", Types.VARCHAR));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new AlmacenExternoLocalMapper()));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarAlmacenExternoLocal] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarTipoMovimiento(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarTipoMovimiento(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarTipoMovimiento] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			input_objParametros.addValue("PI_FLG_DONACION", itemBean.getIcodigo(), Types.NUMERIC);
			input_objParametros.addValue("PI_FLG_TIPO", itemBean.getIcodigoParam2(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_TIP_MOVIMIENTO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FLG_DONACION", new SqlParameter("PI_FLG_DONACION", Types.NUMERIC));
			output_objParametros.put("PI_FLG_TIPO", new SqlParameter("PI_FLG_TIPO", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new TipoMovimientoMapper()));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTipoMovimiento] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarTipoDocumento(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarTipoDocumento(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarTipoDocumento] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_TIP_DOCUMENTO", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_TIP_DOCUMENTO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_TIP_DOCUMENTO", new SqlParameter("PI_IDE_TIP_DOCUMENTO", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new TipoDocumentoMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTipoDocumento] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarTipoCamion(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarTipoCamion(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarTipoCamion] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_TIP_CAMION", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_TIP_CAMION");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_TIP_CAMION", new SqlParameter("PI_IDE_TIP_CAMION", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new TipoCamionMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTipoCamion] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarTipoControlCalidad(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarTipoControlCalidad(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarTipoControlCalidad] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_TIP_CONTROL", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_TIP_CONTROL_CALIDA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_TIP_CONTROL", new SqlParameter("PI_IDE_TIP_CONTROL", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new TipoControlCalidadMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTipoControlCalidad] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarProveedor(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarProveedor(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarProveedor] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_PROVEEDOR", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_PROVEEDOR");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PROVEEDOR", new SqlParameter("PI_IDE_PROVEEDOR", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new ProveedorMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarProveedor] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarPersonal(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarPersonal(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarPersonal] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_DDI", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_PERSONAL");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new PersonalMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarPersonal] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarPersonalExternoLocal(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarPersonalExternoLocal(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarPersonalExternoLocal] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_COD_UBIGEO", itemBean.getVcodigo(), Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_PERSONAL_EXT_LOCAL");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_COD_UBIGEO", new SqlParameter("PI_COD_UBIGEO", Types.VARCHAR));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new PersonalExternoLocalMapper()));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarPersonalExternoLocal] Fin ");
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarPersonalExternoRegion(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarPersonalExternoRegion(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarPersonalExternoRegion] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_FK_IDE_REGION", itemBean.getIcodigo(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_PERSONAL_EXT_REGION");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FK_IDE_REGION", new SqlParameter("PI_FK_IDE_REGION", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new PersonalExternoRegionMapper()));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarPersonalExternoRegion] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarModAlmacen(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarModAlmacen(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarModAlmacen] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_MOD_ALMACEN", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_MOD_ALMACEN");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_MOD_ALMACEN", new SqlParameter("PI_IDE_MOD_ALMACEN", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new ModAlmacenMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarModAlmacen] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarFuenteFinanciamiento(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarFuenteFinanciamiento(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarFuenteFinanciamiento] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_FTE_FINANC", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_FUENTE_FINANC");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_FTE_FINANC", new SqlParameter("PI_IDE_FTE_FINANC", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new FuenteFinanciamientoMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarFuenteFinanciamiento] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarMarca(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarMarca(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarMarca] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_MARCA", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_MARCA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_MARCA", new SqlParameter("PI_IDE_MARCA", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new MarcaMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarMarca] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarDonante(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarDonante(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarDonante] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_DONANTE", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_DONANTE");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DONANTE", new SqlParameter("PI_IDE_DONANTE", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new MaestroDonanteMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarDonante] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarMoneda(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarMoneda(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarMoneda] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_MONEDA", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_MONEDA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_MONEDA", new SqlParameter("PI_IDE_MONEDA", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new MonedaMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarMoneda] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarPais(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarPais(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarPais] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_PAIS", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_PAIS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PAIS", new SqlParameter("PI_IDE_PAIS", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new PaisMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarPais] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarCategoria(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarCategoria(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarCategoria] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			input_objParametros.addValue("PI_TIPO", itemBean.getIcodigo(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_CATEGORIA_PRODUC");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_TIPO", new SqlParameter("PI_TIPO", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new CategoriaMapper()));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarCategoria] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarDee(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarDee(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarDEE] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_DEE", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_DEE");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DEE", new SqlParameter("PI_IDE_DEE", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new DeeMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarDEE] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarCatologoProductos(pe.com.sigbah.common.bean.ProductoBean)
	 */
	@Override
	public List<ProductoBean> listarCatologoProductos(ProductoBean productoBean) throws Exception {
		LOGGER.info("[listarCatologoProductos] Inicio ");
		List<ProductoBean> lista = new ArrayList<ProductoBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(productoBean.getIdProducto());
			input_objParametros.addValue("PI_IDE_CATEGORIA_BAH", productoBean.getIdCategoria(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_PRODUCTO", parametro, Types.NUMERIC);			
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_CATALOGO_PRODUCTOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_CATEGORIA_BAH", new SqlParameter("PI_IDE_CATEGORIA_BAH", Types.NUMERIC));
			output_objParametros.put("PI_IDE_PRODUCTO", new SqlParameter("PI_IDE_PRODUCTO", Types.NUMERIC));			
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new CatologoProductosMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ProductoBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarCatologoProductos] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarMedioTransporte(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarMedioTransporte(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarMedioTransporte] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_MED_TRANSPORTE", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_MEDIO_TRANSPORTE");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_MED_TRANSPORTE", new SqlParameter("PI_IDE_MED_TRANSPORTE", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new MedioTransporteMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarMedioTransporte] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarEmpresaTransporte(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarEmpresaTransporte(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarEmpresaTransporte] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigoParam3());
			input_objParametros.addValue("PI_IDE_DDI", itemBean.getIcodigo(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_MED_TRANSPORTE", itemBean.getIcodigoParam2(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_EMP_TRANS", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_EMP_TRANSPORTE");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PI_IDE_MED_TRANSPORTE", new SqlParameter("PI_IDE_MED_TRANSPORTE", Types.NUMERIC));
			output_objParametros.put("PI_IDE_EMP_TRANS", new SqlParameter("PI_IDE_EMP_TRANS", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new EmpresaTransporteMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarEmpresaTransporte] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarEjecutora()
	 */
	@Override
	public List<ItemBean> listarEjecutora() throws Exception {
		LOGGER.info("[listarEjecutora] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_EJECUTORA");

			Map<String, Object> out = objJdbcCall.withoutProcedureColumnMetaDataAccess()
					.returningResultSet("PO_LR_RECORDSET", new RowMapper<ItemBean>() {
						public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							ItemBean item = new ItemBean();
							item.setIcodigo(rs.getInt("IDE_EJECUTORA"));
							item.setDescripcion(rs.getString("NOM_EJECUTORA"));
							item.setDescripcionCorta(rs.getString("DIRECCION"));
							return item;
						}
					}).execute(objJdbcCall);

			lista = new ArrayList((Collection<ItemBean>) out.get("PO_LR_RECORDSET"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarEjecutora] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarChofer(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarChofer(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarChofer] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigoParam2());
			input_objParametros.addValue("PI_IDE_EMP_TRANS", itemBean.getIcodigo(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_CHOFER", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_CHOFER");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_EMP_TRANS", new SqlParameter("PI_IDE_EMP_TRANS", Types.NUMERIC));
			output_objParametros.put("PI_IDE_CHOFER", new SqlParameter("PI_IDE_CHOFER", Types.NUMERIC));			
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new ChoferMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarChofer] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.general.GeneralDao#listarParametro(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarParametro(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarParametro] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_PARAMETRO", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_PARAMETRO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_PARAMETRO", new SqlParameter("PI_IDE_PARAMETRO", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new ParametroMapper()));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarParametro] Fin ");
		return lista;
	}

	@Override
	public String obtenerAnioActual() throws Exception {
		LOGGER.info("[obtenerAnioActual] Inicio ");
		String anio = null;
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_ANIO_ACTUAL");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PO_CODANIO", new SqlOutParameter("PO_CODANIO", Types.VARCHAR));

			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			anio = (String) out.get("PO_CODANIO");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[obtenerAnioActual] Fin ");
		return anio;
	}

	@Override
	public List<ItemBean> listarEstadoDonacion(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarEstadoDonacion] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_ESTADO", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_ESTADO_DONACION");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_ESTADO", new SqlParameter("PI_IDE_ESTADO", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new EstadoDonacionMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarEstadoDonacion] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.GeneralDao#listarTipoMovimientoPm()
	 */
	@Override
	public List<ItemBean> listarTipoMovimientoPm() throws Exception {
		LOGGER.info("[listarTipoMovimientoPm] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_TIP_MOVIMIENTO_PM");

			Map<String, Object> out = objJdbcCall.withoutProcedureColumnMetaDataAccess()
					.returningResultSet("PO_LR_RECORDSET", new RowMapper<ItemBean>() {
						public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							ItemBean item = new ItemBean();
							item.setIcodigo(rs.getInt("IDE_TIP_MOVIMIENTO"));
							item.setDescripcion(rs.getString("NOM_MOVIMIENTO"));
							return item;
						}
					}).execute(objJdbcCall);

			lista = new ArrayList((Collection<ItemBean>) out.get("PO_LR_RECORDSET"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarTipoMovimientoPm] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.GeneralDao#listarProgramacion()
	 */
	@Override
	public List<ItemBean> listarProgramacion() throws Exception {
		LOGGER.info("[listarProgramacion] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_PROGRAMACION");

			Map<String, Object> out = objJdbcCall.withoutProcedureColumnMetaDataAccess()
					.returningResultSet("PO_LR_RECORDSET", new RowMapper<ItemBean>() {
						public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							ItemBean item = new ItemBean();
							item.setIcodigo(rs.getInt("IDE_PROGRAMACION"));
							item.setDescripcion(rs.getString("COD_PROGRAMACION"));
							return item;
						}
					}).execute(objJdbcCall);

			lista = new ArrayList((Collection<ItemBean>) out.get("PO_LR_RECORDSET"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarProgramacion] Fin ");
		return lista;
	}
	
	@Override
	public List<ItemBean> listarProgramacion1(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarProgramacion] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_DDI", itemBean.getIcodigo(), Types.NUMERIC);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_PROGRAMACION");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new ListaProgramacionMapper()));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarProgramacion] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.GeneralDao#listarRacion(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarRacion(ItemBean itemBean) throws Exception {
		ItemBean item = new ItemBean();
		List<ItemBean> lista = new ArrayList<ItemBean>();
			item.setVcodigo("C");
			item.setDescripcion("Cruda");
			lista.add(item);
			item = new ItemBean();
			item.setVcodigo("F");
			item.setDescripcion("Fria");
			lista.add(item);
		return lista;
	}
	
	@Override
	public List<ItemBean> listarFenomeno(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarFenomeno] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_FENOMENO", parametro, Types.NUMERIC);

			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_FENOMENOS");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_FENOMENO", new SqlParameter("PI_IDE_FENOMENO", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new FenomenoMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarFenomeno] Fin ");
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.GeneralDao#listarMotivoTraslado()
	 */
	@Override
	public List<ItemBean> listarMotivoTraslado() throws Exception {
		LOGGER.info("[listarMotivoTraslado] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_MOTIVO_TRASLADO");

			Map<String, Object> out = objJdbcCall.withoutProcedureColumnMetaDataAccess()
					.returningResultSet("PO_LR_RECORDSET", new RowMapper<ItemBean>() {
						public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							ItemBean item = new ItemBean();
							item.setIcodigo(rs.getInt("IDE_MOTIVO_TRASLADO"));
							item.setDescripcion(rs.getString("NOM_MOTIVO"));
							return item;
						}
					}).execute(objJdbcCall);

			lista = new ArrayList((Collection<ItemBean>) out.get("PO_LR_RECORDSET"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarMotivoTraslado] Fin ");
		return lista;
	}
	
	@Override
	public List<ItemBean> listarOficinas(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarOficinas] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			Integer parametro = Utils.getParamInt(itemBean.getIcodigo());
			input_objParametros.addValue("PI_IDE_DDI", parametro, Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_TAB_OFICINA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new OficinaMapper(parametro)));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarOficinas] Fin ");
		return lista;
	}
	
	@Override
	public List<ItemBean> listarPersonalOficina(ItemBean itemBean) throws Exception {
		LOGGER.info("[listarPersonalOficina] Inicio ");
		List<ItemBean> lista = new ArrayList<ItemBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();			
			input_objParametros.addValue("PI_IDE_DDI", itemBean.getIcodigoParam2(), Types.NUMERIC);
			input_objParametros.addValue("PI_IDE_OFICINA", itemBean.getIcodigo(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_GENERAL);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_SEL_PERSONAL_POR_OFICINA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_IDE_DDI", new SqlParameter("PI_IDE_DDI", Types.NUMERIC));
			output_objParametros.put("PI_IDE_OFICINA", new SqlParameter("PI_IDE_OFICINA", Types.NUMERIC));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new PersonalOficinaMapper()));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);

			lista = (List<ItemBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarPersonalOficina] Fin ");
		return lista;
	}

	@Override
	public List<ItemBean> listarTipoProducto(ItemBean itemBean) throws Exception {
		ItemBean item = new ItemBean();
		List<ItemBean> lista = new ArrayList<ItemBean>();
		item.setIcodigo(1);
		item.setDescripcion("Alimento");
		lista.add(item);
		item = new ItemBean();
		item.setIcodigo(2);
		item.setDescripcion("No alimentario");
		lista.add(item);
		item = new ItemBean();
		item.setIcodigo(3);
		item.setDescripcion("Ambos");
		lista.add(item);
		return lista;
	}
	
	@Override
	public List<ItemBean> listarEstadoPedidoCompra(ItemBean itemBean) throws Exception {
		ItemBean item = new ItemBean();
		List<ItemBean> lista = new ArrayList<ItemBean>();
		item.setIcodigo(0);
		item.setDescripcion("Anulado");
		lista.add(item);
		item = new ItemBean();
		item.setIcodigo(1);
		item.setDescripcion("Pendiente");
		lista.add(item);
		item = new ItemBean();
		item.setIcodigo(2);
		item.setDescripcion("Atendido");
		lista.add(item);
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.GeneralDao#listarEstadoDee(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarEstadoDee(ItemBean itemBean) throws Exception {
		ItemBean item = new ItemBean();
		List<ItemBean> lista = new ArrayList<ItemBean>();
		item.setIcodigo(1);
		item.setDescripcion("Vigente");
		lista.add(item);
		item = new ItemBean();
		item.setIcodigo(2);
		item.setDescripcion("Finalizado");
		lista.add(item);			
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.GeneralDao#listarEstadoRequerimiento(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarEstadoRequerimiento(ItemBean itemBean) throws Exception {
		ItemBean item = new ItemBean();
		List<ItemBean> lista = new ArrayList<ItemBean>();
		item.setIcodigo(1);
		item.setDescripcion("Activo");
		lista.add(item);
		item = new ItemBean();
		item.setIcodigo(0);
		item.setDescripcion("Anulado");
		lista.add(item);			
		return lista;
	}
	
}
