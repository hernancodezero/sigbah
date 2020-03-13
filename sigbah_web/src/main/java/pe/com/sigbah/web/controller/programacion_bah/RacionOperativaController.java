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

import pe.com.sigbah.common.bean.ControlCalidadBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.ListaRespuestaRequerimientoBean;
import pe.com.sigbah.common.bean.ProductoBean;
import pe.com.sigbah.common.bean.ProductoControlCalidadBean;
import pe.com.sigbah.common.bean.ProductoRacionBean;
import pe.com.sigbah.common.bean.RacionBean;
import pe.com.sigbah.common.bean.UsuarioBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.DateUtil;
import pe.com.sigbah.common.util.HtmlUtils;
import pe.com.sigbah.service.AdministracionService;
import pe.com.sigbah.service.GeneralService;
import pe.com.sigbah.service.ProgramacionService;
import pe.com.sigbah.web.controller.common.BaseController;
import pe.com.sigbah.web.report.gestion_almacenes.ReporteControlCalidad;
import pe.com.sigbah.web.report.programacion_bah.ReporteRacionProducto;
import pe.com.sigbah.web.report.programacion_bah.ReporteRaciones;

/**
 * @className: EmergenciaController.java
 * @description: 
 * @date: 13 jul. 2017
 * @author: whr.
 */

