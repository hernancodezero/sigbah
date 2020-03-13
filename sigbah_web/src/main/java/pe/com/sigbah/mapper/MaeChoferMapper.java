package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.MaeAniosBean;
import pe.com.sigbah.common.bean.MaeCategoriaBean;
import pe.com.sigbah.common.bean.MaeChoferBean;

/**
 * @className: MaeChoferMapper.java
 * @description: 
 * @date: 15 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeChoferMapper implements RowMapper<MaeChoferBean> {
	

	@Override
	public MaeChoferBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeChoferBean maeChoferBean = new MaeChoferBean();

		maeChoferBean.setIdChofer(rs.getInt("ID"));
		maeChoferBean.setNombreChofer(rs.getString("NOMBRE"));
		maeChoferBean.setNroDni(rs.getString("NRO_DNI"));
		maeChoferBean.setNroBrevete(rs.getString("NRO_BREVETE"));
		maeChoferBean.setNombreEmpresa(rs.getString("NOM_EMPRESA"));
		maeChoferBean.setIdEmpresaTransporte(rs.getInt("fk_ide_emp_trans"));
		

		
		return maeChoferBean;
	}

}
