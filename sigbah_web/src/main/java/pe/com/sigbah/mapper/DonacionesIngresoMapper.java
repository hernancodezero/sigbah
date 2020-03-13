package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DonacionesIngresoBean;

/**
 * @className: DonacionesIngresoMapper.java
 * @description: 
 * @date: 20 de jul. de 2017
 * @author: ARCHY.
 */
public class DonacionesIngresoMapper implements RowMapper<DonacionesIngresoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DonacionesIngresoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DonacionesIngresoBean donacionesIngreso = new DonacionesIngresoBean();
		donacionesIngreso.setIdIngreso(rs.getInt("IDE_INGRESO"));
		donacionesIngreso.setCodigoAnio(rs.getString("COD_ANIO"));
		donacionesIngreso.setIdDdi(rs.getInt("IDE_DDI"));	
		donacionesIngreso.setCodigoDdi(rs.getString("COD_DDI"));
		donacionesIngreso.setNombreDdi(rs.getString("NOM_DDI"));
		donacionesIngreso.setCodAlmacen(rs.getString("COD_ALMACEN"));
		donacionesIngreso.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		donacionesIngreso.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		donacionesIngreso.setIdDonacion(rs.getInt("FK_IDE_DONACION"));
		donacionesIngreso.setNombreDonante(rs.getString("NOM_DONANTE"));
		donacionesIngreso.setNroOrdenIngreso(rs.getString("NRO_ORDEN_INGRESO"));
		donacionesIngreso.setFechaEmision(rs.getString("FEC_EMISION"));
		donacionesIngreso.setNombreMovimiento(rs.getString("NOM_MOVIMIENTO"));
		donacionesIngreso.setIdEstado(rs.getInt("ESTADO"));
		return donacionesIngreso;
	}

}
