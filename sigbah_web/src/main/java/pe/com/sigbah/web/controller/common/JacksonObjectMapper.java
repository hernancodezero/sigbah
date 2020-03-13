package pe.com.sigbah.web.controller.common;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/**
 * @className: JacksonObjectMapper.java
 * @description:
 * @date: 23/07/2017
 * @author: Junior Huaman Flores.
 */
public class JacksonObjectMapper extends ObjectMapper {

	/**
	 * 
	 */
	public JacksonObjectMapper() {
		configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
	}
}