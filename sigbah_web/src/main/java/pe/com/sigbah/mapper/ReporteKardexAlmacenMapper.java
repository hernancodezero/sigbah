package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.KardexAlmacenBean;

/**
 * @className: ReporteKardexAlmacenMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ReporteKardexAlmacenMapper implements RowMapper<KardexAlmacenBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public KardexAlmacenBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		KardexAlmacenBean kardexAlmacen = new KardexAlmacenBean();
		kardexAlmacen.setIdRegistro(rs.getString("IDE_REGISTRO"));
		kardexAlmacen.setCodigoAnio(rs.getString("COD_ANIO"));		
		kardexAlmacen.setCodigoMes(rs.getString("COD_MES"));
		kardexAlmacen.setNombreMes(rs.getString("NOMBRE_MES"));
		kardexAlmacen.setIdAlmacen(rs.getInt("IDE_ALMACEN"));
		kardexAlmacen.setIdProducto(rs.getInt("IDE_PRODUCTO"));
		kardexAlmacen.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		kardexAlmacen.setNroKardex(rs.getString("NRO_KARDEX"));		
		kardexAlmacen.setNombreUnidad(rs.getString("NOM_UNIDAD"));
		kardexAlmacen.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		kardexAlmacen.setFecha(rs.getString("FECHA"));
		kardexAlmacen.setConcepto(rs.getString("CONCEPTO"));		
		kardexAlmacen.setCantidadIngresos(rs.getBigDecimal("CANT_INGRESOS"));
		kardexAlmacen.setCantidadSalidas(rs.getBigDecimal("CANT_SALIDAS"));
		kardexAlmacen.setCantidadSaldo(rs.getBigDecimal("CANT_SALDO"));
		kardexAlmacen.setPrecioCompra(rs.getBigDecimal("PREC_COMPRA"));
		kardexAlmacen.setPrecioPromedio(rs.getBigDecimal("PREC_PROMEDIO"));
		kardexAlmacen.setImporteValorizado(rs.getBigDecimal("IMP_VALORIZADO"));
		kardexAlmacen.setMotivo(rs.getString("MOTIVO"));
		kardexAlmacen.setNroOrden(rs.getString("NRO_ORDEN"));
		kardexAlmacen.setTipo(rs.getString("TIPO"));
		return kardexAlmacen;
	}

}
