package pe.com.sigbah.web.controller.common;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @className: JsonUtils.java
 * @description:
 * @date: 23/07/2017
 * @author: Junior Huaman Flores.
 */
public class JsonUtils {

	private JsonUtils() {
	}

	/**
	 * @param value
	 * @return json
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String toJson(Object value) throws JsonGenerationException, JsonMappingException, IOException {
		JacksonObjectMapper mapper = new JacksonObjectMapper();
		return mapper.writeValueAsString(value);
	}

}
