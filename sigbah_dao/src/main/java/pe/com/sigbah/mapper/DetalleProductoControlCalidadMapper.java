package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DetalleProductoControlCalidadBean;

/**
 * @className: DetalleProductoControlCalidadMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class DetalleProductoControlCalidadMapper implements RowMapper<DetalleProductoControlCalidadBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DetalleProductoControlCalidadBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DetalleProductoControlCalidadBean producto = new DetalleProductoControlCalidadBean();
		producto.setItem(rs.getBigDecimal("ITEM"));
		producto.setIdControlCalidad(rs.getInt("IDE_CONTROL_CALIDAD"));		
		producto.setNroControlCalidad(rs.getString("NRO_CONTROL_CALIDAD"));
		producto.setProveedorDestino(rs.getString("PROVEEDOR_DESTINO"));
		producto.setFechaEmision(rs.getString("FECHA_EMISION"));
		producto.setNroOrdenCompra(rs.getString("NRO_ORDEN_COMPRA"));
		producto.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		producto.setCantidadLote(rs.getBigDecimal("CANT_LOTE"));
		producto.setCantidadMuestra(rs.getBigDecimal("CANT_MUESTRA"));
		producto.setPrimario(rs.getString("PRIMARIO"));
		producto.setSecundario(rs.getString("SECUNDARIO"));
		producto.setParOlor(rs.getString("PAR_OLOR"));
		producto.setParColor(rs.getString("PAR_COLOR"));
		producto.setParTextura(rs.getString("PAR_TEXTURA"));
		producto.setParSabor(rs.getString("PAR_SABOR"));
		producto.setFlagConforProducto(rs.getString("FLG_CONFOR_PRODUCTO"));
		producto.setFlagEspecTecnicas(rs.getString("FLAG_ESPEC_TECNICAS"));		
		producto.setConclusiones(rs.getString("CONCLUSIONES"));
		producto.setRecomendaciones(rs.getString("RECOMENDACIONES"));
		producto.setFlagTipoProducto(rs.getString("FLG_TIPO_BIEN"));
		producto.setNombreDdi(rs.getString("NOM_DDI"));
		producto.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		producto.setTipoControlCalidad(rs.getString("TIPO_CONTROL_CALIDAD"));
		return producto;
	}

}
