package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoDonacionBean;

/**
 * @className: HistorialReporPropuestoEntidadesMapper.java
 * @description: 
 * @date: 19 de set. de 2017
 * @author: ARCHY.
 */
public class HistorialReporPropuestoEntidadesMapper implements RowMapper<ProductoDonacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoDonacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoDonacionBean productoDonacionBean = new ProductoDonacionBean();
		productoDonacionBean.setFecVencimiento(rs.getString("FECHA"));
		productoDonacionBean.setNroOrdenSalida(rs.getString("NRO_ORDEN_SALIDA"));	
		productoDonacionBean.setEntidadDestino(rs.getString("ENTIDAD_DESTINO"));
		productoDonacionBean.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		productoDonacionBean.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		productoDonacionBean.setUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		productoDonacionBean.setCantidadSalida(rs.getBigDecimal("CANTIDAD_SALIDA"));
		productoDonacionBean.setImporteSalida(rs.getBigDecimal("IMPORTE_TOTAL_SALIDA"));
		productoDonacionBean.setIdAlmacen(rs.getInt("fk_ide_almacen"));
		productoDonacionBean.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));

		return productoDonacionBean;
	}

}
