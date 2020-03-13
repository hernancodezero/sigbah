package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.StockProductoLoteBean;

/**
 * @className: StockProductoLoteMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class StockProductoLoteMapper implements RowMapper<StockProductoLoteBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public StockProductoLoteBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		StockProductoLoteBean producto = new StockProductoLoteBean();
		producto.setNroLote(rs.getString("NRO_LOTE"));
		producto.setLote(rs.getString("LOTE"));
		producto.setTipoOrigen(rs.getString("TIPO_ORIGEN"));
		producto.setIdDdi(rs.getInt("FK_IDE_DDI"));
		producto.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		producto.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		return producto;
	}

}
