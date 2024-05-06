package utils.helper;

import java.lang.reflect.Field;

public class ObjectMerge {
    public static <T> T merge(T a, T b) throws IllegalAccessException {
        Class<?> clazz = a.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object valueA = field.get(a);
            Object valueB = field.get(b);

            if (valueA == null && valueB != null) {
                field.set(a, valueB);
            }
        }

        return a;
    }
}
