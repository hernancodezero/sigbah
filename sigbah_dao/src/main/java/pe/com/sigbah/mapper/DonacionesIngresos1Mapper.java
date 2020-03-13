package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DonacionesBean;
import pe.com.sigbah.common.bean.DonacionesIngresoBean;

/**
 * @className: ControlCalidadMapper.java
 * @description: 
 * @date: 21 de jul. de 2017
 * @author: ARCHY.
 */
public class DonacionesIngresos1Mapper implements RowMapper<DonacionesIngresoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DonacionesIngresoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DonacionesIngresoBean donaciones = new DonacionesIngresoBean();
		donaciones.setIdIngreso(rs.getInt("IDE_INGRESO"));
		donaciones.setCodigoAnio(rs.getString("COD_ANIO"));
		donaciones.setCodigoMes(rs.getString("NOMBRE_MES"));	
		donaciones.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		donaciones.setIdDonacion(rs.getInt("FK_IDE_DONACION"));
		donaciones.setNroOrdenIngreso(rs.getString("NRO_INGRESO_CONCATENADO"));
		donaciones.setFechaEmision(rs.getString("FECHA"));
		donaciones.setNombreMovimiento(rs.getString("NOM_MOVIMIENTO"));
		donaciones.setNombreEstado(rs.getString("ESTADO"));
		donaciones.setIdMovimiento(rs.getInt("FK_IDE_TIP_MOVIMIENTO"));
		donaciones.setNombreAlmacenProcedencia(rs.getString("ALMACEN_PROCEDENCIA"));
		
		return donaciones;
	}

}
