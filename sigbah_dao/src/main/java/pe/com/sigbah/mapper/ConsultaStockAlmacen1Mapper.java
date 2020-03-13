package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.StockAlmacenBean;

/**
 * @className: ConsultaStockAlmacenMapper.java
 * @description: 
 * @date: 01 de set. de 2017
 * @author: ARCHY.
 */
public class ConsultaStockAlmacen1Mapper implements RowMapper<StockAlmacenBean> {
	

	@Override
	public StockAlmacenBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		StockAlmacenBean stockAlmacenBean = new StockAlmacenBean();
		stockAlmacenBean.setNombreDdi(rs.getString("NOM_DDI"));
		stockAlmacenBean.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		stockAlmacenBean.setNombreCategoria(rs.getString("CATEGORIA"));
		stockAlmacenBean.setNombreProducto(rs.getString("PRODUCTO"));
		stockAlmacenBean.setCantidad(rs.getBigDecimal("CANTIDAD"));
		stockAlmacenBean.setImporteTotal(rs.getBigDecimal("IMPORTE_TOTAL"));
		stockAlmacenBean.setTipoStock(rs.getString("TIPO_STOCK"));

		return stockAlmacenBean;
	}

}
