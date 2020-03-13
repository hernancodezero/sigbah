package pe.com.sigbah.web.controller.programacion_bah;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import pe.com.sigbah.common.bean.DocumentoPedidoCompraBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.ListaRespuestaPedidoCompraBean;
import pe.com.sigbah.common.bean.ListaRespuestaRequerimientoBean;
import pe.com.sigbah.common.bean.PedidoCompraBean;
import pe.com.sigbah.common.bean.PedidoCompraReporteBean;
import pe.com.sigbah.common.bean.ProductoBean;
import pe.com.sigbah.common.bean.ProductoPedidoCompraBean;
import pe.com.sigbah.common.bean.UsuarioBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.DateUtil;
import pe.com.sigbah.common.util.ExportarArchivo;
import pe.com.sigbah.common.util.HtmlUtils;
import pe.com.sigbah.service.AdministracionService;
import pe.com.sigbah.service.GeneralService;
import pe.com.sigbah.service.ProgramacionService;
import pe.com.sigbah.web.controller.common.BaseController;
import pe.com.sigbah.web.report.programacion_bah.ReportePedidoCompra;

/**
 * @className: EmergenciaController.java
 * @description: 
 * @date: 13 jul. 2017
 * @author: whr.
 */

@Controller
@RequestMapping("/programacion-bath/pedido")
public class PedidoCompraController extends BaseController {
	
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
        	PedidoCompraBean pedcompra= new PedidoCompraBean();
        	pedcompra.setCodAnio(generalService.obtenerAnioActual());
        	model.addAttribute("lista_anio", generalService.listarAnios());
//        	model.addAttribute("lista_ddi", generalService.listarDdi(new ItemBean()));
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	model.addAttribute("lista_estado", generalService.listarEstadoPedidoCompra( new ItemBean()));
       	
