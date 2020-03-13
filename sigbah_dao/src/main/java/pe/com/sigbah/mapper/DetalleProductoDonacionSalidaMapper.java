package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DetalleProductoControlCalidadBean;
import pe.com.sigbah.common.bean.ProductoDonacionBean;
import pe.com.sigbah.common.bean.ProductoDonacionIngresoBean;
import pe.com.sigbah.common.bean.ProductoDonacionSalidaBean;

/**
 * @className: DetalleProductoDonacionSalidaMapper.java
 * @description: 
 * @date: 04 de ago. de 2017
 * @author: ARCHY.
 */
public class DetalleProductoDonacionSalidaMapper implements RowMapper<ProductoDonacionSalidaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoDonacionSalidaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoDonacionSalidaBean producto = new ProductoDonacionSalidaBean();
		producto.setIdSalida(rs.getInt("FK_IDE_SALIDA"));
		producto.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		producto.setNombreProducto(rs.getString("NOM_PRODUCTO"));	
		producto.setUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		producto.setNombreEnvase(rs.getString("NOM_ENVASE"));
		producto.setNroLote(rs.getInt("NRO_LOTE"));
		producto.setCantidad(rs.getDouble("CANTIDAD"));
		producto.setPrecioUnitario(rs.getDouble("PREC_UNITARIO"));
		producto.setImporteTotal(rs.getDouble("IMPORTE_TOTAL"));

		return producto;
	}

}
