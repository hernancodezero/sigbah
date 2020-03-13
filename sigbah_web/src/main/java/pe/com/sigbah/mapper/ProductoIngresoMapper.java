package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoIngresoBean;

/**
 * @className: ProductoIngresoMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProductoIngresoMapper implements RowMapper<ProductoIngresoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoIngresoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoIngresoBean producto = new ProductoIngresoBean();
		producto.setIdIngreso(rs.getInt("IDE_INGRESO"));
		producto.setIdDetalleIngreso(rs.getInt("IDE_INGRESO_DET"));
		producto.setIdProducto(rs.getInt("IDE_PRODUCTO"));
		producto.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		producto.setNombreUnidad(rs.getString("NOM_UNIDAD"));
		producto.setNombreEnvase(rs.getString("NOM_ENVASE"));
		producto.setNroLote(rs.getString("NRO_LOTE"));
		producto.setCantidad(rs.getBigDecimal("CANTIDAD"));
		producto.setPrecioUnitario(rs.getBigDecimal("PREC_UNITARIO"));
		producto.setImporteTotal(rs.getBigDecimal("IMP_TOTAL"));
		producto.setFechaVencimiento(rs.getString("FECHA_VENCIMIENTO"));
		producto.setIdCategoria(rs.getInt("FK_IDE_CATEGORIA_BAH"));
		return producto;
	}

}
