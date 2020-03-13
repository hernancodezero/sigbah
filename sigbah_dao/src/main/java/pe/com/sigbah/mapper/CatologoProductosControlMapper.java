package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoBean;
import pe.com.sigbah.common.util.Constantes;

/**
 * @className: CatologoProductosMapper.java
 * @description: 
 * @date: 22 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class CatologoProductosControlMapper implements RowMapper<ProductoBean> {
	
	private boolean all_records = false; // Obtener todos los registros

	/**
	 * @param parametro
	 */
	public CatologoProductosControlMapper(Integer parametro) {
		if (parametro.equals(Constantes.ZERO_INT)) {
			all_records = true;
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoBean productoBean = new ProductoBean();
		if (all_records) {
			productoBean.setIdProducto(rs.getInt("IDE_PRODUCTO"));
			productoBean.setNombreProducto(rs.getString("NOM_PRODUCTO"));
			productoBean.setCodigoProducto(rs.getString("COD_PRODUCTO"));
			productoBean.setIdUnidadMedida(rs.getInt("IDE_UNID_MEDIDA"));
			productoBean.setNombreUnidadMedida(rs.getString("NOMBRE_UNIDAD"));
			productoBean.setIdEnvase(rs.getInt("IDE_ENVASE"));
			productoBean.setNombreEnvase(rs.getString("NOMBRE_ENVASE"));
			productoBean.setCantidad(rs.getBigDecimal("CANTIDAD"));
//			productoBean.setPesoUnitarioNeto(rs.getBigDecimal("PESO_UNIT_NETO"));
//			productoBean.setPesoUnitarioBruto(rs.getBigDecimal("PESO_UNIT_BRUTO"));
//			productoBean.setVolumenUnitario(rs.getBigDecimal("VOLUMEN_UNIT"));
		} else {	
			productoBean.setIdProducto(rs.getInt("IDE_PRODUCTO"));
			productoBean.setNombreProducto(rs.getString("NOM_PRODUCTO"));
			productoBean.setCodigoProducto(rs.getString("COD_PRODUCTO"));
			productoBean.setIdUnidadMedida(rs.getInt("IDE_UNID_MEDIDA"));
			productoBean.setNombreUnidadMedida(rs.getString("NOMBRE_UNIDAD"));
			productoBean.setIdEnvase(rs.getInt("IDE_ENVASE"));
			productoBean.setNombreEnvase(rs.getString("NOMBRE_ENVASE"));
			productoBean.setCantidad(rs.getBigDecimal("CANTIDAD"));
//			productoBean.setPesoUnitarioNeto(rs.getBigDecimal("PESO_UNIT_NETO"));
//			productoBean.setPesoUnitarioBruto(rs.getBigDecimal("PESO_UNIT_BRUTO"));
//			productoBean.setVolumenUnitario(rs.getBigDecimal("VOLUMEN_UNIT"));
		}
		return productoBean;
	}

}
