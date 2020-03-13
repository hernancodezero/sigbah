package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.OrdenSalidaBean;

/**
 * @className: RegistroOrdenSalidaMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class RegistroOrdenSalida2Mapper implements RowMapper<OrdenSalidaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public OrdenSalidaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrdenSalidaBean ordenSalida = new OrdenSalidaBean();
		ordenSalida.setIdSalida(rs.getInt("IDE_SALIDA"));
		ordenSalida.setNroOrdenSalida(rs.getString("NRO_ORDEN_SALIDA"));
		ordenSalida.setCodigoAnio(rs.getString("COD_ANIO"));
		ordenSalida.setCodigoMes(rs.getString("COD_MES"));
		ordenSalida.setIdDdi(rs.getInt("IDE_DDI"));
		ordenSalida.setCodigoDdi(rs.getString("COD_DDI"));		
		ordenSalida.setNombreDdi(rs.getString("NOM_DDI"));
		ordenSalida.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		ordenSalida.setCodigoAlmacen(rs.getString("COD_ALMACEN"));		
		ordenSalida.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));		
		ordenSalida.setTipoOrigen(rs.getString("TIPO_ORIGEN"));
		ordenSalida.setFechaEmision(rs.getString("FECHA_EMISION"));
		ordenSalida.setIdEstado(rs.getInt("FK_IDE_ESTADO"));
		ordenSalida.setNombreEstado(rs.getString("NOMBRE_ESTADO"));
		ordenSalida.setIdMovimiento(rs.getInt("FK_IDE_TIP_MOVIMIENTO"));
		ordenSalida.setIdProyectoManifiesto(rs.getInt("FK_IDE_PROYECTO_MANIF"));
		ordenSalida.setIdProgramacion(rs.getInt("FK_IDE_PROGRAMACION"));		
		ordenSalida.setNroProyectoManifiesto(rs.getString("NRO_PROY_MANIFIESTO"));
		ordenSalida.setNroProgramacion(rs.getString("NRO_PROGRAMACION"));		
		ordenSalida.setIdSolicitante(rs.getInt("FK_ID_SOLICITANTE"));
		ordenSalida.setIdResponsable(rs.getInt("FK_ID_RESPONSABLE"));
		ordenSalida.setIdDdiDestino(rs.getInt("IDE_DDI_DESTINO"));
		ordenSalida.setIdAlmacenDestino(rs.getInt("FK_IDE_ALM_DESTINO"));
		ordenSalida.setIdResponsableRecepcion(rs.getInt("FK_ID_RESPONSABLE_RECEPCION"));
		ordenSalida.setFlagTipoDestino(rs.getString("FLG_TIP_DESTINO"));
		ordenSalida.setCodigoUbigeo(rs.getString("COD_UBIGEO"));
		ordenSalida.setCodigoRegion(rs.getString("CODIGO_REGION"));
		ordenSalida.setCodigoDepartamento(rs.getString("CODIGO_DEPARTAMENTO"));
		ordenSalida.setCodigoProvincia(rs.getString("CODIGO_PROVINCIA"));
		ordenSalida.setCodigoDistrito(rs.getString("CODIGO_DISTRITO"));
		ordenSalida.setIdAlmacenDestinoExt(rs.getInt("FK_IDE_ALM_DESTINO_EXT"));
		ordenSalida.setIdResponsableExt(rs.getInt("FK_IDE_RESPONSABLE_EXT"));
		ordenSalida.setIdMedioTransporte(rs.getInt("FK_IDE_MED_TRANSPORTE"));
		ordenSalida.setIdEmpresaTransporte(rs.getInt("FK_IDE_EMP_TRANSPORTE"));
		ordenSalida.setIdChofer(rs.getInt("IDE_CHOFER"));
		ordenSalida.setNroPlaca(rs.getString("NRO_PLACA"));
		ordenSalida.setFechaEntrega(rs.getString("FECHA_ENTREGA"));
		ordenSalida.setObservacion(rs.getString("OBSERVACION")!=null?rs.getString("OBSERVACION").replaceAll("[\n]", "<<E>>").replaceAll("\"", "<<D>>").replaceAll("\'", "<<S>>"):"");		
		ordenSalida.setNombreAlmacenDestino(rs.getString("NOMBRE_ALMACEN_DESTINO"));
		ordenSalida.setNombreMovimiento(rs.getString("NOMBRE_MOVIMIENTO"));
		ordenSalida.setFlagProyecto(rs.getString("FLG_PROY_MANIFIESTO"));
		return ordenSalida;
	}

}
