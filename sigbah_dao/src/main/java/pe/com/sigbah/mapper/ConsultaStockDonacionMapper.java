package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.StockConsultaBean;

/**
 * @className: ConsultaStockDonacionMapper.java
 * @description: 
 * @date: 01 de set. de 2017
 * @author: ARCHY.
 */
public class ConsultaStockDonacionMapper implements RowMapper<StockConsultaBean> {
	

	@Override
	public StockConsultaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		StockConsultaBean maeTipDocumentoBean = new StockConsultaBean();
		maeTipDocumentoBean.setNombreDdi(rs.getString("NOM_DDI"));
		maeTipDocumentoBean.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		maeTipDocumentoBean.setNombreCategoria(rs.getString("CATEGORIA"));
		maeTipDocumentoBean.setNombreProducto(rs.getString("PRODUCTO"));
		maeTipDocumentoBean.setCantidad(rs.getBigDecimal("CANTIDAD"));
		maeTipDocumentoBean.setImporteTotal(rs.getBigDecimal("IMPORTE_TOTAL"));

		return maeTipDocumentoBean;
	}

}
