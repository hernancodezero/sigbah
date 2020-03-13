package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoSalidaBean;

/**
 * @className: ProductoSalidaMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProductoSalidaMapper implements RowMapper<ProductoSalidaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoSalidaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoSalidaBean producto = new ProductoSalidaBean();
		producto.setIdSalida(rs.getInt("FK_IDE_SALIDA"));
		producto.setIdDetalleSalida(rs.getInt("ID_SALIDA_DET"));
		producto.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		producto.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		producto.setNombreUnidad(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		producto.setNroLote(rs.getString("NRO_LOTE"));
		producto.setCantidad(rs.getBigDecimal("CANTIDAD"));
		producto.setPrecioUnitario(rs.getBigDecimal("PREC_UNITARIO"));
		producto.setImporteTotal(rs.getBigDecimal("IMPORTE_TOTAL"));
		producto.setPesoNetoTotal(rs.getBigDecimal("PESO_NETO_TOTAL"));
		producto.setPesoBrutoTotal(rs.getBigDecimal("PESO_BRUTO_TOTAL"));
		producto.setPesoUnitarioNeto(rs.getBigDecimal("PESO_UNIT_NETO"));
		producto.setPesoUnitarioBruto(rs.getBigDecimal("PESO_UNIT_BRUTO"));
		producto.setIdCategoria(rs.getInt("IDE_CATEGORIA_BAH"));
		producto.setNombreEnvase(rs.getString("NOM_ENVASE"));
		return producto;
	}

}
