package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DocumentoSalidaBean;

/**
 * @className: DocumentoDonacionSalidaMapper.java
 * @description: 
 * @date: 31 de jul. de 2017
 * @author: ARCHY.
 */
public class DocumentoDonacionSalidaMapper implements RowMapper<DocumentoSalidaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DocumentoSalidaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocumentoSalidaBean documento = new DocumentoSalidaBean();
		documento.setIdDocumentoSalida(rs.getInt("IDE_DOCUMENTO"));
		documento.setIdSalida(rs.getInt("FK_IDE_SALIDA"));
		documento.setNroDocumento(rs.getString("NRO_DOCUMENTO"));
		documento.setFechaDocumento(rs.getString("FEC_DOCUMENTO"));
		documento.setNombreDocumento(rs.getString("NOMBRE_TIPO_DOCUMENTO"));
		documento.setIdTipoDocumento(rs.getInt("FK_IDE_TIP_DOCUMENTO"));
		documento.setCodigoArchivoAlfresco(rs.getString("COD_ALFRESCO"));
		documento.setNombreArchivo(rs.getString("NOM_ARCHIVO"));
		documento.setObservacion(rs.getString("OBSERVACION"));

		return documento;
	}

}
