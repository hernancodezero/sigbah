package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DetalleProductoControlCalidadBean;
import pe.com.sigbah.common.bean.DocumentoSalidaBean;
import pe.com.sigbah.common.bean.ProductoDonacionBean;
import pe.com.sigbah.common.bean.ProductoDonacionIngresoBean;

/**
 * @className: DetalleProductoDonacionIngresoMapper.java
 * @description: 
 * @date: 03 de ago. de 2017
 * @author: ARCHY.
 */
public class DetalleDocumentoDonacionSalidaMapper implements RowMapper<DocumentoSalidaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DocumentoSalidaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocumentoSalidaBean documento = new DocumentoSalidaBean();
		documento.setIdSalida(rs.getInt("FK_IDE_SALIDA"));
		documento.setIdTipoDocumento(rs.getInt("FK_IDE_TIP_DOCUMENTO"));
		documento.setNombreDocumento(rs.getString("NOM_DOCUMENTO"));	
		documento.setNroDocumento(rs.getString("NRO_DOCUMENTO"));
		documento.setFechaDocumento(rs.getString("FEC_DOCUMENTO"));

		return documento;
	}

}
