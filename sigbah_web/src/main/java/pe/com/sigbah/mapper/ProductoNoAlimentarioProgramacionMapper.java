package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoNoAlimentarioProgramacionBean;

/**
 * @className: ProductoNoAlimentarioProgramacionMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProductoNoAlimentarioProgramacionMapper implements RowMapper<ProductoNoAlimentarioProgramacionBean> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoNoAlimentarioProgramacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoNoAlimentarioProgramacionBean alimento = new ProductoNoAlimentarioProgramacionBean();
		alimento.setIdDetalleProductoNoAlimentario(rs.getInt("IDE_PRODUCTO_BNA"));
		alimento.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		alimento.setIdProgramacion(rs.getInt("FK_IDE_PROGRAMACION"));
		alimento.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		alimento.setDistribuirPor(rs.getString("DISTRIBUIR_POR"));
		alimento.setTipoEntrega(rs.getString("TIPO_ENTREGA"));
		alimento.setCantidad(rs.getBigDecimal("CANTIDAD"));
		alimento.setIdCategoria(rs.getInt("IDE_CATEGORIA_BAH"));
		return alimento;
	}

}
