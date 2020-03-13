package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.MaePaisBean;
import pe.com.sigbah.common.bean.StockConsultaBean;

/**
 * @className: StockBNAMapper.java
 * @description: 
 * @date: 01 de set. de 2017
 * @author: ARCHY.
 */
public class StockBNAMapper implements RowMapper<StockConsultaBean> {
	

	@Override
	public StockConsultaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		StockConsultaBean stockConsultaBean = new StockConsultaBean();

		stockConsultaBean.setNombreCategoria(rs.getString("CATEGORIA"));
		stockConsultaBean.setNombreProducto(rs.getString("PRODUCTO"));
		stockConsultaBean.setCantidad(rs.getBigDecimal("CANTIDAD"));
		stockConsultaBean.setImporteTotal(rs.getBigDecimal("IMPORTE_TOTAL"));
		return stockConsultaBean;
	}

}
