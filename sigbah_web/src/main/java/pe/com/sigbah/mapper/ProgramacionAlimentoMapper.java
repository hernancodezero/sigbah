package pe.com.sigbah.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoAlimentoBean;
import pe.com.sigbah.common.bean.ProgramacionAlimentoBean;

/**
 * @className: ProgramacionAlimentoMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProgramacionAlimentoMapper implements RowMapper<ProgramacionAlimentoBean> {
	
	private List<Integer> listaIdProducto = null;

	/**
	 * @param arrIdProducto 
	 */
	public ProgramacionAlimentoMapper(List<Integer> arrIdProducto) {
		listaIdProducto = arrIdProducto;
	}

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProgramacionAlimentoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProgramacionAlimentoBean alimento = new ProgramacionAlimentoBean();
		alimento.setIdProgramacionUbigeo(rs.getInt("ID_PROG_UBIGEO"));
		alimento.setCodigoAnio(rs.getString("COD_ANIO"));
		alimento.setIdDdi(rs.getInt("IDE_DDI"));
		alimento.setIdProgramacion(rs.getInt("ID_PROG"));
		alimento.setCodigoDistrito(rs.getString("COD_DIST"));
		alimento.setDepartamento(rs.getString("DPTO"));
		alimento.setProvincia(rs.getString("PROV"));
		alimento.setDistrito(rs.getString("DIS"));		
		alimento.setPersAfect(rs.getBigDecimal("NPA"));
		alimento.setPersDam(rs.getBigDecimal("NPD"));
		alimento.setTotalPers(rs.getBigDecimal("NPT"));
		alimento.setTotalRaciones(rs.getBigDecimal("TOT_RACIONES"));
		BigDecimal totalTm = BigDecimal.ZERO;
		List<ProductoAlimentoBean> listaProducto = new ArrayList<ProductoAlimentoBean>();
		for (Integer idProducto : listaIdProducto) {
			ProductoAlimentoBean producto = new ProductoAlimentoBean();
			producto.setIdProducto(idProducto);
			BigDecimal unidad = rs.getBigDecimal(idProducto.toString().concat("_NRO_UNIDADES"));
			producto.setUnidad(unidad);			
			listaProducto.add(producto);
			totalTm = totalTm.add(unidad == null ? BigDecimal.ZERO : unidad);
		}
		
		alimento.setListaProducto(listaProducto);
		
		alimento.setTotalTm(totalTm);
		
		return alimento;
	}

}