@Controller
@RequestMapping("/programacion-bath/racion")
public class RacionOperativaController extends BaseController {
	
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
        	RacionBean racione = new RacionBean();
        	racione.setCodAnio(generalService.obtenerAnioActual());
        	model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	
        	model.addAttribute("lista_racion", generalService.listarRacion(new ItemBean()));
//        	        	
//        	model.addAttribute("lista_tipo_movimiento", generalService.listarTipoMovimiento(new ItemBean(Constantes.TWO_INT, Constantes.TWO_INT)));
//        	
//        	OrdenSalidaBean ordenSalida = new OrdenSalidaBean();
//        	
//        	ControlCalidadBean parametroAlmacenActivo = new ControlCalidadBean();
//    		parametroAlmacenActivo.setIdAlmacen(usuarioBean.getIdAlmacen());
//    		parametroAlmacenActivo.setTipo(Constantes.CODIGO_TIPO_ALMACEN);
//    		List<ControlCalidadBean> listaAlmacenActivo = logisticaService.listarAlmacenActivo(parametroAlmacenActivo);
//    		if (!isEmpty(listaAlmacenActivo)) {
//    			ordenSalida.setCodigoAnio(listaAlmacenActivo.get(0).getCodigoAnio());
//    			ordenSalida.setIdAlmacen(listaAlmacenActivo.get(0).getIdAlmacen());
//    			ordenSalida.setCodigoAlmacen(listaAlmacenActivo.get(0).getCodigoAlmacen());
//    			ordenSalida.setNombreAlmacen(listaAlmacenActivo.get(0).getNombreAlmacen());
//    			ordenSalida.setCodigoMes(listaAlmacenActivo.get(0).getCodigoMes());
//    		}
//    		
//    		model.addAttribute("ordenSalida", getParserObject(ordenSalida));
//        	
        	model.addAttribute("indicador", indicador);
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	model.addAttribute("racione", getParserObject(racione));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "racion-operativa";
    }
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listarRaciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarRaciones(HttpServletRequest request, HttpServletResponse response) {
		List<RacionBean> lista = null;
		try {			
			RacionBean racionBean = new RacionBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(racionBean, request.getParameterMap());			
			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			racionBean.setIdDdi(Integer.parseInt(usuarioBean.getCodigoDdi()));
			lista = programacionService.listarRaciones(racionBean); 
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	

	
	
	/**
	 * @param codigo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/copiarRacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public Object  copiarRacion(HttpServletRequest request, HttpServletResponse response) {
		RacionBean racion = null;
		try {
        	RacionBean racionBean = new RacionBean();
        	
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	 // Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(racionBean, request.getParameterMap());
        
			racionBean.setUsuarioRegistro(usuarioBean.getUsuario());
			racionBean.setIdDdi(usuarioBean.getIdDdi());
        	racion = programacionService.copiarRacion(racionBean);
        	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return racion;
    }
	
	/**
	 * @param codigo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mantenimientoRacion/{codigo}", method = RequestMethod.GET)
    public String mantenimientoRacion(@PathVariable("codigo") Integer codigo, Model model) {
        try {
        	RacionBean racion = new RacionBean();
        	
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        
        	ListaRespuestaRequerimientoBean respuestaEdicion = new ListaRespuestaRequerimientoBean();
        
        	if (!isNullInteger(codigo)) {// editar
        		
        		racion = programacionService.obtenerRegistroRacion(codigo); 

        	} else {//nuevo

        		RacionBean parametros = new RacionBean();
        		String anioActual = generalService.obtenerAnioActual();
        		parametros.setCodAnio(anioActual);
        		parametros.setIdDdi(usuarioBean.getIdDdi());   
        		
        		RacionBean respuestaCorrelativo = programacionService.obtenerCorrelativoRacion(parametros);
        		racion.setCodRacion(respuestaCorrelativo.getCodRacion());   		
        		Date fecha_hora = Calendar.getInstance().getTime();
        		racion.setFechaRacion(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_FECHA, fecha_hora));
        		
    			
        	}
	
        	model.addAttribute("racion", getParserObject(racion));
        	
        	model.addAttribute("lista_racion", generalService.listarRacion(new ItemBean()));
        	model.addAttribute("lista_producto", generalService.listarCatologoProductos(new ProductoBean(null, Constantes.FIVE_INT)));//whr consultar
        	
        	
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "mantenimiento_racion";
    }
	
	
	@RequestMapping(value = "/grabarRacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarRacion(HttpServletRequest request, HttpServletResponse response) {
		RacionBean racion = null;
		try {			
			RacionBean racionBean = new RacionBean();

	        // Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(racionBean, request.getParameterMap());

			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	String anioActual = generalService.obtenerAnioActual();
        	
        	racionBean.setUsuarioRegistro(usuarioBean.getUsuario());
			racionBean.setIdDdi(usuarioBean.getIdDdi());
			racionBean.setCodAnio(anioActual);
			
			if (!isNullInteger(racionBean.getIdRacionOpe())) {		
				racionBean.setControl("U");
				racion = programacionService.insertarRegistroRacion(racionBean);				
			} else {			
				racionBean.setControl("I");
				racion = programacionService.insertarRegistroRacion(racionBean);			
			}
			racion.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return racion;
	}
	
	
	@RequestMapping(value = "/grabarProducto", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarProducto(HttpServletRequest request, HttpServletResponse response) {
		ProductoRacionBean producto = null;
		try {			
			ProductoRacionBean productoBean = new ProductoRacionBean();

	        // Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(productoBean, request.getParameterMap());

			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	String anioActual = generalService.obtenerAnioActual();
        	
        	productoBean.setUsuarioRegistro(usuarioBean.getUsuario());
        	
        	productoBean.setCodAnio(anioActual);
//        	productoBean.setIdRacion(32);//whr consultar por idrracion, deberia devolver al grabar racion o llamr independientemente el 
        	
           
            
			if (!isNullInteger(productoBean.getIdDetaRacion())) {				
				producto = programacionService.actualizarRegistroProducto(productoBean);				
			} else {			
				producto = programacionService.insertarRegistroProducto(productoBean);			
			} 
			producto.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return producto;
	}
	
	
	@RequestMapping(value = "/listarProductos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProductos(HttpServletRequest request, HttpServletResponse response) {
		List<ProductoRacionBean> lista = null;
		try {			
			ProductoRacionBean productoRacionBean = new ProductoRacionBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(productoRacionBean, request.getParameterMap());			
			
			lista = programacionService.listarProductos(productoRacionBean); 
		} catch (Exception e) { 
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		System.out.println("LISTA: "+lista.size());
		return lista;
	}
	
	@RequestMapping(value = "/exportarExcelProducto/{idRacionOpe}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcel(@PathVariable("idRacionOpe") Integer idRacionOpe,
								HttpServletResponse response) {
	    try {
	    	ProductoRacionBean productoRacionBean = new ProductoRacionBean();
	    	productoRacionBean.setIdRacion(idRacionOpe);
	    	
			List<ProductoRacionBean> lista = programacionService.listarProductos(productoRacionBean);
	    	
			String file_name = "ListaRaciones";
			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_XLS);
			
			
			ReporteRacionProducto reporte = new ReporteRacionProducto();
		    HSSFWorkbook wb = reporte.generaReporteExcelRacionProducto(lista);
			
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
	
	@RequestMapping(value = "/exportarExcelRaciones/{codAnio}/{codMes}/{tipoRacion}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcelRacion(@PathVariable("codAnio") String codAnio,
			@PathVariable("codMes") String codMes,
			@PathVariable("tipoRacion") String tipoRacion,
								HttpServletResponse response) {
	    try {
	    	List<RacionBean> lista = null;
	    	RacionBean racionBean = new RacionBean();	
	    	racionBean.setCodAnio(codAnio);
	    	racionBean.setCodMesRacion(codMes.equals("_")?"":codMes);
	    	racionBean.setTipoRacion(tipoRacion.equals("_")?"":tipoRacion);
	    	
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			racionBean.setIdDdi(Integer.parseInt(usuarioBean.getCodigoDdi()));
			lista = programacionService.listarRaciones(racionBean);

			String file_name = "ListaRaciones";
			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_XLS);
			
			
			ReporteRaciones reporte = new ReporteRaciones();
		    HSSFWorkbook wb = reporte.generaReporteExcelRaciones(lista);
			
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
	
	@RequestMapping(value = "/eliminarProductoRacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarProductoRacion(HttpServletRequest request, HttpServletResponse response) {
		ProductoRacionBean producto = null;
		try {			
			String[] arrIdProducto = request.getParameter("arrIdProducto").split(Constantes.UNDERLINE);
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			
			for (String codigo : arrIdProducto) {				
				ProductoRacionBean productoRacionBean = new ProductoRacionBean();

				productoRacionBean.setUsuarioRegistro(usuarioBean.getUsuario());
				productoRacionBean.setIdDetaRacion(getInteger(codigo));
				producto = programacionService.eliminarProductoRacion(productoRacionBean);
				
				
				
			}

			producto.setMensajeRespuesta(getMensaje(messageSource, "msg.info.eliminadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return producto;
	}
	
}
