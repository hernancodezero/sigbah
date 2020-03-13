package pe.com.sigbah.web.controller.donaciones;

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
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import pe.com.sigbah.common.bean.CierreStockBean;
import pe.com.sigbah.common.bean.ControlCalidadBean;
import pe.com.sigbah.common.bean.DetalleActaEntregaBean;
import pe.com.sigbah.common.bean.DetalleGuiaRemisionBean;
import pe.com.sigbah.common.bean.DetalleManifiestoCargaBean;
import pe.com.sigbah.common.bean.GuiaRemisionBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.RolMenuBean;
import pe.com.sigbah.common.bean.UsuarioBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.ExportarArchivo;
import pe.com.sigbah.common.util.HtmlUtils;
import pe.com.sigbah.common.util.Utils;
import pe.com.sigbah.service.AdministracionService;
import pe.com.sigbah.service.DonacionService;
import pe.com.sigbah.service.GeneralService;
import pe.com.sigbah.service.LogisticaService;
import pe.com.sigbah.web.controller.common.BaseController;
import pe.com.sigbah.web.report.gestion_almacenes.ReporteGuiaRemision;

/**
 * @className: GuiaRemisionController.java
 * @description: 
 * @date: 08 de Ago. de 2017
 * @author: ARCHY.
 */
@Controller
@RequestMapping("/donaciones/guia-remision")
public class DonacionesGuiaRemisionController extends BaseController {

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
        try {
        	System.out.println("ENTRO INICIO");
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	
        	model.addAttribute("lista_almacen", generalService.listarAlmacen(new ItemBean(usuarioBean.getIdDdi())));
        	        	
        	model.addAttribute("lista_tipo_movimiento", generalService.listarTipoMovimiento(new ItemBean(Constantes.TWO_INT, Constantes.TWO_INT)));
        	
        	GuiaRemisionBean guiaRemision = new GuiaRemisionBean();
        	guiaRemision.setCodigoAnio(generalService.obtenerAnioActual());
//        	ControlCalidadBean parametroAlmacenActivo = new ControlCalidadBean();
//    		parametroAlmacenActivo.setIdAlmacen(usuarioBean.getIdAlmacen());
//    		parametroAlmacenActivo.setTipo(Constantes.CODIGO_TIPO_ALMACEN);
//    		List<ControlCalidadBean> listaAlmacenActivo = logisticaService.listarAlmacenActivo(parametroAlmacenActivo);
//    		if (!isEmpty(listaAlmacenActivo)) {
//    			guiaRemision.setCodigoAnio(listaAlmacenActivo.get(0).getCodigoAnio());
//    			guiaRemision.setIdAlmacen(listaAlmacenActivo.get(0).getIdAlmacen());
//    			guiaRemision.setCodigoAlmacen(listaAlmacenActivo.get(0).getCodigoAlmacen());
//    			guiaRemision.setNombreAlmacen(listaAlmacenActivo.get(0).getNombreAlmacen());
//    			guiaRemision.setCodigoMes(listaAlmacenActivo.get(0).getCodigoMes());
//    		}
    		
    		model.addAttribute("guiaRemision", getParserObject(guiaRemision));
    		CierreStockBean cierre = new CierreStockBean();
       		cierre = administracionService.mesTrabajoActivo(usuarioBean.getIdAlmacen(), Constantes.TIPO_ORIGEN_DONACIONES);
    		model.addAttribute("mes_anio", cierre.getNombreMes()+" "+cierre.getCodigoAnio());
        	model.addAttribute("indicador", indicador);
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar-donaciones-guia";
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
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(guiaRemisionBean, request.getParameterMap());	

			guiaRemisionBean.setIdAlmacen(usuarioBean.getIdAlmacen());
			lista = donacionService.listarGuiaRemision(guiaRemisionBean);
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
        		guiaRemision = donacionService.obtenerRegistroGuiaRemision(codigo);
        	}
        	CierreStockBean cierre = new CierreStockBean();
       		cierre = administracionService.mesTrabajoActivo(usuarioBean.getIdAlmacen(), Constantes.TIPO_ORIGEN_DONACIONES);
    		model.addAttribute("mes_anio", cierre.getNombreMes()+" "+cierre.getCodigoAnio());
        	
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
        return "mantenimiento-guia-remision-donacion";
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
        	
			guiaRemisionBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);
        	guiaRemisionBean.setUsuarioRegistro(usuarioBean.getUsuario());
			
        	guiaRemision = donacionService.actualizarGuiaRemision(guiaRemisionBean);
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
	@RequestMapping(value = "/exportarExcel/{codigoAnio}/{codigoMes}/{idMovimiento}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcel(@PathVariable("codigoAnio") String codigoAnio, 
								@PathVariable("codigoMes") String codigoMes, 
								@PathVariable("idMovimiento") Integer idMovimiento, 
								HttpServletResponse response) {
	    try {
	    	System.out.println("ENTRO A DESCARGAR");
	    	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
	    	GuiaRemisionBean guiaRemisionBean = new GuiaRemisionBean();
	    	guiaRemisionBean.setCodigoAnio(verificaParametro(codigoAnio));
	    	guiaRemisionBean.setCodigoMes(verificaParametro(codigoMes));
	    	guiaRemisionBean.setIdAlmacen(usuarioBean.getIdAlmacen());
	    	guiaRemisionBean.setIdMovimiento(idMovimiento);
	    	
			List<GuiaRemisionBean> lista = donacionService.listarGuiaRemision(guiaRemisionBean);
	    	
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
	    	List<DetalleGuiaRemisionBean> listaGuiaRemisionCabecera = null;
	    	List<DetalleGuiaRemisionBean> listaGuiaRemisionDetalle = null;
	    	DetalleManifiestoCargaBean listaManifiestoCarga = null;
	    	List<DetalleActaEntregaBean> listaActaEntrega = null;
	    	DetalleActaEntregaBean listaActa = null;
	    	if (ind_gui.equals(Constantes.ONE_STRING)) {
	    		listaGuiaRemisionCabecera = donacionService.listarDetalleGuiaRemisionCabecera(codigo, Constantes.TIPO_ORIGEN_DONACIONES);
	    		listaGuiaRemisionDetalle = donacionService.listarDetalleGuiaRemisionDetalle(codigo, Constantes.TIPO_ORIGEN_DONACIONES);
	    		
	    		if (isEmpty(listaGuiaRemisionCabecera) ) {
					return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
				}
	    		generarReporteGuiaRemision(listaGuiaRemisionCabecera,listaGuiaRemisionDetalle, request, response);
	    	} else if (ind_man.equals(Constantes.ONE_STRING)) {
//	    		listaManifiestoCarga = logisticaService.listarDetalleManifiestoCarga(codigo, Constantes.TIPO_ORIGEN_DONACIONES);
	    		listaManifiestoCarga = donacionService.listarDetalleManifiestoCargaDon(codigo, Constantes.TIPO_ORIGEN_DONACIONES);
	    		
	    		if (isEmpty(listaManifiestoCarga.getLstCabecera())) {
					return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
				}
	    		generarReporteManifiestoCarga(listaManifiestoCarga, request, response);
	    	} else if (ind_act.equals(Constantes.ONE_STRING)) {
//	    		listaActaEntrega = logisticaService.listarDetalleActaEntrega(codigo, Constantes.TIPO_ORIGEN_DONACIONES);
	    		listaActa = donacionService.reporteActaEntrega(codigo, Constantes.TIPO_ORIGEN_DONACIONES);
	    		if (isEmpty(listaActa.getLstDetalle())) {
					return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
				}
	    		generarReporteActaEntrega(listaActa, request, response);
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
	private void generarReporteGuiaRemision(List<DetalleGuiaRemisionBean> listaGuiaRemisionCabecera,
											List<DetalleGuiaRemisionBean> listaGuiaRemisionDetalle,
											HttpServletRequest request, 
											HttpServletResponse response) throws Exception {
		
		DetalleGuiaRemisionBean guiaRemision = null;
		if (!Utils.isEmpty(listaGuiaRemisionCabecera)) {
			guiaRemision = listaGuiaRemisionCabecera.get(0);
		} else {
			guiaRemision = new DetalleGuiaRemisionBean();
		}

		ExportarArchivo printer = new ExportarArchivo();
		StringBuilder jasperFile = new StringBuilder();
		jasperFile.append(getPath(request));
		jasperFile.append(File.separator);
		jasperFile.append(Constantes.REPORT_PATH_DONACIONES);
		jasperFile.append("Guia_Remision.jrxml");
		
		String ruta = getPath(request)+File.separator+Constantes.PDF_PATH_GUIA_DON;
		
		Map<String, Object> parameters = new HashMap<String, Object>();

		// Agregando los parámetros del reporte
		StringBuilder logo_anulado = new StringBuilder();
		logo_anulado.append(getPath(request));
		logo_anulado.append(File.separator);
		logo_anulado.append(Constantes.IMAGE_INPUT_ANULADO_PATH);
		
		StringBuilder logo_noanulado = new StringBuilder();
		logo_noanulado.append(getPath(request));
		logo_noanulado.append(File.separator);
		logo_noanulado.append(Constantes.IMAGE_INPUT_NOANULADO_PATH);
		
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
		parameters.put("P_LOGO_CHECKED", logo_checked_path.toString());
			
		parameters.put("P_SEDE", getString(guiaRemision.getSede()));
		parameters.put("P_NRO_GUIA_REMISION", guiaRemision.getNroGuiaEmision());			
		parameters.put("P_DIRECCION_PARTIDA", guiaRemision.getDireccion());	
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
		
		parameters.put("P_DIRECCION_FISCAL", guiaRemision.getDireccionFiscal());	
		parameters.put("P_NOMBRE_IMPRENTA", guiaRemision.getNombreImprenta());	
		parameters.put("P_RUC_IMPRENTA", guiaRemision.getRucImprenta());	
		parameters.put("P_NRO_AUTORIZACION", guiaRemision.getNroAutorizacion());	
		parameters.put("P_NRO_SERIE", guiaRemision.getNroSerie());	
		parameters.put("P_FECHA_IMPRESION", guiaRemision.getFechaImpresion());	
		
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
		
		parameters.put(JRParameter.REPORT_LOCALE, Locale.US);

//		byte[] array = printer.exportPdf(jasperFile.toString(), parameters, listaGuiaRemisionDetalle);
//		InputStream input = new ByteArrayInputStream(array);
        
//        String file_name = "Reporte_Guia_Remision";
//		file_name = file_name.concat(Constantes.EXTENSION_FORMATO_PDF);
//    	
//        response.resetBuffer();
//        response.setContentType(Constantes.MIME_APPLICATION_PDF);
//        response.setHeader("Content-Disposition", "attachment; filename="+file_name);            
//		response.setHeader("Pragma", "no-cache");
//		response.setHeader("Cache-Control", "no-store");
//		response.setHeader("Pragma", "private");
//		response.setHeader("Set-Cookie", "fileDownload=true; path=/");
//		response.setDateHeader("Expires", 1);
//		
//		byte[] buffer = new byte[4096];
//    	int n = 0;
//
//    	OutputStream output = response.getOutputStream();
//    	while ((n = input.read(buffer)) != -1) {
//    	    output.write(buffer, 0, n);
//    	}
//    	output.close();
    	PDFMergerUtility pdfunir = new PDFMergerUtility();
    	for(int i=1;i<=5;i++){
    		parameters.put("TITULO_GUIA", getMensaje(messageSource, "params.guia.remision"+i));
    		generaArchivoPdf(jasperFile.toString(), parameters, listaGuiaRemisionDetalle,request, ruta+"guia"+i+".pdf");
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
        
//		byte[] array = printer.exportPdf(jasperFile.toString(), parameters, listaGuiaRemisionDetalle);
//		InputStream input = new ByteArrayInputStream(array);
//        
//        String file_name = "Reporte_Guia_Remision";
//		file_name = file_name.concat(Constantes.EXTENSION_FORMATO_PDF);
//    	
//        response.resetBuffer();
//        response.setContentType(Constantes.MIME_APPLICATION_PDF);
//        response.setHeader("Content-Disposition", "attachment; filename="+file_name);            
//		response.setHeader("Pragma", "no-cache");
//		response.setHeader("Cache-Control", "no-store");
//		response.setHeader("Pragma", "private");
//		response.setHeader("Set-Cookie", "fileDownload=true; path=/");
//		response.setDateHeader("Expires", 1);
//		
//		byte[] buffer = new byte[4096];
//    	int n = 0;
//
//    	OutputStream output = response.getOutputStream();
//    	while ((n = input.read(buffer)) != -1) {
//    	    output.write(buffer, 0, n);
//    	}
//    	output.close();
//    	getMensaje(messageSource, "params.guia.remision5");
//    	generaArchivoPdf(jasperFile.toString(), parameters, listaGuiaRemisionDetalle,request, ruta+"guia1.pdf");
    	
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
	
	@RequestMapping(value = "/obtenerRuta", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object obtenerRuta(HttpServletRequest request, HttpServletResponse response) {
		List<ItemBean> lista = null;
		ItemBean listaMenu = new ItemBean();
		try {
			listaMenu.setDescripcion(getPath(request)+File.separator+Constantes.PDF_PATH_GUIA_DON+"guia_remision.pdf");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return listaMenu;
	}
	
	/**
	 * @param listaManifiestoCarga
	 * @param request 
	 * @param response
	 */
	private void generarReporteManifiestoCarga(DetalleManifiestoCargaBean listaManifiestoCarga, 
											   HttpServletRequest request, 
											   HttpServletResponse response) throws Exception {
		
		DetalleManifiestoCargaBean manifiestoCarga = null;
		if (!Utils.isEmpty(listaManifiestoCarga.getLstCabecera())) {
			manifiestoCarga = listaManifiestoCarga.getLstCabecera().get(0);
		} else {
			manifiestoCarga = new DetalleManifiestoCargaBean();
		}
		
		ExportarArchivo printer = new ExportarArchivo();
		StringBuilder jasperFile = new StringBuilder();
		jasperFile.append(getPath(request));
		jasperFile.append(File.separator);
		jasperFile.append(Constantes.REPORT_PATH_DONACIONES);
		jasperFile.append("Manifiesto_Carga.jrxml");
		
		Map<String, Object> parameters = new HashMap<String, Object>();

		// Agregando los parámetros del reporte
		StringBuilder logo_indeci_path = new StringBuilder();
		logo_indeci_path.append(getPath(request));
		logo_indeci_path.append(File.separator);
		logo_indeci_path.append(Constantes.IMAGE_INDECI_REPORT_PATH);
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
		parameters.put("P_LOGO_CHECKED", logo_checked_path.toString());
		parameters.put("D_VERSION_SISTEMA", manifiestoCarga.getVersion());	
		parameters.put("P_NRO_MANIFIESTO", manifiestoCarga.getNroManifiestoCarga());			
		parameters.put("P_ALMACEN_ORIGEN", manifiestoCarga.getAlmacenOrigen());	
		parameters.put("P_REFERENCIA", manifiestoCarga.getReferencia());		
		parameters.put("P_FECHA_EMISION", manifiestoCarga.getFechaEmision());	
		parameters.put("P_TIPO_TRANSPORTE", manifiestoCarga.getTipoTransporte());	
		parameters.put("P_ALMACEN_DESTINO", manifiestoCarga.getAlmacenDestino());	
		parameters.put("P_MARCA_PLATA", manifiestoCarga.getNroPlacaVehiculo());	
		parameters.put("P_CHOFER", manifiestoCarga.getNombreChofer());	

		byte[] array = printer.exportPdf(jasperFile.toString(), parameters, listaManifiestoCarga.getLstDetalle());
		InputStream input = new ByteArrayInputStream(array);
        
        String file_name = "Manifiesto_Carga";
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
	}
	
	/**
	 * @param listaActaEntrega
	 * @param request 
	 * @param response
	 */
	private void generarReporteActaEntrega(DetalleActaEntregaBean listaActaEntrega, 
										   HttpServletRequest request, 
										   HttpServletResponse response) throws Exception {
		
		DetalleActaEntregaBean actaEntrega = null;
		if (!Utils.isEmpty(listaActaEntrega.getLstCabecera())) {
			actaEntrega = listaActaEntrega.getLstCabecera().get(0);
		} else {
			actaEntrega = new DetalleActaEntregaBean();
		}
		
		ExportarArchivo printer = new ExportarArchivo();
		StringBuilder jasperFile = new StringBuilder();
		jasperFile.append(getPath(request));
		jasperFile.append(File.separator);
		jasperFile.append(Constantes.REPORT_PATH_DONACIONES);
		jasperFile.append("Acta_Entrega.jrxml");
		
		Map<String, Object> parameters = new HashMap<String, Object>();

		// Agregando los parámetros del reporte
		StringBuilder logo_indeci_path = new StringBuilder();
		logo_indeci_path.append(getPath(request));
		logo_indeci_path.append(File.separator);
		logo_indeci_path.append(Constantes.IMAGE_INDECI_REPORT_PATH);
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
		parameters.put("P_LOGO_CHECKED", logo_checked_path.toString());
		parameters.put("D_VERSION_SISTEMA", actaEntrega.getVersion());	
		parameters.put("P_NRO_ACTA", actaEntrega.getNroActa());			
		parameters.put("P_ALMACEN_DESTINO1", actaEntrega.getAlmacenDestino1());	
		parameters.put("P_NRO_GUIA", actaEntrega.getNroGuiaRemision());		
		parameters.put("P_FECHA_EMISION", actaEntrega.getFechaEmisionGuia());	
		parameters.put("P_ALMACEN_DESTINO2", actaEntrega.getAlmacenDestino2());	
		parameters.put("P_OBSERVACION", actaEntrega.getObservacionActa());	;	

		byte[] array = printer.exportPdf(jasperFile.toString(), parameters, listaActaEntrega.getLstDetalle());
		InputStream input = new ByteArrayInputStream(array);
        
        String file_name = "Acta_Entrega";
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
		
//		StringBuilder file_path = new StringBuilder();
//    	file_path.append(getPath(request));
//    	file_path.append(File.separator);
//    	file_path.append(Constantes.UPLOAD_PATH_FILE_TEMP);
//    	file_path.append(File.separator);
//    	file_path.append(Calendar.getInstance().getTime().getTime());
//    	file_path.append(Constantes.EXTENSION_FORMATO_PDF);
//    	
//    	String file_name = "Acta_Entrega";
//		file_name = file_name.concat(Constantes.EXTENSION_FORMATO_PDF);
//
//		ReporteGuiaRemision reporte = new ReporteGuiaRemision();
//		reporte.generaPDFReporteActaEntrega(file_path.toString(), listaActaEntrega);
//		
//		response.resetBuffer();
//        response.setContentType(Constantes.MIME_APPLICATION_PDF);
//        response.setHeader("Content-Disposition", "attachment; filename="+file_name);            
//		response.setHeader("Pragma", "no-cache");
//		response.setHeader("Cache-Control", "no-store");
//		response.setHeader("Pragma", "private");
//		response.setHeader("Set-Cookie", "fileDownload=true; path=/");
//		response.setDateHeader("Expires", 1);
//    	
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		baos = convertPDFToByteArrayOutputStream(file_path.toString());
//		
//		// Captured backflow
//        OutputStream out = response.getOutputStream();
//        baos.writeTo(out); // We write in that flow
//        out.flush(); // We emptied the flow
//    	out.close(); // We close the flow
//    	
//    	File file_temp = new File(file_path.toString());
//		if (file_temp.delete()) {
//			LOGGER.info("[generarReporteActaEntrega] "+file_temp.getName()+" se borra el archivo temporal.");
//		} else {
//			LOGGER.info("[generarReporteActaEntrega] "+file_temp.getName()+" no se logró borrar el archivo temporal.");
//		}
	}
	
}
