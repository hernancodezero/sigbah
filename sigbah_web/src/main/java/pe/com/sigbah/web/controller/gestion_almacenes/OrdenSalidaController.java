package pe.com.sigbah.web.controller.gestion_almacenes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pe.com.sigbah.common.bean.CierreStockBean;
import pe.com.sigbah.common.bean.ControlCalidadBean;
import pe.com.sigbah.common.bean.DocumentoSalidaBean;
import pe.com.sigbah.common.bean.DonacionesSalidaBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.LoteProductoBean;
import pe.com.sigbah.common.bean.OrdenSalidaBean;
import pe.com.sigbah.common.bean.ProductoBean;
import pe.com.sigbah.common.bean.ProductoProyectoManifiestoBean;
import pe.com.sigbah.common.bean.ProductoSalidaBean;
import pe.com.sigbah.common.bean.ProyectoManifiestoBean;
import pe.com.sigbah.common.bean.UbigeoBean;
import pe.com.sigbah.common.bean.UsuarioBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.ExportarArchivo;
import pe.com.sigbah.common.util.HtmlUtils;
import pe.com.sigbah.common.util.Utils;
import pe.com.sigbah.service.AdministracionService;
import pe.com.sigbah.service.GeneralService;
import pe.com.sigbah.service.LogisticaService;
import pe.com.sigbah.web.controller.common.BaseController;
import pe.com.sigbah.web.report.gestion_almacenes.ReporteOrdenSalida;

