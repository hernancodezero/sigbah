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
public class RegistroAlimentariaEmergenciaMapper implements RowMapper<AlimentariaEmergenciaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public AlimentariaEmergenciaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		AlimentariaEmergenciaBean emergencia = new AlimentariaEmergenciaBean();
		emergencia.setIdEmergencia(rs.getInt("IDE_EMERGENCIA"));
		emergencia.setCodAnio(rs.getString("COD_ANIO"));
		emergencia.setCodProducto(rs.getString("COD_PRODUCTO"));
		emergencia.setTipoProducto(rs.getString("TIPO_PRODUCTO"));
		emergencia.setCodCategoria(rs.getString("COD_CATEGORIA"));
		emergencia.setCategoriaProducto(rs.getString("CATEGORIA_PRODUCTO"));
		emergencia.setCantidad(rs.getInt("CANTIDAD"));
		
       return emergencia;
	}

}
