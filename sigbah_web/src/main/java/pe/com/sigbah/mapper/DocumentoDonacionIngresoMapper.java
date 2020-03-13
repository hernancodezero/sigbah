package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DocumentoDonacionBean;
import pe.com.sigbah.common.bean.DocumentoIngresoBean;

/**
 * @className: DocumentoDonacionIngresoMapper.java
 * @description: 
 * @date: 21 de jul. de 2017
 * @author: ARCHY.
 */
public class DocumentoDonacionIngresoMapper implements RowMapper<DocumentoIngresoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DocumentoIngresoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocumentoIngresoBean documento = new DocumentoIngresoBean();
		documento.setIdDocumentoIngreso(rs.getInt("IDE_DOCUMENTO_ING"));
		documento.setNroDocumento(rs.getString("NRO_DOCUMENTO"));
		documento.setNombreDocumento(rs.getString("NOM_DOCUMENTO"));
		documento.setIdTipoDocumento(rs.getInt("FK_IDE_TIP_DOCUMENTO"));
		//documento.setObservacion(rs.getString("OBSERVACION"));
		documento.setCodigoArchivoAlfresco(rs.getString("COD_ALFRESCO"));
		documento.setNombreArchivo(rs.getString("NOM_ARCHIVO"));
		documento.setFechaDocumento(rs.getString("FECHA_DOCUMENTO"));
		return documento;
	}

}
