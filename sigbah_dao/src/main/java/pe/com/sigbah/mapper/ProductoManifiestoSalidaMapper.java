package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoProyectoManifiestoBean;

/**
 * @className: ProductoManifiestoSalidaMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProductoManifiestoSalidaMapper implements RowMapper<ProductoProyectoManifiestoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoProyectoManifiestoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoProyectoManifiestoBean producto = new ProductoProyectoManifiestoBean();
		producto.setIdProyectoManifiesto(rs.getInt("FK_IDE_PROYECTO_MANIF"));
		producto.setNroProyectoManifiesto(rs.getString("NRO_PROY_MANIFIESTO"));
		producto.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		producto.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		producto.setNombreUnidad(rs.getString("NOM_UNIDAD"));
		producto.setCantidadProgramada(rs.getBigDecimal("CANT_PROGRAMADA"));
		producto.setCantidadDespachada(rs.getBigDecimal("CANT_DESPACHADA"));
		producto.setCantidadPorDespachar(rs.getBigDecimal("CANT_POR_DESPACHAR"));
		producto.setCantidadSalida(rs.getBigDecimal("CANT_SALIDA"));
		producto.setStockAlmacen(rs.getBigDecimal("STOCK_ALMACEN"));
		producto.setPesoUnitarioBruto(rs.getBigDecimal("PESO_UNITARIO"));
		producto.setVolumenUnitario(rs.getBigDecimal("VOL_UNITARIO"));
		return producto;
	}

}
