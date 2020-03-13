package pe.com.sigbah.web.controller.gestion_almacenes;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import pe.com.sigbah.common.bean.CierreStockBean;
import pe.com.sigbah.common.bean.ControlCalidadBean;
import pe.com.sigbah.common.bean.DetalleProductoControlCalidadBean;
import pe.com.sigbah.common.bean.DocumentoControlCalidadBean;
import pe.com.sigbah.common.bean.DocumentoDonacionBean;
import pe.com.sigbah.common.bean.DocumentoDonacionIngresoBean;
import pe.com.sigbah.common.bean.DocumentoIngresoBean;
import pe.com.sigbah.common.bean.DocumentoSalidaBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.ProductoBean;
import pe.com.sigbah.common.bean.ProductoControlCalidadBean;
import pe.com.sigbah.common.bean.ProductoDonacionBean;
import pe.com.sigbah.common.bean.ProductoDonacionIngresoBean;
import pe.com.sigbah.common.bean.ProductoDonacionSalidaBean;
import pe.com.sigbah.common.bean.ProductoIngresoBean;
import pe.com.sigbah.common.bean.RegionDonacionBean;
import pe.com.sigbah.common.bean.StockAlmacenBean;
import pe.com.sigbah.common.bean.StockConsultaBean;
import pe.com.sigbah.common.bean.UbigeoBean;
import pe.com.sigbah.common.bean.UsuarioBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.ExportarArchivo;
import pe.com.sigbah.common.util.HtmlUtils;
import pe.com.sigbah.common.util.Utils;
import pe.com.sigbah.service.GeneralService;
import pe.com.sigbah.service.AdministracionService;
import pe.com.sigbah.service.DonacionService;
import pe.com.sigbah.service.LogisticaService;
import pe.com.sigbah.web.controller.common.BaseController;
import pe.com.sigbah.web.report.donaciones.ReporteDonaciones;
import pe.com.sigbah.web.report.gestion_almacenes.ReporteControlCalidad;
import pe.com.sigbah.common.bean.DonacionesBean;
import pe.com.sigbah.common.bean.DonacionesIngresoBean;
import pe.com.sigbah.common.bean.DonacionesSalidaBean;


/**
 * @className: ConsultaController.java
 * @description: 
 * @date: 07/09/2017
 * @author: ARCHY.
 */
@Controller
@RequestMapping("/gestion-almacenes/consulta")
public class ConsultaController extends BaseController {

	private static final long serialVersionUID = 1L;
	@Autowired 
	private DonacionService donacionService;
	
	@Autowired 
	private GeneralService generalService;
	
	@Autowired 
	private LogisticaService logisticaService;
	
