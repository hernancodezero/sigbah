package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DetalleGuiaRemisionBean;

/**
 * @className: DetalleGuiaRemisionMapper.java
 * @description: 
 * @date: 22 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class DetalleGuiaRemisionMapper implements RowMapper<DetalleGuiaRemisionBean> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DetalleGuiaRemisionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DetalleGuiaRemisionBean guia = new DetalleGuiaRemisionBean();
		guia.setItem(rs.getBigDecimal("ITEM"));
		guia.setIdSalida(rs.getInt("IDE_SALIDA"));
		guia.setNroGuiaEmision(rs.getString("NRO_GUIA_REMISION"));
		guia.setSede(rs.getString("SEDE"));
		guia.setDireccionPartida(rs.getString("DIRECCION_PARTIDA"));
		guia.setPuntoPartida(rs.getString("PUNTO_PARTIDA"));
		guia.setFechaEmision(rs.getString("FECHA_EMISION"));		
		guia.setFechaInicioTraslado(rs.getString("FECHA_INICIO_TRASLADO"));
		guia.setEmpresaTransporte(rs.getString("EMPRESA_TRANSPORTE"));
		guia.setRucEmpresaTransporte(rs.getString("RUC_EMPRESA_TRANSPORTE"));
		guia.setNombreChofer(rs.getString("NOMBRE_CHOFER"));
		guia.setNroLicenciaConducir(rs.getString("NRO_LICENCIA_CONDUCIR"));
		guia.setNroPlaca(rs.getString("NRO_PLACA"));
		guia.setPuntoLlegada(rs.getString("PUNTO_LLEGADA"));
		guia.setRazonSocialDestino(rs.getString("RAZON_SOCIAL_DESTINO"));
		guia.setRucDestino(rs.getString("RUC_DESTINO"));
		guia.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		guia.setCantidad(rs.getBigDecimal("CANTIDAD"));
		guia.setPesoTotal(rs.getBigDecimal("PESO_TOTAL"));
		guia.setUnidadMedida(rs.getString("UNIDAD_MEDIDA"));
		guia.setObservacionGuia(rs.getString("OBSERVACION_GUIA"));
		guia.setTipoMovimiento(rs.getString("TIPO_MOVIMIENTO"));
		guia.setIdMotivoTraslado(rs.getInt("FK_IDE_MOTIVO_TRASLADO"));
		guia.setMotivoTraslado(rs.getString("MOTIVO_TRASLADO"));
		return guia;
	}

}
