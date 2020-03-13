package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.RacionOperativaBean;

/**
 * @className: ProgramacionRacionOperativaMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProgramacionRacionOperativaMapper implements RowMapper<RacionOperativaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public RacionOperativaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		RacionOperativaBean racion = new RacionOperativaBean();
		racion.setIdRacionOperativa(rs.getInt("IDE_RAC_OPERATIVA"));
		racion.setIdDetalleRacionOperativa(rs.getInt("IDE_DET_RAC_OPERATIVA"));
		racion.setNombreRacion(rs.getString("NOMBRE_RACION"));
		racion.setDiasAtencion(rs.getInt("DIAS_ATENCION"));
		racion.setIdProducto(rs.getInt("FK_IDE_PRODUCTO"));
		racion.setNombreProducto(rs.getString("NOM_PRODUCTO"));		
		racion.setCantidadRacionKg(rs.getBigDecimal("CANT_RACION_KGS"));
		racion.setPesoUnidadPres(rs.getBigDecimal("PESO_UND_PRES"));
		return racion;
	}

}
