package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.StockXAlmacenWSBean;

/**
 * @className: AlmacenesWSMapper.java
 * @description: Clase asociado al almacenes.
 * @date: 14/08/2018
 * @author: Alan Chumpitaz.
 */
public class StockXAlmacenWSMapper implements RowMapper<StockXAlmacenWSBean> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public StockXAlmacenWSBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		StockXAlmacenWSBean almacenStockBean = new StockXAlmacenWSBean();
		almacenStockBean.setNombreDdiAlmacen(rs.getString("NOM_DDI_ALMACEN"));
		almacenStockBean.setNombreCategoria(rs.getString("NOM_CAT"));
		almacenStockBean.setNombreProducto(rs.getString("NOM_PROD"));
		almacenStockBean.setCantidad(rs.getBigDecimal("CANT"));
		almacenStockBean.setTonelada(rs.getBigDecimal("TON"));
		return almacenStockBean;
	}

}
