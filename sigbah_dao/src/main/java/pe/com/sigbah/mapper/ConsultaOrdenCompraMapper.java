package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.CartillaInventarioBean;
import pe.com.sigbah.common.bean.ProductoBean;

/**
 * @className: ConsultaOrdenCompraMapper.java
 * @description: 
 * @date: 10 de ene. de 2018
 * @author: ARCHY.
 */
public class ConsultaOrdenCompraMapper implements RowMapper<ProductoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoBean cartillaInventario = new ProductoBean();
		cartillaInventario.setNombreProducto(rs.getString("NOM_PRODUCTO"));
//		cartillaInventario.setIdUnidadMedida(rs.getInt("FK_IDE_UNID_MEDIDA"));
//		cartillaInventario.setPesoUnitarioNeto(rs.getBigDecimal("PESO_UNIT_NETO"));
//		cartillaInventario.setCodigoProducto(rs.getString("COD_PRODUCTO"));

		return cartillaInventario;
	}

}
