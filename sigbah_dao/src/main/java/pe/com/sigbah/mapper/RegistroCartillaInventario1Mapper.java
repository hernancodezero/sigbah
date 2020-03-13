package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.CartillaInventarioBean;

/**
 * @className: RegistroCartillaInventarioMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class RegistroCartillaInventario1Mapper implements RowMapper<CartillaInventarioBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public CartillaInventarioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		CartillaInventarioBean cartillaInventario = new CartillaInventarioBean();
		cartillaInventario.setIdCartilla(rs.getInt("IDE_CARTILLA"));
		cartillaInventario.setCodigoAnio(rs.getString("COD_ANIO"));
		cartillaInventario.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		cartillaInventario.setCodigoAlmacen(rs.getString("COD_ALMACEN"));
		cartillaInventario.setIdDdi(rs.getInt("FK_IDE_DDI"));
		cartillaInventario.setCodigoDdi(rs.getString("COD_DDI"));
		cartillaInventario.setNombreDdi(rs.getString("NOMBRE_DDI"));
		cartillaInventario.setNroCartilla(rs.getString("NRO_CARTILLA"));		
		cartillaInventario.setCodigoCartilla(rs.getString("COD_CARTILLA"));
		cartillaInventario.setFechaCartilla(rs.getString("FEC_CARTILLA"));
		cartillaInventario.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		cartillaInventario.setIdResponsable(rs.getInt("FK_IDE_RESPONSABLE"));		
		cartillaInventario.setResponsable(rs.getString("RESPONSABLE"));
		cartillaInventario.setIdEstado(rs.getInt("FK_IDE_ESTADO"));
		cartillaInventario.setNombreEstado(rs.getString("NOMBRE_ESTADO"));
		cartillaInventario.setObservacion(rs.getString("OBSERVACION_CARTILLA")!=null?rs.getString("OBSERVACION_CARTILLA").replaceAll("[\n]", "<<E>>").replaceAll("\"", "<<D>>").replaceAll("\'", "<<S>>"):"");				
		return cartillaInventario;
	}

}
