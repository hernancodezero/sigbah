package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DonacionesBean;

/**
 * @className: DetalleDonacionesMapper.java
 * @description: 
 * @date: 03 de ago. de 2017
 * @author: ARCHY.
 */
public class DetalleDonaciones2Mapper implements RowMapper<DonacionesBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DonacionesBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DonacionesBean donaciones = new DonacionesBean();
		donaciones.setFinalidad(rs.getString("FINALIDAD"));
		donaciones.setTextoa(rs.getString("BLOQUE_TEXTO1"));
		donaciones.setTextob(rs.getString("BLOQUE_TEXTO2"));	
		donaciones.setFechaEmision(rs.getString("FECHA_EMISION"));
		donaciones.setIdDonante(rs.getInt("FK_IDE_DONANTE"));
		donaciones.setNombreDonante(rs.getString("NOM_DONANTE"));
		donaciones.setNumDocumento(rs.getString("NUM_DOCUMENTO"));
		donaciones.setObservacion(rs.getString("OBSERVACION"));
		donaciones.setIdDdi(rs.getInt("IDE_DDI"));
		donaciones.setCodigoAnio(rs.getString("COD_ANIO"));
		donaciones.setIdDee(rs.getInt("IDE_DEE"));
		donaciones.setCodigoDonacion(rs.getString("COD_DONACION"));
		donaciones.setIdPersonal(rs.getInt("FK_IDE_PERSONAL"));
		donaciones.setNombrePersonal(rs.getString("NOMBRE_PERSONAL"));
		donaciones.setIdOficina(rs.getInt("FK_IDE_OFICINA"));
		donaciones.setNombreOficina(rs.getString("NOMBRE_OFICINA"));
		donaciones.setNombreOrigen(rs.getString("ORIGEN_DONACION"));
		donaciones.setOficinaResponsable(rs.getString("OFICINA_RESPONSABLE"));
		donaciones.setNombreSistema(rs.getString("NOMBRE_SISTEMA"));
		donaciones.setVersionSistema(rs.getString("VERSION_SISTEMA"));
		donaciones.setFechaEvaluacion(rs.getString("FECHA_EVALUACION"));
		donaciones.setNombreEstado(rs.getString("ESTADO_EVALUACION"));
		donaciones.setIdEstado(rs.getInt("FK_IDE_ESTADO"));
		return donaciones;
	}

}
