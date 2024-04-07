package utils.helper;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class HashPassword {
    public static String Hash(String password) {
        String Salt = BCrypt.gensalt("$2b", 10);
        return BCrypt.hashpw(password, Salt);
    }
}
