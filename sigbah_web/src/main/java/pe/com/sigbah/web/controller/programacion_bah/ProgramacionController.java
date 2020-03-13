package pe.com.sigbah.web.controller.programacion_bah;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;

import pe.com.sigbah.common.bean.ConsultaProgramacionBean;
import pe.com.sigbah.common.bean.CorreoBean;
import pe.com.sigbah.common.bean.DeeConsultaBean;
import pe.com.sigbah.common.bean.DocumentoProgramacionBean;
import pe.com.sigbah.common.bean.EstadoProgramacionBean;
import pe.com.sigbah.common.bean.EstadoUsuarioBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.ProductoAlimentoBean;
import pe.com.sigbah.common.bean.ProductoNoAlimentarioBean;
import pe.com.sigbah.common.bean.ProductoNoAlimentarioProgramacionBean;
import pe.com.sigbah.common.bean.ProgramacionAlimentoBean;
import pe.com.sigbah.common.bean.ProgramacionAlmacenBean;
import pe.com.sigbah.common.bean.ProgramacionBean;
import pe.com.sigbah.common.bean.ProgramacionNoAlimentarioBean;
import pe.com.sigbah.common.bean.RacionOperativaBean;
import pe.com.sigbah.common.bean.RequerimientoBean;
import pe.com.sigbah.common.bean.ResumenStockAlimentoBean;
import pe.com.sigbah.common.bean.ResumenStockNoAlimentarioBean;
import pe.com.sigbah.common.bean.UsuarioBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.DateUtil;
import pe.com.sigbah.common.util.HtmlUtils;
import pe.com.sigbah.service.AdministracionService;
import pe.com.sigbah.service.GeneralService;
import pe.com.sigbah.service.ProgramacionRequerimientoService;
import pe.com.sigbah.service.ProgramacionService;
import pe.com.sigbah.web.controller.common.BaseController;
import pe.com.sigbah.web.report.programacion_bah.ReporteProgramacion;

/**
 * @className: ProgramacionController.java
 * @description: 
 * @date: 29/07/2017
 * @author: Junior Huaman Flores.
 */

@Controller
@RequestMapping("/programacion-bah/programacion")
public class ProgramacionController extends BaseController {
	
private static final long serialVersionUID = 1L;
	
	@Autowired 
	private ProgramacionRequerimientoService programacionRequerimientoService;
	
	@Autowired 
	private ProgramacionService programacionService;
	
	@Autowired 
	private GeneralService generalService;
	
	@Autowired 
	private AdministracionService administracionService;
	
