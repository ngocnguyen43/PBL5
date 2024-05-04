package utils.helper;


import utils.contants.USER_PERMISSIONS;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetAllPermissions {
    private static final Logger LOGGER = Logger.getLogger(GetAllPermissions.class.getName());
    private static final Class<USER_PERMISSIONS> clazz = USER_PERMISSIONS.class;

    public static String[] GetAllForAdmin() {
        List<String> permissions = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(String.class)) {
                try {
                    String permission = (String) field.get(null);
                    if (!permission.endsWith("_ORDER")
                            && !permission.equals("CREATE_SCHEDULE")
                            && !permission.equals("UPDATE_SCHEDULE")
                            && !permission.equals("DELETE_SCHEDULE")) {
                        permissions.add(permission);
                    }
                } catch (IllegalAccessException e) {
                    LOGGER.log(Level.WARNING, e.getMessage());
                }
            }
        }
        return permissions.toArray(new String[0]);
    }

    public static String[] GetAllForCustomer() {
        List<String> permissions = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(String.class)) {
                try {
                    String permission = (String) field.get(null);
                    if (permission.endsWith("_ORDER")
                            || permission.endsWith("_SELF")
                            || permission.equals("READ_ORDERS")) {
                        permissions.add(permission);
                    }
                } catch (IllegalAccessException e) {
                    LOGGER.log(Level.WARNING, e.getMessage());
                }
            }
        }
        return permissions.toArray(new String[0]);
    }

    public static String[] GetAllForEmployee() {
        return null;
    }

    public static String[] GetAllForProvider() {
        List<String> permissions = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(String.class)) {
                try {
                    String permission = (String) field.get(null);
                    if ( permission.endsWith("_REQUESTS")
                            || permission.endsWith("_REQUEST")
                            || permission.endsWith("_SCHEDULE")
                            || permission.endsWith("_SCHEDULES")
                            || permission.endsWith("_SELF")) {
                        permissions.add(permission);
                    }
                } catch (IllegalAccessException e) {
                    LOGGER.log(Level.WARNING, e.getMessage());
                }
            }
        }
        return permissions.toArray(new String[0]);
    }
}
