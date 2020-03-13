package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DetalleManifiestoCargaBean;

/**
 * @className: CabeceraManifiestoCargaDonMapper.java
 * @description: 
 * @date: 09 de oct. de 2017
 * @author: ARCHY.
 */
public class CabeceraManifiestoCargaDonMapper implements RowMapper<DetalleManifiestoCargaBean> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DetalleManifiestoCargaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DetalleManifiestoCargaBean manifiesto = new DetalleManifiestoCargaBean();
		manifiesto.setVersion(rs.getString("VERSION"));
		manifiesto.setIdSalida(rs.getInt("IDE_SALIDA"));
		manifiesto.setIdGuiaRemision(rs.getInt("IDE_GUIA_REMISION"));
		manifiesto.setNroManifiestoCarga(rs.getString("NRO_MANIFIESTO_CARGA"));
		manifiesto.setAlmacenOrigen(rs.getString("ALMACEN_ORIGEN"));
		manifiesto.setFechaEmision(rs.getString("FECHA_EMISION"));
		manifiesto.setDdiRegionDestino(rs.getString("DDI_REGION_DESTINO"));
		manifiesto.setAlmacenDestino(rs.getString("ALMACEN_DESTINO"));
		manifiesto.setReferencia(rs.getString("REFERENCIA"));
		manifiesto.setTipoTransporte(rs.getString("TIPO_TRANSPORTE"));
		manifiesto.setNombreChofer(rs.getString("NOMBRE_CHOFER"));
		manifiesto.setNroPlacaVehiculo(rs.getString("NRO_PLACA_VEHICULO"));
		manifiesto.setObservacionManifiesto(rs.getString("OBSERVACION_MANIFIESTO"));
		
		return manifiesto;
	}

}
