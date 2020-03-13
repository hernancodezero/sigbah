package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DocumentoProgramacionBean;

/**
 * @className: DocumentoProgramacionMapper.java
 * @description: 
 * @date: 4 ago. 2017
 * @author: whr.
 */
public class DocumentoProgramacionMapper implements RowMapper<DocumentoProgramacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DocumentoProgramacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocumentoProgramacionBean documento = new DocumentoProgramacionBean();
		documento.setIdDocumentoProgramacion(rs.getInt("ID_DOCUMENTO"));
		documento.setIdProgramacion(rs.getInt("FK_IDE_PROGRAMACION"));
		documento.setIdTipoDocumento(rs.getInt("FK_IDE_TIP_DOCUMENTO"));
		documento.setNombreDocumento(rs.getString("NOM_DOCUMENTO"));
		documento.setNroDocumento(rs.getString("NRO_DOCUMENTO"));
		documento.setFechaDocumento(rs.getString("FECHA_DOCUMENTO"));
		documento.setCodigoArchivoAlfresco(rs.getString("COD_ALFRESCO"));
		documento.setNombreArchivo(rs.getString("NOMB_ARCHIVO"));
		return documento;
	}

}
