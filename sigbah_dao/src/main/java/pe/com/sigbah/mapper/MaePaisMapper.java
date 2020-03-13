package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.MaePaisBean;

/**
 * @className: MaePaisMapper.java
 * @description: 
 * @date: 16 de ago. de 2017
 * @author: ARCHY.
 */
public class MaePaisMapper implements RowMapper<MaePaisBean> {
	

	@Override
	public MaePaisBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaePaisBean maePaisBean = new MaePaisBean();

		maePaisBean.setIdPais(rs.getInt("IDE_PAIS"));
		maePaisBean.setNombrePais(rs.getString("NOMBRE"));

		return maePaisBean;
	}

}
