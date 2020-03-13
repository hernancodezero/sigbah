package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DocumentoDonacionBean;
import pe.com.sigbah.common.bean.ProductoDonacionBean;

/**
 * @className: HistorialReporDocumentosMapper.java
 * @description: 
 * @date: 06 de set. de 2017
 * @author: ARCHY.
 */
public class HistorialReporDocumentosMapper implements RowMapper<DocumentoDonacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DocumentoDonacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocumentoDonacionBean documentoDonacionBean = new DocumentoDonacionBean();
		documentoDonacionBean.setNombreDocumento(rs.getString("NOM_DOCUMENTO"));	
		documentoDonacionBean.setNroDocumento(rs.getString("NRO_DOCUMENTO"));
		documentoDonacionBean.setFecha(rs.getString("FECHA_DOCUMENTO"));
		documentoDonacionBean.setObservacion(rs.getString("OBSERVACION"));
		documentoDonacionBean.setCodAlfresco(rs.getString("COD_ALFRESCO"));
		documentoDonacionBean.setIdDocumentoDonacion(rs.getInt("IDE_DOCUMENTO"));
		return documentoDonacionBean;
	}

}
