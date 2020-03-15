package pe.com.sigbah.web.controller.donaciones;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;

import pe.com.sigbah.common.bean.BincardAlmacenBean;
import pe.com.sigbah.common.bean.DetalleGuiaRemisionBean;
import pe.com.sigbah.common.bean.DonacionesIngresoBean;
import pe.com.sigbah.common.bean.GuiaRemisionBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.KardexAlmacenBean;
import pe.com.sigbah.common.bean.OrdenIngresoBean;
import pe.com.sigbah.common.bean.OrdenSalidaBean;
import pe.com.sigbah.common.bean.ProductoBean;
import pe.com.sigbah.common.bean.ProductoIngresoBean;
import pe.com.sigbah.common.bean.ProductoProyectoManifiestoBean;
import pe.com.sigbah.common.bean.ProductoSalidaBean;
import pe.com.sigbah.common.bean.ProyectoManifiestoBean;
import pe.com.sigbah.common.bean.StockAlmacenBean;
import pe.com.sigbah.common.bean.StockProductoKardexBean;
import pe.com.sigbah.common.bean.StockProductoLoteBean;
import pe.com.sigbah.common.bean.UsuarioBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.HtmlUtils;
import pe.com.sigbah.service.AdministracionService;
import pe.com.sigbah.service.DonacionService;
import pe.com.sigbah.service.GeneralService;
import pe.com.sigbah.service.LogisticaService;
import pe.com.sigbah.web.controller.common.BaseController;
import pe.com.sigbah.web.report.gestion_almacenes.ReporteAlmacen;

/**
 * @className: DonacionesReporteController.java
 * @description: 
 * @date: 11 de jul. de 2018
 * @author: Alan Chumpitaz.
 */
