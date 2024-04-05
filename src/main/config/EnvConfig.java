package main.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    public static Dotenv load() {

        return Dotenv.configure().directory("/assets").filename(".env").load();
    }
}
