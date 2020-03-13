package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.MaeMarcaBean;

/**
 * @className: MaeMarcaMapper.java
 * @description: 
 * @date: 29 de ago. de 2018
 * @author: ARCHY.
 */
public class MaeMarcaMapper implements RowMapper<MaeMarcaBean> {
	

	@Override
	public MaeMarcaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeMarcaBean maeMarcaBean = new MaeMarcaBean();

		maeMarcaBean.setIdMarca(rs.getInt("IDE_MARCA"));
		maeMarcaBean.setNombreMarca(rs.getString("NOM_MARCA"));

		return maeMarcaBean;
	}

}
