package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoSalidaBean;

/**
 * @className: ReporteDetalleOrdenSalidaMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ReporteDetalleOrdenSalidaMapper implements RowMapper<ProductoSalidaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoSalidaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoSalidaBean productoSalida = new ProductoSalidaBean();
		productoSalida.setIdSalida(rs.getInt("IDE_SALIDA"));
		productoSalida.setNombreDdi(rs.getString("NOMBRE_DDI"));		
		productoSalida.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		productoSalida.setFechaEmision(rs.getString("FECHA_EMISION"));
		productoSalida.setNroOrdenSalida(rs.getString("NRO_ORDEN_SALIDA"));
		productoSalida.setNroGuiaRemision(rs.getString("NRO_GUIA_REMISION"));
		productoSalida.setNombreMovimiento(rs.getString("TIPO_MOVIMIENTO"));
		productoSalida.setNombreAlmacenDestino(rs.getString("ALMACEN_DESTINO"));
		productoSalida.setNombreEstado(rs.getString("NOMBRE_ESTADO"));		
		productoSalida.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		productoSalida.setNombreUnidad(rs.getString("NOMBRE_UNIDAD"));
		productoSalida.setCantidad(rs.getBigDecimal("CANTIDAD"));
		productoSalida.setPrecioUnitario(rs.getBigDecimal("PREC_UNITARIO"));
		productoSalida.setImporteTotal(rs.getBigDecimal("IMPORTE_TOTAL"));
		return productoSalida;
	}

}
