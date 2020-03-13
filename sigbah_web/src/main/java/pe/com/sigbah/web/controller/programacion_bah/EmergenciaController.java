package pe.com.sigbah.web.controller.programacion_bah;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
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
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import pe.com.sigbah.common.bean.AlimentariaEmergenciaBean;
import pe.com.sigbah.common.bean.CabeceraEmergenciaBean;
import pe.com.sigbah.common.bean.EmergenciaBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.ListaRespuestaEmergenciaBean;
import pe.com.sigbah.common.bean.ListaRespuestaPedidoCompraBean;
import pe.com.sigbah.common.bean.LocalidadEmergenciaBean;
import pe.com.sigbah.common.bean.NoAlimentariaEmergenciaBean;
import pe.com.sigbah.common.bean.PedidoCompraReporteBean;
import pe.com.sigbah.common.bean.UsuarioBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.ExportarArchivo;
import pe.com.sigbah.common.util.HtmlUtils;
import pe.com.sigbah.service.AdministracionService;
import pe.com.sigbah.service.GeneralService;
import pe.com.sigbah.service.ProgramacionService;
import pe.com.sigbah.web.controller.common.BaseController;
import pe.com.sigbah.web.report.programacion_bah.ReporteEmergencia;

/**
 * @className: EmergenciaController.java
 * @description: 
 * @date: 13 jul. 2017
 * @author: whr.
 */

@Controller
@RequestMapping("/programacion-bath/emergencia")
public class EmergenciaController extends BaseController {
	
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
        	EmergenciaBean emergencia = new EmergenciaBean();		
        	emergencia.setCodAnio(generalService.obtenerAnioActual());
        	model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	
        	model.addAttribute("lista_region", generalService.listarRegion(new ItemBean()));
        	
