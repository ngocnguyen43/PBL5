package utils.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Helper {
    private static final Logger logger = Logger.getLogger(Helper.class.getName());
    private final String data;

    public Helper(String data) {
        this.data = data;
    }

    public static Helper of(BufferedReader reader) {
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
//		System.out.println(sb.toString());
        return new Helper(sb.toString());
    }

    public static <T> T objectMapper(Object object, Class<T> tClass) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.convertValue(object, tClass);
    }

    public static Helper paramsToString(Map<String, String[]> paramsMap) {
//		Map<String,String[]> data = req.getParameterMap();
        Map<String, String> params = new HashMap<>();
        paramsMap.forEach((key, value) -> {
            params.put(key, value[0]);
        });
        String value;
        try {
            value = new ObjectMapper().writeValueAsString(params);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new Helper(value);
    }

    public <T> T toModel(Class<T> tClass) {
        try {
            return new ObjectMapper().readValue(data, tClass);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public <T> T toModel(TypeReference<T> types) {
        try {

            return new ObjectMapper().readValue(data, types);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
