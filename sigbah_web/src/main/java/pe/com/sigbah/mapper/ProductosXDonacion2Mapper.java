package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoDonacionSalidaBean;

/**
 * @className: ProductosXDonacion2Mapper.java
 * @description: 
 * @date: 31 de jul. de 2017
 * @author: ARCHY.
 */
public class ProductosXDonacion2Mapper implements RowMapper<ProductoDonacionSalidaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoDonacionSalidaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoDonacionSalidaBean producto = new ProductoDonacionSalidaBean();
		producto.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		producto.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		producto.setUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		producto.setCantidad(rs.getDouble("cantidad"));
		producto.setPrecioUnitario(rs.getDouble("PREC_UNITARIO"));
		producto.setImporteTotal(rs.getDouble("IMPORTE_TOTAL"));
		producto.setPesoBrutoTotal(rs.getDouble("PESO_BRUTO_TOTAL"));
		producto.setCodDonacion(rs.getString("COD_DONACION"));
		producto.setIdDonacion(rs.getInt("id_donacion"));
		producto.setPesoNetoUnitario(rs.getDouble("PESO_UNIT_NETO"));
		producto.setPesoBrutoUnitario(rs.getDouble("PESO_UNIT_BRUTO"));
		return producto;
	}

}
