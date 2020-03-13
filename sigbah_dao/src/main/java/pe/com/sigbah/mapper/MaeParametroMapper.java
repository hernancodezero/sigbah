package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.MaeParametroBean;

/**
 * @className: MaeParametroMapper.java
 * @description: 
 * @date: 16 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeParametroMapper implements RowMapper<MaeParametroBean> {
	

	@Override
	public MaeParametroBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeParametroBean maeParametroBean = new MaeParametroBean();

		maeParametroBean.setIdParametro(rs.getInt("IDE_PARAMETRO"));
		maeParametroBean.setNombreParametro(rs.getString("NOM_PARAMETRO"));
		maeParametroBean.setTipo(rs.getString("TIPO"));
		maeParametroBean.setValor(rs.getString("VALOR"));

		return maeParametroBean;
	}

}
