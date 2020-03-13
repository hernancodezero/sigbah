package pe.com.sigbah.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProductoAlimentoBean;
import pe.com.sigbah.common.bean.ProgramacionNoAlimentarioBean;

/**
 * @className: ProgramacionNoAlimentarioMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProgramacionNoAlimentarioMapper implements RowMapper<ProgramacionNoAlimentarioBean> {
	
	private List<Integer> listaIdProducto = null;

	/**
	 * @param arrIdProducto 
	 */
	public ProgramacionNoAlimentarioMapper(List<Integer> arrIdProducto) {
		listaIdProducto = arrIdProducto;
	}

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProgramacionNoAlimentarioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProgramacionNoAlimentarioBean alimento = new ProgramacionNoAlimentarioBean();
		alimento.setIdProgramacionUbigeo(rs.getInt("ID_PROG_UBIGEO"));
		alimento.setCodigoAnio(rs.getString("COD_ANIO"));
		alimento.setIdDdi(rs.getInt("IDE_DDI"));
		alimento.setIdProgramacion(rs.getInt("ID_PROG"));
		alimento.setCodigoDistrito(rs.getString("COD_DIST"));
		alimento.setDepartamento(rs.getString("DPTO"));
		alimento.setProvincia(rs.getString("PROV"));
		alimento.setDistrito(rs.getString("DIS"));		
		alimento.setFamAfect(rs.getBigDecimal("NFA") == null ? BigDecimal.ZERO : rs.getBigDecimal("NFA"));
		alimento.setFamDam(rs.getBigDecimal("NFD") == null ? BigDecimal.ZERO : rs.getBigDecimal("NFD"));
		alimento.setTotalFam(rs.getBigDecimal("NFT") == null ? BigDecimal.ZERO : rs.getBigDecimal("NFT"));
		alimento.setPersAfect(rs.getBigDecimal("NPA") == null ? BigDecimal.ZERO : rs.getBigDecimal("NPA"));
		alimento.setPersDam(rs.getBigDecimal("NPD") == null ? BigDecimal.ZERO : rs.getBigDecimal("NPD"));
		alimento.setTotalPers(rs.getBigDecimal("NPT") == null ? BigDecimal.ZERO : rs.getBigDecimal("NPT"));
		BigDecimal totalTm = BigDecimal.ZERO;
		List<ProductoAlimentoBean> listaProducto = new ArrayList<ProductoAlimentoBean>();
		for (Integer idProducto : listaIdProducto) {
			ProductoAlimentoBean producto = new ProductoAlimentoBean();
			producto.setIdProducto(idProducto);
			BigDecimal unidad = rs.getBigDecimal(idProducto.toString().concat("_NRO_UNIDADES"));
			producto.setUnidad(unidad == null ? BigDecimal.ZERO : unidad);			
			listaProducto.add(producto);
			totalTm = totalTm.add(unidad == null ? BigDecimal.ZERO : unidad);
		}
		
		alimento.setListaProducto(listaProducto);
		
		alimento.setTotalTm(totalTm);
		
		return alimento;
	}

}
