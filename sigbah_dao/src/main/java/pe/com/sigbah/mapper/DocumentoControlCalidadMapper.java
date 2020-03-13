package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DocumentoControlCalidadBean;

/**
 * @className: DocumentoControlCalidadMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class DocumentoControlCalidadMapper implements RowMapper<DocumentoControlCalidadBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DocumentoControlCalidadBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocumentoControlCalidadBean documento = new DocumentoControlCalidadBean();
		documento.setIdDocumentoControlCalidad(rs.getInt("IDE_DOCUMENTO_CAL"));
		documento.setIdControlCalidad(rs.getInt("FK_IDE_CONTROL_CALIDAD"));
		documento.setIdTipoDocumento(rs.getInt("FK_IDE_TIP_DOCUMENTO"));
		documento.setNombreDocumento(rs.getString("NOM_DOCUMENTO"));
		documento.setNroDocumento(rs.getString("NRO_DOCUMENTO"));
		documento.setFechaDocumento(rs.getString("FEC_DOCUMENTO"));
		documento.setCodigoArchivoAlfresco(rs.getString("COD_ALFRESCO"));
		documento.setNombreArchivo(rs.getString("NOM_ARCHIVO"));
		return documento;
	}

}
