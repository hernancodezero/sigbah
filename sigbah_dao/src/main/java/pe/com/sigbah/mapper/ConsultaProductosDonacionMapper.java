package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.StockConsultaBean;

/**
 * @className: ConsultaProductosDonacionMapper.java
 * @description: 
 * @date: 18 de set. de 2017
 * @author: ARCHY.
 */
public class ConsultaProductosDonacionMapper implements RowMapper<StockConsultaBean> {
	

	@Override
	public StockConsultaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		StockConsultaBean stockConsultaBean = new StockConsultaBean();
		stockConsultaBean.setFechaVencimiento(rs.getString("FECHA_VENCIMIENTO"));
		stockConsultaBean.setNombreDdi(rs.getString("NOM_DDI"));
		stockConsultaBean.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		stockConsultaBean.setNombreCategoria(rs.getString("CATEGORIA"));
		stockConsultaBean.setNombreProducto(rs.getString("PRODUCTO"));
		stockConsultaBean.setNroLote(rs.getString("NRO_LOTE"));
		stockConsultaBean.setNombreSistema(rs.getString("NOMBRE_SISTEMA"));

		return stockConsultaBean;
	}

}
