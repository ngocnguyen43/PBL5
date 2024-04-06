package config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    public static Dotenv load() {
        String env = System.getenv().get("ENV");
        return Dotenv.configure().filename( env != null && env.equals("PROD") ? "env.prod" :"env").load();
    }
}
