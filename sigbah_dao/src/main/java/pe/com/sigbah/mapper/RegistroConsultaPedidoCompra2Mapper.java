package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ConsultaPedidoCompraBean;


/**
 * @className: RequerimientoMapper.java
 * @description: 
 * @date: 19 jul. 2017
 * @author: whr.
 */
public class RegistroConsultaPedidoCompra2Mapper implements RowMapper<ConsultaPedidoCompraBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ConsultaPedidoCompraBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ConsultaPedidoCompraBean requerimiento = new ConsultaPedidoCompraBean();
		
		requerimiento.setCodigoAnio(rs.getString("COD_ANIO"));
		requerimiento.setIdDdi(rs.getInt("FK_IDE_DDI"));	
		requerimiento.setNomDdi(rs.getString("NOM_DDI"));
		requerimiento.setIdPedidoCompra(rs.getInt("IDE_PEDIDO_COMPRA"));
		requerimiento.setMotivo(rs.getString("MOTIVO"));
		requerimiento.setNumPedido(rs.getString("NRO_PEDIDO"));
		requerimiento.setTotalTonAlimento(rs.getDouble("TOTAL_TONELADAS_ALIMENTOS"));
		
		requerimiento.setTotalSolesAlimento(rs.getDouble("TOTAL_SOLES_ALIMENTOS"));
		requerimiento.setTotalTonBna(rs.getDouble("TOTAL_TONELADAS_BNA"));
		requerimiento.setTotalSolesBna(rs.getDouble("TOTAL_SOLES_BNA"));		

       return requerimiento;
	}

}
