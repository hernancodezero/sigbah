package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.StockAlmacenBean;

/**
 * @className: RegistroStockAlmacenMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class RegistroStockAlmacen1Mapper implements RowMapper<StockAlmacenBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public StockAlmacenBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		StockAlmacenBean stockAlmacen = new StockAlmacenBean();
		stockAlmacen.setNombreSistema(rs.getString("VERSION"));	
		
		stockAlmacen.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));	
		stockAlmacen.setNroKardex(rs.getString("NRO_KARDEX"));
		stockAlmacen.setNombreProducto(rs.getString("NOMBRE_PRODUCTO"));
		stockAlmacen.setCodigoProducto(rs.getString("CODIGO_PRODUCTO"));
		stockAlmacen.setNombreCategoria(rs.getString("NOMBRE_CATEGORIA"));
		stockAlmacen.setCodigoSiga(rs.getString("CODIGO_SIGA"));
		stockAlmacen.setNombreUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		stockAlmacen.setNombreEnvasePrimario(rs.getString("NOMBRE_ENVASE_PRIMARIO"));	
		stockAlmacen.setPesoUnitarioNeto(rs.getBigDecimal("PESO_UNIT_NETO"));
		stockAlmacen.setPesoUnitarioBruto(rs.getBigDecimal("PESO_UNIT_BRUTO"));
		
		stockAlmacen.setIdTipoEnvaseSecundario(rs.getInt("FK_TIP_ENVASE_SEC"));
		stockAlmacen.setNombreEnvase(rs.getString("NOMBRE_ENVASE_SEC"));		
		stockAlmacen.setDescripcionEnvaseSecundario(rs.getString("DESCR_ENVASE_SEC"));
		stockAlmacen.setUnidadesEnvaseSecundario(rs.getBigDecimal("UNIDADES_ENVASE_SEC"));	
		
		stockAlmacen.setDimLargo(rs.getBigDecimal("DIM_LARGO"));
		stockAlmacen.setDimAlto(rs.getBigDecimal("DIM_ALTO"));
		stockAlmacen.setDimAncho(rs.getBigDecimal("DIM_ANCHO"));
		stockAlmacen.setVolumenUnitario(rs.getBigDecimal("VOLUMEN_UNITARIO"));
		stockAlmacen.setCantidadEnvases(rs.getBigDecimal("CANT_ENVASES"));
		
		stockAlmacen.setCantidadStock(rs.getBigDecimal("CANTIDAD_STOCK"));
		stockAlmacen.setPrecioUnitarioPromedio(rs.getBigDecimal("PREC_UNITARIO"));
		stockAlmacen.setObservacion(rs.getString("OBSERVACION"));
		
		stockAlmacen.setEstado(rs.getString("NOMBRE_ESTADO"));
		
		return stockAlmacen;
	}

}
