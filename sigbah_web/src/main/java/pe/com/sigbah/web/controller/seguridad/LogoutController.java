package pe.com.sigbah.web.controller.seguridad;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;

import pe.com.sigbah.web.controller.common.BaseController;

/**
 * @className: LogoutController.java
 * @description: 
 * @date: 22 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
@Controller
@RequestMapping("/logout")
public class LogoutController extends BaseController {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param model 
	 * @param request 
	 * @return - Retorna a la vista JSP.
	 */
	@RequestMapping(value = "/inicio", method = {RequestMethod.GET, RequestMethod.POST})
    public String inicio(Model model, HttpServletRequest request) {
        context().removeAttribute("usuarioBean", RequestAttributes.SCOPE_SESSION);
		context().removeAttribute("menuBean", RequestAttributes.SCOPE_SESSION);
        return "forward:/login";
    }

}
