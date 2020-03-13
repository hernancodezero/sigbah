package pe.com.sigbah.web.controller.gestion_almacenes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
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

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pe.com.sigbah.common.bean.CierreStockBean;
import pe.com.sigbah.common.bean.ControlCalidadBean;
import pe.com.sigbah.common.bean.DetalleActaEntregaBean;
import pe.com.sigbah.common.bean.DetalleGuiaRemisionBean;
import pe.com.sigbah.common.bean.DetalleManifiestoCargaBean;
import pe.com.sigbah.common.bean.GuiaRemisionBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.UsuarioBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.ExportarArchivo;
import pe.com.sigbah.common.util.HtmlUtils;
import pe.com.sigbah.common.util.Utils;
import pe.com.sigbah.service.AdministracionService;
import pe.com.sigbah.service.GeneralService;
import pe.com.sigbah.service.LogisticaService;
import pe.com.sigbah.web.controller.common.BaseController;
import pe.com.sigbah.web.report.gestion_almacenes.ReporteGuiaRemision;

/**
 * @className: GuiaRemisionController.java
 * @description: 
 * @date: 17 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
@Controller
@RequestMapping("/gestion-almacenes/guia-remision")
public class GuiaRemisionController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private LogisticaService logisticaService;
	
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
        String ruta="";
		try {
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	if(usuarioBean.getIdAlmacen()==null){
           		model.addAttribute("txt_mensaje", "Usted no tiene almacén de trabajo");
           		ruta = "noAlmacen";
           	}else{
	        	CierreStockBean cierre = new CierreStockBean();
	       		cierre = administracionService.mesTrabajoActivo(usuarioBean.getIdAlmacen(), Constantes.TIPO_ORIGEN_ALMACENES);
	       		if(cierre.getCodigoMes()==null){
           			ruta = "mesNoAbierto";
           		}else{
		   			usuarioBean.setCodigoAnio(cierre.getCodigoAnio());
		   			usuarioBean.setCodigoMes(cierre.getCodigoMes());
		        	
		        	model.addAttribute("lista_anio", generalService.listarAnios());
		        	
		        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
		        	
		        	model.addAttribute("lista_almacen", generalService.listarAlmacen(new ItemBean(usuarioBean.getIdDdi())));
		        	        	
		        	model.addAttribute("lista_tipo_movimiento", generalService.listarTipoMovimiento(new ItemBean(Constantes.TWO_INT, Constantes.TWO_INT)));
		        	
		        	GuiaRemisionBean guiaRemision = new GuiaRemisionBean();
		        	
		        	ControlCalidadBean parametroAlmacenActivo = new ControlCalidadBean();
		    		parametroAlmacenActivo.setIdAlmacen(usuarioBean.getIdAlmacen());
		    		parametroAlmacenActivo.setTipo(Constantes.CODIGO_TIPO_ALMACEN);
		//    		List<ControlCalidadBean> listaAlmacenActivo = logisticaService.listarAlmacenActivo(parametroAlmacenActivo);
		//    		if (!isEmpty(listaAlmacenActivo)) {
		//    			guiaRemision.setCodigoAnio(listaAlmacenActivo.get(0).getCodigoAnio());
		//    			guiaRemision.setIdAlmacen(listaAlmacenActivo.get(0).getIdAlmacen());
		//    			guiaRemision.setCodigoAlmacen(listaAlmacenActivo.get(0).getCodigoAlmacen());
		//    			guiaRemision.setNombreAlmacen(listaAlmacenActivo.get(0).getNombreAlmacen());
		//    			guiaRemision.setCodigoMes(listaAlmacenActivo.get(0).getCodigoMes());
		//    		}
		    		CierreStockBean cierreStock = new CierreStockBean();
		    		cierreStock = administracionService.mesTrabajoActivo(usuarioBean.getIdAlmacen(), Constantes.TIPO_ORIGEN_ALMACENES);
		    		if (cierreStock.getCodigoAnio()!=null) {
		    			guiaRemision.setCodigoAnio(cierreStock.getCodigoAnio());
		    			guiaRemision.setIdAlmacen(cierreStock.getIdAlmacen());
		    			
		    			guiaRemision.setNombreAlmacen(cierreStock.getNombreAlmacen());
		    			guiaRemision.setCodigoMes(cierreStock.getCodigoMes());
		    		}
		    		guiaRemision.setCodigoAlmacen(usuarioBean.getCodigoAlmacen());
		    		model.addAttribute("guiaRemision", getParserObject(guiaRemision));
		    		model.addAttribute("anioActivo", cierre.getCodigoAnio());
		    		model.addAttribute("mesActivo", cierre.getCodigoMes());
		        	model.addAttribute("indicador", indicador);
		        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
		        	ruta="listar_guia_remision";
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
	@RequestMapping(value = "/listarGuiaRemision", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarGuiaRemision(HttpServletRequest request, HttpServletResponse response) {
		List<GuiaRemisionBean> lista = null;
		try {			
			GuiaRemisionBean guiaRemisionBean = new GuiaRemisionBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(guiaRemisionBean, request.getParameterMap());			
			lista = logisticaService.listarGuiaRemision(guiaRemisionBean);
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
	@RequestMapping(value = "/mantenimientoGuiaRemision/{codigo}", method = RequestMethod.GET)
    public String mantenimientoGuiaRemision(@PathVariable("codigo") Integer codigo, Model model) {
        try {
        	GuiaRemisionBean guiaRemision = new GuiaRemisionBean();
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	if (!isNullInteger(codigo)) {        		
        		guiaRemision = logisticaService.obtenerRegistroGuiaRemision(codigo);
        	}
        	
        	model.addAttribute("guiaRemision", getParserObject(guiaRemision));

        	model.addAttribute("lista_estado", generalService.listarEstado(new ItemBean(null, Constantes.FOUR_INT)));
        	
        	model.addAttribute("lista_motivo_traslado", generalService.listarMotivoTraslado());
        	
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "mantenimiento_guia_remision";
    }
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/anularGuiaRemision", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object anularGuiaRemision(HttpServletRequest request, HttpServletResponse response) {
		GuiaRemisionBean guiaRemision = null;
		try {			
			GuiaRemisionBean guiaRemisionBean = new GuiaRemisionBean();
			
			// Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(guiaRemisionBean, request.getParameterMap());

			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	guiaRemisionBean.setUsuarioRegistro(usuarioBean.getUsuario());
        	guiaRemisionBean.setTipoOrigen(Constantes.TIPO_ORIGEN_ALMACENES);
			
        	logisticaService.anularGuiaRemision(guiaRemisionBean);
        	
        	guiaRemisionBean.setIdEstado(Constantes.ESTADO_ACTIVO);
        	guiaRemision = logisticaService.insertarGuiaRemision(guiaRemisionBean);
        	
        	guiaRemision = logisticaService.obtenerRegistroGuiaRemision(guiaRemision.getIdGuiaRemision());
        	guiaRemision.setIdEstado(Constantes.ESTADO_ACTIVO);
        	guiaRemision.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return guiaRemision;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/actualizarGuiaRemision", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object actualizarGuiaRemision(HttpServletRequest request, HttpServletResponse response) {
		GuiaRemisionBean guiaRemision = null;
		try {			
			GuiaRemisionBean guiaRemisionBean = new GuiaRemisionBean();
			
			// Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(guiaRemisionBean, request.getParameterMap());

			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
			guiaRemisionBean.setTipoOrigen(Constantes.TIPO_ORIGEN_ALMACENES);
        	guiaRemisionBean.setUsuarioRegistro(usuarioBean.getUsuario());
			
        	guiaRemision = logisticaService.actualizarGuiaRemision(guiaRemisionBean);
        	guiaRemision.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return guiaRemision;
	}
	
	/**
	 * @param codigoAnio 
	 * @param codigoMes 
	 * @param idAlmacen
	 * @param idMovimiento 
	 * @param response
	 * @return Objeto.
	 */
	@RequestMapping(value = "/exportarExcel/{codigoAnio}/{codigoMes}/{idAlmacen}/{idMovimiento}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcel(@PathVariable("codigoAnio") String codigoAnio, 
								@PathVariable("codigoMes") String codigoMes, 
								@PathVariable("idAlmacen") Integer idAlmacen,
								@PathVariable("idMovimiento") Integer idMovimiento, 
								HttpServletResponse response) {
	    try {
	    	GuiaRemisionBean guiaRemisionBean = new GuiaRemisionBean();
	    	guiaRemisionBean.setCodigoAnio(verificaParametro(codigoAnio));
	    	guiaRemisionBean.setCodigoMes(verificaParametro(codigoMes));
	    	guiaRemisionBean.setIdAlmacen(idAlmacen);
	    	guiaRemisionBean.setIdMovimiento(idMovimiento);
	    	
			List<GuiaRemisionBean> lista = logisticaService.listarGuiaRemision(guiaRemisionBean);
	    	
			String file_name = "GuiaRemision";
			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_XLS);
			
			ReporteGuiaRemision reporte = new ReporteGuiaRemision();
		    HSSFWorkbook wb = reporte.generaReporteExcelGuiaRemision(lista);
			
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
	 * @param ind_gui 
	 * @param ind_man 
	 * @param ind_act 
	 * @param request 
	 * @param response
	 * @return Objeto.
	 */
	@RequestMapping(value = "/exportarPdf/{codigo}/{ind_gui}/{ind_man}/{ind_act}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarPdf(@PathVariable("codigo") Integer codigo,
							  @PathVariable("ind_gui") String ind_gui,
							  @PathVariable("ind_man") String ind_man,
							  @PathVariable("ind_act") String ind_act,
							  HttpServletRequest request, 
							  HttpServletResponse response) {
	    try {
	    	List<DetalleGuiaRemisionBean> listaGuiaRemision = null;
	    	List<DetalleManifiestoCargaBean> listaManifiestoCarga = null;
	    	List<DetalleActaEntregaBean> listaActaEntrega = null;
	    	if (ind_gui.equals(Constantes.ONE_STRING)) {
	    		listaGuiaRemision = logisticaService.listarDetalleGuiaRemision(codigo, Constantes.TIPO_ORIGEN_ALMACENES);
	    		if (isEmpty(listaGuiaRemision)) {
					return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
				}
	    		generarReporteGuiaRemision(listaGuiaRemision, request, response);
	    	} else if (ind_man.equals(Constantes.ONE_STRING)) {
	    		listaManifiestoCarga = logisticaService.listarDetalleManifiestoCarga(codigo, Constantes.TIPO_ORIGEN_ALMACENES);
	    		if (isEmpty(listaManifiestoCarga)) {
					return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
				}
	    		generarReporteManifiestoCarga(listaManifiestoCarga, request, response);
	    	} else if (ind_act.equals(Constantes.ONE_STRING)) {
	    		listaActaEntrega = logisticaService.listarDetalleActaEntrega(codigo, Constantes.TIPO_ORIGEN_ALMACENES);
	    		if (isEmpty(listaActaEntrega)) {
					return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
				}
	    		generarReporteActaEntrega(listaActaEntrega, request, response);
	    	}    		
	    	return Constantes.COD_EXITO_GENERAL;
	    } catch (Exception e) {
	    	LOGGER.error(e.getMessage(), e);
	    	return Constantes.COD_ERROR_GENERAL;
	    } 
	}
	
	/**
	 * @param listaGuiaRemision
	 * @param request 
	 * @param response
	 */
	private void generarReporteGuiaRemision(List<DetalleGuiaRemisionBean> listaGuiaRemision, 
											HttpServletRequest request, 
											HttpServletResponse response) throws Exception {
		
		DetalleGuiaRemisionBean guiaRemision = null;
		if (!Utils.isEmpty(listaGuiaRemision)) {
			guiaRemision = listaGuiaRemision.get(0);
		} else {
			guiaRemision = new DetalleGuiaRemisionBean();
		}

		ExportarArchivo printer = new ExportarArchivo();
		StringBuilder jasperFile = new StringBuilder();
		jasperFile.append(getPath(request));
		jasperFile.append(File.separator);
		jasperFile.append(Constantes.REPORT_PATH_ALMACENES);
		jasperFile.append("Guia_Remision.jrxml");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String ruta = getPath(request)+File.separator+Constantes.PDF_PATH_GUIA_ALM;

		// Agregando los parámetros del reporte
		StringBuilder logo_indeci_path = new StringBuilder();
		logo_indeci_path.append(getPath(request));
		logo_indeci_path.append(File.separator);
		logo_indeci_path.append(Constantes.IMAGE_INDECI2_REPORT_PATH);
		parameters.put("P_LOGO_INDECI", logo_indeci_path.toString());		
		StringBuilder logo_check_path = new StringBuilder();
		logo_check_path.append(getPath(request));
		logo_check_path.append(File.separator);
		logo_check_path.append(Constantes.IMAGE_INPUT_CHECK_REPORT_PATH);
		parameters.put("P_LOGO_CHECK", logo_check_path.toString());
		StringBuilder logo_checked_path = new StringBuilder();
		logo_checked_path.append(getPath(request));
		logo_checked_path.append(File.separator);
		logo_checked_path.append(Constantes.IMAGE_INPUT_CHECKED_REPORT_PATH);
		
		StringBuilder logo_anulado = new StringBuilder();
		logo_anulado.append(getPath(request));
		logo_anulado.append(File.separator);
		logo_anulado.append(Constantes.IMAGE_INPUT_ANULADO_PATH);
		
		StringBuilder logo_noanulado = new StringBuilder();
		logo_noanulado.append(getPath(request));
		logo_noanulado.append(File.separator);
		logo_noanulado.append(Constantes.IMAGE_INPUT_NOANULADO_PATH);
		
		parameters.put("P_LOGO_CHECKED", logo_checked_path.toString());
			
		parameters.put("P_SEDE", getString(guiaRemision.getSede()));
		parameters.put("P_NRO_GUIA_REMISION", guiaRemision.getNroGuiaEmision());			
		parameters.put("P_DIRECCION_PARTIDA", guiaRemision.getDireccionPartida());	
		parameters.put("P_PUNTO_PARTIDA", guiaRemision.getPuntoPartida());	
		parameters.put("P_PUNTO_LLEGADA", guiaRemision.getPuntoLlegada());	
		parameters.put("P_FECHA_EMISION", guiaRemision.getFechaEmision());	
		parameters.put("P_FECHA_INICIO_TRABAJO", guiaRemision.getFechaInicioTraslado());	
		parameters.put("P_RAZON_SOCIAL_DESTINATARIO", guiaRemision.getRazonSocialDestino());	
		parameters.put("P_RUC_DESTINATARIO", guiaRemision.getRucDestino());	
		parameters.put("P_CHOFER", guiaRemision.getNombreChofer());	
		parameters.put("P_NRO_PLACA", guiaRemision.getNroPlaca());	
		parameters.put("P_NRO_LICENCIA_CONDUCIR", guiaRemision.getNroLicenciaConducir());	
		parameters.put("P_RAZON_SOCIAL_TRANSPORTE", guiaRemision.getEmpresaTransporte());	
		parameters.put("P_RUC_TRANSPORTE", guiaRemision.getRucEmpresaTransporte());	
		String idMotivoTraslado = Constantes.EMPTY;
		if (!isNullInteger(guiaRemision.getIdMotivoTraslado())) {
			idMotivoTraslado = getString(guiaRemision.getIdMotivoTraslado());
		}
		parameters.put("P_MOTIVO_TRASLADO", idMotivoTraslado);	
		parameters.put("P_TIPO_MOVIMIENTO", guiaRemision.getTipoMovimiento());	
		parameters.put("P_OBSERVACIONES", guiaRemision.getObservacionGuia());	
		
		parameters.put("P_ESTADO", guiaRemision.getIdEstado().toString());
		parameters.put("P_LOGO_ANULADO", logo_anulado.toString());
		parameters.put("P_LOGO_NOANULADO", logo_noanulado.toString());
		
		parameters.put("P_DIRECCION_FISCAL", guiaRemision.getDireccionFiscal());	
		parameters.put("P_NOMBRE_IMPRENTA", guiaRemision.getNombreImprenta());	
		parameters.put("P_RUC_IMPRENTA", guiaRemision.getRucImprenta());	
		parameters.put("P_NRO_AUTORIZACION", guiaRemision.getNroAutorizacion());	
		parameters.put("P_NRO_SERIE", guiaRemision.getNroSerie());	
		parameters.put("P_FECHA_IMPRESION", guiaRemision.getFechaImpresion());
		parameters.put(JRParameter.REPORT_LOCALE, Locale.US);

//		byte[] array = printer.exportPdf(jasperFile.toString(), parameters, listaGuiaRemision);
//		InputStream input = new ByteArrayInputStream(array);
		
		
    	
    	PDFMergerUtility pdfunir = new PDFMergerUtility();
    	for(int i=1;i<=5;i++){
    		parameters.put("TITULO_GUIA", getMensaje(messageSource, "params.guia.remision"+i));
    		generaArchivoPdf(jasperFile.toString(), parameters, listaGuiaRemision,request, ruta+"guia"+i+".pdf");
    		pdfunir.addSource(ruta+"guia"+i+".pdf");
    	}
    	pdfunir.setDestinationFileName(ruta+"guia_remision.pdf");
        
        try {
        	
				pdfunir.mergeDocuments();
			
        } catch (IOException ioe) {
        	ioe.toString();
        }
        
        FileInputStream input = new FileInputStream(ruta+"guia_remision.pdf");
//      
      String file_name = "Reporte_Guia_Remision";
		file_name = file_name.concat(Constantes.EXTENSION_FORMATO_PDF);
//  	
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
    	
	}
	
	private String generaArchivoPdf(String jasperFile, Map<String, Object> parameters, List<?> dataList, HttpServletRequest request, String ruta1) throws Exception {
		
		File fichero = new File(request.getServletContext().getRealPath("/sigbah_web/resources/pdf/guia/donacion/guia_remision.pdf"));
		
        if(fichero.exists()){
        	System.out.println("fichero1.exists()");
        	fichero.delete();
        }
		
		String ruta="";
		JasperReport report = JasperCompileManager.compileReport(jasperFile);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(dataList));
//        jasperPrint.setProperty("net.sf.jasperreports.export.character.encoding", "ISO-8859-1");
        JasperExportManager.exportReportToPdfFile(jasperPrint,ruta1);
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        JRPdfExporter jRPdfExporter = new JRPdfExporter();
//        jRPdfExporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
//        jRPdfExporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
//        jRPdfExporter.exportReport();
//        byte[] bytes = byteArrayOutputStream.toByteArray(); 
//        jRPdfExporter = null;
        System.out.println("RUTA PDF "+ruta1);
        System.out.println("SE CREO PDF");
		return ruta;
	}
	
	/**
	 * @param listaManifiestoCarga
	 * @param request 
	 * @param response
	 */
	private void generarReporteManifiestoCarga(List<DetalleManifiestoCargaBean> listaManifiestoCarga, 
											   HttpServletRequest request, 
											   HttpServletResponse response) throws Exception {
		
		StringBuilder file_path = new StringBuilder();
    	file_path.append(getPath(request));
    	file_path.append(File.separator);
    	file_path.append(Constantes.UPLOAD_PATH_FILE_TEMP);
    	file_path.append(File.separator);
    	file_path.append(Calendar.getInstance().getTime().getTime());
    	file_path.append(Constantes.EXTENSION_FORMATO_PDF);
    	
    	String file_name = "Manifiesto_Carga";
		file_name = file_name.concat(Constantes.EXTENSION_FORMATO_PDF);
		
		ReporteGuiaRemision reporte = new ReporteGuiaRemision();
		reporte.generaPDFReporteManifiestoCarga(file_path.toString(), listaManifiestoCarga);
		
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
			LOGGER.info("[generarReporteManifiestoCarga] "+file_temp.getName()+" se borra el archivo temporal.");
		} else {
			LOGGER.info("[generarReporteManifiestoCarga] "+file_temp.getName()+" no se logró borrar el archivo temporal.");
		}
	}
	
	/**
	 * @param listaActaEntrega
	 * @param request 
	 * @param response
	 */
	private void generarReporteActaEntrega(List<DetalleActaEntregaBean> listaActaEntrega, 
										   HttpServletRequest request, 
										   HttpServletResponse response) throws Exception {
		
		StringBuilder file_path = new StringBuilder();
    	file_path.append(getPath(request));
    	file_path.append(File.separator);
    	file_path.append(Constantes.UPLOAD_PATH_FILE_TEMP);
    	file_path.append(File.separator);
    	file_path.append(Calendar.getInstance().getTime().getTime());
    	file_path.append(Constantes.EXTENSION_FORMATO_PDF);
    	
    	String file_name = "Acta_Entrega";
		file_name = file_name.concat(Constantes.EXTENSION_FORMATO_PDF);

		ReporteGuiaRemision reporte = new ReporteGuiaRemision();
		reporte.generaPDFReporteActaEntrega(file_path.toString(), listaActaEntrega);
		
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
			LOGGER.info("[generarReporteActaEntrega] "+file_temp.getName()+" se borra el archivo temporal.");
		} else {
			LOGGER.info("[generarReporteActaEntrega] "+file_temp.getName()+" no se logró borrar el archivo temporal.");
		}
	}
	
	@RequestMapping(value = "/obtenerRuta", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object obtenerRuta(HttpServletRequest request, HttpServletResponse response) {
		List<ItemBean> lista = null;
		ItemBean listaMenu = new ItemBean();
		try {
			listaMenu.setDescripcion(getPath(request)+File.separator+Constantes.PDF_PATH_GUIA_ALM+"guia_remision.pdf");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return listaMenu;
	}
	
}
