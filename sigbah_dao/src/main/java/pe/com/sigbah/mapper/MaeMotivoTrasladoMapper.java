package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.MaeAniosBean;
import pe.com.sigbah.common.bean.MaeCategoriaBean;
import pe.com.sigbah.common.bean.MaeEstadoBean;
import pe.com.sigbah.common.bean.MaeEstadoProgramacionBean;
import pe.com.sigbah.common.bean.MaeModAlmacenBean;
import pe.com.sigbah.common.bean.MaeMonedaBean;
import pe.com.sigbah.common.bean.MaeMotivoTrasladoBean;

/**
 * @className: MaeModAlmacenMapper.java
 * @description: 
 * @date: 15 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeMotivoTrasladoMapper implements RowMapper<MaeMotivoTrasladoBean> {
	

	@Override
	public MaeMotivoTrasladoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeMotivoTrasladoBean maeMotivoTrasladoBean = new MaeMotivoTrasladoBean();

		maeMotivoTrasladoBean.setIdMotivoTraslado(rs.getInt("IDE_MOTIVO_TRASLADO"));
		maeMotivoTrasladoBean.setNombreMotivo(rs.getString("NOM_MOTIVO"));
		maeMotivoTrasladoBean.setFlagActivo(rs.getString("ESTADO"));

		return maeMotivoTrasladoBean;
	}

}
