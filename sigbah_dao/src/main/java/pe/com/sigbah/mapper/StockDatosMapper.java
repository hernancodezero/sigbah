package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.MaePaisBean;
import pe.com.sigbah.common.bean.StockConsultaBean;

/**
 * @className: StockDatosMapper.java
 * @description: 
 * @date: 28 de ago. de 2018
 * @author: ARCHY.
 */
public class StockDatosMapper implements RowMapper<StockConsultaBean> {
	

	@Override
	public StockConsultaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		StockConsultaBean stockConsultaBean = new StockConsultaBean();

		stockConsultaBean.setNombreDdi(rs.getString("NOM_DDI"));
		stockConsultaBean.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		stockConsultaBean.setNombreCategoria(rs.getString("CATEGORIA"));
		stockConsultaBean.setNombreProducto(rs.getString("PRODUCTO"));
		stockConsultaBean.setCantidad(rs.getBigDecimal("CANTIDAD"));
		stockConsultaBean.setImporteTotal(rs.getBigDecimal("IMPORTE_TOTAL"));
		return stockConsultaBean;
	}

}
