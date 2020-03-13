package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.GuiaRemisionBean;

/**
 * @className: GuiaRemisionMapper.java
 * @description: 
 * @date: 22 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class GuiaRemisionMapper implements RowMapper<GuiaRemisionBean> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public GuiaRemisionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		GuiaRemisionBean guia = new GuiaRemisionBean();
		guia.setIdGuiaRemision(rs.getInt("IDE_GUIA_REMISION"));
		guia.setCodigoAnio(rs.getString("COD_ANIO"));
		guia.setCodigoMes(rs.getString("COD_MES"));
		guia.setNombreMes(rs.getString("NOMBRE_MES"));
		guia.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		guia.setCodigoAlmacen(rs.getString("COD_ALMACEN"));		
		guia.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		guia.setNroOrdenSalida(rs.getString("NRO_ORDEN_SALIDA"));
		guia.setNroGuiaRemision(rs.getString("NRO_GUIA_REMISION"));
		guia.setNroManifiestoCarga(rs.getString("NRO_MANIFIESTO"));
		guia.setNroActaEntregaRecepcion(rs.getString("NRO_ACTA"));		
		guia.setFechaEmision(rs.getString("FECHA_EMISION"));
		guia.setIdMovimiento(rs.getInt("IDE_TIP_MOVIMIENTO"));
		guia.setNombreMovimiento(rs.getString("NOM_MOVIMIENTO"));
		guia.setIdEstado(rs.getInt("IDE_ESTADO"));
		guia.setNombreEstado(rs.getString("NOMBRE_ESTADO"));
		return guia;
	}

}
