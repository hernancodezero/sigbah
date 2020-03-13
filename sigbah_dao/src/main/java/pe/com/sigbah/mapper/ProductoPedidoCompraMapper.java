package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoControlCalidadBean;
import pe.com.sigbah.common.bean.ProductoPedidoCompraBean;

/**
 * @className: ProductoControlCalidadMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProductoPedidoCompraMapper implements RowMapper<ProductoPedidoCompraBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoPedidoCompraBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoPedidoCompraBean producto = new ProductoPedidoCompraBean();
		producto.setIdDetallePedidoCompra(rs.getInt("IDE_DET_PEDIDO_COMPRA"));
		producto.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		producto.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		producto.setUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		producto.setCantidad(rs.getBigDecimal("CANTIDAD"));
		producto.setPrecioUnitario(rs.getBigDecimal("PRECIO_UNITARIO"));
		producto.setImporteTotal(rs.getBigDecimal("IMPORTE_TOTAL"));
		producto.setIdCategoria(rs.getInt("FK_IDE_CATEGORIA_BAH"));
	
         
		return producto;
	}

}
