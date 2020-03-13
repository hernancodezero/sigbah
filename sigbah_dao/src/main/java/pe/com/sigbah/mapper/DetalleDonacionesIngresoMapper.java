package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DonacionesBean;
import pe.com.sigbah.common.bean.DonacionesIngresoBean;
import pe.com.sigbah.common.bean.ProductoDonacionIngresoBean;

/**
 * @className: DetalleDonacionesMapper.java
 * @description: 
 * @date: 03 de ago. de 2017
 * @author: ARCHY.
 */
public class DetalleDonacionesIngresoMapper implements RowMapper<DonacionesIngresoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DonacionesIngresoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DonacionesIngresoBean donaciones = new DonacionesIngresoBean();
		donaciones.setCodigoAnio(rs.getString("COD_ANIO"));
		donaciones.setNombreDdi(rs.getString("NOMBRE_DDI"));
		donaciones.setIdIngreso(rs.getInt("IDE_INGRESO"));	
		donaciones.setNroOrdenIngreso(rs.getString("NRO_INGRESO_CONCATENADO"));
		donaciones.setFechaEmision(rs.getString("FECHA"));
		donaciones.setNombreDdi(rs.getString("NOMBRE_DDI"));
		donaciones.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		donaciones.setNombreMovimiento(rs.getString("NOMBRE_MOVIMIENTO"));
		donaciones.setObservacion(rs.getString("OBSERVACION"));
		donaciones.setNombreSistema(rs.getString("NOMBRE_SISTEMA"));
		donaciones.setVersionSistema(rs.getString("VERSION_SISTEMA"));
		donaciones.setIdEstado(rs.getInt("FK_IDE_ESTADO"));
		return donaciones;
	}

}
