package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DetalleGuiaRemisionBean;

/**
 * @className: DetalleGuiaRemisionCabeceraMapper.java
 * @description: 
 * @date: 04 de ago. de 2017
 * @author: ARCHY.
 */
public class DetalleGuiaRemisionCabecera1Mapper implements RowMapper<DetalleGuiaRemisionBean> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DetalleGuiaRemisionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DetalleGuiaRemisionBean guia = new DetalleGuiaRemisionBean();

		guia.setNroGuiaEmision(rs.getString("NRO_GUIA_REMISION"));
		guia.setDireccion(rs.getString("DIRECCION"));
		guia.setPuntoPartida(rs.getString("PUNTO_PARTIDA"));
		guia.setPuntoLlegada(rs.getString("PUNTO_LLEGADA"));
		guia.setFechaEmision(rs.getString("FECHA_EMISION"));		
		guia.setFechaInicioTraslado(rs.getString("FECHA_INICIO_TRASLADO"));
		guia.setRazonSocialDestino(rs.getString("RAZON_SOCIAL_DESTINO"));
		guia.setRucDestino(rs.getString("RUC_DESTINO"));
		guia.setEmpresaTransporte(rs.getString("EMPRESA_TRANSPORTE"));
		guia.setRucEmpresaTransporte(rs.getString("RUC_EMPRESA_TRANSPORTE"));
		guia.setNombreChofer(rs.getString("NOMBRE_CHOFER"));
		guia.setNroLicenciaConducir(rs.getString("NRO_LICENCIA_CONDUCIR"));
		guia.setNroPlaca(rs.getString("NRO_PLACA"));
		guia.setObservacionGuia(rs.getString("OBSERVACION_GUIA"));
		guia.setTipoMovimiento(rs.getString("TIPO_MOVIMIENTO"));
		guia.setIdMotivoTraslado(rs.getInt("FK_IDE_MOTIVO_TRASLADO"));
		guia.setMotivoTraslado(rs.getString("MOTIVO_TRASLADO"));
		guia.setIdEstado(rs.getInt("FK_IDE_ESTADO"));
		
		guia.setIdSalida(rs.getInt("IDE_SALIDA"));
		guia.setDireccionFiscal(rs.getString("DIRECCION_FISCAL"));
		guia.setNombreImprenta(rs.getString("NOMBRE_IMPRENTA"));
		guia.setRucImprenta(rs.getString("RUC_IMPRENTA"));
		guia.setNroAutorizacion(rs.getString("NRO_AUTORIZACION"));
		guia.setNroSerie(rs.getString("NRO_SERIE"));
		guia.setFechaImpresion(rs.getString("FECHA_IMPRESION"));
		
		return guia;
	}

}
