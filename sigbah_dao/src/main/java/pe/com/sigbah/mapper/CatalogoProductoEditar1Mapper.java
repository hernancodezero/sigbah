package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.CatalogoProductoBean;

/**
 * @className: CatalogoProductoMapper.java
 * @description: 
 * @date: 23 de ago. de 2017
 * @author: ARCHY.
 */
public class CatalogoProductoEditar1Mapper implements RowMapper<CatalogoProductoBean> {
	

	@Override
	public CatalogoProductoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		CatalogoProductoBean catalogoProductoBean = new CatalogoProductoBean();
													  
		catalogoProductoBean.setIdProducto(rs.getInt("IDE_PRODUCTO"));
		catalogoProductoBean.setNombreProducto(rs.getString("NOM_PRODUCTO"));
		catalogoProductoBean.setCodigoSiga(rs.getString("COD_SIGA"));
		catalogoProductoBean.setIdCategoria(rs.getInt("FK_IDE_CATEGORIA_BAH"));
		catalogoProductoBean.setIdEnvase(rs.getInt("FK_IDE_ENVASE"));
		catalogoProductoBean.setIdUnidadMedida(rs.getInt("FK_IDE_UNID_MEDIDA"));
		catalogoProductoBean.setIdFamProducto(rs.getInt("IDE_FAMPRODUCTO"));
		catalogoProductoBean.setObservacion (rs.getString("OBSERVACION"));
		catalogoProductoBean.setIdEjecutora(rs.getInt("SEC_EJEC"));
		catalogoProductoBean.setTipoBien(rs.getString("TIPO_BIEN"));
		catalogoProductoBean.setGrupoBien(rs.getString("GRUPO_BIEN"));
		catalogoProductoBean.setClaseBien(rs.getString("CLASE_BIEN"));
		catalogoProductoBean.setFamiliaBien(rs.getString("FAMILIA_BIEN"));
		catalogoProductoBean.setItemBien(rs.getString("ITEM_BIEN"));
		catalogoProductoBean.setEstado(rs.getString("ESTADO"));
		catalogoProductoBean.setCodigoProducto(rs.getString("COD_PRODUCTO"));
		catalogoProductoBean.setDimLargo(rs.getBigDecimal("DIM_LARGO"));
		catalogoProductoBean.setDimAncho(rs.getBigDecimal("DIM_ANCHO"));
		catalogoProductoBean.setDimAlto(rs.getBigDecimal("DIM_ALTO"));
		catalogoProductoBean.setPesoNeto(rs.getBigDecimal("PESO_UNIT_NETO"));
		catalogoProductoBean.setPesoBruto(rs.getBigDecimal("PESO_UNIT_BRUTO"));
		catalogoProductoBean.setTipoEnvaseSec(rs.getInt("FK_TIP_ENVASE_SEC"));
		catalogoProductoBean.setUnidadEnvaseSec(rs.getInt("UNIDADES_ENVASE_SEC"));
		catalogoProductoBean.setDescripcionEnvaseSec(rs.getString("DESCR_ENVASE_SEC"));
		
		return catalogoProductoBean;
	}

}
