package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.OrdenSalidaBean;

/**
 * @className: ReporteOrdenSalidaMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ReporteOrdenSalidaMapper implements RowMapper<OrdenSalidaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public OrdenSalidaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrdenSalidaBean ordenSalida = new OrdenSalidaBean();
		ordenSalida.setIdSalida(rs.getInt("IDE_SALIDA"));
		ordenSalida.setNombreDdi(rs.getString("NOMBRE_DDI"));		
		ordenSalida.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		ordenSalida.setFechaEmision(rs.getString("FECHA_EMISION"));
		ordenSalida.setNroOrdenSalida(rs.getString("NRO_ORDEN_SALIDA"));
		ordenSalida.setNroGuiaRemision(rs.getString("NRO_GUIA_REMISION"));
		ordenSalida.setNombreMovimiento(rs.getString("TIPO_MOVIMIENTO"));
		ordenSalida.setNombreAlmacenDestino(rs.getString("ALMACEN_DESTINO"));
		ordenSalida.setImporteTotal(rs.getBigDecimal("IMPORTE"));
		ordenSalida.setPesoTotalKgr(rs.getBigDecimal("PESO_TOTAL_KGRL"));
		ordenSalida.setNombreEstado(rs.getString("NOMBRE_ESTADO"));
		return ordenSalida;
	}

}
