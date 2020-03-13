package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoDonacionBean;

/**
 * @className: HistorialReporPropuestoMapper.java
 * @description: 
 * @date: 06 de set. de 2017
 * @author: ARCHY.
 */
public class HistorialReporPropuestoMapper implements RowMapper<ProductoDonacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoDonacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoDonacionBean productoDonacionBean = new ProductoDonacionBean();
		productoDonacionBean.setNombreCategoria(rs.getString("NOM_CATEGORIA"));	
		productoDonacionBean.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		productoDonacionBean.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		productoDonacionBean.setUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		productoDonacionBean.setCantidad(rs.getDouble("CANTIDAD"));
		productoDonacionBean.setMonOrigen(rs.getDouble("IMP_MONEDA_ORIGEN"));
		productoDonacionBean.setNombreMoneda(rs.getString("NOMBRE"));
		productoDonacionBean.setMonSoles(rs.getDouble("IMP_MONEDA_SOLES"));
		productoDonacionBean.setMonDolares(rs.getDouble("IMP_MONEDA_DOLAR"));
		productoDonacionBean.setFecVencimiento(rs.getString("FEC_VENCIMIENTO"));
		return productoDonacionBean;
	}

}
