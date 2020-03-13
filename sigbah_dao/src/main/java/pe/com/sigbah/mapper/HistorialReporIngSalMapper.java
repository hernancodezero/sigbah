package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoDonacionBean;

/**
 * @className: HistorialReporIngSalMapper.java
 * @description: 
 * @date: 06 de set. de 2017
 * @author: ARCHY.
 */
public class HistorialReporIngSalMapper implements RowMapper<ProductoDonacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoDonacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoDonacionBean productoDonacionBean = new ProductoDonacionBean();
		productoDonacionBean.setNombreCategoria(rs.getString("NOM_CATEGORIA"));	
		productoDonacionBean.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		productoDonacionBean.setCantidadIngreso(rs.getBigDecimal("CANTIDAD_INGRESO"));
		productoDonacionBean.setImporteIngreso(rs.getBigDecimal("IMPORTE_TOTAL_INGRESO"));
		productoDonacionBean.setCantidadSalida(rs.getBigDecimal("CANTIDAD_SALIDA"));
		productoDonacionBean.setImporteSalida(rs.getBigDecimal("IMPORTE_TOTAL_SALIDA"));
		productoDonacionBean.setSaldo(rs.getBigDecimal("SALDO"));
		return productoDonacionBean;
	}

}
