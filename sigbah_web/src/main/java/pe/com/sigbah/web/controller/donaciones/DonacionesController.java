package pe.com.sigbah.web.controller.donaciones;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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

import pe.com.sigbah.common.bean.ControlCalidadBean;
import pe.com.sigbah.common.bean.DetalleProductoControlCalidadBean;
import pe.com.sigbah.common.bean.DocumentoControlCalidadBean;
import pe.com.sigbah.common.bean.DocumentoDonacionBean;
import pe.com.sigbah.common.bean.DocumentoDonacionIngresoBean;
import pe.com.sigbah.common.bean.DocumentoIngresoBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.ProductoBean;
import pe.com.sigbah.common.bean.ProductoControlCalidadBean;
import pe.com.sigbah.common.bean.ProductoDonacionBean;
import pe.com.sigbah.common.bean.ProductoIngresoBean;
import pe.com.sigbah.common.bean.RegionDonacionBean;
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


/**
 * @className: DonacionesController.java
 * @description: 
 * @date: 22/06/2017
 * @author: PC.
 */
@Controller
@RequestMapping("/donaciones/registro-donaciones")
public class DonacionesController extends BaseController {

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
        try {        
        	// Retorno los datos de session
           	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
           	DonacionesBean dona=new DonacionesBean();
           	dona.setCodigoAnio(generalService.obtenerAnioActual());
        	model.addAttribute("lista_anio", generalService.listarAnios());
        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
        	List<ItemBean> listaDdi = generalService.listarDdi(new ItemBean(usuarioBean.getIdDdi()));
        	model.addAttribute("lista_ddi", listaDdi);
        	//model.addAttribute("lista_est_donacion", donacionService.listarEstadoDonacionUsuario(new ItemBean(usuarioBean.getIdUsuario(), "DONACION")));
        	model.addAttribute("lista_region", generalService.listarRegion(new ItemBean()));
        	
        	model.addAttribute("lista_estado", generalService.listarEstadoDonacion(new ItemBean()));
        	model.addAttribute("txt_cod_ddi", usuarioBean.getCodigoDdi());
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	model.addAttribute("dona", getParserObject(dona));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));

        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar-donaciones";
    }
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarDonaciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarDonaciones(HttpServletRequest request, HttpServletResponse response) {
		List<DonacionesBean> lista = null;
		try {
			
			DonacionesBean donacionesBean = new DonacionesBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(donacionesBean, request.getParameterMap());	
			lista = donacionService.listarDonaciones(donacionesBean);
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
	@RequestMapping(value = "/mantenimientoDonaciones/{codigo}", method = RequestMethod.GET)
    public String mantenimientoDonaciones(@PathVariable("codigo") Integer codigo,@PathVariable("codigo") Integer dato, Model model) {
        try {
        	DonacionesBean donacionesBean = new DonacionesBean();
        	List<ItemBean> listaDee1=null;
        	DonacionesBean datoDonaciones=new DonacionesBean();
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

        	if (!isNullInteger(codigo)) {
        		System.out.println("ENTRO A MODIFICAR ");
        		donacionesBean = donacionService.obtenerDonacionXIdDonacion(codigo);
        		System.out.println("ENTRO A MODIFICAR "+donacionesBean.getFinalidad());
        		
        		donacionesBean.setNombreDdi(usuarioBean.getNombreDdi());
        		donacionesBean.setIdDdi(usuarioBean.getIdDdi());
            	donacionesBean.setCodigoDdi(usuarioBean.getCodigoDdi());
            	donacionesBean.setTextoCodigo(usuarioBean.getIdDdi()+"-"+donacionesBean.getCodigoDonacion());
        		//model.addAttribute("lista_chofer", generalService.listarChofer(new ItemBean(controlCalidad.getIdEmpresaTransporte())));
            	
            	datoDonaciones.setIdDdi(usuarioBean.getIdDdi());
            	System.out.println("TIPO DONANTE 1 "+donacionesBean.getTipoDonante());
            	datoDonaciones.setTipoDonante(donacionesBean.getTipoDonante());
            	
            	listaDee1 = generalService.listarDee(new ItemBean(donacionesBean.getIdDee()));
        		
        		RegionDonacionBean regionDonacionBean = new RegionDonacionBean();
        		regionDonacionBean.setIdDonacion(codigo);
        		model.addAttribute("lista_region_donacion", donacionService.listarRegionesXDonacion(regionDonacionBean));
//            	model.addAttribute("listaAlimentarios", getParserObject(listaAlimentarios));
//        		model.addAttribute("listaNoAlimentarios", getParserObject(listaAlimentarios));
        		
        	} else {
        		DonacionesBean parametros = new DonacionesBean();
            	String anioActual = generalService.obtenerAnioActual();
            	String codDdi = usuarioBean.getCodigoDdi();
            	Integer idDdi =usuarioBean.getIdDdi();
            	parametros.setCodigoAnio(anioActual);
            	parametros.setIdDdi(idDdi);
            	parametros.setCodigoDdi(codDdi);
            	DonacionesBean datosDonacion = donacionService.obtenerCodigoDonativo(parametros);
            	
            	donacionesBean.setIdDdi(idDdi);
            	donacionesBean.setCodigoDdi(codDdi);
            	donacionesBean.setCodigoDonacion(datosDonacion.getCodigoDonacion());
            	donacionesBean.setTextoCodigo(datosDonacion.getTextoCodigo());
            	donacionesBean.setCodigoAnio(anioActual);
            	donacionesBean.setNombreDdi(usuarioBean.getNombreDdi());
            	
            	
            	//enviar 0 para listar dee

            	//model.addAttribute("lista_dee", generalService.listarDee(new ItemBean(Constantes.ZERO_INT)));
            	
            	listaDee1 = generalService.listarDee(new ItemBean(Constantes.ONE_INT));

            	//model.addAttribute("lista_proce_pais", generalService.listarPais(new ItemBean(Constantes.ZERO_INT)));

            	datoDonaciones.setIdDdi(idDdi);
            	datoDonaciones.setTipoDonante("1");

        	}
        	System.out.println("TEXTO CODIGO: "+donacionesBean.getTextoCodigo());
        	
        	if (!Utils.isNullInteger(donacionesBean.getIdDonacion())) {
        		List<ItemBean> listaEstadoUsu=donacionService.mostrarEstadoDonacionUsuario(new ItemBean(donacionesBean.getIdDonacion()));
        		String tablaEstados=HtmlUtils.TablaEstadosXDonacion(listaEstadoUsu);
        		model.addAttribute("tabla_estados", tablaEstados);
        		System.out.println("ENTRO: tabla ESTADOS: ");
        		//model.addAttribute("lista_monedas", generalService.listarMoneda(new ItemBean(Constantes.ZERO_INT)));
        	}
        	model.addAttribute("lista_monedas", generalService.listarMoneda(new ItemBean(Constantes.ZERO_INT)));
        	
        	model.addAttribute("donaciones", getParserObject(donacionesBean));
        	System.out.println("IDDONACIOM "+donacionesBean.getIdDonacion());
        	model.addAttribute("lista_dee", generalService.listarDee(new ItemBean(Constantes.ZERO_INT)));
        	
        	model.addAttribute("lista_proce_pais", generalService.listarPais(new ItemBean(Constantes.ZERO_INT)));
        	
        	model.addAttribute("lista_donadores", donacionService.listarDonadores(datoDonaciones));
        	
        	model.addAttribute("lista_oficinas", generalService.listarOficinas(new ItemBean(usuarioBean.getIdDdi())));
        	
        	model.addAttribute("nombreDee", listaDee1.get(0).getDescripcion());
        	
        	model.addAttribute("lista_categoria", generalService.listarCategoria(new ItemBean(Constantes.THREE_INT)));
        	//falta pintar
        	model.addAttribute("lista_tipo_documento", generalService.listarTipoDocumento(new ItemBean(Constantes.ZERO_INT)));
        	
        	//Para estados
        	model.addAttribute("lista_estado", generalService.listarEstadoDonacion(new ItemBean()));
//
//        	model.addAttribute("lista_estado", generalService.listarEstado(new ItemBean(null, Constantes.THREE_INT)));
//        	
//        	model.addAttribute("lista_orden_compra", logisticaService.listarOrdenCompra());
//        	
//        	model.addAttribute("lista_tipo_control", generalService.listarTipoControlCalidad(new ItemBean()));
//        	
//        	model.addAttribute("lista_personal", generalService.listarPersonal(new ItemBean(usuarioBean.getIdDdi())));
//        	
//        	model.addAttribute("lista_proveedor", generalService.listarProveedor(new ItemBean()));
//        	  
//        	ItemBean parametroEmpresaTransporte = new ItemBean();
//        	parametroEmpresaTransporte.setIcodigo(usuarioBean.getIdDdi());
//        	parametroEmpresaTransporte.setIcodigoParam2(Constantes.ONE_INT);
//        	model.addAttribute("lista_empresa_transporte", generalService.listarEmpresaTransporte(parametroEmpresaTransporte));
//        	
//        	model.addAttribute("lista_producto", generalService.listarCatologoProductos(new ProductoBean(null, Constantes.FIVE_INT)));
//        	
//        	model.addAttribute("lista_tipo_documento", generalService.listarTipoDocumento(new ItemBean()));
//     
//        	
//        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "mantenimiento_donaciones";
    }
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarProductosXCategoria", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProductoXCategoria(HttpServletRequest request, HttpServletResponse response) {
		List<ProductoBean> lista = null;
		try {			
			ProductoBean producto = new ProductoBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(producto, request.getParameterMap());			
			lista = generalService.listarCatologoProductos(new ProductoBean(Constantes.ZERO_INT, producto.getIdCategoria()));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/listarDonadores", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarDonadores(HttpServletRequest request, HttpServletResponse response) {
		List<ItemBean> lista = null;
		try {			
			DonacionesBean datoDonaciones = new DonacionesBean();	
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(datoDonaciones, request.getParameterMap());
			datoDonaciones.setIdDdi(usuarioBean.getIdDdi());
			lista = donacionService.listarDonadores(datoDonaciones);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/listarPersonalOficina", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarPersonalOficina(HttpServletRequest request, HttpServletResponse response) {
		List<ItemBean> lista = null;
		try {			
			ItemBean item = new ItemBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(item, request.getParameterMap());			
			lista = generalService.listarPersonalOficina(item);
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
	@RequestMapping(value = "/grabarDonaciones", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarDonacion(HttpServletRequest request, HttpServletResponse response) {
		DonacionesBean donaciones = null;
		System.out.println("ENTRO A GUARDAR");
		try {			
			DonacionesBean donacionesBean = new DonacionesBean();
			
			// Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(donacionesBean, request.getParameterMap());
			System.out.println("ANIO: "+donacionesBean.getCodigoAnio());
			System.out.println("CODDONACION: "+donacionesBean.getCodigoDonacion());
			System.out.println("IDEEE: "+donacionesBean.getIdDee());
			System.out.println("TIPODONACION: "+donacionesBean.getTipoDonacion());
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	donacionesBean.setUsuarioRegistro(usuarioBean.getUsuario());
			
			if (!isNullInteger(donacionesBean.getIdDonacion())) {
				//donacionesBean =donacionService.obtenerDonacionXIdDonacion(donacionesBean.getIdDonacion());
				donaciones = donacionService.actualizarRegistroDonacion(donacionesBean);
				donaciones.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));				
			} else {			
				donaciones = donacionService.insertarRegistroDonacion(donacionesBean);			
			}
			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return donaciones;
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
	 * @param model 
	 * @return - Retorna a la vista JSP.
	 */
	@RequestMapping(value = "/ingreso", method = RequestMethod.GET)
    public String ingreso(Model model) {
        try {        
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

        	model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	List<ItemBean> listaDdi = generalService.listarDdi(new ItemBean(usuarioBean.getIdDdi()));
        	model.addAttribute("lista_ddi", listaDdi);
        	
        	if (!Utils.isEmpty(listaDdi) && listaDdi.size() == Constantes.ONE_INT) {
        		model.addAttribute("lista_almacen", generalService.listarAlmacen(new ItemBean(usuarioBean.getIdDdi())));
        	}
        	
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar-donaciones-ingreso";
    }
	 
	
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/grabarDonacionesfdgdfg", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarDonaciones(HttpServletRequest request, HttpServletResponse response) {
		DonacionesBean controlCalidad = null;
		try {			
			DonacionesBean donacionesBean = new DonacionesBean();
			System.out.println("ENTRO A GUARDAR");
			// Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(donacionesBean, request.getParameterMap());

			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	 
        	donacionesBean.setUsuarioRegistro(usuarioBean.getUsuario());
			
			if (!isNullInteger(donacionesBean.getIdDonacion())) {				
				controlCalidad = donacionService.actualizarDonaciones(donacionesBean);
				controlCalidad.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));				
			} else {			
				controlCalidad = donacionService.insertarDonaciones(donacionesBean);			
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return controlCalidad;
	}
	
	
	/////////////ARCHY/////////////////
	
	@RequestMapping(value = "/registroOrdenIngreso", method = RequestMethod.GET)
    public String registroOrdenIngreso(Model model) {
		System.out.println("REGISTRO OI");
//        try {
//        	ControlCalidadBean controlCalidad = new ControlCalidadBean();
//        	
//        	// Retorno los datos de session
//        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
//        	
//        	if (!isNullInteger(codigo)) {
//        		
//        		controlCalidad = logisticaService.obtenerRegistroControlCalidad(codigo);
//        		
////        		model.addAttribute("lista_chofer", generalService.listarChofer(new ItemBean(controlCalidad.getIdEmpresaTransporte())));
////        		
////        		List<ProductoControlCalidadBean> listaAlimentarios = new ArrayList<ProductoControlCalidadBean>(); // Cambiar
////
////            	model.addAttribute("listaAlimentarios", getParserObject(listaAlimentarios));
////        		model.addAttribute("listaNoAlimentarios", getParserObject(listaAlimentarios));
//        		
//        	} else {
//
//        		StringBuilder correlativo = new StringBuilder();
//        		correlativo.append(usuarioBean.getCodigoDdi());
//        		correlativo.append(Constantes.SEPARADOR);
//        		correlativo.append(usuarioBean.getCodigoAlmacen());
//        		correlativo.append(Constantes.SEPARADOR);
//        		
//        		ControlCalidadBean parametros = new ControlCalidadBean();
//        		String anioActual = generalService.obtenerAnioActual();
//        		parametros.setCodigoAnio(anioActual);
//        		parametros.setCodigoDdi(usuarioBean.getCodigoDdi());
//        		parametros.setIdAlmacen(usuarioBean.getIdAlmacen());        		
//        		//ControlCalidadBean respuestaCorrelativo = logisticaService.obtenerCorrelativoControlCalidad(parametros);
//        	      
//        	//	correlativo.append(respuestaCorrelativo.getNroControlCalidad());
//        		
//        		controlCalidad.setNroControlCalidad(correlativo.toString());        		
//        		
//        		ControlCalidadBean parametroAlmacenActivo = new ControlCalidadBean();
//        		parametroAlmacenActivo.setIdAlmacen(usuarioBean.getIdAlmacen());
//        		parametroAlmacenActivo.setTipo(Constantes.CODIGO_TIPO_ALMACEN);
//        		List<ControlCalidadBean> listaAlmacenActivo = logisticaService.listarAlmacenActivo(parametroAlmacenActivo);
//        		if (!isEmpty(listaAlmacenActivo)) {
//        			controlCalidad.setCodigoAnio(listaAlmacenActivo.get(0).getCodigoAnio());
//        			controlCalidad.setIdAlmacen(listaAlmacenActivo.get(0).getIdAlmacen());
//        			controlCalidad.setCodigoAlmacen(listaAlmacenActivo.get(0).getCodigoAlmacen());
//        			controlCalidad.setNombreAlmacen(listaAlmacenActivo.get(0).getNombreAlmacen());
//        			controlCalidad.setCodigoMes(listaAlmacenActivo.get(0).getCodigoMes());
//        		}
//        		
//            	controlCalidad.setIdDdi(usuarioBean.getIdDdi());
//        		controlCalidad.setCodigoDdi(usuarioBean.getCodigoDdi());
//        		controlCalidad.setNombreDdi(usuarioBean.getNombreDdi());
//        	}
//        	
//        	if (!Utils.isNullInteger(usuarioBean.getIdDdi())) {
//        		model.addAttribute("lista_almacen", generalService.listarAlmacen(new ItemBean(usuarioBean.getIdDdi())));
//        	}
//        	
//        	model.addAttribute("controlCalidad", getParserObject(controlCalidad));
//
//        	model.addAttribute("lista_estado", generalService.listarEstado(new ItemBean(null, Constantes.THREE_INT)));
//        	
//        	model.addAttribute("lista_orden_compra", logisticaService.listarOrdenCompra());
//        	
//        	model.addAttribute("lista_tipo_control", generalService.listarTipoControlCalidad(new ItemBean()));
//        	
//        	model.addAttribute("lista_personal", generalService.listarPersonal(new ItemBean(usuarioBean.getIdDdi())));
//        	
//        	model.addAttribute("lista_proveedor", generalService.listarProveedor(new ItemBean()));
//        	  
//        	ItemBean parametroEmpresaTransporte = new ItemBean();
//        	parametroEmpresaTransporte.setIcodigo(usuarioBean.getIdDdi());
//        	parametroEmpresaTransporte.setIcodigoParam2(Constantes.ONE_INT);
//        	model.addAttribute("lista_empresa_transporte", generalService.listarEmpresaTransporte(parametroEmpresaTransporte));
//        	
//        	model.addAttribute("lista_producto", generalService.listarCatologoProductos(new ProductoBean(null, Constantes.FIVE_INT)));
//        	
//        	model.addAttribute("lista_tipo_documento", generalService.listarTipoDocumento(new ItemBean()));
//     
//        	
//        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
//            
//        } catch (Exception e) {
//        	LOGGER.error(e.getMessage(), e);
//        	model.addAttribute("base", getBaseRespuesta(null));
//        }
        return "registrar-donaciones-ingreso";
    }
	
	/**
	 * @param model 
	 * @return - Retorna a la vista JSP.
	 */
	@RequestMapping(value = "/salida", method = RequestMethod.GET)
    public String salida(Model model) {
        try {        
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

        	model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	model.addAttribute("lista_movimiento", generalService.listarTipoMovimiento(new ItemBean(1,2)));
        		
        	List<ItemBean> listaDdi = generalService.listarDdi(new ItemBean(usuarioBean.getIdDdi()));
        	model.addAttribute("lista_ddi", listaDdi);
        	
        	if (!Utils.isEmpty(listaDdi) && listaDdi.size() == Constantes.ONE_INT) {
        		model.addAttribute("lista_almacen", generalService.listarAlmacen(new ItemBean(usuarioBean.getIdDdi())));
        	}
        	
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar-donaciones-salida";
    }
	
	@RequestMapping(value = "/registroOrdenSalida", method = RequestMethod.GET)
    public String registroOrdenSalida(Model model) {
		System.out.println("REGISTRO OS");
//        try {
//        	ControlCalidadBean controlCalidad = new ControlCalidadBean();
//        	
//        	// Retorno los datos de session
//        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
//        	
//        	if (!isNullInteger(codigo)) {
//        		
//        		controlCalidad = logisticaService.obtenerRegistroControlCalidad(codigo);
//        		
////        		model.addAttribute("lista_chofer", generalService.listarChofer(new ItemBean(controlCalidad.getIdEmpresaTransporte())));
////        		
////        		List<ProductoControlCalidadBean> listaAlimentarios = new ArrayList<ProductoControlCalidadBean>(); // Cambiar
////
////            	model.addAttribute("listaAlimentarios", getParserObject(listaAlimentarios));
////        		model.addAttribute("listaNoAlimentarios", getParserObject(listaAlimentarios));
//        		
//        	} else {
//
//        		StringBuilder correlativo = new StringBuilder();
//        		correlativo.append(usuarioBean.getCodigoDdi());
//        		correlativo.append(Constantes.SEPARADOR);
//        		correlativo.append(usuarioBean.getCodigoAlmacen());
//        		correlativo.append(Constantes.SEPARADOR);
//        		
//        		ControlCalidadBean parametros = new ControlCalidadBean();
//        		String anioActual = generalService.obtenerAnioActual();
//        		parametros.setCodigoAnio(anioActual);
//        		parametros.setCodigoDdi(usuarioBean.getCodigoDdi());
//        		parametros.setIdAlmacen(usuarioBean.getIdAlmacen());        		
//        		//ControlCalidadBean respuestaCorrelativo = logisticaService.obtenerCorrelativoControlCalidad(parametros);
//        	      
//        	//	correlativo.append(respuestaCorrelativo.getNroControlCalidad());
//        		
//        		controlCalidad.setNroControlCalidad(correlativo.toString());        		
//        		
//        		ControlCalidadBean parametroAlmacenActivo = new ControlCalidadBean();
//        		parametroAlmacenActivo.setIdAlmacen(usuarioBean.getIdAlmacen());
//        		parametroAlmacenActivo.setTipo(Constantes.CODIGO_TIPO_ALMACEN);
//        		List<ControlCalidadBean> listaAlmacenActivo = logisticaService.listarAlmacenActivo(parametroAlmacenActivo);
//        		if (!isEmpty(listaAlmacenActivo)) {
//        			controlCalidad.setCodigoAnio(listaAlmacenActivo.get(0).getCodigoAnio());
//        			controlCalidad.setIdAlmacen(listaAlmacenActivo.get(0).getIdAlmacen());
//        			controlCalidad.setCodigoAlmacen(listaAlmacenActivo.get(0).getCodigoAlmacen());
//        			controlCalidad.setNombreAlmacen(listaAlmacenActivo.get(0).getNombreAlmacen());
//        			controlCalidad.setCodigoMes(listaAlmacenActivo.get(0).getCodigoMes());
//        		}
//        		
//            	controlCalidad.setIdDdi(usuarioBean.getIdDdi());
//        		controlCalidad.setCodigoDdi(usuarioBean.getCodigoDdi());
//        		controlCalidad.setNombreDdi(usuarioBean.getNombreDdi());
//        	}
//        	
//        	if (!Utils.isNullInteger(usuarioBean.getIdDdi())) {
//        		model.addAttribute("lista_almacen", generalService.listarAlmacen(new ItemBean(usuarioBean.getIdDdi())));
//        	}
//        	
//        	model.addAttribute("controlCalidad", getParserObject(controlCalidad));
//
//        	model.addAttribute("lista_estado", generalService.listarEstado(new ItemBean(null, Constantes.THREE_INT)));
//        	
//        	model.addAttribute("lista_orden_compra", logisticaService.listarOrdenCompra());
//        	
//        	model.addAttribute("lista_tipo_control", generalService.listarTipoControlCalidad(new ItemBean()));
//        	
//        	model.addAttribute("lista_personal", generalService.listarPersonal(new ItemBean(usuarioBean.getIdDdi())));
//        	
//        	model.addAttribute("lista_proveedor", generalService.listarProveedor(new ItemBean()));
//        	  
//        	ItemBean parametroEmpresaTransporte = new ItemBean();
//        	parametroEmpresaTransporte.setIcodigo(usuarioBean.getIdDdi());
//        	parametroEmpresaTransporte.setIcodigoParam2(Constantes.ONE_INT);
//        	model.addAttribute("lista_empresa_transporte", generalService.listarEmpresaTransporte(parametroEmpresaTransporte));
//        	
//        	model.addAttribute("lista_producto", generalService.listarCatologoProductos(new ProductoBean(null, Constantes.FIVE_INT)));
//        	
//        	model.addAttribute("lista_tipo_documento", generalService.listarTipoDocumento(new ItemBean()));
//     
//        	
//        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
//            
//        } catch (Exception e) {
//        	LOGGER.error(e.getMessage(), e);
//        	model.addAttribute("base", getBaseRespuesta(null));
//        }
        return "registrar-donaciones-salida";
    }
	
	
	/**
	 * @param model 
	 * @return - Retorna a la vista JSP.
	 */
	@RequestMapping(value = "/guiaRemision", method = RequestMethod.GET)
    public String guiaRemision(Model model) {
        try {        
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

        	model.addAttribute("lista_anio", generalService.listarAnios());
        	
        	List<ItemBean> listaDdi = generalService.listarDdi(new ItemBean(usuarioBean.getIdDdi()));
        	model.addAttribute("lista_ddi", listaDdi);
        	
        	if (!Utils.isEmpty(listaDdi) && listaDdi.size() == Constantes.ONE_INT) {
        		model.addAttribute("lista_almacen", generalService.listarAlmacen(new ItemBean(usuarioBean.getIdDdi())));
        	}
        	
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
	@RequestMapping(value = "/listarSalidaDonaciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarSalidaDonaciones(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BUSCAR SALIDA DONACIONES");
		List<DonacionesBean> lista = null;
		try {			
			DonacionesBean donacionesBean = new DonacionesBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(donacionesBean, request.getParameterMap());			
			lista = donacionService.listarDonaciones(donacionesBean);
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
	@RequestMapping(value = "/grabarProductoDonacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarProductoDonacion(HttpServletRequest request, HttpServletResponse response) {
		ProductoDonacionBean productoDonacion = null;
		try {			
			ProductoDonacionBean productoDonacionBean = new ProductoDonacionBean();

			// Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);			
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Convierte los vacios en nulos en los decimales
			BigDecimalConverter con_decimal = new BigDecimalConverter(null);
			beanUtilsBean.getConvertUtils().register(con_decimal, BigDecimal.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(productoDonacionBean, request.getParameterMap());
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	productoDonacionBean.setUsuarioRegistro(usuarioBean.getUsuario());
        	System.out.println("IDDET: "+productoDonacionBean.getIdDetDonacion());
        	Integer idDetDona=productoDonacionBean.getIdDetDonacion()!=null?productoDonacionBean.getIdDetDonacion():0;
        	
        	if(idDetDona==0){
        		productoDonacion = donacionService.insertarProductoDonacion(productoDonacionBean);
        	}else{
        		productoDonacion = donacionService.actualizarProductoDonacion(productoDonacionBean);
        	}
			
        	
			
        	productoDonacion.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return productoDonacion;
	}
	
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarProductoDonacion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProductoDonacion(HttpServletRequest request, HttpServletResponse response) {
		List<ProductoDonacionBean> lista = null;
		try {			
			ProductoDonacionBean producto = new ProductoDonacionBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(producto, request.getParameterMap());			
			lista = donacionService.listarProductoDonacion(producto);
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
	@RequestMapping(value = "/eliminarProductoDonacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarProductoDonacion(HttpServletRequest request, HttpServletResponse response) {
		ProductoDonacionBean producto = null;
		try {			
			System.out.println("PARAMETRO : "+request.getParameter("idDetDonacion"));
			String[] arrIdDetalleControlCalidad = request.getParameter("idDetDonacion").split(Constantes.UNDERLINE);
			Integer idDonacion  = Integer.parseInt(request.getParameter("idDonacion"));
			for (String codigo : arrIdDetalleControlCalidad) {				
				ProductoDonacionBean productoDonacionBean = new ProductoDonacionBean(getInteger(codigo));

				// Retorno los datos de session
	        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
	        	
	        	productoDonacionBean.setUsuarioRegistro(usuarioBean.getUsuario());
				productoDonacionBean.setIdDonacion(idDonacion);
				producto = donacionService.eliminarProductoDonacion(productoDonacionBean);				
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
	@RequestMapping(value = "/listarDocumentoDonacion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarDocumentoDonacion(HttpServletRequest request, HttpServletResponse response) {
		List<DocumentoDonacionBean> lista = null;
		try {			
			DocumentoDonacionBean documento = new DocumentoDonacionBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(documento, request.getParameterMap());			
			lista = donacionService.listarDocumentoDonacion(documento);
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
	@RequestMapping(value = "/listarEstadoDonacion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarEstadosDonacion(HttpServletRequest request, HttpServletResponse response) {
		List<ItemBean> lista = null;
		try {			
			DonacionesBean estados = new DonacionesBean();	
			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(estados, request.getParameterMap());	

			lista = donacionService.mostrarEstadoDonacionUsuario(new ItemBean(estados.getIdDonacion()));
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
	@RequestMapping(value = "/listarRegionDonacion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarRegionDonacion(HttpServletRequest request, HttpServletResponse response) {
		List<RegionDonacionBean> lista = null;
		try {			
			RegionDonacionBean documento = new RegionDonacionBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(documento, request.getParameterMap());			
			lista = donacionService.listarRegionesXDonacion(documento);
			
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
	@RequestMapping(value = "/grabarDocumentoDonacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarDocumentoDonacion(HttpServletRequest request, HttpServletResponse response) {
		DocumentoDonacionBean documento = null;
		try {			
			DocumentoDonacionBean documentoDonacionBean = new DocumentoDonacionBean();
			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(documentoDonacionBean, request.getParameterMap());
			System.out.println("IDDOCUMENTO: :"+documentoDonacionBean.getIdDocumentoDonacion());
			System.out.println("IDDONACION: :"+documentoDonacionBean.getIdDonacion());
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	documentoDonacionBean.setUsuarioRegistro(usuarioBean.getUsuario());
        	if(documentoDonacionBean.getIdDocumentoDonacion().equals("") || documentoDonacionBean.getIdDocumentoDonacion().equals(0)){
            	documento = donacionService.insertarDocumentoDonacion(documentoDonacionBean);
        	}else{
            	documento = donacionService.actualizarDocumentoDonacion(documentoDonacionBean);	
        	}	
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
	@RequestMapping(value = "/eliminarDocumentoDonacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarDocumentoDonacion(HttpServletRequest request, HttpServletResponse response) {
		DocumentoDonacionBean documento = null;
		try {			
			String[] arrIdDocumentoDonacion = request.getParameter("arrIdDocumentoDonacion").split(Constantes.UNDERLINE);
			Integer idDonacion = Integer.parseInt(request.getParameter("idDonacion"));
			System.out.println("ARRAY: "+request.getParameter("arrIdDocumentoDonacion"));
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
			for (String codigo : arrIdDocumentoDonacion) {				
				DocumentoDonacionBean documentoDonacionBean = new DocumentoDonacionBean();
				System.out.println("ARRAYddd: "+idDonacion+"--"+codigo);
				documentoDonacionBean.setIdDonacion(idDonacion);
				documentoDonacionBean.setIdDocumentoDonacion(getInteger(codigo));
	        	documento = donacionService.eliminarDocumentoDonacion(documentoDonacionBean);				
			}

			documento.setMensajeRespuesta(getMensaje(messageSource, "msg.info.eliminadoOk"));				

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
	@RequestMapping(value = "/insertarRegionXDonacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object insertarRegionXDonacion(HttpServletRequest request, HttpServletResponse response) {
		RegionDonacionBean regionDonacion = null;
		try {			
			RegionDonacionBean regionDonacionBean = new RegionDonacionBean();

			// Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);			
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Convierte los vacios en nulos en los decimales
			BigDecimalConverter con_decimal = new BigDecimalConverter(null);
			beanUtilsBean.getConvertUtils().register(con_decimal, BigDecimal.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(regionDonacionBean, request.getParameterMap());
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	regionDonacionBean.setUsuarioRegistro(usuarioBean.getUsuario());
			
        	regionDonacion = donacionService.insertarRegionDonacion(regionDonacionBean);
			
        	regionDonacion.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return regionDonacion;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/eliminarRegionDonacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarRegionDonacion(HttpServletRequest request, HttpServletResponse response) {
		RegionDonacionBean region = null;
		try {			
			Integer idDonacion = Integer.parseInt(request.getParameter("idDonacion"));
			Integer idRegion = Integer.parseInt(request.getParameter("idRegion"));
			
			RegionDonacionBean regionDonacionBean = new RegionDonacionBean();
			regionDonacionBean.setIdDonacion(idDonacion);
			regionDonacionBean.setIdRegion(idRegion);
			region = donacionService.eliminarRegionDonacion(regionDonacionBean);				

			region.setMensajeRespuesta(getMensaje(messageSource, "msg.info.eliminadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return region;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/actualizarEstadoDonacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object actualizarEstadoDonacion(HttpServletRequest request, HttpServletResponse response) {
		DonacionesBean donacion = new DonacionesBean();
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			Integer idDonacion = Integer.parseInt(request.getParameter("idDonacion"));
			Integer idEstado = Integer.parseInt(request.getParameter("idEstado"));
			String observacion = (request.getParameter("observacion"));
			System.out.println("ESTADO: "+idEstado);
			donacion.setIdEstado(idEstado);
			donacion.setIdDonacion(idDonacion);
			donacion.setUsuarioRegistro(usuarioBean.getUsuario());
			donacion.setObservacion(observacion);
			donacion = donacionService.actualizarEstadoDonacion(donacion);
			donacion.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));				

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return donacion;
	}
	
	/**
	 * @param codigoAnio 
	 * @param codigoDdi 
	 * @param codigoAlmacen
	 * @param response
	 * @return Objeto.
	 */
	@RequestMapping(value = "/exportarExcel/{codigoAnio}/{codigoDdi}/{codigoMes}/{codigoEstado}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcel(@PathVariable("codigoAnio") String codigoAnio, 
								@PathVariable("codigoDdi") String codigoDdi, 
								@PathVariable("codigoMes") String codigoMes, 
								@PathVariable("codigoEstado") String codigoEstado, 
								HttpServletResponse response) {
	    try {
	    	DonacionesBean donacionesBean = new DonacionesBean();
	    	donacionesBean.setCodigoAnio(verificaParametro(codigoAnio));
	    	donacionesBean.setCodigoDdi(verificaParametro(codigoDdi));
	    	donacionesBean.setCodigoMes(verificaParametro(codigoMes));
	    	donacionesBean.setCodigoEstado(verificaParametro(codigoEstado));
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
	@RequestMapping(value = "/exportarPdf/{codigo}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarPdf(@PathVariable("codigo") Integer codigo, HttpServletRequest request, HttpServletResponse response) {
	    try {
			List<DonacionesBean> lista = donacionService.listarReporteDonacion(codigo);
			List<ProductoDonacionBean> listaProductos = donacionService.listarReporteDonacionProductos(codigo);
			List<RegionDonacionBean> listaRegiones = donacionService.listarReporteDonacionRegiones(codigo);
//			if(isEmpty(listaRegiones)){
//				RegionDonacionBean data = new RegionDonacionBean();
//				data.setIdDonacion(0);
//				data.setIdRegion(0);
//				data.setNombreRegion("-");	
//
//				listaRegiones.add(data);
//			}
			if (isEmpty(lista) || isEmpty(listaProductos)) {
				return Constantes.COD_VALIDACION_GENERAL;
			}			
			DonacionesBean producto = lista.get(0);
			System.out.println("GENERAL: "+producto.getNombreEstado()+" - "+producto.getNombreEstado());
			ExportarArchivo printer = new ExportarArchivo();
			StringBuilder jasperFile = new StringBuilder();
			jasperFile.append(getPath(request));
			jasperFile.append(File.separator);
			jasperFile.append(Constantes.REPORT_PATH_DONACIONES);
			jasperFile.append("Solicitud_Aprobacion.jrxml");
			
			StringBuilder jasperFile1 = new StringBuilder();
			jasperFile1.append(getPath(request));
			jasperFile1.append(File.separator);
			jasperFile1.append(Constantes.REPORT_PATH_DONACIONES);
			jasperFile1.append("SR_Solicitud_Aprobacion.jrxml");
//			jasperFile.append("Solicitud_Aprobacion.jasper");

			String ruta = getPath(request)+File.separator+Constantes.REPORT_PATH_DONACIONES;
			System.out.println("RUTA: "+ruta);

			JRBeanCollectionDataSource ListaRegiones = new JRBeanCollectionDataSource(listaRegiones);
			
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
			
			StringBuilder logo_checked_path = new StringBuilder();
			logo_checked_path.append(getPath(request));
			logo_checked_path.append(File.separator);
			logo_checked_path.append(Constantes.IMAGE_INPUT_CHECKED_REPORT_PATH);
			
			StringBuilder logo_unchecked_path = new StringBuilder();
			logo_unchecked_path.append(getPath(request));
			logo_unchecked_path.append(File.separator);
			logo_unchecked_path.append(Constantes.IMAGE_INPUT_CHECK_REPORT_PATH);
			parameters.put("P_LOGO_UNCHECKED", logo_unchecked_path.toString());
			parameters.put("P_LOGO_CHECKED", logo_checked_path.toString());
			parameters.put("P_LOGO_CHECK", logo_check_path.toString());
							
			System.out.println("CHECK: "+ logo_check_path.toString());
			StringBuilder logo_check_min_path = new StringBuilder();
			logo_check_min_path.append(getPath(request));
			logo_check_min_path.append(File.separator);
			logo_check_min_path.append(Constantes.IMAGE_CHECK_REPORT_PATH);
			parameters.put("P_LOGO_CHECK_MIN", logo_check_min_path.toString());			
			parameters.put("D_FECHA_EMISION", producto.getFechaEmision());
			parameters.put("D_FECHA_EVALUACION", producto.getFechaEvaluacion());
			parameters.put("D_NOM_DONANTE", producto.getNombreDonante());			
			parameters.put("D_NUM_DOCUMENTO", producto.getNumDocumento());
			parameters.put("D_FINALIDAD", producto.getFinalidad()!=null?producto.getFinalidad():"");
			parameters.put("D_BLOQUE_TEXTO1", producto.getTextoa()!=null?producto.getTextoa():"");
			parameters.put("D_BLOQUE_TEXTO2", producto.getTextob()!=null?producto.getTextob():"");
			parameters.put("D_OFICINA_RESPONSABLE", producto.getNombreOficina());
			parameters.put("D_ESTADO", producto.getNombreEstado());
			System.out.println("ESTADOOOOOOO: "+producto.getNombreEstado());
			parameters.put("D_NOMBRE_PERSONAL", producto.getNombrePersonal());
			parameters.put("LISTA_REGIONES", ListaRegiones);

			parameters.put("SR_RUTA_REGIONES", ruta);
			parameters.put("D_NOMBRE_SISTEMA", producto.getNombreSistema());
			parameters.put("D_VERSION_SISTEMA", producto.getVersionSistema());
			
			parameters.put("P_ESTADO", producto.getIdEstado().toString());
			parameters.put("P_LOGO_ANULADO", logo_anulado.toString());
			parameters.put("P_LOGO_NOANULADO", logo_noanulado.toString());

			byte[] array = printer.exportPdfSub(jasperFile.toString(),jasperFile1.toString(), parameters, listaProductos);
			InputStream input = new ByteArrayInputStream(array);
	        
	        String file_name = "FichaPropuestaDonacion";
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
	
	/////////////////////////////////
	
	@RequestMapping(value = "/listarEstadosPorUsuario", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarEstadosPorUsuario(HttpServletRequest request, HttpServletResponse response) {
		List<ItemBean> lista = null;
		try {			
			ItemBean itemBean = new ItemBean();	
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(itemBean, request.getParameterMap());
			itemBean.setIcodigo(usuarioBean.getIdUsuario());
			lista = donacionService.listarEstadoDonacionUsuario(itemBean);
			System.out.println("LISTA DON: "+lista.size());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	

}