@Controller
@RequestMapping("/donaciones/reportes")
public class DonacionesReporteController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private LogisticaService logisticaService;
	
	@Autowired 
	private DonacionService donacionService;
	
	@Autowired 
	private GeneralService generalService;
	
	@Autowired 
	private AdministracionService administracionService;
	
	/**
	 * @param model 
	 * @return - Retorna a la vista JSP.
	 */
	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
    public String inicio(Model model) {
        try {
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

        	model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	
        	StockProductoKardexBean stockProductoKardexBean = new StockProductoKardexBean();
        	stockProductoKardexBean.setTipoOrigen(Constantes.TIPO_ORIGEN_ALMACENES);
        	stockProductoKardexBean.setIdDdi(usuarioBean.getIdDdi());
        	stockProductoKardexBean.setIdAlmacen(usuarioBean.getIdAlmacen());
        	model.addAttribute("lista_producto", logisticaService.listarStockProductoKardex(stockProductoKardexBean));
        	
//        	ProductoBean productoBean = new ProductoBean();			
//			productoBean.setIdAlmacen(usuarioBean.getIdAlmacen());
//			productoBean.setIdCategoria(0);
//			model.addAttribute("lista_producto", logisticaService.listarProductosStock(productoBean));
        	
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar_reportes_almacen";
    }
	
	@RequestMapping(value = "/inicioProyecto", method = RequestMethod.GET)
    public String inicioProyecto(Model model) {
        try {
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

        	model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	
//        	StockProductoKardexBean stockProductoKardexBean = new StockProductoKardexBean();
//        	stockProductoKardexBean.setTipoOrigen(Constantes.TIPO_ORIGEN_ALMACENES);
//        	stockProductoKardexBean.setIdDdi(usuarioBean.getIdDdi());
//        	stockProductoKardexBean.setIdAlmacen(usuarioBean.getIdAlmacen());
//        	model.addAttribute("lista_producto", logisticaService.listarStockProductoKardex(stockProductoKardexBean));
        	
        	ProductoBean productoBean = new ProductoBean();			
			productoBean.setIdAlmacen(usuarioBean.getIdAlmacen());
			productoBean.setIdCategoria(0);
			model.addAttribute("lista_producto", logisticaService.listarProductosStock(productoBean));
        	
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar_reportes_proyecto";
    }
	
	@RequestMapping(value = "/inicioSalida", method = RequestMethod.GET)
    public String inicioSalida(Model model) {
        try {
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

        	model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	
//        	StockProductoKardexBean stockProductoKardexBean = new StockProductoKardexBean();
//        	stockProductoKardexBean.setTipoOrigen(Constantes.TIPO_ORIGEN_ALMACENES);
//        	stockProductoKardexBean.setIdDdi(usuarioBean.getIdDdi());
//        	stockProductoKardexBean.setIdAlmacen(usuarioBean.getIdAlmacen());
//        	model.addAttribute("lista_producto", logisticaService.listarStockProductoKardex(stockProductoKardexBean));
        	
        	StockAlmacenBean productoBean = new StockAlmacenBean();	
        	productoBean.setTipoOrigen(Constantes.CODIGO_TIPO_DONACION);
			productoBean.setIdAlmacen(usuarioBean.getIdAlmacen());
			productoBean.setCodigoCategoria("0");
			model.addAttribute("lista_producto", donacionService.listarStockAlmacen(productoBean));
        	
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar_reportes_salida_don";
    }
	
	@RequestMapping(value = "/inicioIngreso", method = RequestMethod.GET)
    public String inicioIngreso(Model model) {
        try {
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

        	model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	
//        	StockProductoKardexBean stockProductoKardexBean = new StockProductoKardexBean();
//        	stockProductoKardexBean.setTipoOrigen(Constantes.TIPO_ORIGEN_ALMACENES);
//        	stockProductoKardexBean.setIdDdi(usuarioBean.getIdDdi());
//        	stockProductoKardexBean.setIdAlmacen(usuarioBean.getIdAlmacen());
//        	model.addAttribute("lista_producto", logisticaService.listarStockProductoKardex(stockProductoKardexBean));
        	
        	StockAlmacenBean productoBean = new StockAlmacenBean();	
        	productoBean.setTipoOrigen(Constantes.CODIGO_TIPO_DONACION);
			productoBean.setIdAlmacen(usuarioBean.getIdAlmacen());
			productoBean.setCodigoCategoria("0");
			model.addAttribute("lista_producto", donacionService.listarStockAlmacen(productoBean));
        	
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar_reportes_ingreso_don";
    }
	
	@RequestMapping(value = "/inicioGuia", method = RequestMethod.GET)
    public String inicioGuia(Model model) {
        try {
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

        	model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	
//        	StockProductoKardexBean stockProductoKardexBean = new StockProductoKardexBean();
//        	stockProductoKardexBean.setTipoOrigen(Constantes.TIPO_ORIGEN_ALMACENES);
//        	stockProductoKardexBean.setIdDdi(usuarioBean.getIdDdi());
//        	stockProductoKardexBean.setIdAlmacen(usuarioBean.getIdAlmacen());
//        	model.addAttribute("lista_producto", logisticaService.listarStockProductoKardex(stockProductoKardexBean));
        	
        	StockAlmacenBean productoBean = new StockAlmacenBean();	
        	productoBean.setTipoOrigen(Constantes.CODIGO_TIPO_DONACION);
			productoBean.setIdAlmacen(usuarioBean.getIdAlmacen());
			productoBean.setCodigoCategoria("0");
			model.addAttribute("lista_producto", donacionService.listarStockAlmacen(productoBean));
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar_reportes_guia_don";
    }
	
	@RequestMapping(value = "/inicioSiga", method = RequestMethod.GET)
    public String inicioSiga(Model model) {
        try {
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

        	model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	
        	StockProductoKardexBean stockProductoKardexBean = new StockProductoKardexBean();
        	stockProductoKardexBean.setTipoOrigen(Constantes.TIPO_ORIGEN_ALMACENES);
        	stockProductoKardexBean.setIdDdi(usuarioBean.getIdDdi());
        	stockProductoKardexBean.setIdAlmacen(usuarioBean.getIdAlmacen());
        	model.addAttribute("lista_producto", logisticaService.listarStockProductoKardex(stockProductoKardexBean));
        	
//        	ProductoBean productoBean = new ProductoBean();			
//			productoBean.setIdAlmacen(usuarioBean.getIdAlmacen());
//			productoBean.setIdCategoria(0);
//			model.addAttribute("lista_producto", logisticaService.listarProductosStock(productoBean));
        	
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar_reportes_siga_don";
    }
	/**
	 * @param tipoReporte 
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarTipoMovimiento", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarTipoMovimiento(@RequestParam(value="tipoReporte") String tipoReporte) {
		List<ItemBean> lista = null;
		try {			
			if (tipoReporte.equals(Constantes.ONE_STRING)) { // Reporte de Proyectos de Manifiesto 
				lista = generalService.listarTipoMovimiento(new ItemBean(Constantes.TWO_INT, Constantes.TWO_INT));
			} else if (tipoReporte.equals(Constantes.TWO_STRING)) { // Reporte de Ordenes de Salida  
				lista = generalService.listarTipoMovimiento(new ItemBean(Constantes.ONE_INT, Constantes.TWO_INT));
			} else if (tipoReporte.equals(Constantes.THREE_STRING)) { // Reporte de Ordenes de Ingreso  
				lista = generalService.listarTipoMovimiento(new ItemBean(Constantes.ONE_INT, Constantes.ONE_INT));
			} else if (tipoReporte.equals(Constantes.FOUR_STRING)) { // Reporte de Guias de Remision  
				lista = generalService.listarTipoMovimiento(new ItemBean(Constantes.ONE_INT, Constantes.TWO_INT));
			}
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
	@RequestMapping(value = "/listarStockProductoLote", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarStockProductoLote(HttpServletRequest request, HttpServletResponse response) {
		List<StockProductoLoteBean> lista = null;
		try {		
			StockProductoLoteBean stockProductoLote = new StockProductoLoteBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(stockProductoLote, request.getParameterMap());
			stockProductoLote.setTipoOrigen(Constantes.TIPO_ORIGEN_ALMACENES);
			lista = logisticaService.listarStockProductoLote(stockProductoLote);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	/**
	 * @param tipoReporte 
	 * @param anio 
	 * @param mesInicio 
	 * @param mesFin  
	 * @param tipoMovimiento 
	 * @param flagProducto 
	 * @param codigoProducto 
	 * @param nroLote 
	 * @param codigoAlmacen 
	 * @param request 
	 * @param response
	 * @return Objeto.
	 */
	@RequestMapping(value = "/exportarPdf", method = RequestMethod.GET)
	@ResponseBody
	public String exportarPdf(@RequestParam("tipoReporte") Integer tipoReporte,
							  @RequestParam("anio") String anio,
							  @RequestParam("mesInicio") String mesInicio,
							  @RequestParam("mesFin") String mesFin,							  
							  @RequestParam("tipoMovimiento") Integer tipoMovimiento,
							  @RequestParam("flagProducto") String flagProducto,
							  @RequestParam("codigoProducto") Integer codigoProducto,
							  @RequestParam("nroLote") String nroLote,
							  @RequestParam("codigoAlmacen") Integer codigoAlmacen,
							  HttpServletRequest request, 
							  HttpServletResponse response) {
	    try {	    	
	    	StringBuilder file_path = new StringBuilder();
	    	file_path.append(getPath(request));
	    	file_path.append(File.separator);
	    	file_path.append(Constantes.UPLOAD_PATH_FILE_TEMP);
	    	file_path.append(File.separator);
	    	file_path.append(Calendar.getInstance().getTime().getTime());
	    	file_path.append(Constantes.EXTENSION_FORMATO_PDF);
	    	
	    	String file_name = null;
	    	ReporteAlmacen reporte = new ReporteAlmacen();
	    	
	    	if (tipoReporte.equals(Constantes.ONE_INT)) { // Reporte de Proyectos de Manifiesto
	    		if (flagProducto.equals(Constantes.ZERO_STRING)) { // No incluye productos
	    			file_name = "Reporte_Proyecto_Manifiesto";
	    			ProyectoManifiestoBean proyectoManifiestoBean = new ProyectoManifiestoBean();
	    			proyectoManifiestoBean.setCodigoAnio(anio);
	    			proyectoManifiestoBean.setCodigoMesInicio(mesInicio);
	    			proyectoManifiestoBean.setCodigoMesFin(mesFin);
	    			proyectoManifiestoBean.setIdAlmacen(codigoAlmacen);
	    			proyectoManifiestoBean.setIdMovimiento(tipoMovimiento);
	    			proyectoManifiestoBean.setTipoOrigen(Constantes.TIPO_ORIGEN_ALMACENES);
	    			proyectoManifiestoBean.setIdProducto(codigoProducto);
	    			List<ProyectoManifiestoBean> listaProyectoManifiesto = logisticaService.listarReporteProyectoManifiesto(proyectoManifiestoBean);
	    			if (isEmpty(listaProyectoManifiesto)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			reporte.generaPDFReporteProyectoManifiesto(file_path.toString(), proyectoManifiestoBean, listaProyectoManifiesto);
	    		} else { // Si incluye productos
	    			file_name = "Reporte_Detalle_Proyecto_Manifiesto";
	    			ProductoProyectoManifiestoBean productoProyectoManifiestoBean = new ProductoProyectoManifiestoBean();
	    			productoProyectoManifiestoBean.setCodigoAnio(anio);
	    			productoProyectoManifiestoBean.setCodigoMesInicio(mesInicio);
	    			productoProyectoManifiestoBean.setCodigoMesFin(mesFin);
	    			productoProyectoManifiestoBean.setIdAlmacen(codigoAlmacen);
	    			productoProyectoManifiestoBean.setIdMovimiento(tipoMovimiento);
	    			productoProyectoManifiestoBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);
	    			productoProyectoManifiestoBean.setIdProducto(codigoProducto);
	    			List<ProductoProyectoManifiestoBean> listaDetalleProyectoManifiesto = logisticaService.listarReporteDetalleProyectoManifiesto(productoProyectoManifiestoBean);
	    			if (isEmpty(listaDetalleProyectoManifiesto)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			reporte.generaPDFReporteDetalleProyectoManifiesto(file_path.toString(), productoProyectoManifiestoBean, listaDetalleProyectoManifiesto);
	    		}
	    	} else if (tipoReporte.equals(Constantes.TWO_INT)) { // Reporte de Ordenes de Salida
	    		if (flagProducto.equals(Constantes.ZERO_STRING)) { // No incluye productos
	    			file_name = "Reporte_Orden_Salida";
	    			OrdenSalidaBean ordenSalidaBean = new OrdenSalidaBean();
	    			ordenSalidaBean.setCodigoAnio(anio);
	    			ordenSalidaBean.setCodigoMesInicio(mesInicio);
	    			ordenSalidaBean.setCodigoMesFin(mesFin);
	    			ordenSalidaBean.setIdAlmacen(codigoAlmacen);
	    			ordenSalidaBean.setIdMovimiento(tipoMovimiento);
	    			ordenSalidaBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);
	    			ordenSalidaBean.setIdProducto(codigoProducto);
	    			List<OrdenSalidaBean> listaOrdenSalida = donacionService.listarReporteOrdenSalida(ordenSalidaBean);
	    			if (isEmpty(listaOrdenSalida)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			reporte.generaPDFReporteOrdenSalida(file_path.toString(), ordenSalidaBean, listaOrdenSalida);
	    		} else { // Si incluye productos
	    			file_name = "Reporte_Detalle_Orden_Salida";
	    			ProductoSalidaBean productoSalidaBean = new ProductoSalidaBean();
	    			productoSalidaBean.setCodigoAnio(anio);
	    			productoSalidaBean.setCodigoMesInicio(mesInicio);
	    			productoSalidaBean.setCodigoMesFin(mesFin);
	    			productoSalidaBean.setIdAlmacen(codigoAlmacen);
	    			productoSalidaBean.setIdMovimiento(tipoMovimiento);
	    			productoSalidaBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);
	    			productoSalidaBean.setIdProducto(codigoProducto);
	    			List<ProductoSalidaBean> listaDetalleOrdenSalida = donacionService.listarReporteDetalleOrdenSalida(productoSalidaBean);
	    			if (isEmpty(listaDetalleOrdenSalida)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			reporte.generaPDFReporteDetalleOrdenSalida(file_path.toString(), productoSalidaBean, listaDetalleOrdenSalida);
	    		}
			} else if (tipoReporte.equals(Constantes.THREE_INT)) { // Reporte de Ordenes de Ingreso
	    		if (flagProducto.equals(Constantes.ZERO_STRING)) { // No incluye productos
	    			file_name = "Reporte_Orden_Ingreso";
	    			OrdenIngresoBean ordenIngresoBean = new OrdenIngresoBean();
	    			ordenIngresoBean.setCodigoAnio(anio);
	    			ordenIngresoBean.setCodigoMesInicio(mesInicio);
	    			ordenIngresoBean.setCodigoMesFin(mesFin);
	    			ordenIngresoBean.setIdAlmacen(codigoAlmacen);
	    			ordenIngresoBean.setIdMovimiento(tipoMovimiento);
	    			ordenIngresoBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);
	    			ordenIngresoBean.setIdProducto(codigoProducto);
	    			List<OrdenIngresoBean> listaOrdenIngreso = donacionService.listarReporteOrdenIngreso(ordenIngresoBean);
	    			if (isEmpty(listaOrdenIngreso)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			reporte.generaPDFReporteOrdenIngreso(file_path.toString(), ordenIngresoBean, listaOrdenIngreso);
	    		} else { // Si incluye productos
	    			file_name = "Reporte_Detalle_Orden_Ingreso";
	    			ProductoIngresoBean productoIngresoBean = new ProductoIngresoBean();
	    			productoIngresoBean.setCodigoAnio(anio);
	    			productoIngresoBean.setCodigoMesInicio(mesInicio);
	    			productoIngresoBean.setCodigoMesFin(mesFin);
	    			productoIngresoBean.setIdAlmacen(codigoAlmacen);
	    			productoIngresoBean.setIdMovimiento(tipoMovimiento);
	    			productoIngresoBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);
	    			productoIngresoBean.setIdProducto(codigoProducto);
	    			List<ProductoIngresoBean> listaDetalleOrdenIngreso = donacionService.listarReporteDetalleOrdenIngreso(productoIngresoBean);
	    			if (isEmpty(listaDetalleOrdenIngreso)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			reporte.generaPDFReporteDetalleOrdenIngreso(file_path.toString(), productoIngresoBean, listaDetalleOrdenIngreso);
	    		}    		
			} else if (tipoReporte.equals(Constantes.FOUR_INT)) { // Reporte de Guias de Remision
	    		if (flagProducto.equals(Constantes.ZERO_STRING)) { // No incluye productos
	    			file_name = "Reporte_Guia_Remision";
	    			GuiaRemisionBean guiaRemisionBean = new GuiaRemisionBean();
	    			guiaRemisionBean.setCodigoAnio(anio);
	    			guiaRemisionBean.setCodigoMesInicio(mesInicio);
	    			guiaRemisionBean.setCodigoMesFin(mesFin);
	    			guiaRemisionBean.setIdAlmacen(codigoAlmacen);
	    			guiaRemisionBean.setIdMovimiento(tipoMovimiento);
	    			guiaRemisionBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);
	    			guiaRemisionBean.setIdProducto(codigoProducto);
	    			List<GuiaRemisionBean> listaGuiaRemision = donacionService.listarReporteGuiaRemision(guiaRemisionBean);
	    			if (isEmpty(listaGuiaRemision)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			reporte.generaPDFReporteGuiaRemision(file_path.toString(), guiaRemisionBean, listaGuiaRemision);
	    		} else { // Si incluye productos
	    			file_name = "Reporte_Detalle_Guia_Remision";
	    			DetalleGuiaRemisionBean detalleGuiaRemisionBean = new DetalleGuiaRemisionBean();
	    			detalleGuiaRemisionBean.setCodigoAnio(anio);
	    			detalleGuiaRemisionBean.setCodigoMesInicio(mesInicio);
	    			detalleGuiaRemisionBean.setCodigoMesFin(mesFin);
	    			detalleGuiaRemisionBean.setIdAlmacen(codigoAlmacen);
	    			detalleGuiaRemisionBean.setIdMovimiento(tipoMovimiento);
	    			detalleGuiaRemisionBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);
	    			detalleGuiaRemisionBean.setIdProducto(codigoProducto);
	    			List<DetalleGuiaRemisionBean> listaDetalleGuiaRemision = donacionService.listarReporteDetalleGuiaRemision(detalleGuiaRemisionBean);
	    			if (isEmpty(listaDetalleGuiaRemision)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			reporte.generaPDFReporteDetalleGuiaRemision(file_path.toString(), detalleGuiaRemisionBean, listaDetalleGuiaRemision);
	    		}
			} else if (tipoReporte.equals(Constantes.FIVE_INT)) { // Reporte de Kardex y Bincard
	    		if (isNullOrEmpty(verificaParametro(nroLote))) { // Reporte de Kardex
	    			file_name = "Reporte_Kardex";
	    			KardexAlmacenBean kardexAlmacenBean = new KardexAlmacenBean();
	    			kardexAlmacenBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);	    			
	    			kardexAlmacenBean.setCodigoAnio(anio);
	    			kardexAlmacenBean.setCodigoMes(mesInicio);
	    			kardexAlmacenBean.setIdAlmacen(codigoAlmacen);
	    			kardexAlmacenBean.setIdProducto(codigoProducto);	    			
	    			List<KardexAlmacenBean> listaKardexAlmacen = logisticaService.listarReporteKardex(kardexAlmacenBean);
	    			if (isEmpty(listaKardexAlmacen)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			reporte.generaPDFReporteKardexAlmacen(file_path.toString(), kardexAlmacenBean, listaKardexAlmacen);
	    		} else { // Reporte de Bincard
	    			file_name = "Reporte_Bincard";
	    			BincardAlmacenBean bincardAlmacenBean = new BincardAlmacenBean();
	    			bincardAlmacenBean.setTipoOrigen(Constantes.TIPO_ORIGEN_ALMACENES);	
	    			bincardAlmacenBean.setCodigoAnio(anio);
	    			bincardAlmacenBean.setCodigoMes(mesInicio);
	    			bincardAlmacenBean.setIdAlmacen(codigoAlmacen);
	    			bincardAlmacenBean.setIdProducto(codigoProducto);
	    			bincardAlmacenBean.setNroLote(nroLote);
	    			List<BincardAlmacenBean> listaBincardAlmacen = logisticaService.listarReporteBincard(bincardAlmacenBean);
	    			if (isEmpty(listaBincardAlmacen)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			reporte.generaPDFReporteBincardAlmacen(file_path.toString(), bincardAlmacenBean, listaBincardAlmacen);
	    		}
			}
	    	
	    	file_name = file_name.concat(Constantes.EXTENSION_FORMATO_PDF);

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
	
	/**
	 * @param tipoReporte 
	 * @param anio 
	 * @param mesInicio 
	 * @param mesFin  
	 * @param tipoMovimiento 
	 * @param flagProducto 
	 * @param codigoProducto 
	 * @param nroLote 
	 * @param codigoAlmacen 
	 * @param request 
	 * @param response
	 * @return Objeto.
	 */
	@RequestMapping(value = "/exportarExcel", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcel(@RequestParam("tipoReporte") Integer tipoReporte,
							  	@RequestParam("anio") String anio,
							  	@RequestParam("mesInicio") String mesInicio,
							  	@RequestParam("mesFin") String mesFin,							  
							  	@RequestParam("tipoMovimiento") Integer tipoMovimiento,
							  	@RequestParam("flagProducto") String flagProducto,
							  	@RequestParam("codigoProducto") Integer codigoProducto,
							  	@RequestParam("nroLote") String nroLote,
							  	@RequestParam("codigoAlmacen") Integer codigoAlmacen,
							  	HttpServletRequest request, 
							  	HttpServletResponse response) {
	    try {
	    	StringBuilder file_path = new StringBuilder();
	    	file_path.append(getPath(request));
	    	file_path.append(File.separator);
	    	file_path.append(Constantes.UPLOAD_PATH_FILE_TEMP);
	    	file_path.append(File.separator);
	    	file_path.append(Calendar.getInstance().getTime().getTime());
	    	file_path.append(Constantes.EXTENSION_FORMATO_PDF);
	    	
	    	String file_name = null;
	    	HSSFWorkbook wb = null;
	    	ReporteAlmacen reporte = new ReporteAlmacen();
	    	
	    	if (tipoReporte.equals(Constantes.ONE_INT)) { // Reporte de Proyectos de Manifiesto
	    		if (flagProducto.equals(Constantes.ZERO_STRING)) { // No incluye productos
	    			file_name = "Reporte_Proyecto_Manifiesto";
	    			ProyectoManifiestoBean proyectoManifiestoBean = new ProyectoManifiestoBean();
	    			proyectoManifiestoBean.setCodigoAnio(anio);
	    			proyectoManifiestoBean.setCodigoMesInicio(mesInicio);
	    			proyectoManifiestoBean.setCodigoMesFin(mesFin);
	    			proyectoManifiestoBean.setIdAlmacen(codigoAlmacen);
	    			proyectoManifiestoBean.setIdMovimiento(tipoMovimiento);
	    			proyectoManifiestoBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);
	    			proyectoManifiestoBean.setIdProducto(codigoProducto);
	    			List<ProyectoManifiestoBean> listaProyectoManifiesto = logisticaService.listarReporteProyectoManifiesto(proyectoManifiestoBean);
	    			if (isEmpty(listaProyectoManifiesto)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			wb = reporte.generaExcelReporteProyectoManifiesto(file_path.toString(), proyectoManifiestoBean, listaProyectoManifiesto);
	    		} else { // Si incluye productos
	    			file_name = "Reporte_Detalle_Proyecto_Manifiesto";
	    			ProductoProyectoManifiestoBean productoProyectoManifiestoBean = new ProductoProyectoManifiestoBean();
	    			productoProyectoManifiestoBean.setCodigoAnio(anio);
	    			productoProyectoManifiestoBean.setCodigoMesInicio(mesInicio);
	    			productoProyectoManifiestoBean.setCodigoMesFin(mesFin);
	    			productoProyectoManifiestoBean.setIdAlmacen(codigoAlmacen);
	    			productoProyectoManifiestoBean.setIdMovimiento(tipoMovimiento);
	    			productoProyectoManifiestoBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);
	    			productoProyectoManifiestoBean.setIdProducto(codigoProducto);
	    			List<ProductoProyectoManifiestoBean> listaDetalleProyectoManifiesto = logisticaService.listarReporteDetalleProyectoManifiesto(productoProyectoManifiestoBean);
	    			if (isEmpty(listaDetalleProyectoManifiesto)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			wb = reporte.generaExcelReporteDetalleProyectoManifiesto(file_path.toString(), productoProyectoManifiestoBean, listaDetalleProyectoManifiesto);
	    		}
	    	} else if (tipoReporte.equals(Constantes.TWO_INT)) { // Reporte de Ordenes de Salida
	    		if (flagProducto.equals(Constantes.ZERO_STRING)) { // No incluye productos
	    			file_name = "Reporte_Orden_Salida";
	    			OrdenSalidaBean ordenSalidaBean = new OrdenSalidaBean();
	    			ordenSalidaBean.setCodigoAnio(anio);
	    			ordenSalidaBean.setCodigoMesInicio(mesInicio);
	    			ordenSalidaBean.setCodigoMesFin(mesFin);
	    			ordenSalidaBean.setIdAlmacen(codigoAlmacen);
	    			ordenSalidaBean.setIdMovimiento(tipoMovimiento);
	    			ordenSalidaBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);
	    			ordenSalidaBean.setIdProducto(codigoProducto);
	    			List<OrdenSalidaBean> listaOrdenSalida = donacionService.listarReporteOrdenSalida(ordenSalidaBean);
	    			if (isEmpty(listaOrdenSalida)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			wb = reporte.generaExcelReporteOrdenSalida(file_path.toString(), ordenSalidaBean, listaOrdenSalida);
	    		} else { // Si incluye productos
	    			file_name = "Reporte_Detalle_Orden_Salida";
	    			ProductoSalidaBean productoSalidaBean = new ProductoSalidaBean();
	    			productoSalidaBean.setCodigoAnio(anio);
	    			productoSalidaBean.setCodigoMesInicio(mesInicio);
	    			productoSalidaBean.setCodigoMesFin(mesFin);
	    			productoSalidaBean.setIdAlmacen(codigoAlmacen);
	    			productoSalidaBean.setIdMovimiento(tipoMovimiento);
	    			productoSalidaBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);
	    			productoSalidaBean.setIdProducto(codigoProducto);
	    			List<ProductoSalidaBean> listaDetalleOrdenSalida = donacionService.listarReporteDetalleOrdenSalida(productoSalidaBean);
	    			if (isEmpty(listaDetalleOrdenSalida)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			wb = reporte.generaExcelReporteDetalleOrdenSalida(file_path.toString(), productoSalidaBean, listaDetalleOrdenSalida);
	    		}
			} else if (tipoReporte.equals(Constantes.THREE_INT)) { // Reporte de Ordenes de Ingreso
	    		if (flagProducto.equals(Constantes.ZERO_STRING)) { // No incluye productos
	    			file_name = "Reporte_Orden_Ingreso";
	    			OrdenIngresoBean ordenIngresoBean = new OrdenIngresoBean();
	    			ordenIngresoBean.setCodigoAnio(anio);
	    			ordenIngresoBean.setCodigoMesInicio(mesInicio);
	    			ordenIngresoBean.setCodigoMesFin(mesFin);
	    			ordenIngresoBean.setIdAlmacen(codigoAlmacen);
	    			ordenIngresoBean.setIdMovimiento(tipoMovimiento);
	    			ordenIngresoBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);
	    			ordenIngresoBean.setIdProducto(codigoProducto);
	    			List<OrdenIngresoBean> listaOrdenIngreso = donacionService.listarReporteOrdenIngreso(ordenIngresoBean);
	    			if (isEmpty(listaOrdenIngreso)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			wb = reporte.generaExcelReporteOrdenIngreso(file_path.toString(), ordenIngresoBean, listaOrdenIngreso);
	    		} else { // Si incluye productos
	    			file_name = "Reporte_Detalle_Orden_Ingreso";
	    			ProductoIngresoBean productoIngresoBean = new ProductoIngresoBean();
	    			productoIngresoBean.setCodigoAnio(anio);
	    			productoIngresoBean.setCodigoMesInicio(mesInicio);
	    			productoIngresoBean.setCodigoMesFin(mesFin);
	    			productoIngresoBean.setIdAlmacen(codigoAlmacen);
	    			productoIngresoBean.setIdMovimiento(tipoMovimiento);
	    			productoIngresoBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);
	    			productoIngresoBean.setIdProducto(codigoProducto);
	    			List<ProductoIngresoBean> listaDetalleOrdenIngreso = donacionService.listarReporteDetalleOrdenIngreso(productoIngresoBean);
	    			if (isEmpty(listaDetalleOrdenIngreso)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			wb = reporte.generaExcelReporteDetalleOrdenIngreso(file_path.toString(), productoIngresoBean, listaDetalleOrdenIngreso);
	    		}    		
			} else if (tipoReporte.equals(Constantes.FOUR_INT)) { // Reporte de Guias de Remision
	    		if (flagProducto.equals(Constantes.ZERO_STRING)) { // No incluye productos
	    			file_name = "Reporte_Guia_Remision";
	    			GuiaRemisionBean guiaRemisionBean = new GuiaRemisionBean();
	    			guiaRemisionBean.setCodigoAnio(anio);
	    			guiaRemisionBean.setCodigoMesInicio(mesInicio);
	    			guiaRemisionBean.setCodigoMesFin(mesFin);
	    			guiaRemisionBean.setIdAlmacen(codigoAlmacen);
	    			guiaRemisionBean.setIdMovimiento(tipoMovimiento);
	    			guiaRemisionBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);
	    			guiaRemisionBean.setIdProducto(codigoProducto);
	    			List<GuiaRemisionBean> listaGuiaRemision = donacionService.listarReporteGuiaRemision(guiaRemisionBean);
	    			if (isEmpty(listaGuiaRemision)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			wb = reporte.generaExcelReporteGuiaRemision(file_path.toString(), guiaRemisionBean, listaGuiaRemision);
	    		} else { // Si incluye productos
	    			file_name = "Reporte_Detalle_Guia_Remision";
	    			DetalleGuiaRemisionBean detalleGuiaRemisionBean = new DetalleGuiaRemisionBean();
	    			detalleGuiaRemisionBean.setCodigoAnio(anio);
	    			detalleGuiaRemisionBean.setCodigoMesInicio(mesInicio);
	    			detalleGuiaRemisionBean.setCodigoMesFin(mesFin);
	    			detalleGuiaRemisionBean.setIdAlmacen(codigoAlmacen);
	    			detalleGuiaRemisionBean.setIdMovimiento(tipoMovimiento);
	    			detalleGuiaRemisionBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);
	    			detalleGuiaRemisionBean.setIdProducto(codigoProducto);
	    			List<DetalleGuiaRemisionBean> listaDetalleGuiaRemision = donacionService.listarReporteDetalleGuiaRemision(detalleGuiaRemisionBean);
	    			if (isEmpty(listaDetalleGuiaRemision)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			wb = reporte.generaExcelReporteDetalleGuiaRemision(file_path.toString(), detalleGuiaRemisionBean, listaDetalleGuiaRemision);
	    		}
			} else if (tipoReporte.equals(Constantes.FIVE_INT)) { // Reporte de Kardex y Bincard
	    		if (isNullOrEmpty(verificaParametro(nroLote))) { // Reporte de Kardex
	    			file_name = "Reporte_Kardex";
	    			KardexAlmacenBean kardexAlmacenBean = new KardexAlmacenBean();
	    			kardexAlmacenBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);	    			
	    			kardexAlmacenBean.setCodigoAnio(anio);
	    			kardexAlmacenBean.setCodigoMes(mesInicio);
	    			kardexAlmacenBean.setIdAlmacen(codigoAlmacen);
	    			kardexAlmacenBean.setIdProducto(codigoProducto);	    			
	    			List<KardexAlmacenBean> listaKardexAlmacen = logisticaService.listarReporteKardex(kardexAlmacenBean);
	    			if (isEmpty(listaKardexAlmacen)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			wb = reporte.generaExcelReporteKardexAlmacen(file_path.toString(), kardexAlmacenBean, listaKardexAlmacen);
	    		} else { // Reporte de Bincard
	    			file_name = "Reporte_Bincard";
	    			BincardAlmacenBean bincardAlmacenBean = new BincardAlmacenBean();
	    			bincardAlmacenBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);	
	    			bincardAlmacenBean.setCodigoAnio(anio);
	    			bincardAlmacenBean.setCodigoMes(mesInicio);
	    			bincardAlmacenBean.setIdAlmacen(codigoAlmacen);
	    			bincardAlmacenBean.setIdProducto(codigoProducto);
	    			bincardAlmacenBean.setNroLote(nroLote);
	    			List<BincardAlmacenBean> listaBincardAlmacen = logisticaService.listarReporteBincard(bincardAlmacenBean);
	    			if (isEmpty(listaBincardAlmacen)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
	    			wb = reporte.generaExcelReporteBincardAlmacen(file_path.toString(), bincardAlmacenBean, listaBincardAlmacen);
	    		}
			}else if (tipoReporte.equals(Constantes.SIX_INT)) {//REPORTE SIGA
				file_name = "Reporte_SIGA";
				usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
				OrdenIngresoBean sigaAlmacenBean = new OrdenIngresoBean();	
				// Copia los parametros del cliente al objeto
				BeanUtils.populate(sigaAlmacenBean, request.getParameterMap());
				sigaAlmacenBean.setCodigoAnio(anio);
				sigaAlmacenBean.setCodigoMes(mesInicio);
				sigaAlmacenBean.setTipoExportacion(flagProducto);
				sigaAlmacenBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);
				sigaAlmacenBean.setIdAlmacen(usuarioBean.getIdAlmacen());
				List<OrdenIngresoBean> lista= null;
				if(sigaAlmacenBean.getTipoExportacion().equals("E")){
					lista = donacionService.listarReporteSigaE(sigaAlmacenBean);
					if (isEmpty(lista)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
					wb = reporte.generaExcelReporteSigaAlmacenE(file_path.toString(), lista,"E");
				}else{
					lista = donacionService.listarReporteSigaS(sigaAlmacenBean);
					if (isEmpty(lista)) {
	    				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
	    			}
					wb = reporte.generaExcelReporteSigaAlmacenS(file_path.toString(), lista,"S");
				}
				
			}
	    	
	    	file_name = file_name.concat(Constantes.EXTENSION_FORMATO_XLS);

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
	@RequestMapping(value = "/exportarRepoKardex", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object exportarRepoKardex(HttpServletRequest request, HttpServletResponse response) {
		List<KardexAlmacenBean> lista = null;
		try {
			
			KardexAlmacenBean kardexAlmacenBean = new KardexAlmacenBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(kardexAlmacenBean, request.getParameterMap());
			kardexAlmacenBean.setTipoOrigen(Constantes.TIPO_ORIGEN_ALMACENES);
			lista = logisticaService.listarReporteKardex(kardexAlmacenBean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		System.out.println(lista.size());
		return lista;
	}
	
	@RequestMapping(value = "/exportarRepoSiga", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object exportarRepoSiga(HttpServletRequest request, HttpServletResponse response) {
		List<OrdenIngresoBean> lista = null;
		try {
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			OrdenIngresoBean sigaAlmacenBean = new OrdenIngresoBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(sigaAlmacenBean, request.getParameterMap());
			sigaAlmacenBean.setTipoOrigen(Constantes.TIPO_ORIGEN_DONACIONES);
			sigaAlmacenBean.setIdAlmacen(usuarioBean.getIdAlmacen());
			System.out.println("TIPO "+sigaAlmacenBean.getTipoExportacion());
			if(sigaAlmacenBean.getTipoExportacion().equals("E")){
				lista = donacionService.listarReporteSigaE(sigaAlmacenBean);
			}else{
				lista = donacionService.listarReporteSigaS(sigaAlmacenBean);
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		System.out.println(lista.size());
		return lista;
	}

}