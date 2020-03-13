package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ConsultaPedidoCompraBean;
import pe.com.sigbah.common.bean.ProductoConsultaPedidoBean;


/**
 * @className: RequerimientoMapper.java
 * @description: 
 * @date: 19 jul. 2017
 * @author: whr.
 */
public class RegistroConsultaPedidoModalCab2Mapper implements RowMapper<ProductoConsultaPedidoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoConsultaPedidoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoConsultaPedidoBean consulta = new ProductoConsultaPedidoBean();
		
		consulta.setNumPedido(rs.getString("NRO_PEDIDO"));	
		consulta.setCodAnio(rs.getString("COD_ANIO"));
		consulta.setIdDdi(rs.getInt("FK_IDE_DDI"));	
		consulta.setNomDdi(rs.getString("NOM_DDI"));
		consulta.setEstado(rs.getString("ESTADO"));
		consulta.setMotivo(rs.getString("MOTIVO"));
		
           
       return consulta;
	}

}
