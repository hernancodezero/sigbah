package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DetalleProductoControlCalidadBean;
import pe.com.sigbah.common.bean.ProductoDonacionBean;
import pe.com.sigbah.common.bean.ProductoDonacionIngresoBean;

/**
 * @className: DetalleProductoDonacionIngresoMapper.java
 * @description: 
 * @date: 03 de ago. de 2017
 * @author: ARCHY.
 */
public class DetalleProductoDonacionIngresoNacionalMapper implements RowMapper<ProductoDonacionIngresoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoDonacionIngresoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoDonacionIngresoBean producto = new ProductoDonacionIngresoBean();
		producto.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		producto.setNombreProducto(rs.getString("NOM_PRODUCTO"));	
		producto.setUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		producto.setCantidad(rs.getDouble("CANTIDAD"));
		producto.setImporteTotal(rs.getDouble("IMPORTE_TOTAL"));
		producto.setEstadoConservacion(rs.getString("ESTADO_CONSERVACION"));
		producto.setFecVencimiento(rs.getString("FECHA_VENCIMIENTO"));

		return producto;
	}

}
