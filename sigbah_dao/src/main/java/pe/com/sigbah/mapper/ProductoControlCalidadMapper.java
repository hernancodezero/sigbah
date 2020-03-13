package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoControlCalidadBean;

/**
 * @className: ProductoControlCalidadMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProductoControlCalidadMapper implements RowMapper<ProductoControlCalidadBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoControlCalidadBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoControlCalidadBean producto = new ProductoControlCalidadBean();
		producto.setIdDetalleControlCalidad(rs.getInt("IDE_DET_CONTROL_CAL"));
		producto.setIdControlCalidad(rs.getInt("FK_IDE_CONTROL_CALIDAD"));
		producto.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		producto.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		producto.setNombreUnidad(rs.getString("NOMBRE_UNID_MEDIDA"));
		producto.setCantidadLote(rs.getBigDecimal("CANT_LOTE"));
		producto.setCantidadMuestra(rs.getBigDecimal("CANT_MUESTRA"));
		producto.setPrimario(rs.getString("PRIMARIO"));
		producto.setValorPrimario(rs.getString("VALOR_PRIMARIO"));
		producto.setSecundario(rs.getString("SECUNDARIO"));
		producto.setValorSecundario(rs.getString("VALOR_SECUNDARIO"));
		producto.setParOlor(rs.getString("PAR_OLOR"));
		producto.setValorOlor(rs.getString("VALOR_OLOR"));
		producto.setParColor(rs.getString("PAR_COLOR"));
		producto.setValorColor(rs.getString("VALOR_COLOR"));
		producto.setParTextura(rs.getString("PAR_TEXTURA"));
		producto.setValorTextura(rs.getString("VALOR_TEXTURA"));
		producto.setParSabor(rs.getString("PAR_SABOR"));
		producto.setValorSabor(rs.getString("VALOR_SABOR"));
		producto.setFlagConforProducto(rs.getString("FLG_CONFOR_PRODUCTO"));
		producto.setValorConforProducto(rs.getString("VALOR_CONFOR_PRODUCTO"));
		producto.setFlagEspecTecnicas(rs.getString("FLAG_ESPEC_TECNICAS"));
		producto.setValorEspecTecnicas(rs.getString("VALOR_ESPEC_TECNICAS"));
		producto.setFechaVencimiento(rs.getString("FECHA_VENCIMIENTO"));
		producto.setIdCategoria(rs.getInt("FK_IDE_CATEGORIA_BAH"));
		return producto;
	}

}
