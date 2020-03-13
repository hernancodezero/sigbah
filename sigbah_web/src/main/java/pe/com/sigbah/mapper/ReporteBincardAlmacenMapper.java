package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.BincardAlmacenBean;

/**
 * @className: ReporteBincardAlmacenMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ReporteBincardAlmacenMapper implements RowMapper<BincardAlmacenBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public BincardAlmacenBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		BincardAlmacenBean bincardAlmacen = new BincardAlmacenBean();
		bincardAlmacen.setIdRegistro(rs.getString("IDE_REGISTRO"));
		bincardAlmacen.setCodigoAnio(rs.getString("COD_ANIO"));		
		bincardAlmacen.setCodigoMes(rs.getString("COD_MES"));
		bincardAlmacen.setNombreMes(rs.getString("NOMBRE_MES"));
		bincardAlmacen.setIdAlmacen(rs.getInt("IDE_ALMACEN"));
		bincardAlmacen.setIdProducto(rs.getInt("IDE_PRODUCTO"));
		bincardAlmacen.setNroLote(rs.getString("NRO_LOTE"));
		bincardAlmacen.setNroBincard(rs.getString("NRO_BINCARD"));
		bincardAlmacen.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		bincardAlmacen.setNombreUnidad(rs.getString("NOM_UNIDAD"));
		bincardAlmacen.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		bincardAlmacen.setFecha(rs.getString("FECHA"));
		bincardAlmacen.setConcepto(rs.getString("CONCEPTO"));		
		bincardAlmacen.setCantidadIngresos(rs.getBigDecimal("CANT_INGRESOS"));
		bincardAlmacen.setCantidadSalidas(rs.getBigDecimal("CANT_SALIDAS"));
		bincardAlmacen.setCantidadSaldo(rs.getBigDecimal("CANT_SALDO"));
		bincardAlmacen.setPrecioCompra(rs.getBigDecimal("PREC_COMPRA"));
		bincardAlmacen.setPrecioPromedio(rs.getBigDecimal("PREC_PROMEDIO"));
		bincardAlmacen.setImporteValorizado(rs.getBigDecimal("IMP_VALORIZADO"));
		bincardAlmacen.setMotivo(rs.getString("MOTIVO"));
		bincardAlmacen.setNroOrden(rs.getString("NRO_ORDEN"));
		bincardAlmacen.setTipo(rs.getString("TIPO"));
		return bincardAlmacen;
	}

}
