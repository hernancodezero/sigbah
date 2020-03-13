package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ConsultaPedidoCompraBean;
import pe.com.sigbah.common.bean.ProductoConsultaPedidoBean;


/**
 * @className: RequerimientoMapper.java
 * @description: 
 * @date: 19 jul. 2017
 * @author: whr.
 */
public class RegistroConsultaPedidoModalNoProdMapper implements RowMapper<ProductoConsultaPedidoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoConsultaPedidoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoConsultaPedidoBean requerimiento = new ProductoConsultaPedidoBean();
		
		requerimiento.setNomCategoria(rs.getString("NOM_CATEGORIA"));
		requerimiento.setNomProducto(rs.getString("NOM_PRODUCTO"));
		requerimiento.setNomUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));	
		requerimiento.setTotalCantidad(rs.getBigDecimal("TOTAL_CANTIDAD"));
		requerimiento.setTotalToneladasAli(rs.getBigDecimal("TOTAL_TONELADAS"));
		requerimiento.setTotalSolesAli(rs.getBigDecimal("TOTAL_SOLES"));
			
		
        
       return requerimiento;
	}

}
