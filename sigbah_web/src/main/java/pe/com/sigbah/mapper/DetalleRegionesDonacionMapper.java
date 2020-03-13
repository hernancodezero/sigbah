package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DetalleProductoControlCalidadBean;
import pe.com.sigbah.common.bean.ProductoDonacionBean;
import pe.com.sigbah.common.bean.RegionDonacionBean;

/**
 * @className: DetalleProductoDonacionMapper.java
 * @description: 
 * @date: 03 de ago. de 2017
 * @author: ARCHY.
 */
public class DetalleRegionesDonacionMapper implements RowMapper<RegionDonacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public RegionDonacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		RegionDonacionBean region = new RegionDonacionBean();
		region.setIdDonacion(rs.getInt("IDE_DONACION"));
//		region.se(rs.getInt("IDE_DET_DONACION_REGION"));
		region.setIdRegion(rs.getInt("IDE_REGION"));	
		region.setNombreRegion(rs.getString("NOM_REGION"));


		return region;
	}

}
