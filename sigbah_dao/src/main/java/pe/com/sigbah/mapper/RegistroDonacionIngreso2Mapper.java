package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DonacionesIngresoBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.DateUtil;

/**
 * @className: RegistroDonacionIngresoMapper.java
 * @description: 
 * @date: 21 de jul. de 2017
 * @author: ARCHY
 */
public class RegistroDonacionIngreso2Mapper implements RowMapper<DonacionesIngresoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DonacionesIngresoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DonacionesIngresoBean donacionesBean = new DonacionesIngresoBean();
		donacionesBean.setIdIngreso(rs.getInt("IDE_INGRESO"));
		donacionesBean.setNroOrdenIngreso(rs.getString("NRO_ORDEN_INGRESO"));
		donacionesBean.setFechaEmision(rs.getString("FECHA"));
		donacionesBean.setIdTipoMovimiento(rs.getInt("FK_IDE_TIP_MOVIMIENTO"));
		donacionesBean.setIdEstado(rs.getInt("FK_IDE_ESTADO"));
		donacionesBean.setCodigoAnio(rs.getString("ANIO"));
		donacionesBean.setIdDdi(rs.getInt("DDI"));
		donacionesBean.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		donacionesBean.setIdDonacion(rs.getInt("FK_IDE_DONACION"));		
		donacionesBean.setTipoDonacion(rs.getString("TIPO_DONACION"));		
		donacionesBean.setNombreTipoDonacion(rs.getString("NOMBRE_TIPO_DONACION"));		
		donacionesBean.setIdControlCalidad(rs.getInt("FK_IDE_CONTROL_CALIDAD"));
		donacionesBean.setIdAlmacenProcedencia(rs.getInt("FK_ID_ALM_PROCEDENCIA"));
		donacionesBean.setNombreDonante(rs.getString("NOM_DONANTE"));
		donacionesBean.setRepresentante(rs.getString("REPRESENTANTE_DONACION"));
		donacionesBean.setIdMedTransporte(rs.getInt("FK_IDE_MED_TRANSPORTE"));
		donacionesBean.setIdEmpresaTrans(rs.getInt("FK_IDE_EMP_TRANS"));
		donacionesBean.setNroPlaca(rs.getString("NRO_PLACA"));
		donacionesBean.setIdChofer(rs.getInt("FK_IDE_CHOFER"));
		donacionesBean.setFechaLlegada(rs.getString("FECHA_LLEGADA"));
		donacionesBean.setIdResponsable(rs.getInt("FK_IDE_RESPONSABLE"));
		donacionesBean.setObservacion(rs.getString("OBSERVACION")!=null?rs.getString("OBSERVACION").replaceAll("[\n]", "<<E>>").replaceAll("\"", "<<D>>").replaceAll("\'", "<<S>>"):"");		
//		donacionesBean.setTextoCodigo(rs.getString("COD_DONACION_CONCATENADO"));
		donacionesBean.setIdSalida(rs.getInt("FK_IDE_SALIDA"));
		donacionesBean.setAnioAceptacion(rs.getString("ANIO_ACEPTO_DONACION"));
		donacionesBean.setIdDdiAceptacion(rs.getInt("ID_DDI_DONDE_ACEPTO_DONACION"));
		return donacionesBean;
	}

}
