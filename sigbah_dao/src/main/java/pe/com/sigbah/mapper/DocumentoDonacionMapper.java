package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DocumentoDonacionBean;
import pe.com.sigbah.common.bean.DocumentoIngresoBean;

/**
 * @className: DocumentoDonacionMapper.java
 * @description: 
 * @date: 16 de jul. de 2017
 * @author: ARCHY.
 */
public class DocumentoDonacionMapper implements RowMapper<DocumentoDonacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DocumentoDonacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocumentoDonacionBean documento = new DocumentoDonacionBean();
		documento.setIdDocumentoDonacion(rs.getInt("IDE_DOCUMENTO"));
		documento.setNombreDocumento(rs.getString("NOM_DOCUMENTO"));
		documento.setIdTipoDocumento(rs.getInt("IDE_TIP_DOCUMENTO"));
		documento.setNroDocumento(rs.getString("NRO_DOCUMENTO"));
		documento.setFecha(rs.getString("FEC_REGISTRO"));
		documento.setObservacion(rs.getString("OBSERVACION"));
		documento.setNombreArchivo(rs.getString("NOM_ARCHIVO"));
		documento.setCodAlfresco(rs.getString("COD_ALFRESCO"));
		return documento;
	}

}
