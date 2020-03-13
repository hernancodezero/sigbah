package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoIngresoBean;

/**
 * @className: ReporteDetalleOrdenIngresoMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ReporteDetalleOrdenIngresoMapper implements RowMapper<ProductoIngresoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoIngresoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoIngresoBean productoIngreso = new ProductoIngresoBean();
		productoIngreso.setIdIngreso(rs.getInt("IDE_INGRESO"));
		productoIngreso.setNombreDdi(rs.getString("NOMBRE_DDI"));		
		productoIngreso.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		productoIngreso.setFechaEmision(rs.getString("FECHA_EMISION"));
		productoIngreso.setNroOrdenIngreso(rs.getString("NRO_ORDEN_INGRESO"));
		productoIngreso.setNroOrdenCompra(rs.getString("NRO_ORDEN_COMPRA"));
		productoIngreso.setNombreMovimiento(rs.getString("TIPO_MOVIMIENTO"));
		productoIngreso.setNombreAlmacenProcedencia(rs.getString("ALMACEN_ORIGEN"));
		productoIngreso.setNombreEstado(rs.getString("NOMBRE_ESTADO"));		
		productoIngreso.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		productoIngreso.setNombreUnidad(rs.getString("NOMBRE_UNIDAD"));		
		productoIngreso.setCantidad(rs.getBigDecimal("CANTIDAD"));
		productoIngreso.setPrecioUnitario(rs.getBigDecimal("PREC_UNITARIO"));
		productoIngreso.setImporteTotal(rs.getBigDecimal("IMPORTE_TOTAL"));
		productoIngreso.setPesoTotalKgr(rs.getBigDecimal("KGRS_TOTAL"));		
		return productoIngreso;
	}

}
