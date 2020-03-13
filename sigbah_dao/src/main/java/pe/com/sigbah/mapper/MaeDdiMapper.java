package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.MaeDdiBean;

/**
 * @className: MaeDdiMapper.java
 * @description: 
 * @date: 16 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeDdiMapper implements RowMapper<MaeDdiBean> {
	

	@Override
	public MaeDdiBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeDdiBean maeDdiBean = new MaeDdiBean();

		maeDdiBean.setIdDdi(rs.getInt("IDE_DDI"));
		maeDdiBean.setCodigoDdi(rs.getString("COD_DDI"));
		maeDdiBean.setNombreDdi(rs.getString("NOM_DDI"));

		return maeDdiBean;
	}

}
