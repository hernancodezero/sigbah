package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.OrdenIngresoBean;

/**
 * @className: RegistroOrdenIngresoMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class RegistroOrdenIngreso1Mapper implements RowMapper<OrdenIngresoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public OrdenIngresoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrdenIngresoBean ordenIngreso = new OrdenIngresoBean();
		ordenIngreso.setIdIngreso(rs.getInt("IDE_INGRESO"));
		ordenIngreso.setNroOrdenIngreso(rs.getString("NRO_ORDEN_INGRESO"));
		ordenIngreso.setCodigoAnio(rs.getString("COD_ANIO"));
		ordenIngreso.setCodigoDdi(rs.getString("COD_DDI"));
		ordenIngreso.setIdDdi(rs.getInt("IDE_DDI"));
		ordenIngreso.setNombreDdi(rs.getString("NOMBRE_DDI"));
		ordenIngreso.setCodigoAlmacen(rs.getString("COD_ALMACEN"));
		ordenIngreso.setIdAlmacen(rs.getInt("FK_IDE_ALMACEN"));
		ordenIngreso.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		ordenIngreso.setCodigoMes(rs.getString("COD_MES"));
		ordenIngreso.setFechaEmision(rs.getString("FECHA_EMISION"));
		ordenIngreso.setIdEstado(rs.getInt("FK_IDE_ESTADO"));
		ordenIngreso.setNombreEstado(rs.getString("NOMBRE_ESTADO"));
		ordenIngreso.setIdMovimiento(rs.getInt("FK_IDE_TIP_MOVIMIENTO"));
		ordenIngreso.setNombreMovimiento(rs.getString("NOMBRE_MOVIMIENTO"));
		ordenIngreso.setNroOrdenCompra(rs.getString("NRO_ORDEN_COMPRA"));
		ordenIngreso.setConcepto(rs.getString("CONCEPTO"));
		ordenIngreso.setFlagTipoCompra(rs.getString("FLG_TIP_COMPRA"));
		ordenIngreso.setFlagControlCalidad(rs.getString("FLG_CONTROL_CALIDAD"));
		ordenIngreso.setIdControlCalidad(rs.getInt("FK_IDE_CONTROL_CALIDAD"));
		ordenIngreso.setIdProveedor(rs.getInt("FK_IDE_PROVEEDOR"));
		ordenIngreso.setProvRep(rs.getString("PROV_REP"));
		ordenIngreso.setRepresentante(rs.getString("REPRESENTANTE")!=null?rs.getString("REPRESENTANTE"):"");
		ordenIngreso.setRazonSocial(rs.getString("RAZON_SOCIAL"));
		ordenIngreso.setIdAlmacenProcedencia(rs.getInt("FK_ID_ALM_PROCEDENCIA"));
		ordenIngreso.setNombreAlmacenProcedencia(rs.getString("NOMBRE_ALMACEN_PROCEDENCIA"));
		ordenIngreso.setIdMedioTransporte(rs.getInt("FK_IDE_MED_TRANSPORTE"));
		ordenIngreso.setFechaLlegada(rs.getString("FECHA_LLEGADA"));
		ordenIngreso.setIdEmpresaTransporte(rs.getInt("FK_IDE_EMP_TRANS"));
		ordenIngreso.setEmpresaTransporte(rs.getString("EMPRESA_TRANSPORTE"));
		ordenIngreso.setIdChofer(rs.getInt("FK_IDE_CHOFER"));
		ordenIngreso.setNombreChofer(rs.getString("NOMBRE_CHOFER"));
		ordenIngreso.setNroPlaca(rs.getString("NRO_PLACA"));
		ordenIngreso.setIdResponsable(rs.getInt("FK_IDE_RESPONSABLE"));
		ordenIngreso.setResponsable(rs.getString("RESPONSABLE"));
		ordenIngreso.setObservacion(rs.getString("OBSERVACION")!=null?rs.getString("OBSERVACION").replaceAll("[\n]", "<<E>>").replaceAll("\"", "<<D>>").replaceAll("\'", "<<S>>"):"");
		ordenIngreso.setIdDonacion(rs.getInt("FK_IDE_DONACION"));
		ordenIngreso.setIdSalida(rs.getInt("FK_IDE_SALIDA"));
		return ordenIngreso;
	}

}
