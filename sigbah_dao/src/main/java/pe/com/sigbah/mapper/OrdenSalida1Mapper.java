package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.OrdenSalidaBean;

/**
 * @className: OrdenSalidaMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class OrdenSalida1Mapper implements RowMapper<OrdenSalidaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public OrdenSalidaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrdenSalidaBean ordenSalida = new OrdenSalidaBean();
		ordenSalida.setIdSalida(rs.getInt("IDE_SALIDA"));
		ordenSalida.setCodigoAnio(rs.getString("COD_ANIO"));
		ordenSalida.setCodigoDdi(rs.getString("COD_DDI"));
		ordenSalida.setNombreDdi(rs.getString("NOM_DDI"));
		ordenSalida.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		ordenSalida.setCodigoAlmacen(rs.getString("COD_ALMACEN"));		
		ordenSalida.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		ordenSalida.setTipoOrigen(rs.getString("TIPO_ORIGEN"));		
		ordenSalida.setNroOrdenSalida(rs.getString("NRO_ORDEN_SALIDA"));
		ordenSalida.setFechaEmision(rs.getString("FECHA_EMISION"));
		ordenSalida.setIdMovimiento(rs.getInt("FK_IDE_TIP_MOVIMIENTO"));
		ordenSalida.setNombreMovimiento(rs.getString("NOM_MOVIMIENTO"));
		ordenSalida.setIdEstado(rs.getInt("FK_IDE_ESTADO"));
		ordenSalida.setNombreEstado(rs.getString("NOMBRE_ESTADO"));
		ordenSalida.setNombreEmpresa(rs.getString("NOM_EMPRESA"));
		ordenSalida.setNroGuiaRemision(rs.getString("NRO_GUIA_REMISION"));
		ordenSalida.setCodigoMes(rs.getString("COD_MES"));
		ordenSalida.setNombreMes(rs.getString("NOMBRE_MES"));
		ordenSalida.setNombreAlmacenDestino(rs.getString("ALMACEN_EXTERNO"));
		return ordenSalida;
	}

}
