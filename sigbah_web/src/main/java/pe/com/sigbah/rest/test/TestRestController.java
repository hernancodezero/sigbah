package pe.com.sigbah.rest.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import pe.com.sigbah.common.bean.BaseRequestEntity;
import pe.com.sigbah.common.bean.UsuarioBean;
import pe.com.sigbah.web.controller.common.BaseController;

/**
 * @className: TestRestController.java
 * @description: Clase para manejar el consumo de los servicios rest.
 * @date: 28/07/2018
 * @author: Sumerio.
 */
@Controller
@RequestMapping(value = "/test/rest")
public class TestRestController extends BaseController {

	private static final long serialVersionUID = 1L;
	private static final String SERVICIO_LISTAR_ALMACEN_STOCK = "/consultar/stock/rest/listarAlmacenStock";
	private static final String SERVICIO_LISTAR_CATEGORIA_STOCK = "/consultar/stock/rest/listarCategoriaStock/{idCategoria}";
	private static final String SERVICIO_LISTAR_PRODUCTO_STOCK = "/consultar/stock/rest/listarProductoStock/{nombreProducto}";
	private static final String SERVICIO_LISTAR_ALMACENES = "/consultar/stock/rest/listarAlmacenes";
	private static final String SERVICIO_LOGIN_USUARIO = "/consultar/stock/rest/loginUsuario/{usu}/{pass}/{email}";
	private static final String SERVICIO_LISTAR_STOCK_ALMACEN = "/consultar/stock/rest/stockAlmacen/{idAlmacen}";
	
	/**
	 * @param model 
	 * @param request 
	 * @return - Retorna a la vista JSP.
	 */
	@RequestMapping(value = "/testAlmacenStock", method = RequestMethod.GET)
    public String testAlmacenStock(Model model, HttpServletRequest request) {
		RestTemplate restTemplate = new RestTemplate();
		try {
//			http://localhost:8085/sigbah_web/consultar/stock/rest/listarAlmacenStock
			String requestURLServicio = obtenerRequestURL(request) + SERVICIO_LISTAR_ALMACEN_STOCK;
			BaseRequestEntity base = restTemplate.getForObject(requestURLServicio, BaseRequestEntity.class);
        	model.addAttribute("listarAlmacenStock", base.getData());
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        } finally {
        	usuarioBean = new UsuarioBean();
        	usuarioBean.setNombreUsuario("TEST - listarAlmacenStock");
        	model.addAttribute("usuarioBean", usuarioBean);
		}
        return "testAlmacenStock";
    }
	
	/**
	 * @param model 
	 * @param request 
	 * @return - Retorna a la vista JSP.
	 */
	@RequestMapping(value = "/testCategoriaStock", method = RequestMethod.GET)
    public String testCategoriaStock(Model model, HttpServletRequest request) {
		RestTemplate restTemplate = new RestTemplate();
		try {
//			http://localhost:8085/sigbah_web/consultar/stock/rest/listarCategoriaStock/{idCategoria}
			String requestURLServicio = obtenerRequestURL(request) + SERVICIO_LISTAR_CATEGORIA_STOCK;
			BaseRequestEntity base = restTemplate.getForObject(requestURLServicio, BaseRequestEntity.class, 3);
        	model.addAttribute("listarCategoriaStock", base.getData());
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        } finally {
        	usuarioBean = new UsuarioBean();
        	usuarioBean.setNombreUsuario("TEST - listarCategoriaStock");
        	model.addAttribute("usuarioBean", usuarioBean);
		}
        return "testCategoriaStock";
    }
	
