package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.DonacionesBean;

/**
 * @className: ConsultaResolucionesDonacionMapper.java
 * @description: 
 * @date: 18 de set. de 2017
 * @author: ARCHY.
 */
public class ConsultaResolucionesDonacionMapper implements RowMapper<DonacionesBean> {
	

	@Override
	public DonacionesBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DonacionesBean donacionesBean = new DonacionesBean();
		donacionesBean.setFechaRj(rs.getString("FECHA_RJ"));
		donacionesBean.setNroRj(rs.getString("NRO_RJ"));
		donacionesBean.setCodigoAlfrescoRj(rs.getString("COD_ALFRESCO_RJ"));
		donacionesBean.setObservacion(rs.getString("OBSERVACION"));
		donacionesBean.setNombreDonante(rs.getString("NOM_DONANTE"));
		donacionesBean.setCodigoDonacion(rs.getString("COD_DONACION"));
		donacionesBean.setImporteMonedaOrigen(rs.getBigDecimal("IMPORTE_MONEDA_ORIGEN"));
		donacionesBean.setNroDocInafectacion(rs.getString("NRO_DOC_INAFECTACION"));
		donacionesBean.setCodigoAlfrescoInafectacion(rs.getString("COD_ALFRESCO_INAFECTACION"));
		donacionesBean.setFinalidad(rs.getString("FINALIDAD"));
		donacionesBean.setNombreSistema(rs.getString("NOMBRE_SISTEMA"));
		donacionesBean.setNombreArchivo(rs.getString("NOM_ARCHIVO"));

		return donacionesBean;
	}

}
