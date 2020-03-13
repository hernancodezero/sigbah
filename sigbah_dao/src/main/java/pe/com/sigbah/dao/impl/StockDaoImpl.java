package pe.com.sigbah.dao.impl;

import java.io.Serializable;
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
import pe.com.sigbah.common.bean.AlmacenStockBean;
import pe.com.sigbah.common.bean.AlmacenesWSBean;
import pe.com.sigbah.common.bean.CategoriaStockBean;
import pe.com.sigbah.common.bean.ProductoStockBean;
import pe.com.sigbah.common.bean.StockXAlmacenWSBean;
import pe.com.sigbah.common.bean.UsuarioWSBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.SpringUtil;
import pe.com.sigbah.dao.StockDao;
import pe.com.sigbah.mapper.AlmacenStockMapper;
import pe.com.sigbah.mapper.AlmacenesWSMapper;
import pe.com.sigbah.mapper.CategoriaStockMapper;
import pe.com.sigbah.mapper.ProductoStockMapper;
import pe.com.sigbah.mapper.StockXAlmacenWSMapper;
import pe.com.sigbah.mapper.UsuarioWSMapper;

/**
 * @className: StockDaoImpl.java
 * @description: Clase que contiene el consumo de los procedimientos del package BAH_PKG_STOCK.
 * @date: 28/07/2018
 * @author: Sumerio.
 */
@Repository
public class StockDaoImpl extends JdbcDaoSupport implements StockDao, Serializable {
	
	private static final long serialVersionUID = 1L;
	private transient final Log LOGGER = LogFactory.getLog(getClass());
	
	private SimpleJdbcCall objJdbcCall;
	
