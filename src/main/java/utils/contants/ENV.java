package utils.contants;

import config.EnvConfig;

public interface ENV {
    String SECRET = EnvConfig.load().get("SECRET");
    long ACCESS_TOKEN_EXPIRES_TIME = System.currentTimeMillis() + Integer.parseInt(EnvConfig.load().get("ACCESS_TOKEN_DURATION"));
    long REFRESH_TOKEN_EXPIRES_TIME = System.currentTimeMillis() + Integer.parseInt(EnvConfig.load().get("REFRESH_TOKEN_DURATION"));
}
