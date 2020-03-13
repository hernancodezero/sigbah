package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoDonacionBean;
import pe.com.sigbah.common.bean.ProductoRacionBean;

/**
 * @className: ProductDonacionMapper.java
 * @description: 
 * @date: 18 de jul. de 2017
 * @author: ARCHY.
 */
public class ProductoRacionMapper implements RowMapper<ProductoRacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoRacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoRacionBean producto = new ProductoRacionBean();
		producto.setIdRacion(rs.getInt("IDE_RAC_OPERATIVA"));
		producto.setIdDetaRacion(rs.getInt("IDE_DET_RAC_OPERATIVA"));
		producto.setFkIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		producto.setNombProducto(rs.getString("NOM_PRODUCTO"));
		producto.setPesoUnitarioPres(rs.getBigDecimal("PESO_UND_PRES"));
		producto.setCantRacionKg(rs.getBigDecimal("CANT_RACION_KGS"));
		
		
		return producto;
	}

}
