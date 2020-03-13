package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.StockAlmacenBean;

/**
 * @className: StockAlmacenMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class StockAlmacenMapper implements RowMapper<StockAlmacenBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public StockAlmacenBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		StockAlmacenBean stockAlmacen = new StockAlmacenBean();
		stockAlmacen.setTipoOrigen(rs.getString("TIPO_ORIGEN"));
		stockAlmacen.setIdDdi(rs.getInt("FK_IDE_DDI"));
		stockAlmacen.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		stockAlmacen.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));		
		stockAlmacen.setNombreCategoria(rs.getString("NOMBRE_CATEGORIA"));
		stockAlmacen.setCodigoProducto(rs.getString("CODIGO_PRODUCTO"));
		stockAlmacen.setNombreProducto(rs.getString("NOMBRE_PRODUCTO"));		
		stockAlmacen.setNombreUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		stockAlmacen.setNombreEnvase(rs.getString("NOMBRE_ENVASE"));
		stockAlmacen.setPrecioUnitarioPromedio(rs.getBigDecimal("PRECIO_UNIT_PROMEDIO"));
		stockAlmacen.setCantidad(rs.getBigDecimal("CANTIDAD"));
		return stockAlmacen;
	}

}
