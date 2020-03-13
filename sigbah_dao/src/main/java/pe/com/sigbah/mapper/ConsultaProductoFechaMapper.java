package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.ProductoBean;

/**
 * @className: ConsultaProductoFechaMapper.java
 * @description: 
 * @date: 08 de set. de 2017
 * @author: ARCHY.
 */
public class ConsultaProductoFechaMapper implements RowMapper<ProductoBean> {
	

	@Override
	public ProductoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoBean productoBean = new ProductoBean();
		productoBean.setNombreDdi(rs.getString("NOM_DDI"));
		productoBean.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		productoBean.setNombreCategoria(rs.getString("CATEGORIA"));
		productoBean.setNombreProducto(rs.getString("PRODUCTO"));
		productoBean.setNroLote(rs.getString("NRO_LOTE"));
		productoBean.setCantidad1(rs.getBigDecimal("CANT_01"));
		productoBean.setCantidad2(rs.getBigDecimal("CANT_02"));
		productoBean.setCantidad3(rs.getBigDecimal("CANT_03"));
		productoBean.setCantidad4(rs.getBigDecimal("CANT_04"));
		productoBean.setCantidad5(rs.getBigDecimal("CANT_05"));
		productoBean.setCantidad6(rs.getBigDecimal("CANT_06"));
		productoBean.setCantidad7(rs.getBigDecimal("CANT_07"));
		productoBean.setCantidad8(rs.getBigDecimal("CANT_08"));
		productoBean.setCantidad9(rs.getBigDecimal("CANT_09"));
		productoBean.setCantidad10(rs.getBigDecimal("CANT_10"));
		productoBean.setCantidad11(rs.getBigDecimal("CANT_11"));
		productoBean.setCantidad12(rs.getBigDecimal("CANT_12"));
		productoBean.setFecha1(rs.getString("FEC_01"));
		productoBean.setFecha2(rs.getString("FEC_02"));
		productoBean.setFecha3(rs.getString("FEC_03"));
		productoBean.setFecha4(rs.getString("FEC_04"));
		productoBean.setFecha5(rs.getString("FEC_05"));
		productoBean.setFecha6(rs.getString("FEC_06"));
		productoBean.setFecha7(rs.getString("FEC_07"));
		productoBean.setFecha8(rs.getString("FEC_08"));
		productoBean.setFecha9(rs.getString("FEC_09"));
		productoBean.setFecha10(rs.getString("FEC_10"));
		productoBean.setFecha11(rs.getString("FEC_11"));
		productoBean.setFecha12(rs.getString("FEC_12"));

		return productoBean;
	}

}
