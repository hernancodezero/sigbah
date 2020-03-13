package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ManifiestoVehiculoBean;

/**
 * @className: ManifiestoVehiculoMapper.java
 * @description: 
 * @date: 22 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ManifiestoVehiculoMapper implements RowMapper<ManifiestoVehiculoBean> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ManifiestoVehiculoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ManifiestoVehiculoBean proyecto = new ManifiestoVehiculoBean();
		proyecto.setIdDetalleVehicular(rs.getInt("IDE_DET_PROYECTO_VEH"));
		proyecto.setIdProyectoManifiesto(rs.getInt("IDE_PROYECTO_MANIF"));
		proyecto.setFlagVehiculo(rs.getString("FLG_ACTIVO"));
		proyecto.setIdTipoCamion(rs.getInt("IDE_TIP_CAMION"));
		proyecto.setDescripcionCamion(rs.getString("DESCRIPCION"));
		proyecto.setTonelaje(rs.getBigDecimal("TONELAJE"));
		proyecto.setVolumen(rs.getBigDecimal("VOLUMEN"));		
		proyecto.setCantidadVehiculos(rs.getInt("CANT_VEHICULOS"));
		return proyecto;
	}

}
