package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.MaeTipControlCalidadBean;

/**
 * @className: MaeTipControlCalidadMapper.java
 * @description: 
 * @date: 16 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeTipControlCalidadMapper implements RowMapper<MaeTipControlCalidadBean> {
	

	@Override
	public MaeTipControlCalidadBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeTipControlCalidadBean maeTipControlCalidadBean = new MaeTipControlCalidadBean();

		maeTipControlCalidadBean.setIdTipControl(rs.getInt("IDE_TIP_CONTROL"));
		maeTipControlCalidadBean.setNombreTipControl(rs.getString("NOM_TIP_CONTROL"));

		return maeTipControlCalidadBean;
	}

}
