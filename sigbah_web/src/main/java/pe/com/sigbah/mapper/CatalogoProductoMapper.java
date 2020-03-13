package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.CatalogoProductoBean;

/**
 * @className: CatalogoProductoMapper.java
 * @description: 
 * @date: 23 de ago. de 2017
 * @author: ARCHY.
 */
public class CatalogoProductoMapper implements RowMapper<CatalogoProductoBean> {
	

	@Override
	public CatalogoProductoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		CatalogoProductoBean catalogoProductoBean = new CatalogoProductoBean();
													  
		catalogoProductoBean.setIdProducto(rs.getInt("IDE_PRODUCTO"));
		catalogoProductoBean.setNombreCategoria(rs.getString("NOM_CATEGORIA"));
		catalogoProductoBean.setCodigoProducto(rs.getString("COD_PRODUCTO"));
		catalogoProductoBean.setCodigoSiga(rs.getString("COD_SIGA"));
		catalogoProductoBean.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		catalogoProductoBean.setNombreUnidadMedida(rs.getString("NOMBRE_UNIDAD_MEDIDA"));
		catalogoProductoBean.setNombreEnvase(rs.getString("NOMBRE_ENVASE"));
		catalogoProductoBean.setEstado (rs.getString("ESTADO"));
		
		return catalogoProductoBean;
	}

}
