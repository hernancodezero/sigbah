package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.GuiaRemisionBean;

/**
 * @className: ReporteGuiaRemisionMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ReporteGuiaRemisionMapper implements RowMapper<GuiaRemisionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public GuiaRemisionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		GuiaRemisionBean guiaRemision = new GuiaRemisionBean();
		guiaRemision.setIdSalida(rs.getInt("IDE_SALIDA"));
		guiaRemision.setNombreDdi(rs.getString("NOMBRE_DDI"));		
		guiaRemision.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		guiaRemision.setFechaEmision(rs.getString("FECHA_EMISION"));
		guiaRemision.setNroOrdenSalida(rs.getString("NRO_ORDEN_SALIDA"));
		guiaRemision.setNroGuiaRemision(rs.getString("NRO_GUIA_REMISION"));		
		guiaRemision.setNroManifiestoCarga(rs.getString("NRO_MANIFIESTO"));
		guiaRemision.setNroActaEntregaRecepcion(rs.getString("NRO_ACTA"));		
		guiaRemision.setNombreMovimiento(rs.getString("TIPO_MOVIMIENTO"));
		guiaRemision.setNombreAlmacenDestino(rs.getString("ALMACEN_DESTINO"));
		guiaRemision.setPesoTotalKgr(rs.getBigDecimal("PESO_TOTAL_KGRL"));
		guiaRemision.setNombreEstado(rs.getString("NOMBRE_ESTADO"));
		return guiaRemision;
	}

}
