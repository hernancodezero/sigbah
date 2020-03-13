package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoStockAlmacenBean;

/**
 * @className: ProductoStockAlmacenMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProductoStockAlmacenMapper implements RowMapper<ProductoStockAlmacenBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoStockAlmacenBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoStockAlmacenBean productostockAlmacen = new ProductoStockAlmacenBean();
		productostockAlmacen.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		productostockAlmacen.setCodigoProducto(rs.getString("COD_PRODUCTO"));
		productostockAlmacen.setCodigoSiga(rs.getString("COD_SIGA"));
		productostockAlmacen.setCantidadStock(rs.getBigDecimal("CANTIDAD_STOCK"));		
		productostockAlmacen.setUnidadMedida(rs.getString("UNIDAD_MEDIDA"));
		productostockAlmacen.setNombreEnvase(rs.getString("NOMBRE_ENVASE"));
		productostockAlmacen.setPrecioPromedio(rs.getBigDecimal("PREC_PROMEDIO"));
		return productostockAlmacen;
	}

}
