package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.StockAlmacenProductoLoteBean;

/**
 * @className: StockAlmacenProductoLoteMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class StockAlmacenProductoLoteMapper implements RowMapper<StockAlmacenProductoLoteBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public StockAlmacenProductoLoteBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		StockAlmacenProductoLoteBean stock = new StockAlmacenProductoLoteBean();
		stock.setNroLote(rs.getString("NRO_LOTE"));
		stock.setLote(rs.getString("LOTE"));
		stock.setCantidadStock(rs.getBigDecimal("CANTIDAD_STOCK"));		
		stock.setPrecioUnitario(rs.getBigDecimal("PREC_UNITARIO"));
		return stock;
	}

}
