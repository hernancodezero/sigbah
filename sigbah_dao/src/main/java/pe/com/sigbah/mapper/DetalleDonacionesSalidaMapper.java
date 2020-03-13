package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DonacionesBean;
import pe.com.sigbah.common.bean.DonacionesIngresoBean;
import pe.com.sigbah.common.bean.DonacionesSalidaBean;
import pe.com.sigbah.common.bean.ProductoDonacionIngresoBean;

/**
 * @className: DetalleDonacionesSalidaMapper.java
 * @description: 
 * @date: 04 de ago. de 2017
 * @author: ARCHY.
 */
public class DetalleDonacionesSalidaMapper implements RowMapper<DonacionesSalidaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DonacionesSalidaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DonacionesSalidaBean donaciones = new DonacionesSalidaBean();
		donaciones.setIdSalida(rs.getInt("IDE_SALIDA"));
		donaciones.setNombreSistema(rs.getString("NOMBRE_SISTEMA"));
		donaciones.setNroOrdenSalida(rs.getString("ORDEN_SALIDA"));	
		donaciones.setFechaEmision(rs.getString("FECHA"));
		donaciones.setNombreDdi(rs.getString("NOMBRE_DDI"));
		donaciones.setNomAlmacenOrigen(rs.getString("ALMACEN_ORIGEN"));
		donaciones.setNombreMovimiento(rs.getString("NOMBRE_TIPO_MOVIMIENTO"));
		donaciones.setNomAlmacenDestino(rs.getString("NOMBRE_ALMACEN_DESTINO"));
		donaciones.setObservacion(rs.getString("OBSERVACION"));
		donaciones.setVersionSistema(rs.getString("VERSION_SISTEMA"));
		donaciones.setIdEstado(rs.getInt("FK_IDE_ESTADO"));
		return donaciones;
	}

}
