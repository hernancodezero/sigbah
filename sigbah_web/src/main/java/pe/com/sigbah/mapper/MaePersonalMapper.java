package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.MaePersonalBean;

/**
 * @className: MaePersonalMapper.java
 * @description: 
 * @date: 16 de ago. de 2017
 * @author: ARCHY.
 */
public class MaePersonalMapper implements RowMapper<MaePersonalBean> {
	

	@Override
	public MaePersonalBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaePersonalBean maePersonalBean = new MaePersonalBean();

		maePersonalBean.setIdPersonal(rs.getInt("IDE_PERSONAL"));
		maePersonalBean.setIdDdi(rs.getInt("FK_IDE_DDI"));
		maePersonalBean.setNombreCompleto(rs.getString("NOMBRE"));
		maePersonalBean.setCargo(rs.getString("CARGO"));
		maePersonalBean.setNombreOficina(rs.getString("NOMBRE_OFICINA"));
		maePersonalBean.setTelefono(rs.getString("TELEFONOS"));
		maePersonalBean.setEstado(rs.getString("ESTADO"));
		maePersonalBean.setIdOficina(rs.getInt("FK_IDE_OFICINA"));
		maePersonalBean.setNombres(rs.getString("NOMBRES"));
		maePersonalBean.setApellidos(rs.getString("APELLIDOS"));
		maePersonalBean.setFlagActivo(rs.getString("FLG_ACTIVO"));
		return maePersonalBean;
	}

}
