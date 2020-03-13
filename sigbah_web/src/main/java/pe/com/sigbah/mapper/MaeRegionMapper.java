package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.MaeRegionBean;

/**
 * @className: MaeOficinaMapper.java
 * @description: 
 * @date: 16 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeRegionMapper implements RowMapper<MaeRegionBean> {
	

	@Override
	public MaeRegionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeRegionBean maeRegionBean = new MaeRegionBean();

		maeRegionBean.setIdRegion(rs.getInt("IDE_REGION"));
		maeRegionBean.setCodRegion(rs.getString("COD_REGION"));
		maeRegionBean.setNombreRegion(rs.getString("NOM_REGION"));

		return maeRegionBean;
	}

}
