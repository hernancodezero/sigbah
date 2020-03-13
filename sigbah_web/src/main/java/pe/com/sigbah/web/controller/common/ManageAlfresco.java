package pe.com.sigbah.web.controller.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import pe.com.sigbah.common.util.Constantes;

/**
 * @className: PostAlfrescoBean.java
 * @description:
 * @date: 29 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
@SuppressWarnings({ "deprecation", "resource" })
@Component
public class ManageAlfresco implements Serializable {

	private static final long serialVersionUID = 1L;
	protected transient final Log LOGGER = LogFactory.getLog(getClass());
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * @param servletContext
	 */
//	public void setServletContext(ServletContext servletContext) {
//	    this.context = servletContext;
//	}

	/**
	 * @param path
	 * @param uploadDirectory
	 * @param contentType
	 * @return Correlativo secuencial de Alfresco.
	 * @throws Exception 
	 */
	public String uploadFile(String path, String uploadDirectory, String contentType) throws Exception {
		String idAlfresco = "";
		try {
			LOGGER.info("[uploadFile] Inicio - Se ingresa a ejecutar Alfresco ");
			
			String ip = getPropertyValue(messageSource, "params.alfresco.ip");
			String port = getPropertyValue(messageSource, "params.alfresco.port");
			String user = getPropertyValue(messageSource, "params.alfresco.user");
			String pass = getPropertyValue(messageSource, "params.alfresco.pass");
			
			LOGGER.info("[uploadFile] ip >>> "+ip);
			LOGGER.info("[uploadFile] port >>> "+port);
			LOGGER.info("[uploadFile] user >>> "+user);
			LOGGER.info("[uploadFile] pass >>> "+pass);
			
			String authTicket = login(user, pass, ip, port);
			
			String description = getPropertyValue(messageSource, "params.alfresco.description");			
			String siteId = getPropertyValue(messageSource, "params.alfresco.siteId");
			String containerId = getPropertyValue(messageSource, "params.alfresco.containerId");

			HttpClient client = new DefaultHttpClient();
			HttpHost targetHost = new HttpHost(ip, Integer.parseInt(port), getPropertyValue(messageSource, "params.alfresco.http"));
			HttpPost mPost = new HttpPost(getPropertyValue(messageSource, "params.alfresco.http.post.upload") + authTicket);

			File upFile = new File(path);
			FileBody bin = new FileBody(upFile);

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.addPart("filedata", bin);
			builder.addTextBody("contenttype", contentType);
			builder.addTextBody("siteid", siteId);
			builder.addTextBody("containerid", containerId);
			builder.addTextBody("uploaddirectory", uploadDirectory);
			builder.addTextBody("description", description);
			
			HttpEntity entity = builder.build();
			mPost.setEntity(entity);
			
			HttpResponse response = client.execute(targetHost, mPost);
			HttpEntity resEntity = response.getEntity();

			LOGGER.info("[uploadFile] statusLine >>> "+response.getStatusLine().getStatusCode());

			LOGGER.info(response.getStatusLine());
			
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output = null;
			
			String spacesStore = getPropertyValue(messageSource, "params.alfresco.http.spaces.store");

			LOGGER.info("[uploadFile] Output from Server ....");
			
			while ((output = br.readLine()) != null) {
				
				idAlfresco = parseXML(output, spacesStore, Constantes.ID_ALFRESCO_SIZE);
				
				LOGGER.info(output);
				
				if (idAlfresco != null) {
					return idAlfresco;
				}
			}
			
			EntityUtils.consume(resEntity);
			client.getConnectionManager().shutdown();
			
			return Constantes.EMPTY + response.getStatusLine().getStatusCode();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}
	}

	/**
	 * @param user
	 * @param pass
	 * @param ip
	 * @param port
	 * @return Retorna el ticket de acceso.
	 * @throws Exception 
	 */
	public String login(String user, String pass, String ip, String port) throws Exception {
		try {
			StringBuilder url = new StringBuilder();
			url.append(getPropertyValue(messageSource, "params.alfresco.http.url"));
			url.append(ip);
			url.append(Constantes.DOS_PUNTOS);
			url.append(port);
			url.append(getPropertyValue(messageSource, "params.alfresco.http.alfresco"));
			url.append(getPropertyValue(messageSource, "params.alfresco.http.service"));
			url.append(user);
			url.append(getPropertyValue(messageSource, "params.alfresco.http.pass"));
			url.append(pass);

			LOGGER.info("[login] url >>> "+url.toString());
			
			HttpClient client = new DefaultHttpClient();

			HttpGet mGet = new HttpGet(url.toString());
			mGet.addHeader("accept", "application/json");
			
			String ticket = getPropertyValue(messageSource, "params.alfresco.http.ticket");

			HttpResponse response = client.execute(mGet);
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String output = null;
			while ((output = br.readLine()) != null) {
				String tmp = parseXML(output, ticket, Constantes.TICKET_SIZE);
				if (tmp != null) {
					LOGGER.info("[login] ticket >>> "+tmp);
					return tmp;
				}
			}
			client.getConnectionManager().shutdown();
			mGet.releaseConnection();
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}
	}

	/**
	 * @param out
	 * @param att
	 * @param size
	 * @return Obtiene información necesaria de las respuestas Http hechas al webservices.
	 * @throws Exception 
	 */
	public String parseXML(String out, String att, int size) throws Exception {
		LOGGER.info("[parseXML] out >>> "+out);
		if (out.indexOf(att) > 0) {
			return out.substring(out.indexOf(att) + att.length() + 1, out.indexOf(att) + att.length() + 1 + size);
		} else {
			if (out.indexOf("Exception") > 0) {
				throw new Exception();
			} else {
				return null;
			}
		}
	}

	/**
	 * @param id
	 * @return Retorna la URL para la descarga del archivo.
	 * @throws Exception 
	 */
	public String downloadFile(String id) throws Exception {
		try {
			String ip = getPropertyValue(messageSource, "params.alfresco.ip");
			String port = getPropertyValue(messageSource, "params.alfresco.port");
			String user = getPropertyValue(messageSource, "params.alfresco.user");
			String pass = getPropertyValue(messageSource, "params.alfresco.pass");
			
			LOGGER.info("[downloadFile] ip >>> "+ip);
			LOGGER.info("[downloadFile] port >>> "+port);
			LOGGER.info("[downloadFile] user >>> "+user);
			LOGGER.info("[downloadFile] pass >>> "+pass);
			
			String ticket = login(user, pass, ip, port);
			
			StringBuilder url = new StringBuilder();
			url.append(getPropertyValue(messageSource, "params.alfresco.http.url"));
			url.append(ip);
			url.append(Constantes.DOS_PUNTOS);
			url.append(port);
			url.append(getPropertyValue(messageSource, "params.alfresco.http.alfresco"));
			url.append(getPropertyValue(messageSource, "params.alfresco.http.worskpace"));
			url.append(id);
			url.append(getPropertyValue(messageSource, "params.alfresco.http.alf_ticket"));
			url.append(ticket);

			LOGGER.info("[downloadFile] url >>> "+url.toString());
			
			return url.toString();			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception();
		}
	}
	
	/**
	 * Método que lee una propiedad y retorna la ruta de la web principal.
	 * @param key - Llave de codigo de valor;
	 * @return El valor de la propiedad correspondiente a la llave.
	 */
//	private String getPropertyValue(messageSource, String key) {
//		Properties properties = new Properties();
//		String directorio = null;
//		InputStream inputStream = null;
//		try {
//			inputStream = context.getResourceAsStream(Constantes.FICHERO_CONFIGURACION);
//			properties.load(inputStream);
//			if (inputStream == null) {
//				throw new FileNotFoundException("Archivo properties '" + Constantes.FICHERO_CONFIGURACION +"' no se encuentra en el classpath");
//			}
//			directorio = properties.getProperty(key);
//		} catch (IOException e) {			
//			LOGGER.error(e.getMessage(), e);
//		} finally {
//		    if (inputStream != null) {
//		    	try {
//					inputStream.close();
//				} catch (IOException e) {
//					LOGGER.error(e.getMessage(), e);
//				}
//		    }
//		}
//		return directorio;
//	}
	
	/**
	 * Obtener mensaje i18n con Locale.Default
	 * 
	 * @param messageSource
	 * @param mensaje
	 * @return Retorna el mensaje del archivo properties.
	 */
	private static String getPropertyValue(MessageSource messageSource, String mensaje) {
		return messageSource.getMessage(mensaje, null, Locale.getDefault());
	}

}
