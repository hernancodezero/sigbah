package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.AlimentariaEmergenciaBean;


/**
 * @className: EmergenciaMapper.java
 * @description: 
 * @date: 15 jul. 2017
 * @author: whr.
 */
public class RegistroAlimentariaEmergenciaReportMapper implements RowMapper<AlimentariaEmergenciaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public AlimentariaEmergenciaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		AlimentariaEmergenciaBean emergencia = new AlimentariaEmergenciaBean();
		
		emergencia.setTipoProducto(rs.getString("TIPO_PRODUCTO"));
		emergencia.setNumRaciones(rs.getInt("NRO_RACIONES"));
		 
       return emergencia;
	}

}
