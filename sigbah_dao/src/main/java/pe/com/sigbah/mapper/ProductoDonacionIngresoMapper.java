package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoDonacionIngresoBean;

/**
 * @className: ProductoDonacionIngresoMapper.java
 * @description: 
 * @date: 21 de jul. de 2017
 * @author: ARCHY.
 */
public class ProductoDonacionIngresoMapper implements RowMapper<ProductoDonacionIngresoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoDonacionIngresoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoDonacionIngresoBean producto = new ProductoDonacionIngresoBean();
		producto.setIdIngreso(rs.getInt("FK_IDE_INGRESO"));
		producto.setIdIngresoDet(rs.getInt("IDE_INGRESO_DET"));
		producto.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		producto.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		producto.setUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		producto.setCantidad(rs.getDouble("CANTIDAD"));
		producto.setPrecioUnitario(rs.getDouble("PREC_UNITARIO"));
		producto.setImporteTotal(rs.getDouble("IMPORTE_TOTAL"));
		producto.setFecVencimiento(rs.getString("FECHA_VENCIMIENTO"));
		
		return producto;
	}

}
