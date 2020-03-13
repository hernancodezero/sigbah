package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.OrdenIngresoBean;

/**
 * @className: ReporteSigaMapper.java
 * @description: 
 * @date: 06 de jul. de 2018
 * @author: Alan Chumpitaz.
 */
public class ReporteSigaEMapper implements RowMapper<OrdenIngresoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public OrdenIngresoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrdenIngresoBean ordenIngreso = new OrdenIngresoBean();
		ordenIngreso.setCodigoAnio(rs.getString("COD_ANIO"));
		ordenIngreso.setNombreMes(rs.getString("NOMBRE_MES"));		
		ordenIngreso.setNombreMovimiento(rs.getString("TIPO_MOVIMIENTO"));
		ordenIngreso.setNroOrdenIngreso(rs.getString("NRO_ORDEN_INGRESO"));
		ordenIngreso.setFechaEmision(rs.getString("FECHA_EMISION"));
		ordenIngreso.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		ordenIngreso.setCodigoSiga(rs.getString("COD_SIGA"));
		ordenIngreso.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		ordenIngreso.setNombreUnidad(rs.getString("NOMBRE_UNIDAD"));
		ordenIngreso.setCantidad(rs.getBigDecimal("CANTIDAD"));
		ordenIngreso.setPrecio(rs.getBigDecimal("PREC_UNITARIO"));
		ordenIngreso.setImporte(rs.getBigDecimal("IMPORTE_TOTAL"));
		ordenIngreso.setNombreMarca(rs.getString("NOM_MARCA"));
		ordenIngreso.setFechaVencimiento(rs.getString("FEC_VENCIMIENTO"));
		ordenIngreso.setNombreAlmacenOrigen(rs.getString("ALMACEN_ORIGEN"));
		ordenIngreso.setNroOrdenSalida(rs.getString("NRO_ORDEN_SALIDA"));
		ordenIngreso.setNroOrdenCompra(rs.getString("NRO_ORDEN_COMPRA"));
		ordenIngreso.setRazonSocial(rs.getString("RAZON_SOCIAL"));
		return ordenIngreso;
	}

}
