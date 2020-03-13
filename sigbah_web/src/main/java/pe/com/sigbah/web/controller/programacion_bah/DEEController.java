package pe.com.sigbah.web.controller.programacion_bah;

import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;

import pe.com.sigbah.common.bean.DeeBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.ListaRespuestaRequerimientoBean;
import pe.com.sigbah.common.bean.UbigeoBean;
import pe.com.sigbah.common.bean.UbigeoDeeBean;
import pe.com.sigbah.common.bean.UbigeoIneiBean;
import pe.com.sigbah.common.bean.UsuarioBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.DateUtil;
import pe.com.sigbah.common.util.HtmlUtils;
import pe.com.sigbah.service.AdministracionService;
import pe.com.sigbah.service.GeneralService;
import pe.com.sigbah.service.ProgramacionService;
import pe.com.sigbah.web.controller.common.BaseController;
import pe.com.sigbah.web.report.programacion_bah.ReporteDee;
import pe.com.sigbah.web.report.programacion_bah.ReporteDistritoEmergencia;
import pe.com.sigbah.web.report.programacion_bah.ReporteEmergencia;

/**
 * @className: EmergenciaController.java
 * @description: 
 * @date: 13 jul. 2017
 * @author: whr.
 */

@Controller
@RequestMapping("/programacion-bath/decreto")
public class DEEController extends BaseController {
	
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
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	DeeBean decreemer = new DeeBean();
        	decreemer.setCodAnio(generalService.obtenerAnioActual());
        	model.addAttribute("lista_anio", generalService.listarAnios());
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	model.addAttribute("lista_estado", generalService.listarEstadoDee( new ItemBean()));
        	model.addAttribute("indicador", indicador);
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	model.addAttribute("decreemer", getParserObject(decreemer));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));

        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "decreto-estado";
    }

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listarDee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarDee(HttpServletRequest request, HttpServletResponse response) {
		List<DeeBean> lista = null;
		try {			
			DeeBean deeBean = new DeeBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(deeBean, request.getParameterMap());			
			lista = programacionService.listarDee(deeBean); 
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/exportarExcel/{codAnio}/{codMes}/{idEstado}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcel(@PathVariable("codAnio") String codAnio, 
								@PathVariable("codMes") String codMes, 
								@PathVariable("idEstado") Integer idEstado, 
								HttpServletResponse response) {
	    try {
	    	DeeBean deeBean = new DeeBean();
	    	deeBean.setCodAnio(verificaParametro(codAnio));
	    	deeBean.setCodMes(verificaParametro(codMes));
	    	deeBean.setIdEstado(idEstado);
	    	
			List<DeeBean> lista = programacionService.listarDee(deeBean);
	    	
			String file_name = "DecretosEstadoEmergencia";
			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_XLS);
			
			ReporteDee reporte = new ReporteDee();
		    HSSFWorkbook wb = reporte.generaReporteExcelDee(lista);
		    
			response.resetBuffer();
            response.setContentType(Constantes.MIME_APPLICATION_XLS);
            response.setHeader("Content-Disposition", "attachment; filename="+file_name);            
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "private");
			response.setHeader("Set-Cookie", "fileDownload=true; path=/");
			response.setDateHeader("Expires", 1);
	    	
		    // Captured backflow
	    	OutputStream out = response.getOutputStream();
	    	wb.write(out); // We write in that flow
	    	out.flush(); // We emptied the flow
	    	out.close(); // We close the flow
	    	
	    	return Constantes.COD_EXITO_GENERAL;   	
	    } catch (Exception e) {
	    	LOGGER.error(e.getMessage(), e);
	    	return Constantes.COD_ERROR_GENERAL;
	    } 
	}
	
	
	@RequestMapping(value = "/mantenimientoDee/{codigo}", method = RequestMethod.GET)
    public String mantenimientoDee(@PathVariable("codigo") Integer codigo, Model model) {
        try {
        	DeeBean dee = new DeeBean();
        	
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	ListaRespuestaRequerimientoBean respuestaEdicion = new ListaRespuestaRequerimientoBean();
        	if (!isNullInteger(codigo)) {// editar
        		
        		dee = programacionService.obtenerDee(codigo); 
        		
//        		model.addAttribute("lista_requerimiento", getParserObject(respuestaEdicion.getLstDetalle()));
        	  		
        	} else {//nuevo

        		Date fecha_hora = Calendar.getInstance().getTime();
        		dee.setFechaIni(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_FECHA, fecha_hora));
        		dee.setFechaFin(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_FECHA, fecha_hora));
        	}
        	model.addAttribute("dee", getParserObject(dee));
        	
        	model.addAttribute("lista_estado", generalService.listarEstadoDee( new ItemBean()));
        	model.addAttribute("lista_departamento", generalService.listarDepartamentos(new UbigeoBean()));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
            
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "mantenimiento-decreto-estado";
    }
	
	@RequestMapping(value = "/grabarDecreto", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarDecreto(HttpServletRequest request, HttpServletResponse response) {
		DeeBean pedido = null;
		try {			
			DeeBean deeBean = new DeeBean();

	        // Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(deeBean, request.getParameterMap());

			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	deeBean.setUsuarioRegistro(usuarioBean.getNombreUsuario());
        	
			if (!isNullInteger(deeBean.getIdDee())) {			
				deeBean.setControl("U");
				pedido = programacionService.insertarRegistroDee(deeBean);				
			} else {			
				deeBean.setControl("I");
				pedido = programacionService.insertarRegistroDee(deeBean);			
			}
			pedido.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return pedido;
	}
	
	@RequestMapping(value = "/listarUbigeo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarUbigeo(HttpServletRequest request, HttpServletResponse response) {
		List<UbigeoIneiBean> lista = null;
		try {			
			UbigeoIneiBean ubigeoBean = new UbigeoIneiBean();	
			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			ubigeoBean.setUsuarioRegistro(usuarioBean.getNombreUsuario());
        	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(ubigeoBean, request.getParameterMap());		
			lista = programacionService.listarUbigeoDee(ubigeoBean); 
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/pasarDistritosUbigeo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object pasarDistritosUbigeo(HttpServletRequest request, HttpServletResponse response) {
		UbigeoDeeBean ubigeo = null;
		try {			
			DeeBean deeBean = new DeeBean();

			BeanUtils.populate(deeBean, request.getParameterMap());			
			ubigeo = programacionService.pasarDistritosUbigeoDee(deeBean); 

			ubigeo.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return ubigeo;
    }
	
	
	@RequestMapping(value = "/listarDistritosEmergencia", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarDistritosEmergencia(HttpServletRequest request, HttpServletResponse response) {
		List<UbigeoDeeBean> lista = null;
		try {			
			UbigeoDeeBean documento = new UbigeoDeeBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(documento, request.getParameterMap());			
			lista = programacionService.listarDistritosEmergencia(documento);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	
	@RequestMapping(value = "/eliminarDistritoEmergencia", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarDistritoEmergencia(HttpServletRequest request, HttpServletResponse response) {
		UbigeoDeeBean ubigeodee = null;
		try {			
			String[] arrIdDeeDetalle = request.getParameter("arrIdDeeDetalle").split(Constantes.UNDERLINE);
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
			for (String codigo : arrIdDeeDetalle) {				
				UbigeoDeeBean ubigeoDeeBean = new UbigeoDeeBean(getInteger(codigo));

				ubigeoDeeBean.setUsuarioRegistro(usuarioBean.getUsuario());
				
				ubigeodee = programacionService.eliminarDistritoEmergencia(ubigeoDeeBean);				
			}

			ubigeodee.setMensajeRespuesta(getMensaje(messageSource, "msg.info.eliminadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return ubigeodee;
	}
	
	@RequestMapping(value = "/exportarExcelDistritosEmergencia/{idDeeDetalle}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcelDistritosEmergencia( @PathVariable("idDeeDetalle") Integer idDeeDetalle, 
													HttpServletResponse response) {
	    try {
	    	UbigeoDeeBean ubigeoDeeBean = new UbigeoDeeBean();
	    	
	    	ubigeoDeeBean.setIdDeeDetalle(idDeeDetalle);
			List<UbigeoDeeBean> lista = programacionService.listarDistritosEmergencia(ubigeoDeeBean);
	    	
			String file_name = "DistritosEnEmergencia";
			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_XLS);
			
			ReporteDistritoEmergencia reporte = new ReporteDistritoEmergencia();
		    HSSFWorkbook wb = reporte.generaReporteExcelDistritoEmergencia(lista);
			
			response.resetBuffer();
            response.setContentType(Constantes.MIME_APPLICATION_XLS);
            response.setHeader("Content-Disposition", "attachment; filename="+file_name);            
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "private");
			response.setHeader("Set-Cookie", "fileDownload=true; path=/");
			response.setDateHeader("Expires", 1);
	    	
		    // Captured backflow
	    	OutputStream out = response.getOutputStream();
	    	wb.write(out); // We write in that flow
	    	out.flush(); // We emptied the flow
	    	out.close(); // We close the flow
	    	
	    	return Constantes.COD_EXITO_GENERAL;   	
	    } catch (Exception e) {
	    	LOGGER.error(e.getMessage(), e);
	    	return Constantes.COD_ERROR_GENERAL;
	    } 
	}
	
	
	
}
