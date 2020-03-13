package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.RolBean;

/**
 * @className: RolMapper.java
 * @description: 
 * @date: 21 de ago. de 2017
 * @author: ARCHY.
 */
public class RolMapper implements RowMapper<RolBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public RolBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		RolBean rolBean = new RolBean();
		rolBean.setIdRol(rs.getInt("ID_ROL"));
		rolBean.setNombreRol(rs.getString("NOMBRE_ROL"));
		rolBean.setExiste(rs.getString("EXISTE"));
		return rolBean;
		
	}

}
