package pe.com.sigbah.web.controller.seguridad;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;

import pe.com.sigbah.common.bean.AlmacenBean;
import pe.com.sigbah.common.bean.CierreStockBean;
import pe.com.sigbah.common.bean.DetalleUsuarioBean;
import pe.com.sigbah.common.bean.UsuarioBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.DateUtil;
import pe.com.sigbah.service.AdministracionService;
import pe.com.sigbah.web.controller.common.BaseController;

/**
 * @className: LoginController.java
 * @description: Clase para manejar el login.
 * @date: 10/05/2015 22:36:29
 * @author: SUMERIO
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	private Integer indicadorSesion=0;
	
	private static final long serialVersionUID = 1L;

	@Autowired 
	private AdministracionService administracionService;
	
	

	/**
	 * @param request
	 * @param model
	 * @return - Retorna a la vista JSP.
	 */
	@RequestMapping(method = RequestMethod.GET)
    public String doShowForm(HttpServletRequest request, Model model) {
        UsuarioBean usuario = new UsuarioBean();
        model.addAttribute("usuario", usuario);
        if(usuario.getIdUsuario()==null && indicadorSesion==1){
        	model.addAttribute("mensajeError", "La cuenta del usuario o la contraseña es inválida");
        }
        indicadorSesion=0;
        LOGGER.info("[login] "+usuario);
        return "login";
    }
    
    /**
     * @param usuario
     * @param result
     * @param request 
     * @param response 
     * @param model 
	 * @return - Retorna a la vista JSP.
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public Object doProcessForm(@ModelAttribute("usuario") UsuarioBean usuario, BindingResult result, 
    							HttpServletRequest request, HttpServletResponse response, Model model) {
    	String indicador = Constantes.ZERO_STRING;
        boolean isAccessOk = true;
        
        if (usuario == null) {
            isAccessOk = false;
        }

        if (!result.hasErrors() && isAccessOk) {
            isAccessOk = false;
            Integer idAlmacen = usuario.getIdAlmacen();
            try {
            	DetalleUsuarioBean detalleUsuarioBean = administracionService.obtenerDatosUsuario(usuario);
            	
            	if (detalleUsuarioBean != null && !isEmpty(detalleUsuarioBean.getDatosUsuario())) {
            		usuario = detalleUsuarioBean.getDatosUsuario().get(0);
            	} else {
            		usuario = null;
            	}
               
	            if (usuario != null) {
	            	indicadorSesion=0;
	            	List<AlmacenBean> listaAlmacenUsuario = administracionService.ListaAlmacenesXUsuario(usuario.getIdUsuario());
	            	if (!isEmpty(listaAlmacenUsuario) && listaAlmacenUsuario.size() > 1) {
	            		if (!isNullInteger(idAlmacen)) {
	            			AlmacenBean almacenBean = obtenerAlmacen(listaAlmacenUsuario, idAlmacen);
	            			CierreStockBean mesTrabajo = administracionService.obtenerMesTrabajo(almacenBean.getIdAlmacen());
	            			usuario.setCodigoAnio(mesTrabajo.getCodigoAnio());
	            			usuario.setCodigoMes(mesTrabajo.getCodigoMes());
	            			usuario.setIdAlmacen(almacenBean.getIdAlmacen());
	            			usuario.setCodigoAlmacen(almacenBean.getCodigoAlmacen());
	            			usuario.setNombreAlmacen(almacenBean.getNombreAlmacen());
	            			
	            			administracionService.valoresIniciales(almacenBean.getIdAlmacen(), usuario.getUsuario());
	            			context().setAttribute("usuarioBean", usuario, RequestAttributes.SCOPE_SESSION);
		            		indicador = Constantes.ONE_STRING;
	            		} else {
	            			return listaAlmacenUsuario;
	            		}
	            	} else {
	            		if (listaAlmacenUsuario.size() == 1) { // Tiene asignado un almacen
	            			AlmacenBean almacen = listaAlmacenUsuario.get(0);
	            			CierreStockBean mesTrabajo = administracionService.obtenerMesTrabajo(almacen.getIdAlmacen());
	            			usuario.setCodigoAnio(mesTrabajo.getCodigoAnio());
	            			usuario.setCodigoMes(mesTrabajo.getCodigoMes());
		            		usuario.setIdAlmacen(almacen.getIdAlmacen());
	            			usuario.setCodigoAlmacen(almacen.getCodigoAlmacen());
	            			usuario.setNombreAlmacen(almacen.getNombreAlmacen());
	            		
	            			administracionService.valoresIniciales(almacen.getIdAlmacen(), usuario.getUsuario());
	            		} else { // No tiene asignado ningun almacen
	                        usuario.setCodigoAnio(String.valueOf(DateUtil.getAnioActual()));
	            		}
	            		context().setAttribute("usuarioBean", usuario, RequestAttributes.SCOPE_SESSION);
	            		indicador = Constantes.ONE_STRING;
	            	}
	            }else{
	            	indicadorSesion=1;
	            	System.out.println("La cuenta del Usuario o la Contraseña es inválido");
	            }
            } catch (Exception e) {
            	LOGGER.error(e.getMessage(), e);
                usuario = null;
                return getBaseRespuesta(null);
            }
        }
        return indicador;
    }
    
    private AlmacenBean obtenerAlmacen(List<AlmacenBean> listaAlmacenUsuario, Integer idAlmacen) {
    	for (AlmacenBean almacen : listaAlmacenUsuario) {
    		if (almacen.getIdAlmacen().equals(idAlmacen)) {
    			return almacen;
    		}
    	}
		return null;
    }

}
