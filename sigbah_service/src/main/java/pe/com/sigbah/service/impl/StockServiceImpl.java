package pe.com.sigbah.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sigbah.common.bean.AlmacenStockBean;
import pe.com.sigbah.common.bean.AlmacenesWSBean;
import pe.com.sigbah.common.bean.CategoriaStockBean;
import pe.com.sigbah.common.bean.ProductoStockBean;
import pe.com.sigbah.common.bean.StockXAlmacenWSBean;
import pe.com.sigbah.common.bean.UsuarioWSBean;
import pe.com.sigbah.dao.StockDao;
import pe.com.sigbah.service.StockService;

/**
 * @className: StockServiceImpl.java
 * @description: Clase que contiene el consumo de los procedimientos del package BAH_PKG_STOCK.
 * @date: 28/07/2018
 * @author: Sumerio.
 */
@Service
public class StockServiceImpl implements StockService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private StockDao stockDao;

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.StockService#listarAlmacenStock()
	 */
	@Override
	public List<AlmacenStockBean> listarAlmacenStock() throws Exception {
		return stockDao.listarAlmacenStock();
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.StockService#listarCategoriaStock(pe.com.sigbah.common.bean.CategoriaStockBean)
	 */
	@Override
	public List<CategoriaStockBean> listarCategoriaStock(CategoriaStockBean categoriaStockBean) throws Exception {
		return stockDao.listarCategoriaStock(categoriaStockBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.StockService#listarProductoStock(pe.com.sigbah.common.bean.ProductoStockBean)
	 */
	@Override
	public List<ProductoStockBean> listarProductoStock(ProductoStockBean productoStockBean) throws Exception {
		return stockDao.listarProductoStock(productoStockBean);
	}
	
	@Override
	public List<AlmacenesWSBean> listarAlmacenesWS() throws Exception {
		return stockDao.listarAlmacenesWS();
	}

	@Override
	public List<UsuarioWSBean> loginUsuarioWS(UsuarioWSBean usuarioWSBean) throws Exception {
		return stockDao.loginUsuarioWS(usuarioWSBean);
	}
	
	@Override
	public List<StockXAlmacenWSBean> stockXAlmacenWS(StockXAlmacenWSBean stockXAlmacenWSBean) throws Exception {
		return stockDao.stockXAlmacenWS(stockXAlmacenWSBean);
	}
}