	@Autowired 
	private AdministracionService administracionService;
	/**
	 * @param model 
	 * @return - Retorna a la vista JSP.
	 */
	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
    public String inicio(Model model) {
		String ruta="";
        try {       
        	// Retorno los datos de session
           	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

        	model.addAttribute("lista_ddi", generalService.listarDdi(new ItemBean(Constantes.ZERO_INT)));
        	model.addAttribute("lista_categoria", generalService.listarCategoria(new ItemBean(Constantes.THREE_INT)));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar-consulta-stock-almacen";
    }
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarStock", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarDonaciones(HttpServletRequest request, HttpServletResponse response) {
		List<StockAlmacenBean> lista = null;
		try {
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			StockAlmacenBean stockAlmacenBean = new StockAlmacenBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(stockAlmacenBean, request.getParameterMap());

			lista = logisticaService.listarConsultaStockAlmacen(stockAlmacenBean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/inicioHistorial", method = RequestMethod.GET)
    public String inicioHistorial(Model model) {
		String ruta="";
        try {       
        	// Retorno los datos de session
           	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

   			model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	model.addAttribute("lista_ddi", generalService.listarDdi(new ItemBean(Constantes.ZERO_INT)));
        	
        	model.addAttribute("lista_estado", generalService.listarEstado(new ItemBean(null, Constantes.FOUR_INT)));
        	
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	
        	model.addAttribute("lista_tipo_movimiento", generalService.listarTipoMovimiento(new ItemBean(Constantes.TWO_INT, Constantes.ONE_INT)));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));

        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar-consulta-historial-almacen";
    }
	
	@RequestMapping(value = "/inicioHistorialSalida", method = RequestMethod.GET)
    public String inicioHistorialSalida(Model model) {
		String ruta="";
        try {       
        	// Retorno los datos de session
           	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

   			model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	model.addAttribute("lista_ddi", generalService.listarDdi(new ItemBean(Constantes.ZERO_INT)));
        	
        	model.addAttribute("lista_estado", generalService.listarEstado(new ItemBean(null, Constantes.FOUR_INT)));
        	
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	
        	model.addAttribute("lista_tipo_movimiento", generalService.listarTipoMovimiento(new ItemBean(Constantes.TWO_INT, Constantes.ONE_INT)));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar-consulta-historial-almacen-s";
    }
	
	@RequestMapping(value = "/inicioProductosFecha", method = RequestMethod.GET)
    public String inicioProductosFecha(Model model) {
		String ruta="";
        try {       
        	// Retorno los datos de session
           	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

   			model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	model.addAttribute("lista_ddi", generalService.listarDdi(new ItemBean(Constantes.ZERO_INT)));
        	
        	model.addAttribute("lista_estado", generalService.listarEstado(new ItemBean(null, Constantes.FOUR_INT)));
        	
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	
        	model.addAttribute("lista_tipo_movimiento", generalService.listarTipoMovimiento(new ItemBean(Constantes.TWO_INT, Constantes.ONE_INT)));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar-consulta-productos-fecha";
    }
	
	@RequestMapping(value = "/listarHistorial", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarHistorial(HttpServletRequest request, HttpServletResponse response) {
		List<ProductoIngresoBean> lista = null;
		try {
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			ProductoIngresoBean productoIngresoBean = new ProductoIngresoBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(productoIngresoBean, request.getParameterMap());

			lista = logisticaService.listarHistorialIngreso(productoIngresoBean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/listarHistorialSalida", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarHistorialSalida(HttpServletRequest request, HttpServletResponse response) {
		List<ProductoIngresoBean> lista = null;
		try {
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			ProductoIngresoBean productoIngresoBean = new ProductoIngresoBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(productoIngresoBean, request.getParameterMap());

			lista = logisticaService.listarHistorialSalida(productoIngresoBean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/listarProductosFecha", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProductosFecha(HttpServletRequest request, HttpServletResponse response) {
		List<ProductoBean> lista = null;
		try {
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			ProductoBean productoBean = new ProductoBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(productoBean, request.getParameterMap());

			lista = logisticaService.listarProductosFechaVencimiento(productoBean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	
	/**
	 * @param codigoAnio 
	 * @param codigoDdi 
	 * @param codigoAlmacen
	 * @param response
	 * @return Objeto.
	 */
	@RequestMapping(value = "/exportarExcel/{codigoAnio}/{codigoDdi}/{codigoMes}/{codigoMov}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcel(@PathVariable("codigoAnio") String codigoAnio, 
								@PathVariable("codigoDdi") String codigoDdi, 
								@PathVariable("codigoMes") String codigoMes, 
								@PathVariable("codigoMov") String codigoMov, 
								HttpServletResponse response) {
	    try {
	    	DonacionesBean donacionesBean = new DonacionesBean();
	    	donacionesBean.setCodigoAnio(verificaParametro(codigoAnio));
	    	donacionesBean.setCodigoDdi(verificaParametro(codigoDdi));
	    	donacionesBean.setCodigoMes(verificaParametro(codigoMes));
	    	donacionesBean.setCodigoEstado(verificaParametro(codigoMov));
			List<DonacionesBean> lista = donacionService.listarDonaciones(donacionesBean);
	    	
			String file_name = "Reporte_Donaciones";
			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_XLS);
			
			ReporteDonaciones reporte = new ReporteDonaciones();
		    HSSFWorkbook wb = reporte.generaReporteExcelDonaciones(lista);
			
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
	 * @param request 
	 * @param response
	 * @return Objeto.
	 */
	@RequestMapping(value = "/exportarPdf/{idDdi}/{idAlmacen}/{nomDdi}/{nomAlmacen}/{idCategoria}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarPdf(@PathVariable("idDdi") Integer idddi, 
								@PathVariable("idAlmacen") Integer idalmacen,
								@PathVariable("nomDdi") String nomDdi,
								@PathVariable("nomAlmacen") String nomAlmacen,
								@PathVariable("idCategoria") Integer idCategoria,
								HttpServletRequest request, HttpServletResponse response) {
	    try {
	    	StockConsultaBean stock = new StockConsultaBean();
	    	stock.setIdDdi(idddi);
	    	stock.setIdAlmacen(idalmacen);
	    	stock.setIdCategoria(idCategoria);
	    	String titulo="";
	    	Date ahora = new Date();
	        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
	        String fecha = formateador.format(ahora);
	    	if(idddi==0 && idalmacen==0){
	    		
	    		titulo="Stock al "+fecha+" A nivel Nacional";
	    	}else if(idddi!=0 && idalmacen==0){
	    		titulo="Stock al "+fecha+" de la DDI: "+nomDdi;
	    		
	    	}else if(idddi!=0 && idalmacen!=0){
	    		titulo="Stock al "+fecha+" de la DDI: "+nomDdi+" y : "+nomAlmacen;
	    		
	    	}
	    	
			List<StockConsultaBean> listaAlimentos = logisticaService.listarReporteStockDatos(stock);
			StockConsultaBean nomSistema = logisticaService.listarReporteStockTitulo(stock);
			
			if(isEmpty(listaAlimentos)){
				StockConsultaBean data = new StockConsultaBean();
				data.setNombreCategoria("-");
				data.setNombreProducto("-");
				data.setCantidad(null);	
				data.setImporteTotal(null);	
				listaAlimentos.add(data);
			}
			
			if (isEmpty(listaAlimentos)) {
				return Constantes.COD_VALIDACION_GENERAL;
			}			
//			DonacionesSalidaBean general = lista.get(0);

			ExportarArchivo printer = new ExportarArchivo();
			StringBuilder jasperFile = new StringBuilder();
			jasperFile.append(getPath(request));
			jasperFile.append(File.separator);
			jasperFile.append(Constantes.REPORT_PATH_ALMACENES);
			jasperFile.append("Consulta_Stock.jrxml");
			
			
			
			String ruta = getPath(request)+File.separator+Constantes.REPORT_PATH_ALMACENES;
//			if (producto.getFlagTipoProducto().equals(Constantes.ONE_STRING)) {
//				jasperFile.append("Control_Calidad_Alimentaria.jrxml");
//			} else {
//				jasperFile.append("Control_Calidad_No_Alimentaria.jrxml");
//			}
			
			JRBeanCollectionDataSource ListaAli = new JRBeanCollectionDataSource(listaAlimentos);
			
			
//			
			Map<String, Object> parameters = new HashMap<String, Object>();

			// Agregando los parÃ¡metros del reporte
			StringBuilder logo_indeci_path = new StringBuilder();
			logo_indeci_path.append(getPath(request));
			logo_indeci_path.append(File.separator);
			logo_indeci_path.append(Constantes.IMAGE_INDECI_REPORT_PATH);
			parameters.put("P_LOGO_INDECI", logo_indeci_path.toString());			
			StringBuilder logo_wfp_path = new StringBuilder();
			logo_wfp_path.append(getPath(request));
			logo_wfp_path.append(File.separator);
			logo_wfp_path.append(Constantes.IMAGE_WFP_REPORT_PATH);
			parameters.put("P_LOGO_WFP", logo_wfp_path.toString());			
			StringBuilder logo_check_path = new StringBuilder();
			logo_check_path.append(getPath(request));
			logo_check_path.append(File.separator);
			logo_check_path.append(Constantes.IMAGE_CHECK_REPORT_PATH);
			parameters.put("P_LOGO_CHECK", logo_check_path.toString());			
			StringBuilder logo_check_min_path = new StringBuilder();
			logo_check_min_path.append(getPath(request));
			logo_check_min_path.append(File.separator);
			logo_check_min_path.append(Constantes.IMAGE_CHECK_REPORT_PATH);
			parameters.put("P_LOGO_CHECK_MIN", logo_check_min_path.toString());			
//			parameters.put("P_NRO_ORDEN_INGRESO", general.getNroOrdenSalida());
//			parameters.put("P_DDI", general.getNombreDdi());			
//			parameters.put("P_FECHA_EMISION", general.getFechaEmision());
//			parameters.put("P_ALMACEN_ORIGEN", general.getNomAlmacenOrigen());
//			parameters.put("P_OBSERVACION", general.getObservacion());
//			parameters.put("P_TIPO_MOVIMIENTO", general.getNombreMovimiento());
//			parameters.put("P_ALMACEN_DESTINO", general.getNomAlmacenDestino());
//			parameters.put("LISTA_DOCUMENTOS", ListaDocumentos);
			parameters.put("SR_RUTA_ALIMENTOS", ruta);
//			parameters.put("D_NOMBRE_SISTEMA", general.getNombreSistema());
			parameters.put("NOMBRE_SISTEMA", nomSistema.getNombreSistema());
			parameters.put("LISTA_ALIMENTOS", ListaAli);
			parameters.put("TITULO", titulo);
			
			byte[] array = printer.exportPdf(jasperFile.toString(), parameters, listaAlimentos);
			InputStream input = new ByteArrayInputStream(array);
	        
	        String file_name = "Consulta_Stock";
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
	    	LOGGER.info("PDF listo");
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
	@RequestMapping(value = "/listarAlmacen", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProvincia(HttpServletRequest request, HttpServletResponse response) {
		List<ItemBean> lista = null;
		try {			
			ItemBean itemBean = new ItemBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(itemBean, request.getParameterMap());
			lista = generalService.listarAlmacen(itemBean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/listarProductos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProductos(HttpServletRequest request, HttpServletResponse response) {
		List<StockConsultaBean> lista = null;
		try {
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			StockConsultaBean stockConsultaBean = new StockConsultaBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(stockConsultaBean, request.getParameterMap());

			lista = logisticaService.listarConsultaProductos(stockConsultaBean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	/**
	 * @param codigo 
	 * @param request 
	 * @param response
	 * @return Objeto.
	 */
	@RequestMapping(value = "/exportarPdfFecha/{idDdi}/{idAlmacen}/{nomDdi}/{nomAlmacen}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarPdfFecha(@PathVariable("idDdi") Integer idddi, 
								@PathVariable("idAlmacen") Integer idalmacen,
								@PathVariable("nomDdi") String nomDdi,
								@PathVariable("nomAlmacen") String nomAlmacen,
								HttpServletRequest request, HttpServletResponse response) {
	    try {

	        StockConsultaBean stockConsultaBean = new StockConsultaBean();	
	        stockConsultaBean.setIdDdi(idddi);
	        stockConsultaBean.setIdAlmacen(idalmacen);
	        List<StockConsultaBean> lista = logisticaService.listarConsultaProductos(stockConsultaBean);
			
			if (isEmpty(lista)) {
				return Constantes.COD_VALIDACION_GENERAL;
			}			
			StockConsultaBean general = lista.get(0);

			ExportarArchivo printer = new ExportarArchivo();
			StringBuilder jasperFile = new StringBuilder();
			jasperFile.append(getPath(request));
			jasperFile.append(File.separator);
			jasperFile.append(Constantes.REPORT_PATH_ALMACENES);
			jasperFile.append("Consulta_Productos_Fecha.jrxml");
			
			String ruta = getPath(request)+File.separator+Constantes.REPORT_PATH_ALMACENES;

			Map<String, Object> parameters = new HashMap<String, Object>();

			// Agregando los parÃ¡metros del reporte
			StringBuilder logo_indeci_path = new StringBuilder();
			logo_indeci_path.append(getPath(request));
			logo_indeci_path.append(File.separator);
			logo_indeci_path.append(Constantes.IMAGE_INDECI_REPORT_PATH);
			parameters.put("P_LOGO_INDECI", logo_indeci_path.toString());			
			StringBuilder logo_wfp_path = new StringBuilder();
			logo_wfp_path.append(getPath(request));
			logo_wfp_path.append(File.separator);
			logo_wfp_path.append(Constantes.IMAGE_WFP_REPORT_PATH);
			parameters.put("P_LOGO_WFP", logo_wfp_path.toString());			
			StringBuilder logo_check_path = new StringBuilder();
			logo_check_path.append(getPath(request));
			logo_check_path.append(File.separator);
			logo_check_path.append(Constantes.IMAGE_CHECK_REPORT_PATH);
			parameters.put("P_LOGO_CHECK", logo_check_path.toString());			
			StringBuilder logo_check_min_path = new StringBuilder();
			logo_check_min_path.append(getPath(request));
			logo_check_min_path.append(File.separator);
			logo_check_min_path.append(Constantes.IMAGE_CHECK_REPORT_PATH);
			parameters.put("P_LOGO_CHECK_MIN", logo_check_min_path.toString());			
//			parameters.put("P_NRO_ORDEN_INGRESO", general.getNroOrdenSalida());
//			parameters.put("P_DDI", general.getNombreDdi());			
//			parameters.put("P_FECHA_EMISION", general.getFechaEmision());
//			parameters.put("P_ALMACEN_ORIGEN", general.getNomAlmacenOrigen());
//			parameters.put("P_OBSERVACION", general.getObservacion());
//			parameters.put("P_TIPO_MOVIMIENTO", general.getNombreMovimiento());
//			parameters.put("P_ALMACEN_DESTINO", general.getNomAlmacenDestino());
//			parameters.put("LISTA_DOCUMENTOS", ListaDocumentos);
			parameters.put("SR_RUTA_ALIMENTOS", ruta);
			parameters.put("D_NOMBRE_SISTEMA", general.getNombreSistema());
			parameters.put("P_DDI", nomDdi);
			parameters.put("P_ALMACEN", nomAlmacen);
			
			byte[] array = printer.exportPdfSub(jasperFile.toString(), jasperFile.toString(),parameters, lista);
			InputStream input = new ByteArrayInputStream(array);
	        
	        String file_name = "Consulta_Fecha_Vencimiento";
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
	    	LOGGER.info("PDF listo");
	    	return Constantes.COD_EXITO_GENERAL;
	    	
	    } catch (Exception e) {
	    	LOGGER.error(e.getMessage(), e);
	    	return Constantes.COD_ERROR_GENERAL;
	    } 
	}
	

}
