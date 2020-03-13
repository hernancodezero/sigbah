package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.util.Constantes;

/**
 * @className: EstadoMapper.java
 * @description: 
 * @date: 22 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProveedorMapper implements RowMapper<ItemBean> {
	
	private boolean all_records = false; // Obtener todos los registros

	/**
	 * @param parametro
	 */
	public ProveedorMapper(Integer parametro) {
		if (parametro.equals(Constantes.ZERO_INT)) {
			all_records = true;
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ItemBean itemBean = new ItemBean();
		if (all_records) {
			itemBean.setVcodigo(rs.getString("IDE_PROVEEDOR"));
			itemBean.setDescripcion(rs.getString("RAZON_SOCIAL"));
			itemBean.setDescripcionCorta(rs.getString("REPRESENTANTE"));
		} else {	
			itemBean.setVcodigo(rs.getString("IDE_PROVEEDOR"));
			itemBean.setDescripcion(rs.getString("RAZON_SOCIAL"));
			itemBean.setDescripcionCorta(rs.getString("REPRESENTANTE"));
		}
		return itemBean;
	}

}
