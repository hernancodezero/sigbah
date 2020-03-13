package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.EmergenciaBean;
import pe.com.sigbah.common.bean.UbigeoIneiBean;


/**
 * @className: EmergenciaMapper.java
 * @description: 
 * @date: 15 jul. 2017
 * @author: whr.
 */
public class UbigeoDeeMapper implements RowMapper<UbigeoIneiBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public UbigeoIneiBean  mapRow(ResultSet rs, int rowNum) throws SQLException {
		UbigeoIneiBean ubi = new UbigeoIneiBean();
		
		ubi.setCoddist(rs.getString("COD_DISTRITO"));
		ubi.setDesprov(rs.getString("DES_PROVINCIA"));		
		ubi.setDesdist(rs.getString("DES_DISTRITO"));
		
       return ubi;
	}

}
