package pe.com.sigbah.web.controller.donaciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;

import pe.com.sigbah.common.bean.CierreStockBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.UsuarioBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.HtmlUtils;
import pe.com.sigbah.service.AdministracionService;
import pe.com.sigbah.service.DonacionService;
import pe.com.sigbah.service.GeneralService;
import pe.com.sigbah.service.LogisticaService;
import pe.com.sigbah.web.controller.common.BaseController;

/**
 * @className: DonacionesCierreStockController.java
 * @description: 
 * @date: 02 de Ago. de 2017
 * @author: ARCHY.
 */
@Controller
@RequestMapping("/donaciones/cierre-stock")
public class DonacionesCierreStockController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private LogisticaService logisticaService;
	
	@Autowired 
	private GeneralService generalService;
	
	@Autowired 
	private DonacionService donacionService;
	
	@Autowired 
	private AdministracionService administracionService;
	
	/**
	 * @param indicador 
	 * @param model 
	 * @return - Retorna a la vista JSP.
	 */
	@RequestMapping(value = "/inicio/{indicador}", method = RequestMethod.GET)
    public String inicio(@PathVariable("indicador") String indicador, Model model) {
		String ruta="";
        try {
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	if(usuarioBean.getIdAlmacen()==null){
        		ruta = "noAlmacen";
        	}else{
        		CierreStockBean cierre = new CierreStockBean();
           		cierre = administracionService.mesTrabajoActivo(usuarioBean.getIdAlmacen(), Constantes.TIPO_ORIGEN_DONACIONES);
           		System.out.println("TRABAJO : "+cierre.getCodigoMes());
           		if(cierre.getCodigoMes()==null){
           			ruta = "mesNoAbierto";
           		}else{
           			usuarioBean.setCodigoAnio(cierre.getCodigoAnio());
           			usuarioBean.setCodigoMes(cierre.getCodigoMes());
           			context().setAttribute("usuarioBean", usuarioBean, RequestAttributes.SCOPE_SESSION);
		        	model.addAttribute("lista_anio", generalService.listarAnios());
		        	
		        	model.addAttribute("lista_almacen", generalService.listarAlmacen(new ItemBean(usuarioBean.getIdDdi())));
		        	model.addAttribute("mes_anio", cierre.getNombreMes()+" "+cierre.getCodigoAnio());
		        	model.addAttribute("indicador", indicador);
		        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
		        	
		        	
		        	ruta = "listar-donaciones-cierre";
           		}
        	}
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return ruta;
    }
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarCierreStock", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarCierreStock(HttpServletRequest request, HttpServletResponse response) {
		List<CierreStockBean> lista = null;
		try {			
			CierreStockBean cierreStockBean = new CierreStockBean();
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(cierreStockBean, request.getParameterMap());
			cierreStockBean.setIdAlmacen(usuarioBean.getIdAlmacen());
			lista = donacionService.listarCierreStock(cierreStockBean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	/**
	 * @param codigoAnio 
	 * @param codigoMes 
	 * @param idAlmacen 
	 * @param model
	 * @return - Retorna a la vista JSP.
	 */
	@RequestMapping(value = "/mantenimientoCierreStock/{codigoAnio}/{codigoMes}/{idAlmacen}", method = RequestMethod.GET)
    public String mantenimientoCierreStock(@PathVariable("codigoAnio") String codigoAnio,
    									   @PathVariable("codigoMes") String codigoMes,
    									   @PathVariable("idAlmacen") Integer idAlmacen,
    									   Model model) {
        try {
        	CierreStockBean cierreStock = new CierreStockBean();
        	
        	CierreStockBean params = new CierreStockBean();
        	params.setCodigoAnio(codigoAnio);
        	params.setCodigoMes(codigoMes);
        	params.setIdAlmacen(idAlmacen);
        	cierreStock = donacionService.obtenerRegistroCierreStock(params);

        	model.addAttribute("cierreStock", getParserObject(cierreStock));
        	
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

        	CierreStockBean cierre = new CierreStockBean();
       		cierre = administracionService.mesTrabajoActivo(usuarioBean.getIdAlmacen(), Constantes.TIPO_ORIGEN_DONACIONES);
        	
        	model.addAttribute("lista_personal", generalService.listarPersonal(new ItemBean(usuarioBean.getIdDdi())));
        	model.addAttribute("mes_anio", cierre.getNombreMes()+" "+cierre.getCodigoAnio());
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "mantenimiento-cierre-donacion";
    }

	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/grabarCierreStock", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarCierreStock(HttpServletRequest request, HttpServletResponse response) {
		CierreStockBean cierreStock = null;
		try {			
			CierreStockBean cierreStockBean = new CierreStockBean();
			
			// Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(cierreStockBean, request.getParameterMap());

			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	cierreStockBean.setUsuarioRegistro(usuarioBean.getUsuario());
        	cierreStockBean.setCodigoAnio(usuarioBean.getCodigoAnio());
        	cierreStockBean.setCodigoMes(usuarioBean.getCodigoMes());
        	cierreStockBean.setIdDdi(usuarioBean.getIdDdi());
			cierreStock = donacionService.grabarCierreStock(cierreStockBean);
			cierreStock.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return cierreStock;
	}
	
}