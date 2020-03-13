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
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.ProductoBean;
import pe.com.sigbah.common.bean.ProductoControlCalidadBean;
import pe.com.sigbah.common.bean.ProductoDonacionBean;
import pe.com.sigbah.common.bean.ProductoDonacionIngresoBean;
import pe.com.sigbah.common.bean.ProductoIngresoBean;
import pe.com.sigbah.common.bean.RegionDonacionBean;
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
 * @className: DonacionesIngresoController.java
 * @description: 
 * @date: 20/07/2017
 * @author: ARCHY.
 */
@Controller
@RequestMapping("/donacionesIngreso/registro-donacionesIngreso")
public class DonacionesIngresoController extends BaseController {

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
           	
           	if(usuarioBean.getIdAlmacen()==null){
           		model.addAttribute("txt_mensaje", "Usted no tiene almacén de trabajo");
           		ruta = "noAlmacen";
           	}else{
           		CierreStockBean cierre = new CierreStockBean();
           		cierre = administracionService.mesTrabajoActivo(usuarioBean.getIdAlmacen(), Constantes.TIPO_ORIGEN_DONACIONES);
           		System.out.println("TRABAJO : "+cierre.getCodigoMes());
           		DonacionesIngresoBean donaIngreso=new DonacionesIngresoBean();
           		donaIngreso.setCodigoAnio(generalService.obtenerAnioActual());
           		if(cierre.getCodigoMes()==null){
           			ruta = "mesNoAbierto";
           		}else{
           			usuarioBean.setCodigoAnio(cierre.getCodigoAnio());
           			usuarioBean.setCodigoMes(cierre.getCodigoMes());
           			context().setAttribute("usuarioBean", usuarioBean, RequestAttributes.SCOPE_SESSION);
           			System.out.println("AÑO Y MES: "+usuarioBean.getCodigoAnio()+ " "+usuarioBean.getCodigoMes());
		        	model.addAttribute("lista_anio", generalService.listarAnios());
		        	model.addAttribute("lista_mes", generalService.listarMeses(new ItemBean()));
		        	List<ItemBean> listaDdi = generalService.listarDdi(new ItemBean(usuarioBean.getIdDdi()));
		        	model.addAttribute("lista_ddi", listaDdi);
		        	model.addAttribute("lista_est_donacion", donacionService.listarEstadoDonacionUsuario(new ItemBean(usuarioBean.getIdUsuario(), "DONACION")));
		        	model.addAttribute("lista_region", generalService.listarRegion(new ItemBean()));
		        	
		        	model.addAttribute("lista_movimiento", generalService.listarTipoMovimiento(new ItemBean(Constantes.ONE_INT,Constantes.ONE_INT)));
		        	
		        	//model.addAttribute("lista_estado", generalService.listarEstadoDonacion(new ItemBean()));
		        	model.addAttribute("txt_cod_ddi", usuarioBean.getCodigoDdi());
		        	model.addAttribute("mes_anio", cierre.getNombreMes()+" "+cierre.getCodigoAnio());
		        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
		        	model.addAttribute("donaIngreso", getParserObject(donaIngreso));
		        	ruta="listar-donaciones-ingreso";
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
	@RequestMapping(value = "/listarDonaciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarDonaciones(HttpServletRequest request, HttpServletResponse response) {
		List<DonacionesIngresoBean> lista = null;
		try {
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			DonacionesIngresoBean donacionesIngresoBean = new DonacionesIngresoBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(donacionesIngresoBean, request.getParameterMap());
			donacionesIngresoBean.setIdAlmacen(usuarioBean.getIdAlmacen());
			lista = donacionService.listarIngresoDonaciones(donacionesIngresoBean);
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
        	
        	DonacionesIngresoBean donacionesBean = new DonacionesIngresoBean();
        	List<ItemBean> listaDee1=null;
        	DonacionesIngresoBean datoDonaciones=new DonacionesIngresoBean();
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	String codDdi = usuarioBean.getCodigoDdi();
        	Integer idDdi =usuarioBean.getIdDdi();
        	Integer idAlmacen = usuarioBean.getIdAlmacen();
        	String codAlmacen = usuarioBean.getCodigoAlmacen();
        	String anioActual = generalService.obtenerAnioActual();
        	//para anio y ddi
        	donacionesBean.setAnioAceptacion(anioActual);
        	donacionesBean.setIdDdiAceptacion(usuarioBean.getIdDdi());
        	//Para año y mes de trabajo
        	donacionesBean.setAnio(usuarioBean.getCodigoAnio());
        	donacionesBean.setMes(usuarioBean.getCodigoMes());
        	
        	CierreStockBean cierre = new CierreStockBean();
       		cierre = administracionService.mesTrabajoActivo(usuarioBean.getIdAlmacen(), Constantes.TIPO_ORIGEN_DONACIONES);
        	if (!isNullInteger(codigo)) {
        	
        		donacionesBean = donacionService.obtenerDonacionIngresoXIdIngreso(codigo);
        		donacionesBean.setNombreTipoDonacion(donacionesBean.getNombreTipoDonacion()==null?null:donacionesBean.getNombreTipoDonacion().trim());
        		donacionesBean.setNombreDdi(usuarioBean.getNombreDdi());
        		donacionesBean.setIdDdi(usuarioBean.getIdDdi());
            	donacionesBean.setCodigoDdi(usuarioBean.getCodigoDdi());
            	donacionesBean.setTextoCodigo(usuarioBean.getIdDdi()+"-"+usuarioBean.getCodigoAlmacen()+"-"+donacionesBean.getNroOrdenIngreso());
            	
            	model.addAttribute("lista_productos_donacion", donacionService.listarProductosDonacion(donacionesBean));
            	
//            	donacionesBean.setTextoCodigo(usuarioBean.getIdDdi()+"-"+donacionesBean.getCodigoDonacion());
//        		//model.addAttribute("lista_chofer", generalService.listarChofer(new ItemBean(controlCalidad.getIdEmpresaTransporte())));
//            	
//            	datoDonaciones.setIdDdi(usuarioBean.getIdDdi());
//            	datoDonaciones.setTipoDonante(donacionesBean.getTipoDonante());
//            	
//            	listaDee1 = generalService.listarDee(new ItemBean(donacionesBean.getIdDee()));
//        		
//        		List<ProductoControlCalidadBean> listaAlimentarios = new ArrayList<ProductoControlCalidadBean>(); // Cambiar
//        		
//        		RegionDonacionBean regionDonacionBean = new RegionDonacionBean();
//        		regionDonacionBean.setIdDonacion(codigo);
//        		model.addAttribute("lista_region_donacion", donacionService.listarRegionesXDonacion(regionDonacionBean));
//            	model.addAttribute("listaAlimentarios", getParserObject(listaAlimentarios));
//        		model.addAttribute("listaNoAlimentarios", getParserObject(listaAlimentarios));
            	model.addAttribute("lista_codigo_donacion", donacionService.listarCodigoDonacion(new ItemBean(donacionesBean.getIdDdiAceptacion(),donacionesBean.getAnioAceptacion())));
        		
        	} else {
        		DonacionesIngresoBean parametros = new DonacionesIngresoBean();
            	
            	parametros.setCodigoAnio(anioActual);
            	parametros.setIdDdi(idDdi);
            	parametros.setCodigoDdi(codDdi);
            	parametros.setIdAlmacen(idAlmacen);
            	parametros.setCodAlmacen(codAlmacen);
            	DonacionesIngresoBean datosDonacion = donacionService.obtenerCorrelativoOrdenIngreso(parametros);
            	
            	donacionesBean.setIdDdi(idDdi);
            	donacionesBean.setCodigoDdi(codDdi);
            	//donacionesBean.setCodigoDonacion(datosDonacion.getCodigoDonacion());
            	donacionesBean.setTextoCodigo(datosDonacion.getCodIngreso());
            	donacionesBean.setCodigoAnio(usuarioBean.getCodigoAnio());
            	donacionesBean.setNombreDdi(usuarioBean.getNombreDdi());
            	donacionesBean.setNroOrdenIngreso("");
            	donacionesBean.setCodIngreso(datosDonacion.getCodIngreso());

            	listaDee1 = generalService.listarDee(new ItemBean(Constantes.ONE_INT));

            	//model.addAttribute("lista_proce_pais", generalService.listarPais(new ItemBean(Constantes.ZERO_INT)));
            	model.addAttribute("lista_codigo_donacion", donacionService.listarCodigoDonacion(new ItemBean(idDdi,usuarioBean.getCodigoAnio())));
            	datoDonaciones.setIdDdi(idDdi);
            	datoDonaciones.setTipoDonante("1");
            	
            	
        	}
        	
        	if (!Utils.isNullInteger(donacionesBean.getIdDonacion())) {
        		List<ItemBean> listaEstadoUsu=donacionService.mostrarEstadoDonacionUsuario(new ItemBean(donacionesBean.getIdDonacion()));
        		String tablaEstados=HtmlUtils.TablaEstadosXDonacion(listaEstadoUsu);
        		model.addAttribute("tabla_estados", tablaEstados);
        		
        		model.addAttribute("lista_monedas", generalService.listarMoneda(new ItemBean(Constantes.ZERO_INT)));
        	}
        	//////////////////
        	
        	model.addAttribute("lista_movimiento", generalService.listarTipoMovimiento(new ItemBean(Constantes.ONE_INT,Constantes.ONE_INT)));
        	
//        	model.addAttribute("lista_codigo_donacion", donacionService.listarCodigoDonacion(new ItemBean(idDdi,anioActual)));
        	
        	model.addAttribute("lista_control_calidad", donacionService.listarControCalidad(new ItemBean(anioActual,codDdi)));
        	
        	model.addAttribute("lista_almacen", generalService.listarAlmacen(new ItemBean(0)));
        	
        	model.addAttribute("lista_medio_transporte", generalService.listarMedioTransporte(new ItemBean()));
        	
        	model.addAttribute("lista_personal", generalService.listarPersonal(new ItemBean(idDdi)));
        	
        	model.addAttribute("lista_salida", donacionService.listarSalida(new ItemBean(idAlmacen)));
        	
        	
        	
        	/////////////////
        	
        	
        	model.addAttribute("donaciones", getParserObject(donacionesBean));
        	
        	model.addAttribute("lista_dee", generalService.listarDee(new ItemBean(Constantes.ZERO_INT)));
        	
        	model.addAttribute("lista_proce_pais", generalService.listarPais(new ItemBean(Constantes.ZERO_INT)));
        	
        	//model.addAttribute("lista_donadores", donacionService.listarDonadores(datoDonaciones));
        	
        	model.addAttribute("lista_oficinas", generalService.listarOficinas(new ItemBean(Constantes.ZERO_INT)));
        	
        	//model.addAttribute("nombreDee", listaDee1.get(0).getDescripcion());
        	
        	model.addAttribute("lista_categoria", generalService.listarCategoria(new ItemBean(Constantes.THREE_INT)));
        	//falta pintar
        	model.addAttribute("lista_tipo_documento", generalService.listarTipoDocumento(new ItemBean(Constantes.ZERO_INT)));
        	model.addAttribute("lista_anio", generalService.listarAnios());
       		
        	List<ItemBean> listaDdi = generalService.listarDdi(new ItemBean(0));
        	model.addAttribute("lista_ddi", listaDdi);
        	model.addAttribute("mes_anio", cierre.getNombreMes()+" "+cierre.getCodigoAnio());
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "registrar-donaciones-ingreso";
    }
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarEmpresaTransporte", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarEmpresaTransporte(HttpServletRequest request, HttpServletResponse response) {
		List<ItemBean> lista = null;
		try {			
			ItemBean item = new ItemBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(item, request.getParameterMap());
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	item.setIcodigo(usuarioBean.getIdDdi());
			lista = generalService.listarEmpresaTransporte(item);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/listarDatosTransporte", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarDatosTransporte(HttpServletRequest request, HttpServletResponse response) {
		ItemBean lista = null;
		try {			
			ItemBean item = new ItemBean();			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(item, request.getParameterMap());
			item.setIcodigoParam2(usuarioBean.getIdDdi());
			// Retorno los datos de session
			lista = donacionService.obtenerTransporte(item);
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
	@RequestMapping(value = "/listarCodigoDonacion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarCodigoDonacion(HttpServletRequest request, HttpServletResponse response) {
		List<DonacionesBean> lista = new ArrayList<DonacionesBean>();
		try {			
			ItemBean item = new ItemBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(item, request.getParameterMap());
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	System.out.println(item.getVcodigoParam2());
        	if(item.getVcodigoParam2().equals("11") || item.getVcodigoParam2().equals("12") || item.getVcodigoParam2().equals("13")){
        		lista = donacionService.listarCodigoDonacion(item);
        	}else{
        		item.setIcodigo(usuarioBean.getIdDdi());
        		item.setDescripcion(usuarioBean.getCodigoAnio());
        		lista = donacionService.listarCodigoDonacion(item);
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
	@RequestMapping(value = "/listarProductosAlGrabar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProductosAlGrabar(HttpServletRequest request, HttpServletResponse response) {
		List<ProductoDonacionBean> lista = null;
		try {			
			
			DonacionesIngresoBean donacionesBean = new DonacionesIngresoBean();		
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(donacionesBean, request.getParameterMap());
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			donacionesBean.setIdAlmacen(usuarioBean.getIdAlmacen());
			lista = donacionService.listarProductosDonacion(donacionesBean);
			
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
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/buscarCantidadProducto", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object buscarCantidadProducto(HttpServletRequest request, HttpServletResponse response) {
		List<ProductoDonacionBean> lista = null;
		try {			
			DonacionesIngresoBean donacionesBean = new DonacionesIngresoBean();
			
			DonacionesIngresoBean producto = new DonacionesIngresoBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(producto, request.getParameterMap());	
			donacionesBean = donacionService.obtenerDonacionIngresoXIdIngreso(producto.getIdIngreso());
			donacionesBean.setIdTipoMovimiento(producto.getIdTipoMovimiento());
			donacionesBean.setIdProducto(producto.getIdProducto());
			lista = donacionService.buscarCantidadProducto(donacionesBean);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}

	@RequestMapping(value = "/listarIngresoSalidas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarSalidas(HttpServletRequest request, HttpServletResponse response) {
		List<ItemBean> lista = null;
		try {			
			ItemBean item = new ItemBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(item, request.getParameterMap());	
			//model.addAttribute("lista_salida", donacionService.listarSalida(new ItemBean(idAlmacen)));
			lista = donacionService.listarSalida(new ItemBean(item.getIcodigo()));
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
	@RequestMapping(value = "/grabarDonacionesIngreso", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarDonacion(HttpServletRequest request, HttpServletResponse response) {
		DonacionesIngresoBean donaciones = null;
		System.out.println("ENTRO A GUARDAR");
		try {			
			DonacionesIngresoBean donacionesIngresoBean = new DonacionesIngresoBean();
			
			// Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(donacionesIngresoBean, request.getParameterMap());
			System.out.println("ANIO: "+donacionesIngresoBean.getCodigoAnio());
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	donacionesIngresoBean.setUsuarioRegistro(usuarioBean.getUsuario());
        	donacionesIngresoBean.setIdAlmacen(usuarioBean.getIdAlmacen());
        	donacionesIngresoBean.setCodAlmacen(usuarioBean.getCodigoAlmacen());
        	donacionesIngresoBean.setTipoOrigen("D");
        	donacionesIngresoBean.setIdDdi(usuarioBean.getIdDdi());
        	donacionesIngresoBean.setCodigoMes(usuarioBean.getCodigoMes());
			if (!isNullInteger(donacionesIngresoBean.getIdIngreso())) {
				//donacionesBean =donacionService.obtenerDonacionXIdDonacion(donacionesBean.getIdDonacion());
				donaciones = donacionService.actualizarRegistroDonacionIngreso(donacionesIngresoBean);
				donaciones.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));				
			} else {			
				donaciones = donacionService.insertarRegistroDonacionIngreso(donacionesIngresoBean);			
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
	@RequestMapping(value = "/grabarProductoDonacionIngreso", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarProductoDonacion(HttpServletRequest request, HttpServletResponse response) {
		ProductoDonacionIngresoBean productoDonacion = null;
		try {			
			ProductoDonacionIngresoBean productoDonacionBean = new ProductoDonacionIngresoBean();

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
        	productoDonacionBean.setIdDdi(usuarioBean.getIdDdi());
        	productoDonacionBean.setCodDdi(usuarioBean.getCodigoDdi());
        	productoDonacionBean.setIdAlmacen(usuarioBean.getIdAlmacen());
        	productoDonacionBean.setCodAlmacen(usuarioBean.getCodigoAlmacen());
        	productoDonacionBean.setCodigoAnio(usuarioBean.getCodigoAnio());
        	productoDonacionBean.setCodigoMes(usuarioBean.getCodigoMes());
        	System.out.println("IDINGRESODET: "+productoDonacionBean.getIdIngresoDet());
        	if(productoDonacionBean.getIdIngresoDet().equals("") || productoDonacionBean.getIdIngresoDet()==0){
        		productoDonacion = donacionService.insertarProductoDonacionIngreso(productoDonacionBean);
        	}else{
        		productoDonacion = donacionService.actualizarProductoDonacionIngreso(productoDonacionBean);
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
		List<ProductoDonacionIngresoBean> lista = null;
		try {			
			ProductoDonacionIngresoBean producto = new ProductoDonacionIngresoBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(producto, request.getParameterMap());			
			lista = donacionService.listarProductoDonacionIngreso(producto);

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
		ProductoDonacionIngresoBean producto = null;
		try {			
			System.out.println("PARAMETRO : "+request.getParameter("idIngresoDet"));
			String[] arrIdDetalleControlCalidad = request.getParameter("idIngresoDet").split(Constantes.UNDERLINE);
			for (String codigo : arrIdDetalleControlCalidad) {				
				ProductoDonacionIngresoBean productoDonacionBean = new ProductoDonacionIngresoBean();
				productoDonacionBean.setIdIngresoDet(Integer.parseInt(codigo));
				// Retorno los datos de session
	        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
	        	System.out.println("PARAMETROrrrr : "+productoDonacionBean.getIdIngresoDet());
	        	productoDonacionBean.setUsuarioRegistro(usuarioBean.getUsuario());
				
				producto = donacionService.eliminarProductoDonacionIngreso(productoDonacionBean);				
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
	@RequestMapping(value = "/listarDocumentoDonacionIngreso", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarDocumentoDonacion(HttpServletRequest request, HttpServletResponse response) {
		List<DocumentoIngresoBean> lista = null;
		try {			
			DocumentoIngresoBean documento = new DocumentoIngresoBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(documento, request.getParameterMap());			
			lista = donacionService.listarDocumentoDonacionIngreso(documento);
			
			for(int i=0;i<lista.size();i++){
				System.out.println("LISTA: "+lista.get(i).getObservacion());
				System.out.println("LISTA1: "+lista.get(i).getIdDocumentoIngreso());
				System.out.println("LISTA2: "+lista.get(i).getNroDocumento());
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
	@RequestMapping(value = "/grabarDocumentoDonacionIngreso", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarDocumentoDonacion(HttpServletRequest request, HttpServletResponse response) {
		DocumentoIngresoBean documento = null;
		try {			
			DocumentoIngresoBean documentoDonacionBean = new DocumentoIngresoBean();
			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(documentoDonacionBean, request.getParameterMap());
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	documentoDonacionBean.setUsuarioRegistro(usuarioBean.getUsuario());
        	System.out.println("IDDOCUMENTO: "+documentoDonacionBean.getIdDocumentoIngreso());
        	if(documentoDonacionBean.getIdDocumentoIngreso().equals("") || documentoDonacionBean.getIdDocumentoIngreso()==0){
        		documento = donacionService.insertarDocumentoDonacionIngreso(documentoDonacionBean);

        	}else{
        		documento = donacionService.actualizarDocumentoDonacionIngreso(documentoDonacionBean);

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
	@RequestMapping(value = "/eliminarDocumentoDonacionIngreso", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarDocumentoDonacion(HttpServletRequest request, HttpServletResponse response) {
		DocumentoIngresoBean documento = null;
		try {			
			String[] arrIdDocumentoDonacion = request.getParameter("arrIdDocumentoDonacion").split(Constantes.UNDERLINE);
			Integer idIngreso = Integer.parseInt(request.getParameter("idIngreso"));
			System.out.println("ARRAY: "+request.getParameter("arrIdDocumentoDonacion"));
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
			for (String codigo : arrIdDocumentoDonacion) {				
				DocumentoIngresoBean documentoDonacionBean = new DocumentoIngresoBean();
				System.out.println("ARRAYddd: "+idIngreso+"--"+codigo);
				documentoDonacionBean.setIdIngreso(idIngreso);
				documentoDonacionBean.setIdDocumentoIngreso(getInteger(codigo));
				documentoDonacionBean.setUsuarioRegistro(usuarioBean.getNombreUsuario());
	        	documento = donacionService.eliminarDocumentoIngresoDonacion(documentoDonacionBean);				
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
		DonacionesBean donacion = null;
		DonacionesBean donacion1 = null;
		try {			
			Integer idDonacion = Integer.parseInt(request.getParameter("idDonacion"));
			Integer idEstado = Integer.parseInt(request.getParameter("idEstado"));
			donacion = donacionService.obtenerDonacionXIdDonacion(idDonacion);	
			donacion.setIdEstado(idEstado);
			donacion1 = donacionService.actualizarRegistroDonacion(donacion);
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
	@RequestMapping(value = "/exportarPdf/{codigo}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarPdf(@PathVariable("codigo") Integer codigo, HttpServletRequest request, HttpServletResponse response) {
	    try {
	    	
			List<DonacionesIngresoBean> lista = donacionService.listarReporteDonacionIngreso(codigo);
			List<ProductoDonacionIngresoBean> listaProductos = donacionService.listarProductosReporteDonacionIngreso(codigo);
			List<DocumentoDonacionIngresoBean> listaDocumentos = donacionService.listarDocumentosReporteDonacionIngreso(codigo);
			
			System.out.println("DATOS_: "+listaDocumentos.size());
			if (isEmpty(lista) || isEmpty(listaProductos)) {
				return Constantes.COD_VALIDACION_GENERAL;
			}			
			DonacionesIngresoBean general = lista.get(0);
			ExportarArchivo printer = new ExportarArchivo();
			StringBuilder jasperFile = new StringBuilder();
			jasperFile.append(getPath(request));
			jasperFile.append(File.separator);
			jasperFile.append(Constantes.REPORT_PATH_DONACIONES);
			jasperFile.append("Orden_Ingreso.jrxml");
			
			StringBuilder jasperFile1 = new StringBuilder();
			jasperFile1.append(getPath(request));
			jasperFile1.append(File.separator);
			jasperFile1.append(Constantes.REPORT_PATH_DONACIONES);
			jasperFile1.append("SR_Orden_Ingreso.jrxml");
			
			StringBuilder logo_anulado = new StringBuilder();
			logo_anulado.append(getPath(request));
			logo_anulado.append(File.separator);
			logo_anulado.append(Constantes.IMAGE_INPUT_ANULADO_PATH);
			
			StringBuilder logo_noanulado = new StringBuilder();
			logo_noanulado.append(getPath(request));
			logo_noanulado.append(File.separator);
			logo_noanulado.append(Constantes.IMAGE_INPUT_NOANULADO_PATH);
			
			String ruta = getPath(request)+File.separator+Constantes.REPORT_PATH_DONACIONES;
//			if (producto.getFlagTipoProducto().equals(Constantes.ONE_STRING)) {
//				jasperFile.append("Control_Calidad_Alimentaria.jrxml");
//			} else {
//				jasperFile.append("Control_Calidad_No_Alimentaria.jrxml");
//			}
			JRBeanCollectionDataSource ListaDocumentos = new JRBeanCollectionDataSource(listaDocumentos);
			
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
			parameters.put("P_NRO_ORDEN_INGRESO", general.getNroOrdenIngreso());
			parameters.put("P_DDI", general.getNombreDdi());			
			parameters.put("P_FECHA_EMISION", general.getFechaEmision());
			parameters.put("P_ALMACEN_ORIGEN", general.getNombreAlmacen());
			parameters.put("P_OBSERVACION", general.getObservacion());
			parameters.put("P_TIPO_MOVIMIENTO", general.getNombreMovimiento());
			parameters.put("D_NOMBRE_SISTEMA", general.getNombreSistema());
			parameters.put("LISTA_DOCUMENTOS", ListaDocumentos);
			parameters.put("SR_RUTA_DOCUMENTOS", ruta);
			parameters.put("D_VERSION_SISTEMA", general.getVersionSistema());
			
			parameters.put("P_ESTADO", general.getIdEstado().toString());
			parameters.put("P_LOGO_ANULADO", logo_anulado.toString());
			parameters.put("P_LOGO_NOANULADO", logo_noanulado.toString());

			byte[] array = printer.exportPdfSub(jasperFile.toString(),jasperFile1.toString(), parameters, listaProductos);
			InputStream input = new ByteArrayInputStream(array);
	        
	        String file_name = "Orden_Ingreso_Donacion";
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
	
	@RequestMapping(value = "/exportarPdfActa/{codigo}/{tipo}/{ind_gui}/{ind_man}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarPdfActa(@PathVariable("codigo") Integer codigo, 
			@PathVariable("tipo") Integer tipo,
			@PathVariable("ind_gui") Integer ind_orden,
			@PathVariable("ind_man") Integer ind_acta,
			HttpServletRequest request, HttpServletResponse response) {
	    try {
	    	
	    	System.out.println("TIPO: "+tipo);
	    	if(ind_orden==1){
	    		List<DonacionesIngresoBean> lista = donacionService.listarReporteDonacionIngreso(codigo);
				List<ProductoDonacionIngresoBean> listaProductos = donacionService.listarProductosReporteDonacionIngreso(codigo);
				List<DocumentoDonacionIngresoBean> listaDocumentos = donacionService.listarDocumentosReporteDonacionIngreso(codigo);
				
				if (isEmpty(lista) || isEmpty(listaProductos)) {
					return Constantes.COD_VALIDACION_GENERAL;
				}			
				DonacionesIngresoBean general = lista.get(0);

				ExportarArchivo printer = new ExportarArchivo();
				StringBuilder jasperFile = new StringBuilder();
				jasperFile.append(getPath(request));
				jasperFile.append(File.separator);
				jasperFile.append(Constantes.REPORT_PATH_DONACIONES);
				jasperFile.append("Orden_Ingreso.jrxml");
				
				StringBuilder jasperFile1 = new StringBuilder();
				jasperFile1.append(getPath(request));
				jasperFile1.append(File.separator);
				jasperFile1.append(Constantes.REPORT_PATH_DONACIONES);
				jasperFile1.append("SR_Orden_Ingreso.jrxml");
				
				String ruta = getPath(request)+File.separator+Constantes.REPORT_PATH_DONACIONES;
				
//				if (producto.getFlagTipoProducto().equals(Constantes.ONE_STRING)) {
//					jasperFile.append("Control_Calidad_Alimentaria.jrxml");
//				} else {
//					jasperFile.append("Control_Calidad_No_Alimentaria.jrxml");
//				}
				JRBeanCollectionDataSource ListaDocumentos = new JRBeanCollectionDataSource(listaDocumentos);
				
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
				parameters.put("P_LOGO_CHECK", logo_check_path.toString());			
				StringBuilder logo_check_min_path = new StringBuilder();
				logo_check_min_path.append(getPath(request));
				logo_check_min_path.append(File.separator);
				logo_check_min_path.append(Constantes.IMAGE_CHECK_REPORT_PATH);
				parameters.put("P_LOGO_CHECK_MIN", logo_check_min_path.toString());			
				parameters.put("P_NRO_ORDEN_INGRESO", general.getNroOrdenIngreso());
				parameters.put("P_DDI", general.getNombreDdi());			
				parameters.put("P_FECHA_EMISION", general.getFechaEmision());
				parameters.put("P_ALMACEN_ORIGEN", general.getNombreAlmacen());
				parameters.put("P_OBSERVACION", general.getObservacion());
				parameters.put("P_TIPO_MOVIMIENTO", general.getNombreMovimiento());
				parameters.put("LISTA_DOCUMENTOS", ListaDocumentos);
				parameters.put("SR_RUTA_DOCUMENTOS", ruta);
				parameters.put("D_NOMBRE_SISTEMA", general.getNombreSistema());
				parameters.put("D_VERSION_SISTEMA", general.getVersionSistema());
				
				parameters.put("P_ESTADO", general.getIdEstado().toString());
				parameters.put("P_LOGO_ANULADO", logo_anulado.toString());
				parameters.put("P_LOGO_NOANULADO", logo_noanulado.toString());

				byte[] array = printer.exportPdfSub(jasperFile.toString(), jasperFile1.toString(),parameters, listaProductos);
				InputStream input = new ByteArrayInputStream(array);
		        
		        String file_name = "Orden_Ingreso_Donacion";
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
	    	}else{
	    		
	    		if(tipo==12 || tipo==13){
	    			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
		    		List<DonacionesIngresoBean> lista = donacionService.listarReporteDonacionIngresoNacional(codigo, usuarioBean.getIdDdi());
					List<ProductoDonacionIngresoBean> listaProductos = donacionService.listarProductoReporteDonacionIngresoNacional(codigo, usuarioBean.getIdDdi());
					if (isEmpty(lista) || isEmpty(listaProductos)) {
						return Constantes.COD_VALIDACION_GENERAL;
					}			
					DonacionesIngresoBean general = lista.get(0);

					ExportarArchivo printer = new ExportarArchivo();
					StringBuilder jasperFile = new StringBuilder();
					jasperFile.append(getPath(request));
					jasperFile.append(File.separator);
					jasperFile.append(Constantes.REPORT_PATH_DONACIONES);
					jasperFile.append("Acta_Entrega_Nacional.jrxml");
					
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
					parameters.put("D_CIUDAD", general.getNombreDdi());
					parameters.put("D_DIAS", general.getDia());			
					parameters.put("D_MES", general.getMes());
					parameters.put("D_ANIO", general.getAnio());
					parameters.put("D_NOM_ALMACEN", general.getNombreAlmacen());
					parameters.put("D_NOM_DONANTE", general.getNombreDonante());
					parameters.put("D_NUM_DOCUMENTO", general.getNroDocumento());
					parameters.put("D_FECHA_EMISION", general.getFechaEmision());
					parameters.put("D_NOMBRE_SISTEMA", general.getNombreSistema());
					parameters.put("D_VERSION_SISTEMA", general.getVersionSistema());

					byte[] array = printer.exportPdf(jasperFile.toString(), parameters, listaProductos);
					InputStream input = new ByteArrayInputStream(array);
			        
			        String file_name = "Acta_Nacional";
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
	    		}else{
	    			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
		    		List<DonacionesIngresoBean> lista = donacionService.listarReporteDonacionIngresoInternacional(codigo, usuarioBean.getIdDdi());
					List<ProductoDonacionIngresoBean> listaProductos = donacionService.listarProductoReporteDonacionIngresoInternacional(codigo, usuarioBean.getIdDdi());
					if (isEmpty(lista) || isEmpty(listaProductos)) {
						return Constantes.COD_VALIDACION_GENERAL;
					}			
					DonacionesIngresoBean general = lista.get(0);

					ExportarArchivo printer = new ExportarArchivo();
					StringBuilder jasperFile = new StringBuilder();
					jasperFile.append(getPath(request));
					jasperFile.append(File.separator);
					jasperFile.append(Constantes.REPORT_PATH_DONACIONES);
					jasperFile.append("Acta_Entrega_Internacional.jrxml");
					
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
					parameters.put("D_CIUDAD", general.getNombreDdi());
					parameters.put("D_DIAS", general.getDia());			
					parameters.put("D_MES", general.getMes());
					parameters.put("D_ANIO", general.getAnio());
					parameters.put("D_NOM_ALMACEN", general.getNombreAlmacen());
					parameters.put("D_NOM_DONANTE", general.getNombreDonante());
					parameters.put("D_NUM_DOCUMENTO", general.getNroDocumento());
					parameters.put("D_JEFE_ALMACEN", general.getResponsableAlmacen());
					parameters.put("D_NOMBRE_SISTEMA", general.getNombreSistema());
					parameters.put("D_VERSION_SISTEMA", general.getVersionSistema());

					byte[] array = printer.exportPdf(jasperFile.toString(), parameters, listaProductos);
					InputStream input = new ByteArrayInputStream(array);
			        
			        String file_name = "Acta_Internacional";
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
	    		}
	    		
	    	}
	    	
			
	    } catch (Exception e) {
	    	LOGGER.error(e.getMessage(), e);
	    	return Constantes.COD_ERROR_GENERAL;
	    } 
	}
	
	/////////////////////////////////
	

}
