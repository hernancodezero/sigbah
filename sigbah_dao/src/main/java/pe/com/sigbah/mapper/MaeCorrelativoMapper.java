package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.MaeCorrelativoBean;

/**
 * @className: MaeCorrelativoMapper.java
 * @description: 
 * @date: 31 de jul. de 2018
 * @author: ARCHY.
 */
public class MaeCorrelativoMapper implements RowMapper<MaeCorrelativoBean> {
	

	@Override
	public MaeCorrelativoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeCorrelativoBean maeCorrelativoBean = new MaeCorrelativoBean();

		maeCorrelativoBean.setIdCorrelativo(rs.getInt("IDE_CORRELATIVO"));
		maeCorrelativoBean.setIdAlmacen(rs.getInt("IDE_ALMACEN"));
		maeCorrelativoBean.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		maeCorrelativoBean.setNroSerie(rs.getString("NRO_SERIE"));
		maeCorrelativoBean.setNroInicio(rs.getString("NRO_CORR_INI"));
		maeCorrelativoBean.setNroFin(rs.getString("NRO_CORR_FIN"));
		maeCorrelativoBean.setNroActual(rs.getString("NRO_CORR_ACTUAL"));
		maeCorrelativoBean.setNombreImprenta(rs.getString("NOM_IMPRENTA"));
		maeCorrelativoBean.setRucImprenta(rs.getString("RUC_IMPRENTA"));
		maeCorrelativoBean.setNroAutorizacion(rs.getString("NRO_AUTORIZACION"));
		maeCorrelativoBean.setFechaInicio(rs.getString("FEC_INICIO"));
		maeCorrelativoBean.setFlagActivo(rs.getString("FLG_ACTIVO"));
		maeCorrelativoBean.setCodTipo(rs.getString("COD_TIPO"));
		maeCorrelativoBean.setActivarFormato(rs.getString("ESTADO"));

		return maeCorrelativoBean;
	}

}
