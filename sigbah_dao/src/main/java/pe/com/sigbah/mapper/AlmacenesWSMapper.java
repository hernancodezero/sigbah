package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.AlmacenesWSBean;

/**
 * @className: AlmacenesWSMapper.java
 * @description: Clase asociado al almacenes.
 * @date: 14/08/2018
 * @author: Alan Chumpitaz.
 */
public class AlmacenesWSMapper implements RowMapper<AlmacenesWSBean> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public AlmacenesWSBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		AlmacenesWSBean almacenStockBean = new AlmacenesWSBean();
		almacenStockBean.setIdDdi(rs.getInt("ID_DDI"));
		almacenStockBean.setIdAlmacen(rs.getInt("ID_ALM"));
		almacenStockBean.setNombreAlmacen(rs.getString("NOM_ALM"));
		return almacenStockBean;
	}

}
