package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.MaeTipCamionBean;

/**
 * @className: MaeOficinaMapper.java
 * @description: 
 * @date: 16 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeTipCamionMapper implements RowMapper<MaeTipCamionBean> {
	

	@Override
	public MaeTipCamionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeTipCamionBean maeTipCamionBean = new MaeTipCamionBean();

		maeTipCamionBean.setIdTipCamion(rs.getInt("IDE_TIP_CAMION"));
		maeTipCamionBean.setTonelaje(rs.getBigDecimal("TONELAJE"));
		maeTipCamionBean.setVolumen(rs.getBigDecimal("VOLUMEN"));
		maeTipCamionBean.setDescripcion(rs.getString("DESCRIPCION"));

		return maeTipCamionBean;
	}

}
