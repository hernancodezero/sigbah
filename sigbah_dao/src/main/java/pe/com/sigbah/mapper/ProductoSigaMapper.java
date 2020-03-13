package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.CatalogoProductoBean;

/**
 * @className: ProductoSigaMapper.java
 * @description: 
 * @date: 24 de ago. de 2017
 * @author: ARCHY.
 */
public class ProductoSigaMapper implements RowMapper<CatalogoProductoBean> {

	@Override
	public CatalogoProductoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		CatalogoProductoBean productoBean = new CatalogoProductoBean();

		productoBean.setCodigoSiga(rs.getString("CODIGO_SIGA"));
		productoBean.setNombreProducto(rs.getString("NOMBRE_PRODUCTO"));
		productoBean.setGrupoBien(rs.getString("GRUPO_BIEN"));
		productoBean.setClaseBien(rs.getString("CLASE_BIEN"));
		productoBean.setFamiliaBien(rs.getString("FAMILIA_BIEN"));
		productoBean.setItemBien(rs.getString("ITEM_BIEN"));
		productoBean.setEstado(rs.getString("ESTADO"));
		
		return productoBean;
	}

}
