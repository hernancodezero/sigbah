package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.MaePersonalExternoBean;

/**
 * @className: MaePersonalExternoMapper.java
 * @description: 
 * @date: 16 de ago. de 2017
 * @author: ARCHY.
 */
public class MaePersonalExternoMapper implements RowMapper<MaePersonalExternoBean> {
	

	@Override
	public MaePersonalExternoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaePersonalExternoBean maePersonalExternoBean = new MaePersonalExternoBean();

		maePersonalExternoBean.setIdPersonalExterno(rs.getInt("IDE_PERSONAL_EXT"));
		maePersonalExternoBean.setNombres(rs.getString("NOMBRE_PERSONAL_EXTERNO"));
		maePersonalExternoBean.setCargo(rs.getString("CARGO"));
		maePersonalExternoBean.setTelefono(rs.getString("TELEFONOS"));
		maePersonalExternoBean.setRegion(rs.getString("NOM_REGION"));
		maePersonalExternoBean.setProvincia(rs.getString("PROVINCIA"));
		maePersonalExternoBean.setDistrito(rs.getString("DISTRITO"));
		maePersonalExternoBean.setFlagActivo(rs.getString("ESTADO"));
		maePersonalExternoBean.setCodDepartamento(rs.getString("COD_DEPARTAMENTO"));
		maePersonalExternoBean.setCodProvincia(rs.getString("COD_PROVINCIA"));
		maePersonalExternoBean.setTipoPersonal(rs.getString("TIPO_PERSONAL"));
		maePersonalExternoBean.setCodUbigeo(rs.getString("COD_UBIGEO"));
		maePersonalExternoBean.setEstado(rs.getString("FLG_ACTIVO"));

		return maePersonalExternoBean;
	}

}
