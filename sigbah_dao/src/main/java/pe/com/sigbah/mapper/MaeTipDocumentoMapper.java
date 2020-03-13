package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.MaeTipDocumentoBean;

/**
 * @className: MaeTipDocumentoMapper.java
 * @description: 
 * @date: 16 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeTipDocumentoMapper implements RowMapper<MaeTipDocumentoBean> {
	

	@Override
	public MaeTipDocumentoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeTipDocumentoBean maeTipDocumentoBean = new MaeTipDocumentoBean();

		maeTipDocumentoBean.setIdTipDocumento(rs.getInt("IDE_TIP_DOCUMENTO"));
		maeTipDocumentoBean.setNombreDocumento(rs.getString("NOM_DOCUMENTO"));
		maeTipDocumentoBean.setNombreCorto(rs.getString("NOM_CORTO"));

		return maeTipDocumentoBean;
	}

}
