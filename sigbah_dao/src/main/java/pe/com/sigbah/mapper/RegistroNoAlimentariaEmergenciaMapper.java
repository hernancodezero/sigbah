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
public class RegistroNoAlimentariaEmergenciaMapper implements RowMapper<NoAlimentariaEmergenciaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public NoAlimentariaEmergenciaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		NoAlimentariaEmergenciaBean emergencia = new NoAlimentariaEmergenciaBean();
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
