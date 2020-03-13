package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoCartillaInventarioBean;

/**
 * @className: ReporteCartillaInventarioMapper.java
 * @description: 
 * @date: 15 de set. de 2017
 * @author: ARCHY.
 */
public class ReporteCartillaInventario1Mapper implements RowMapper<ProductoCartillaInventarioBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoCartillaInventarioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoCartillaInventarioBean producto = new ProductoCartillaInventarioBean();
		producto.setItem(rs.getBigDecimal("ROWNUM"));
		producto.setIdCartilla(rs.getInt("IDE_CARTILLA"));
		producto.setIdDetalleCartilla(rs.getInt("IDE_DET_CARTILLA"));
		producto.setNroCartilla(rs.getString("NRO_CARTILLA"));		
		producto.setNombreDdi(rs.getString("NOMBRE_DDI"));
		producto.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));		
		producto.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));		
		producto.setResponsable(rs.getString("RESPONSABLE"));
		producto.setNombreProducto(rs.getString("NOMBRE_PRODUCTO"));
		producto.setCodigoProducto(rs.getString("COD_PRODUCTO"));
		producto.setNombreUnidad(rs.getString("NOMBRE_UNIDAD"));
		producto.setNroLote(rs.getString("NRO_LOTE"));
		producto.setNave(rs.getString("NAVE"));		
		producto.setCantidadStock(rs.getBigDecimal("CANTIDAD_STOCK"));		
		producto.setStockFisico(rs.getBigDecimal("STOCK_FISICO"));
		producto.setStockSistema(rs.getBigDecimal("STOCK_SISTEMA"));
		producto.setDiferencia(rs.getBigDecimal("DIFERENCIA"));
		producto.setPrecioUnitario(rs.getBigDecimal("PREC_UNITARIO"));
		producto.setTipo(rs.getString("TIPO_AJUSTE"));	
		producto.setDocumentoAjuste(rs.getString("DOCUMENTO_AJUSTE"));
		producto.setVersion(rs.getString("VERSION"));
		producto.setFechaVencimiento(rs.getString("FEC_VENCIMIENTO"));
		producto.setIdEstado(rs.getInt("ID_ESTADO"));
		return producto;
	}

}
