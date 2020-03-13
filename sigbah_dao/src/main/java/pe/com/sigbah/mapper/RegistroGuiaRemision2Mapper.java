package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.GuiaRemisionBean;

/**
 * @className: RegistroGuiaRemision2Mapper.java
 * @description: 
 * @date: 18 de jul. de 2018
 * @author: ARCHY.
 */
public class RegistroGuiaRemision2Mapper implements RowMapper<GuiaRemisionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public GuiaRemisionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		GuiaRemisionBean guiaRemision = new GuiaRemisionBean();
		guiaRemision.setIdGuiaRemision(rs.getInt("IDE_GUIA_REMISION"));
		guiaRemision.setIdSalida(rs.getInt("FK_IDE_SALIDA"));
		guiaRemision.setNroOrdenSalida(rs.getString("NRO_ORDEN_SALIDA"));
		guiaRemision.setNroGuiaRemision(rs.getString("NRO_GUIA_REMISION"));
		guiaRemision.setNroManifiestoCarga(rs.getString("NRO_MANIFIESTO"));
		guiaRemision.setFechaEmision(rs.getString("FEC_EMISION"));
		guiaRemision.setNombreMovimiento(rs.getString("TIP_MOVIMIENTO"));
		guiaRemision.setIdDdi(rs.getInt("IDE_DDI"));
		guiaRemision.setCodigoDdi(rs.getString("COD_DDI"));
		guiaRemision.setNombreDdi(rs.getString("NOMBRE_DDI"));
		guiaRemision.setIdAlmacen(rs.getInt("IDE_ALMACEN"));
		guiaRemision.setCodigoAlmacen(rs.getString("COD_ALMACEN"));		
		guiaRemision.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));		
		guiaRemision.setCodigoAnio(rs.getString("COD_ANIO"));
		guiaRemision.setCodigoMes(rs.getString("COD_MES"));		
		guiaRemision.setIdEstado(rs.getInt("FK_IDE_ESTADO"));
		guiaRemision.setNombreEstado(rs.getString("NOMBRE_ESTADO"));
		guiaRemision.setObservacionGuiaRemision(rs.getString("OBSERVACION_GUIA"));
		guiaRemision.setObservacionManifiestoCarga(rs.getString("OBSERVACION_MANIFIESTO"));
		guiaRemision.setNroActaEntregaRecepcion(rs.getString("NRO_ACTA"));
		guiaRemision.setObservacionActaEntregaRecepcion(rs.getString("OBSERVACION_ACTA"));
		guiaRemision.setIdMotivoTraslado(rs.getInt("FK_IDE_MOTIVO_TRASLADO"));
		guiaRemision.setMotivoTraslado(rs.getString("MOTIVO_TRASLADO"));
		guiaRemision.setFechaEntrega(rs.getString("FEC_ACTA_ENTREGA"));
		guiaRemision.setFechaEmisionN(rs.getString("FEC_EMISION_OS"));
		guiaRemision.setFechaTrasladoN(rs.getString("FEC_TRASLADO"));
		return guiaRemision;
	}

}
