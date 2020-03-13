package pe.com.sigbah.web.controller.gestion_almacenes;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
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

import pe.com.sigbah.common.bean.CierreStockBean;
import pe.com.sigbah.common.bean.ControlCalidadBean;
import pe.com.sigbah.common.bean.DetalleProductoControlCalidadBean;
import pe.com.sigbah.common.bean.DocumentoControlCalidadBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.ProductoBean;
import pe.com.sigbah.common.bean.ProductoControlCalidadBean;
import pe.com.sigbah.common.bean.UsuarioBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.ExportarArchivo;
import pe.com.sigbah.common.util.HtmlUtils;
import pe.com.sigbah.service.AdministracionService;
import pe.com.sigbah.service.DonacionService;
import pe.com.sigbah.service.GeneralService;
import pe.com.sigbah.service.LogisticaService;
import pe.com.sigbah.web.controller.common.BaseController;
import pe.com.sigbah.web.report.gestion_almacenes.ReporteControlCalidad;

/**
 * @className: ControlCalidadController.java
 * @description: 
 * @date: 17 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
@Controller
@RequestMapping("/gestion-almacenes/control-calidad")
public class ControlCalidadController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private LogisticaService logisticaService;
	
	@Autowired 
	private GeneralService generalService;
	
	@Autowired 
	private AdministracionService administracionService;
	
	@Autowired 
	private DonacionService donacionService;
	
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
		        	
		        	model.addAttribute("lista_ddi", generalService.listarDdi(new ItemBean(usuarioBean.getIdDdi())));
		        	
		        	model.addAttribute("lista_almacen", generalService.listarAlmacen(new ItemBean(usuarioBean.getIdDdi())));
		        	
		        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
		        	model.addAttribute("lista_tipo_control", generalService.listarTipoControlCalidad(new ItemBean()));
		        	
		        	ControlCalidadBean controlCalidad = new ControlCalidadBean();
		        	controlCalidad.setIdDdi(usuarioBean.getIdDdi());
		        	controlCalidad.setCodigoDdi(usuarioBean.getCodigoDdi());
		        	controlCalidad.setNombreDdi(usuarioBean.getNombreDdi());
		        	ControlCalidadBean parametroAlmacenActivo = new ControlCalidadBean();
		    		parametroAlmacenActivo.setIdAlmacen(usuarioBean.getIdAlmacen());
		    		parametroAlmacenActivo.setTipo(Constantes.CODIGO_TIPO_ALMACEN);
		//    		List<ControlCalidadBean> listaAlmacenActivo = logisticaService.listarAlmacenActivo(parametroAlmacenActivo);
		//    		if (!isEmpty(listaAlmacenActivo)) {
		//    			controlCalidad.setCodigoAnio(listaAlmacenActivo.get(0).getCodigoAnio());
		//    			controlCalidad.setIdAlmacen(listaAlmacenActivo.get(0).getIdAlmacen());
		//    			controlCalidad.setCodigoAlmacen(listaAlmacenActivo.get(0).getCodigoAlmacen());
		//    			controlCalidad.setNombreAlmacen(listaAlmacenActivo.get(0).getNombreAlmacen());
		//    			controlCalidad.setCodigoMes(listaAlmacenActivo.get(0).getCodigoMes());
		//    		}
		    		CierreStockBean cierreStock = new CierreStockBean();
		    		cierreStock = administracionService.mesTrabajoActivo(usuarioBean.getIdAlmacen(), Constantes.TIPO_ORIGEN_ALMACENES);
		    		if (cierreStock.getCodigoAnio()!=null) {
		    			controlCalidad.setCodigoAnio(cierreStock.getCodigoAnio());
		    			controlCalidad.setIdAlmacen(cierreStock.getIdAlmacen());
		    			
		    			controlCalidad.setNombreAlmacen(cierreStock.getNombreAlmacen());
		    			controlCalidad.setCodigoMes(cierreStock.getCodigoMes());
		    		}
		    		controlCalidad.setCodigoAlmacen(usuarioBean.getCodigoAlmacen());
		    		model.addAttribute("controlCalidad", getParserObject(controlCalidad));
		    		model.addAttribute("anioActivo", cierre.getCodigoAnio());
		    		model.addAttribute("mesActivo", cierre.getCodigoMes());
		        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
		        	
		        	ruta="listar_control_calidad";
           		}
           	}
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", utils.mostrarMenuUsuario(administracionService.mostrarMenuUsuario(usuarioBean.getIdUsuario())));
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
	@RequestMapping(value = "/listarControlCalidad", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarControlCalidad(HttpServletRequest request, HttpServletResponse response) {
		List<ControlCalidadBean> lista = null;
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			ControlCalidadBean controlCalidadBean = new ControlCalidadBean();	
			// Copia los parametros del cliente al objeto
			controlCalidadBean.setIdDdi(usuarioBean.getIdDdi());
			BeanUtils.populate(controlCalidadBean, request.getParameterMap());			
			lista = logisticaService.listarControlCalidad(controlCalidadBean);
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
	@RequestMapping(value = "/mantenimientoControlCalidad/{codigo}", method = RequestMethod.GET)
    public String mantenimientoControlCalidad(@PathVariable("codigo") Integer codigo, Model model) {
        try {
        	ControlCalidadBean controlCalidad = new ControlCalidadBean();
        	
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	Integer idDdi=usuarioBean.getIdDdi();
        	String anioActual1 = generalService.obtenerAnioActual();
        	if (!isNullInteger(codigo)) {
        		
        		controlCalidad = logisticaService.obtenerRegistroControlCalidad(codigo);
        		
        		model.addAttribute("lista_chofer", generalService.listarChofer(new ItemBean(controlCalidad.getIdEmpresaTransporte())));
        		
        	} else {

        		StringBuilder correlativo = new StringBuilder();
        		correlativo.append(usuarioBean.getCodigoDdi());
        		correlativo.append(Constantes.SEPARADOR);
        		correlativo.append(usuarioBean.getCodigoAlmacen());
        		correlativo.append(Constantes.SEPARADOR);
        		
        		ControlCalidadBean parametros = new ControlCalidadBean();
        		String anioActual = generalService.obtenerAnioActual();
        		parametros.setCodigoAnio(anioActual);
        		parametros.setCodigoDdi(usuarioBean.getCodigoDdi());
        		parametros.setIdAlmacen(usuarioBean.getIdAlmacen());        		
        		ControlCalidadBean respuestaCorrelativo = logisticaService.obtenerCorrelativoControlCalidad(parametros);
      
        		correlativo.append(respuestaCorrelativo.getNroControlCalidad());
        		
        		controlCalidad.setNroControlCalidad(correlativo.toString());        		
        		
        		ControlCalidadBean parametroAlmacenActivo = new ControlCalidadBean();
        		parametroAlmacenActivo.setIdAlmacen(usuarioBean.getIdAlmacen());
        		parametroAlmacenActivo.setTipo(Constantes.CODIGO_TIPO_ALMACEN);
//        		List<ControlCalidadBean> listaAlmacenActivo = logisticaService.listarAlmacenActivo(parametroAlmacenActivo);
//        		if (!isEmpty(listaAlmacenActivo)) {
//        			controlCalidad.setCodigoAnio(listaAlmacenActivo.get(0).getCodigoAnio());
//        			controlCalidad.setIdAlmacen(listaAlmacenActivo.get(0).getIdAlmacen());
//        			controlCalidad.setCodigoAlmacen(listaAlmacenActivo.get(0).getCodigoAlmacen());
//        			controlCalidad.setNombreAlmacen(listaAlmacenActivo.get(0).getNombreAlmacen());
//        			controlCalidad.setCodigoMes(listaAlmacenActivo.get(0).getCodigoMes());
//        		}
        		CierreStockBean cierreStock = new CierreStockBean();
        		cierreStock = administracionService.mesTrabajoActivo(usuarioBean.getIdAlmacen(), Constantes.TIPO_ORIGEN_ALMACENES);
        		if (cierreStock.getCodigoAnio()!=null) {
        			controlCalidad.setCodigoAnio(cierreStock.getCodigoAnio());
        			controlCalidad.setIdAlmacen(cierreStock.getIdAlmacen());
        			
        			controlCalidad.setNombreAlmacen(cierreStock.getNombreAlmacen());
        			controlCalidad.setCodigoMes(cierreStock.getCodigoMes());
        		}
        		controlCalidad.setCodigoAlmacen(usuarioBean.getCodigoAlmacen());
            	controlCalidad.setIdDdi(usuarioBean.getIdDdi());
        		controlCalidad.setCodigoDdi(usuarioBean.getCodigoDdi());
        		controlCalidad.setNombreDdi(usuarioBean.getNombreDdi());
        	}
        	
        	model.addAttribute("lista_almacen", generalService.listarAlmacen(new ItemBean()));
        	
        	model.addAttribute("controlCalidad", getParserObject(controlCalidad));
        	System.out.println("DATOS: "+controlCalidad.getConclusiones());

        	model.addAttribute("lista_estado", generalService.listarEstado(new ItemBean(null, Constantes.FOUR_INT)));
        	
        	model.addAttribute("lista_orden_compra", logisticaService.listarOrdenCompra());
        	
        	model.addAttribute("lista_tipo_control", generalService.listarTipoControlCalidad(new ItemBean()));
        	
        	model.addAttribute("lista_personal", generalService.listarPersonal(new ItemBean(usuarioBean.getIdDdi())));
        	
        	model.addAttribute("lista_proveedor", generalService.listarProveedor(new ItemBean()));
        	
        	ItemBean parametroEmpresaTransporte = new ItemBean();
        	parametroEmpresaTransporte.setIcodigo(usuarioBean.getIdDdi());
        	parametroEmpresaTransporte.setIcodigoParam2(Constantes.ONE_INT);
        	model.addAttribute("lista_empresa_transporte", generalService.listarEmpresaTransporte(parametroEmpresaTransporte));
        	
//        	model.addAttribute("lista_producto", generalService.listarCatologoProductos(new ProductoBean(null, Constantes.FIVE_INT)));
        	
        	model.addAttribute("lista_tipo_documento", generalService.listarTipoDocumento(new ItemBean()));
     
        	model.addAttribute("lista_categoria", generalService.listarCategoria(new ItemBean(Constantes.TWO_INT)));
        	
        	model.addAttribute("lista_codigo_donacion", donacionService.listarCodigoDonacion(new ItemBean(idDdi,anioActual1)));
        	
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", utils.mostrarMenuUsuario(administracionService.mostrarMenuUsuario(usuarioBean.getIdUsuario())));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "mantenimiento_control_calidad";
    }
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarChofer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarChofer(HttpServletRequest request, HttpServletResponse response) {
		List<ItemBean> lista = null;
		try {			
			ItemBean item = new ItemBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(item, request.getParameterMap());			
			lista = generalService.listarChofer(item);
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
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/grabarControlCalidad", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarControlCalidad(HttpServletRequest request, HttpServletResponse response) {
		ControlCalidadBean controlCalidad = new ControlCalidadBean();
		try {			
			ControlCalidadBean controlCalidadBean = new ControlCalidadBean();
			
			// Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(controlCalidadBean, request.getParameterMap());

			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	controlCalidadBean.setUsuarioRegistro(usuarioBean.getUsuario());
        	
        	ProductoBean productoBean = new ProductoBean();
			productoBean.setNroOrden(controlCalidadBean.getNroOrdenCompra());
			List<ProductoBean> listaOrdenCompra = logisticaService.nroProductosXCompra(productoBean);
			
			if(listaOrdenCompra.size()>0){
				String mensajee="Existen productos de la orden de compra que no están en el catálogo de Productos, vaya la opción “Catálogo de productos” y proceda a registrarlos. <br/>";
				for(int i=0;i<listaOrdenCompra.size();i++){
					mensajee+=(i+1)+". "+listaOrdenCompra.get(i).getNombreProducto()+"<br/>";
				}
				controlCalidad.setMensajeRespuesta(mensajee);
				controlCalidad.setCodigoRespuesta("03");
			}else{
				if (!isNullInteger(controlCalidadBean.getIdControlCalidad())) {				
					controlCalidad = logisticaService.actualizarRegistroControlCalidad(controlCalidadBean);				
				} else {			
					controlCalidad = logisticaService.insertarRegistroControlCalidad(controlCalidadBean);			
				}
				controlCalidad.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return controlCalidad;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarProductoControlCalidad", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProductoControlCalidad(HttpServletRequest request, HttpServletResponse response) {
		List<ProductoControlCalidadBean> lista = null;
		try {			
			ProductoControlCalidadBean producto = new ProductoControlCalidadBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(producto, request.getParameterMap());			
			lista = logisticaService.listarProductoControlCalidad(producto);
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
	@RequestMapping(value = "/grabarProductoControlCalidad", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarProductoControlCalidad(HttpServletRequest request, HttpServletResponse response) {
		ProductoControlCalidadBean producto = null;
		try {			
			ProductoControlCalidadBean productoControlCalidadBean = new ProductoControlCalidadBean();

			// Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);			
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Convierte los vacios en nulos en los decimales
			BigDecimalConverter con_decimal = new BigDecimalConverter(null);
			beanUtilsBean.getConvertUtils().register(con_decimal, BigDecimal.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(productoControlCalidadBean, request.getParameterMap());
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	productoControlCalidadBean.setUsuarioRegistro(usuarioBean.getUsuario());
			
			producto = logisticaService.grabarProductoControlCalidad(productoControlCalidadBean);
			
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
	@RequestMapping(value = "/eliminarProductoControlCalidad", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarProductoControlCalidad(HttpServletRequest request, HttpServletResponse response) {
		ProductoControlCalidadBean producto = null;
		try {			
			String[] arrIdDetalleControlCalidad = request.getParameter("arrIdDetalleControlCalidad").split(Constantes.UNDERLINE);
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			
			for (String codigo : arrIdDetalleControlCalidad) {				
				ProductoControlCalidadBean productoControlCalidadBean = new ProductoControlCalidadBean(getInteger(codigo));

	        	productoControlCalidadBean.setUsuarioRegistro(usuarioBean.getUsuario());
				
				producto = logisticaService.eliminarProductoControlCalidad(productoControlCalidadBean);				
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
	@RequestMapping(value = "/listarDocumentoControlCalidad", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarDocumentoControlCalidad(HttpServletRequest request, HttpServletResponse response) {
		List<DocumentoControlCalidadBean> lista = null;
		try {			
			DocumentoControlCalidadBean documento = new DocumentoControlCalidadBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(documento, request.getParameterMap());			
			lista = logisticaService.listarDocumentoControlCalidad(documento);
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
	@RequestMapping(value = "/grabarDocumentoControlCalidad", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarDocumentoControlCalidad(HttpServletRequest request, HttpServletResponse response) {
		DocumentoControlCalidadBean documento = null;
		try {			
			DocumentoControlCalidadBean documentoControlCalidadBean = new DocumentoControlCalidadBean();
			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(documentoControlCalidadBean, request.getParameterMap());
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	documentoControlCalidadBean.setUsuarioRegistro(usuarioBean.getUsuario());
			
        	documento = logisticaService.grabarDocumentoControlCalidad(documentoControlCalidadBean);
			
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
	@RequestMapping(value = "/eliminarDocumentoControlCalidad", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarDocumentoControlCalidad(HttpServletRequest request, HttpServletResponse response) {
		DocumentoControlCalidadBean documento = null;
		try {			
			String[] arrIdDetalleControlCalidad = request.getParameter("arrIdDocumentoControlCalidad").split(Constantes.UNDERLINE);
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
			for (String codigo : arrIdDetalleControlCalidad) {				
				DocumentoControlCalidadBean documentoControlCalidadBean = new DocumentoControlCalidadBean(getInteger(codigo));

	        	documentoControlCalidadBean.setUsuarioRegistro(usuarioBean.getUsuario());
				
	        	documento = logisticaService.eliminarDocumentoControlCalidad(documentoControlCalidadBean);				
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
	 * @param codigoDdi 
	 * @param codigoAlmacen
	 * @param response
	 * @return Objeto.
	 */
	@RequestMapping(value = "/exportarExcel/{codigoAnio}/{codigoDdi}/{codigoAlmacen}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcel(@PathVariable("codigoAnio") String codigoAnio, 
								@PathVariable("codigoDdi") String codigoDdi, 
								@PathVariable("codigoAlmacen") String codigoAlmacen, 
								HttpServletResponse response) {
	    try {
	    	ControlCalidadBean controlCalidadBean = new ControlCalidadBean();
			controlCalidadBean.setCodigoAnio(verificaParametro(codigoAnio));
			controlCalidadBean.setCodigoDdi(verificaParametro(codigoDdi));
			controlCalidadBean.setCodigoAlmacen(verificaParametro(codigoAlmacen));
			List<ControlCalidadBean> lista = logisticaService.listarControlCalidad(controlCalidadBean);
	    	
			String file_name = "Reporte_Control_Calidad";
			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_XLS);
			
			ReporteControlCalidad reporte = new ReporteControlCalidad();
		    HSSFWorkbook wb = reporte.generaReporteExcelControlCalidad(lista);
			
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
	@RequestMapping(value = "/exportarPdf/{codigo}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarPdf(@PathVariable("codigo") Integer codigo, HttpServletRequest request, HttpServletResponse response) {
	    try {
			List<DetalleProductoControlCalidadBean> lista = logisticaService.listarDetalleProductoControlCalidad(codigo);
			if (isEmpty(lista)) {
				return Constantes.COD_VALIDACION_GENERAL; // Sin registros asociados
			}			
			DetalleProductoControlCalidadBean producto = lista.get(0);

			ExportarArchivo printer = new ExportarArchivo();
			StringBuilder jasperFile = new StringBuilder();
			jasperFile.append(getPath(request));
			jasperFile.append(File.separator);
			jasperFile.append(Constantes.REPORT_PATH_ALMACENES);
			if (producto.getFlagTipoProducto().equals(Constantes.ONE_STRING)) {
				jasperFile.append("Control_Calidad_Alimentaria.jrxml");
			} else {
				jasperFile.append("Control_Calidad_No_Alimentaria.jrxml");
			}
			
			Map<String, Object> parameters = new HashMap<String, Object>();

			// Agregando los parámetros del reporte
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
			parameters.put("P_NRO_CONTROL_CALIDAD", producto.getNroControlCalidad());
			parameters.put("P_DDI", producto.getNombreDdi());			
			parameters.put("P_ALMACEN", producto.getNombreAlmacen());
			parameters.put("P_FECHA_EMISION", producto.getFechaEmision());
			parameters.put("P_TIPO_CONTROL", producto.getTipoControlCalidad());
			parameters.put("P_ALMACEN_ORIGEN_DESTINO", producto.getNombreAlmacen());
			parameters.put("P_PROVEEDOR", producto.getProveedorDestino());
			parameters.put("P_NRO_ORDEN_COMPRA", producto.getNroOrdenCompra());
			parameters.put("P_CONCLUSIONES", producto.getConclusiones());
			parameters.put("P_RECOMENDACIONES", producto.getRecomendaciones());

			byte[] array = printer.exportPdf(jasperFile.toString(), parameters, lista);
			InputStream input = new ByteArrayInputStream(array);
	        
	        String file_name = "Reporte_Control_Calidad";
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
	
	@RequestMapping(value = "/listarProductosXCategoriaControl", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProductosXCategoriaControl(HttpServletRequest request, HttpServletResponse response) {
		List<ProductoBean> lista = null;
		try {			
			ProductoBean producto = new ProductoBean();	
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(producto, request.getParameterMap());
			producto.setIdAlmacen(usuarioBean.getIdAlmacen());
			lista = logisticaService.listarCatologoProductosControl(producto);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
}
