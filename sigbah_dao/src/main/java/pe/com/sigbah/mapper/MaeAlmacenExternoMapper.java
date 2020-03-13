package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.MaeAlmacenExternoBean;

/**
 * @className: MaeAlmacenExternoMapper.java
 * @description: 
 * @date: 11 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeAlmacenExternoMapper implements RowMapper<MaeAlmacenExternoBean> {
	

	@Override
	public MaeAlmacenExternoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeAlmacenExternoBean maeAlmacenBean = new MaeAlmacenExternoBean();
	
		maeAlmacenBean.setIdAlmacenExterno(rs.getInt("ID"));
		//maeAlmacenBean.setIdRegion(rs.getInt("ID_REGION"));
		maeAlmacenBean.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		maeAlmacenBean.setCodAlmacen(rs.getString("CODIGO"));
		maeAlmacenBean.setDireccion(rs.getString("DIRECCION"));
		maeAlmacenBean.setRucUbigeo(rs.getString("RUC"));
		maeAlmacenBean.setTipoAlmacen(rs.getString("TIPO"));
		maeAlmacenBean.setNombreUbigeo(rs.getString("LUGAR"));
		maeAlmacenBean.setIdRegion(rs.getInt("FK_IDE_REGION"));
		maeAlmacenBean.setCodUbigeo(rs.getString("COD_UBIGEO"));
		maeAlmacenBean.setFlgActivo(rs.getString("ESTADO"));
		maeAlmacenBean.setCodigoDepartamento(rs.getString("COD_DEPARTAMENTO"));
		maeAlmacenBean.setCodigoProvincia(rs.getString("COD_PROVINCIA"));
		
		
		return maeAlmacenBean;
	}

}
