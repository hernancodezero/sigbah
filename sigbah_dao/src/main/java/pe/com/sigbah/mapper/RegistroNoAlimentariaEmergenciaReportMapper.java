package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.NoAlimentariaEmergenciaBean;


/**
 * @className: EmergenciaMapper.java
 * @description: 
 * @date: 15 jul. 2017
 * @author: whr.
 */
public class RegistroNoAlimentariaEmergenciaReportMapper implements RowMapper<NoAlimentariaEmergenciaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public NoAlimentariaEmergenciaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		NoAlimentariaEmergenciaBean emergencia = new NoAlimentariaEmergenciaBean();
		
		emergencia.setCategoriaProducto(rs.getString("CATEGORIA_PRODUCTO"));
		emergencia.setTipoProducto(rs.getString("TIPO_PRODUCTO"));
		emergencia.setCantidad(rs.getInt("CANTIDAD"));
		
       return emergencia;
	}

}
