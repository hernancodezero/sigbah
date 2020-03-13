package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoDonacionSalidaBean;
import pe.com.sigbah.common.bean.ProductoSalidaBean;

/**
 * @className: ProductoDonacionSalidaMapper.java
 * @description: 
 * @date: 31 de jul. de 2017
 * @author: ARCHY.
 */
public class ProductoDonacionSalidaMapper implements RowMapper<ProductoDonacionSalidaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoDonacionSalidaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoDonacionSalidaBean producto = new ProductoDonacionSalidaBean();
		producto.setIdSalidaDet(rs.getInt("ID_SALIDA_DET"));
		producto.setIdSalida(rs.getInt("FK_IDE_SALIDA"));
		producto.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		producto.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		producto.setUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		producto.setCantidad(rs.getDouble("CANTIDAD"));
		producto.setPrecioUnitario(rs.getDouble("PREC_UNITARIO"));
		producto.setImporteTotal(rs.getDouble("IMPORTE_TOTAL"));
		producto.setPesoBrutoTotal(rs.getDouble("PESO_BRUTO_TOTAL"));
		producto.setCodDonacion(rs.getString("COD_DONACION"));
		producto.setIdCategoria(rs.getInt("FK_IDE_CATEGORIA_BAH"));
		producto.setIdDonacion(rs.getInt("IDE_DONACION"));
		producto.setPesoNetoUnitario(rs.getDouble("PESO_UNIT_NETO"));
		producto.setPesoBrutoUnitario(rs.getDouble("PESO_UNIT_BRUTO"));
		
		return producto;
	}

}
