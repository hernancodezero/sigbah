package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.UbigeoDeeBean;

/**
 * @className: DeeDistritoEmergenciaMapper.java
 * @description: 
 * @date: 12 ago. 2017
 * @author: whr.
 */
public class DeeDistritoEmergenciaMapper implements RowMapper<UbigeoDeeBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public UbigeoDeeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		UbigeoDeeBean ubigeo = new UbigeoDeeBean();
		
		ubigeo.setIdDeeDetalle(rs.getInt("IDE_DEE_DET"));
		ubigeo.setCoddist(rs.getString("COD_DISTRITO"));
		ubigeo.setDesdpto(rs.getString("DES_DEPARTAMENTO"));
		ubigeo.setDesprov(rs.getString("DES_PROVINCIA"));
		ubigeo.setDesdist(rs.getString("DES_DISTRITO"));
	
		
       return ubigeo;
		
	
    
	}

}
