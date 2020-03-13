package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DetalleGuiaRemisionBean;

/**
 * @className: ReporteDetalleGuiaRemisionMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ReporteDetalleGuiaRemisionMapper implements RowMapper<DetalleGuiaRemisionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DetalleGuiaRemisionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DetalleGuiaRemisionBean detalleGuiaRemision = new DetalleGuiaRemisionBean();
		detalleGuiaRemision.setIdSalida(rs.getInt("IDE_SALIDA"));
		detalleGuiaRemision.setNombreDdi(rs.getString("NOMBRE_DDI"));		
		detalleGuiaRemision.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		detalleGuiaRemision.setFechaEmision(rs.getString("FECHA_EMISION"));
		detalleGuiaRemision.setNroOrdenSalida(rs.getString("NRO_ORDEN_SALIDA"));
		detalleGuiaRemision.setNroGuiaRemision(rs.getString("NRO_GUIA_REMISION"));		
		detalleGuiaRemision.setNroManifiestoCarga(rs.getString("NRO_MANIFIESTO"));
		detalleGuiaRemision.setNroActa(rs.getString("NRO_ACTA"));		
		detalleGuiaRemision.setNombreMovimiento(rs.getString("TIPO_MOVIMIENTO"));
		detalleGuiaRemision.setNombreEstado(rs.getString("NOMBRE_ESTADO"));
		detalleGuiaRemision.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		detalleGuiaRemision.setUnidadMedida(rs.getString("NOMBRE_UNIDAD"));		
		detalleGuiaRemision.setCantidad(rs.getBigDecimal("CANTIDAD"));
		detalleGuiaRemision.setPesoTotal(rs.getBigDecimal("PESO_TOTAL"));		
		return detalleGuiaRemision;
	}

}
