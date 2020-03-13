package pe.com.sigbah.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.com.sigbah.common.bean.AlmacenStockBean;
import pe.com.sigbah.common.bean.AlmacenesWSBean;
import pe.com.sigbah.common.bean.BaseResponseEntity;
import pe.com.sigbah.common.bean.CategoriaStockBean;
import pe.com.sigbah.common.bean.ProductoStockBean;
import pe.com.sigbah.common.bean.StockXAlmacenWSBean;
import pe.com.sigbah.common.bean.UsuarioWSBean;
import pe.com.sigbah.service.StockService;
import pe.com.sigbah.web.controller.common.BaseController;

/**
 * @className: ConsultarStockRestController.java
 * @description: Clase para manejar las consultas stock.
 * @date: 28/07/2018
 * @author: Sumerio.
 */
@Controller
@RequestMapping(value = "/consultar/stock/rest")
public class ConsultarStockRestController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private StockService stockService;
	
	/**
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarAlmacenStock", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarAlmacenStock() {
		BaseResponseEntity baseResponseEntity = new BaseResponseEntity();
		try {		
			List<AlmacenStockBean> lista = stockService.listarAlmacenStock();
			baseResponseEntity.setData(lista);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return baseResponseEntity;
	}
	
	/**
	 * @param idCategoria
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarCategoriaStock/{idCategoria}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarCategoriaStock(@PathVariable("idCategoria") String idCategoria) {
		BaseResponseEntity baseResponseEntity = new BaseResponseEntity();
		try {
			List<CategoriaStockBean> lista = stockService.listarCategoriaStock(new CategoriaStockBean(Integer.valueOf(idCategoria)));
			baseResponseEntity.setData(lista);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return baseResponseEntity;
	}
	
	/**
	 * @param nombreProducto
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarProductoStock/{nombreProducto}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProductoStock(@PathVariable("nombreProducto") String nombreProducto) {
		BaseResponseEntity baseResponseEntity = new BaseResponseEntity();
		try {
			List<ProductoStockBean> lista = stockService.listarProductoStock(new ProductoStockBean(nombreProducto));
			baseResponseEntity.setData(lista);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return baseResponseEntity;
	}
	
	/**
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarAlmacenes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarAlmacenes() {
		BaseResponseEntity baseResponseEntity = new BaseResponseEntity();
		try {		
			List<AlmacenesWSBean> lista = stockService.listarAlmacenesWS();
			baseResponseEntity.setData(lista);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return baseResponseEntity;
	}
	
	/**
	 * @param nombreProducto
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/stockAlmacen/{idAlmacen}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarStockAlmacenes(@PathVariable("idAlmacen") String idAlm) {
		BaseResponseEntity baseResponseEntity = new BaseResponseEntity();
		try {
			StockXAlmacenWSBean idAl = new StockXAlmacenWSBean();
			idAl.setIdAlmacen(idAlm);
			List<StockXAlmacenWSBean> lista = stockService.stockXAlmacenWS(idAl);
			baseResponseEntity.setData(lista);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return baseResponseEntity;
	}
	
	/**
	 * @param nombreProducto
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/loginUsuario/{usu}/{pass}/{email}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarUsuario(@PathVariable("usu") String usu,
			@PathVariable("pass") String pass,
			@PathVariable("email") String email) {
		BaseResponseEntity baseResponseEntity = new BaseResponseEntity();
		try {
			UsuarioWSBean usua=new UsuarioWSBean();
			usua.setUsuario(usu);
			usua.setPassword(pass);
			usua.setEmail(email);
			List<UsuarioWSBean> lista = stockService.loginUsuarioWS(usua);
			baseResponseEntity.setData(lista);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return baseResponseEntity;
	}
	
	/**
	 * @param nombreProducto
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/loginUsuario///{email}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarUsuario1(
			@PathVariable("email") String email) {
		BaseResponseEntity baseResponseEntity = new BaseResponseEntity();
		try {
			UsuarioWSBean usua=new UsuarioWSBean();
			usua.setUsuario("");
			usua.setPassword("");
			usua.setEmail(email);
			List<UsuarioWSBean> lista = stockService.loginUsuarioWS(usua);
			baseResponseEntity.setData(lista);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return baseResponseEntity;
	}
	
	/**
	 * @param nombreProducto
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/loginUsuario/{usu}/{pass}//", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarUsuario2(@PathVariable("usu") String usu,
			@PathVariable("pass") String pass) {
		BaseResponseEntity baseResponseEntity = new BaseResponseEntity();
		try {
			UsuarioWSBean usua=new UsuarioWSBean();
			usua.setUsuario(usu);
			usua.setPassword(pass);
			usua.setEmail("");
			List<UsuarioWSBean> lista = stockService.loginUsuarioWS(usua);
			baseResponseEntity.setData(lista);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return baseResponseEntity;
	}
	

}
