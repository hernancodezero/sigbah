package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoDonacionBean;
import pe.com.sigbah.common.bean.RegionDonacionBean;

/**
 * @className: RegionDonacionMapper.java
 * @description: 
 * @date: 19 de jul. de 2017
 * @author: ARCHY.
 */
public class RegionDonacionMapper implements RowMapper<RegionDonacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public RegionDonacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		RegionDonacionBean region = new RegionDonacionBean();
		region.setIdRegion(rs.getInt("IDE_REGION"));
		region.setNombreRegion(rs.getString("NOM_REGION"));
		return region;
	}

}
