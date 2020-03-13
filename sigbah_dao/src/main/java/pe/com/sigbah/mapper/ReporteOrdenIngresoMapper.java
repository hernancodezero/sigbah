package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.OrdenIngresoBean;

/**
 * @className: ReporteOrdenIngresoMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ReporteOrdenIngresoMapper implements RowMapper<OrdenIngresoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public OrdenIngresoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrdenIngresoBean ordenIngreso = new OrdenIngresoBean();
		ordenIngreso.setIdIngreso(rs.getInt("IDE_INGRESO"));
		ordenIngreso.setNombreDdi(rs.getString("NOMBRE_DDI"));		
		ordenIngreso.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		ordenIngreso.setFechaEmision(rs.getString("FECHA_EMISION"));
		ordenIngreso.setNroOrdenIngreso(rs.getString("NRO_ORDEN_INGRESO"));
		ordenIngreso.setNroOrdenCompra(rs.getString("NRO_ORDEN_COMPRA"));
		ordenIngreso.setNombreMovimiento(rs.getString("TIPO_MOVIMIENTO"));
		ordenIngreso.setNombreAlmacenProcedencia(rs.getString("ALMACEN_ORIGEN"));
		ordenIngreso.setImporteTotal(rs.getBigDecimal("IMPORTE"));
		ordenIngreso.setPesoTotalKgr(rs.getBigDecimal("PESO_TOTAL_KGRL"));
		ordenIngreso.setNombreEstado(rs.getString("NOMBRE_ESTADO"));
		return ordenIngreso;
	}

}
