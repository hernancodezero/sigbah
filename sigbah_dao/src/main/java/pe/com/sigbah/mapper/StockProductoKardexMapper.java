package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.StockProductoKardexBean;

/**
 * @className: StockProductoKardexMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class StockProductoKardexMapper implements RowMapper<StockProductoKardexBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public StockProductoKardexBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		StockProductoKardexBean producto = new StockProductoKardexBean();
		producto.setTipoOrigen(rs.getString("TIPO_ORIGEN"));
		producto.setIdDdi(rs.getInt("FK_IDE_DDI"));
		producto.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		producto.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		producto.setNombreProducto(rs.getString("NOMBRE_PRODUCTO"));
		producto.setNroKardex(rs.getString("NRO_KARDEX"));
		return producto;
	}

}
