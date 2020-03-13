package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DetalleProductoControlCalidadBean;
import pe.com.sigbah.common.bean.DocumentoDonacionIngresoBean;
import pe.com.sigbah.common.bean.ProductoDonacionBean;
import pe.com.sigbah.common.bean.ProductoDonacionIngresoBean;

/**
 * @className: DetalleProductoDonacionIngresoMapper.java
 * @description: 
 * @date: 03 de ago. de 2017
 * @author: ARCHY.
 */
public class DetalleDocumentoDonacionIngresoMapper implements RowMapper<DocumentoDonacionIngresoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DocumentoDonacionIngresoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocumentoDonacionIngresoBean documento = new DocumentoDonacionIngresoBean();
		documento.setIdDocumentoDonacion(rs.getInt("IDE_DOCUMENTO_ING"));
		documento.setNroDocumento(rs.getString("NRO_DOCUMENTO"));
		documento.setNombreDocumento(rs.getString("NOM_DOCUMENTO"));	
		documento.setIdTipoDocumento(rs.getInt("FK_IDE_TIP_DOCUMENTO"));
		documento.setObservacion(rs.getString("OBSERVACION"));
		documento.setCodAlfresco(rs.getString("COD_ALFRESCO"));
		documento.setNombreArchivo(rs.getString("NOM_ARCHIVO"));
		documento.setFecha(rs.getString("FEC_DOCUMENTO"));

		return documento;
	}

}