	/**
	 * @param dataSource
	 */
	@Autowired
	public StockDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.StockDao#listarAlmacenStock()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AlmacenStockBean> listarAlmacenStock() throws Exception {
		LOGGER.info("[listarAlmacenStockBean] Inicio ");
		List<AlmacenStockBean> lista = new ArrayList<AlmacenStockBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_STOCK);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_STOCK_POR_ALMACEN");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new AlmacenStockMapper()));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarAlmacenStockBean] Ocurrio un error en la operacion del USP_STOCK_POR_ALMACEN : "+mensajeRespuesta);
    			throw new Exception(mensajeRespuesta);
    		}
			
			lista = (List<AlmacenStockBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarAlmacenStockBean] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.StockDao#listarCategoriaStock(pe.com.sigbah.common.bean.CategoriaStockBean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoriaStockBean> listarCategoriaStock(CategoriaStockBean categoriaStockBean) throws Exception {
		LOGGER.info("[listarCategoriaStock] Inicio ");
		List<CategoriaStockBean> lista = new ArrayList<CategoriaStockBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_FK_IDE_CATEGORIA_BAH", categoriaStockBean.getIdCategoria(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_STOCK);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_STOCK_POR_CATEGORIA");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_FK_IDE_CATEGORIA_BAH", new SqlParameter("PI_FK_IDE_CATEGORIA_BAH", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new CategoriaStockMapper()));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarCategoriaStock] Ocurrio un error en la operacion del USP_STOCK_POR_CATEGORIA : "+mensajeRespuesta);
    			throw new Exception(mensajeRespuesta);
    		}
			
			lista = (List<CategoriaStockBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarCategoriaStock] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.StockDao#listarProductoStock(pe.com.sigbah.common.bean.ProductoStockBean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductoStockBean> listarProductoStock(ProductoStockBean productoStockBean) throws Exception {
		LOGGER.info("[listarProductoStock] Inicio ");
		List<ProductoStockBean> lista = new ArrayList<ProductoStockBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_NOMBRE_PRODUCTO", productoStockBean.getNombreProducto(), Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_STOCK);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_STOCK_POR_PRODUCTO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_NOMBRE_PRODUCTO", new SqlParameter("PI_NOMBRE_PRODUCTO", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new ProductoStockMapper()));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarProductoStock] Ocurrio un error en la operacion del USP_STOCK_POR_PRODUCTO : "+mensajeRespuesta);
    			throw new Exception(mensajeRespuesta);
    		}
			
			lista = (List<ProductoStockBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarProductoStock] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.StockDao#listarAlmacenesWS()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AlmacenesWSBean> listarAlmacenesWS() throws Exception {
		LOGGER.info("[listarAlmacenesWS] Inicio ");
		List<AlmacenesWSBean> lista = new ArrayList<AlmacenesWSBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_STOCK);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_ALMACENES");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new AlmacenesWSMapper()));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[listarAlmacenesWS] Ocurrio un error en la operacion del USP_ALMACENES : "+mensajeRespuesta);
    			throw new Exception(mensajeRespuesta);
    		}
			
			lista = (List<AlmacenesWSBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[listarAlmacenesWS] Fin ");
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.StockDao#listarProductoStock(pe.com.sigbah.common.bean.ProductoStockBean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioWSBean> loginUsuarioWS(UsuarioWSBean usuarioWSBean) throws Exception {
		LOGGER.info("[loginUsuarioWS] Inicio ");
		List<UsuarioWSBean> lista = new ArrayList<UsuarioWSBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_USER", usuarioWSBean.getUsuario(), Types.VARCHAR);
			input_objParametros.addValue("PI_PASS", usuarioWSBean.getPassword(), Types.VARCHAR);
			input_objParametros.addValue("PI_EMAIL", usuarioWSBean.getEmail(), Types.VARCHAR);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_STOCK);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_USUARIO");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_USER", new SqlParameter("PI_USER", Types.VARCHAR));
			output_objParametros.put("PI_PASS", new SqlParameter("PI_PASS", Types.VARCHAR));
			output_objParametros.put("PI_EMAIL", new SqlParameter("PI_EMAIL", Types.VARCHAR));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new UsuarioWSMapper()));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[loginUsuarioWS] Ocurrio un error en la operacion del USP_USUARIO : "+mensajeRespuesta);
    			throw new Exception(mensajeRespuesta);
    		}
			
			lista = (List<UsuarioWSBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[loginUsuarioWS] Fin ");
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.dao.StockDao#listarProductoStock(pe.com.sigbah.common.bean.ProductoStockBean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<StockXAlmacenWSBean> stockXAlmacenWS(StockXAlmacenWSBean stockXAlmacenWSBean) throws Exception {
		LOGGER.info("[stockXAlmacenWS] Inicio ");
		List<StockXAlmacenWSBean> lista = new ArrayList<StockXAlmacenWSBean>();
		try {
			MapSqlParameterSource input_objParametros = new MapSqlParameterSource();		
			input_objParametros.addValue("PI_ID_ALMACEN", stockXAlmacenWSBean.getIdAlmacen(), Types.NUMERIC);
			
			objJdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			objJdbcCall.withoutProcedureColumnMetaDataAccess();
			objJdbcCall.withCatalogName(Constantes.PACKAGE_STOCK);
			objJdbcCall.withSchemaName(Constantes.ESQUEMA_SINPAD);
			objJdbcCall.withProcedureName("USP_STOCK_X_ALMACEN");

			LinkedHashMap<String, SqlParameter> output_objParametros = new LinkedHashMap<String, SqlParameter>();
			output_objParametros.put("PI_ID_ALMACEN", new SqlParameter("PI_ID_ALMACEN", Types.NUMERIC));
			output_objParametros.put("PO_CODIGO_RESPUESTA", new SqlOutParameter("PO_CODIGO_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_MENSAJE_RESPUESTA", new SqlOutParameter("PO_MENSAJE_RESPUESTA", Types.VARCHAR));
			output_objParametros.put("PO_LR_RECORDSET", new SqlOutParameter("PO_LR_RECORDSET", OracleTypes.CURSOR, new StockXAlmacenWSMapper()));
			
			objJdbcCall.declareParameters((SqlParameter[]) SpringUtil.getHashMapObjectsArray(output_objParametros));
			
			Map<String, Object> out = objJdbcCall.execute(input_objParametros);
			
			String codigoRespuesta = (String) out.get("PO_CODIGO_RESPUESTA");
			
			if (codigoRespuesta.equals(Constantes.COD_ERROR_GENERAL)) {
				String mensajeRespuesta = (String) out.get("PO_MENSAJE_RESPUESTA");
				LOGGER.info("[stockXAlmacenWS] Ocurrio un error en la operacion del USP_STOCK_X_ALMACEN : "+mensajeRespuesta);
    			throw new Exception(mensajeRespuesta);
    		}
			
			lista = (List<StockXAlmacenWSBean>) out.get("PO_LR_RECORDSET");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}		
		LOGGER.info("[stockXAlmacenWS] Fin ");
		return lista;
	}
}
