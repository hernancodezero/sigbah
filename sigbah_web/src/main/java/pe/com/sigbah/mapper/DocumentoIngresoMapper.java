package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DocumentoIngresoBean;

/**
 * @className: DocumentoIngresoMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class DocumentoIngresoMapper implements RowMapper<DocumentoIngresoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DocumentoIngresoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocumentoIngresoBean documento = new DocumentoIngresoBean();
		documento.setIdDocumentoIngreso(rs.getInt("IDE_DOCUMENTO_ING"));
		documento.setIdIngreso(rs.getInt("FK_IDE_INGRESO"));
		documento.setIdTipoDocumento(rs.getInt("FK_IDE_TIP_DOCUMENTO"));
		documento.setNombreDocumento(rs.getString("NOM_DOCUMENTO"));
		documento.setNroDocumento(rs.getString("NRO_DOCUMENTO"));
		documento.setFechaDocumento(rs.getString("FEC_DOCUMENTO"));
		documento.setCodigoArchivoAlfresco(rs.getString("COD_ALFRESCO"));
		documento.setNombreArchivo(rs.getString("NOM_ARCHIVO"));
		return documento;
	}

}
