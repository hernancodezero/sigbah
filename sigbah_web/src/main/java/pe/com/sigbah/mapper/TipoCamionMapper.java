package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.util.Constantes;

/**
 * @className: TipoCamionMapper.java
 * @description: 
 * @date: 22 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class TipoCamionMapper implements RowMapper<ItemBean> {
	
	private boolean all_records = false; // Obtener todos los registros

	/**
	 * @param parametro
	 */
	public TipoCamionMapper(Integer parametro) {
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
			itemBean.setIcodigo(rs.getInt("IDE_TIP_CAMION"));
			itemBean.setDescripcion(rs.getString("DESCRIPCION"));
			itemBean.setBcodigo(rs.getBigDecimal("TONELAJE"));
			itemBean.setBcodigoParam2(rs.getBigDecimal("VOLUMEN"));
		} else {	
			itemBean.setIcodigo(rs.getInt("IDE_TIP_CAMION"));
			itemBean.setDescripcion(rs.getString("DESCRIPCION"));
			itemBean.setBcodigo(rs.getBigDecimal("TONELAJE"));
			itemBean.setBcodigoParam2(rs.getBigDecimal("VOLUMEN"));
		}
		return itemBean;
	}

}
