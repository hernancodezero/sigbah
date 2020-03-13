package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoStockBean;

/**
 * @className: ProductoStockMapper.java
 * @description: Clase asociado al producto stock.
 * @date: 28/07/2018
 * @author: Sumerio.
 */
public class ProductoStockMapper implements RowMapper<ProductoStockBean> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoStockBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoStockBean categoriaStockBean = new ProductoStockBean();
		categoriaStockBean.setNroFila(rs.getInt("ROWNUM"));
		categoriaStockBean.setIdDdi(rs.getInt("ID_DDI"));
		categoriaStockBean.setIdAlmacen(rs.getInt("ID_ALM"));
		categoriaStockBean.setIdProducto(rs.getInt("ID_PROD"));
		categoriaStockBean.setNombreCategoria(rs.getString("NOM_CAT"));
		categoriaStockBean.setNombreDdiAlmacen(rs.getString("NOM_DDI_ALMACEN"));
		categoriaStockBean.setNombreProducto(rs.getString("NOM_PROD"));
		categoriaStockBean.setCantidadProducto(rs.getBigDecimal("CANT"));
		categoriaStockBean.setCantidadToneladas(rs.getBigDecimal("TON"));
		return categoriaStockBean;
	}

}
