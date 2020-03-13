package pe.com.sigbah.web.controller.donaciones;

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

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
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
import pe.com.sigbah.common.bean.StockConsultaBean;
import pe.com.sigbah.common.bean.StockProductoKardexBean;
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
 * @className: DonacionesSalidaController.java
 * @description: 
 * @date: 01/09/2017
 * @author: ARCHY.
 */
@Controller
@RequestMapping("/donaciones/consulta")
public class DonacionesConsultaController extends BaseController {

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
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar-consulta-stock";
    }
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarStock", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarDonaciones(HttpServletRequest request, HttpServletResponse response) {
		List<StockConsultaBean> lista = null;
		try {
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			StockConsultaBean stockConsultaBean = new StockConsultaBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(stockConsultaBean, request.getParameterMap());

			lista = donacionService.listarConsultaStock(stockConsultaBean);
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
//           	if(usuarioBean.getIdAlmacen()==null){
//           		model.addAttribute("txt_mensaje", "Usted no tiene almacén de trabajo");
//           		ruta = "noAlmacen";
//           	}else{
//           		
//           		CierreStockBean cierre = new CierreStockBean();
//           		cierre = administracionService.mesTrabajoActivo(usuarioBean.getIdAlmacen(), Constantes.TIPO_ORIGEN_DONACIONES);
//           		System.out.println("TRABAJO : "+cierre.getCodigoMes());
//           		if(cierre.getCodigoMes()==null){
//           			ruta = "mesNoAbierto";
//           		}else{
           			model.addAttribute("lista_anio", generalService.listarAnios());
		        	
		        	model.addAttribute("lista_ddi", generalService.listarDdi(new ItemBean(Constantes.ZERO_INT)));
		        	
		        	model.addAttribute("lista_estado", generalService.listarEstadoDonacion(new ItemBean()));
		        	
//		        	ruta="listar-donaciones-salida";
//           		}
//           	}
		        	HtmlUtils utils = new HtmlUtils();
		        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar-consulta-historial";
    }
	
	@RequestMapping(value = "/listarHistorial", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarHistorial(HttpServletRequest request, HttpServletResponse response) {
		List<DonacionesBean> lista = null;
		try {
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			DonacionesBean donacionesBean = new DonacionesBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(donacionesBean, request.getParameterMap());

			lista = donacionService.listarHistorialDonaciones(donacionesBean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	
	@RequestMapping(value = "/inicioProductos", method = RequestMethod.GET)
    public String inicioProductos(Model model) {
		String ruta="";
        try {       
        	// Retorno los datos de session
           	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

   			model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	model.addAttribute("lista_ddi", generalService.listarDdi(new ItemBean(Constantes.ZERO_INT)));
        	
        	model.addAttribute("lista_estado", generalService.listarEstadoDonacion(new ItemBean()));
//		        	
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar-consulta-productos";
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

			lista = donacionService.listarConsultaProductos(stockConsultaBean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/inicioResoluciones", method = RequestMethod.GET)
    public String inicioResoluciones(Model model) {
		String ruta="";
        try {       
        	// Retorno los datos de session
           	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

   			model.addAttribute("lista_anio", generalService.listarAnios());
   			HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar-consulta-resoluciones";
    }
	
	@RequestMapping(value = "/listarResoluciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarResoluciones(HttpServletRequest request, HttpServletResponse response) {
		List<DonacionesBean> lista = null;
		try {
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			DonacionesBean donacionesBean = new DonacionesBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(donacionesBean, request.getParameterMap());

			lista = donacionService.listarConsultaResoluciones(donacionesBean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/inicioKardex", method = RequestMethod.GET)
    public String inicioKardex(Model model) {
		String ruta="";
        try {       
        	// Retorno los datos de session
           	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
		        	
//        	model.addAttribute("lista_ddi", generalService.listarDdi(new ItemBean(Constantes.ZERO_INT)));
        	model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	StockProductoKardexBean stockProductoKardexBean = new StockProductoKardexBean();
        	stockProductoKardexBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);
        	stockProductoKardexBean.setIdDdi(usuarioBean.getIdDdi());
        	stockProductoKardexBean.setIdAlmacen(usuarioBean.getIdAlmacen());
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        	model.addAttribute("lista_producto", logisticaService.listarStockProductoKardex(stockProductoKardexBean));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar-consulta-kardex";
    }
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarKardex", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarKardex(HttpServletRequest request, HttpServletResponse response) {
		List<ProductoDonacionBean> lista = null;
		try {
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			ProductoDonacionBean productoDonacionBean = new ProductoDonacionBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(productoDonacionBean, request.getParameterMap());
			productoDonacionBean.setTipoOrigen("D");
			productoDonacionBean.setIdAlmacen(usuarioBean.getIdAlmacen());
			lista = donacionService.listarConsultaKardex(productoDonacionBean);
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
	@RequestMapping(value = "/exportarPdf/{idDdi}/{idAlmacen}/{nomDdi}/{nomAlmacen}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarPdf(@PathVariable("idDdi") Integer idddi, 
								@PathVariable("idAlmacen") Integer idalmacen,
								@PathVariable("nomDdi") String nomDdi,
								@PathVariable("nomAlmacen") String nomAlmacen,
								HttpServletRequest request, HttpServletResponse response) {
	    try {
	    	StockConsultaBean stock = new StockConsultaBean();
	    	stock.setIdDdi(idddi);
	    	stock.setIdAlmacen(idalmacen);
	    	String titulo="";
	    	Date ahora = new Date();
	        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
	        String fecha = formateador.format(ahora);
	    	if(idddi==0 && idalmacen==0){
	    		
	    		titulo="Stock al "+fecha+" A nivel Nacional";
	    	}else if(idddi!=0 && idalmacen==0){
	    		titulo="Stock al "+fecha+" de la DDI: "+nomDdi;
	    		
	    	}else if(idddi!=0 && idalmacen!=0){
	    		titulo="Stock al "+fecha+" de la DDI: "+nomDdi+" y Almacén: "+nomAlmacen;
	    		
	    	}
	    	
			List<StockConsultaBean> listaAlimentos = donacionService.listarReporteStockAlimentos(stock);
			List<StockConsultaBean> listaBNA = donacionService.listarReporteStockBNA(stock);
			StockConsultaBean nomSistema = donacionService.listarReporteStockTitulo(stock);
			
			if(isEmpty(listaAlimentos)){
				StockConsultaBean data = new StockConsultaBean();
				data.setNombreCategoria("-");
				data.setNombreProducto("-");
				data.setCantidad(null);	
				data.setImporteTotal(null);	
				listaAlimentos.add(data);
			}
			
			if(isEmpty(listaBNA)){
				StockConsultaBean data = new StockConsultaBean();
				data.setNombreCategoria("-");
				data.setNombreProducto("-");
				data.setCantidad(null);	
				data.setImporteTotal(null);	

				listaBNA.add(data);
			}
			
			if (isEmpty(listaAlimentos) || isEmpty(listaBNA)) {
				return Constantes.COD_VALIDACION_GENERAL;
			}			
//			DonacionesSalidaBean general = lista.get(0);

			ExportarArchivo printer = new ExportarArchivo();
			StringBuilder jasperFile = new StringBuilder();
			jasperFile.append(getPath(request));
			jasperFile.append(File.separator);
			jasperFile.append(Constantes.REPORT_PATH_DONACIONES);
			jasperFile.append("Consulta_Stock.jrxml");
			
			StringBuilder jasperFileAl = new StringBuilder();
			jasperFileAl.append(getPath(request));
			jasperFileAl.append(File.separator);
			jasperFileAl.append(Constantes.REPORT_PATH_DONACIONES);
			jasperFileAl.append("SR_Consulta_Alimentos.jrxml");
			
			StringBuilder jasperFileNo = new StringBuilder();
			jasperFileNo.append(getPath(request));
			jasperFileNo.append(File.separator);
			jasperFileNo.append(Constantes.REPORT_PATH_DONACIONES);
			jasperFileNo.append("SR_Consulta_BNA.jrxml");
			
			String ruta = getPath(request)+File.separator+Constantes.REPORT_PATH_DONACIONES;
//			if (producto.getFlagTipoProducto().equals(Constantes.ONE_STRING)) {
//				jasperFile.append("Control_Calidad_Alimentaria.jrxml");
//			} else {
//				jasperFile.append("Control_Calidad_No_Alimentaria.jrxml");
//			}
			
			JRBeanCollectionDataSource ListaAli = new JRBeanCollectionDataSource(listaAlimentos);
			
			JRBeanCollectionDataSource ListaBna = new JRBeanCollectionDataSource(listaBNA);
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
			parameters.put("LISTA_BNA", ListaBna);
			parameters.put("LISTA_ALIMENTOS", ListaAli);
			parameters.put("TITULO", titulo);
			
			byte[] array = printer.exportPdfSub2(jasperFile.toString(), jasperFileAl.toString(),jasperFileNo.toString(),parameters, listaAlimentos);
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
	 *  @param idDonacion
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
@RequestMapping(value = "/exportarHistorialPdf/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object exportarHistorialPdf(@PathVariable("codigo") Integer idDonacion, 
								HttpServletRequest request, HttpServletResponse response) {
		ItemBean paraPdf = null;
	    try {
	    	ItemBean paraPdf1 = new ItemBean();
	    	System.out.println("IDDONACION: "+idDonacion);
	    	BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(paraPdf1, request.getParameterMap());
	    
	    	List<DonacionesBean> listaGeneral = donacionService.listarReporteHistorialGeneral(idDonacion);
	    	List<DonacionesBean> listaDonante = donacionService.listarReporteHistorialDonante(idDonacion);
	    	List<ProductoDonacionBean> listaProducto = donacionService.listarReporteHistorialProducto(idDonacion);
	    	List<ProductoDonacionBean> listaProductoIngSal = donacionService.listarReporteHistorialProductoIngSal(idDonacion);
			List<DocumentoDonacionBean> listaDocumento = donacionService.listarReporteHistorialDocumento(idDonacion);
			List<ProductoDonacionBean> listaProductoIndeci = donacionService.listarReporteHistorialProductoIndeci(idDonacion);
			List<ProductoDonacionBean> listaProductoEntidad = donacionService.listarReporteHistorialProductoEntidades(idDonacion);
			ItemBean nomSistema = donacionService.listarReporteHistorialCabecera(idDonacion);
			
			System.out.println("GENERAL "+listaGeneral.size());
			System.out.println("Donante "+listaDonante.size());
			System.out.println("listaPRoducto "+listaProducto.size());
			System.out.println("listaProductoIngSal "+listaProductoIngSal.size());
			System.out.println("listaDocumento "+listaDocumento.size());
			System.out.println("listaProductoIndeci "+listaProductoIndeci.size());
			System.out.println("listaProductoEntidad "+listaProductoEntidad.size());
			
			
			if (isEmpty(listaGeneral) || isEmpty(listaDonante)) {
				return Constantes.COD_VALIDACION_GENERAL;
			}			
			DonacionesBean general = listaGeneral.get(0);
			DonacionesBean donante = listaDonante.get(0);

			ExportarArchivo printer = new ExportarArchivo();
			StringBuilder jasperFile = new StringBuilder();
			jasperFile.append(getPath(request));
			jasperFile.append(File.separator);
			jasperFile.append(Constantes.REPORT_PATH_DONACIONES);
			jasperFile.append("Consulta_Historial.jrxml");
			
			StringBuilder jasperFilePro = new StringBuilder();
			jasperFilePro.append(getPath(request));
			jasperFilePro.append(File.separator);
			jasperFilePro.append(Constantes.REPORT_PATH_DONACIONES);
			jasperFilePro.append("SR_Consulta_Historial_Pro.jrxml");
			
			StringBuilder jasperFileIngSal = new StringBuilder();
			jasperFileIngSal.append(getPath(request));
			jasperFileIngSal.append(File.separator);
			jasperFileIngSal.append(Constantes.REPORT_PATH_DONACIONES);
			jasperFileIngSal.append("SR_Consulta_Historial_IngSal.jrxml");
			
			StringBuilder jasperFileInd = new StringBuilder();
			jasperFileInd.append(getPath(request));
			jasperFileInd.append(File.separator);
			jasperFileInd.append(Constantes.REPORT_PATH_DONACIONES);
			jasperFileInd.append("SR_Consulta_Historial_Ind.jrxml");
			
			StringBuilder jasperFileEnt = new StringBuilder();
			jasperFileEnt.append(getPath(request));
			jasperFileEnt.append(File.separator);
			jasperFileEnt.append(Constantes.REPORT_PATH_DONACIONES);
			jasperFileEnt.append("SR_Consulta_Historial_Ent.jrxml");
			
			StringBuilder jasperFileDoc = new StringBuilder();
			jasperFileDoc.append(getPath(request));
			jasperFileDoc.append(File.separator);
			jasperFileDoc.append(Constantes.REPORT_PATH_DONACIONES);
			jasperFileDoc.append("SR_Consulta_Historial_Doc.jrxml");
			
			String ruta = getPath(request)+File.separator+Constantes.REPORT_PATH_DONACIONES;
			
			String rutaCrear = getPath(request)+File.separator+Constantes.PDF_PATH_HIS_DON;
//			if (producto.getFlagTipoProducto().equals(Constantes.ONE_STRING)) {
//				jasperFile.append("Control_Calidad_Alimentaria.jrxml");
//			} else {
//				jasperFile.append("Control_Calidad_No_Alimentaria.jrxml");
//			}
		
			JRBeanCollectionDataSource ListaPro = new JRBeanCollectionDataSource(listaProducto);
			JRBeanCollectionDataSource ListaProIngSal = new JRBeanCollectionDataSource(listaProductoIngSal);
			JRBeanCollectionDataSource ListaDoc = new JRBeanCollectionDataSource(listaDocumento);
			JRBeanCollectionDataSource ListaProInd = new JRBeanCollectionDataSource(listaProductoIndeci);
			JRBeanCollectionDataSource ListaProEnt = new JRBeanCollectionDataSource(listaProductoEntidad);
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
			//GENERAL
			parameters.put("P_NOMBRE_SISTEMA", nomSistema.getDescripcion());
			
			parameters.put("P_CODIGO_DONACION", general.getCodigoDonacion());
			parameters.put("P_ANIO", general.getCodigoAnio());			
			parameters.put("P_DDI", general.getNombreDdi());
			parameters.put("P_FECHA", general.getFechaEmision());
			parameters.put("P_ESTADO", general.getNombreEstado());
			parameters.put("P_NRO_DEE", general.getNroDee());
			parameters.put("P_TIPO_DONACION", general.getTipoDonacion());
			//DONANTE
			parameters.put("P_PROCEDENCIA_DONACION", donante.getNombreOrigen());
			parameters.put("P_PAIS_PROCEDENCIA", donante.getNombrePais());
			parameters.put("P_TIPO_PERSONA", donante.getTipoDonante());
			parameters.put("P_DONANTE", donante.getNombreDonante());
			parameters.put("P_REPRESENTANTE", donante.getRepresentante());
			parameters.put("P_FINALIDAD", donante.getFinalidad());
			//Productos
			parameters.put("LISTA_PRODUCTOS", ListaPro);
			parameters.put("SR_RUTA_PRODUCTOS", ruta);
			
			parameters.put("LISTA_PRODUCTOS_IND", ListaProInd);
			parameters.put("LISTA_PRODUCTOS_ENT", ListaProEnt);
			parameters.put("LISTA_PRODUCTOS_INGSAL", ListaProIngSal);
			parameters.put("LISTA_DOCUMENTOS", ListaDoc);

			byte[] array = printer.exportPdfHistorialSub(jasperFile.toString(), jasperFilePro.toString(),jasperFileDoc.toString(), jasperFileEnt.toString(),jasperFileInd.toString(),jasperFileIngSal.toString(),parameters, listaGeneral,rutaCrear+"Consulta_Historial.pdf");
//			generaArchivoPdf(jasperFile.toString(), parameters, listaGeneral,request, rutaCrear+"Consulta_Historial.pdf");
			InputStream input = new ByteArrayInputStream(array);
	        
	        String file_name = "Consulta_Historial";
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
	    	LOGGER.info("PDF listoDescarga");
	    	
	    	
//	    	paraPdf1.setDescripcion(Constantes.COD_EXITO_GENERAL);
//	    	paraPdf1.setUrl(file_name);
//	    	paraPdf=paraPdf1;
	    	return Constantes.COD_EXITO_GENERAL;
//	    	return paraPdf;
	    	
	    } catch (Exception e) {
	    	LOGGER.error(e.getMessage(), e);
//	    	paraPdf.setDescripcion(Constantes.COD_ERROR_GENERAL);
	    	return Constantes.COD_ERROR_GENERAL;
//	    	return paraPdf;
	    } 
	}

	private String generaArchivoPdf(String jasperFile, Map<String, Object> parameters, List<?> dataList, HttpServletRequest request, String ruta1) throws Exception {
		String ruta="";
		JasperReport report = JasperCompileManager.compileReport(jasperFile);
	    JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(dataList));
//	    jasperPrint.setProperty("net.sf.jasperreports.export.character.encoding", "ISO-8859-1");
	    JasperExportManager.exportReportToPdfFile(jasperPrint,ruta1);
	    System.out.println("RUTA PDF "+ruta1);
	    System.out.println("SE CREO PDF");
		return ruta;
	}

	@RequestMapping(value = "/obtenerRuta", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object obtenerRuta(HttpServletRequest request, HttpServletResponse response) {
		List<ItemBean> lista = null;
		ItemBean listaMenu = new ItemBean();
		try {
			request.getServletContext().getRealPath("/");
			System.out.println("RUTA: "+getPath(request)+File.separator+Constantes.PDF_PATH_HIS_DON+"Consulta_Historial.pdf");
			listaMenu.setDescripcion(getPath(request)+File.separator+Constantes.PDF_PATH_HIS_DON+"Consulta_Historial.pdf");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return listaMenu;
	}
	
	@RequestMapping(value = "/exportarPdfKardex/{codigoAnio}/{codigoMes}/{idProducto}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object exportarPdfKardex(@PathVariable("codigoAnio") String codigoAnio,
			@PathVariable("codigoMes") String codigoMes,
			@PathVariable("idProducto") Integer idProducto,
								HttpServletRequest request, HttpServletResponse response) {
		ItemBean paraPdf = null;
	    try {
	    	ItemBean paraPdf1 = new ItemBean();

	    	BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(paraPdf1, request.getParameterMap());
			
	    	List<ProductoDonacionBean> listaProducto = null;
	    	ProductoDonacionBean productoDonacionBean = new ProductoDonacionBean();
	    	productoDonacionBean.setCodigoAnio(codigoAnio);
	    	productoDonacionBean.setCodigoMes(codigoMes);
	    	productoDonacionBean.setIdProducto(idProducto);
			productoDonacionBean.setTipoOrigen("D");
			productoDonacionBean.setIdAlmacen(usuarioBean.getIdAlmacen());
			listaProducto = donacionService.listarConsultaKardex(productoDonacionBean);
	    
//	    	List<DonacionesBean> listaGeneral = donacionService.listarReporteHistorialGeneral(idDonacion);
//	    	List<DonacionesBean> listaDonante = donacionService.listarReporteHistorialDonante(idDonacion);
//	    	List<ProductoDonacionBean> listaProducto = donacionService.listarReporteHistorialProducto(idDonacion);
//	    	List<ProductoDonacionBean> listaProductoIngSal = donacionService.listarReporteHistorialProductoIngSal(idDonacion);
//			List<DocumentoDonacionBean> listaDocumento = donacionService.listarReporteHistorialDocumento(idDonacion);
//			List<ProductoDonacionBean> listaProductoIndeci = donacionService.listarReporteHistorialProductoIndeci(idDonacion);
//			List<ProductoDonacionBean> listaProductoEntidad = donacionService.listarReporteHistorialProductoEntidades(idDonacion);
//			ItemBean nomSistema = donacionService.listarReporteHistorialCabecera(idDonacion);
//			
//			System.out.println("GENERAL "+listaGeneral.size());
//			System.out.println("Donante "+listaDonante.size());
//			System.out.println("listaPRoducto "+listaProducto.size());
//			System.out.println("listaProductoIngSal "+listaProductoIngSal.size());
//			System.out.println("listaDocumento "+listaDocumento.size());
//			System.out.println("listaProductoIndeci "+listaProductoIndeci.size());
//			System.out.println("listaProductoEntidad "+listaProductoEntidad.size());
			
			
			if (isEmpty(listaProducto)) {
				return Constantes.COD_VALIDACION_GENERAL;
			}			
			ProductoDonacionBean general = listaProducto.get(0);
	
			ExportarArchivo printer = new ExportarArchivo();
			StringBuilder jasperFile = new StringBuilder();
			jasperFile.append(getPath(request));
			jasperFile.append(File.separator);
			jasperFile.append(Constantes.REPORT_PATH_DONACIONES);
			jasperFile.append("Consulta_Kardex.jrxml");
			
			String ruta = getPath(request)+File.separator+Constantes.REPORT_PATH_DONACIONES;
//			if (producto.getFlagTipoProducto().equals(Constantes.ONE_STRING)) {
//				jasperFile.append("Control_Calidad_Alimentaria.jrxml");
//			} else {
//				jasperFile.append("Control_Calidad_No_Alimentaria.jrxml");
//			}
			

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
			parameters.put("P_NRO_KARDEX", general.getNroKardex());
			parameters.put("P_ANIO", general.getCodigoAnio());
			parameters.put("P_MES", general.getNombreMes());
			parameters.put("P_ALMACEN", general.getNombreAlmacen());
			parameters.put("P_PRODUCTO", general.getNombreProducto());
			parameters.put("P_UNIDAD_MEDIDA", general.getUnidadMedida());
			parameters.put("P_NOMBRE_SISTEMA", general.getNombreSistema());
			
			byte[] array = printer.exportPdf(jasperFile.toString(), parameters, listaProducto);
			InputStream input = new ByteArrayInputStream(array);
	        
	        String file_name = "Consulta_Kardex";
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
//	    	paraPdf.setDescripcion(Constantes.COD_ERROR_GENERAL);
	    	return Constantes.COD_ERROR_GENERAL;
//	    	return paraPdf;
	    } 
	}
	
	@RequestMapping(value = "/exportarPdfResolucion/{anio}/{tipo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object exportarPdfResolucion(@PathVariable("anio") String anio,
			@PathVariable("tipo") String tipo,
								HttpServletRequest request, HttpServletResponse response) {
		List<DonacionesBean> lista = null;
	    try {
	    	
	    	DonacionesBean donacionesBean = new DonacionesBean();
	    	BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
	    	donacionesBean.setCodigoAnio(anio);
	    	donacionesBean.setTipoDonacion(tipo);
			lista = donacionService.listarConsultaResoluciones(donacionesBean);
	    System.out.println("TAMANIO "+lista.size());
//	    	List<DonacionesBean> listaGeneral = donacionService.listarReporteHistorialGeneral(idDonacion);
//	    	List<DonacionesBean> listaDonante = donacionService.listarReporteHistorialDonante(idDonacion);
//	    	List<ProductoDonacionBean> listaProducto = donacionService.listarReporteHistorialProducto(idDonacion);
//	    	List<ProductoDonacionBean> listaProductoIngSal = donacionService.listarReporteHistorialProductoIngSal(idDonacion);
//			List<DocumentoDonacionBean> listaDocumento = donacionService.listarReporteHistorialDocumento(idDonacion);
//			List<ProductoDonacionBean> listaProductoIndeci = donacionService.listarReporteHistorialProductoIndeci(idDonacion);
//			List<ProductoDonacionBean> listaProductoEntidad = donacionService.listarReporteHistorialProductoEntidades(idDonacion);
//			ItemBean nomSistema = donacionService.listarReporteHistorialCabecera(idDonacion);
//			
//			System.out.println("GENERAL "+listaGeneral.size());
//			System.out.println("Donante "+listaDonante.size());
//			System.out.println("listaPRoducto "+listaProducto.size());
//			System.out.println("listaProductoIngSal "+listaProductoIngSal.size());
//			System.out.println("listaDocumento "+listaDocumento.size());
//			System.out.println("listaProductoIndeci "+listaProductoIndeci.size());
//			System.out.println("listaProductoEntidad "+listaProductoEntidad.size());
			
			
			if (isEmpty(lista)) {
				return Constantes.COD_VALIDACION_GENERAL;
			}			
			
	
			ExportarArchivo printer = new ExportarArchivo();
			StringBuilder jasperFile = new StringBuilder();
			jasperFile.append(getPath(request));
			jasperFile.append(File.separator);
			jasperFile.append(Constantes.REPORT_PATH_DONACIONES);
			jasperFile.append("Consulta_Resolucion.jrxml");
			
			String ruta = getPath(request)+File.separator+Constantes.REPORT_PATH_DONACIONES;
//			if (producto.getFlagTipoProducto().equals(Constantes.ONE_STRING)) {
//				jasperFile.append("Control_Calidad_Alimentaria.jrxml");
//			} else {
//				jasperFile.append("Control_Calidad_No_Alimentaria.jrxml");
//			}
			

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
			
//			parameters.put("D_NOMBRE_SISTEMA", general.getNombreSistema());
//			parameters.put("NOMBRE_SISTEMA", nomSistema.getNombreSistema());
//			parameters.put("LISTA_BNA", ListaBna);
			String origen="";
			if(tipo.equals("1")){
				origen="Nacional";
			}else{
				origen="Internacional";
			}
			parameters.put("D_ORIGEN", origen);
			parameters.put("D_ANIO", anio);
			
			byte[] array = printer.exportPdf(jasperFile.toString(), parameters, lista);
			InputStream input = new ByteArrayInputStream(array);
	        
	        String file_name = "Consulta_Resolucion";
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
//	    	paraPdf.setDescripcion(Constantes.COD_ERROR_GENERAL);
	    	return Constantes.COD_ERROR_GENERAL;
//	    	return paraPdf;
	    } 
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
	        List<StockConsultaBean> lista = donacionService.listarConsultaProductos(stockConsultaBean);
			
			if (isEmpty(lista)) {
				return Constantes.COD_VALIDACION_GENERAL;
			}			
			StockConsultaBean general = lista.get(0);

			ExportarArchivo printer = new ExportarArchivo();
			StringBuilder jasperFile = new StringBuilder();
			jasperFile.append(getPath(request));
			jasperFile.append(File.separator);
			jasperFile.append(Constantes.REPORT_PATH_DONACIONES);
			jasperFile.append("Consulta_Productos_Fecha.jrxml");
			
			String ruta = getPath(request)+File.separator+Constantes.REPORT_PATH_DONACIONES;

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
	

}
