package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.CierreStockBean;

/**
 * @className: RegistroCierreStockMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class RegistroCierreStockMapper implements RowMapper<CierreStockBean> {

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
		cierreStock.setIdResponsable(rs.getInt("FK_IDE_RESPONSABLE_ALM"));
		cierreStock.setResponsable(rs.getString("RESPONSABLE"));
		cierreStock.setFlagCierreAlmacen(rs.getString("FLG_CIERRE_ALM"));		
		cierreStock.setNombreEstado(rs.getString("ESTADO"));
		cierreStock.setObservacion(rs.getString("OBSERVACION"));
		return cierreStock;
	}

}
