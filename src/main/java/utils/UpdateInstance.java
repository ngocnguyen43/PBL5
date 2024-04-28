package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;

public class UpdateInstance {
    public static void update(Object instanceA, Object instanceB) {
        try {
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(instanceA));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Field[] fields = instanceA.getClass().getDeclaredFields(); // Get all fields of MyClass using reflection
        for (Field field : fields) {
            try {
                Object value = field.get(instanceB);
                // If the value is not null, update the corresponding property in instanceA
                if (value != null) {
                    field.set(instanceA, value);
                    System.out.println(field.getName() + " of instance A updated to: " + value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
}
