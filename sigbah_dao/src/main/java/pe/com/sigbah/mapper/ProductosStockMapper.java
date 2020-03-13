package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoBean;

/**
 * @className: ProductosStockMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProductosStockMapper implements RowMapper<ProductoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoBean producto = new ProductoBean();
		producto.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		producto.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		producto.setNombreUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		producto.setPesoUnitarioNeto(rs.getBigDecimal("PESO_UNIT_NETO"));
		producto.setPesoUnitarioBruto(rs.getBigDecimal("PESO_UNIT_BRUTO"));
		return producto;
	}

}
