package pe.com.sigbah.web.controller.administracion;

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
import pe.com.sigbah.common.bean.ControlCalidadBean;
import pe.com.sigbah.common.bean.DetalleProductoControlCalidadBean;
import pe.com.sigbah.common.bean.DocumentoControlCalidadBean;
import pe.com.sigbah.common.bean.DocumentoDonacionBean;
import pe.com.sigbah.common.bean.DocumentoDonacionIngresoBean;
import pe.com.sigbah.common.bean.DocumentoIngresoBean;
import pe.com.sigbah.common.bean.DocumentoSalidaBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.MaeAlmacenBean;
import pe.com.sigbah.common.bean.MaeAlmacenExternoBean;
import pe.com.sigbah.common.bean.MaeAniosBean;
import pe.com.sigbah.common.bean.MaeCategoriaBean;
import pe.com.sigbah.common.bean.MaeChoferBean;
import pe.com.sigbah.common.bean.MaeCorrelativoBean;
import pe.com.sigbah.common.bean.MaeDdiBean;
import pe.com.sigbah.common.bean.MaeDistritoIneiBean;
import pe.com.sigbah.common.bean.MaeDonanteBean;
import pe.com.sigbah.common.bean.MaeEmpresaTransporteBean;
import pe.com.sigbah.common.bean.MaeEnvaseBean;
import pe.com.sigbah.common.bean.MaeEstadoBean;
import pe.com.sigbah.common.bean.MaeEstadoDonacionBean;
import pe.com.sigbah.common.bean.MaeEstadoProgramacionBean;
import pe.com.sigbah.common.bean.MaeMarcaBean;
import pe.com.sigbah.common.bean.MaeMedioTransporteBean;
import pe.com.sigbah.common.bean.MaeModAlmacenBean;
import pe.com.sigbah.common.bean.MaeMonedaBean;
import pe.com.sigbah.common.bean.MaeMotivoTrasladoBean;
import pe.com.sigbah.common.bean.MaeOficinaBean;
import pe.com.sigbah.common.bean.MaePaisBean;
import pe.com.sigbah.common.bean.MaeParametroBean;
import pe.com.sigbah.common.bean.MaePersonalBean;
import pe.com.sigbah.common.bean.MaePersonalExternoBean;
import pe.com.sigbah.common.bean.MaeRegionBean;
import pe.com.sigbah.common.bean.MaeTipCamionBean;
import pe.com.sigbah.common.bean.MaeTipControlCalidadBean;
import pe.com.sigbah.common.bean.MaeTipDocumentoBean;
import pe.com.sigbah.common.bean.MaeTipMovimientoBean;
import pe.com.sigbah.common.bean.MaeUnidadMedidaBean;
import pe.com.sigbah.common.bean.OrdenIngresoBean;
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
import pe.com.sigbah.web.report.administracion.ReporteTablasGenerales;
import pe.com.sigbah.web.report.donaciones.ReporteDonaciones;
import pe.com.sigbah.web.report.gestion_almacenes.ReporteControlCalidad;
import pe.com.sigbah.web.report.gestion_almacenes.ReporteOrdenIngreso;
import pe.com.sigbah.common.bean.DonacionesBean;
import pe.com.sigbah.common.bean.DonacionesIngresoBean;
import pe.com.sigbah.common.bean.DonacionesSalidaBean;


/**
 * @className: TablasGeneralesController.java
 * @description: 
 * @date: 11/08/2017
 * @author: ARCHY.
 */
@Controller
@RequestMapping("/administracion/tablas-generales")
public class TablasGeneralesController extends BaseController {

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

        	model.addAttribute("lista_tablas", administracionService.listarTablas());
        	
        	//almacenes externos
        	model.addAttribute("lista_departamento", generalService.listarDepartamentos(new UbigeoBean()));
        	model.addAttribute("lista_region", generalService.listarRegion(new ItemBean(Constantes.ZERO_INT)));
        	
        	model.addAttribute("lista_medio_transporte", generalService.listarMedioTransporte(new ItemBean()));
        	
        	model.addAttribute("lista_ddi", generalService.listarDdi(new ItemBean(Constantes.ZERO_INT)));
        	
        	model.addAttribute("lista_oficinas_ddi", generalService.listarOficinas(new ItemBean(usuarioBean.getIdDdi())));
        	
        	model.addAttribute("lista_mod_almacen", generalService.listarModAlmacen(new ItemBean(Constantes.ZERO_INT)));
        	
