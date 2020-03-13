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
public class DetalleProductoDonacionIngreso1Mapper implements RowMapper<ProductoDonacionIngresoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoDonacionIngresoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoDonacionIngresoBean producto = new ProductoDonacionIngresoBean();
		producto.setIdIngreso(rs.getInt("FK_IDE_INGRESO"));
		producto.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		producto.setNombreProducto(rs.getString("NOM_PRODUCTO"));	
		producto.setUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		producto.setNombreEnvase(rs.getString("NOM_ENVASE"));
		producto.setNroLote(rs.getString("NRO_LOTE"));
		producto.setCantidad(rs.getDouble("CANTIDAD"));
		producto.setPrecioUnitario(rs.getDouble("PREC_UNITARIO"));
		producto.setImporteTotal(rs.getDouble("IMPORTE_TOTAL"));
		producto.setFecVencimiento(rs.getString("FECHA_VENCIMIENTO"));

		return producto;
	}

}
