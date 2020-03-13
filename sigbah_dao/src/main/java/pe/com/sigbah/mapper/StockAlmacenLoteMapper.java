package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.StockAlmacenLoteBean;

/**
 * @className: StockAlmacenLoteMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class StockAlmacenLoteMapper implements RowMapper<StockAlmacenLoteBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public StockAlmacenLoteBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		StockAlmacenLoteBean stockAlmacen = new StockAlmacenLoteBean();
		stockAlmacen.setTipoOrigen(rs.getString("TIPO_ORIGEN"));
		stockAlmacen.setIdDdi(rs.getInt("FK_IDE_DDI"));
		stockAlmacen.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		stockAlmacen.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));		
		stockAlmacen.setNroLote(rs.getString("NRO_LOTE"));
		stockAlmacen.setCantidad(rs.getBigDecimal("CANTIDAD"));
		stockAlmacen.setPrecioUnitario(rs.getBigDecimal("PREC_UNITARIO"));		
		stockAlmacen.setImporteTotal(rs.getBigDecimal("IMPORTE_TOTAL"));
		stockAlmacen.setFechaVencimiento(rs.getString("FEC_VENCIMIENTO"));
		stockAlmacen.setFechaProduccion(rs.getString("FEC_PRODUCCION"));
		stockAlmacen.setFechaAlta(rs.getString("FEC_ALTA"));		
		stockAlmacen.setPlanta(rs.getString("PLANTA"));
		stockAlmacen.setNave(rs.getString("NAVE"));
		stockAlmacen.setArea(rs.getString("AREA"));
		stockAlmacen.setIdMarca(rs.getInt("FK_IDE_MARCA"));
		stockAlmacen.setNombreMarca(rs.getString("NOMBRE_MARCA"));
		stockAlmacen.setItem(rs.getBigDecimal("ROWNUM"));
		return stockAlmacen;
	}

}
