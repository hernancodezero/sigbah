package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.RolMenuBean;

/**
 * @className: RolMostrarMapper.java
 * @description: 
 * @date: 04 de oct. de 2017
 * @author: ARCHY.
 */
public class MenuUsuarioMapper implements RowMapper<RolMenuBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public RolMenuBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		RolMenuBean rolMenuBean = new RolMenuBean();
		rolMenuBean.setIdRol(rs.getInt("ID_ROL"));
		rolMenuBean.setNombreRol(rs.getString("NOMBRE_ROL"));
		rolMenuBean.setIdMenu(rs.getInt("ID_MENU"));
		rolMenuBean.setNombreMenu(rs.getString("NOMBRE_MENU"));
		rolMenuBean.setIdPadre(rs.getInt("ID_PADRE"));
		rolMenuBean.setNroOrden(rs.getInt("NRO_ORDEN"));
		rolMenuBean.setCodigoMenu(rs.getString("CODIGO_MENU"));
		rolMenuBean.setEstiloMenu(rs.getString("ESTILO_MENU"));
		rolMenuBean.setExiste(rs.getString("EXISTE"));
		rolMenuBean.setHijo(rs.getString("HIJO"));
		rolMenuBean.setPintar(rs.getString("PINTAR"));
		return rolMenuBean;
		
	}

}
