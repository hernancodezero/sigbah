package pe.com.sigbah.web.controller.programacion_bah;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pe.com.sigbah.common.bean.EmergenciaBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.ListaRespuestaRequerimientoBean;
import pe.com.sigbah.common.bean.ProductoDonacionIngresoBean;
import pe.com.sigbah.common.bean.RequerimientoBean;
import pe.com.sigbah.common.bean.UbigeoBean;
import pe.com.sigbah.common.bean.UbigeoIneiBean;
import pe.com.sigbah.common.bean.UsuarioBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.DateUtil;
import pe.com.sigbah.common.util.ExportarArchivo;
import pe.com.sigbah.common.util.HtmlUtils;
import pe.com.sigbah.service.AdministracionService;
import pe.com.sigbah.service.GeneralService;
import pe.com.sigbah.service.ProgramacionRequerimientoService;
import pe.com.sigbah.service.ProgramacionService;
import pe.com.sigbah.web.controller.common.BaseController;
import pe.com.sigbah.web.report.programacion_bah.ReporteEmergencia;
import pe.com.sigbah.web.report.programacion_bah.ReporteRequerimiento;
import pe.com.sigbah.web.report.programacion_bah.ReporteRequerimientoAfectado;

/**
 * @className: EmergenciaController.java
 * @description: 
 * @date: 13 jul. 2017
 * @author: whr.
 */

@Controller
@RequestMapping("/programacion-bath/requerimiento")
public class RequerimientoEdanController extends BaseController {
	
private static final long serialVersionUID = 1L;
	
	@Autowired 
	private ProgramacionService programacionService;
	
	@Autowired 
	private ProgramacionRequerimientoService programacionRequerimientoService;
	
	@Autowired 
	private GeneralService generalService;
	
	@Autowired 
	private AdministracionService administracionService;
	
