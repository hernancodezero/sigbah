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
 * @className: TotalProductoAlimentoMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class TotalProductoAlimentoMapper implements RowMapper<ProgramacionAlimentoBean> {
	
	private List<Integer> listaIdProducto = null;

	/**
	 * @param arrIdProducto 
	 */
	public TotalProductoAlimentoMapper(List<Integer> arrIdProducto) {
		listaIdProducto = arrIdProducto;
	}

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProgramacionAlimentoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProgramacionAlimentoBean alimento = new ProgramacionAlimentoBean();
		alimento.setIdProgramacion(rs.getInt("IDE_PROGRAMACION"));
		alimento.setIdRacionOperativa(rs.getInt("IDE_RAC_OPERATIVA"));
		BigDecimal totalTm = BigDecimal.ZERO;
		List<ProductoAlimentoBean> listaProducto = new ArrayList<ProductoAlimentoBean>();
		for (Integer idProducto : listaIdProducto) {
			ProductoAlimentoBean producto = new ProductoAlimentoBean();
			producto.setIdProducto(idProducto);
			BigDecimal unidad = rs.getBigDecimal(idProducto.toString().concat("_PRESENTACION"));
			producto.setUnidad(unidad);			
			listaProducto.add(producto);
			totalTm = totalTm.add(unidad == null ? BigDecimal.ZERO : unidad);
		}
		
		alimento.setListaProducto(listaProducto);
		
		alimento.setTotalTm(totalTm);
		
		return alimento;
	}

}