        	model.addAttribute("lista_fenomeno", generalService.listarFenomeno(new ItemBean()));
   	
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	model.addAttribute("indicador", indicador);
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        	model.addAttribute("emergencia", getParserObject(emergencia));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "emergencias-sinpad";
    }

	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarEmergencias", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarEmergencias(HttpServletRequest request, HttpServletResponse response) {
		List<EmergenciaBean> lista = null;
		try {			
			EmergenciaBean emergenciaBean = new EmergenciaBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(emergenciaBean, request.getParameterMap());			
			lista = programacionService.listarEmergencia(emergenciaBean); 
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/exportarExcel/{codAnio}/{codMes}/{codRegion}/{codFenomeno}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcel(@PathVariable("codAnio") String codAnio, 
								@PathVariable("codMes") String codMes, 
								@PathVariable("codRegion") Integer codRegion, 
								@PathVariable("codFenomeno") Integer codFenomeno, 
								HttpServletResponse response) {
	    try {
	    	EmergenciaBean emergenciaBean = new EmergenciaBean();
			emergenciaBean.setCodAnio(verificaParametro(codAnio));
			emergenciaBean.setCodMes(verificaParametro(codMes));
			emergenciaBean.setIdRegion(codRegion);
			emergenciaBean.setIdFenomeno(codFenomeno);
			List<EmergenciaBean> lista = programacionService.listarEmergencia(emergenciaBean);
	    	
			String file_name = "Reporte_Emergencias";
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
	 * @param model
	 * @return - Retorna a la vista JSP.
	 */ 
	@RequestMapping(value = "/mantenimientoEmergencia/{codigo}/{codigoAnio}/{region}/{anio}/{mes}/{fenomeno}", method = RequestMethod.GET)
    public String mantenimientoEmergencia(@PathVariable("codigo") Integer codigo,
    									  @PathVariable("codigoAnio") String codigoAnio,
    									  @PathVariable("region") String region,
    									  @PathVariable("anio") String anio,
    									  @PathVariable("mes") String mes,
    									  @PathVariable("fenomeno") String fenomeno,
    									  Model model) {
        try {
        	ListaRespuestaEmergenciaBean detalle = new ListaRespuestaEmergenciaBean();
        	
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	detalle = programacionService.obtenerRegistroEmergencia(codigo,  codigoAnio);
    		model.addAttribute("cabecera", getParserObject(detalle.getLstCabecera().get(0)));
    		model.addAttribute("lista_localidad", getParserObject(detalle.getLstLocalidad()));
    		model.addAttribute("lista_alimentaria", getParserObject(detalle.getLstAlimentaria()));
    		model.addAttribute("lista_no_alimentaria", getParserObject(detalle.getLstNoAlimentaria()));
    		
    		model.addAttribute("codiRegion", region);
    		model.addAttribute("codiAnio", anio);
    		model.addAttribute("codiMes", mes);
    		model.addAttribute("codiFenomeno", fenomeno);
    		
    		HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
            
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "mantenimiento_emergencias-sinpad";
    }
	
	@RequestMapping(value = "/exportarPdf/{codigo}/{codAnio}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarPdf(@PathVariable("codigo") Integer codigo, 
								@PathVariable("codAnio") String codAnio, 
			HttpServletRequest request, HttpServletResponse response) {
		
		ListaRespuestaEmergenciaBean datosReporte =  new ListaRespuestaEmergenciaBean();
		try {
	    	
	    	datosReporte = programacionService.obtenerReporteEmergencia(codigo, codAnio);
	    	
	    	CabeceraEmergenciaBean cabecera =datosReporte.getLstCabecera().get(0);
	   		List<LocalidadEmergenciaBean> listaLocalidad = datosReporte.getLstLocalidad();
			List<AlimentariaEmergenciaBean> listaAlimentaria = datosReporte.getLstAlimentaria();
			List<NoAlimentariaEmergenciaBean> listaNoAlimentaria = datosReporte.getLstNoAlimentaria();
		
        	ExportarArchivo printer = new ExportarArchivo();
			StringBuilder jasperFile = new StringBuilder();
			jasperFile.append(getPath(request));
			jasperFile.append(File.separator);
			jasperFile.append(Constantes.REPORT_PATH_PROGRAMACION);
			jasperFile.append("PROG_Report_DetalleEmergenciasSINPAD.jrxml");
			
			String ruta = getPath(request)+File.separator+Constantes.REPORT_PATH_PROGRAMACION;
			System.out.println("RUTAJRXML: "+ruta);
			Map<String, Object> parameters = new HashMap<String, Object>();

			// Agregando los par�metros del reporte
			StringBuilder logo_indeci_path = new StringBuilder();
			logo_indeci_path.append(getPath(request));
			logo_indeci_path.append(File.separator);
			logo_indeci_path.append(Constantes.IMAGE_INDECI_REPORT_PATH);
//			parameters.put("REPORT_LOCALE", new Locale("en", "US")); 
			parameters.put("P_LOGO_INDECI", logo_indeci_path.toString());
	    	parameters.put("P_NUM_SINPAD", cabecera.getIdEmergencia().toString());
	    	parameters.put("P_DESCRIPCION", cabecera.getNombreEmergencia());
	    	parameters.put("P_FECHA", cabecera.getFechaEmergencia());
	    	parameters.put("P_FENOMENO", cabecera.getDescFenomeno());
	    	parameters.put("P_UBIGEO", cabecera.getUbigeo());
	    	
	    	parameters.put("P_LISTA_LOCALIDADES", new JRBeanCollectionDataSource(listaLocalidad));
	    	parameters.put("P_LISTA_ALIMENTOS", new JRBeanCollectionDataSource(listaAlimentaria));
	    	parameters.put("P_LISTA_NO_ALIMENTOS", new JRBeanCollectionDataSource(listaNoAlimentaria));
	    	
//	    	StringBuilder jasperFile2 = new StringBuilder();
//			jasperFile2.append(request.getServletContext().getRealPath("/"));
//			jasperFile2.append(Constantes.REPORT_PATH_PROGRAMACION);
			parameters.put("SUBREPORT_DIR",ruta );
//			parameters.put("SUBREPORT_DIR",Constantes.REPORT_PATH_PROGRAMACION );
//	    	bites=   ReporteUtil.exportPdf( request,Constantes.REP_REGISTRO_UNICO_DETALLE,  parameters, lista);
//	    	public   static byte[] exportPdf(HttpServletRequest request,String nombreReporteJasper, Map<String, Object> parameters, List<?> dataList) throws Exception {
//			 	parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
//			 	StringBuilder jasperFile4 = new StringBuilder();
	    	
	    	
	    	
//				jasperFile.append(request.getServletContext().getRealPath("/"));
//				jasperFile.append(Constantes.REPORT_PATH_PROGRAMACION);
//				jasperFile.append("Report_DetalleEmergenciasSINPAD");
//				String file = jasperFile.toString();
				
			 	
				//resources\report\programacion_bah\jrxml\
				
//				StringBuilder jasperFile2 = new StringBuilder();
//				jasperFile2.append(request.getServletContext().getRealPath("/"));
//				jasperFile2.append(Constantes.REPORT_PATH_PROGRAMACION);
//				parameters.put("SUBREPORT_DIR",jasperFile2.toString() );
//				parameters.put("SUBREPORT_DIR",Constantes.REPORT_PATH_PROGRAMACION );
				
//				StringBuilder jasperFile3 = new StringBuilder();
//				jasperFile3.append(request.getServletContext().getRealPath("/"));
//				jasperFile3.append(Constantes.REPORT_PATH_PROGRAMACION);
//				parameters.put("RUTA_IMG",jasperFile3.toString() );
				
//				JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(jasperFile.toString());
//				
//				 JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(null));
//		        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//		        JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
//		        byte[] bytes = byteArrayOutputStream.toByteArray();
		        
		        
			byte[] array = printer.exportPdf(jasperFile.toString(), parameters,listaLocalidad);//verificar
			InputStream input = new ByteArrayInputStream(array);
	        
	        String file_name = "Report_DetalleEmergenciasSINPAD";
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