        	model.addAttribute("indicador", indicador);
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	model.addAttribute("pedcompra", getParserObject(pedcompra));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "pedido-compra";
    }

	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listarPedidosCompra", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarPedidosCompra(HttpServletRequest request, HttpServletResponse response) {
		List<PedidoCompraBean> lista = null;
		try {			
			PedidoCompraBean pedidoCompraBean = new PedidoCompraBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(pedidoCompraBean, request.getParameterMap());			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			pedidoCompraBean.setFkIdeDdi(Integer.parseInt(usuarioBean.getCodigoDdi()));
			lista = programacionService.listarPedidosCompra(pedidoCompraBean); 
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	/**
	 * @param codAnio
	 * @param codMes
	 * @param codFenomeno
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/exportarExcel/{codAnio}/{codMes}/{codEstado}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcel(@PathVariable("codAnio") String codAnio, 
								@PathVariable("codMes") String codMes, 
								@PathVariable("codEstado") String codEstado, 
								HttpServletResponse response) {
	    try {
	    	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
	    	PedidoCompraBean pedidoCompraBean = new PedidoCompraBean();
	    	pedidoCompraBean.setCodAnio(verificaParametro(codAnio));
	    	pedidoCompraBean.setCodMes(verificaParametro(codMes));
	    	pedidoCompraBean.setCodEstado(codEstado);
	    	pedidoCompraBean.setFkIdeDdi(usuarioBean.getIdDdi());
	    	
			List<PedidoCompraBean> lista = programacionService.listarPedidosCompra(pedidoCompraBean);
	    	
			String file_name = "PedidoCompra";
			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_XLS);
			
			ReportePedidoCompra reporte = new ReportePedidoCompra();
		    HSSFWorkbook wb = reporte.generaReporteExcelPedidoCompra(lista);
		    
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
	
	
	@RequestMapping(value = "/mantenimientoPedido/{codigo}", method = RequestMethod.GET)
    public String mantenimientoPedido(@PathVariable("codigo") Integer codigo, Model model) {
        try {
        	PedidoCompraBean pedido = new PedidoCompraBean();
        	
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	ListaRespuestaRequerimientoBean respuestaEdicion = new ListaRespuestaRequerimientoBean();
        	if (!isNullInteger(codigo)) {// editar
        		
        		pedido = programacionService.obtenerPedidoCompra(codigo); 
        		
//        		model.addAttribute("lista_requerimiento", getParserObject(respuestaEdicion.getLstDetalle()));
        	  		
        	} else {//nuevo

        		PedidoCompraBean parametros = new PedidoCompraBean();
        		String anioActual = generalService.obtenerAnioActual();
        		parametros.setCodAnio(anioActual);
        		parametros.setFkIdeDdi(usuarioBean.getIdDdi());   
        		parametros.setCodDdi(usuarioBean.getCodigoDdi());   
        		PedidoCompraBean respuestaCorrelativo = programacionService.obtenerCorrelativoPedidoCompra(parametros);
        		pedido.setCodPedidoConcate(respuestaCorrelativo.getCodPedidoConcate()); 
        		pedido.setCodPedido(respuestaCorrelativo.getCodPedido()); 
        		Date fecha_hora = Calendar.getInstance().getTime();
        		pedido.setFecPedido(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_FECHA, fecha_hora));
        		
    			
        	}
        	model.addAttribute("pedido", getParserObject(pedido));
        	
        	model.addAttribute("lista_estado", generalService.listarEstadoPedidoCompra(new ItemBean()));
        	model.addAttribute("lista_dee", generalService.listarDee(new ItemBean()));
        	
        	model.addAttribute("lista_tipo_doc", generalService.listarTipoDocumento(new ItemBean()));
        	model.addAttribute("lista_categoria", generalService.listarCategoria(new ItemBean()));
//        	model.addAttribute("lista_producto", generalService.listarCatologoProductos(new ProductoBean(null, Constantes.FIVE_INT)));
        	
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "mantenimiento-pedido-compra";
    }
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/grabarPedido", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarPedido(HttpServletRequest request, HttpServletResponse response) {
		PedidoCompraBean pedido = null;
		try {			
			PedidoCompraBean pedidoBean = new PedidoCompraBean();

	        // Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(pedidoBean, request.getParameterMap());

			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	String anioActual = generalService.obtenerAnioActual();
        	
        	pedidoBean.setUsuarioRegistro(usuarioBean.getNombreUsuario());
        	pedidoBean.setFkIdeDdi(usuarioBean.getIdDdi());
        	pedidoBean.setCodAnio(anioActual);
        	pedidoBean.setCodDdi(usuarioBean.getCodigoDdi());
        	
        	SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
        	String mes= dateFormat.format(DateUtil.obtenerFechaHoraParseada(pedidoBean.getFecPedido()));
        	pedidoBean.setCodMes(mes);
			if (!isNullInteger(pedidoBean.getIdPedidoCom())) {			
				pedidoBean.setControl("U");
				pedido = programacionService.insertarRegistroPedido(pedidoBean);				
			} else {			
				pedidoBean.setControl("I");
				pedido = programacionService.insertarRegistroPedido(pedidoBean);			
			}
			pedido.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return pedido;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/grabarDocumentoPedidoCompra", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarDocumentoPedidoCompra(HttpServletRequest request, HttpServletResponse response) {
		DocumentoPedidoCompraBean documento = null;
		try {			
			DocumentoPedidoCompraBean documentoPedidoCompraBean = new DocumentoPedidoCompraBean();
			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(documentoPedidoCompraBean, request.getParameterMap());
		
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	documentoPedidoCompraBean.setUsuarioRegistro(usuarioBean.getUsuario());
			
        	documento = programacionService.grabarDocumentoPedidoCompra(documentoPedidoCompraBean);
        	
        	documento.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return documento;
	}
	
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listarDocumentoPedidoCompra", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarDocumentoPedidoCompra(HttpServletRequest request, HttpServletResponse response) {
		List<DocumentoPedidoCompraBean> lista = null;
		try {			
			DocumentoPedidoCompraBean documento = new DocumentoPedidoCompraBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(documento, request.getParameterMap());			
			lista = programacionService.listarDocumentoPedidoCompra(documento);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/eliminarDocumentoPedidoCompra", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarDocumentoPedidoCompra(HttpServletRequest request, HttpServletResponse response) {
		DocumentoPedidoCompraBean documento = null;
		try {			
			String[] arrIdDetallePedidoCompra = request.getParameter("arrIdDocumentoPedidoCompra").split(Constantes.UNDERLINE);
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
			for (String codigo : arrIdDetallePedidoCompra) {				
				DocumentoPedidoCompraBean documentoPedidoCompraBean = new DocumentoPedidoCompraBean(getInteger(codigo));

				documentoPedidoCompraBean.setUsuarioRegistro(usuarioBean.getUsuario());
				
	        	documento = programacionService.eliminarDocumentoPedidoCompra(documentoPedidoCompraBean);				
			}

			documento.setMensajeRespuesta(getMensaje(messageSource, "msg.info.eliminadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return documento;
	}
	
	@RequestMapping(value = "/grabarProductoPedidoCompra", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarProductoPedidoCompra(HttpServletRequest request, HttpServletResponse response) {
		ProductoPedidoCompraBean producto = null;
		try {			
			ProductoPedidoCompraBean productoPedidoCompraBean = new ProductoPedidoCompraBean();

			// Convierte los vacios en nulos en los enteros
//			IntegerConverter con_integer = new IntegerConverter(null);			
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
//			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Convierte los vacios en nulos en los decimales
			BigDecimalConverter con_decimal = new BigDecimalConverter(null);
			beanUtilsBean.getConvertUtils().register(con_decimal, BigDecimal.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(productoPedidoCompraBean, request.getParameterMap());
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	productoPedidoCompraBean.setUsuarioRegistro(usuarioBean.getUsuario());
			
			producto = programacionService.grabarProductoPedidoCompra(productoPedidoCompraBean);
			
			producto.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return producto;
	}
	
	@RequestMapping(value = "/listarProductoPedidoCompra", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProductoPedidoCompra(HttpServletRequest request, HttpServletResponse response) {
		List<ProductoPedidoCompraBean> lista = null;
		try {			
			ProductoPedidoCompraBean producto = new ProductoPedidoCompraBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(producto, request.getParameterMap());			
			lista = programacionService.listarProductoPedidoCompra(producto);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/eliminarProductoPedidoCompra", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarProductoPedidoCompra(HttpServletRequest request, HttpServletResponse response) {
		ProductoPedidoCompraBean producto = null;
		try {			
			String[] arrIdProductoPedidoCompra = request.getParameter("arrIdProductoPedidoCompra").split(Constantes.UNDERLINE);
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
			for (String codigo : arrIdProductoPedidoCompra) {				
				ProductoPedidoCompraBean productoPedidoCompraBean = new ProductoPedidoCompraBean(getInteger(codigo));

				productoPedidoCompraBean.setUsuarioRegistro(usuarioBean.getUsuario());
				
				producto = programacionService.eliminarProductoPedidoCompra(productoPedidoCompraBean);				
			}

			producto.setMensajeRespuesta(getMensaje(messageSource, "msg.info.eliminadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return producto;
	}
	
	@RequestMapping(value = "/listarProductoXCategoria", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProductoXCategoria(HttpServletRequest request, HttpServletResponse response) {
		List<ProductoBean> lista = null;
		try {			
			ProductoBean producto = new ProductoBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(producto, request.getParameterMap());			
			lista = generalService.listarCatologoProductos(new ProductoBean(null, producto.getIdCategoria()));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	

	@RequestMapping(value = "/exportarPdf/{idPedidoCom}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarPdf(@PathVariable("idPedidoCom") Integer idPedidoCom, HttpServletRequest request, HttpServletResponse response) {
		ListaRespuestaPedidoCompraBean datosReportePedidoCompra =  new ListaRespuestaPedidoCompraBean();
		try {
	    	
	    	
			datosReportePedidoCompra = programacionService.obtenerReportePedidoCompra(idPedidoCom); 
        		
			PedidoCompraReporteBean cabecera =datosReportePedidoCompra.getLstCabecera().get(0);
   		 List<PedidoCompraReporteBean> detalle=datosReportePedidoCompra.getLstDetalle();
    		
        	ExportarArchivo printer = new ExportarArchivo();
			StringBuilder jasperFile = new StringBuilder();
			jasperFile.append(getPath(request));
			jasperFile.append(File.separator);
			jasperFile.append(Constantes.REPORT_PATH_PROGRAMACION);
			jasperFile.append("PROG_Report_PedidoCompra.jrxml");
			
			Map<String, Object> parameters = new HashMap<String, Object>();

			// Agregando los parámetros del reporte
			StringBuilder logo_indeci_path = new StringBuilder();
			logo_indeci_path.append(getPath(request));
			logo_indeci_path.append(File.separator);
			logo_indeci_path.append(Constantes.IMAGE_INDECI_REPORT_PATH);
			parameters.put("REPORT_LOCALE", new Locale("en", "US")); 
			parameters.put("P_LOGO_INDECI", logo_indeci_path.toString());
	    	parameters.put("P_NOM_SISTEMA", cabecera.getNombreSistema());
	    	parameters.put("P_NUM_PEDIDO", cabecera.getNumPedidoCompra());
	    	parameters.put("P_FECHA", cabecera.getFecPedido());
	    	parameters.put("P_NOM_PEDIDO", cabecera.getDescripcion());
	    	parameters.put("P_TIP_PEDIDO", cabecera.getTipoPedido());
	    	parameters.put("P_MOTIVO_COMPRA", cabecera.getMotivoCompra());
	    	parameters.put("P_NUM_DEE", cabecera.getDee());
			
			 /* Convert List to JRBeanCollectionDataSource */
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(detalle);
            parameters.put("P_LISTA_PRODUCTOS", itemsJRBean);
					
			byte[] array = printer.exportPdf(jasperFile.toString(), parameters,detalle);//verificar
			InputStream input = new ByteArrayInputStream(array);
	        
	        String file_name = "PROG_Report_PedidoCompra";
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
	
}
