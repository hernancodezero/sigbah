package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DetalleGuiaRemisionBean;

/**
 * @className: DetalleGuiaRemisionDetalleMapper.java
 * @description: 
 * @date: 04 de ago. de 2017
 * @author: ARCHY.
 */
public class DetalleGuiaRemisionDetalleMapper implements RowMapper<DetalleGuiaRemisionBean> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DetalleGuiaRemisionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DetalleGuiaRemisionBean guia = new DetalleGuiaRemisionBean();

		guia.setIdSalida(rs.getInt("IDE_SALIDA"));
		guia.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		guia.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		guia.setCantidad(rs.getBigDecimal("CANTIDAD"));
		guia.setPesoTotal(rs.getBigDecimal("PESO_TOTAL"));		
		guia.setUnidadMedida(rs.getString("UNIDAD_MEDIDA"));

		return guia;
	}

}
