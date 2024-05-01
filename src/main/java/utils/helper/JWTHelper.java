package utils.helper;

import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWTHelper {
    public static boolean isJWTExpired(DecodedJWT decodedJWT) {
        Date expiresAt = decodedJWT.getExpiresAt();
        return expiresAt.before(new Date());
    }
}