	/**
	 * @param model 
	 * @param request 
	 * @return - Retorna a la vista JSP.
	 */
	@RequestMapping(value = "/testProductoStock", method = RequestMethod.GET)
    public String testProductoStock(Model model, HttpServletRequest request) {
		RestTemplate restTemplate = new RestTemplate();
		try {
//			http://localhost:8085/sigbah_web/consultar/stock/rest/listarProductoStock/{nombreProducto}
			String requestURLServicio = obtenerRequestURL(request) + SERVICIO_LISTAR_PRODUCTO_STOCK;
			BaseRequestEntity base = restTemplate.getForObject(requestURLServicio, BaseRequestEntity.class, "ARROZ");
        	model.addAttribute("listarProductoStock", base.getData());
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        } finally {
        	usuarioBean = new UsuarioBean();
        	usuarioBean.setNombreUsuario("TEST - listarProductoStock");
        	model.addAttribute("usuarioBean", usuarioBean);
		}
        return "testProductoStock";
    }
	
	private String obtenerRequestURL(HttpServletRequest request) {
		String requestURL = request.getRequestURL().toString();
		int tamanio = 10;
		int posicion = requestURL.indexOf("sigbah_web");
		if (posicion == -1) {
			tamanio = 6;
			posicion = requestURL.indexOf("sigbah");
		}
		String url = requestURL.substring(0, posicion + tamanio);
		return url;
	}

	/**
	 * @param model 
	 * @param request 
	 * @return - Retorna a la vista JSP.
	 */
	@RequestMapping(value = "/testAlmacenes", method = RequestMethod.GET)
    public String testAlmacenes(Model model, HttpServletRequest request) {
		RestTemplate restTemplate = new RestTemplate();
		try {
//			http://localhost:8085/sigbah_web/consultar/stock/rest/listarAlmacenStock
			String requestURLServicio = obtenerRequestURL(request) + SERVICIO_LISTAR_ALMACENES;
			BaseRequestEntity base = restTemplate.getForObject(requestURLServicio, BaseRequestEntity.class);
        	model.addAttribute("listarAlmacenStock", base.getData());
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        } finally {
        	usuarioBean = new UsuarioBean();
        	usuarioBean.setNombreUsuario("TEST - listarAlmacenes");
        	model.addAttribute("usuarioBean", usuarioBean);
		}
        return "testAlmacenStock";
    }
	
	/**
	 * @param model 
	 * @param request 
	 * @return - Retorna a la vista JSP.
	 */
	@RequestMapping(value = "/testLoginUsuario", method = RequestMethod.GET)
    public String testLoginUsuario(Model model, HttpServletRequest request) {
		RestTemplate restTemplate = new RestTemplate();
		try {
//			http://localhost:8085/sigbah_web/consultar/stock/rest/listarProductoStock/{nombreProducto}
			String requestURLServicio = obtenerRequestURL(request) + SERVICIO_LOGIN_USUARIO;
			BaseRequestEntity base = restTemplate.getForObject(requestURLServicio, BaseRequestEntity.class, "administrador", "admin","ala@ala.com");
			System.out.println("DATA: "+base.getData());
        	model.addAttribute("testLoginUsuario", base.getData());
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        } finally {
        	usuarioBean = new UsuarioBean();
        	usuarioBean.setNombreUsuario("TEST - LoginUsuario");
        	model.addAttribute("usuarioBean", usuarioBean);
		}
        return "testProductoStock";
    }
	
	/**
	 * @param model 
	 * @param request 
	 * @return - Retorna a la vista JSP.
	 */
	@RequestMapping(value = "/testStockXAlmacen", method = RequestMethod.GET)
    public String testStockXAlmacen(Model model, HttpServletRequest request) {
		RestTemplate restTemplate = new RestTemplate();
		try {
//			http://localhost:8085/sigbah_web/consultar/stock/rest/listarProductoStock/{nombreProducto}
			String requestURLServicio = obtenerRequestURL(request) + SERVICIO_LISTAR_STOCK_ALMACEN;
			BaseRequestEntity base = restTemplate.getForObject(requestURLServicio, BaseRequestEntity.class, "19");
        	model.addAttribute("listarProductoStock", base.getData());
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        } finally {
        	usuarioBean = new UsuarioBean();
        	usuarioBean.setNombreUsuario("TEST - listarAlmacenXStock");
        	model.addAttribute("usuarioBean", usuarioBean);
		}
        return "testProductoStock";
    }
}