/**
 * @className: OrdenSalidaController.java
 * @description: 
 * @date: 17 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
@Controller
@RequestMapping("/gestion-almacenes/orden-salida")
public class OrdenSalidaController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private LogisticaService logisticaService;
	
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
		        	
		        	OrdenSalidaBean ordenSalida = new OrdenSalidaBean();
		        	
		        	ControlCalidadBean parametroAlmacenActivo = new ControlCalidadBean();
		    		parametroAlmacenActivo.setIdAlmacen(usuarioBean.getIdAlmacen());
		    		parametroAlmacenActivo.setTipo(Constantes.CODIGO_TIPO_ALMACEN);
		//    		List<ControlCalidadBean> listaAlmacenActivo = logisticaService.listarAlmacenActivo(parametroAlmacenActivo);
		//    		if (!isEmpty(listaAlmacenActivo)) {
		//    			ordenSalida.setCodigoAnio(listaAlmacenActivo.get(0).getCodigoAnio());
		//    			ordenSalida.setIdAlmacen(listaAlmacenActivo.get(0).getIdAlmacen());
		//    			ordenSalida.setCodigoAlmacen(listaAlmacenActivo.get(0).getCodigoAlmacen());
		//    			ordenSalida.setNombreAlmacen(listaAlmacenActivo.get(0).getNombreAlmacen());
		//    			ordenSalida.setCodigoMes(listaAlmacenActivo.get(0).getCodigoMes());
		//    		}
		    		CierreStockBean cierreStock = new CierreStockBean();
		    		cierreStock = administracionService.mesTrabajoActivo(usuarioBean.getIdAlmacen(), Constantes.TIPO_ORIGEN_ALMACENES);
		    		if (cierreStock.getCodigoAnio()!=null) {
		    			ordenSalida.setCodigoAnio(cierreStock.getCodigoAnio());
		    			ordenSalida.setIdAlmacen(cierreStock.getIdAlmacen());
		    			
		    			ordenSalida.setNombreAlmacen(cierreStock.getNombreAlmacen());
		    			ordenSalida.setCodigoMes(cierreStock.getCodigoMes());
		    		}
		    		ordenSalida.setCodigoAlmacen(usuarioBean.getCodigoAlmacen());
		    		model.addAttribute("ordenSalida", getParserObject(ordenSalida));
		    		model.addAttribute("mes_anio", cierre.getNombreMes()+" "+cierre.getCodigoAnio());
		    		model.addAttribute("anioActivo", cierre.getCodigoAnio());
		    		model.addAttribute("mesActivo", cierre.getCodigoMes());
		        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
		        	ruta="listar_orden_salida";
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
	@RequestMapping(value = "/listarOrdenSalida", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarOrdenSalida(HttpServletRequest request, HttpServletResponse response) {
		List<OrdenSalidaBean> lista = null;
		try {			
			OrdenSalidaBean ordenSalidaBean = new OrdenSalidaBean();
			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(ordenSalidaBean, request.getParameterMap());
			
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			
        	ordenSalidaBean.setTipoOrigen(Constantes.TIPO_ORIGEN_ALMACENES);
        	ordenSalidaBean.setIdDdi(usuarioBean.getIdDdi());
        	ordenSalidaBean.setCodigoDdi(usuarioBean.getCodigoDdi());
			
			lista = logisticaService.listarOrdenSalida(ordenSalidaBean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	/**
	 * @param codigo 
	 * @param anio 
	 * @param model
	 * @return - Retorna a la vista JSP.
	 */
	@RequestMapping(value = "/mantenimientoOrdenSalida/{codigo}/{anio}", method = RequestMethod.GET)
    public String mantenimientoOrdenSalida(@PathVariable("codigo") Integer codigo, @PathVariable("anio") String anio, Model model) {
        try {
        	OrdenSalidaBean ordenSalida = new OrdenSalidaBean();
        	
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	String anioActual = generalService.obtenerAnioActual();
        	CierreStockBean cierre = new CierreStockBean();
       		cierre = administracionService.mesTrabajoActivo(usuarioBean.getIdAlmacen(), Constantes.TIPO_ORIGEN_ALMACENES);
        	if (!isNullInteger(codigo)) {
        		
        		ordenSalida = logisticaService.obtenerRegistroOrdenSalida(codigo, anio);
        		
        		if (!isNullInteger(ordenSalida.getIdMedioTransporte())) {
        			ItemBean item = new ItemBean();
        			item.setIcodigo(usuarioBean.getIdDdi());
        			item.setIcodigoParam2(ordenSalida.getIdMedioTransporte());
        			model.addAttribute("lista_empresa_transporte", generalService.listarEmpresaTransporte(item));
        		}        		
        		if (!isNullInteger(ordenSalida.getIdEmpresaTransporte())) {
        			model.addAttribute("lista_chofer", generalService.listarChofer(new ItemBean(ordenSalida.getIdEmpresaTransporte())));
        		}
        	} else {

        		StringBuilder correlativo = new StringBuilder();
        		correlativo.append(usuarioBean.getCodigoDdi());
        		correlativo.append(Constantes.SEPARADOR);
        		correlativo.append(usuarioBean.getCodigoAlmacen());
        		correlativo.append(Constantes.SEPARADOR);
        		
        		OrdenSalidaBean parametros = new OrdenSalidaBean();        		
        		parametros.setCodigoAnio(anioActual);
        		parametros.setCodigoDdi(usuarioBean.getCodigoDdi());
        		parametros.setIdAlmacen(usuarioBean.getIdAlmacen());
        		parametros.setCodigoAlmacen(usuarioBean.getCodigoAlmacen());
        		parametros.setTipoOrigen(Constantes.TIPO_ORIGEN_ALMACENES);
        		OrdenSalidaBean respuestaCorrelativo = logisticaService.obtenerCorrelativoOrdenSalida(parametros);
      
        		correlativo.append(respuestaCorrelativo.getNroOrdenSalida());
        		
        		ordenSalida.setNroOrdenSalida(correlativo.toString());        		
        		
        		ControlCalidadBean parametroAlmacenActivo = new ControlCalidadBean();
        		parametroAlmacenActivo.setIdAlmacen(usuarioBean.getIdAlmacen());
        		parametroAlmacenActivo.setTipo(Constantes.CODIGO_TIPO_ALMACEN);
//        		List<ControlCalidadBean> listaAlmacenActivo = logisticaService.listarAlmacenActivo(parametroAlmacenActivo);
//        		if (!isEmpty(listaAlmacenActivo)) {
//        			ordenSalida.setCodigoAnio(listaAlmacenActivo.get(0).getCodigoAnio());
//        			ordenSalida.setIdAlmacen(listaAlmacenActivo.get(0).getIdAlmacen());
//        			ordenSalida.setCodigoAlmacen(listaAlmacenActivo.get(0).getCodigoAlmacen());
//        			ordenSalida.setNombreAlmacen(listaAlmacenActivo.get(0).getNombreAlmacen());
//        			ordenSalida.setCodigoMes(listaAlmacenActivo.get(0).getCodigoMes());
//        		}
        		CierreStockBean cierreStock = new CierreStockBean();
        		cierreStock = administracionService.mesTrabajoActivo(usuarioBean.getIdAlmacen(), Constantes.TIPO_ORIGEN_ALMACENES);
        		if (cierreStock.getCodigoAnio()!=null) {
        			ordenSalida.setCodigoAnio(cierreStock.getCodigoAnio());
        			ordenSalida.setIdAlmacen(cierreStock.getIdAlmacen());
        			
        			ordenSalida.setNombreAlmacen(cierreStock.getNombreAlmacen());
        			ordenSalida.setCodigoMes(cierreStock.getCodigoMes());
        		}
        		ordenSalida.setCodigoAlmacen(usuarioBean.getCodigoAlmacen());
        		ordenSalida.setIdDdi(usuarioBean.getIdDdi());
        		ordenSalida.setCodigoDdi(usuarioBean.getCodigoDdi());
        		ordenSalida.setNombreDdi(usuarioBean.getNombreDdi());
        	}
        	
        	if (!Utils.isNullInteger(usuarioBean.getIdDdi())) {
        		model.addAttribute("lista_almacen", generalService.listarAlmacen(new ItemBean(usuarioBean.getIdDdi())));
        	}
        	
        	model.addAttribute("ordenSalida", getParserObject(ordenSalida));
        	
        	model.addAttribute("lista_tipo_movimiento", generalService.listarTipoMovimiento(new ItemBean(Constantes.TWO_INT, Constantes.TWO_INT)));

        	model.addAttribute("lista_estado", generalService.listarEstado(new ItemBean(null, Constantes.FOUR_INT)));
        	
        	ProyectoManifiestoBean proyectoManifiestoBean = new ProyectoManifiestoBean();
        	proyectoManifiestoBean.setCodigoAnio(anioActual);
        	proyectoManifiestoBean.setIdDdi(usuarioBean.getIdDdi());
        	proyectoManifiestoBean.setIdAlmacen(usuarioBean.getIdAlmacen());
        	model.addAttribute("lista_proyecto_manifiesto", logisticaService.listarManifiesto(proyectoManifiestoBean));
        	
        	model.addAttribute("lista_personal", generalService.listarPersonal(new ItemBean(usuarioBean.getIdDdi())));
        	
        	model.addAttribute("lista_ddi", generalService.listarDdi(new ItemBean()));
        	
        	model.addAttribute("lista_medio_transporte", generalService.listarMedioTransporte(new ItemBean()));
        	
        	model.addAttribute("lista_region", generalService.listarRegion(new ItemBean()));
        	
        	model.addAttribute("lista_departamento", generalService.listarDepartamentos(new UbigeoBean()));
        	
        	model.addAttribute("lista_tipo_documento", generalService.listarTipoDocumento(new ItemBean()));
     
        	model.addAttribute("lista_categoria", generalService.listarCategoria(new ItemBean(Constantes.THREE_INT)));
        	
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	model.addAttribute("mes_anio", cierre.getNombreMes()+" "+cierre.getCodigoAnio());
        	
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "mantenimiento_orden_salida";
    }
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarAlmacenDestino", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarAlmacenDestino(HttpServletRequest request, HttpServletResponse response) {
		List<ItemBean> lista = null;
		try {			
			ItemBean item = new ItemBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(item, request.getParameterMap());
			lista = generalService.listarAlmacen(item);
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
	@RequestMapping(value = "/listarResponsableRecepcion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarResponsableRecepcion(HttpServletRequest request, HttpServletResponse response) {
		List<ItemBean> lista = null;
		try {			
			ItemBean item = new ItemBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(item, request.getParameterMap());
			lista = generalService.listarPersonal(item);
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
	@RequestMapping(value = "/listarAlmacenExtRegion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarAlmacenExtRegion(HttpServletRequest request, HttpServletResponse response) {
		List<ItemBean> lista = null;
		try {			
			ItemBean item = new ItemBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(item, request.getParameterMap());
			lista = generalService.listarAlmacenExternoRegion(item);
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
	@RequestMapping(value = "/listarPersonalExtRegion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarPersonalExtRegion(HttpServletRequest request, HttpServletResponse response) {
		List<ItemBean> lista = null;
		try {			
			ItemBean item = new ItemBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(item, request.getParameterMap());
			lista = generalService.listarPersonalExternoRegion(item);
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
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarDistrito", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarDistrito(HttpServletRequest request, HttpServletResponse response) {
		List<UbigeoBean> lista = null;
		try {			
			UbigeoBean ubigeoBean = new UbigeoBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(ubigeoBean, request.getParameterMap());
			lista = generalService.listarDistrito(ubigeoBean);
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
	@RequestMapping(value = "/listarAlmacenExtLocal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarAlmacenExtLocal(HttpServletRequest request, HttpServletResponse response) {
		List<ItemBean> lista = null;
		try {			
			ItemBean item = new ItemBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(item, request.getParameterMap());
			lista = generalService.listarAlmacenExternoLocal(item);
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
	@RequestMapping(value = "/listarPersonalExtLocal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarPersonalExtLocal(HttpServletRequest request, HttpServletResponse response) {
		List<ItemBean> lista = null;
		try {			
			ItemBean item = new ItemBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(item, request.getParameterMap());
			lista = generalService.listarPersonalExternoLocal(item);
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
	@RequestMapping(value = "/grabarOrdenSalida", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarOrdenSalida(HttpServletRequest request, HttpServletResponse response) {
		OrdenSalidaBean ordenSalida = null;
		try {			
			OrdenSalidaBean ordenSalidaBean = new OrdenSalidaBean();
			
			// Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(ordenSalidaBean, request.getParameterMap());

			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
			ordenSalidaBean.setTipoOrigen(Constantes.TIPO_ORIGEN_ALMACENES);
        	ordenSalidaBean.setUsuarioRegistro(usuarioBean.getUsuario());
			
        	ordenSalida = logisticaService.grabarOrdenSalida(ordenSalidaBean);
        	ordenSalida.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return ordenSalida;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarProductoOrdenSalida", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProductoOrdenSalida(HttpServletRequest request, HttpServletResponse response) {
		List<ProductoSalidaBean> lista = null;
		try {			
			ProductoSalidaBean producto = new ProductoSalidaBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(producto, request.getParameterMap());			
			lista = logisticaService.listarProductoSalida(producto);
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
	@RequestMapping(value = "/listarProductosStock", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProductosStock(HttpServletRequest request, HttpServletResponse response) {
		List<ProductoBean> lista = null;
		try {			
			ProductoBean productoBean = new ProductoBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(productoBean, request.getParameterMap());			
			lista = logisticaService.listarProductosStock(productoBean);
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
	@RequestMapping(value = "/listarLoteProductoSalida", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarLoteProductoSalida(HttpServletRequest request, HttpServletResponse response) {
		List<LoteProductoBean> lista = null;
		try {			
			LoteProductoBean loteProductoBean = new LoteProductoBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(loteProductoBean, request.getParameterMap());
			lista = logisticaService.listarLoteProductoSalida(loteProductoBean);
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
	@RequestMapping(value = "/grabarProductoOrdenSalida", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarProductoOrdenSalida(HttpServletRequest request, HttpServletResponse response) {
		ProductoSalidaBean producto = null;
		try {			
			ProductoSalidaBean productoSalidaBean = new ProductoSalidaBean();

			// Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);			
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Convierte los vacios en nulos en los decimales
			BigDecimalConverter con_decimal = new BigDecimalConverter(null);
			beanUtilsBean.getConvertUtils().register(con_decimal, BigDecimal.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(productoSalidaBean, request.getParameterMap());
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	productoSalidaBean.setUsuarioRegistro(usuarioBean.getUsuario());
			
			producto = logisticaService.grabarProductoSalida(productoSalidaBean);
			
			producto.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return producto;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/eliminarProductoOrdenSalida", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarProductoOrdenSalida(HttpServletRequest request, HttpServletResponse response) {
		ProductoSalidaBean producto = null;
		try {			
			String[] arrIdDetalleControlCalidad = request.getParameter("arrIdDetalleSalida").split(Constantes.UNDERLINE);
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			
			for (String codigo : arrIdDetalleControlCalidad) {				
				ProductoSalidaBean productoControlCalidadBean = new ProductoSalidaBean(getInteger(codigo));

	        	productoControlCalidadBean.setUsuarioRegistro(usuarioBean.getUsuario());
				
				producto = logisticaService.eliminarProductoSalida(productoControlCalidadBean);				
			}

			producto.setMensajeRespuesta(getMensaje(messageSource, "msg.info.eliminadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return producto;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarProductoManifiestoSalida", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProductoManifiestoSalida(HttpServletRequest request, HttpServletResponse response) {
		List<ProductoProyectoManifiestoBean> lista = null;
		try {			
			ProductoProyectoManifiestoBean productoProyectoManifiesto = new ProductoProyectoManifiestoBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(productoProyectoManifiesto, request.getParameterMap());
			lista = logisticaService.listarProductoManifiestoSalida(productoProyectoManifiesto);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	/**
	 * @param idSalida 
	 * @param arrIdProducto 
	 * @param arrCantidad 
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/grabarProductoManifiestoSalida", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarProductoManifiestoSalida(@RequestParam(value="idSalida") Integer idSalida,
												 @RequestParam(value="arrIdProducto[]") List<Integer> arrIdProducto,
												 @RequestParam(value="arrCantidad[]") List<BigDecimal> arrCantidad) {
		ProductoProyectoManifiestoBean producto = null;
		try {
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	for (int i = 0; i < arrIdProducto.size(); i++) {        		
        		ProductoProyectoManifiestoBean productoProyectoManifiestoBean = new ProductoProyectoManifiestoBean();
        		productoProyectoManifiestoBean.setIdSalida(idSalida);
        		productoProyectoManifiestoBean.setIdAlmacen(usuarioBean.getIdAlmacen());
        		productoProyectoManifiestoBean.setIdProducto(arrIdProducto.get(i));
        		productoProyectoManifiestoBean.setCantidad(arrCantidad.get(i));
            	productoProyectoManifiestoBean.setUsuarioRegistro(usuarioBean.getUsuario());    			
    			producto = logisticaService.grabarProductoManifiestoSalida(productoProyectoManifiestoBean);
        	}
        	
			producto.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return producto;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarDocumentoOrdenSalida", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarDocumentoOrdenSalida(HttpServletRequest request, HttpServletResponse response) {
		List<DocumentoSalidaBean> lista = null;
		try {			
			DocumentoSalidaBean documento = new DocumentoSalidaBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(documento, request.getParameterMap());
			documento.setTipoOrigen(Constantes.TIPO_ORIGEN_ALMACENES);
			lista = logisticaService.listarDocumentoSalida(documento);
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
	@RequestMapping(value = "/grabarDocumentoOrdenSalida", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarDocumentoOrdenSalida(HttpServletRequest request, HttpServletResponse response) {
		DocumentoSalidaBean documento = null;
		try {			
			DocumentoSalidaBean documentoSalidaBean = new DocumentoSalidaBean();
			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(documentoSalidaBean, request.getParameterMap());
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	documentoSalidaBean.setUsuarioRegistro(usuarioBean.getUsuario());
			
        	documento = logisticaService.grabarDocumentoSalida(documentoSalidaBean);
			
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
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/eliminarDocumentoOrdenSalida", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarDocumentoOrdenSalida(HttpServletRequest request, HttpServletResponse response) {
		DocumentoSalidaBean documento = null;
		try {			
			String[] arrIdDocumentoSalida = request.getParameter("arrIdDocumentoSalida").split(Constantes.UNDERLINE);
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
			for (String codigo : arrIdDocumentoSalida) {				
				DocumentoSalidaBean documentoSalidaBean = new DocumentoSalidaBean(getInteger(codigo));

	        	documentoSalidaBean.setUsuarioRegistro(usuarioBean.getUsuario());
				
	        	documento = logisticaService.eliminarDocumentoSalida(documentoSalidaBean);				
			}

			documento.setMensajeRespuesta(getMensaje(messageSource, "msg.info.eliminadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return documento;
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
	    	OrdenSalidaBean ordenSalidaBean = new OrdenSalidaBean();
	    	ordenSalidaBean.setCodigoAnio(verificaParametro(codigoAnio));
	    	ordenSalidaBean.setCodigoMes(verificaParametro(codigoMes));
	    	ordenSalidaBean.setIdAlmacen(idAlmacen);
	    	ordenSalidaBean.setIdMovimiento(idMovimiento);
	    	
	    	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			
        	ordenSalidaBean.setTipoOrigen(Constantes.TIPO_ORIGEN_ALMACENES);
        	ordenSalidaBean.setIdDdi(usuarioBean.getIdDdi());
        	ordenSalidaBean.setCodigoDdi(usuarioBean.getCodigoDdi());	    	
	    	
			List<OrdenSalidaBean> lista = logisticaService.listarOrdenSalida(ordenSalidaBean);
	    	
			String file_name = "OrdenSalida";
			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_XLS);
			
			ReporteOrdenSalida reporte = new ReporteOrdenSalida();
		    HSSFWorkbook wb = reporte.generaReporteExcelOrdenSalida(lista);
			
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
	 * @param anio 
	 * @param request 
	 * @param response
	 * @return Objeto.
	 */
	@RequestMapping(value = "/exportarPdf/{codigo}/{anio}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarPdf(@PathVariable("codigo") Integer codigo, @PathVariable("anio") String anio, HttpServletRequest request, HttpServletResponse response) {
	    try {
	    	
	    	OrdenSalidaBean ordenSalidaDatos = logisticaService.reportOrdenSalida(codigo, anio);
	    	OrdenSalidaBean ordenSalida = new OrdenSalidaBean();
	    	if (isEmpty(ordenSalidaDatos.getLstGeneral())) {
				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
			}else{
				ordenSalida = ordenSalidaDatos.getLstGeneral().get(0);
			}
	    	List<DocumentoSalidaBean> listaDocumento = null;
	    	if (!Utils.isEmpty(ordenSalidaDatos.getLstDocumento())) {
	    		listaDocumento = ordenSalidaDatos.getLstDocumento();
			}
	    	List<ProductoSalidaBean> listaProducto = null;
	    	if (!Utils.isEmpty(ordenSalidaDatos.getLstProducto())) {
	    		listaProducto = ordenSalidaDatos.getLstProducto();
			}
	    	
//	    	OrdenSalidaBean ordenSalida = logisticaService.obtenerRegistroOrdenSalida(codigo, anio);
//	    	ProductoSalidaBean producto = new ProductoSalidaBean();
//	    	producto.setIdSalida(codigo);
//	    	List<ProductoSalidaBean> listaProducto = logisticaService.listarProductoSalida(producto);
//	    	
//	    	DocumentoSalidaBean documento = new DocumentoSalidaBean();
//	    	documento.setIdSalida(codigo);
//	    	documento.setTipoOrigen(Constantes.TIPO_ORIGEN_ALMACENES);
//	    	List<DocumentoSalidaBean> listaDocumento = logisticaService.listarDocumentoSalida(documento);	    	

//	    	if(isEmpty(listaDocumento)){
//	    		DocumentoSalidaBean data = new DocumentoSalidaBean();
//				data.setIdSalida(0);
//				data.setIdTipoDocumento(0);
//				data.setNombreDocumento("-");	
//				data.setNroDocumento("-");
//				data.setFechaDocumento("-");
//				listaDocumento.add(data);
//			}
			
			if (ordenSalida==null || isEmpty(listaProducto)) {
				return Constantes.COD_VALIDACION_GENERAL;
			}			

			ExportarArchivo printer = new ExportarArchivo();
			StringBuilder jasperFile = new StringBuilder();
			jasperFile.append(getPath(request));
			jasperFile.append(File.separator);
			jasperFile.append(Constantes.REPORT_PATH_ALMACENES);
			jasperFile.append("Orden_Salida.jrxml");
			
			StringBuilder jasperFile1 = new StringBuilder();
			jasperFile1.append(getPath(request));
			jasperFile1.append(File.separator);
			jasperFile1.append(Constantes.REPORT_PATH_ALMACENES);
			jasperFile1.append("SR_Orden_Salida.jrxml");
			
			String ruta = getPath(request)+File.separator+Constantes.REPORT_PATH_ALMACENES;
//			if (producto.getFlagTipoProducto().equals(Constantes.ONE_STRING)) {
//				jasperFile.append("Control_Calidad_Alimentaria.jrxml");
//			} else {
//				jasperFile.append("Control_Calidad_No_Alimentaria.jrxml");
//			}
			
			JRBeanCollectionDataSource ListaDocumentos = new JRBeanCollectionDataSource(listaDocumento);
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
			
			StringBuilder logo_anulado = new StringBuilder();
			logo_anulado.append(getPath(request));
			logo_anulado.append(File.separator);
			logo_anulado.append(Constantes.IMAGE_INPUT_ANULADO_PATH);
			
			StringBuilder logo_noanulado = new StringBuilder();
			logo_noanulado.append(getPath(request));
			logo_noanulado.append(File.separator);
			logo_noanulado.append(Constantes.IMAGE_INPUT_NOANULADO_PATH);
			
			parameters.put("P_LOGO_CHECK_MIN", logo_check_min_path.toString());			
			parameters.put("P_NRO_ORDEN_INGRESO", ordenSalida.getNroOrdenSalida());
			parameters.put("P_DDI", ordenSalida.getNombreDdi());			
			parameters.put("P_FECHA_EMISION", ordenSalida.getFechaEmision());
			parameters.put("P_ALMACEN_ORIGEN", ordenSalida.getNombreAlmacen());
			parameters.put("P_OBSERVACION", ordenSalida.getObservacion());
			parameters.put("P_TIPO_MOVIMIENTO", ordenSalida.getNombreMovimiento());
			parameters.put("P_ALMACEN_DESTINO", ordenSalida.getNombreAlmacenDestino());
			parameters.put("LISTA_DOCUMENTOS", ListaDocumentos);
			parameters.put("SR_RUTA_DOCUMENTOS", ruta);
			parameters.put("D_NOMBRE_SISTEMA", ordenSalida.getNombreSistema());
			
			parameters.put("P_ESTADO", ordenSalida.getIdEstado().toString());
			parameters.put("P_LOGO_ANULADO", logo_anulado.toString());
			parameters.put("P_LOGO_NOANULADO", logo_noanulado.toString());
			
//			byte[] array = printer.exportPdf(jasperFile.toString(), parameters, listaProducto);
			byte[] array = printer.exportPdfSub(jasperFile.toString(),jasperFile1.toString(), parameters, listaProducto);
			InputStream input = new ByteArrayInputStream(array);
	        
	        String file_name = "Orden_Salida";
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
	    	
//	    	StringBuilder file_path = new StringBuilder();
//	    	file_path.append(getPath(request));
//	    	file_path.append(File.separator);
//	    	file_path.append(Constantes.UPLOAD_PATH_FILE_TEMP);
//	    	file_path.append(File.separator);
//	    	file_path.append(Calendar.getInstance().getTime().getTime());
//	    	file_path.append(Constantes.EXTENSION_FORMATO_PDF);
//	    	
//	    	String file_name = "Orden_Salida";
//			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_PDF);
//			
//			ReporteOrdenSalida reporte = new ReporteOrdenSalida();
//			reporte.generaPDFReporteSalidas(file_path.toString(), ordenSalida, listaProducto, listaDocumento);
//			
//			response.resetBuffer();
//            response.setContentType(Constantes.MIME_APPLICATION_PDF);
//            response.setHeader("Content-Disposition", "attachment; filename="+file_name);            
//			response.setHeader("Pragma", "no-cache");
//			response.setHeader("Cache-Control", "no-store");
//			response.setHeader("Pragma", "private");
//			response.setHeader("Set-Cookie", "fileDownload=true; path=/");
//			response.setDateHeader("Expires", 1);
//	    	
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			baos = convertPDFToByteArrayOutputStream(file_path.toString());
//			
//			// Captured backflow
//	        OutputStream out = response.getOutputStream();
//	        baos.writeTo(out); // We write in that flow
//	        out.flush(); // We emptied the flow
//	    	out.close(); // We close the flow
//	    	
//	    	File file_temp = new File(file_path.toString());
//    		if (file_temp.delete()) {
//    			LOGGER.info("[exportarPdf] "+file_temp.getName()+" se borra el archivo temporal.");
//    		} else {
//    			LOGGER.info("[exportarPdf] "+file_temp.getName()+" no se logró borrar el archivo temporal.");
//    		}
    		
	    	return Constantes.COD_EXITO_GENERAL;
	    } catch (Exception e) {
	    	LOGGER.error(e.getMessage(), e);
	    	return Constantes.COD_ERROR_GENERAL;
	    } 
	}
	
}
