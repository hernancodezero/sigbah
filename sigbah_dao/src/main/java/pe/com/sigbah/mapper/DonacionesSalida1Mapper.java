package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DonacionesSalidaBean;

/**
 * @className: DonacionesSalidaMapper.java
 * @description: 
 * @date: 27 de jul. de 2017
 * @author: ARCHY.
 */
public class DonacionesSalida1Mapper implements RowMapper<DonacionesSalidaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DonacionesSalidaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DonacionesSalidaBean donacionesSalida = new DonacionesSalidaBean();
		donacionesSalida.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		donacionesSalida.setIdSalida(rs.getInt("IDE_SALIDA"));
		donacionesSalida.setCodigoAnio(rs.getString("COD_ANIO"));	
		donacionesSalida.setCodigoMes(rs.getString("Mes"));
		donacionesSalida.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		donacionesSalida.setNroOrdenSalida(rs.getString("NRO_ORDEN"));
		donacionesSalida.setFechaEmision(rs.getString("FECHA_EMISION"));
		donacionesSalida.setNombreMovimiento(rs.getString("NOM_MOVIMIENTO"));
		donacionesSalida.setNroGuiaRemision(rs.getString("NRO_GUIA_REMISION"));
		donacionesSalida.setNombreEstado(rs.getString("NOMBRE_ESTADO"));
		donacionesSalida.setNomAlmacenDestino(rs.getString("ALMACEN_DESTINO"));

		return donacionesSalida;
	}

}
