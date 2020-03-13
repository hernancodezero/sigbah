package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.LoteProductoBean;

/**
 * @className: LoteProductoSalidaMapper.java
 * @description: 
 * @date: 22 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class LoteProductoSalidaMapper implements RowMapper<LoteProductoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public LoteProductoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		LoteProductoBean loteProductoBean = new LoteProductoBean();
		loteProductoBean.setNroLote(rs.getString("NRO_LOTE"));
		loteProductoBean.setLote(rs.getString("LOTE"));
		loteProductoBean.setCantidad(rs.getBigDecimal("CANTIDAD"));
		loteProductoBean.setPrecioUnitario(rs.getBigDecimal("PREC_UNITARIO"));
		return loteProductoBean;
	}

}
