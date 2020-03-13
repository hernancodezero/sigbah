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
public class RegistroStockAlmacenMapper implements RowMapper<StockAlmacenBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public StockAlmacenBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		StockAlmacenBean stockAlmacen = new StockAlmacenBean();
		stockAlmacen.setIdDdi(rs.getInt("FK_IDE_DDI"));
		stockAlmacen.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		stockAlmacen.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		stockAlmacen.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));		
		stockAlmacen.setNroKardex(rs.getString("NRO_KARDEX"));
		stockAlmacen.setCodigoProducto(rs.getString("CODIGO_PRODUCTO"));
		stockAlmacen.setCodigoSiga(rs.getString("CODIGO_SIGA"));		
		stockAlmacen.setNombreCategoria(rs.getString("NOMBRE_CATEGORIA"));
		stockAlmacen.setNombreProducto(rs.getString("NOMBRE_PRODUCTO"));
		stockAlmacen.setNombreUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		stockAlmacen.setNombreEnvasePrimario(rs.getString("NOMBRE_ENVASE_PRIMARIO"));		
		stockAlmacen.setPesoUnitarioNeto(rs.getBigDecimal("PESO_UNIT_NETO"));
		stockAlmacen.setPesoUnitarioBruto(rs.getBigDecimal("PESO_UNIT_BRUTO"));
		stockAlmacen.setDimLargo(rs.getBigDecimal("DIM_LARGO"));
		stockAlmacen.setDimAlto(rs.getBigDecimal("DIM_ALTO"));
		stockAlmacen.setDimAncho(rs.getBigDecimal("DIM_ANCHO"));
		stockAlmacen.setVolumenUnitario(rs.getBigDecimal("VOLUMEN_UNITARIO"));
		stockAlmacen.setVolumenTotal(rs.getBigDecimal("VOLUMEN_TOTAL"));
		stockAlmacen.setPrecioUnitarioPromedio(rs.getBigDecimal("PRECIO_UNIT_PROMEDIO"));
		stockAlmacen.setStockSeguridad(rs.getBigDecimal("STOCK_SEGURIDAD"));
		stockAlmacen.setCantidadStock(rs.getBigDecimal("CANTIDAD_STOCK"));
		stockAlmacen.setPrecioPromedio(rs.getBigDecimal("PREC_PROMEDIO"));
		stockAlmacen.setIdTipoEnvaseSecundario(rs.getInt("FK_TIP_ENVASE_SEC"));
		stockAlmacen.setNombreEnvase(rs.getString("NOMBRE_ENVASE"));
		stockAlmacen.setDescripcionEnvaseSecundario(rs.getString("DESCR_ENVASE_SEC"));
		stockAlmacen.setUnidadesEnvaseSecundario(rs.getBigDecimal("UNIDADES_ENVASE_SEC"));		
		stockAlmacen.setCantidadEnvases(rs.getBigDecimal("CANT_ENVASES"));
		stockAlmacen.setUnidadesSueltas(rs.getBigDecimal("UNIDADES_SUELTAS"));		
		stockAlmacen.setObservacion(rs.getString("OBSERVACION"));		
		return stockAlmacen;
	}

}