        	model.addAttribute("lista_anio", generalService.listarAnios());
        	model.addAttribute("lista_categoria", generalService.listarCategoria(new ItemBean(Constantes.ZERO_INT)));
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
			
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar-tablas";
    }
	
	@RequestMapping(value = "/inicioGeneral", method = RequestMethod.GET)
    public String inicioGeneral(Model model) {
        try {       
        	// Retorno los datos de session
           	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

        	model.addAttribute("lista_tablas", administracionService.listarTablasGenerales());
        	
        	//almacenes externos
        	model.addAttribute("lista_departamento", generalService.listarDepartamentos(new UbigeoBean()));
        	model.addAttribute("lista_region", generalService.listarRegion(new ItemBean(Constantes.ZERO_INT)));
        	
        	model.addAttribute("lista_medio_transporte", generalService.listarMedioTransporte(new ItemBean()));
        	
        	model.addAttribute("lista_ddi", generalService.listarDdi(new ItemBean(Constantes.ZERO_INT)));
        	
        	model.addAttribute("lista_oficinas_ddi", generalService.listarOficinas(new ItemBean(usuarioBean.getIdDdi())));
        	model.addAttribute("lista_anio", generalService.listarAnios());
        	model.addAttribute("lista_mod_almacen", generalService.listarModAlmacen(new ItemBean(Constantes.ZERO_INT)));
        	model.addAttribute("lista_tipo_documento", generalService.listarTipoDocumento(new ItemBean(Constantes.ZERO_INT)));
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar-tablas-generales";
    }
	
	@RequestMapping(value = "/inicioDdi", method = RequestMethod.GET)
    public String inicioDdi(Model model) {
        try {       
        	// Retorno los datos de session
           	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

        	model.addAttribute("lista_tablas", administracionService.listarTablasDdi());
        	model.addAttribute("lista_anio", generalService.listarAnios());
        	//almacenes externos
        	model.addAttribute("lista_departamento", generalService.listarDepartamentos(new UbigeoBean()));
        	model.addAttribute("lista_region", generalService.listarRegion(new ItemBean(Constantes.ZERO_INT)));
        	
        	model.addAttribute("lista_medio_transporte", generalService.listarMedioTransporte(new ItemBean()));
        	
        	model.addAttribute("lista_ddi", generalService.listarDdi(new ItemBean(Constantes.ZERO_INT)));
        	
        	model.addAttribute("lista_oficinas_ddi", generalService.listarOficinas(new ItemBean(usuarioBean.getIdDdi())));
        	
        	model.addAttribute("lista_mod_almacen", generalService.listarModAlmacen(new ItemBean(Constantes.ZERO_INT)));
        	
        	ItemBean item = new ItemBean();
        	
        	item.setIcodigo(usuarioBean.getIdDdi());
			
			model.addAttribute("lista_empresa_transporte", generalService.listarEmpresaTransporte(item));
        	
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	
        	model.addAttribute("dataAlmacen", getParserObject(usuarioBean));
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));

        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar-tablas-ddi";
    }
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarTabla", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarTabla(HttpServletRequest request, HttpServletResponse response) {
		Object dato = new Object();
		List<MaeAlmacenBean> listaAlmacen = null;
		List<MaeAlmacenExternoBean> listaAlmacenExterno = null;
		List<MaeAniosBean> listaAnio = null;
		List<MaeCategoriaBean> listaCategoria = null;
		List<MaeChoferBean> listaChofer = null;
		List<MaeDdiBean> listaDdi = null;
		List<MaeDistritoIneiBean> listaDistritoInei = null;
		List<MaeDonanteBean> listaDonante = null;
		List<MaeEmpresaTransporteBean> listaEmpresaTransporte = null;
		List<MaeEnvaseBean> listaEnvase = null;
		List<MaeEstadoBean> listaEstado = null;
		List<MaeEstadoDonacionBean> listaEstadoDonacion = null;
		List<MaeEstadoProgramacionBean> listaEstadoProgramacion = null;
		List<MaeMedioTransporteBean> listaMedioTransporte = null;
		List<MaeModAlmacenBean> listaModAlmacen = null;
		List<MaeMonedaBean> listaMoneda = null;
		List<MaeMotivoTrasladoBean> listaMotivoTraslado = null;
		List<MaeOficinaBean> listaOficina = null;
		List<MaePaisBean> listaPais = null;
		List<MaeParametroBean> listaParametro = null;
		List<MaePersonalBean> listaPersonal = null;
		List<MaePersonalExternoBean> listaPersonalExterno = null;
		List<MaeRegionBean> listaRegion = null;
		List<MaeTipCamionBean> listaTipCamion = null;
		List<MaeTipControlCalidadBean> listaTipControlCalidad = null;
		List<MaeTipDocumentoBean> listaTipDocumento = null;
		List<MaeTipMovimientoBean> listaTipMovimiento = null;
		List<MaeUnidadMedidaBean> listaUnidadMedida = null;
		List<MaeMarcaBean> listaMarca = null;
		
		String nomTabla="";
		try {
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			ItemBean itemBean = new ItemBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(itemBean, request.getParameterMap());
			nomTabla = itemBean.getDescripcion();
			Integer idDdi = usuarioBean.getIdDdi();
			System.out.println("IDTABLA: "+nomTabla);
			if(nomTabla.equals("BAH_MAE_ALMACEN")){
				listaAlmacen = administracionService.listarTablaAlmacen(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_ALMACEN_EXTERNO")){
				listaAlmacenExterno = administracionService.listarTablaAlmacenExterno(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_ANIO")){
				listaAnio = administracionService.listarTablaAnios(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_CATEGORIA_BAH")){
				listaCategoria = administracionService.listarTablaCategoria(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_CHOFER")){
				listaChofer = administracionService.listarTablaChofer(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_DDI")){
				listaDdi = administracionService.listarTablaDdi(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_DISTRITO_INEI")){
				listaDistritoInei = administracionService.listarTablaDistritoInei(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_DONANTE")){
				listaDonante = administracionService.listarTablaDonante(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_EMP_TRANSPORTE")){
				listaEmpresaTransporte = administracionService.listarTablaEmpresaTransporte(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_ENVASE")){
				listaEnvase = administracionService.listarTablaEnvase(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_ESTADO")){
				listaEstado = administracionService.listarTablaEstado(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_ESTADO_DONACION")){
				listaEstadoDonacion = administracionService.listarTablaEstadoDonacion(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_ESTADO_PROGRAMACION")){
				listaEstadoProgramacion = administracionService.listarTablaEstadoProgramacion(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_MEDIO_TRANSPORTE")){
				listaMedioTransporte = administracionService.listarTablaMedioTransporte(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_MOD_ALMACEN")){
				listaModAlmacen = administracionService.listarTablaModAlmacen(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_MONEDA")){
				listaMoneda = administracionService.listarTablaMoneda(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_MOTIVO_TRASLADO")){
				listaMotivoTraslado = administracionService.listarTablaMotivoTraslado(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_OFICINA")){
				listaOficina = administracionService.listarTablaOficina(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_PAIS")){
				listaPais = administracionService.listarTablaPais(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_PARAMETRO")){
				listaParametro = administracionService.listarTablaParametro(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_PERSONAL")){
				listaPersonal = administracionService.listarTablaPersonal(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_PERSONAL_EXTERNO")){
				listaPersonalExterno = administracionService.listarTablaPersonalExterno(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_REGION")){
				listaRegion = administracionService.listarTablaRegion(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_TIP_CAMION")){
				listaTipCamion = administracionService.listarTablaTipCamion(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_TIP_CONTROL_CALIDAD")){
				listaTipControlCalidad = administracionService.listarTablaTipControlCalidad(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_TIP_DOCUMENTO")){
				listaTipDocumento = administracionService.listarTablaTipDocumento(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_TIP_MOVIMIENTO")){
				listaTipMovimiento = administracionService.listarTablaTipMovimiento(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_UNIDAD_MEDIDA")){
				listaUnidadMedida = administracionService.listarTablaUnidadMedida(idDdi, nomTabla);
			}else if(nomTabla.equals("BAH_MAE_MARCA")){
				listaMarca = administracionService.listarTablaMarca(idDdi, nomTabla);
			}
			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		if(nomTabla.equals("BAH_MAE_ALMACEN")){
			System.out.println("ALMACEN");
			return listaAlmacen;
		}else if(nomTabla.equals("BAH_MAE_ALMACEN_EXTERNO")){
			System.out.println("ALMACEN EXTERNO");
			return listaAlmacenExterno;
		}else if (nomTabla.equals("BAH_MAE_ANIO")){
			System.out.println("ALMACEN BAH_MAE_ANIO");
			return listaAnio;
		}else if (nomTabla.equals("BAH_MAE_CATEGORIA_BAH")){
			return listaCategoria;
		}else if (nomTabla.equals("BAH_MAE_CHOFER")){
			return listaChofer;
		}else if (nomTabla.equals("BAH_MAE_DDI")){
			return listaDdi;
		}else if (nomTabla.equals("BAH_MAE_DISTRITO_INEI")){
			return listaDistritoInei;
		}else if (nomTabla.equals("BAH_MAE_DONANTE")){
			return listaDonante;
		}else if (nomTabla.equals("BAH_MAE_EMP_TRANSPORTE")){
			return listaEmpresaTransporte;
		}else if (nomTabla.equals("BAH_MAE_ENVASE")){
			return listaEnvase;
		}else if (nomTabla.equals("BAH_MAE_ESTADO")){
			return listaEstado;
		}else if (nomTabla.equals("BAH_MAE_ESTADO_DONACION")){
			return listaEstadoDonacion;
		}else if (nomTabla.equals("BAH_MAE_ESTADO_PROGRAMACION")){
			return listaEstadoProgramacion;
		}else if (nomTabla.equals("BAH_MAE_MEDIO_TRANSPORTE")){
			return listaMedioTransporte;
		}else if (nomTabla.equals("BAH_MAE_MOD_ALMACEN")){
			return listaModAlmacen;
		}else if (nomTabla.equals("BAH_MAE_MONEDA")){
			return listaMoneda;
		}else if (nomTabla.equals("BAH_MAE_MOTIVO_TRASLADO")){
			return listaMotivoTraslado;
		}else if (nomTabla.equals("BAH_MAE_OFICINA")){
			return listaOficina;
		}else if (nomTabla.equals("BAH_MAE_PAIS")){
			return listaPais;
		}else if (nomTabla.equals("BAH_MAE_PARAMETRO")){
			return listaParametro;
		}else if (nomTabla.equals("BAH_MAE_PERSONAL")){
			return listaPersonal;
		}else if (nomTabla.equals("BAH_MAE_PERSONAL_EXTERNO")){
			return listaPersonalExterno;
		}else if (nomTabla.equals("BAH_MAE_REGION")){
			return listaRegion;
		}else if (nomTabla.equals("BAH_MAE_TIP_CAMION")){
			return listaTipCamion;
		}else if (nomTabla.equals("BAH_MAE_TIP_CONTROL_CALIDAD")){
			return listaTipControlCalidad;
		}else if (nomTabla.equals("BAH_MAE_TIP_DOCUMENTO")){
			return listaTipDocumento;
		}else if (nomTabla.equals("BAH_MAE_TIP_MOVIMIENTO")){
			return listaTipMovimiento;
		}else if (nomTabla.equals("BAH_MAE_UNIDAD_MEDIDA")){
			return listaUnidadMedida;
		}else if (nomTabla.equals("BAH_MAE_MARCA")){
			return listaMarca;
		}else
			return dato;
		
	}
	
	@RequestMapping(value = "/listarTablaAlmacenExterno", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarTablaAlmacenExterno(HttpServletRequest request, HttpServletResponse response) {
		Object dato = new Object();
		
		List<MaeAlmacenExternoBean> listaAlmacenExterno = null;
		
		String nomTabla="";
		try {
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			ItemBean itemBean = new ItemBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(itemBean, request.getParameterMap());
			nomTabla = itemBean.getDescripcion();
			Integer idDdi = usuarioBean.getIdDdi();
			System.out.println("IDTABLA: "+nomTabla);
			
			listaAlmacenExterno = administracionService.listarTablaAlmacenExterno1(itemBean);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		
			return listaAlmacenExterno;
	}
	
	@RequestMapping(value = "/listarTablaPoblacionInei", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarTablaPoblacionInei(HttpServletRequest request, HttpServletResponse response) {
		Object dato = new Object();
		
		List<MaeDistritoIneiBean> listaAlmacenExterno = null;
		
		String nomTabla="";
		try {
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			ItemBean itemBean = new ItemBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(itemBean, request.getParameterMap());

			
			listaAlmacenExterno = administracionService.listarTablaPoblacionInei(itemBean);
			
			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		
			return listaAlmacenExterno;
		
		
	}
	
	/**
	 * @param codigo 
	 * @param model
	 * @return - Retorna a la vista JSP.
	 */
	@RequestMapping(value = "/mantenimientoTablas/{codigo}", method = RequestMethod.GET)
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
        	
        	donacionesBean.setAnio(usuarioBean.getCodigoAnio());
        	donacionesBean.setMes(usuarioBean.getCodigoMes());
        	if (!isNullInteger(codigo)) {
        	
        		donacionesBean = donacionService.obtenerDonacionSalidaXIdSalida(codigo);

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

            	listaDee1 = generalService.listarDee(new ItemBean(Constantes.ONE_INT));

            	//model.addAttribute("lista_proce_pais", generalService.listarPais(new ItemBean(Constantes.ZERO_INT)));

            	datoDonaciones.setIdDdi(idDdi);

        	}
        	
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
        	

        	model.addAttribute("lista_medio_transporte", generalService.listarMedioTransporte(new ItemBean()));
//        	

        	
        	model.addAttribute("donaciones", getParserObject(donacionesBean));
        	
        	model.addAttribute("lista_dee", generalService.listarDee(new ItemBean(Constantes.ZERO_INT)));
        	
        	model.addAttribute("lista_proce_pais", generalService.listarPais(new ItemBean(Constantes.ZERO_INT)));
        	
        	//model.addAttribute("lista_donadores", donacionService.listarDonadores(datoDonaciones));
        	
        	model.addAttribute("lista_oficinas", generalService.listarOficinas(new ItemBean(Constantes.ZERO_INT)));
        	
        	//model.addAttribute("nombreDee", listaDee1.get(0).getDescripcion());
        	
        	model.addAttribute("lista_categoria", generalService.listarCategoria(new ItemBean(Constantes.ZERO_INT)));
        	//falta pintar
        	model.addAttribute("lista_tipo_documento", generalService.listarTipoDocumento(new ItemBean(Constantes.ZERO_INT)));
        	
        	//Para estados
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "registrar-tablas-generales";
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
	/////////////////////////////////////////////////////////////////
	////////////////Grabar tablas generales/////////////////////////
	///////////////////////////////////////////////////////////////
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/grabarAlmacen", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarAlmacen(HttpServletRequest request, HttpServletResponse response) {
		MaeAlmacenBean almacen = null;
		try {			
			MaeAlmacenBean maeAlmacenBean = new MaeAlmacenBean();
			

			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(maeAlmacenBean, request.getParameterMap());
			maeAlmacenBean.setUsuario(usuarioBean.getUsuario());
			if(maeAlmacenBean.getIdAlmacen()==0){
				MaeAlmacenBean codigo = new MaeAlmacenBean();
				codigo =  administracionService.obtenerCodigoAlmacen(maeAlmacenBean);
				maeAlmacenBean.setCodAlmacen(codigo.getCodAlmacen());
				almacen = administracionService.insertarRegistroAlmacen(maeAlmacenBean);			
			}else{
				almacen = administracionService.actualizarRegistroAlmacen(maeAlmacenBean);			
			}
			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return almacen;
	}
	
	@RequestMapping(value = "/grabarAlmacenExterno", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarAlmacenExterno(HttpServletRequest request, HttpServletResponse response) {
		MaeAlmacenExternoBean personalExterno = null;
		try {		
			MaeAlmacenExternoBean maeAlmacenExternoBean = new MaeAlmacenExternoBean();
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(maeAlmacenExternoBean, request.getParameterMap());
			maeAlmacenExternoBean.setUsuario(usuarioBean.getUsuario());
			
			List<ItemBean> Region = administracionService.obtenerRegionXDdi(usuarioBean.getIdDdi());
			System.out.println("REGION: "+!isEmpty(Region));
			System.out.println("ID "+Region.get(0).getIcodigo());
    		if (!isEmpty(Region)) {
    			maeAlmacenExternoBean.setIdRegion(Region.get(0).getIcodigo());
    		}

			String control="U";
			if(maeAlmacenExternoBean.getIdAlmacenExterno()==0){
				control="I";
			}
			personalExterno = administracionService.insertarRegistroAlmacenExterno(maeAlmacenExternoBean, control);			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return personalExterno;
	}
	
	
	@RequestMapping(value = "/grabarAnio", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarAnio(HttpServletRequest request, HttpServletResponse response) {
		MaeAniosBean anios = null;
		try {			
			MaeAniosBean maeAniosBean = new MaeAniosBean();
			

			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(maeAniosBean, request.getParameterMap());
		
			anios = administracionService.insertarRegistroAnio(maeAniosBean);			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return anios;
	}
	
	@RequestMapping(value = "/grabarDdi", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarDdi(HttpServletRequest request, HttpServletResponse response) {
		MaeDdiBean ddi = null;
		try {			
			MaeDdiBean maeDdiBean = new MaeDdiBean();
			
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(maeDdiBean, request.getParameterMap());
			System.out.println("ID: "+maeDdiBean.getIdDdi());
			String control="U";
			if(maeDdiBean.getIdDdi()==0){
				control="I";
			}
			ddi = administracionService.insertarRegistroDdi(maeDdiBean, control);			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return ddi;
	}
	
	@RequestMapping(value = "/grabarDonante", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarDonante(HttpServletRequest request, HttpServletResponse response) {
		MaeDonanteBean donante = null;
		try {			
			MaeDonanteBean maeDonanteBean = new MaeDonanteBean();
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(maeDonanteBean, request.getParameterMap());
			maeDonanteBean.setIdDdi(usuarioBean.getIdDdi());
			String control="U";
			if(maeDonanteBean.getIdDonante()==0){
				control="I";
			}
			donante = administracionService.insertarRegistroDonante(maeDonanteBean, control);			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return donante;
	}
	
	@RequestMapping(value = "/grabarEmpresaTransporte", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarEmpresaTransporte(HttpServletRequest request, HttpServletResponse response) {
		MaeEmpresaTransporteBean empresaTransporte = null;
		try {			
			MaeEmpresaTransporteBean maeEmpresaTransporteBean = new MaeEmpresaTransporteBean();
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(maeEmpresaTransporteBean, request.getParameterMap());
			maeEmpresaTransporteBean.setIdDdi(usuarioBean.getIdDdi());
			String control="U";
			if(maeEmpresaTransporteBean.getIdEmpresaTransporte()==0){
				control="I";
			}
			empresaTransporte = administracionService.insertarRegistroEmpresaTransportes(maeEmpresaTransporteBean, control);			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return empresaTransporte;
	}
	
	@RequestMapping(value = "/grabarEnvase", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarEnvase(HttpServletRequest request, HttpServletResponse response) {
		MaeEnvaseBean envase = null;
		try {			
			MaeEnvaseBean maeEnvaseBean = new MaeEnvaseBean();
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(maeEnvaseBean, request.getParameterMap());
			
			String control="U";
			if(maeEnvaseBean.getIdEnvase()==0){
				control="I";
			}
			envase = administracionService.insertarRegistroEnvase(maeEnvaseBean, control);			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return envase;
	}
	
	@RequestMapping(value = "/grabarModAlmacen", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarModAlmacen(HttpServletRequest request, HttpServletResponse response) {
		MaeModAlmacenBean modAlmacen = null;
		try {			
			MaeModAlmacenBean maeModAlmacenBean = new MaeModAlmacenBean();
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(maeModAlmacenBean, request.getParameterMap());
			
			String control="U";
			if(maeModAlmacenBean.getIdModAlmacen()==0){
				control="I";
			}
			modAlmacen = administracionService.insertarRegistroModAlmacen(maeModAlmacenBean, control);			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return modAlmacen;
	}
	
	@RequestMapping(value = "/grabarOficina", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarOficina(HttpServletRequest request, HttpServletResponse response) {
		MaeOficinaBean oficina = null;
		try {		
			MaeOficinaBean maeOficinaBean = new MaeOficinaBean();
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(maeOficinaBean, request.getParameterMap());
			//maeOficinaBean.setIdDdi(usuarioBean.getIdDdi());
			String control="U";
			if(maeOficinaBean.getIdOficina()==0){
				control="I";
			}
			oficina = administracionService.insertarRegistroOficina(maeOficinaBean, control);			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return oficina;
	}
	
	@RequestMapping(value = "/grabarPersonal", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarPersonal(HttpServletRequest request, HttpServletResponse response) {
		MaePersonalBean personal = null;
		try {		
			MaePersonalBean maePersonalBean = new MaePersonalBean();
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(maePersonalBean, request.getParameterMap());
			maePersonalBean.setIdDdi(usuarioBean.getIdDdi());
			maePersonalBean.setUsuario(usuarioBean.getUsuario());
			String control="U";
			if(maePersonalBean.getIdPersonal()==0){
				control="I";
			}
			personal = administracionService.insertarRegistroPersonal(maePersonalBean, control);			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return personal;
	}
	
	@RequestMapping(value = "/grabarPersonalExterno", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarPersonalExterno(HttpServletRequest request, HttpServletResponse response) {
		MaePersonalExternoBean personalExterno = null;
		try {		
			MaePersonalExternoBean maePersonalExternoBean = new MaePersonalExternoBean();
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(maePersonalExternoBean, request.getParameterMap());
			//maePersonalBean.setIdDdi(usuarioBean.getIdDdi());
			
			List<ItemBean> Region = administracionService.obtenerRegionXDdi(usuarioBean.getIdDdi());
			System.out.println("REGION: "+!isEmpty(Region));
			System.out.println("ID "+Region.get(0).getIcodigo());
    		if (!isEmpty(Region)) {
    			maePersonalExternoBean.setIdRegion(Region.get(0).getIcodigo());
    		}

			String control="U";
			if(maePersonalExternoBean.getIdPersonalExterno()==0){
				control="I";
			}
			maePersonalExternoBean.setUsuario(usuarioBean.getUsuario());
			personalExterno = administracionService.insertarRegistroPersonalExterno(maePersonalExternoBean, control);			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return personalExterno;
	}
	
	@RequestMapping(value = "/grabarTipoCamion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarTipoCamion(HttpServletRequest request, HttpServletResponse response) {
		MaeTipCamionBean tipCamion = null;
		try {		
			MaeTipCamionBean maeTipCamionBean = new MaeTipCamionBean();
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(maeTipCamionBean, request.getParameterMap());
			//maePersonalBean.setIdDdi(usuarioBean.getIdDdi());

			String control="U";
			if(maeTipCamionBean.getIdTipCamion()==0){
				control="I";
			}
			tipCamion = administracionService.insertarRegistroTipoCamion(maeTipCamionBean, control);			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return tipCamion;
	}
	
	@RequestMapping(value = "/grabarTipoControlCalidad", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarTipoControlCalidad(HttpServletRequest request, HttpServletResponse response) {
		MaeTipControlCalidadBean tipControlCalidad = null;
		try {		
			MaeTipControlCalidadBean maeTipControlCalidadBean = new MaeTipControlCalidadBean();
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(maeTipControlCalidadBean, request.getParameterMap());
			//maePersonalBean.setIdDdi(usuarioBean.getIdDdi());

			String control="U";
			if(maeTipControlCalidadBean.getIdTipControl()==0){
				control="I";
			}
			tipControlCalidad = administracionService.insertarRegistroControlCalidad(maeTipControlCalidadBean, control);			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return tipControlCalidad;
	}
	
	@RequestMapping(value = "/grabarTipoDocumento", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarTipoDocumento(HttpServletRequest request, HttpServletResponse response) {
		MaeTipDocumentoBean tipDocumento = null;
		try {		
			MaeTipDocumentoBean maeTipDocumentoBean = new MaeTipDocumentoBean();
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(maeTipDocumentoBean, request.getParameterMap());
			//maePersonalBean.setIdDdi(usuarioBean.getIdDdi());

			String control="U";
			if(maeTipDocumentoBean.getIdTipDocumento()==0){
				control="I";
			}
			tipDocumento = administracionService.insertarRegistroDocumentos(maeTipDocumentoBean, control);			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return tipDocumento;
	}
	
	@RequestMapping(value = "/grabarUnidadMedida", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarUnidadMedida(HttpServletRequest request, HttpServletResponse response) {
		MaeUnidadMedidaBean unidadMedida = null;
		try {		
			MaeUnidadMedidaBean maeUnidadMedidaBean = new MaeUnidadMedidaBean();
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(maeUnidadMedidaBean, request.getParameterMap());
			//maePersonalBean.setIdDdi(usuarioBean.getIdDdi());

			String control="U";
			if(maeUnidadMedidaBean.getIdUnidad()==0){
				control="I";
			}
			unidadMedida = administracionService.insertarRegistroUnidadMedida(maeUnidadMedidaBean, control);			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return unidadMedida;
	}
	
	@RequestMapping(value = "/grabarMarca", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarMarca(HttpServletRequest request, HttpServletResponse response) {
		MaeMarcaBean marca = null;
		try {		
			MaeMarcaBean maeMarcaBean = new MaeMarcaBean();
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(maeMarcaBean, request.getParameterMap());
			//maePersonalBean.setIdDdi(usuarioBean.getIdDdi());
			maeMarcaBean.setUsuario(usuarioBean.getUsuario());
			String control="U";
			if(maeMarcaBean.getIdMarca()==0){
				control="I";
			}
			marca = administracionService.insertarRegistroMarca(maeMarcaBean, control);			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return marca;
	}
	
	@RequestMapping(value = "/grabarChofer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarChofer(HttpServletRequest request, HttpServletResponse response) {
		MaeChoferBean chofer = null;
		try {		
			MaeChoferBean maeChoferBean = new MaeChoferBean();
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(maeChoferBean, request.getParameterMap());
			maeChoferBean.setUsuario(usuarioBean.getUsuario());
			String control="U";
			if(maeChoferBean.getIdChofer()==0){
				control="I";
			}
			chofer = administracionService.insertarRegistroChofer(maeChoferBean, control);			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return chofer;
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
			if (isEmpty(lista) || isEmpty(listaProductos)) {
				return Constantes.COD_VALIDACION_GENERAL;
			}			
			DonacionesSalidaBean general = lista.get(0);

			ExportarArchivo printer = new ExportarArchivo();
			StringBuilder jasperFile = new StringBuilder();
			jasperFile.append(getPath(request));
			jasperFile.append(File.separator);
			jasperFile.append(Constantes.REPORT_PATH_DONACIONES);
			jasperFile.append("Orden_Salida.jrxml");
			
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
			
			byte[] array = printer.exportPdf(jasperFile.toString(), parameters, listaProductos);
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
	
	@RequestMapping(value = "/obtenerCodigoAlmacenExterno", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object obtenerCodigoAlmacenExterno(HttpServletRequest request, HttpServletResponse response) {
		MaeAlmacenExternoBean lista = null;
		try {			
			MaeAlmacenExternoBean maeAlmacenExternoBean = new MaeAlmacenExternoBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(maeAlmacenExternoBean, request.getParameterMap());
			lista = administracionService.obtenerCodigoAlmacenExterno(maeAlmacenExternoBean);
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
	
	@RequestMapping(value = "/exportarExcelInei/{codanio}/{coddepa}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcelInei(@PathVariable("codanio") String codanio, 
								@PathVariable("coddepa") String coddepa, 
							
								HttpServletResponse response) {
	    try {
	    	ItemBean itemBean = new ItemBean();
	    	itemBean.setVcodigoParam2((codanio));
	    	itemBean.setVcodigoParam3((coddepa));

			List<MaeDistritoIneiBean> lista = administracionService.listarTablaPoblacionInei(itemBean);
	    	
			String file_name = "Formato_Inei";
			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_XLS);
			
			ReporteTablasGenerales reporte = new ReporteTablasGenerales();
		    HSSFWorkbook wb = reporte.generaReporteExcelPoblacionInei(lista);
			
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
	
	@RequestMapping(value = "/exportarExcelTablaInei/{codanio}/{coddepa}", method = RequestMethod.GET)
	@ResponseBody
	public String exportarExcelTablaInei(@PathVariable("codanio") String codanio, 
								@PathVariable("coddepa") String coddepa, 
							
								HttpServletResponse response) {
	    try {
	    	ItemBean itemBean = new ItemBean();
	    	itemBean.setVcodigoParam2((codanio));
	    	itemBean.setVcodigoParam3((coddepa));

			List<MaeDistritoIneiBean> lista = administracionService.listarTablaPoblacionInei(itemBean);
	    	
			String file_name = "UBIGEO_INEI_"+codanio;
			file_name = file_name.concat(Constantes.EXTENSION_FORMATO_XLS);
			
			ReporteTablasGenerales reporte = new ReporteTablasGenerales();
		    HSSFWorkbook wb = reporte.generaReporteExcelTablaInei(lista);
			
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
	
	@RequestMapping(value = "/listarCorrelativo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarCorrelativo(HttpServletRequest request, HttpServletResponse response) {
		List<MaeCorrelativoBean> listaCorrelativo = null;
		try {
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			listaCorrelativo = administracionService.listarCorrelativo(usuarioBean.getIdAlmacen());

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		
			return listaCorrelativo;
	}
	
	@RequestMapping(value = "/grabarCorrelativo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarCorrelativo(HttpServletRequest request, HttpServletResponse response) {
		MaeCorrelativoBean correlativo = new MaeCorrelativoBean();
		try {		
			MaeCorrelativoBean maeCorrelativoBean = new MaeCorrelativoBean();
			MaeCorrelativoBean dataVigencia = new MaeCorrelativoBean();
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(maeCorrelativoBean, request.getParameterMap());
			maeCorrelativoBean.setUsuario(usuarioBean.getUsuario());
			maeCorrelativoBean.setControl("U");
			
			String respuestaVigencia="";
			System.out.println("getIdCorrelativo "+maeCorrelativoBean.getIdCorrelativo());
			if(maeCorrelativoBean.getIdCorrelativo()==0){
				maeCorrelativoBean.setControl("I");
				dataVigencia = administracionService.validaVigencia(usuarioBean.getIdAlmacen());
				if(dataVigencia.getVigencia().equals("1")){
					correlativo.setCodigoRespuesta("03");
					correlativo.setMensajeRespuesta("No se puede registrar un nueva autorización si antes no desactiva la vigente para este almacén");
				}else{
					correlativo = administracionService.insertarActualizarCorrelativo(maeCorrelativoBean, maeCorrelativoBean.getControl());
				}
				
			}else{
				correlativo = administracionService.insertarActualizarCorrelativo(maeCorrelativoBean, maeCorrelativoBean.getControl());
			}
			System.out.println("CONTROL "+maeCorrelativoBean.getControl());
			System.out.println("VIGENCIA "+dataVigencia.getVigencia());
									
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return correlativo;
	}

}
