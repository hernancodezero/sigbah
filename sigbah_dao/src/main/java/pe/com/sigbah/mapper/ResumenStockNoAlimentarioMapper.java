package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ResumenStockNoAlimentarioBean;

/**
 * @className: ResumenStockNoAlimentarioMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ResumenStockNoAlimentarioMapper implements RowMapper<ResumenStockNoAlimentarioBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ResumenStockNoAlimentarioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResumenStockNoAlimentarioBean resumen = new ResumenStockNoAlimentarioBean();
		resumen.setIdProducto(rs.getInt("ID_PRODUCTO"));
		resumen.setNombreProducto(rs.getString("NOMBRE_PRODUCTO"));
		resumen.setTotalStock(rs.getBigDecimal("TOTAL_STOCK"));
		resumen.setTotalConsumo(rs.getBigDecimal("TOTAL_CONSUMO"));
		resumen.setTotalSaldo(rs.getBigDecimal("TOTAL_SALDO"));
		return resumen;
	}

}
