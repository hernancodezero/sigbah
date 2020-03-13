package pe.com.sigbah.web.controller.programacion_bah;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
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

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pe.com.sigbah.common.bean.ConsultaProgramacionBean;
import pe.com.sigbah.common.bean.DeeBean;
import pe.com.sigbah.common.bean.DonacionesSalidaBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.ListaRespuestaConsultaProductoBean;
import pe.com.sigbah.common.bean.ProductoConsultaProductoBean;
import pe.com.sigbah.common.bean.ProgramacionBean;
import pe.com.sigbah.common.bean.ReporteConsultaProgramacionBean;
import pe.com.sigbah.common.bean.StockConsultaBean;
import pe.com.sigbah.common.bean.UsuarioBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.ExportarArchivo;
import pe.com.sigbah.common.util.HtmlUtils;
import pe.com.sigbah.service.AdministracionService;
import pe.com.sigbah.service.GeneralService;
import pe.com.sigbah.service.ProgramacionService;
import pe.com.sigbah.web.controller.common.BaseController;
import pe.com.sigbah.web.report.programacion_bah.ReporteDee;

/**
 * @className: EmergenciaController.java
 * @description: 
 * @date: 13 jul. 2017
 * @author: whr.
 */

@Controller
@RequestMapping("/programacion-bath/consulta-programacion")
public class ConsultaProgramacionController extends BaseController {
	
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
        	model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	
        	model.addAttribute("lista_dee", generalService.listarDee(new ItemBean()));
        	
