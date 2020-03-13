package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.ProductoIngresoBean;

/**
 * @className: ConsultaHistorialIngresoMapper.java
 * @description: 
 * @date: 07 de set. de 2017
 * @author: ARCHY.
 */
public class ConsultaHistorialSalidaMapper implements RowMapper<ProductoIngresoBean> {
	

	@Override
	public ProductoIngresoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoIngresoBean productoIngresoBean = new ProductoIngresoBean();
		productoIngresoBean.setCodigoAnio(rs.getString("COD_ANIO"));
		productoIngresoBean.setNombreDdi(rs.getString("NOMBRE_DDI"));
		productoIngresoBean.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		productoIngresoBean.setFechaEmision(rs.getString("FECHA_EMISION"));
		productoIngresoBean.setNroOrdenIngreso(rs.getString("NRO_ORDEN_SALIDA"));
		productoIngresoBean.setNombreMovimiento(rs.getString("TIPO_MOVIMIENTO"));
		productoIngresoBean.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		productoIngresoBean.setNombreUnidad(rs.getString("NOMBRE_UNIDAD"));
		productoIngresoBean.setCantidad(rs.getBigDecimal("CANTIDAD"));
		productoIngresoBean.setPrecioUnitario(rs.getBigDecimal("PREC_UNITARIO"));
		productoIngresoBean.setImporteTotal(rs.getBigDecimal("IMPORTE_TOTAL"));
		productoIngresoBean.setPesoTotalKgr(rs.getBigDecimal("KGRS_TOTAL_BRUTO"));
		productoIngresoBean.setNombreEstado(rs.getString("NOMBRE_ESTADO"));

		return productoIngresoBean;
	}

}
