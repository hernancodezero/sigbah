package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.AlmacenStockBean;

/**
 * @className: AlmacenStockMapper.java
 * @description: Clase asociado al almacen stock.
 * @date: 28/07/2018
 * @author: Sumerio.
 */
public class AlmacenStockMapper implements RowMapper<AlmacenStockBean> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public AlmacenStockBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		AlmacenStockBean almacenStockBean = new AlmacenStockBean();
		almacenStockBean.setNroFila(rs.getInt("ROWNUM"));
		almacenStockBean.setIdDdi(rs.getInt("ID_DDI"));
		almacenStockBean.setIdAlmacen(rs.getInt("ID_ALM"));
		almacenStockBean.setIdProducto(rs.getInt("ID_PROD"));
		almacenStockBean.setIdCategoria(rs.getInt("ID_CAT"));
		almacenStockBean.setNombreDdiAlmacen(rs.getString("NOM_DDI_ALMACEN"));
		almacenStockBean.setNombreCategoria(rs.getString("NOM_CAT"));
		almacenStockBean.setNombreProducto(rs.getString("NOM_PROD"));
		almacenStockBean.setCantidadProducto(rs.getBigDecimal("CANT"));
		almacenStockBean.setCantidadToneladas(rs.getBigDecimal("TON"));
		return almacenStockBean;
	}

}
