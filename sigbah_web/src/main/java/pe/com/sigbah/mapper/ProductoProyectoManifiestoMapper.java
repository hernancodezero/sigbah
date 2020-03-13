package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoProyectoManifiestoBean;

/**
 * @className: ProductoProyectoManifiestoMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProductoProyectoManifiestoMapper implements RowMapper<ProductoProyectoManifiestoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoProyectoManifiestoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoProyectoManifiestoBean producto = new ProductoProyectoManifiestoBean();
		producto.setIdProyectoManifiesto(rs.getInt("FK_IDE_PROYECTO_MANIF"));
		producto.setIdDetalleProyecto(rs.getInt("IDE_DET_PROYECTO_MANIF"));
		producto.setIdProducto(rs.getInt("IDE_PRODUCTO"));
		producto.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		producto.setNombreUnidad(rs.getString("NOM_UNIDAD"));
		producto.setCantidad(rs.getBigDecimal("CANTIDAD"));
		producto.setPesoUnitarioBruto(rs.getBigDecimal("PESO_UNIT_BRUTO"));
		producto.setPesoTotal(rs.getBigDecimal("PESO_TOTAL"));
		producto.setVolumenUnitario(rs.getBigDecimal("VOLUMEN_UNIT"));
		producto.setVolumenTotal(rs.getBigDecimal("VOLUMEN_TOTAL"));
		producto.setCostoBruto(rs.getBigDecimal("COSTO_UNITARIO"));
		producto.setCostoTotal(rs.getBigDecimal("COSTO_TOTAL"));
		producto.setIdCategoria(rs.getInt("FK_IDE_CATEGORIA_BAH"));
		producto.setNombreEnvase(rs.getString("NOM_ENVASE"));
		return producto;
	}

}
