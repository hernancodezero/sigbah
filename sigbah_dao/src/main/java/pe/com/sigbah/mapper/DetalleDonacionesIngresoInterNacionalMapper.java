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
public class DetalleDonacionesIngresoInterNacionalMapper implements RowMapper<DonacionesIngresoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DonacionesIngresoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DonacionesIngresoBean donaciones = new DonacionesIngresoBean();
		donaciones.setNombreDdi(rs.getString("CIUDAD"));
		donaciones.setDia(rs.getString("DIA"));
		donaciones.setMes(rs.getString("MES"));	
		donaciones.setAnio(rs.getString("AÑO"));
		donaciones.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		donaciones.setNombreDonante(rs.getString("NOM_DONANTE"));
		donaciones.setIdDonacion(rs.getInt("FK_IDE_DONACION"));
		donaciones.setNroDocumento(rs.getString("NRO_DOCUMENTO"));
		donaciones.setResponsableAlmacen(rs.getString("RESPONSABLE_ALMACEN"));
		donaciones.setNombreSistema(rs.getString("NOMBRE_SISTEMA"));
		donaciones.setVersionSistema(rs.getString("VERSION_SISTEMA"));
		
		return donaciones;
	}

}
