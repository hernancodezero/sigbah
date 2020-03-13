package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DocumentoPedidoCompraBean;

/**
 * @className: DocumentoPedidoCompraMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class DocumentoPedidoCompraMapper implements RowMapper<DocumentoPedidoCompraBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DocumentoPedidoCompraBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocumentoPedidoCompraBean documento = new DocumentoPedidoCompraBean();
		documento.setIdDocumentoPedidoCompra(rs.getInt("ID_DOCUMENTO"));
		documento.setIdPedidoCompra(rs.getInt("FK_IDE_PEDIDO_COMPRA"));
		documento.setNombreDocumento(rs.getString("NOM_DOCUMENTO"));
		documento.setNroDocumento(rs.getString("NRO_DOCUMENTO"));
		documento.setFechaDocumento(rs.getString("FECHA_DOCUMENTO"));
		documento.setCodigoArchivoAlfresco(rs.getString("COD_ALFRESCO"));
		documento.setNombreArchivo(rs.getString("NOMB_ARCHIVO"));
		documento.setIdTipoDocumento(rs.getInt("IDE_TIP_DOCUMENTO"));
		return documento;
	}

}

