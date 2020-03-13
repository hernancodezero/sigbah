package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DetalleManifiestoCargaBean;

/**
 * @className: DetalleManifiestoCargaMapper.java
 * @description: 
 * @date: 22 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class DetalleManifiestoCargaMapper implements RowMapper<DetalleManifiestoCargaBean> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DetalleManifiestoCargaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DetalleManifiestoCargaBean manifiesto = new DetalleManifiestoCargaBean();
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
		manifiesto.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		manifiesto.setNombreUnidad(rs.getString("NOM_UNIDAD"));		
		manifiesto.setCantidad(rs.getBigDecimal("CANTIDAD"));
		manifiesto.setPesoUnitario(rs.getBigDecimal("PESO_UNIT_BRUTO"));
		manifiesto.setPesoTotal(rs.getBigDecimal("PESO_TOTAL"));
		manifiesto.setVolumenUnitario(rs.getBigDecimal("VOLUMEN_UNIT"));
		manifiesto.setVolumenTotal(rs.getBigDecimal("VOLUMEN_TOTAL"));
		manifiesto.setVersion(rs.getString("VERSION"));
		manifiesto.setObservacionManifiesto(rs.getString("OBSERVACION_MANIFIESTO"));
		return manifiesto;
	}

}
