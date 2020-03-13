package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.PedidoCompraBean;
import pe.com.sigbah.common.bean.RacionBean;


/**
 * @className: racionMapper.java
 * @description: 
 * @date: 19 jul. 2017
 * @author: whr.
 */
public class RegistroPedidoCompraMapper implements RowMapper<PedidoCompraBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public PedidoCompraBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		PedidoCompraBean pedido = new PedidoCompraBean();
		
		pedido.setIdPedidoCom(rs.getInt("IDE_PEDIDO_COMPRA"));
		pedido.setCodPedido(rs.getString("COD_PEDIDO"));
		pedido.setFkIdeDdi(rs.getInt("FK_IDE_DDI"));
		pedido.setNumPedidoCompra(rs.getString("NRO_PEDIDO"));
		pedido.setiEstado(rs.getInt("IDE_ESTADO"));	
		pedido.setTipPedido(rs.getString("TIP_PEDIDO"));
		pedido.setFecPedido(rs.getString("FEC_PEDIDO"));
		pedido.setDescripcion(rs.getString("NOM_PEDIDO_COMPRA"));
		pedido.setDee(rs.getString("FK_IDE_DEE"));
		pedido.setDeclaratoriaDee(rs.getString("DECLARATORIA_DEE"));
		
         
       return pedido;
	}

}
