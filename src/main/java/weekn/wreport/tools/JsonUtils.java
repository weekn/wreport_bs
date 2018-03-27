package weekn.wreport.tools;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	private final static ObjectMapper json_mapper = new ObjectMapper();

	/**
	 * 将json string反序列化成对象
	 */
	public static <T> T decode(String json, Class<T> valueType)
			throws JsonParseException, JsonMappingException, IOException {
		return json_mapper.readValue(json, valueType);
	}
	
	public static String encode(Object obj) throws JsonProcessingException {
		return json_mapper.writeValueAsString(obj);
	}
}
