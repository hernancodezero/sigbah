package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.MaeAniosBean;
import pe.com.sigbah.common.bean.MaeCategoriaBean;
import pe.com.sigbah.common.bean.MaeDonanteBean;

/**
 * @className: MaeDonanteMapper.java
 * @description: 
 * @date: 15 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeDonanteMapper implements RowMapper<MaeDonanteBean> {
	

	@Override
	public MaeDonanteBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeDonanteBean maeDonanteBean = new MaeDonanteBean();

		maeDonanteBean.setIdDonante(rs.getInt("ID"));
		maeDonanteBean.setNombreDonante(rs.getString("NOM_DONANTE"));
		maeDonanteBean.setTipo(rs.getString("Tipo"));
		maeDonanteBean.setRepresentante(rs.getString("REPRESENTANTE"));
		maeDonanteBean.setNroDocumento(rs.getString("NUM_DOCUMENTO"));
		
		return maeDonanteBean;
	}

}
