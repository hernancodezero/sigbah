package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoConsultaProductoBean;


/**
 * @className: EmergenciaMapper.java
 * @description: 
 * @date: 15 jul. 2017
 * @author: whr.
 */
public class AlimentosConsultaProgramacionMapper implements RowMapper<ProductoConsultaProductoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoConsultaProductoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoConsultaProductoBean emergencia = new ProductoConsultaProductoBean();
		emergencia.setIdProgramacion(rs.getInt("ID_PROG"));
		emergencia.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		emergencia.setNombreCategoria(rs.getString("NOM_CATEGORIA"));
		emergencia.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		emergencia.setNombreUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		emergencia.setNroUnidades(rs.getInt("NRO_UNIDADES"));
		emergencia.setTotalKilos(rs.getBigDecimal("TOTAL_KILOS"));
		emergencia.setTotalToneladas(rs.getBigDecimal("TOTAL_TONELADAS"));
		   
       return emergencia;
	}

}
