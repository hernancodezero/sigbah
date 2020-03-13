package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.MaeAlmacenBean;

/**
 * @className: MaeAlmacenMapper.java
 * @description: 
 * @date: 11 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeAlmacenMapper implements RowMapper<MaeAlmacenBean> {
	

	@Override
	public MaeAlmacenBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeAlmacenBean maeAlmacenBean = new MaeAlmacenBean();
	
		maeAlmacenBean.setIdAlmacen(rs.getInt("ID"));
		maeAlmacenBean.setDireccion(rs.getString("DIRECCION"));
		maeAlmacenBean.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		maeAlmacenBean.setCodAlmacen(rs.getString("CODIGO"));
		maeAlmacenBean.setNombreDdi(rs.getString("DDI"));
		maeAlmacenBean.setIdDdi(rs.getInt("fk_ide_ddi"));
		maeAlmacenBean.setCodAlmacen(rs.getString("cod_almacen"));
		maeAlmacenBean.setCodDdi(rs.getString("cod_ddi"));
		maeAlmacenBean.setIdModAlmacen(rs.getInt("fk_ide_mod_almacen"));
		maeAlmacenBean.setCaracteristicas(rs.getString("caracteristicas"));
		maeAlmacenBean.setFlgActivo(rs.getString("flg_activo"));
//		maeAlmacenBean.setNombreSecundario(rs.getString("nombre_secundario"));
		
		return maeAlmacenBean;
	}

}
