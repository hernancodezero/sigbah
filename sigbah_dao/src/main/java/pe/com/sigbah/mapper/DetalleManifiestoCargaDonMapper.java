package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DetalleManifiestoCargaBean;

/**
 * @className: DetalleManifiestoCargaDonMapper.java
 * @description: 
 * @date: 09 de oct. de 2017
 * @author: ARCHY.
 */
public class DetalleManifiestoCargaDonMapper implements RowMapper<DetalleManifiestoCargaBean> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DetalleManifiestoCargaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DetalleManifiestoCargaBean manifiesto = new DetalleManifiestoCargaBean();

		manifiesto.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		manifiesto.setNombreUnidad(rs.getString("NOM_UNIDAD"));		
		manifiesto.setCantidad(rs.getBigDecimal("CANTIDAD"));
		manifiesto.setPesoUnitario(rs.getBigDecimal("PESO_UNIT_BRUTO"));
		manifiesto.setPesoTotal(rs.getBigDecimal("PESO_TOTAL"));
		manifiesto.setVolumenUnitario(rs.getBigDecimal("VOLUMEN_UNIT"));
		manifiesto.setVolumenTotal(rs.getBigDecimal("VOLUMEN_TOTAL"));
//		manifiesto.setVersion(rs.getString("VERSION"));
//		manifiesto.setObservacionManifiesto(rs.getString("OBSERVACION_MANIFIESTO"));
		return manifiesto;
	}

}
