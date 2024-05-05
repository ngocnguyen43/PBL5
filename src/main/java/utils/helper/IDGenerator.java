package utils.helper;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

import java.util.Random;

public class IDGenerator {
    private static final char[] salt = "_-0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
            .toCharArray();

    public static String generate(Integer length) {
        Random random = new Random();
        return NanoIdUtils.randomNanoId(random, salt, length);
    }
}