	/**
	 * @param indicador
	 * @param model
	 * @return - Retorna a la vista JSP.
	 */
	@RequestMapping(value = "/inicio/{indicador}", method = RequestMethod.GET)
    public String inicio(@PathVariable("indicador") String indicador, Model model) {
        try {
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	usuarioBean.setCodigoAnio(String.valueOf(DateUtil.getAnioActual()));
        	model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	
        	model.addAttribute("lista_fenomeno", generalService.listarFenomeno(new ItemBean()));
        	
        	model.addAttribute("lista_usuario_correos", administracionService.listarUsuariosCorreo());
        	
        	model.addAttribute("lista_estado", generalService.listarEstado(new ItemBean(null, Constantes.ONE_INT)));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        	model.addAttribute("indicador", indicador);
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));

        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar_programacion";
    }
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarProgramacion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProgramacion(HttpServletRequest request, HttpServletResponse response) {
		List<ProgramacionBean> lista = null;
		try {			
			ProgramacionBean programacionBean = new ProgramacionBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(programacionBean, request.getParameterMap());			
			lista = programacionRequerimientoService.listarProgramacion(programacionBean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	/**
	 * @param codigo 
	 * @param model
	 * @return - Retorna a la vista JSP.
	 */
	@RequestMapping(value = "/mantenimientoProgramacion/{codigo}", method = RequestMethod.GET)
    public String mantenimientoProgramacion(@PathVariable("codigo") Integer codigo, Model model) {
        try {
        	ProgramacionBean programacion = new ProgramacionBean();
        	
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	if (!isNullInteger(codigo)) {
        		
        		programacion = programacionRequerimientoService.obtenerRegistroProgramacion(codigo);
        		
        	} else {

        		ProgramacionBean parametros = new ProgramacionBean();
        		String anioActual = generalService.obtenerAnioActual();
        		parametros.setCodigoAnio(anioActual);
        		parametros.setCodigoDdi(usuarioBean.getCodigoDdi());
        		parametros.setIdDdi(usuarioBean.getIdDdi());        		
        		ProgramacionBean respuestaCorrelativo = programacionRequerimientoService.obtenerCorrelativoProgramacion(parametros);
        		programacion.setNroProgramacion(respuestaCorrelativo.getNroProgramacion());
            	programacion.setIdDdi(usuarioBean.getIdDdi());
        		programacion.setCodigoDdi(usuarioBean.getCodigoDdi());
        		programacion.setNombreDdi(usuarioBean.getNombreDdi());
        		programacion.setCodigoAnio(anioActual);
        	}
        	
        	model.addAttribute("programacion", getParserObject(programacion));
        	
        	RequerimientoBean requerimientoBean = new RequerimientoBean();
        	requerimientoBean.setCodAnio(usuarioBean.getCodigoAnio());
        	requerimientoBean.setIdDdi(usuarioBean.getIdDdi());
        	model.addAttribute("lista_requerimiento", programacionRequerimientoService.listarRequerimiento(requerimientoBean));
        	
        	
        	RacionOperativaBean racion = new RacionOperativaBean();
        	racion.setIdDdi(usuarioBean.getIdDdi());
        	model.addAttribute("lista_racion", programacionRequerimientoService.listarRacionOperativa(racion));
        	
        	model.addAttribute("lista_dee", generalService.listarDee(new ItemBean()));

        	model.addAttribute("lista_region", generalService.listarRegion(new ItemBean()));
        	
        	List<ItemBean> listaAlmacen = null;
        	if (usuarioBean.getCodigoDdi().equals(Constantes.CODIGO_DDI_INDECI_CENTRAL)) {
        		listaAlmacen = generalService.listarAlmacen(new ItemBean());
        	} else {
        		listaAlmacen = generalService.listarAlmacen(new ItemBean(usuarioBean.getIdDdi()));
        	}
        	model.addAttribute("listaAlmacen", getParserObject(listaAlmacen));
        	
        	model.addAttribute("lista_categoria", generalService.listarCategoria(new ItemBean(Constantes.TWO_INT)));
        	
        	model.addAttribute("lista_tipo_documento", generalService.listarTipoDocumento(new ItemBean()));

        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "mantenimiento_programacion";
    }
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/grabarProgramacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarProgramacion(HttpServletRequest request, HttpServletResponse response) {
		ProgramacionBean programacion = null;
		try {			
			ProgramacionBean programacionBean = new ProgramacionBean();
			
			// Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(programacionBean, request.getParameterMap());

			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	programacionBean.setUsuarioRegistro(usuarioBean.getUsuario());
			
			programacion = programacionRequerimientoService.grabarProgramacion(programacionBean);			
			programacion.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return programacion;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarProgramacionAlmacen", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProgramacionAlmacen(HttpServletRequest request, HttpServletResponse response) {
		List<ProgramacionAlmacenBean> lista = null;
		try {			
			ProgramacionAlmacenBean programacionAlmacenBean = new ProgramacionAlmacenBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(programacionAlmacenBean, request.getParameterMap());			
			lista = programacionRequerimientoService.listarProgramacionAlmacen(programacionAlmacenBean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/grabarProgramacionAlmacen", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarProgramacionAlmacen(HttpServletRequest request, HttpServletResponse response) {
		ProgramacionAlmacenBean programacionAlmacen = null;
		try {			
			ProgramacionAlmacenBean programacionAlmacenBean = new ProgramacionAlmacenBean();
			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(programacionAlmacenBean, request.getParameterMap());

			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	programacionAlmacenBean.setUsuarioRegistro(usuarioBean.getUsuario());
			
        	programacionAlmacen = programacionRequerimientoService.grabarProgramacionAlmacen(programacionAlmacenBean);			
        	programacionAlmacen.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return programacionAlmacen;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/eliminarProgramacionAlmacen", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarProgramacionAlmacen(HttpServletRequest request, HttpServletResponse response) {
		ProgramacionAlmacenBean programacionAlmacen = null;
		try {			
			String[] arrIdDetalleControlCalidad = request.getParameter("arrIdDetalleProgramacionAlmacen").split(Constantes.UNDERLINE);
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			
			for (String codigo : arrIdDetalleControlCalidad) {				
				ProgramacionAlmacenBean programacionAlmacenBean = new ProgramacionAlmacenBean(getInteger(codigo));

				programacionAlmacenBean.setUsuarioRegistro(usuarioBean.getUsuario());
				
				programacionAlmacen = programacionRequerimientoService.eliminarProgramacionAlmacen(programacionAlmacenBean);				
			}

			programacionAlmacen.setMensajeRespuesta(getMensaje(messageSource, "msg.info.eliminadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return programacionAlmacen;
	}
	
	/**
	 * @param idRacionOperativa 
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarProgramacionRacionOperativa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProgramacionRacionOperativa(@RequestParam(value="idRacionOperativa") Integer idRacionOperativa) {
		List<RacionOperativaBean> lista = null;
		try {		
			lista = programacionRequerimientoService.listarProgramacionRacionOperativa(idRacionOperativa);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/actualizarProgramacionRacionOperativa", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object actualizarProgramacionRacionOperativa(HttpServletRequest request, HttpServletResponse response) {
		RacionOperativaBean racionOperativa = null;
		try {			
			RacionOperativaBean racionOperativaBean = new RacionOperativaBean();
			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(racionOperativaBean, request.getParameterMap());

			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	racionOperativaBean.setUsuarioRegistro(usuarioBean.getUsuario());
			
        	racionOperativa = programacionRequerimientoService.actualizarProgramacionRacionOperativa(racionOperativaBean);			
        	racionOperativa.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return racionOperativa;
	}
	
	/**
	 * @param idProgramacion 
	 * @param arrIdProducto 
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarProgramacionAlimento", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProgramacionAlimento(@RequestParam(value="idProgramacion") Integer idProgramacion,
											 @RequestParam(value="arrIdProducto[]") List<Integer> arrIdProducto) {
		List<ProgramacionAlimentoBean> lista = null;
		try {		
			lista = programacionRequerimientoService.listarProgramacionAlimento(idProgramacion, arrIdProducto);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	/**
	 * @param idProgramacion 
	 * @param idRacionOperativa 
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarResumenStockAlimento", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarResumenStockAlimento(@RequestParam(value="idProgramacion") Integer idProgramacion,
									  @RequestParam(value="idRacionOperativa") Integer idRacionOperativa) {
		List<ResumenStockAlimentoBean> lista = null;
		try {		
			lista = programacionRequerimientoService.listarResumenStockAlimento(idProgramacion, idRacionOperativa);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	/**
	 * @param idProgramacionUbigeo 
	 * @param arrIdProducto 
	 * @param arrUnidad 
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/actualizarDetalleProgramacionAlimento", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object actualizarDetalleProgramacionAlimento(@RequestParam(value="idProgramacionUbigeo") Integer idProgramacionUbigeo,
														@RequestParam(value="arrIdProducto[]") List<Integer> arrIdProducto,
														@RequestParam(value="arrUnidad[]") List<BigDecimal> arrUnidad) {
		ProductoAlimentoBean productoAlimento = null;
		try {
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	if (!isEmpty(arrIdProducto)) {
        		for (int i = 0; i < arrIdProducto.size(); i++) {
		        	ProductoAlimentoBean productoAlimentoBean = new ProductoAlimentoBean();
		        	productoAlimentoBean.setIdProgramacionUbigeo(idProgramacionUbigeo);
		        	productoAlimentoBean.setIdProducto(arrIdProducto.get(i));
		        	productoAlimentoBean.setUnidad(arrUnidad.get(i));
		        	productoAlimentoBean.setUsuarioRegistro(usuarioBean.getUsuario());				
		        	productoAlimento = programacionRequerimientoService.actualizarDetalleProgramacionAlimento(productoAlimentoBean);
        		}
        	} else {
        		productoAlimento = new ProductoAlimentoBean();
        		productoAlimento.setCodigoRespuesta(Constantes.COD_EXITO_GENERAL);
        	}
	        	
        	productoAlimento.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return productoAlimento;
	}
	
	/**
	 * @param arrIdDetalleProgramacionUbigeo 
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/eliminarDetalleProgramacionAlimento", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarDetalleProgramacionAlimento(@RequestParam(value="arrIdDetalleProgramacionUbigeo[]") List<Integer> arrIdDetalleProgramacionUbigeo) {
		ProgramacionAlimentoBean programacionAlimento = null;
		try {			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			
			for (Integer idProgramacionUbigeo : arrIdDetalleProgramacionUbigeo) {				
				ProgramacionAlimentoBean programacionAlimentoBean = new ProgramacionAlimentoBean(idProgramacionUbigeo);

				programacionAlimentoBean.setUsuarioRegistro(usuarioBean.getUsuario());
				
				programacionAlimento = programacionRequerimientoService.eliminarDetalleProgramacionAlimento(programacionAlimentoBean);				
			}

			programacionAlimento.setMensajeRespuesta(getMensaje(messageSource, "msg.info.eliminadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return programacionAlimento;
	}
	
	/**
	 * @param idProgramacion 
	 *  @param idRacion 
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/eliminarProgramacionAlimento", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarProgramacionAlimento(@RequestParam(value="idProgramacion") Integer idProgramacion,@RequestParam(value="idRacion") Integer idRacion) {
		ProgramacionBean programacion = null;
		try {			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			
			ProgramacionBean programacionBean = new ProgramacionBean(idProgramacion);

			programacionBean.setUsuarioRegistro(usuarioBean.getUsuario());
				
			programacion = programacionRequerimientoService.eliminarProgramacionAlimento(programacionBean);
			
			ProgramacionBean programacionBean1 = new ProgramacionBean();
			programacionBean1.setIdProgramacion(idProgramacion);
			programacionBean1.setIdRacion(idRacion);
			programacionRequerimientoService.insertarUbigeoRacion(programacionBean1);

			programacion.setMensajeRespuesta(getMensaje(messageSource, "msg.info.eliminadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return programacion;
	}
	
	/**
	 * @param idProgramacion 
	 * @param arrIdProducto 
	 * @param arrNombreProducto 
	 * @param arrUnidadProducto 
	 * @param response 
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/exportarExcelAlimento", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcelAlimento(@RequestParam(value="idProgramacion") Integer idProgramacion,
										@RequestParam(value="arrIdProducto[]") List<Integer> arrIdProducto,
			 							@RequestParam(value="arrNombreProducto[]") List<String> arrNombreProducto,
			 							@RequestParam(value="arrUnidadProducto[]") List<BigDecimal> arrUnidadProducto,
										HttpServletResponse response) {
		try {
	    	
			List<ProgramacionAlimentoBean> listaProgramacionAlimento = programacionRequerimientoService.listarProgramacionAlimento(idProgramacion, arrIdProducto);
			
			String file_name = "ProgramacionAlimento";
			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_XLS);
			
			ReporteProgramacion reporte = new ReporteProgramacion();
		    HSSFWorkbook wb = reporte.generaReporteExcelAlimento(listaProgramacionAlimento, arrIdProducto, arrNombreProducto, arrUnidadProducto);
			
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
	
	/**
	 * @param idProgramacion 
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarProductoNoAlimentarioProgramacion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProductoNoAlimentarioProgramacion(@RequestParam(value="idProgramacion") Integer idProgramacion) {
		List<ProductoNoAlimentarioProgramacionBean> lista = null;
		try {						
			lista = programacionRequerimientoService.listarProductoNoAlimentarioProgramacion(idProgramacion);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/grabarProductoNoAlimentarioProgramacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarProductoNoAlimentarioProgramacion(HttpServletRequest request, HttpServletResponse response) {
		ProductoNoAlimentarioProgramacionBean producto = null;
		try {			
			ProductoNoAlimentarioProgramacionBean productoNoAlimentarioProgramacionBean = new ProductoNoAlimentarioProgramacionBean();

			// Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);			
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Convierte los vacios en nulos en los decimales
			BigDecimalConverter con_decimal = new BigDecimalConverter(null);
			beanUtilsBean.getConvertUtils().register(con_decimal, BigDecimal.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(productoNoAlimentarioProgramacionBean, request.getParameterMap());
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	productoNoAlimentarioProgramacionBean.setUsuarioRegistro(usuarioBean.getUsuario());
			
			producto = programacionRequerimientoService.grabarProductoNoAlimentarioProgramacion(productoNoAlimentarioProgramacionBean);
			
			producto.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return producto;
	}
	
	/**
	 * @param arrIdDetalleProductoNoAlimentario
	 * @return objeto en formato json
	 */
	
	@RequestMapping(value = "/eliminarProductoNoAlimentarioProgramacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
//	public Object eliminarProductoNoAlimentarioProgramacion(@RequestParam(value="arrIdDetalleProductoNoAlimentario[]") List<Integer> arrIdDetalleProductoNoAlimentario) {
	public Object eliminarProductoNoAlimentarioProgramacion(HttpServletRequest request, HttpServletResponse response) {
		ProductoNoAlimentarioProgramacionBean producto = null;
		try {
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	String[] arrIdDetalleProductoNoAlimentario = request.getParameter("arrIdDetalleProductoNoAlimentario").split(Constantes.UNDERLINE);

			for (String codigo : arrIdDetalleProductoNoAlimentario) {				
				ProductoNoAlimentarioProgramacionBean productoNoAlimentarioProgramacionBean = new ProductoNoAlimentarioProgramacionBean(Integer.parseInt(codigo));

				productoNoAlimentarioProgramacionBean.setUsuarioRegistro(usuarioBean.getUsuario());
				
				producto = programacionRequerimientoService.eliminarProductoNoAlimentarioProgramacion(productoNoAlimentarioProgramacionBean);				
			}

			producto.setMensajeRespuesta(getMensaje(messageSource, "msg.info.eliminadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return producto;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/actualizarProgramacionNoAlimentario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object actualizarProgramacionNoAlimentario(HttpServletRequest request, HttpServletResponse response) {
		ProductoNoAlimentarioProgramacionBean productoNoAlimentarioProgramacion = null;
		try {			
			ProductoNoAlimentarioProgramacionBean productoNoAlimentarioProgramacionBean = new ProductoNoAlimentarioProgramacionBean();
			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(productoNoAlimentarioProgramacionBean, request.getParameterMap());

			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	productoNoAlimentarioProgramacionBean.setUsuarioRegistro(usuarioBean.getUsuario());
			
        	productoNoAlimentarioProgramacion = programacionRequerimientoService.actualizarProgramacionNoAlimentario(productoNoAlimentarioProgramacionBean);			
        	productoNoAlimentarioProgramacion.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return productoNoAlimentarioProgramacion;
	}
	
	/**
	 * @param idProgramacion 
	 * @param arrIdProducto 
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarProgramacionNoAlimentario", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProgramacionNoAlimentario(@RequestParam(value="idProgramacion") Integer idProgramacion,
											   	  @RequestParam(value="arrIdProducto[]") List<Integer> arrIdProducto) {
		List<ProgramacionNoAlimentarioBean> lista = null;
		try {		
			lista = programacionRequerimientoService.listarProgramacionNoAlimentario(idProgramacion, arrIdProducto);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	/**
	 * @param idProgramacion 
	 * @param idRacionOperativa 
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarResumenStockNoAlimentario", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarResumenStockNoAlimentario(@RequestParam(value="idProgramacion") Integer idProgramacion,
									  		   	  @RequestParam(value="idRacionOperativa") Integer idRacionOperativa) {
		List<ResumenStockNoAlimentarioBean> lista = null;
		try {		
			lista = programacionRequerimientoService.listarResumenStockNoAlimentario(idProgramacion, idRacionOperativa);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	/**
	 * @param idProgramacionUbigeo 
	 * @param arrIdProducto 
	 * @param arrUnidad 
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/actualizarDetalleProgramacionNoAlimentario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object actualizarDetalleProgramacionNoAlimentario(@RequestParam(value="idProgramacionUbigeo") Integer idProgramacionUbigeo,
														  	 @RequestParam(value="arrIdProducto[]") List<Integer> arrIdProducto,
														  	 @RequestParam(value="arrUnidad[]") List<BigDecimal> arrUnidad) {
		ProductoNoAlimentarioBean productoNoAlimentario = null;
		try {
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	if (!isEmpty(arrIdProducto)) {
        		for (int i = 0; i < arrIdProducto.size(); i++) {
		        	ProductoNoAlimentarioBean productoNoAlimentarioBean = new ProductoNoAlimentarioBean();
		        	productoNoAlimentarioBean.setIdProgramacionUbigeo(idProgramacionUbigeo);
		        	productoNoAlimentarioBean.setIdProducto(arrIdProducto.get(i));
		        	productoNoAlimentarioBean.setUnidad(arrUnidad.get(i));
		        	productoNoAlimentarioBean.setUsuarioRegistro(usuarioBean.getUsuario());				
		        	productoNoAlimentario = programacionRequerimientoService.actualizarDetalleProgramacionNoAlimentario(productoNoAlimentarioBean);
        		}
        	} else {
        		productoNoAlimentario = new ProductoNoAlimentarioBean();
        		productoNoAlimentario.setCodigoRespuesta(Constantes.COD_EXITO_GENERAL);
        	}
	        	
        	productoNoAlimentario.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return productoNoAlimentario;
	}
	
	/**
	 * @param arrIdDetalleProgramacionUbigeo 
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/eliminarDetalleProgramacionNoAlimentario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarDetalleProgramacionNoAlimentario(@RequestParam(value="arrIdDetalleProgramacionUbigeo[]") List<Integer> arrIdDetalleProgramacionUbigeo) {
		ProgramacionNoAlimentarioBean programacionNoAlimentario = null;
		try {			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			
			for (Integer idProgramacionUbigeo : arrIdDetalleProgramacionUbigeo) {				
				ProgramacionNoAlimentarioBean programacionNoAlimentarioBean = new ProgramacionNoAlimentarioBean(idProgramacionUbigeo);

				programacionNoAlimentarioBean.setUsuarioRegistro(usuarioBean.getUsuario());
				
				programacionNoAlimentario = programacionRequerimientoService.eliminarDetalleProgramacionNoAlimentario(programacionNoAlimentarioBean);				
			}

			programacionNoAlimentario.setMensajeRespuesta(getMensaje(messageSource, "msg.info.eliminadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return programacionNoAlimentario;
	}
	
	/**
	 * @param idProgramacion 
	 * @param arrIdProducto 
	 * @param arrNombreProducto 
	 * @param arrUnidadProducto 
	 * @param response 
	 * @return Objeto
	 */
	@RequestMapping(value = "/exportarExcelNoAlimentario", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcelNoAlimentario(@RequestParam(value="idProgramacion") Integer idProgramacion,
											 @RequestParam(value="arrIdProducto[]") List<Integer> arrIdProducto,
											 @RequestParam(value="arrNombreProducto[]") List<String> arrNombreProducto,
											 @RequestParam(value="arrUnidadProducto[]") List<BigDecimal> arrUnidadProducto,
											 HttpServletResponse response) {
		try {
	    	
			List<ProgramacionNoAlimentarioBean> listaProgramacionNoAlimentario = programacionRequerimientoService.listarProgramacionNoAlimentario(idProgramacion, arrIdProducto);
			
			String file_name = "ProgramacionNoAlimentario";
			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_XLS);
			
			ReporteProgramacion reporte = new ReporteProgramacion();
		    HSSFWorkbook wb = reporte.generaReporteExcelNoAlimentario(listaProgramacionNoAlimentario, arrIdProducto, arrNombreProducto, arrUnidadProducto);
			
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
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarDocumentoProgramacion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarDocumentoProgramacion(HttpServletRequest request, HttpServletResponse response) {
		List<DocumentoProgramacionBean> lista = null;
		try {			
			DocumentoProgramacionBean documento = new DocumentoProgramacionBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(documento, request.getParameterMap());			
			lista = programacionRequerimientoService.listarDocumentoProgramacion(documento);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/grabarDocumentoProgramacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarDocumentoProgramacion(HttpServletRequest request, HttpServletResponse response) {
		DocumentoProgramacionBean documento = null;
		try {			
			DocumentoProgramacionBean documentoProgramacionBean = new DocumentoProgramacionBean();
			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(documentoProgramacionBean, request.getParameterMap());
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	documentoProgramacionBean.setUsuarioRegistro(usuarioBean.getUsuario());
			
        	documento = programacionRequerimientoService.grabarDocumentoProgramacion(documentoProgramacionBean);
			
        	documento.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return documento;
	}
	
	/**
	 * @param arrIdDocumentoProgramacion
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/eliminarDocumentoProgramacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarDocumentoProgramacion(@RequestParam(value="arrIdDocumentoProgramacion[]") List<Integer> arrIdDocumentoProgramacion) {
		DocumentoProgramacionBean documento = null;
		try {			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
			for (Integer codigo : arrIdDocumentoProgramacion) {				
				DocumentoProgramacionBean documentoProgramacionBean = new DocumentoProgramacionBean(codigo);

	        	documentoProgramacionBean.setUsuarioRegistro(usuarioBean.getUsuario());
				
	        	documento = programacionRequerimientoService.eliminarDocumentoProgramacion(documentoProgramacionBean);				
			}

			documento.setMensajeRespuesta(getMensaje(messageSource, "msg.info.eliminadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return documento;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarEstadoProgramacion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarEstadoProgramacion(HttpServletRequest request, HttpServletResponse response) {
		List<EstadoProgramacionBean> lista = null;
		try {			
			EstadoProgramacionBean estado = new EstadoProgramacionBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(estado, request.getParameterMap());			
			lista = programacionRequerimientoService.listarEstadoProgramacion(estado);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}	
	
	/**
	 * @param idProgramacion 
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/obtenerEstadosProgramacion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object obtenerEstadosProgramacion(@RequestParam(value="idProgramacion") Integer idProgramacion) {
		List<EstadoUsuarioBean> lista = null;
		try {			
			// Retorno los datos de session
           	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
           	EstadoUsuarioBean estadoUsuarioBean = new EstadoUsuarioBean();
           	estadoUsuarioBean.setIdUsuario(usuarioBean.getIdUsuario());
           	estadoUsuarioBean.setIdProgramacion(idProgramacion);
			lista = programacionRequerimientoService.listarEstadoUsuario(estadoUsuarioBean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/grabarEstadoProgramacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarEstadoProgramacion(HttpServletRequest request, HttpServletResponse response) {
		EstadoProgramacionBean producto = null;
		try {
			EstadoProgramacionBean estadoProgramacionBean = new EstadoProgramacionBean();
			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(estadoProgramacionBean, request.getParameterMap());	
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

        	estadoProgramacionBean.setUsuarioRegistro(usuarioBean.getUsuario());
        	
//        	Integer idEstado = estadoProgramacionBean.getIdEstado();
//        	if (idEstado.equals(Constantes.TWO_INT) ||  // Aprobado 
//        			idEstado.equals(Constantes.FOUR_INT)) { // Autorizado      	
//	        	// Envio de correo
//	        	CorreoBean correoBean = new CorreoBean();
//				correoBean.setIdProgramacion(estadoProgramacionBean.getIdProgramacion());
//				correoBean.setIdEstado(idEstado);
//				List<CorreoBean> listaCorreoDestino = programacionRequerimientoService.listarCorreoDestino(correoBean);
//				if (!isEmpty(listaCorreoDestino)) {
//					enviarCorreoDestinatario(usuarioBean.getEmail(), usuarioBean.getUsuario(), listaCorreoDestino);
//				}
//        	}
				
			producto = programacionRequerimientoService.grabarEstadoProgramacion(estadoProgramacionBean);			
			producto.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return producto;
	}
	
	private void enviarCorreoDestinatario(String email, String usuario, List<CorreoBean> listaCorreoDestino) {
		LOGGER.info("[enviarCorreoDestinatario] Inicio ");		
		try {
			final String username = getMensaje(messageSource, "params.mail.user");
			final String password = getMensaje(messageSource, "params.mail.password");
			
			Properties props = new Properties();
			props.put("mail.smtp.auth", getMensaje(messageSource, "params.mail.smtp.auth"));
			props.put("mail.smtp.starttls.enable", getMensaje(messageSource, "params.mail.smtp.starttls.enable"));
			props.put("mail.smtp.host", getMensaje(messageSource, "params.mail.smtp.host"));
			props.put("mail.smtp.port", getMensaje(messageSource, "params.mail.smtp.port"));

			Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
			  	});
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email, usuario));
			message.setSubject(listaCorreoDestino.get(0).getAsunto());
			message.setText(listaCorreoDestino.get(0).getMensaje());
			
			InternetAddress[] recipientAddress = new InternetAddress[listaCorreoDestino.size()];
			int index = 0;
			for (CorreoBean correo : listaCorreoDestino) {
			    recipientAddress[index] = new InternetAddress(correo.getCorreo());
			    index++;
			}
			message.setRecipients(Message.RecipientType.TO, recipientAddress);

			Transport.send(message);

		} catch (MessagingException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		LOGGER.info("[enviarCorreoDestinatario] Fin ");
	}
	
	/**
	 * @param codigoAnio 
	 * @param codigoMes 
	 * @param idDdi
	 * @param idFenomeno 
	 * @param idEstado 
	 * @param response
	 * @return Objeto.
	 */
	@RequestMapping(value = "/exportarExcel/{codigoAnio}/{codigoMes}/{idDdi}/{idFenomeno}/{idEstado}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcel(@PathVariable("codigoAnio") String codigoAnio, 
								@PathVariable("codigoMes") String codigoMes, 
								@PathVariable("idDdi") Integer idDdi,
								@PathVariable("idFenomeno") Integer idFenomeno,
								@PathVariable("idEstado") Integer idEstado,
								HttpServletResponse response) {
	    try {
	    	ProgramacionBean programacionBean = new ProgramacionBean();
			programacionBean.setCodigoAnio(verificaParametro(codigoAnio));
			programacionBean.setCodigoMes(verificaParametro(codigoMes));
			programacionBean.setIdDdi(idDdi);
			programacionBean.setIdFenomeno(idFenomeno);
			programacionBean.setIdEstado(idEstado);
			List<ProgramacionBean> lista = programacionRequerimientoService.listarProgramacion(programacionBean);
	    	
			String file_name = "ListaProgramacion";
			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_XLS);
			
			ReporteProgramacion reporte = new ReporteProgramacion();
		    HSSFWorkbook wb = reporte.generaReporteExcelProgramacion(lista);
			
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
	
	/**
	 * @param idProgramacion 
	 * @param request 
	 * @param response
	 * @return Objeto.
	 */
	@RequestMapping(value = "/exportarPdf/{idProgramacion}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarPdf(@PathVariable("idProgramacion") Integer idProgramacion, HttpServletRequest request, HttpServletResponse response) {
	    try {
	    	ProgramacionBean programacion = programacionRequerimientoService.obtenerRegistroCabeceraProgramacion(idProgramacion);
	    	Integer idRacionOperativa = programacion.getIdRacion();
	    	List<RacionOperativaBean> listaRacion = null;
	    	List<ProductoNoAlimentarioProgramacionBean> listaProductoNoAlimentario = null;
	    	List<ProgramacionAlimentoBean> listaAlimento = null;
	    	List<ProgramacionAlimentoBean> listaTotalProductoAlimento = null;
	    	List<ProgramacionNoAlimentarioBean> listaNoAlimentario = null;
	    	List<Integer> arrIdProductoRacion = null;
	    	List<Integer> arrIdProductoNoAlimentario = null;
	    	if (programacion.getTipoAtencion().equals(Constantes.ONE_INT) || 
					programacion.getTipoAtencion().equals(Constantes.THREE_INT)) { // Alimentos ó Ambos
	    		listaRacion = programacionRequerimientoService.listarProgramacionRacionOperativa(idRacionOperativa);
	    		if (!isEmpty(listaRacion)) {
	    			arrIdProductoRacion = new ArrayList<Integer>();
	    			for (RacionOperativaBean racion : listaRacion) {
	    				arrIdProductoRacion.add(racion.getIdProducto());
	    			}	    			
	    			listaAlimento = programacionRequerimientoService.listarProgramacionAlimento(idProgramacion, arrIdProductoRacion);
	    			listaTotalProductoAlimento = programacionRequerimientoService.listarTotalProductoAlimento(idProgramacion, idRacionOperativa, arrIdProductoRacion);
	    		} else {
	    			listaRacion = new ArrayList<RacionOperativaBean>();
	    		}
	    	}
	    		
	    	if (programacion.getTipoAtencion().equals(Constantes.TWO_INT) || 
					programacion.getTipoAtencion().equals(Constantes.THREE_INT)) { // No Alimentarios ó Ambos
	    		listaProductoNoAlimentario = programacionRequerimientoService.listarProductoNoAlimentarioProgramacion(idProgramacion);
	    		if (!isEmpty(listaProductoNoAlimentario)) {
	    			arrIdProductoNoAlimentario = new ArrayList<Integer>();
	    			for (ProductoNoAlimentarioProgramacionBean producto : listaProductoNoAlimentario) {
	    				arrIdProductoNoAlimentario.add(producto.getIdProducto());
	    			}	    			
	    			listaNoAlimentario = programacionRequerimientoService.listarProgramacionNoAlimentario(idProgramacion, arrIdProductoNoAlimentario);
	    		} else {
	    			listaProductoNoAlimentario = new ArrayList<ProductoNoAlimentarioProgramacionBean>();
	    		}
	    	}

	    	StringBuilder file_path = new StringBuilder();
	    	file_path.append(getPath(request));
	    	file_path.append(File.separator);
	    	file_path.append(Constantes.UPLOAD_PATH_FILE_TEMP);
	    	file_path.append(File.separator);
	    	file_path.append(Calendar.getInstance().getTime().getTime());
	    	file_path.append(Constantes.EXTENSION_FORMATO_PDF);
	    	
	    	String file_name = "Programacion";
			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_PDF);
			
			ReporteProgramacion reporte = new ReporteProgramacion();
			reporte.generaPDFReporteProgramacion(file_path.toString(), 
												 programacion, 
												 listaRacion, 
												 listaAlimento, 
												 listaTotalProductoAlimento, 
												 listaProductoNoAlimentario, 
												 listaNoAlimentario);
			
			response.resetBuffer();
            response.setContentType(Constantes.MIME_APPLICATION_PDF);
            response.setHeader("Content-Disposition", "attachment; filename="+file_name);            
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "private");
			response.setHeader("Set-Cookie", "fileDownload=true; path=/");
			response.setDateHeader("Expires", 1);
	    	
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos = convertPDFToByteArrayOutputStream(file_path.toString());
			
			// Captured backflow
	        OutputStream out = response.getOutputStream();
	        baos.writeTo(out); // We write in that flow
	        out.flush(); // We emptied the flow
	    	out.close(); // We close the flow
	    	
	    	File file_temp = new File(file_path.toString());
    		if (file_temp.delete()) {
    			LOGGER.info("[exportarPdf] "+file_temp.getName()+" se borra el archivo temporal.");
    		} else {
    			LOGGER.info("[exportarPdf] "+file_temp.getName()+" no se logró borrar el archivo temporal.");
    		}

	    	return Constantes.COD_EXITO_GENERAL;
	    } catch (Exception e) {
	    	LOGGER.error(e.getMessage(), e);
	    	return Constantes.COD_ERROR_GENERAL;
	    } 
	}
	
	@RequestMapping(value = "/enviarCorreoProg", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object enviarCorreoProg(HttpServletRequest request, HttpServletResponse response) {
		List<CorreoBean> lista = null;
		try {			
			CorreoBean consultaBean = new CorreoBean();	
			ConsultaProgramacionBean guardar = new ConsultaProgramacionBean();
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

			// Copia los parametros del cliente al objeto
			BeanUtils.populate(consultaBean, request.getParameterMap());
			BeanUtils.populate(guardar, request.getParameterMap());
			guardar.setUsuarioRegistro(usuarioBean.getUsuario());
			lista = programacionRequerimientoService.listarCorreoProg(consultaBean);  
			if (guardar.getIdEstado().equals(Constantes.TWO_INT) ||  // Aprobado 
					guardar.getIdEstado().equals(Constantes.FOUR_INT)) { // Autorizado      	
	        	// Envio de correo
				lista = programacionRequerimientoService.listarCorreoProg(consultaBean);
				System.out.println(guardar.getUsuariosCorreo());
				if (!isEmpty(lista)) {
					List<String> correosEnviar=new ArrayList<String>();
					List<Integer> idEnviar=new ArrayList<Integer>();
					for (String retval: guardar.getUsuariosCorreo().split(",")) {
				         String[] usuario=retval.split("_");
				         idEnviar.add(Integer.parseInt(usuario[0]));
				         correosEnviar.add(usuario[1]);
				         guardar.setIdUsuarioUsu(Integer.parseInt(usuario[0]));
				         guardar.setEmailUsu(usuario[1]);
				         guardar.setUsuarioUsu(usuario[2]);
				         guardar.setNombreUsu(usuario[3]);
				         programacionService.grabarCorreosEnviados(guardar);
					 }
					enviarCorreoDestinatarioProg(correosEnviar,usuarioBean.getEmail(), usuarioBean.getUsuario(), lista);
				}
        	}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	private void enviarCorreoDestinatarioProg(List<String> correos, String email, String usuario, List<CorreoBean> listaCorreoDestino) {
		LOGGER.info("[enviarCorreoDestinatarioProg] Inicio ");		
		try {
			final String username = getMensaje(messageSource, "params.mail.user");
			final String password = getMensaje(messageSource, "params.mail.password");
			
			Properties props = new Properties();
			props.put("mail.smtp.auth", getMensaje(messageSource, "params.mail.smtp.auth"));
			props.put("mail.smtp.starttls.enable", getMensaje(messageSource, "params.mail.smtp.starttls.enable"));
			props.put("mail.smtp.host", getMensaje(messageSource, "params.mail.smtp.host"));
			props.put("mail.smtp.port", getMensaje(messageSource, "params.mail.smtp.port"));

			Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
			  	});
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email, usuario));
			message.setSubject(listaCorreoDestino.get(0).getAsunto());
			message.setText(listaCorreoDestino.get(0).getMensaje());
			
			InternetAddress[] recipientAddress = new InternetAddress[correos.size()];
			int index = 0;
			for (String correo1 : correos) {
			    recipientAddress[index] = new InternetAddress(correo1);
			    index++;
			}
			message.setRecipients(Message.RecipientType.TO, recipientAddress);

			Transport.send(message);

		} catch (MessagingException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		LOGGER.info("[enviarCorreoDestinatarioProg] Fin ");
	}
}
