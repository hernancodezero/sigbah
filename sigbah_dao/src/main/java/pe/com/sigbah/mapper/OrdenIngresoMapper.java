package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.OrdenIngresoBean;

/**
 * @className: OrdenIngresoMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class OrdenIngresoMapper implements RowMapper<OrdenIngresoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public OrdenIngresoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrdenIngresoBean ordenIngreso = new OrdenIngresoBean();
		ordenIngreso.setIdIngreso(rs.getInt("IDE_INGRESO"));
		ordenIngreso.setCodigoAnio(rs.getString("COD_ANIO"));
		ordenIngreso.setCodigoMes(rs.getString("COD_MES"));
		ordenIngreso.setNombreMes(rs.getString("NOMBRE_MES"));
		ordenIngreso.setNombreDdi(rs.getString("NOMBRE_DDI"));
		ordenIngreso.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		ordenIngreso.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));		
		ordenIngreso.setNroOrdenIngreso(rs.getString("NRO_ORDEN_INGRESO"));
		ordenIngreso.setFechaEmision(rs.getString("FECHA_EMISION"));
		ordenIngreso.setNombreMovimiento(rs.getString("NOMBRE_MOVIMIENTO"));
		ordenIngreso.setIdEstado(rs.getInt("IDE_ESTADO"));
		ordenIngreso.setNombreEstado(rs.getString("NOMBRE_ESTADO"));
		return ordenIngreso;
	}

}
