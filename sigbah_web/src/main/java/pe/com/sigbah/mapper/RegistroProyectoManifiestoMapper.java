package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProyectoManifiestoBean;

/**
 * @className: RegistroProyectoManifiestoMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class RegistroProyectoManifiestoMapper implements RowMapper<ProyectoManifiestoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProyectoManifiestoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProyectoManifiestoBean proyectoManifiesto = new ProyectoManifiestoBean();
		proyectoManifiesto.setIdProyectoManifiesto(rs.getInt("IDE_PROYECTO_MANIF"));
		proyectoManifiesto.setNroProyectoManifiesto(rs.getString("NRO_PROY_MANIFIESTO"));
		proyectoManifiesto.setCodigoAnio(rs.getString("COD_ANIO"));
		proyectoManifiesto.setCodigoMes(rs.getString("COD_MES"));
		proyectoManifiesto.setIdDdi(rs.getInt("IDE_DDI"));
		proyectoManifiesto.setCodigoDdi(rs.getString("COD_DDI"));		
		proyectoManifiesto.setNombreDdi(rs.getString("NOMBRE_DDI"));
		proyectoManifiesto.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		proyectoManifiesto.setCodigoAlmacen(rs.getString("COD_ALMACEN"));		
		proyectoManifiesto.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));		
		proyectoManifiesto.setTipoOrigen(rs.getString("TIPO_ORIGEN"));
		proyectoManifiesto.setFechaEmision(rs.getString("FECHA_EMISION"));
		proyectoManifiesto.setIdEstado(rs.getInt("FK_IDE_ESTADO"));
		proyectoManifiesto.setNombreEstado(rs.getString("NOMBRE_ESTADO"));
		proyectoManifiesto.setIdMovimiento(rs.getInt("FK_IDE_TIP_MOVIMIENTO"));
		proyectoManifiesto.setNombreMovimiento(rs.getString("NOMBRE_MOVIMIENTO"));
		proyectoManifiesto.setIdAlmacenDestino(rs.getInt("FK_IDE_ALMACEN_DEST"));
		proyectoManifiesto.setNombreAlmacenDestino(rs.getString("NOMBRE_ALMACEN_DESTINO"));
		proyectoManifiesto.setObservacion(rs.getString("OBSERVACION"));
		proyectoManifiesto.setIdProgramacion(rs.getInt("FK_IDE_PROGRAMACION"));	
		proyectoManifiesto.setNroProgramacion(rs.getString("NRO_PROGRAMACION"));
		proyectoManifiesto.setFlagProgramacion(rs.getString("FLG_PROGRAMACION"));
		proyectoManifiesto.setTipoControl(rs.getString("FLG_TIPO_CONTROL"));
		return proyectoManifiesto;
	}

}
