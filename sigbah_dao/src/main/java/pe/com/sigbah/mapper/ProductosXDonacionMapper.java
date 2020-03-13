package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DocumentoDonacionBean;
import pe.com.sigbah.common.bean.DocumentoIngresoBean;
import pe.com.sigbah.common.bean.ProductoDonacionBean;

/**
 * @className: DocumentoDonacionMapper.java
 * @description: 
 * @date: 16 de jul. de 2017
 * @author: ARCHY.
 */
public class ProductosXDonacionMapper implements RowMapper<ProductoDonacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoDonacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoDonacionBean documento = new ProductoDonacionBean();
		documento.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		documento.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		documento.setUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		documento.setPrecio(rs.getBigDecimal("PRECIO_UNITARIO"));
		documento.setFecVencimiento(rs.getString("FECHA_VENCIMIENTO"));
		return documento;
	}

}
