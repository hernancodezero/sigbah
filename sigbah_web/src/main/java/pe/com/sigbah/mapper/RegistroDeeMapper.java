package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DeeBean;
import pe.com.sigbah.common.bean.PedidoCompraBean;


/**
 * @className: racionMapper.java
 * @description: 
 * @date: 19 jul. 2017
 * @author: whr.
 */
public class RegistroDeeMapper implements RowMapper<DeeBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DeeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeeBean dee = new DeeBean();
		
		dee.setIdDee(rs.getInt("IDE_DEE"));
		dee.setNumDee(rs.getString("NRO_DEE"));
		dee.setNomDee(rs.getString("NOM_DECLARATORIA"));
		dee.setFechaIni(rs.getString("FEC_INICIO"));
		dee.setFechaFin(rs.getString("FEC_FIN"));	
		dee.setNumDias(rs.getString("PLAZO"));
		dee.setIdEstado(rs.getInt("ID_ESTADO_DEE"));
		dee.setMotivo(rs.getString("TIP_FENOMENO"));
		dee.setFlgProrroga(rs.getString("FLG_PRORROGA"));
		dee.setObservacion(rs.getString("OBSERVACION"));
		dee.setNombreArchivo(rs.getString("NOM_ARCHIVO"));
		dee.setCodigoArchivoAlfresco(rs.getString("COD_ALFRESCO"));
	
         
       return dee;
	}

}