	@RequestMapping(value = "/inicio/{indicador}", method = RequestMethod.GET)
    public String inicio(@PathVariable("indicador") String indicador, Model model) {
        try {
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	RequerimientoBean requerimiento = new RequerimientoBean();
        	requerimiento.setCodAnio(generalService.obtenerAnioActual());
        	model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	
        	model.addAttribute("lista_ddi", generalService.listarDdi(new ItemBean()));
        	
        	model.addAttribute("lista_fenomeno", generalService.listarFenomeno(new ItemBean()));
      	
        	model.addAttribute("indicador", indicador);
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("requerimiento", getParserObject(requerimiento));
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "requerimiento-edan";
    }

	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listarRequerimientos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarRequerimientos(HttpServletRequest request, HttpServletResponse response) {
		List<RequerimientoBean> lista = null;
		try {			
			RequerimientoBean requerimientoBean = new RequerimientoBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(requerimientoBean, request.getParameterMap());	
			
			 
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			requerimientoBean.setFkIdeDdi(Integer.parseInt(usuarioBean.getCodigoDdi()));
			
			lista = programacionService.listarRequerimiento(requerimientoBean); 
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/exportarExcel/{codAnio}/{codMes}/{codFenomeno}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcel(@PathVariable("codAnio") String codAnio, 
								@PathVariable("codMes") String codMes, 
								@PathVariable("codFenomeno") Integer codFenomeno, 
								HttpServletResponse response) {
	    try {
	    	RequerimientoBean requerimientoBean = new RequerimientoBean();
	    	requerimientoBean.setCodAnio(verificaParametro(codAnio));
	    	requerimientoBean.setCodMes(verificaParametro(codMes));
	    	requerimientoBean.setIdFenomeno(codFenomeno);
	    	
	    	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
	    	requerimientoBean.setFkIdeDdi(Integer.parseInt(usuarioBean.getCodigoDdi()));
	    	
			List<RequerimientoBean> lista = programacionService.listarRequerimiento(requerimientoBean);
	    	
			String file_name = "Reporte_Requerimiento";
			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_XLS);
			
			ReporteRequerimiento reporte = new ReporteRequerimiento();
		    HSSFWorkbook wb = reporte.generaReporteExcelRequerimiento(lista);
			
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
	 * @param codigo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mantenimientoRequerimiento/{codigo}", method = RequestMethod.GET)
    public String mantenimientoRequerimiento(@PathVariable("codigo") Integer codigo, Model model) {
        try {
        	RequerimientoBean requerimiento = new RequerimientoBean();
        	
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        
        	ListaRespuestaRequerimientoBean respuestaEdicion = new ListaRespuestaRequerimientoBean();
        	if (!isNullInteger(codigo)) {//para editar
        		
        		respuestaEdicion = programacionService.obtenerRequerimiento(usuarioBean.getCodigoAnio(),usuarioBean.getCodigoDdi(),codigo); 
        		
        		model.addAttribute("requerimiento", getParserObject(respuestaEdicion.getLstCabecera().get(0)));
        		model.addAttribute("lista_requerimiento", getParserObject(respuestaEdicion.getLstDetalle()));
        	  		
        	} else {//para buevo

        		StringBuilder correlativo = new StringBuilder();
        		correlativo.append(usuarioBean.getCodigoDdi());
        		correlativo.append(Constantes.SEPARADOR);
        		correlativo.append(usuarioBean.getIdDdi());
        		correlativo.append(Constantes.SEPARADOR);
        		
        		RequerimientoBean parametros = new RequerimientoBean();
        		String anioActual = generalService.obtenerAnioActual();
        		parametros.setCodAnio(anioActual);
        		parametros.setIdDdi(usuarioBean.getIdDdi());   
        		parametros.setCodDdi(usuarioBean.getCodigoDdi()); 
        		RequerimientoBean respuestaCorrelativo = programacionService.obtenerCorrelativoRequerimiento(parametros);
      
//        		correlativo.append(respuestaCorrelativo.getNumRequerimiento());
        		
        		requerimiento.setCodAnio(anioActual);
        		requerimiento.setNumRequerimiento(respuestaCorrelativo.getNumRequerimiento());        		
        		Date fecha_hora = Calendar.getInstance().getTime();
    			requerimiento.setFechaRequerimiento(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_FECHA, fecha_hora));
    			requerimiento.setCodMes(DateUtil.getMesActual()+""); 
    			requerimiento.setCodDdi(usuarioBean.getCodigoDdi());
    			requerimiento.setFkIdeDdi(usuarioBean.getIdDdi()); 
    			requerimiento.setIdDdi(usuarioBean.getIdDdi()); 
    			requerimiento.setCodRequerimiento(respuestaCorrelativo.getCodRequerimiento());
    			model.addAttribute("requerimiento", getParserObject(requerimiento));
        	}
	
        	
        	model.addAttribute("lista_region", generalService.listarRegion(new ItemBean()));
        	model.addAttribute("lista_fenomeno", generalService.listarFenomeno(new ItemBean()));
       	
        	
     
        	model.addAttribute("lista_anio", generalService.listarAnios());
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	model.addAttribute("lista_departamento", generalService.listarDepartamentos(new UbigeoBean()));
        	model.addAttribute("lista_estado", generalService.listarEstadoRequerimiento( new ItemBean()));
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "mantenimiento_requerimiento";
    }
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/grabarRequerimiento", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarRequerimiento(HttpServletRequest request, HttpServletResponse response) {
		RequerimientoBean requerimiento = null;
		try {			
			RequerimientoBean requerimientoBean = new RequerimientoBean();

	        // Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(requerimientoBean, request.getParameterMap());

			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	requerimientoBean.setUsuarioRegistro(usuarioBean.getUsuario());
        	//whr
        	requerimientoBean.setObservacion(BaseController.quitarSaltos(requerimientoBean.getObservacion()));
			if (!isNullInteger(requerimientoBean.getIdRequerimiento())) {	
				requerimientoBean.setCodMes(DateUtil.getMesActual()+""); 
				requerimientoBean.setControl("U");
				requerimiento = programacionService.insertarRegistroRequerimiento(requerimientoBean);				
			} else {			
				requerimientoBean.setControl("I");
				requerimiento = programacionService.insertarRegistroRequerimiento(requerimientoBean);			
			}
			requerimiento.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return requerimiento;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listarProvincia", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProvincia(HttpServletRequest request, HttpServletResponse response) {
		List<UbigeoBean> lista = null;
		try {			
			UbigeoBean ubigeoBean = new UbigeoBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(ubigeoBean, request.getParameterMap());
			lista = generalService.listarProvincia(ubigeoBean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listarEmergenciasActivas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarEmergenciasActivas(HttpServletRequest request, HttpServletResponse response) {
		List<EmergenciaBean> lista = null;
		try {			
			RequerimientoBean requerimientoBean = new RequerimientoBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(requerimientoBean, request.getParameterMap());			
			lista = programacionService.listarEmergenciasActivas(requerimientoBean); 
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	/**
	 * @param codAnio
	 * @param codMes
	 * @param codDpto
	 * @param codProvincia
	 * @param idFenomeno
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/exportarExcelEmergenciasActivas/{codAnio}/{codMes}/{codDpto}/{codProvincia}/{idFenomeno}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcelEmergenciasActivas(  @PathVariable("codAnio") String codAnio, 
													@PathVariable("codMes") String codMes, 
													@PathVariable("codDpto") String codDpto, 
													@PathVariable("codProvincia") String codProvincia, 
													@PathVariable("idFenomeno") Integer idFenomeno, 
													HttpServletResponse response) {
	    try {
	    	RequerimientoBean requerimientoBean = new RequerimientoBean();
	    	requerimientoBean.setCodAnio(verificaParametro(codAnio));
	    	requerimientoBean.setCodMes(verificaParametro(codMes));
	    	requerimientoBean.setCodDpto(verificaParametro(codDpto));
	    	requerimientoBean.setCodProvincia(verificaParametro(codProvincia));
	    	requerimientoBean.setIdFenomeno(idFenomeno);
			List<EmergenciaBean> lista = programacionService.listarEmergenciasActivas(requerimientoBean);
	    	
			String file_name = "EmergenciasporDistrito";
			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_XLS);
			
			ReporteEmergencia reporte = new ReporteEmergencia();
		    HSSFWorkbook wb = reporte.generaReporteExcelEmergencia(lista);
			
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
	 * @param codigo
	 * @param codigoAnio
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/pasarDistritos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object pasarDistritos(HttpServletRequest request, HttpServletResponse response) {
		EmergenciaBean emergencia = null;
		try {			
			EmergenciaBean emergenciaBean = new EmergenciaBean();

			BeanUtils.populate(emergenciaBean, request.getParameterMap());			
        	emergencia = programacionService.pasarDistritos(emergenciaBean); 

			emergencia.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return emergencia;
    }
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listarUbigeoInei", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarUbigeoInei(HttpServletRequest request, HttpServletResponse response) {
		List<UbigeoIneiBean> lista = null;
		try {			
			UbigeoIneiBean ubigeoBean = new UbigeoIneiBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(ubigeoBean, request.getParameterMap());			
			lista = programacionService.listarUbigeoInei(ubigeoBean); 
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/pasarDistritosUbigeo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object pasarDistritosUbigeo(HttpServletRequest request, HttpServletResponse response) {
		EmergenciaBean ubigeo = null;
		try {			
			EmergenciaBean ubigeoBean = new EmergenciaBean();
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			BeanUtils.populate(ubigeoBean, request.getParameterMap());	
			ubigeoBean.setUsuarioRegistro(usuarioBean.getUsuario());
			ubigeo = programacionService.pasarDistritosUbigeo(ubigeoBean); 

			ubigeo.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return ubigeo;
    }
	
	
	@RequestMapping(value = "/listarRequerimientoDetalle", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarRequerimientoDetalle(HttpServletRequest request, HttpServletResponse response) {
		List<EmergenciaBean> lista = null;
		try {			
			RequerimientoBean documento = new RequerimientoBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(documento, request.getParameterMap());			
			lista = programacionService.listarRequerimientoDetalle(documento);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	
	@RequestMapping(value = "/actualizarDamnificados", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object actualizarDamnificados(HttpServletRequest request, HttpServletResponse response) {
		EmergenciaBean emergencia = null;
		try {			
			EmergenciaBean emergenciaBean = new EmergenciaBean();

			// Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);			
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Convierte los vacios en nulos en los decimales
			BigDecimalConverter con_decimal = new BigDecimalConverter(null);
			beanUtilsBean.getConvertUtils().register(con_decimal, BigDecimal.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(emergenciaBean, request.getParameterMap());
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	emergenciaBean.setUsuarioRegistro(usuarioBean.getUsuario());
			
        	emergencia = programacionService.actualizarDamnificados(emergenciaBean);
			
        	emergencia.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return emergencia;
	}
	

	
	@RequestMapping(value = "/exportarExcelAfectados/{codAnio}/{idRequerimiento}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcelAfectados(@PathVariable("codAnio") String codAnio, 
								@PathVariable("idRequerimiento") Integer idRequerimiento, 
								HttpServletResponse response) {
	    try {
	    	RequerimientoBean requerimientoBean = new RequerimientoBean();
	    	requerimientoBean.setCodAnio(verificaParametro(codAnio));
	    	requerimientoBean.setIdRequerimiento(idRequerimiento);
	    	
//	    	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
//	    	requerimientoBean.setFkIdeDdi(Integer.parseInt(usuarioBean.getCodigoDdi()));
	    	
			List<EmergenciaBean> lista = programacionService.listarRequerimientoDetalle(requerimientoBean);
	    	
			String file_name = "Plantilla_REQ";
			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_XLS);
			
			ReporteRequerimientoAfectado reporte = new ReporteRequerimientoAfectado();
		    HSSFWorkbook wb = reporte.generaReporteExcelRequerimientoAfectado(lista);
			
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
	
	
	@RequestMapping(value = "/exportarPdf/{idRequerimiento}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarPdf(@PathVariable("idRequerimiento") Integer idRequerimiento, HttpServletRequest request, HttpServletResponse response) {
	    try {
	    	
	    	ListaRespuestaRequerimientoBean datosReporteRequerimiento = new ListaRespuestaRequerimientoBean();
        	
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	datosReporteRequerimiento = programacionService.obtenerReporteRequerimiento(idRequerimiento);
        	
        	RequerimientoBean cabecera =datosReporteRequerimiento.getLstCabecera().get(0);
    		 List<EmergenciaBean> detalles=datosReporteRequerimiento.getLstDetalle();
    		
        	ExportarArchivo printer = new ExportarArchivo();
			StringBuilder jasperFile = new StringBuilder();
			jasperFile.append(getPath(request));
			jasperFile.append(File.separator);
			jasperFile.append(Constantes.REPORT_PATH_PROGRAMACION);
			jasperFile.append("PROG_Report_Requerimiento.jrxml");
			
			Map<String, Object> parameters = new HashMap<String, Object>();

			// Agregando los parámetros del reporte
			StringBuilder logo_indeci_path = new StringBuilder();
			logo_indeci_path.append(getPath(request));
			logo_indeci_path.append(File.separator);
			logo_indeci_path.append(Constantes.IMAGE_INDECI_REPORT_PATH);
			
			StringBuilder logo_anulado = new StringBuilder();
			logo_anulado.append(getPath(request));
			logo_anulado.append(File.separator);
			logo_anulado.append(Constantes.IMAGE_INPUT_ANULADO_PATH);
			
			StringBuilder logo_noanulado = new StringBuilder();
			logo_noanulado.append(getPath(request));
			logo_noanulado.append(File.separator);
			logo_noanulado.append(Constantes.IMAGE_INPUT_NOANULADO_PATH);
			
			parameters.put("REPORT_LOCALE", new Locale("en", "US")); 
			parameters.put("P_LOGO_INDECI", logo_indeci_path.toString());
	    	parameters.put("P_DESCRIPCION", cabecera.getNomRequerimiento());
			parameters.put("P_FECHA", cabecera.getFechaRequerimiento());
			parameters.put("P_REGION", cabecera.getNomRegion());
			parameters.put("P_ATENCION_SINPAD", cabecera.getFlgSinpad().equals("1")?"Si":"No");
			parameters.put("P_REQUERIMIENTO", cabecera.getNumRequerimiento());
//			parameters.put("P_FENOMENO", cabecera.getDescFenomeno());
			parameters.put("P_ESTADO", cabecera.getIdEstado().toString());
			parameters.put("P_LOGO_ANULADO", logo_anulado.toString());
			parameters.put("P_LOGO_NOANULADO", logo_noanulado.toString());
			
			 /* Convert List to JRBeanCollectionDataSource */
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(detalles);
            parameters.put("P_LISTA_DAMNIFICADOS", itemsJRBean);
					
//			byte[] array = printer.exportPdf2(jasperFile.toString(), parameters);//verificar
			byte[] array = printer.exportPdf(jasperFile.toString(), parameters,detalles);//verificar
			InputStream input = new ByteArrayInputStream(array);
	        
	        String file_name = "PROG_Report_Requerimiento";
			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_PDF);
	    	
	        response.resetBuffer();
            response.setContentType(Constantes.MIME_APPLICATION_PDF);
            response.setHeader("Content-Disposition", "attachment; filename="+file_name);            
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "private");
			response.setHeader("Set-Cookie", "fileDownload=true; path=/");
			response.setDateHeader("Expires", 1);
			
			byte[] buffer = new byte[4096];
	    	int n = 0;

	    	OutputStream output = response.getOutputStream();
	    	while ((n = input.read(buffer)) != -1) {
	    	    output.write(buffer, 0, n);
	    	}
	    	output.close();
    	

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
	@RequestMapping(value = "/eliminarRequerimiento", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarRequerimiento(HttpServletRequest request, HttpServletResponse response) {
		RequerimientoBean producto = null;
		try {			
			String[] arrIdRequerimiento = request.getParameter("arrIdRequerimiento").split(Constantes.UNDERLINE);
			System.out.println("ARRAY: "+request.getParameter("arrIdRequerimiento"));
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			for (String codigo : arrIdRequerimiento) {				
				RequerimientoBean requerimientoBean = new RequerimientoBean();
				requerimientoBean.setIdRequerimiento(Integer.parseInt(codigo));
				// Retorno los datos de session
	        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

	        	requerimientoBean.setUsuarioRegistro(usuarioBean.getUsuario());
				
				producto = programacionRequerimientoService.eliminarRequerimientoEdan(requerimientoBean);				
			}

			producto.setMensajeRespuesta(getMensaje(messageSource, "msg.info.eliminadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return producto;
	}
	
	@RequestMapping(value = "/listarDetalleRequerimientoUpload", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarDetalleRequerimientoUpload(HttpServletRequest request, HttpServletResponse response) {
		List<EmergenciaBean> lista = null;
		try {			
			ItemBean itemBean = new ItemBean();
			BeanUtils.populate(itemBean, request.getParameterMap());	
			
			ListaRespuestaRequerimientoBean respuestaEdicion = new ListaRespuestaRequerimientoBean();
			respuestaEdicion = programacionService.obtenerRequerimiento(usuarioBean.getCodigoAnio(),usuarioBean.getCodigoDdi(),itemBean.getIcodigo()); 
		
			lista = respuestaEdicion.getLstDetalle();
			

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	
}
