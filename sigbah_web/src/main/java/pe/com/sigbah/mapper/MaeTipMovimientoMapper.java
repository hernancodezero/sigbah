package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.MaeTipMovimientoBean;

/**
 * @className: MaeTipMovimientoMapper.java
 * @description: 
 * @date: 16 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeTipMovimientoMapper implements RowMapper<MaeTipMovimientoBean> {
	

	@Override
	public MaeTipMovimientoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeTipMovimientoBean maeTipMovimientoBean = new MaeTipMovimientoBean();

		maeTipMovimientoBean.setIdTipMovimiento(rs.getInt("IDE_TIP_MOVIMIENTO"));
		maeTipMovimientoBean.setNombreMovimiento(rs.getString("NOM_MOVIMIENTO"));
		maeTipMovimientoBean.setFlagIngreso(rs.getString("FLG_INGRESO"));
		maeTipMovimientoBean.setFlagSalida(rs.getString("FLG_SALIDA"));
		maeTipMovimientoBean.setFlagDonacionIngreso(rs.getString("FLG_DONACION_INGRESO"));
		maeTipMovimientoBean.setFlagDonacionSalida(rs.getString("FLG_DONACION_SALIDA"));
		maeTipMovimientoBean.setFlagActivo(rs.getString("ESTADO"));

		return maeTipMovimientoBean;
	}

}
