package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoBean;
import pe.com.sigbah.common.util.Constantes;

/**
 * @className: CatologoProductos2Mapper.java
 * @description: 
 * @date: 03 de jul. de 2018
 * @author: Alan Chumpitaz.
 */
public class CatologoProductos2Mapper implements RowMapper<ProductoBean> {
	
	private boolean all_records = false; // Obtener todos los registros

	/**
	 * @param parametro
	 */
	public CatologoProductos2Mapper(Integer parametro) {
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
			productoBean.setPesoUnitarioNeto(rs.getBigDecimal("PESO_UNIT_NETO"));
			productoBean.setPesoUnitarioBruto(rs.getBigDecimal("PESO_UNIT_BRUTO"));
			productoBean.setVolumenUnitario(rs.getBigDecimal("VOLUMEN_UNIT"));
			productoBean.setCantidad(rs.getBigDecimal("CANTIDAD"));
			productoBean.setPrecioUnitario(rs.getBigDecimal("PREC_UNITARIO"));
			productoBean.setIdCategoria(rs.getInt("FK_IDE_CATEGORIA_BAH"));
		} else {	
			productoBean.setIdProducto(rs.getInt("IDE_PRODUCTO"));
			productoBean.setNombreProducto(rs.getString("NOM_PRODUCTO"));
			productoBean.setCodigoProducto(rs.getString("COD_PRODUCTO"));
			productoBean.setIdUnidadMedida(rs.getInt("IDE_UNID_MEDIDA"));
			productoBean.setNombreUnidadMedida(rs.getString("NOMBRE_UNIDAD"));
			productoBean.setIdEnvase(rs.getInt("IDE_ENVASE"));
			productoBean.setNombreEnvase(rs.getString("NOMBRE_ENVASE"));
			productoBean.setPesoUnitarioNeto(rs.getBigDecimal("PESO_UNIT_NETO"));
			productoBean.setPesoUnitarioBruto(rs.getBigDecimal("PESO_UNIT_BRUTO"));
			productoBean.setVolumenUnitario(rs.getBigDecimal("VOLUMEN_UNIT"));
			productoBean.setCantidad(rs.getBigDecimal("CANTIDAD"));
			productoBean.setPrecioUnitario(rs.getBigDecimal("PREC_UNITARIO"));
			productoBean.setIdCategoria(rs.getInt("FK_IDE_CATEGORIA_BAH"));
		}
		return productoBean;
	}

}
