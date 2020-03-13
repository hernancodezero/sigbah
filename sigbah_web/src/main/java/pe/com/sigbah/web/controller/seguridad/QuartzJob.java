package pe.com.sigbah.web.controller.seguridad;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.request.RequestAttributes;

import pe.com.sigbah.common.bean.UsuarioBean;

/**
 *
 * @author Ali
 */
public class QuartzJob implements Job {
    
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException{
    	
    	PrincipalController preguntar = new PrincipalController();
    	preguntar.preguntarSesion();
    	
        Date fechaHoy = new Date(); // your date
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaHoy);
        System.out.println("entro job: "+cal);

    }
}
