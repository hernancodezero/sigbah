package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.MaeOficinaBean;

/**
 * @className: MaeOficinaMapper.java
 * @description: 
 * @date: 16 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeOficinaMapper implements RowMapper<MaeOficinaBean> {
	

	@Override
	public MaeOficinaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeOficinaBean maeOficinaBean = new MaeOficinaBean();

		maeOficinaBean.setIdOficina(rs.getInt("IDE_OFICINA"));
		maeOficinaBean.setNombreOficina(rs.getString("NOMBRE_OFICINA"));
		maeOficinaBean.setIdDdi(rs.getInt("IDE_DDI"));
		maeOficinaBean.setNombreDdi(rs.getString("NOM_DDI"));

		return maeOficinaBean;
	}

}
