package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.OrdenSalidaBean;

/**
 * @className: OrdenSalidaGeneralMapper.java
 * @description: 
 * @date: 22 de nov. de 2017
 * @author: ARCHY.
 */
public class OrdenSalidaGeneral1Mapper implements RowMapper<OrdenSalidaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public OrdenSalidaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrdenSalidaBean ordenSalida = new OrdenSalidaBean();
		ordenSalida.setIdSalida(rs.getInt("IDE_SALIDA"));
		ordenSalida.setNombreSistema(rs.getString("NOMBRE_SISTEMA"));
		ordenSalida.setNroOrdenSalida(rs.getString("ORDEN_SALIDA"));
		ordenSalida.setFechaEmision(rs.getString("FECHA"));
		ordenSalida.setNombreDdi(rs.getString("NOMBRE_DDI"));
		ordenSalida.setNombreAlmacen(rs.getString("ALMACEN_ORIGEN"));
		ordenSalida.setNombreMovimiento(rs.getString("NOMBRE_TIPO_MOVIMIENTO"));
		ordenSalida.setNombreAlmacenDestino(rs.getString("NOMBRE_ALMACEN_DESTINO"));
		ordenSalida.setObservacion(rs.getString("OBSERVACION")!=null?rs.getString("OBSERVACION").replaceAll("[\n]", "<<E>>").replaceAll("\"", "<<D>>").replaceAll("\'", "<<S>>"):"");	
		ordenSalida.setIdEstado(rs.getInt("FK_IDE_ESTADO"));
		return ordenSalida;
	}

}
