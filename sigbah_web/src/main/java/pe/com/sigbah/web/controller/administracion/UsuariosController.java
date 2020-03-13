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
import pe.com.sigbah.common.bean.AlmacenBean;
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
import pe.com.sigbah.common.bean.RolBean;
import pe.com.sigbah.common.bean.RolMenuBean;
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
import pe.com.sigbah.common.bean.EstadosXUsuarioBean;


/**
 * @className: UsuariosController.java
 * @description: 
 * @date: 21/08/2017
 * @author: ARCHY.
 */
@Controller
@RequestMapping("/administracion/usuarios")
public class UsuariosController extends BaseController {

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

           	model.addAttribute("lista_ddi", generalService.listarDdi(new ItemBean(Constantes.ZERO_INT)));
        	
        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "listar-usuarios";
    }
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarUsuarios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarUsuarios(HttpServletRequest request, HttpServletResponse response) {
		List<UsuarioBean> lista = null;
		try {
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			UsuarioBean user = new UsuarioBean();	
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(user, request.getParameterMap());
			
			lista = administracionService.listarUsuarios(user);
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
	@RequestMapping(value = "/mantenimientoUsuarios/{codigo}", method = RequestMethod.GET)
    public String mantenimientoDonaciones(@PathVariable("codigo") Integer codigo,@PathVariable("codigo") Integer dato, Model model) {
        try {
        	
        	UsuarioBean user = new UsuarioBean();
        	// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);

        	if (!isNullInteger(codigo)) {
        	
        		user = administracionService.obtenerUsuarioXId(codigo);
        		
        	} else {


            	
        	}
        	
        	if (!Utils.isNullInteger(user.getIdUsuario())) {
        		
        		
        		model.addAttribute("lista_monedas", generalService.listarMoneda(new ItemBean(Constantes.ZERO_INT)));
        	}
        	//////////////////
        	
        	model.addAttribute("usuario", getParserObject(user));
        	model.addAttribute("lista_ddi", generalService.listarDdi(new ItemBean(Constantes.ZERO_INT)));
//        	model.addAttribute("base", getBaseRespuesta(Constantes.COD_EXITO_GENERAL));
        	HtmlUtils utils = new HtmlUtils();
        	model.addAttribute("menuUsuario", usuarioBean.getMenuXUsuario().replaceAll(";", "\""));
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        	model.addAttribute("base", getBaseRespuesta(null));
        }
        return "registrar-usuarios";
    }
	
	/**
	 * @param request
	 * @param response
	 * @return objeto en formato json
	 */
	@RequestMapping(value = "/listarAlmacenes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarAlmacenes(HttpServletRequest request, HttpServletResponse response) {
		List<AlmacenBean> lista = null;
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			AlmacenBean almacen = new AlmacenBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(almacen, request.getParameterMap());
			System.out.println("ID USUARIO: "+almacen.getIdUsuario());
			lista = administracionService.ListaAlmacenesXUsuarioDdi(almacen);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/insertarAlmacenes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object insertarAlmacenes(HttpServletRequest request, HttpServletResponse response) {
		List<AlmacenBean> lista = null;
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			AlmacenBean almacen = new AlmacenBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(almacen, request.getParameterMap());
			almacen.setUsuario(usuarioBean.getUsuario());
			administracionService.insertarAlmacenXUsuario(almacen);
			lista = administracionService.ListaAlmacenesXUsuarioDdi(almacen);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/eliminarAlmacenes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarAlmacenes(HttpServletRequest request, HttpServletResponse response) {
		List<AlmacenBean> lista = null;
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			AlmacenBean almacen = new AlmacenBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(almacen, request.getParameterMap());
			almacen.setUsuario(usuarioBean.getUsuario());
			administracionService.eliminarAlmacenXUsuario(almacen);
			lista = administracionService.ListaAlmacenesXUsuarioDdi(almacen);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	
	@RequestMapping(value = "/listarRoles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarRoles(HttpServletRequest request, HttpServletResponse response) {
		List<RolBean> lista = null;
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			RolBean rol = new RolBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(rol, request.getParameterMap());
			lista = administracionService.listarRoles(rol.getIdUsuario());
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/insertarRoles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object insertarRoles(HttpServletRequest request, HttpServletResponse response) {
		List<RolBean> lista = null;
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			RolBean rol = new RolBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(rol, request.getParameterMap());
			rol.setUsuario(usuarioBean.getUsuario());
			administracionService.insertarRolXUsuario(rol);
			lista = administracionService.listarRoles(rol.getIdUsuario());
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/eliminarRoles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarRoles(HttpServletRequest request, HttpServletResponse response) {
		List<RolBean> lista = null;
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			RolBean rol = new RolBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(rol, request.getParameterMap());
			rol.setUsuario(usuarioBean.getUsuario());
			administracionService.eliminarRolXUsuario(rol);
			lista = administracionService.listarRoles(rol.getIdUsuario());
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/mostrarRoles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object mostrarRoles(HttpServletRequest request, HttpServletResponse response) {
		List<RolMenuBean> lista = null;
		ItemBean listaMenu = new ItemBean();
		String menuRol="";
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			RolMenuBean rolMenu = new RolMenuBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(rolMenu, request.getParameterMap());
			lista = administracionService.mostrarRoles(rolMenu.getIdRol());
			
			HtmlUtils utils = new HtmlUtils();
			listaMenu.setDescripcion(utils.mostrarRol(lista));
			if(lista.size()>0){
				listaMenu.setDescripcionCorta(lista.get(0).getNombreRol());
			}else{
				listaMenu.setDescripcionCorta("");
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return listaMenu;
	}
	
	@RequestMapping(value = "/listarEstadosDonacion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarEstadosDonacion(HttpServletRequest request, HttpServletResponse response) {
		List<EstadosXUsuarioBean> lista = null;
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			EstadosXUsuarioBean estado = new EstadosXUsuarioBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(estado, request.getParameterMap());
			lista = administracionService.listarEstadosDonacion(estado.getIdUsuario());
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/listarEstadosProgramacion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarEstadosProgramacion(HttpServletRequest request, HttpServletResponse response) {
		List<EstadosXUsuarioBean> lista = null;
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			EstadosXUsuarioBean estado = new EstadosXUsuarioBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(estado, request.getParameterMap());
			lista = administracionService.listarEstadosProgramacion(estado.getIdUsuario());
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/listarEstadosInventario", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object listarEstadosInventario(HttpServletRequest request, HttpServletResponse response) {
		List<EstadosXUsuarioBean> lista = null;
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			EstadosXUsuarioBean estado = new EstadosXUsuarioBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(estado, request.getParameterMap());
			lista = administracionService.listarEstadosInventario(estado.getIdUsuario());
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}

	@RequestMapping(value = "/insertarDonaciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object insertarDonaciones(HttpServletRequest request, HttpServletResponse response) {
		List<EstadosXUsuarioBean> lista = null;
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			EstadosXUsuarioBean estados = new EstadosXUsuarioBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(estados, request.getParameterMap());
			estados.setUsuario(usuarioBean.getUsuario());
			administracionService.insertarDonacionXUsuario(estados);
			lista = administracionService.listarEstadosDonacion(estados.getIdUsuario());
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/eliminarDonaciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarDonaciones(HttpServletRequest request, HttpServletResponse response) {
		List<EstadosXUsuarioBean> lista = null;
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			EstadosXUsuarioBean estados = new EstadosXUsuarioBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(estados, request.getParameterMap());
			estados.setUsuario(usuarioBean.getUsuario());
			administracionService.eliminarDonacionXUsuario(estados);
			lista = administracionService.listarEstadosDonacion(estados.getIdUsuario());
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/insertarProgramaciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object insertarProgramaciones(HttpServletRequest request, HttpServletResponse response) {
		List<EstadosXUsuarioBean> lista = null;
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			EstadosXUsuarioBean estados = new EstadosXUsuarioBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(estados, request.getParameterMap());
			estados.setUsuario(usuarioBean.getUsuario());
			administracionService.insertarProgramacionXUsuario(estados);
			lista = administracionService.listarEstadosProgramacion(estados.getIdUsuario());
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/eliminarProgramaciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarProgramaciones(HttpServletRequest request, HttpServletResponse response) {
		List<EstadosXUsuarioBean> lista = null;
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			EstadosXUsuarioBean estados = new EstadosXUsuarioBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(estados, request.getParameterMap());
			estados.setUsuario(usuarioBean.getUsuario());
			administracionService.eliminarProgramacionXUsuario(estados);
			lista = administracionService.listarEstadosProgramacion(estados.getIdUsuario());
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/insertarInventarios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object insertarInventarios(HttpServletRequest request, HttpServletResponse response) {
		List<EstadosXUsuarioBean> lista = null;
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			EstadosXUsuarioBean estados = new EstadosXUsuarioBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(estados, request.getParameterMap());
			estados.setUsuario(usuarioBean.getUsuario());
			administracionService.insertarInventarioXUsuario(estados);
			lista = administracionService.listarEstadosInventario(estados.getIdUsuario());
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return lista;
	}
	
	@RequestMapping(value = "/eliminarInventarios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object eliminarInventarios(HttpServletRequest request, HttpServletResponse response) {
		List<EstadosXUsuarioBean> lista = null;
		try {			
			usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
			EstadosXUsuarioBean estados = new EstadosXUsuarioBean();			
			// Copia los parametros del cliente al objeto
			BeanUtils.populate(estados, request.getParameterMap());
			estados.setUsuario(usuarioBean.getUsuario());
			administracionService.eliminarInventarioXUsuario(estados);
			lista = administracionService.listarEstadosInventario(estados.getIdUsuario());
			
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
	@RequestMapping(value = "/grabarUsuario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object grabarUsuario(HttpServletRequest request, HttpServletResponse response) {
		UsuarioBean usuarios = null;
		try {			
			UsuarioBean user = new UsuarioBean();
			
			// Convierte los vacios en nulos en los enteros
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
			// Copia los parametros del cliente al objeto
			beanUtilsBean.populate(user, request.getParameterMap());
			// Retorno los datos de session
        	usuarioBean = (UsuarioBean) context().getAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
        	
			if (user.getIdUsuario()!=0) {
				usuarios = administracionService.actualizarUsuario(user);
				usuarios.setMensajeRespuesta(getMensaje(messageSource, "msg.info.grabadoOk"));				
			} else {			
				usuarios = administracionService.insertarUsuario(user);			
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return getBaseRespuesta(null);
		}
		return usuarios;
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
