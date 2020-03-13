package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.PedidoCompraReporteBean;


/**
 * @className: EmergenciaMapper.java
 * @description: 
 * @date: 15 jul. 2017
 * @author: whr.
 */
public class ReporteDetallePedidoCompraMapper implements RowMapper<PedidoCompraReporteBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public PedidoCompraReporteBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		PedidoCompraReporteBean pedido = new PedidoCompraReporteBean();
		
//		pedido.setNombreSistema(rs.getString("NOMBRE_SISTEMA"));
//		pedido.setNumPedidoCompra(rs.getString("NRO_PEDIDO"));
//		pedido.setFecPedido(rs.getString("FECHA_PEDIDO"));
//		pedido.setDescripcion(rs.getString("NOM_PEDIDO_COMPRA"));
//		pedido.setDee(rs.getString("NRO_DEE"));
		
		pedido.setIdeDetPedido(rs.getInt("IDE_DET_PEDIDO_COMPRA"));
		pedido.setNomProducto(rs.getString("NOM_PRODUCTO"));
		pedido.setNombreUniMed(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		pedido.setCantidad(rs.getBigDecimal("CANTIDAD"));
		pedido.setPrecioUni(rs.getBigDecimal("PRECIO_UNITARIO"));
		pedido.setImporteTotal(rs.getBigDecimal("IMPORTE_TOTAL"));
		pedido.setFkIdCategoria(rs.getInt("FK_IDE_CATEGORIA_BAH"));
		pedido.setFkIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
        
       return pedido;
	}

}
