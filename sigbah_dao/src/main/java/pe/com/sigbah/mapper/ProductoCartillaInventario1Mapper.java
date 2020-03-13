package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoCartillaInventarioBean;

/**
 * @className: ProductoCartillaInventarioMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProductoCartillaInventario1Mapper implements RowMapper<ProductoCartillaInventarioBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoCartillaInventarioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoCartillaInventarioBean producto = new ProductoCartillaInventarioBean();
		producto.setItem(rs.getBigDecimal("ROWNUM"));
		producto.setIdCartilla(rs.getInt("IDE_CARTILLA"));
		producto.setIdDetalleCartilla(rs.getInt("IDE_DET_CARTILLA"));
		producto.setTipoOrigen(rs.getString("TIPO_ORIGEN"));		
		producto.setIdDdi(rs.getInt("FK_IDE_DDI"));
		producto.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));		
		producto.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));		
		producto.setNombreProducto(rs.getString("NOMBRE_PRODUCTO"));
		producto.setCodigoProducto(rs.getString("COD_PRODUCTO"));
		producto.setNombreUnidad(rs.getString("NOMBRE_UNIDAD"));
		producto.setNombreEnvase(rs.getString("NOMBRE_ENVASE"));
		producto.setNroLote(rs.getString("NRO_LOTE"));
		producto.setNave(rs.getString("NAVE"));		
		producto.setCantidadStock(rs.getBigDecimal("CANTIDAD_STOCK"));		
		producto.setStockFisico(rs.getBigDecimal("STOCK_FISICO"));
		producto.setStockSistema(rs.getBigDecimal("STOCK_SISTEMA"));
		producto.setDiferencia(rs.getBigDecimal("DIFERENCIA"));
		producto.setPrecioUnitario(rs.getBigDecimal("PREC_UNITARIO"));
		producto.setTipo(rs.getString("TIPO"));	
		producto.setDocumentoAjuste(rs.getString("DOCUMENTO_AJUSTE"));
		producto.setFechaVencimiento(rs.getString("FEC_VENCIMIENTO"));
		producto.setObservacion(rs.getString("OBSERVACION")!=null?rs.getString("OBSERVACION").replaceAll("[\n]", "<<E>>").replaceAll("\"", "<<D>>").replaceAll("\'", "<<S>>"):"");
		return producto;
	}

}
