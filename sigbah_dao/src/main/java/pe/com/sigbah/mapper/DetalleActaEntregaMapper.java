package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DetalleActaEntregaBean;

/**
 * @className: DetalleActaEntregaMapper.java
 * @description: 
 * @date: 22 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class DetalleActaEntregaMapper implements RowMapper<DetalleActaEntregaBean> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DetalleActaEntregaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DetalleActaEntregaBean acta = new DetalleActaEntregaBean();
		acta.setIdSalida(rs.getInt("IDE_SALIDA"));
		acta.setIdGuiaRemision(rs.getInt("IDE_GUIA_REMISION"));
		acta.setNroActa(rs.getString("NRO_ACTA"));
		acta.setAlmacenDestino1(rs.getString("ALMACEN_DESTINO1"));
		acta.setFechaEmisionActa(rs.getString("FECHA_EMISION_ACTA"));
		acta.setNombreProducto(rs.getString("NOM_PRODUCTO"));		
		acta.setUnidadMedida(rs.getString("UNIDAD_MEDIDA"));
		acta.setCantidad(rs.getBigDecimal("CANTIDAD"));
		acta.setNroGuiaRemision(rs.getString("NRO_GUIA_REMISION"));
		acta.setFechaEmisionGuia(rs.getString("FECHA_EMISION_GUIA"));
		acta.setAlmacenDestino2(rs.getString("ALMACEN_DESTINO2"));		
		acta.setObservacionActa(rs.getString("OBSERVACION_ACTA"));
		acta.setVersion(rs.getString("VERSION"));
		return acta;
	}

}
