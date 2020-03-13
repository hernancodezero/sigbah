package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoDonacionBean;

/**
 * @className: ProductDonacionMapper.java
 * @description: 
 * @date: 18 de jul. de 2017
 * @author: ARCHY.
 */
public class ProductoDonacionMapper implements RowMapper<ProductoDonacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoDonacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoDonacionBean producto = new ProductoDonacionBean();
		producto.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		producto.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		producto.setUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		producto.setIdCategoria(rs.getInt("FK_IDE_CATEGORIA_BAH"));
		producto.setNombreCategoria(rs.getString("NOM_CATEGORIA"));
		producto.setCantidad(rs.getDouble("CANTIDAD"));
		producto.setMonOrigen(rs.getDouble("IMP_MONEDA_ORIGEN"));
		producto.setMonSoles(rs.getDouble("IMP_MONEDA_SOLES"));
		producto.setMonDolares(rs.getDouble("IMP_MONEDA_DOLAR"));
		producto.setFecVencimiento(rs.getString("FECHA_VENCIMIENTO"));
		producto.setNombreMoneda(rs.getString("NOMBRE"));
		producto.setIdMoneda(rs.getInt("IDE_MONEDA"));
		producto.setIdDetDonacion(rs.getInt("IDE_DET_DONACION"));

		return producto;
	}

}
