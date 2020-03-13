package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoSalidaBean;

/**
 * @className: OrdenSalidaProductoMapper.java
 * @description: 
 * @date: 22 de nov. de 2017
 * @author: ARCHY.
 */
public class OrdenSalidaProductoMapper implements RowMapper<ProductoSalidaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoSalidaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoSalidaBean producto = new ProductoSalidaBean();
		producto.setIdSalida(rs.getInt("FK_IDE_SALIDA"));
		producto.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		producto.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		producto.setNombreUnidad(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		producto.setNombreEnvase(rs.getString("NOM_ENVASE"));
		producto.setNroLote(rs.getString("NRO_LOTE"));
		producto.setCantidad(rs.getBigDecimal("CANTIDAD"));
		producto.setPrecioUnitario(rs.getBigDecimal("PREC_UNITARIO"));
		producto.setImporteTotal(rs.getBigDecimal("IMPORTE_TOTAL"));
		
		return producto;
	}

}
