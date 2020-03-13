package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DonacionesSalidaBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.DateUtil;

/**
 * @className: RegistroDonacionSalidaMapper.java
 * @description: 
 * @date: 29 de jul. de 2017
 * @author: ARCHY
 */
public class RegistroDonacionSalida1Mapper implements RowMapper<DonacionesSalidaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DonacionesSalidaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DonacionesSalidaBean donacionesBean = new DonacionesSalidaBean();
		donacionesBean.setIdSalida(rs.getInt("IDE_SALIDA"));
		donacionesBean.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		donacionesBean.setNroOrden(rs.getString("NRO_ORDEN"));
		donacionesBean.setNroOrdenSalida(rs.getString("NRO_ORDEN_SALIDA"));
		donacionesBean.setCodigoAnio(rs.getString("COD_ANIO"));
		donacionesBean.setNombreDdi(rs.getString("NOM_DDI"));
		donacionesBean.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		donacionesBean.setFechaEmision(rs.getString("FECHA_EMISION"));
		donacionesBean.setIdTipoMovimiento(rs.getInt("FK_IDE_TIP_MOVIMIENTO"));		
		donacionesBean.setIdEstado(rs.getInt("FK_IDE_ESTADO"));		
		donacionesBean.setIdSolicitante(rs.getInt("FK_ID_SOLICITANTE"));		
		donacionesBean.setIdResponsable(rs.getInt("FK_ID_RESPONSABLE"));
		donacionesBean.setIdDdiDestino(rs.getInt("IDE_DDI_DESTINO"));
		donacionesBean.setIdAlmacenDestino(rs.getInt("FK_IDE_ALM_DESTINO"));
		donacionesBean.setIdResponsableRecepcion(rs.getInt("FK_ID_RESPONSABLE_RECEPCION"));
		donacionesBean.setFlagTipoDestino(rs.getString("FLG_TIP_DESTINO"));
		donacionesBean.setCodigoUbigeo(rs.getString("COD_UBIGEO"));
		donacionesBean.setCodRegion(rs.getString("CODIGO_REGION"));
		donacionesBean.setCodDepartamento(rs.getString("CODIGO_DEPARTAMENTO"));
		donacionesBean.setCodProvincia(rs.getString("CODIGO_PROVINCIA"));
		donacionesBean.setCodDistrito(rs.getString("CODIGO_DISTRITO"));
		donacionesBean.setIdAlmacenDestinoExt(rs.getInt("FK_IDE_ALM_DESTINO_EXT"));
		donacionesBean.setIdResponsableExt(rs.getInt("FK_IDE_RESPONSABLE_EXT"));
		donacionesBean.setIdMedTransporte(rs.getInt("FK_IDE_MED_TRANSPORTE"));
		donacionesBean.setIdEmpresaTrans(rs.getInt("FK_IDE_EMP_TRANSPORTE"));
		donacionesBean.setNroPlaca(rs.getString("NRO_PLACA"));
		donacionesBean.setIdChofer(rs.getInt("IDE_CHOFER"));
		donacionesBean.setFechaEntrega(rs.getString("FECHA_ENTREGA"));
		donacionesBean.setObservacion(rs.getString("OBSERVACION")!=null?rs.getString("OBSERVACION").replaceAll("[\n]", "<<E>>").replaceAll("\"", "<<D>>").replaceAll("\'", "<<S>>"):"");		
		
		return donacionesBean;
	}

}
