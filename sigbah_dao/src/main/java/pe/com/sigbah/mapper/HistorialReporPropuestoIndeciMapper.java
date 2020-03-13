package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoDonacionBean;

/**
 * @className: HistorialReporPropuestoIndeciMapper.java
 * @description: 
 * @date: 19 de set. de 2017
 * @author: ARCHY.
 */
public class HistorialReporPropuestoIndeciMapper implements RowMapper<ProductoDonacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoDonacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoDonacionBean productoDonacionBean = new ProductoDonacionBean();
		productoDonacionBean.setTipoOrigen(rs.getString("TIPO_ORIGEN"));	
		productoDonacionBean.setIdDdi(rs.getInt("FK_IDE_DDI"));
		productoDonacionBean.setNombreDdi(rs.getString("NOM_DDI"));
		productoDonacionBean.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		productoDonacionBean.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		productoDonacionBean.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		productoDonacionBean.setNombreCategoria(rs.getString("CATEGORIA"));
		productoDonacionBean.setNombreProducto(rs.getString("PRODUCTO"));
		productoDonacionBean.setUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		productoDonacionBean.setCantidad(rs.getDouble("CANTIDAD"));
		productoDonacionBean.setImporteTotal(rs.getBigDecimal("IMPORTE_TOTAL"));
		return productoDonacionBean;
	}

}
