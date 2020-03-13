package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DetalleProductoControlCalidadBean;
import pe.com.sigbah.common.bean.ProductoDonacionBean;

/**
 * @className: DetalleProductoDonacionMapper.java
 * @description: 
 * @date: 03 de ago. de 2017
 * @author: ARCHY.
 */
public class DetalleProductoDonacionMapper implements RowMapper<ProductoDonacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoDonacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoDonacionBean producto = new ProductoDonacionBean();
		producto.setIdDonacion(rs.getInt("IDE_DONACION"));
		producto.setIdDetDonacion(rs.getInt("IDE_DET_DONACION"));
		producto.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));	
		producto.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		producto.setUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		producto.setCantidad(rs.getDouble("CANTIDAD"));
		producto.setIdMoneda(rs.getInt("IDE_MONEDA"));
		producto.setMonOrigen(rs.getDouble("IMP_MONEDA_ORIGEN"));
		producto.setMonSoles(rs.getDouble("IMP_MONEDA_SOLES"));

		return producto;
	}

}