        	model.addAttribute("lista_estado", generalService.listarEstado( new ItemBean(null,Constantes.ONE_INT)));
     	
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        	
        	model.addAttribute("indicador", indicador);
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));

        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "consulta-programacion";
    }

	
	@RequestMapping(value = "/listarProg_x_mes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProg_x_mes(HttpServletRequest request, HttpServletResponse response) {
		List<ConsultaProgramacionBean> lista = null;
		try {			
			ConsultaProgramacionBean consultaBean = new ConsultaProgramacionBean();	
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			consultaBean.setIdDdi(Integer.parseInt(usuarioBean.getCodigoDdi()));
			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(consultaBean, request.getParameterMap());			
			lista = programacionService.listarProg_x_mes(consultaBean); 
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	
	@RequestMapping(value = "/listarProg_x_dee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProg_x_dee(HttpServletRequest request, HttpServletResponse response) {
		List<ConsultaProgramacionBean> lista = null;
		try {			
			ConsultaProgramacionBean consultaBean = new ConsultaProgramacionBean();	
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			consultaBean.setIdDdi(Integer.parseInt(usuarioBean.getCodigoDdi()));
			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(consultaBean, request.getParameterMap());			
			lista = programacionService.listarProg_x_dee(consultaBean); 
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	

	@RequestMapping(value = "/listarProg_x_deePDF/{codAnio}/{nroDee}/{idEstado}", method = RequestMethod.GET)
	@ResponseBody
	public String listarProg_x_deePDF(@PathVariable("codAnio") String codAnio,
			  @PathVariable("nroDee") String nroDee,
			  @PathVariable("idEstado") Integer idEstado,
			  HttpServletRequest request, HttpServletResponse response) {
		ReporteConsultaProgramacionBean lista = null;
		try {			
			ConsultaProgramacionBean consultaBean = new ConsultaProgramacionBean();	
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			
			
			// Copia los parametros del cliente al objeto
//			BeanUtils.populate(consultaBean, request.getParameterMap());		
			consultaBean.setIdDdi(usuarioBean.getIdDdi());
			consultaBean.setCodigoAnio(codAnio);
			consultaBean.setNroDee(nroDee);
			consultaBean.setIdEstado(idEstado);

			lista = programacionService.obtenerReporteConsultaProg(consultaBean); 
			
			List<ProgramacionBean> listaCabecera = lista.getLstCabecera();
			List<ProductoConsultaProductoBean> listaAlimentos = lista.getLstAlimentaria();
			List<ProductoConsultaProductoBean> listaBNA = lista.getLstNoAlimentaria();

			if (isEmpty(listaCabecera)) {
				return Constantes.COD_VALIDACION_GENERAL;
			}			
//			DonacionesSalidaBean general = lista.get(0);
			String nrosDEE="";
			String nombreSistema=listaCabecera.get(0).getNombreSistema();
			for(int i=0;i<listaCabecera.size();i++){
				
				if(i==(listaCabecera.size()-1)){
					nrosDEE+=listaCabecera.get(i).getNroDee()+".";
				}else{
					nrosDEE+=listaCabecera.get(i).getNroDee()+", ";
				}
			}
			
			System.out.println("tamanio "+listaCabecera.size()+" nroDee "+nrosDEE);
			System.out.println("tamanio "+listaAlimentos.size());
			System.out.println("tamanio "+listaBNA.size());

			ExportarArchivo printer = new ExportarArchivo();
			StringBuilder jasperFile = new StringBuilder();
			jasperFile.append(getPath(request));
			jasperFile.append(File.separator);
			jasperFile.append(Constantes.REPORT_PATH_PROGRAMACION);
			jasperFile.append("PROG_Report_ConsultaProgramacion.jrxml");
			
			String ruta = getPath(request)+File.separator+Constantes.REPORT_PATH_PROGRAMACION;

			
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
			parameters.put("P_NRO_DEE", nrosDEE);
			parameters.put("NOMBRE_SISTEMA", nombreSistema);
			parameters.put("LISTA_BNA", ListaBna);
			parameters.put("LISTA_ALIMENTOS", ListaAli);

			System.out.println("RUTA: "+ruta);
			byte[] array = printer.exportPdf(jasperFile.toString(), parameters, listaCabecera);
			InputStream input = new ByteArrayInputStream(array);
	        
	        String file_name = "Consulta_Programacion";
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
	
	
	@RequestMapping(value = "/mostrarProductos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public ListaRespuestaConsultaProductoBean mostrarProductos(HttpServletRequest request, HttpServletResponse response)  {
		
		ListaRespuestaConsultaProductoBean detalle = new ListaRespuestaConsultaProductoBean();
		try {
//        	ListaRespuestaEmergenciaBean detalle = new ListaRespuestaEmergenciaBean();
			ProductoConsultaProductoBean pc=new ProductoConsultaProductoBean();
			BeanUtils.populate(pc, request.getParameterMap());	
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
//        	detalle = programacionService.obtenerRegistroEmergencia(codigo,  codigoAnio);
        	detalle = programacionService.obtenerConsultaProducto(pc.getIdProgramacion());
        	
//        	model.addAttribute("lista_alimentaria", getParserObject(detalle.getLstAlimentaria()));
//    		model.addAttribute("lista_NO_alimentaria", getParserObject(detalle.getLstNoAlimentaria()));
    		
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
			return (ListaRespuestaConsultaProductoBean) getBaseRespuesta(null);
        }
        return detalle;
    }
	
//	@RequestMapping(value = "/exportarExcel/{idProgramacion}", method = RequestMethod.GET)
//	@ResponseBody
//	public String exportarExcel(@PathVariable("idProgramacion") Integer idProgramacion, 
//								HttpServletResponse response) {
//	    try {
//	    	DeeBean deeBean = new DeeBean();
//	    	deeBean.setCodAnio(verificaParametro(codAnio));
//	    	deeBean.setCodMes(verificaParametro(codMes));
//	    	deeBean.setIdEstado(idEstado);
//	    	
//			List<DeeBean> lista = programacionService.listarDee(deeBean);
//	    	
//			String file_name = "DecretosEstadoEmergencia";
//			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_XLS);
//			
//			ReporteDee reporte = new ReporteDee();
//		    HSSFWorkbook wb = reporte.generaReporteExcelDee(lista);
//		    
//			response.resetBuffer();
//            response.setContentType(Constantes.MIME_APPLICATION_XLS);
//            response.setHeader("Content-Disposition", "attachment; filename="+file_name);            
//			response.setHeader("Pragma", "no-cache");
//			response.setHeader("Cache-Control", "no-store");
//			response.setHeader("Pragma", "private");
//			response.setHeader("Set-Cookie", "fileDownload=true; path=/");
//			response.setDateHeader("Expires", 1);
//	    	
//		    // Captured backflow
//	    	OutputStream out = response.getOutputStream();
//	    	wb.write(out); // We write in that flow
//	    	out.flush(); // We emptied the flow
//	    	out.close(); // We close the flow
//	    	
//	    	return Constantes.COD_EXITO_GENERAL;   	
//	    } catch (Exception e) {
//	    	LOGGER.error(e.getMessage(), e);
//	    	return Constantes.COD_ERROR_GENERAL;
//	    } 
//	}
	
}
