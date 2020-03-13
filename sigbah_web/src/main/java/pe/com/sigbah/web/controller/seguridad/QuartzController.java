package pe.com.sigbah.web.controller.seguridad;


import pe.com.sigbah.web.controller.seguridad.QuartzJob;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author proydal
 */
public class QuartzController implements ServletContextListener {
        Scheduler scheduler = null;
        
        @Override
        public void contextInitialized(ServletContextEvent servletContext) {
                System.out.println("Context Initialized");
                
                try {
                        scheduler=StdSchedulerFactory.getDefaultScheduler();
                        JobDetail job = JobBuilder.newJob(QuartzJob.class).build();
                        Trigger t1 = TriggerBuilder.newTrigger().withIdentity("CronTrigger","group1").withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(20).repeatForever()).build();

//                        JobDetail job1 = JobBuilder.newJob(QuartzJobCumpleanos.class).build();    
//                        Trigger t2 = TriggerBuilder.newTrigger().withIdentity("CronCumpleaños","group2").withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(12).repeatForever()).build();
                        scheduler.start();
                        scheduler.scheduleJob(job,t1); 
                      //  scheduler.scheduleJob(job1,t2);
                }
                catch (SchedulerException e) {
                        e.printStackTrace();
                }
        }

        @Override
        public void contextDestroyed(ServletContextEvent servletContext) {
                System.out.println("Context Destroyed");
                try 
                {
                        scheduler.shutdown();
                } 
                catch (SchedulerException e) 
                {
                        e.printStackTrace();
                }
        }
}