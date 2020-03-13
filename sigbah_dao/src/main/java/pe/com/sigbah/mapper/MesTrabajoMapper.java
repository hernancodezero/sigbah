package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.CierreStockBean;

/**
 * @className: MesTrabajoMapper.java
 * @description: 
 * @date: 09 de ago. de 2017
 * @author: Junior Huaman Flores.
 */
public class MesTrabajoMapper implements RowMapper<CierreStockBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public CierreStockBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		CierreStockBean cierreStock = new CierreStockBean();
		cierreStock.setCodigoAnio(rs.getString("COD_ANIO"));
		cierreStock.setCodigoMes(rs.getString("COD_MES"));
		cierreStock.setNombreMes(rs.getString("NOMBRE_MES"));
		cierreStock.setIdAlmacen(rs.getInt("IDE_ALMACEN"));
		cierreStock.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));		
		return cierreStock;
	}

}
