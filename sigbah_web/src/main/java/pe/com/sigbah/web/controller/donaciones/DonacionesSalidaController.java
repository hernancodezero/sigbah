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
import pe.com.sigbah.common.bean.DocumentoSalidaBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.ProductoBean;
import pe.com.sigbah.common.bean.ProductoControlCalidadBean;
import pe.com.sigbah.common.bean.ProductoDonacionBean;
import pe.com.sigbah.common.bean.ProductoDonacionIngresoBean;
import pe.com.sigbah.common.bean.ProductoDonacionSalidaBean;
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
import pe.com.sigbah.common.bean.DonacionesSalidaBean;


/**
 * @className: DonacionesSalidaController.java
 * @description: 
 * @date: 27/07/2017
 * @author: ARCHY.
 */
@Controller
@RequestMapping("/donacionesSalida/registro-donacionesSalida")
public class DonacionesSalidaController extends BaseController {

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
        	System.out.println("DONACIONES Salida");
        	// Retorno los datos de session
           	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
           	DonacionesSalidaBean donsalida = new DonacionesSalidaBean();
           	donsalida.setCodigoAnio(generalService.obtenerAnioActual());
           	if(usuarioBean.getIdAlmacen()==null){
           		model.addAttribute("txt_mensaje", "Usted no tiene almacén de trabajo");
           		ruta = "noAlmacen";
           	}else{
           		
           		CierreStockBean cierre = new CierreStockBean();
           		cierre = administracionService.mesTrabajoActivo(usuarioBean.getIdAlmacen(), Constantes.TIPO_ORIGEN_DONACIONES);
           		System.out.println("TRABAJO : "+cierre.getCodigoMes());
           		
           		
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
		
		        	model.addAttribute("lista_movimiento", generalService.listarTipoMovimiento(new ItemBean(Constantes.ONE_INT,Constantes.TWO_INT)));
		        	model.addAttribute("mes_anio", cierre.getNombreMes()+" "+cierre.getCodigoAnio());
		        	model.addAttribute("txt_cod_ddi", usuarioBean.getCodigoDdi());
		        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
		        	model.addAttribute("donsalida", getParserObject(donsalida));
		        	ruta="listar-donaciones-salida";
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
		List<DonacionesSalidaBean> lista = null;
		try {
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			DonacionesSalidaBean donacionesSalidaBean = new DonacionesSalidaBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(donacionesSalidaBean, request.getParameterMap());
			donacionesSalidaBean.setIdAlmacen(usuarioBean.getIdAlmacen());
			lista = donacionService.listarSalidaDonaciones(donacionesSalidaBean);
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
        	
        	DonacionesSalidaBean donacionesBean = new DonacionesSalidaBean();
        	List<ItemBean> listaDee1=null;
        	DonacionesSalidaBean datoDonaciones=new DonacionesSalidaBean();
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	String codDdi = usuarioBean.getCodigoDdi();
        	Integer idDdi =usuarioBean.getIdDdi();
        	Integer idAlmacen = usuarioBean.getIdAlmacen();
        	String codAlmacen = usuarioBean.getCodigoAlmacen();
        	String anioActual = generalService.obtenerAnioActual();
        	String codiAnio = usuarioBean.getCodigoAnio();
        	
        	
        	
        	CierreStockBean cierre = new CierreStockBean();
       		cierre = administracionService.mesTrabajoActivo(usuarioBean.getIdAlmacen(), Constantes.TIPO_ORIGEN_DONACIONES);
        	if (!isNullInteger(codigo)) {
        	
        		donacionesBean = donacionService.obtenerDonacionSalidaXIdSalida(codigo);
//        		donacionesBean.setNombreTipoDonacion(donacionesBean.getNombreTipoDonacion()==null?null:donacionesBean.getNombreTipoDonacion().trim());
//        		donacionesBean.setNombreDdi(usuarioBean.getNombreDdi());
//        		donacionesBean.setIdDdi(usuarioBean.getIdDdi());
//            	donacionesBean.setCodigoDdi(usuarioBean.getCodigoDdi());
//            	donacionesBean.setTextoCodigo(usuarioBean.getIdDdi()+"-"+usuarioBean.getCodigoAlmacen()+"-"+donacionesBean.getNroOrdenIngreso());
//            	System.out.println("Almacen: "+donacionesBean.getIdAlmacen());
//            	System.out.println("DOnacion: "+donacionesBean.getIdDonacion());
//            	System.out.println("Movimiento: "+donacionesBean.getIdTipoMovimiento());
//            	model.addAttribute("lista_productos_donacion", donacionService.listarProductosDonacion(donacionesBean));
            	
            	
            	
            	
            	
            	
            	
            	
            	
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
        		
        	} else {
        		DonacionesSalidaBean parametros = new DonacionesSalidaBean();
            	
            	
            	parametros.setCodigoAnio(anioActual);
            	parametros.setIdDdi(idDdi);
            	parametros.setCodigoDdi(codDdi);
            	parametros.setIdAlmacen(idAlmacen);
            	parametros.setCodAlmacen(codAlmacen);
            	DonacionesSalidaBean datosDonacion = donacionService.obtenerCorrelativoOrdenSalida(parametros);
            	
            	donacionesBean.setIdDdi(idDdi);
            	donacionesBean.setCodigoDdi(codDdi);
            	//donacionesBean.setCodigoDonacion(datosDonacion.getCodigoDonacion());
            	//donacionesBean.setTextoCodigo(datosDonacion.getCodSalida());
            	donacionesBean.setCodigoAnio(anioActual);
            	donacionesBean.setNombreDdi(usuarioBean.getNombreDdi());
            	donacionesBean.setNroOrdenSalida(datosDonacion.getNroOrdenSalida());
            	donacionesBean.setCodSalida(datosDonacion.getCodSalida());
           
            	//enviar 0 para listar dee

            	//model.addAttribute("lista_dee", generalService.listarDee(new ItemBean(Constantes.ZERO_INT)));
            	
            	listaDee1 = generalService.listarDee(new ItemBean(Constantes.ONE_INT));

            	//model.addAttribute("lista_proce_pais", generalService.listarPais(new ItemBean(Constantes.ZERO_INT)));

            	datoDonaciones.setIdDdi(idDdi);
            	//datoDonaciones.setTipoDonante("1");
            	
            	
            	

            	//model.addAttribute("lista_donadores", donacionService.listarDonadores(datoDonaciones));
            	
            	//model.addAttribute("lista_oficinas", generalService.listarOficinas(new ItemBean(Constantes.ZERO_INT)));
            	
            	//model.addAttribute("nombreDee", listaDee1.get(0).getDescripcion());
            	
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
        		
        		//ControlCalidadBean respuestaCorrelativo = logisticaService.obtenerCorrelativoControlCalidad(parametros);
        	      
        	//	correlativo.append(respuestaCorrelativo.getNroControlCalidad());
        		
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
        	}
        	donacionesBean.setAnio(usuarioBean.getCodigoAnio());
        	donacionesBean.setMes(usuarioBean.getCodigoMes());
        	if (!Utils.isNullInteger(donacionesBean.getIdDonacion())) {
        		List<ItemBean> listaEstadoUsu=donacionService.mostrarEstadoDonacionUsuario(new ItemBean(donacionesBean.getIdDonacion()));
        		String tablaEstados=HtmlUtils.TablaEstadosXDonacion(listaEstadoUsu);
        		model.addAttribute("tabla_estados", tablaEstados);
        		
        		model.addAttribute("lista_monedas", generalService.listarMoneda(new ItemBean(Constantes.ZERO_INT)));
        	}
        	//////////////////
        	
        	model.addAttribute("lista_movimiento", generalService.listarTipoMovimiento(new ItemBean(Constantes.ONE_INT,Constantes.TWO_INT)));
        	
        	model.addAttribute("lista_personal", generalService.listarPersonal(new ItemBean(idDdi)));
        	
        	model.addAttribute("lista_ddi", generalService.listarDdi(new ItemBean(Constantes.ZERO_INT)));
        	
        	model.addAttribute("lista_departamento", generalService.listarDepartamentos(new UbigeoBean()));
        	
        	model.addAttribute("lista_region", generalService.listarRegion(new ItemBean(Constantes.ZERO_INT)));
        	
        	//model.addAttribute("lista_almacen", generalService.listarAlmacen(new ItemBean(idDdi)));
        	
//        	model.addAttribute("lista_codigo_donacion", donacionService.listarCodigoDonacion(new ItemBean(idDdi,anioActual)));
//        	
//        	model.addAttribute("lista_control_calidad", donacionService.listarControCalidad(new ItemBean(anioActual,codDdi)));
//        	
//        	model.addAttribute("lista_almacen", generalService.listarAlmacen(new ItemBean(0)));

        	model.addAttribute("lista_medio_transporte", generalService.listarMedioTransporte(new ItemBean()));
//        	
//        	model.addAttribute("lista_personal", generalService.listarPersonal(new ItemBean(idDdi)));
//        	
//        	model.addAttribute("lista_salida", donacionService.listarSalida(new ItemBean(idAlmacen)));
        	
        	
        	
        	/////////////////
        	
        	
        	model.addAttribute("donaciones", getParserObject(donacionesBean));
        	System.out.println(donacionesBean.getCodigoAnio()+" - "+donacionesBean.getCodigoMes());
        	model.addAttribute("lista_dee", generalService.listarDee(new ItemBean(Constantes.ZERO_INT)));
        	
        	model.addAttribute("lista_proce_pais", generalService.listarPais(new ItemBean(Constantes.ZERO_INT)));
        	
        	//model.addAttribute("lista_donadores", donacionService.listarDonadores(datoDonaciones));
        	
        	model.addAttribute("lista_oficinas", generalService.listarOficinas(new ItemBean(Constantes.ZERO_INT)));
        	
        	//model.addAttribute("nombreDee", listaDee1.get(0).getDescripcion());
        	
        	model.addAttribute("lista_categoria", generalService.listarCategoria(new ItemBean(Constantes.ZERO_INT)));
        	//falta pintar
        	model.addAttribute("lista_tipo_documento", generalService.listarTipoDocumento(new ItemBean(Constantes.ZERO_INT)));
        	
        	//Para estados
        	
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
        	model.addAttribute("mes_anio", cierre.getNombreMes()+" "+cierre.getCodigoAnio());
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "registrar-donaciones-salida";
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
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarProductosXCategoria", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProductoXCategoria(HttpServletRequest request, HttpServletResponse response) {
		List<ProductoDonacionSalidaBean> lista = null;
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			ProductoDonacionSalidaBean producto = new ProductoDonacionSalidaBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(producto, request.getParameterMap());
			producto.setIdAlmacen(usuarioBean.getIdAlmacen());
			
			lista = donacionService.listarProductosDonacionSalida(producto);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/listarProductosXEditar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarProductosXEditar(HttpServletRequest request, HttpServletResponse response) {
		List<ProductoDonacionSalidaBean> lista = null;
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			ProductoDonacionSalidaBean producto = new ProductoDonacionSalidaBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(producto, request.getParameterMap());
			producto.setIdAlmacen(usuarioBean.getIdAlmacen());
			producto.setIdDdi(usuarioBean.getIdDdi());
			lista = donacionService.listarProductosDonacionSalidaEdi(producto);
			System.out.println("LISTA "+lista.size());
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
	@RequestMapping(value = "/grabarDonacionesSalida", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarDonacion(HttpServletRequest request, HttpServletResponse response) {
		DonacionesSalidaBean donaciones = null;
		System.out.println("ENTRO A GUARDAR");
		try {			
			DonacionesSalidaBean donacionesSalidaBean = new DonacionesSalidaBean();
			
			// Convierte los vacios en nulos en los enteros
			IntegerConverter con_integer = new IntegerConverter(null);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			beanUtilsBean.getConvertUtils().register(con_integer, Integer.class);
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(donacionesSalidaBean, request.getParameterMap());
			System.out.println("ANIO: "+donacionesSalidaBean.getCodigoAnio());
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	donacionesSalidaBean.setUsuarioRegistro(usuarioBean.getUsuario());
        	donacionesSalidaBean.setIdAlmacen(usuarioBean.getIdAlmacen());
        	donacionesSalidaBean.setCodAlmacen(usuarioBean.getCodigoAlmacen());
        	donacionesSalidaBean.setCodigoDdi(usuarioBean.getCodigoDdi());
        	donacionesSalidaBean.setTipoOrigen("D");
			if (!isNullInteger(donacionesSalidaBean.getIdSalida())) {
				//donacionesBean =donacionService.obtenerDonacionXIdDonacion(donacionesBean.getIdDonacion());
				donaciones = donacionService.actualizarRegistroDonacionSalida(donacionesSalidaBean);
				donaciones.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));				
			} else {			
				donaciones = donacionService.insertarRegistroDonacionSalida(donacionesSalidaBean);			
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
	@RequestMapping(value = "/grabarProductoDonacionSalida", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarProductoDonacion(HttpServletRequest request, HttpServletResponse response) {
		ProductoDonacionSalidaBean productoDonacion = new ProductoDonacionSalidaBean();
		try {			
			ProductoDonacionSalidaBean productoDonacionBean = new ProductoDonacionSalidaBean();

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
        	productoDonacionBean.setAnio(usuarioBean.getCodigoAnio());
        	productoDonacionBean.setMes(usuarioBean.getCodigoMes());
        	
        	
        	ItemBean parametros = new ItemBean();
        	parametros.setIcodigo(productoDonacionBean.getIdProducto());
        	parametros.setIcodigoParam2(productoDonacionBean.getIdAlmacen());
        	parametros.setIcodigoParam3(productoDonacionBean.getCantidad().intValue());
        	ItemBean validaStock = donacionService.validaStockProducto(parametros);
        	String mensajeResp="";
        	if(validaStock.getCodigoRespuesta().equals(Constantes.COD_VALIDACION_GENERAL)){
        		mensajeResp=validaStock.getMensajeRespuesta();
        		productoDonacion.setCodigoRespuesta(validaStock.getCodigoRespuesta());
        		 
        	}else{
        		if(productoDonacionBean.getIdSalidaDet().equals("") || productoDonacionBean.getIdSalidaDet()==0){
            		
            		productoDonacion = donacionService.insertarProductoDonacionSalida(productoDonacionBean);
            	}else{
            		productoDonacion = donacionService.actualizarProductoDonacionSalida(productoDonacionBean);
            	}
        		mensajeResp=(getMensaje(messageSource, "msg.info.grabadoOk"));
    			
            	//productoDonacion.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));	
        	}
        	System.out.println("Mensaje : "+mensajeResp);
        	productoDonacion.setMensajeRespuesta(mensajeResp);
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
		List<ProductoDonacionSalidaBean> lista = null;
		try {			
			ProductoDonacionSalidaBean producto = new ProductoDonacionSalidaBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(producto, request.getParameterMap());			
			lista = donacionService.listarProductoDonacionSalida(producto);
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
		ProductoDonacionSalidaBean producto = null;
		try {			
			System.out.println("PARAMETRO : "+request.getParameter("idSalidaDet"));
			String[] arrIdDetalleControlCalidad = request.getParameter("idSalidaDet").split(Constantes.UNDERLINE);
			for (String codigo : arrIdDetalleControlCalidad) {				
				ProductoDonacionSalidaBean productoDonacionBean = new ProductoDonacionSalidaBean();
				productoDonacionBean.setIdSalidaDet(Integer.parseInt(codigo));
				// Retorno los datos de session
	        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
	        	System.out.println("PARAMETROrrrr : "+productoDonacionBean.getIdSalidaDet());
	        	productoDonacionBean.setUsuarioRegistro(usuarioBean.getUsuario());
				
				producto = donacionService.eliminarProductoDonacionSalida(productoDonacionBean);				
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
	@RequestMapping(value = "/listarDocumentoDonacionSalida", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarDocumentoDonacion(HttpServletRequest request, HttpServletResponse response) {
		List<DocumentoSalidaBean> lista = null;
		try {			
			DocumentoSalidaBean documento = new DocumentoSalidaBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(documento, request.getParameterMap());			
			lista = donacionService.listarDocumentoDonacionSalida(documento);
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
	@RequestMapping(value = "/grabarDocumentoDonacionSalida", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarDocumentoDonacion(HttpServletRequest request, HttpServletResponse response) {
		DocumentoSalidaBean documento = null;
		try {			
			DocumentoSalidaBean documentoDonacionBean = new DocumentoSalidaBean();
			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(documentoDonacionBean, request.getParameterMap());
			
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
        	documentoDonacionBean.setUsuarioRegistro(usuarioBean.getUsuario());
        	Integer iddocumento = documentoDonacionBean.getIdDocumentoSalida()!=null?documentoDonacionBean.getIdDocumentoSalida():0;
        	if(iddocumento==0){
        		documento = donacionService.insertarDocumentoDonacionSalida(documentoDonacionBean);

        	}else{
        		documento = donacionService.actualizarDocumentoDonacionSalida(documentoDonacionBean);

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
	@RequestMapping(value = "/eliminarDocumentoDonacionSalida", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarDocumentoDonacion(HttpServletRequest request, HttpServletResponse response) {
		DocumentoSalidaBean documento = null;
		try {			
			String[] arrIdDocumentoDonacion = request.getParameter("arrIdDocumentoDonacion").split(Constantes.UNDERLINE);
			Integer idSalida = Integer.parseInt(request.getParameter("idSalida"));
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
			for (String codigo : arrIdDocumentoDonacion) {				
				DocumentoSalidaBean documentoDonacionBean = new DocumentoSalidaBean();
				
				documentoDonacionBean.setIdSalida(idSalida);
				documentoDonacionBean.setIdDocumentoSalida(getInteger(codigo));
				documentoDonacionBean.setUsuarioRegistro(usuarioBean.getNombreUsuario());
	        	documento = donacionService.eliminarDocumentoSalidaDonacion(documentoDonacionBean);				
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
			List<DonacionesSalidaBean> lista = donacionService.listarReporteDonacionSalida(codigo);
			List<ProductoDonacionSalidaBean> listaProductos = donacionService.listarProductosReporteDonacionSalida(codigo);
			List<DocumentoSalidaBean> listaDocumentos = donacionService.listarDocumentosReporteDonacionSalida(codigo);
			
			if(isEmpty(listaDocumentos)){
				DocumentoSalidaBean data = new DocumentoSalidaBean();
				data.setIdSalida(0);
				data.setIdTipoDocumento(0);
				data.setNombreDocumento("-");	
				data.setNroDocumento("-");
				data.setFechaDocumento("-");
				listaDocumentos.add(data);
			}
			
			if (isEmpty(lista) || isEmpty(listaProductos) || isEmpty(listaDocumentos)) {
				return Constantes.COD_VALIDACION_GENERAL;
			}			
			DonacionesSalidaBean general = lista.get(0);

			ExportarArchivo printer = new ExportarArchivo();
			StringBuilder jasperFile = new StringBuilder();
			jasperFile.append(getPath(request));
			jasperFile.append(File.separator);
			jasperFile.append(Constantes.REPORT_PATH_DONACIONES);
			jasperFile.append("Orden_Salida.jrxml");
			
			StringBuilder jasperFile1 = new StringBuilder();
			jasperFile1.append(getPath(request));
			jasperFile1.append(File.separator);
			jasperFile1.append(Constantes.REPORT_PATH_DONACIONES);
			jasperFile1.append("SR_Orden_Salida.jrxml");
			
			String ruta = getPath(request)+File.separator+Constantes.REPORT_PATH_DONACIONES;
//			if (producto.getFlagTipoProducto().equals(Constantes.ONE_STRING)) {
//				jasperFile.append("Control_Calidad_Alimentaria.jrxml");
//			} else {
//				jasperFile.append("Control_Calidad_No_Alimentaria.jrxml");
//			}
			
			JRBeanCollectionDataSource ListaDocumentos = new JRBeanCollectionDataSource(listaDocumentos);
//			
			Map<String, Object> parameters = new HashMap<String, Object>();

			// Agregando los parÃ¡metros del reporte
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
			parameters.put("P_NRO_ORDEN_INGRESO", general.getNroOrdenSalida());
			parameters.put("P_DDI", general.getNombreDdi());			
			parameters.put("P_FECHA_EMISION", general.getFechaEmision());
			parameters.put("P_ALMACEN_ORIGEN", general.getNomAlmacenOrigen());
			parameters.put("P_OBSERVACION", general.getObservacion());
			parameters.put("P_TIPO_MOVIMIENTO", general.getNombreMovimiento());
			parameters.put("P_ALMACEN_DESTINO", general.getNomAlmacenDestino());
			parameters.put("LISTA_DOCUMENTOS", ListaDocumentos);
			parameters.put("SR_RUTA_DOCUMENTOS", ruta);
			parameters.put("D_NOMBRE_SISTEMA", general.getNombreSistema());
			parameters.put("D_VERSION_SISTEMA", general.getVersionSistema());
			
			parameters.put("P_ESTADO", general.getIdEstado().toString());
			parameters.put("P_LOGO_ANULADO", logo_anulado.toString());
			parameters.put("P_LOGO_NOANULADO", logo_noanulado.toString());
			
//			byte[] array = printer.exportPdf(jasperFile.toString(), parameters, listaProductos);
			byte[] array = printer.exportPdfSub(jasperFile.toString(),jasperFile1.toString(), parameters, listaProductos);
			InputStream input = new ByteArrayInputStream(array);
	        
	        String file_name = "Orden_Salida_Donacion";
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

	
	///////////////SALIDA//////////////////
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarAlmacenDestino", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarAlmacenDestino(HttpServletRequest request, HttpServletResponse response) {
		List<ItemBean> lista = null;
		System.out.println("CODIGO11111: ");
		try {			
			ItemBean item = new ItemBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(item, request.getParameterMap());
			System.out.println("CODIGO: "+item.getIcodigo());
			lista = generalService.listarAlmacen(item);
			System.out.println(lista.size());
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
		System.out.println("CODIGO22222: ");
		try {			
			ItemBean item = new ItemBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(item, request.getParameterMap());
			System.out.println("CODIGO: "+item.getIcodigo());
			lista = generalService.listarPersonal(item);
			System.out.println(lista.size());
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
	

}
