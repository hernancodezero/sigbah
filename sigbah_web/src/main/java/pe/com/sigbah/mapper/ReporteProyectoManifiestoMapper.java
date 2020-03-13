package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProyectoManifiestoBean;

/**
 * @className: ReporteProyectoManifiestoMapper.java
 * @description: 
 * @date: 22 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ReporteProyectoManifiestoMapper implements RowMapper<ProyectoManifiestoBean> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProyectoManifiestoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProyectoManifiestoBean proyecto = new ProyectoManifiestoBean();
		proyecto.setIdProyectoManifiesto(rs.getInt("IDE_PROYECTO_MANIF"));
		proyecto.setNombreDdi(rs.getString("NOMBRE_DDI"));
		proyecto.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		proyecto.setFechaEmision(rs.getString("FECHA_EMISION"));
		proyecto.setNroProyectoManifiesto(rs.getString("NRO_PROY_MANIFIESTO"));
		proyecto.setNroProgramacion(rs.getString("NRO_PROGRAMACION"));		
		proyecto.setNombreMovimiento(rs.getString("TIPO_MOVIMIENTO"));
		proyecto.setNombreAlmacenDestino(rs.getString("ALMACEN_DESTINO"));
		proyecto.setVolumenTotal(rs.getBigDecimal("VOLUMEN_TOTAL"));
		proyecto.setPesoTotalKgr(rs.getBigDecimal("PESO_TOTAL_KGRL"));
		proyecto.setNombreEstado(rs.getString("NOMBRE_ESTADO"));
		return proyecto;
	}

}
