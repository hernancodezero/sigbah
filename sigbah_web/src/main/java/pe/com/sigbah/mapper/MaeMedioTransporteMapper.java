package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.MaeAniosBean;
import pe.com.sigbah.common.bean.MaeMedioTransporteBean;

/**
 * @className: MaeAnioMapper.java
 * @description: 
 * @date: 15 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeMedioTransporteMapper implements RowMapper<MaeMedioTransporteBean> {
	

	@Override
	public MaeMedioTransporteBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeMedioTransporteBean maeMedioTransporteBean = new MaeMedioTransporteBean();

		maeMedioTransporteBean.setIdMedioTransporte(rs.getInt("IDE_MED_TRANSPORTE"));
		maeMedioTransporteBean.setNombreMedioTransporte(rs.getString("NOM_MEDIO"));

		
		return maeMedioTransporteBean;
	}

}
