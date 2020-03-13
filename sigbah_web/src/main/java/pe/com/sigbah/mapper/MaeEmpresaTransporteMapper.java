package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.MaeAniosBean;
import pe.com.sigbah.common.bean.MaeCategoriaBean;
import pe.com.sigbah.common.bean.MaeChoferBean;
import pe.com.sigbah.common.bean.MaeEmpresaTransporteBean;

/**
 * @className: MaeEmpresaTransporteMapper.java
 * @description: 
 * @date: 15 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeEmpresaTransporteMapper implements RowMapper<MaeEmpresaTransporteBean> {
	

	@Override
	public MaeEmpresaTransporteBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeEmpresaTransporteBean maeEmpresaTransporteBean = new MaeEmpresaTransporteBean();

		maeEmpresaTransporteBean.setIdEmpresaTransporte(rs.getInt("ID"));
		maeEmpresaTransporteBean.setNombreEmpresa(rs.getString("EMPRESA"));
		maeEmpresaTransporteBean.setDireccion(rs.getString("DIRECCION"));
		maeEmpresaTransporteBean.setRepresentante(rs.getString("REPRESENTANTE"));
		maeEmpresaTransporteBean.setRuc(rs.getString("RUC"));
		maeEmpresaTransporteBean.setTipoTransporte(rs.getString("TIPO_TRANSPORTE"));
		maeEmpresaTransporteBean.setIdMedioTransporte(rs.getInt("FK_IDE_MED_TRANSPORTE"));
		maeEmpresaTransporteBean.setTelefono(rs.getString("TELEFONOS"));

		return maeEmpresaTransporteBean;
	}

}
