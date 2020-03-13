package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DetalleActaEntregaBean;

/**
 * @className: DetalleActaEntregaDetalleMapper.java
 * @description: 
 * @date: 23 de oct. de 2017
 * @author: ARCHY.
 */
public class DetalleActaEntregaDetalleMapper implements RowMapper<DetalleActaEntregaBean> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DetalleActaEntregaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DetalleActaEntregaBean acta = new DetalleActaEntregaBean();

		acta.setNombreProducto(rs.getString("NOM_PRODUCTO"));		
		acta.setUnidadMedida(rs.getString("UNIDAD_MEDIDA"));
		acta.setCantidad(rs.getBigDecimal("CANTIDAD"));
		
		return acta;
	}

}
