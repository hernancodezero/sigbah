package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoDonacionBean;

/**
 * @className: ConsultaKardexMapper.java
 * @description: 
 * @date: 09 de oct. de 2017
 * @author: ARCHY.
 */
public class ConsultaKardexMapper implements RowMapper<ProductoDonacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoDonacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoDonacionBean productoDonacionBean = new ProductoDonacionBean();
		productoDonacionBean.setNombreSistema(rs.getString("NOMBRE_SISTEMA"));	
		productoDonacionBean.setCodigoAnio(rs.getString("COD_ANIO"));
		productoDonacionBean.setCodigoMes(rs.getString("COD_MES"));
		productoDonacionBean.setNombreMes(rs.getString("NOMBRE_MES"));
		productoDonacionBean.setIdAlmacen(rs.getInt("IDE_ALMACEN"));
		productoDonacionBean.setIdProducto(rs.getInt("IDE_PRODUCTO"));
		productoDonacionBean.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		productoDonacionBean.setNroKardex(rs.getString("NRO_KARDEX"));
		productoDonacionBean.setUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		productoDonacionBean.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		productoDonacionBean.setFecVencimiento(rs.getString("FECHA"));
		productoDonacionBean.setCantidadIngreso(rs.getBigDecimal("CANT_INGRESOS"));
		productoDonacionBean.setCantidadSalida(rs.getBigDecimal("CANT_SALIDAS"));
		productoDonacionBean.setCantidad(rs.getDouble("CANT_SALDO"));
		productoDonacionBean.setPrecio(rs.getBigDecimal("PREC_UNITARIO"));
		productoDonacionBean.setImporteTotal(rs.getBigDecimal("IMPORTE_TOTAL"));
		productoDonacionBean.setMotivo(rs.getString("MOTIVO"));
		productoDonacionBean.setNroOrden(rs.getString("NRO_ORDEN"));
		productoDonacionBean.setTipo(rs.getString("TIPO"));
		
		return productoDonacionBean;
	}

}
