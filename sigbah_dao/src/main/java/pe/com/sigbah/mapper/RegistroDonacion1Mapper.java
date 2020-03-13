package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DonacionesBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.DateUtil;

/**
 * @className: RegistroDonacionMapper.java
 * @description: 
 * @date: 17 de jul. de 2017
 * @author: ARCHY
 */
public class RegistroDonacion1Mapper implements RowMapper<DonacionesBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DonacionesBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DonacionesBean donacionesBean = new DonacionesBean();
		donacionesBean.setIdDonacion(rs.getInt("IDE_DONACION"));
		donacionesBean.setCodigoDonacion(rs.getString("COD_DONACION"));
		donacionesBean.setFechaEmision(rs.getString("FEC_EMISION"));
		donacionesBean.setIdEstado(rs.getInt("FK_IDE_ESTADO"));
		donacionesBean.setCodigoAnio(rs.getString("COD_ANIO"));
		donacionesBean.setIdDee(rs.getInt("IDE_DEE"));
		donacionesBean.setNombreDeclaratoria(rs.getString("NOM_DECLARATORIA"));
		donacionesBean.setTipoDonante(rs.getString("TIP_DONANTE"));
		donacionesBean.setTipoDonacion(rs.getString("TIPO_DONACION"));		
		donacionesBean.setTipoOrigenDonante(rs.getString("TIP_ORIGEN_DONANTE"));		
		donacionesBean.setIdPaisDonante(rs.getInt("FK_IDE_PAIS_DONANTE"));		
		donacionesBean.setTipoDonante(rs.getString("TIP_DONANTE"));
		donacionesBean.setIdDonante(rs.getInt("FK_IDE_DONANTE"));
		donacionesBean.setNombreDonante(rs.getString("NOM_DONANTE"));
		donacionesBean.setRepresentante(rs.getString("REPRESENTANTE"));
		donacionesBean.setObservacion(rs.getString("OBSERVACION")!=null?rs.getString("OBSERVACION").replaceAll("[\n]", "<<E>>").replaceAll("\"", "<<D>>").replaceAll("\'", "<<S>>"):"");		
		donacionesBean.setFinalidad(rs.getString("FINALIDAD")!=null?rs.getString("FINALIDAD").replaceAll("[\n]", "<<E>>").replaceAll("\"", "<<D>>").replaceAll("\'", "<<S>>"):"");		
		donacionesBean.setIdOficina(rs.getInt("FK_IDE_OFICINA"));
		donacionesBean.setIdPersonal(rs.getInt("FK_IDE_PERSONAL"));
		donacionesBean.setTextoa(rs.getString("BLOQUE_TEXTO1")!=null?rs.getString("BLOQUE_TEXTO1").replaceAll("[\n]", "<<E>>").replaceAll("\"", "<<D>>").replaceAll("\'", "<<S>>"):"");		
		donacionesBean.setTextob(rs.getString("BLOQUE_TEXTO2")!=null?rs.getString("BLOQUE_TEXTO2").replaceAll("[\n]", "<<E>>").replaceAll("\"", "<<D>>").replaceAll("\'", "<<S>>"):"");		
		donacionesBean.setTextoCodigo(rs.getString("COD_DONACION_CONCATENADO"));

		return donacionesBean;
	}

}
