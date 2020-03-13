package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoProyectoManifiestoBean;

/**
 * @className: ReporteDetalleProyectoManifiestoMapper.java
 * @description: 
 * @date: 22 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ReporteDetalleProyectoManifiestoMapper implements RowMapper<ProductoProyectoManifiestoBean> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProductoProyectoManifiestoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoProyectoManifiestoBean proyecto = new ProductoProyectoManifiestoBean();
		proyecto.setIdProyectoManifiesto(rs.getInt("IDE_PROYECTO_MANIF"));
		proyecto.setNombreDdi(rs.getString("NOMBRE_DDI"));
		proyecto.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		proyecto.setFechaEmision(rs.getString("FECHA_EMISION"));
		proyecto.setNroProyectoManifiesto(rs.getString("NRO_PROY_MANIFIESTO"));
		proyecto.setNroProgramacion(rs.getString("NRO_PROGRAMACION"));		
		proyecto.setNombreMovimiento(rs.getString("TIPO_MOVIMIENTO"));
		proyecto.setNombreAlmacenDestino(rs.getString("ALMACEN_DESTINO"));
		proyecto.setNombreEstado(rs.getString("NOMBRE_ESTADO"));		
		proyecto.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		proyecto.setNombreUnidad(rs.getString("NOMBRE_UNIDAD"));		
		proyecto.setVolumenTotal(rs.getBigDecimal("VOLUMENT_TOTAL"));
		proyecto.setPesoTotal(rs.getBigDecimal("PESO_TOTAL"));		
		return proyecto;
	}

}
