package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.CartillaInventarioBean;

/**
 * @className: CartillaInventarioMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class CartillaInventarioMapper implements RowMapper<CartillaInventarioBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public CartillaInventarioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		CartillaInventarioBean cartillaInventario = new CartillaInventarioBean();
		cartillaInventario.setIdCartilla(rs.getInt("IDE_CARTILLA"));
		cartillaInventario.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		cartillaInventario.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		cartillaInventario.setCodigoAnio(rs.getString("COD_ANIO"));
		cartillaInventario.setNroCartilla(rs.getString("NRO_CARTILLA"));		
		cartillaInventario.setFechaCartilla(rs.getString("FEC_CARTILLA"));
		cartillaInventario.setResponsable(rs.getString("REPONSABLE"));
		cartillaInventario.setItemInventariados(rs.getBigDecimal("ITEM_INVENTARIADOS"));		
		cartillaInventario.setIdEstado(rs.getInt("FK_IDE_ESTADO"));
		cartillaInventario.setNombreEstado(rs.getString("NOMBRE_ESTADO"));
		return cartillaInventario;
	}

}
