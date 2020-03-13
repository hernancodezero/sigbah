package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.EstadosXUsuarioBean;

/**
 * @className: EstadoXUsuarioMapper.java
 * @description: 
 * @date: 28 de ago. de 2017
 * @author: ARCHY.
 */
public class EstadoXUsuarioMapper implements RowMapper<EstadosXUsuarioBean> {

	@Override
	public EstadosXUsuarioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		EstadosXUsuarioBean itemBean = new EstadosXUsuarioBean();

		itemBean.setIdEstado(rs.getInt("IDE_ESTADO"));
		itemBean.setNombreEstado(rs.getString("NOM_ESTADO"));
		itemBean.setExiste(rs.getString("EXISTE"));

		
		return itemBean;
	}

}
