package pe.com.sigbah.dao;

import java.util.List;

import pe.com.sigbah.common.bean.AlmacenStockBean;
import pe.com.sigbah.common.bean.AlmacenesWSBean;
import pe.com.sigbah.common.bean.CategoriaStockBean;
import pe.com.sigbah.common.bean.ProductoStockBean;
import pe.com.sigbah.common.bean.StockXAlmacenWSBean;
import pe.com.sigbah.common.bean.UsuarioWSBean;

/**
 * @className: StockDao.java
 * @description: Clase que contiene el consumo de los procedimientos del package BAH_PKG_STOCK.
 * @date: 28/07/2018
 * @author: Sumerio.
 */
public interface StockDao {
	
	/**
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<AlmacenStockBean> listarAlmacenStock() throws Exception;
	
	/**
	 * @param categoriaStockBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<CategoriaStockBean> listarCategoriaStock(CategoriaStockBean categoriaStockBean) throws Exception;
	
	/**
	 * @param productoStockBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ProductoStockBean> listarProductoStock(ProductoStockBean productoStockBean) throws Exception;

	/**
	 * @return
	 * @throws Exception
	 */
	public abstract List<AlmacenesWSBean> listarAlmacenesWS() throws Exception;

	/**
	 * @param usuarioWSBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<UsuarioWSBean> loginUsuarioWS(UsuarioWSBean usuarioWSBean) throws Exception;

	/**
	 * @param stockXAlmacenWSBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<StockXAlmacenWSBean> stockXAlmacenWS(StockXAlmacenWSBean stockXAlmacenWSBean) throws Exception;

}
