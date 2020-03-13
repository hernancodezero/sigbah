package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ControlCalidadBean;

/**
 * @className: RegistroControlCalidadMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class RegistroControlCalidad1Mapper implements RowMapper<ControlCalidadBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ControlCalidadBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ControlCalidadBean controlCalidad = new ControlCalidadBean();
		controlCalidad.setIdControlCalidad(rs.getInt("IDE_CONTROL_CALIDAD"));
		controlCalidad.setCodigoAnio(rs.getString("COD_ANIO"));
		controlCalidad.setCodigoMes(rs.getString("COD_MES"));
		controlCalidad.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		controlCalidad.setCodigoAlmacen(rs.getString("COD_ALMACEN"));
		controlCalidad.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		controlCalidad.setIdDdi(rs.getInt("FK_IDE_DDI"));
		controlCalidad.setCodigoDdi(rs.getString("COD_DDI"));
		controlCalidad.setNombreDdi(rs.getString("NOM_DDI"));		
		controlCalidad.setNroControlCalidad(rs.getString("NRO_REP_CONTROL_CALIDAD"));		
		controlCalidad.setFechaEmision(rs.getString("FEC_EMISION"));		
		controlCalidad.setIdTipoControl(rs.getInt("COD_TIPO_CONTROL_CALIDAD"));
		controlCalidad.setIdEstado(rs.getInt("COD_ESTADO"));
		controlCalidad.setNroOrdenCompra(rs.getString("NRO_ORDEN_COMPRA"));
		controlCalidad.setIdAlmacenOrigen(rs.getInt("FK_IDE_ALMACEN_OD"));
		controlCalidad.setIdEncargado(rs.getInt("FK_IDE_ENCARGADO"));
		controlCalidad.setIdInspector(rs.getInt("FK_IDE_INSPECTOR"));
		controlCalidad.setIdProveedor(rs.getInt("FK_IDE_PROVEEDOR"));
		controlCalidad.setProvRep(rs.getString("PROV_REP"));
		controlCalidad.setIdEmpresaTransporte(rs.getInt("FK_IDE_EMP_TRANS"));
		controlCalidad.setIdChofer(rs.getInt("FK_IDE_CHOFER"));
		controlCalidad.setNroPlaca(rs.getString("NRO_PLACA"));
		controlCalidad.setFlagTipoBien(rs.getString("FLG_TIPO_BIEN"));
		controlCalidad.setConclusiones(rs.getString("CONCLUSIONES")!=null?rs.getString("CONCLUSIONES").replaceAll("[\n]", "<<E>>").replaceAll("\"", "<<D>>").replaceAll("\'", "<<S>>"):"");
		controlCalidad.setRecomendaciones(rs.getString("RECOMENDACIONES")!=null?rs.getString("RECOMENDACIONES").replaceAll("[\n]", "<<E>>").replaceAll("\"", "<<D>>").replaceAll("\'", "<<S>>"):"");
		controlCalidad.setConcepto(rs.getString("CONCEPTO"));
		controlCalidad.setIdDonacion(rs.getInt("FK_IDE_DONACION"));
		controlCalidad.setNombreDonante(rs.getString("NOM_DONANTE"));
		controlCalidad.setRazonSocial(rs.getString("RAZON_SOCIAL")!=null?rs.getString("RAZON_SOCIAL"):"");
		controlCalidad.setRepresentante(rs.getString("REPRESENTANTE")!=null?rs.getString("REPRESENTANTE"):"");
		return controlCalidad;
	}

}
