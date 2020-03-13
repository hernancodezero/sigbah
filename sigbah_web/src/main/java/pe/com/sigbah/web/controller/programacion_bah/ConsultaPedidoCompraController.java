package pe.com.sigbah.web.controller.programacion_bah;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;

import pe.com.sigbah.common.bean.ConsultaPedidoCompraBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.ListaRespuestaConsultaPedidoBean;
import pe.com.sigbah.common.bean.ListaRespuestaConsultaProductoBean;
import pe.com.sigbah.common.bean.ProductoConsultaPedidoBean;
import pe.com.sigbah.common.bean.ProductoConsultaProductoBean;
import pe.com.sigbah.common.bean.UsuarioBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.HtmlUtils;
import pe.com.sigbah.service.AdministracionService;
import pe.com.sigbah.service.GeneralService;
import pe.com.sigbah.service.ProgramacionService;
import pe.com.sigbah.web.controller.common.BaseController;

/**
 * @className: ConsultaPedidoCompraController.java
 * @description: 
 * @date: 13 jul. 2017
 * @author: whr.
 */

@Controller
@RequestMapping("/programacion-bath/consulta-pedido")
public class ConsultaPedidoCompraController extends BaseController {
	
private static final long serialVersionUID = 1L;
	
	@Autowired 
	private ProgramacionService programacionService;
	
	@Autowired 
	private GeneralService generalService;
	
	@Autowired 
	private AdministracionService administracionService;
	
	@RequestMapping(value = "/inicio/{indicador}", method = RequestMethod.GET)
    public String inicio(@PathVariable("indicador") String indicador, Model model) {
        try {
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
//        	
        	model.addAttribute("lista_anio", generalService.listarAnios());
        	model.addAttribute("lista_ddi", generalService.listarDdi(new ItemBean()));
        	model.addAttribute("lista_estado", generalService.listarEstado( new ItemBean(null,Constantes.ONE_INT)));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        	model.addAttribute("indicador", indicador);
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));

        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "consulta-pedido";
    }

	
	@RequestMapping(value = "/listarConsultaPedidosCompra", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarConsultaPedidosCompra(HttpServletRequest request, HttpServletResponse response) {
		List<ConsultaPedidoCompraBean> lista = null;
		try {			
			ConsultaPedidoCompraBean consultaBean = new ConsultaPedidoCompraBean();	
//			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
//			consultaBean.setIdDdi(Integer.parseInt(usuarioBean.getCodigoDdi()));
			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(consultaBean, request.getParameterMap());	
			
				lista = programacionService.listarConsultaPedidosCompra(consultaBean); 
			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/listarConsultaPedidosCompra2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarConsultaPedidosCompra2(HttpServletRequest request, HttpServletResponse response) {
		List<ConsultaPedidoCompraBean> lista = null;
		try {			
			ConsultaPedidoCompraBean consultaBean = new ConsultaPedidoCompraBean();	
//			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
//			consultaBean.setIdDdi(Integer.parseInt(usuarioBean.getCodigoDdi()));
			BeanUtils.populate(consultaBean, request.getParameterMap());	
			lista = programacionService.listarConsultaPedidosCompra2(consultaBean); 
			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/mostrarProductos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public ListaRespuestaConsultaPedidoBean mostrarProductos(HttpServletRequest request, HttpServletResponse response)  {
		
		ListaRespuestaConsultaPedidoBean detalle = new ListaRespuestaConsultaPedidoBean();
		try {
			ProductoConsultaPedidoBean pc=new ProductoConsultaPedidoBean();
			BeanUtils.populate(pc, request.getParameterMap());	
        	// Retorno los datos de session
//        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
//        	pc.setIdDdi(usuarioBean.getIdDdi());
        	
        	detalle = programacionService.obtenerConsultaPedido(pc);
    		
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
			return (ListaRespuestaConsultaPedidoBean) getBaseRespuesta(null);
        }
        return detalle;
    }
	
	
	@RequestMapping(value = "/mostrarProductosxPedido", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public ListaRespuestaConsultaPedidoBean mostrarProductosxPedido(HttpServletRequest request, HttpServletResponse response)  {
		
		ListaRespuestaConsultaPedidoBean detalle = new ListaRespuestaConsultaPedidoBean();
		try {
			ProductoConsultaPedidoBean pc=new ProductoConsultaPedidoBean();
			BeanUtils.populate(pc, request.getParameterMap());	
        
//        	detalle = programacionService.obtenerConsultaPedido(pc);
        	detalle = programacionService.mostrarProductosxPedido(pc);
        	
    		
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
			return (ListaRespuestaConsultaPedidoBean) getBaseRespuesta(null);
        }
        return detalle;
    }
	
}
