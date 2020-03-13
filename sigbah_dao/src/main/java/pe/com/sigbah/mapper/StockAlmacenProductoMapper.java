package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.StockAlmacenProductoBean;

/**
 * @className: StockAlmacenProductoMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class StockAlmacenProductoMapper implements RowMapper<StockAlmacenProductoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public StockAlmacenProductoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		StockAlmacenProductoBean stock = new StockAlmacenProductoBean();
		stock.setTipoOrigen(rs.getString("TIPO_ORIGEN"));
		stock.setIdDdi(rs.getInt("FK_IDE_DDI"));
		stock.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		stock.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));		
		stock.setNombreProducto(rs.getString("NOMBRE_PRODUCTO"));
		stock.setCodigoProducto(rs.getString("COD_PRODUCTO"));
		stock.setNombreUnidad(rs.getString("NOMBRE_UNIDAD"));		
		stock.setNombreEnvase(rs.getString("NOMBRE_ENVASE"));
		return stock;
	}

}
